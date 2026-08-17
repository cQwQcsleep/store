package com.reandroid.dex.ins;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Ins21s extends Size4Ins implements RegistersSet {
    public Ins21s(Opcode<?> opcode) {
        super(opcode);
    }

    @Override // com.reandroid.dex.ins.Size4Ins, com.reandroid.dex.ins.SizeXIns
    public int getData() {
        return getShortSigned();
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegister(int i) {
        return getByteUnsigned(1);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegisterLimit(int i) {
        return 255;
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegistersCount() {
        return 1;
    }

    @Override // com.reandroid.dex.ins.Size4Ins, com.reandroid.dex.ins.SizeXIns
    public void setData(int i) {
        setShort(2, i);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegister(int i, int i2) {
        setByte(1, i2);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegistersCount(int i) {
    }
}
