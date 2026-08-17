package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.DexBlockItem;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.data.DataItem;
import com.reandroid.dex.data.DefIndex;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DirectoryEntry<DEFINITION extends DefIndex, VALUE extends DataItem> extends DexBlockItem implements BlockRefresh, Comparable<DirectoryEntry<?, ?>> {
    public static final int SIZE = 8;
    private DEFINITION mDefinition;
    private Key mDefinitionKey;
    private VALUE mValue;
    private final SectionType<VALUE> sectionType;

    public DirectoryEntry(SectionType<VALUE> sectionType) {
        super(8);
        this.sectionType = sectionType;
    }

    public static int compareDefIndex(DefIndex defIndex, DefIndex defIndex2) {
        if (defIndex == defIndex2) {
            return 0;
        }
        if (defIndex == null) {
            return 1;
        }
        if (defIndex2 == null) {
            return -1;
        }
        return CompareUtil.compare(defIndex.getDefinitionIndex(), defIndex2.getDefinitionIndex());
    }

    private void copyToIfPresent(VALUE value) {
        DataItem value2 = getValue();
        if (value2 != null) {
            value.copyFrom(value2);
        }
    }

    private VALUE createNewCopy() {
        VALUE value = (VALUE) createSectionItem(getSectionType());
        copyToIfPresent(value);
        setValue(value);
        return value;
    }

    private void pullItem() {
        this.mValue = (VALUE) getSectionItem(getSectionType(), getValueOffset());
        updateUsage();
    }

    private void refreshValue() {
        DataItem value = getValue();
        if (value != null) {
            value = (DataItem) value.getReplace();
        }
        setValue(value);
    }

    private void updateUsage() {
        DataItem value = getValue();
        if (value != null) {
            value.addUsageType(UsageMarker.USAGE_ANNOTATION);
            value.addUniqueUser(this);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(DirectoryEntry<?, ?> directoryEntry) {
        return compareDefIndex(getDefinition(), directoryEntry.getDefinition());
    }

    public void editInternal() {
        DataItem uniqueValue = getUniqueValue();
        if (uniqueValue != null) {
            uniqueValue.editInternal(this);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            DirectoryEntry directoryEntry = (DirectoryEntry) obj;
            if (ObjectsUtil.equals(getDefinitionKey(), directoryEntry.getDefinitionKey()) && ObjectsUtil.equals(getValue(), directoryEntry.getValue())) {
                return true;
            }
        }
        return false;
    }

    public boolean equalsDefIndex(DefIndex defIndex) {
        DefIndex definition;
        return (defIndex == null || (definition = getDefinition()) == null || definition != defIndex) ? false : true;
    }

    public boolean equalsValue(VALUE value) {
        return ObjectsUtil.equals(getValue(), value);
    }

    public DEFINITION getDefinition() {
        return this.mDefinition;
    }

    public int getDefinitionIndexValue() {
        return Block.getInteger(getBytesInternal(), 0);
    }

    public Key getDefinitionKey() {
        DefIndex definition = getDefinition();
        if (definition != null) {
            this.mDefinitionKey = definition.getKey();
        }
        return this.mDefinitionKey;
    }

    public VALUE getOrCreateUniqueValue() {
        VALUE value = (VALUE) getUniqueValue();
        return value == null ? (VALUE) getOrCreateValue() : value;
    }

    public VALUE getOrCreateValue() {
        VALUE value = (VALUE) getValue();
        if (value != null) {
            return value;
        }
        VALUE value2 = (VALUE) getOrCreateSection(getSectionType()).createItem();
        setValue(value2);
        return value2;
    }

    public SectionType<VALUE> getSectionType() {
        return this.sectionType;
    }

    public VALUE getUniqueValue() {
        refreshValue();
        VALUE value = (VALUE) getValue();
        return (value == null || !value.isSharedItem(this)) ? value : (VALUE) createNewCopy();
    }

    public VALUE getValue() {
        return this.mValue;
    }

    public Key getValueKey() {
        DataItem value = getValue();
        if (value != null) {
            return value.getKey();
        }
        return null;
    }

    public int getValueOffset() {
        return Block.getInteger(getBytesInternal(), 4);
    }

    public int hashCode() {
        return ObjectsUtil.hash(getDefinitionKey(), getValue());
    }

    public void link(DEFINITION definition) {
        if (definition == null || this.mDefinition != null) {
            return;
        }
        Key definitionKey = getDefinitionKey();
        if (definitionKey == null) {
            if (getDefinitionIndexValue() == definition.getDefinitionIndex()) {
                this.mDefinition = definition;
            }
        } else if (definitionKey.equals(definition.getKey())) {
            this.mDefinition = definition;
            setDefinitionIndexValue(definition.getDefinitionIndex());
        }
    }

    public boolean matchesDefinition(Key key) {
        return ObjectsUtil.equals(getDefinitionKey(), key);
    }

    public boolean matchesValue(Key key) {
        return ObjectsUtil.equals(getValueKey(), key);
    }

    public void merge(DirectoryEntry<DEFINITION, VALUE> directoryEntry) {
        if (directoryEntry == this) {
            return;
        }
        setDefinitionKey(directoryEntry.getDefinitionKey());
        setValue(directoryEntry.getValueKey());
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.onReadBytes(blockReader);
        pullItem();
    }

    public void refresh() {
        DefIndex definition = getDefinition();
        if (definition != null) {
            setDefinitionIndexValue(definition.getDefinitionIndex());
        }
        refreshValue();
    }

    public void set(DEFINITION definition, VALUE value) {
        setDefinition(definition);
        setValue(value);
    }

    public void setDefinition(DEFINITION definition) {
        this.mDefinition = definition;
        setDefinitionKey(definition != null ? definition.getKey() : null);
    }

    public void setDefinitionIndexValue(int i) {
        if (i == getDefinitionIndexValue()) {
            return;
        }
        Block.putInteger(getBytesInternal(), 0, i);
    }

    public void setDefinitionKey(Key key) {
        this.mDefinitionKey = key;
    }

    public void setValue(VALUE value) {
        this.mValue = value;
        setValueOffset(value != null ? value.getOffset() : 0);
        updateUsage();
    }

    public void setValueOffset(int i) {
        Block.putInteger(getBytesInternal(), 4, i);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        DefIndex definition = getDefinition();
        if (definition != null) {
            sb.append(definition.getKey());
        } else {
            sb.append(getDefinitionIndexValue());
        }
        sb.append(" (");
        DataItem value = getValue();
        if (value != null) {
            sb.append(value);
        } else {
            sb.append(getValueOffset());
        }
        sb.append(')');
        return sb.toString();
    }

    public boolean equalsDefIndex(int i) {
        return getDefinitionIndexValue() == i;
    }

    public void setValue(Key key) {
        setValue((DataItem) getOrCreateSectionItem(getSectionType(), key));
    }
}
