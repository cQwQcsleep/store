package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConstantDouble extends Constant implements ConstantObject {
    private double bytes;

    public ConstantDouble(ConstantDouble constantDouble) {
        this(constantDouble.getBytes());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitConstantDouble(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getTag());
        dataOutputStream.writeDouble(this.bytes);
    }

    public double getBytes() {
        return this.bytes;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ConstantObject
    public Object getConstantValue(ConstantPool constantPool) {
        return Double.valueOf(this.bytes);
    }

    public void setBytes(double d) {
        this.bytes = d;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public String toString() {
        return super.toString() + "(bytes = " + this.bytes + ")";
    }

    public ConstantDouble(DataInput dataInput) throws IOException {
        this(dataInput.readDouble());
    }

    public ConstantDouble(double d) {
        super((byte) 6);
        this.bytes = d;
    }
}
