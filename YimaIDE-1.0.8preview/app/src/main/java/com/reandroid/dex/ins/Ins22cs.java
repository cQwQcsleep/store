package com.reandroid.dex.ins;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Ins22cs extends Ins22 {
    public Ins22cs(Opcode<?> opcode) {
        super(opcode);
    }

    @Override // com.reandroid.dex.ins.Size4Ins, com.reandroid.dex.ins.SizeXIns
    public int getData() {
        return getShortUnsigned(2);
    }

    @Override // com.reandroid.dex.ins.Ins22, com.reandroid.dex.ins.RegistersSet
    public int getRegister(int i) {
        return getNibble(i + 2);
    }

    @Override // com.reandroid.dex.ins.Ins22, com.reandroid.dex.ins.RegistersSet
    public int getRegisterLimit(int i) {
        return 15;
    }

    @Override // com.reandroid.dex.ins.Size4Ins, com.reandroid.dex.ins.SizeXIns
    public void setData(int i) {
        setShort(2, i);
    }

    @Override // com.reandroid.dex.ins.Ins22, com.reandroid.dex.ins.RegistersSet
    public void setRegister(int i, int i2) {
        setNibble(i + 2, i2);
    }
}
