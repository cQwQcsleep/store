package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CompilationID_attribute extends Attribute {
    public final int compilationID_index;

    public CompilationID_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        this.compilationID_index = classReader.readUnsignedShort();
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitCompilationID(this, d);
    }

    public String getCompilationID(ConstantPool constantPool) throws ConstantPoolException {
        return constantPool.getUTF8Value(this.compilationID_index);
    }

    public CompilationID_attribute(ConstantPool constantPool, int i) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.CompilationID), i);
    }

    public CompilationID_attribute(int i, int i2) {
        super(i, 2);
        this.compilationID_index = i2;
    }
}
