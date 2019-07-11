const AES = require("../crypto/aes");

const aes = new AES();

/**
 * works.
 */
const token = (uuid) =>
  aes.encrypt(
    `${uuid}|3.8.2|1080|1920|2000002|7.1.1|1|FOO|183|2000002|${Date.now()}|1||||`
  );

module.exports = token;
