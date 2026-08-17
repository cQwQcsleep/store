package com.reandroid.dex.header;

import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.ByteArray;
import com.reandroid.utils.HexUtil;
import com.sun.org.apache.bcel.internal.classfile.ElementValue;
import defpackage.ds9;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Magic extends HeaderPiece {
    public static final byte[] DEFAULT_BYTES = {100, ElementValue.ENUM_CONSTANT, 120, 10};
    private boolean disableVerification;

    public Magic() {
        super.set((byte[]) DEFAULT_BYTES.clone());
    }

    public boolean isDisableVerification() {
        return this.disableVerification;
    }

    public boolean isValid() {
        return ByteArray.equals(getBytesInternal(), DEFAULT_BYTES);
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.onReadBytes(blockReader);
        if (isDisableVerification() || isValid()) {
            return;
        }
        ds9.a("Invalid dex magic: '", HexUtil.toHexString(getBytesInternal()), "', expecting '", HexUtil.toHexString(DEFAULT_BYTES), "'");
    }

    public void reset() {
        super.set((byte[]) DEFAULT_BYTES.clone());
    }

    public void setDisableVerification(boolean z) {
        this.disableVerification = z;
    }

    @Override // com.reandroid.dex.header.HeaderPiece, com.reandroid.arsc.item.ByteArray
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
