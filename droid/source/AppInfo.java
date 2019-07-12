package com.qidian.QDReader.core.config;

import android.content.Context;
import android.location.Address;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.qidian.QDReader.core.log.QDLog;
import com.qidian.QDReader.core.p114e.QDFileUtil;
import com.qidian.QDReader.core.p115i.DES;
import com.qidian.QDReader.core.p115i.DES3Utils;
import com.qidian.QDReader.core.p115i.DeviceUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/* renamed from: com.qidian.QDReader.core.config.a */
public class AppInfo {

    /* renamed from: a */
    private static AppInfo f6323a = null;

    /* renamed from: b */
    private static boolean f6324b = false;

    /* renamed from: A */
    private String f6325A;

    /* renamed from: B */
    private boolean f6326B = false;

    /* renamed from: C */
    private int f6327C = 0;

    /* renamed from: D */
    private int f6328D = 1;

    /* renamed from: E */
    private int f6329E = 901;

    /* renamed from: F */
    private int f6330F = 36;

    /* renamed from: G */
    private int f6331G = 0;

    /* renamed from: H */
    private int f6332H = 14;

    /* renamed from: I */
    private int f6333I;

    /* renamed from: J */
    private String f6334J;

    /* renamed from: K */
    private String f6335K;

    /* renamed from: c */
    private boolean f6336c = false;

    /* renamed from: d */
    private String f6337d;

    /* renamed from: e */
    private String f6338e;

    /* renamed from: f */
    private String f6339f;

    /* renamed from: g */
    private String f6340g;

    /* renamed from: h */
    private int f6341h;

    /* renamed from: i */
    private String f6342i;

    /* renamed from: j */
    private int f6343j;

    /* renamed from: k */
    private int f6344k;

    /* renamed from: l */
    private String f6345l;

    /* renamed from: m */
    private int f6346m = 4;

    /* renamed from: n */
    private Address f6347n;

    /* renamed from: o */
    private String f6348o;

    /* renamed from: p */
    private String f6349p;

    /* renamed from: q */
    private String f6350q = "";

    /* renamed from: r */
    private String f6351r;

    /* renamed from: s */
    private String f6352s;

    /* renamed from: t */
    private String f6353t;

    /* renamed from: u */
    private String f6354u;

    /* renamed from: v */
    private int f6355v;

    /* renamed from: w */
    private int f6356w;

    /* renamed from: x */
    private String f6357x;

    /* renamed from: y */
    private String f6358y;

    /* renamed from: z */
    private String f6359z;

    /* renamed from: D */
    public int mo21319D() {
        return 10;
    }

    /* renamed from: a */
    public static AppInfo m9267a() {
        if (f6323a == null) {
            if (!f6324b) {
                f6324b = true;
                f6323a = new AppInfo();
                f6323a.m9262F();
                f6324b = false;
            } else {
                throw new IllegalStateException("不能在AppInfo加载中的时候，再调用AppInfo加载他本身");
            }
        }
        return f6323a;
    }

    /* renamed from: b */
    public String mo21323b() {
        return this.f6334J;
    }

    /* renamed from: a */
    public void mo21322a(String str) {
        this.f6334J = str;
    }

