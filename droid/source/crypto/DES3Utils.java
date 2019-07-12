package com.qidian.QDReader.core.p115i;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* renamed from: com.qidian.QDReader.core.i.j */
public class DES3Utils {
    /* renamed from: a */
    public static String m20016a(String str) {
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec("bMyzJ1D7Kl7zt9mwjegtJGMoF53msSfP".getBytes()));
            Cipher instance = Cipher.getInstance("desede/CBC/PKCS5Padding");
            instance.init(1, generateSecret, new IvParameterSpec("W9F1bXrz".getBytes()));
            return Base64.m19982a(instance.doFinal(str.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}