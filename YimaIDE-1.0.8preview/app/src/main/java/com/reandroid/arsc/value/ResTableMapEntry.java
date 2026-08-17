package com.reandroid.arsc.value;

import com.reandroid.arsc.array.CompoundItemArray;
import com.reandroid.arsc.array.ResValueMapArray;
import com.reandroid.arsc.item.TypeString;
import com.reandroid.arsc.refactor.ResourceMergeOption;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ResTableMapEntry extends CompoundEntry<ResValueMap, ResValueMapArray> {
    public ResTableMapEntry() {
        super(new ResValueMapArray());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.arsc.value.TableEntry
    public boolean canMerge(TableEntry<?, ?> tableEntry) {
        return tableEntry != this && (tableEntry instanceof ResTableMapEntry) && ((ResValueMapArray) ((ResTableMapEntry) tableEntry).getValue()).size() != 0 && ((ResValueMapArray) getValue()).size() == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ValueType isAllSameValueType() {
        Iterator it = ((ResValueMapArray) getValue()).iterator();
        ValueType valueType = null;
        while (it.hasNext()) {
            ValueType valueType2 = ((ResValueMap) it.next()).getValueType();
            if (valueType2 == null) {
                return null;
            }
            if (valueType2 != ValueType.REFERENCE) {
                if (valueType == null) {
                    valueType = valueType2;
                } else if (valueType2 != valueType) {
                    return null;
                }
            }
        }
        return valueType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean isArray() {
        int size = ((ResValueMapArray) getValue()).size();
        Iterator it = ((ResValueMapArray) getValue()).iterator();
        while (it.hasNext()) {
            int arrayIndex = ((ResValueMap) it.next()).getArrayIndex();
            if (arrayIndex < 0 || arrayIndex > size) {
                return false;
            }
        }
        if (size != 0) {
            return true;
        }
        Entry parentEntry = getParentEntry();
        if (parentEntry == null) {
            return false;
        }
        return TypeString.isTypeArray(parentEntry.getTypeName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean isAttr() {
        Iterator it = ((ResValueMapArray) getValue()).iterator();
        boolean z = false;
        while (it.hasNext()) {
            AttributeType attributeType = ((ResValueMap) it.next()).getAttributeType();
            if (attributeType != null && attributeType.isPlural()) {
                return false;
            }
            if (attributeType == AttributeType.FORMATS) {
                if (z) {
                    return false;
                }
                z = true;
            }
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean isPlural() {
        HashSet hashSet = new HashSet();
        Iterator it = ((ResValueMapArray) getValue()).iterator();
        while (it.hasNext()) {
            AttributeType attributeType = ((ResValueMap) it.next()).getAttributeType();
            if (attributeType == null || !attributeType.isPlural() || hashSet.contains(attributeType)) {
                return false;
            }
            hashSet.add(attributeType);
        }
        return hashSet.size() > 0;
    }

    public boolean isStyle() {
        if (getParentId() != 0) {
            return true;
        }
        Entry parentEntry = getParentEntry();
        if (parentEntry != null) {
            return TypeString.isTypeStyle(parentEntry.getTypeName());
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.arsc.value.TableEntry
    public void merge(TableEntry<?, ?> tableEntry) {
        if (tableEntry == null || tableEntry == this) {
            return;
        }
        ResTableMapEntry resTableMapEntry = (ResTableMapEntry) tableEntry;
        getHeader().merge(resTableMapEntry.getHeader());
        ((ResValueMapArray) getValue()).merge((CompoundItemArray) resTableMapEntry.getValue());
        refresh();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.arsc.value.TableEntry
    public void mergeWithName(ResourceMergeOption resourceMergeOption, TableEntry<?, ?> tableEntry) {
        if (tableEntry == null || tableEntry == this) {
            return;
        }
        ResTableMapEntry resTableMapEntry = (ResTableMapEntry) tableEntry;
        getHeader().mergeWithName(resourceMergeOption, resTableMapEntry.getHeader());
        ((ResValueMapArray) getValue()).mergeWithName(resourceMergeOption, (CompoundItemArray) resTableMapEntry.getValue());
        refresh();
    }
}
