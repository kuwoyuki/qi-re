# Webnovel/Qidian API client

![npm bundle size](https://img.shields.io/bundlephobia/min/webnovel.js.svg)
![NPM](https://img.shields.io/npm/l/webnovel.js.svg)
![npm](https://img.shields.io/npm/dm/webnovel.js.svg)

Node.js client for Qidian APIs build by reverse engineering their native application. Uses native app endpoints.

_Note:_ All endpoints default to webnovel.com's.

If you just want to use this as a reference the useful files are:

- [crypto](/src/crypto)
- [wdToken](/src/helpers/wdToken.js)
- [signatures](/src/helpers/signatures.js)
- [decryptChapter](/src/helpers/decryptChapter.js) 😰

The rest is just traffic sniffing.

## Usage

```sh
yarn add webnovel.js
```

```js
const { Client: WNClient } = require("webnovel.js");

(async () => {
  const username = "some@mail.com";

  const client = new WNClient({
    username,
    password: "supersekret",
    uuid: "000000003ede1bf9000000003ede1bf9" // UUID
  });

  const res = await client.login(true);

  // Since we set emailVer = true we need to manually check for "encry" (email verification token)
  // you can of course catch and call client.sendEmail() on your own
  const {
    data: { encry }
  } = res;

  // if you want raw tokens (ticket, autologin)
  let user;

  if (encry) {
    const code = await getCodeUsingIMAP(email); // somehow get the emailed code
    user = await client.confirmCode(encry, code); // cookies now set
  } else {
    user = res;
  }

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
  // chapter IDs.. maybe /book-case/report-operation-time
  const {
    body: {
      Data: {
        Chapters: [, { Id: secChptID }]
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

  const chapter = await client.getChapter(bookId, secChptID);
  // ...
})();
```

The client class only implements complex/encrypted/signed requests, so for the most part you need to manually find the endpoint you need and use the `Client.apiClient` Got instance to request it.  
Most API endpoints are declared in the `com.qidian.QDReader.components.api` package, in the `Urls` class.

Maybe have a look at [examples](/examples) too.
## Classes

<dl>
<dt><a href="#Client">Client</a></dt>
<dd><p>Webnovel client, instantiate, login then call the API endpoints using <code>this.apiClient</code></p>
</dd>
</dl>

## Typedefs

<dl>
<dt><a href="#SessionInfo">SessionInfo</a> : <code>Object</code></dt>
<dd><p>Auth methods session info</p>
</dd>
<dt><a href="#RegisterPayload">RegisterPayload</a> : <code>Object</code></dt>
<dd></dd>
</dl>

<a name="Client"></a>

## Client
Webnovel client, instantiate, login then call the API endpoints using `this.apiClient`

**Kind**: global class

* [Client](#Client)
    * [new Client()](#new_Client_new)
    * _instance_
        * [.ctx](#Client+ctx)
        * [.authClient](#Client+authClient) : <code>got.GotInstance.&lt;got.GotJSONFn&gt;</code>
        * [.apiClient](#Client+apiClient) : <code>got.GotInstance.&lt;got.GotJSONFn&gt;</code>
        * [.register()](#Client+register) ⇒ [<code>RegisterPayload</code>](#RegisterPayload)
        * [.confirmRegistration(emailkey, code)](#Client+confirmRegistration) ⇒ [<code>Promise.&lt;SessionInfo&gt;</code>](#SessionInfo)
        * [.confirmCode(encry, code)](#Client+confirmCode) ⇒ [<code>Promise.&lt;SessionInfo&gt;</code>](#SessionInfo)
        * [.login(emailVer)](#Client+login) ⇒ [<code>Promise.&lt;SessionInfo&gt;</code>](#SessionInfo)
        * [.resumeSession()](#Client+resumeSession) ⇒ [<code>Promise.&lt;SessionInfo&gt;</code>](#SessionInfo)
        * [.getChapter(bookId, chapterId)](#Client+getChapter) ⇒ <code>Promise.&lt;Object&gt;</code>
    * _static_
        * [.Client](#Client.Client)
            * [new Client(obj)](#new_Client.Client_new)

<a name="new_Client_new"></a>

### new Client()
Webnovel client

<a name="Client+ctx"></a>

### client.ctx
**Kind**: instance property of [<code>Client</code>](#Client)
**Properties**

| Name | Type | Description |
| --- | --- | --- |
| credentials | <code>Object</code> | Auth credentials |
| cookieJar | <code>CookieJar</code> | CookieJar instance |
| apiURL | <code>string</code> | API base URL |
| authURL | <code>string</code> | Auth API base URL |
| uuid | <code>string</code> | IMEI/UUID, |
| session | <code>Object</code> | Session properties, can be used to manually resume sessions |
| session.id | <code>number</code> | User session ID |
| session.key | <code>string</code> | User session key |
| session.autoLoginKey | <code>string</code> | User session autologin key |
| session.autoLoginExpires | <code>string</code> | Autologin expiration time |

<a name="Client+authClient"></a>

### client.authClient : <code>got.GotInstance.&lt;got.GotJSONFn&gt;</code>
Got auth (passport endpoint) client instance

**Kind**: instance property of [<code>Client</code>](#Client)
**Access**: public
<a name="Client+apiClient"></a>

### client.apiClient : <code>got.GotInstance.&lt;got.GotJSONFn&gt;</code>
Got API (idroid) client instance

**Kind**: instance property of [<code>Client</code>](#Client)
**Access**: public
<a name="Client+register"></a>

### client.register() ⇒ [<code>RegisterPayload</code>](#RegisterPayload)
Register an account, gets credentials from Client context

**Kind**: instance method of [<code>Client</code>](#Client)
<a name="Client+confirmRegistration"></a>

### client.confirmRegistration(emailkey, code) ⇒ [<code>Promise.&lt;SessionInfo&gt;</code>](#SessionInfo)
Confirm registration and get session creds

**Kind**: instance method of [<code>Client</code>](#Client)

| Param | Type |
| --- | --- |
| emailkey | <code>string</code> |
| code | <code>string</code> |

<a name="Client+confirmCode"></a>

### client.confirmCode(encry, code) ⇒ [<code>Promise.&lt;SessionInfo&gt;</code>](#SessionInfo)
Login using email verification code (if login method returned code 11318)

**Kind**: instance method of [<code>Client</code>](#Client)
**Throws**:

- <code>AuthError</code>


| Param | Type | Description |
| --- | --- | --- |
| encry | <code>string</code> | encry property from the failed login response |
| code | <code>string</code> | email verif. code |

<a name="Client+login"></a>

### client.login(emailVer) ⇒ [<code>Promise.&lt;SessionInfo&gt;</code>](#SessionInfo)
Login into Webnovel

**Kind**: instance method of [<code>Client</code>](#Client)
**Throws**:

- <code>AuthError</code>


| Param | Type | Default | Description |
| --- | --- | --- | --- |
| emailVer | <code>boolean</code> | <code>false</code> | Set to true if you want it to pass and send an email with a ver. code |

<a name="Client+resumeSession"></a>

### client.resumeSession() ⇒ [<code>Promise.&lt;SessionInfo&gt;</code>](#SessionInfo)
Resume current session

**Kind**: instance method of [<code>Client</code>](#Client)
<a name="Client+getChapter"></a>

### client.getChapter(bookId, chapterId) ⇒ <code>Promise.&lt;Object&gt;</code>
gets and decrypts a chapter, unauthenticated requests probably won't work

**Kind**: instance method of [<code>Client</code>](#Client)

| Param | Type |
| --- | --- |
| bookId | <code>string</code> |
| chapterId | <code>string</code> |

<a name="Client.Client"></a>

### Client.Client
**Kind**: static class of [<code>Client</code>](#Client)
<a name="new_Client.Client_new"></a>

#### new Client(obj)
Creates an instance of Webnovel Client.


| Param | Type | Description |
| --- | --- | --- |
| obj | <code>Object</code> |  |
| obj.username | <code>string</code> | Webnovel username |
| obj.password | <code>string</code> | Webnovel password |
| obj.apiURL | <code>string</code> | override Webnovel API endpoint (Qidian should work) |
| obj.authURL | <code>string</code> | override Webnovel auth API endoint (Qidian should work) |
| obj.uuid | <code>string</code> | UUID is auto generated if not passed, which will trigger mail verification |

<a name="SessionInfo"></a>

## SessionInfo : <code>Object</code>
Auth methods session info

**Kind**: global typedef
**Properties**

| Name | Type | Description |
| --- | --- | --- |
| code | <code>number</code> | status code. |
| data | <code>Object</code> | Session data |
| data.ticket | <code>string</code> | Session validation ticket |
| data.ukey | <code>string</code> | Currently logged in user's key (used in jwkey cookie) |
| data.autoLoginFlag | <code>number</code> | Whether we logged in with an autologin flag |
| data.autoLoginSessionKey | <code>string</code> | AL session key |
| data.autoLoginKeepTime | <code>number</code> | AL session lifetime |
| data.autoLoginExpiredTime | <code>number</code> | expiration unix timestamp |
| data.userid | <code>number</code> | user ID |
| msg | <code>string</code> | ok. |

<a name="RegisterPayload"></a>

## RegisterPayload : <code>Object</code>
**Kind**: global typedef
**Properties**

| Name | Type | Description |
| --- | --- | --- |
| code | <code>number</code> | status code |
| data | <code>Object</code> | data payload |
| email | <code>string</code> | email used for the registration |
| emailkey | <code>string</code> | email confirmation code |
| msg | <code>string</code> | status message |

### Web login

Here's the web login version:
_note:_ csrf token is static

```js
const NodeRSA = require("node-rsa");

// Webnovel's auth RSA key
// https://passport.webnovel.com/login.html
const keyData = `-----BEGIN PUBLIC KEY-----
MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOf5B7Sg/EsfK+29BhFn1SUgoX
gcLP9Dl1Sf3g3PgwRTEkqMwhFVpIYoNVo1TV1q6Y6dRYZ1BExt/tqQqJcLvQhCKc
b4JuINKdftwG5le+Q2n6S/Ioyx7euYZgkmm3LSQ5VW7JmWV9VJFOIm4mpHmom9kE
CwVP/wBG9hmUs+USSwIDAQAB
-----END PUBLIC KEY-----`;

/**
 * web passport aes encryption
 * @param {string} str
 * @returns {string} aes -> base64 encoded string
 */
function rsaEncrypt(str) {
  const key = new NodeRSA();

  key.importKey(keyData, "public");
  return key.encrypt(str, "base64");
}

/**
 * @param {Object} q
 * @param {string} q.username - webnovel username
 * @param {string} q.password - webnovel password
 * @param {string} q._csrfToken - CSRF token from cookie
 * @param {CookieJar} cookieJar
 * @returns {GotPromise<string>}
 */
function web(q, cookieJar) {
  const query = {
    appId: 900,
    areaId: 1,
    source: "",
    returnurl: "http://www.webnovel.com",
    version: "",
    imei: "",
    qimei: "",
    target: "",
    format: "",
    ticket: "",
    autotime: "",
    auto: 1, // adds autologin tokens
    fromuid: 0,
    method: "LoginV1.checkCodeCallback",
    logintype: 22,
    username: "",
    password: rsaEncrypt(q.password),
    _csrfToken: q._csrfToken
  };

  return got("https://ptlogin.webnovel.com/login/checkcode", {
    query,
    headers: {
      "user-agent":
        "Mozilla/5.0 (X11; Linux x86_64; rv:69.0) Gecko/20100101 Firefox/69.0"
    },
    cookieJar,
    json: true
  });
}
```
