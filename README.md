# QI "worker"

auth works.

### Chapter encryption

```sh
curl -X GET \
  'https://idruid.webnovel.com/app/api/book/get-chapter?bookId=11721981606490105&chapterId=34602770786999615' \
  -H 'User-Agent: Mozilla/mobile QDHWReaderAndroid/3.8.2/183/2000002/000000008fd8e514000000008fd8e514' \
  -H 'wdToken: zFo1gTRxWzzRKLDiZZNgY1hYtafhSAlbubfBjp02ySim784MVkCGM6iz2bXJDW6+QWUjbRjZ19TAhZYFfHO6H1uSeDLgnnLQKV6cZs76xTcSFcWQiiebaLDG9/zIeQUOTxJCqSAXPjLOiuKdWqzpKA=='
```

`Data['Data']['ContentItems']` returns an encrypted string, [decrypter/Main.kt](/decrypter/Main.kt) has a decrpytion example based on `libload-jni.so`.

```java
byte[] b = C0000b.m0b(j, j2, bArr, QDUserManager.getInstance().mo36470a(), AppInfo.m9267a().mo21343p());
// b bookId chapterId content userId (jwguid) IMEI (UUID)

// b :: long -> long -> byte[] -> long -> String -> byte[]
public static native byte[] a.b.b(long j, long j2, byte[] bArr, long j3, String str);
```
