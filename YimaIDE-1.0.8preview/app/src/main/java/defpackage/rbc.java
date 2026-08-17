package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.reandroid.arsc.value.CompoundEntry;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class rbc {
    public static final rbc a = new rbc();
    public static final Regex b = new Regex("\\bversionName\\b\\s*=?\\s*[\"']([^\"']+)[\"']");
    public static final Regex c = new Regex("\\bversionCode\\b\\s*=?\\s*(\\d+)");
    public static final Regex d = new Regex("<string\\s+name=\"app_name\">(.*?)</string>", RegexOption.DOT_MATCHES_ALL);
    public static final int e = 8;

    public static final class a {
        public final String a;
        public final String b;
        public final int c;
        public final boolean d;

        public a(String str, String str2, int i, boolean z) {
            str.getClass();
            str2.getClass();
            this.a = str;
            this.b = str2;
            this.c = i;
            this.d = z;
        }

        public final String a() {
            return this.a;
        }

        public final int b() {
            return this.c;
        }

        public final String c() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return Intrinsics.areEqual(this.a, aVar.a) && Intrinsics.areEqual(this.b, aVar.b) && this.c == aVar.c && this.d == aVar.d;
        }

        public int hashCode() {
            return (((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + Integer.hashCode(this.c)) * 31) + Boolean.hashCode(this.d);
        }

        public String toString() {
            return "Meta(appName=" + this.a + ", versionName=" + this.b + ", versionCode=" + this.c + ", hasCustomIcon=" + this.d + ")";
        }
    }

    public final void a(Context context, String str) {
        context.getClass();
        str.getClass();
        FilesKt.deleteRecursively(g(context, str));
    }

    public final a b(File file, String str) {
        String str2;
        List groupValues;
        String str3;
        Integer intOrNull;
        List groupValues2;
        String strJ = j(file);
        if (strJ != null) {
            str = strJ;
        }
        File file2 = new File(file, "build.gradle");
        if (!file2.isFile()) {
            file2 = null;
        }
        int iIntValue = 1;
        String text$default = file2 != null ? FilesKt.readText$default(file2, (Charset) null, 1, (Object) null) : null;
        if (text$default == null) {
            text$default = "";
        }
        MatchResult matchResultFind$default = Regex.find$default(b, text$default, 0, 2, (Object) null);
        if (matchResultFind$default == null || (groupValues2 = matchResultFind$default.getGroupValues()) == null || (str2 = (String) groupValues2.get(1)) == null) {
            str2 = "1.0";
        }
        MatchResult matchResultFind$default2 = Regex.find$default(c, text$default, 0, 2, (Object) null);
        if (matchResultFind$default2 != null && (groupValues = matchResultFind$default2.getGroupValues()) != null && (str3 = (String) groupValues.get(1)) != null && (intOrNull = StringsKt.toIntOrNull(str3)) != null) {
            iIntValue = intOrNull.intValue();
        }
        return new a(str, str2, iIntValue, false);
    }

    public final synchronized void c(Context context, String str) {
        context.getClass();
        str.getClass();
        File fileL = l(context, str);
        if (fileL.isFile()) {
            try {
                Result.Companion companion = Result.Companion;
                p(h(context, str), fileL);
                Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                Result.constructor-impl(ResultKt.createFailure(th));
            }
        }
    }

    public final boolean d(Context context, String str) {
        context.getClass();
        str.getClass();
        File fileH = h(context, str);
        if (!fileH.isDirectory()) {
            return false;
        }
        Iterator it = FilesKt.walkTopDown(fileH).iterator();
        while (it.hasNext()) {
            if (((File) it.next()).isFile()) {
                return true;
            }
        }
        return false;
    }

    public final JSONObject e(File file) {
        Object obj;
        File file2 = new File(file, "meta.json");
        if (!file2.isFile()) {
            return null;
        }
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.constructor-impl(new JSONObject(FilesKt.readText$default(file2, (Charset) null, 1, (Object) null)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        return (JSONObject) (Result.isFailure-impl(obj) ? null : obj);
    }

    public final Bitmap f(Context context, String str) {
        context.getClass();
        str.getClass();
        File fileL = l(context, str);
        if (!fileL.isFile()) {
            return null;
        }
        try {
            return BitmapFactory.decodeFile(fileL.getAbsolutePath());
        } catch (Exception unused) {
            return null;
        }
    }

    public final File g(Context context, String str) {
        context.getClass();
        str.getClass();
        return new File(context.getFilesDir(), "release-meta/" + str);
    }

    public final File h(Context context, String str) {
        context.getClass();
        str.getClass();
        return new File(g(context, str), "res");
    }

    /* JADX WARN: Code duplicated, block: B:11:0x004a  */
    /* JADX WARN: Code duplicated, block: B:21:0x0067  */
    /* JADX WARN: Code duplicated, block: B:28:0x0087  */
    public final ufb.c i(Context context, dfb dfbVar) {
        String strA;
        String strC;
        int iB;
        context.getClass();
        dfbVar.getClass();
        a aVarB = b(new File(context.getFilesDir(), "projects/" + dfbVar.e()), dfbVar.f());
        JSONObject jSONObjectE = e(g(context, dfbVar.e()));
        if (jSONObjectE == null || (strA = jSONObjectE.optString("appName")) == null) {
            strA = aVarB.a();
        } else {
            if (StringsKt.isBlank(strA)) {
                strA = null;
            }
            if (strA == null) {
                strA = aVarB.a();
            }
        }
        String strH = dfbVar.h();
        if (jSONObjectE == null || (strC = jSONObjectE.optString("versionName")) == null) {
            strC = aVarB.c();
        } else {
            if (StringsKt.isBlank(strC)) {
                strC = null;
            }
            if (strC == null) {
                strC = aVarB.c();
            }
        }
        if (jSONObjectE != null) {
            Integer numValueOf = Integer.valueOf(jSONObjectE.optInt("versionCode"));
            Integer num = numValueOf.intValue() >= 1 ? numValueOf : null;
            if (num != null) {
                iB = num.intValue();
            } else {
                iB = aVarB.b();
            }
        } else {
            iB = aVarB.b();
        }
        return new ufb.c(strA, strH, strC, iB);
    }

    public final String j(File file) {
        MatchResult matchResultFind$default;
        List groupValues;
        String str;
        File file2 = new File(file, "res/values/strings.xml");
        if (!file2.isFile()) {
            file2 = null;
        }
        if (file2 == null || (matchResultFind$default = Regex.find$default(d, FilesKt.readText$default(file2, (Charset) null, 1, (Object) null), 0, 2, (Object) null)) == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str = (String) groupValues.get(1)) == null) {
            return null;
        }
        return StringsKt.trim(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(str, SerializerConstants.ENTITY_AMP, "&", false, 4, (Object) null), SerializerConstants.ENTITY_LT, "<", false, 4, (Object) null), SerializerConstants.ENTITY_QUOT, "\"", false, 4, (Object) null)).toString();
    }

    /* JADX WARN: Code duplicated, block: B:11:0x002d  */
    /* JADX WARN: Code duplicated, block: B:20:0x0046  */
    /* JADX WARN: Code duplicated, block: B:27:0x0066  */
    public final a k(Context context, String str, File file, String str2) {
        String strA;
        String strC;
        int iB;
        context.getClass();
        str.getClass();
        file.getClass();
        str2.getClass();
        a aVarB = b(file, str2);
        JSONObject jSONObjectE = e(g(context, str));
        if (jSONObjectE == null || (strA = jSONObjectE.optString("appName")) == null) {
            strA = aVarB.a();
        } else {
            if (StringsKt.isBlank(strA)) {
                strA = null;
            }
            if (strA == null) {
                strA = aVarB.a();
            }
        }
        if (jSONObjectE == null || (strC = jSONObjectE.optString("versionName")) == null) {
            strC = aVarB.c();
        } else {
            if (StringsKt.isBlank(strC)) {
                strC = null;
            }
            if (strC == null) {
                strC = aVarB.c();
            }
        }
        if (jSONObjectE != null) {
            Integer numValueOf = Integer.valueOf(jSONObjectE.optInt("versionCode"));
            Integer num = numValueOf.intValue() >= 1 ? numValueOf : null;
            if (num != null) {
                iB = num.intValue();
            } else {
                iB = aVarB.b();
            }
        } else {
            iB = aVarB.b();
        }
        return new a(strA, strC, iB, l(context, str).isFile());
    }

    public final File l(Context context, String str) {
        context.getClass();
        str.getClass();
        return new File(h(context, str), "mipmap/ic_launcher.png");
    }

    public final synchronized void m(Context context, String str, String str2, String str3, int i) {
        try {
            context.getClass();
            str.getClass();
            str2.getClass();
            str3.getClass();
            File fileG = g(context, str);
            fileG.mkdirs();
            String string = StringsKt.trim(str2).toString();
            String string2 = StringsKt.trim(str3).toString();
            if (string2.length() == 0) {
                string2 = "1.0";
            }
            JSONObject jSONObjectPut = new JSONObject().put("appName", string).put("versionName", string2).put("versionCode", RangesKt.coerceAtLeast(i, 1));
            jSONObjectPut.getClass();
            q(jSONObjectPut, new File(fileG, "meta.json"));
            o(h(context, str), string);
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized boolean n(Context context, String str, File file) {
        context.getClass();
        str.getClass();
        file.getClass();
        boolean z = false;
        if (!file.isFile()) {
            return false;
        }
        try {
            p(h(context, str), file);
            z = true;
        } catch (Exception unused) {
        }
        return z;
    }

    public final void o(File file, String str) {
        File file2 = new File(file, CompoundEntry.NAME_values);
        file2.mkdirs();
        String strReplace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(str, "&", SerializerConstants.ENTITY_AMP, false, 4, (Object) null), "<", SerializerConstants.ENTITY_LT, false, 4, (Object) null), "\"", SerializerConstants.ENTITY_QUOT, false, 4, (Object) null);
        FilesKt.writeText$default(new File(file2, "strings.xml"), StringsKt.trimIndent("\n            <?xml version=\"1.0\" encoding=\"utf-8\"?>\n            <resources>\n                <string name=\"app_name\">" + strReplace$default + "</string>\n            </resources>\n            "), (Charset) null, 2, (Object) null);
    }

    public final void p(File file, File file2) {
        kv kvVar = kv.a;
        Bitmap bitmapB = kvVar.b(file2);
        if (bitmapB == null) {
            f2f.a("decode icon failed: ", file2.getAbsolutePath());
            return;
        }
        a aVarA = kvVar.a(bitmapB);
        if (bitmapB != aVarA.b() && bitmapB != aVarA.a()) {
            bitmapB.recycle();
        }
        File file3 = new File(file, "drawable");
        file3.mkdirs();
        File file4 = new File(file3, "ic_launcher_user_fg.png");
        File file5 = new File(file3, "ic_launcher_user_bg.png");
        if (!kvVar.d(aVarA.b(), file4)) {
            k2d.a("Check failed.");
            return;
        }
        if (!kvVar.d(aVarA.a(), file5)) {
            k2d.a("Check failed.");
            return;
        }
        aVarA.b().recycle();
        aVarA.a().recycle();
        new File(file3, "ic_launcher_user.png").delete();
        new File(file3, "ic_launcher_preview.png").delete();
        File file6 = new File(file, "mipmap");
        file6.mkdirs();
        File file7 = new File(file6, "ic_launcher.png");
        File file8 = new File(file6, "ic_launcher_round.png");
        File absoluteFile = file2.getAbsoluteFile();
        absoluteFile.getClass();
        File fileNormalize = FilesKt.normalize(absoluteFile);
        File absoluteFile2 = file7.getAbsoluteFile();
        absoluteFile2.getClass();
        if (!Intrinsics.areEqual(fileNormalize, FilesKt.normalize(absoluteFile2))) {
            FilesKt.copyTo$default(file2, file7, true, 0, 4, (Object) null);
            file7 = file7;
        }
        FilesKt.copyTo$default(file7, file8, true, 0, 4, (Object) null);
        File file9 = new File(file, "mipmap-anydpi-v26");
        file9.mkdirs();
        FilesKt.writeText$default(new File(file9, "ic_launcher.xml"), "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<adaptive-icon xmlns:android=\"http://schemas.android.com/apk/res/android\">\n    <background android:drawable=\"@drawable/ic_launcher_user_bg\" />\n    <foreground android:drawable=\"@drawable/ic_launcher_user_fg\" />\n</adaptive-icon>", (Charset) null, 2, (Object) null);
        FilesKt.writeText$default(new File(file9, "ic_launcher_round.xml"), "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<adaptive-icon xmlns:android=\"http://schemas.android.com/apk/res/android\">\n    <background android:drawable=\"@drawable/ic_launcher_user_bg\" />\n    <foreground android:drawable=\"@drawable/ic_launcher_user_fg\" />\n</adaptive-icon>", (Charset) null, 2, (Object) null);
    }

    public final void q(JSONObject jSONObject, File file) {
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        String string = jSONObject.toString();
        string.getClass();
        FilesKt.writeText$default(file, string, (Charset) null, 2, (Object) null);
    }
}
