package com.sun.org.apache.xml.internal.utils.res;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IntArrayWrapper {
    private int[] m_int;

    public IntArrayWrapper(int[] iArr) {
        this.m_int = iArr;
    }

    public int getInt(int i) {
        return this.m_int[i];
    }

    public int getLength() {
        return this.m_int.length;
    }
}
