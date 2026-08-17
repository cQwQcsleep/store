package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Synthetic_attribute extends Attribute {
    public Synthetic_attribute(ConstantPool constantPool) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.Synthetic));
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitSynthetic(this, d);
    }

    public Synthetic_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
    }

    public Synthetic_attribute(int i) {
        super(i, 0);
    }
}
