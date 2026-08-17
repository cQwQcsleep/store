package org.jline.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.jline.utils.Log;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public final class Log {
    private static final Logger logger = Logger.getLogger("org.jline");

    /* JADX WARN: Code duplicated, block: B:10:0x001e  */
    public static LogRecord createRecord(Level level, Object... objArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        Throwable th = null;
        int i = 0;
        while (i < objArr.length) {
            int i2 = i + 1;
            if (i2 == objArr.length) {
                Object obj = objArr[i];
                if (obj instanceof Throwable) {
                    th = (Throwable) obj;
                } else {
                    render(printStream, objArr[i]);
                }
            } else {
                render(printStream, objArr[i]);
            }
            i = i2;
        }
        printStream.close();
        LogRecord logRecord = new LogRecord(level, byteArrayOutputStream.toString());
        logRecord.setThrown(th);
        return logRecord;
    }

    public static void debug(Supplier<String> supplier) {
        log(Level.FINE, supplier);
    }

    public static void info(Object... objArr) {
        log(Level.INFO, objArr);
    }

    public static boolean isDebugEnabled() {
        return isEnabled(Level.FINE);
    }

    public static boolean isEnabled(Level level) {
        return logger.isLoggable(level);
    }

    public static void log(final Level level, final Supplier<String> supplier) {
        logr(level, new Supplier() { // from class: uf9
            @Override // java.util.function.Supplier
            public final Object get() {
                return Log.createRecord(level, (Supplier<String>) supplier);
            }
        });
    }

    public static void logr(Level level, Supplier<LogRecord> supplier) {
        Logger logger2 = logger;
        if (logger2.isLoggable(level)) {
            LogRecord logRecord = supplier.get();
            logRecord.setLoggerName(logger2.getName());
            logger2.log(logRecord);
        }
    }

    public static void render(PrintStream printStream, Object obj) {
        if (obj == null || !obj.getClass().isArray()) {
            printStream.print(obj);
            return;
        }
        Object[] objArr = (Object[]) obj;
        printStream.print("[");
        int i = 0;
        while (i < objArr.length) {
            printStream.print(objArr[i]);
            i++;
            if (i < objArr.length) {
                printStream.print(",");
            }
        }
        printStream.print("]");
    }

    public static void trace(Object... objArr) {
        log(Level.FINEST, objArr);
    }

    public static void warn(Object... objArr) {
        log(Level.WARNING, objArr);
    }

    public static void debug(Object... objArr) {
        log(Level.FINE, objArr);
    }

    public static void log(final Level level, final Object... objArr) {
        logr(level, new Supplier() { // from class: rf9
            @Override // java.util.function.Supplier
            public final Object get() {
                return Log.createRecord(level, objArr);
            }
        });
    }

    public static LogRecord createRecord(Level level, Supplier<String> supplier) {
        return new LogRecord(level, supplier.get());
    }
}
