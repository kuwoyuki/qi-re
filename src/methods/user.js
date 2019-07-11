const client = require("./apiClient");

module.exports = {
  current: (ctx) => client(ctx)("/user/get")
};
