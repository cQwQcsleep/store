package com.reandroid.dex.ins;

import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NullInstruction extends InsNop {
    @Override // com.reandroid.dex.ins.Ins10x, com.reandroid.dex.ins.SizeXIns, com.reandroid.dex.ins.Ins
    public void appendCode(SmaliWriter smaliWriter) throws IOException {
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public int countBytes() {
        return 0;
    }

    @Override // com.reandroid.dex.ins.Size2Ins, com.reandroid.dex.ins.Ins
    public int getCodeUnits() {
        return 0;
    }

    public boolean isNull() {
        return true;
    }

    public int onWriteBytes(OutputStream outputStream) throws IOException {
        return super/*com.reandroid.arsc.base.BlockContainer*/.onWriteBytes(outputStream);
    }
}
