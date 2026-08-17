package com.sun.org.apache.xml.internal.utils;

import java.util.EmptyStackException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IntStack extends IntVector {
    public IntStack() {
    }

    @Override // com.sun.org.apache.xml.internal.utils.IntVector
    public Object clone() throws CloneNotSupportedException {
        return (IntStack) super.clone();
    }

    public boolean empty() {
        return this.m_firstFree == 0;
    }

    public int peek(int i) {
        try {
            return this.m_map[this.m_firstFree - (i + 1)];
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new EmptyStackException();
        }
    }

    public final int pop() {
        int[] iArr = this.m_map;
        int i = this.m_firstFree - 1;
        this.m_firstFree = i;
        return iArr[i];
    }

    public int push(int i) {
        int i2 = this.m_firstFree;
        int i3 = i2 + 1;
        int i4 = this.m_mapSize;
        if (i3 >= i4) {
            int i5 = i4 + this.m_blocksize;
            this.m_mapSize = i5;
            int[] iArr = new int[i5];
            System.arraycopy(this.m_map, 0, iArr, 0, i2 + 1);
            this.m_map = iArr;
        }
        int[] iArr2 = this.m_map;
        int i6 = this.m_firstFree;
        iArr2[i6] = i;
        this.m_firstFree = i6 + 1;
        return i;
    }

    public final void quickPop(int i) {
        this.m_firstFree -= i;
    }

    public int search(int i) {
        int iLastIndexOf = lastIndexOf(i);
        if (iLastIndexOf >= 0) {
            return size() - iLastIndexOf;
        }
        return -1;
    }

    public void setTop(int i) {
        try {
            this.m_map[this.m_firstFree - 1] = i;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new EmptyStackException();
        }
    }

    public IntStack(int i) {
        super(i);
    }

    public IntStack(IntStack intStack) {
        super(intStack);
    }

    public final int peek() {
        try {
            return this.m_map[this.m_firstFree - 1];
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new EmptyStackException();
        }
    }
}
