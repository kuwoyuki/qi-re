package a;

import android.content.Context;
import com.qidian.QDReader.core.i.ab;
import com.qidian.QDReader.core.i.ah;
import com.qidian.QDReader.core.i.i;
import com.qidian.QDReader.core.i.r;
import com.qidian.QDReader.core.log.QDLog;
import java.security.NoSuchAlgorithmException;

public class b {
    // public static native byte[] b(long j, long j2, byte[] bArr, long j3, String str);
    public static byte[] b(int bookId, int chapterId, byte[] bs, long userId, String imei) {
        String salt1 = "" + userId + imei + chapterId + "2EEE1433A152E84B3756301D8FA3E69A";
        String secret1 = s(imei, salt1);

        if (secret1.length() > 24) {
            secret1 = secret1.substring(0, 24);
        }

        String salt2=secrect1+aid;
        String secret2=m(uid + "", salt2);
        
        if (secret2.length() > 24) {
            secret2 = secret2.substring(0, 24);
        }
        
        bs = d(bs, secret2);
        return d(bs,secrect1);
    }


    public static native void c(Context context);

    public static native void e(String str, int i);

    static {
        System.loadLibrary("load-jni");
    }

    // SHA1
    public static String s(String str, String str2) {
        String str3;
        try {
            str3 = ab.a(str, str2);
            try {
                if (str3.length() < 24) {
                    return ah.a(str3, 24, 0);
                }
                return str3;
            } catch (Exception e) {
                e = e;
                QDLog.exception(e);
                return str3;
            }
        } catch (Exception e2) {
            e = e2;
            str3 = null;
            QDLog.exception(e);
            return str3;
        }
    }

    // MD5
    public static String m(String str, String str2) {
        try {
            return r.a(str, str2);
        } catch (NoSuchAlgorithmException e) {
            QDLog.exception(e);
            return null;
        }
    }

    // DES
    public static byte[] d(byte[] bArr, String str) {
        try {
            return i.a(bArr, str);
        } catch (Exception e) {
            QDLog.exception(e);
            return null;
        }
    }
}
