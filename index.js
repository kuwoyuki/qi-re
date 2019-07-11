/* global BigInt */
const login = require("./src/methods/login");
// const ping = require("./src/methods/ping");
// const tasks = require("./src/methods/tasks");
// const { AuthError } = require("./src/errors");
const { Cookie, CookieJar } = require("tough-cookie");
const UUID = require("./src/helpers/uuid");
const user = require("./src/methods/user");
const setCookies = require("./src/helpers/loginResponse");
const readline = require("readline-promise").default;

const rlp = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
  terminal: true
});

/**
 * Login into webnovel
 *
 * @param {*} { credentials, token, cookieJar }
 * @returns {Array<Cookie>}
 */
async function userLogin({ uuid, credentials, cookieJar }) {
  const {
    data: { userid, ukey, ticket }
  } = await login.android({ uuid, ...credentials }, cookieJar);

  // validate login
  const status = await login.validate({
    uuid,
    userId: userid,
    userKey: ukey,
    ticket
  });

  // TODO: rm
  console.log(status);

  setCookies({ userid, ukey }, cookieJar);
  // return [
  //   new Cookie({ key: "ywkey", value: ukey }),
  //   new Cookie({ key: "ywguid", value: userid }),
  //   new Cookie({ key: "webnovel-language", value: "en" }),
  //   new Cookie({ key: "webnovel-content-language", value: "en" })
  // ];
}

// TODO: combolist
async function handler(credentials) {
  const currentUrl = "https://www.webnovel.com";
  const cookieJar = new CookieJar();

  // we're android now
  const x = BigInt(Math.floor(1000000000 + Math.random() * 2000000000));
  const uuid = new UUID(x, x);

  try {
    // wait for cookies to be set
    await userLogin({ uuid, credentials });
  } catch (err) {
    if (!err.options || err.options.code !== 11318) {
      throw err;
    }

    console.error(err.options);

    const {
      data: { encry }
    } = await login.sendTrustEmail(uuid, err.options.data.encry);

    console.log(encry);

    // TODO: tempmail api, or some imap client
    const code = await rlp.questionAsync("Webnovel Guard code: ");

    const codeRes = await login.checkCode(uuid, encry, code);

    console.log(codeRes);

    setCookies(codeRes.data, cookieJar);
  }

  // TODO: "loginsign" cookie needs to updated on every req
  // i just did it inside the user method rn..
  const { body } = await user.current(uuid, cookieJar);
  console.log(body);
}

(async () => {
  await handler({
    username: "",
    password: ""
  });
})();
