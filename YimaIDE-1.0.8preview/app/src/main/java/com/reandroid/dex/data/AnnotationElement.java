package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.common.SectionTool;
import com.reandroid.dex.data.AnnotationElement;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.id.StringId;
import com.reandroid.dex.key.AnnotationElementKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.ProtoKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.reference.StringUle128Reference;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliAnnotationElement;
import com.reandroid.dex.smali.model.SmaliValue;
import com.reandroid.dex.value.DexValueBlock;
import com.reandroid.dex.value.DexValueType;
import com.reandroid.dex.value.NullValue;
import com.reandroid.utils.collection.CombiningIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationElement extends DataItem implements KeyReference, Comparable<AnnotationElement>, SmaliFormat {
    public static final Creator<AnnotationElement> CREATOR = new Creator() { // from class: r80
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return new AnnotationElement();
        }
    };
    private final StringUle128Reference elementName;

    public AnnotationElement() {
        super(2);
        StringUle128Reference stringUle128Reference = new StringUle128Reference(UsageMarker.USAGE_METHOD_NAME);
        this.elementName = stringUle128Reference;
        addChildBlock(0, stringUle128Reference);
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append((CharSequence) getName());
        smaliWriter.append(" = ");
        getValueBlock().append(smaliWriter);
    }

    @Override // java.lang.Comparable
    public int compareTo(AnnotationElement annotationElement) {
        if (annotationElement == null) {
            return -1;
        }
        if (annotationElement == this) {
            return 0;
        }
        return SectionTool.compareIdx(getNameId(), annotationElement.getNameId());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AnnotationElement annotationElement = (AnnotationElement) obj;
        if (Objects.equals(getName(), annotationElement.getName())) {
            return Objects.equals(getValueBlock(), annotationElement.getValueBlock());
        }
        return false;
    }

    public void fromSmali(SmaliAnnotationElement smaliAnnotationElement) {
        setName(smaliAnnotationElement.getName());
        SmaliValue value = smaliAnnotationElement.getValue();
        getOrCreateValue(value.getValueType()).fromSmali(value);
    }

    public TypeKey getDataTypeKey() {
        DexValueBlock<?> valueBlock = getValueBlock();
        if (valueBlock != null) {
            return valueBlock.getDataTypeKey();
        }
        return null;
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public AnnotationElementKey getKey() {
        return AnnotationElementKey.create(getName(), getValue());
    }

    public MethodKey getMethodKey() {
        return MethodKey.create(getParentType(), getName(), ProtoKey.emptyParameters(getDataTypeKey()));
    }

    public String getName() {
        return this.elementName.getString();
    }

    public StringId getNameId() {
        return this.elementName.getItem();
    }

    public <T1 extends DexValueBlock<?>> T1 getOrCreateValue(DexValueType<T1> dexValueType) {
        NullValue nullValue = (T1) getValueBlock();
        if (nullValue != null && nullValue != NullValue.PLACE_HOLDER && nullValue.getValueType() == dexValueType) {
            return nullValue;
        }
        T1 t1 = (T1) dexValueType.newInstance();
        setValue(t1);
        return t1;
    }

    public TypeKey getParentType() {
        AnnotationItem annotationItem = (AnnotationItem) getParentInstance(AnnotationItem.class);
        if (annotationItem != null) {
            return annotationItem.getType();
        }
        return null;
    }

    public <T1 extends DexValueBlock<?>> T1 getValue(DexValueType<T1> dexValueType) {
        T1 t1 = (T1) getValueBlock();
        if (t1 == null || !t1.is(dexValueType)) {
            return null;
        }
        return t1;
    }

    public DexValueBlock<?> getValueBlock() {
        return getChildBlockAt(1);
    }

    public DexValueType<?> getValueType() {
        DexValueBlock<?> valueBlock = getValueBlock();
        if (valueBlock != null) {
            return valueBlock.getValueType();
        }
        return null;
    }

    public int hashCode() {
        String name = getName();
        int iHashCode = name != null ? name.hashCode() + 31 : 31;
        DexValueBlock<?> valueBlock = getValueBlock();
        int i = iHashCode * 31;
        return valueBlock != null ? i + valueBlock.hashCode() : i;
    }

    public boolean is(MethodKey methodKey) {
        return methodKey != null && methodKey.equalsIgnoreReturnType(getMethodKey());
    }

    public void merge(AnnotationElement annotationElement) {
        if (annotationElement == this) {
            return;
        }
        setName(annotationElement.getName());
        DexValueBlock<?> valueBlock = annotationElement.getValueBlock();
        getOrCreateValue(valueBlock.getValueType()).merge(valueBlock);
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public void onReadBytes(BlockReader blockReader) throws IOException {
        this.elementName.onReadBytes(blockReader);
        DexValueBlock<?> dexValueBlockCreate = DexValueType.create(blockReader);
        setValue(dexValueBlockCreate);
        dexValueBlockCreate.onReadBytes(blockReader);
    }

    public void replaceKeys(Key key, Key key2) {
        getValueBlock().replaceKeys(key, key2);
    }

    @Override // com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        AnnotationElementKey annotationElementKey = (AnnotationElementKey) key;
        setName(annotationElementKey.getName());
        setValue(annotationElementKey.getValue());
    }

    public void setName(String str) {
        this.elementName.setString(str);
    }

    public void setValue(Key key) {
        DexValueBlock<?> dexValueBlockM69newInstance = DexValueType.forKey(key).newInstance();
        setValue(dexValueBlockM69newInstance);
        dexValueBlockM69newInstance.setKey(key);
    }

    public String toString() {
        return getName() + " = " + getValueBlock();
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return CombiningIterator.singleOne(getNameId(), getValueBlock().usedIds());
    }

    @Override // com.reandroid.dex.common.IdUsageIterator
    public Iterator<Key> usedKeys() {
        return CombiningIterator.singleOne(getMethodKey(), super.usedKeys());
    }

    public Key getValue() {
        DexValueBlock<?> valueBlock = getValueBlock();
        if (valueBlock != null) {
            return valueBlock.getKey();
        }
        return null;
    }

    public void setValue(DexValueBlock<?> dexValueBlock) {
        addChildBlock(1, dexValueBlock);
    }

    public boolean is(DexValueType<?> dexValueType) {
        return getValueType() == dexValueType;
    }
}
