package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class cc3 {
    public static volatile String b;
    public static volatile a c;
    public static final cc3 a = new cc3();
    public static volatile long d = -1;
    public static final Set e = SetsKt.setOf(new String[]{"DiagLogger.java", "CrashApp.java", "CrashActivity.java"});
    public static final int f = 8;

    public static final class a {
        public final String a;
        public final String b;
        public final String c;

        public a(String str, String str2, String str3) {
            str.getClass();
            str2.getClass();
            str3.getClass();
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        public final String a() {
            return this.b;
        }

        public final String b() {
            return this.c;
        }

        public final String c() {
            return this.a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return Intrinsics.areEqual(this.a, aVar.a) && Intrinsics.areEqual(this.b, aVar.b) && Intrinsics.areEqual(this.c, aVar.c);
        }

        public int hashCode() {
            return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
        }

        public String toString() {
            return "Dual(structure=" + this.a + ", code=" + this.b + ", heavy=" + this.c + ")";
        }
    }

    public static final class b implements Comparator {
        public final /* synthetic */ File b;

        public b(File file) {
            this.b = file;
        }

        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ComparisonsKt.compareValues(FilesKt.getInvariantSeparatorsPath(FilesKt.relativeTo((File) obj, this.b)), FilesKt.getInvariantSeparatorsPath(FilesKt.relativeTo((File) obj2, this.b)));
        }
    }

    public static boolean a(File file) {
        file.getClass();
        return true;
    }

    public static boolean b(Function1 function1, File file) {
        file.getClass();
        return file.isFile() && ((Boolean) function1.invoke(file)).booleanValue();
    }

    public static boolean c(File file) {
        file.getClass();
        return !e.contains(file.getName());
    }

    public static CharSequence d(byte b2) {
        return String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b2)}, 1));
    }

    public static /* synthetic */ void p(cc3 cc3Var, MessageDigest messageDigest, File file, File file2, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: bc3
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(cc3.a((File) obj2));
                }
            };
        }
        cc3Var.o(messageDigest, file, file2, function1);
    }

    public static final boolean s(File file) {
        File[] fileArrListFiles;
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file2), "dex")) {
                    return true;
                }
            }
        }
        return false;
    }

    public final String A(String str) {
        return "heavy_" + str;
    }

    public final String B(String str) {
        return "slot_bytes_" + str;
    }

    public final String C(String str) {
        return "slot_run_" + str;
    }

    public final String D(String str) {
        return "struct_" + str;
    }

    public final long E(Context context, String str) {
        context.getClass();
        str.getClass();
        return G(context).getLong(B(str), -1L);
    }

    public final String F(Context context, String str) {
        context.getClass();
        str.getClass();
        String string = G(context).getString(C(str), "");
        return string == null ? "" : string;
    }

    public final SharedPreferences G(Context context) {
        return context.getApplicationContext().getSharedPreferences("yima_debug_host_fp", 0);
    }

    public final void H(String str, a aVar) {
        str.getClass();
        aVar.getClass();
        b = str;
        c = aVar;
    }

    public final void I(MessageDigest messageDigest, String str) {
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        bytes.getClass();
        messageDigest.update(bytes);
        messageDigest.update((byte) 0);
    }

    public final int e(Context context) {
        context.getClass();
        int iCoerceAtLeast = RangesKt.coerceAtLeast(Math.max(G(context).getInt("debug_host_version_code", 1), RangesKt.coerceAtLeast((int) d, 0)) + 1, 2);
        d = iCoerceAtLeast;
        return iCoerceAtLeast;
    }

    public final boolean f(Context context, String str, a aVar) {
        SharedPreferences sharedPreferencesG;
        String string;
        String string2;
        String string3;
        context.getClass();
        str.getClass();
        aVar.getClass();
        if (!y(context, str) || (string = (sharedPreferencesG = G(context)).getString(z(str), null)) == null || (string2 = sharedPreferencesG.getString(D(str), null)) == null || (string3 = sharedPreferencesG.getString(A(str), null)) == null || !Intrinsics.areEqual(string, aVar.a()) || Intrinsics.areEqual(string2, aVar.c()) || !Intrinsics.areEqual(string3, aVar.b())) {
            return false;
        }
        return r(context, str);
    }

    public final boolean g(Context context, String str, a aVar) {
        context.getClass();
        str.getClass();
        aVar.getClass();
        if (!y(context, str)) {
            return false;
        }
        SharedPreferences sharedPreferencesG = G(context);
        return Intrinsics.areEqual(sharedPreferencesG.getString(D(str), null), aVar.c()) && Intrinsics.areEqual(sharedPreferencesG.getString(z(str), null), aVar.a());
    }

    public final boolean h(Context context, String str, a aVar) {
        SharedPreferences sharedPreferencesG;
        String string;
        String string2;
        context.getClass();
        str.getClass();
        aVar.getClass();
        return (!y(context, str) || (string = (sharedPreferencesG = G(context)).getString(D(str), null)) == null || (string2 = sharedPreferencesG.getString(z(str), null)) == null || !Intrinsics.areEqual(string, aVar.c()) || Intrinsics.areEqual(string2, aVar.a())) ? false : true;
    }

    public final void i() {
        b = null;
        c = null;
    }

    public final void j(Context context, String str, a aVar, long j, String str2) {
        context.getClass();
        str.getClass();
        aVar.getClass();
        str2.getClass();
        SharedPreferences.Editor editorRemove = G(context).edit().putString(D(str), aVar.c()).putString(z(str), aVar.a()).putString(A(str), aVar.b()).remove("fp_" + str).remove("last_project_id");
        if (j >= 0) {
            editorRemove.putLong(B(str), j);
        }
        if (str2.length() > 0) {
            editorRemove.putString(C(str), str2);
        }
        editorRemove.apply();
    }

    public final void k(Context context) {
        context.getClass();
        long j = d;
        if (j < 2) {
            return;
        }
        G(context).edit().putInt("debug_host_version_code", (int) j).commit();
    }

    public final void l(Context context) {
        a aVar;
        context.getClass();
        String str = b;
        if (str == null || (aVar = c) == null) {
            return;
        }
        j(context, str, aVar, xb3.a.i(context, str), kb3.a.g());
        i();
    }

    public final a m(Context context, String str, String str2) {
        context.getClass();
        str.getClass();
        str2.getClass();
        File file = new File(context.getFilesDir(), "projects/" + str);
        String strV = v(context, file, str2, str);
        String strU = u(file);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.getClass();
        I(messageDigest, "light=" + strV);
        I(messageDigest, "heavy=" + strU);
        return new a(w(messageDigest), t(file), strU);
    }

    public final void n(MessageDigest messageDigest, File file, String str) {
        if (!file.isFile()) {
            I(messageDigest, "missing:" + str);
            return;
        }
        I(messageDigest, "file:" + str + ":" + file.length());
        byte[] bArr = new byte[8192];
        FileInputStream fileInputStream = new FileInputStream(file);
        while (true) {
            try {
                int i = fileInputStream.read(bArr);
                if (i <= 0) {
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileInputStream, (Throwable) null);
                    messageDigest.update((byte) 0);
                    return;
                }
                messageDigest.update(bArr, 0, i);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileInputStream, th);
                    throw th2;
                }
            }
        }
    }

    public final void o(MessageDigest messageDigest, File file, File file2, final Function1 function1) {
        if (file.isDirectory()) {
            for (File file3 : CollectionsKt.sortedWith(SequencesKt.toList(SequencesKt.filter(FilesKt.walkTopDown(file), new Function1() { // from class: ac3
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(cc3.b(function1, (File) obj));
                }
            })), new b(file2))) {
                n(messageDigest, file3, FilesKt.getInvariantSeparatorsPath(FilesKt.relativeTo(file3, file2)));
            }
            return;
        }
        I(messageDigest, "missingDir:" + file.getName());
    }

    public final long q() {
        return d;
    }

    public final boolean r(Context context, String str) {
        context.getClass();
        str.getClass();
        File file = new File(context.getFilesDir(), "projects/" + str + "/build");
        return s(new File(file, "shell-dex")) && s(new File(file, "code-slot-dex"));
    }

    public final String t(File file) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.getClass();
        o(messageDigest, new File(file, "src"), file, new Function1() { // from class: zb3
            public final Object invoke(Object obj) {
                return Boolean.valueOf(cc3.c((File) obj));
            }
        });
        return w(messageDigest);
    }

    public final String u(File file) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.getClass();
        I(messageDigest, "toolchain=" + n11.a.c());
        I(messageDigest, "debugHostHint=1");
        n(messageDigest, new File(file, "build.gradle"), "build.gradle");
        p(this, messageDigest, new File(file, "res"), file, null, 8, null);
        p(this, messageDigest, new File(file, "assets"), file, null, 8, null);
        p(this, messageDigest, new File(file, "lib"), file, null, 8, null);
        p(this, messageDigest, new File(file, "libs"), file, null, 8, null);
        p(this, messageDigest, new File(file, "jni"), file, null, 8, null);
        p(this, messageDigest, new File(file, "jniLibs"), file, null, 8, null);
        return w(messageDigest);
    }

    public final String v(Context context, File file, String str, String str2) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.getClass();
        dc3 dc3Var = dc3.a;
        I(messageDigest, "host=" + dc3Var.a(str2));
        I(messageDigest, "label=" + StringsKt.trim(str).toString());
        I(messageDigest, "dbgLabel=" + dc3Var.e(str));
        File file2 = new File(context.getFilesDir(), "signing/debug.p12");
        if (file2.isFile()) {
            I(messageDigest, "debugKey=" + file2.length() + ":" + file2.lastModified());
        } else {
            I(messageDigest, "debugKey=missing");
        }
        n(messageDigest, new File(file, "AndroidManifest.xml"), "AndroidManifest.xml");
        return w(messageDigest);
    }

    public final String w(MessageDigest messageDigest) {
        byte[] bArrDigest = messageDigest.digest();
        bArrDigest.getClass();
        return ArraysKt.joinToString$default(bArrDigest, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: yb3
            public final Object invoke(Object obj) {
                return cc3.d(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    public final void x(Context context, String str) {
        context.getClass();
        str.getClass();
        G(context).edit().remove(D(str)).remove(z(str)).remove(A(str)).remove(C(str)).remove(B(str)).remove("fp_" + str).remove("last_project_id").apply();
        if (Intrinsics.areEqual(b, str)) {
            i();
        }
    }

    public final boolean y(Context context, String str) {
        Object obj;
        context.getClass();
        str.getClass();
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.constructor-impl(context.getPackageManager().getPackageInfo(dc3.a.a(str), 0));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        return Result.isSuccess-impl(obj);
    }

    public final String z(String str) {
        return "code_" + str;
    }
}
