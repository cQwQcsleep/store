package com.reandroid.dex.ins;

import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.HexUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Ins21t extends Size4Ins implements RegistersSet, Label {
    public Ins21t(Opcode<?> opcode) {
        super(opcode);
    }

    @Override // com.reandroid.dex.ins.SizeXIns, com.reandroid.dex.ins.Ins
    public void appendCode(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append((CharSequence) getOpcode().getName());
        smaliWriter.append(' ');
        getRegistersIterator().append(smaliWriter);
        smaliWriter.append(", ");
        smaliWriter.appendLabelName(getLabelName());
    }

    @Override // com.reandroid.dex.ins.Size4Ins, com.reandroid.dex.ins.SizeXIns
    public int getData() {
        return getShortSigned();
    }

    @Override // com.reandroid.dex.ins.Label
    public String getLabelName() {
        return HexUtil.toHex(":cond_", getTargetAddress(), 1);
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

    @Override // com.reandroid.dex.ins.ExtraLine
    public int getSortOrder() {
        return 5;
    }

    @Override // com.reandroid.dex.ins.ExtraLine
    public int getTargetAddress() {
        return getAddress() + getData();
    }

    @Override // com.reandroid.dex.ins.Size4Ins, com.reandroid.dex.ins.SizeXIns
    public void setData(int i) {
        setShort(2, i);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegister(int i, int i2) {
        setByte(1, i2);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegistersCount(int i) {
    }

    @Override // com.reandroid.dex.ins.ExtraLine
    public void setTargetAddress(int i) {
        setShort(2, i - getAddress());
    }
}
