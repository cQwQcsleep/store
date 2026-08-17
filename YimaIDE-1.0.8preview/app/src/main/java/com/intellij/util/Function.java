package com.intellij.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@FunctionalInterface
public interface Function<Param, Result> extends java.util.function.Function<Param, Result> {

    public interface Mono<T> extends Function<T, T> {
    }

    @Override // java.util.function.Function
    default Result apply(Param param) {
        return fun(param);
    }

    Result fun(Param param);
}
