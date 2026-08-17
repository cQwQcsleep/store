package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConstantClass extends Constant implements ConstantObject {
    private int nameIndex;

    public ConstantClass(ConstantClass constantClass) {
        this(constantClass.getNameIndex());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitConstantClass(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getTag());
        dataOutputStream.writeShort(this.nameIndex);
    }

    public String getBytes(ConstantPool constantPool) {
        return (String) getConstantValue(constantPool);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ConstantObject
    public Object getConstantValue(ConstantPool constantPool) {
        return constantPool.getConstantUtf8(this.nameIndex).getBytes();
    }

    public int getNameIndex() {
        return this.nameIndex;
    }

    public void setNameIndex(int i) {
        this.nameIndex = i;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public String toString() {
        return super.toString() + "(nameIndex = " + this.nameIndex + ")";
    }

    public ConstantClass(DataInput dataInput) throws IOException {
        this(dataInput.readUnsignedShort());
    }

    public ConstantClass(int i) {
        super((byte) 7);
        this.nameIndex = i;
    }
}
