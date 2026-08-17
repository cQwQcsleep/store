package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationDefault extends Attribute {
    private ElementValue defaultValue;

    public AnnotationDefault(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, (ElementValue) null, constantPool);
        this.defaultValue = ElementValue.readElementValue(dataInput, constantPool);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitAnnotationDefault(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        return (Attribute) clone();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public final void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        this.defaultValue.dump(dataOutputStream);
    }

    public final ElementValue getDefaultValue() {
        return this.defaultValue;
    }

    public final void setDefaultValue(ElementValue elementValue) {
        this.defaultValue = elementValue;
    }

    public AnnotationDefault(int i, int i2, ElementValue elementValue, ConstantPool constantPool) {
        super((byte) 16, i, i2, constantPool);
        this.defaultValue = elementValue;
    }
}
