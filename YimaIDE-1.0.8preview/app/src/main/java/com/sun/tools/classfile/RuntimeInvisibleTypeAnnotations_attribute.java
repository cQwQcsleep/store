package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RuntimeInvisibleTypeAnnotations_attribute extends RuntimeTypeAnnotations_attribute {
    public RuntimeInvisibleTypeAnnotations_attribute(ConstantPool constantPool, TypeAnnotation[] typeAnnotationArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.RuntimeInvisibleTypeAnnotations), typeAnnotationArr);
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, P> R accept(Attribute.Visitor<R, P> visitor, P p) {
        return visitor.visitRuntimeInvisibleTypeAnnotations(this, p);
    }

    public RuntimeInvisibleTypeAnnotations_attribute(ClassReader classReader, int i, int i2) throws Annotation.InvalidAnnotation, IOException {
        super(classReader, i, i2);
    }

    public RuntimeInvisibleTypeAnnotations_attribute(int i, TypeAnnotation[] typeAnnotationArr) {
        super(i, typeAnnotationArr);
    }
}
