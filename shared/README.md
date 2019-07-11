`libload-jni.so` has a chapter encryption function.

```java
public static native byte[] m0b(long j, long j2, byte[] bArr, long j3, String str);

// (bookId, chapterId, content, userId (jwguid), IMEI (UUID))
byte[] b = C0000b.m0b(j, j2, bArr, QDUserManager.getInstance().mo36470a(), AppInfo.m9267a().mo21343p());
```

```c
sym.Java_a_b_b (int32_t arg1, int32_t arg3, int32_t arg4); @ 0x000015f0
```
