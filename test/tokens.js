const { sign, wdToken } = require("../src/helpers");

const uuid = "000000008fd8e514000000008fd8e514";

console.log(encodeURIComponent(sign.cookie(uuid)));
console.log(sign.payload(uuid));
console.log(wdToken(uuid));
