package com.qidian.QDReader.core.p115i;

import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.qidian.QDReader.core.ApplicationContext;
import java.util.Random;
import java.util.UUID;

/* renamed from: com.qidian.QDReader.core.i.m */
public class DeviceUtils {
    /* renamed from: a */
    public static String m20030a() {
        String str = "";
        try {
            str = Secure.getString(ApplicationContext.getInstance().getContentResolver(), "android_id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(str) && !"9774d56d682e549c".equals(str)) {
            return str;
        }
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toHexString(random.nextInt()));
        sb.append(Integer.toHexString(random.nextInt()));
        sb.append(Integer.toHexString(random.nextInt()));
        return sb.toString();
    }

    /* renamed from: f */
    private static String m20035f() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Build.BRAND);
        stringBuffer.append("/");
        stringBuffer.append(Build.PRODUCT);
        stringBuffer.append("/");
        stringBuffer.append(Build.DEVICE);
        stringBuffer.append("/");
        stringBuffer.append(Build.ID);
        stringBuffer.append("/");
        stringBuffer.append(VERSION.INCREMENTAL);
        return stringBuffer.toString();
    }

    /* renamed from: b */
    public static String m20031b() {
        return new UUID((long) m20030a().hashCode(), (long) m20035f().hashCode()).toString();
    }

    /* renamed from: c */
    public static int m20032c() {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) ApplicationContext.getInstance().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: d */
    public static int m20033d() {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) ApplicationContext.getInstance().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.widthPixels;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: e */
    public static int m20034e() {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return ApplicationContext.getInstance().getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return 38;
        }
    }
}