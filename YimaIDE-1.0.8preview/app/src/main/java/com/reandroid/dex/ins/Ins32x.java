package com.reandroid.dex.ins;

import com.reandroid.dex.smali.SmaliWriter;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Ins32x extends Size6Ins implements RegistersSet {
    public Ins32x(Opcode<?> opcode) {
        super(opcode);
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void appendOperand(SmaliWriter smaliWriter) {
    }

    @Override // com.reandroid.dex.ins.Size6Ins, com.reandroid.dex.ins.SizeXIns
    public int getData() {
        return 0;
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegister(int i) {
        return getShortUnsigned((i * 2) + 2);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegisterLimit(int i) {
        return 65535;
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegistersCount() {
        return 2;
    }

    @Override // com.reandroid.dex.ins.Size6Ins, com.reandroid.dex.ins.SizeXIns
    public void setData(int i) {
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegister(int i, int i2) {
        setShort((i * 2) + 2, i2);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public final void setRegistersCount(int i) {
    }
}
