package com.qidian.QDReader.components.api;

import android.text.TextUtils;
import android.util.SparseArray;
import com.qidian.QDReader.core.config.AppInfo;
import com.qidian.QDReader.core.config.QDConfig;
import com.qidian.QDReader.core.log.QDLog;
import com.qidian.QDReader.core.p115i.DeviceUtils;
import com.tencent.midas.oversea.newnetwork.http.NetworkManager;

public class Urls {

    /* renamed from: a */
    public static String f14069a = "https://oaapp.webnovel.com";

    /* renamed from: b */
    public static int f14070b = 1;

    /* renamed from: c */
    public static String f14071c = "https://oaptlogin.webnovel.com/";

    /* renamed from: d */
    public static String f14072d = "https://oaidruid.webnovel.com/";

    /* renamed from: e */
    public static String f14073e = "https://devpay.webnovel.com/ajax/sdk/";

    /* renamed from: f */
    public static String f14074f = "https://activity.webnovel.com/noah/855041507";

    /* renamed from: g */
    private static SparseArray<String> f14075g = new SparseArray<>();

    /* renamed from: h */
    private static int f14076h;

    /* renamed from: a */
    public static boolean m18925a(int i, String str) {
        if (i < 61000) {
            throw new IllegalArgumentException("error param ,tag or url is error!");
        } else if (TextUtils.isEmpty(str)) {
            QDLog.m9354e("CoreUrl error ,url is null");
            return false;
        } else {
            try {
                f14075g.put(i, str);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /* renamed from: a */
    public static void m18924a(int i) {
        try {
            f14076h = i;
            f14075g.clear();
            String str = "";
            String str2 = "";
            String str3 = "";
            String str4 = "";
            String str5 = "";
            String str6 = "";
            String str7 = "";
            if (f14076h == 0) {
                str = f14069a;
                str2 = "https://oa-book-pic.webnovel.com/";
                str3 = f14072d;
                str4 = f14071c;
                str5 = "https://oa-user-pic.webnovel.com/";
                str6 = "https://oadynamic.webnovel.com/";
                str7 = f14073e;
            } else if (f14076h == 1) {
                str = "https://app.webnovel.com";
                str2 = "https://book-pic.webnovel.com/";
                str3 = "https://idruid.webnovel.com/";
                str4 = "https://ptlogin.webnovel.com/";
                str5 = "https://user-pic.webnovel.com/";
                str6 = "https://dynamic.webnovel.com/";
                str7 = "https://pay.webnovel.com/ajax/sdk/";
            } else if (f14076h == 2) {
                str = "https://preapp.webnovel.com";
                str2 = "https://book-pic.webnovel.com/";
                str3 = "https://preidruid.webnovel.com/";
                str4 = "https://preptlogin.webnovel.com/";
                str5 = "https://user-pic.webnovel.com/";
                str6 = "https://dynamic.webnovel.com/";
                str7 = "https://pay.webnovel.com/ajax/sdk/";
            } else if (f14076h == 3) {
                str = "https://devapp.webnovel.com";
                str2 = "https://oa-book-pic.webnovel.com/";
                str3 = "https://devidruid.webnovel.com/";
                str4 = "https://devptlogin.webnovel.com/";
                str5 = "https://oa-user-pic.webnovel.com/";
                str6 = "https://oadynamic.webnovel.com/";
                str7 = f14073e;
            }
            if (AppInfo.m9267a().mo21328c()) {
                int parseInt = Integer.parseInt(QDConfig.getInstance().GetSetting("UserDebugAppHost", "-1"));
                if (parseInt == 0) {
                    str3 = f14072d;
                    str = f14069a;
                    str2 = "https://oa-book-pic.webnovel.com/";
                    str5 = "https://oa-user-pic.webnovel.com/";
                } else if (parseInt == 1) {
                    str3 = "https://idruid.webnovel.com/";
                    str = "https://app.webnovel.com";
                    str2 = "https://book-pic.webnovel.com/";
                    str5 = "https://user-pic.webnovel.com/";
                } else if (parseInt == 2) {
                    str3 = "https://preidruid.webnovel.com/";
                    str = "https://preapp.webnovel.com";
                    str2 = "https://book-pic.webnovel.com/";
                    str5 = "https://user-pic.webnovel.com/";
                } else if (parseInt == 3) {
                    str3 = "https://devidruid.webnovel.com/";
                    str = "https://devapp.webnovel.com";
                    str2 = "https://oa-book-pic.webnovel.com/";
                    str5 = "https://oa-user-pic.webnovel.com/";
                }
                int parseInt2 = Integer.parseInt(QDConfig.getInstance().GetSetting("UserDebugLoginHost", "-1"));
                if (parseInt2 == 0) {
                    str4 = f14071c;
                } else if (parseInt2 == 1) {
                    str4 = "https://ptlogin.webnovel.com/";
                } else if (parseInt2 == 2) {
                    str4 = "https://preptlogin.webnovel.com/";
                } else if (parseInt2 == 3) {
                    str4 = "https://devptlogin.webnovel.com/";
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("/views/page/invite/index.html?pkgId=%1$s");
            m18925a(61008, sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append("/views/page/inbox/message.html?pkgId=%1$s");
            m18925a(61011, sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append("/views/page/inbox/notification.html?pkgId=%1$s");
            m18925a(61012, sb3.toString());
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str);
            sb4.append("/views/page/help/terms_of_service.html?pkgId=%1$s");
            m18925a(61013, sb4.toString());
            StringBuilder sb5 = new StringBuilder();
            sb5.append(str);
            sb5.append("/views/page/help/privacy_policy.html?pkgId=%1$s");
            m18925a(61014, sb5.toString());
            StringBuilder sb6 = new StringBuilder();
            sb6.append(str);
            sb6.append("/views/page/badges/index.html?pkgid=1");
            m18925a(61022, sb6.toString());
            StringBuilder sb7 = new StringBuilder();
            sb7.append(str);
            sb7.append("/views/page/giftcard/index.html");
            m18925a(61100, sb7.toString());
            StringBuilder sb8 = new StringBuilder();
            sb8.append(str);
            sb8.append("/views/page/helpcenter/index.html?pkgId=1");
            m18925a(61111, sb8.toString());
            StringBuilder sb9 = new StringBuilder();
            sb9.append(str);
            sb9.append("/views/page/helpcenter/detail.html?pkgid=1&id=11");
            m18925a(61121, sb9.toString());
            StringBuilder sb10 = new StringBuilder();
            sb10.append(str4);
            sb10.append("sdk/checkstatus");
            m18925a(61076, sb10.toString());
            StringBuilder sb11 = new StringBuilder();
            sb11.append(str4);
            sb11.append("sdk/google");
            m18925a(61077, sb11.toString());
            StringBuilder sb12 = new StringBuilder();
            sb12.append(str4);
            sb12.append("sdk/twitter");
            m18925a(61078, sb12.toString());
            StringBuilder sb13 = new StringBuilder();
            sb13.append(str4);
            sb13.append("sdk/facebook");
            m18925a(61079, sb13.toString());
            StringBuilder sb14 = new StringBuilder();
            sb14.append(str4);
            sb14.append("sdk/emaillogin");
            m18925a(70037, sb14.toString());
            StringBuilder sb15 = new StringBuilder();
            sb15.append(str4);
            sb15.append("sdk/doregister");
            m18925a(70034, sb15.toString());
            StringBuilder sb16 = new StringBuilder();
            sb16.append(str4);
            sb16.append("sdk/resendregemail");
            m18925a(70035, sb16.toString());
            StringBuilder sb17 = new StringBuilder();
            sb17.append(str4);
            sb17.append("sdk/confirm");
            m18925a(70036, sb17.toString());
            StringBuilder sb18 = new StringBuilder();
            sb18.append(str4);
            sb18.append("sdk/resetPwsMail");
            m18925a(70038, sb18.toString());
            StringBuilder sb19 = new StringBuilder();
            sb19.append(str4);
            sb19.append("sdk/sendtrustemail");
            m18925a(70039, sb19.toString());
            StringBuilder sb20 = new StringBuilder();
            sb20.append(str4);
            sb20.append("sdk/resendtrustemail");
            m18925a(70040, sb20.toString());
            StringBuilder sb21 = new StringBuilder();
            sb21.append(str4);
            sb21.append("sdk/checktrust");
            m18925a(70041, sb21.toString());
            StringBuilder sb22 = new StringBuilder();
            sb22.append(str7);
            sb22.append(NetworkManager.CMD_ORDER);
            m18925a(70031, sb22.toString());
            StringBuilder sb23 = new StringBuilder();
            sb23.append(str7);
            sb23.append("notify");
            m18925a(70032, sb23.toString());
            StringBuilder sb24 = new StringBuilder();
            sb24.append(str6);
            sb24.append("book/%1$s");
            m18925a(70007, sb24.toString());
            StringBuilder sb25 = new StringBuilder();
            sb25.append(str6);
            sb25.append("comic/%1$s");
            m18925a(700052, sb25.toString());
            StringBuilder sb26 = new StringBuilder();
            sb26.append(str2);
            sb26.append("bookcover/%1$s/%2$s?BookCoverId=%3$s");
            m18925a(61037, sb26.toString());
            StringBuilder sb27 = new StringBuilder();
            sb27.append(str5);
            sb27.append("userheadimg/%1$s/%2$s?coverId=%3$s");
            m18925a(61059, sb27.toString());
            m18925a(61067, (String) "https://webbanner.webnovel.com/rechargeGearImg/%1$d.jpg");
            StringBuilder sb28 = new StringBuilder();
            sb28.append(str3);
            sb28.append("app/api/client/get-android-update");
            m18925a(61023, sb28.toString());
            StringBuilder sb29 = new StringBuilder();
            sb29.append(str3);
            sb29.append("app/api/user/get-msg-nums?msgStatus=%1$d&msgType=%2$d");
            m18925a(61025, sb29.toString());
            StringBuilder sb30 = new StringBuilder();
            sb30.append(str3);
            sb30.append("app/api/user/set-msg-status?msgStatus=%1$d&msgType=%2$d");
            m18925a(61026, sb30.toString());
            StringBuilder sb31 = new StringBuilder();
            sb31.append(str3);
            sb31.append("app/api/user/set-head-image");
            m18925a(61027, sb31.toString());
            StringBuilder sb32 = new StringBuilder();
            sb32.append(str3);
            sb32.append("app/api/reading/add-history-with-type");
            m18925a(61029, sb32.toString());
            StringBuilder sb33 = new StringBuilder();
            sb33.append(str3);
            sb33.append("app/api/reading/del-history?bookId=%1$s");
            m18925a(61030, sb33.toString());
            StringBuilder sb34 = new StringBuilder();
            sb34.append(str3);
            sb34.append("app/api/reading/del-batch-history");
            m18925a(61031, sb34.toString());
            StringBuilder sb35 = new StringBuilder();
            sb35.append(str3);
            sb35.append("app/api/reading/get-history?pageIndex=%1$d&pageSize=%2$d");
            m18925a(61032, sb35.toString());
            StringBuilder sb36 = new StringBuilder();
            sb36.append(str3);
            sb36.append("app/api/reading/batch-add-history");
            m18925a(61033, sb36.toString());
            StringBuilder sb37 = new StringBuilder();
            sb37.append(str3);
            sb37.append("app/api/user/report-fbtoken");
            m18925a(61048, sb37.toString());
            StringBuilder sb38 = new StringBuilder();
            sb38.append(str3);
            sb38.append("app/api/activity/get-book-config");
            m18925a(61051, sb38.toString());
            StringBuilder sb39 = new StringBuilder();
            sb39.append(str3);
            sb39.append("app/api/user/set-user-info");
            m18925a(61058, sb39.toString());
            StringBuilder sb40 = new StringBuilder();
            sb40.append(str3);
            sb40.append("app/api/power/get-power-info?pageIndex=%1$d&pageSize=%2$d&bookId=%3$s");
            m18925a(61060, sb40.toString());
            StringBuilder sb41 = new StringBuilder();
            sb41.append(str3);
            sb41.append("app/api/power/vote");
            m18925a(61061, sb41.toString());
            StringBuilder sb42 = new StringBuilder();
            sb42.append(str3);
            sb42.append("app/api/activity/get-bookpool-advs?types=%1$s&platformids=%2$s&advnum=%3$d");
            m18925a(61071, sb42.toString());
            StringBuilder sb43 = new StringBuilder();
            sb43.append(str3);
            sb43.append("app/api/user/get-book-notification?bookIds=%1$s");
            m18925a(61073, sb43.toString());
            StringBuilder sb44 = new StringBuilder();
            sb44.append(str3);
            sb44.append("app/api/user/set-book-notification?nFlag=%1$d&bookId=%2$s");
            m18925a(61074, sb44.toString());
            StringBuilder sb45 = new StringBuilder();
            sb45.append(str3);
            sb45.append("app/api/reading/report");
            m18925a(61075, sb45.toString());
            StringBuilder sb46 = new StringBuilder();
            sb46.append(str3);
            sb46.append("app/api/client/get-conf");
            m18925a(61080, sb46.toString());
            StringBuilder sb47 = new StringBuilder();
            sb47.append(str3);
            sb47.append("app/api/client/report-device");
            m18925a(61133, sb47.toString());
            StringBuilder sb48 = new StringBuilder();
            sb48.append(str3);
            sb48.append("app/api/chapter-review/add-chapter-rate?bookId=%1$s&score=%2$s&chapterId=%3$s");
            m18925a(61087, sb48.toString());
            StringBuilder sb49 = new StringBuilder();
            sb49.append(str3);
            sb49.append("app/api/reading/add-progress?bookId=%1$s&%2$s");
            m18925a(61088, sb49.toString());
            StringBuilder sb50 = new StringBuilder();
            sb50.append(str3);
            sb50.append("app/api/user/login-validate");
            m18925a(61090, sb50.toString());
            StringBuilder sb51 = new StringBuilder();
            sb51.append(str3);
            sb51.append("app/api/user/get");
            m18925a(61091, sb51.toString());
            StringBuilder sb52 = new StringBuilder();
            sb52.append(str3);
            sb52.append("app/api/book/get-chapter?chapterId=%1$s&bookId=%2$s");
            m18925a(61093, sb52.toString());
            StringBuilder sb53 = new StringBuilder();
            sb53.append(str3);
            sb53.append("app/api/user/add-suggestion");
            m18925a(61094, sb53.toString());
            StringBuilder sb54 = new StringBuilder();
            sb54.append(str3);
            sb54.append("app/api/user/set-user-name");
            m18925a(61095, sb54.toString());
            StringBuilder sb55 = new StringBuilder();
            sb55.append(str3);
            sb55.append("app/api/reading/get-progress?bookId=%1$s&itemType=%2$d");
            m18925a(61097, sb55.toString());
            StringBuilder sb56 = new StringBuilder();
            sb56.append(str3);
            sb56.append("app/api/client/get-marketingInfo?Type=%1$s");
            m18925a(61101, sb56.toString());
            StringBuilder sb57 = new StringBuilder();
            sb57.append(str3);
            sb57.append("app/api/user/first-add-bind-email");
            m18925a(61102, sb57.toString());
            StringBuilder sb58 = new StringBuilder();
            sb58.append(str3);
            sb58.append("app/api/user/modify-mail");
            m18925a(61103, sb58.toString());
            StringBuilder sb59 = new StringBuilder();
            sb59.append(str3);
            sb59.append("app/api/user/send-captcha-o-email");
            m18925a(61104, sb59.toString());
            StringBuilder sb60 = new StringBuilder();
            sb60.append(str3);
            sb60.append("app/api/user/verify-captcha");
            m18925a(61105, sb60.toString());
            StringBuilder sb61 = new StringBuilder();
            sb61.append(str3);
            sb61.append("app/api/user/send-captcha-n-email");
            m18925a(61106, sb61.toString());
            StringBuilder sb62 = new StringBuilder();
            sb62.append(str3);
            sb62.append("app/api/book-case/synchronize");
            m18925a(61108, sb62.toString());
            StringBuilder sb63 = new StringBuilder();
            sb63.append(str3);
            sb63.append("app/api/book-case/report-operation-time");
            m18925a(61109, sb63.toString());
            StringBuilder sb64 = new StringBuilder();
            sb64.append(str3);
            sb64.append("app/api/user/problem-feedback");
            m18925a(61110, sb64.toString());
            StringBuilder sb65 = new StringBuilder();
            sb65.append(str3);
            sb65.append("app/api/user/get-edminfos");
            m18925a(61112, sb65.toString());
            StringBuilder sb66 = new StringBuilder();
            sb66.append(str3);
            sb66.append("app/api/user/set-edminfos");
            m18925a(61113, sb66.toString());
            StringBuilder sb67 = new StringBuilder();
            sb67.append(str3);
            sb67.append("app/api/gift/get-book-gift-status?bookId=%1$s");
            m18925a(61115, sb67.toString());
            StringBuilder sb68 = new StringBuilder();
            sb68.append(str3);
            sb68.append("app/api/gift/get-gift-list?bookId=%1$s");
            m18925a(61116, sb68.toString());
            StringBuilder sb69 = new StringBuilder();
            sb69.append(str3);
            sb69.append("app/api/gift/donate?giftId=%1$s&amount=%2$d&totalPrice=%3$d&chapterId=%4$s&transactionId=%5$s&bookId=%6$s");
            m18925a(61117, sb69.toString());
            StringBuilder sb70 = new StringBuilder();
            sb70.append(str3);
            sb70.append("app/api/gift/get-roll-donate-record?bookId=%1$s");
            m18925a(61118, sb70.toString());
            StringBuilder sb71 = new StringBuilder();
            sb71.append(str3);
            sb71.append("app/api/gift/get-donate-uuid");
            m18925a(61119, sb71.toString());
            StringBuilder sb72 = new StringBuilder();
            sb72.append(str3);
            sb72.append("app/api/comic/get-comic?comicId=%1$s");
            m18925a(61122, sb72.toString());
            StringBuilder sb73 = new StringBuilder();
            sb73.append(str3);
            sb73.append("app/api/comic/get-transition-page?comicId=%1$s");
            m18925a(61131, sb73.toString());
            StringBuilder sb74 = new StringBuilder();
            sb74.append(str3);
            sb74.append("app/api/book/get-book?bookId=%1$s");
            m18925a(61134, sb74.toString());
            StringBuilder sb75 = new StringBuilder();
            sb75.append(str3);
            sb75.append("app/api/book/get-book-extended?bookId=%1$s");
            m18925a(61135, sb75.toString());
            StringBuilder sb76 = new StringBuilder();
            sb76.append(str3);
            sb76.append("app/api/book-review/get-reviews?bookId=%1$s&sortCondition=%2$s&pageIndex=%3$s&pageSize=%4$s");
            m18925a(61136, sb76.toString());
            StringBuilder sb77 = new StringBuilder();
            sb77.append(str3);
            sb77.append("app/api/book/report");
            m18925a(61138, sb77.toString());
            StringBuilder sb78 = new StringBuilder();
            sb78.append(str3);
            sb78.append("app/api/book-review/like");
            m18925a(61137, sb78.toString());
            StringBuilder sb79 = new StringBuilder();
            sb79.append(str3);
            sb79.append("app/api/expect-ticket/vote");
            m18925a(61139, sb79.toString());
            StringBuilder sb80 = new StringBuilder();
            sb80.append(str3);
            sb80.append("app/api/book/get-chapters?bookId=%1$s&maxUpdateTime=%2$s&maxIndex=%3$s&sign=%4$s");
            m18925a(70000, sb80.toString());
            StringBuilder sb81 = new StringBuilder();
            sb81.append(str3);
            sb81.append("app/api/book/get-init-chapters?bookId=%1$s");
            m18925a(70001, sb81.toString());
            StringBuilder sb82 = new StringBuilder();
            sb82.append(str3);
            sb82.append("app/api/book/last-chapter?bookId=%1$s");
            m18925a(70002, sb82.toString());
            StringBuilder sb83 = new StringBuilder();
            sb83.append(str3);
            sb83.append("app/api/book/get-chapter?bookId=%1$s&chapterId=%2$s");
            m18925a(70003, sb83.toString());
            StringBuilder sb84 = new StringBuilder();
            sb84.append(str3);
            sb84.append("app/api/book/unlock-chapter");
            m18925a(70004, sb84.toString());
            StringBuilder sb85 = new StringBuilder();
            sb85.append(str3);
            sb85.append("app/api/book/watch-chapter-ad");
            m18925a(70005, sb85.toString());
            StringBuilder sb86 = new StringBuilder();
            sb86.append(str3);
            sb86.append("app/api/book/get-chapter-extended?bookId=%1$s&chapterId=%2$s");
            m18925a(70006, sb86.toString());
            StringBuilder sb87 = new StringBuilder();
            sb87.append(str3);
            sb87.append("app/api/search/popular");
            m18925a(70008, sb87.toString());
            StringBuilder sb88 = new StringBuilder();
            sb88.append(str3);
            sb88.append("app/api/search/auto-complete?keyword=%1$s");
            m18925a(70009, sb88.toString());
            StringBuilder sb89 = new StringBuilder();
            sb89.append(str3);
            sb89.append("app/api/search/result");
            m18925a(70010, sb89.toString());
            StringBuilder sb90 = new StringBuilder();
            sb90.append(str3);
            sb90.append("app/api/sign/is-sign");
            m18925a(70014, sb90.toString());
            StringBuilder sb91 = new StringBuilder();
            sb91.append(str3);
            sb91.append("app/api/sign/sign");
            m18925a(70015, sb91.toString());
            StringBuilder sb92 = new StringBuilder();
            sb92.append(str3);
            sb92.append("app/api/boon/gain?boonId=%1$s&remark=%2$s");
            m18925a(70016, sb92.toString());
            StringBuilder sb93 = new StringBuilder();
            sb93.append(str3);
            sb93.append("app/api/boon/info?boonId=%1$s");
            m18925a(70017, sb93.toString());
            StringBuilder sb94 = new StringBuilder();
            sb94.append(str3);
            sb94.append("app/api/recharge/get-channel-info");
            m18925a(70018, sb94.toString());
            StringBuilder sb95 = new StringBuilder();
            sb95.append(str3);
            sb95.append("app/api/bullet/get?bookId=%1$s&chapterId=%2$s&pageId=%3$s&pageSize=%4$d&pageIndex=%5$d");
            m18925a(70019, sb95.toString());
            StringBuilder sb96 = new StringBuilder();
            sb96.append(str3);
            sb96.append("app/api/bullet/add");
            m18925a(70020, sb96.toString());
            StringBuilder sb97 = new StringBuilder();
            sb97.append(str3);
            sb97.append("app/api/user/get-preference");
            m18925a(70021, sb97.toString());
            StringBuilder sb98 = new StringBuilder();
            sb98.append(str3);
            sb98.append("app/api/user/set-preference");
            m18925a(70022, sb98.toString());
            StringBuilder sb99 = new StringBuilder();
            sb99.append(str3);
            sb99.append("app/api/book/end-read?bookId=%1$s");
            m18925a(70023, sb99.toString());
            StringBuilder sb100 = new StringBuilder();
            sb100.append(str3);
            sb100.append("app/api/comic/get-chapters?comicId=%1$s&timestamp=%2$s&md5Signature=%3$s");
            m18925a(70024, sb100.toString());
            StringBuilder sb101 = new StringBuilder();
            sb101.append(str3);
            sb101.append("app/api/comic/unlock-chapter");
            m18925a(70026, sb101.toString());
            StringBuilder sb102 = new StringBuilder();
            sb102.append(str3);
            sb102.append("app/api/comic/get-chapter?comicId=%1$s&chapterId=%2$s");
            m18925a(70027, sb102.toString());
            StringBuilder sb103 = new StringBuilder();
            sb103.append(str3);
            sb103.append("app/api/comic/get-chapter-byindex?comicId=%1$s&index=%2$d");
            m18925a(70028, sb103.toString());
            StringBuilder sb104 = new StringBuilder();
            sb104.append(str3);
            sb104.append("app/api/recharge/get-monthpay-detail");
            m18925a(70030, sb104.toString());
            StringBuilder sb105 = new StringBuilder();
            sb105.append(str3);
            sb105.append("app/api/recharge/get-monthpay-info");
            m18925a(70029, sb105.toString());
            StringBuilder sb106 = new StringBuilder();
            sb106.append(str3);
            sb106.append("app/api/marketing/get?position=%1$s");
            m18925a(70033, sb106.toString());
            StringBuilder sb107 = new StringBuilder();
            sb107.append(str3);
            sb107.append("app/api/limit-free/get-infos?bookId=%1$s&bookType=%2$d");
            m18925a(70042, sb107.toString());
            StringBuilder sb108 = new StringBuilder();
            sb108.append(str3);
            sb108.append("app/api/tracking/get-google-deep-link");
            m18925a(70043, sb108.toString());
            StringBuilder sb109 = new StringBuilder();
            sb109.append(str3);
            sb109.append("app/api/reading/readingTime");
            m18925a(70045, sb109.toString());
            StringBuilder sb110 = new StringBuilder();
            sb110.append(str3);
            sb110.append("app/api/message/list?lastId=%1$s");
            m18925a(70046, sb110.toString());
            StringBuilder sb111 = new StringBuilder();
            sb111.append(str3);
            sb111.append("app/api/message/get-notification?lastId=%1$s");
            m18925a(70047, sb111.toString());
            StringBuilder sb112 = new StringBuilder();
            sb112.append(str3);
            sb112.append("app/api/message/get-system-notification?lastId=%1$s");
            m18925a(70048, sb112.toString());
            StringBuilder sb113 = new StringBuilder();
            sb113.append(str3);
            sb113.append("app/api/message/get-status");
            m18925a(70049, sb113.toString());
            StringBuilder sb114 = new StringBuilder();
            sb114.append(str3);
            sb114.append("app/api/message/get-message-status?time=%1$s");
            m18925a(70050, sb114.toString());
            StringBuilder sb115 = new StringBuilder();
            sb115.append(str3);
            sb115.append("app/api/comic/get-comic-detail?comicId=%1$s");
            m18925a(70051, sb115.toString());
            StringBuilder sb116 = new StringBuilder();
            sb116.append(str3);
            sb116.append("app/api/operation/get-home-popup");
            m18925a(70053, sb116.toString());
            StringBuilder sb117 = new StringBuilder();
            sb117.append(str3);
            sb117.append("app/api/message/switch");
            m18925a(70054, sb117.toString());
            StringBuilder sb118 = new StringBuilder();
            sb118.append(str3);
            sb118.append("app/api/paragraph-review/get-review-num?chapterId=%1$s");
            m18925a(70055, sb118.toString());
            StringBuilder sb119 = new StringBuilder();
            sb119.append(str3);
            sb119.append("app/api/privilege/get-list?bookId=%1$s&bookType=0");
            m18925a(70056, sb119.toString());
            StringBuilder sb120 = new StringBuilder();
            sb120.append(str3);
            sb120.append("app/api/privilege/purchase");
            m18925a(70057, sb120.toString());
            StringBuilder sb121 = new StringBuilder();
            sb121.append(str3);
            sb121.append("app/api/privilege/get-user-list?lastTime=%1$s");
            m18925a(70058, sb121.toString());
            StringBuilder sb122 = new StringBuilder();
            sb122.append(str3);
            sb122.append("app/api/paragraph-review/get-review-list?chapterId=%1$s&paragraphId=%2$s&lastTime=%3$s");
            m18925a(70059, sb122.toString());
            StringBuilder sb123 = new StringBuilder();
            sb123.append(str3);
            sb123.append("app/api/paragraph-review/get-review-replys?chapterId=%1$s&paragraphId=%2$s&reviewId=%3$s&lastTime=%4$s");
            m18925a(70060, sb123.toString());
            StringBuilder sb124 = new StringBuilder();
            sb124.append(str3);
            sb124.append("app/api/paragraph-review/add");
            m18925a(70061, sb124.toString());
            StringBuilder sb125 = new StringBuilder();
            sb125.append(str3);
            sb125.append("app/api/paragraph-review/like");
            m18925a(70062, sb125.toString());
            StringBuilder sb126 = new StringBuilder();
            sb126.append(str3);
            sb126.append("app/api/paragraph-review/report");
            m18925a(70063, sb126.toString());
            StringBuilder sb127 = new StringBuilder();
            sb127.append(str3);
            sb127.append("app/api/paragraph-review/delete");
            m18925a(70064, sb127.toString());
            StringBuilder sb128 = new StringBuilder();
            sb128.append(str3);
            sb128.append("app/api/paragraph-review/get-review-detail?chapterId=%1$s&paragraphId=%2$s&replyId=%3$s&lastTime=%4$s");
            m18925a(70065, sb128.toString());
        } catch (Exception e) {
            QDLog.exception(e);
        }
    }

    /* renamed from: a */
    public static String m18902a() {
        if (f14075g.get(61023) != null) {
            return f14075g.get(61023);
        }
        return null;
    }

    /* renamed from: b */
    public static String m18943b() {
        if (f14075g.get(61091) != null) {
            return f14075g.get(61091);
        }
        return null;
    }

    /* renamed from: c */
    public static String m18951c() {
        if (f14075g.get(61027) != null) {
            return f14075g.get(61027);
        }
        return null;
    }

    /* renamed from: d */
    public static String m18957d() {
        QDLog.m9354e("getCloudConfig");
        if (f14075g.get(61080) != null) {
            return f14075g.get(61080);
        }
        return null;
    }

    /* renamed from: a */
    public static String m18912a(long j, long j2) {
        return m18948b(j, "180.jpg", j2);
    }

    /* renamed from: b */
    private static String m18948b(long j, String str, long j2) {
        if (f14075g.get(61037) == null) {
            return null;
        }
        return String.format(f14075g.get(61037), new Object[]{String.valueOf(j), str, String.valueOf(j2)});
    }

    /* renamed from: a */
    public static String m18913a(long j, long j2, float f) {
        float f2;
        if (DeviceUtils.m20033d() > 600) {
            f2 = f * 600.0f;
        } else {
            f2 = f * ((float) DeviceUtils.m20033d());
        }
        String valueOf = String.valueOf((int) f2);
        StringBuilder sb = new StringBuilder();
        sb.append(valueOf);
        sb.append(".jpg");
        String b = m18948b(j, sb.toString(), j2);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("书籍详情封面URL : ");
        sb2.append(b);
        QDLog.m9353d("Qidian", sb2.toString());
        return b;
    }

    /* renamed from: e */
    public static String m18962e() {
        if (f14075g.get(61090) != null) {
            return f14075g.get(61090);
        }
        return null;
    }

    /* renamed from: a */
    public static String m18914a(long j, long j2, int i, String str) {
        if (f14075g.get(70000) == null) {
            return null;
        }
        return String.format(f14075g.get(70000), new Object[]{String.valueOf(j), String.valueOf(j2), String.valueOf(i), str});
    }

    /* renamed from: f */
    public static String m18966f() {
        if (f14075g.get(61133) != null) {
            return f14075g.get(61133);
        }
        return null;
    }

    /* renamed from: g */
    public static String m18970g() {
        if (f14075g.get(61088) != null) {
            return f14075g.get(61088);
        }
        return null;
    }

    /* renamed from: a */
    public static String m18907a(long j, int i) {
        if (f14075g.get(61097) == null) {
            return null;
        }
        return String.format(f14075g.get(61097), new Object[]{String.valueOf(j), Integer.valueOf(i)});
    }

    /* renamed from: h */
    public static String m18973h() {
        return m18944b(f14070b);
    }

    /* renamed from: b */
    public static String m18944b(int i) {
        if (f14075g.get(61013) == null) {
            return null;
        }
        return String.format(f14075g.get(61013), new Object[]{String.valueOf(i)});
    }

    /* renamed from: i */
    public static String m18976i() {
        return m18952c(f14070b);
    }

    /* renamed from: c */
    public static String m18952c(int i) {
        if (f14075g.get(61014) == null) {
            return null;
        }
        return String.format(f14075g.get(61014), new Object[]{String.valueOf(i)});
    }

    /* renamed from: j */
    public static String m18978j() {
        return m18967f(f14070b);
    }

    /* renamed from: f */
    private static String m18967f(int i) {
        if (f14075g.get(61008) == null) {
            return null;
        }
        return String.format(f14075g.get(61008), new Object[]{String.valueOf(i)});
    }

    /* renamed from: a */
    public static String m18918a(long j, String str, long j2) {
        if (f14075g.get(61087) == null) {
            return "";
        }
        return String.format(f14075g.get(61087), new Object[]{String.valueOf(j), String.valueOf(str), String.valueOf(j2)});
    }

    /* renamed from: b */
    public static String m18947b(long j, long j2) {
        if (f14075g.get(61093) == null) {
            return "";
        }
        return String.format(f14075g.get(61093), new Object[]{String.valueOf(j), String.valueOf(j2)});
    }

    /* renamed from: k */
    public static String m18980k() {
        return f14075g.get(61094) != null ? f14075g.get(61094) : "";
    }

    /* renamed from: l */
    public static String m18982l() {
        return f14075g.get(61095) != null ? f14075g.get(61095) : "";
    }

    /* renamed from: d */
    public static String m18958d(int i) {
        return f14075g.get(i) != null ? f14075g.get(i) : "";
    }

    /* renamed from: m */
    public static String m18984m() {
        if (f14075g.get(61029) != null) {
            return f14075g.get(61029);
        }
        return null;
    }

    /* renamed from: a */
    public static String m18906a(long j) {
        if (f14075g.get(61030) == null) {
            return null;
        }
        return String.format(f14075g.get(61030), new Object[]{String.valueOf(j)});
    }

    /* renamed from: n */
    public static String m18986n() {
        if (f14075g.get(61031) != null) {
            return f14075g.get(61031);
        }
        return null;
    }

    /* renamed from: o */
    public static String m18988o() {
        if (f14075g.get(61033) != null) {
            return f14075g.get(61033);
        }
        return null;
    }

    /* renamed from: a */
    public static String m18903a(int i, int i2) {
        if (f14075g.get(61032) == null) {
            return null;
        }
        return String.format(f14075g.get(61032), new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
    }

    /* renamed from: p */
    public static String m18990p() {
        if (f14075g.get(70037) != null) {
            return f14075g.get(70037);
        }
        return null;
    }

    /* renamed from: q */
    public static String m18992q() {
        if (f14075g.get(61048) != null) {
            return f14075g.get(61048);
        }
        return null;
    }

    /* renamed from: r */
    public static String m18994r() {
        if (f14075g.get(61051) != null) {
            return f14075g.get(61051);
        }
        return null;
    }

    /* renamed from: s */
    public static String m18995s() {
        if (f14075g.get(61058) != null) {
            return f14075g.get(61058);
        }
        return null;
    }

    /* renamed from: a */
    public static String m18910a(long j, int i, long j2) {
        return m18911a(j, i, (String) "1.jpg", j2);
    }

    /* renamed from: a */
    public static String m18911a(long j, int i, String str, long j2) {
        if (f14075g.get(61059) == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(j));
        sb.append("-");
        sb.append(String.valueOf(i));
        return String.format(f14075g.get(61059), new Object[]{sb.toString(), str, String.valueOf(j2)});
    }

    /* renamed from: t */
    public static String m18996t() {
        if (f14075g.get(61061) != null) {
            return String.format(f14075g.get(61061), new Object[0]);
        }
        return null;
    }

    /* renamed from: a */
    public static String m18904a(int i, int i2, long j) {
        if (f14075g.get(61060) == null) {
            return null;
        }
        return String.format(f14075g.get(61060), new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(j)});
    }

    /* renamed from: a */
    public static String m18917a(long j, long j2, boolean z) {
        if (f14075g.get(70003) == null) {
            return null;
        }
        return String.format(f14075g.get(70003), new Object[]{String.valueOf(j), String.valueOf(j2)});
    }

    /* renamed from: e */
    public static String m18963e(int i) {
        if (f14075g.get(61067) == null) {
            return null;
        }
        return String.format(f14075g.get(61067), new Object[]{Integer.valueOf(i)});
    }

    /* renamed from: a */
    public static String m18921a(String str, String str2, int i) {
        if (f14075g.get(61071) == null) {
            return null;
        }
        return String.format(f14075g.get(61071), new Object[]{str, str2, Integer.valueOf(i)});
    }

    @Deprecated
    /* renamed from: u */
    public static String m18997u() {
        if (f14075g.get(61022) != null) {
            return f14075g.get(61022);
        }
        return null;
    }

    /* renamed from: a */
    public static String m18919a(String str) {
        if (f14075g.get(61073) == null) {
            return null;
        }
        return String.format(f14075g.get(61073), new Object[]{str});
    }

    /* renamed from: a */
    public static String m18905a(int i, long j) {
        if (f14075g.get(61074) == null) {
            return null;
        }
        return String.format(f14075g.get(61074), new Object[]{Integer.valueOf(i), String.valueOf(j)});
    }

    /* renamed from: v */
    public static String m18998v() {
        if (f14075g.get(61075) != null) {
            return f14075g.get(61075);
        }
        return null;
    }

    /* renamed from: w */
    public static String m18999w() {
        if (f14075g.get(61076) != null) {
            return f14075g.get(61076);
        }
        return null;
    }

    /* renamed from: x */
    public static String m19000x() {
        if (f14075g.get(61100) != null) {
            return f14075g.get(61100);
        }
        return null;
    }

    /* renamed from: b */
    public static String m18949b(String str) {
        if (f14075g.get(61101) == null) {
            return null;
        }
        return String.format(f14075g.get(61101), new Object[]{str});
    }

    /* renamed from: y */
    public static String m19001y() {
        if (f14075g.get(61102) != null) {
            return f14075g.get(61102);
        }
        return null;
    }

    /* renamed from: z */
    public static String m19002z() {
        if (f14075g.get(61104) != null) {
            return f14075g.get(61104);
        }
        return null;
    }

    /* renamed from: A */
    public static String m18876A() {
        if (f14075g.get(61105) != null) {
            return f14075g.get(61105);
        }
        return null;
    }

    /* renamed from: B */
    public static String m18877B() {
        if (f14075g.get(61106) != null) {
            return f14075g.get(61106);
        }
        return null;
    }

    /* renamed from: C */
    public static String m18878C() {
        if (f14075g.get(61103) != null) {
            return f14075g.get(61103);
        }
        return null;
    }

    /* renamed from: D */
    public static String m18879D() {
        if (f14075g.get(61108) != null) {
            return f14075g.get(61108);
        }
        return null;
    }

    /* renamed from: E */
    public static String m18880E() {
        if (f14075g.get(61109) != null) {
            return f14075g.get(61109);
        }
        return null;
    }

    /* renamed from: F */
    public static String m18881F() {
        if (f14075g.get(61110) != null) {
            return f14075g.get(61110);
        }
        return null;
    }

    /* renamed from: G */
    public static String m18882G() {
        if (f14075g.get(61111) != null) {
            return f14075g.get(61111);
        }
        return null;
    }

    /* renamed from: H */
    public static String m18883H() {
        if (f14075g.get(61113) != null) {
            return f14075g.get(61113);
        }
        return null;
    }

    /* renamed from: b */
    public static String m18945b(long j) {
        if (f14075g.get(61115) == null) {
            return null;
        }
        return String.format(f14075g.get(61115), new Object[]{String.valueOf(j)});
    }

    /* renamed from: c */
    public static String m18953c(long j) {
        if (f14075g.get(61116) == null) {
            return null;
        }
        return String.format(f14075g.get(61116), new Object[]{String.valueOf(j)});
    }

    /* renamed from: a */
    public static String m18909a(long j, int i, int i2, long j2, String str, long j3) {
        if (f14075g.get(61117) == null) {
            return null;
        }
        return String.format(f14075g.get(61117), new Object[]{String.valueOf(j), Integer.valueOf(i), Integer.valueOf(i2), String.valueOf(j2), str, String.valueOf(j3)});
    }

    /* renamed from: d */
    public static String m18959d(long j) {
        if (f14075g.get(61118) == null) {
            return null;
        }
        return String.format(f14075g.get(61118), new Object[]{String.valueOf(j)});
    }

    /* renamed from: I */
    public static String m18884I() {
        if (f14075g.get(61119) != null) {
            return f14075g.get(61119);
        }
        return null;
    }

    /* renamed from: J */
    public static String m18885J() {
        if (f14075g.get(61121) != null) {
            return f14075g.get(61121);
        }
        return null;
    }

    /* renamed from: e */
    public static String m18964e(long j) {
        if (f14075g.get(61122) == null) {
            return null;
        }
        return String.format(f14075g.get(61122), new Object[]{String.valueOf(j)});
    }

    /* renamed from: a */
    public static String m18916a(long j, long j2, String str) {
        if (f14075g.get(70024) == null) {
            return null;
        }
        return String.format(f14075g.get(70024), new Object[]{String.valueOf(j), String.valueOf(j2), str});
    }

    /* renamed from: K */
    public static String m18886K() {
        if (f14075g.get(70026) != null) {
            return f14075g.get(70026);
        }
        return null;
    }

    /* renamed from: c */
    public static String m18955c(long j, long j2) {
        if (f14075g.get(70027) == null) {
            return null;
        }
        return String.format(f14075g.get(70027), new Object[]{String.valueOf(j), String.valueOf(j2)});
    }

    /* renamed from: b */
    public static String m18946b(long j, int i) {
        if (f14075g.get(70028) == null) {
            return null;
        }
        return String.format(f14075g.get(70028), new Object[]{String.valueOf(j), Integer.valueOf(i)});
    }

    /* renamed from: f */
    public static String m18968f(long j) {
        if (f14075g.get(61131) == null) {
            return null;
        }
        return String.format(f14075g.get(61131), new Object[]{String.valueOf(j)});
    }

    /* renamed from: g */
    public static String m18971g(long j) {
        if (f14075g.get(61134) == null) {
            return null;
        }
        return String.format(f14075g.get(61134), new Object[]{String.valueOf(j)});
    }

    /* renamed from: h */
    public static String m18974h(long j) {
        if (f14075g.get(70051) == null) {
            return null;
        }
        return String.format(f14075g.get(70051), new Object[]{String.valueOf(j)});
    }

    /* renamed from: i */
    public static String m18977i(long j) {
        if (f14075g.get(61135) == null) {
            return null;
        }
        return String.format(f14075g.get(61135), new Object[]{String.valueOf(j)});
    }

    /* renamed from: a */
    public static String m18908a(long j, int i, int i2, int i3) {
        if (f14075g.get(61136) == null) {
            return null;
        }
        return String.format(f14075g.get(61136), new Object[]{String.valueOf(j), String.valueOf(i), String.valueOf(i2), String.valueOf(i3)});
    }

    /* renamed from: L */
    public static String m18887L() {
        if (f14075g.get(61138) != null) {
            return String.format(f14075g.get(61138), new Object[0]);
        }
        return null;
    }

    /* renamed from: M */
    public static String m18888M() {
        if (f14075g.get(61137) != null) {
            return String.format(f14075g.get(61137), new Object[0]);
        }
        return null;
    }

    /* renamed from: N */
    public static String m18889N() {
        if (f14075g.get(61139) != null) {
            return String.format(f14075g.get(61139), new Object[0]);
        }
        return null;
    }

    /* renamed from: j */
    public static String m18979j(long j) {
        if (f14075g.get(70002) == null) {
            return null;
        }
        return String.format(f14075g.get(70002), new Object[]{String.valueOf(j)});
    }

    /* renamed from: O */
    public static String m18890O() {
        if (f14075g.get(70004) != null) {
            return f14075g.get(70004);
        }
        return null;
    }

    /* renamed from: P */
    public static String m18891P() {
        if (f14075g.get(70005) != null) {
            return f14075g.get(70005);
        }
        return null;
    }

    /* renamed from: d */
    public static String m18960d(long j, long j2) {
        if (f14075g.get(70006) == null) {
            return null;
        }
        return String.format(f14075g.get(70006), new Object[]{String.valueOf(j), String.valueOf(j2)});
    }

    /* renamed from: k */
    public static String m18981k(long j) {
        if (f14075g.get(70007) == null) {
            return null;
        }
        return String.format(f14075g.get(70007), new Object[]{String.valueOf(j)});
    }

    /* renamed from: Q */
    public static String m18892Q() {
        if (f14075g.get(70008) != null) {
            return f14075g.get(70008);
        }
        return null;
    }

    /* renamed from: c */
    public static String m18956c(String str) {
        if (f14075g.get(70009) == null) {
            return null;
        }
        return String.format(f14075g.get(70009), new Object[]{str});
    }

    /* renamed from: R */
    public static String m18893R() {
        if (f14075g.get(70010) != null) {
            return f14075g.get(70010);
        }
        return null;
    }

    /* renamed from: S */
    public static String m18894S() {
        if (f14075g.get(70015) != null) {
            return f14075g.get(70015);
        }
        return null;
    }

    /* renamed from: a */
    public static String m18920a(String str, String str2) {
        if (f14075g.get(70016) == null) {
            return null;
        }
        return String.format(f14075g.get(70016), new Object[]{str, str2});
    }

    /* renamed from: d */
    public static String m18961d(String str) {
        if (f14075g.get(70017) == null) {
            return null;
        }
        return String.format(f14075g.get(70017), new Object[]{str});
    }

    /* renamed from: T */
    public static String m18895T() {
        if (f14075g.get(70018) != null) {
            return f14075g.get(70018);
        }
        return null;
    }

    /* renamed from: a */
    public static String m18915a(long j, long j2, long j3, int i, int i2) {
        if (f14075g.get(70019) == null) {
            return null;
        }
        return String.format(f14075g.get(70019), new Object[]{String.valueOf(j), String.valueOf(j2), String.valueOf(j3), Integer.valueOf(i), Integer.valueOf(i2)});
    }

    /* renamed from: U */
    public static String m18896U() {
        if (f14075g.get(70020) != null) {
            return f14075g.get(70020);
        }
        return null;
    }

    /* renamed from: V */
    public static String m18897V() {
        if (f14075g.get(70021) != null) {
            return f14075g.get(70021);
        }
        return null;
    }

    /* renamed from: W */
    public static String m18898W() {
        if (f14075g.get(70022) != null) {
            return f14075g.get(70022);
        }
        return null;
    }

    /* renamed from: l */
    public static String m18983l(long j) {
        if (f14075g.get(70023) == null) {
            return null;
        }
        return String.format(f14075g.get(70023), new Object[]{String.valueOf(j)});
    }

    /* renamed from: X */
    public static String m18899X() {
        if (f14075g.get(70030) != null) {
            return f14075g.get(70030);
        }
        return null;
    }

    /* renamed from: Y */
    public static String m18900Y() {
        if (f14075g.get(70031) != null) {
            return f14075g.get(70031);
        }
        return null;
    }

    /* renamed from: Z */
    public static String m18901Z() {
        if (f14075g.get(70032) != null) {
            return f14075g.get(70032);
        }
        return null;
    }

    /* renamed from: e */
    public static String m18965e(String str) {
        if (f14075g.get(70033) == null) {
            return null;
        }
        return String.format(f14075g.get(70033), new Object[]{str});
    }

    /* renamed from: aa */
    public static String m18926aa() {
        if (f14075g.get(70034) != null) {
            return f14075g.get(70034);
        }
        return null;
    }

    /* renamed from: ab */
    public static String m18927ab() {
        if (f14075g.get(70035) != null) {
            return f14075g.get(70035);
        }
        return null;
    }

    /* renamed from: ac */
    public static String m18928ac() {
        if (f14075g.get(70036) != null) {
            return f14075g.get(70036);
        }
        return null;
    }

    /* renamed from: ad */
    public static String m18929ad() {
        if (f14075g.get(70038) != null) {
            return f14075g.get(70038);
        }
        return null;
    }

    /* renamed from: ae */
    public static String m18930ae() {
        if (f14075g.get(70039) != null) {
            return f14075g.get(70039);
        }
        return null;
    }

    /* renamed from: af */
    public static String m18931af() {
        if (f14075g.get(70040) != null) {
            return f14075g.get(70040);
        }
        return null;
    }

    /* renamed from: ag */
    public static String m18932ag() {
        if (f14075g.get(70041) != null) {
            return f14075g.get(70041);
        }
        return null;
    }

    /* renamed from: c */
    public static String m18954c(long j, int i) {
        if (f14075g.get(70042) == null) {
            return null;
        }
        return String.format(f14075g.get(70042), new Object[]{String.valueOf(j), Integer.valueOf(i)});
    }

    /* renamed from: ah */
    public static String m18933ah() {
        if (f14075g.get(70043) != null) {
            return String.format(f14075g.get(70043), new Object[0]);
        }
        return null;
    }

    /* renamed from: ai */
    public static String m18934ai() {
        if (f14075g.get(70045) != null) {
            return String.valueOf(f14075g.get(70045));
        }
        return null;
    }

    /* renamed from: f */
    public static String m18969f(String str) {
        if (f14075g.get(70046) == null) {
            return null;
        }
        return String.format(f14075g.get(70046), new Object[]{str});
    }

    /* renamed from: g */
    public static String m18972g(String str) {
        if (f14075g.get(70047) == null) {
            return null;
        }
        return String.format(f14075g.get(70047), new Object[]{str});
    }

    /* renamed from: h */
    public static String m18975h(String str) {
        if (f14075g.get(70048) == null) {
            return null;
        }
        return String.format(f14075g.get(70048), new Object[]{str});
    }

    /* renamed from: aj */
    public static String m18935aj() {
        if (f14075g.get(70049) != null) {
            return String.valueOf(f14075g.get(70049));
        }
        return null;
    }

    /* renamed from: m */
    public static String m18985m(long j) {
        if (f14075g.get(70050) == null) {
            return null;
        }
        return String.format(f14075g.get(70050), new Object[]{String.valueOf(j)});
    }

    /* renamed from: n */
    public static String m18987n(long j) {
        if (f14075g.get(700052) == null) {
            return null;
        }
        return String.format(f14075g.get(700052), new Object[]{String.valueOf(j)});
    }

    /* renamed from: ak */
    public static String m18936ak() {
        if (f14075g.get(70053) != null) {
            return String.valueOf(f14075g.get(70053));
        }
        return null;
    }

    /* renamed from: al */
    public static String m18937al() {
        if (f14075g.get(70054) != null) {
            return String.valueOf(f14075g.get(70054));
        }
        return null;
    }

    /* renamed from: o */
    public static String m18989o(long j) {
        if (f14075g.get(70055) == null) {
            return null;
        }
        return String.format(f14075g.get(70055), new Object[]{String.valueOf(j)});
    }

    /* renamed from: p */
    public static String m18991p(long j) {
        if (f14075g.get(70056) == null) {
            return null;
        }
        return String.format(f14075g.get(70056), new Object[]{String.valueOf(j)});
    }

    /* renamed from: am */
    public static String m18938am() {
        if (f14075g.get(70057) != null) {
            return String.format(f14075g.get(70057), new Object[0]);
        }
        return null;
    }

    /* renamed from: q */
    public static String m18993q(long j) {
        if (f14075g.get(70058) == null) {
            return null;
        }
        return String.format(f14075g.get(70058), new Object[]{String.valueOf(j)});
    }

    /* renamed from: a */
    public static String m18922a(String str, String str2, String str3) {
        if (f14075g.get(70059) == null) {
            return null;
        }
        return String.format(f14075g.get(70059), new Object[]{str, str2, str3});
    }

    /* renamed from: a */
    public static String m18923a(String str, String str2, String str3, String str4) {
        if (f14075g.get(70060) == null) {
            return null;
        }
        return String.format(f14075g.get(70060), new Object[]{str, str2, str3, str4});
    }

    /* renamed from: an */
    public static String m18939an() {
        if (f14075g.get(70061) != null) {
            return String.valueOf(f14075g.get(70061));
        }
        return null;
    }

    /* renamed from: ao */
    public static String m18940ao() {
        if (f14075g.get(70062) != null) {
            return String.valueOf(f14075g.get(70062));
        }
        return null;
    }

    /* renamed from: ap */
    public static String m18941ap() {
        if (f14075g.get(70063) != null) {
            return String.valueOf(f14075g.get(70063));
        }
        return null;
    }

    /* renamed from: aq */
    public static String m18942aq() {
        if (f14075g.get(70064) != null) {
            return String.valueOf(f14075g.get(70064));
        }
        return null;
    }

    /* renamed from: b */
    public static String m18950b(String str, String str2, String str3, String str4) {
        if (f14075g.get(70065) == null) {
            return null;
        }
        return String.format(f14075g.get(70065), new Object[]{str, str2, str3, str4});
    }
}