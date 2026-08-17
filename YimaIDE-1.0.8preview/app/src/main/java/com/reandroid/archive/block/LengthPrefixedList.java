package com.reandroid.archive.block;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockCreator;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.arsc.container.SingleBlockContainer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.LongItem;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class LengthPrefixedList<T extends Block> extends FixedBlockContainer implements BlockCreator<T>, Iterable<T> {
    private final SingleBlockContainer<Block> bottomContainer;
    private final BlockList<T> elements;
    private final Block numberBlock;

    public LengthPrefixedList(boolean z) {
        super(3);
        LongItem longItem = z ? new LongItem() : new IntegerItem();
        this.numberBlock = longItem;
        BlockList<T> blockList = new BlockList<>();
        this.elements = blockList;
        SingleBlockContainer<Block> singleBlockContainer = new SingleBlockContainer<>();
        this.bottomContainer = singleBlockContainer;
        addChild(0, longItem);
        addChild(1, blockList);
        addChild(2, singleBlockContainer);
    }

    private void readElements(BlockReader blockReader) throws IOException {
        int iCountBytes = this.bottomContainer.countBytes() + 4;
        while (blockReader.available() > iCountBytes) {
            int position = blockReader.getPosition();
            add(mo6464newInstance()).readBytes(blockReader);
            if (position == blockReader.getPosition()) {
                return;
            }
        }
    }

    public T add(T t) {
        this.elements.add(t);
        return t;
    }

    public Block getBottomBlock() {
        return this.bottomContainer.getItem();
    }

    public long getDataSize() {
        LongItem longItem = this.numberBlock;
        return longItem instanceof LongItem ? longItem.getLong() : ((IntegerItem) longItem).get();
    }

    public List<T> getElements() {
        return this.elements.getChildes();
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return this.elements.iterator();
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        if (blockReader.isAvailable()) {
            this.numberBlock.readBytes(blockReader);
            int dataSize = (int) getDataSize();
            if (dataSize <= 0) {
                return;
            }
            BlockReader blockReaderCreate = blockReader.create(dataSize);
            readElements(blockReaderCreate);
            this.bottomContainer.readBytes(blockReaderCreate);
            blockReader.offset(dataSize);
        }
    }

    public void onRefreshed() {
        setDataSize(countBytes() - this.numberBlock.countBytes());
    }

    public boolean remove(T t) {
        return this.elements.remove(t);
    }

    public void setBottomBlock(Block block) {
        this.bottomContainer.setItem(block);
    }

    public void setDataSize(long j) {
        LongItem longItem = this.numberBlock;
        if (longItem instanceof LongItem) {
            longItem.set(j);
        } else {
            ((IntegerItem) longItem).set((int) j);
        }
    }

    public int size() {
        return this.elements.size();
    }

    public void sort(Comparator<T> comparator) {
        this.elements.sort(comparator);
    }

    public String toString() {
        return "size=" + this.numberBlock + ", count=" + size();
    }
}
