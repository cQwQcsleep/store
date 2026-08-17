package org.eclipse.tm4e.core.internal.utils;

import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class NullSafetyHelper {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    private NullSafetyHelper() {
    }

    public static <T> T castNonNull(T t) {
        return t;
    }

    private static <T> T castNonNullUnsafe(T t) {
        return t;
    }

    public static <T> T castNullable(T t) {
        return t;
    }

    public static <T> T defaultIfNull(T t, Supplier<T> supplier) {
        return t == null ? supplier.get() : t;
    }

    public static <T> T lazyNonNull() {
        return (T) castNonNullUnsafe(null);
    }

    public static <T> T defaultIfNull(T t, T t2) {
        return t == null ? t2 : t;
    }
}
