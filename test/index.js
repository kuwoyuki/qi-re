const { Client: WNClient } = require("../index");

(async () => {
  const username = "wijo@4easyemail.com";

  const client = new WNClient({
    username,
    password: "UT6bp8X3Nee6XwJGYC",
    uuid: "000000003ede1bf9000000003ede1bf9"
  });

  const res = await client.login(true);

  // Since we set emailVer = true we need to manually check for "encry" (email verification token)
  // you can of course catch and call client.sendEmail() on your own
  const {
    data: { encry }
  } = res;

  // if you want their auth "HELLO" response
  let user;

  if (encry) {
    // get the emailed code
    // const code = await getCodeUsingIMAP(email);
    const code = "ABCDE";
    user = await client.confirmCode(encry, code); // cookies get set here
  } else {
    user = res;
  }

  console.log(user);

  const {
    body: {
      Data: { Email }
    }
  } = await client.apiClient("/user/get");

  console.log(Email === username); // true

  // Ze Tian Ji 😍
  const bookId = "8205217405006105";

  // destructure the first chapter
  // *note*: check com.qidian.QDReader.components.book.al.QDChapterManager
  // you probably need to send some other requests, I'm getting some incorrect
  // chapter IDs..
  const {
    body: {
      Data: {
        Chapters: [, { Id: secChpt }]
      }
    }
  } = await client.apiClient("/book/get-chapters", {
    query: {
      bookId,
      maxUpdateTime: 0,
      maxIndex: 0,
      sign: ""
    }
  });

  // have fun
  const chapter = await client.getChapter(bookId, secChpt);
  console.log(chapter);
})();
