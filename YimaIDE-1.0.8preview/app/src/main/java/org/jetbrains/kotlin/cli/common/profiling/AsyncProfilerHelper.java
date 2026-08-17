package org.jetbrains.kotlin.cli.common.profiling;

import java.io.File;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.net.URL;
import java.net.URLClassLoader;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0016\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/profiling/AsyncProfilerHelper;", Argument.Delimiters.none, "<init>", "()V", "instance", "Lorg/jetbrains/kotlin/cli/common/profiling/AsyncProfilerReflected;", "getInstance", "libPath", Argument.Delimiters.none, "loadAsyncProfilerClass", "Ljava/lang/Class;", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AsyncProfilerHelper {
    public static final AsyncProfilerHelper INSTANCE = new AsyncProfilerHelper();
    private static AsyncProfilerReflected instance;

    private AsyncProfilerHelper() {
    }

    private final Class<?> loadAsyncProfilerClass(String libPath) throws ClassNotFoundException {
        try {
            return Class.forName("one.profiler.AsyncProfiler");
        } catch (ClassNotFoundException e) {
            if (libPath == null) {
                throw e;
            }
            File parentFile = new File(libPath).getParentFile();
            if (!parentFile.isDirectory()) {
                mx5.a(parentFile);
                return null;
            }
            File fileResolve = FilesKt.resolve(parentFile, "async-profiler.jar");
            if (!fileResolve.exists()) {
                w04.a("To use async-profiler, either add it to the compiler classpath, or put async-profiler.jar at this path: ", fileResolve);
                return null;
            }
            Class<?> clsLoadClass = new URLClassLoader(new URL[]{fileResolve.toURI().toURL()}, null).loadClass("one.profiler.AsyncProfiler");
            clsLoadClass.getClass();
            return clsLoadClass;
        }
    }

    public final AsyncProfilerReflected getInstance(String libPath) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        AsyncProfilerReflected asyncProfilerReflected = instance;
        if (asyncProfilerReflected != null) {
            return asyncProfilerReflected;
        }
        Class<?> clsLoadAsyncProfilerClass = loadAsyncProfilerClass(libPath);
        MethodHandle methodHandleFindStatic = MethodHandles.lookup().findStatic(clsLoadAsyncProfilerClass, "getInstance", MethodType.methodType(clsLoadAsyncProfilerClass, (Class<?>) String.class));
        AsyncProfilerReflected asyncProfilerReflected2 = new AsyncProfilerReflected(MethodHandles.lookup().findVirtual(clsLoadAsyncProfilerClass, "execute", MethodType.methodType((Class<?>) String.class, (Class<?>) String.class)), methodHandleFindStatic.invokeWithArguments(libPath), MethodHandles.lookup().findVirtual(clsLoadAsyncProfilerClass, "stop", MethodType.methodType(Void.TYPE)), MethodHandles.lookup().findVirtual(clsLoadAsyncProfilerClass, "getVersion", MethodType.methodType(String.class))) { // from class: org.jetbrains.kotlin.cli.common.profiling.AsyncProfilerHelper.getInstance.2
            private final MethodHandle boundExecute;
            private final MethodHandle boundGetVersion;
            private final MethodHandle boundStop;

            {
                this.boundExecute = methodHandle.bindTo(obj);
                this.boundStop = methodHandle.bindTo(obj);
                this.boundGetVersion = methodHandle.bindTo(obj);
            }

            @Override // org.jetbrains.kotlin.cli.common.profiling.AsyncProfilerReflected
            public String execute(String command) throws Throwable {
                command.getClass();
                Object objInvokeWithArguments = this.boundExecute.invokeWithArguments(command);
                objInvokeWithArguments.getClass();
                return (String) objInvokeWithArguments;
            }

            @Override // org.jetbrains.kotlin.cli.common.profiling.AsyncProfilerReflected
            public String getVersion() throws Throwable {
                Object objInvokeWithArguments = this.boundGetVersion.invokeWithArguments(new Object[0]);
                objInvokeWithArguments.getClass();
                return (String) objInvokeWithArguments;
            }

            @Override // org.jetbrains.kotlin.cli.common.profiling.AsyncProfilerReflected
            public void stop() throws Throwable {
                this.boundStop.invokeWithArguments(new Object[0]);
            }
        };
        instance = asyncProfilerReflected2;
        return asyncProfilerReflected2;
    }
}
