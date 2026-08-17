package com.reandroid.arsc.header;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.ByteItem;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.ShortItem;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class SpecHeader extends HeaderBlock {
    private final IntegerItem entryCount;
    private final ByteItem id;

    public SpecHeader() {
        super(ChunkType.SPEC.ID);
        ByteItem byteItem = new ByteItem();
        this.id = byteItem;
        Block byteItem2 = new ByteItem();
        Block shortItem = new ShortItem();
        IntegerItem integerItem = new IntegerItem();
        this.entryCount = integerItem;
        addChild(byteItem);
        addChild(byteItem2);
        addChild(shortItem);
        addChild(integerItem);
    }

    public static SpecHeader read(BlockReader blockReader) throws IOException {
        SpecHeader specHeader = new SpecHeader();
        if (blockReader.available() < specHeader.countBytes()) {
            e44.a("Too few bytes to read spec header, available = ", blockReader.available());
            return null;
        }
        int position = blockReader.getPosition();
        specHeader.readBytes(blockReader);
        blockReader.seek(position);
        return specHeader;
    }

    public IntegerItem getEntryCount() {
        return this.entryCount;
    }

    public ByteItem getId() {
        return this.id;
    }

    @Override // com.reandroid.arsc.header.HeaderBlock
    public String toString() {
        if (getChunkType() != ChunkType.SPEC) {
            return super.toString();
        }
        return getClass().getSimpleName() + " {id=" + getId().toHex() + ", entryCount=" + getEntryCount() + '}';
    }
}
