package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RuntimeInvisibleAnnotations_attribute extends RuntimeAnnotations_attribute {
    public RuntimeInvisibleAnnotations_attribute(ConstantPool constantPool, Annotation[] annotationArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.RuntimeInvisibleAnnotations), annotationArr);
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, P> R accept(Attribute.Visitor<R, P> visitor, P p) {
        return visitor.visitRuntimeInvisibleAnnotations(this, p);
    }

    public RuntimeInvisibleAnnotations_attribute(ClassReader classReader, int i, int i2) throws AttributeException, IOException {
        super(classReader, i, i2);
    }

    public RuntimeInvisibleAnnotations_attribute(int i, Annotation[] annotationArr) {
        super(i, annotationArr);
    }
}
