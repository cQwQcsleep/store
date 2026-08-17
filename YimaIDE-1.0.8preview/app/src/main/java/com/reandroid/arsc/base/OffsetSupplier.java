package com.reandroid.arsc.base;

import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface OffsetSupplier {
    IntegerReference getOffsetReference();

    void readBytes(BlockReader blockReader) throws IOException;
}
