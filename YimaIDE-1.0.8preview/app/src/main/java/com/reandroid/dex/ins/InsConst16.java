package com.reandroid.dex.ins;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InsConst16 extends Ins21s implements RegistersSet, ConstNumber {
    public InsConst16() {
        super(Opcode.CONST_16);
    }

    public int get() {
        return getData();
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegister() {
        return getRegister(0);
    }

    public void set(int i) {
        setData(i);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegister(int i) {
        setRegister(0, i);
    }
}
