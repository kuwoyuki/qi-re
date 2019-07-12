package com.qidian.QDReader.core.p115i;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.qidian.QDReader.core.i.a */
public class AESUtils {
    /* renamed from: a */
    public static String m19923a(String str, String str2, String str3) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, new SecretKeySpec(Base64.m19983a(str), "AES"), new IvParameterSpec(str2.getBytes()));
            return Base64.m19982a(instance.doFinal(str3.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public static String m19922a(String str) {
        return m19923a("anhtc2xzaW9kamZwd2UwMQ==", "webnovel-mobiles", str);
    }
}