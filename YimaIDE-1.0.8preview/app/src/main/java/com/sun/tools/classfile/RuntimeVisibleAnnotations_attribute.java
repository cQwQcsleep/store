package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RuntimeVisibleAnnotations_attribute extends RuntimeAnnotations_attribute {
    public RuntimeVisibleAnnotations_attribute(ConstantPool constantPool, Annotation[] annotationArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.RuntimeVisibleAnnotations), annotationArr);
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, P> R accept(Attribute.Visitor<R, P> visitor, P p) {
        return visitor.visitRuntimeVisibleAnnotations(this, p);
    }

    public RuntimeVisibleAnnotations_attribute(ClassReader classReader, int i, int i2) throws Annotation.InvalidAnnotation, IOException {
        super(classReader, i, i2);
    }

    public RuntimeVisibleAnnotations_attribute(int i, Annotation[] annotationArr) {
        super(i, annotationArr);
    }
}
