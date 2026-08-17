package com.reandroid.dex.dexopt;

import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.StringBlock;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProfString extends StringBlock {
    private final IntegerReference lengthReference;

    public ProfString(IntegerReference integerReference) {
        this.lengthReference = integerReference;
    }

    public String decodeString(byte[] bArr) {
        return new String(bArr, 0, bArr.length, StandardCharsets.UTF_8);
    }

    public byte[] encodeString(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        setBytesLength(this.lengthReference.get(), false);
        super/*com.reandroid.arsc.item.BlockItem*/.onReadBytes(blockReader);
    }

    public void onStringChanged(String str, String str2) {
        super.onStringChanged(str, str2);
        this.lengthReference.set(countBytes());
    }
}
