/* global BigInt */

function digits(val, d) {
  const hi = BigInt(1) << (BigInt(d) * BigInt(4));
  return (hi | (val & (hi - BigInt(1)))).toString(16).substring(1);
}

/**
 * UUID builder from (MSB, LSB) values like Java's
 *
 * @class UUID
 */
class UUID {
  constructor(mostSigBits, leastSigBits) {
    this.mostSigBits = mostSigBits;
    this.leastSigBits = leastSigBits;
  }

  /**
   * get the UUID string, pass empty string for no separator (webnovel needs it)
   *
   * @param {string} separator=-
   * @returns {string}
   * @memberof UUID
   */
  toString(separator = "-") {
    return (
      digits(this.mostSigBits >> BigInt(32), 8) +
      separator +
      digits(this.mostSigBits >> BigInt(16), 4) +
      separator +
      digits(this.mostSigBits, 4) +
      separator +
      digits(this.leastSigBits >> BigInt(48), 4) +
      separator +
      digits(this.leastSigBits, 12)
    );
  }
}

module.exports = UUID;
