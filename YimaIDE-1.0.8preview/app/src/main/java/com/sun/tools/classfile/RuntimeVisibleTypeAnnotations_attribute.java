package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RuntimeVisibleTypeAnnotations_attribute extends RuntimeTypeAnnotations_attribute {
    public RuntimeVisibleTypeAnnotations_attribute(ConstantPool constantPool, TypeAnnotation[] typeAnnotationArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.RuntimeVisibleTypeAnnotations), typeAnnotationArr);
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, P> R accept(Attribute.Visitor<R, P> visitor, P p) {
        return visitor.visitRuntimeVisibleTypeAnnotations(this, p);
    }

    public RuntimeVisibleTypeAnnotations_attribute(ClassReader classReader, int i, int i2) throws Annotation.InvalidAnnotation, IOException {
        super(classReader, i, i2);
    }

    public RuntimeVisibleTypeAnnotations_attribute(int i, TypeAnnotation[] typeAnnotationArr) {
        super(i, typeAnnotationArr);
    }
}
