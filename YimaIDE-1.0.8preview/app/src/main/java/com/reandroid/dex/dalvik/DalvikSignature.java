package com.reandroid.dex.dalvik;

import com.reandroid.dex.common.AnnotationVisibility;
import com.reandroid.dex.key.AnnotationElementKey;
import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.ArrayValueKey;
import com.reandroid.dex.key.DalvikSignatureKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.AnnotatedProgram;
import com.reandroid.utils.StringsUtil;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DalvikSignature extends DalvikAnnotation {
    private DalvikSignature(AnnotatedProgram annotatedProgram) {
        super(annotatedProgram, TypeKey.DALVIK_Signature);
    }

    public static DalvikSignature getOrCreate(AnnotatedProgram annotatedProgram) {
        TypeKey typeKey = TypeKey.DALVIK_Signature;
        if (!annotatedProgram.hasAnnotation(typeKey)) {
            annotatedProgram.addAnnotation(AnnotationItemKey.create(AnnotationVisibility.SYSTEM, typeKey, AnnotationElementKey.create(Key.DALVIK_value, ArrayValueKey.empty())));
        }
        return of(annotatedProgram);
    }

    public static DalvikSignature of(AnnotatedProgram annotatedProgram) {
        if (annotatedProgram.hasAnnotation(TypeKey.DALVIK_Signature)) {
            return new DalvikSignature(annotatedProgram);
        }
        return null;
    }

    public ArrayValueKey getArrayKey() {
        ArrayValueKey arrayValueKey = (ArrayValueKey) getKey().getValue(Key.DALVIK_value);
        return arrayValueKey == null ? ArrayValueKey.empty() : arrayValueKey;
    }

    public DalvikSignatureKey getSignature() {
        return DalvikSignatureKey.parse(getString());
    }

    public String getString() {
        return StringsUtil.join(values(), "");
    }

    public void replace(TypeKey typeKey, TypeKey typeKey2) {
        DalvikSignatureKey signature = getSignature();
        if (signature != null) {
            setSignature(signature.replaceKey((Key) typeKey, (Key) typeKey2));
        }
    }

    public void setArrayKey(ArrayValueKey arrayValueKey) {
        setKey(getKey().add(Key.DALVIK_value, arrayValueKey));
    }

    public void setSignature(DalvikSignatureKey dalvikSignatureKey) {
        if (dalvikSignatureKey != null) {
            setArrayKey(dalvikSignatureKey.toStringValues());
        }
    }

    @Override // com.reandroid.dex.dalvik.DalvikAnnotation
    public String toString() {
        return getString();
    }

    public Iterator<String> values() {
        return getArrayKey().stringValuesIterator();
    }
}
