const d3 = require("../crypto/des3");

/**
 * unix timestamp w/o ms
 */
const ts = () => (+new Date() / 1000) | 0;

module.exports = {
  /**
   * w/ second uuid (form payload)
   */
  payload: (uuid) => d3(`${uuid}|${uuid}|${ts()}`),
  /**
   * w/o second uuid (loginsign) cookie
   */
  cookie: (uuid) => d3(`${uuid}||${ts()}`)
};
