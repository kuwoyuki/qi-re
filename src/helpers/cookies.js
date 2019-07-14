/**
 * Build a cookie array, used for API requests
 *
 * @param {Object} user
 * @param {string} user.userid - user id
 * @param {string} user.ukey - user session key
 * @returns {Array<Object>}
 */
function buildCookies({ userid, ukey }) {
  return [
    { key: "ywkey", value: ukey },
    { key: "ywguid", value: userid },
    {
      key: "lang",
      value: "cn"
    },
    {
      key: "domain",
      value: ".webnovel.com"
    },
    {
      key: "appId",
      value: "901"
    },
    {
      key: "areaId",
      value: "1"
    },
    {
      key: "webnovel-language",
      value: "en"
    },
    {
      key: "webnovel-content-language",
      value: "en"
    }
  ];
}

module.exports = buildCookies;
