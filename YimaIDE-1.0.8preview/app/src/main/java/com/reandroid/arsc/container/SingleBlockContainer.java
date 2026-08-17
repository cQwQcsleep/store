package com.reandroid.arsc.container;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockContainer;
import com.reandroid.arsc.base.BlockCounter;
import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.io.BlockReader;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class SingleBlockContainer<T extends Block> extends BlockContainer<T> {
    private T mItem;

    @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public int countBytes() {
        T t = this.mItem;
        if (t != null) {
            return t.countBytes();
        }
        return 0;
    }

    @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public byte[] getBytes() {
        T t = this.mItem;
        if (t != null) {
            return t.getBytes();
        }
        return null;
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public T[] getChildes() {
        return null;
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public int getChildesCount() {
        return this.mItem == null ? 0 : 1;
    }

    public T getItem() {
        return this.mItem;
    }

    public boolean hasItem() {
        return this.mItem != null;
    }

    @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public void onCountUpTo(BlockCounter blockCounter) {
        if (blockCounter.FOUND) {
            return;
        }
        blockCounter.setCurrent(this);
        if (blockCounter.END == this) {
            blockCounter.FOUND = true;
            return;
        }
        T t = this.mItem;
        if (t != null) {
            t.onCountUpTo(blockCounter);
        }
    }

    @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        T t = this.mItem;
        if (t != null) {
            t.readBytes(blockReader);
        }
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public void onRefreshed() {
    }

    @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public int onWriteBytes(OutputStream outputStream) throws IOException {
        T t = this.mItem;
        if (t != null) {
            return t.writeBytes(outputStream);
        }
        return 0;
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public void refreshChildes() {
        T t = this.mItem;
        if (t instanceof BlockRefresh) {
            ((BlockRefresh) t).refresh();
        }
    }

    public void setItem(T t) {
        if (t != null) {
            this.mItem = t;
            t.setIndex(getIndex());
            t.setParent(this);
        } else {
            T t2 = this.mItem;
            if (t2 != null) {
                t2.setIndex(-1);
                t2.setParent(null);
            }
            this.mItem = null;
        }
    }

    public String toString() {
        T t = this.mItem;
        return t != null ? t.toString() : getClass().getSimpleName().concat(": EMPTY");
    }
}
