package com.qidian.QDReader.core.network;

import android.os.Build.VERSION;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import com.qidian.QDReader.core.config.AppInfo;
import com.qidian.QDReader.core.config.QDConfig;
import com.qidian.QDReader.core.p115i.CommonUtil;
import com.qidian.QDReader.core.p115i.LanguageUtils;
import java.util.List;

/* renamed from: com.qidian.QDReader.core.network.g */
public class QDHttpCookie {

    /* renamed from: a */
    public static String f14530a = ".webnovel.com";

    /* renamed from: b */
    private static QDHttpCookie f14531b;

    /* renamed from: a */
    public static synchronized QDHttpCookie m20084a() {
        QDHttpCookie gVar;
        synchronized (QDHttpCookie.class) {
            try {
                if (f14531b == null) {
                    f14531b = new QDHttpCookie();
                }
                gVar = f14531b;
            }
        }
        return gVar;
    }

    /* renamed from: a */
    public String mo36602a(String str) {
        long j;
        StringBuilder sb = new StringBuilder();
        String GetSetting = QDConfig.getInstance().GetSetting("SettingYWKey", "");
        try {
            j = Long.parseLong(QDConfig.getInstance().GetSetting("SettingYWGuid", "-1"));
        } catch (Exception unused) {
            j = -1;
        }
        if (TextUtils.isEmpty(GetSetting) || j <= 0) {
            sb.append("; QDInfo=");
            sb.append(AppInfo.m9267a().mo21333f());
            if (TextUtils.isEmpty(str)) {
                sb.append("; domain=");
                sb.append(f14530a);
            } else {
                sb.append("; domain=");
                sb.append(str);
            }
            sb.append("; lang=");
            sb.append(CommonUtil.m20013b());
            sb.append("; imei=");
            sb.append(AppInfo.m9267a().mo21343p());
        } else {
            sb.append("QDInfo=");
            sb.append(AppInfo.m9267a().mo21333f());
            if (TextUtils.isEmpty(str)) {
                sb.append("; domain=");
                sb.append(f14530a);
            } else {
                sb.append("; domain=");
                sb.append(str);
            }
            sb.append("; lang=");
            sb.append(CommonUtil.m20013b());
            sb.append("; ywkey=");
            sb.append(GetSetting);
            sb.append("; appId=");
            sb.append(AppInfo.m9267a().mo21316A());
            sb.append("; areaId=");
            sb.append(AppInfo.m9267a().mo21352z());
            sb.append("; ywguid=");
            sb.append(j);
            sb.append("; loginsign=");
            sb.append(AppInfo.m9267a().mo21317B());
            sb.append("; imei=");
            sb.append(AppInfo.m9267a().mo21343p());
        }
        sb.append("; webnovel-language=");
        sb.append(LanguageUtils.m9342a().mo21357c());
        sb.append("; webnovel-content-language=");
        sb.append(LanguageUtils.m9342a().mo21358d());
        return sb.toString();
    }

    /* renamed from: b */
    public void mo36605b() {
        mo36603a((WebView) null);
    }

