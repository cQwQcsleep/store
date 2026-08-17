package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConstantFloat extends Constant implements ConstantObject {
    private float bytes;

    public ConstantFloat(ConstantFloat constantFloat) {
        this(constantFloat.getBytes());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitConstantFloat(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getTag());
        dataOutputStream.writeFloat(this.bytes);
    }

    public float getBytes() {
        return this.bytes;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ConstantObject
    public Object getConstantValue(ConstantPool constantPool) {
        return Float.valueOf(this.bytes);
    }

    public void setBytes(float f) {
        this.bytes = f;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public String toString() {
        return super.toString() + "(bytes = " + this.bytes + ")";
    }

    public ConstantFloat(DataInput dataInput) throws IOException {
        this(dataInput.readFloat());
    }

    public ConstantFloat(float f) {
        super((byte) 4);
        this.bytes = f;
    }
}
