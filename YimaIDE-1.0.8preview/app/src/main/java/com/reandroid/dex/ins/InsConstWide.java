package com.reandroid.dex.ins;

import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InsConstWide extends Ins51l implements ConstNumberLong {
    public InsConstWide() {
        super(Opcode.CONST_WIDE);
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void appendHexData(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendHex(getLong());
    }

    @Override // com.reandroid.dex.ins.Ins51l, com.reandroid.dex.ins.SizeXIns
    public long getDataAsLong() {
        return getLong();
    }

    @Override // com.reandroid.dex.ins.Ins51l, com.reandroid.dex.ins.SizeXIns
    public long getLong() {
        return super.getLong();
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

    @Override // com.reandroid.dex.ins.Ins51l, com.reandroid.dex.ins.SizeXIns
    public void setData(long j) {
        set(j);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegister(int i) {
        setRegister(0, i);
    }
}
