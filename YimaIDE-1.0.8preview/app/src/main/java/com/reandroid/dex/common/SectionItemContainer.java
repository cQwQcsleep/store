package com.reandroid.dex.common;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockCounter;
import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.base.OffsetSupplier;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.NumberIntegerReference;
import com.reandroid.dex.base.OffsetReceiver;
import com.reandroid.dex.base.PositionedItem;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SectionItemContainer extends SectionItem implements BlockRefresh, PositionedItem, OffsetSupplier, OffsetReceiver {
    private static final Block[] EMPTY = new Block[0];
    private final Block[] mChildBlocks;
    private IntegerReference mReference;

    public SectionItemContainer(int i) {
        super(0);
        this.mChildBlocks = i == 0 ? EMPTY : new Block[i];
    }

    private Block[] getChildBlocks() {
        return this.mChildBlocks;
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

    public void addChildBlock(int i, Block block) {
        this.mChildBlocks[i] = block;
        if (block != null) {
            block.setIndex(i);
            block.setParent(this);
        }
    }

    public int countBytes() {
        Block[] childBlocks;
        if (isNull() || (childBlocks = getChildBlocks()) == null) {
            return 0;
        }
        int iCountBytes = 0;
        for (Block block : childBlocks) {
            if (block != null) {
                iCountBytes += block.countBytes();
            }
        }
        return iCountBytes;
    }

    public byte[] getBytes() {
        Block[] childBlocks;
        byte[] bArrAddBytes = null;
        if (isNull() || (childBlocks = getChildBlocks()) == null) {
            return null;
        }
        for (Block block : childBlocks) {
            if (block != null) {
                bArrAddBytes = Block.addBytes(bArrAddBytes, block.getBytes());
            }
        }
        return bArrAddBytes;
    }

    public final byte[] getBytesInternal() {
        throw new RuntimeException("Not block item");
    }

    public Block getChildBlockAt(int i) {
        return this.mChildBlocks[i];
    }

    @Override // com.reandroid.dex.common.SectionItem
    public int getIdx() {
        return getOffset();
    }

    public int getOffset() {
        IntegerReference offsetReference = getOffsetReference();
        if (offsetReference != null) {
            return offsetReference.get();
        }
        return 0;
    }

    @Override // com.reandroid.arsc.base.OffsetSupplier, com.reandroid.dex.base.DexArraySupplier
    public IntegerReference getOffsetReference() {
        return this.mReference;
    }

    public boolean isValidOffset(int i) {
        return i > 0;
    }

    public void nonCheckRead(BlockReader blockReader) throws IOException {
        Block[] childBlocks = getChildBlocks();
        if (childBlocks == null) {
            return;
        }
        for (Block block : childBlocks) {
            if (block != null) {
                block.readBytes(blockReader);
            }
        }
    }

    public void onBytesChanged() {
        throw new RuntimeException("Not block item");
    }

    public void onCountUpTo(BlockCounter blockCounter) {
        if (blockCounter.FOUND) {
            return;
        }
        blockCounter.setCurrent(this);
        if (blockCounter.END == this) {
            blockCounter.FOUND = true;
            return;
        }
        Block[] childBlocks = getChildBlocks();
        if (childBlocks == null) {
            return;
        }
        int length = childBlocks.length;
        for (int i = 0; i < length && !blockCounter.FOUND; i++) {
            Block block = childBlocks[i];
            if (block != null) {
                block.onCountUpTo(blockCounter);
            }
        }
    }

    public void onPreRefresh() {
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        Block[] childBlocks = getChildBlocks();
        if (childBlocks == null) {
            return;
        }
        if (skipReading(this, blockReader)) {
            return;
        }
        for (Block block : childBlocks) {
            if (block != null && !skipReading(block, blockReader)) {
                block.readBytes(blockReader);
            }
        }
    }

    public void onRefreshed() {
    }

    public int onWriteBytes(OutputStream outputStream) throws IOException {
        Block[] childBlocks;
        if (isNull() || (childBlocks = getChildBlocks()) == null) {
            return 0;
        }
        int iWriteBytes = 0;
        for (Block block : childBlocks) {
            if (block != null) {
                iWriteBytes += block.writeBytes(outputStream);
            }
        }
        return iWriteBytes;
    }

    public int readBytes(InputStream inputStream) throws IOException {
        throw new RuntimeException("Not block item");
    }

    public void refresh() {
        if (isNull()) {
            return;
        }
        onPreRefresh();
        refreshChildes();
        onRefreshed();
    }

    public void refreshChildes() {
        BlockRefresh[] childBlocks = getChildBlocks();
        if (childBlocks == null) {
            return;
        }
        for (BlockRefresh blockRefresh : childBlocks) {
            if (blockRefresh instanceof BlockRefresh) {
                blockRefresh.refresh();
            }
        }
    }

    @Override // com.reandroid.dex.base.PositionedItem
    public void removeLastAlign() {
    }

    @Override // com.reandroid.dex.base.OffsetReceiver
    public void setOffsetReference(IntegerReference integerReference) {
        this.mReference = integerReference;
    }

    @Override // com.reandroid.dex.base.PositionedItem
    public void setPosition(int i) {
        IntegerReference offsetReference = getOffsetReference();
        if (offsetReference == null) {
            setOffsetReference(new NumberIntegerReference(i));
        } else {
            offsetReference.set(i);
        }
    }
}
