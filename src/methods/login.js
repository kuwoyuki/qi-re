const got = require("got");
const encrypt = require("../helpers/crypto");

/**
 *
 *
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
    auto: 1, // TODO: how tf do you use this
    fromuid: 0,
    username: "",
    password: encrypt(""),
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

// TODO: RE the native app login, it doesn't use captcha (uses heavy RSA/base64 *magik* instead)

module.exports = { web };
