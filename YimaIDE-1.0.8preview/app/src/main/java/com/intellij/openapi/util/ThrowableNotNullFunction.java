package com.intellij.openapi.util;

import java.lang.Throwable;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@FunctionalInterface
public interface ThrowableNotNullFunction<T, R, E extends Throwable> {
    R fun(T t) throws Throwable;
}
