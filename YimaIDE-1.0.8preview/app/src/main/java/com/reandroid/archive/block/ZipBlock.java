package com.reandroid.archive.block;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.BlockItem;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class ZipBlock extends BlockItem {
    public ZipBlock(int i) {
        super(i);
    }

    public boolean getBit(int i, int i2) {
        return Block.getBit(getBytesInternal(), i, i2);
    }

    public int getByteUnsigned(int i) {
        return getBytesInternal()[i] & 255;
    }

    public byte[] getBytes(int i, int i2, boolean z) {
        byte[] bytesInternal = getBytesInternal();
        if (z && (i < 0 || i >= bytesInternal.length || i + i2 > bytesInternal.length)) {
            return null;
        }
        if (i < 0) {
            i = 0;
        }
        int length = bytesInternal.length - i;
        if (i2 <= 0 || length <= 0) {
            return new byte[0];
        }
        if (i2 > length) {
            i2 = length;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(getBytesInternal(), i, bArr, 0, i2);
        return bArr;
    }

    public int getInteger(int i) {
        return Block.getInteger(getBytesInternal(), i);
    }

    public long getIntegerUnsigned(int i) {
        return ((long) getInteger(i)) & 4294967295L;
    }

    public long getLong(int i) {
        return Block.getLong(getBytesInternal(), i);
    }

    public int getShortUnsigned(int i) {
        return Block.getShort(getBytesInternal(), i) & 65535;
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        readBytes(blockReader);
    }

    public void putBit(int i, int i2, boolean z) {
        Block.putBit(getBytesInternal(), i, i2, z);
    }

    public void putBytes(byte[] bArr, int i, int i2, int i3) {
        if (i3 <= 0 || bArr.length == 0) {
            return;
        }
        int i4 = i2 + i3;
        if (i4 > countBytes()) {
            setBytesLength(i4, false);
        }
        System.arraycopy(bArr, i, getBytesInternal(), i2, i3);
    }

    public void putInteger(int i, long j) {
        Block.putInteger(getBytesInternal(), i, (int) j);
    }

    public void putLong(int i, long j) {
        Block.putLong(getBytesInternal(), i, j);
    }

    public void putShort(int i, int i2) {
        Block.putShort(getBytesInternal(), i, (short) i2);
    }

    public abstract int readBytes(InputStream inputStream) throws IOException;

    public void putInteger(int i, int i2) {
        Block.putInteger(getBytesInternal(), i, i2);
    }
}
