package com.intellij.util;

import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@FunctionalInterface
@Deprecated
public interface BooleanFunction<S> extends Predicate<S> {
    boolean fun(S s);

    @Override // java.util.function.Predicate
    default boolean test(S s) {
        return fun(s);
    }
}
