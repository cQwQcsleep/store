package com.reandroid.dex.ins;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ConstNumberLong extends ConstNumber {
    default int get() {
        long j = getLong();
        int i = (int) j;
        if ((((long) i) & 4294967295L) != j) {
            return 0;
        }
        return i;
    }

    long getLong();

    default void set(int i) {
        set(i);
    }

    void set(long j);
}
