package com.reandroid.dex.base;

import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Le128 extends DexBlockItem implements IntegerReference {
    private int value;

    public Le128() {
        super(1);
    }

    public int get() {
        return this.value;
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        this.value = readLe128(blockReader);
    }

    public abstract int readLe128(BlockReader blockReader) throws IOException;

    public void set(int i) {
        if (i == this.value) {
            return;
        }
        this.value = i;
        writeValue(i);
    }

    public String toString() {
        return Integer.toString(get());
    }

    public abstract void writeValue(int i);
}
