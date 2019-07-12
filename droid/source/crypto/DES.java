package com.qidian.QDReader.core.p115i;

import com.qidian.QDReader.core.log.QDLog;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.qidian.QDReader.core.i.i */
public class DES {
    /* renamed from: a */
    public static String m20014a(String str, String str2) throws Exception {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[8]);
        byte[] bytes = str2.getBytes("UTF-8");
        if (bytes.length == 16) {
            byte[] bArr = new byte[24];
            System.arraycopy(bytes, 0, bArr, 0, 16);
            System.arraycopy(bytes, 0, bArr, 16, 8);
            bytes = bArr;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "DESede");
        Cipher instance = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        if (instance == null) {
            return "";
        }
        instance.init(1, secretKeySpec, ivParameterSpec);
        return Base64.m19982a(instance.doFinal(str.getBytes()));
    }

    /* renamed from: a */
    public static byte[] m20015a(byte[] bArr, String str) throws Exception {
        if (bArr == null || str == null) {
            return null;
        }
        IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[8]);
        byte[] bytes = str.getBytes("UTF-8");
        if (bytes == null || bytes.length < 1) {
            QDLog.m9354e("keyBytes is illegal");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("vip keylength = ");
        sb.append(bytes.length);
        QDLog.m9354e(sb.toString());
        int i = 0;
        if (bytes.length == 16) {
            byte[] bArr2 = new byte[24];
            System.arraycopy(bytes, 0, bArr2, 0, 16);
            System.arraycopy(bytes, 0, bArr2, 16, 8);
            bytes = bArr2;
        }
        Cipher instance = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        if (instance == null) {
            QDLog.m9354e("cipher is null");
            return null;
        }
        instance.init(2, new SecretKeySpec(bytes, "DESede"), ivParameterSpec);
        try {
            return instance.doFinal(bArr);
        } catch (Exception e) {
            QDLog.exception(e);
            if (bArr != null) {
                i = bArr.length;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("decryptDES失败：");
            sb2.append(str);
            sb2.append(",");
            sb2.append(i);
            QDLog.m9354e(sb2.toString());
            QDLog.m9354e(e.getMessage());
            return null;
        }
    }
}