package com.reandroid.dex.dalvik;

import com.reandroid.dex.common.AnnotationVisibility;
import com.reandroid.dex.key.AnnotationElementKey;
import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.ArrayKey;
import com.reandroid.dex.key.ArrayValueKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.AnnotatedProgram;
import com.reandroid.dex.program.MethodProgram;
import com.reandroid.utils.StringsUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DalvikThrows extends DalvikAnnotation {
    private DalvikThrows(AnnotatedProgram annotatedProgram) {
        super(annotatedProgram, TypeKey.DALVIK_Throws);
    }

    public static DalvikThrows getOrCreate(AnnotatedProgram annotatedProgram) {
        TypeKey typeKey = TypeKey.DALVIK_Throws;
        if (!annotatedProgram.hasAnnotation(typeKey)) {
            annotatedProgram.addAnnotation(AnnotationItemKey.create(AnnotationVisibility.SYSTEM, typeKey, AnnotationElementKey.create(Key.DALVIK_value, ArrayValueKey.empty())));
        }
        return of(annotatedProgram);
    }

    public static DalvikThrows of(AnnotatedProgram annotatedProgram) {
        if (annotatedProgram.hasAnnotation(TypeKey.DALVIK_Throws)) {
            return new DalvikThrows(annotatedProgram);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void add(TypeKey typeKey) {
        ArrayKey<TypeKey> arrayKey = getThrows();
        if (arrayKey.contains(typeKey)) {
            return;
        }
        setThrows(arrayKey.add(typeKey));
    }

    public boolean contains(TypeKey typeKey) {
        return getThrows().contains(typeKey);
    }

    @Override // com.reandroid.dex.dalvik.DalvikAnnotation
    public MethodProgram getAnnotatedProgram() {
        return (MethodProgram) super.getAnnotatedProgram();
    }

    public ArrayKey<TypeKey> getThrows() {
        Key value = readValue(Key.DALVIK_value);
        return !(value instanceof ArrayKey) ? ArrayKey.empty() : (ArrayKey) value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean remove(TypeKey typeKey) {
        ArrayKey<TypeKey> arrayKey = getThrows();
        if (!arrayKey.contains(typeKey)) {
            return false;
        }
        setThrows(arrayKey.remove(typeKey));
        return true;
    }

    public void setThrows(ArrayKey<TypeKey> arrayKey) {
        writeValue(Key.DALVIK_value, ArrayValueKey.create(arrayKey));
    }

    @Override // com.reandroid.dex.dalvik.DalvikAnnotation
    public String toString() {
        return StringsUtil.join(getThrows().iterator(), ", ");
    }
}
