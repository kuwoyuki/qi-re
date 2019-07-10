const got = require("got");

const list = ({ cookieJar, _csrfToken }) =>
  got("https://www.webnovel.com/apiajax/task/taskList", {
    query: { taskType: 1, _csrfToken },
    cookieJar,
    json: true
  });

module.exports = { list };
