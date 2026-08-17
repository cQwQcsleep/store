package com.reandroid.dex.dexopt;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SizedBlockList<T extends Block> extends BlockList<T> {
    private final IntegerReference bytesSize;

    public SizedBlockList(IntegerReference integerReference, Creator<? extends T> creator) {
        super(creator);
        this.bytesSize = integerReference;
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        int i = this.bytesSize.get();
        BlockReader blockReaderCreate = blockReader.create(i);
        while (blockReaderCreate.isAvailable()) {
            createNext().readBytes(blockReaderCreate);
        }
        blockReader.offset(i);
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void onRefreshed() {
        super.onRefreshed();
        this.bytesSize.set(countBytes());
    }
}
