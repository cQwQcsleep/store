package com.reandroid.dex.ins;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Ins51l extends Size10Ins implements RegistersSet {
    public Ins51l(Opcode<?> opcode) {
        super(opcode);
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public int getData() {
        return (int) getLong();
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public long getDataAsLong() {
        return getLong();
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public long getLong() {
        return super.getLong();
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

    @Override // com.reandroid.dex.ins.SizeXIns
    public void setData(int i) {
        setLong(i);
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void setLong(long j) {
        super.setLong(j);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegister(int i, int i2) {
        setByte(1, i2);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegistersCount(int i) {
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void setData(long j) {
        setLong(j);
    }
}
