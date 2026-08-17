package com.reandroid.dex.ins;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InsConstWide32 extends Ins31i implements ConstNumberLong {
    public InsConstWide32() {
        super(Opcode.CONST_WIDE_32);
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public long getDataAsLong() {
        return getLong();
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public long getLong() {
        return getInteger();
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
        setData(j);
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void setData(long j) {
        setInteger((int) j);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegister(int i) {
        setRegister(0, i);
    }
}
