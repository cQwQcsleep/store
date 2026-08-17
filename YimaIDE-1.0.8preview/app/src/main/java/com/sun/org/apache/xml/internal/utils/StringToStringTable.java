package com.sun.org.apache.xml.internal.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringToStringTable {
    private int m_blocksize;
    private int m_firstFree;
    private String[] m_map;
    private int m_mapSize;

    public StringToStringTable() {
        this.m_firstFree = 0;
        this.m_blocksize = 16;
        this.m_mapSize = 16;
        this.m_map = new String[16];
    }

    public final boolean contains(String str) {
        for (int i = 0; i < this.m_firstFree; i += 2) {
            if (this.m_map[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    public final boolean containsValue(String str) {
        for (int i = 1; i < this.m_firstFree; i += 2) {
            if (this.m_map[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    public final String elementAt(int i) {
        return this.m_map[i];
    }

    public final String get(String str) {
        for (int i = 0; i < this.m_firstFree; i += 2) {
            if (this.m_map[i].equals(str)) {
                return this.m_map[i + 1];
            }
        }
        return null;
    }

    public final String getByValue(String str) {
        for (int i = 1; i < this.m_firstFree; i += 2) {
            if (this.m_map[i].equals(str)) {
                return this.m_map[i - 1];
            }
        }
        return null;
    }

    public final String getIgnoreCase(String str) {
        if (str == null) {
            return null;
        }
        for (int i = 0; i < this.m_firstFree; i += 2) {
            if (this.m_map[i].equalsIgnoreCase(str)) {
                return this.m_map[i + 1];
            }
        }
        return null;
    }

    public final int getLength() {
        return this.m_firstFree;
    }

    public final void put(String str, String str2) {
        int i = this.m_firstFree;
        int i2 = i + 2;
        int i3 = this.m_mapSize;
        if (i2 >= i3) {
            int i4 = i3 + this.m_blocksize;
            this.m_mapSize = i4;
            String[] strArr = new String[i4];
            System.arraycopy(this.m_map, 0, strArr, 0, i + 1);
            this.m_map = strArr;
        }
        String[] strArr2 = this.m_map;
        int i5 = this.m_firstFree;
        strArr2[i5] = str;
        int i6 = i5 + 1;
        this.m_firstFree = i6;
        strArr2[i6] = str2;
        this.m_firstFree = i5 + 2;
    }

    public final void remove(String str) {
        for (int i = 0; i < this.m_firstFree; i += 2) {
            if (this.m_map[i].equals(str)) {
                int i2 = i + 2;
                int i3 = this.m_firstFree;
                if (i2 < i3) {
                    String[] strArr = this.m_map;
                    System.arraycopy(strArr, i2, strArr, i, i3 - i2);
                }
                int i4 = this.m_firstFree;
                int i5 = i4 - 2;
                this.m_firstFree = i5;
                String[] strArr2 = this.m_map;
                strArr2[i5] = null;
                strArr2[i4 - 1] = null;
                return;
            }
        }
    }

    public StringToStringTable(int i) {
        this.m_firstFree = 0;
        this.m_blocksize = i;
        this.m_mapSize = i;
        this.m_map = new String[i];
    }
}
