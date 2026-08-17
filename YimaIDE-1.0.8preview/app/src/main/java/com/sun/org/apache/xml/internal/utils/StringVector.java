package com.sun.org.apache.xml.internal.utils;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringVector implements Serializable {
    static final long serialVersionUID = 4995234972032919748L;
    protected int m_blocksize;
    protected int m_firstFree;
    protected String[] m_map;
    protected int m_mapSize;

    public StringVector() {
        this.m_firstFree = 0;
        this.m_blocksize = 8;
        this.m_mapSize = 8;
        this.m_map = new String[8];
    }

    public final void addElement(String str) {
        int i = this.m_firstFree;
        int i2 = i + 1;
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
        this.m_firstFree = i5 + 1;
    }

    public final boolean contains(String str) {
        if (str == null) {
            return false;
        }
        for (int i = 0; i < this.m_firstFree; i++) {
            if (this.m_map[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    public final boolean containsIgnoreCase(String str) {
        if (str == null) {
            return false;
        }
        for (int i = 0; i < this.m_firstFree; i++) {
            if (this.m_map[i].equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public final String elementAt(int i) {
        return this.m_map[i];
    }

    public int getLength() {
        return this.m_firstFree;
    }

    public final String peek() {
        int i = this.m_firstFree;
        if (i <= 0) {
            return null;
        }
        return this.m_map[i - 1];
    }

    public final String pop() {
        int i = this.m_firstFree;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        this.m_firstFree = i2;
        String[] strArr = this.m_map;
        String str = strArr[i2];
        strArr[i2] = null;
        return str;
    }

    public final void push(String str) {
        int i = this.m_firstFree;
        int i2 = i + 1;
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
        this.m_firstFree = i5 + 1;
    }

    public final int size() {
        return this.m_firstFree;
    }

    public StringVector(int i) {
        this.m_firstFree = 0;
        this.m_blocksize = i;
        this.m_mapSize = i;
        this.m_map = new String[i];
    }
}
