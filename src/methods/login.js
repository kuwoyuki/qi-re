const got = require("got");
const rsaEncrypt = require("../crypto/rsa");
const { AuthError } = require("../errors");
const { wdToken, userAgent, sign } = require("../helpers");

// TODO: make this a class or something..
const common = {
  source: "2000002",
  autotime: "30",
  version: "3.8.2",
  appid: "901",
  auto: "1",
  areaid: "1",
  format: "json"
};

/**
 * web login
 * @param {Object} q
 * @param {string} q.username - webnovel username
 * @param {string} q.password - webnovel password
 * @param {string} q._csrfToken - CSRF token from cookie
 * @param {CookieJar} cookieJar
 * @returns {GotPromise<string>}
 */
function web(q, cookieJar) {
  const query = {
    appId: 900,
    areaId: 1,
    source: "",
    returnurl: "http://www.webnovel.com",
    version: "",
    imei: "",
    qimei: "",
    target: "",
    format: "",
    ticket: "",
    autotime: "",
    auto: 1, // TODO: ??
    fromuid: 0,
    username: "",
    password: rsaEncrypt(""),
    method: "LoginV1.checkCodeCallback",
    logintype: 22,
    _csrfToken: "",
    ...q
  };

  return got("https://ptlogin.webnovel.com/login/checkcode", {
    query,
    headers: {
      "user-agent":
        "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:69.0) Gecko/20100101 Firefox/69.0"
    },
    cookieJar,
    json: true
  });
}

/**
 * android login method
 * @param {*} { uuid, username, password }
 * @returns
 */
async function android({ uuid, username, password }) {
  const headers = {
    "User-Agent": userAgent(uuid)
  };
  const body = {
    signature: sign.payload(uuid),
    ...common,
    nextAction: "0",
    type: "1",
    username,
    password
  };
  const { body: res } = await got.post(
    "https://ptlogin.webnovel.com/sdk/emaillogin",
    {
      headers,
      body,
      form: true,
      json: true
    }
  );

  if (res.code !== 0) {
    console.error(res);
    throw new AuthError("Authentication failed.", 2, res);
  }

  console.log(res);

  return res;
}

/**
 * post-login validation or sth
 * @param {*} { uuid, ticket, userId, userKey }
 * @returns
 */
async function validate({ uuid, ticket, userId, userKey }) {
  const { body } = await got.post(
    "https://idruid.webnovel.com/app/api/user/login-validate",
    {
      headers: {
        "user-agent": userAgent(uuid),
        wdToken: wdToken(uuid)
      },
      form: true,
      body: {
        userKey,
        appId: "901",
        ticket,
        areaId: "1",
        userId,
        fromSource: "2000002"
      },
      json: true
    }
  );

  if (body.code !== 0) {
    console.error(body);
    throw new AuthError("Login validation failed.", 3, body);
  }

  return body;
}

async function sendTrustEmail(uuid, encry) {
  const { body } = await got.post(
    "https://ptlogin.webnovel.com/sdk/sendtrustemail",
    {
      headers: {
        "user-agent": userAgent(uuid),
        wdToken: wdToken(uuid)
      },
      form: true,
      body: {
        ...common,
        signature: sign.payload(uuid),
        encry
      },
      json: true
    }
  );

  if (body.code !== 0) {
    throw new AuthError("Failed to send mail.", 4, body);
  }

  return body;
}

async function checkCode(uuid, encry, code) {
  const { body } = await got.post(
    "https://ptlogin.webnovel.com/sdk/sendtrustemail",
    {
      headers: {
        "user-agent": userAgent(uuid),
        wdToken: wdToken(uuid)
      },
      form: true,
      body: {
        ...common,
        signature: sign.payload(uuid),
        encry,
        code
      },
      json: true
    }
  );

  if (body.code !== 0) {
    throw new AuthError("Failed to validate mail code.", 5, body);
  }

  return body;
}

module.exports = { android, web, validate, sendTrustEmail, checkCode };
