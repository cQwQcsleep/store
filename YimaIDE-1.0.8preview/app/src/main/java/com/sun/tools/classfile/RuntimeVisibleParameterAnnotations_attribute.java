package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RuntimeVisibleParameterAnnotations_attribute extends RuntimeParameterAnnotations_attribute {
    public RuntimeVisibleParameterAnnotations_attribute(ConstantPool constantPool, Annotation[][] annotationArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.RuntimeVisibleParameterAnnotations), annotationArr);
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, P> R accept(Attribute.Visitor<R, P> visitor, P p) {
        return visitor.visitRuntimeVisibleParameterAnnotations(this, p);
    }

    public RuntimeVisibleParameterAnnotations_attribute(ClassReader classReader, int i, int i2) throws Annotation.InvalidAnnotation, IOException {
        super(classReader, i, i2);
    }

    public RuntimeVisibleParameterAnnotations_attribute(int i, Annotation[][] annotationArr) {
        super(i, annotationArr);
    }
}
