package com.reandroid.dex.ins;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InsConstWideHigh16 extends Ins21lh implements ConstNumberLong {
    public InsConstWideHigh16() {
        super(Opcode.CONST_WIDE_HIGH16);
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public long getLong() {
        return getDataAsLong();
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegister() {
        return getRegister(0);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public boolean isWideRegisterAt(int i) {
        return true;
    }

    @Override // com.reandroid.dex.ins.ConstNumberLong
    public void set(long j) {
        setLong(j);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegister(int i) {
        setRegister(0, i);
    }
}
