package com.reandroid.dex.base;

import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.io.StreamUtil;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Ule128Item extends Le128 implements IntegerReference {
    private final boolean large;

    public Ule128Item(boolean z) {
        this.large = z;
    }

    @Override // com.reandroid.dex.base.Le128
    public int readLe128(BlockReader blockReader) throws IOException {
        int position = blockReader.getPosition();
        int uleb128 = DexBlockItem.readUleb128(StreamUtil.createByteReader((InputStream) blockReader), this.large ? 5 : 4);
        int position2 = blockReader.getPosition() - position;
        blockReader.seek(position);
        setBytesLength(position2, false);
        blockReader.readFully(getBytesInternal());
        return uleb128;
    }

    @Override // com.reandroid.dex.base.Le128
    public void writeValue(int i) {
        setBytesLength(this.large ? 5 : 4, false);
        setBytesLength(DexBlockItem.writeUleb128(getBytesInternal(), 0, i), false);
    }

    public Ule128Item() {
        this(false);
    }
}
