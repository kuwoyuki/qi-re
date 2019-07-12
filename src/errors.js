/**
 * Authentication error
 *
 * @class AuthError
 * @extends {Error}
 */
class AuthError extends Error {
  constructor(message, code, options = {}) {
    super(message);
    Error.captureStackTrace(this, this.constructor);

    this.name = "AuthError";
    this.code = code;

    Object.defineProperty(this, "options", {
      value: options
    });
  }
}

module.exports = { AuthError };
