const { Cookie } = require("tough-cookie");
const got = require("got");
const { userAgent, wdToken, sign } = require("../helpers");

function current(uuid, cookieJar) {
  cookieJar.setCookieSync(
    new Cookie({ key: "loginsign", value: sign.cookie(uuid) }),
    "https://www.webnovel.com"
  );

  return got("https://idruid.webnovel.com/app/api/user/get", {
    headers: {
      "User-Agent": userAgent(uuid),
      wdToken: wdToken(uuid)
    },
    cookieJar,
    json: true
  });
}

module.exports = {
  current
};
