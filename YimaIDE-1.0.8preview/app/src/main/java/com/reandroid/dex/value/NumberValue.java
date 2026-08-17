package com.reandroid.dex.value;

import com.reandroid.arsc.base.Block;
import com.reandroid.dex.base.DexBlockItem;
import com.reandroid.utils.HexUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NumberValue extends DexBlockItem {
    public NumberValue() {
        super(1);
    }

    private static int calculateSignedSize(long j, boolean z) {
        if (j == 0) {
            return 1;
        }
        long j2 = j;
        int i = 0;
        int i2 = 0;
        while (j2 != 0) {
            i2 = (int) j2;
            j2 >>>= 8;
            i++;
        }
        if (!z && i2 >= 128) {
            return i + 1;
        }
        if (z) {
            byte[] bArr = new byte[i];
            Block.putNumber(bArr, 0, i, j);
            for (int i3 = i - 1; i3 > 0; i3--) {
                int i4 = bArr[i3] & 255;
                int i5 = bArr[i3 - 1] & 255;
                if (i4 != 255 || i5 <= 128) {
                    break;
                }
                i--;
            }
        }
        return i;
    }

    private static int calculateUnsignedSize(long j) {
        if (j == 0) {
            return 1;
        }
        int i = 0;
        while (j != 0) {
            j >>>= 8;
            i++;
        }
        return i;
    }

    private void setSignedNumberValue(long j, boolean z) {
        int iCalculateSignedSize = calculateSignedSize(j, z);
        setSize(iCalculateSignedSize);
        Block.putNumber(getBytesInternal(), 0, iCalculateSignedSize, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && getUnsignedNumber() == ((NumberValue) obj).getUnsignedNumber();
    }

    public long getSignedNumber() {
        return DexBlockItem.getSignedNumber(getBytesInternal(), 0, getSize());
    }

    public int getSize() {
        return countBytes();
    }

    public long getUnsignedNumber() {
        return Block.getUnsignedNumber(getBytesInternal(), 0, getSize());
    }

    public int hashCode() {
        return Long.hashCode(getUnsignedNumber());
    }

    public void merge(NumberValue numberValue) {
        byte[] bytes = numberValue.getBytes();
        int length = bytes.length;
        setBytesLength(length, false);
        byte[] bytesInternal = getBytesInternal();
        for (int i = 0; i < length; i++) {
            bytesInternal[i] = bytes[i];
        }
    }

    public void setNumber(long j, int i) {
        setSize(i);
        Block.putNumber(getBytesInternal(), 0, i, j);
    }

    public void setNumberValue(int i) {
        setSignedNumberValue(((long) i) & 4294967295L, i < 0);
    }

    public void setSize(int i) {
        setBytesLength(i, false);
    }

    public void setUnsignedNumber(long j) {
        int iCalculateUnsignedSize = calculateUnsignedSize(j);
        setSize(iCalculateUnsignedSize);
        Block.putNumber(getBytesInternal(), 0, iCalculateUnsignedSize, j);
    }

    public String toHex() {
        return HexUtil.toHex(getUnsignedNumber(), getSize());
    }

    public String toString() {
        return getSize() + ":" + toHex();
    }

    public void setNumberValue(short s) {
        setSignedNumberValue(((long) s) & 65535, s < 0);
    }

    public void setNumberValue(byte b) {
        setSize(1);
        getBytesInternal()[0] = b;
    }

    public void setNumberValue(long j) {
        setSignedNumberValue(j, j < 0);
    }
}
