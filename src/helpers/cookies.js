const { Cookie } = require("tough-cookie");

/**
 * Build a cookie array, used for API requests
 *
 * @param {string} userId
 * @param {string} userKey
 * @returns {Array<Cookie>}
 */
function buildCookies(userId, userKey) {
  return [
    new Cookie({ key: "ywkey", value: userId }),
    new Cookie({ key: "ywguid", value: userKey }),
    new Cookie({
      key: "lang",
      value: "cn"
    }),
    new Cookie({
      key: "domain",
      value: ".webnovel.com"
    }),
    new Cookie({
      key: "appId",
      value: "901"
    }),
    new Cookie({
      key: "areaId",
      value: "1"
    }),
    new Cookie({
      key: "webnovel-language",
      value: "en"
    }),
    new Cookie({
      key: "webnovel-content-language",
      value: "en"
    })
  ];
}

module.exports = buildCookies;
