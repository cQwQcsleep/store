package com.reandroid.dex.base;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.OffsetSupplier;
import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FixedDexContainer extends FixedBlockContainer {
    public FixedDexContainer(int i) {
        super(i);
    }

    private boolean skipReading(Block block, BlockReader blockReader) {
        IntegerReference offsetReference;
        if ((block instanceof OffsetSupplier) && (offsetReference = ((OffsetSupplier) block).getOffsetReference()) != null) {
            int i = offsetReference.get();
            if (!isValidOffset(i)) {
                return true;
            }
            blockReader.seek(i);
        }
        return false;
    }

    public boolean isValidOffset(int i) {
        return i > 0;
    }

    public void nonCheckRead(BlockReader blockReader) throws IOException {
        Block[] childes = getChildes();
        if (childes == null) {
            return;
        }
        for (Block block : childes) {
            if (block != null) {
                block.readBytes(blockReader);
            }
        }
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        Block[] childes = getChildes();
        if (childes == null) {
            return;
        }
        if (skipReading(this, blockReader)) {
            return;
        }
        for (Block block : childes) {
            if (block != null && !skipReading(block, blockReader)) {
                block.readBytes(blockReader);
            }
        }
    }
}
