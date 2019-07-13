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

The rest is just traffic sniffing.

## Usage

```sh
yarn add webnovel.js
```

```js
const { Client: WNClient } = require("webnovel.js");

(async () => {
  const email = "some@mail.com";

  const client = new WNClient({
    username: email,
    password: "supersekret",
    uuid: "000000003ede1bf9000000003ede1bf9" // UUID
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
    const code = await imapMagicGetCodeFromEmail(email);
    user = await client.confirmCode(encry, code); // cookies get set here
  } else {
    user = res;
  }

  const {
    body: {
      Data: { Email }
    }
  } = client.apiClient("/user/get");

  console.log(Email === email); // true

  // Ze Tian Ji 😍
  const bookId = "8205217405006105";

  // destructure the first chapter
  const {
    body: {
      Chapters: [{ Id: firstChapterId }]
    }
  } = await wnClient.apiClient("/book/get-chapters", {
    query: {
      bookId: "8205217405006105",
      maxUpdateTime: 0,
      maxIndex: 0,
      sign: null
    }
  });

  // have fun
  const chapter = await client.getChapter(bookId, firstChapterId);
  console.log(chapter);
})();
```

The client class only implements complex/encrypted/signed requests, so for the most part you need to manually find the endpoint you need and use the `Client.apiClient` Got instance to request it.  
Most API endpoints are declared in the `com.qidian.QDReader.components.api` package, in the `Urls` class.

## Classes

<dl>
<dt><a href="#Client">Client</a></dt>
<dd></dd>
<dt><a href="#AES">AES</a></dt>
<dd></dd>
<dt><a href="#UUID">UUID</a></dt>
<dd></dd>
</dl>

## Functions

<dl>
<dt><a href="#des3">des3(str)</a> ⇒ <code>string</code></dt>
<dd><p>des3 encrypt string</p>
</dd>
<dt><a href="#buildCookies">buildCookies(userId, userKey)</a> ⇒ <code>Array.&lt;Cookie&gt;</code></dt>
<dd><p>Build a cookie array, used for API requests</p>
</dd>
<dt><a href="#auth">auth(ctx)</a> ⇒ <code>got.GotInstance.&lt;got.GotJSONFn&gt;</code></dt>
<dd><p>Create an auth API Got instance</p>
</dd>
<dt><a href="#api">api(ctx)</a> ⇒ <code>got.GotInstance.&lt;got.GotJSONFn&gt;</code></dt>
<dd><p>Create an API (idroid) Got instance</p>
</dd>
<dt><a href="#ts">ts()</a></dt>
<dd><p>current unix timestamp w/o ms</p>
</dd>
<dt><a href="#userAgent">userAgent(uuid)</a> ⇒ <code>string</code></dt>
<dd><p>Creates a useragent string used by the android app</p>
</dd>
<dt><a href="#token">token(uuid)</a> ⇒ <code>string</code></dt>
<dd><p>AES encrypted wdToken header - Chinese telemetry =)</p>
</dd>
</dl>

<a name="Client"></a>

## Client

Webnovel client, instantiate, login then call the API endpoints using `this.apiClient`

**Kind**: global class

