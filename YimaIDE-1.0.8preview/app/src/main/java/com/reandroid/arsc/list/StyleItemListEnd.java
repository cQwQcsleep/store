package com.reandroid.arsc.list;

import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.BlockItem;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.utils.HexUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StyleItemListEnd extends BlockItem implements BlockRefresh {
    private final IntegerReference stylesCount;

    public StyleItemListEnd(IntegerReference integerReference) {
        super(0);
        this.stylesCount = integerReference;
    }

    private boolean updateSize() {
        int i = this.stylesCount.get() != 0 ? 8 : 0;
        setBytesLength(i, false);
        if (i == 0) {
            return false;
        }
        byte[] bytesInternal = getBytesInternal();
        for (int i2 = 0; i2 < i; i2++) {
            bytesInternal[i2] = -1;
        }
        return true;
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        if (updateSize()) {
            super.onReadBytes(blockReader);
        }
    }

    public void refresh() {
        updateSize();
    }

    public String toString() {
        return HexUtil.toHexString(getBytesInternal());
    }
}
