package com.shadow.kotlin;

import java.io.Serializable;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class InitializedLazyImpl<T> implements kotlin.Lazy<T>, Serializable {
    private final T value;

    public InitializedLazyImpl(T t) {
        this.value = t;
    }

    public T getValue() {
        return this.value;
    }

    public boolean isInitialized() {
        return true;
    }

    public String toString() {
        return String.valueOf(getValue());
    }
}
