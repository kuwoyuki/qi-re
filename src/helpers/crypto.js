const NodeRSA = require("node-rsa");
const key = new NodeRSA();

// Webnovel's auth RSA key
// https://passport.webnovel.com/login.html
const keyData = `-----BEGIN PUBLIC KEY-----
MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOf5B7Sg/EsfK+29BhFn1SUgoX
gcLP9Dl1Sf3g3PgwRTEkqMwhFVpIYoNVo1TV1q6Y6dRYZ1BExt/tqQqJcLvQhCKc
b4JuINKdftwG5le+Q2n6S/Ioyx7euYZgkmm3LSQ5VW7JmWV9VJFOIm4mpHmom9kE
CwVP/wBG9hmUs+USSwIDAQAB
-----END PUBLIC KEY-----`;

key.importKey(keyData, "public");

module.exports = (str) => key.encrypt(str, "base64");
