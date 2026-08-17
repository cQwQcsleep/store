package com.reandroid.dex.ins;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Ins22 extends Size4Ins implements RegistersSet {
    public Ins22(Opcode<?> opcode) {
        super(opcode);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public abstract int getRegister(int i);

    @Override // com.reandroid.dex.ins.RegistersSet
    public abstract int getRegisterLimit(int i);

    @Override // com.reandroid.dex.ins.RegistersSet
    public final int getRegistersCount() {
        return 2;
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public abstract void setRegister(int i, int i2);

    @Override // com.reandroid.dex.ins.RegistersSet
    public final void setRegistersCount(int i) {
    }
}
