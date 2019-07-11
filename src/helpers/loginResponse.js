const { Cookie } = require("tough-cookie");

function parse(data, cookieJar) {
  const { userid, ukey } = data;

  console.log(data);

  const cookies = [
    new Cookie({ key: "ywkey", value: ukey }),
    new Cookie({ key: "ywguid", value: userid }),
    new Cookie({ key: "webnovel-language", value: "en" }),
    new Cookie({ key: "webnovel-content-language", value: "en" })
  ];

  for (const c of cookies) {
    console.log(c);
    cookieJar.setCookieSync(c, "https://www.webnovel.com");
  }
}

module.exports = parse;
