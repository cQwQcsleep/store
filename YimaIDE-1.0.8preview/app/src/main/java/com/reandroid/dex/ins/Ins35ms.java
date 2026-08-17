package com.reandroid.dex.ins;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Ins35ms extends Size6Ins implements RegistersSet {
    public Ins35ms(Opcode<?> opcode) {
        super(opcode);
    }

    @Override // com.reandroid.dex.ins.Size6Ins, com.reandroid.dex.ins.SizeXIns
    public int getData() {
        return getShortUnsigned(2);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegister(int i) {
        return i < 4 ? getNibble(i + 8) : getNibble(2);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegisterLimit(int i) {
        return 15;
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegistersCount() {
        return getNibble(3);
    }

    @Override // com.reandroid.dex.ins.Size6Ins, com.reandroid.dex.ins.SizeXIns
    public void setData(int i) {
        setShort(2, i);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegister(int i, int i2) {
        if (i < 4) {
            setNibble(i + 8, i2);
        } else {
            setNibble(2, i2);
        }
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegistersCount(int i) {
        setNibble(3, i);
    }
}
