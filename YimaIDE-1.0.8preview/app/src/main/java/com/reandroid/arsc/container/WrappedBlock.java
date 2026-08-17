package com.reandroid.arsc.container;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockCounter;
import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.io.BlockReader;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class WrappedBlock extends Block implements BlockRefresh {
    private final Block baseBlock;

    public WrappedBlock(Block block) {
        block.getClass();
        this.baseBlock = block;
        block.setParent(this);
        block.setIndex(0);
    }

    @Override // com.reandroid.arsc.base.Block
    public int countBytes() {
        return getBaseBlock().countBytes();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        Block baseBlock = getBaseBlock();
        return obj instanceof WrappedBlock ? baseBlock.equals(((WrappedBlock) obj).getBaseBlock()) : baseBlock.equals(obj);
    }

    public final Block getBaseBlock() {
        return this.baseBlock;
    }

    @Override // com.reandroid.arsc.base.Block
    public byte[] getBytes() {
        return getBaseBlock().getBytes();
    }

    public int hashCode() {
        return getBaseBlock().hashCode();
    }

    @Override // com.reandroid.arsc.base.Block
    public boolean isNull() {
        return getBaseBlock().isNull();
    }

    @Override // com.reandroid.arsc.base.Block
    public void onCountUpTo(BlockCounter blockCounter) {
        getBaseBlock().onCountUpTo(blockCounter);
    }

    public void onPreRefresh() {
    }

    @Override // com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        getBaseBlock().readBytes(blockReader);
    }

    public void onRefreshed() {
    }

    @Override // com.reandroid.arsc.base.Block
    public int onWriteBytes(OutputStream outputStream) throws IOException {
        return getBaseBlock().writeBytes(outputStream);
    }

    @Override // com.reandroid.arsc.base.BlockRefresh
    public final void refresh() {
        Object baseBlock = getBaseBlock();
        if (baseBlock instanceof BlockRefresh) {
            onPreRefresh();
            ((BlockRefresh) baseBlock).refresh();
            onRefreshed();
        }
    }

    @Override // com.reandroid.arsc.base.Block
    public void setNull(boolean z) {
        getBaseBlock().setNull(z);
    }

    public String toString() {
        return getBaseBlock().toString();
    }
}
