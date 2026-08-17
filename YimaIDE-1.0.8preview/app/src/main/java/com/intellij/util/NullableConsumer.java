package com.intellij.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@FunctionalInterface
public interface NullableConsumer<T> extends Consumer<T>, java.util.function.Consumer<T> {
    @Override // com.intellij.util.Consumer, java.util.function.Consumer
    default void accept(T t) {
        consume(t);
    }

    @Override // com.intellij.util.Consumer
    void consume(T t);
}
