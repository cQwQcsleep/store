package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConstantString extends Constant implements ConstantObject {
    private int stringIndex;

    public ConstantString(ConstantString constantString) {
        this(constantString.getStringIndex());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitConstantString(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getTag());
        dataOutputStream.writeShort(this.stringIndex);
    }

    public String getBytes(ConstantPool constantPool) {
        return (String) getConstantValue(constantPool);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ConstantObject
    public Object getConstantValue(ConstantPool constantPool) {
        return constantPool.getConstantUtf8(this.stringIndex).getBytes();
    }

    public int getStringIndex() {
        return this.stringIndex;
    }

    public void setStringIndex(int i) {
        this.stringIndex = i;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public String toString() {
        return super.toString() + "(stringIndex = " + this.stringIndex + ")";
    }

    public ConstantString(DataInput dataInput) throws IOException {
        this(dataInput.readUnsignedShort());
    }

    public ConstantString(int i) {
        super((byte) 8);
        this.stringIndex = i;
    }
}
