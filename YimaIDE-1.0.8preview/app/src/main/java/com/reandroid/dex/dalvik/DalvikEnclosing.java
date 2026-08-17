package com.reandroid.dex.dalvik;

import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.NullValueKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.AnnotatedProgram;
import com.reandroid.utils.ObjectsUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DalvikEnclosing<T extends Key> extends DalvikAnnotation {
    public DalvikEnclosing(AnnotatedProgram annotatedProgram, TypeKey typeKey) {
        super(annotatedProgram, typeKey);
    }

    public static DalvikEnclosing<?> of(AnnotatedProgram annotatedProgram) {
        DalvikEnclosingClass dalvikEnclosingClassOf = DalvikEnclosingClass.of(annotatedProgram);
        return dalvikEnclosingClassOf == null ? DalvikEnclosingMethod.of(annotatedProgram) : dalvikEnclosingClassOf;
    }

    public T getEnclosing() {
        Key value = readValue(Key.DALVIK_value);
        if (value == null || (value instanceof NullValueKey)) {
            return null;
        }
        return (T) ObjectsUtil.cast(value);
    }

    public TypeKey getEnclosingClass() {
        Key enclosing = getEnclosing();
        if (enclosing != null) {
            return enclosing.getDeclaring();
        }
        return null;
    }

    public void setEnclosing(T t) {
        if (t == null) {
            t = NullValueKey.INSTANCE;
        }
        writeValue(Key.DALVIK_value, t);
    }

    @Override // com.reandroid.dex.dalvik.DalvikAnnotation
    public String toString() {
        return String.valueOf(getEnclosing());
    }
}
