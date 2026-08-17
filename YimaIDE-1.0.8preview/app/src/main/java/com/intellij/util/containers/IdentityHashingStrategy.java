package com.intellij.util.containers;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class IdentityHashingStrategy<T> implements HashingStrategy<T> {
    static final HashingStrategy<?> INSTANCE = new IdentityHashingStrategy();

    @Override // com.intellij.util.containers.HashingStrategy
    public boolean equals(T t, T t2) {
        return t == t2;
    }

    @Override // com.intellij.util.containers.HashingStrategy
    public int hashCode(T t) {
        return System.identityHashCode(t);
    }
}
