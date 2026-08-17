package defpackage;

import android.content.Context;
import com.sun.jna.platform.win32.WinError;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class jl3 {
    public static final jl3 a = new jl3();

    public static Unit a(AtomicBoolean atomicBoolean, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Ref.ObjectRef objectRef3, Ref.IntRef intRef, Ref.ObjectRef objectRef4, Ref.IntRef intRef2, Ref.IntRef intRef3, Function1 function1, bl3.f fVar) {
        fVar.getClass();
        if (atomicBoolean.get()) {
            return Unit.INSTANCE;
        }
        if (Intrinsics.areEqual(fVar.g(), "resolve")) {
            objectRef.element = "resolve";
            objectRef2.element = fVar.c();
            objectRef3.element = "";
            intRef.element = 0;
            j(function1, intRef, objectRef2, objectRef, intRef2, intRef3, objectRef3, 0, false, false, null, WinError.ERROR_NETLOGON_NOT_STARTED, null);
            return Unit.INSTANCE;
        }
        String str = "";
        objectRef.element = "download";
        if (!Intrinsics.areEqual(fVar.c(), objectRef4.element)) {
            objectRef4.element = fVar.c();
            intRef.element = 0;
        }
        objectRef2.element = fVar.c();
        intRef2.element = fVar.d();
        if (fVar.h() > 0) {
            intRef3.element = RangesKt.coerceAtLeast(fVar.h(), intRef3.element);
        }
        intRef.element = Math.max(intRef.element, RangesKt.coerceIn(fVar.f(), 0, 100));
        if (fVar.b() > 0) {
            jl3 jl3Var = a;
            str = jl3Var.c(RangesKt.coerceAtLeast(fVar.a(), 0L)) + " / " + jl3Var.c(fVar.b());
        }
        objectRef3.element = str;
        j(function1, intRef, objectRef2, objectRef, intRef2, intRef3, objectRef3, null, false, false, null, WinError.ERROR_CANT_ACCESS_FILE, null);
        return Unit.INSTANCE;
    }

    public static Unit b(AtomicBoolean atomicBoolean, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Ref.ObjectRef objectRef3, Ref.IntRef intRef, Ref.ObjectRef objectRef4, Function1 function1, Ref.IntRef intRef2, Ref.IntRef intRef3, String str) {
        str.getClass();
        if (atomicBoolean.get()) {
            return Unit.INSTANCE;
        }
        if (StringsKt.startsWith$default(str, "Resolving ", false, 2, (Object) null)) {
            objectRef.element = "resolve";
            objectRef2.element = "解析依赖图…";
            objectRef3.element = "";
            intRef.element = 0;
            j(function1, intRef, objectRef2, objectRef, intRef2, intRef3, objectRef3, 0, false, false, null, WinError.ERROR_NETLOGON_NOT_STARTED, null);
        } else if (StringsKt.startsWith$default(str, "Graph resolved:", false, 2, (Object) null)) {
            objectRef.element = "resolve";
            objectRef2.element = "依赖图已展开，准备下载…";
            objectRef3.element = "";
            j(function1, intRef, objectRef2, objectRef, intRef2, intRef3, objectRef3, 0, false, false, null, WinError.ERROR_NETLOGON_NOT_STARTED, null);
        } else if (StringsKt.startsWith$default(str, "Downloading ", false, 2, (Object) null)) {
            String strTrimEnd = StringsKt.trimEnd(StringsKt.trim(StringsKt.removePrefix(str, "Downloading ")).toString(), new char[]{8230, '.', ' '});
            if (!Intrinsics.areEqual(objectRef.element, "download")) {
                objectRef.element = "resolve";
                objectRef2.element = "解析：" + strTrimEnd;
                objectRef3.element = "";
                intRef.element = 0;
                j(function1, intRef, objectRef2, objectRef, intRef2, intRef3, objectRef3, 0, false, false, null, WinError.ERROR_NETLOGON_NOT_STARTED, null);
            } else if (!Intrinsics.areEqual(strTrimEnd, objectRef4.element)) {
                objectRef4.element = strTrimEnd;
                intRef.element = 0;
                objectRef3.element = "";
                objectRef2.element = strTrimEnd;
                j(function1, intRef, objectRef2, objectRef, intRef2, intRef3, objectRef3, 0, false, false, null, WinError.ERROR_NETLOGON_NOT_STARTED, null);
            }
        } else if (StringsKt.startsWith$default(str, "· ", false, 2, (Object) null)) {
            objectRef.element = "resolve";
            objectRef2.element = "解析：" + StringsKt.trim(StringsKt.removePrefix(str, "· ")).toString();
            objectRef3.element = "";
            intRef.element = 0;
            j(function1, intRef, objectRef2, objectRef, intRef2, intRef3, objectRef3, 0, false, false, null, WinError.ERROR_NETLOGON_NOT_STARTED, null);
        }
        return Unit.INSTANCE;
    }

    public static final void i(Function1 function1, Ref.IntRef intRef, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Ref.IntRef intRef2, Ref.IntRef intRef3, Ref.ObjectRef objectRef3, Integer num, boolean z, boolean z2, String str) {
        int iCoerceAtMost;
        int iCoerceIn = RangesKt.coerceIn(num != null ? num.intValue() : intRef.element, 0, 100);
        String str2 = (String) objectRef.element;
        if (Intrinsics.areEqual(objectRef2.element, "download")) {
            int i = intRef2.element;
            iCoerceAtMost = RangesKt.coerceAtMost(i, RangesKt.coerceAtLeast(intRef3.element, i));
        } else {
            iCoerceAtMost = 0;
        }
        function1.invoke(new a(iCoerceIn, str2, iCoerceAtMost, Intrinsics.areEqual(objectRef2.element, "download") ? RangesKt.coerceAtLeast(intRef3.element, intRef2.element) : 0, (String) objectRef3.element, (String) objectRef2.element, z, z2, str));
    }

    public static /* synthetic */ void j(Function1 function1, Ref.IntRef intRef, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Ref.IntRef intRef2, Ref.IntRef intRef3, Ref.ObjectRef objectRef3, Integer num, boolean z, boolean z2, String str, int i, Object obj) {
        i(function1, intRef, objectRef, objectRef2, intRef2, intRef3, objectRef3, (i & 128) != 0 ? null : num, (i & 256) != 0 ? false : z, (i & 512) != 0 ? false : z2, (i & 1024) != 0 ? "" : str);
    }

    public final String c(long j) {
        if (j >= 1000000) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            return String.format(Locale.US, "%.1f MB", Arrays.copyOf(new Object[]{Double.valueOf(j / 1000000.0d)}, 1));
        }
        if (j >= 1000) {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            return String.format(Locale.US, "%.0f KB", Arrays.copyOf(new Object[]{Double.valueOf(j / 1000.0d)}, 1));
        }
        return j + " B";
    }

    public final File d(Context context) {
        context.getClass();
        return new File(context.getFilesDir(), "libcache");
    }

    public final bl3.l e(Context context, String str) {
        context.getClass();
        str.getClass();
        File fileF = f(context, str);
        File fileD = d(context);
        bl3 bl3Var = bl3.a;
        bl3.l lVarK = k(fileF, fileD, bl3Var.P(fileF, fileD));
        return lVarK.e() ? lVarK : k(fileF, fileD, bl3Var.O(fileF, fileD));
    }

    public final File f(Context context, String str) {
        context.getClass();
        str.getClass();
        return new File(context.getFilesDir(), "projects/" + str);
    }

    public final boolean g(File file) {
        File file2 = new File(file, "src");
        if (!file2.isDirectory()) {
            return false;
        }
        for (File file3 : FilesKt.walkTopDown(file2)) {
            if (file3.isFile() && StringsKt.equals(FilesKt.getExtension(file3), "kt", true)) {
                return true;
            }
        }
        return false;
    }

    public final boolean h(Context context, String str, final AtomicBoolean atomicBoolean, final Function1 function1) {
        Object obj;
        Ref.IntRef intRef;
        Ref.ObjectRef objectRef;
        context.getClass();
        str.getClass();
        atomicBoolean.getClass();
        function1.getClass();
        File fileF = f(context, str);
        File fileD = d(context);
        final Ref.IntRef intRef2 = new Ref.IntRef();
        final Ref.IntRef intRef3 = new Ref.IntRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = "准备中…";
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = "";
        final Ref.IntRef intRef4 = new Ref.IntRef();
        final Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
        objectRef4.element = "";
        final Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
        objectRef5.element = "resolve";
        Function1 function2 = new Function1() { // from class: hl3
            public final Object invoke(Object obj2) {
                return jl3.a(atomicBoolean, objectRef5, objectRef2, objectRef3, intRef4, objectRef4, intRef3, intRef2, function1, (bl3.f) obj2);
            }
        };
        Function1 function3 = new Function1() { // from class: il3
            public final Object invoke(Object obj2) {
                return jl3.b(atomicBoolean, objectRef5, objectRef2, objectRef3, intRef4, objectRef4, function1, intRef3, intRef2, (String) obj2);
            }
        };
        objectRef5.element = "resolve";
        j(function1, intRef4, objectRef2, objectRef5, intRef3, intRef2, objectRef3, 0, false, false, null, WinError.ERROR_NETLOGON_NOT_STARTED, null);
        if (atomicBoolean.get()) {
            i(function1, intRef4, objectRef2, objectRef5, intRef3, intRef2, objectRef3, Integer.valueOf(intRef4.element), true, true, "已取消");
            return false;
        }
        bl3 bl3Var = bl3.a;
        bl3.j jVarS = bl3Var.S(fileF, fileD, function3, function2);
        if (atomicBoolean.get()) {
            j(function1, intRef4, objectRef2, objectRef5, intRef3, intRef2, objectRef3, null, true, true, "已取消", 128, null);
            return false;
        }
        if (g(fileF)) {
            objectRef5.element = "download";
            objectRef2.element = "org.jetbrains.kotlin:kotlin-stdlib:2.4.0";
            objectRef4.element = "org.jetbrains.kotlin:kotlin-stdlib:2.4.0";
            intRef4.element = 0;
            objectRef3.element = "";
            obj = "download";
            j(function1, intRef, objectRef2, objectRef, intRef3, intRef2, objectRef3, 0, false, false, null, WinError.ERROR_NETLOGON_NOT_STARTED, null);
            bl3.j jVarT = bl3Var.T(CollectionsKt.listOf("org.jetbrains.kotlin:kotlin-stdlib:2.4.0"), fileD, function3, function2);
            if (!jVarT.d().isEmpty() && jVarS.d().isEmpty()) {
                String str2 = (String) CollectionsKt.firstOrNull(jVarT.d());
                if (str2 == null) {
                    intRef = intRef4;
                    objectRef = objectRef5;
                    str2 = "Kotlin 运行库下载失败";
                }
                intRef = intRef4;
                objectRef = objectRef5;
                i(function1, intRef, objectRef2, objectRef, intRef3, intRef2, objectRef3, 100, true, true, str2);
                return false;
            }
        } else {
            obj = "download";
            intRef = intRef4;
            objectRef = objectRef5;
        }
        intRef = intRef4;
        objectRef = objectRef5;
        intRef = intRef4;
        objectRef = objectRef5;
        List listD = jVarS.d();
        if (!(listD instanceof Collection) || !listD.isEmpty()) {
            Iterator it = listD.iterator();
            while (it.hasNext()) {
                if (!Intrinsics.areEqual((String) it.next(), "已取消")) {
                    Integer numValueOf = Integer.valueOf(intRef.element);
                    String str3 = (String) CollectionsKt.firstOrNull(jVarS.d());
                    if (str3 == null) {
                        str3 = "依赖下载失败";
                    }
                    i(function1, intRef, objectRef2, objectRef, intRef3, intRef2, objectRef3, numValueOf, true, true, str3);
                    return false;
                }
            }
        }
        objectRef.element = obj;
        intRef3.element = RangesKt.coerceAtLeast(intRef2.element, intRef3.element);
        objectRef2.element = "依赖已就绪";
        objectRef3.element = "";
        intRef.element = 100;
        i(function1, intRef, objectRef2, objectRef, intRef3, intRef2, objectRef3, 100, true, false, "依赖已就绪，可以运行了");
        return true;
    }

    public final bl3.l k(File file, File file2, bl3.l lVar) {
        if (g(file)) {
            File file3 = new File(file2, "org/jetbrains/kotlin/kotlin-stdlib/2.4.0/kotlin-stdlib-2.4.0.jar");
            if (!file3.isFile() || file3.length() <= 0) {
                return bl3.l.b(lVar, lVar.c() + 1, lVar.d() + 1, null, 4, null);
            }
        }
        return lVar;
    }
}
