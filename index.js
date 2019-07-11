/* global BigInt */
const UUID = require("./src/helpers/uuid");
const login = require("./src/methods/login");
const { CookieJar } = require("tough-cookie");
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
 * @param {*} { uuid, credentials, cookieJar }
 * @returns {void}
 */
async function userLogin({ uuid, credentials, cookieJar }) {
  const {
    data: { userid, ukey, ticket }
  } = await login.android({ uuid, ...credentials }, cookieJar);

  // validate login
  await login.validate({
    uuid,
    userId: userid,
    userKey: ukey,
    ticket
  });

  setCookies({ userid, ukey }, cookieJar);
}

async function authenticate(ctx) {
  try {
    // wait for cookies to be set
    await userLogin(ctx);
  } catch (err) {
    if (!err.options || err.options.code !== 11318) {
      throw err;
    }

    const {
      data: { encry }
    } = await login.sendTrustEmail({ ...ctx, encry: err.options.data.encry });

    // TODO: tempmail api, or some imap client
    const code = await rlp.questionAsync("Webnovel Guard code: ");

    const { data } = await login.confirmCode({ ...ctx, encry, code });

    // validate login
    await login.validate({
      userId: data.userid,
      userKey: data.ukey,
      ticket: data.ticket
    });

    setCookies(data, ctx.cookieJar);
  }
}

async function work(ctx) {
  const {
    body: { Data: userInfo }
  } = await user.current(ctx);

  // TODO: farm SS or some shit

  console.log(userInfo);
}

async function handler(credentials) {
  const cookieJar = new CookieJar();

  // we're android now
  // const x = BigInt(Math.floor(1000000000 + Math.random() * 2000000000));

  // TODO: make an account -> UUID cache so we don't trigger verification mechanism
  // const uuid = new UUID(x, x).toString("");
  const uuid = "000000003ede1bf9000000003ede1bf9";

  const ctx = {
    uuid,
    credentials,
    cookieJar
  };

  // auth
  await authenticate(ctx);

  // work
  setInterval(async () => {
    try {
      await work(ctx);
    } catch (err) {
      console.error(err);
      await authenticate(ctx);
      await work(ctx);
    }
  }, 5 * 10e2);
}

(async () => {
  // TODO: combolist
  await handler({
    username: "wijo@4easyemail.com",
    password: "UT6bp8X3Nee6XwJGYC"
  });
})();
