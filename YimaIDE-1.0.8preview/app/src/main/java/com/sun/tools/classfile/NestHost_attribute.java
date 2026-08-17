package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NestHost_attribute extends Attribute {
    public final int top_index;

    public NestHost_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        this.top_index = classReader.readUnsignedShort();
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitNestHost(this, d);
    }

    public ConstantPool.CONSTANT_Class_info getNestTop(ConstantPool constantPool) throws ConstantPoolException {
        return constantPool.getClassInfo(this.top_index);
    }

    public NestHost_attribute(ConstantPool constantPool, int i) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.Signature), i);
    }

    public NestHost_attribute(int i, int i2) {
        super(i, 2);
        this.top_index = i2;
    }
}
