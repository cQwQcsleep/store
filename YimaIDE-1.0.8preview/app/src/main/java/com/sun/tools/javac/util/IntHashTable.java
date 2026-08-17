package com.sun.tools.javac.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IntHashTable {
    private static final int DEFAULT_INITIAL_SIZE = 64;
    private static final Object DELETED = new Object();
    protected int[] ints;
    protected int mask;
    protected int num_bindings;
    protected Object[] objs;

    public IntHashTable(int i) {
        int i2 = 4;
        while (true) {
            int i3 = 1 << i2;
            if (i <= i3) {
                this.objs = new Object[i3];
                this.ints = new int[i3];
                this.mask = i3 - 1;
                return;
            }
            i2++;
        }
    }

    public void clear() {
        int length = this.objs.length;
        while (true) {
            length--;
            if (length < 0) {
                this.num_bindings = 0;
                return;
            }
            this.objs[length] = null;
        }
    }

    public int get(Object obj) {
        int iLookup = lookup(obj);
        Object obj2 = this.objs[iLookup];
        if (obj2 == null || obj2 == DELETED) {
            return -1;
        }
        return this.ints[iLookup];
    }

    public int hash(Object obj) {
        return System.identityHashCode(obj);
    }

    public int lookup(Object obj) {
        int iHash = hash(obj);
        int i = (iHash >>> 15) ^ iHash;
        int i2 = (iHash ^ (iHash << 6)) | 1;
        int i3 = i & this.mask;
        int i4 = -1;
        while (true) {
            Object obj2 = this.objs[i3];
            if (obj2 == obj) {
                break;
            }
            if (obj2 != null) {
                if (obj2 == DELETED && i4 < 0) {
                    i4 = i3;
                }
                i3 = (i3 + i2) & this.mask;
            } else if (i4 >= 0) {
                return i4;
            }
        }
        return i3;
    }

    public int put(Object obj, int i) {
        int iLookup = lookup(obj);
        Object[] objArr = this.objs;
        Object obj2 = objArr[iLookup];
        if (obj2 != null && obj2 != DELETED) {
            int[] iArr = this.ints;
            int i2 = iArr[iLookup];
            iArr[iLookup] = i;
            return i2;
        }
        objArr[iLookup] = obj;
        this.ints[iLookup] = i;
        if (obj2 != DELETED) {
            this.num_bindings++;
        }
        if (this.num_bindings * 3 < objArr.length * 2) {
            return -1;
        }
        rehash();
        return -1;
    }

    public void rehash() {
        Object[] objArr = this.objs;
        int[] iArr = this.ints;
        int length = objArr.length << 1;
        this.objs = new Object[length];
        this.ints = new int[length];
        this.mask = length - 1;
        this.num_bindings = 0;
        int length2 = iArr.length;
        while (true) {
            length2--;
            if (length2 < 0) {
                return;
            }
            Object obj = objArr[length2];
            if (obj != null && obj != DELETED) {
                put(obj, iArr[length2]);
            }
        }
    }

    public int remove(Object obj) {
        Object obj2;
        int iLookup = lookup(obj);
        Object[] objArr = this.objs;
        Object obj3 = objArr[iLookup];
        if (obj3 == null || obj3 == (obj2 = DELETED)) {
            return -1;
        }
        objArr[iLookup] = obj2;
        return this.ints[iLookup];
    }

    public IntHashTable() {
        this.objs = new Object[64];
        this.ints = new int[64];
        this.mask = 63;
    }
}
