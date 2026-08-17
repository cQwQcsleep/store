package com.reandroid.arsc.header;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.ByteItem;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.ShortItem;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TypeHeader extends HeaderBlock {
    private final ResConfig config;
    private final IntegerItem count;
    private final IntegerItem entriesStart;
    private final ByteItem flags;
    private final ByteItem id;
    private OffsetTypeChangedListener offsetTypeChangedListener;
    public static final int OFFSET_32 = ObjectsUtil.of(0);
    public static final int OFFSET_SPARSE = ObjectsUtil.of(1);
    public static final int OFFSET_16 = ObjectsUtil.of(2);

    public interface OffsetTypeChangedListener {
        void onOffsetTypeChanged(int i);
    }

    public TypeHeader() {
        super(ChunkType.TYPE.ID);
        ByteItem byteItem = new ByteItem();
        this.id = byteItem;
        ByteItem byteItem2 = new ByteItem();
        this.flags = byteItem2;
        Block shortItem = new ShortItem();
        IntegerItem integerItem = new IntegerItem();
        this.count = integerItem;
        IntegerItem integerItem2 = new IntegerItem();
        this.entriesStart = integerItem2;
        Block resConfig = new ResConfig();
        this.config = resConfig;
        addChild(byteItem);
        addChild(byteItem2);
        addChild(shortItem);
        addChild(integerItem);
        addChild(integerItem2);
        addChild(resConfig);
    }

    private void notifyOffsetTypeChanged() {
        OffsetTypeChangedListener offsetTypeChangedListener = this.offsetTypeChangedListener;
        if (offsetTypeChangedListener != null) {
            offsetTypeChangedListener.onOffsetTypeChanged(getOffsetType());
        }
    }

    public static TypeHeader read(BlockReader blockReader) throws IOException {
        TypeHeader typeHeader = new TypeHeader(false, false);
        if (blockReader.available() < typeHeader.getMinimumSize()) {
            e44.a("Too few bytes to read type header, available = ", blockReader.available());
            return null;
        }
        int position = blockReader.getPosition();
        typeHeader.readBytes(blockReader);
        blockReader.seek(position);
        return typeHeader;
    }

    public ResConfig getConfig() {
        return this.config;
    }

    public IntegerReference getCountItem() {
        return this.count;
    }

    public IntegerReference getEntriesStart() {
        return this.entriesStart;
    }

    public ByteItem getFlags() {
        return this.flags;
    }

    public ByteItem getId() {
        return this.id;
    }

    @Override // com.reandroid.arsc.header.HeaderBlock
    public int getMinimumSize() {
        return 36;
    }

    public int getOffsetType() {
        return getFlags().get() & 3;
    }

    public boolean isOffset16() {
        return (getFlags().get() & 3) == 2;
    }

    public boolean isSparse() {
        return (getFlags().get() & 3) == 1;
    }

    @Override // com.reandroid.arsc.header.HeaderBlock, com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.onReadBytes(blockReader);
        notifyOffsetTypeChanged();
    }

    public void setOffset16(boolean z) {
        if (z != isOffset16()) {
            setOffsetType(z ? OFFSET_16 : OFFSET_32);
        }
    }

    public void setOffsetType(int i) {
        if (i != getOffsetType()) {
            if (i != OFFSET_32 && i != OFFSET_16 && i != OFFSET_SPARSE) {
                qf1.a("Invalid offset type: ", i);
            } else {
                getFlags().set(i | (getFlags().get() & 252));
                notifyOffsetTypeChanged();
            }
        }
    }

    public void setOffsetTypeChangedListener(OffsetTypeChangedListener offsetTypeChangedListener) {
        this.offsetTypeChangedListener = offsetTypeChangedListener;
    }

    public void setSparse(boolean z) {
        if (z != isSparse()) {
            setOffsetType(z ? OFFSET_SPARSE : OFFSET_32);
        }
    }

    @Override // com.reandroid.arsc.header.HeaderBlock
    public String toString() {
        if (getChunkType() != ChunkType.TYPE) {
            return super.toString();
        }
        return getClass().getSimpleName() + " {id=" + getId().toHex() + ", flags=" + getFlags().toHex() + ", count=" + getCountItem() + ", entriesStart=" + getEntriesStart() + ", config=" + getConfig() + '}';
    }

    public void setOffsetType(boolean z, boolean z2) {
        int i;
        if (z) {
            i = OFFSET_SPARSE;
        } else if (z2) {
            i = OFFSET_16;
        } else {
            i = OFFSET_32;
        }
        setOffsetType(i);
    }

    public TypeHeader(boolean z, boolean z2) {
        this();
        setSparse(z);
        setOffset16(z2);
    }
}
