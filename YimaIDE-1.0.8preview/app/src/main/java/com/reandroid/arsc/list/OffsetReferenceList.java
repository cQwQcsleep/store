package com.reandroid.arsc.list;

import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.base.DirectStreamReader;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.OffsetItem;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class OffsetReferenceList<T extends OffsetItem> extends BlockList<T> implements DirectStreamReader {
    private final IntegerReference countReference;

    public OffsetReferenceList(IntegerReference integerReference, Creator<? extends T> creator) {
        super(creator);
        this.countReference = integerReference;
    }

    public void clear() {
        clearChildes();
    }

    public IntegerReference getCountReference() {
        return this.countReference;
    }

    public boolean hasSimilarEntries() {
        return true;
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        int i = getCountReference().get();
        setSize(i);
        for (int i2 = 0; i2 < i; i2++) {
            ((OffsetItem) get(i2)).readBytes(blockReader);
        }
    }

    public int readBytes(InputStream inputStream) throws IOException {
        int i = getCountReference().get();
        setSize(i);
        int bytes = 0;
        for (int i2 = 0; i2 < i; i2++) {
            bytes += ((OffsetItem) get(i2)).readBytes(inputStream);
        }
        return bytes;
    }

    public void refreshChildes() {
    }

    public void setSize(int i) {
        super.setSize(i);
        getCountReference().set(i);
    }
}
