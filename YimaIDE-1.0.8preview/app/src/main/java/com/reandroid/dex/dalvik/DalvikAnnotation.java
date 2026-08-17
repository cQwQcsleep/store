package com.reandroid.dex.dalvik;

import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.AnnotatedProgram;
import defpackage.qk5;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DalvikAnnotation {
    private final AnnotatedProgram annotatedProgram;
    private final TypeKey annotationType;

    public DalvikAnnotation(AnnotatedProgram annotatedProgram, TypeKey typeKey) {
        this.annotatedProgram = annotatedProgram;
        this.annotationType = typeKey;
    }

    public AnnotatedProgram getAnnotatedProgram() {
        return this.annotatedProgram;
    }

    public TypeKey getAnnotationType() {
        return this.annotationType;
    }

    public AnnotationItemKey getKey() {
        return getAnnotatedProgram().getAnnotation(getAnnotationType());
    }

    public Key readValue(String str) {
        return getKey().getValue(str);
    }

    public void setKey(AnnotationItemKey annotationItemKey) {
        if (getAnnotationType().equals(annotationItemKey.getType())) {
            getAnnotatedProgram().addAnnotation(annotationItemKey);
            return;
        }
        StringBuilder sb = new StringBuilder("Different annotation type: ");
        sb.append(getAnnotationType());
        qk5.a(sb, ", ", annotationItemKey.getType());
    }

    public String toString() {
        return getKey().toString();
    }

    public void writeValue(String str, Key key) {
        setKey(getKey().add(str, key));
    }
}
