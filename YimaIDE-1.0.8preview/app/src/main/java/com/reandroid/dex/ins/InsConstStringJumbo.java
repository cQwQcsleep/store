package com.reandroid.dex.ins;

import com.reandroid.dex.id.StringId;
import com.reandroid.dex.key.StringKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InsConstStringJumbo extends Ins31c implements ConstString {
    public InsConstStringJumbo() {
        super(Opcode.CONST_STRING_JUMBO);
    }

    private boolean needsConvertToConstString(StringId stringId) {
        int index = stringId.getIndex();
        return index == (65535 & index);
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public StringKey getKey() {
        return (StringKey) super.getKey();
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegister() {
        return getRegister(0);
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public StringId getSectionId() {
        return (StringId) super.getSectionId();
    }

    @Override // com.reandroid.dex.ins.ConstString
    public String getString() {
        StringId stringId = getStringId();
        if (stringId != null) {
            return stringId.getString();
        }
        return null;
    }

    public StringId getStringId() {
        return getSectionId();
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegister(int i) {
        setRegister(0, i);
    }

    @Override // com.reandroid.dex.ins.ConstString
    public void setString(StringKey stringKey) {
        super.setKey(stringKey);
    }

    public InsConstString toConstString() {
        StringId sectionId;
        if (getParent() == null || (sectionId = getSectionId()) == null || !needsConvertToConstString(sectionId)) {
            return null;
        }
        InsConstString insConstString = (InsConstString) replace(Opcode.CONST_STRING);
        insConstString.setRegister(getRegister());
        insConstString.setSectionId(sectionId);
        return insConstString;
    }
}
