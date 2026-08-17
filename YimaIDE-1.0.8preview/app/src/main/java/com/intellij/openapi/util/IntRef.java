package com.intellij.openapi.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class IntRef {
    private int myValue;

    public IntRef(int i) {
        this.myValue = i;
    }

    public int get() {
        return this.myValue;
    }

    public void set(int i) {
        this.myValue = i;
    }

    public String toString() {
        return "IntRef(" + this.myValue + ")";
    }

    public IntRef() {
        this(0);
    }
}
