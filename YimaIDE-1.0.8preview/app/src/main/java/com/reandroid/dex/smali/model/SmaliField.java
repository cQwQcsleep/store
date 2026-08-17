package com.reandroid.dex.smali.model;

import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.common.HiddenApiFlag;
import com.reandroid.dex.common.Modifier;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.FieldProgram;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliField extends SmaliDef implements FieldProgram {
    private TypeKey type;
    private SmaliValue value;

    private boolean isInitializedInStaticConstructor(SmaliClass smaliClass, FieldKey fieldKey) {
        SmaliMethod staticConstructor = smaliClass.getStaticConstructor();
        if (staticConstructor == null) {
            return false;
        }
        Iterator<SmaliInstruction> instructions = staticConstructor.getInstructions();
        while (instructions.hasNext()) {
            SmaliInstruction next = instructions.next();
            if (fieldKey.equals(next.getKey())) {
                return next.getOpcode().isFieldStaticPut();
            }
        }
        return false;
    }

    private void parseAnnotationSet(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        if (SmaliDirective.parse(smaliReader, false) != SmaliDirective.ANNOTATION) {
            getSmaliDirective().skipEnd(smaliReader);
            return;
        }
        int iPosition = smaliReader.position();
        SmaliAnnotationSet smaliAnnotationSet = new SmaliAnnotationSet();
        smaliAnnotationSet.parse(smaliReader);
        smaliReader.skipWhitespacesOrComment();
        if (!getSmaliDirective().isEnd(smaliReader)) {
            smaliReader.position(iPosition);
        } else {
            setSmaliAnnotationSet(smaliAnnotationSet);
            SmaliDirective.parse(smaliReader);
        }
    }

    private void parseValue(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespaces();
        if (!smaliReader.finished() && smaliReader.get() == 61) {
            smaliReader.skip(1);
            smaliReader.skipWhitespaces();
            SmaliValue smaliValueCreate = SmaliValueFactory.create(smaliReader);
            setStaticValue(smaliValueCreate);
            smaliValueCreate.parse(smaliReader);
        }
    }

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getSmaliDirective().append(smaliWriter);
        Modifier.append(smaliWriter, getModifiers());
        smaliWriter.append((CharSequence) getName());
        smaliWriter.append(':');
        getType().append(smaliWriter);
        SmaliValue value = getValue();
        if (value != null) {
            smaliWriter.append(" = ");
            value.append(smaliWriter);
        }
        SmaliAnnotationSet annotationSet = getAnnotationSet();
        if (annotationSet == null || annotationSet.isEmpty()) {
            return;
        }
        smaliWriter.indentPlus();
        smaliWriter.newLine();
        annotationSet.append(smaliWriter);
        smaliWriter.indentMinus();
        getSmaliDirective().appendEnd(smaliWriter);
    }

    public void fixUninitializedFinalValue() {
        if (getValue() == null && isStatic() && isFinal()) {
            SmaliClass smaliClass = getSmaliClass();
            FieldKey key = getKey();
            if (smaliClass == null || key == null || isInitializedInStaticConstructor(smaliClass, key)) {
                return;
            }
            setStaticValue(SmaliValueFactory.createForField(key.getType()));
        }
    }

    @Override // com.reandroid.dex.smali.model.SmaliDef, com.reandroid.dex.program.ProgramElement, com.reandroid.dex.data.DefIndex
    public FieldKey getKey() {
        TypeKey defining = getDefining();
        if (defining != null) {
            return getKey(defining);
        }
        return null;
    }

    @Override // com.reandroid.dex.smali.model.SmaliDef, com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.FIELD;
    }

    @Override // com.reandroid.dex.program.FieldProgram
    public Key getStaticValue() {
        SmaliValue value = getValue();
        if (value != null) {
            return value.getKey();
        }
        return null;
    }

    public TypeKey getType() {
        return this.type;
    }

    public SmaliValue getValue() {
        return this.value;
    }

    @Override // com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        SmaliParseException.expect(smaliReader, getSmaliDirective());
        setAccessFlags(AccessFlag.parse(smaliReader));
        setHiddenApiFlags(HiddenApiFlag.parse(smaliReader));
        setName(StringKey.readSimpleName(smaliReader, ':'));
        smaliReader.skip(1);
        setType(TypeKey.read(smaliReader));
        parseValue(smaliReader);
        parseAnnotationSet(smaliReader);
    }

    public void setKey(Key key) {
        FieldKey fieldKey = (FieldKey) key;
        setName(fieldKey.getNameKey());
        setType(fieldKey.getType());
        setDefining(fieldKey.getDeclaring());
    }

    public void setStaticValue(SmaliValue smaliValue) {
        SmaliValue smaliValue2 = this.value;
        this.value = smaliValue;
        if (smaliValue != null) {
            smaliValue.setParent(this);
        }
        if (smaliValue2 == null || smaliValue2 == smaliValue) {
            return;
        }
        smaliValue2.setParent(null);
    }

    public void setType(TypeKey typeKey) {
        this.type = typeKey;
    }

    @Override // com.reandroid.dex.smali.model.Smali
    public String toDebugString() {
        StringBuilder sb = new StringBuilder();
        TypeKey defining = getDefining();
        if (defining != null) {
            sb.append(defining);
            sb.append(", ");
        }
        sb.append("field = ");
        sb.append(getName());
        sb.append(':');
        sb.append(getType());
        SmaliValue value = getValue();
        if (value != null) {
            sb.append(" = ");
            sb.append(value.toDebugString());
        }
        return sb.toString();
    }

    public FieldKey getKey(TypeKey typeKey) {
        return FieldKey.create(typeKey, getName(), getType());
    }

    public void setStaticValue(Key key) {
        setStaticValue(SmaliValueFactory.createForValue(key));
    }
}
