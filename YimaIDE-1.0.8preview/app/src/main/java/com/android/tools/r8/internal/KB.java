package com.android.tools.r8.internal;

import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class KB {
    public static Throwable a(RuntimeException runtimeException, String str) {
        StackTraceElement[] stackTrace = runtimeException.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        runtimeException.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i + 1, length));
        return runtimeException;
    }

    public static void b(Object obj, String str) {
        if (obj == null) {
            throw ((NullPointerException) a((RuntimeException) new NullPointerException(str.concat(" must not be null")), KB.class.getName()));
        }
    }

    public static void c(Object obj, String str) {
        if (obj == null) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String name = KB.class.getName();
            int i = 0;
            while (!stackTrace[i].getClassName().equals(name)) {
                i++;
            }
            while (stackTrace[i].getClassName().equals(name)) {
                i++;
            }
            StackTraceElement stackTraceElement = stackTrace[i];
            throw ((NullPointerException) a((RuntimeException) new NullPointerException("Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str), KB.class.getName()));
        }
    }

    public static void a(String str) {
        throw ((C1772ik0) a((RuntimeException) new C1772ik0(C40.a("lateinit property ", str, " has not been initialized")), KB.class.getName()));
    }

    public static void a(Object obj) {
        if (obj == null) {
            throw ((NullPointerException) a((RuntimeException) new NullPointerException(), KB.class.getName()));
        }
    }

    public static void a(Object obj, String str) {
        if (obj == null) {
            throw ((NullPointerException) a((RuntimeException) new NullPointerException(str), KB.class.getName()));
        }
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static int a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }
}
