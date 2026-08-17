package com.reandroid.dex.ins;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Ins3rc extends Size6Ins implements RegistersSet {
    public Ins3rc(Opcode<?> opcode) {
        super(opcode);
    }

    @Override // com.reandroid.dex.ins.Size6Ins, com.reandroid.dex.ins.SizeXIns
    public int getData() {
        return getShortUnsigned(2);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegister(int i) {
        return getShortUnsigned(4) + i;
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegisterLimit(int i) {
        return 255;
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegistersCount() {
        return getByteUnsigned(1);
    }

    @Override // com.reandroid.dex.ins.Size6Ins, com.reandroid.dex.ins.SizeXIns
    public void setData(int i) {
        setShort(2, i);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegister(int i, int i2) {
        if (i != 0) {
            setByte(1, (i2 + 1) - getRegister());
        } else {
            setShort(4, i2);
        }
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegistersCount(int i) {
        setByte(1, i);
    }
}
