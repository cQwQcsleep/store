package com.reandroid.dex.header;

import com.reandroid.dex.sections.DexLayoutBlock;
import com.reandroid.utils.Alder32OutputStream;
import com.reandroid.utils.HexUtil;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexChecksum extends HeaderPiece {
    public DexChecksum() {
        super(4);
    }

    public int getValue() {
        return getInteger(0);
    }

    public int onWriteBytes(OutputStream outputStream) throws IOException {
        if (!(outputStream instanceof Alder32OutputStream)) {
            return super.onWriteBytes(outputStream);
        }
        ((Alder32OutputStream) outputStream).reset();
        return 0;
    }

    public void setValue(long j) {
        setSize(4);
        putInteger(0, (int) j);
    }

    @Override // com.reandroid.dex.header.HeaderPiece, com.reandroid.arsc.item.ByteArray
    public String toString() {
        return HexUtil.toHex8(getValue());
    }

    public boolean update() {
        DexLayoutBlock dexLayoutBlock = (DexLayoutBlock) getParentInstance(DexLayoutBlock.class);
        if (dexLayoutBlock == null) {
            return false;
        }
        int value = getValue();
        Alder32OutputStream alder32OutputStream = new Alder32OutputStream();
        try {
            dexLayoutBlock.writeBytes(alder32OutputStream);
            setValue(alder32OutputStream.getValue());
            return value != getValue();
        } catch (IOException e) {
            rc6.a(e);
            return false;
        }
    }
}
