package com.intellij.util;

import java.util.function.BiFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@FunctionalInterface
public interface PairFunction<Arg1, Arg2, ResultType> extends BiFunction<Arg1, Arg2, ResultType> {
    @Override // java.util.function.BiFunction
    default ResultType apply(Arg1 arg1, Arg2 arg2) {
        return fun(arg1, arg2);
    }

    ResultType fun(Arg1 arg1, Arg2 arg2);
}
