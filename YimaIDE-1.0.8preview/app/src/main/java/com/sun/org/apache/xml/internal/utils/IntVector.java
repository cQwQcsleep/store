package com.sun.org.apache.xml.internal.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IntVector implements Cloneable {
    protected int m_blocksize;
    protected int m_firstFree;
    protected int[] m_map;
    protected int m_mapSize;

    public IntVector(IntVector intVector) {
        this.m_firstFree = 0;
        int i = intVector.m_mapSize;
        int[] iArr = new int[i];
        this.m_map = iArr;
        this.m_mapSize = i;
        int i2 = intVector.m_firstFree;
        this.m_firstFree = i2;
        this.m_blocksize = intVector.m_blocksize;
        System.arraycopy(intVector.m_map, 0, iArr, 0, i2);
    }

    public final void addElement(int i) {
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
    }

    public final void addElements(int i, int i2) {
        int i3 = this.m_firstFree;
        int i4 = i3 + i2;
        int i5 = this.m_mapSize;
        if (i4 >= i5) {
            int i6 = i5 + this.m_blocksize + i2;
            this.m_mapSize = i6;
            int[] iArr = new int[i6];
            System.arraycopy(this.m_map, 0, iArr, 0, i3 + 1);
            this.m_map = iArr;
        }
        for (int i7 = 0; i7 < i2; i7++) {
            int[] iArr2 = this.m_map;
            int i8 = this.m_firstFree;
            iArr2[i8] = i;
            this.m_firstFree = i8 + 1;
        }
    }

    public Object clone() throws CloneNotSupportedException {
        return new IntVector(this);
    }

    public final boolean contains(int i) {
        for (int i2 = 0; i2 < this.m_firstFree; i2++) {
            if (this.m_map[i2] == i) {
                return true;
            }
        }
        return false;
    }

    public final int elementAt(int i) {
        return this.m_map[i];
    }

    public final int indexOf(int i) {
        for (int i2 = 0; i2 < this.m_firstFree; i2++) {
            if (this.m_map[i2] == i) {
                return i2;
            }
        }
        return Integer.MIN_VALUE;
    }

    public final void insertElementAt(int i, int i2) {
        int i3 = this.m_firstFree;
        int i4 = i3 + 1;
        int i5 = this.m_mapSize;
        if (i4 >= i5) {
            int i6 = i5 + this.m_blocksize;
            this.m_mapSize = i6;
            int[] iArr = new int[i6];
            System.arraycopy(this.m_map, 0, iArr, 0, i3 + 1);
            this.m_map = iArr;
        }
        int i7 = this.m_firstFree;
        if (i2 <= i7 - 1) {
            int[] iArr2 = this.m_map;
            System.arraycopy(iArr2, i2, iArr2, i2 + 1, i7 - i2);
        }
        this.m_map[i2] = i;
        this.m_firstFree++;
    }

    public final int lastIndexOf(int i) {
        for (int i2 = this.m_firstFree - 1; i2 >= 0; i2--) {
            if (this.m_map[i2] == i) {
                return i2;
            }
        }
        return Integer.MIN_VALUE;
    }

    public final void removeAllElements() {
        for (int i = 0; i < this.m_firstFree; i++) {
            this.m_map[i] = Integer.MIN_VALUE;
        }
        this.m_firstFree = 0;
    }

    public final boolean removeElement(int i) {
        int i2 = 0;
        while (true) {
            int i3 = this.m_firstFree;
            if (i2 >= i3) {
                return false;
            }
            int[] iArr = this.m_map;
            if (iArr[i2] == i) {
                int i4 = i2 + 1;
                if (i4 < i3) {
                    System.arraycopy(iArr, i4, iArr, i2 - 1, i3 - i2);
                } else {
                    iArr[i2] = Integer.MIN_VALUE;
                }
                this.m_firstFree--;
                return true;
            }
            i2++;
        }
    }

    public final void removeElementAt(int i) {
        int i2 = this.m_firstFree;
        int[] iArr = this.m_map;
        if (i > i2) {
            System.arraycopy(iArr, i + 1, iArr, i, i2);
        } else {
            iArr[i] = Integer.MIN_VALUE;
        }
        this.m_firstFree--;
    }

    public final void setElementAt(int i, int i2) {
        this.m_map[i2] = i;
    }

    public final void setSize(int i) {
        this.m_firstFree = i;
    }

    public final int size() {
        return this.m_firstFree;
    }

    public final int indexOf(int i, int i2) {
        while (i2 < this.m_firstFree) {
            if (this.m_map[i2] == i) {
                return i2;
            }
            i2++;
        }
        return Integer.MIN_VALUE;
    }

    public IntVector(int i) {
        this.m_firstFree = 0;
        this.m_blocksize = i;
        this.m_mapSize = i;
        this.m_map = new int[i];
    }

    public IntVector(int i, int i2) {
        this.m_firstFree = 0;
        this.m_blocksize = i2;
        this.m_mapSize = i;
        this.m_map = new int[i];
    }

    public IntVector() {
        this.m_firstFree = 0;
        this.m_blocksize = 32;
        this.m_mapSize = 32;
        this.m_map = new int[32];
    }

    public final void addElements(int i) {
        int i2 = this.m_firstFree;
        int i3 = i2 + i;
        int i4 = this.m_mapSize;
        if (i3 >= i4) {
            int i5 = i4 + this.m_blocksize + i;
            this.m_mapSize = i5;
            int[] iArr = new int[i5];
            System.arraycopy(this.m_map, 0, iArr, 0, i2 + 1);
            this.m_map = iArr;
        }
        this.m_firstFree += i;
    }
}
