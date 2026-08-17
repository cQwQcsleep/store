package com.reandroid.arsc.array;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface OffsetArray {
    public static final int NO_ENTRY = -1;

    void clear();

    int getOffset(int i);

    int[] getOffsets();

    void setOffset(int i, int i2);

    void setSize(int i);

    int size();
}
