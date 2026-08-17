package defpackage;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class cfb {
    public static final cfb a = new cfb();
    public static final Set b = SetsKt.setOf(new String[]{"build", ".gradle", ".idea", "node_modules", "target", ".cxx", "__pycache__", ".venv", "venv", ".git"});
    public static final int c = 8;

    public static final class a extends Exception {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str) {
            super(str);
            str.getClass();
        }
    }

    public static boolean a(File file) {
        file.getClass();
        return file.isFile();
    }

    public static String b(File file, File file2) {
        file2.getClass();
        String path = FilesKt.relativeTo(file2, file).getPath();
        path.getClass();
        return StringsKt.replace$default(path, '\\', '/', false, 4, (Object) null);
    }

    public static boolean c(String str) {
        str.getClass();
        return (StringsKt.isBlank(str) || a.j(str) || Intrinsics.areEqual(str, "yima-project.json")) ? false : true;
    }

    public static Unit d(String str, File file, OutputStream outputStream) {
        outputStream.getClass();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream, 8192));
        try {
            zipOutputStream.putNextEntry(new ZipEntry("yima-project.json"));
            byte[] bytes = str.getBytes(Charsets.UTF_8);
            bytes.getClass();
            zipOutputStream.write(bytes);
            zipOutputStream.closeEntry();
            for (File file2 : FilesKt.walkTopDown(file)) {
                if (!Intrinsics.areEqual(file2, file) && file2.isFile()) {
                    String path = FilesKt.relativeTo(file2, file).getPath();
                    path.getClass();
                    String strReplace$default = StringsKt.replace$default(path, '\\', '/', false, 4, (Object) null);
                    if (!StringsKt.isBlank(strReplace$default) && !StringsKt.startsWith$default(strReplace$default, PsuedoNames.PSEUDONAME_ROOT, false, 2, (Object) null) && !StringsKt.contains$default(strReplace$default, Constants.ATTRVAL_PARENT, false, 2, (Object) null) && !a.j(strReplace$default) && !Intrinsics.areEqual(strReplace$default, "yima-project.json")) {
                        zipOutputStream.putNextEntry(new ZipEntry(strReplace$default));
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file2), 8192);
                        try {
                            ByteStreamsKt.copyTo$default(bufferedInputStream, zipOutputStream, 0, 2, (Object) null);
                            CloseableKt.closeFinally(bufferedInputStream, (Throwable) null);
                            zipOutputStream.closeEntry();
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                CloseableKt.closeFinally(bufferedInputStream, th);
                                throw th2;
                            }
                        }
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(zipOutputStream, (Throwable) null);
            return Unit.INSTANCE;
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                CloseableKt.closeFinally(zipOutputStream, th3);
                throw th4;
            }
        }
    }

    public final long e(InputStream inputStream, OutputStream outputStream, long j) throws IOException, a {
        byte[] bArr = new byte[16384];
        long j2 = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (i <= 0) {
                return j2;
            }
            j2 += (long) i;
            if (j2 > j) {
                throw new a("单个文件过大");
            }
            outputStream.write(bArr, 0, i);
        }
    }

    public final b f(Context context, dfb dfbVar) {
        context.getClass();
        dfbVar.getClass();
        final File fileV = ufb.a.V(context, dfbVar.e());
        if (!fileV.isDirectory()) {
            return new b.a("项目目录不存在");
        }
        String strReplace = new Regex("[\\\\/:*?\"<>|\\s]+").replace(StringsKt.trim(dfbVar.c()).toString(), "_");
        if (StringsKt.isBlank(strReplace)) {
            strReplace = "project";
        }
        String str = StringsKt.take(strReplace, 40) + "-source-" + new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.getDefault()).format(new Date()) + ".zip";
        final String string = new JSONObject().put(Constants.ATTRNAME_FORMAT, "yima-project").put("formatVersion", 1).put("name", dfbVar.f()).put("nickname", dfbVar.g()).put("packageName", dfbVar.h()).put("type", dfbVar.i()).toString();
        string.getClass();
        hb0.a aVarX = hb0.a.x(context, str, new Function1() { // from class: yeb
            public final Object invoke(Object obj) {
                return cfb.d(string, fileV, (OutputStream) obj);
            }
        });
        return aVarX != null ? new b.C0001b(aVarX) : new b.a("无法写入下载目录");
    }

    public final String g(Context context, Uri uri, File file) throws IOException, a {
        File canonicalFile = file.getCanonicalFile();
        InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
        if (inputStreamOpenInputStream == null) {
            throw new a("无法读取所选文件");
        }
        try {
            int i = 8192;
            ZipInputStream zipInputStream = new ZipInputStream(inputStreamOpenInputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStreamOpenInputStream : new BufferedInputStream(inputStreamOpenInputStream, 8192));
            long j = 0;
            String text$default = null;
            int i2 = 0;
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry == null) {
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(zipInputStream, (Throwable) null);
                        CloseableKt.closeFinally(inputStreamOpenInputStream, (Throwable) null);
                        return text$default;
                    }
                    cfb cfbVar = a;
                    String name = nextEntry.getName();
                    name.getClass();
                    String strM = cfbVar.m(name);
                    if (strM != null) {
                        if (cfbVar.j(strM)) {
                            zipInputStream.closeEntry();
                        } else {
                            i2++;
                            if (i2 > 8000) {
                                throw new a("压缩包内文件过多");
                            }
                            if (nextEntry.isDirectory()) {
                                File file2 = new File(file, strM);
                                canonicalFile.getClass();
                                if (!cfbVar.k(canonicalFile, file2)) {
                                    throw new a("压缩包路径不合法");
                                }
                                file2.mkdirs();
                                zipInputStream.closeEntry();
                            } else {
                                File file3 = new File(file, strM);
                                canonicalFile.getClass();
                                if (!cfbVar.k(canonicalFile, file3)) {
                                    throw new a("压缩包路径不合法");
                                }
                                File parentFile = file3.getParentFile();
                                if (parentFile != null) {
                                    parentFile.mkdirs();
                                }
                                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file3), i);
                                try {
                                    long jE = cfbVar.e(zipInputStream, bufferedOutputStream, 31457280L);
                                    CloseableKt.closeFinally(bufferedOutputStream, (Throwable) null);
                                    j += jE;
                                    if (j > 209715200) {
                                        throw new a("解压后体积过大");
                                    }
                                    if (Intrinsics.areEqual(strM, "yima-project.json") || StringsKt.endsWith$default(strM, "/yima-project.json", false, 2, (Object) null)) {
                                        text$default = FilesKt.readText$default(file3, (Charset) null, 1, (Object) null);
                                    }
                                    zipInputStream.closeEntry();
                                    i = 8192;
                                } catch (Throwable th) {
                                    try {
                                        throw th;
                                    } catch (Throwable th2) {
                                        CloseableKt.closeFinally(bufferedOutputStream, th);
                                        throw th2;
                                    }
                                }
                            }
                        }
                    }
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        CloseableKt.closeFinally(zipInputStream, th3);
                        throw th4;
                    }
                }
                try {
                    throw th;
                } catch (Throwable th5) {
                    CloseableKt.closeFinally(inputStreamOpenInputStream, th);
                    throw th5;
                }
            }
        } catch (Throwable th6) {
            throw th6;
        }
    }

    public final void h(File file) {
        boolean z;
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            ArrayList arrayList = new ArrayList();
            for (File file2 : fileArrListFiles) {
                if (!Intrinsics.areEqual(file2.getName(), ".DS_Store") && !Intrinsics.areEqual(file2.getName(), "__MACOSX")) {
                    arrayList.add(file2);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (((File) obj).isDirectory()) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : arrayList) {
                if (((File) obj2).isFile()) {
                    arrayList3.add(obj2);
                }
            }
            if (arrayList3.isEmpty()) {
                z = true;
                break;
            }
            Iterator it = arrayList3.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (!Intrinsics.areEqual(((File) it.next()).getName(), "yima-project.json")) {
                        z = false;
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
            if (arrayList2.size() != 1) {
                return;
            }
            if (arrayList3.isEmpty() || z) {
                File file3 = (File) CollectionsKt.single(arrayList2);
                if (new File(file3, "AndroidManifest.xml").isFile() || new File(file3, "build.gradle").isFile() || new File(file3, "yima-project.json").isFile()) {
                    File[] fileArrListFiles2 = file3.listFiles();
                    if (fileArrListFiles2 != null) {
                        for (File file4 : fileArrListFiles2) {
                            File file5 = new File(file, file4.getName());
                            if (!file5.exists()) {
                                file4.renameTo(file5);
                            }
                        }
                    }
                    FilesKt.deleteRecursively(file3);
                }
            }
        }
    }

    public final c i(Context context, Uri uri) {
        c.a aVar;
        Object obj;
        JSONObject jSONObject;
        context.getClass();
        uri.getClass();
        if (n(context, uri) > 83886080) {
            return new c.a("压缩包过大（超过 80MB）");
        }
        ufb ufbVar = ufb.a;
        String strF0 = ufbVar.f0();
        File fileV = ufbVar.V(context, strF0);
        if (fileV.exists()) {
            return new c.a("无法分配项目目录，请重试");
        }
        fileV.mkdirs();
        try {
            String strG = g(context, uri, fileV);
            h(fileV);
            File file = new File(fileV, "yima-project.json");
            if (strG == null) {
                File file2 = file.isFile() ? file : null;
                strG = file2 != null ? FilesKt.readText$default(file2, (Charset) null, 1, (Object) null) : null;
            }
            file.delete();
            if (strG != null) {
                try {
                    Result.Companion companion = Result.Companion;
                    obj = Result.constructor-impl(new JSONObject(strG));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                if (Result.isFailure-impl(obj)) {
                    obj = null;
                }
                jSONObject = (JSONObject) obj;
            } else {
                jSONObject = null;
            }
            if (jSONObject != null && Intrinsics.areEqual(jSONObject.optString(Constants.ATTRNAME_FORMAT), "yima-project")) {
                if (jSONObject.optInt("formatVersion", 1) > 1) {
                    FilesKt.deleteRecursively(fileV);
                    return new c.a("源码包版本较新，请升级 Yima IDE 后再导入");
                }
                if (!new File(fileV, "AndroidManifest.xml").isFile() && !new File(fileV, "build.gradle").isFile()) {
                    FilesKt.deleteRecursively(fileV);
                    return new c.a("项目文件不完整");
                }
                String strOptString = jSONObject.optString("name");
                strOptString.getClass();
                String string = StringsKt.trim(strOptString).toString();
                if (StringsKt.isBlank(string)) {
                    string = a.o(fileV);
                }
                if (StringsKt.isBlank(string)) {
                    string = "导入的项目";
                }
                String strOptString2 = jSONObject.optString("nickname");
                strOptString2.getClass();
                String string2 = StringsKt.trim(strOptString2).toString();
                String strOptString3 = jSONObject.optString("packageName");
                strOptString3.getClass();
                String string3 = StringsKt.trim(strOptString3).toString();
                jwa jwaVar = jwa.a;
                String strP = jwaVar.c(string3).a() ? string3 : null;
                if (strP == null && (strP = p(fileV)) == null) {
                    strP = jwaVar.a(strF0, string);
                }
                String str = strP;
                String strOptString4 = jSONObject.optString("type");
                strOptString4.getClass();
                String string4 = StringsKt.trim(strOptString4).toString();
                if (StringsKt.isBlank(string4)) {
                    string4 = "android";
                }
                String str2 = string4;
                List listL = l(fileV);
                if (listL.isEmpty()) {
                    listL = CollectionsKt.listOf("AndroidManifest.xml");
                }
                return new c.b(ufb.a.m(context, new dfb(strF0, string, string2, str, str2, listL)));
            }
            FilesKt.deleteRecursively(fileV);
            return new c.a("非法项目");
        } catch (a e) {
            FilesKt.deleteRecursively(fileV);
            String message = e.getMessage();
            aVar = new c.a(message != null ? message : "导入失败");
            return aVar;
        } catch (Throwable th2) {
            Log.e("ProjectArchive", "importFromUri failed", th2);
            FilesKt.deleteRecursively(fileV);
            String message2 = th2.getMessage();
            aVar = new c.a(message2 != null ? message2 : "导入失败");
            return aVar;
        }
    }

    public final boolean j(String str) {
        List<String> listSplit$default = StringsKt.split$default(str, new char[]{'/'}, false, 0, 6, (Object) null);
        if ((listSplit$default instanceof Collection) && listSplit$default.isEmpty()) {
            return false;
        }
        for (String str2 : listSplit$default) {
            if (b.contains(str2) || Intrinsics.areEqual(str2, "__MACOSX")) {
                return true;
            }
        }
        return false;
    }

    public final boolean k(File file, File file2) throws IOException {
        String canonicalPath = file.getCanonicalPath();
        String path = file2.getCanonicalFile().getPath();
        if (Intrinsics.areEqual(path, canonicalPath)) {
            return true;
        }
        path.getClass();
        String str = File.separator;
        StringBuilder sb = new StringBuilder();
        sb.append(canonicalPath);
        sb.append(str);
        return StringsKt.startsWith$default(path, sb.toString(), false, 2, (Object) null);
    }

    public final List l(final File file) {
        return !file.isDirectory() ? CollectionsKt.emptyList() : SequencesKt.toList(SequencesKt.sorted(SequencesKt.filter(SequencesKt.map(SequencesKt.filter(FilesKt.walkTopDown(file), new Function1() { // from class: zeb
            public final Object invoke(Object obj) {
                return Boolean.valueOf(cfb.a((File) obj));
            }
        }), new Function1() { // from class: afb
            public final Object invoke(Object obj) {
                return cfb.b(file, (File) obj);
            }
        }), new Function1() { // from class: bfb
            public final Object invoke(Object obj) {
                return Boolean.valueOf(cfb.c((String) obj));
            }
        })));
    }

    public final String m(String str) {
        String strTrimStart = StringsKt.trimStart(StringsKt.replace$default(StringsKt.trim(str).toString(), '\\', '/', false, 4, (Object) null), new char[]{'/'});
        if (StringsKt.isBlank(strTrimStart) || StringsKt.contains$default(strTrimStart, Constants.ATTRVAL_PARENT, false, 2, (Object) null) || StringsKt.startsWith$default(strTrimStart, "__MACOSX", false, 2, (Object) null)) {
            return null;
        }
        return strTrimStart;
    }

    public final long n(Context context, Uri uri) {
        Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{"_size"}, null, null, null);
        if (cursorQuery == null) {
            return 0L;
        }
        try {
            long j = cursorQuery.moveToFirst() ? cursorQuery.getLong(0) : 0L;
            CloseableKt.closeFinally(cursorQuery, (Throwable) null);
            return j;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorQuery, th);
                throw th2;
            }
        }
    }

    public final String o(File file) {
        List groupValues;
        String str;
        File file2 = new File(file, "res/values/strings.xml");
        if (!file2.isFile()) {
            return "";
        }
        String string = null;
        MatchResult matchResultFind$default = Regex.find$default(new Regex("<string\\s+name=\"app_name\">([^<]*)</string>"), FilesKt.readText$default(file2, (Charset) null, 1, (Object) null), 0, 2, (Object) null);
        if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null && (str = (String) groupValues.get(1)) != null) {
            string = StringsKt.trim(str).toString();
        }
        return string == null ? "" : string;
    }

    public final String p(File file) {
        String text$default;
        MatchResult matchResultFind$default;
        File file2 = new File(file, "AndroidManifest.xml");
        if (!file2.isFile()) {
            file2 = null;
        }
        if (file2 == null || (text$default = FilesKt.readText$default(file2, (Charset) null, 1, (Object) null)) == null || (matchResultFind$default = Regex.find$default(new Regex("package\\s*=\\s*\"([^\"]+)\""), text$default, 0, 2, (Object) null)) == null) {
            return null;
        }
        String string = StringsKt.trim((String) matchResultFind$default.getGroupValues().get(1)).toString();
        if (jwa.a.c(string).a()) {
            return string;
        }
        return null;
    }

    public static abstract class b {

        public static final class a extends b {
            public final String a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(String str) {
                super(null);
                str.getClass();
                this.a = str;
            }

            public final String a() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof a) && Intrinsics.areEqual(this.a, ((a) obj).a);
            }

            public int hashCode() {
                return this.a.hashCode();
            }

            public String toString() {
                return "Err(message=" + this.a + ")";
            }
        }

        /* JADX INFO: renamed from: cfb$b$b, reason: collision with other inner class name */
        public static final class C0001b extends b {
            public final hb0.a a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0001b(hb0.a aVar) {
                super(null);
                aVar.getClass();
                this.a = aVar;
            }

            public final hb0.a a() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof C0001b) && Intrinsics.areEqual(this.a, ((C0001b) obj).a);
            }

            public int hashCode() {
                return this.a.hashCode();
            }

            public String toString() {
                return "Ok(file=" + this.a + ")";
            }
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public b() {
        }
    }

    public static abstract class c {

        public static final class a extends c {
            public final String a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(String str) {
                super(null);
                str.getClass();
                this.a = str;
            }

            public final String a() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof a) && Intrinsics.areEqual(this.a, ((a) obj).a);
            }

            public int hashCode() {
                return this.a.hashCode();
            }

            public String toString() {
                return "Err(message=" + this.a + ")";
            }
        }

        public static final class b extends c {
            public static final int b = dfb.g;
            public final dfb a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(dfb dfbVar) {
                super(null);
                dfbVar.getClass();
                this.a = dfbVar;
            }

            public final dfb a() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof b) && Intrinsics.areEqual(this.a, ((b) obj).a);
            }

            public int hashCode() {
                return this.a.hashCode();
            }

            public String toString() {
                return "Ok(project=" + this.a + ")";
            }
        }

        public /* synthetic */ c(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public c() {
        }
    }
}
