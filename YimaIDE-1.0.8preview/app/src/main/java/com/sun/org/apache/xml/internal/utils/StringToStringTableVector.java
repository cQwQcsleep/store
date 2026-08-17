package com.sun.org.apache.xml.internal.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringToStringTableVector {
    private int m_blocksize;
    private int m_firstFree;
    private StringToStringTable[] m_map;
    private int m_mapSize;

    public StringToStringTableVector() {
        this.m_firstFree = 0;
        this.m_blocksize = 8;
        this.m_mapSize = 8;
        this.m_map = new StringToStringTable[8];
    }

    public final void addElement(StringToStringTable stringToStringTable) {
        int i = this.m_firstFree;
        int i2 = i + 1;
        int i3 = this.m_mapSize;
        if (i2 >= i3) {
            int i4 = i3 + this.m_blocksize;
            this.m_mapSize = i4;
            StringToStringTable[] stringToStringTableArr = new StringToStringTable[i4];
            System.arraycopy(this.m_map, 0, stringToStringTableArr, 0, i + 1);
            this.m_map = stringToStringTableArr;
        }
        StringToStringTable[] stringToStringTableArr2 = this.m_map;
        int i5 = this.m_firstFree;
        stringToStringTableArr2[i5] = stringToStringTable;
        this.m_firstFree = i5 + 1;
    }

    public final boolean contains(StringToStringTable stringToStringTable) {
        for (int i = 0; i < this.m_firstFree; i++) {
            if (this.m_map[i].equals(stringToStringTable)) {
                return true;
            }
        }
        return false;
    }

    public final boolean containsKey(String str) {
        for (int i = this.m_firstFree - 1; i >= 0; i--) {
            if (this.m_map[i].get(str) != null) {
                return true;
            }
        }
        return false;
    }

    public final StringToStringTable elementAt(int i) {
        return this.m_map[i];
    }

    public final String get(String str) {
        for (int i = this.m_firstFree - 1; i >= 0; i--) {
            String str2 = this.m_map[i].get(str);
            if (str2 != null) {
                return str2;
            }
        }
        return null;
    }

    public final int getLength() {
        return this.m_firstFree;
    }

    public final void removeLastElem() {
        int i = this.m_firstFree;
        if (i > 0) {
            this.m_map[i] = null;
            this.m_firstFree = i - 1;
        }
    }

    public final int size() {
        return this.m_firstFree;
    }

    public StringToStringTableVector(int i) {
        this.m_firstFree = 0;
        this.m_blocksize = i;
        this.m_mapSize = i;
        this.m_map = new StringToStringTable[i];
    }
}
