package com.intellij.util;

import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@FunctionalInterface
public interface Producer<T> extends Supplier<T> {
    @Override // java.util.function.Supplier
    default T get() {
        return produce();
    }

    T produce();
}
