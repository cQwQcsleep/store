package com.sun.org.apache.xerces.internal.impl.xs.util;

import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.sun.org.apache.xerces.internal.xs.XSException;
import java.util.AbstractList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ShortListImpl extends AbstractList<Short> implements ShortList {
    public static final ShortListImpl EMPTY_LIST = new ShortListImpl(new short[0], 0);
    private final short[] fArray;
    private final int fLength;

    public ShortListImpl(short[] sArr, int i) {
        this.fArray = sArr;
        this.fLength = i;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ShortList
    public boolean contains(short s) {
        for (int i = 0; i < this.fLength; i++) {
            if (this.fArray[i] == s) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ShortList)) {
            return false;
        }
        ShortList shortList = (ShortList) obj;
        if (this.fLength != shortList.getLength()) {
            return false;
        }
        for (int i = 0; i < this.fLength; i++) {
            if (this.fArray[i] != shortList.item(i)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public Short get(int i) {
        if (i >= 0 && i < this.fLength) {
            return Short.valueOf(this.fArray[i]);
        }
        b1e.a("Index: ", i);
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ShortList
    public int getLength() {
        return this.fLength;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ShortList
    public short item(int i) throws XSException {
        if (i < 0 || i >= this.fLength) {
            throw new XSException((short) 2, null);
        }
        return this.fArray[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return getLength();
    }
}
