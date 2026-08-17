package com.reandroid.dex.reference;

import com.reandroid.dex.base.DexException;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.ins.Opcode;
import com.reandroid.dex.ins.SizeXIns;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.sections.SectionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InsIdSectionReference extends IdSectionReference<IdItem> {
    public InsIdSectionReference(SizeXIns sizeXIns) {
        super(sizeXIns, UsageMarker.USAGE_INSTRUCTION);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    private IdItem validateType(IdItem idItem) throws DexException {
        Key key = idItem.getKey();
        if (key instanceof TypeKey) {
            TypeKey typeKey = (TypeKey) key;
            if (getItem() != null && !typeKey.isTypeObject() && !getSectionTool().is(Opcode.CONST_CLASS)) {
                StringBuilder sb = new StringBuilder("Unexpected type '");
                sb.append(key);
                String strBuildTrace = buildTrace(idItem);
                sb.append("', ");
                sb.append(strBuildTrace);
                throw new DexException(sb.toString());
            }
        }
        return idItem;
    }

    @Override // com.reandroid.dex.reference.IdSectionReference
    public String buildTrace(IdItem idItem) {
        return SizeXIns.buildTrace(getSectionTool(), idItem, get());
    }

    @Override // com.reandroid.dex.reference.IdSectionReference
    public int get() {
        return getSectionTool().getData();
    }

    @Override // com.reandroid.dex.reference.IdSectionReference
    public SizeXIns getSectionTool() {
        return (SizeXIns) super.getSectionTool();
    }

    @Override // com.reandroid.dex.reference.IdSectionReference, com.reandroid.dex.reference.DexReference
    public SectionType<IdItem> getSectionType() {
        return getSectionTool().getSectionType();
    }

    @Override // com.reandroid.dex.reference.IdSectionReference
    public int onSetKey(int i) {
        if (i <= 65535 || !getSectionTool().is(Opcode.CONST_STRING)) {
            return i;
        }
        return 0;
    }

    @Override // com.reandroid.dex.reference.IdSectionReference
    public void set(int i) {
        getSectionTool().setData(i);
    }

    @Override // com.reandroid.dex.reference.IdSectionReference
    public IdItem validateReplace(IdItem idItem) {
        return validateType(super.validateReplace(idItem));
    }
}
