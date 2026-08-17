package com.reandroid.dex.dalvik;

import com.reandroid.dex.common.AnnotationVisibility;
import com.reandroid.dex.key.AnnotationElementKey;
import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.NullValueKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.AnnotatedProgram;
import com.reandroid.dex.program.ClassProgram;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DalvikEnclosingClass extends DalvikEnclosing<TypeKey> {
    private DalvikEnclosingClass(AnnotatedProgram annotatedProgram) {
        super(annotatedProgram, TypeKey.DALVIK_EnclosingClass);
    }

    public static DalvikEnclosingClass getOrCreate(AnnotatedProgram annotatedProgram) {
        TypeKey typeKey = TypeKey.DALVIK_EnclosingClass;
        if (!annotatedProgram.hasAnnotation(typeKey)) {
            annotatedProgram.addAnnotation(AnnotationItemKey.create(AnnotationVisibility.SYSTEM, typeKey, AnnotationElementKey.create(Key.DALVIK_value, annotatedProgram instanceof ClassProgram ? ((ClassProgram) annotatedProgram).getKey() : NullValueKey.INSTANCE)));
        }
        return of(annotatedProgram);
    }

    public static DalvikEnclosingClass of(AnnotatedProgram annotatedProgram) {
        if (annotatedProgram.hasAnnotation(TypeKey.DALVIK_EnclosingClass)) {
            return new DalvikEnclosingClass(annotatedProgram);
        }
        return null;
    }
}
