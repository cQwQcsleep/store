package com.reandroid.dex.ins;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Size2Ins extends SizeXIns {
    public Size2Ins(Opcode<?> opcode) {
        super(opcode);
    }

    @Override // com.reandroid.dex.ins.Ins
    public int getCodeUnits() {
        return 1;
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public int getData() {
        return getNibble(2);
    }
}
