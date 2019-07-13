const { Cookie } = require("tough-cookie");

/**
 * Build a cookie array, used for API requests
 *
 * @param {Object} user
 * @param {string} user.userid - user id
 * @param {string} user.ukey - user session key
 * @returns {Array<Cookie>}
 */
function buildCookies({ userid, ukey }) {
  return [
    new Cookie({ key: "ywkey", value: ukey }),
    new Cookie({ key: "ywguid", value: userid }),
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
