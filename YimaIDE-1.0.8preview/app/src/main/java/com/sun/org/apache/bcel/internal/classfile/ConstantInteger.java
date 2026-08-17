package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConstantInteger extends Constant implements ConstantObject {
    private int bytes;

    public ConstantInteger(ConstantInteger constantInteger) {
        this(constantInteger.getBytes());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitConstantInteger(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getTag());
        dataOutputStream.writeInt(this.bytes);
    }

    public int getBytes() {
        return this.bytes;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ConstantObject
    public Object getConstantValue(ConstantPool constantPool) {
        return Integer.valueOf(this.bytes);
    }

    public void setBytes(int i) {
        this.bytes = i;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public String toString() {
        return super.toString() + "(bytes = " + this.bytes + ")";
    }

    public ConstantInteger(DataInput dataInput) throws IOException {
        this(dataInput.readInt());
    }

    public ConstantInteger(int i) {
        super((byte) 3);
        this.bytes = i;
    }
}
