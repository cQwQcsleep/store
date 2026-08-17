package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationDefault_attribute extends Attribute {
    public final Annotation.element_value default_value;

    public AnnotationDefault_attribute(ClassReader classReader, int i, int i2) throws Annotation.InvalidAnnotation, IOException {
        super(i, i2);
        this.default_value = Annotation.element_value.read(classReader);
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitAnnotationDefault(this, d);
    }

    public AnnotationDefault_attribute(ConstantPool constantPool, Annotation.element_value element_valueVar) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.AnnotationDefault), element_valueVar);
    }

    public AnnotationDefault_attribute(int i, Annotation.element_value element_valueVar) {
        super(i, element_valueVar.length());
        this.default_value = element_valueVar;
    }
}
