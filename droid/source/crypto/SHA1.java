package com.qidian.QDReader.core.p115i;

import android.support.p008v4.internal.view.SupportMenu;

/* renamed from: com.qidian.QDReader.core.i.ab */
public abstract class SHA1 {
    /* renamed from: a */
    private static int m19930a(int i) {
        if (i < 20) {
            return 1518500249;
        }
        if (i < 40) {
            return 1859775393;
        }
        return i < 60 ? -1894007588 : -899497514;
    }

    /* renamed from: a */
    private static int m19931a(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    /* renamed from: a */
    private static int m19932a(int i, int i2, int i3, int i4) {
        return i < 20 ? (i2 & i3) | ((i2 ^ -1) & i4) : i < 40 ? (i2 ^ i3) ^ i4 : i < 60 ? (i2 & i3) | (i2 & i4) | (i3 & i4) : (i2 ^ i3) ^ i4;
    }

    // public static final int USER_MASK = 65535;
    /* renamed from: b */
    private static int m19938b(int i, int i2) {
        int i3 = (i & SupportMenu.USER_MASK) + (i2 & SupportMenu.USER_MASK);
        return ((((i >> 16) + (i2 >> 16)) + (i3 >> 16)) << 16) | (i3 & SupportMenu.USER_MASK);
    }

    /* renamed from: a */
    public static String m19934a(String str, String str2) {
        return m19935a(m19940b(str, str2));
    }

    /* renamed from: a */
    private static String m19935a(int[] iArr) {
        int[] b = m19942b(iArr, iArr.length * 4);
        String str = "";
        int i = 0;
        while (i < b.length * 4) {
            int i2 = i + 1;
            int i3 = (((b[i >> 2] >> ((3 - (i % 4)) * 8)) & 255) << 16) | (((b[i2 >> 2] >> ((3 - (i2 % 4)) * 8)) & 255) << 8);
            int i4 = i + 2;
            int i5 = i3 | ((b[i4 >> 2] >> ((3 - (i4 % 4)) * 8)) & 255);
            String str2 = str;
            for (int i6 = 0; i6 < 4; i6++) {
                if ((i * 8) + (i6 * 6) > b.length * 32) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2);
                    sb.append("=");
                    str2 = sb.toString();
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str2);
                    sb2.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((i5 >> ((3 - i6) * 6)) & 63));
                    str2 = sb2.toString();
                }
            }
            i += 3;
            str = str2;
        }
        return m19933a(str);
    }

    /* renamed from: a */
    private static String m19933a(String str) {
        if (str == null) {
            str = "";
        }
        int length = str.length();
        if (length <= 1) {
            return str;
        }
        int i = length - 1;
        char charAt = str.charAt(i);
        String str2 = "";
        while (i >= 0 && str.charAt(i) == charAt) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(str.charAt(i));
            str2 = sb.toString();
            i--;
        }
        return str.substring(0, str.indexOf(str2));
    }

    /* renamed from: b */
    private static int[] m19941b(int[] iArr) {
        if (iArr.length >= 16) {
            return iArr;
        }
        int[] iArr2 = new int[(16 - iArr.length)];
        for (int i = 0; i < iArr2.length; i++) {
            iArr2[i] = 0;
        }
        return m19937a(iArr, iArr2);
    }

    /* renamed from: a */
    private static int[] m19937a(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[(iArr.length + iArr2.length)];
        for (int i = 0; i < iArr.length + iArr2.length; i++) {
            if (i < iArr.length) {
                iArr3[i] = iArr[i];
            } else {
                iArr3[i] = iArr2[i - iArr.length];
            }
        }
        return iArr3;
    }

    /* renamed from: b */
    private static int[] m19940b(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        int[] b = m19941b(m19939b(str));
        if (b.length > 16) {
            b = m19936a(b, str.length() * 8);
        }
        int[] iArr = new int[16];
        int[] iArr2 = new int[16];
        for (int i = 0; i < 16; i++) {
            iArr[i] = 0;
            iArr2[i] = 0;
        }
        for (int i2 = 0; i2 < 16; i2++) {
            iArr[i2] = b[i2] ^ 909522486;
            iArr2[i2] = b[i2] ^ 1549556828;
        }
        return m19936a(m19937a(iArr2, m19936a(m19937a(iArr, m19939b(str2)), (str2.length() * 8) + 512)), 672);
    }

    /* renamed from: a */
    private static int[] m19936a(int[] iArr, int i) {
        int i2 = i >> 5;
        int[] b = m19942b(iArr, i2);
        b[i2] = b[i2] | (128 << (24 - (i % 32)));
        int i3 = (((i + 64) >> 9) << 4) + 15;
        int[] b2 = m19942b(b, i3);
        b2[i3] = i;
        int i4 = 80;
        int[] iArr2 = new int[80];
        int i5 = 0;
        int i6 = 1732584193;
        int i7 = -271733879;
        int i8 = -1732584194;
        int i9 = 271733878;
        int i10 = -1009589776;
        while (true) {
            int i11 = 1;
            int i12 = 5;
            if (i5 < b2.length) {
                int i13 = i6;
                int i14 = i7;
                int i15 = i8;
                int i16 = i9;
                int i17 = i10;
                int i18 = 0;
                while (i18 < i4) {
                    if (i18 < 16) {
                        iArr2[i18] = b2[i5 + i18];
                    } else {
                        iArr2[i18] = m19931a(((iArr2[i18 - 3] ^ iArr2[i18 - 8]) ^ iArr2[i18 - 14]) ^ iArr2[i18 - 16], i11);
                    }
                    int b3 = m19938b(m19938b(m19931a(i13, i12), m19932a(i18, i14, i15, i16)), m19938b(m19938b(i17, iArr2[i18]), m19930a(i18)));
                    int a = m19931a(i14, 30);
                    i18++;
                    i17 = i16;
                    i14 = i13;
                    i12 = 5;
                    i13 = b3;
                    i16 = i15;
                    i15 = a;
                    i4 = 80;
                    i11 = 1;
                }
                i6 = m19938b(i13, i6);
                i7 = m19938b(i14, i7);
                i8 = m19938b(i15, i8);
                i9 = m19938b(i16, i9);
                i10 = m19938b(i17, i10);
                i5 += 16;
                i4 = 80;
            } else {
                return new int[]{i6, i7, i8, i9, i10};
            }
        }
    }

    /* renamed from: b */
    private static int[] m19939b(String str) {
        if (str == null) {
            str = "";
        }
        int[] iArr = new int[(str.length() * 8)];
        for (int i = 0; i < str.length() * 8; i += 8) {
            int i2 = i >> 5;
            iArr[i2] = iArr[i2] | ((str.charAt(i / 8) & 255) << (24 - (i % 32)));
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < iArr.length && iArr[i3] != 0) {
            i3++;
            i4++;
        }
        int[] iArr2 = new int[i4];
        System.arraycopy(iArr, 0, iArr2, 0, i4);
        return iArr2;
    }

    /* renamed from: b */
    private static int[] m19942b(int[] iArr, int i) {
        int length = iArr.length;
        int i2 = i + 1;
        if (length >= i2) {
            return iArr;
        }
        int[] iArr2 = new int[i2];
        for (int i3 = 0; i3 < i; i3++) {
            iArr2[i3] = 0;
        }
        System.arraycopy(iArr, 0, iArr2, 0, length);
        return iArr2;
    }
}
