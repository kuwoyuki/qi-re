const crypto = require("crypto");

/**
 * des3 encrypt string
 * @param {string} str - utf8 input string
 * @returns {string} des-ede3 -> base64 encoded string
 */
function des3(str) {
  // from DES3Utils.java
  const key = Buffer.from("bMyzJ1D7Kl7zt9mwjegtJGMoF53msSfP").slice(0, 24);
  const iv = Buffer.from("W9F1bXrz");
  const cipher = crypto.createCipheriv("des-ede3-cbc", key, iv);
  const encrypted =
    cipher.update(str, "utf8", "base64") + cipher.final("base64");

  return encrypted;
}

module.exports = des3;
