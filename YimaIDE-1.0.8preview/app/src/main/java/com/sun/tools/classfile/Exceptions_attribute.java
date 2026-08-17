package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Exceptions_attribute extends Attribute {
    public final int[] exception_index_table;
    public final int number_of_exceptions;

    public Exceptions_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        int unsignedShort = classReader.readUnsignedShort();
        this.number_of_exceptions = unsignedShort;
        this.exception_index_table = new int[unsignedShort];
        for (int i3 = 0; i3 < this.number_of_exceptions; i3++) {
            this.exception_index_table[i3] = classReader.readUnsignedShort();
        }
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitExceptions(this, d);
    }

    public String getException(int i, ConstantPool constantPool) throws ConstantPoolException {
        return constantPool.getClassInfo(this.exception_index_table[i]).getName();
    }

    public Exceptions_attribute(ConstantPool constantPool, int[] iArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.Exceptions), iArr);
    }

    public Exceptions_attribute(int i, int[] iArr) {
        super(i, (iArr.length * 2) + 2);
        this.number_of_exceptions = iArr.length;
        this.exception_index_table = iArr;
    }
}
