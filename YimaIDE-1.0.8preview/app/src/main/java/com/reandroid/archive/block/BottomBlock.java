package com.reandroid.archive.block;

import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class BottomBlock extends BlockList<LengthPrefixedBytes> {
    public void onReadBytes(BlockReader blockReader) throws IOException {
        while (blockReader.isAvailable()) {
            LengthPrefixedBytes lengthPrefixedBytes = new LengthPrefixedBytes(false);
            lengthPrefixedBytes.readBytes(blockReader);
            add(lengthPrefixedBytes);
        }
    }
}
