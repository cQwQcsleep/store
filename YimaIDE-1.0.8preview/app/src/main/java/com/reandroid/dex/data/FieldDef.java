package com.reandroid.dex.data;

import com.reandroid.dex.base.DexException;
import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.common.Modifier;
import com.reandroid.dex.id.FieldId;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.AnnotationSetKey;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.NullValueKey;
import com.reandroid.dex.key.PrimitiveKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.FieldProgram;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.Smali;
import com.reandroid.dex.smali.model.SmaliField;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.SingleIterator;
import defpackage.jq6;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FieldDef extends Def<FieldId> implements FieldProgram {
    private Key cachedStaticValue;

    public FieldDef() {
        super(0, SectionType.FIELD_ID);
    }

    private void appendStaticValue(SmaliWriter smaliWriter) throws IOException {
        Key staticValue = getStaticValue();
        if (staticValue == null) {
            return;
        }
        if (isNonDefaultValue(staticValue) || !isInitializedInStaticConstructor()) {
            smaliWriter.append(" = ");
            staticValue.append(smaliWriter);
        }
    }

    private boolean isNonDefaultValue(Key key) {
        if (key instanceof PrimitiveKey) {
            return ((PrimitiveKey) key).getValueAsLong() != 0;
        }
        return !(key instanceof NullValueKey);
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        FieldKey key = getKey();
        if (key == null) {
            a16.a("Null FieldKey");
            return;
        }
        smaliWriter.newLine();
        getSmaliDirective().append(smaliWriter);
        smaliWriter.appendModifiers(getModifiers());
        key.appendDefinition(smaliWriter);
        appendStaticValue(smaliWriter);
        AnnotationSetKey annotation = getAnnotation();
        if (annotation.isEmpty()) {
            return;
        }
        smaliWriter.indentPlus();
        annotation.append(smaliWriter);
        smaliWriter.indentMinus();
        getSmaliDirective().appendEnd(smaliWriter);
    }

    public Key cachedStaticValue() {
        return this.cachedStaticValue;
    }

    @Override // com.reandroid.dex.data.Def
    public void fromSmali(Smali smali) {
        SmaliField smaliField = (SmaliField) smali;
        setKey(smaliField.getKey());
        setAccessFlagsValue(smaliField.getAccessFlagsValue());
        addHiddenApiFlags(smaliField.getHiddenApiFlags());
        if (smaliField.hasAnnotation()) {
            setAnnotation(smaliField.getAnnotationSetKey());
        }
        Key staticValue = smaliField.getStaticValue();
        if (staticValue != null) {
            setStaticValue(staticValue);
        }
    }

    @Override // com.reandroid.dex.data.Def, com.reandroid.dex.program.ProgramElement, com.reandroid.dex.data.DefIndex
    public FieldKey getKey() {
        FieldId id = getId();
        if (id != null) {
            return id.getKey();
        }
        return null;
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.FIELD;
    }

    @Override // com.reandroid.dex.program.FieldProgram
    public Key getStaticValue() {
        StaticFieldDefArray staticFieldDefArray = (StaticFieldDefArray) getParentInstance(StaticFieldDefArray.class);
        if (staticFieldDefArray != null) {
            return staticFieldDefArray.getStaticValue(this);
        }
        return null;
    }

    public boolean isInitializedInStaticConstructor() {
        StaticFieldDefArray staticFieldDefArray = (StaticFieldDefArray) getParentInstance(StaticFieldDefArray.class);
        if (staticFieldDefArray != null) {
            return staticFieldDefArray.isInitializedInStaticConstructor(this);
        }
        return false;
    }

    @Override // com.reandroid.dex.data.Def
    public void merge(Def<?> def) {
        super.merge(def);
        Key staticValue = ((FieldDef) def).getStaticValue();
        if (staticValue != null) {
            setStaticValue(staticValue);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    @Override // com.reandroid.dex.data.Def
    public void onRefreshed() throws DexException {
        super.onRefreshed();
        validateStaticValue();
    }

    public void setStaticValue(Key key) {
        StaticFieldDefArray staticFieldDefArray = (StaticFieldDefArray) getParentInstance(StaticFieldDefArray.class);
        if (staticFieldDefArray == null) {
            jq6.a("Not a member of StaticFieldDefArray: ", Modifier.toString(getModifiers()), getKey());
        } else {
            staticFieldDefArray.setStaticValue(this, key);
            validateStaticValue();
        }
    }

    @Override // com.reandroid.dex.data.Def
    public SmaliField toSmali() {
        SmaliField smaliField = new SmaliField();
        smaliField.setKey(getKey());
        smaliField.setAccessFlags(AccessFlag.valuesOfField(getAccessFlagsValue()));
        smaliField.setStaticValue(getStaticValue());
        smaliField.setAnnotation(getAnnotationKeys());
        return smaliField;
    }

    public String toString() {
        return SmaliWriter.toStringSafe(this);
    }

    @Override // com.reandroid.dex.data.Def, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return SingleIterator.of(getId());
    }

    @Override // com.reandroid.dex.common.IdUsageIterator
    public Iterator<Key> usedKeys() {
        return CombiningIterator.singleOne(getKey(), SingleIterator.of(getStaticValue()));
    }

    @Override // com.reandroid.dex.common.IdDefinition, com.reandroid.dex.common.IdUsageIterator
    public boolean uses(Key key) {
        return key.equals(getStaticValue());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public void validateStaticValue() throws DexException {
        Key staticValue = getStaticValue();
        if (staticValue == null) {
            return;
        }
        FieldKey key = getKey();
        if (!isStatic()) {
            StringBuilder sb = new StringBuilder("Instance field could not have initial value: ");
            sb.append(Modifier.toString(getAccessFlags()));
            sb.append(" ");
            sb.append(key);
            String stringSafe = SmaliWriter.toStringSafe(staticValue);
            sb.append(" = ");
            sb.append(stringSafe);
            throw new DexException(sb.toString());
        }
        TypeKey type = key.getType();
        boolean z = staticValue instanceof PrimitiveKey;
        if (type.isPrimitive() != z) {
            throw new DexException("Mismatch in type object vs primitive for value: " + SmaliWriter.toStringSafe(staticValue) + ", in field: " + key + "\n");
        }
        if (!z) {
            if (type.isPrimitive()) {
                StringBuilder sb2 = new StringBuilder("Mismatch in type: ");
                sb2.append(type);
                String stringSafe2 = SmaliWriter.toStringSafe(staticValue);
                sb2.append(" vs L , for value: ");
                sb2.append(stringSafe2);
                sb2.append(", in field: ");
                sb2.append(key);
                throw new DexException(sb2.toString());
            }
            return;
        }
        TypeKey typeKeyValueType = ((PrimitiveKey) staticValue).valueType();
        if (type.equals(typeKeyValueType)) {
            return;
        }
        StringBuilder sb3 = new StringBuilder("Mismatch in type: ");
        sb3.append(type);
        sb3.append(" vs ");
        sb3.append(typeKeyValueType);
        String stringSafe3 = SmaliWriter.toStringSafe(staticValue);
        sb3.append(", for value: ");
        sb3.append(stringSafe3);
        sb3.append(", in field: ");
        sb3.append(key);
        throw new DexException(sb3.toString());
    }

    public void cachedStaticValue(Key key) {
        this.cachedStaticValue = key;
    }
}
