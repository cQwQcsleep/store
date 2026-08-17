package com.sun.org.apache.xml.internal.serializer.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class StringToIntTable {
    public static final int INVALID_KEY = -10000;
    private int m_blocksize;
    private int m_firstFree;
    private String[] m_map;
    private int m_mapSize;
    private int[] m_values;

    public StringToIntTable() {
        this.m_firstFree = 0;
        this.m_blocksize = 8;
        this.m_mapSize = 8;
        this.m_map = new String[8];
        this.m_values = new int[8];
    }

    public final boolean contains(String str) {
        for (int i = 0; i < this.m_firstFree; i++) {
            if (this.m_map[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    public final int get(String str) {
        for (int i = 0; i < this.m_firstFree; i++) {
            if (this.m_map[i].equals(str)) {
                return this.m_values[i];
            }
        }
        return -10000;
    }

    public final int getIgnoreCase(String str) {
        if (str == null) {
            return -10000;
        }
        for (int i = 0; i < this.m_firstFree; i++) {
            if (this.m_map[i].equalsIgnoreCase(str)) {
                return this.m_values[i];
            }
        }
        return -10000;
    }

    public final int getLength() {
        return this.m_firstFree;
    }

    public final String[] keys() {
        String[] strArr = new String[this.m_firstFree];
        for (int i = 0; i < this.m_firstFree; i++) {
            strArr[i] = this.m_map[i];
        }
        return strArr;
    }

    public final void put(String str, int i) {
        int i2 = this.m_firstFree;
        int i3 = i2 + 1;
        int i4 = this.m_mapSize;
        if (i3 >= i4) {
            int i5 = i4 + this.m_blocksize;
            this.m_mapSize = i5;
            String[] strArr = new String[i5];
            System.arraycopy(this.m_map, 0, strArr, 0, i2 + 1);
            this.m_map = strArr;
            int[] iArr = new int[this.m_mapSize];
            System.arraycopy(this.m_values, 0, iArr, 0, this.m_firstFree + 1);
            this.m_values = iArr;
        }
        String[] strArr2 = this.m_map;
        int i6 = this.m_firstFree;
        strArr2[i6] = str;
        this.m_values[i6] = i;
        this.m_firstFree = i6 + 1;
    }

    public StringToIntTable(int i) {
        this.m_firstFree = 0;
        this.m_blocksize = i;
        this.m_mapSize = i;
        this.m_map = new String[i];
        this.m_values = new int[i];
    }
}
