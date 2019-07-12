package com.qidian.QDReader.components.user;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import com.google.android.gms.measurement.AppMeasurement.Param;
import com.qidian.QDReader.components.api.QDRequestLimit;
import com.qidian.QDReader.components.api.Urls;
import com.qidian.QDReader.components.book.QDBookManager;
import com.qidian.QDReader.components.book.QDCategoryManager;
import com.qidian.QDReader.components.setting.CloudConfig;
import com.qidian.QDReader.core.ApplicationContext;
import com.qidian.QDReader.core.config.AppInfo;
import com.qidian.QDReader.core.config.QDConfig;
import com.qidian.QDReader.core.log.QDLog;
import com.qidian.QDReader.core.network.QDHttpCookie;
import com.qidian.QDReader.core.network.QDThreadPool;
import com.qidian.QDReader.core.p114e.QDFileUtil;
import com.qidian.QDReader.core.p115i.DES3Utils;
import com.qidian.QDReader.core.p115i.StringUtils;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import com.unity3d.ads.metadata.MediationMetaData;
import com.yuewen.library.http.QDHttpCallBack;
import com.yuewen.library.http.QDHttpClient.C6270a;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

public class QDLoginManager {

    /* renamed from: a */
    private static QDLoginManager f14354a;

    /* renamed from: b */
    private Context f14355b = ApplicationContext.getInstance();

    /* renamed from: c */
    private int f14356c;

    /* renamed from: d */
    private int f14357d;

    /* renamed from: e */
    private String f14358e;

    /* renamed from: f */
    private String f14359f;

    /* renamed from: g */
    private String f14360g;

    /* renamed from: h */
    private String f14361h;

    /* renamed from: com.qidian.QDReader.components.user.QDLoginManager$a */
    public interface C5270a {
        /* renamed from: a */
        void mo33479a();

        /* renamed from: a */
        void mo33480a(int i, String str);
    }

    /* renamed from: com.qidian.QDReader.components.user.QDLoginManager$b */
    public interface C5271b {
        /* renamed from: a */
        void mo33656a();

        /* renamed from: a */
        void mo33657a(int i, String str);

        /* renamed from: a */
        void mo33658a(String str);

        /* renamed from: a */
        void mo33659a(JSONObject jSONObject);

        /* renamed from: b */
        void mo33660b(String str);

        /* renamed from: c */
        void mo33661c(String str);

        /* renamed from: d */
        void mo33662d(String str);
    }

    public static synchronized QDLoginManager getInstance() {
        QDLoginManager qDLoginManager;
        synchronized (QDLoginManager.class) {
            try {
                if (f14354a == null) {
                    f14354a = new QDLoginManager();
                }
                qDLoginManager = f14354a;
            }
        }
        return qDLoginManager;
    }

    private QDLoginManager() {
        mo36456a();
    }

