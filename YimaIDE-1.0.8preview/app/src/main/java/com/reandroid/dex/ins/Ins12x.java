package com.reandroid.dex.ins;

import com.reandroid.dex.smali.SmaliWriter;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Ins12x extends Size2Ins implements RegistersSet {
    public Ins12x(Opcode<?> opcode) {
        super(opcode);
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void appendOperand(SmaliWriter smaliWriter) {
    }

    @Override // com.reandroid.dex.ins.Size2Ins, com.reandroid.dex.ins.SizeXIns
    public int getData() {
        return 0;
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegister(int i) {
        return getNibble(i + 2);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegisterLimit(int i) {
        return 15;
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegistersCount() {
        return 2;
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void setData(int i) {
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegister(int i, int i2) {
        setNibble(i + 2, i2);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegistersCount(int i) {
    }
}
