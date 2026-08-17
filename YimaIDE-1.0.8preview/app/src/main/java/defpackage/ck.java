package defpackage;

import android.content.Context;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ck {
    public static final ck a = new ck();

    public static final class a {
        public final String a;
        public final String b;
        public final int c;
        public final int d;

        public a(String str, String str2, int i, int i2) {
            str.getClass();
            str2.getClass();
            this.a = str;
            this.b = str2;
            this.c = i;
            this.d = i2;
        }

        public final String a() {
            return this.b;
        }

        public final int b() {
            return this.c;
        }

        public final String c() {
            return this.a;
        }

        public final int d() {
            return this.d;
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
            return (((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d);
        }

        public String toString() {
            return "Progress(stage=" + this.a + ", detail=" + this.b + ", done=" + this.c + ", total=" + this.d + ")";
        }
    }

    public static final class c extends TimerTask {
        public final /* synthetic */ long b;
        public final /* synthetic */ Function1 c;

        public c(long j, Function1 function1) {
            this.b = j;
            this.c = function1;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            long jCurrentTimeMillis = (System.currentTimeMillis() - this.b) / 1000;
            this.c.invoke(new a("link", jCurrentTimeMillis + "s", 0, 0));
        }
    }

    public static final String b(Ref.IntRef intRef, Function1 function1, int i, File file, Context context, ArrayList arrayList, File file2, String str, String str2) {
        hk hkVar = hk.a;
        if (hkVar.i()) {
            return "aapt2 已取消";
        }
        intRef.element++;
        if (function1 != null) {
            function1.invoke(new a("compile", str2, intRef.element, i));
        }
        File file3 = new File(file, str);
        hk.a aVarL = hk.l(hkVar, context, CollectionsKt.listOf(new String[]{"compile", "--dir", file2.getAbsolutePath(), "-o", file3.getAbsolutePath()}), null, 0L, 12, null);
        if (aVarL.a()) {
            return "aapt2 已取消";
        }
        if (!aVarL.b()) {
            return aVarL.d();
        }
        if (!file3.isFile()) {
            return null;
        }
        arrayList.add(file3);
        return null;
    }

    public final b a(Context context, File file, File file2, String str, int i, int i2, int i3, String str2, File file3, File file4, List list, List list2, List list3, String str3, List list4, Function1 function1) {
        String strB;
        context.getClass();
        file.getClass();
        file2.getClass();
        str.getClass();
        str2.getClass();
        file3.getClass();
        file4.getClass();
        list.getClass();
        list2.getClass();
        list3.getClass();
        str3.getClass();
        list4.getClass();
        ArrayList arrayList = new ArrayList();
        File file5 = new File(file2, "aapt2");
        FilesKt.deleteRecursively(file5);
        file5.mkdirs();
        File file6 = new File(file5, "compiled");
        file6.mkdirs();
        File file7 = new File(file2, "generated/aapt2-r");
        file7.mkdirs();
        File file8 = new File(file5, "out.ap_");
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : list) {
            if (((File) obj).isDirectory()) {
                arrayList2.add(obj);
            }
        }
        File file9 = new File(file, "res");
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : list4) {
            if (((File) obj2).isDirectory()) {
                arrayList3.add(obj2);
            }
        }
        int size = arrayList2.size() + (file9.isDirectory() ? 1 : 0) + arrayList3.size();
        if (size == 0) {
            return new b(false, null, null, arrayList, "没有可编译的资源(项目 res/ 缺失且无库资源)", 6, null);
        }
        ArrayList arrayList4 = new ArrayList();
        Ref.IntRef intRef = new Ref.IntRef();
        int i4 = 0;
        for (Object obj3 : arrayList2) {
            int i5 = i4 + 1;
            if (i4 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            File file10 = (File) obj3;
            File file11 = file8;
            int i6 = size;
            File file12 = file7;
            int i7 = i4;
            String strB2 = b(intRef, function1, i6, file6, context, arrayList4, file10, "lib_" + i4 + ".zip", a.d(file10));
            if (strB2 != null) {
                return new b(false, null, null, arrayList, "aapt2 compile(库资源 #" + i7 + ")失败:\n" + strB2, 6, null);
            }
            file8 = file11;
            size = i6;
            i4 = i5;
            file7 = file12;
        }
        File file13 = file8;
        int i8 = size;
        File file14 = file7;
        if (file9.isDirectory() && (strB = b(intRef, function1, i8, file6, context, arrayList4, file9, "project.zip", "项目 res")) != null) {
            return new b(false, null, null, arrayList, "aapt2 compile(项目资源)失败:\n".concat(strB), 6, null);
        }
        int i9 = 0;
        for (Object obj4 : arrayList3) {
            int i10 = i9 + 1;
            if (i9 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String strB3 = b(intRef, function1, i8, file6, context, arrayList4, (File) obj4, "overlay_" + i9 + ".zip", "overlay #" + i9);
            if (strB3 != null) {
                return new b(false, null, null, arrayList, "aapt2 compile(正式 overlay #" + i9 + ")失败:\n" + strB3, 6, null);
            }
            i9 = i10;
        }
        if (arrayList4.isEmpty()) {
            return new b(false, null, null, arrayList, "没有可编译的资源(项目 res/ 缺失且无库资源)", 6, null);
        }
        arrayList.add("aapt2 compile: " + arrayList4.size() + " 个资源包");
        if (function1 != null) {
            function1.invoke(new a("manifest", "合并清单", 0, 0));
        }
        if (hk.a.i()) {
            return new b(false, null, null, arrayList, "aapt2 已取消", 6, null);
        }
        File file15 = new File(file5, "AndroidManifest.merged.xml");
        Timer timer = null;
        ht9.a aVarF = ht9.a.f(FilesKt.readText$default(file4, (Charset) null, 1, (Object) null), list3, str3);
        Iterator it = aVarF.a().iterator();
        while (it.hasNext()) {
            arrayList.add("[Manifest] " + ((String) it.next()));
        }
        FilesKt.writeText$default(file15, aVarF.b(), (Charset) null, 2, (Object) null);
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add("link");
        arrayList5.add("-o");
        arrayList5.add(file13.getAbsolutePath());
        arrayList5.add("-I");
        arrayList5.add(file3.getAbsolutePath());
        arrayList5.add("--manifest");
        arrayList5.add(file15.getAbsolutePath());
        arrayList5.add("--java");
        arrayList5.add(file14.getAbsolutePath());
        arrayList5.add("--custom-package");
        arrayList5.add(str);
        arrayList5.add("--min-sdk-version");
        arrayList5.add(String.valueOf(i));
        arrayList5.add("--target-sdk-version");
        arrayList5.add(String.valueOf(i2));
        arrayList5.add("--version-code");
        arrayList5.add(String.valueOf(i3));
        arrayList5.add("--version-name");
        arrayList5.add(str2);
        arrayList5.add("--auto-add-overlay");
        ArrayList arrayList6 = new ArrayList();
        for (Object obj5 : list2) {
            String str4 = (String) obj5;
            if (!StringsKt.isBlank(str4) && !Intrinsics.areEqual(str4, str)) {
                arrayList6.add(obj5);
            }
        }
        List listDistinct = CollectionsKt.distinct(arrayList6);
        if (!listDistinct.isEmpty()) {
            arrayList5.add("--extra-packages");
            arrayList5.add(CollectionsKt.joinToString$default(listDistinct, ":", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        }
        arrayList5.add(((File) CollectionsKt.first(arrayList4)).getAbsolutePath());
        for (File file16 : CollectionsKt.drop(arrayList4, 1)) {
            arrayList5.add("-R");
            arrayList5.add(file16.getAbsolutePath());
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (function1 != null) {
            function1.invoke(new a("link", "0s", 0, 0));
        }
        hk hkVar = hk.a;
        if (hkVar.i()) {
            return new b(false, null, null, arrayList, "aapt2 已取消", 6, null);
        }
        if (function1 != null) {
            timer = new Timer("aapt2-link-progress", true);
            timer.scheduleAtFixedRate(new c(jCurrentTimeMillis, function1), 1000L, 1000L);
        }
        try {
            hk.a aVarL = hk.l(hkVar, context, arrayList5, null, 240L, 4, null);
            if (timer != null) {
                timer.cancel();
            }
            if (aVarL.a() || hkVar.i()) {
                return new b(false, null, null, arrayList, "aapt2 已取消", 6, null);
            }
            if (!aVarL.b() || !file13.isFile()) {
                return new b(false, null, null, arrayList, "aapt2 link 失败:\n" + aVarL.d(), 6, null);
            }
            if (!StringsKt.isBlank(aVarL.c())) {
                arrayList.add(StringsKt.trim(aVarL.c()).toString());
            }
            arrayList.add("aapt2 link OK -> " + file13.getName() + " (" + (file13.length() / 1024) + " KB)");
            return new b(true, file13, file14, arrayList, null, 16, null);
        } catch (Throwable th) {
            if (timer != null) {
                timer.cancel();
            }
            throw th;
        }
    }

    public final String d(File file) {
        int iNextIndex;
        String name;
        String absolutePath = file.getAbsolutePath();
        absolutePath.getClass();
        List listSplit$default = StringsKt.split$default(StringsKt.replace$default(absolutePath, '\\', '/', false, 4, (Object) null), new char[]{'/'}, false, 0, 6, (Object) null);
        ListIterator listIterator = listSplit$default.listIterator(listSplit$default.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                iNextIndex = -1;
                break;
            }
            if (StringsKt.equals((String) listIterator.previous(), "exploded", true)) {
                iNextIndex = listIterator.nextIndex();
                break;
            }
        }
        if (iNextIndex >= 2) {
            String str = (String) CollectionsKt.getOrNull(listSplit$default, iNextIndex - 1);
            if (str == null) {
                str = "";
            }
            String str2 = (String) CollectionsKt.getOrNull(listSplit$default, iNextIndex - 2);
            String str3 = str2 != null ? str2 : "";
            if (str3.length() > 0) {
                if (str.length() <= 0) {
                    return str3;
                }
                return str3 + ":" + str;
            }
        }
        File parentFile = file.getParentFile();
        if (parentFile != null && (name = parentFile.getName()) != null) {
            if (StringsKt.isBlank(name) || Intrinsics.areEqual(name, "exploded")) {
                name = null;
            }
            if (name != null) {
                return name;
            }
        }
        String name2 = file.getName();
        name2.getClass();
        return name2;
    }

    public static final class b {
        public final boolean a;
        public final File b;
        public final File c;
        public final List d;
        public final String e;

        public /* synthetic */ b(boolean z, File file, File file2, List list, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i & 2) != 0 ? null : file, (i & 4) != 0 ? null : file2, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? null : str);
        }

        public final File a() {
            return this.b;
        }

        public final String b() {
            return this.e;
        }

        public final File c() {
            return this.c;
        }

        public final boolean d() {
            return this.a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.a == bVar.a && Intrinsics.areEqual(this.b, bVar.b) && Intrinsics.areEqual(this.c, bVar.c) && Intrinsics.areEqual(this.d, bVar.d) && Intrinsics.areEqual(this.e, bVar.e);
        }

        public int hashCode() {
            int iHashCode = Boolean.hashCode(this.a) * 31;
            File file = this.b;
            int iHashCode2 = (iHashCode + (file == null ? 0 : file.hashCode())) * 31;
            File file2 = this.c;
            int iHashCode3 = (((iHashCode2 + (file2 == null ? 0 : file2.hashCode())) * 31) + this.d.hashCode()) * 31;
            String str = this.e;
            return iHashCode3 + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Result(success=" + this.a + ", apFile=" + this.b + ", genSrcDir=" + this.c + ", logs=" + this.d + ", error=" + this.e + ")";
        }

        public b(boolean z, File file, File file2, List list, String str) {
            list.getClass();
            this.a = z;
            this.b = file;
            this.c = file2;
            this.d = list;
            this.e = str;
        }
    }
}
