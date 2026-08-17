package com.reandroid.arsc.array;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockArray;
import com.reandroid.arsc.base.BlockCounter;
import com.reandroid.arsc.io.BlockLoad;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.AlignItem;
import com.reandroid.arsc.item.IntegerReference;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class OffsetBlockArray<T extends Block> extends BlockArray<T> implements BlockLoad {
    private final AlignItem alignItem = new AlignItem();
    private final IntegerReference countReference;
    private final OffsetArray mOffsetArray;
    private final IntegerReference startReference;

    /* JADX WARN: Multi-variable type inference failed */
    public OffsetBlockArray(OffsetArray offsetArray, IntegerReference integerReference, IntegerReference integerReference2) {
        this.mOffsetArray = offsetArray;
        this.countReference = integerReference;
        this.startReference = integerReference2;
        if (integerReference instanceof Block) {
            ((Block) integerReference).setBlockLoad(this);
        }
    }

    private void calculateOffsets() {
        int iCountBytes;
        int size = size();
        OffsetArray offsetArray = getOffsetArray();
        offsetArray.setSize(size);
        if (size == 0) {
            return;
        }
        int i = 0;
        int i2 = 0;
        while (i < size) {
            Block block = get(i);
            if (block == null || block.isNull()) {
                iCountBytes = i2;
                i2 = -1;
            } else {
                iCountBytes = block.countBytes() + i2;
            }
            offsetArray.setOffset(i, i2);
            i++;
            i2 = iCountBytes;
        }
    }

    private int getZeroPosition() {
        int i = this.startReference.get();
        if (i < 0) {
            return 0;
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void refreshStart() {
        if (size() == 0) {
            this.startReference.set(0);
            this.alignItem.clear();
            return;
        }
        Block parent = getParent();
        if (parent == null) {
            return;
        }
        this.startReference.set(parent.countUpTo(this));
    }

    @Override // com.reandroid.arsc.base.BlockArray
    public void clear() {
        super.clear();
        this.mOffsetArray.clear();
        this.startReference.set(0);
        this.countReference.set(0);
        this.alignItem.clear();
    }

    public int countBytes() {
        return super.countBytes() + getAlignItem().countBytes();
    }

    public AlignItem getAlignItem() {
        return this.alignItem;
    }

    public byte[] getBytes() {
        byte[] bytes = super.getBytes();
        if (bytes == null) {
            return null;
        }
        return Block.addBytes(bytes, this.alignItem.getBytes());
    }

    public OffsetArray getOffsetArray() {
        return this.mOffsetArray;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // com.reandroid.arsc.io.BlockLoad
    public void onBlockLoaded(BlockReader blockReader, Block block) throws IOException {
        IntegerReference integerReference = this.countReference;
        if (block == integerReference) {
            int i = integerReference.get();
            setSize(i);
            getOffsetArray().setSize(i);
        }
    }

    public void onCountUpTo(BlockCounter blockCounter) {
        super.onCountUpTo(blockCounter);
        if (blockCounter.FOUND) {
            return;
        }
        getAlignItem().onCountUpTo(blockCounter);
    }

    @Override // com.reandroid.arsc.base.BlockArray
    public void onReadBytes(BlockReader blockReader) throws IOException {
        int size = size();
        if (size == 0) {
            return;
        }
        int[] offsets = this.mOffsetArray.getOffsets();
        int zeroPosition = getZeroPosition();
        blockReader.seek(zeroPosition);
        int i = zeroPosition;
        for (int i2 = 0; i2 < size; i2++) {
            Block block = get(i2);
            int i3 = offsets[i2];
            if (i3 == -1) {
                block.setNull(true);
            } else {
                blockReader.seek(i3 + zeroPosition);
                block.readBytes(blockReader);
                int position = blockReader.getPosition();
                if (position > i) {
                    i = position;
                }
            }
        }
        if (i > 0) {
            blockReader.seek(i);
            refreshAlignment(blockReader, getAlignItem());
        }
    }

    public void onRefreshed() {
        calculateOffsets();
        refreshCount();
        refreshStart();
        refreshAlignment(getAlignItem());
    }

    public int onWriteBytes(OutputStream outputStream) throws IOException {
        int iOnWriteBytes = super.onWriteBytes(outputStream);
        if (iOnWriteBytes == 0) {
            return 0;
        }
        return iOnWriteBytes + this.alignItem.writeBytes(outputStream);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void refreshAlignment(AlignItem alignItem) {
        if (size() == 0) {
            alignItem.clear();
        } else {
            alignItem.align((Block) this);
        }
    }

    public void refreshCount() {
        this.countReference.set(size());
    }

    public void refreshCountAndStart() {
        refreshCount();
        refreshStart();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(": size = ");
        int size = size();
        sb.append(size);
        int i = this.countReference.get();
        if (size != i) {
            sb.append(", sizeValue = ");
            sb.append(i);
        }
        sb.append(", start = ");
        sb.append(this.startReference.get());
        return sb.toString();
    }

    public void refreshAlignment(BlockReader blockReader, AlignItem alignItem) throws IOException {
        refreshAlignment(alignItem);
    }
}
