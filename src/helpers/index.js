module.exports = {
  userAgent: (uuid) =>
    `Mozilla/mobile QDHWReaderAndroid/3.8.2/183/2000002/${uuid}`,
  wdToken: require("./wdToken"),
  sign: require("./signatures")
};
