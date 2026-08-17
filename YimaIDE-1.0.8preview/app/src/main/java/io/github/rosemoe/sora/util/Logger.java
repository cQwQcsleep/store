package io.github.rosemoe.sora.util;

import android.util.Log;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Logger {
    private static final Map<String, Logger> map = new WeakHashMap();
    private final String name;

    private Logger(String str) {
        this.name = str;
    }

    public static synchronized Logger instance(String str) {
        Logger logger;
        Map<String, Logger> map2 = map;
        logger = map2.get(str);
        if (logger == null) {
            logger = new Logger(str);
            map2.put(str, logger);
        }
        return logger;
    }

    public void d(String str, Object... objArr) {
        Log.d(this.name, String.format(str, objArr));
    }

    public void e(String str, Object... objArr) {
        Log.e(this.name, String.format(str, objArr));
    }

    public void i(String str, Object... objArr) {
        Log.i(this.name, String.format(str, objArr));
    }

    public void v(String str, Object... objArr) {
        Log.v(this.name, String.format(str, objArr));
    }

    public void w(String str, Object... objArr) {
        Log.w(this.name, String.format(str, objArr));
    }

    public void d(String str) {
        Log.d(this.name, str);
    }

    public void e(String str) {
        Log.e(this.name, str);
    }

    public void i(String str) {
        Log.i(this.name, str);
    }

    public void v(String str) {
        Log.v(this.name, str);
    }

    public void w(String str) {
        Log.w(this.name, str);
    }

    public void e(String str, Throwable th) {
        Log.e(this.name, str, th);
    }

    public void w(String str, Throwable th) {
        Log.w(this.name, str, th);
    }

    public void e(String str, Throwable th, Object... objArr) {
        Log.e(this.name, String.format(str, objArr), th);
    }

    public void w(String str, Throwable th, Object... objArr) {
        Log.w(this.name, String.format(str, objArr), th);
    }
}
