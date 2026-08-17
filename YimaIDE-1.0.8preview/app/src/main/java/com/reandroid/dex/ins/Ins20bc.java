package com.reandroid.dex.ins;

import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.sections.SectionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Ins20bc extends Size4Ins {
    public Ins20bc(Opcode<?> opcode) {
        super(opcode, true);
    }

    @Override // com.reandroid.dex.ins.Size4Ins, com.reandroid.dex.ins.SizeXIns
    public int getData() {
        return getShortUnsigned(2);
    }

    public int getReferenceTypeValue() {
        return (getByteUnsigned(1) >> 6) & 3;
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public SectionType<? extends IdItem> getSectionType() {
        return SectionType.getReferenceType(getReferenceTypeValue());
    }

    public int getVerificationError() {
        return getByteUnsigned(1) & 63;
    }

    @Override // com.reandroid.dex.ins.Size4Ins, com.reandroid.dex.ins.SizeXIns
    public void setData(int i) {
        setShort(2, i);
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void setKey(Key key) {
        setSectionType(SectionType.getSectionType(key));
        super.setKey(key);
    }

    public void setReferenceTypeValue(int i) {
        setByte(1, ((i & 3) << 6) | (getByteUnsigned(1) & 63));
    }

    public void setSectionType(SectionType<?> sectionType) {
        setReferenceTypeValue(sectionType.getReferenceType());
    }

    public void setVerificationError(int i) {
        setByte(1, (i & 63) | (getByteUnsigned(1) & 192));
    }
}
