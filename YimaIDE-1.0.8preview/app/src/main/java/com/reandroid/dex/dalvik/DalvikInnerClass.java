package com.reandroid.dex.dalvik;

import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.common.AnnotationVisibility;
import com.reandroid.dex.common.Modifier;
import com.reandroid.dex.key.AnnotationElementKey;
import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.NullValueKey;
import com.reandroid.dex.key.PrimitiveKey;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.AnnotatedProgram;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DalvikInnerClass extends DalvikAnnotation {
    private DalvikInnerClass(AnnotatedProgram annotatedProgram) {
        super(annotatedProgram, TypeKey.DALVIK_InnerClass);
    }

    public static DalvikInnerClass getOrCreate(AnnotatedProgram annotatedProgram) {
        TypeKey typeKey = TypeKey.DALVIK_InnerClass;
        if (!annotatedProgram.hasAnnotation(typeKey)) {
            annotatedProgram.addAnnotation(AnnotationItemKey.create(AnnotationVisibility.SYSTEM, typeKey, AnnotationElementKey.create(Key.DALVIK_accessFlags, PrimitiveKey.of(0)), AnnotationElementKey.create(Key.DALVIK_name, NullValueKey.INSTANCE)));
        }
        return of(annotatedProgram);
    }

    public static DalvikInnerClass of(AnnotatedProgram annotatedProgram) {
        if (annotatedProgram.hasAnnotation(TypeKey.DALVIK_InnerClass)) {
            return new DalvikInnerClass(annotatedProgram);
        }
        return null;
    }

    public Iterator<AccessFlag> getAccessFlags() {
        return AccessFlag.valuesOfClass(getAccessFlagsValue());
    }

    public int getAccessFlagsValue() {
        return (int) ((PrimitiveKey) readValue(Key.DALVIK_accessFlags)).getValueAsLong();
    }

    public String getName() {
        Key value = readValue(Key.DALVIK_name);
        if (value instanceof StringKey) {
            return ((StringKey) value).getString();
        }
        return null;
    }

    public boolean hasName() {
        return readValue(Key.DALVIK_name) instanceof StringKey;
    }

    public void setAccessFlags(int i) {
        writeValue(Key.DALVIK_accessFlags, PrimitiveKey.of(i));
    }

    public void setName(String str) {
        writeValue(Key.DALVIK_name, str == null ? NullValueKey.INSTANCE : StringKey.create(str));
    }

    @Override // com.reandroid.dex.dalvik.DalvikAnnotation
    public String toString() {
        return Modifier.toString(getAccessFlags()) + getName();
    }
}
