package com.reandroid.dex.header;

import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.base.DirectStreamReader;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.ByteArray;
import com.reandroid.utils.HexUtil;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class UnknownHeaderBytes extends ByteArray implements DirectStreamReader, BlockRefresh {
    private int computeUnknownSize() {
        int iCountBytes;
        DexHeader dexHeader = (DexHeader) getParentInstance(DexHeader.class);
        if (dexHeader != null && (iCountBytes = (dexHeader.headerSize.get() - dexHeader.countBytes()) - size()) >= 0 && iCountBytes <= 255) {
            return iCountBytes;
        }
        return 0;
    }

    private void updateSize() {
        setSize(computeUnknownSize());
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        updateSize();
        super.onReadBytes(blockReader);
    }

    @Override // com.reandroid.arsc.base.DirectStreamReader
    public int readBytes(InputStream inputStream) throws IOException {
        updateSize();
        return super.readBytes(inputStream);
    }

    public void refresh() {
        updateSize();
    }

    @Override // com.reandroid.arsc.item.ByteArray
    public String toString() {
        int size = size();
        if (size <= 0 || size > 32) {
            return super.toString();
        }
        return "size = " + size + " [" + HexUtil.toHexString(getBytesInternal()) + "]";
    }
}
