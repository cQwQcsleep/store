package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SourceFile_attribute extends Attribute {
    public final int sourcefile_index;

    public SourceFile_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        this.sourcefile_index = classReader.readUnsignedShort();
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, P> R accept(Attribute.Visitor<R, P> visitor, P p) {
        return visitor.visitSourceFile(this, p);
    }

    public String getSourceFile(ConstantPool constantPool) throws ConstantPoolException {
        return constantPool.getUTF8Value(this.sourcefile_index);
    }

    public SourceFile_attribute(ConstantPool constantPool, int i) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.SourceFile), i);
    }

    public SourceFile_attribute(int i, int i2) {
        super(i, 2);
        this.sourcefile_index = i2;
    }
}
