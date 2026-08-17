package com.reandroid.dex.id;

import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.reference.IdItemIndirectReference;
import com.reandroid.dex.reference.IdItemIndirectShortReference;
import com.reandroid.dex.reference.IndirectStringReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.SingleIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FieldId extends IdItem implements Comparable<FieldId> {
    private final IdItemIndirectReference<TypeId> defining;
    private final IdItemIndirectReference<TypeId> fieldType;
    private final IndirectStringReference nameReference;

    public FieldId() {
        super(8);
        SectionType<TypeId> sectionType = SectionType.TYPE_ID;
        this.defining = new IdItemIndirectShortReference(sectionType, this, 0, UsageMarker.USAGE_FIELD_CLASS);
        this.fieldType = new IdItemIndirectShortReference(sectionType, this, 2, UsageMarker.USAGE_FIELD_TYPE);
        this.nameReference = new IndirectStringReference(this, 4, UsageMarker.USAGE_FIELD_NAME);
    }

    public static boolean equals(boolean z, FieldId fieldId, FieldId fieldId2) {
        if (fieldId == fieldId2) {
            return true;
        }
        if (fieldId != null && IndirectStringReference.equals(fieldId.getNameReference(), fieldId2.getNameReference())) {
            return z || TypeId.equals(fieldId.getDefiningId(), fieldId2.getDefiningId());
        }
        return false;
    }

    public void append(SmaliWriter smaliWriter, boolean z) throws IOException {
        if (z) {
            smaliWriter.appendRequired(getDefiningId());
            smaliWriter.append("->");
        }
        smaliWriter.append((CharSequence) getName());
        smaliWriter.append(':');
        smaliWriter.appendRequired(getFieldTypeId());
    }

    @Override // com.reandroid.dex.id.IdItem
    public void cacheItems() {
        this.defining.pullItem();
        this.fieldType.pullItem();
        this.nameReference.pullItem();
    }

    @Override // java.lang.Comparable
    public int compareTo(FieldId fieldId) {
        if (fieldId == null) {
            return -1;
        }
        int iCompare = CompareUtil.compare(getDefiningId(), fieldId.getDefiningId());
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompare2 = CompareUtil.compare(getNameReference(), fieldId.getNameReference());
        return iCompare2 != 0 ? iCompare2 : CompareUtil.compare(getFieldTypeId(), fieldId.getFieldTypeId());
    }

    public String getClassName() {
        TypeId definingId = getDefiningId();
        if (definingId != null) {
            return definingId.getName();
        }
        return null;
    }

    public TypeKey getDefining() {
        return (TypeKey) this.defining.getKey();
    }

    public TypeId getDefiningId() {
        return (TypeId) this.defining.getItem();
    }

    public TypeKey getFieldType() {
        return (TypeKey) this.fieldType.getKey();
    }

    public TypeId getFieldTypeId() {
        return (TypeId) this.fieldType.getItem();
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public FieldKey getKey() {
        return (FieldKey) checkKey(FieldKey.create(getDefining(), getNameKey(), getFieldType()));
    }

    public String getName() {
        return this.nameReference.getString();
    }

    public StringId getNameId() {
        return this.nameReference.getItem();
    }

    public StringKey getNameKey() {
        return this.nameReference.getKey();
    }

    public IndirectStringReference getNameReference() {
        return this.nameReference;
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.SectionItem
    public SectionType<FieldId> getSectionType() {
        return SectionType.FIELD_ID;
    }

    public void refresh() {
        this.defining.refresh();
        this.fieldType.refresh();
        this.nameReference.refresh();
    }

    public void setDefining(TypeKey typeKey) {
        this.defining.setKey(typeKey);
    }

    public void setFieldType(TypeKey typeKey) {
        this.fieldType.setKey(typeKey);
    }

    public void setKey(FieldKey fieldKey) {
        FieldKey key = getKey();
        if (ObjectsUtil.equals(fieldKey, key)) {
            return;
        }
        this.defining.setKey(fieldKey.getDeclaring());
        this.nameReference.setKey(fieldKey.getNameKey());
        this.fieldType.setKey(fieldKey.getType());
        keyChanged(key);
    }

    public void setName(StringKey stringKey) {
        this.nameReference.setKey(stringKey);
    }

    public String toString() {
        FieldKey key = getKey();
        if (key != null) {
            return key.toString();
        }
        return getDefiningId() + "->" + getNameId() + ":" + getFieldTypeId();
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return CombiningIterator.singleThree(this, SingleIterator.of((TypeId) this.defining.getItem()), SingleIterator.of(this.nameReference.getItem()), SingleIterator.of((TypeId) this.fieldType.getItem()));
    }

    public void setDefining(TypeId typeId) {
        this.defining.setItem(typeId);
    }

    public void setFieldType(TypeId typeId) {
        this.fieldType.setItem(typeId);
    }

    public void setName(String str) {
        this.nameReference.setString(str);
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        append(smaliWriter, true);
    }

    public static boolean equals(FieldId fieldId, FieldId fieldId2) {
        return equals(false, fieldId, fieldId2);
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        setKey((FieldKey) key);
    }
}
