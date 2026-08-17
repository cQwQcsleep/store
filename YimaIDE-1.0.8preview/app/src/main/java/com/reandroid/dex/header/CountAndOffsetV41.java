package com.reandroid.dex.header;

import com.reandroid.arsc.io.BlockReader;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CountAndOffsetV41 extends CountAndOffset {
    public boolean isNull() {
        DexHeader dexHeader = (DexHeader) getParentInstance(DexHeader.class);
        return dexHeader == null || !dexHeader.isMultiLayoutVersion();
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        if (isNull()) {
            return;
        }
        super.onReadBytes(blockReader);
    }

    @Override // com.reandroid.arsc.base.DirectStreamReader
    public int readBytes(InputStream inputStream) throws IOException {
        if (isNull()) {
            return 0;
        }
        return super.readBytes(inputStream);
    }
}
