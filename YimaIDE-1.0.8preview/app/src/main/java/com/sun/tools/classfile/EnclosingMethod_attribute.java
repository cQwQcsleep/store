package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EnclosingMethod_attribute extends Attribute {
    public final int class_index;
    public final int method_index;

    public EnclosingMethod_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        this.class_index = classReader.readUnsignedShort();
        this.method_index = classReader.readUnsignedShort();
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitEnclosingMethod(this, d);
    }

    public String getClassName(ConstantPool constantPool) throws ConstantPoolException {
        return constantPool.getClassInfo(this.class_index).getName();
    }

    public String getMethodName(ConstantPool constantPool) throws ConstantPoolException {
        int i = this.method_index;
        return i == 0 ? "" : constantPool.getNameAndTypeInfo(i).getName();
    }

    public EnclosingMethod_attribute(ConstantPool constantPool, int i, int i2) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.EnclosingMethod), i, i2);
    }

    public EnclosingMethod_attribute(int i, int i2, int i3) {
        super(i, 4);
        this.class_index = i2;
        this.method_index = i3;
    }
}
