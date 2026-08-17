package com.intellij.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@FunctionalInterface
public interface NotNullFunction<Param, Result> extends NullableFunction<Param, Result>, java.util.function.Function<Param, Result> {
    @Override // com.intellij.util.NullableFunction, com.intellij.util.Function
    Result fun(Param param);
}
