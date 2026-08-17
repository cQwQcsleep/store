package com.sun.org.apache.xml.internal.serializer.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class BoolStack {
    private int m_allocatedSize;
    private int m_index;
    private boolean[] m_values;

    public BoolStack(int i) {
        this.m_allocatedSize = i;
        this.m_values = new boolean[i];
        this.m_index = -1;
    }

    private void grow() {
        int i = this.m_allocatedSize * 2;
        this.m_allocatedSize = i;
        boolean[] zArr = new boolean[i];
        System.arraycopy(this.m_values, 0, zArr, 0, this.m_index + 1);
        this.m_values = zArr;
    }

    public final void clear() {
        this.m_index = -1;
    }

    public boolean isEmpty() {
        return this.m_index == -1;
    }

    public final boolean peek() {
        return this.m_values[this.m_index];
    }

    public final boolean peekOrFalse() {
        int i = this.m_index;
        if (i > -1) {
            return this.m_values[i];
        }
        return false;
    }

    public final boolean peekOrTrue() {
        int i = this.m_index;
        if (i > -1) {
            return this.m_values[i];
        }
        return true;
    }

    public final boolean pop() {
        boolean[] zArr = this.m_values;
        int i = this.m_index;
        this.m_index = i - 1;
        return zArr[i];
    }

    public final boolean popAndTop() {
        int i = this.m_index - 1;
        this.m_index = i;
        if (i >= 0) {
            return this.m_values[i];
        }
        return false;
    }

    public final boolean push(boolean z) {
        if (this.m_index == this.m_allocatedSize - 1) {
            grow();
        }
        boolean[] zArr = this.m_values;
        int i = this.m_index + 1;
        this.m_index = i;
        zArr[i] = z;
        return z;
    }

    public final void setTop(boolean z) {
        this.m_values[this.m_index] = z;
    }

    public final int size() {
        return this.m_index + 1;
    }

    public BoolStack() {
        this(32);
    }
}
