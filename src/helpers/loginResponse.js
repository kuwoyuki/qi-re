const { Cookie } = require("tough-cookie");

const apiUrl = "https://idruid.webnovel.com";

function parse({ userid, ukey }, cookieJar) {
  const cookies = [
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

  for (const c of cookies) {
    cookieJar.setCookieSync(c, apiUrl);
  }
}

module.exports = parse;
