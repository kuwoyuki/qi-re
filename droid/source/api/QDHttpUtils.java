package com.qidian.Int.reader.utils;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.react.modules.network.OkHttpClientProvider;
import com.qidian.Int.reader.p112i.QDAuthInterceptor;
import com.qidian.Int.reader.p112i.QDAuthInterceptor.C4855a;
import com.qidian.QDReader.components.user.QDLoginManager;
import com.qidian.QDReader.components.user.QDUserManager;
import com.qidian.QDReader.core.config.AppInfo;
import com.qidian.QDReader.core.config.QDConfig;
import com.qidian.QDReader.core.config.QDPath;
import com.qidian.QDReader.core.log.QDLog;
import com.qidian.QDReader.core.network.QDHttpCookie;
import com.qidian.QDReader.core.p115i.AESUtils;
import com.qidian.QDReader.readerengine.p181a.QDRichPageCache;
import com.yuewen.library.http.IAuthInterceptor;
import com.yuewen.library.http.QDHttpClient;
import com.yuewen.library.http.YHttpConfig;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import p012b.p013a.p014a.Pandora;

/* renamed from: com.qidian.Int.reader.utils.ad */
public class QDHttpUtils {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static OkHttpClient f6269a;

    /* renamed from: a */
    public static void m9214a(Context context) {
        YHttpConfig.m25197a(context);
        QDHttpClient.m25083a(QDPath.m9310b(), 31457280);
        QDHttpClient.m25085a(false);
        QDHttpClient.m25081a().mo42219a($$Lambda$ad$5xKU42rSelHNEFQWOn_7shX__A4.INSTANCE);
        QDHttpClient.m25086b().mo42217a($$Lambda$ad$6X5B0KSTfPJKUstrZ9rFOd53z74.INSTANCE);
        QDHttpClient.m25082a((IAuthInterceptor) new QDAuthInterceptor(new C4855a(context) {
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final void clearToken() {
                QDHttpUtils.m9217b(this.f$0);
            }
        }));
        QDHttpClient.m25084a((Interceptor) Pandora.m314a().mo8983b());
        m9213a();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public static /* synthetic */ Map m9211a(Object obj, String str, String str2) {
        String a = QDHttpCookie.m20084a().mo36602a(QDHttpCookie.f14530a);
        String f = AppInfo.m9267a().mo21333f();
        String j = AppInfo.m9267a().mo21337j();
        String h = AppInfo.m9267a().mo21335h();
        HashMap hashMap = new HashMap();
        hashMap.put("QDInfo", f);
        hashMap.put("User-Agent", j);
        hashMap.put("Cookie", a);
        if (!TextUtils.isEmpty(h)) {
            hashMap.put("wdToken", AESUtils.m19922a(h));
            StringBuilder sb = new StringBuilder();
            sb.append("wdToken : ");
            sb.append(AESUtils.m19922a(h));
            sb.append(" , ywkey :");
            sb.append(QDConfig.getInstance().GetSetting("SettingYWKey", ""));
            sb.append(" , ywguid:");
            sb.append(QDConfig.getInstance().GetSetting("SettingYWGuid", ""));
            QDLog.m9352d(sb.toString());
        }
        if (!TextUtils.isEmpty(str) && str.contains(QDHttpCookie.f14530a)) {
            hashMap.put("Content-Encoding", "gzip");
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static /* synthetic */ void m9217b(Context context) {
        if (QDUserManager.getInstance().mo36478b()) {
            QDLoginManager.m19582b(context.getApplicationContext());
            QDRichPageCache.m20167a().mo36612b();
            QDConfig.getInstance().SetSetting("SettingLoginOut", "LoginFailed");
            RNEventUtils.m17928a();
        }
    }

    /* renamed from: a */
    public static void m9213a() {
        OkHttpClientProvider.setOkHttpClientFactory($$Lambda$ad$8OJRQF525h1bKGpLInM8UR0Arsk.INSTANCE);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public static /* synthetic */ Response m9212a(Chain chain) throws IOException {
        Request request = chain.request();
        Builder removeHeader = request.newBuilder().removeHeader("Cookie");
        removeHeader.header("Cookie", QDHttpCookie.m20084a().mo36602a(QDHttpCookie.f14530a));
        removeHeader.method(request.method(), request.body());
        return chain.proceed(removeHeader.build());
    }
}