- [Client](#Client)
  - [new Client()](#new_Client_new)
  - _instance_
    - [.authClient](#Client+authClient) : <code>got.GotInstance.&lt;got.GotJSONFn&gt;</code>
    - [.apiClient](#Client+apiClient) : <code>got.GotInstance.&lt;got.GotJSONFn&gt;</code>
    - [.confirmCode(encry, code)](#Client+confirmCode) ⇒ <code>Object</code>
    - [.login(emailVer)](#Client+login) ⇒ <code>Object</code>
    - [.getChapter(bookId, chapterId)](#Client+getChapter) ⇒ <code>Object</code>
  - _static_
    - [.Client](#Client.Client)
      - [new Client(obj)](#new_Client.Client_new)

<a name="new_Client_new"></a>

### new Client()

Webnovel client

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
<a name="Client+confirmCode"></a>

### client.confirmCode(encry, code) ⇒ <code>Object</code>

Login using email verification code (if login method returned code 11318)

**Kind**: instance method of [<code>Client</code>](#Client)  
**Returns**: <code>Object</code> - user info  
**Throws**:

- <code>AuthError</code>

| Param | Type                | Description                                   |
| ----- | ------------------- | --------------------------------------------- |
| encry | <code>string</code> | encry property from the failed login response |
| code  | <code>string</code> | email verif. code                             |

<a name="Client+login"></a>

### client.login(emailVer) ⇒ <code>Object</code>

Login into Webnovel

**Kind**: instance method of [<code>Client</code>](#Client)  
**Returns**: <code>Object</code> - user info  
**Throws**:

- <code>AuthError</code>

| Param    | Type                 | Default            | Description                                                           |
| -------- | -------------------- | ------------------ | --------------------------------------------------------------------- |
| emailVer | <code>boolean</code> | <code>false</code> | Set to true if you want it to pass and send an email with a ver. code |

<a name="Client+getChapter"></a>

### client.getChapter(bookId, chapterId) ⇒ <code>Object</code>

Get and decrypt a chapter, unauthenticated requests probably won't work.

**Kind**: instance method of [<code>Client</code>](#Client)  
**Returns**: <code>Object</code> - The original chapter response with the `body['Data']['ContentItems']` property decrypted.

| Param     | Type                | Description |
| --------- | ------------------- | ----------- |
| bookId    | <code>string</code> | Book ID     |
| chapterId | <code>string</code> | Chapter ID  |

<a name="Client.Client"></a>

### Client.Client

**Kind**: static class of [<code>Client</code>](#Client)  
<a name="new_Client.Client_new"></a>

#### new Client(obj)

Creates an instance of Webnovel Client.

| Param        | Type                | Description                                                                |
| ------------ | ------------------- | -------------------------------------------------------------------------- |
| obj          | <code>Object</code> |                                                                            |
| obj.username | <code>string</code> | Webnovel username                                                          |
| obj.password | <code>string</code> | Webnovel password                                                          |
| obj.apiURL   | <code>string</code> | override Webnovel API endpoint                                             |
| obj.authURL  | <code>string</code> | override Webnovel auth API endoint (Qidian should work)                    |
| obj.uuid     | <code>string</code> | UUID is auto generated if not passed, which will trigger mail verification |

<a name="AES"></a>

## AES

**Kind**: global class

- [AES](#AES)
  - [.encrypt(str)](#AES+encrypt) ⇒ <code>string</code>
  - [.decrypt(str)](#AES+decrypt) ⇒ <code>string</code>

<a name="AES+encrypt"></a>

### aes.encrypt(str) ⇒ <code>string</code>

**Kind**: instance method of [<code>AES</code>](#AES)  
**Returns**: <code>string</code> - base64 encoded string

| Param | Type                | Description |
| ----- | ------------------- | ----------- |
| str   | <code>string</code> | utf8 string |

<a name="AES+decrypt"></a>

### aes.decrypt(str) ⇒ <code>string</code>

**Kind**: instance method of [<code>AES</code>](#AES)

| Param | Type                | Description           |
| ----- | ------------------- | --------------------- |
| str   | <code>string</code> | base64 encoded string |

<a name="UUID"></a>

## UUID

**Kind**: global class

- [UUID](#UUID)
  - [new UUID()](#new_UUID_new)
  - [.toString(separator)](#UUID+toString) ⇒ <code>string</code>

<a name="new_UUID_new"></a>

### new UUID()

UUID builder from (MSB, LSB) values like Java's

<a name="UUID+toString"></a>

### uuid.toString(separator) ⇒ <code>string</code>

get the UUID string, pass empty string for no separator (webnovel needs it)

**Kind**: instance method of [<code>UUID</code>](#UUID)

| Param     | Type                | Default                    |
| --------- | ------------------- | -------------------------- |
| separator | <code>string</code> | <code>&quot;-&quot;</code> |

<a name="des3"></a>

## des3(str) ⇒ <code>string</code>

des3 encrypt string

**Kind**: global function  
**Returns**: <code>string</code> - des-ede3 -> base64 encoded string

| Param | Type                | Description       |
| ----- | ------------------- | ----------------- |
| str   | <code>string</code> | utf8 input string |

**Kind**: global function  
**Returns**: <code>string</code> - aes -> base64 encoded string

| Param | Type                |
| ----- | ------------------- |
| str   | <code>string</code> |

<a name="buildCookies"></a>

## buildCookies(userId, userKey) ⇒ <code>Array.&lt;Cookie&gt;</code>

Build a cookie array, used for API requests

**Kind**: global function

| Param   | Type                |
| ------- | ------------------- |
| userId  | <code>string</code> |
| userKey | <code>string</code> |

<a name="auth"></a>

## auth(ctx) ⇒ <code>got.GotInstance.&lt;got.GotJSONFn&gt;</code>

Create an auth API Got instance

**Kind**: global function

| Param       | Type                | Description           |
| ----------- | ------------------- | --------------------- |
| ctx         | <code>Object</code> |                       |
| ctx.uuid    | <code>string</code> | UUID                  |
| ctx.authURL | <code>string</code> | Auth API endpoint URL |

<a name="api"></a>

## api(ctx) ⇒ <code>got.GotInstance.&lt;got.GotJSONFn&gt;</code>

Create an API (idroid) Got instance

**Kind**: global function

| Param         | Type                   | Description      |
| ------------- | ---------------------- | ---------------- |
| ctx           | <code>Object</code>    |                  |
| ctx.uuid      | <code>string</code>    | UUID             |
| ctx.cookieJar | <code>CookieJar</code> | CookieJar        |
| ctx.apiURL    | <code>string</code>    | API endpoint URL |

<a name="ts"></a>

## ts()

current unix timestamp w/o ms

**Kind**: global function  
<a name="userAgent"></a>

## userAgent(uuid) ⇒ <code>string</code>

Creates a useragent string used by the android app

**Kind**: global function  
**Returns**: <code>string</code> - useragent string

| Param | Type                | Description |
| ----- | ------------------- | ----------- |
| uuid  | <code>string</code> | UUID        |

<a name="token"></a>

## token(uuid) ⇒ <code>string</code>

AES encrypted wdToken header - Chinese telemetry =)

**Kind**: global function  
**Returns**: <code>string</code> - wdToken

| Param | Type                | Description |
| ----- | ------------------- | ----------- |
| uuid  | <code>string</code> | UUID        |

## Chapter encryption

**TODO:** implement in the lib

```sh
curl -X GET \
  'https://idruid.webnovel.com/app/api/book/get-chapter?bookId=11721981606490105&chapterId=34602770786999615' \
  -H 'User-Agent: Mozilla/mobile QDHWReaderAndroid/3.8.2/183/2000002/000000008fd8e514000000008fd8e514' \
  -H 'wdToken: zFo1gTRxWzzRKLDiZZNgY1hYtafhSAlbubfBjp02ySim784MVkCGM6iz2bXJDW6+QWUjbRjZ19TAhZYFfHO6H1uSeDLgnnLQKV6cZs76xTcSFcWQiiebaLDG9/zIeQUOTxJCqSAXPjLOiuKdWqzpKA=='
```

`res['Data']['ContentItems']` returns an encrypted string, [decrypter/Main.kt](/decrypter/Main.kt) has a decrpytion example based on `libload-jni.so`.

```java
byte[] b = C0000b.m0b(j, j2, bArr, QDUserManager.getInstance().mo36470a(), AppInfo.m9267a().mo21343p());
// b bookId chapterId content userId (jwguid) IMEI (UUID)

// b :: long -> long -> byte[] -> long -> String -> byte[]
public static native byte[] a.b.b(long j, long j2, byte[] bArr, long j3, String str);
```

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
