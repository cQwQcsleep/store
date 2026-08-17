package com.reandroid.archive.block;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.container.ExpandableBlockContainer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.LongItem;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class LengthPrefixedBlock extends ExpandableBlockContainer {
    private final Block numberBlock;

    public LengthPrefixedBlock(int i, boolean z) {
        super(i + 1);
        LongItem longItem = z ? new LongItem() : new IntegerItem();
        this.numberBlock = longItem;
        addChild(longItem);
    }

    public long getDataSize() {
        LongItem longItem = this.numberBlock;
        return longItem instanceof LongItem ? longItem.getLong() : ((IntegerItem) longItem).get();
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        if (blockReader.isAvailable()) {
            Block block = this.numberBlock;
            block.readBytes(blockReader);
            int dataSize = (int) getDataSize();
            if (dataSize <= 0) {
                onSizeLoaded(0);
                return;
            }
            onSizeLoaded(dataSize);
            BlockReader blockReaderCreate = blockReader.create(dataSize);
            for (Block block2 : getChildes()) {
                if (block2 != block) {
                    block2.readBytes(blockReaderCreate);
                }
            }
            blockReader.offset(dataSize);
        }
    }

    public void onRefreshed() {
        setDataSize(countBytes() - this.numberBlock.countBytes());
    }

    public void onSizeLoaded(int i) {
    }

    public void setDataSize(long j) {
        LongItem longItem = this.numberBlock;
        if (longItem instanceof LongItem) {
            longItem.set(j);
        } else {
            ((IntegerItem) longItem).set((int) j);
        }
    }

    public String toString() {
        return "size=" + this.numberBlock;
    }
}
