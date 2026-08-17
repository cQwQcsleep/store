package com.intellij.util.containers;

import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class CanonicalHashingStrategy<T> implements HashingStrategy<T> {
    static final HashingStrategy<?> INSTANCE = new CanonicalHashingStrategy();

    @Override // com.intellij.util.containers.HashingStrategy
    public boolean equals(T t, T t2) {
        return Objects.equals(t, t2);
    }

    @Override // com.intellij.util.containers.HashingStrategy
    public int hashCode(T t) {
        return Objects.hashCode(t);
    }
}
