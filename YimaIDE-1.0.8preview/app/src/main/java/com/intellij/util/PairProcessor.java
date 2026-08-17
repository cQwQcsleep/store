package com.intellij.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@FunctionalInterface
public interface PairProcessor<S, T> {
    boolean process(S s, T t);
}
