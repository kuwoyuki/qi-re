const { Client: WNClient } = require("../index");

const reportOperation = (books) =>
  this.apiClient.post("/book-case/report-operation-time", {
    form: true,
    body: {
      bookList: JSON.stringify(
        books.map((bookId) => ({ bookId, operationTime: Date.now() }))
      )
    }
  });

(async () => {
  // account probably got sandboxed anyway..
  const username = "wijo@4easyemail.com";

  const client = new WNClient({
    username,
    password: "UT6bp8X3Nee6XwJGYC",
    uuid: "000000003ede1bf9000000003ede1bf9"
  });

  // ??? does this even work..
  const heartbeat = reportOperation.bind(client);

  // you can also set ctx and resume
  const sessionInfo = await client.login();
  console.log(sessionInfo);

  const bookId = "8205217405006105";

  const book = await client.apiClient("/book/get-book", { query: { bookId } });
  console.log(book.body);

  // heartbeat on the book
  await heartbeat([bookId]);

  const {
    body: { Data: chapters }
  } = await client.apiClient("/book/get-chapters", {
    query: { bookId, maxUpdateTime: 0, maxIndex: 0, sign: "" }
  });

  console.log(chapters);
})();
