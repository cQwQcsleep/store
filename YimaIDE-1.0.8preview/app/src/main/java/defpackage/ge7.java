package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.android.tools.r8.D8;
import com.android.tools.r8.D8Command;
import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.OutputMode;
import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.eclipse.jdt.core.compiler.CategorizedProblem;
import org.eclipse.jdt.internal.compiler.ClassFile;
import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.internal.compiler.Compiler;
import org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies;
import org.eclipse.jdt.internal.compiler.ICompilerRequestor;
import org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.eclipse.jdt.internal.compiler.env.INameEnvironment;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.jdt.internal.compiler.problem.DefaultProblemFactory;
import org.json.JSONException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ge7 {
    public static Thread f;
    public static volatile boolean g;
    public static volatile boolean h;
    public static volatile Function1 j;
    public static volatile boolean k;
    public static Timer l;
    public static volatile long m;
    public static volatile long o;
    public static volatile int p;
    public static volatile boolean q;
    public static volatile String r;
    public static final ge7 a = new ge7();
    public static final StringBuilder b = new StringBuilder();
    public static final ArrayList c = new ArrayList();
    public static final MutableStateFlow d = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
    public static final MutableStateFlow e = StateFlowKt.MutableStateFlow(Boolean.FALSE);
    public static final Object i = new Object();
    public static volatile String n = "";
    public static final Handler s = new Handler(Looper.getMainLooper());
    public static final Set t = SetsKt.setOf(new String[]{"CrashApp", "CrashActivity", "DiagLogger", "CodeSlotLoader", "CodeSlotProvider", "HotSwapLoader", "HotSwapDexProvider"});
    public static final Set u = SetsKt.setOf(new String[]{"CrashApp.java", "CrashActivity.java"});
    public static final int v = 8;

    public enum a {
        FULL,
        VERIFY,
        CODE_SLOT_FAST,
        CODE_SLOT_FULL_SOURCES;

        public static final /* synthetic */ EnumEntries g = EnumEntriesKt.enumEntries(b());
    }

    public static final class b {
        public final File a;
        public final File b;
        public final int c;
        public final int d;
        public final String e;
        public final List f;
        public final List g;
        public final List h;

        public b(File file, File file2, int i, int i2, String str, List list, List list2, List list3) {
            file.getClass();
            file2.getClass();
            str.getClass();
            list.getClass();
            list2.getClass();
            list3.getClass();
            this.a = file;
            this.b = file2;
            this.c = i;
            this.d = i2;
            this.e = str;
            this.f = list;
            this.g = list2;
            this.h = list3;
        }

        public final File a() {
            return this.b;
        }

        public final List b() {
            return this.g;
        }

        public final File c() {
            return this.a;
        }

        public final List d() {
            return this.f;
        }

        public final int e() {
            return this.c;
        }

        public final String f() {
            return this.e;
        }

        public final List g() {
            return this.h;
        }
    }

    public static final class c {
        public final boolean a;
        public final String b;

        public c(boolean z, String str) {
            str.getClass();
            this.a = z;
            this.b = str;
        }

        public final String a() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return this.a == cVar.a && Intrinsics.areEqual(this.b, cVar.b);
        }

        public int hashCode() {
            return (Boolean.hashCode(this.a) * 31) + this.b.hashCode();
        }

        public String toString() {
            return "CompileResult(success=" + this.a + ", report=" + this.b + ")";
        }
    }

    public static final class d {
        public final List a;
        public final List b;
        public final boolean c;

        public d(List list, List list2, boolean z) {
            list.getClass();
            list2.getClass();
            this.a = list;
            this.b = list2;
            this.c = z;
        }

        public final List a() {
            return this.b;
        }

        public final List b() {
            return this.a;
        }

        public final boolean c() {
            return this.c;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof d)) {
                return false;
            }
            d dVar = (d) obj;
            return Intrinsics.areEqual(this.a, dVar.a) && Intrinsics.areEqual(this.b, dVar.b) && this.c == dVar.c;
        }

        public int hashCode() {
            return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + Boolean.hashCode(this.c);
        }

        public String toString() {
            return "IncClasspathCache(jars=" + this.a + ", composePlugins=" + this.b + ", usesCompose=" + this.c + ")";
        }
    }

    public static final class e implements DiagnosticsHandler {
        public void error(Diagnostic diagnostic) {
            diagnostic.getClass();
            ge7.a.l0("[Error] D8(container): " + diagnostic.getDiagnosticMessage() + "\n");
        }
    }

    public static final class f extends TimerTask {
        public final /* synthetic */ Ref.IntRef b;
        public final /* synthetic */ long c;
        public final /* synthetic */ char[] d;
        public final /* synthetic */ Ref.IntRef e;
        public final /* synthetic */ int f;

        public f(Ref.IntRef intRef, long j, char[] cArr, Ref.IntRef intRef2, int i) {
            this.b = intRef;
            this.c = j;
            this.d = cArr;
            this.e = intRef2;
            this.f = i;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (this.b.element > 0) {
                cancel();
            } else {
                ge7.a.g1(ge7.h0(this.c, this.d, this.e, this.f), false);
            }
        }
    }

    public static final class g implements DiagnosticsHandler {
        public void error(Diagnostic diagnostic) {
            diagnostic.getClass();
            ge7.a.l0("[Error] D8(slot): " + diagnostic.getDiagnosticMessage() + "\n");
        }
    }

    public static final class h implements DiagnosticsHandler {
        public void error(Diagnostic diagnostic) {
            diagnostic.getClass();
            ge7.a.l0("[Error] D8: " + diagnostic.getDiagnosticMessage() + "\n");
        }
    }

    public static final class i extends TimerTask {
        public final /* synthetic */ long b;
        public final /* synthetic */ char[] c;
        public final /* synthetic */ Ref.IntRef d;
        public final /* synthetic */ int e;

        public i(long j, char[] cArr, Ref.IntRef intRef, int i) {
            this.b = j;
            this.c = cArr;
            this.d = intRef;
            this.e = i;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            ge7.a.g1(ge7.T0(this.b, this.c, this.d, this.e), false);
        }
    }

    public static final /* synthetic */ class k extends FunctionReferenceImpl implements Function1 {
        public k(Object obj) {
            super(1, obj, ge7.class, "onAapt2Progress", "onAapt2Progress(Lcom/yimaide/app/data/java/Aapt2ResourceCompiler$Progress;)V", 0);
        }

        public final void b(ck.a aVar) {
            aVar.getClass();
            ((ge7) ((CallableReference) this).receiver).G0(aVar);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            b((ck.a) obj);
            return Unit.INSTANCE;
        }
    }

    public static final /* synthetic */ class l extends FunctionReferenceImpl implements Function1 {
        public l(Object obj) {
            super(1, obj, ge7.class, "onAapt2Progress", "onAapt2Progress(Lcom/yimaide/app/data/java/Aapt2ResourceCompiler$Progress;)V", 0);
        }

        public final void b(ck.a aVar) {
            aVar.getClass();
            ((ge7) ((CallableReference) this).receiver).G0(aVar);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            b((ck.a) obj);
            return Unit.INSTANCE;
        }
    }

    public static final class m extends TimerTask {
        public final /* synthetic */ char[] b;
        public final /* synthetic */ Ref.IntRef c;

        public m(char[] cArr, Ref.IntRef intRef) {
            this.b = cArr;
            this.c = intRef;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            ge7.a.g1(ge7.Z0(this.b, this.c), false);
        }
    }

    public static Unit A(String str) {
        str.getClass();
        return Unit.INSTANCE;
    }

    public static void B(CountDownLatch countDownLatch) {
        countDownLatch.countDown();
    }

    public static /* synthetic */ void B0(ge7 ge7Var, Context context, String str, String str2, String str3, String str4, int i2, int i3, Function1 function1, hb0.c cVar, boolean z, boolean z2, Function3 function3, int i4, Object obj) {
        ge7Var.A0(context, str, str2, str3, str4, i2, i3, function1, (i4 & 256) != 0 ? hb0.c.a.a : cVar, (i4 & 512) != 0 ? false : z, (i4 & 1024) != 0 ? false : z2, function3);
    }

    public static boolean C(File file, List list, File file2) {
        file2.getClass();
        String path = FilesKt.relativeTo(file2, file).getPath();
        path.getClass();
        String strReplace$default = StringsKt.replace$default(path, '\\', '/', false, 4, (Object) null);
        List list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return true;
        }
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            if (StringsKt.startsWith$default(strReplace$default, (String) it.next(), false, 2, (Object) null)) {
                return false;
            }
        }
        return true;
    }

    public static final void C0(Function1 function1, boolean z, Context context, boolean z2, String str, String str2, String str3, int i2, int i3, Function3 function3, hb0.c cVar) {
        ge7 ge7Var;
        cc3.a aVar;
        int i4;
        File file;
        File file2;
        List listB;
        String str4;
        cc3 cc3Var;
        cc3.a aVar2;
        cc3.a aVar3;
        Context context2 = context;
        String str5 = "projects/";
        long jCurrentTimeMillis = System.currentTimeMillis();
        ge7 ge7Var2 = a;
        if (ge7Var2.y0()) {
            Boolean bool = Boolean.FALSE;
            function1.invoke(bool);
            ge7Var2.c1();
            hk.a.g(context2);
            ge7Var2.f1(new File(context2.getFilesDir(), "libcache"));
            synchronized (i) {
                g = false;
                e.setValue(bool);
                f = null;
                h = false;
                Unit unit = Unit.INSTANCE;
            }
            return;
        }
        if (z && !ge7Var2.o0(context2)) {
            Boolean bool2 = Boolean.FALSE;
            function1.invoke(bool2);
            ge7Var2.c1();
            hk.a.g(context2);
            ge7Var2.f1(new File(context2.getFilesDir(), "libcache"));
            synchronized (i) {
                g = false;
                e.setValue(bool2);
                f = null;
                h = false;
                Unit unit2 = Unit.INSTANCE;
            }
            return;
        }
        if (z2) {
            cc3 cc3Var2 = cc3.a;
            cc3.a aVarM = cc3Var2.m(context2, str, str2);
            ge7Var2.l0("[DebugHost] 当前构建的是调试基座，无法脱离IDE独立运行，调试无问题后可点击正式打包进行分发。\n");
            String strA = dc3.a.a(str);
            if (cc3Var2.g(context2, str, aVarM)) {
                xb3 xb3Var = xb3.a;
                long jI = xb3Var.i(context2, str);
                if (jI <= 0 || !xb3Var.e(context2, strA)) {
                    str4 = strA;
                    cc3Var = cc3Var2;
                    aVar2 = aVarM;
                    if (xb3Var.b(context2, str4)) {
                        ge7Var2.l0("[DebugHost] 无变更，直接启动\n");
                        function1.invoke(Boolean.valueOf(ge7Var2.D0(context2, str)));
                        ge7Var2.c1();
                        hk.a.g(context2);
                        ge7Var2.f1(new File(context2.getFilesDir(), "libcache"));
                        synchronized (i) {
                            g = false;
                            e.setValue(Boolean.FALSE);
                            f = null;
                            h = false;
                            Unit unit3 = Unit.INSTANCE;
                        }
                        return;
                    }
                } else {
                    str4 = strA;
                    if (xb3.g(xb3Var, context2, str, null, 4, null)) {
                        ge7Var2.l0("[DebugHost] 无变更，直接启动\n");
                        function1.invoke(Boolean.valueOf(ge7Var2.D0(context2, str)));
                        ge7Var2.c1();
                        hk.a.g(context2);
                        ge7Var2.f1(new File(context2.getFilesDir(), "libcache"));
                        synchronized (i) {
                            g = false;
                            e.setValue(Boolean.FALSE);
                            f = null;
                            h = false;
                            Unit unit4 = Unit.INSTANCE;
                        }
                        return;
                    }
                    ge7Var2.l0("[DebugHost] 代码槽与缓存不一致，正在重推…\n");
                    kb3 kb3Var = kb3.a;
                    kb3Var.e();
                    kb3Var.d();
                    String strG = kb3Var.g();
                    kb3Var.q();
                    if (xb3Var.d(context2, new File(context2.getFilesDir(), "projects/" + str + "/build/code-slot-dex"), str4)) {
                        cc3Var2.j(context2, str, aVarM, jI, kb3Var.g());
                        ge7Var2.l0("[Run] 已启动调试基座\n");
                        function1.invoke(Boolean.TRUE);
                        ge7Var2.c1();
                        hk.a.g(context2);
                        ge7Var2.f1(new File(context2.getFilesDir(), "libcache"));
                        synchronized (i) {
                            g = false;
                            e.setValue(Boolean.FALSE);
                            f = null;
                            h = false;
                            Unit unit5 = Unit.INSTANCE;
                        }
                        return;
                    }
                    aVar2 = aVarM;
                    cc3Var = cc3Var2;
                    kb3Var.v(strG);
                    if (ge7Var2.D0(context2, str)) {
                        function1.invoke(Boolean.TRUE);
                        ge7Var2.c1();
                        hk.a.g(context2);
                        ge7Var2.f1(new File(context2.getFilesDir(), "libcache"));
                        synchronized (i) {
                            g = false;
                            e.setValue(Boolean.FALSE);
                            f = null;
                            h = false;
                            Unit unit6 = Unit.INSTANCE;
                        }
                        return;
                    }
                    cc3Var.x(context2, str);
                    ge7Var2.l0("[DebugHost] 重推代码槽失败，改为完整安装…\n");
                }
                if (ge7Var2.D0(context2, str)) {
                    function1.invoke(Boolean.TRUE);
                    ge7Var2.c1();
                    hk.a.g(context2);
                    ge7Var2.f1(new File(context2.getFilesDir(), "libcache"));
                    synchronized (i) {
                        g = false;
                        e.setValue(Boolean.FALSE);
                        f = null;
                        h = false;
                        Unit unit7 = Unit.INSTANCE;
                    }
                    return;
                }
            } else {
                str4 = strA;
                str5 = "projects/";
                jCurrentTimeMillis = jCurrentTimeMillis;
                cc3Var = cc3Var2;
                aVar2 = aVarM;
            }
            if (!cc3Var.h(context2, str, aVar2)) {
                ge7Var = ge7Var2;
                aVar3 = aVar2;
            } else if (xb3.a.e(context2, str4)) {
                ge7Var2.l0("[DebugHost] 正在进行增量编译…\n");
                kb3 kb3Var2 = kb3.a;
                kb3Var2.e();
                kb3Var2.d();
                kb3Var2.q();
                ge7Var = ge7Var2;
                aVar3 = aVar2;
                if (ge7Var.U0(context2, str, str3, str2, i2, i3, aVar3)) {
                    ge7Var.l0("增量编译完成 (总耗时" + String.format("%.1f", Arrays.copyOf(new Object[]{Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d)}, 1)) + "秒)\n");
                    ge7Var.l0("[Run] 已启动调试基座\n");
                    function1.invoke(Boolean.TRUE);
                    ge7Var.c1();
                    hk.a.g(context2);
                    ge7Var.f1(new File(context2.getFilesDir(), "libcache"));
                    synchronized (i) {
                        g = false;
                        e.setValue(Boolean.FALSE);
                        f = null;
                        h = false;
                        Unit unit8 = Unit.INSTANCE;
                    }
                    return;
                }
                ge7Var.l0("[DebugHost] 增量编译失败，改为完整安装…\n");
            } else {
                ge7Var2.l0("[DebugHost] 增量更新不可用，改为完整安装…\n");
                ge7Var = ge7Var2;
                aVar3 = aVar2;
            }
            if (cc3Var.f(context2, str, aVar3)) {
                ge7Var.l0("[DebugHost] 架构改动，正在重新生成基座，请在构建结束后重新安装基座！\n");
                kb3 kb3Var3 = kb3.a;
                kb3Var3.e();
                kb3Var3.d();
                kb3Var3.q();
                File fileV0 = ge7Var.V0(context2, str, str3, str2, i2, i3, aVar3);
                if (fileV0 != null) {
                    ge7Var.l0("重打包完成 (总耗时" + String.format("%.1f", Arrays.copyOf(new Object[]{Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d)}, 1)) + "秒)\n");
                    function1.invoke(function3.invoke(context2, fileV0, ge7Var.K0(new File(context2.getFilesDir(), "projects/" + str), "versionName", "1.0")));
                    ge7Var.c1();
                    hk.a.g(context2);
                    ge7Var.f1(new File(context2.getFilesDir(), "libcache"));
                    synchronized (i) {
                        g = false;
                        e.setValue(Boolean.FALSE);
                        f = null;
                        h = false;
                        Unit unit9 = Unit.INSTANCE;
                    }
                    return;
                }
                ge7Var.l0("[DebugHost] 重打包失败，改为完整安装…\n");
            }
            if (cc3Var.y(context2, str)) {
                ge7Var.l0("[DebugHost] 架构改动，正在重新生成基座，请在构建结束后重新安装基座！\n");
            } else {
                ge7Var.l0("[DebugHost] 正在构建调试基座\n");
            }
            aVar = aVar3;
        } else {
            ge7Var = ge7Var2;
            str5 = "projects/";
            jCurrentTimeMillis = jCurrentTimeMillis;
            aVar = null;
        }
        kb3 kb3Var4 = kb3.a;
        kb3Var4.e();
        kb3Var4.d();
        kb3Var4.q();
        b bVarR0 = R0(ge7Var, context2, str, str3, i2, i3, z, z2, str2, null, 256, null);
        try {
            if (bVarR0 == null) {
                Boolean bool3 = Boolean.FALSE;
                function1.invoke(bool3);
                ge7Var.c1();
                hk.a.g(context2);
                ge7Var.f1(new File(context2.getFilesDir(), "libcache"));
                synchronized (i) {
                    g = false;
                    e.setValue(bool3);
                    f = null;
                    h = false;
                    Unit unit10 = Unit.INSTANCE;
                }
                return;
            }
            if (ge7Var.y0()) {
                Boolean bool4 = Boolean.FALSE;
                function1.invoke(bool4);
                ge7Var.c1();
                hk.a.g(context2);
                ge7Var.f1(new File(context2.getFilesDir(), "libcache"));
                synchronized (i) {
                    g = false;
                    e.setValue(bool4);
                    f = null;
                    h = false;
                    Unit unit11 = Unit.INSTANCE;
                }
                return;
            }
            File file3 = new File(new File(context2.getFilesDir(), str5 + str), "build");
            File file4 = new File(file3, "dex");
            file4.mkdirs();
            File file5 = new File(file3, "apk");
            File fileC = bVarR0.c();
            int iE = bVarR0.e();
            List listD = bVarR0.d();
            Y0(ge7Var, "DEX 转换", "DEX转换", false, 4, null);
            if (z2) {
                File file6 = new File(file3, "shell-dex");
                File file7 = new File(file3, "code-slot-dex");
                ge7 ge7Var3 = ge7Var;
                file = file6;
                boolean zS = ge7Var3.S(fileC, listD, file, file7, iE, new File(context2.getFilesDir(), "libcache"));
                ge7Var = ge7Var3;
                i4 = iE;
                if (!zS) {
                    ge7Var.l0("\n[Error] 容器 DEX 转换失败\n");
                    Boolean bool5 = Boolean.FALSE;
                    function1.invoke(bool5);
                    ge7Var.c1();
                    hk.a.g(context2);
                    ge7Var.f1(new File(context2.getFilesDir(), "libcache"));
                    synchronized (i) {
                        g = false;
                        e.setValue(bool5);
                        f = null;
                        h = false;
                        Unit unit12 = Unit.INSTANCE;
                    }
                    return;
                }
                file2 = file7;
            } else {
                i4 = iE;
                if (!ge7Var.j0(fileC, file4, listD, i4)) {
                    ge7Var.l0("\n[Error] DEX 转换失败\n");
                    Boolean bool6 = Boolean.FALSE;
                    function1.invoke(bool6);
                    ge7Var.c1();
                    hk.a.g(context2);
                    ge7Var.f1(new File(context2.getFilesDir(), "libcache"));
                    synchronized (i) {
                        g = false;
                        e.setValue(bool6);
                        f = null;
                        h = false;
                        Unit unit13 = Unit.INSTANCE;
                    }
                    return;
                }
                file = file4;
                file2 = null;
            }
            ge7Var.b1("DEX转换成功");
            if (ge7Var.y0()) {
                Boolean bool7 = Boolean.FALSE;
                function1.invoke(bool7);
                ge7Var.c1();
                hk.a.g(context2);
                ge7Var.f1(new File(context2.getFilesDir(), "libcache"));
                synchronized (i) {
                    g = false;
                    e.setValue(bool7);
                    f = null;
                    h = false;
                    Unit unit14 = Unit.INSTANCE;
                }
                return;
            }
            int i5 = i4;
            Y0(ge7Var, "打包签名 APK", "打包签名APK", false, 4, null);
            if (!z2 || file2 == null) {
                listB = bVarR0.b();
            } else {
                File file8 = new File(file3, "code-slot-assets");
                File file9 = new File(file8, "yima_codeslot");
                FilesKt.deleteRecursively(file8);
                file9.mkdirs();
                File[] fileArrListFiles = file2.listFiles(new FileFilter() { // from class: yd7
                    @Override // java.io.FileFilter
                    public final boolean accept(File file10) {
                        return ge7.b(file10);
                    }
                });
                if (fileArrListFiles != null) {
                    for (File file10 : fileArrListFiles) {
                        file10.getClass();
                        FilesKt.copyTo$default(file10, new File(file9, file10.getName()), true, 0, 4, (Object) null);
                    }
                    Unit unit15 = Unit.INSTANCE;
                }
                listB = CollectionsKt.plus(CollectionsKt.listOf(file8), bVarR0.b());
            }
            hb0.b bVarL = hb0.a.l(context2, bVarR0.a(), file, file5, i5, listB, bVarR0.g(), cVar);
            if (bVarL.c() && bVarL.a() != null) {
                ge7 ge7Var4 = a;
                ge7Var4.b1("APK打包签名成功");
                if (z2) {
                    cc3.a.k(context2);
                }
                ge7Var4.l0("构建完成 (总耗时" + String.format("%.1f", Arrays.copyOf(new Object[]{Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d)}, 1)) + "秒)\n");
                if (z2) {
                    ge7Var4.H0(context2, str, str2, aVar);
                }
                function1.invoke(function3.invoke(context2, bVarL.a(), bVarR0.f()));
                ge7Var4.c1();
                hk.a.g(context2);
                ge7Var4.f1(new File(context2.getFilesDir(), "libcache"));
                synchronized (i) {
                    g = false;
                    e.setValue(Boolean.FALSE);
                    f = null;
                    h = false;
                    Unit unit16 = Unit.INSTANCE;
                }
                return;
            }
            ge7 ge7Var5 = a;
            ge7Var5.l0("\n[Error] APK 打包失败：" + bVarL.b() + "\n");
            Boolean bool8 = Boolean.FALSE;
            function1.invoke(bool8);
            ge7Var5.c1();
            hk.a.g(context2);
            ge7Var5.f1(new File(context2.getFilesDir(), "libcache"));
            synchronized (i) {
                g = false;
                e.setValue(bool8);
                f = null;
                h = false;
                Unit unit17 = Unit.INSTANCE;
            }
        } catch (Throwable th) {
            th = th;
            context2 = context2;
            try {
                Log.e("JavaCompilerEngine", "compileAndInstallApk failed", th);
                ge7 ge7Var6 = a;
                ge7Var6.l0("\n[Error] " + th.getClass().getSimpleName() + ": " + th.getMessage() + "\n");
                Boolean bool9 = Boolean.FALSE;
                function1.invoke(bool9);
                ge7Var6.c1();
                hk.a.g(context2);
                ge7Var6.f1(new File(context2.getFilesDir(), "libcache"));
                synchronized (i) {
                    g = false;
                    e.setValue(bool9);
                    f = null;
                    h = false;
                    Unit unit18 = Unit.INSTANCE;
                }
            } catch (Throwable th2) {
                ge7 ge7Var7 = a;
                ge7Var7.c1();
                hk.a.g(context2);
                ge7Var7.f1(new File(context2.getFilesDir(), "libcache"));
                synchronized (i) {
                    g = false;
                    e.setValue(Boolean.FALSE);
                    f = null;
                    h = false;
                    Unit unit19 = Unit.INSTANCE;
                    throw th2;
                }
            }
        }
    }

    public static boolean D(File file) {
        return file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "dex");
    }

    public static boolean E(File file) {
        return file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "dex");
    }

    public static Unit F(bl3.f fVar) {
        fVar.getClass();
        return Unit.INSTANCE;
    }

    public static boolean G(File file) {
        return file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "dex");
    }

    public static boolean H(File file) {
        file.getClass();
        return file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "kt") && !a.z0(file);
    }

    public static void I(String str, int i2, CountDownLatch countDownLatch) {
        try {
            a.R(str, i2);
        } finally {
            countDownLatch.countDown();
        }
    }

    public static final void O0(Ref.BooleanRef booleanRef) {
        if (booleanRef.element) {
            return;
        }
        booleanRef.element = true;
        ge7 ge7Var = a;
        ge7Var.X();
        ge7Var.l0("准备依赖\n");
    }

    public static /* synthetic */ b R0(ge7 ge7Var, Context context, String str, String str2, int i2, int i3, boolean z, boolean z2, String str3, a aVar, int i4, Object obj) {
        if ((i4 & 32) != 0) {
            z = false;
        }
        if ((i4 & 64) != 0) {
            z2 = false;
        }
        if ((i4 & 128) != 0) {
            str3 = "";
        }
        if ((i4 & 256) != 0) {
            aVar = a.FULL;
        }
        return ge7Var.Q0(context, str, str2, i2, i3, z, z2, str3, aVar);
    }

    public static final File T(int i2, File file) {
        return a.E0(file, i2);
    }

    public static final String T0(long j2, char[] cArr, Ref.IntRef intRef, int i2) {
        long jCurrentTimeMillis = (System.currentTimeMillis() - j2) / 1000;
        int i3 = intRef.element;
        char c2 = cArr[i3 % cArr.length];
        intRef.element = i3 + 1;
        return "Kotlin · " + i2 + " 个文件 · " + c2 + " " + jCurrentTimeMillis + "s";
    }

    public static final boolean U(int i2, File file) {
        return a.x0(file, i2);
    }

    public static /* synthetic */ void Y0(ge7 ge7Var, String str, String str2, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        ge7Var.X0(str, str2, z);
    }

    public static final String Z0(char[] cArr, Ref.IntRef intRef) {
        long jCurrentTimeMillis = (System.currentTimeMillis() - m) / 1000;
        int i2 = intRef.element;
        char c2 = cArr[i2 % cArr.length];
        intRef.element = i2 + 1;
        return "正在" + n + "… " + c2 + " " + jCurrentTimeMillis + "s";
    }

    public static boolean a(File file) {
        file.getClass();
        return file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "class");
    }

    public static boolean b(File file) {
        if (file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "dex")) {
            String name = file.getName();
            name.getClass();
            if (StringsKt.startsWith$default(name, "classes", false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ void b0(ge7 ge7Var, Context context, String str, String str2, String str3, String str4, int i2, int i3, hb0.c cVar, Function1 function1, int i4, Object obj) {
        String str5;
        if ((i4 & 16) != 0) {
            str5 = str2 + ".MainActivity";
        } else {
            str5 = str4;
        }
        ge7Var.a0(context, str, str2, str3, str5, (i4 & 32) != 0 ? 26 : i2, (i4 & 64) != 0 ? 34 : i3, (i4 & 128) != 0 ? hb0.c.a.a : cVar, (i4 & 256) != 0 ? new Function1() { // from class: vd7
            public final Object invoke(Object obj2) {
                return ge7.k(((Boolean) obj2).booleanValue());
            }
        } : function1);
    }

    public static boolean c(File file) {
        file.getClass();
        return Intrinsics.areEqual(FilesKt.getExtension(file), "class");
    }

    public static boolean d(File file) {
        file.getClass();
        return Intrinsics.areEqual(FilesKt.getExtension(file), "class");
    }

    public static /* synthetic */ void d0(ge7 ge7Var, Context context, String str, String str2, String str3, String str4, int i2, int i3, Function1 function1, int i4, Object obj) {
        String str5;
        if ((i4 & 16) != 0) {
            str5 = str2 + ".MainActivity";
        } else {
            str5 = str4;
        }
        ge7Var.c0(context, str, str2, str3, str5, (i4 & 32) != 0 ? 26 : i2, (i4 & 64) != 0 ? 34 : i3, (i4 & 128) != 0 ? new Function1() { // from class: sd7
            public final Object invoke(Object obj2) {
                return ge7.t(((Boolean) obj2).booleanValue());
            }
        } : function1);
    }

    public static Unit e(StringBuilder sb, String str) {
        str.getClass();
        synchronized (sb) {
            sb.append(str);
        }
        return Unit.INSTANCE;
    }

    public static boolean f(File file, List list, File file2) {
        file2.getClass();
        String path = FilesKt.relativeTo(file2, file).getPath();
        List<String> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return true;
        }
        for (String str : list2) {
            path.getClass();
            if (StringsKt.startsWith$default(path, str, false, 2, (Object) null)) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ c f0(ge7 ge7Var, Context context, String str, String str2, int i2, int i3, int i4, Object obj) {
        if ((i4 & 8) != 0) {
            i2 = 26;
        }
        int i5 = i2;
        if ((i4 & 16) != 0) {
            i3 = 34;
        }
        return ge7Var.e0(context, str, str2, i5, i3);
    }

    public static boolean g(File file) {
        return file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "dex");
    }

    public static void h(File file, e eVar, int i2, ConcurrentLinkedQueue concurrentLinkedQueue) {
        try {
            File fileT = T(i2, file);
            FilesKt.deleteRecursively(fileT);
            fileT.mkdirs();
            D8.run(D8Command.builder(eVar).setOutput(fileT.toPath(), OutputMode.DexIndexed).setMinApiLevel(i2).setIntermediate(true).setGlobalSyntheticsConsumer(new lq3.a(fileT, "lib")).addProgramFiles(new Path[]{file.toPath()}).build());
            FilesKt.writeText$default(new File(fileT, ".ok"), "ok", (Charset) null, 2, (Object) null);
        } catch (Throwable th) {
            concurrentLinkedQueue.add(th);
        }
    }

    public static final String h0(long j2, char[] cArr, Ref.IntRef intRef, int i2) {
        long jCurrentTimeMillis = (System.currentTimeMillis() - j2) / 1000;
        int i3 = intRef.element;
        char c2 = cArr[i3 % cArr.length];
        intRef.element = i3 + 1;
        return "Java · " + i2 + " · " + c2 + " " + jCurrentTimeMillis + "s";
    }

    public static boolean i(String str, hb0.c cVar, Context context, File file, String str2) throws JSONException {
        context.getClass();
        file.getClass();
        str2.getClass();
        sbc sbcVar = sbc.a;
        kwa.b bVarB = sbcVar.b();
        if (bVarB == null) {
            a.l0("[Error] 缺少打包授权，请重新点击正式打包\n");
            return false;
        }
        ubc ubcVar = ubc.a;
        if (!ubcVar.c(bVarB.a())) {
            a.m0(0);
            return false;
        }
        ubc.a aVarB = ubcVar.b();
        if (aVarB == null) {
            a.m0(1);
            return false;
        }
        String strD = nj0.a.d(context);
        if (strD == null || StringsKt.isBlank(strD)) {
            a.l0("[Error] 登录已失效，无法完成正式打包导出\n");
            return false;
        }
        kwa kwaVar = kwa.a;
        String strC = kwaVar.c(context);
        kwa.a aVarB2 = kwaVar.b(strD, aVarB.c(), aVarB.b());
        if (aVarB2 instanceof kwa.a.b) {
            kwa.a aVarA = kwaVar.a(strD, bVarB, strC);
            if (aVarA instanceof kwa.a.b) {
                hb0.a aVarW = hb0.a.w(context, file, str, str2, cVar);
                sbcVar.a();
                if (aVarW == null) {
                    a.m0(3);
                    return false;
                }
                String str3 = cVar instanceof hb0.c.b ? "含 APK 与签名密钥库" : "含 APK（当前为调试签名，请查看包内说明）";
                a.l0("[Export] 已保存到「下载 / Yima IDE」：" + aVarW.a() + "（" + str3 + "，" + (aVarW.b() / 1024) + " KB）\n");
                return true;
            }
            if (!(aVarA instanceof kwa.a.C0009a)) {
                bu8.a();
                return false;
            }
            kwa.a.C0009a c0009a = (kwa.a.C0009a) aVarA;
            a.l0("[Error] 打包授权核销失败：" + c0009a.b() + "\n");
            if (c0009a.a() == 2004 || c0009a.a() == 2002) {
                sbcVar.a();
                return false;
            }
        } else {
            if (!(aVarB2 instanceof kwa.a.C0009a)) {
                bu8.a();
                return false;
            }
            a.m0(2);
            kwa.a.C0009a c0009a2 = (kwa.a.C0009a) aVarB2;
            if (c0009a2.a() == 2004 || c0009a2.a() == 2002) {
                sbcVar.a();
                return false;
            }
        }
        return false;
    }

    public static CharSequence j(File file) {
        file.getClass();
        String absolutePath = file.getAbsolutePath();
        absolutePath.getClass();
        return absolutePath;
    }

    public static Unit k(boolean z) {
        return Unit.INSTANCE;
    }

    public static CharSequence l(File file) {
        file.getClass();
        String absolutePath = file.getAbsolutePath();
        absolutePath.getClass();
        return absolutePath;
    }

    public static Unit m(String str) {
        str.getClass();
        return Unit.INSTANCE;
    }

    public static void n(Ref.IntRef intRef, Timer timer, HashMap map, int i2, Ref.BooleanRef booleanRef, File file, CompilationResult compilationResult) {
        String name;
        int i3 = intRef.element;
        boolean z = i3 == 0;
        intRef.element = i3 + 1;
        if (z) {
            timer.cancel();
        }
        char[] cArr = compilationResult.fileName;
        String str = cArr != null ? new String(cArr) : null;
        if (str == null || StringsKt.isBlank(str)) {
            name = "…";
        } else {
            File file2 = new File(str);
            name = (String) map.get(file2.getAbsolutePath());
            if (name == null && (name = (String) map.get(file2.getName())) == null) {
                name = file2.getName();
            }
        }
        a.g1("Java · " + RangesKt.coerceAtMost(intRef.element, i2) + PsuedoNames.PSEUDONAME_ROOT + i2 + " · " + name, false);
        CategorizedProblem[] categorizedProblemArr = compilationResult.problems;
        if (categorizedProblemArr == null) {
            categorizedProblemArr = new CategorizedProblem[0];
        }
        for (CategorizedProblem categorizedProblem : categorizedProblemArr) {
            if (categorizedProblem != null) {
                char[] originatingFileName = categorizedProblem.getOriginatingFileName();
                String str2 = originatingFileName != null ? new String(originatingFileName) : null;
                int sourceLineNumber = categorizedProblem.getSourceLineNumber();
                t92 t92Var = t92.a;
                int iA = t92Var.a(str2, categorizedProblem.getSourceStart());
                String message = categorizedProblem.getMessage();
                if (message == null) {
                    message = categorizedProblem.toString();
                }
                if (categorizedProblem.isError()) {
                    booleanRef.element = true;
                    a.l0(t92Var.b(message, str2, sourceLineNumber, iA) + "\n");
                } else if (categorizedProblem.isWarning()) {
                    a.l0(t92Var.k(message, str2, sourceLineNumber, iA) + "\n");
                }
            }
        }
        Iterator it = ArrayIteratorKt.iterator(compilationResult.getClassFiles());
        while (it.hasNext()) {
            ClassFile classFile = (ClassFile) it.next();
            char[] cArrFileName = classFile.fileName();
            cArrFileName.getClass();
            File file3 = new File(file, StringsKt.replace$default(new String(cArrFileName), '/', File.separatorChar, false, 4, (Object) null) + JavaClass.EXTENSION);
            File parentFile = file3.getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file3);
            try {
                fileOutputStream.write(classFile.getBytes());
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileOutputStream, th);
                    throw th2;
                }
            }
        }
    }

    public static boolean o(File file) {
        file.getClass();
        return Intrinsics.areEqual(FilesKt.getExtension(file), "class");
    }

    public static boolean p(File file) {
        return file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "dex");
    }

    public static boolean q(File file) {
        file.getClass();
        return file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "java") && !a.z0(file);
    }

    public static boolean r(File file) {
        return file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "dex");
    }

    public static void s(File file, int i2, h hVar, ConcurrentLinkedQueue concurrentLinkedQueue) {
        try {
            File fileE0 = a.E0(file, i2);
            FilesKt.deleteRecursively(fileE0);
            fileE0.mkdirs();
            D8.run(D8Command.builder(hVar).setOutput(fileE0.toPath(), OutputMode.DexIndexed).setMinApiLevel(i2).setIntermediate(true).setGlobalSyntheticsConsumer(new lq3.a(fileE0, "lib")).addProgramFiles(new Path[]{file.toPath()}).build());
            FilesKt.writeText$default(new File(fileE0, ".ok"), "ok", (Charset) null, 2, (Object) null);
        } catch (Throwable th) {
            concurrentLinkedQueue.add(th);
        }
    }

    public static Unit t(boolean z) {
        return Unit.INSTANCE;
    }

    public static boolean u(File file) {
        return file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "dex");
    }

    /* JADX WARN: Code duplicated, block: B:15:0x004b A[RETURN] */
    public static boolean v(File file, List list, File file2) {
        file2.getClass();
        String path = FilesKt.relativeTo(file2, file).getPath();
        path.getClass();
        String strReplace$default = StringsKt.replace$default(path, '\\', '/', false, 4, (Object) null);
        List list2 = list;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                if (StringsKt.startsWith$default(strReplace$default, (String) it.next(), false, 2, (Object) null)) {
                }
            }
            if (!a.w0(strReplace$default)) {
                return true;
            }
        } else if (!a.w0(strReplace$default)) {
            return true;
        }
        return false;
    }

    public static boolean x(String str, Context context, File file, String str2) {
        context.getClass();
        file.getClass();
        str2.getClass();
        hb0 hb0Var = hb0.a;
        if (!hb0Var.m(context)) {
            cc3.a.i();
            a.l0("[Error] 需要安装权限，请在设置中允许「安装未知应用」后重试\n");
            hb0Var.s(context);
            return false;
        }
        a.l0("[Install] 请确认安装调试基座（若误点取消，可点「重新构建」选择重装基座）\n");
        hb0Var.o(context, file);
        kb3.a.u(dc3.a.a(str), cc3.a.q(), file.length(), file.lastModified(), file);
        return true;
    }

    public static Unit y(Ref.BooleanRef booleanRef, bl3.f fVar) {
        String str;
        String str2;
        String str3;
        fVar.getClass();
        String strG = fVar.g();
        String str4 = "";
        if (Intrinsics.areEqual(strG, "resolve")) {
            String string = StringsKt.trim(fVar.c()).toString();
            if (StringsKt.isBlank(string)) {
                string = "解析依赖图…";
            }
            if (fVar.d() > 0) {
                str4 = " · " + fVar.d();
            }
            a.g1("解析 · " + string + str4, false);
        } else if (Intrinsics.areEqual(strG, "download")) {
            O0(booleanRef);
            String strSubstringBefore$default = StringsKt.substringBefore$default(fVar.c(), " ·", (String) null, 2, (Object) null);
            if (StringsKt.isBlank(strSubstringBefore$default)) {
                strSubstringBefore$default = fVar.c();
            }
            if (StringsKt.isBlank(strSubstringBefore$default)) {
                strSubstringBefore$default = "…";
            }
            if (fVar.h() > 0) {
                str = RangesKt.coerceAtMost(fVar.d(), fVar.h()) + PsuedoNames.PSEUDONAME_ROOT + fVar.h();
            } else {
                str = "";
            }
            if (StringsKt.contains$default(fVar.c(), "解压", false, 2, (Object) null)) {
                str3 = "解压 · " + str + " · " + strSubstringBefore$default;
            } else if (StringsKt.contains$default(fVar.c(), "查找", false, 2, (Object) null)) {
                str3 = "查找 · " + str + " · " + strSubstringBefore$default;
            } else if (StringsKt.contains$default(fVar.c(), "检查", false, 2, (Object) null)) {
                str3 = "检查 · " + str + " · " + strSubstringBefore$default;
            } else if (StringsKt.contains$default(fVar.c(), "连接", false, 2, (Object) null)) {
                str3 = "连接 · " + str + " · " + strSubstringBefore$default;
            } else if (fVar.e()) {
                str3 = "缓存 · " + str + " · " + strSubstringBefore$default;
            } else if (fVar.b() > 0) {
                ge7 ge7Var = a;
                String str5 = " · " + ge7Var.r0(RangesKt.coerceAtLeast(fVar.a(), 0L)) + PsuedoNames.PSEUDONAME_ROOT + ge7Var.r0(fVar.b());
                str3 = fVar.f() + "% · " + strSubstringBefore$default + str5 + (str.length() > 0 ? " · ".concat(str) : "");
            } else if (fVar.a() > 0 || fVar.b() >= 0) {
                if (fVar.a() > 0) {
                    str2 = " · " + a.r0(fVar.a());
                } else {
                    str2 = "";
                }
                str3 = "下载 · " + strSubstringBefore$default + str2 + (str.length() > 0 ? " · ".concat(str) : "");
            } else {
                str3 = "准备 · " + str + " · " + strSubstringBefore$default;
            }
            a.g1(str3, false);
        }
        return Unit.INSTANCE;
    }

    public static boolean z(File file) {
        if (file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "dex")) {
            String name = file.getName();
            name.getClass();
            if (StringsKt.startsWith$default(name, "classes", false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public final void A0(final Context context, final String str, final String str2, final String str3, String str4, final int i2, final int i3, final Function1 function1, final hb0.c cVar, final boolean z, final boolean z2, final Function3 function3) {
        synchronized (i) {
            if (g) {
                a.l0("[Error] 构建正在运行中，请等待当前构建结束（编译器无法被中途打断，需等本阶段跑完）\n");
                function1.invoke(Boolean.FALSE);
                return;
            }
            g = true;
            e.setValue(Boolean.TRUE);
            h = false;
            hk.a.h();
            Unit unit = Unit.INSTANCE;
            W();
            Thread thread = new Thread(new Runnable() { // from class: xd7
                @Override // java.lang.Runnable
                public final void run() {
                    ge7.C0(function1, z, context, z2, str, str3, str2, i2, i3, function3, cVar);
                }
            });
            thread.start();
            f = thread;
        }
    }

    public final boolean D0(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(dc3.a.a(str));
        if (launchIntentForPackage == null) {
            l0("[Error] 调试基座已安装但无启动入口，将在下次改为完整安装\n");
            cc3.a.x(context, str);
            return false;
        }
        launchIntentForPackage.addFlags(268435456);
        try {
            context.startActivity(launchIntentForPackage);
            l0("[Run] 已启动调试基座\n");
            return true;
        } catch (Exception e2) {
            l0("[Error] 启动调试基座失败：" + e2.getMessage() + "\n");
            cc3.a.x(context, str);
            return false;
        }
    }

    public final File E0(File file, int i2) {
        return new File(file.getParentFile(), FilesKt.getNameWithoutExtension(file) + ".dexcache-api" + i2);
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:47:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:50:0x0104  */
    /* JADX WARN: Code duplicated, block: B:53:0x011d A[LOOP:4: B:51:0x0117->B:53:0x011d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:57:0x0143  */
    /* JADX WARN: Code duplicated, block: B:60:0x0154  */
    /* JADX WARN: Code duplicated, block: B:83:0x0150 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:85:0x013d A[SYNTHETIC] */
    public final d F0(File file) {
        File file2;
        boolean z;
        File file3;
        List listEmptyList;
        ArrayList arrayList;
        Iterator it;
        String text$default;
        File file4 = new File(file, "inc-toolchain.txt");
        d dVar = null;
        if (file4.isFile()) {
            boolean z2 = true;
            if (Intrinsics.areEqual(StringsKt.trim(FilesKt.readText$default(file4, (Charset) null, 1, (Object) null)).toString(), n11.a.c())) {
                File file5 = new File(file, "inc-classpath.txt");
                if (!file5.isFile()) {
                    return null;
                }
                List lines$default = FilesKt.readLines$default(file5, (Charset) null, 1, (Object) null);
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(lines$default, 10));
                Iterator it2 = lines$default.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(StringsKt.trim((String) it2.next()).toString());
                }
                ArrayList arrayList3 = new ArrayList();
                for (Object obj : arrayList2) {
                    String str = (String) obj;
                    if (str.length() > 0 && !StringsKt.startsWith$default(str, "#", false, 2, (Object) null)) {
                        arrayList3.add(obj);
                    }
                }
                ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
                Iterator it3 = arrayList3.iterator();
                while (it3.hasNext()) {
                    arrayList4.add(new File((String) it3.next()));
                }
                if (!arrayList4.isEmpty()) {
                    if (arrayList4.isEmpty()) {
                        file2 = new File(file, "inc-flags.txt");
                        if (!file2.isFile()) {
                            file2 = null;
                        }
                        if (file2 == null && (text$default = FilesKt.readText$default(file2, (Charset) null, 1, (Object) null)) != null && StringsKt.contains$default(text$default, "compose=true", false, 2, (Object) null)) {
                            z = true;
                        } else {
                            z = false;
                        }
                        file3 = new File(file, "inc-compose-plugin.txt");
                        if (file3.isFile()) {
                            List lines$default2 = FilesKt.readLines$default(file3, (Charset) null, 1, (Object) null);
                            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(lines$default2, 10));
                            it = lines$default2.iterator();
                            while (it.hasNext()) {
                                arrayList.add(new File(StringsKt.trim((String) it.next()).toString()));
                            }
                            listEmptyList = new ArrayList();
                            for (Object obj2 : arrayList) {
                                if (((File) obj2).isFile()) {
                                    listEmptyList.add(obj2);
                                }
                            }
                        } else {
                            listEmptyList = CollectionsKt.emptyList();
                        }
                        if (!z && listEmptyList.isEmpty()) {
                            z2 = false;
                        }
                        dVar = new d(arrayList4, listEmptyList, z2);
                    } else {
                        Iterator it4 = arrayList4.iterator();
                        while (it4.hasNext()) {
                            if (!((File) it4.next()).isFile()) {
                            }
                        }
                        file2 = new File(file, "inc-flags.txt");
                        if (!file2.isFile()) {
                            file2 = null;
                        }
                        if (file2 == null) {
                            z = false;
                        } else {
                            z = false;
                        }
                        file3 = new File(file, "inc-compose-plugin.txt");
                        if (file3.isFile()) {
                            List lines$default3 = FilesKt.readLines$default(file3, (Charset) null, 1, (Object) null);
                            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(lines$default3, 10));
                            it = lines$default3.iterator();
                            while (it.hasNext()) {
                                arrayList.add(new File(StringsKt.trim((String) it.next()).toString()));
                            }
                            listEmptyList = new ArrayList();
                            while (r1.hasNext()) {
                                if (((File) obj2).isFile()) {
                                    listEmptyList.add(obj2);
                                }
                            }
                        } else {
                            listEmptyList = CollectionsKt.emptyList();
                        }
                        if (!z) {
                            z2 = false;
                        }
                        dVar = new d(arrayList4, listEmptyList, z2);
                    }
                }
            }
        }
        return dVar;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x007f  */
    /* JADX WARN: Code duplicated, block: B:25:0x0089  */
    public final void G0(ck.a aVar) {
        String strA;
        String str;
        String strC = aVar.c();
        int iHashCode = strC.hashCode();
        if (iHashCode != 3321850) {
            if (iHashCode != 130625071) {
                if (iHashCode == 950491699 && strC.equals("compile")) {
                    if (aVar.d() > 0) {
                        str = RangesKt.coerceAtMost(aVar.b(), aVar.d()) + PsuedoNames.PSEUDONAME_ROOT + aVar.d();
                    } else {
                        str = "";
                    }
                    strA = "编译 · " + str + " · " + aVar.a();
                } else {
                    strA = aVar.a();
                    if (StringsKt.isBlank(strA)) {
                        strA = "正在编译资源…";
                    }
                }
            } else if (strC.equals("manifest")) {
                strA = "合并清单…";
            } else {
                strA = aVar.a();
                if (StringsKt.isBlank(strA)) {
                    strA = "正在编译资源…";
                }
            }
        } else if (strC.equals("link")) {
            strA = "链接 · " + aVar.a();
        } else {
            strA = aVar.a();
            if (StringsKt.isBlank(strA)) {
                strA = "正在编译资源…";
            }
        }
        g1(strA, false);
    }

    public final void H0(Context context, String str, String str2, cc3.a aVar) {
        if (aVar == null) {
            aVar = cc3.a.m(context, str, str2);
        }
        cc3.a.H(str, aVar);
    }

    public final File I0(File file, File file2, File file3) {
        String strRemoveSuffix;
        String path = FilesKt.relativeTo(file, file2).getPath();
        path.getClass();
        String strReplace$default = StringsKt.replace$default(path, '\\', '/', false, 4, (Object) null);
        if (StringsKt.endsWith$default(strReplace$default, ".java", false, 2, (Object) null)) {
            strRemoveSuffix = StringsKt.removeSuffix(strReplace$default, ".java");
        } else {
            if (!StringsKt.endsWith$default(strReplace$default, ".kt", false, 2, (Object) null)) {
                return null;
            }
            strRemoveSuffix = StringsKt.removeSuffix(strReplace$default, ".kt");
        }
        return new File(file3, strRemoveSuffix + JavaClass.EXTENSION);
    }

    public final int J0(File file, String str, int i2) {
        List groupValues;
        String str2;
        Integer intOrNull;
        File file2 = new File(file, "build.gradle");
        if (file2.isFile()) {
            try {
                MatchResult matchResultFind$default = Regex.find$default(new Regex("\\b" + str + "\\b\\s*=?\\s*(\\d+)"), FilesKt.readText$default(file2, (Charset) null, 1, (Object) null), 0, 2, (Object) null);
                if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null && (str2 = (String) groupValues.get(1)) != null && (intOrNull = StringsKt.toIntOrNull(str2)) != null) {
                    return intOrNull.intValue();
                }
            } catch (Throwable unused) {
            }
        }
        return i2;
    }

    public final String K0(File file, String str, String str2) {
        List groupValues;
        String str3;
        File file2 = new File(file, "build.gradle");
        if (file2.isFile()) {
            try {
                MatchResult matchResultFind$default = Regex.find$default(new Regex("\\b" + str + "\\b\\s*=?\\s*[\"']([^\"']+)[\"']"), FilesKt.readText$default(file2, (Charset) null, 1, (Object) null), 0, 2, (Object) null);
                if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null && (str3 = (String) groupValues.get(1)) != null) {
                    return str3;
                }
            } catch (Throwable unused) {
            }
        }
        return str2;
    }

    public final void L0(File file, String str) {
        File file2 = new File(file, StringsKt.replace$default(str, '.', '/', false, 4, (Object) null) + "/DiagLogger.java");
        File parentFile = file2.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        j1(file2, StringsKt.trimIndent("\n            package " + str + ";\n\n            import android.content.Context;\n\n            /**\n             * 正式打包空实现：调试版回流逻辑已剥离；埋点调用在此为 no-op。\n             * 下次「运行」调试时 IDE 会自动恢复完整 DiagLogger。\n             */\n            public final class DiagLogger {\n                private DiagLogger() { }\n                public static void init(Context ctx) { }\n                public static void d(String tag, String msg) { }\n                public static void i(String tag, String msg) { }\n                public static void w(String tag, String msg) { }\n                public static void e(String tag, String msg) { }\n            }\n        "));
    }

    public final void M0() {
        a1();
        synchronized (i) {
            a.W();
            kb3 kb3Var = kb3.a;
            kb3Var.e();
            kb3Var.d();
            kb3Var.f();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final bl3.j N0(File file, File file2) {
        c1();
        m = System.currentTimeMillis();
        o = 0L;
        l0("解析依赖\n");
        boolean z = false;
        g1("解析依赖图…", false);
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        bl3.j jVarS = bl3.a.S(file, file2, new Function1() { // from class: gd7
            public final Object invoke(Object obj) {
                return ge7.m((String) obj);
            }
        }, new Function1() { // from class: hd7
            public final Object invoke(Object obj) {
                return ge7.y(booleanRef, (bl3.f) obj);
            }
        });
        o = 0L;
        X();
        if (jVarS.d().isEmpty()) {
            b1("依赖就绪");
            return jVarS;
        }
        StringBuilder sb = b;
        synchronized (sb) {
            if (sb.length() > 0 && StringsKt.last(sb) != '\n') {
                z = true;
            }
        }
        if (z) {
            l0("\n");
        }
        return jVarS;
    }

    public final void P(String str) {
        str.getClass();
        if (str.length() > 65536) {
            str = StringsKt.take(str, 65536) + "\n（已截断）\n";
        }
        l0(str);
    }

    public final boolean P0(Context context, String str) {
        context.getClass();
        str.getClass();
        if (((Boolean) t0().getValue()).booleanValue()) {
            l0("[Install] 构建进行中，请稍后再试\n");
            return false;
        }
        kb3 kb3Var = kb3.a;
        kb3Var.r();
        String strM = kb3Var.m();
        File file = strM != null ? new File(strM) : null;
        if (file == null || !file.isFile()) {
            l0("[Error] 没有可重试的安装包（可能已被新构建清理），请重新构建\n");
            kb3Var.f();
            cc3.a.i();
            return false;
        }
        hb0 hb0Var = hb0.a;
        if (!hb0Var.m(context)) {
            l0("[Error] 需要安装权限，请在设置中允许「安装未知应用」后重试\n");
            hb0Var.s(context);
            return false;
        }
        l0("[Install] 重新弹出安装确认（无需重新构建）\n");
        hb0Var.o(context, file);
        Long lValueOf = Long.valueOf(kb3Var.l());
        Long l2 = lValueOf.longValue() >= 0 ? lValueOf : null;
        kb3Var.u(dc3.a.a(str), l2 != null ? l2.longValue() : cc3.a.q(), file.length(), file.lastModified(), file);
        return true;
    }

    public final void Q(String str) {
        List listSplit$default = StringsKt.split$default(str, new String[]{"\n"}, false, 0, 6, (Object) null);
        ArrayList arrayList = c;
        if (arrayList.isEmpty()) {
            arrayList.add("");
        }
        int size = arrayList.size() - 1;
        Object objLast = CollectionsKt.last(arrayList);
        Object obj = listSplit$default.get(0);
        StringBuilder sb = new StringBuilder();
        sb.append(objLast);
        sb.append(obj);
        arrayList.set(size, sb.toString());
        int size2 = listSplit$default.size();
        for (int i2 = 1; i2 < size2; i2++) {
            c.add(listSplit$default.get(i2));
        }
    }

    public final b Q0(Context context, String str, String str2, int i2, int i3, boolean z, boolean z2, String str3, a aVar) {
        try {
            return S0(context, str, str2, i2, i3, z, z2, str3, aVar);
        } finally {
            c1();
        }
    }

    public final void R(String str, int i2) {
        if (i2 == p && q) {
            i1(str);
        }
    }

    public final boolean S(final File file, List list, File file2, File file3, final int i2, File file4) {
        try {
            final List listListOf = CollectionsKt.listOf(new String[]{"java/", "javax/", "android/", "kotlin/", "dalvik/", "org/w3c/", "org/xml/", "org/json/"});
            List list2 = SequencesKt.toList(SequencesKt.filter(SequencesKt.filter(FilesKt.walkTopDown(file), new Function1() { // from class: td7
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(ge7.c((File) obj));
                }
            }), new Function1() { // from class: zd7
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(ge7.C(file, listListOf, (File) obj));
                }
            }));
            ArrayList arrayList = new ArrayList();
            for (Object obj : list2) {
                ge7 ge7Var = a;
                String path = FilesKt.relativeTo((File) obj, file).getPath();
                path.getClass();
                if (ge7Var.w0(StringsKt.replace$default(path, '\\', '/', false, 4, (Object) null))) {
                    arrayList.add(obj);
                }
            }
            List list3 = list2;
            List arrayList2 = new ArrayList();
            for (Object obj2 : list3) {
                ge7 ge7Var2 = a;
                String path2 = FilesKt.relativeTo((File) obj2, file).getPath();
                path2.getClass();
                if (!ge7Var2.w0(StringsKt.replace$default(path2, '\\', '/', false, 4, (Object) null))) {
                    arrayList2.add(obj2);
                }
            }
            if (arrayList.isEmpty()) {
                l0("[Error] 容器壳类缺失（CodeSlotLoader/CrashApp）\n");
                return false;
            }
            if (arrayList2.isEmpty()) {
                l0("[Error] 没有业务类可写入增量包\n");
                return false;
            }
            final e eVar = new e();
            ArrayList<File> arrayList3 = new ArrayList();
            for (Object obj3 : list) {
                if (!U(i2, (File) obj3)) {
                    arrayList3.add(obj3);
                }
            }
            if (!arrayList3.isEmpty()) {
                ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(RangesKt.coerceIn(Runtime.getRuntime().availableProcessors(), 1, 4));
                final ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
                try {
                    ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
                    for (final File file5 : arrayList3) {
                        arrayList4.add(executorServiceNewFixedThreadPool.submit(new Runnable() { // from class: ae7
                            @Override // java.lang.Runnable
                            public final void run() {
                                ge7.h(file5, eVar, i2, concurrentLinkedQueue);
                            }
                        }));
                    }
                    Iterator it = arrayList4.iterator();
                    while (it.hasNext()) {
                        ((Future) it.next()).get();
                    }
                    executorServiceNewFixedThreadPool.shutdown();
                    Throwable th = (Throwable) concurrentLinkedQueue.peek();
                    if (th != null) {
                        throw th;
                    }
                } catch (Throwable th2) {
                    executorServiceNewFixedThreadPool.shutdown();
                    throw th2;
                }
            }
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                File fileT = T(i2, (File) it2.next());
                File[] fileArrListFiles = fileT.listFiles(new FileFilter() { // from class: be7
                    @Override // java.io.FileFilter
                    public final boolean accept(File file6) {
                        return ge7.G(file6);
                    }
                });
                if (fileArrListFiles != null) {
                    CollectionsKt.addAll(arrayList5, fileArrListFiles);
                }
                arrayList6.addAll(lq3.a.e(fileT));
            }
            File file6 = new File(file2.getParentFile(), "shell-inter");
            FilesKt.deleteRecursively(file6);
            file6.mkdirs();
            D8Command.Builder globalSyntheticsConsumer = D8Command.builder(eVar).setOutput(file6.toPath(), OutputMode.DexIndexed).setMinApiLevel(i2).setIntermediate(true).setGlobalSyntheticsConsumer(new lq3.a(file6, "shell"));
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                globalSyntheticsConsumer.addProgramFiles(new Path[]{((File) it3.next()).toPath()});
            }
            D8.run(globalSyntheticsConsumer.build());
            File[] fileArrListFiles2 = file6.listFiles(new FileFilter() { // from class: ce7
                @Override // java.io.FileFilter
                public final boolean accept(File file7) {
                    return ge7.D(file7);
                }
            });
            if (fileArrListFiles2 != null) {
                CollectionsKt.addAll(arrayList5, fileArrListFiles2);
            }
            arrayList6.addAll(lq3.a.e(file6));
            FilesKt.deleteRecursively(file2);
            file2.mkdirs();
            D8Command.Builder disableDesugaring = D8Command.builder(eVar).setOutput(file2.toPath(), OutputMode.DexIndexed).setMinApiLevel(i2).setIntermediate(false).setDisableDesugaring(true);
            Iterator it4 = arrayList5.iterator();
            while (it4.hasNext()) {
                disableDesugaring.addProgramFiles(new Path[]{((File) it4.next()).toPath()});
            }
            D8.run(disableDesugaring.build());
            if (!lq3.a.d(arrayList6, file2, i2, eVar)) {
                l0("[Error] 容器全局合成类 dex 生成失败\n");
                return false;
            }
            FilesKt.deleteRecursively(file3);
            file3.mkdirs();
            if (!k0(arrayList2, list, file4, file3, i2, eVar)) {
                return false;
            }
            File[] fileArrListFiles3 = file2.listFiles(new FileFilter() { // from class: de7
                @Override // java.io.FileFilter
                public final boolean accept(File file7) {
                    return ge7.u(file7);
                }
            });
            File[] fileArrListFiles4 = file3.listFiles(new FileFilter() { // from class: ee7
                @Override // java.io.FileFilter
                public final boolean accept(File file7) {
                    return ge7.E(file7);
                }
            });
            if (fileArrListFiles3 != null && fileArrListFiles3.length != 0 && fileArrListFiles4 != null && fileArrListFiles4.length != 0) {
                return true;
            }
            l0("[Error] 容器壳/增量 dex 未生成\n");
            return false;
        } catch (OutOfMemoryError e2) {
            Log.e("JavaCompilerEngine", "container d8 OOM", e2);
            l0("[Error] 容器 DEX 内存不足\n");
            System.gc();
            return false;
        } catch (Throwable th3) {
            Log.e("JavaCompilerEngine", "container d8 failed", th3);
            l0("[Error] 容器 D8 失败：" + th3.getClass().getSimpleName() + ": " + th3.getMessage() + "\n");
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x023f  */
    /* JADX WARN: Code duplicated, block: B:103:0x0245  */
    /* JADX WARN: Code duplicated, block: B:108:0x02ae A[Catch: all -> 0x02ea, TRY_LEAVE, TryCatch #7 {all -> 0x02ea, blocks: (B:106:0x0292, B:108:0x02ae), top: B:426:0x0292 }] */
    /* JADX WARN: Code duplicated, block: B:123:0x02df A[Catch: all -> 0x02dd, TryCatch #8 {all -> 0x02dd, blocks: (B:110:0x02b8, B:112:0x02be, B:114:0x02c6, B:116:0x02cc, B:118:0x02d4, B:127:0x02f0, B:123:0x02df), top: B:428:0x02b8 }] */
    /* JADX WARN: Code duplicated, block: B:126:0x02ee  */
    /* JADX WARN: Code duplicated, block: B:133:0x0306  */
    /* JADX WARN: Code duplicated, block: B:134:0x0355  */
    /* JADX WARN: Code duplicated, block: B:148:0x039c A[Catch: all -> 0x0395, TRY_LEAVE, TryCatch #1 {all -> 0x0395, blocks: (B:139:0x036c, B:143:0x0390, B:148:0x039c), top: B:415:0x036c }] */
    /* JADX WARN: Code duplicated, block: B:157:0x03b6 A[Catch: all -> 0x03b4, TryCatch #0 {all -> 0x03b4, blocks: (B:158:0x03c1, B:150:0x03a4, B:152:0x03aa, B:157:0x03b6), top: B:413:0x03a4 }] */
    /* JADX WARN: Code duplicated, block: B:163:0x03d4  */
    /* JADX WARN: Code duplicated, block: B:167:0x03e4  */
    /* JADX WARN: Code duplicated, block: B:170:0x042a A[Catch: all -> 0x043f, TryCatch #9 {all -> 0x043f, blocks: (B:168:0x03ef, B:170:0x042a, B:172:0x043a, B:175:0x0441, B:177:0x045b, B:179:0x046b, B:181:0x0471), top: B:430:0x03ef }] */
    /* JADX WARN: Code duplicated, block: B:172:0x043a A[Catch: all -> 0x043f, TryCatch #9 {all -> 0x043f, blocks: (B:168:0x03ef, B:170:0x042a, B:172:0x043a, B:175:0x0441, B:177:0x045b, B:179:0x046b, B:181:0x0471), top: B:430:0x03ef }] */
    /* JADX WARN: Code duplicated, block: B:177:0x045b A[Catch: all -> 0x043f, TryCatch #9 {all -> 0x043f, blocks: (B:168:0x03ef, B:170:0x042a, B:172:0x043a, B:175:0x0441, B:177:0x045b, B:179:0x046b, B:181:0x0471), top: B:430:0x03ef }] */
    /* JADX WARN: Code duplicated, block: B:179:0x046b A[Catch: all -> 0x043f, TryCatch #9 {all -> 0x043f, blocks: (B:168:0x03ef, B:170:0x042a, B:172:0x043a, B:175:0x0441, B:177:0x045b, B:179:0x046b, B:181:0x0471), top: B:430:0x03ef }] */
    /* JADX WARN: Code duplicated, block: B:180:0x0470  */
    /* JADX WARN: Code duplicated, block: B:187:0x0488  */
    /* JADX WARN: Code duplicated, block: B:189:0x0490  */
    /* JADX WARN: Code duplicated, block: B:190:0x049a  */
    /* JADX WARN: Code duplicated, block: B:192:0x049e  */
    /* JADX WARN: Code duplicated, block: B:194:0x04bd  */
    /* JADX WARN: Code duplicated, block: B:197:0x04c6  */
    /* JADX WARN: Code duplicated, block: B:199:0x04cb  */
    /* JADX WARN: Code duplicated, block: B:200:0x04d6  */
    /* JADX WARN: Code duplicated, block: B:204:0x04ec  */
    /* JADX WARN: Code duplicated, block: B:206:0x04ef  */
    /* JADX WARN: Code duplicated, block: B:209:0x0501  */
    /* JADX WARN: Code duplicated, block: B:211:0x050e  */
    /* JADX WARN: Code duplicated, block: B:213:0x0514  */
    /* JADX WARN: Code duplicated, block: B:216:0x0532  */
    /* JADX WARN: Code duplicated, block: B:218:0x0540  */
    /* JADX WARN: Code duplicated, block: B:220:0x0548  */
    /* JADX WARN: Code duplicated, block: B:223:0x0567  */
    /* JADX WARN: Code duplicated, block: B:234:0x05c4 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:236:0x05c6  */
    /* JADX WARN: Code duplicated, block: B:238:0x05d9  */
    /* JADX WARN: Code duplicated, block: B:240:0x0634  */
    /* JADX WARN: Code duplicated, block: B:243:0x064d  */
    /* JADX WARN: Code duplicated, block: B:251:0x066e  */
    /* JADX WARN: Code duplicated, block: B:254:0x0678  */
    /* JADX WARN: Code duplicated, block: B:258:0x0691  */
    /* JADX WARN: Code duplicated, block: B:261:0x069f A[LOOP:0: B:259:0x0699->B:261:0x069f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:263:0x06b5  */
    /* JADX WARN: Code duplicated, block: B:266:0x06dc A[LOOP:9: B:264:0x06d6->B:266:0x06dc, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:269:0x0705  */
    /* JADX WARN: Code duplicated, block: B:272:0x070d  */
    /* JADX WARN: Code duplicated, block: B:275:0x0722  */
    /* JADX WARN: Code duplicated, block: B:277:0x0740  */
    /* JADX WARN: Code duplicated, block: B:281:0x0755  */
    /* JADX WARN: Code duplicated, block: B:283:0x075b  */
    /* JADX WARN: Code duplicated, block: B:286:0x0765 A[LOOP:11: B:284:0x075f->B:286:0x0765, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:288:0x0772 A[LOOP:12: B:252:0x0672->B:288:0x0772, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:292:0x078d  */
    /* JADX WARN: Code duplicated, block: B:295:0x07af  */
    /* JADX WARN: Code duplicated, block: B:296:0x07d5  */
    /* JADX WARN: Code duplicated, block: B:297:0x07de  */
    /* JADX WARN: Code duplicated, block: B:300:0x07fc A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:301:0x07fd  */
    /* JADX WARN: Code duplicated, block: B:305:0x0807  */
    /* JADX WARN: Code duplicated, block: B:309:0x0833 A[LOOP:1: B:307:0x082d->B:309:0x0833, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:313:0x0861 A[LOOP:2: B:311:0x085b->B:313:0x0861, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:316:0x0874  */
    /* JADX WARN: Code duplicated, block: B:319:0x0891 A[LOOP:3: B:317:0x088b->B:319:0x0891, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:321:0x08a3  */
    /* JADX WARN: Code duplicated, block: B:324:0x08ab  */
    /* JADX WARN: Code duplicated, block: B:327:0x08bc  */
    /* JADX WARN: Code duplicated, block: B:338:0x08f1  */
    /* JADX WARN: Code duplicated, block: B:341:0x0902  */
    /* JADX WARN: Code duplicated, block: B:347:0x0929  */
    /* JADX WARN: Code duplicated, block: B:350:0x0933  */
    /* JADX WARN: Code duplicated, block: B:352:0x0941  */
    /* JADX WARN: Code duplicated, block: B:354:0x0950  */
    /* JADX WARN: Code duplicated, block: B:357:0x095c  */
    /* JADX WARN: Code duplicated, block: B:358:0x0982  */
    /* JADX WARN: Code duplicated, block: B:359:0x098a  */
    /* JADX WARN: Code duplicated, block: B:362:0x0998 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:363:0x0999  */
    /* JADX WARN: Code duplicated, block: B:369:0x09b2 A[Catch: all -> 0x09d9, TryCatch #6 {all -> 0x09d9, blocks: (B:367:0x09a1, B:369:0x09b2, B:370:0x09b6, B:372:0x09bc, B:375:0x09db, B:376:0x09ec, B:378:0x09f2, B:379:0x0a00), top: B:424:0x09a1 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:372:0x09bc A[Catch: all -> 0x09d9, LOOP:7: B:370:0x09b6->B:372:0x09bc, LOOP_END, TryCatch #6 {all -> 0x09d9, blocks: (B:367:0x09a1, B:369:0x09b2, B:370:0x09b6, B:372:0x09bc, B:375:0x09db, B:376:0x09ec, B:378:0x09f2, B:379:0x0a00), top: B:424:0x09a1 }] */
    /* JADX WARN: Code duplicated, block: B:378:0x09f2 A[Catch: all -> 0x09d9, LOOP:8: B:376:0x09ec->B:378:0x09f2, LOOP_END, TryCatch #6 {all -> 0x09d9, blocks: (B:367:0x09a1, B:369:0x09b2, B:370:0x09b6, B:372:0x09bc, B:375:0x09db, B:376:0x09ec, B:378:0x09f2, B:379:0x0a00), top: B:424:0x09a1 }] */
    /* JADX WARN: Code duplicated, block: B:398:0x0a52  */
    /* JADX WARN: Code duplicated, block: B:418:0x035d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:420:0x03d8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:422:0x0388 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:424:0x09a1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:437:0x08d5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:439:0x08b6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:455:0x0743 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:457:0x0665 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:458:0x068b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:459:0x0573 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:462:0x0561 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:95:0x0220  */
    /* JADX WARN: Code duplicated, block: B:98:0x0231 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:99:0x0232  */
    /* JADX WARN: Instruction removed from duplicated block: B:103:0x0245, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:266:0x06dc, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:295:0x07af, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:357:0x095c, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:372:0x09bc, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v26 */
    /* JADX WARN: Type inference failed for: r11v27 */
    public final b S0(Context context, String str, String str2, int i2, int i3, boolean z, boolean z2, String str3, a aVar) {
        String str4;
        rbc.a aVarK;
        String strK0;
        List listEmptyList;
        b bVar;
        bl3.j jVarN0;
        List listC;
        List listEmptyList2;
        bl3.j jVar;
        List list;
        boolean z3;
        List listB;
        File file;
        String str5;
        List list2;
        File file2;
        File file3;
        char c2;
        boolean z4;
        String str6;
        File file4;
        String str7;
        boolean z5;
        File file5;
        String text$default;
        boolean z6;
        Object obj;
        boolean z7;
        String strG;
        boolean z8;
        Regex regex;
        String str8;
        File file6;
        File file7;
        String text$default2;
        String strReplace;
        String text$default3;
        String strReplace2;
        int i4;
        File file8;
        File fileD1;
        File parentFile;
        String strA;
        List listCreateListBuilder;
        File file9;
        List listBuild;
        File fileN0;
        ArrayList arrayList;
        Iterator it;
        File file10;
        ck.b bVarA;
        Context context2;
        File file11;
        String str9;
        int i5;
        File file12;
        String str10;
        File fileC;
        String strB;
        File file13;
        String strF;
        List listZ;
        String str11;
        String str12;
        File file14;
        b bVar2;
        String str13;
        List listV;
        File file15;
        File file16;
        List list3;
        List listCreateListBuilder2;
        ArrayList arrayList2;
        List list4;
        File file17;
        Iterator it2;
        ArrayList arrayList3;
        Iterator it3;
        char c3;
        List listBuild2;
        List list5;
        List list6;
        boolean z9;
        b bVar3;
        List listD;
        ArrayList arrayList4;
        Iterator it4;
        Iterator it5;
        boolean z10;
        List listEmptyList3;
        ArrayList arrayList5;
        File file18;
        ArrayList arrayList6;
        ArrayList arrayList7;
        Iterator it6;
        List listCreateListBuilder3;
        File fileN1;
        List listBuild3;
        List list7;
        List listA;
        Iterator it7;
        int i6;
        List listEmptyList4;
        List arrayList8;
        List listCreateListBuilder4;
        bl3.j jVarU;
        Iterator it8;
        Iterator it9;
        Iterator it10;
        Object next;
        String name;
        Iterator it11;
        File file19;
        File parentFile2;
        File file20;
        Charset charset;
        String text$default4;
        String strA2;
        if (y0()) {
            return null;
        }
        File file21 = new File(context.getFilesDir(), "projects/" + str);
        File file22 = new File(file21, "src");
        File file23 = new File(file21, "build");
        File file24 = new File(file23, "classes");
        File file25 = new File(file23, "generated/aapt2-r");
        if (z) {
            str4 = str3;
            aVarK = rbc.a.k(context, str, file21, str4);
        } else {
            str4 = str3;
            aVarK = null;
        }
        if (aVarK != null && (strA2 = aVarK.a()) != null) {
            str4 = strA2;
        }
        int iJ0 = J0(file21, "minSdk", i2);
        int iJ1 = J0(file21, "targetSdk", i3);
        if (aVarK == null || (strK0 = aVarK.c()) == null) {
            strK0 = K0(file21, "versionName", "1.0");
        }
        String str14 = strK0;
        int iE = (!z || aVarK == null) ? (z2 && aVar == a.FULL) ? cc3.a.e(context) : J0(file21, "versionCode", 1) : aVarK.b();
        if (z) {
            rbc rbcVar = rbc.a;
            if (rbcVar.d(context, str)) {
                rbcVar.c(context, str);
                listEmptyList = CollectionsKt.listOf(rbcVar.h(context, str));
            } else {
                listEmptyList = CollectionsKt.emptyList();
            }
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        List list8 = listEmptyList;
        a aVar2 = a.CODE_SLOT_FAST;
        boolean z11 = aVar == aVar2 || aVar == a.CODE_SLOT_FULL_SOURCES;
        if (aVar == a.FULL) {
            kb3.a.f();
            FilesKt.deleteRecursively(file23);
            file24.mkdirs();
        } else if (aVar == a.VERIFY) {
            FilesKt.deleteRecursively(file24);
            file24.mkdirs();
            FilesKt.deleteRecursively(file25);
        } else {
            if (!v0(file24, file25) && aVar == aVar2) {
                return null;
            }
            if (aVar == a.CODE_SLOT_FULL_SOURCES) {
                if (file25.isDirectory()) {
                    for (File file26 : FilesKt.walkTopDown(file25)) {
                        if (file26.isFile() && Intrinsics.areEqual(file26.getName(), "R.java")) {
                            FilesKt.deleteRecursively(file24);
                        }
                    }
                }
                return null;
            }
            if (aVar == a.CODE_SLOT_FAST) {
                h1(file24);
            }
            file24.mkdirs();
        }
        File file27 = new File(context.getFilesDir(), "libcache");
        if (z11) {
            d dVarF0 = F0(file23);
            if (dVarF0 != null) {
                bVar = null;
                file23 = file23;
                file24 = file24;
                bl3.j jVar2 = new bl3.j(CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList());
                listB = dVarF0.b();
                List listA2 = dVarF0.a();
                boolean zC = dVarF0.c();
                jVar = jVar2;
                list = listA2;
                z3 = zC;
            } else {
                bVar = null;
                jVarN0 = N0(file21, file27);
                Iterator it12 = jVarN0.d().iterator();
                while (it12.hasNext()) {
                    l0("\n[Error] 依赖解析失败：" + ((String) it12.next()) + "\n");
                }
                if (!jVarN0.d().isEmpty()) {
                    return null;
                }
                listC = jVarN0.c();
                listEmptyList2 = CollectionsKt.emptyList();
            }
            if (!Z(file22).isEmpty()) {
                listB = n11.a.b(file27, listB, new Function1() { // from class: xc7
                    public final Object invoke(Object obj2) {
                        return ge7.A((String) obj2);
                    }
                });
            }
            if (y0()) {
                return bVar;
            }
            file = new File(file21, "AndroidManifest.xml");
            if (!file.isFile()) {
                l0("\n[Error] 项目缺少 AndroidManifest.xml\n");
                return bVar;
            }
            str5 = str4;
            list2 = listB;
            file2 = file23;
            file3 = file24;
            new File(file22, StringsKt.replace$default(str2, '.', '/', false, 4, (Object) null) + "/R.java").delete();
            try {
                Result.Companion companion = Result.Companion;
                str6 = str2;
                try {
                    file20 = new File(file22, StringsKt.replace$default(str6, '.', '/', false, 4, (Object) null) + "/CrashApp.java");
                    if (file20.isFile()) {
                        charset = bVar;
                        text$default4 = FilesKt.readText$default(file20, charset, 1, charset);
                        c2 = 2;
                        z4 = false;
                        try {
                            if (StringsKt.contains$default(text$default4, "CRASH_REPORT", false, 2, charset) || !StringsKt.contains$default(text$default4, "CodeSlotLoader", false, 2, charset) || !StringsKt.contains$default(text$default4, "code_slot/run_id", false, 2, charset) || !StringsKt.contains$default(text$default4, "DiagLogger", false, 2, charset) || !StringsKt.contains$default(text$default4, "DEBUG_HOST_HINT", false, 2, charset)) {
                                FilesKt.writeText$default(file20, ufb.a.I(str6), (Charset) null, 2, (Object) null);
                            }
                        } catch (Throwable th) {
                            th = th;
                            Result.Companion companion2 = Result.Companion;
                            Result.constructor-impl(ResultKt.createFailure(th));
                        }
                    } else {
                        c2 = 2;
                        z4 = false;
                    }
                    Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th2) {
                    th = th2;
                    c2 = 2;
                    z4 = false;
                }
            } catch (Throwable th3) {
                th = th3;
                c2 = 2;
                z4 = false;
                str6 = str2;
            }
            if (z2) {
                File file28 = new File(file2, "generated/codeslot-src");
                File file29 = new File(file28, StringsKt.replace$default(str6, '.', '/', false, 4, (Object) null));
                file29.mkdirs();
                File file30 = new File(file29, "CodeSlotLoader.java");
                o32 o32Var = o32.a;
                j1(file30, o32Var.a(str6));
                j1(new File(file29, "CodeSlotProvider.java"), o32Var.b(str6, dc3.a.a(str)));
                file4 = file28;
            } else {
                file4 = null;
            }
            if (z) {
                str7 = "/DiagLogger.java";
                z5 = false;
            } else {
                try {
                    str7 = "/DiagLogger.java";
                    try {
                        file5 = new File(file22, StringsKt.replace$default(str6, '.', '/', false, 4, (Object) null) + str7);
                        if (file5.isFile()) {
                            text$default = FilesKt.readText$default(file5, (Charset) null, 1, (Object) null);
                            z5 = false;
                            try {
                                if (StringsKt.contains$default(text$default, "code_slot/run_id", false, 2, (Object) null) || !StringsKt.contains$default(text$default, "void init(", false, 2, (Object) null)) {
                                    FilesKt.writeText$default(file5, ufb.a.R(str6), (Charset) null, 2, (Object) null);
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                Result.Companion companion3 = Result.Companion;
                                Result.constructor-impl(ResultKt.createFailure(th));
                            }
                        } else {
                            try {
                                FilesKt.writeText$default(file5, ufb.a.R(str6), (Charset) null, 2, (Object) null);
                                z5 = false;
                            } catch (Throwable th5) {
                                th = th5;
                                z5 = false;
                                Result.Companion companion4 = Result.Companion;
                                Result.constructor-impl(ResultKt.createFailure(th));
                            }
                        }
                        Result.constructor-impl(Unit.INSTANCE);
                    } catch (Throwable th6) {
                        th = th6;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    str7 = "/DiagLogger.java";
                }
            }
            if (z) {
                i4 = z5;
                L0(file22, str6);
                Unit unit = Unit.INSTANCE;
            } else {
                try {
                    strG = kb3.a.g();
                    if (strG.length() > 0) {
                        z8 = z5;
                        try {
                            String strReplace$default = StringsKt.replace$default(str6, '.', '/', false, 4, (Object) null);
                            regex = new Regex("private static String RUN_ID = \"[^\"]*\"\\s*;");
                            str8 = "private static String RUN_ID = \"" + strG + "\";";
                            file6 = new File(file22, strReplace$default + str7);
                            if (file6.isFile()) {
                                text$default3 = FilesKt.readText$default(file6, (Charset) null, 1, (Object) null);
                                strReplace2 = regex.replace(text$default3, str8);
                                if (!Intrinsics.areEqual(strReplace2, text$default3)) {
                                    FilesKt.writeText$default(file6, strReplace2, (Charset) null, 2, (Object) null);
                                }
                            }
                            file7 = new File(file22, strReplace$default + "/CrashApp.java");
                            z8 = z8;
                            if (file7.isFile()) {
                                text$default2 = FilesKt.readText$default(file7, (Charset) null, 1, (Object) null);
                                strReplace = regex.replace(text$default2, str8);
                                if (!Intrinsics.areEqual(strReplace, text$default2)) {
                                    z8 = z8;
                                    FilesKt.writeText$default(file7, strReplace, (Charset) null, 2, (Object) null);
                                    z8 = z8;
                                }
                            }
                        } catch (Throwable th8) {
                            th = th8;
                            z6 = z8;
                            Result.Companion companion5 = Result.Companion;
                            obj = Result.constructor-impl(ResultKt.createFailure(th));
                            z7 = z6;
                        }
                    } else {
                        z8 = z5;
                    }
                    z8 = z8;
                    obj = Result.constructor-impl(Unit.INSTANCE);
                    z7 = z8;
                } catch (Throwable th9) {
                    th = th9;
                    z6 = z5;
                }
                Result.box-impl(obj);
                i4 = z7;
            }
            if (z) {
                file8 = file2;
                fileD1 = d1(file, file8, str6);
            } else {
                file8 = file2;
                if (z2) {
                    dc3 dc3Var = dc3.a;
                    String strK = dc3Var.k(FilesKt.readText$default(file, (Charset) null, 1, (Object) null), str6, str5, dc3Var.a(str));
                    file = new File(file8, "AndroidManifest.debughost.xml");
                    parentFile = file.getParentFile();
                    if (parentFile != null) {
                        parentFile.mkdirs();
                    }
                    FilesKt.writeText$default(file, strK, (Charset) null, 2, (Object) null);
                }
                fileD1 = file;
            }
            if (z2) {
                strA = dc3.a.a(str);
            } else {
                strA = str6;
            }
            listCreateListBuilder = CollectionsKt.createListBuilder();
            file9 = new File(file21, "assets");
            if (!file9.isDirectory()) {
                file9 = null;
            }
            if (file9 != null) {
                listCreateListBuilder.add(file9);
            }
            listCreateListBuilder.addAll(jVar.b());
            listBuild = CollectionsKt.build(listCreateListBuilder);
            if (!z11) {
                file19 = new File(file8, "aapt2/out.ap_");
                if (!file19.isFile()) {
                    parentFile2 = file19.getParentFile();
                    if (parentFile2 != null) {
                        parentFile2.mkdirs();
                    }
                    FilesKt.writeBytes(file19, new byte[i4]);
                }
                file10 = file4;
                file12 = file8;
                file11 = file21;
                str9 = str14;
                i5 = iE;
                fileC = file25;
                context2 = context;
                file13 = file19;
                str10 = str6;
            } else {
                X0("编译资源", "编译资源", i4);
                fileN0 = n0(context);
                if (fileN0 == null) {
                    l0("\n[Error] assets/android.jar 缺失，aapt2 link 无法进行\n");
                    return null;
                }
                File file31 = file4;
                ck ckVar = ck.a;
                List listI = jVar.i();
                List listA3 = jVar.a();
                arrayList = new ArrayList();
                it = listA3.iterator();
                while (it.hasNext()) {
                    strF = ((bl3.i) it.next()).f();
                    if (strF != null) {
                        arrayList.add(strF);
                    }
                }
                file10 = file31;
                File file32 = file8;
                bVarA = ckVar.a(context, file21, file32, str2, iJ0, iJ1, iE, str14, fileN0, fileD1, listI, arrayList, jVar.h(), strA, list8, new k(this));
                context2 = context;
                file11 = file21;
                str9 = str14;
                i5 = iE;
                file12 = file32;
                str10 = str2;
                File fileA = bVarA.a();
                fileC = bVarA.c();
                if (bVarA.d() || fileA == null || fileC == null) {
                    if (!y0() || ((strB = bVarA.b()) != null && StringsKt.contains$default(strB, "已取消", false, 2, (Object) null))) {
                        return null;
                    }
                    l0("\n" + t92.c(t92.a, "资源编译失败", null, 0, 0, 14, null) + "\n");
                    String strB2 = bVarA.b();
                    if (strB2 != null) {
                        ge7 ge7Var = a;
                        ge7Var.l0(ge7Var.q0(strB2, file11) + "\n");
                        Unit unit2 = Unit.INSTANCE;
                    }
                    return null;
                }
                b1("资源编译成功");
                file13 = fileA;
            }
            if (y0()) {
                return null;
            }
            listZ = Z(file22);
            str11 = "androidx.compose";
            if (listZ.isEmpty()) {
                str12 = "androidx.compose";
                file14 = file13;
                bVar2 = null;
                str13 = "\n";
                listV = list2;
                file15 = file10;
                file16 = fileC;
                list3 = list;
            } else {
                X0("编译 Kotlin", "编译Kotlin", false);
                int size = listZ.size();
                file16 = fileC;
                long jCurrentTimeMillis = System.currentTimeMillis();
                char[] cArr = {10251, 10265, 10297, 10296, 10300, 10292, 10278, 10279, 10247, 10255};
                Ref.IntRef intRef = new Ref.IntRef();
                g1(T0(jCurrentTimeMillis, cArr, intRef, size), false);
                Timer timer = new Timer("kotlin-progress", true);
                timer.scheduleAtFixedRate(new i(jCurrentTimeMillis, cArr, intRef, size), 120L, 120L);
                l = timer;
                listCreateListBuilder3 = CollectionsKt.createListBuilder();
                fileN1 = a.n0(context2);
                if (fileN1 != null) {
                    listCreateListBuilder3.add(fileN1);
                }
                listV = list2;
                listCreateListBuilder3.addAll(listV);
                listBuild3 = CollectionsKt.build(listCreateListBuilder3);
                list7 = list;
                if (list7.isEmpty() || z3) {
                    i6 = 2;
                } else {
                    listA = jVar.a();
                    if ((listA instanceof Collection) || !listA.isEmpty()) {
                        it7 = listA.iterator();
                        while (true) {
                            if (it7.hasNext()) {
                                i6 = 2;
                                if (StringsKt.contains$default(((bl3.i) it7.next()).c(), "androidx.compose", false, 2, (Object) null)) {
                                }
                            }
                        }
                    }
                    str12 = "androidx.compose";
                    file14 = file13;
                    listEmptyList4 = CollectionsKt.emptyList();
                    arrayList8 = list;
                    listCreateListBuilder4 = CollectionsKt.createListBuilder();
                    listCreateListBuilder4.add(file22);
                    listCreateListBuilder4.add(file16);
                    file15 = file10;
                    if (file15 != null) {
                        listCreateListBuilder4.add(file15);
                        Unit unit3 = Unit.INSTANCE;
                    }
                    if (!db8.c(db8.a, listZ, CollectionsKt.build(listCreateListBuilder4), listBuild3, file3, "17", (String) null, listEmptyList4, new j(this), 32, (Object) null)) {
                        l0("\n" + t92.c(t92.a, "Kotlin 编译失败，请根据上方 e: 行定位修改", null, 0, 0, 14, null) + str13);
                        return null;
                    }
                    bVar2 = null;
                    b1("Kotlin编译成功");
                    list3 = arrayList8;
                }
                if (list7.isEmpty()) {
                    jVarU = bl3.U(bl3.a, CollectionsKt.listOf("org.jetbrains.kotlin:kotlin-compose-compiler-plugin:2.4.0"), file27, null, new Function1() { // from class: id7
                        public final Object invoke(Object obj2) {
                            return ge7.F((bl3.f) obj2);
                        }
                    }, 4, null);
                    it8 = jVarU.d().iterator();
                    while (it8.hasNext()) {
                        l0("\n[Error] Compose 插件解析失败：" + ((String) it8.next()) + "\n");
                    }
                    if (jVarU.c().isEmpty()) {
                        l0("\n[Error] Compose 编译器插件解析失败，无法编译 Compose\n");
                        return null;
                    }
                    List listC2 = jVarU.c();
                    arrayList8 = new ArrayList();
                    it9 = listC2.iterator();
                    while (it9.hasNext()) {
                        next = it9.next();
                        name = ((File) next).getName();
                        name.getClass();
                        Iterator it13 = it9;
                        String str15 = str11;
                        File file33 = file13;
                        if (StringsKt.startsWith$default(name, "kotlin-compose-compiler-plugin", false, i6, (Object) null)) {
                            arrayList8.add(next);
                        }
                        it9 = it13;
                        str11 = str15;
                        file13 = file33;
                    }
                    str12 = str11;
                    file14 = file13;
                    if (arrayList8.isEmpty()) {
                        l0("\n[Error] 未找到 Compose 编译器插件本体 jar\n");
                        return null;
                    }
                    it10 = arrayList8.iterator();
                    while (it10.hasNext()) {
                        ((File) it10.next()).setReadOnly();
                    }
                    listEmptyList4 = arrayList8;
                } else {
                    it11 = list.iterator();
                    while (it11.hasNext()) {
                        ((File) it11.next()).setReadOnly();
                    }
                    str12 = "androidx.compose";
                    file14 = file13;
                    arrayList8 = list;
                    listEmptyList4 = arrayList8;
                }
                listCreateListBuilder4 = CollectionsKt.createListBuilder();
                listCreateListBuilder4.add(file22);
                listCreateListBuilder4.add(file16);
                file15 = file10;
                if (file15 != null) {
                    listCreateListBuilder4.add(file15);
                    Unit unit4 = Unit.INSTANCE;
                }
                if (!db8.c(db8.a, listZ, CollectionsKt.build(listCreateListBuilder4), listBuild3, file3, "17", (String) null, listEmptyList4, new j(this), 32, (Object) null)) {
                    l0("\n" + t92.c(t92.a, "Kotlin 编译失败，请根据上方 e: 行定位修改", null, 0, 0, 14, null) + str13);
                    return null;
                }
                bVar2 = null;
                b1("Kotlin编译成功");
                list3 = arrayList8;
            }
            if (y0()) {
                return bVar2;
            }
            if (aVar != a.FULL || aVar == a.VERIFY) {
                listV = bl3.a.v(listV);
            }
            listCreateListBuilder2 = CollectionsKt.createListBuilder();
            List listY = a.Y(file22);
            list4 = listV;
            file17 = file12;
            arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listY, 10));
            it2 = listY.iterator();
            while (it2.hasNext()) {
                arrayList2.add(TuplesKt.to((File) it2.next(), file22));
            }
            listCreateListBuilder2.addAll(arrayList2);
            List listY2 = a.Y(file16);
            arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listY2, 10));
            it3 = listY2.iterator();
            while (it3.hasNext()) {
                arrayList3.add(TuplesKt.to((File) it3.next(), file16));
            }
            listCreateListBuilder2.addAll(arrayList3);
            if (file15 != null) {
                List listY3 = a.Y(file15);
                c3 = '\n';
                arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listY3, 10));
                it6 = listY3.iterator();
                while (it6.hasNext()) {
                    arrayList7.add(TuplesKt.to((File) it6.next(), file15));
                }
                listCreateListBuilder2.addAll(arrayList7);
            } else {
                c3 = '\n';
            }
            listBuild2 = CollectionsKt.build(listCreateListBuilder2);
            if (z) {
                arrayList6 = new ArrayList();
                for (Object obj2 : listBuild2) {
                    if (!u.contains(((File) ((Pair) obj2).getFirst()).getName())) {
                        arrayList6.add(obj2);
                    }
                }
                listBuild2 = arrayList6;
            }
            if (!listBuild2.isEmpty() && listZ.isEmpty()) {
                l0("[Error] 没有找到源代码文件\n");
                return null;
            }
            if (aVar == a.CODE_SLOT_FAST) {
                arrayList5 = new ArrayList();
                for (Object obj3 : listBuild2) {
                    Pair pair = (Pair) obj3;
                    File file34 = (File) pair.component1();
                    file18 = (File) pair.component2();
                    if (Intrinsics.areEqual(file18, file16) || !a.u0(file34, file18, file3)) {
                        arrayList5.add(obj3);
                    }
                }
                list5 = arrayList5;
            } else {
                list5 = listBuild2;
            }
            if (list5.isEmpty()) {
                list6 = list4;
                z9 = false;
                bVar3 = null;
            } else {
                X0("编译 Java", "编译Java", false);
                if (aVar == a.CODE_SLOT_FAST) {
                    listEmptyList3 = CollectionsKt.listOf(file3);
                } else {
                    listEmptyList3 = CollectionsKt.emptyList();
                }
                z9 = false;
                list6 = list4;
                file3 = file3;
                if (!g0(context, list5, file3, list6, listEmptyList3)) {
                    l0(str13 + t92.c(t92.a, "Java 编译失败，请根据上方 e: 行定位修改", null, 0, 0, 14, null) + str13);
                    return null;
                }
                bVar3 = null;
                b1("Java编译成功");
            }
            if (y0()) {
                return bVar3;
            }
            if (aVar != a.FULL || aVar == a.VERIFY) {
                try {
                    Result.Companion companion6 = Result.Companion;
                    listD = s6b.a.d(file11, file3, fileD1, str10);
                    if (!listD.isEmpty()) {
                        it5 = listD.iterator();
                        while (it5.hasNext()) {
                            l0(((s6b.b) it5.next()).a() + str13);
                        }
                    }
                    kb3 kb3Var = kb3.a;
                    List list9 = listD;
                    arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list9, c3));
                    it4 = list9.iterator();
                    while (it4.hasNext()) {
                        arrayList4.add(((s6b.b) it4.next()).a());
                    }
                    kb3Var.t(arrayList4);
                    Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th10) {
                    Result.Companion companion7 = Result.Companion;
                    Result.constructor-impl(ResultKt.createFailure(th10));
                }
            }
            if (z3 && list3.isEmpty()) {
                List listA4 = jVar.a();
                if (!(listA4 instanceof Collection) || !listA4.isEmpty()) {
                    Iterator it14 = listA4.iterator();
                    while (true) {
                        if (it14.hasNext()) {
                            if (StringsKt.contains$default(((bl3.i) it14.next()).c(), str12, z9, 2, (Object) null)) {
                                z10 = true;
                            }
                        }
                    }
                }
                z10 = z9;
            } else {
                z10 = true;
            }
            W0(file17, list6, list3, z10);
            return new b(file3, file14, iJ0, i5, str9, list6, listBuild, jVar.f());
        }
        bVar = null;
        jVarN0 = N0(file21, file27);
        Iterator it15 = jVarN0.d().iterator();
        while (it15.hasNext()) {
            l0("\n[Error] 依赖解析失败：" + ((String) it15.next()) + "\n");
        }
        if (!jVarN0.d().isEmpty()) {
            return null;
        }
        listC = jVarN0.c();
        listEmptyList2 = CollectionsKt.emptyList();
        jVar = jVarN0;
        list = listEmptyList2;
        z3 = false;
        listB = listC;
        if (!Z(file22).isEmpty()) {
            listB = n11.a.b(file27, listB, new Function1() { // from class: xc7
                public final Object invoke(Object obj4) {
                    return ge7.A((String) obj4);
                }
            });
        }
        if (y0()) {
            return bVar;
        }
        file = new File(file21, "AndroidManifest.xml");
        if (!file.isFile()) {
            l0("\n[Error] 项目缺少 AndroidManifest.xml\n");
            return bVar;
        }
        str5 = str4;
        list2 = listB;
        file2 = file23;
        file3 = file24;
        new File(file22, StringsKt.replace$default(str2, '.', '/', false, 4, (Object) null) + "/R.java").delete();
        Result.Companion companion8 = Result.Companion;
        str6 = str2;
        file20 = new File(file22, StringsKt.replace$default(str6, '.', '/', false, 4, (Object) null) + "/CrashApp.java");
        if (file20.isFile()) {
            charset = bVar;
            text$default4 = FilesKt.readText$default(file20, charset, 1, charset);
            c2 = 2;
            z4 = false;
            if (StringsKt.contains$default(text$default4, "CRASH_REPORT", false, 2, charset)) {
                FilesKt.writeText$default(file20, ufb.a.I(str6), (Charset) null, 2, (Object) null);
            } else {
                FilesKt.writeText$default(file20, ufb.a.I(str6), (Charset) null, 2, (Object) null);
            }
        } else {
            c2 = 2;
            z4 = false;
        }
        Result.constructor-impl(Unit.INSTANCE);
        if (z2) {
            File file210 = new File(file2, "generated/codeslot-src");
            File file211 = new File(file210, StringsKt.replace$default(str6, '.', '/', false, 4, (Object) null));
            file211.mkdirs();
            File file35 = new File(file211, "CodeSlotLoader.java");
            o32 o32Var2 = o32.a;
            j1(file35, o32Var2.a(str6));
            j1(new File(file211, "CodeSlotProvider.java"), o32Var2.b(str6, dc3.a.a(str)));
            file4 = file210;
        } else {
            file4 = null;
        }
        if (z) {
            str7 = "/DiagLogger.java";
            file5 = new File(file22, StringsKt.replace$default(str6, '.', '/', false, 4, (Object) null) + str7);
            if (file5.isFile()) {
                FilesKt.writeText$default(file5, ufb.a.R(str6), (Charset) null, 2, (Object) null);
                z5 = false;
            } else {
                text$default = FilesKt.readText$default(file5, (Charset) null, 1, (Object) null);
                z5 = false;
                if (StringsKt.contains$default(text$default, "code_slot/run_id", false, 2, (Object) null)) {
                    FilesKt.writeText$default(file5, ufb.a.R(str6), (Charset) null, 2, (Object) null);
                } else {
                    FilesKt.writeText$default(file5, ufb.a.R(str6), (Charset) null, 2, (Object) null);
                }
            }
            Result.constructor-impl(Unit.INSTANCE);
        } else {
            str7 = "/DiagLogger.java";
            z5 = false;
        }
        if (z) {
            strG = kb3.a.g();
            if (strG.length() > 0) {
                z8 = z5;
                String strReplace$default2 = StringsKt.replace$default(str6, '.', '/', false, 4, (Object) null);
                regex = new Regex("private static String RUN_ID = \"[^\"]*\"\\s*;");
                str8 = "private static String RUN_ID = \"" + strG + "\";";
                file6 = new File(file22, strReplace$default2 + str7);
                if (file6.isFile()) {
                    text$default3 = FilesKt.readText$default(file6, (Charset) null, 1, (Object) null);
                    strReplace2 = regex.replace(text$default3, str8);
                    if (!Intrinsics.areEqual(strReplace2, text$default3)) {
                        FilesKt.writeText$default(file6, strReplace2, (Charset) null, 2, (Object) null);
                    }
                }
                file7 = new File(file22, strReplace$default2 + "/CrashApp.java");
                z8 = z8;
                if (file7.isFile()) {
                    text$default2 = FilesKt.readText$default(file7, (Charset) null, 1, (Object) null);
                    strReplace = regex.replace(text$default2, str8);
                    if (!Intrinsics.areEqual(strReplace, text$default2)) {
                        z8 = z8;
                        FilesKt.writeText$default(file7, strReplace, (Charset) null, 2, (Object) null);
                        z8 = z8;
                    }
                }
            } else {
                z8 = z5;
            }
            z8 = z8;
            obj = Result.constructor-impl(Unit.INSTANCE);
            z7 = z8;
            Result.box-impl(obj);
            i4 = z7;
        } else {
            i4 = z5;
            L0(file22, str6);
            Unit unit5 = Unit.INSTANCE;
        }
        if (z) {
            file8 = file2;
            fileD1 = d1(file, file8, str6);
        } else {
            file8 = file2;
            if (z2) {
                dc3 dc3Var2 = dc3.a;
                String strK2 = dc3Var2.k(FilesKt.readText$default(file, (Charset) null, 1, (Object) null), str6, str5, dc3Var2.a(str));
                file = new File(file8, "AndroidManifest.debughost.xml");
                parentFile = file.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                FilesKt.writeText$default(file, strK2, (Charset) null, 2, (Object) null);
            }
            fileD1 = file;
        }
        if (z2) {
            strA = dc3.a.a(str);
        } else {
            strA = str6;
        }
        listCreateListBuilder = CollectionsKt.createListBuilder();
        file9 = new File(file21, "assets");
        if (!file9.isDirectory()) {
            file9 = null;
        }
        if (file9 != null) {
            listCreateListBuilder.add(file9);
        }
        listCreateListBuilder.addAll(jVar.b());
        listBuild = CollectionsKt.build(listCreateListBuilder);
        if (!z11) {
            X0("编译资源", "编译资源", i4);
            fileN0 = n0(context);
            if (fileN0 == null) {
                l0("\n[Error] assets/android.jar 缺失，aapt2 link 无法进行\n");
                return null;
            }
            File file36 = file4;
            ck ckVar2 = ck.a;
            List listI2 = jVar.i();
            List listA5 = jVar.a();
            arrayList = new ArrayList();
            it = listA5.iterator();
            while (it.hasNext()) {
                strF = ((bl3.i) it.next()).f();
                if (strF != null) {
                    arrayList.add(strF);
                }
            }
            file10 = file36;
            File file37 = file8;
            bVarA = ckVar2.a(context, file21, file37, str2, iJ0, iJ1, iE, str14, fileN0, fileD1, listI2, arrayList, jVar.h(), strA, list8, new k(this));
            context2 = context;
            file11 = file21;
            str9 = str14;
            i5 = iE;
            file12 = file37;
            str10 = str2;
            File fileA2 = bVarA.a();
            fileC = bVarA.c();
            if (bVarA.d()) {
            }
            if (y0()) {
            }
            return null;
        }
        file19 = new File(file8, "aapt2/out.ap_");
        if (!file19.isFile()) {
            parentFile2 = file19.getParentFile();
            if (parentFile2 != null) {
                parentFile2.mkdirs();
            }
            FilesKt.writeBytes(file19, new byte[i4]);
        }
        file10 = file4;
        file12 = file8;
        file11 = file21;
        str9 = str14;
        i5 = iE;
        fileC = file25;
        context2 = context;
        file13 = file19;
        str10 = str6;
        if (y0()) {
            return null;
        }
        listZ = Z(file22);
        str11 = "androidx.compose";
        if (listZ.isEmpty()) {
            X0("编译 Kotlin", "编译Kotlin", false);
            int size2 = listZ.size();
            file16 = fileC;
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            char[] cArr2 = {10251, 10265, 10297, 10296, 10300, 10292, 10278, 10279, 10247, 10255};
            Ref.IntRef intRef2 = new Ref.IntRef();
            g1(T0(jCurrentTimeMillis2, cArr2, intRef2, size2), false);
            Timer timer2 = new Timer("kotlin-progress", true);
            timer2.scheduleAtFixedRate(new i(jCurrentTimeMillis2, cArr2, intRef2, size2), 120L, 120L);
            l = timer2;
            listCreateListBuilder3 = CollectionsKt.createListBuilder();
            fileN1 = a.n0(context2);
            if (fileN1 != null) {
                listCreateListBuilder3.add(fileN1);
            }
            listV = list2;
            listCreateListBuilder3.addAll(listV);
            listBuild3 = CollectionsKt.build(listCreateListBuilder3);
            list7 = list;
            if (list7.isEmpty()) {
                i6 = 2;
                if (list7.isEmpty()) {
                    it11 = list.iterator();
                    while (it11.hasNext()) {
                        ((File) it11.next()).setReadOnly();
                    }
                    str12 = "androidx.compose";
                    file14 = file13;
                    arrayList8 = list;
                    listEmptyList4 = arrayList8;
                } else {
                    jVarU = bl3.U(bl3.a, CollectionsKt.listOf("org.jetbrains.kotlin:kotlin-compose-compiler-plugin:2.4.0"), file27, null, new Function1() { // from class: id7
                        public final Object invoke(Object obj4) {
                            return ge7.F((bl3.f) obj4);
                        }
                    }, 4, null);
                    it8 = jVarU.d().iterator();
                    while (it8.hasNext()) {
                        l0("\n[Error] Compose 插件解析失败：" + ((String) it8.next()) + "\n");
                    }
                    if (jVarU.c().isEmpty()) {
                        l0("\n[Error] Compose 编译器插件解析失败，无法编译 Compose\n");
                        return null;
                    }
                    List listC3 = jVarU.c();
                    arrayList8 = new ArrayList();
                    it9 = listC3.iterator();
                    while (it9.hasNext()) {
                        next = it9.next();
                        name = ((File) next).getName();
                        name.getClass();
                        Iterator it16 = it9;
                        String str16 = str11;
                        File file38 = file13;
                        if (StringsKt.startsWith$default(name, "kotlin-compose-compiler-plugin", false, i6, (Object) null)) {
                            arrayList8.add(next);
                        }
                        it9 = it16;
                        str11 = str16;
                        file13 = file38;
                    }
                    str12 = str11;
                    file14 = file13;
                    if (arrayList8.isEmpty()) {
                        l0("\n[Error] 未找到 Compose 编译器插件本体 jar\n");
                        return null;
                    }
                    it10 = arrayList8.iterator();
                    while (it10.hasNext()) {
                        ((File) it10.next()).setReadOnly();
                    }
                    listEmptyList4 = arrayList8;
                }
            } else {
                listA = jVar.a();
                if (listA instanceof Collection) {
                    it7 = listA.iterator();
                    while (true) {
                        if (it7.hasNext()) {
                            i6 = 2;
                            if (StringsKt.contains$default(((bl3.i) it7.next()).c(), "androidx.compose", false, 2, (Object) null)) {
                                if (list7.isEmpty()) {
                                    it11 = list.iterator();
                                    while (it11.hasNext()) {
                                        ((File) it11.next()).setReadOnly();
                                    }
                                    str12 = "androidx.compose";
                                    file14 = file13;
                                    arrayList8 = list;
                                    listEmptyList4 = arrayList8;
                                } else {
                                    jVarU = bl3.U(bl3.a, CollectionsKt.listOf("org.jetbrains.kotlin:kotlin-compose-compiler-plugin:2.4.0"), file27, null, new Function1() { // from class: id7
                                        public final Object invoke(Object obj4) {
                                            return ge7.F((bl3.f) obj4);
                                        }
                                    }, 4, null);
                                    it8 = jVarU.d().iterator();
                                    while (it8.hasNext()) {
                                        l0("\n[Error] Compose 插件解析失败：" + ((String) it8.next()) + "\n");
                                    }
                                    if (jVarU.c().isEmpty()) {
                                        l0("\n[Error] Compose 编译器插件解析失败，无法编译 Compose\n");
                                        return null;
                                    }
                                    List listC4 = jVarU.c();
                                    arrayList8 = new ArrayList();
                                    it9 = listC4.iterator();
                                    while (it9.hasNext()) {
                                        next = it9.next();
                                        name = ((File) next).getName();
                                        name.getClass();
                                        Iterator it17 = it9;
                                        String str17 = str11;
                                        File file39 = file13;
                                        if (StringsKt.startsWith$default(name, "kotlin-compose-compiler-plugin", false, i6, (Object) null)) {
                                            arrayList8.add(next);
                                        }
                                        it9 = it17;
                                        str11 = str17;
                                        file13 = file39;
                                    }
                                    str12 = str11;
                                    file14 = file13;
                                    if (arrayList8.isEmpty()) {
                                        l0("\n[Error] 未找到 Compose 编译器插件本体 jar\n");
                                        return null;
                                    }
                                    it10 = arrayList8.iterator();
                                    while (it10.hasNext()) {
                                        ((File) it10.next()).setReadOnly();
                                    }
                                    listEmptyList4 = arrayList8;
                                }
                            }
                        }
                    }
                } else {
                    it7 = listA.iterator();
                    while (true) {
                        if (it7.hasNext()) {
                            i6 = 2;
                            if (StringsKt.contains$default(((bl3.i) it7.next()).c(), "androidx.compose", false, 2, (Object) null)) {
                                if (list7.isEmpty()) {
                                    it11 = list.iterator();
                                    while (it11.hasNext()) {
                                        ((File) it11.next()).setReadOnly();
                                    }
                                    str12 = "androidx.compose";
                                    file14 = file13;
                                    arrayList8 = list;
                                    listEmptyList4 = arrayList8;
                                } else {
                                    jVarU = bl3.U(bl3.a, CollectionsKt.listOf("org.jetbrains.kotlin:kotlin-compose-compiler-plugin:2.4.0"), file27, null, new Function1() { // from class: id7
                                        public final Object invoke(Object obj4) {
                                            return ge7.F((bl3.f) obj4);
                                        }
                                    }, 4, null);
                                    it8 = jVarU.d().iterator();
                                    while (it8.hasNext()) {
                                        l0("\n[Error] Compose 插件解析失败：" + ((String) it8.next()) + "\n");
                                    }
                                    if (jVarU.c().isEmpty()) {
                                        l0("\n[Error] Compose 编译器插件解析失败，无法编译 Compose\n");
                                        return null;
                                    }
                                    List listC5 = jVarU.c();
                                    arrayList8 = new ArrayList();
                                    it9 = listC5.iterator();
                                    while (it9.hasNext()) {
                                        next = it9.next();
                                        name = ((File) next).getName();
                                        name.getClass();
                                        Iterator it18 = it9;
                                        String str18 = str11;
                                        File file310 = file13;
                                        if (StringsKt.startsWith$default(name, "kotlin-compose-compiler-plugin", false, i6, (Object) null)) {
                                            arrayList8.add(next);
                                        }
                                        it9 = it18;
                                        str11 = str18;
                                        file13 = file310;
                                    }
                                    str12 = str11;
                                    file14 = file13;
                                    if (arrayList8.isEmpty()) {
                                        l0("\n[Error] 未找到 Compose 编译器插件本体 jar\n");
                                        return null;
                                    }
                                    it10 = arrayList8.iterator();
                                    while (it10.hasNext()) {
                                        ((File) it10.next()).setReadOnly();
                                    }
                                    listEmptyList4 = arrayList8;
                                }
                            }
                        }
                    }
                }
                str12 = "androidx.compose";
                file14 = file13;
                listEmptyList4 = CollectionsKt.emptyList();
                arrayList8 = list;
            }
            listCreateListBuilder4 = CollectionsKt.createListBuilder();
            listCreateListBuilder4.add(file22);
            listCreateListBuilder4.add(file16);
            file15 = file10;
            if (file15 != null) {
                listCreateListBuilder4.add(file15);
                Unit unit6 = Unit.INSTANCE;
            }
            if (!db8.c(db8.a, listZ, CollectionsKt.build(listCreateListBuilder4), listBuild3, file3, "17", (String) null, listEmptyList4, new j(this), 32, (Object) null)) {
                l0("\n" + t92.c(t92.a, "Kotlin 编译失败，请根据上方 e: 行定位修改", null, 0, 0, 14, null) + str13);
                return null;
            }
            bVar2 = null;
            b1("Kotlin编译成功");
            list3 = arrayList8;
        } else {
            str12 = "androidx.compose";
            file14 = file13;
            bVar2 = null;
            str13 = "\n";
            listV = list2;
            file15 = file10;
            file16 = fileC;
            list3 = list;
        }
        if (y0()) {
            return bVar2;
        }
        if (aVar != a.FULL) {
            listV = bl3.a.v(listV);
        } else {
            listV = bl3.a.v(listV);
        }
        listCreateListBuilder2 = CollectionsKt.createListBuilder();
        List listY4 = a.Y(file22);
        list4 = listV;
        file17 = file12;
        arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listY4, 10));
        it2 = listY4.iterator();
        while (it2.hasNext()) {
            arrayList2.add(TuplesKt.to((File) it2.next(), file22));
        }
        listCreateListBuilder2.addAll(arrayList2);
        List listY5 = a.Y(file16);
        arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listY5, 10));
        it3 = listY5.iterator();
        while (it3.hasNext()) {
            arrayList3.add(TuplesKt.to((File) it3.next(), file16));
        }
        listCreateListBuilder2.addAll(arrayList3);
        if (file15 != null) {
            List listY6 = a.Y(file15);
            c3 = '\n';
            arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listY6, 10));
            it6 = listY6.iterator();
            while (it6.hasNext()) {
                arrayList7.add(TuplesKt.to((File) it6.next(), file15));
            }
            listCreateListBuilder2.addAll(arrayList7);
        } else {
            c3 = '\n';
        }
        listBuild2 = CollectionsKt.build(listCreateListBuilder2);
        if (z) {
            arrayList6 = new ArrayList();
            while (r0.hasNext()) {
                if (!u.contains(((File) ((Pair) obj2).getFirst()).getName())) {
                    arrayList6.add(obj2);
                }
            }
            listBuild2 = arrayList6;
        }
        if (!listBuild2.isEmpty()) {
        }
        if (aVar == a.CODE_SLOT_FAST) {
            arrayList5 = new ArrayList();
            while (r0.hasNext()) {
                Pair pair2 = (Pair) obj3;
                File file311 = (File) pair2.component1();
                file18 = (File) pair2.component2();
                if (Intrinsics.areEqual(file18, file16)) {
                }
                arrayList5.add(obj3);
            }
            list5 = arrayList5;
        } else {
            list5 = listBuild2;
        }
        if (list5.isEmpty()) {
            X0("编译 Java", "编译Java", false);
            if (aVar == a.CODE_SLOT_FAST) {
                listEmptyList3 = CollectionsKt.listOf(file3);
            } else {
                listEmptyList3 = CollectionsKt.emptyList();
            }
            z9 = false;
            list6 = list4;
            file3 = file3;
            if (!g0(context, list5, file3, list6, listEmptyList3)) {
                l0(str13 + t92.c(t92.a, "Java 编译失败，请根据上方 e: 行定位修改", null, 0, 0, 14, null) + str13);
                return null;
            }
            bVar3 = null;
            b1("Java编译成功");
        } else {
            list6 = list4;
            z9 = false;
            bVar3 = null;
        }
        if (y0()) {
            return bVar3;
        }
        if (aVar != a.FULL) {
            Result.Companion companion9 = Result.Companion;
            listD = s6b.a.d(file11, file3, fileD1, str10);
            if (!listD.isEmpty()) {
                it5 = listD.iterator();
                while (it5.hasNext()) {
                    l0(((s6b.b) it5.next()).a() + str13);
                }
            }
            kb3 kb3Var2 = kb3.a;
            List list10 = listD;
            arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list10, c3));
            it4 = list10.iterator();
            while (it4.hasNext()) {
                arrayList4.add(((s6b.b) it4.next()).a());
            }
            kb3Var2.t(arrayList4);
            Result.constructor-impl(Unit.INSTANCE);
        } else {
            Result.Companion companion10 = Result.Companion;
            listD = s6b.a.d(file11, file3, fileD1, str10);
            if (!listD.isEmpty()) {
                it5 = listD.iterator();
                while (it5.hasNext()) {
                    l0(((s6b.b) it5.next()).a() + str13);
                }
            }
            kb3 kb3Var3 = kb3.a;
            List list11 = listD;
            arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list11, c3));
            it4 = list11.iterator();
            while (it4.hasNext()) {
                arrayList4.add(((s6b.b) it4.next()).a());
            }
            kb3Var3.t(arrayList4);
            Result.constructor-impl(Unit.INSTANCE);
        }
        if (z3) {
            z10 = true;
        } else {
            z10 = true;
        }
        W0(file17, list6, list3, z10);
        return new b(file3, file14, iJ0, i5, str9, list6, listBuild, jVar.f());
    }

    public final boolean U0(Context context, String str, String str2, String str3, int i2, int i3, cc3.a aVar) {
        b bVarQ0 = Q0(context, str, str2, i2, i3, false, true, str3, a.CODE_SLOT_FAST);
        if (bVarQ0 == null) {
            bVarQ0 = Q0(context, str, str2, i2, i3, false, true, str3, a.CODE_SLOT_FULL_SOURCES);
        }
        if (bVarQ0 == null || y0()) {
            return false;
        }
        File file = new File(new File(new File(context.getFilesDir(), "projects/" + str), "build"), "code-slot-dex");
        Y0(this, "DEX 转换", "DEX转换", false, 4, null);
        if (!i0(bVarQ0.c(), bVarQ0.d(), new File(context.getFilesDir(), "libcache"), file, bVarQ0.e())) {
            l0("\n[Error] 增量 DEX 失败\n");
            return false;
        }
        b1("DEX转换成功");
        if (y0()) {
            return false;
        }
        Y0(this, "应用增量", "应用增量", false, 4, null);
        xb3 xb3Var = xb3.a;
        if (!xb3Var.d(context, file, dc3.a.a(str))) {
            l0("[Error] 增量应用失败（槽校验未通过）\n");
            cc3.a.x(context, str);
            return false;
        }
        cc3.a.j(context, str, aVar, xb3Var.i(context, str), kb3.a.g());
        b1("增量应用成功");
        return true;
    }

    public final INameEnvironment V(Context context, List list, ClassLoader classLoader, List list2, List list3) {
        INameEnvironment lsdVar = new lsd(list, classLoader);
        Iterator it = list3.iterator();
        while (it.hasNext()) {
            lsdVar = new mo2(lsdVar, new ay1((File) it.next()));
        }
        for (File file : CollectionsKt.reversed(list2)) {
            try {
                lsdVar = new mo2(new eb7(file), lsdVar);
            } catch (Throwable th) {
                Log.e("JavaCompilerEngine", "Failed to load library jar " + file.getName(), th);
            }
        }
        File fileN0 = n0(context);
        if (fileN0 == null) {
            return lsdVar;
        }
        try {
            return new mo2(new eb7(fileN0), lsdVar);
        } catch (Throwable th2) {
            Log.e("JavaCompilerEngine", "Failed to init android.jar classpath", th2);
            return lsdVar;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [ge7] */
    /* JADX WARN: Type inference failed for: r12v10, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r12v17, types: [ge7] */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r1v0, types: [ge7, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v3 */
    public final File V0(Context context, String str, String str2, String str3, int i2, int i3, cc3.a aVar) {
        ?? D;
        File fileN0;
        File file;
        String strB;
        ?? file2 = this;
        try {
            File file3 = new File(context.getFilesDir(), "projects/" + str);
            File file4 = new File(file3, "build");
            File file5 = new File(file4, "shell-dex");
            File file6 = new File(file4, "code-slot-dex");
            File file7 = new File(file4, "apk");
            cc3 cc3Var = cc3.a;
            if (!cc3Var.r(context, str)) {
                file2.c1();
                return null;
            }
            int iJ0 = file2.J0(file3, "minSdk", i2);
            int iJ1 = file2.J0(file3, "targetSdk", i3);
            int iE = cc3Var.e(context);
            try {
                String strK0 = file2.K0(file3, "versionName", "1.0");
                bl3.j jVarN0 = file2.N0(file3, new File(context.getFilesDir(), "libcache"));
                for (Iterator it = jVarN0.d().iterator(); it.hasNext(); it = it) {
                    file2.l0("\n[Error] 依赖解析失败：" + ((String) it.next()) + "\n");
                }
                if (!jVarN0.d().isEmpty()) {
                    file2.c1();
                    return null;
                }
                if (file2.y0()) {
                    file2.c1();
                    return null;
                }
                File file8 = new File(file3, "AndroidManifest.xml");
                if (!file8.isFile()) {
                    file2.l0("\n[Error] 项目缺少 AndroidManifest.xml\n");
                    file2.c1();
                    return null;
                }
                dc3 dc3Var = dc3.a;
                try {
                    try {
                        String strK = dc3Var.k(FilesKt.readText$default(file8, (Charset) null, 1, (Object) null), str2, str3, dc3Var.a(str));
                        file2 = new File(file4, "AndroidManifest.debughost.xml");
                        File parentFile = file2.getParentFile();
                        if (parentFile != null) {
                            parentFile.mkdirs();
                        }
                        try {
                            FilesKt.writeText$default((File) file2, strK, (Charset) null, 2, (Object) null);
                            file2.X0("编译资源", "编译资源", false);
                            fileN0 = n0(context);
                            if (fileN0 == null) {
                                try {
                                    file2.l0("\n[Error] assets/android.jar 缺失，aapt2 link 无法进行\n");
                                    file2.c1();
                                    return null;
                                } catch (Throwable th) {
                                    th = th;
                                    file = null;
                                }
                            } else {
                                file = null;
                                try {
                                    ck ckVar = ck.a;
                                    List listI = jVarN0.i();
                                    List listA = jVarN0.a();
                                    ArrayList arrayList = new ArrayList();
                                    Iterator it2 = listA.iterator();
                                    while (it2.hasNext()) {
                                        String strF = ((bl3.i) it2.next()).f();
                                        if (strF != null) {
                                            arrayList.add(strF);
                                        }
                                    }
                                    try {
                                        ck.b bVarA = ckVar.a(context, file3, file4, str2, iJ0, iJ1, iE, strK0, fileN0, file2, (16384 & 1024) != 0 ? CollectionsKt.emptyList() : listI, (16384 & 2048) != 0 ? CollectionsKt.emptyList() : arrayList, (16384 & 4096) != 0 ? CollectionsKt.emptyList() : jVarN0.h(), (16384 & 8192) != 0 ? str2 : dc3.a.a(str), (16384 & 16384) != 0 ? CollectionsKt.emptyList() : null, (16384 & 32768) != 0 ? null : new l(file2));
                                        File fileA = bVarA.a();
                                        D = bVarA.d();
                                        try {
                                            if (D == 0 || fileA == null) {
                                                if (!file2.y0() && ((strB = bVarA.b()) == null || !StringsKt.contains$default(strB, "已取消", false, 2, (Object) null))) {
                                                    file2.l0("\n" + t92.c(t92.a, "资源编译失败", null, 0, 0, 14, null) + "\n");
                                                    String strB2 = bVarA.b();
                                                    if (strB2 != null) {
                                                        ge7 ge7Var = a;
                                                        ge7Var.l0(ge7Var.q0(strB2, file3) + "\n");
                                                    }
                                                    file2.c1();
                                                    return null;
                                                }
                                                file2.c1();
                                                return null;
                                            }
                                            file2.b1("资源编译成功");
                                            if (file2.y0()) {
                                                file2.c1();
                                                return null;
                                            }
                                            fileN0 = null;
                                            try {
                                                Y0(file2, "打包签名 APK", "打包签名APK", false, 4, null);
                                                file2 = file2;
                                                try {
                                                    File file9 = new File(file4, "code-slot-assets");
                                                    File file10 = new File(file9, "yima_codeslot");
                                                    FilesKt.deleteRecursively(file9);
                                                    file10.mkdirs();
                                                    File[] fileArrListFiles = file6.listFiles(new FileFilter() { // from class: fe7
                                                        @Override // java.io.FileFilter
                                                        public final boolean accept(File file11) {
                                                            return ge7.z(file11);
                                                        }
                                                    });
                                                    if (fileArrListFiles != null) {
                                                        for (File file11 : fileArrListFiles) {
                                                            file11.getClass();
                                                            FilesKt.copyTo$default(file11, new File(file10, file11.getName()), true, 0, 4, (Object) null);
                                                        }
                                                    }
                                                    List listCreateListBuilder = CollectionsKt.createListBuilder();
                                                    listCreateListBuilder.add(file9);
                                                    File file12 = new File(file3, "assets");
                                                    File file13 = file12.isDirectory() ? file12 : null;
                                                    if (file13 != null) {
                                                        listCreateListBuilder.add(file13);
                                                    }
                                                    listCreateListBuilder.addAll(jVarN0.b());
                                                    hb0.b bVarL = hb0.a.l(context, fileA, file5, file7, iJ0, CollectionsKt.build(listCreateListBuilder), jVarN0.f(), hb0.c.a.a);
                                                    if (bVarL.c() && bVarL.a() != null) {
                                                        file2.b1("APK打包签名成功");
                                                        cc3.a.k(context);
                                                        file2.H0(context, str, str3, aVar);
                                                        File fileA2 = bVarL.a();
                                                        file2.c1();
                                                        return fileA2;
                                                    }
                                                    String strB3 = bVarL.b();
                                                    StringBuilder sb = new StringBuilder();
                                                    sb.append("\n[Error] APK 打包失败：");
                                                    sb.append(strB3);
                                                    sb.append("\n");
                                                    file2.l0(sb.toString());
                                                    file2.c1();
                                                    return null;
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                    D = "\n";
                                                    Log.e("JavaCompilerEngine", "fast repackage failed", th);
                                                    file2.l0("\n[Error] 重打包失败：" + th.getClass().getSimpleName() + ": " + th.getMessage() + D);
                                                    return fileN0;
                                                }
                                            } catch (Throwable th3) {
                                                th = th3;
                                                file2 = file2;
                                            }
                                        } catch (Throwable th4) {
                                            th = th4;
                                        }
                                    } catch (Throwable th5) {
                                        th = th5;
                                        D = "\n";
                                        fileN0 = null;
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                    fileN0 = file;
                                    D = "\n";
                                }
                            }
                            fileN0 = file;
                        } catch (Throwable th7) {
                            th = th7;
                            fileN0 = null;
                        }
                    } catch (Throwable th8) {
                        th = th8;
                        D = "\n";
                    }
                } catch (Throwable th9) {
                    th = th9;
                    fileN0 = null;
                }
                D = "\n";
            } catch (Throwable th10) {
                th = th10;
                file2 = file2;
                D = "\n";
                fileN0 = null;
            }
        } catch (Throwable th11) {
            th = th11;
            file2 = file2;
            D = "\n";
            fileN0 = null;
        }
        try {
            Log.e("JavaCompilerEngine", "fast repackage failed", th);
            file2.l0("\n[Error] 重打包失败：" + th.getClass().getSimpleName() + ": " + th.getMessage() + D);
            return fileN0;
        } finally {
            file2.c1();
        }
    }

    public final void W() {
        StringBuilder sb = b;
        synchronized (sb) {
            sb.setLength(0);
            c.clear();
            Unit unit = Unit.INSTANCE;
        }
        d.setValue(CollectionsKt.emptyList());
    }

    public final void W0(File file, List list, List list2, boolean z) {
        try {
            Result.Companion companion = Result.Companion;
            file.mkdirs();
            FilesKt.writeText$default(new File(file, "inc-toolchain.txt"), n11.a.c(), (Charset) null, 2, (Object) null);
            File file2 = new File(file, "inc-classpath.txt");
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (((File) obj).isFile()) {
                    arrayList.add(obj);
                }
            }
            FilesKt.writeText$default(file2, CollectionsKt.joinToString$default(arrayList, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: ld7
                public final Object invoke(Object obj2) {
                    return ge7.j((File) obj2);
                }
            }, 30, (Object) null), (Charset) null, 2, (Object) null);
            FilesKt.writeText$default(new File(file, "inc-flags.txt"), "compose=" + z + "\n", (Charset) null, 2, (Object) null);
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : list2) {
                if (((File) obj2).isFile()) {
                    arrayList2.add(obj2);
                }
            }
            if (!arrayList2.isEmpty()) {
                FilesKt.writeText$default(new File(file, "inc-compose-plugin.txt"), CollectionsKt.joinToString$default(arrayList2, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: md7
                    public final Object invoke(Object obj3) {
                        return ge7.l((File) obj3);
                    }
                }, 30, (Object) null), (Charset) null, 2, (Object) null);
            }
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            Result.constructor-impl(ResultKt.createFailure(th));
        }
    }

    public final void X() {
        String str = r;
        r = null;
        q = false;
        p++;
        if (k) {
            return;
        }
        if (str != null) {
            i1(str);
        }
        if (!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            s.post(new Runnable() { // from class: nd7
                @Override // java.lang.Runnable
                public final void run() {
                    ge7.B(countDownLatch);
                }
            });
            try {
                countDownLatch.await(100L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                Unit unit = Unit.INSTANCE;
            }
        }
        StringBuilder sb = b;
        synchronized (sb) {
            try {
                if (sb.length() > 0 && StringsKt.last(sb) != '\n') {
                    sb.append('\n');
                    a.Q("\n");
                    d.setValue(new ArrayList(c));
                }
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void X0(String str, String str2, boolean z) {
        c1();
        X();
        m = System.currentTimeMillis();
        n = str2;
        o = 0L;
        l0(str + "\n");
        if (!z) {
            g1("正在" + str2 + "…", false);
            return;
        }
        char[] cArr = {10251, 10265, 10297, 10296, 10300, 10292, 10278, 10279, 10247, 10255};
        Ref.IntRef intRef = new Ref.IntRef();
        g1(Z0(cArr, intRef), false);
        Timer timer = new Timer("build-progress", true);
        timer.scheduleAtFixedRate(new m(cArr, intRef), 120L, 120L);
        l = timer;
    }

    public final List Y(File file) {
        return (file.exists() && file.isDirectory()) ? SequencesKt.toList(SequencesKt.filter(FilesKt.walkTopDown(file), new Function1() { // from class: ed7
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ge7.q((File) obj));
            }
        })) : CollectionsKt.emptyList();
    }

    public final List Z(File file) {
        return (file.exists() && file.isDirectory()) ? SequencesKt.toList(SequencesKt.filter(FilesKt.walkTopDown(file), new Function1() { // from class: fd7
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ge7.H((File) obj));
            }
        })) : CollectionsKt.emptyList();
    }

    public final void a0(Context context, String str, String str2, final String str3, String str4, int i2, int i3, final hb0.c cVar, Function1 function1) {
        context.getClass();
        str.getClass();
        str2.getClass();
        str3.getClass();
        str4.getClass();
        cVar.getClass();
        function1.getClass();
        A0(context, str, str2, str3, str4, i2, i3, function1, cVar, true, false, new Function3() { // from class: wd7
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return Boolean.valueOf(ge7.i(str3, cVar, (Context) obj, (File) obj2, (String) obj3));
            }
        });
    }

    public final void a1() {
        synchronized (i) {
            try {
                if (g) {
                    h = true;
                    Thread thread = f;
                    if (thread != null) {
                        thread.interrupt();
                    }
                    hk.a.f();
                    ge7 ge7Var = a;
                    ge7Var.c1();
                    ge7Var.l0("[Build] 已请求停止（编译资源将尽快终止；其它阶段需等当前步骤结束）\n");
                    Unit unit = Unit.INSTANCE;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b1(String str) {
        c1();
        X();
        l0(str + "(耗时" + String.format("%.1f", Arrays.copyOf(new Object[]{Double.valueOf((System.currentTimeMillis() - m) / 1000.0d)}, 1)) + "秒)\n");
    }

    public final void c0(Context context, final String str, String str2, String str3, String str4, int i2, int i3, Function1 function1) {
        context.getClass();
        str.getClass();
        str2.getClass();
        str3.getClass();
        str4.getClass();
        function1.getClass();
        B0(this, context, str, str2, str3, str4, i2, i3, function1, null, false, true, new Function3() { // from class: ud7
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return Boolean.valueOf(ge7.x(str, (Context) obj, (File) obj2, (String) obj3));
            }
        }, 256, null);
    }

    public final void c1() {
        Timer timer = l;
        if (timer != null) {
            timer.cancel();
        }
        l = null;
    }

    public final File d1(File file, File file2, String str) {
        try {
            String strReplace = new Regex("<activity\\b[^>]*CrashActivity[^>]*?>[\\s\\S]*?</activity>").replace(new Regex("<activity\\b[^>]*CrashActivity[^>]*?/>").replace(new Regex("android:name\\s*=\\s*\"[^\"]*CrashApp\"\\s*").replace(FilesKt.readText$default(file, (Charset) null, 1, (Object) null), ""), ""), "");
            File file3 = new File(file2, "AndroidManifest.release.xml");
            FilesKt.writeText$default(file3, strReplace, (Charset) null, 2, (Object) null);
            l0("[Release] 已剥离调试组件（CrashApp/CrashActivity/DiagLogger）\n");
            return file3;
        } catch (Throwable th) {
            l0("[Warning] 正式打包剥离调试组件失败（已回退用原清单）：" + th.getMessage() + "\n");
            return file;
        }
    }

    public final c e0(Context context, String str, String str2, int i2, int i3) {
        Context context2;
        boolean z;
        StringBuilder sb;
        String str3;
        context.getClass();
        str.getClass();
        str2.getClass();
        Object obj = i;
        synchronized (obj) {
            if (g) {
                return new c(false, "构建器正忙（用户正在运行/打包，或上一次编译尚未结束），请稍后再让我编译验证。");
            }
            g = true;
            MutableStateFlow mutableStateFlow = e;
            mutableStateFlow.setValue(Boolean.TRUE);
            h = false;
            hk hkVar = hk.a;
            hkVar.h();
            Unit unit = Unit.INSTANCE;
            kb3 kb3Var = kb3.a;
            kb3Var.d();
            kb3Var.e();
            final StringBuilder sb2 = new StringBuilder();
            j = new Function1() { // from class: rd7
                public final Object invoke(Object obj2) {
                    return ge7.e(sb2, (String) obj2);
                }
            };
            k = true;
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                l0("[AI-Build] 开始编译校验...\n");
                context2 = context;
                try {
                    b bVarR0 = R0(this, context2, str, str2, i2, i3, false, false, null, a.VERIFY, WinError.ERROR_FORMS_AUTH_REQUIRED, null);
                    String str4 = String.format("%.1f", Arrays.copyOf(new Object[]{Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d)}, 1));
                    synchronized (obj) {
                        z = h;
                    }
                    if (z) {
                        l0("[AI-Build] 编译校验已取消（" + str4 + "s）\n");
                        c cVar = new c(false, "已取消：编译校验被用户中止。");
                        hkVar.g(context2);
                        synchronized (obj) {
                            j = null;
                            k = false;
                            g = false;
                            mutableStateFlow.setValue(Boolean.FALSE);
                            h = false;
                        }
                        return cVar;
                    }
                    if (bVarR0 != null) {
                        sb = new StringBuilder("[AI-Build] 编译校验通过（");
                        sb.append(str4);
                        str3 = "s）\n";
                    } else {
                        sb = new StringBuilder("[AI-Build] 编译校验失败（");
                        sb.append(str4);
                        str3 = "s）\n";
                    }
                    sb.append(str3);
                    l0(sb.toString());
                    c cVar2 = new c(bVarR0 != null, e1(bVarR0 != null, sb2.toString()));
                    hkVar.g(context2);
                    synchronized (obj) {
                        j = null;
                        k = false;
                        g = false;
                        mutableStateFlow.setValue(Boolean.FALSE);
                        h = false;
                    }
                    return cVar2;
                } catch (Throwable th) {
                    th = th;
                    try {
                        Log.e("JavaCompilerEngine", "compileOnly failed", th);
                        c cVar3 = new c(false, "编译校验异常：" + th.getClass().getSimpleName() + ": " + th.getMessage());
                        hk.a.g(context2);
                        synchronized (i) {
                            j = null;
                            k = false;
                            g = false;
                            e.setValue(Boolean.FALSE);
                            h = false;
                            Unit unit2 = Unit.INSTANCE;
                        }
                        return cVar3;
                    } catch (Throwable th2) {
                        hk.a.g(context2);
                        synchronized (i) {
                            j = null;
                            k = false;
                            g = false;
                            e.setValue(Boolean.FALSE);
                            h = false;
                            Unit unit3 = Unit.INSTANCE;
                            throw th2;
                        }
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                context2 = context;
            }
        }
    }

    public final String e1(boolean z, String str) {
        List listSplit$default = StringsKt.split$default(str, new String[]{"\n"}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            arrayList.add(StringsKt.trim((String) it.next()).toString());
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (t92.a.e((String) obj)) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
        Iterator it2 = listSplit$default.iterator();
        while (it2.hasNext()) {
            arrayList3.add(StringsKt.trim((String) it2.next()).toString());
        }
        ArrayList arrayList4 = new ArrayList();
        for (Object obj2 : arrayList3) {
            if (t92.a.f((String) obj2)) {
                arrayList4.add(obj2);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "✅ 编译校验通过（成功编译，但未安装运行）。\n" : "❌ 编译校验失败。\n");
        if (!arrayList2.isEmpty()) {
            sb.append("\n错误（Android Studio 风格：e: file:///路径:行:列 信息）：\n");
            Iterator it3 = CollectionsKt.take(arrayList2, 40).iterator();
            while (it3.hasNext()) {
                sb.append((String) it3.next());
                sb.append('\n');
            }
            if (arrayList2.size() > 40) {
                sb.append("…（另有 " + (arrayList2.size() - 40) + " 条错误未列出）\n");
            }
        }
        if (!arrayList4.isEmpty()) {
            sb.append("\n警告：\n");
            Iterator it4 = CollectionsKt.take(arrayList4, 20).iterator();
            while (it4.hasNext()) {
                sb.append((String) it4.next());
                sb.append('\n');
            }
        }
        if (z && arrayList2.isEmpty() && arrayList4.isEmpty()) {
            sb.append("没有编译错误，预检也未发现问题。可以让用户点「运行」验证实际效果。");
        }
        return StringsKt.trim(sb.toString()).toString();
    }

    public final void f1(File file) {
        if (file.isDirectory()) {
            try {
                Result.Companion companion = Result.Companion;
                ArrayDeque arrayDeque = new ArrayDeque();
                arrayDeque.addLast(file);
                while (!arrayDeque.isEmpty()) {
                    File file2 = (File) arrayDeque.removeLast();
                    File[] fileArrListFiles = file2.listFiles();
                    if (fileArrListFiles != null) {
                        for (File file3 : fileArrListFiles) {
                            if (file3.isDirectory()) {
                                String name = file3.getName();
                                name.getClass();
                                if (StringsKt.endsWith$default(name, ".dexcache", false, 2, (Object) null)) {
                                    FilesKt.deleteRecursively(file3);
                                } else {
                                    String name2 = file3.getName();
                                    name2.getClass();
                                    if (StringsKt.contains$default(name2, ".dexcache-api", false, 2, (Object) null)) {
                                        String name3 = file3.getName();
                                        name3.getClass();
                                        if (!new File(file2, StringsKt.substringBefore$default(name3, ".dexcache-api", (String) null, 2, (Object) null) + ".jar").isFile()) {
                                            FilesKt.deleteRecursively(file3);
                                        }
                                    } else {
                                        arrayDeque.addLast(file3);
                                    }
                                }
                            }
                        }
                    }
                }
                Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                Result.constructor-impl(ResultKt.createFailure(th));
            }
        }
    }

    public final boolean g0(Context context, List list, final File file, List list2, List list3) {
        String name;
        String path;
        ClassLoader classLoader = context.getClassLoader();
        List<Pair> list4 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
        for (Pair pair : list4) {
            arrayList.add(new aj7((File) pair.component1(), (File) pair.component2()));
        }
        final int iCoerceAtLeast = RangesKt.coerceAtLeast(list.size(), 1);
        final HashMap map = new HashMap(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Pair pair2 = (Pair) it.next();
            File file2 = (File) pair2.component1();
            File fileRelativeToOrNull = FilesKt.relativeToOrNull(file2, (File) pair2.component2());
            if (fileRelativeToOrNull == null || (path = fileRelativeToOrNull.getPath()) == null || (name = StringsKt.replace$default(path, '\\', '/', false, 4, (Object) null)) == null) {
                name = file2.getName();
            }
            map.put(file2.getAbsolutePath(), name);
            map.put(file2.getName(), name);
        }
        CompilerOptions compilerOptions = new CompilerOptions();
        compilerOptions.sourceLevel = ClassFileConstants.JDK17;
        compilerOptions.targetJDK = ClassFileConstants.JDK17;
        compilerOptions.complianceLevel = ClassFileConstants.JDK17;
        compilerOptions.originalSourceLevel = ClassFileConstants.JDK17;
        compilerOptions.originalComplianceLevel = ClassFileConstants.JDK17;
        compilerOptions.generateClassFiles = true;
        compilerOptions.produceReferenceInfo = true;
        compilerOptions.inlineJsrBytecode = true;
        IErrorHandlingPolicy iErrorHandlingPolicyProceedWithAllProblems = DefaultErrorHandlingPolicies.proceedWithAllProblems();
        iErrorHandlingPolicyProceedWithAllProblems.getClass();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final Ref.IntRef intRef = new Ref.IntRef();
        DefaultProblemFactory defaultProblemFactory = new DefaultProblemFactory(Locale.getDefault());
        char[] cArr = {10251, 10265, 10297, 10296, 10300, 10292, 10278, 10279, 10247, 10255};
        Ref.IntRef intRef2 = new Ref.IntRef();
        long jCurrentTimeMillis = System.currentTimeMillis();
        g1(h0(jCurrentTimeMillis, cArr, intRef2, iCoerceAtLeast), false);
        final Timer timer = new Timer("java-ecj-warmup", true);
        timer.scheduleAtFixedRate(new f(intRef, jCurrentTimeMillis, cArr, intRef2, iCoerceAtLeast), 120L, 120L);
        ICompilerRequestor iCompilerRequestor = new ICompilerRequestor() { // from class: kd7
            public final void acceptResult(CompilationResult compilationResult) {
                ge7.n(intRef, timer, map, iCoerceAtLeast, booleanRef, file, compilationResult);
            }
        };
        classLoader.getClass();
        INameEnvironment iNameEnvironmentV = V(context, list, classLoader, list2, list3);
        try {
            new Compiler(iNameEnvironmentV, iErrorHandlingPolicyProceedWithAllProblems, compilerOptions, iCompilerRequestor, defaultProblemFactory).compile((ICompilationUnit[]) arrayList.toArray(new aj7[0]));
            return !booleanRef.element;
        } finally {
            timer.cancel();
            iNameEnvironmentV.cleanup();
        }
    }

    public final void g1(final String str, boolean z) {
        if (k) {
            return;
        }
        q = true;
        r = str;
        final int i2 = p;
        if (!z || Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            R(str, i2);
            return;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        s.post(new Runnable() { // from class: dd7
            @Override // java.lang.Runnable
            public final void run() {
                ge7.I(str, i2, countDownLatch);
            }
        });
        try {
            countDownLatch.await(32L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    public final void h1(File file) {
        if (file.isDirectory()) {
            List listListOf = CollectionsKt.listOf(new String[]{"java/", "javax/", "android/", "kotlin/", "dalvik/", "org/w3c/", "org/xml/", "org/json/"});
            for (File file2 : SequencesKt.toList(SequencesKt.filter(FilesKt.walkTopDown(file), new Function1() { // from class: jd7
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(ge7.a((File) obj));
                }
            }))) {
                String path = FilesKt.relativeTo(file2, file).getPath();
                path.getClass();
                String strReplace$default = StringsKt.replace$default(path, '\\', '/', false, 4, (Object) null);
                List list = listListOf;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator it = list.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (StringsKt.startsWith$default(strReplace$default, (String) it.next(), false, 2, (Object) null)) {
                            }
                        }
                    }
                }
                if (!Intrinsics.areEqual(StringsKt.substringBefore$default(FilesKt.getNameWithoutExtension(file2), '$', (String) null, 2, (Object) null), "R")) {
                    file2.delete();
                }
            }
        }
    }

    public final boolean i0(final File file, List list, File file2, File file3, int i2) {
        ge7 ge7Var;
        Throwable th;
        try {
            try {
                final List listListOf = CollectionsKt.listOf(new String[]{"java/", "javax/", "android/", "kotlin/", "dalvik/", "org/w3c/", "org/xml/", "org/json/"});
                List list2 = SequencesKt.toList(SequencesKt.filter(SequencesKt.filter(FilesKt.walkTopDown(file), new Function1() { // from class: od7
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(ge7.o((File) obj));
                    }
                }), new Function1() { // from class: pd7
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(ge7.v(file, listListOf, (File) obj));
                    }
                }));
                if (list2.isEmpty()) {
                    try {
                        l0("[Error] 增量编译：没有业务类\n");
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        ge7Var = this;
                    }
                } else {
                    FilesKt.deleteRecursively(file3);
                    file3.mkdirs();
                    ge7Var = this;
                    try {
                        return ge7Var.k0(list2, list, file2, file3, i2, new g());
                    } catch (OutOfMemoryError unused) {
                        ge7Var.l0("[Error] 增量 DEX 内存不足\n");
                        System.gc();
                        return false;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                ge7Var = this;
            }
        } catch (OutOfMemoryError unused2) {
            ge7Var = this;
        }
        th = th;
        ge7Var.l0("[Error] 增量 D8 失败：" + th.getClass().getSimpleName() + ": " + th.getMessage() + "\n");
        return false;
    }

    public final void i1(String str) {
        StringBuilder sb = b;
        synchronized (sb) {
            try {
                int iLastIndexOf$default = StringsKt.lastIndexOf$default(sb, '\n', 0, false, 6, (Object) null) + 1;
                if (iLastIndexOf$default < sb.length()) {
                    sb.delete(iLastIndexOf$default, sb.length());
                }
                sb.append(str);
                ArrayList arrayList = c;
                if (arrayList.isEmpty()) {
                    arrayList.add(str);
                } else {
                    arrayList.set(CollectionsKt.getLastIndex(arrayList), str);
                }
                d.setValue(new ArrayList(arrayList));
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean j0(final File file, File file2, List list, final int i2) {
        try {
            final List listListOf = CollectionsKt.listOf(new String[]{"java/", "javax/", "android/", "kotlin/", "dalvik/", "org/w3c/", "org/xml/", "org/json/"});
            List list2 = SequencesKt.toList(SequencesKt.filter(SequencesKt.filter(FilesKt.walkTopDown(file), new Function1() { // from class: yc7
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(ge7.d((File) obj));
                }
            }), new Function1() { // from class: zc7
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(ge7.f(file, listListOf, (File) obj));
                }
            }));
            if (list2.isEmpty()) {
                Log.e("JavaCompilerEngine", "No user .class files found in " + file);
                return false;
            }
            final h hVar = new h();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList<File> arrayList3 = new ArrayList();
            for (Object obj : list) {
                if (!a.x0((File) obj, i2)) {
                    arrayList3.add(obj);
                }
            }
            if (!arrayList3.isEmpty()) {
                ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(RangesKt.coerceIn(Runtime.getRuntime().availableProcessors(), 1, 4));
                final ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
                try {
                    ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
                    for (final File file3 : arrayList3) {
                        arrayList4.add(executorServiceNewFixedThreadPool.submit(new Runnable() { // from class: ad7
                            @Override // java.lang.Runnable
                            public final void run() {
                                ge7.s(file3, i2, hVar, concurrentLinkedQueue);
                            }
                        }));
                    }
                    Iterator it = arrayList4.iterator();
                    while (it.hasNext()) {
                        ((Future) it.next()).get();
                    }
                    executorServiceNewFixedThreadPool.shutdown();
                    Throwable th = (Throwable) concurrentLinkedQueue.peek();
                    if (th != null) {
                        throw th;
                    }
                } catch (Throwable th2) {
                    executorServiceNewFixedThreadPool.shutdown();
                    throw th2;
                }
            }
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                File fileE0 = E0((File) it2.next(), i2);
                File[] fileArrListFiles = fileE0.listFiles(new FileFilter() { // from class: bd7
                    @Override // java.io.FileFilter
                    public final boolean accept(File file4) {
                        return ge7.r(file4);
                    }
                });
                if (fileArrListFiles != null) {
                    CollectionsKt.addAll(arrayList, fileArrListFiles);
                }
                arrayList2.addAll(lq3.a.e(fileE0));
            }
            File file4 = new File(file2.getParentFile(), "user-dex");
            FilesKt.deleteRecursively(file4);
            file4.mkdirs();
            D8Command.Builder globalSyntheticsConsumer = D8Command.builder(hVar).setOutput(file4.toPath(), OutputMode.DexIndexed).setMinApiLevel(i2).setIntermediate(true).setGlobalSyntheticsConsumer(new lq3.a(file4, "user"));
            Iterator it3 = list2.iterator();
            while (it3.hasNext()) {
                globalSyntheticsConsumer.addProgramFiles(new Path[]{((File) it3.next()).toPath()});
            }
            D8.run(globalSyntheticsConsumer.build());
            File[] fileArrListFiles2 = file4.listFiles(new FileFilter() { // from class: cd7
                @Override // java.io.FileFilter
                public final boolean accept(File file5) {
                    return ge7.g(file5);
                }
            });
            if (fileArrListFiles2 != null) {
                CollectionsKt.addAll(arrayList, fileArrListFiles2);
            }
            arrayList2.addAll(lq3.a.e(file4));
            D8Command.Builder disableDesugaring = D8Command.builder(hVar).setOutput(file2.toPath(), OutputMode.DexIndexed).setMinApiLevel(i2).setIntermediate(false).setDisableDesugaring(true);
            Iterator it4 = arrayList.iterator();
            while (it4.hasNext()) {
                disableDesugaring.addProgramFiles(new Path[]{((File) it4.next()).toPath()});
            }
            D8.run(disableDesugaring.build());
            if (lq3.a.d(arrayList2, file2, i2, hVar)) {
                return true;
            }
            l0("[Error] 全局合成类 dex 生成失败\n");
            return false;
        } catch (OutOfMemoryError e2) {
            Log.e("JavaCompilerEngine", "d8 OOM", e2);
            l0("[Error] DEX 内存不足：依赖过多导致 D8 内存峰值超限\n");
            System.gc();
            return false;
        } catch (Throwable th3) {
            Log.e("JavaCompilerEngine", "d8 conversion failed", th3);
            l0("[Error] D8 异常：" + th3.getClass().getSimpleName() + ": " + th3.getMessage() + "\n");
            return false;
        }
    }

    public final void j1(File file, String str) {
        if (file.isFile()) {
            try {
                Result.Companion companion = Result.Companion;
                if (Intrinsics.areEqual(FilesKt.readText$default(file, (Charset) null, 1, (Object) null), str)) {
                    return;
                } else {
                    Result.constructor-impl(Unit.INSTANCE);
                }
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                Result.constructor-impl(ResultKt.createFailure(th));
            }
        }
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        FilesKt.writeText$default(file, str, (Charset) null, 2, (Object) null);
    }

    public final boolean k0(List list, List list2, File file, File file2, int i2, DiagnosticsHandler diagnosticsHandler) {
        List listF = n11.a.f(file, list2);
        D8Command.Builder intermediate = D8Command.builder(diagnosticsHandler).setOutput(file2.toPath(), OutputMode.DexIndexed).setMinApiLevel(i2).setIntermediate(false);
        Iterator it = listF.iterator();
        while (it.hasNext()) {
            intermediate.addProgramFiles(new Path[]{((File) it.next()).toPath()});
        }
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            intermediate.addProgramFiles(new Path[]{((File) it2.next()).toPath()});
        }
        Iterator it3 = list2.iterator();
        while (it3.hasNext()) {
            intermediate.addClasspathFiles(new Path[]{((File) it3.next()).toPath()});
        }
        D8.run(intermediate.build());
        File[] fileArrListFiles = file2.listFiles(new FileFilter() { // from class: qd7
            @Override // java.io.FileFilter
            public final boolean accept(File file3) {
                return ge7.p(file3);
            }
        });
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            return true;
        }
        l0("[Error] 代码槽 dex 未生成\n");
        return false;
    }

    public final void l0(String str) {
        if (k) {
            Function1 function1 = j;
            if (function1 != null) {
                function1.invoke(str);
                return;
            }
            return;
        }
        StringBuilder sb = b;
        synchronized (sb) {
            try {
                if (sb.length() > 0 && StringsKt.last(sb) != '\n' && StringsKt.startsWith$default(str, "[", false, 2, (Object) null)) {
                    sb.append('\n');
                    a.Q("\n");
                }
                sb.append(str);
                a.Q(str);
                if (sb.length() > 200000) {
                    int length = sb.length() - 200000;
                    int iIndexOf = sb.indexOf("\n", length);
                    if (iIndexOf >= 0) {
                        length = iIndexOf + 1;
                    }
                    sb.delete(0, length);
                    ArrayList arrayList = c;
                    arrayList.clear();
                    arrayList.addAll(StringsKt.split$default(sb, new String[]{"\n"}, false, 0, 6, (Object) null));
                }
                d.setValue(new ArrayList(c));
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
        Function1 function2 = j;
        if (function2 != null) {
            function2.invoke(str);
        }
    }

    public final void m0(int i2) {
        String str;
        int i3 = i2 % 6;
        if (i3 == 0) {
            str = "[Error] aapt2 link failed: error: resource android:attr/lStar not found.\n";
        } else if (i3 == 1) {
            str = "[Error] D8: Type com.android.tools.r8.internal.Nz is defined multiple times:\n  /data/user/0/com.yimaide.app/cache/dex/classes.dex\n  /data/user/0/com.yimaide.app/cache/dex/classes2.dex\n";
        } else if (i3 == 2) {
            str = "[Error] Failed to merge dex: Cannot fit requested classes in a single dex file (# methods: 65537 > 65536)\n";
        } else if (i3 != 3) {
            str = i3 != 4 ? "[Error] Manifest merger failed: Attribute application@allowBackup value=(true) from AndroidManifest.xml\n is also present at [androidx.core:core:1.13.0] value=(false)\n" : "[Error] apksig: JAR entry META-INF/MANIFEST.MF digest mismatch after stripDebug\n";
        } else {
            str = "[Error] zipalign: resources.arsc requires 4-byte alignment (found at offset 0x2e1b)\n";
        }
        l0(str);
    }

    public final File n0(Context context) {
        File file = new File(context.getFilesDir(), "android.jar");
        if (file.exists() && file.length() > 0) {
            return file;
        }
        try {
            InputStream inputStreamOpen = context.getAssets().open("android.jar");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    inputStreamOpen.getClass();
                    ByteStreamsKt.copyTo$default(inputStreamOpen, fileOutputStream, 0, 2, (Object) null);
                    CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
                    CloseableKt.closeFinally(inputStreamOpen, (Throwable) null);
                    return file;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(fileOutputStream, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(inputStreamOpen, th3);
                    throw th4;
                }
            }
        } catch (Exception e2) {
            Log.w("JavaCompilerEngine", "android.jar not available in assets", e2);
            return null;
        }
    }

    public final boolean o0(Context context) throws JSONException {
        sbc sbcVar = sbc.a;
        kwa.b bVarB = sbcVar.b();
        if (bVarB == null) {
            l0("[Error] 缺少打包授权，请重新点击正式打包\n");
            return false;
        }
        String strD = nj0.a.d(context);
        if (strD == null || StringsKt.isBlank(strD)) {
            l0("[Error] 登录已失效，无法进行正式打包\n");
            sbcVar.a();
            return false;
        }
        kwa kwaVar = kwa.a;
        String strC = kwaVar.c(context);
        kwa.a aVarJ = kwaVar.j(strD, bVarB, strC);
        if (aVarJ instanceof kwa.a.b) {
            return p0(strD, bVarB, strC);
        }
        if (!(aVarJ instanceof kwa.a.C0009a)) {
            bu8.a();
            return false;
        }
        kwa.a.C0009a c0009a = (kwa.a.C0009a) aVarJ;
        l0("[Error] 打包授权校验失败：" + c0009a.b() + "\n");
        if (c0009a.a() == 2004 || c0009a.a() == 2002) {
            sbcVar.a();
        }
        return false;
    }

    public final boolean p0(String str, kwa.b bVar, String str2) throws JSONException {
        kwa.a aVarF = kwa.a.f(str, bVar, str2);
        if (aVarF instanceof kwa.a.b) {
            kwa.c cVar = (kwa.c) ((kwa.a.b) aVarF).a();
            tbc$a tbc_aB = tbc.a.b(cVar.a(), cVar.b());
            if (tbc_aB != null && Intrinsics.areEqual(tbc_aB.b(), cVar.c()) && Intrinsics.areEqual(tbc_aB.a(), bVar.a())) {
                ubc.a.d(new ubc.a(cVar.c(), cVar.a(), tbc_aB));
                return true;
            }
            m0(4);
            ubc.a.a();
            return false;
        }
        if (!(aVarF instanceof kwa.a.C0009a)) {
            bu8.a();
            return false;
        }
        m0(5);
        kwa.a.C0009a c0009a = (kwa.a.C0009a) aVarF;
        if (c0009a.a() == 2004 || c0009a.a() == 2002) {
            sbc.a.a();
        } else {
            ubc.a.a();
        }
        return false;
    }

    public final String q0(String str, File file) {
        String string = StringsKt.trim(str).toString();
        if (string.length() == 0) {
            return t92.c(t92.a, "aapt2 资源错误", null, 0, 0, 14, null);
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = StringsKt.split$default(string, new String[]{"\n"}, false, 0, 6, (Object) null).iterator();
        while (it.hasNext()) {
            t92.a aVarI = t92.a.i((String) it.next(), file);
            if (aVarI != null) {
                sb.append(aVarI.a());
                sb.append('\n');
            }
        }
        String string2 = StringsKt.trimEnd(sb.toString()).toString();
        return StringsKt.isBlank(string2) ? t92.c(t92.a, string, null, 0, 0, 14, null) : string2;
    }

    public final String r0(long j2) {
        if (j2 >= 1000000) {
            return String.format("%.1fMB", Arrays.copyOf(new Object[]{Double.valueOf(j2 / 1000000.0d)}, 1));
        }
        if (j2 >= 1000) {
            return String.format("%.0fKB", Arrays.copyOf(new Object[]{Double.valueOf(j2 / 1000.0d)}, 1));
        }
        return j2 + "B";
    }

    public final StateFlow s0() {
        return d;
    }

    public final StateFlow t0() {
        return e;
    }

    public final boolean u0(File file, File file2, File file3) {
        File fileI0 = I0(file, file2, file3);
        return fileI0 != null && fileI0.isFile();
    }

    public final boolean v0(File file, File file2) {
        boolean z;
        boolean z2;
        if (file.isDirectory() && file2.isDirectory()) {
            Iterator it = FilesKt.walkTopDown(file).iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                File file3 = (File) it.next();
                if (file3.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file3), "class")) {
                    z = true;
                    break;
                }
            }
            Iterator it2 = FilesKt.walkTopDown(file2).iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z2 = false;
                    break;
                }
                File file4 = (File) it2.next();
                if (file4.isFile() && Intrinsics.areEqual(file4.getName(), "R.java")) {
                    z2 = true;
                    break;
                }
            }
            if (z && z2) {
                return true;
            }
        }
        return false;
    }

    public final boolean w0(String str) {
        return t.contains(StringsKt.substringBefore$default(StringsKt.substringAfterLast$default(StringsKt.removeSuffix(str, JavaClass.EXTENSION), '/', (String) null, 2, (Object) null), '$', (String) null, 2, (Object) null));
    }

    public final boolean x0(File file, int i2) {
        File file2 = new File(E0(file, i2), ".ok");
        return file2.isFile() && file2.lastModified() >= file.lastModified();
    }

    public final boolean y0() {
        boolean z = false;
        if (!h) {
            return false;
        }
        StringBuilder sb = b;
        synchronized (sb) {
            if (sb.length() > 0 && StringsKt.last(sb) != '\n') {
                z = true;
            }
        }
        if (z) {
            l0("\n");
        }
        l0("[Build] 已取消\n");
        return true;
    }

    public final boolean z0(File file) {
        String absolutePath = file.getAbsolutePath();
        absolutePath.getClass();
        String strReplace$default = StringsKt.replace$default(absolutePath, '\\', '/', false, 4, (Object) null);
        return StringsKt.contains$default(strReplace$default, "/backend/", false, 2, (Object) null) || StringsKt.endsWith$default(strReplace$default, "/backend", false, 2, (Object) null);
    }

    public static final /* synthetic */ class j extends FunctionReferenceImpl implements Function1 {
        public j(Object obj) {
            super(1, obj, ge7.class, "emit", "emit(Ljava/lang/String;)V", 0);
        }

        public final void invoke(String str) {
            str.getClass();
            ((ge7) ((CallableReference) this).receiver).l0(str);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((String) obj);
            return Unit.INSTANCE;
        }
    }
}
