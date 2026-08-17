package com.reandroid.dex.base;

import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.io.StreamUtil;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Sle128Item extends Le128 {
    @Override // com.reandroid.dex.base.Le128
    public int readLe128(BlockReader blockReader) throws IOException {
        int position = blockReader.getPosition();
        int sleb128 = DexBlockItem.readSleb128(StreamUtil.createByteReader((InputStream) blockReader));
        int position2 = blockReader.getPosition() - position;
        blockReader.seek(position);
        setBytesLength(position2, false);
        blockReader.readFully(getBytesInternal());
        return sleb128;
    }

    @Override // com.reandroid.dex.base.Le128
    public void writeValue(int i) {
        setBytesLength(5, false);
        setBytesLength(DexBlockItem.writeSleb128(getBytesInternal(), 0, i), false);
    }
}
