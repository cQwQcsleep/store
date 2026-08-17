package com.reandroid.arsc.header;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockContainer;
import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.container.ExpandableBlockContainer;
import com.reandroid.arsc.io.BlockLoad;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.BlockItem;
import com.reandroid.arsc.item.ByteArray;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.ShortItem;
import com.reandroid.utils.HexBytesWriter;
import com.reandroid.utils.HexUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class HeaderBlock extends ExpandableBlockContainer implements BlockLoad {
    private final ByteArray extraBytes;
    private final IntegerItem mChunkSize;
    private HeaderLoaded mHeaderLoaded;
    private final ShortItem mHeaderSize;
    private final ShortItem mType;

    public interface HeaderLoaded {
        void onChunkSizeLoaded(int i, int i2);

        void onChunkTypeLoaded(short s);

        void onHeaderSizeLoaded(int i);
    }

    public HeaderBlock(short s) {
        super(3);
        ShortItem shortItem = new ShortItem(s);
        this.mType = shortItem;
        ShortItem shortItem2 = new ShortItem();
        this.mHeaderSize = shortItem2;
        IntegerItem integerItem = new IntegerItem();
        this.mChunkSize = integerItem;
        this.extraBytes = new ByteArray();
        addChild(shortItem);
        addChild(shortItem2);
        addChild(integerItem);
        shortItem.setBlockLoad(this);
        shortItem2.setBlockLoad(this);
        integerItem.setBlockLoad(this);
    }

    private int readBytes(InputStream inputStream, Block block) throws IOException {
        if (block instanceof BlockItem) {
            return ((BlockItem) block).readBytes(inputStream);
        }
        int bytes = 0;
        if (block instanceof BlockList) {
            Iterator it = ((BlockList) block).getChildes().iterator();
            while (it.hasNext()) {
                bytes += readBytes(inputStream, (Block) it.next());
            }
            return bytes;
        }
        if (!(block instanceof BlockContainer)) {
            u8g.a("Can not read block type: ", block.getClass());
            return 0;
        }
        Block[] childes = ((BlockContainer) block).getChildes();
        int length = childes.length;
        int bytes2 = 0;
        while (bytes < length) {
            bytes2 += readBytes(inputStream, childes[bytes]);
            bytes++;
        }
        return bytes2;
    }

    private void refreshChunkSize() {
        Block parent = getParent();
        if (parent == null) {
            return;
        }
        setChunkSize(parent.countBytes());
    }

    private void refreshHeaderSize() {
        setHeaderSize((short) countBytes());
    }

    public int getChunkSize() {
        return this.mChunkSize.get();
    }

    public ChunkType getChunkType() {
        return ChunkType.get(this.mType.getShort());
    }

    public ByteArray getExtraBytes() {
        return this.extraBytes;
    }

    public int getHeaderSize() {
        return this.mHeaderSize.unsignedInt();
    }

    public int getMinimumSize() {
        return countBytes();
    }

    public short getType() {
        return this.mType.getShort();
    }

    public void initExtraBytes(ByteArray byteArray, int i) {
        if (i == 0) {
            return;
        }
        if (byteArray.getParent() == null) {
            addChild(byteArray);
        }
        byteArray.setSize(i);
    }

    @Override // com.reandroid.arsc.io.BlockLoad
    public void onBlockLoaded(BlockReader blockReader, Block block) throws IOException {
        ShortItem shortItem = this.mType;
        if (block == shortItem) {
            onChunkTypeLoaded(shortItem.getShort());
            return;
        }
        ShortItem shortItem2 = this.mHeaderSize;
        if (block == shortItem2) {
            onHeaderSizeLoaded(shortItem2.unsignedInt());
        } else if (block == this.mChunkSize) {
            onChunkSizeLoaded(shortItem2.unsignedInt(), this.mChunkSize.get());
        }
    }

    public void onChunkSizeLoaded(int i, int i2) {
        HeaderLoaded headerLoaded = this.mHeaderLoaded;
        if (headerLoaded != null) {
            headerLoaded.onChunkSizeLoaded(i, i2);
        }
    }

    public void onChunkTypeLoaded(short s) {
        HeaderLoaded headerLoaded = this.mHeaderLoaded;
        if (headerLoaded != null) {
            headerLoaded.onChunkTypeLoaded(s);
        }
    }

    public void onHeaderSizeLoaded(int i) {
        HeaderLoaded headerLoaded = this.mHeaderLoaded;
        if (headerLoaded != null) {
            headerLoaded.onHeaderSizeLoaded(i);
        }
    }

    @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        int position = blockReader.getPosition();
        super.onReadBytes(blockReader);
        initExtraBytes(this.extraBytes, getHeaderSize() - (blockReader.getPosition() - position));
        if (this.extraBytes.size() > 0) {
            this.extraBytes.readBytes(blockReader);
        }
    }

    @Override // com.reandroid.arsc.container.ExpandableBlockContainer, com.reandroid.arsc.base.BlockContainer
    public void onRefreshed() {
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public void refreshChildes() {
    }

    public final void refreshHeader() {
        refreshHeaderSize();
        refreshChunkSize();
    }

    public void setChunkSize(int i) {
        this.mChunkSize.set(i);
    }

    public void setHeaderLoaded(HeaderLoaded headerLoaded) {
        this.mHeaderLoaded = headerLoaded;
    }

    public void setHeaderSize(short s) {
        this.mHeaderSize.set(s);
    }

    public void setType(ChunkType chunkType) {
        setType(chunkType == null ? (short) 0 : chunkType.ID);
    }

    public String toHex() {
        return HexBytesWriter.toHex(getBytes());
    }

    public String toString() {
        short type = getType();
        ChunkType chunkType = ChunkType.get(type);
        StringBuilder sb = new StringBuilder();
        if (chunkType != null) {
            sb.append(chunkType.toString());
        } else {
            sb.append("Unknown type=");
            sb.append(HexUtil.toHex4(type));
        }
        sb.append("{ValueHeader=");
        sb.append(getHeaderSize());
        sb.append(", Chunk=");
        sb.append(getChunkSize());
        sb.append("}");
        return sb.toString();
    }

    public void setType(short s) {
        this.mType.set(s);
    }

    private int onReadBytes(InputStream inputStream) throws IOException {
        int bytes = readBytes(inputStream, this);
        initExtraBytes(this.extraBytes, getHeaderSize() - bytes);
        return this.extraBytes.size() > 0 ? bytes + this.extraBytes.readBytes(inputStream) : bytes;
    }

    public HeaderBlock(ChunkType chunkType) {
        this(chunkType.ID);
    }

    public int readBytes(InputStream inputStream) throws IOException {
        int iOnReadBytes = onReadBytes(inputStream);
        super.notifyBlockLoad();
        return iOnReadBytes;
    }
}
