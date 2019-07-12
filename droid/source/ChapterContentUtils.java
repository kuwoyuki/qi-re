package com.qidian.QDReader.components.p168c;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.qidian.QDReader.components.book.QDParagraphCommentManager;
import com.qidian.QDReader.components.entity.ChapterContentItem;
import com.qidian.QDReader.components.entity.ParagraphCommentItem;
import com.qidian.QDReader.components.user.QDUserManager;
import com.qidian.QDReader.core.config.AppInfo;
import com.qidian.QDReader.core.config.QDConfig;
import com.qidian.QDReader.core.config.QDPath;
import com.qidian.QDReader.core.log.QDLog;
import com.qidian.QDReader.core.p114e.QDFileUtil;
import com.qidian.QDReader.core.p115i.Base64;
import com.qidian.QDReader.core.p115i.DES;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import p000a.C0000b;

/* renamed from: com.qidian.QDReader.components.c.a */
public class ChapterContentUtils {
    /* renamed from: a */
    public static long m19409a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return 0;
        }
        try {
            byte[] a = DES.m20015a(bArr, (String) "0821CAAD409B8402");
            if (a == null) {
                return 0;
            }
            String str = new String(a, "UTF-8");
            if (str.length() == 0) {
                return 0;
            }
            String[] split = str.split(",");
            return Long.parseLong(split[split.length - 1]);
        } catch (Exception e) {
            QDLog.exception(e);
            return 0;
        }
    }

    /* renamed from: a */
    public static byte[][] m19417a(File file) {
        byte[] bArr;
        byte[] bArr2;
        byte[] bArr3;
        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            if (fileInputStream.available() > 0) {
                int a = QDFileUtil.m9323a((InputStream) fileInputStream);
                if (((long) a) > Math.min(file.length(), 500000)) {
                    file.delete();
                    return null;
                }
                bArr3 = new byte[a];
                fileInputStream.read(bArr3, 0, a);
                int a2 = QDFileUtil.m9323a((InputStream) fileInputStream);
                bArr2 = new byte[a2];
                fileInputStream.read(bArr2, 0, a2);
                int a3 = QDFileUtil.m9323a((InputStream) fileInputStream);
                if (a3 <= 0 || a3 >= 3072) {
                    bArr = null;
                } else {
                    bArr = new byte[a3];
                    fileInputStream.read(bArr, 0, a3);
                }
            } else {
                bArr3 = null;
                bArr2 = null;
                bArr = null;
            }
            fileInputStream.close();
            return new byte[][]{bArr3, bArr2, bArr};
        } catch (Exception e) {
            QDLog.exception(e);
            if (file != null && file.exists()) {
                file.delete();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m19415a(String str, String str2) {
        File file = new File(str);
        if (file.exists()) {
            try {
                file.delete();
            } catch (Exception e) {
                QDLog.exception(e);
            }
        }
        return QDFileUtil.m9337b(file, str2);
    }

    /* renamed from: a */
    public static boolean m19414a(long j, @NonNull ChapterContentItem chapterContentItem) {
        long id = chapterContentItem.getId();
        if (j <= 0 || id <= 0 || chapterContentItem == null || chapterContentItem.getUnlocked() != 1) {
            return false;
        }
        File file = new File(m19410a(j, id));
        if (file.exists()) {
            file.delete();
        }
        if (TextUtils.isEmpty(chapterContentItem.getContentItems())) {
            return false;
        }
        return m19415a(m19411a(j, id, (String) ".qd"), new Gson().toJson((Object) chapterContentItem));
    }

    /* renamed from: a */
    public static String m19411a(long j, long j2, @NonNull String str) {
        if (j2 <= 0) {
            return "";
        }
        File file = new File(QDPath.m9308a(j, QDUserManager.getInstance().mo36470a()));
        if (!file.exists()) {
            file.mkdirs();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(file.getAbsolutePath());
        sb.append(File.separator);
        sb.append(j2);
        sb.append(str);
        return sb.toString();
    }

    /* renamed from: a */
    public static String m19410a(long j, long j2) {
        if (j2 <= 0) {
            return "";
        }
        String a = m19411a(j, j2, (String) ".qd");
        if (new File(a).exists()) {
            return a;
        }
        String a2 = m19411a(j, j2, (String) ".cc");
        return new File(a2).exists() ? a2 : "";
    }

    /* renamed from: a */
    public static byte[] m19416a(byte[] bArr, long j, long j2) {
        try {
            byte[] b = C0000b.m0b(j, j2, bArr, QDUserManager.getInstance().mo36470a(), AppInfo.m9267a().mo21343p());
            if (b != null) {
                return b;
            }
        } catch (Exception e) {
            QDLog.exception(e);
        }
        try {
            ArrayList<String> a = QDConfig.getInstance().mo36512a();
            if (a != null && a.size() > 0) {
                for (int i = 0; i < a.size(); i++) {
                    String str = a.get(i);
                    if (!TextUtils.isEmpty(str)) {
                        byte[] b2 = C0000b.m0b(j, j2, bArr, QDUserManager.getInstance().mo36470a(), str);
                        if (b2 != null) {
                            return b2;
                        }
                    }
                }
            }
        } catch (Exception e2) {
            QDLog.exception(e2);
        }
        return null;
    }

    /* renamed from: b */
    public static String m19418b(byte[] bArr) {
        try {
            return new String(bArr, "UTF-8");
        } catch (Exception e) {
            QDLog.exception(e);
            return null;
        }
    }

    /* renamed from: a */
    public static void m19413a(@NonNull String str) {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
                StringBuilder sb = new StringBuilder();
                sb.append("delete file = ");
                sb.append(file.getName());
                QDLog.m9353d("ChapterContentUtils", sb.toString());
            }
        }
    }

    /* renamed from: a */
    public static void m19412a(long j, long j2, boolean z) {
        if (j > 0 && j2 > 0) {
            if (!z) {
                try {
                    m19413a(m19411a(j, j2, (String) ".ad"));
                    m19413a(m19411a(j, j2, (String) ".pre"));
                } catch (Exception e) {
                    QDLog.exception(e);
                    return;
                }
            }
            m19413a(m19411a(j, j2, (String) ".qd"));
            m19413a(m19411a(j, j2, (String) ".cc"));
        }
    }

    /* renamed from: b */
    public static void m19419b(long j, ChapterContentItem chapterContentItem) {
        if (j > 0 && chapterContentItem != null) {
            try {
                byte[] a = m19416a(Base64.m19983a(chapterContentItem.getContentItems()), j, chapterContentItem.getId());
                String str = null;
                if (a != null) {
                    str = m19418b(a);
                }
                JSONArray optJSONArray = new JSONObject(str).optJSONArray("ContentItems");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        ParagraphCommentItem paragraphCommentItem = new ParagraphCommentItem();
                        paragraphCommentItem.setBookId(j);
                        paragraphCommentItem.setChapterId(chapterContentItem.getId());
                        paragraphCommentItem.setParagraphId(optJSONObject.optString("ParagraphId"));
                        paragraphCommentItem.setReviewAmount(optJSONObject.optInt("ReviewAmount"));
                        arrayList.add(paragraphCommentItem);
                    }
                    if (arrayList.size() > 0) {
                        QDParagraphCommentManager.m19284a(j, chapterContentItem.getId()).mo34901a((List<ParagraphCommentItem>) arrayList);
                    }
                }
            } catch (Exception e) {
                QDLog.exception(e);
            }
        }
    }
}
