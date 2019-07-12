const { AES } = require("../crypto");

const aes = new AES();

/**
 * AES encrypted wdToken header - Chinese telemetry =)
 * @param {string} uuid - UUID
 * @returns {string} wdToken
 */
const token = (uuid) =>
  aes.encrypt(
    `${uuid}|3.8.2|1080|1920|2000002|7.1.1|1|FOO|183|2000002|${Date.now()}|1||||`
  );

module.exports = token;
