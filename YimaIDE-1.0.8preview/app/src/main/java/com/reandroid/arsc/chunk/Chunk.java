package com.reandroid.arsc.chunk;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.container.ExpandableBlockContainer;
import com.reandroid.arsc.container.SingleBlockContainer;
import com.reandroid.arsc.header.HeaderBlock;
import com.reandroid.arsc.header.InfoHeader;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.AlignItem;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Chunk<T extends HeaderBlock> extends ExpandableBlockContainer {
    private AlignItem alignItem;
    private final SingleBlockContainer<Block> firstPlaceHolder;
    private final T mHeaderBlock;

    public Chunk(T t, SingleBlockContainer<Block> singleBlockContainer, int i) {
        super(i + (singleBlockContainer == null ? 2 : 3));
        this.mHeaderBlock = t;
        this.firstPlaceHolder = singleBlockContainer;
        addChild(t);
        if (singleBlockContainer != null) {
            addChild(singleBlockContainer);
        }
    }

    private void updateAlign() {
        AlignItem alignItem = getAlignItem();
        alignItem.setSize(0);
        alignItem.align(this);
    }

    public void checkInvalidChunk(HeaderBlock headerBlock) throws IOException {
        ChunkType chunkType = headerBlock.getChunkType();
        if (chunkType == null || chunkType == ChunkType.NULL) {
            r8g.a("Invalid chunk: ", headerBlock);
        }
    }

    public AlignItem getAlignItem() {
        AlignItem alignItem = this.alignItem;
        if (alignItem != null) {
            return alignItem;
        }
        AlignItem alignItem2 = new AlignItem();
        addChild(alignItem2);
        this.alignItem = alignItem2;
        return alignItem2;
    }

    public SingleBlockContainer<Block> getFirstPlaceHolder() {
        return this.firstPlaceHolder;
    }

    public final T getHeaderBlock() {
        return this.mHeaderBlock;
    }

    public void onChunkLoaded() {
    }

    public abstract void onChunkRefreshed();

    @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        InfoHeader headerBlock = blockReader.readHeaderBlock();
        checkInvalidChunk(headerBlock);
        BlockReader blockReaderCreate = blockReader.create(headerBlock.getChunkSize());
        onReadChildes(blockReaderCreate);
        blockReader.offset(headerBlock.getChunkSize());
        blockReaderCreate.close();
        onChunkLoaded();
    }

    public void onReadChildes(BlockReader blockReader) throws IOException {
        AlignItem alignItem = getAlignItem();
        alignItem.setSize(0);
        super.onReadBytes(blockReader);
        alignItem.alignSafe(blockReader);
    }

    @Override // com.reandroid.arsc.container.ExpandableBlockContainer, com.reandroid.arsc.base.BlockContainer
    public final void onRefreshed() {
        updateAlign();
        getHeaderBlock().refreshHeader();
        onChunkRefreshed();
    }

    public void setHeaderLoaded(HeaderBlock.HeaderLoaded headerLoaded) {
        getHeaderBlock().setHeaderLoaded(headerLoaded);
    }

    public String toString() {
        return getClass().getSimpleName() + ": " + getHeaderBlock();
    }

    public Chunk(T t, int i) {
        this(t, new SingleBlockContainer(), i);
    }
}
