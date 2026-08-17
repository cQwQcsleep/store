package org.jetbrains.kotlin.utils;

import com.intellij.openapi.diagnostic.Logger;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;
import org.jline.terminal.TerminalBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class Profiler {
    private long cumulative;
    private String formatString;
    private final Logger log;
    private boolean mute;
    private final String name;
    private boolean paused;
    private StackTraceElement[] stackTrace;
    private long start;
    private static final ThreadLocal<Stack<Profiler>> PROFILERS = new ThreadLocal<Stack<Profiler>>() { // from class: org.jetbrains.kotlin.utils.Profiler.1
        @Override // java.lang.ThreadLocal
        public Stack<Profiler> initialValue() {
            return new Stack<>();
        }
    };
    private static final ReentrantLock OUT_LOCK = new ReentrantLock();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 5 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 5 ? 3 : 2];
        if (i == 2) {
            objArr[0] = TerminalBuilder.PROP_OUTPUT_OUT;
        } else if (i == 7 || i == 4) {
            objArr[0] = "log";
        } else if (i != 5) {
            objArr[0] = "name";
        } else {
            objArr[0] = "org/jetbrains/kotlin/utils/Profiler";
        }
        if (i != 5) {
            objArr[1] = "org/jetbrains/kotlin/utils/Profiler";
        } else {
            objArr[1] = "create";
        }
        if (i != 5) {
            if (i == 6 || i == 7) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "create";
            }
        }
        String str2 = String.format(str, objArr);
        if (i == 5) {
            throw new IllegalStateException(str2);
        }
    }

    private Profiler(String str, Logger logger) {
        if (str == null) {
            $$$reportNull$$$0(6);
        }
        if (logger == null) {
            $$$reportNull$$$0(7);
        }
        this.start = Long.MAX_VALUE;
        this.cumulative = 0L;
        this.paused = true;
        this.name = str;
        this.log = logger;
        setPrintAccuracy(3);
    }

    public static Profiler create(String str, Logger logger) {
        if (str == null) {
            $$$reportNull$$$0(3);
        }
        if (logger == null) {
            $$$reportNull$$$0(4);
        }
        Profiler profiler = new Profiler(str, logger);
        PROFILERS.get().push(profiler);
        return profiler;
    }

    private String format(long j) {
        return String.format(this.formatString, Double.valueOf(j / 1.0E9d));
    }

    public static Profiler getFromContext() {
        Stack<Profiler> stack = PROFILERS.get();
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        a9g.a();
        return null;
    }

    public Profiler end() {
        long jNanoTime = this.cumulative;
        if (!this.paused) {
            jNanoTime += System.nanoTime() - this.start;
        }
        this.paused = true;
        this.cumulative = 0L;
        if (this.mute || !this.log.isDebugEnabled()) {
            return this;
        }
        OUT_LOCK.lock();
        try {
            println(this.name, " took ", format(jNanoTime));
            printStackTrace();
            return this;
        } finally {
            OUT_LOCK.unlock();
        }
    }

    public long getCumulative() {
        return this.cumulative;
    }

    public Profiler mute() {
        this.mute = true;
        return this;
    }

    public Profiler pause() {
        if (!this.paused) {
            this.cumulative += System.nanoTime() - this.start;
            this.paused = true;
        }
        return this;
    }

    public Profiler printEntering() {
        println("Entering ", this.name);
        return this;
    }

    public Profiler printStackTrace() {
        if (this.stackTrace == null || !this.log.isDebugEnabled()) {
            return this;
        }
        OUT_LOCK.lock();
        try {
            for (StackTraceElement stackTraceElement : this.stackTrace) {
                println("\tat ", stackTraceElement);
            }
            OUT_LOCK.unlock();
            return this;
        } catch (Throwable th) {
            OUT_LOCK.unlock();
            throw th;
        }
    }

    public Profiler printThreadName() {
        println(Thread.currentThread().getName() + " ", this.name);
        return this;
    }

    public Profiler println(Object obj, Object obj2, Object obj3, Object... objArr) {
        if (this.mute || !this.log.isDebugEnabled()) {
            return this;
        }
        OUT_LOCK.lock();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(obj);
            sb.append(obj2);
            sb.append(obj3);
            for (Object obj4 : objArr) {
                sb.append(obj4);
            }
            this.log.debug(sb.toString());
            return this;
        } finally {
            OUT_LOCK.unlock();
        }
    }

    public Profiler recordStackTrace(int i, int i2) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int i3 = i + 1;
        if (i3 >= stackTrace.length) {
            return this;
        }
        this.stackTrace = (StackTraceElement[]) Arrays.copyOfRange(stackTrace, i3, i2 == -1 ? stackTrace.length : Math.min(i + i2 + 1, stackTrace.length));
        return this;
    }

    public Profiler resetStackTrace() {
        this.stackTrace = null;
        return this;
    }

    public Profiler setPrintAccuracy(int i) {
        this.formatString = "%." + i + "fs";
        return this;
    }

    public Profiler start() {
        if (this.paused) {
            this.start = System.nanoTime();
            this.paused = false;
        }
        return this;
    }

    public Profiler unmute() {
        this.mute = false;
        return this;
    }

    public static Profiler create(String str, PrintStream printStream) {
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        if (printStream == null) {
            $$$reportNull$$$0(2);
        }
        return create(str, (Logger) new PrintingLogger(printStream));
    }

    public static Profiler create(String str) {
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        return create(str, System.out);
    }

    public Profiler recordStackTrace(int i) {
        return recordStackTrace(1, i);
    }

    public Profiler println(Object obj, Object obj2) {
        if (this.mute || !this.log.isDebugEnabled()) {
            return this;
        }
        OUT_LOCK.lock();
        try {
            this.log.debug(String.valueOf(obj) + obj2);
            return this;
        } finally {
            OUT_LOCK.unlock();
        }
    }

    public Profiler println(Object obj, Object obj2, Object obj3) {
        if (this.mute || !this.log.isDebugEnabled()) {
            return this;
        }
        OUT_LOCK.lock();
        try {
            this.log.debug(String.valueOf(obj) + obj2 + obj3);
            return this;
        } finally {
            OUT_LOCK.unlock();
        }
    }

    public Profiler println(Object obj) {
        if (!this.mute && this.log.isDebugEnabled()) {
            this.log.debug(String.valueOf(obj));
        }
        return this;
    }
}
