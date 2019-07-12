package a;

import android.content.Context;
import com.qidian.QDReader.core.i.ab;
import com.qidian.QDReader.core.i.ah;
import com.qidian.QDReader.core.i.i;
import com.qidian.QDReader.core.i.r;
import com.qidian.QDReader.core.log.QDLog;
import java.security.NoSuchAlgorithmException;

public class b {
    public static native byte[] b(long j, long j2, byte[] bArr, long j3, String str);

    public static native void c(Context context);

    public static native void e(String str, int i);

    static {
        System.loadLibrary("load-jni");
    }

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

    public static String m(String str, String str2) {
        try {
            return r.a(str, str2);
        } catch (NoSuchAlgorithmException e) {
            QDLog.exception(e);
            return null;
        }
    }

    public static byte[] d(byte[] bArr, String str) {
        try {
            return i.a(bArr, str);
        } catch (Exception e) {
            QDLog.exception(e);
            return null;
        }
    }
}
