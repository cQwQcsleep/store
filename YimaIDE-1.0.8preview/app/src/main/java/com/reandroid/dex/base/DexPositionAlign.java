package com.reandroid.dex.base;

import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.AlignItem;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexPositionAlign extends AlignItem {
    public DexPositionAlign() {
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.align(blockReader.getPosition());
        super.onReadBytes(blockReader);
    }

    public DexPositionAlign(int i) {
        super(i);
    }
}
