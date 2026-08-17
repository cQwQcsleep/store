package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConstantMethodHandle extends Constant {
    private int referenceIndex;
    private int referenceKind;

    public ConstantMethodHandle(ConstantMethodHandle constantMethodHandle) {
        this(constantMethodHandle.getReferenceKind(), constantMethodHandle.getReferenceIndex());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitConstantMethodHandle(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getTag());
        dataOutputStream.writeByte(this.referenceKind);
        dataOutputStream.writeShort(this.referenceIndex);
    }

    public int getReferenceIndex() {
        return this.referenceIndex;
    }

    public int getReferenceKind() {
        return this.referenceKind;
    }

    public void setReferenceIndex(int i) {
        this.referenceIndex = i;
    }

    public void setReferenceKind(int i) {
        this.referenceKind = i;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public String toString() {
        return super.toString() + "(referenceKind = " + this.referenceKind + ", referenceIndex = " + this.referenceIndex + ")";
    }

    public ConstantMethodHandle(DataInput dataInput) throws IOException {
        this(dataInput.readUnsignedByte(), dataInput.readUnsignedShort());
    }

    public ConstantMethodHandle(int i, int i2) {
        super((byte) 15);
        this.referenceKind = i;
        this.referenceIndex = i2;
    }
}
