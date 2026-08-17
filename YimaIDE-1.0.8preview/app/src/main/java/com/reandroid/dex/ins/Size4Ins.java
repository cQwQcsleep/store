package com.reandroid.dex.ins;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Size4Ins extends SizeXIns {
    public Size4Ins(Opcode<?> opcode) {
        super(opcode);
    }

    @Override // com.reandroid.dex.ins.Ins
    public int getCodeUnits() {
        return 2;
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public int getData() {
        return getShortUnsigned(2);
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void setData(int i) {
        setShort(2, i);
    }

    public Size4Ins(Opcode<?> opcode, boolean z) {
        super(opcode, z);
    }
}
