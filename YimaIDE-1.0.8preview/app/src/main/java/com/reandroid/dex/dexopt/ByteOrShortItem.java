package com.reandroid.dex.dexopt;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.BlockItem;
import com.reandroid.arsc.item.BooleanReference;
import com.reandroid.arsc.item.IntegerReference;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ByteOrShortItem extends BlockItem implements IntegerReference {
    private final BooleanReference is_byte;

    public ByteOrShortItem(BooleanReference booleanReference) {
        super(2);
        this.is_byte = booleanReference;
    }

    public int get() {
        byte[] bytesInternal = getBytesInternal();
        return this.is_byte.get() ? bytesInternal[0] & 255 : Block.getShortUnsigned(bytesInternal, 0);
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        setBytesLength(this.is_byte.get() ? 1 : 2, false);
        super.onReadBytes(blockReader);
    }

    public void set(int i) {
        boolean z = this.is_byte.get();
        setBytesLength(z ? 1 : 2, false);
        byte[] bytesInternal = getBytesInternal();
        if (z) {
            bytesInternal[0] = (byte) i;
        } else {
            Block.putShort(bytesInternal, 0, i);
        }
    }

    public String toString() {
        return Integer.toString(get());
    }
}
