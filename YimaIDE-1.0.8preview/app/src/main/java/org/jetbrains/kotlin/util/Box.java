package org.jetbrains.kotlin.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class Box<T> {
    private final T data;

    public Box(T t) {
        this.data = t;
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    public T getData() {
        return this.data;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
