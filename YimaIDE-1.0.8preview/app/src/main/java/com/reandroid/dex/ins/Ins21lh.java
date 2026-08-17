package com.reandroid.dex.ins;

import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Ins21lh extends Size4Ins implements RegistersSet {
    private InsConstWide mReplaced;

    public Ins21lh(Opcode<?> opcode) {
        super(opcode);
    }

    private void replaceIns(long j) {
        InsConstWide insConstWide = (InsConstWide) replace(Opcode.CONST_WIDE);
        insConstWide.setRegister(getRegister());
        insConstWide.setData(j);
        this.mReplaced = insConstWide;
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void appendHexData(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendHex(getDataAsLong());
    }

    @Override // com.reandroid.dex.ins.Size4Ins, com.reandroid.dex.ins.SizeXIns
    public int getData() {
        InsConstWide insConstWide = this.mReplaced;
        return insConstWide != null ? insConstWide.getData() : getShortUnsigned(2);
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public long getDataAsLong() {
        InsConstWide insConstWide = this.mReplaced;
        return insConstWide != null ? insConstWide.getLong() : ((long) getData()) << 48;
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

    @Override // com.reandroid.dex.ins.Size4Ins, com.reandroid.dex.ins.SizeXIns
    public void setData(int i) {
        if (((-65536) & i) != 0) {
            replaceIns(i);
        } else {
            setShort(2, i);
        }
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void setLong(long j) {
        InsConstWide insConstWide = this.mReplaced;
        if (insConstWide != null) {
            insConstWide.setData(j);
        }
        if ((281470681743360L & j) != 0) {
            replaceIns(j);
        } else {
            setData((int) (j >>> 48));
        }
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
