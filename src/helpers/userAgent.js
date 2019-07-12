/**
 * Creates a useragent string used by the android app
 *
 * @param {string} uuid - UUID
 * @returns {string} useragent string
 */
const userAgent = (uuid) =>
  `Mozilla/mobile QDHWReaderAndroid/3.8.2/183/2000002/${uuid}`;

module.exports = userAgent;
