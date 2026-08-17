package com.intellij.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@FunctionalInterface
public interface ParameterizedRunnable<T> extends java.util.function.Consumer<T> {
    @Override // java.util.function.Consumer
    default void accept(T t) {
        run(t);
    }

    void run(T t);
}
