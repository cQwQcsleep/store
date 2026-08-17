package com.reandroid.arsc.item;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class NumberLongReference implements LongReference {
    private long value;

    public NumberLongReference(long j) {
        this.value = j;
    }

    @Override // com.reandroid.arsc.item.IntegerReference
    public int get() {
        return (int) getLong();
    }

    @Override // com.reandroid.arsc.item.LongReference
    public long getLong() {
        return this.value;
    }

    @Override // com.reandroid.arsc.item.IntegerReference
    public void set(int i) {
        set(i);
    }

    public String toString() {
        return Long.toString(getLong());
    }

    @Override // com.reandroid.arsc.item.LongReference
    public void set(long j) {
        this.value = j;
    }

    public NumberLongReference() {
    }
}