    /* renamed from: a */
    public void mo36603a(WebView webView) {
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        if (webView != null && VERSION.SDK_INT >= 21) {
            instance.setAcceptThirdPartyCookies(webView, true);
        }
        String str = f14530a;
        instance.setCookie(str, mo36602a(str));
        String str2 = f14530a;
        StringBuilder sb = new StringBuilder();
        sb.append("QDInfo=");
        sb.append(AppInfo.m9267a().mo21333f());
        instance.setCookie(str2, sb.toString());
        String str3 = f14530a;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("ywkey=");
        sb2.append(QDConfig.getInstance().GetSetting("SettingYWKey", ""));
        instance.setCookie(str3, sb2.toString());
        String str4 = f14530a;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("appId=");
        sb3.append(AppInfo.m9267a().mo21316A());
        instance.setCookie(str4, sb3.toString());
        String str5 = f14530a;
        StringBuilder sb4 = new StringBuilder();
        sb4.append("areaId=");
        sb4.append(AppInfo.m9267a().mo21352z());
        instance.setCookie(str5, sb4.toString());
        String str6 = f14530a;
        StringBuilder sb5 = new StringBuilder();
        sb5.append("lang=");
        sb5.append(CommonUtil.m20013b());
        instance.setCookie(str6, sb5.toString());
        String str7 = f14530a;
        StringBuilder sb6 = new StringBuilder();
        sb6.append("ywguid=");
        sb6.append(QDConfig.getInstance().GetSetting("SettingYWGuid", ""));
        instance.setCookie(str7, sb6.toString());
        String str8 = f14530a;
        StringBuilder sb7 = new StringBuilder();
        sb7.append("loginsign=");
        sb7.append(AppInfo.m9267a().mo21317B());
        instance.setCookie(str8, sb7.toString());
        String str9 = f14530a;
        StringBuilder sb8 = new StringBuilder();
        sb8.append("imei =");
        sb8.append(AppInfo.m9267a().mo21343p());
        instance.setCookie(str9, sb8.toString());
        String str10 = f14530a;
        StringBuilder sb9 = new StringBuilder();
        sb9.append("webnovel-language=");
        sb9.append(LanguageUtils.m9342a().mo21357c());
        instance.setCookie(str10, sb9.toString());
        String str11 = f14530a;
        StringBuilder sb10 = new StringBuilder();
        sb10.append("webnovel-content-language=");
        sb10.append(LanguageUtils.m9342a().mo21358d());
        instance.setCookie(str11, sb10.toString());
        StringBuilder sb11 = new StringBuilder();
        sb11.append("ywguid=");
        sb11.append(QDConfig.getInstance().GetSetting("SettingYWGuid", ""));
        instance.setCookie(".yuewen.com", sb11.toString());
        StringBuilder sb12 = new StringBuilder();
        sb12.append("ywkey=");
        sb12.append(QDConfig.getInstance().GetSetting("SettingYWKey", ""));
        instance.setCookie(".yuewen.com", sb12.toString());
        StringBuilder sb13 = new StringBuilder();
        sb13.append("loginsign=");
        sb13.append(AppInfo.m9267a().mo21317B());
        instance.setCookie(".yuewen.com", sb13.toString());
        StringBuilder sb14 = new StringBuilder();
        sb14.append("imei =");
        sb14.append(AppInfo.m9267a().mo21343p());
        instance.setCookie(".yuewen.com", sb14.toString());
        StringBuilder sb15 = new StringBuilder();
        sb15.append("ywguid=");
        sb15.append(QDConfig.getInstance().GetSetting("SettingYWGuid", ""));
        instance.setCookie(".qidian.com", sb15.toString());
        StringBuilder sb16 = new StringBuilder();
        sb16.append("ywkey=");
        sb16.append(QDConfig.getInstance().GetSetting("SettingYWKey", ""));
        instance.setCookie(".qidian.com", sb16.toString());
        StringBuilder sb17 = new StringBuilder();
        sb17.append("loginsign=");
        sb17.append(AppInfo.m9267a().mo21317B());
        instance.setCookie(".qidian.com", sb17.toString());
        StringBuilder sb18 = new StringBuilder();
        sb18.append("imei =");
        sb18.append(AppInfo.m9267a().mo21343p());
        instance.setCookie(".qidian.com", sb18.toString());
        StringBuilder sb19 = new StringBuilder();
        sb19.append("ywguid=");
        sb19.append(QDConfig.getInstance().GetSetting("SettingYWGuid", ""));
        instance.setCookie(".qq.com", sb19.toString());
        StringBuilder sb20 = new StringBuilder();
        sb20.append("ywkey=");
        sb20.append(QDConfig.getInstance().GetSetting("SettingYWKey", ""));
        instance.setCookie(".qq.com", sb20.toString());
        StringBuilder sb21 = new StringBuilder();
        sb21.append("loginsign=");
        sb21.append(AppInfo.m9267a().mo21317B());
        instance.setCookie(".qq.com", sb21.toString());
        StringBuilder sb22 = new StringBuilder();
        sb22.append("imei =");
        sb22.append(AppInfo.m9267a().mo21343p());
        instance.setCookie(".qq.com", sb22.toString());
        CookieSyncManager.getInstance().sync();
    }

    /* renamed from: c */
    public void mo36606c() {
        CookieManager.getInstance().removeAllCookie();
    }

    /* renamed from: a */
    public void mo36604a(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (str != null) {
                CookieManager.getInstance().setCookie(f14530a, str);
            }
        }
    }
}