package com.reandroid.dex.header;

import com.reandroid.dex.sections.DexLayoutBlock;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.Sha1OutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Signature extends HeaderPiece {
    public Signature() {
        super(20);
    }

    public String getHex() {
        return HexUtil.toHexString(getBytesInternal());
    }

    public int onWriteBytes(OutputStream outputStream) throws IOException {
        if (!(outputStream instanceof Sha1OutputStream)) {
            return super.onWriteBytes(outputStream);
        }
        ((Sha1OutputStream) outputStream).reset();
        return 0;
    }

    @Override // com.reandroid.dex.header.HeaderPiece, com.reandroid.arsc.item.ByteArray
    public String toString() {
        return getHex();
    }

    public void update() {
        DexLayoutBlock dexLayoutBlock = (DexLayoutBlock) getParentInstance(DexLayoutBlock.class);
        if (dexLayoutBlock == null) {
            return;
        }
        Sha1OutputStream sha1OutputStream = new Sha1OutputStream();
        try {
            dexLayoutBlock.writeBytes(sha1OutputStream);
            sha1OutputStream.digest(getBytesInternal(), 0);
        } catch (IOException e) {
            rc6.a(e);
        }
    }
}
