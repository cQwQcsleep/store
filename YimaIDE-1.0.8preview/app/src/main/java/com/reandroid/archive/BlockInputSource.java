package com.reandroid.archive;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.utils.Crc32OutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class BlockInputSource<T extends Block> extends ByteInputSource {
    private final T mBlock;

    public BlockInputSource(InputSource inputSource, T t) {
        this(inputSource.getAlias(), t);
        setMethod(inputSource.getMethod());
        setSort(inputSource.getSort());
    }

    public T getBlock() {
        BlockRefresh blockRefresh = this.mBlock;
        if (blockRefresh instanceof BlockRefresh) {
            blockRefresh.refresh();
        }
        return blockRefresh;
    }

    @Override // com.reandroid.archive.ByteInputSource
    public byte[] getBytes() {
        return getBlock().getBytes();
    }

    @Override // com.reandroid.archive.InputSource
    public long getCrc() throws IOException {
        Block block = getBlock();
        Crc32OutputStream crc32OutputStream = new Crc32OutputStream();
        block.writeBytes(crc32OutputStream);
        return crc32OutputStream.getValue();
    }

    @Override // com.reandroid.archive.InputSource
    public long getLength() throws IOException {
        return getBlock().countBytes();
    }

    @Override // com.reandroid.archive.ByteInputSource, com.reandroid.archive.InputSource
    public long write(OutputStream outputStream) throws IOException {
        return getBlock().writeBytes(outputStream);
    }

    public BlockInputSource(String str, T t) {
        super(new byte[0], str);
        this.mBlock = t;
    }
}
