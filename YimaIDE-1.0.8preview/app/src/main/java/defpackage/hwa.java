package defpackage;

import java.io.File;
import java.nio.charset.Charset;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class hwa {
    public static final hwa a = new hwa();

    public static CharSequence a(String str, MatchResult matchResult) {
        matchResult.getClass();
        return matchResult.getGroupValues().get(1) + str;
    }

    public static boolean b(File file) {
        file.getClass();
        if (file.isFile()) {
            return Intrinsics.areEqual(FilesKt.getExtension(file), "java") || Intrinsics.areEqual(FilesKt.getExtension(file), "kt");
        }
        return false;
    }

    public static CharSequence c(String str, MatchResult matchResult) {
        matchResult.getClass();
        return matchResult.getGroupValues().get(1) + str + matchResult.getGroupValues().get(2);
    }

    public static CharSequence d(String str, MatchResult matchResult) {
        matchResult.getClass();
        return matchResult.getGroupValues().get(1) + str + matchResult.getGroupValues().get(2);
    }

    public static CharSequence e(String str, MatchResult matchResult) {
        matchResult.getClass();
        return matchResult.getGroupValues().get(1) + str + matchResult.getGroupValues().get(2);
    }

    public static CharSequence f(String str, MatchResult matchResult) {
        matchResult.getClass();
        return matchResult.getGroupValues().get(1) + str;
    }

    public static CharSequence g(String str, MatchResult matchResult) {
        matchResult.getClass();
        return str;
    }

    public final void h(File file, File file2) {
        while (file != null && FilesKt.startsWith(file, file2) && !Intrinsics.areEqual(file, file2)) {
            String[] list = file.list();
            if (list != null) {
                if (!(list.length == 0)) {
                    return;
                }
            }
            File parentFile = file.getParentFile();
            file.delete();
            file = parentFile;
        }
    }

    public final void i(File file, File file2) {
        if (!file.isDirectory()) {
            File parentFile = file2.getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            FilesKt.copyTo$default(file, file2, true, 0, 4, (Object) null);
            return;
        }
        file2.mkdirs();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file3 : fileArrListFiles) {
                hwa hwaVar = a;
                file3.getClass();
                hwaVar.i(file3, new File(file2, file3.getName()));
            }
        }
    }

    public final void j(File file, String str, String str2) {
        File file2 = new File(file, "src/" + str);
        if (file2.isDirectory()) {
            File file3 = new File(file, "src/" + str2);
            File parentFile = file3.getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            if (!file2.renameTo(file3)) {
                i(file2, file3);
                FilesKt.deleteRecursively(file2);
            }
            h(file2.getParentFile(), new File(file, "src"));
        }
    }

    public final a k(File file, String str, String str2) {
        file.getClass();
        str.getClass();
        str2.getClass();
        String string = StringsKt.trim(str2).toString();
        if (Intrinsics.areEqual(string, str)) {
            return a.c.a;
        }
        a aVarC = jwa.a.c(string);
        if (aVarC instanceof a) {
            return new a.C0008a(aVarC.b());
        }
        if (!Intrinsics.areEqual(aVarC, b.a)) {
            bu8.a();
            return null;
        }
        String strReplace$default = StringsKt.replace$default(str, '.', '/', false, 4, (Object) null);
        String strReplace$default2 = StringsKt.replace$default(string, '.', '/', false, 4, (Object) null);
        File file2 = new File(file, "src/" + strReplace$default2);
        if (file2.exists()) {
            if (!Intrinsics.areEqual(new File(file, "src/" + strReplace$default).getCanonicalPath(), file2.getCanonicalPath())) {
                return new a.C0008a("目标源码目录已存在：src/" + strReplace$default2);
            }
        }
        try {
            o(new File(file, "AndroidManifest.xml"), str, string);
            n(new File(file, "build.gradle"), str, string);
            j(file, strReplace$default, strReplace$default2);
            m(file, str, string);
            return new a.b(string);
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null) {
                message = "包名迁移失败";
            }
            return new a.C0008a(message);
        }
    }

    public final String l(String str, String str2, final String str3) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        if (Intrinsics.areEqual(str2, str3)) {
            return str;
        }
        String strEscape = Regex.Companion.escape(str2);
        return new Regex("(?<![A-Za-z0-9_.])" + strEscape + "(?=\\.)").replace(new Regex("(?m)^(\\s*import\\s+)" + strEscape + "(?=\\.|\\s|;|$)").replace(new Regex("(?m)^(\\s*package\\s+)" + strEscape + "(?=\\.|\\s|;|$)").replace(str, new Function1() { // from class: ewa
            public final Object invoke(Object obj) {
                return hwa.a(str3, (MatchResult) obj);
            }
        }), new Function1() { // from class: fwa
            public final Object invoke(Object obj) {
                return hwa.f(str3, (MatchResult) obj);
            }
        }), new Function1() { // from class: gwa
            public final Object invoke(Object obj) {
                return hwa.g(str3, (MatchResult) obj);
            }
        });
    }

    public final void m(File file, String str, String str2) {
        File file2 = new File(file, "src");
        if (file2.isDirectory()) {
            for (File file3 : SequencesKt.filter(FilesKt.walkTopDown(file2), new Function1() { // from class: dwa
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(hwa.b((File) obj));
                }
            })) {
                String text$default = FilesKt.readText$default(file3, (Charset) null, 1, (Object) null);
                String strL = a.l(text$default, str, str2);
                if (!Intrinsics.areEqual(strL, text$default)) {
                    FilesKt.writeText$default(file3, strL, (Charset) null, 2, (Object) null);
                }
            }
        }
    }

    public final void n(File file, String str, final String str2) {
        if (file.isFile()) {
            String text$default = FilesKt.readText$default(file, (Charset) null, 1, (Object) null);
            Regex.Companion companion = Regex.Companion;
            FilesKt.writeText$default(file, new Regex("(\\bnamespace\\s+['\"])" + companion.escape(str) + "(['\"])").replace(new Regex("(\\bapplicationId\\s+[\"'])" + companion.escape(str) + "([\"'])").replace(text$default, new Function1() { // from class: bwa
                public final Object invoke(Object obj) {
                    return hwa.c(str2, (MatchResult) obj);
                }
            }), new Function1() { // from class: cwa
                public final Object invoke(Object obj) {
                    return hwa.e(str2, (MatchResult) obj);
                }
            }), (Charset) null, 2, (Object) null);
        }
    }

    public final void o(File file, String str, final String str2) {
        if (file.isFile()) {
            FilesKt.writeText$default(file, l(new Regex("(<manifest\\b[^>]*\\spackage=\")" + Regex.Companion.escape(str) + "(\")").replace(FilesKt.readText$default(file, (Charset) null, 1, (Object) null), new Function1() { // from class: awa
                public final Object invoke(Object obj) {
                    return hwa.d(str2, (MatchResult) obj);
                }
            }), str, str2), (Charset) null, 2, (Object) null);
        }
    }

    public static abstract class a {

        /* JADX INFO: renamed from: hwa$a$a, reason: collision with other inner class name */
        public static final class C0008a extends a {
            public final String a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0008a(String str) {
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
                return (obj instanceof C0008a) && Intrinsics.areEqual(this.a, ((C0008a) obj).a);
            }

            public int hashCode() {
                return this.a.hashCode();
            }

            public String toString() {
                return "Failure(message=" + this.a + ")";
            }
        }

        public static final class b extends a {
            public final String a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(String str) {
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
                return (obj instanceof b) && Intrinsics.areEqual(this.a, ((b) obj).a);
            }

            public int hashCode() {
                return this.a.hashCode();
            }

            public String toString() {
                return "Success(newPackage=" + this.a + ")";
            }
        }

        public static final class c extends a {
            public static final c a = new c();

            public c() {
                super(null);
            }

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof c);
            }

            public int hashCode() {
                return 344053000;
            }

            public String toString() {
                return "Unchanged";
            }
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }
}