    private AppInfo() {
        m9261E();
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0134 A[Catch:{ Exception -> 0x0154 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x015e A[Catch:{ NameNotFoundException -> 0x017d }] */
    /* renamed from: E */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m9261E() {
        /*
            r7 = this;
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.String r2 = r7.m9265I()     // Catch:{ Exception -> 0x000b }
            r7.f6350q = r2     // Catch:{ Exception -> 0x000b }
            goto L_0x000f
        L_0x000b:
            r2 = move-exception
            com.qidian.QDReader.core.log.QDLog.exception(r2)
        L_0x000f:
            android.app.Application r2 = com.qidian.QDReader.core.ApplicationContext.getInstance()     // Catch:{ Exception -> 0x002a }
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ Exception -> 0x002a }
            java.lang.String r3 = "wifi"
            java.lang.Object r2 = r2.getSystemService(r3)     // Catch:{ Exception -> 0x002a }
            android.net.wifi.WifiManager r2 = (android.net.wifi.WifiManager) r2     // Catch:{ Exception -> 0x002a }
            android.net.wifi.WifiInfo r2 = r2.getConnectionInfo()     // Catch:{ Exception -> 0x002a }
            java.lang.String r2 = r2.getMacAddress()     // Catch:{ Exception -> 0x002a }
            r7.f6351r = r2     // Catch:{ Exception -> 0x002a }
            goto L_0x002e
        L_0x002a:
            r2 = move-exception
            com.qidian.QDReader.core.log.QDLog.exception(r2)
        L_0x002e:
            android.app.Application r2 = com.qidian.QDReader.core.ApplicationContext.getInstance()     // Catch:{ Exception -> 0x003f }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ Exception -> 0x003f }
            java.lang.String r3 = "android_id"
            java.lang.String r2 = android.provider.Settings.Secure.getString(r2, r3)     // Catch:{ Exception -> 0x003f }
            r7.f6352s = r2     // Catch:{ Exception -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r2 = move-exception
            com.qidian.QDReader.core.log.QDLog.exception(r2)
        L_0x0043:
            java.lang.String r2 = r7.m9263G()
            r7.f6353t = r2
            java.lang.String r2 = r7.m9264H()     // Catch:{ Exception -> 0x0050 }
            r7.f6354u = r2     // Catch:{ Exception -> 0x0050 }
            goto L_0x0058
        L_0x0050:
            r2 = move-exception
            com.qidian.QDReader.core.log.QDLog.exception(r2)
            java.lang.String r2 = ""
            r7.f6354u = r2
        L_0x0058:
            java.lang.String r2 = android.os.Build.VERSION.RELEASE
            r7.f6357x = r2
            java.lang.String r2 = android.os.Build.MODEL
            if (r2 == 0) goto L_0x006b
            java.lang.String r2 = android.os.Build.MODEL
            java.lang.String r3 = "\\|"
            java.lang.String r4 = "_"
            java.lang.String r2 = r2.replaceAll(r3, r4)
            goto L_0x006d
        L_0x006b:
            java.lang.String r2 = ""
        L_0x006d:
            r7.f6358y = r2
            java.lang.String r2 = android.os.Build.VERSION.SDK
            if (r2 == 0) goto L_0x007e
            java.lang.String r2 = android.os.Build.VERSION.SDK
            java.lang.String r3 = "\\|"
            java.lang.String r4 = "_"
            java.lang.String r2 = r2.replaceAll(r3, r4)
            goto L_0x0080
        L_0x007e:
            java.lang.String r2 = ""
        L_0x0080:
            r7.f6325A = r2
            java.lang.String r2 = android.os.Build.BRAND
            if (r2 == 0) goto L_0x0091
            java.lang.String r2 = android.os.Build.BRAND
            java.lang.String r3 = "\\|"
            java.lang.String r4 = "_"
            java.lang.String r2 = r2.replaceAll(r3, r4)
            goto L_0x0093
        L_0x0091:
            java.lang.String r2 = ""
        L_0x0093:
            r7.f6359z = r2
            java.lang.String r2 = "b"
            r7.f6340g = r2
            r2 = 1
            r7.f6343j = r2
            int r3 = com.qidian.QDReader.core.p115i.RootUtil.m19926a()
            r7.f6341h = r3
            r3 = 0
            java.lang.String r4 = new java.lang.String     // Catch:{ Exception -> 0x00d8 }
            android.app.Application r5 = com.qidian.QDReader.core.ApplicationContext.getInstance()     // Catch:{ Exception -> 0x00d8 }
            java.lang.String r6 = "BuildConfig.txt"
            byte[] r5 = com.qidian.QDReader.core.p114e.QDFileUtil.m9335a(r5, r6)     // Catch:{ Exception -> 0x00d8 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x00d8 }
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ Exception -> 0x00d8 }
            r5.<init>(r4)     // Catch:{ Exception -> 0x00d8 }
            java.lang.String r4 = "Debug"
            int r4 = r5.optInt(r4, r3)     // Catch:{ Exception -> 0x00d8 }
            r7.f6327C = r4     // Catch:{ Exception -> 0x00d8 }
            int r4 = r7.f6327C     // Catch:{ Exception -> 0x00d8 }
            if (r4 == 0) goto L_0x00d5
            int r4 = r7.f6327C     // Catch:{ Exception -> 0x00d8 }
            r5 = 3
            if (r4 != r5) goto L_0x00c9
            goto L_0x00d5
        L_0x00c9:
            int r4 = r7.f6327C     // Catch:{ Exception -> 0x00d8 }
            if (r4 == r2) goto L_0x00d2
            int r4 = r7.f6327C     // Catch:{ Exception -> 0x00d8 }
            r5 = 2
            if (r4 != r5) goto L_0x00dc
        L_0x00d2:
            r7.f6326B = r3     // Catch:{ Exception -> 0x00d8 }
            goto L_0x00dc
        L_0x00d5:
            r7.f6326B = r2     // Catch:{ Exception -> 0x00d8 }
            goto L_0x00dc
        L_0x00d8:
            r4 = move-exception
            com.qidian.QDReader.core.log.QDLog.exception(r4)
        L_0x00dc:
            java.lang.String r4 = com.qidian.QDReader.core.config.CooperationInstallUtils.m19666a()
            r7.f6338e = r4
            java.lang.String r4 = r7.f6338e
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x0102
            java.lang.String r4 = "AppInfo"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "oppo source = "
            r5.append(r6)
            java.lang.String r6 = r7.f6338e
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            android.util.Log.d(r4, r5)
        L_0x0102:
            android.app.Application r4 = com.qidian.QDReader.core.ApplicationContext.getInstance()
            java.lang.String r5 = "config.txt"
            byte[] r4 = com.qidian.QDReader.core.p114e.QDFileUtil.m9335a(r4, r5)
            if (r4 == 0) goto L_0x0128
            java.lang.String r5 = new java.lang.String
            r5.<init>(r4)
            java.lang.String r4 = "\\|"
            java.lang.String[] r4 = r5.split(r4)
            int r5 = r4.length
            if (r5 <= 0) goto L_0x0128
            r4 = r4[r3]
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 == 0) goto L_0x0126
            java.lang.String r4 = "2000002"
        L_0x0126:
            r7.f6345l = r4
        L_0x0128:
            android.app.Application r4 = com.qidian.QDReader.core.ApplicationContext.getInstance()     // Catch:{ Exception -> 0x0154 }
            java.lang.String r5 = "build.txt"
            byte[] r4 = com.qidian.QDReader.core.p114e.QDFileUtil.m9335a(r4, r5)     // Catch:{ Exception -> 0x0154 }
            if (r4 == 0) goto L_0x0158
            java.lang.String r5 = new java.lang.String     // Catch:{ Exception -> 0x0154 }
            r5.<init>(r4)     // Catch:{ Exception -> 0x0154 }
            r7.f6337d = r5     // Catch:{ Exception -> 0x0154 }
            java.lang.String r4 = "QDReader"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0154 }
            r5.<init>()     // Catch:{ Exception -> 0x0154 }
            java.lang.String r6 = "app 编译用的代码截至点:"
            r5.append(r6)     // Catch:{ Exception -> 0x0154 }
            java.lang.String r6 = r7.f6337d     // Catch:{ Exception -> 0x0154 }
            r5.append(r6)     // Catch:{ Exception -> 0x0154 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0154 }
            android.util.Log.d(r4, r5)     // Catch:{ Exception -> 0x0154 }
            goto L_0x0158
        L_0x0154:
            r4 = move-exception
            com.qidian.QDReader.core.log.QDLog.exception(r4)
        L_0x0158:
            android.app.Application r4 = com.qidian.QDReader.core.ApplicationContext.getInstance()     // Catch:{ NameNotFoundException -> 0x017d }
            if (r4 == 0) goto L_0x0181
            android.app.Application r4 = com.qidian.QDReader.core.ApplicationContext.getInstance()     // Catch:{ NameNotFoundException -> 0x017d }
            android.content.pm.PackageManager r4 = r4.getPackageManager()     // Catch:{ NameNotFoundException -> 0x017d }
            android.app.Application r5 = com.qidian.QDReader.core.ApplicationContext.getInstance()     // Catch:{ NameNotFoundException -> 0x017d }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ NameNotFoundException -> 0x017d }
            android.content.pm.PackageInfo r3 = r4.getPackageInfo(r5, r3)     // Catch:{ NameNotFoundException -> 0x017d }
            if (r3 == 0) goto L_0x0181
            int r4 = r3.versionCode     // Catch:{ NameNotFoundException -> 0x017d }
            r7.f6344k = r4     // Catch:{ NameNotFoundException -> 0x017d }
            java.lang.String r3 = r3.versionName     // Catch:{ NameNotFoundException -> 0x017d }
            r7.f6339f = r3     // Catch:{ NameNotFoundException -> 0x017d }
            goto L_0x0181
        L_0x017d:
            r3 = move-exception
            com.qidian.QDReader.core.log.QDLog.exception(r3)
        L_0x0181:
            int r3 = com.qidian.QDReader.core.p115i.EmulatorUtil.m20036a()
            r7.f6333I = r3
            r7.f6331G = r2
            r2 = 30
            r7.f6332H = r2
            java.lang.String r2 = "QDReader"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "appinfo使用时间:"
            r3.append(r4)
            long r4 = java.lang.System.currentTimeMillis()
            long r4 = r4 - r0
            r3.append(r4)
            java.lang.String r0 = r3.toString()
            android.util.Log.d(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qidian.QDReader.core.config.AppInfo.m9261E():void");
    }

    /* renamed from: F */
    private void m9262F() {
        QDConfig.getInstance().mo36513a(this.f6350q);
        try {
            if (!TextUtils.isEmpty(this.f6338e)) {
                this.f6342i = this.f6338e;
                return;
            }
            String GetSetting = QDConfig.getInstance().GetSetting("Source", "");
            if (TextUtils.isEmpty(GetSetting)) {
                this.f6342i = this.f6345l;
                QDConfig.getInstance().SetSetting("Source", this.f6342i);
            } else {
                this.f6342i = GetSetting;
            }
        } catch (Exception e) {
            QDLog.exception(e);
        }
    }

    /* renamed from: c */
    public boolean mo21328c() {
        return this.f6326B;
    }

    /* renamed from: d */
    public int mo21329d() {
        return this.f6327C;
    }

    /* renamed from: e */
    public void mo21331e() {
        if (TextUtils.isEmpty(this.f6350q)) {
            this.f6350q = m9265I();
            if (TextUtils.isEmpty(this.f6350q)) {
                m9266J();
            }
            QDConfig.getInstance().SetSetting("UUID", this.f6350q);
            StringBuilder sb = new StringBuilder();
            sb.append("saveIMEI QD IMEI = ");
            sb.append(this.f6350q);
            QDLog.m9352d(sb.toString());
        }
    }

    /* renamed from: G */
    private String m9263G() {
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /proc/cpuinfo").getInputStream()));
            for (int i = 1; i < 100; i++) {
                String readLine = lineNumberReader.readLine();
                if (readLine == null) {
                    return "0000000000000000";
                }
                if (readLine.contains("Serial")) {
                    return readLine.substring(readLine.indexOf(":") + 1, readLine.length()).trim();
                }
            }
            return "0000000000000000";
        } catch (Exception e) {
            QDLog.exception(e);
            return "0000000000000000";
        }
    }

    /* renamed from: H */
    private String m9264H() {
        String[] strArr = {"null", "null", "null", "null"};
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/version"), 8192);
            strArr[0] = bufferedReader.readLine().split("\\s+")[2];
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        strArr[1] = VERSION.RELEASE;
        strArr[2] = Build.MODEL;
        strArr[3] = Build.DISPLAY;
        StringBuilder sb = new StringBuilder();
        for (String append : strArr) {
            sb.append(append);
        }
        return sb.toString().replaceAll("\\|", "_");
    }

    @Deprecated
    /* renamed from: f */
    public String mo21333f() {
        try {
            return DES.m20014a(mo21334g(), (String) "0821CAAD409B8402");
        } catch (Exception e) {
            QDLog.exception(e);
            return "";
        }
    }

    @Deprecated
    /* renamed from: g */
    public String mo21334g() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f6350q);
        sb.append("|");
        sb.append(this.f6339f);
        sb.append("|");
        sb.append(this.f6355v);
        sb.append("|");
        sb.append(this.f6356w);
        sb.append("|");
        sb.append(this.f6342i);
        sb.append("|");
        sb.append(this.f6357x);
        sb.append("|");
        sb.append(this.f6343j);
        sb.append("|");
        sb.append(this.f6358y);
        sb.append("|");
        sb.append(this.f6344k);
        sb.append("|");
        sb.append(this.f6345l);
        sb.append("|");
        sb.append(this.f6346m);
        sb.append("|");
        sb.append(mo21350x());
        sb.append("|");
        sb.append(System.currentTimeMillis());
        sb.append("|");
        sb.append(this.f6333I);
        if (this.f6347n == null) {
            sb.append("|");
            sb.append("|");
            sb.append("");
            sb.append("|");
            sb.append("");
            sb.append("|");
            sb.append("");
        } else {
            sb.append("|");
            sb.append(this.f6347n.getLongitude());
            sb.append("|");
            sb.append(this.f6347n.getLatitude());
            sb.append("|");
            String countryCode = this.f6347n.getCountryCode();
            if (TextUtils.isEmpty(countryCode)) {
                countryCode = "";
            }
            sb.append(countryCode);
            sb.append("|");
            String locality = this.f6347n.getLocality();
            if (TextUtils.isEmpty(locality)) {
                locality = "";
            }
            sb.append(locality);
        }
        return sb.toString();
    }

    /* renamed from: h */
    public String mo21335h() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f6350q);
        sb.append("|");
        sb.append(this.f6339f);
        sb.append("|");
        sb.append(this.f6355v);
        sb.append("|");
        sb.append(this.f6356w);
        sb.append("|");
        sb.append(this.f6342i);
        sb.append("|");
        sb.append(this.f6357x);
        sb.append("|");
        sb.append(this.f6343j);
        sb.append("|");
        sb.append(this.f6358y);
        sb.append("|");
        sb.append(this.f6344k);
        sb.append("|");
        sb.append(this.f6345l);
        sb.append("|");
        sb.append(System.currentTimeMillis());
        sb.append("|");
        sb.append(this.f6333I);
        if (this.f6347n == null) {
            sb.append("|");
            sb.append("|");
            sb.append("|");
            sb.append("|");
        } else {
            sb.append("|");
            sb.append(this.f6347n.getLongitude());
            sb.append("|");
            sb.append(this.f6347n.getLatitude());
            sb.append("|");
            String countryCode = this.f6347n.getCountryCode();
            if (TextUtils.isEmpty(countryCode)) {
                countryCode = "";
            }
            sb.append(countryCode);
            sb.append("|");
            String locality = this.f6347n.getLocality();
            if (TextUtils.isEmpty(locality)) {
                locality = "";
            }
            sb.append(locality);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public void mo21321a(Address address) {
        if (address != null) {
            this.f6347n = address;
        }
    }

    /* renamed from: i */
    public String mo21336i() {
        StringBuilder sb = new StringBuilder();
        sb.append("IMEI:");
        sb.append(this.f6350q);
        sb.append("||PhoneModel:");
        sb.append(this.f6358y);
        sb.append("|Source:");
        sb.append(this.f6342i);
        sb.append("|SDK:");
        sb.append(this.f6357x);
        sb.append("|VersionCode:");
        sb.append(this.f6344k);
        sb.append("|VersionName:");
        sb.append(this.f6339f);
        return sb.toString();
    }

    /* renamed from: j */
    public String mo21337j() {
        return this.f6348o;
    }

    /* renamed from: k */
    public String mo21338k() {
        return this.f6349p;
    }

    /* renamed from: l */
    public String mo21339l() {
        return this.f6342i;
    }

    /* renamed from: m */
    public int mo21340m() {
        return this.f6344k;
    }

    /* renamed from: n */
    public String mo21341n() {
        return this.f6339f;
    }

    /* renamed from: o */
    public String mo21342o() {
        return this.f6345l;
    }

    /* renamed from: p */
    public String mo21343p() {
        return this.f6350q;
    }

    /* renamed from: b */
    public void mo21325b(String str) {
        try {
            String replaceAll = str.replaceAll("[^\u001f-]", "");
            StringBuilder sb = new StringBuilder();
            sb.append(replaceAll);
            sb.append(" QDHWReaderAndroid/");
            sb.append(this.f6339f);
            sb.append("/");
            sb.append(this.f6344k);
            sb.append("/");
            sb.append(this.f6342i);
            sb.append("/");
            sb.append(mo21343p());
            this.f6348o = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(" QDHWReaderAndroid/");
            sb2.append(this.f6339f);
            sb2.append("/");
            sb2.append(this.f6344k);
            sb2.append("/");
            sb2.append(this.f6342i);
            sb2.append("/");
            sb2.append(mo21343p());
            this.f6349p = sb2.toString();
        } catch (Exception e) {
            QDLog.exception(e);
        }
    }

    /* renamed from: q */
    public int mo21344q() {
        return this.f6355v;
    }

    /* renamed from: a */
    public void mo21320a(int i) {
        this.f6355v = i;
    }

    /* renamed from: r */
    public int mo21345r() {
        return this.f6356w;
    }

    /* renamed from: b */
    public void mo21324b(int i) {
        this.f6356w = i;
    }

    /* renamed from: s */
    public String mo21346s() {
        return this.f6357x;
    }

    /* renamed from: t */
    public String mo21347t() {
        return this.f6358y;
    }

    /* renamed from: u */
    public String mo21348u() {
        return this.f6359z;
    }

    /* renamed from: v */
    public String mo21349v() {
        if (TextUtils.isEmpty(this.f6337d) || this.f6337d.length() <= 7) {
            return this.f6337d;
        }
        return this.f6337d.substring(0, 8);
    }

    /* renamed from: a */
    public static boolean m9268a(Context context) {
        boolean z = false;
        try {
            if ((context.getResources().getConfiguration().screenLayout & 15) >= 3) {
                z = true;
            }
            return z;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: w */
    public static String m9269w() {
        return TextUtils.isEmpty("") ? m9267a().mo21343p() : "";
    }

    /* renamed from: x */
    public long mo21350x() {
        String GetSetting = QDConfig.getInstance().GetSetting("SettingYWGuid", "-1");
        if (!TextUtils.isEmpty(GetSetting)) {
            try {
                return Long.valueOf(GetSetting).longValue();
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    /* renamed from: y */
    public int mo21351y() {
        return this.f6329E;
    }

    /* renamed from: c */
    public void mo21326c(int i) {
        this.f6329E = i;
        mo21332e(i);
    }

    /* renamed from: z */
    public int mo21352z() {
        return this.f6328D;
    }

    /* renamed from: d */
    public void mo21330d(int i) {
        this.f6328D = i;
    }

    /* renamed from: e */
    public void mo21332e(int i) {
        this.f6330F = i;
    }

    /* renamed from: A */
    public int mo21316A() {
        int i = this.f6329E;
        int i2 = this.f6330F;
        return i != i2 ? i2 : i;
    }

    /* renamed from: I */
    private String m9265I() {
        String str = "";
        File file = new File(QDPath.m9322k());
        if (file.exists()) {
            str = QDFileUtil.m9325a(file);
            if (TextUtils.isEmpty(str)) {
                file.delete();
            }
        }
        return str;
    }

    /* renamed from: J */
    private void m9266J() {
        if (TextUtils.isEmpty(this.f6350q)) {
            File file = new File(QDPath.m9322k());
            if (file.exists()) {
                this.f6350q = QDFileUtil.m9325a(file);
                if (TextUtils.isEmpty(this.f6350q)) {
                    file.delete();
                }
            }
            this.f6350q = DeviceUtils.m20031b().replaceAll("-", "");
            QDFileUtil.m9337b(file, this.f6350q);
        }
    }

    /* renamed from: B */
    public String mo21317B() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(this.f6350q);
            sb.append("|");
            sb.append("");
            sb.append("|");
            sb.append(String.valueOf(System.currentTimeMillis() / 1000));
            return Uri.encode(DES3Utils.m20016a(sb.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: c */
    public void mo21327c(String str) {
        this.f6335K = str;
    }

    /* renamed from: C */
    public String mo21318C() {
        return this.f6335K;
    }
}