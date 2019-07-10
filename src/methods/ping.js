const got = require("got");

module.exports = (cookieJar) => got("https://www.webnovel.com/", { cookieJar });
