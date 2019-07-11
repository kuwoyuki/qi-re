const { Cookie } = require("tough-cookie");
const got = require("got");
const { userAgent, wdToken, sign } = require("../helpers");

const apiUrl = "https://idruid.webnovel.com";

function client({ uuid, cookieJar }) {
  cookieJar.setCookieSync(
    new Cookie({
      key: "loginsign",
      value: encodeURIComponent(sign.cookie(uuid))
    }),
    apiUrl
  );
  cookieJar.setCookieSync(
    new Cookie({
      key: "imei",
      value: uuid
    }),
    apiUrl
  );

  return got.extend({
    baseUrl: "https://idruid.webnovel.com/app/api",
    headers: {
      "User-Agent": userAgent(uuid),
      wdToken: wdToken(uuid)
    },
    cookieJar,
    json: true
  });
}

module.exports = client;
