package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;
import com.reandroid.utils.NumberX;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NumberBlock extends BlockItem implements LongReference {
    public NumberBlock(int i) {
        super(i);
    }

    private long unsigned() {
        byte[] bytesInternal = getBytesInternal();
        return Block.getUnsignedNumber(bytesInternal, 0, bytesInternal.length);
    }

    private void validate(int i, long j) {
        if (j != 0) {
            if (i == 0) {
                throw new NumberFormatException("Width == 0 for value = " + j);
            }
            if ((j >= 0 || j >= NumberX.minValueForWidth(i)) && (j <= 0 || j <= NumberX.maxValueForWidth(i))) {
                return;
            }
            throw new NumberFormatException("Out of range for width = " + i + ", value = " + j);
        }
    }

    public int get() {
        return (int) getLong();
    }

    public long getLong() {
        int iWidth = width();
        if (iWidth > 8) {
            iWidth = 8;
        }
        return NumberX.valueOfUnsigned(iWidth, unsigned()).longValue();
    }

    public void set(long j) {
        byte[] bytesInternal = getBytesInternal();
        int length = bytesInternal.length;
        validate(length, j);
        Block.putNumber(bytesInternal, 0, length, j);
    }

    public String toHexString() {
        return NumberX.toHexString(width(), getLong());
    }

    public String toString() {
        return toHexString();
    }

    public void width(int i) {
        if (i == width()) {
            return;
        }
        long j = getLong();
        setBytesLength(i, false);
        set(j);
    }

    public void set(int i) {
        set(i);
    }

    public int width() {
        return countBytes();
    }
}
