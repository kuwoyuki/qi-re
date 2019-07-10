const login = require("./src/methods/login");
const ping = require("./src/methods/ping");
const tasks = require("./src/methods/tasks");
const { AuthError } = require("./src/errors");
const { Cookie, CookieJar } = require("tough-cookie");

/**
 * Login into webnovel
 *
 * @param {*} { credentials, token, cookieJar }
 * @returns {Array<Cookie>}
 */
async function userLogin({ credentials, token, cookieJar }) {
  const { body } = await login.web(
    { ...credentials, _csrfToken: token },
    cookieJar
  );

  const {
    code,
    data: { uid, ukey }
  } = body;

  if (code !== 0) {
    throw new AuthError("Login failed.", 1, { body });
  }

  return [
    new Cookie({ key: "ukey", value: ukey }),
    new Cookie({ key: "uid", value: uid })
  ];
}

// TODO: combolist
async function handler(credentials) {
  const currentUrl = "https://www.webnovel.com";
  const cookieJar = new CookieJar();

  // set csrf/uuid
  await ping(cookieJar);
  const [{ value: token }] = cookieJar.getCookiesSync(currentUrl);

  // login for these creds
  const ctxLogin = async () => {
    const cookies = await userLogin({ cookieJar, token, credentials });
    // set user session cookies
    for (const c of cookies) {
      console.log(c);
      cookieJar.setCookieSync(c, currentUrl);
    }
  };

  for (;;) {
    try {
      // wait for cookies to be set
      await ctxLogin();

      const ctx = { cookieJar, query: { _csrfToken: token } };
      const { body } = await tasks.list(ctx);

      console.log(body);
    } catch (err) {
      if (err.name === "AuthError") {
        console.error(err.options);
        throw err;
      }
      // TODO: only loop on login errors (rethrow rest)
      console.error(err);
    }
  }
}

(async () => {
  await handler({
    username: "berdudadro@desoz.com",
    password: "123123qwe"
  });
})();
