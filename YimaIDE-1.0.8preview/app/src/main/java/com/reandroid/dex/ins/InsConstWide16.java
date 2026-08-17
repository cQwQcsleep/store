package com.reandroid.dex.ins;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InsConstWide16 extends Ins21s implements ConstNumberLong {
    private InsConstWide mReplaced;

    public InsConstWide16() {
        super(Opcode.CONST_WIDE_16);
    }

    private void replaceIns(long j) {
        InsConstWide insConstWide = (InsConstWide) replace(Opcode.CONST_WIDE);
        insConstWide.setRegister(getRegister());
        insConstWide.setData(j);
        this.mReplaced = insConstWide;
    }

    @Override // com.reandroid.dex.ins.Ins21s, com.reandroid.dex.ins.Size4Ins, com.reandroid.dex.ins.SizeXIns
    public int getData() {
        InsConstWide insConstWide = this.mReplaced;
        return insConstWide != null ? insConstWide.getData() : super.getData();
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public long getDataAsLong() {
        return getLong();
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public long getLong() {
        InsConstWide insConstWide = this.mReplaced;
        return insConstWide != null ? insConstWide.getLong() : getData();
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
        InsConstWide insConstWide = this.mReplaced;
        if (insConstWide != null) {
            insConstWide.set(j);
        } else if (j > 32767 || j < -32768) {
            replaceIns(j);
        } else {
            setData((int) j);
        }
    }

    @Override // com.reandroid.dex.ins.Ins21s, com.reandroid.dex.ins.Size4Ins, com.reandroid.dex.ins.SizeXIns
    public void setData(int i) {
        InsConstWide insConstWide = this.mReplaced;
        if (insConstWide != null) {
            insConstWide.set(i);
        } else if (i > 32767 || i < -32768) {
            replaceIns(i);
        } else {
            super.setData(i);
        }
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegister(int i) {
        setRegister(0, i);
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void setData(long j) {
        set(j);
    }
}
