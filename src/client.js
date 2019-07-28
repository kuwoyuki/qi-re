/* global BigInt */
const { CookieJar, Cookie, getPublicSuffix } = require("tough-cookie");
const {
  UUID,
  buildCookies,
  clients,
  defaults,
  sign,
  decryptChapter
} = require("./helpers");
const { AuthError } = require("./errors");
const { promisify } = require("util");
const url = require("url");

/**
 * Auth methods session info
 * @typedef {Object} SessionInfo
 * @property {number} code - status code.
 * @property {Object} data - Session data
 * @property {string} data.ticket - Session validation ticket
 * @property {string} data.ukey - Currently logged in user's key (used in jwkey cookie)
 * @property {number} data.autoLoginFlag - Whether we logged in with an autologin flag
 * @property {string} data.autoLoginSessionKey - AL session key
 * @property {number} data.autoLoginKeepTime - AL session lifetime
 * @property {number} data.autoLoginExpiredTime - expiration unix timestamp
 * @property {number} data.userid - user ID
 * @property {string} msg - ok.
 */

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
    /**
     * @property {Object} credentials - Auth credentials
     * @property {CookieJar} cookieJar - CookieJar instance
     * @property {string} apiURL - API base URL
     * @property {string} authURL - Auth API base URL
     * @property {string} uuid - IMEI/UUID,
     * @property {Object} session - Session properties, can be used to manually resume sessions
     * @property {number} session.id - User session ID
     * @property {string} session.key - User session key
     * @property {string} session.autoLoginKey - User session autologin key
     * @property {string} session.autoLoginExpires - Autologin expiration time
     */
    this.ctx = {
      credentials: { username, password },
      cookieJar: new CookieJar(),
      apiURL: apiURL || "https://idruid.webnovel.com",
      authURL: authURL || "https://ptlogin.webnovel.com/sdk",
      uuid,
      session: {}
    };

    if (!uuid) {
      this.ctx.uuid = new UUID().toString("");
    }

    this.ctx.domain = getPublicSuffix(url.parse(this.ctx.apiURL).hostname);
    this.setCookie = promisify(
      this.ctx.cookieJar.setCookie.bind(this.ctx.cookieJar)
    );

    // IMEI cookie
    this.setCookie(
      new Cookie({
        key: "imei",
        value: this.ctx.uuid,
        hostOnly: false,
        domain: this.ctx.domain
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
    this.ctx.session = {
      id: user.userid,
      key: user.ukey,
      autoLoginKey: user.autoLoginSessionKey,
      autoLoginExpires: user.autoLoginExpiredTime
    };

    const cookies = buildCookies(user, this.ctx);

    for (const c of cookies) {
      this.setCookie(
        new Cookie({ ...c, hostOnly: false, domain: this.ctx.domain }),
        this.ctx.apiURL
      );
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
   * @typedef {Object} RegisterPayload
   * @property {number} code - status code
   * @property {Object} data - data payload
   * @property {string} email - email used for the registration
   * @property {string} emailkey - email confirmation code
   * @property {string} msg - status message
   */

  /**
   * Register an account, gets credentials from Client context
   *
   * @memberof Client
   * @returns {RegisterPayload}
   */
  async register() {
    const {
      ctx: { credentials, uuid }
    } = this;

    return this.authClient.post("/doregister", {
      body: {
        signature: sign.payload(uuid),
        ...defaults.login,
        nextAction: "0",
        type: "1",
        account: credentials.username,
        password: credentials.password
      }
    });
  }

  /**
   * Confirm registration and get session creds
   *
   * @param {string} emailkey
   * @param {string} code
   * @returns {Promise<SessionInfo>}
   * @memberof Client
   */
  confirmRegistration(emailkey, code) {
    return this.authClient("/confirm", {
      body: {
        ...defaults.login,
        signature: sign.payload(this.ctx.uuid),
        emailkey,
        code
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
   * @returns {Promise<SessionInfo>}
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
   * @returns {Promise<SessionInfo>}
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
   * Resume current session
   * @returns {Promise<SessionInfo>}
   * @memberof Client
   */
  async resumeSession() {
    const {
      session: { id, key, autoLoginKey, autoLoginExpires }
    } = this.ctx;

    if (autoLoginExpires - ((Date.now() / 1000) | 0) < 0) {
      throw new AuthError("Session expired.");
    }

    const { body } = await this.authClient("/checkstatus", {
      body: {
        ...defaults.login,
        signature: sign.payload(this.ctx.uuid),
        ukey: key,
        alk: autoLoginKey,
        userid: id
      }
    });

    this.setCookies(body.data);

    return body;
  }

  /**
   * gets and decrypts a chapter, unauthenticated requests probably won't work
   *
   * @param {string} bookId
   * @param {string} chapterId
   * @memberof Client
   * @returns {Promise<Object>}
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
        String(this.ctx.session.id),
        this.ctx.uuid
      )
    ).ContentItems;

    return body;
  }
}

module.exports = Client;
