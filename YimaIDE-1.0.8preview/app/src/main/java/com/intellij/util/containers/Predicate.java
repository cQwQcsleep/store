package com.intellij.util.containers;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@FunctionalInterface
@Deprecated
public interface Predicate<T> extends java.util.function.Predicate<T> {
    boolean apply(T t);

    @Override // java.util.function.Predicate
    default boolean test(T t) {
        return apply(t);
    }
}
