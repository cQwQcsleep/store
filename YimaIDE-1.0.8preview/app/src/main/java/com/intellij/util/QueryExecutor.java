package com.intellij.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@FunctionalInterface
public interface QueryExecutor<Result, Param> {
    boolean execute(Param param, Processor<? super Result> processor);
}
