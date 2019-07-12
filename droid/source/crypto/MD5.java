package com.qidian.QDReader.core.p115i;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.qidian.QDReader.core.i.r */
public class MD5 {

    /* renamed from: a */
    private static char[] f14485a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    /* renamed from: a */
    public static String m20046a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            int i2 = i + 1;
            byte b = bArr[i] & 255;
            if (i2 == length) {
                sb.append(f14485a[b >>> 2]);
                sb.append(f14485a[(b & 3) << 4]);
                sb.append("==");
                break;
            }
            int i3 = i2 + 1;
            byte b2 = bArr[i2] & 255;
            if (i3 == length) {
                sb.append(f14485a[b >>> 2]);
                sb.append(f14485a[((b & 3) << 4) | ((b2 & 240) >>> 4)]);
                sb.append(f14485a[(b2 & 15) << 2]);
                sb.append("=");
                break;
            }
            int i4 = i3 + 1;
            byte b3 = bArr[i3] & 255;
            sb.append(f14485a[b >>> 2]);
            sb.append(f14485a[((b & 3) << 4) | ((b2 & 240) >>> 4)]);
            sb.append(f14485a[((b2 & 15) << 2) | ((b3 & 192) >>> 6)]);
            sb.append(f14485a[b3 & 63]);
            i = i4;
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static String m20044a(String str) throws Exception {
        byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder(digest.length * 2);
        for (byte b : digest) {
            byte b2 = b & 255;
            if (b2 < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(b2));
        }
        return sb.toString();
    }

    /* renamed from: b */
    private static byte[] m20048b(byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(bArr);
        return instance.digest();
    }

    /* renamed from: a */
    public static String m20045a(String str, String str2) throws NoSuchAlgorithmException {
        return m20046a(m20047a(str.getBytes(), str2.getBytes()));
    }

    /* renamed from: a */
    private static byte[] m20047a(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException {
        byte[] bArr3 = new byte[64];
        byte[] bArr4 = new byte[64];
        for (int i = 0; i < 64; i++) {
            bArr3[i] = 54;
            bArr4[i] = 92;
        }
        byte[] bArr5 = new byte[64];
        if (bArr.length > 64) {
            bArr = m20048b(bArr);
        }
        System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
        if (bArr.length < 64) {
            for (int length = bArr.length; length < bArr5.length; length++) {
                bArr5[length] = 0;
            }
        }
        byte[] bArr6 = new byte[64];
        for (int i2 = 0; i2 < 64; i2++) {
            bArr6[i2] = (byte) (bArr5[i2] ^ bArr3[i2]);
        }
        byte[] bArr7 = new byte[(bArr6.length + bArr2.length)];
        System.arraycopy(bArr6, 0, bArr7, 0, bArr6.length);
        System.arraycopy(bArr2, 0, bArr7, bArr5.length + 0, bArr2.length);
        byte[] b = m20048b(bArr7);
        byte[] bArr8 = new byte[64];
        for (int i3 = 0; i3 < 64; i3++) {
            bArr8[i3] = (byte) (bArr5[i3] ^ bArr4[i3]);
        }
        byte[] bArr9 = new byte[(bArr8.length + b.length)];
        System.arraycopy(bArr8, 0, bArr9, 0, bArr8.length);
        System.arraycopy(b, 0, bArr9, bArr5.length + 0, b.length);
        return m20048b(bArr9);
    }
}
