const got = require("got");
const { Cookie } = require("tough-cookie");
const wdToken = require("./wdToken");
const sign = require("./signatures");
const userAgent = require("./userAgent");

/**
 * Create an auth API Got instance
 *
 * @param {Object} ctx
 * @param {string} ctx.uuid - UUID
 * @param {string} ctx.authURL - Auth API endpoint URL
 * @returns {got.GotInstance<got.GotJSONFn>}
 */
const auth = ({ uuid, authURL }) =>
  got.extend({
    baseUrl: authURL,
    headers: {
      "User-Agent": userAgent(uuid)
    },
    hooks: {
      beforeRequest: [
        (options) => {
          options.headers.wdToken = wdToken(uuid);
        }
      ]
    },
    form: true,
    json: true
  });

/**
 * Create an API (idroid) Got instance
 *
 * @param {Object} ctx
 * @param {string} ctx.uuid - UUID
 * @param {string} ctx.apiURL - API endpoint URL
 * @param {CookieJar} ctx.cookieJar - CookieJar
 * @returns {got.GotInstance<got.GotJSONFn>}
 */
const api = ({ uuid, cookieJar, apiURL }) =>
  got.extend({
    baseUrl: `${apiURL}/app/api`,
    cookieJar,
    headers: {
      "User-Agent": userAgent(uuid),
      wdToken: wdToken(uuid)
    },
    hooks: {
      beforeRequest: [
        (options) => {
          options.headers.wdToken = wdToken(uuid);
          cookieJar.setCookieSync(
            new Cookie({
              key: "loginsign",
              value: encodeURIComponent(sign.cookie(uuid))
            }),
            apiURL
          );
        }
      ]
    },
    json: true
  });

module.exports = { auth, api };
