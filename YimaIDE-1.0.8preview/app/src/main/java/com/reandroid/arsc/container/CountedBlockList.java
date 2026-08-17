package com.reandroid.arsc.container;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.base.DirectStreamReader;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CountedBlockList<T extends Block> extends BlockList<T> implements DirectStreamReader {
    private final IntegerReference countReference;

    public CountedBlockList(Creator<? extends T> creator, IntegerReference integerReference) {
        super(creator);
        this.countReference = integerReference;
    }

    public IntegerReference getCountReference() {
        return this.countReference;
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        setSize(getCountReference().get());
        readChildes(blockReader);
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void onRefreshed() {
        updateCountReference();
        super.onRefreshed();
    }

    @Override // com.reandroid.arsc.base.DirectStreamReader
    public int readBytes(InputStream inputStream) throws IOException, ClassCastException {
        int i = getCountReference().get();
        setSize(i);
        int bytes = 0;
        for (int i2 = 0; i2 < i; i2++) {
            bytes += get(i2).readBytes(inputStream);
        }
        return bytes;
    }

    public void updateCountReference() {
        getCountReference().set(size());
    }
}
