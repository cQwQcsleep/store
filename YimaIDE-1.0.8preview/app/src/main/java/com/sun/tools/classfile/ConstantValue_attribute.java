package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ConstantValue_attribute extends Attribute {
    public final int constantvalue_index;

    public ConstantValue_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        this.constantvalue_index = classReader.readUnsignedShort();
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitConstantValue(this, d);
    }

    public ConstantValue_attribute(ConstantPool constantPool, int i) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.ConstantValue), i);
    }

    public ConstantValue_attribute(int i, int i2) {
        super(i, 2);
        this.constantvalue_index = i2;
    }
}
