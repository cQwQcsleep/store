package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;
import com.sun.org.apache.bcel.internal.Const;
import java.util.AbstractList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ByteArray extends BlockItem {
    public ByteArray() {
        this(0);
    }

    public static boolean equals(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr.length == 0) {
            return bArr2 == null || bArr2.length == 0;
        }
        if (bArr2 == null || bArr2.length == 0 || bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsIgnoreTrailZero(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        return equals(trimTrailZeros(bArr), trimTrailZeros(bArr2));
    }

    public static byte[] trimTrailZeros(byte[] bArr) {
        if (bArr == null) {
            return new byte[0];
        }
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if (bArr[i2] != 0) {
                i = i2 + 1;
            }
        }
        byte[] bArr2 = new byte[i];
        if (i > 0) {
            System.arraycopy(bArr, 0, bArr2, 0, i);
        }
        return bArr2;
    }

    public final void add(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        int size = size();
        int length = bArr.length;
        setBytesLength(size + length, false);
        System.arraycopy(bArr, 0, getBytesInternal(), size, length);
    }

    public final void clear() {
        setSize(0);
    }

    public final void ensureArraySize(int i) {
        if (size() >= i) {
            return;
        }
        setSize(i);
    }

    public final void fill(byte b) {
        byte[] bytesInternal = getBytesInternal();
        int length = bytesInternal.length;
        for (int i = 0; i < length; i++) {
            bytesInternal[i] = b;
        }
    }

    public byte get(int i) {
        return getBytesInternal()[i];
    }

    public boolean getBit(int i, int i2) {
        return Block.getBit(getBytesInternal(), i, i2);
    }

    public final byte[] getByteArray(int i, int i2) {
        byte[] bytesInternal = getBytesInternal();
        byte[] bArr = new byte[i2];
        if (i2 >= 0) {
            System.arraycopy(bytesInternal, i, bArr, 0, i2);
        }
        return bArr;
    }

    public int getByteUnsigned(int i) {
        return get(i) & 255;
    }

    public final int getInteger(int i) {
        byte[] bytesInternal = getBytesInternal();
        if (i + 4 > bytesInternal.length) {
            return 0;
        }
        return ((bytesInternal[i + 3] & 255) << 24) | (bytesInternal[i] & 255) | ((bytesInternal[i + 1] & 255) << 8) | ((bytesInternal[i + 2] & 255) << 16);
    }

    public final short getShort(int i) {
        byte[] bytesInternal = getBytesInternal();
        return (short) (((bytesInternal[i + 1] & 255) << 8) | (bytesInternal[i] & 255));
    }

    public final int getShortUnsigned(int i) {
        return getShort(i) & 65535;
    }

    public final void put(int i, byte b) {
        getBytesInternal()[i] = b;
    }

    public void putBit(int i, int i2, boolean z) {
        Block.putBit(getBytesInternal(), i, i2, z);
    }

    public final void putByte(int i, int i2) {
        put(i, (byte) i2);
    }

    public final void putByteArray(int i, byte[] bArr) {
        byte[] bytesInternal = getBytesInternal();
        int length = bytesInternal.length - i;
        if (length <= 0) {
            return;
        }
        int length2 = bArr.length;
        if (length2 <= length) {
            length = length2;
        }
        System.arraycopy(bArr, 0, bytesInternal, i, length);
    }

    public final void putInteger(int i, int i2) {
        byte[] bytesInternal = getBytesInternal();
        if (i + 4 > bytesInternal.length) {
            return;
        }
        bytesInternal[i + 3] = (byte) ((i2 >>> 24) & 255);
        bytesInternal[i + 2] = (byte) ((i2 >>> 16) & 255);
        bytesInternal[i + 1] = (byte) ((i2 >>> 8) & 255);
        bytesInternal[i] = (byte) (i2 & 255);
    }

    public final void putShort(int i, short s) {
        byte[] bytesInternal = getBytesInternal();
        bytesInternal[i + 1] = (byte) ((s >>> 8) & 255);
        bytesInternal[i] = (byte) (s & Const.IMPDEP2);
    }

    public final void set(byte[] bArr) {
        super.setBytesInternal(bArr);
    }

    public final void setSize(int i) {
        if (i < 0) {
            i = 0;
        }
        setBytesLength(i);
    }

    public final int size() {
        return getBytesLength();
    }

    public final byte[] toArray() {
        return getBytes();
    }

    public final List<Byte> toByteList() {
        return new AbstractList<Byte>() { // from class: com.reandroid.arsc.item.ByteArray.1
            @Override // java.util.AbstractList, java.util.List
            public Byte get(int i) {
                return Byte.valueOf(ByteArray.this.get(i));
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return ByteArray.this.size();
            }
        };
    }

    public final List<Integer> toIntegerList() {
        return new 3(this);
    }

    public final List<Short> toShortList() {
        return new 2(this);
    }

    public String toString() {
        return "size=" + size();
    }

    public ByteArray(int i) {
        super(i);
    }

    public final void putShort(int i, int i2) {
        putShort(i, (short) i2);
    }
}
