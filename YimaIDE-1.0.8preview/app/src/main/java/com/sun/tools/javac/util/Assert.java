package com.sun.tools.javac.util;

import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Assert {
    private Assert() {
    }

    public static void check(boolean z, Supplier<String> supplier) {
        if (z) {
            return;
        }
        error(supplier.get());
    }

    public static <T> T checkNonNull(T t, Supplier<String> supplier) {
        if (t == null) {
            error(supplier.get());
        }
        return t;
    }

    public static void checkNull(Object obj, Supplier<String> supplier) {
        if (obj != null) {
            error(supplier.get());
        }
    }

    public static Error error() {
        throw new AssertionError();
    }

    public static Error error(String str) {
        throw new AssertionError(str);
    }

    public static void check(boolean z, int i) {
        if (z) {
            return;
        }
        error(String.valueOf(i));
    }

    public static <T> T checkNonNull(T t, String str) {
        if (t == null) {
            error(str);
        }
        return t;
    }

    public static void checkNull(Object obj, Object obj2) {
        if (obj != null) {
            error(String.valueOf(obj2));
        }
    }

    public static void check(boolean z, long j) {
        if (z) {
            return;
        }
        error(String.valueOf(j));
    }

    public static <T> T checkNonNull(T t) {
        if (t == null) {
            error();
        }
        return t;
    }

    public static void checkNull(Object obj, String str) {
        if (obj != null) {
            error(str);
        }
    }

    public static void check(boolean z, Object obj) {
        if (z) {
            return;
        }
        error(String.valueOf(obj));
    }

    public static void checkNull(Object obj) {
        if (obj != null) {
            error();
        }
    }

    public static void check(boolean z, String str) {
        if (z) {
            return;
        }
        error(str);
    }

    public static void check(boolean z) {
        if (z) {
            return;
        }
        error();
    }
}
