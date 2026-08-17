package com.intellij.openapi.util;

import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class ThreadLocalCachedValue<T> {
    private final ThreadLocal<SoftReference<T>> myThreadLocal = new ThreadLocal<>();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/intellij/openapi/util/ThreadLocalCachedValue", "init"));
    }

    public abstract T create();

    public T getValue() {
        T t = (T) com.intellij.reference.SoftReference.dereference(this.myThreadLocal.get());
        if (t != null) {
            init(t);
            return t;
        }
        T tCreate = create();
        this.myThreadLocal.set(new SoftReference<>(tCreate));
        return tCreate;
    }

    public void init(T t) {
        if (t == null) {
            $$$reportNull$$$0(0);
        }
    }
}
