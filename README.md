# QI "worker"

auth works.

###

`libload-jni.so` has a chapter encryption function.

```java
byte[] b = C0000b.m0b(j, j2, bArr, QDUserManager.getInstance().mo36470a(), AppInfo.m9267a().mo21343p());
// b bookId chapterId content userId (jwguid) IMEI (UUID)

// b :: long -> long -> byte[] -> long -> String -> byte[]
public static native byte[] a.b.b(long j, long j2, byte[] bArr, long j3, String str);
```

Idk what this does yet..

```c
sym.Java_a_b_b (int32_t arg1, int32_t arg3, int32_t arg4); // 0x000015f0
```

```asm
0x000016c8      blx sym.imp.strcpy ; char *strcpy(char *dest, const char *src)
0x000016cc      ldr r1, [sp + var_1ch] ; const char *s2
0x000016ce      mov r0, r8 ; char *s1
0x000016d0      blx sym.imp.strcat ; char *strcat(char *s1, const char *s2)
0x000016d4      ldr r1, [sp + var_24h] ; const char *s2
0x000016d6      blx sym.imp.strcat ; char *strcat(char *s1, const char *s2)
0x000016da      ldr r1, [0x00001a2c]
0x000016dc      add r1, pc ; 0x2d46 ; "2EEE1433A152E84B3756301D8FA3E69A" ; const char *s2
0x000016de      blx sym.imp.strcat ; char *strcat(char *s1, const char *s2)
0x000016e2      ldr r2, aav.0x0000167b ; 0x1a30
```
