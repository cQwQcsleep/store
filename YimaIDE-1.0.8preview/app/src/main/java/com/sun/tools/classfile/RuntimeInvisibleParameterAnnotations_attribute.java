package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RuntimeInvisibleParameterAnnotations_attribute extends RuntimeParameterAnnotations_attribute {
    public RuntimeInvisibleParameterAnnotations_attribute(ConstantPool constantPool, Annotation[][] annotationArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.RuntimeInvisibleParameterAnnotations), annotationArr);
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, P> R accept(Attribute.Visitor<R, P> visitor, P p) {
        return visitor.visitRuntimeInvisibleParameterAnnotations(this, p);
    }

    public RuntimeInvisibleParameterAnnotations_attribute(ClassReader classReader, int i, int i2) throws Annotation.InvalidAnnotation, IOException {
        super(classReader, i, i2);
    }

    public RuntimeInvisibleParameterAnnotations_attribute(int i, Annotation[][] annotationArr) {
        super(i, annotationArr);
    }
}
