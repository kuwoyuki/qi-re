/* global BigInt */
const { CookieJar, Cookie } = require("tough-cookie");
const {
  UUID,
  buildCookies,
  clients,
  defaults,
  sign,
  decryptChapter
} = require("./helpers");
const { AuthError } = require("./errors");

/**
 * Webnovel client
 *
 * @class Client
 * @classdesc Webnovel client, instantiate, login then call the API endpoints using `this.apiClient`
 */
class Client {
  /**
   * Creates an instance of Webnovel Client.
   * @param {Object} obj
   * @param {string} obj.username - Webnovel username
   * @param {string} obj.password - Webnovel password
   * @param {string} obj.apiURL - override Webnovel API endpoint (Qidian should work)
   * @param {string} obj.authURL - override Webnovel auth API endoint (Qidian should work)
   * @param {string} obj.uuid - UUID is auto generated if not passed, which will trigger mail verification
   * @memberof Client
   */
  constructor({ username, password, apiURL, authURL, uuid }) {
    this.ctx = {
      credentials: { username, password },
      cookieJar: new CookieJar(),
      apiURL: apiURL || "https://idruid.webnovel.com",
      authURL: authURL || "https://ptlogin.webnovel.com/sdk",
      uuid
    };

    if (!uuid) {
      const x = BigInt(Math.floor(1e9 + Math.random() * 21e8)) * -1;
      const y = BigInt(Math.floor(1e9 + Math.random() * 21e8));

      this.ctx.uuid = new UUID(x, y);
    }

    // IMEI cookie
    this.ctx.cookieJar.setCookieSync(
      new Cookie({
        key: "imei",
        value: uuid
      }),
      this.ctx.apiURL
    );

    /**
     * Got auth (passport endpoint) client instance
     * @type {got.GotInstance<got.GotJSONFn>}
     * @public
     */
    this.authClient = clients.auth(this.ctx);
    /**
     * Got API (idroid) client instance
     * @type {got.GotInstance<got.GotJSONFn>}
     * @public
     */
    this.apiClient = clients.api(this.ctx);
  }

  /**
   * Set tough-cookies
   *
   * @private
   * @param {Object} user
   * @param {string} user.userid
   * @param {string} user.ukey
   * @memberof Client
   */
  setCookies(user) {
    this.ctx.userId = user.userid;
    this.ctx.userKey = user.ukey;

    const cookies = buildCookies(user);

    for (const c of cookies) {
      this.ctx.cookieJar.setCookieSync(c, this.ctx.apiURL);
    }
  }

  /**
   * Validate a session after a successful authentication
   *
   * @param {Object} obj
   * @param {string} obj.userId
   * @param {string} obj.userKey
   * @param {string} obj.ticket
   * @returns {got.GotPromise<any>}
   * @memberof Client
   * @private
   */
  validate(userId, userKey, ticket) {
    return this.apiClient.post("/user/login-validate", {
      form: true,
      body: {
        userId,
        userKey,
        ticket,
        ...defaults.validate
      }
    });
  }

  /**
   * Send a verification email
   *
   * @private
   * @param {string} encry
   * @returns {got.GotPromise<any>}
   * @memberof Client
   */
  sendEmail(encry) {
    return this.authClient.post("/sendtrustemail", {
      body: {
        ...defaults.login,
        signature: sign.payload(this.ctx.uuid),
        encry
      }
    });
  }

  /**
   * Login using email verification code (if login method returned code 11318)
   *
   * @param {string} encry - encry property from the failed login response
   * @param {string} code - email verif. code
   * @returns {Object} user info
   * @memberof Client
   * @throws {AuthError}
   */
  async confirmCode(encry, code) {
    const { body } = this.authClient.post("/checktrust", {
      body: {
        ...defaults.login,
        signature: sign.payload(this.ctx.uuid),
        encry,
        code
      },
      json: true
    });

    const { data } = body;

    // validate session
    const { body: val } = await this.validate(
      data.userid,
      data.ukey,
      data.ticket
    );

    if (val.Result !== 0) {
      throw new AuthError("Validation failed.", "INVLD", { body: val });
    }

    this.setCookies(data);

    return body;
  }

  /**
   * Login into Webnovel
   *
   * @param {boolean} emailVer=false - Set to true if you want it to pass and send an email with a ver. code
   * @returns {Object} user info
   * @memberof Client
   * @throws {AuthError}
   */
  async login(emailVer = false) {
    const {
      ctx: { credentials, uuid }
    } = this;

    const { body } = await this.authClient.post("/emaillogin", {
      body: {
        signature: sign.payload(uuid),
        ...defaults.login,
        nextAction: "0",
        type: "1",
        ...credentials
      }
    });

    // Call email verif method with err.options.body.data.encry if you want to handle these
    if (body.code === 11318) {
      if (!emailVer) {
        throw new AuthError(
          "Email verification required. Set 'emailVer' arg to true to not throw and send verification emails.",
          "EMAIL",
          { body }
        );
      }

      // send a verification email
      const { body: ver } = await this.sendEmail(body.data.encry);

      return ver;

      // nextAction === 1 means recaptcha
      // https://developers.google.com/android/reference/com/google/android/gms/safetynet/SafetyNetClient#verifyWithRecaptcha(java.lang.String)
      // the siteKey can be found in plaintext in the app but Google's link gen method needs to be reversed..
    } else if (body.code !== 0 || body.data.nextAction === 1) {
      throw new AuthError("Authentication error.", "ERROR", { body });
    }

    const { data } = body;

    // validate session
    const { body: val } = await this.validate(
      data.userid,
      data.ukey,
      data.ticket
    );

    if (val.Result !== 0) {
      throw new AuthError("Validation failed.", "INVLD", { body: val });
    }

    this.setCookies(data);

    return body;
  }

  /**
   * gets and decrypts a chapter, unauthenticated requests probably won't work
   *
   * @param {string} bookId
   * @param {string} chapterId
   * @memberof Client
   * @returns {Object}
   */
  async getChapter(bookId, chapterId) {
    const { body } = await this.apiClient("/book/get-chapter", {
      query: {
        bookId,
        chapterId
      }
    });

    body.Data.ContentItems = JSON.parse(
      decryptChapter(
        String(chapterId),
        body.Data.ContentItems,
        String(this.ctx.userId),
        this.ctx.uuid
      )
    ).ContentItems;

    return body;
  }
}

module.exports = Client;
