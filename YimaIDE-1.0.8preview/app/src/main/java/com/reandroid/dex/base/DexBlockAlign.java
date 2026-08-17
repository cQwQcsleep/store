package com.reandroid.dex.base;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.AlignItem;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexBlockAlign extends AlignItem {
    private final Block block;

    public DexBlockAlign(Block block) {
        this.block = block;
    }

    public void align() {
        super.align(this.block);
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.align(this.block);
        super.onReadBytes(blockReader);
    }
}
