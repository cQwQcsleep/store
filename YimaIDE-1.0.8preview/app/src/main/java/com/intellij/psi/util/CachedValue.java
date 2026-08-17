package com.intellij.psi.util;

import com.intellij.openapi.util.Getter;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface CachedValue<T> {
    Getter<T> getUpToDateOrNull();

    T getValue();

    CachedValueProvider<T> getValueProvider();

    boolean hasUpToDateValue();
}
