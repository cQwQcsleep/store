package com.reandroid.dex.debug;

import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.Smali;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DebugRestartLocal extends DebugRegisterNumber {
    public DebugRestartLocal() {
        super(0, DebugElementType.RESTART_LOCAL);
    }

    @Override // com.reandroid.dex.debug.DebugRegisterNumber, com.reandroid.dex.debug.DebugElement, com.reandroid.dex.ins.ExtraLine
    public /* bridge */ /* synthetic */ void appendExtra(SmaliWriter smaliWriter) throws IOException {
        super.appendExtra(smaliWriter);
    }

    @Override // com.reandroid.dex.debug.DebugRegisterNumber, com.reandroid.dex.debug.DebugElement
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.reandroid.dex.debug.DebugRegisterNumber, com.reandroid.dex.debug.DebugElement
    public /* bridge */ /* synthetic */ void fromSmali(Smali smali) {
        super.fromSmali(smali);
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public DebugElementType<DebugRestartLocal> getElementType() {
        return DebugElementType.RESTART_LOCAL;
    }

    @Override // com.reandroid.dex.debug.DebugRegisterNumber
    public /* bridge */ /* synthetic */ int getRegisterNumber() {
        return super.getRegisterNumber();
    }

    @Override // com.reandroid.dex.debug.DebugRegisterNumber, com.reandroid.dex.debug.DebugElement
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.reandroid.dex.debug.DebugRegisterNumber, com.reandroid.dex.debug.DebugElement
    public /* bridge */ /* synthetic */ void merge(DebugElement debugElement) {
        super.merge(debugElement);
    }

    @Override // com.reandroid.dex.debug.DebugRegisterNumber
    public /* bridge */ /* synthetic */ void setRegister(int i) {
        super.setRegister(i);
    }

    @Override // com.reandroid.dex.debug.DebugRegisterNumber, com.reandroid.dex.debug.DebugElement
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
