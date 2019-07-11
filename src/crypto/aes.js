const crypto = require("crypto");

/**
 * @class AES
 */
class AES {
  constructor() {
    this.key = Buffer.from("jxmslsiodjfpwe01");
    this.iv = Buffer.from("webnovel-mobiles");
  }

  /**
   * @param {string} str - utf8 string
   * @returns {string} base64 encoded string
   * @memberof AES
   */
  encrypt(str) {
    const cipher = crypto.createCipheriv("aes-128-cbc", this.key, this.iv);
    return cipher.update(str, "utf8", "base64") + cipher.final("base64");
  }

  /**
   * @param {string} str - base64 encoded string
   * @returns {string}
   * @memberof AES
   */
  decrypt(str) {
    const decipher = crypto.createDecipheriv("aes-128-cbc", this.key, this.iv);
    return decipher.update(str, "base64") + decipher.final();
  }
}

module.exports = AES;
