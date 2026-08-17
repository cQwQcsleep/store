package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SourceID_attribute extends Attribute {
    public final int sourceID_index;

    public SourceID_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        this.sourceID_index = classReader.readUnsignedShort();
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitSourceID(this, d);
    }

    public String getSourceID(ConstantPool constantPool) throws ConstantPoolException {
        return constantPool.getUTF8Value(this.sourceID_index);
    }

    public SourceID_attribute(ConstantPool constantPool, int i) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.SourceID), i);
    }

    public SourceID_attribute(int i, int i2) {
        super(i, 2);
        this.sourceID_index = i2;
    }
}
