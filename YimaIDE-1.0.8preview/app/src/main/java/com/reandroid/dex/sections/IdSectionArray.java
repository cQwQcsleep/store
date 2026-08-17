package com.reandroid.dex.sections;

import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.IntegerPair;
import com.reandroid.dex.id.IdItem;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IdSectionArray<T extends IdItem> extends SectionArray<T> {
    public IdSectionArray(IntegerPair integerPair, Creator<T> creator) {
        super(integerPair, creator);
    }

    @Override // com.reandroid.dex.base.BlockListArray
    public void readChild(BlockReader blockReader, T t) throws IOException {
        t.onReadBytes(blockReader);
    }
}
