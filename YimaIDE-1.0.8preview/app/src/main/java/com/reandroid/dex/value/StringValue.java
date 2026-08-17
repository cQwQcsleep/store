package com.reandroid.dex.value;

import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.data.AnnotationItem;
import com.reandroid.dex.id.StringId;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.sections.SectionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringValue extends SectionIdValue<StringId> {
    public StringValue() {
        super(SectionType.STRING_ID, DexValueType.STRING);
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public TypeKey getDataTypeKey() {
        return TypeKey.STRING;
    }

    @Override // com.reandroid.dex.value.SectionIdValue, com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyItem
    public StringKey getKey() {
        return (StringKey) super.getKey();
    }

    public String getString() {
        StringKey key = getKey();
        if (key != null) {
            return key.getString();
        }
        return null;
    }

    @Override // com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock
    public DexValueType<?> getValueType() {
        return DexValueType.STRING;
    }

    public void setString(String str) {
        setKey(StringKey.create(str));
    }

    @Override // com.reandroid.dex.value.SectionIdValue
    public void updateUsageType(StringId stringId) {
        AnnotationItem annotationItem;
        super.updateUsageType(stringId);
        if (stringId == null || !stringId.containsUsage(UsageMarker.USAGE_ANNOTATION)) {
            return;
        }
        int i = UsageMarker.USAGE_SIGNATURE_TYPE;
        if (stringId.containsUsage(i) || (annotationItem = (AnnotationItem) getParentInstance(AnnotationItem.class)) == null || !TypeKey.DALVIK_Signature.equals(annotationItem.getType())) {
            return;
        }
        stringId.addUsageType(i);
    }
}
