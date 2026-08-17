package com.reandroid.dex.model;

import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.data.FieldDef;
import com.reandroid.dex.id.FieldId;
import com.reandroid.dex.ins.Opcode;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.PrimitiveKey;
import com.reandroid.dex.program.FieldProgram;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexField extends DexDeclaration implements FieldProgram {
    private final DexClass dexClass;
    private final FieldDef fieldDef;

    public DexField(DexClass dexClass, FieldDef fieldDef) {
        this.dexClass = dexClass;
        this.fieldDef = fieldDef;
    }

    private IntegerReference resolveValueFromStaticConstructor() {
        DexInstruction previousSetter;
        DexMethod staticConstructor = getDexClass().getStaticConstructor();
        if (staticConstructor == null) {
            return null;
        }
        Iterator<DexInstruction> instructions = staticConstructor.getInstructions();
        FieldKey key = getKey();
        while (instructions.hasNext()) {
            DexInstruction next = instructions.next();
            if (key.equals(next.getKeyAsField())) {
                if (next.is(Opcode.SPUT) && (previousSetter = next.getPreviousSetter(next.getRegister())) != null) {
                    return previousSetter.getAsIntegerReference();
                }
                return null;
            }
        }
        return null;
    }

    @Override // com.reandroid.dex.model.Dex, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getDefinition().append(smaliWriter);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return FieldId.equals(getId(), ((DexField) obj).getId());
    }

    @Override // com.reandroid.dex.model.DexDeclaration
    public DexClass getDexClass() {
        return this.dexClass;
    }

    @Override // com.reandroid.dex.model.DexDeclaration
    public FieldId getId() {
        return getDefinition().getId();
    }

    @Override // com.reandroid.dex.model.DexDeclaration, com.reandroid.dex.model.AnnotatedDex, com.reandroid.dex.program.ProgramElement, com.reandroid.dex.data.DefIndex
    public FieldKey getKey() {
        return getId().getKey();
    }

    public String getName() {
        return getId().getName();
    }

    public IntegerReference getStaticIntegerValue() {
        if (!isStatic()) {
            return null;
        }
        IntegerReference integerReferenceResolveValueFromStaticConstructor = resolveValueFromStaticConstructor();
        return integerReferenceResolveValueFromStaticConstructor == null ? getStaticValueIntegerReference() : integerReferenceResolveValueFromStaticConstructor;
    }

    @Override // com.reandroid.dex.program.FieldProgram
    public Key getStaticValue() {
        return getDefinition().getStaticValue();
    }

    public IntegerReference getStaticValueIntegerReference() {
        if (getStaticValue() instanceof PrimitiveKey.IntegerKey) {
            return new IntegerReference() { // from class: com.reandroid.dex.model.DexField.1
                public int get() {
                    Key staticValue = this.getStaticValue();
                    if (staticValue instanceof PrimitiveKey.IntegerKey) {
                        return ((PrimitiveKey.IntegerKey) staticValue).value();
                    }
                    return 0;
                }

                public void set(int i) {
                    this.setStaticValue(PrimitiveKey.of(i));
                }

                public String toString() {
                    return Integer.toString(get());
                }
            };
        }
        return null;
    }

    @Override // com.reandroid.dex.model.Dex
    public void removeSelf() {
        getDefinition().removeSelf();
    }

    public void setName(String str) {
        getId().setName(str);
    }

    public void setStaticValue(Key key) {
        getDefinition().setStaticValue(key);
    }

    @Override // com.reandroid.dex.model.DexDeclaration
    public FieldDef getDefinition() {
        return this.fieldDef;
    }
}
