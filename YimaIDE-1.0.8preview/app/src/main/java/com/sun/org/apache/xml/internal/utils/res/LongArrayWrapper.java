package com.sun.org.apache.xml.internal.utils.res;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LongArrayWrapper {
    private long[] m_long;

    public LongArrayWrapper(long[] jArr) {
        this.m_long = jArr;
    }

    public int getLength() {
        return this.m_long.length;
    }

    public long getLong(int i) {
        return this.m_long[i];
    }
}
