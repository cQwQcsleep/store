package com.sun.org.apache.xerces.internal.impl.dv.util;

import com.sun.org.apache.xerces.internal.xs.XSException;
import com.sun.org.apache.xerces.internal.xs.datatypes.ByteList;
import java.util.AbstractList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ByteListImpl extends AbstractList<Byte> implements ByteList {
    protected String canonical;
    protected final byte[] data;

    public ByteListImpl(byte[] bArr) {
        this.data = bArr;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.datatypes.ByteList
    public boolean contains(byte b) {
        int i = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i >= bArr.length) {
                return false;
            }
            if (bArr[i] == b) {
                return true;
            }
            i++;
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public Byte get(int i) {
        if (i >= 0) {
            byte[] bArr = this.data;
            if (i < bArr.length) {
                return Byte.valueOf(bArr[i]);
            }
        }
        b1e.a("Index: ", i);
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.datatypes.ByteList
    public int getLength() {
        return this.data.length;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.datatypes.ByteList
    public byte item(int i) throws XSException {
        if (i >= 0) {
            byte[] bArr = this.data;
            if (i <= bArr.length - 1) {
                return bArr[i];
            }
        }
        throw new XSException((short) 2, null);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return getLength();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.datatypes.ByteList
    public byte[] toByteArray() {
        byte[] bArr = this.data;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }
}
