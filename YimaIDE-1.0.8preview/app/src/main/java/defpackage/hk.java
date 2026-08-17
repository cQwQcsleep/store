package defpackage;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class hk {
    public static final hk a = new hk();
    public static final AtomicBoolean b = new AtomicBoolean(false);
    public static final AtomicReference c = new AtomicReference(null);
    public static final int d = 8;

    public static final class a {
        public final int a;
        public final String b;
        public final String c;

        public a(int i, String str, String str2) {
            str.getClass();
            str2.getClass();
            this.a = i;
            this.b = str;
            this.c = str2;
        }

        public final boolean a() {
            return this.a == -4;
        }

        public final boolean b() {
            return this.a == 0;
        }

        public final String c() {
            return this.b;
        }

        public final String d() {
            String str = this.c;
            if (StringsKt.isBlank(str)) {
                str = this.b;
            }
            return StringsKt.trim(str).toString();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && Intrinsics.areEqual(this.b, aVar.b) && Intrinsics.areEqual(this.c, aVar.c);
        }

        public int hashCode() {
            return (((Integer.hashCode(this.a) * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
        }

        public String toString() {
            return "ExecResult(exitCode=" + this.a + ", stdout=" + this.b + ", stderr=" + this.c + ")";
        }
    }

    public static Unit a(StringBuilder sb, String str) {
        str.getClass();
        k(sb, str);
        return Unit.INSTANCE;
    }

    public static void b(Ref.ObjectRef objectRef, final StringBuilder sb) {
        try {
            InputStream errorStream = ((Process) objectRef.element).getErrorStream();
            errorStream.getClass();
            TextStreamsKt.forEachLine(new BufferedReader(new InputStreamReader(errorStream, Charsets.UTF_8), 8192), new Function1() { // from class: gk
                public final Object invoke(Object obj) {
                    return hk.d(sb, (String) obj);
                }
            });
        } catch (Throwable unused) {
        }
    }

    public static void c(Ref.ObjectRef objectRef, final StringBuilder sb) {
        try {
            InputStream inputStream = ((Process) objectRef.element).getInputStream();
            inputStream.getClass();
            TextStreamsKt.forEachLine(new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8), 8192), new Function1() { // from class: fk
                public final Object invoke(Object obj) {
                    return hk.a(sb, (String) obj);
                }
            });
        } catch (Throwable unused) {
        }
    }

    public static Unit d(StringBuilder sb, String str) {
        str.getClass();
        k(sb, str);
        return Unit.INSTANCE;
    }

    public static final void k(StringBuilder sb, String str) {
        if (sb.length() >= 262144) {
            return;
        }
        sb.append(str);
        sb.append('\n');
        if (sb.length() >= 262144) {
            sb.append("（输出过长，已截断）\n");
        }
    }

    public static /* synthetic */ a l(hk hkVar, Context context, List list, File file, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            file = null;
        }
        File file2 = file;
        if ((i & 8) != 0) {
            j = 180;
        }
        return hkVar.j(context, list, file2, j);
    }

    public final File e(Context context) {
        context.getClass();
        return new File(context.getApplicationInfo().nativeLibraryDir, "libaapt2.so");
    }

    public final void f() {
        b.set(true);
        Process process = (Process) c.get();
        if (process == null) {
            return;
        }
        try {
            process.destroyForcibly();
            Log.i("Aapt2Runner", "aapt2 process destroyForcibly requested");
        } catch (Throwable th) {
            Log.w("Aapt2Runner", "destroyForcibly failed", th);
        }
    }

    public final void g(Context context) {
        context.getClass();
        try {
            Result.Companion companion = Result.Companion;
            Result.constructor-impl(Boolean.valueOf(FilesKt.deleteRecursively(m(context))));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            Result.constructor-impl(ResultKt.createFailure(th));
        }
    }

    public final void h() {
        b.set(false);
    }

    public final boolean i() {
        return b.get();
    }

    public final a j(Context context, List list, File file, long j) {
        a aVar;
        Process process;
        a aVar2;
        context.getClass();
        list.getClass();
        AtomicBoolean atomicBoolean = b;
        if (atomicBoolean.get()) {
            return new a(-4, "", "aapt2 已取消");
        }
        File fileE = e(context);
        if (!fileE.canExecute()) {
            return new a(-1, "", "aapt2 不可执行: " + fileE.getAbsolutePath() + " (检查 extractNativeLibs / 当前设备 ABI 是否带了 libaapt2.so)");
        }
        ArrayList arrayList = new ArrayList(list.size() + 1);
        arrayList.add(fileE.getAbsolutePath());
        arrayList.addAll(list);
        ProcessBuilder processBuilder = new ProcessBuilder(arrayList);
        if (file != null) {
            processBuilder.directory(file);
        }
        Map<String, String> mapEnvironment = processBuilder.environment();
        mapEnvironment.getClass();
        File fileM = m(context);
        fileM.mkdirs();
        mapEnvironment.put("TMPDIR", fileM.getAbsolutePath());
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        try {
            try {
                if (atomicBoolean.get()) {
                    a aVar3 = new a(-4, "", "aapt2 已取消");
                    Process process2 = (Process) objectRef.element;
                    if (process2 != null) {
                        c.compareAndSet(process2, null);
                    }
                    return aVar3;
                }
                Process processStart = processBuilder.start();
                objectRef.element = processStart;
                Process process3 = (Process) c.getAndSet(processStart);
                if (process3 != null && process3 != objectRef.element) {
                    Log.w("Aapt2Runner", "replacing stale aapt2 process ref");
                    try {
                        Result.Companion companion = Result.Companion;
                        Result.constructor-impl(process3.destroyForcibly());
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.Companion;
                        Result.constructor-impl(ResultKt.createFailure(th));
                    }
                }
                if (b.get()) {
                    try {
                        Result.Companion companion3 = Result.Companion;
                        Result.constructor-impl(((Process) objectRef.element).destroyForcibly());
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.Companion;
                        Result.constructor-impl(ResultKt.createFailure(th2));
                    }
                }
                final StringBuilder sb = new StringBuilder();
                final StringBuilder sb2 = new StringBuilder();
                Thread thread = new Thread(new Runnable() { // from class: dk
                    @Override // java.lang.Runnable
                    public final void run() {
                        hk.c(objectRef, sb);
                    }
                });
                Thread thread2 = new Thread(new Runnable() { // from class: ek
                    @Override // java.lang.Runnable
                    public final void run() {
                        hk.b(objectRef, sb2);
                    }
                });
                thread.start();
                thread2.start();
                if (((Process) objectRef.element).waitFor(j, TimeUnit.SECONDS)) {
                    thread.join(2000L);
                    thread2.join(2000L);
                    if (b.get()) {
                        a aVar4 = new a(-4, sb.toString(), "aapt2 已取消");
                        Process process4 = (Process) objectRef.element;
                        if (process4 != null) {
                            c.compareAndSet(process4, null);
                        }
                        return aVar4;
                    }
                    aVar = new a(((Process) objectRef.element).exitValue(), sb.toString(), sb2.toString());
                    process = (Process) objectRef.element;
                    if (process != null) {
                        c.compareAndSet(process, null);
                    }
                    return aVar;
                }
                ((Process) objectRef.element).destroyForcibly();
                thread.join(3000L);
                thread2.join(3000L);
                if (b.get()) {
                    aVar2 = new a(-4, sb.toString(), "aapt2 已取消");
                } else {
                    aVar2 = new a(-2, sb.toString(), "aapt2 执行超时(" + j + "s)\n" + ((Object) sb2));
                }
                Process process5 = (Process) objectRef.element;
                if (process5 != null) {
                    c.compareAndSet(process5, null);
                }
                return aVar2;
            } catch (Exception e) {
                if (b.get()) {
                    aVar = new a(-4, "", "aapt2 已取消");
                } else {
                    aVar = new a(-3, "", "aapt2 启动失败: " + e.getMessage());
                }
                process = (Process) objectRef.element;
                if (process != null) {
                }
            }
        } catch (Throwable th3) {
            Process process6 = (Process) objectRef.element;
            if (process6 != null) {
                c.compareAndSet(process6, null);
            }
            throw th3;
        }
    }

    public final File m(Context context) {
        return new File(context.getCacheDir(), "aapt2-tmp");
    }
}
