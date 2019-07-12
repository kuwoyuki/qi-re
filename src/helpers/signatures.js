const { des3 } = require("../crypto");

/**
 * current unix timestamp w/o ms
 */
const ts = () => (+new Date() / 1000) | 0;

module.exports = {
  /**
   * DES3 signature w/ second uuid (form payload)
   * @param {string} uuid - UUID
   * @returns {string} des3 sig
   */
  payload: (uuid) => des3(`${uuid}|${uuid}|${ts()}`),
  /**
   * DES3 signature w/o second uuid (loginsign) cookie
   * @param {string} uuid - UUID
   * @returns {string} des3 sig
   */
  cookie: (uuid) => des3(`${uuid}||${ts()}`)
};
