jbyteArray __fastcall Java_a_b_b(JNIEnv *env, jobject thiz, jlong arg1, jlong arg2, jbyteArray arg3, jlong arg4, jstring arg5)
{
  JNIEnv *v7;           // r4
  char *v8;             // r8
  const char *v9;       // r6
  char *v10;            // r7
  char *v11;            // r11
  int v12;              // r10
  char *v14;            // r0
  char *v15;            // r0
  int v16;              // r9
  int v17;              // r9
  const char *v18;      // r8
  int v19;              // r9
  int v20;              // r10
  int v21;              // r8
  int v22;              // r10
  const char *v23;      // r7
  int v24;              // r9
  int v25;              // r7
  int v26;              // r10
  struct _jobject *v27; // r7
  char *src;            // [sp+14h] [bp-44h]
  int v29;              // [sp+18h] [bp-40h]
  char *v30;            // [sp+1Ch] [bp-3Ch]
  int v31;              // [sp+20h] [bp-38h]
  int v32;              // [sp+20h] [bp-38h]
  char *v33;            // [sp+24h] [bp-34h]
  int v34;              // [sp+28h] [bp-30h]
  void *ptr;            // [sp+2Ch] [bp-2Ch]

  v7 = env;

  ptr = (void *)longToString(arg1, HIDWORD(arg1)); // book ID
  v33 = (char *)longToString(arg2, HIDWORD(arg2)); // chapter ID
  src = (char *)longToString(arg4, HIDWORD(arg4)); // userID

  initstr(0x32u); // 50 ??

  v8 = (char *)initstr(0xFFu); // 255

  initstr(0xFFu);

  v9 = (const char *)initstr(0xFFu);
  v10 = (char *)initstr(0xFFu);

  initstr(0xFFu);

  v11 = (char *)initstr(0xFFu);
  v12 = ((int(__fastcall *)(JNIEnv *, const char *))(*v7)->FindClass)(v7, "a/b");

  if (!v12)
    return 0;

  v34 = ((int(__fastcall *)(JNIEnv *, const char *))(*v7)->FindClass)(v7, "a/b");

  if (!v34)
    return 0;

  v29 = ((int(__fastcall *)(JNIEnv *, const char *))(*v7)->FindClass)(v7, "a/b");

  if (!v29)
    return 0;

  v30 = (char *)((int(__fastcall *)(JNIEnv *, jstring, _DWORD))(*v7)->GetStringUTFChars)(v7, arg5, 0); // IMEI

  _android_log_print(3, "QDReader_Jni", "bookid: %s,chapterid: %s,userid: %s,imei: %s", ptr, v33, src, v30);

  strcpy(v8, src);

  v14 = strcat(v8, v30);
  v15 = strcat(v14, v33);

  strcat(v15, "2EEE1433A152E84B3756301D8FA3E69A");

  v31 = ((int(__fastcall *)(JNIEnv *, char *))(*v7)->NewStringUTF)(v7, v8);

  // sha1 id
  // sha1 hmac?
  v16 = ((int(__fastcall *)(JNIEnv *, int, const char *, const char *))(*v7)->GetStaticMethodID)(
      v7,
      v12,
      "s",
      "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");

  if (!v16)
    return 0;

  v17 = ((int(__fastcall *)(JNIEnv *, int, int, jstring, int))(*v7)->CallStaticObjectMethod)(v7, v12, v16, arg5, v31);

  ((void(__fastcall *)(JNIEnv *, int, char *))(*v7)->ReleaseStringUTFChars)(v7, v31, v8);

  // sha1 key 1
  v18 = (const char *)((int(__fastcall *)(JNIEnv *, int, _DWORD))(*v7)->GetStringUTFChars)(v7, v17, 0);

  if (strlen(v18) > 0x17)
    // sha1 key 2
    v9 = (const char *)substr(v18, 0, 23);

  ((void(__fastcall *)(JNIEnv *, int, const char *))(*v7)->ReleaseStringUTFChars)(v7, v17, v18);
  v32 = ((int(__fastcall *)(JNIEnv *, const char *))(*v7)->NewStringUTF)(v7, v9);

  strcpy(v10, v9);
  strcat(v10, v30);

  v19 = ((int(__fastcall *)(JNIEnv *, char *))(*v7)->NewStringUTF)(v7, v10);

  // a.b.m
  v20 = ((int(__fastcall *)(JNIEnv *, int, const char *, const char *))(*v7)->GetStaticMethodID)(
      v7,
      v34,
      "m",
      "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");

  if (!v20)
    return 0;

  v21 = ((int(__fastcall *)(JNIEnv *, char *))(*v7)->NewStringUTF)(v7, src);

  // com.qidian.QDReader.core.i.r.a :: String -> String -> byte[] -> String
  // md5 hash of 2 strings then base64?
  v22 = ((int(__fastcall *)(JNIEnv *, int, int, int, int))(*v7)->CallStaticObjectMethod)(v7, v34, v20, v21, v19);

  ((void(__fastcall *)(JNIEnv *, int, char *))(*v7)->ReleaseStringUTFChars)(v7, v19, v10);

  v23 = (const char *)((int(__fastcall *)(JNIEnv *, int, _DWORD))(*v7)->GetStringUTFChars)(v7, v22, 0);

  if (strlen(v23) <= 0x18)
    strcpy(v11, v23);
  else
    v11 = (char *)substr(v23, 0, 23);

  ((void(__fastcall *)(JNIEnv *, int, const char *))(*v7)->ReleaseStringUTFChars)(v7, v22, v23);

  v24 = ((int(__fastcall *)(JNIEnv *, char *))(*v7)->NewStringUTF)(v7, v11);
  v25 = ((int(__fastcall *)(JNIEnv *, int, const char *, const char *))(*v7)->GetStaticMethodID)(
      v7,
      v29,
      "d",
      "([BLjava/lang/String;)[B");

  if (!v25)
    return 0;

  v26 = ((int(__fastcall *)(JNIEnv *, int, int, jbyteArray, int))(*v7)->CallStaticObjectMethod)(
      v7,
      v29,
      v25,
      arg3,
      v24);

  v27 = (struct _jobject *)((int(__fastcall *)(JNIEnv *, int, int, int, int))(*v7)->CallStaticObjectMethod)(
      v7,
      v29,
      v25,
      v26,
      v32);

  ((void(__fastcall *)(JNIEnv *, jstring, char *))(*v7)->ReleaseStringUTFChars)(v7, arg5, v30);
  ((void(__fastcall *)(JNIEnv *, int, char *))(*v7)->ReleaseStringUTFChars)(v7, v24, v11);
  ((void(__fastcall *)(JNIEnv *, int, char *))(*v7)->ReleaseStringUTFChars)(v7, v21, src);

  free(ptr);
  free(v33);

  return v27;
}
