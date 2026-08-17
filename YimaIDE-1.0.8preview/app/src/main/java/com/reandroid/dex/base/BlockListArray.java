package com.reandroid.dex.base;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockArray;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.base.OffsetSupplier;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.header.DexHeader;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class BlockListArray<T extends Block> extends BlockArray<T> implements OffsetSupplier, DexArraySupplier<T>, Creator<T> {
    private final IntegerPair countAndOffset;

    public BlockListArray(IntegerPair integerPair, Creator<T> creator) {
        super(creator);
        this.countAndOffset = integerPair;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isDexHeaderArray() {
        return get(0) instanceof DexHeader;
    }

    private void positionItem(Block block, BlockReader blockReader) {
        if (block instanceof PositionedItem) {
            ((PositionedItem) block).setPosition(blockReader.getPosition());
        }
    }

    private boolean skipReading(IntegerPair integerPair, BlockReader blockReader) {
        IntegerReference second;
        if (integerPair != null && (second = integerPair.getSecond()) != null) {
            int i = second.get();
            if (!isValidOffset(i)) {
                return true;
            }
            blockReader.seek(i);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int countBytes() {
        Block first = getFirst();
        return first instanceof FixedSizeBlock ? first.countBytes() * getCount() : super/*com.reandroid.arsc.container.BlockList*/.countBytes();
    }

    public IntegerPair getCountAndOffset() {
        return this.countAndOffset;
    }

    @Override // com.reandroid.arsc.base.OffsetSupplier, com.reandroid.dex.base.DexArraySupplier
    public IntegerReference getOffsetReference() {
        return getCountAndOffset().getSecond();
    }

    public boolean isValidOffset(int i) {
        if (i == 0) {
            return isDexHeaderArray();
        }
        return i > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.arsc.base.Creator
    public T newInstance() {
        return (T) getCreator().newInstance();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onReadBytes(BlockReader blockReader) throws IOException {
        if (skipReading(getCountAndOffset(), blockReader)) {
            return;
        }
        readChildes(blockReader);
        trimToSize();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onRefreshed() {
        updateCount();
        super/*com.reandroid.arsc.container.BlockList*/.onRefreshed();
    }

    public void readChild(BlockReader blockReader, T t) throws IOException {
        positionItem(t, blockReader);
        t.readBytes(blockReader);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void readChildes(BlockReader blockReader) throws IOException {
        int i = this.countAndOffset.getFirst().get();
        setSize(i);
        for (int i2 = 0; i2 < i; i2++) {
            readChild(blockReader, get(i2));
        }
        onChanged();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void updateCount() {
        getCountAndOffset().getFirst().set(size());
    }
}
