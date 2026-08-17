package com.reandroid.arsc.base;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.io.BlockReader;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class BlockContainer<T extends Block> extends Block implements BlockRefresh {
    @Override // com.reandroid.arsc.base.Block
    public int countBytes() {
        Block[] childes;
        if (isNull() || (childes = getChildes()) == null) {
            return 0;
        }
        int iCountBytes = 0;
        for (Block block : childes) {
            if (block != null) {
                iCountBytes += block.countBytes();
            }
        }
        return iCountBytes;
    }

    @Override // com.reandroid.arsc.base.Block
    public byte[] getBytes() {
        Block[] childes;
        byte[] bArrAddBytes = null;
        if (isNull() || (childes = getChildes()) == null) {
            return null;
        }
        for (Block block : childes) {
            if (block != null) {
                bArrAddBytes = Block.addBytes(bArrAddBytes, block.getBytes());
            }
        }
        return bArrAddBytes;
    }

    public abstract T[] getChildes();

    public abstract int getChildesCount();

    @Override // com.reandroid.arsc.base.Block
    public void onCountUpTo(BlockCounter blockCounter) {
        if (blockCounter.FOUND) {
            return;
        }
        blockCounter.setCurrent(this);
        if (blockCounter.END == this) {
            blockCounter.FOUND = true;
            return;
        }
        Block[] childes = getChildes();
        if (childes == null) {
            return;
        }
        int length = childes.length;
        for (int i = 0; i < length && !blockCounter.FOUND; i++) {
            Block block = childes[i];
            if (block != null) {
                block.onCountUpTo(blockCounter);
            }
        }
    }

    public void onPreRefresh() {
    }

    @Override // com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        Block[] childes = getChildes();
        if (childes == null) {
            return;
        }
        for (Block block : childes) {
            if (block != null) {
                block.readBytes(blockReader);
            }
        }
    }

    public abstract void onRefreshed();

    @Override // com.reandroid.arsc.base.Block
    public int onWriteBytes(OutputStream outputStream) throws IOException {
        Block[] childes;
        if (isNull() || (childes = getChildes()) == null) {
            return 0;
        }
        int iWriteBytes = 0;
        for (Block block : childes) {
            if (block != null) {
                iWriteBytes += block.writeBytes(outputStream);
            }
        }
        return iWriteBytes;
    }

    @Override // com.reandroid.arsc.base.BlockRefresh
    public final void refresh() {
        if (isNull()) {
            return;
        }
        onPreRefresh();
        refreshChildes();
        onRefreshed();
    }

    public void refreshChildes() {
        Object[] childes = getChildes();
        if (childes == null) {
            return;
        }
        for (Object obj : childes) {
            if (obj instanceof BlockRefresh) {
                ((BlockRefresh) obj).refresh();
            }
        }
    }
}
