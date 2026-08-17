package com.reandroid.dex.ins;

import com.reandroid.dex.smali.SmaliWriter;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Ins23x extends Size4Ins implements RegistersSet {
    public Ins23x(Opcode<?> opcode) {
        super(opcode);
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void appendOperand(SmaliWriter smaliWriter) {
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegister(int i) {
        return getByteUnsigned(i + 1);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegisterLimit(int i) {
        return 255;
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegistersCount() {
        return 3;
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegister(int i, int i2) {
        setByte(i + 1, i2);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegistersCount(int i) {
    }
}
