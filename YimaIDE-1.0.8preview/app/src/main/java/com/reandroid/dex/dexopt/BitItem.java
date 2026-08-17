package com.reandroid.dex.dexopt;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.item.BlockItem;
import com.reandroid.utils.NumbersUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class BitItem extends BlockItem {
    private int bitsLength;

    public BitItem(int i) {
        super(bitsToBytes(i));
        this.bitsLength = i;
    }

    public static int bitIndex(int i) {
        return i & 7;
    }

    public static int bitsToBytes(int i) {
        return ((i + 7) & (-8)) / 8;
    }

    public static int byteIndex(int i) {
        return i >>> 3;
    }

    public static int wordIndex(int i) {
        return i >>> 6;
    }

    public int append(int i, BitItem bitItem) {
        int iBitsLength = bitItem.bitsLength();
        for (int i2 = 0; i2 < iBitsLength; i2++) {
            i += i2;
            set(i, bitItem.get(i2));
        }
        return i;
    }

    public void bitsLength(int i) {
        setBytesLength(bitsToBytes(i), true);
        this.bitsLength = i;
    }

    public void ensureBitsLength(int i) {
        if (i > bitsLength()) {
            bitsLength(i);
        }
    }

    public void ensureSize(int i) {
        if (i > size()) {
            setSize(i);
        }
    }

    public boolean get(int i) {
        return ((readByte(byteIndex(i)) >> bitIndex(i)) & 1) == 1;
    }

    public long getValueUnsigned() {
        byte[] bytesInternal = getBytesInternal();
        int length = bytesInternal.length - 1;
        return (((long) NumbersUtil.getUInt(bytesInternal[length] & 255, 0, bitIndex(bitsLength()))) << (length * 8)) | Block.getUnsignedNumber(bytesInternal, 0, length);
    }

    public int readByte(int i) {
        return getBytesInternal()[i] & 255;
    }

    public void set(int i, boolean z) {
        int iByteIndex = byteIndex(i);
        ensureSize(iByteIndex + 1);
        writeByte(iByteIndex, NumbersUtil.setUInt(readByte(iByteIndex), z ? 1 : 0, bitIndex(i), 1));
    }

    public void setSize(int i) {
        setBytesLength(i, false);
    }

    public int size() {
        return countBytes();
    }

    public String toString() {
        return "bits=" + this.bitsLength;
    }

    public void writeByte(int i, int i2) {
        getBytesInternal()[i] = (byte) i2;
    }

    public int bitsLength() {
        return this.bitsLength;
    }
}
