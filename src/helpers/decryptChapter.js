const crypto = require("crypto");

/**
 * sha1 hmac hash
 * @param {string} key
 * @param {string} data
 */
const sha1Hmac = (key, data) =>
  crypto
    .createHmac("SHA1", key)
    .update(data)
    .digest("base64")
    .substring(0, 24);

/**
 * md5 hmac hash
 * @param {string} key
 * @param {string} data
 */
const md5Hmac = (key, data) =>
  crypto
    .createHmac("MD5", key)
    .update(data)
    .digest("base64")
    .substring(0, 24);

/**
 * first des3 decode
 * @param {string} data
 * @param {string} secret
 * @returns {Buffer}
 */
function desDecode(data, secret) {
  const key = Buffer.from(secret);
  const iv = Buffer.alloc(8);
  const cipher = crypto.createDecipheriv("DES-EDE3-CBC", key, iv);

  const buffers = [cipher.update(data, "base64")];
  buffers.push(cipher.final());

  return Buffer.concat(buffers);
}

/**
 * 2nd des3 decode
 * @param {Buffer} data
 * @param {string} secret
 * @returns {string}
 */
function desDecrypt(data, secret) {
  const key = Buffer.from(secret);
  const iv = Buffer.alloc(8);
  const cipher = crypto.createDecipheriv("DES-EDE3-CBC", key, iv);
  const decrypted =
    cipher.update(data, undefined, "utf8") + cipher.final("utf8");

  return decrypted;
}

/**
 * Decrypt chapter content. Depends on the caller to convert all args to strings
 * @param {string} cid - chapter ID
 * @param {string} bs - encrypted chapter content
 * @param {string} uid - user ID
 * @param {string} imei - IMEI (UUID)
 */
function decrypt(cid, bs, uid, imei) {
  const sec1 = sha1Hmac(
    imei,
    uid + imei + cid + "2EEE1433A152E84B3756301D8FA3E69A"
  );
  const sec2 = md5Hmac(uid, sec1 + imei);

  return desDecrypt(desDecode(bs, sec2), sec1);
}

module.exports = decrypt;
