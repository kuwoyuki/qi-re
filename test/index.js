const { Client } = require("../index");

async function authenticate(ctx) {
  // try {
  //   // wait for cookies to be set
  //   await userLogin(ctx);
  // } catch (err) {
  //   if (!err.options || err.options.code !== 11318) {
  //     throw err;
  //   }
  //   const {
  //     data: { encry }
  //   } = await login.sendTrustEmail({ ...ctx, encry: err.options.data.encry });
  //   // TODO: tempmail api, or some imap client
  //   const code = await rlp.questionAsync("Webnovel Guard code: ");
  //   const { data } = await login.confirmCode({ ...ctx, encry, code });
  //   // validate login
  //   await login.validate({
  //     userId: data.userid,
  //     userKey: data.ukey,
  //     ticket: data.ticket
  //   });
  //   setCookies(data, ctx.cookieJar);
  // }
}

async function work(ctx) {
  // const {
  //   body: { Data: userInfo }
  // } = await user.current(ctx);
  // TODO: farm SS or some shit
  // console.log(userInfo);
}

/**
 *
 *
 * @param {Client} client
 */
async function handler(client) {
  // auth
  try {
    const login = await client.login(true);
    console.log(login);
  } catch (err) {
    console.log(err, err.code, err.options);
  }
  // work
  setInterval(async () => {
    try {
      const { body } = await client.apiClient();
      console.log(body);
    } catch (err) {
      console.error(err);
      // await authenticate(ctx);
      // await work(ctx);
    }
  }, 5 * 10e2);
}

(async () => {
  const wnClient = new Client({
    username: "wijo@4easyemail.com",
    password: "UT6bp8X3Nee6XwJGYC",
    uuid: "000000003ede1bf9000000003ede1bf9"
  });
  // wnClient.
  await handler(wnClient);
})();
