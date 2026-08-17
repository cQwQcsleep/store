package com.reandroid.dex.sections;

import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.base.IntegerPair;
import com.reandroid.dex.data.DataItem;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DataSectionArray<T extends DataItem> extends SectionArray<T> {
    public DataSectionArray(IntegerPair integerPair, Creator<T> creator) {
        super(integerPair, creator);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private T binaryOffsetSearch(int i) {
        int size = size() - 1;
        int i2 = 0;
        while (size >= i2) {
            int i3 = ((size - i2) / 2) + i2;
            T t = get(i3);
            int offset = t.getOffset();
            if (offset == i) {
                return t;
            }
            if (offset < i) {
                i2 = i3 + 1;
            } else {
                size = i3 - 1;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private T lazyOffsetSearch(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            T t = get(i2);
            if (i == t.getOffset()) {
                return t;
            }
        }
        return null;
    }

    public T getAt(int i) {
        if (i <= 0) {
            return null;
        }
        T t = (T) binaryOffsetSearch(i);
        return t == null ? (T) lazyOffsetSearch(i) : t;
    }

    @Override // com.reandroid.dex.sections.SectionArray
    public void onPreRemove(T t) {
        super.onPreRemove(t);
        IntegerReference offsetReference = t.getOffsetReference();
        if (offsetReference != null) {
            offsetReference.set(0);
        }
    }

    @Override // com.reandroid.dex.base.BlockListArray
    public void readChild(BlockReader blockReader, T t) throws IOException {
        t.setPosition(blockReader.getPosition());
        t.onReadBytes(blockReader);
    }
}
