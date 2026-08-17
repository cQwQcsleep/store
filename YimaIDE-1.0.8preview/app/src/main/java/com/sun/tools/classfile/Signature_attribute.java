package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Signature_attribute extends Attribute {
    public final int signature_index;

    public Signature_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        this.signature_index = classReader.readUnsignedShort();
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitSignature(this, d);
    }

    public Signature getParsedSignature() {
        return new Signature(this.signature_index);
    }

    public String getSignature(ConstantPool constantPool) throws ConstantPoolException {
        return constantPool.getUTF8Value(this.signature_index);
    }

    public Signature_attribute(ConstantPool constantPool, int i) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.Signature), i);
    }

    public Signature_attribute(int i, int i2) {
        super(i, 2);
        this.signature_index = i2;
    }
}
