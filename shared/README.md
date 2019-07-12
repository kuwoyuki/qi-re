My guess at `Java_a_b_b`

```java
public static byte[] b(long bookId, long chapterId, byte[] bs, long userId, String imei) {
    String salt1 = "" + userId + imei + chapterId + "2EEE1433A152E84B3756301D8FA3E69A";

    // First secret, calls s :: String -> String -> String
    // SHA1 HMAC?
    String secret1 = s(imei, salt1);

    if (secret1.length() > 24) {
        secret1 = secret1.substring(0, 24);
    }

    String salt2 = secret1 + imei
    // Second secret, calls m :: String -> String -> String
    // something MD5
    String secret2 = m(userId + "", salt2);

    if (secret2.length() > 24) {
        secret2 = secret2.substring(0, 24);
    }

    // Double decrypt with DES?

    // d :: byte[] -> String -> byte[]
    bs = d(bs, secret2);

    return d(bs, secret1);
}
```
