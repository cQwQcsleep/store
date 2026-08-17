package com.reandroid.arsc.item;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NumberIntegerReference implements IntegerReference {
    private int value;

    public NumberIntegerReference(int i) {
        this.value = i;
    }

    public int get() {
        return this.value;
    }

    public void set(int i) {
        this.value = i;
    }

    public String toString() {
        return Integer.toString(get());
    }

    public NumberIntegerReference() {
        this(0);
    }
}