    /* renamed from: a */
    public void mo36456a() {
        this.f14356c = AppInfo.m9267a().mo21351y();
        this.f14357d = AppInfo.m9267a().mo21352z();
        this.f14358e = AppInfo.m9267a().mo21339l();
        this.f14359f = AppInfo.m9267a().mo21343p();
        this.f14360g = AppInfo.m9269w();
        this.f14361h = AppInfo.m9267a().mo21341n();
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public ContentValues m19583d() {
        ContentValues contentValues = new ContentValues();
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(this.f14359f);
            stringBuffer.append("|");
            stringBuffer.append(this.f14360g);
            stringBuffer.append("|");
            stringBuffer.append(String.valueOf(System.currentTimeMillis() / 1000));
            String a = DES3Utils.m20016a(stringBuffer.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("signature :");
            sb.append(a);
            QDLog.m9355e("Qidian", sb.toString());
            contentValues.put(InAppPurchaseMetaData.KEY_SIGNATURE, Uri.encode(a));
            contentValues.put("appid", Integer.valueOf(this.f14356c));
            contentValues.put("areaid", Integer.valueOf(this.f14357d));
            contentValues.put("format", "json");
            contentValues.put("source", this.f14358e);
            contentValues.put(MediationMetaData.KEY_VERSION, this.f14361h);
            contentValues.put("auto", Integer.valueOf(1));
            contentValues.put("autotime", Integer.valueOf(30));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentValues;
    }

    /* renamed from: a */
    public void mo36463a(String str, C5270a aVar) {
        QDThreadPool.getInstance(0).submit(new C5275e(this, str, new Handler(), aVar));
    }

    /* renamed from: a */
    public void mo36464a(String str, String str2, C5270a aVar) {
        Handler handler = new Handler();
        ExecutorService instance = QDThreadPool.getInstance(0);
        C5276f fVar = new C5276f(this, str, str2, handler, aVar);
        instance.submit(fVar);
    }

    /* renamed from: b */
    public void mo36467b(String str, C5270a aVar) {
        QDThreadPool.getInstance(0).submit(new C5277g(this, str, new Handler(), aVar));
    }

    /* renamed from: a */
    public void mo36462a(String str, long j, String str2, String str3, C5270a aVar) {
        Handler handler = new Handler();
        ExecutorService instance = QDThreadPool.getInstance(0);
        C5278h hVar = new C5278h(this, str, j, str2, str3, aVar, handler);
        instance.submit(hVar);
    }

    /* renamed from: a */
    public static void m19580a(Context context) {
        QDFileUtil.m9334a(QDUserManager.getInstance().mo36491j());
        QDFileUtil.m9334a(QDUserManager.getInstance().mo36492k());
        QDHttpCookie.m20084a().mo36606c();
        m19581b();
        QDHttpCookie.m20084a().mo36605b();
        QDUserManager.getInstance().mo36472a((String) "");
        QDBookManager.m19329a().mo34954c();
        QDCategoryManager.m19198a().mo34848b();
        QDRequestLimit.m19046a(true);
        CloudConfig.getInstance().mo36372a(context, null);
        QDConfig.getInstance().SetSetting("SettingVisitorLoginMode", "Yes");
    }

    /* renamed from: b */
    public static void m19581b() {
        QDConfig.getInstance().SetSetting("SettingYWKey", "");
        QDConfig.getInstance().SetSetting("SettingYWGuid", "0");
    }

    /* renamed from: b */
    public static void m19582b(Context context) {
        QDThreadPool.getInstance(0).submit(new C5279i(context));
    }

    /* renamed from: c */
    public void mo36468c() {
        String GetSetting = QDConfig.getInstance().GetSetting("SettingLoginCheckStatus", "0");
        long longValue = Long.valueOf(GetSetting).longValue();
        long longValue2 = Long.valueOf(StringUtils.m19967a(System.currentTimeMillis())).longValue();
        StringBuilder sb = new StringBuilder();
        sb.append("自动续期：上次时间[ ");
        sb.append(GetSetting);
        sb.append(" ]; 当前时间[ ");
        sb.append(longValue2);
        sb.append(" ]");
        QDLog.m9353d("Qidian", sb.toString());
        if (longValue != longValue2) {
            QDThreadPool.getInstance(0).submit(new C5280j(this));
        }
    }

    /* renamed from: a */
    public void mo36461a(Context context, String str, String str2, String str3, int i, int i2, String str4, int i3, C5271b bVar) {
        String aa = Urls.m18926aa();
        StringBuilder sb = new StringBuilder();
        sb.append("邮件注册Url :");
        sb.append(aa);
        QDLog.m9353d("Qidian", sb.toString());
        ContentValues d = m19583d();
        d.put("account", str);
        d.put("password", str2);
        if (!TextUtils.isEmpty(str3)) {
            d.put("code", str3);
        }
        if (i > 0) {
            d.put("ver", Integer.valueOf(i));
        }
        d.put("nextAction", Integer.valueOf(i2));
        if (!TextUtils.isEmpty(str4)) {
            d.put("fromuid", str4);
        }
        d.put(Param.TYPE, Integer.valueOf(i3));
        new C6270a().mo42237a().mo42230a(context.toString(), aa, d, (QDHttpCallBack) new C5281k(this, bVar));
    }

    /* renamed from: a */
    public void mo36458a(Context context, String str, QDHttpCallBack oVar) {
        String ab = Urls.m18927ab();
        StringBuilder sb = new StringBuilder();
        sb.append("重发邮件注册验证码Url :");
        sb.append(ab);
        QDLog.m9353d("Qidian", sb.toString());
        ContentValues d = m19583d();
        d.put("emailkey", str);
        new C6270a().mo42237a().mo42230a(context.toString(), ab, d, oVar);
    }

    /* renamed from: a */
    public void mo36459a(Context context, String str, String str2, QDHttpCallBack oVar) {
        String ac = Urls.m18928ac();
        StringBuilder sb = new StringBuilder();
        sb.append("邮件短信激活接口Url :");
        sb.append(ac);
        QDLog.m9353d("Qidian", sb.toString());
        ContentValues d = m19583d();
        d.put("code", str);
        d.put("emailkey", str2);
        new C6270a().mo42237a().mo42230a(context.toString(), ac, d, oVar);
    }

    /* renamed from: a */
    public void mo36460a(Context context, String str, String str2, String str3, int i, int i2, int i3, C5271b bVar) {
        String p = Urls.m18990p();
        StringBuilder sb = new StringBuilder();
        sb.append("邮箱登录Url : ");
        sb.append(p);
        QDLog.m9353d("Qidian", sb.toString());
        ContentValues d = m19583d();
        d.put("username", str);
        d.put("password", str2);
        if (!TextUtils.isEmpty(str3)) {
            d.put("code", str3);
        }
        if (i > 0) {
            d.put("ver", Integer.valueOf(i));
        }
        d.put("nextAction", Integer.valueOf(i2));
        d.put(Param.TYPE, Integer.valueOf(i3));
        new C6270a().mo42237a().mo42230a(context.toString(), p, d, (QDHttpCallBack) new C5282l(this, bVar));
    }

    /* renamed from: b */
    public void mo36465b(Context context, String str, QDHttpCallBack oVar) {
        String ae = Urls.m18930ae();
        StringBuilder sb = new StringBuilder();
        sb.append("发非信任设备较验邮件Url :");
        sb.append(ae);
        QDLog.m9353d("Qidian", sb.toString());
        ContentValues d = m19583d();
        try {
            d.put("encry", URLEncoder.encode(str, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        new C6270a().mo42237a().mo42230a(context.toString(), ae, d, oVar);
    }

    /* renamed from: c */
    public void mo36469c(Context context, String str, QDHttpCallBack oVar) {
        String af = Urls.m18931af();
        StringBuilder sb = new StringBuilder();
        sb.append("重发非信任设备较验邮件Url :");
        sb.append(af);
        QDLog.m9353d("Qidian", sb.toString());
        ContentValues d = m19583d();
        try {
            d.put("encry", URLEncoder.encode(str, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        new C6270a().mo42237a().mo42230a(context.toString(), af, d, oVar);
    }

    /* renamed from: b */
    public void mo36466b(Context context, String str, String str2, QDHttpCallBack oVar) {
        String ag = Urls.m18932ag();
        StringBuilder sb = new StringBuilder();
        sb.append("校验非信任设备验证码Url :");
        sb.append(ag);
        QDLog.m9353d("Qidian", sb.toString());
        ContentValues d = m19583d();
        try {
            d.put("encry", URLEncoder.encode(str, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        d.put("code", str2);
        new C6270a().mo42237a().mo42230a(context.toString(), ag, d, oVar);
    }

    /* renamed from: a */
    public void mo36457a(Context context, String str, int i, String str2, int i2, int i3, C5271b bVar) {
        String ad = Urls.m18929ad();
        StringBuilder sb = new StringBuilder();
        sb.append("忘记密码发邮件接口Url :");
        sb.append(ad);
        QDLog.m9353d("Qidian", sb.toString());
        ContentValues d = m19583d();
        d.put("email", str);
        d.put("nextAction", Integer.valueOf(i));
        if (!TextUtils.isEmpty(str2)) {
            d.put("code", str2);
        }
        if (i2 > 0) {
            d.put("ver", Integer.valueOf(i2));
        }
        d.put(Param.TYPE, Integer.valueOf(i3));
        new C6270a().mo42237a().mo42230a(context.toString(), ad, d, (QDHttpCallBack) new C5283m(this, bVar));
    }
}