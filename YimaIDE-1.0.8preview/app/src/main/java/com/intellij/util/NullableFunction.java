package com.intellij.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@FunctionalInterface
public interface NullableFunction<Param, Result> extends Function<Param, Result>, java.util.function.Function<Param, Result> {
    @Override // com.intellij.util.Function, java.util.function.Function
    default Result apply(Param param) {
        return fun(param);
    }

    @Override // com.intellij.util.Function
    Result fun(Param param);
}
