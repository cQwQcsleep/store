package com.reandroid.dex.dalvik;

import com.reandroid.dex.common.AnnotationVisibility;
import com.reandroid.dex.key.AnnotationElementKey;
import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.NullValueKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.AnnotatedProgram;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DalvikEnclosingMethod extends DalvikEnclosing<MethodKey> {
    private DalvikEnclosingMethod(AnnotatedProgram annotatedProgram) {
        super(annotatedProgram, TypeKey.DALVIK_EnclosingMethod);
    }

    public static DalvikEnclosingMethod getOrCreate(AnnotatedProgram annotatedProgram) {
        TypeKey typeKey = TypeKey.DALVIK_EnclosingMethod;
        if (!annotatedProgram.hasAnnotation(typeKey)) {
            annotatedProgram.addAnnotation(AnnotationItemKey.create(AnnotationVisibility.SYSTEM, typeKey, AnnotationElementKey.create(Key.DALVIK_value, NullValueKey.INSTANCE)));
        }
        return of(annotatedProgram);
    }

    public static DalvikEnclosingMethod of(AnnotatedProgram annotatedProgram) {
        if (annotatedProgram.hasAnnotation(TypeKey.DALVIK_EnclosingMethod)) {
            return new DalvikEnclosingMethod(annotatedProgram);
        }
        return null;
    }
}
