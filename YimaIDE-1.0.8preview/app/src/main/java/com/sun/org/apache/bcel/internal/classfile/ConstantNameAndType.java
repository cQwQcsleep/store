package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConstantNameAndType extends Constant {
    private int nameIndex;
    private int signatureIndex;

    public ConstantNameAndType(ConstantNameAndType constantNameAndType) {
        this(constantNameAndType.getNameIndex(), constantNameAndType.getSignatureIndex());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitConstantNameAndType(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getTag());
        dataOutputStream.writeShort(this.nameIndex);
        dataOutputStream.writeShort(this.signatureIndex);
    }

    public String getName(ConstantPool constantPool) {
        return constantPool.constantToString(getNameIndex(), (byte) 1);
    }

    public int getNameIndex() {
        return this.nameIndex;
    }

    public String getSignature(ConstantPool constantPool) {
        return constantPool.constantToString(getSignatureIndex(), (byte) 1);
    }

    public int getSignatureIndex() {
        return this.signatureIndex;
    }

    public void setNameIndex(int i) {
        this.nameIndex = i;
    }

    public void setSignatureIndex(int i) {
        this.signatureIndex = i;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public String toString() {
        return super.toString() + "(nameIndex = " + this.nameIndex + ", signatureIndex = " + this.signatureIndex + ")";
    }

    public ConstantNameAndType(DataInput dataInput) throws IOException {
        this(dataInput.readUnsignedShort(), dataInput.readUnsignedShort());
    }

    public ConstantNameAndType(int i, int i2) {
        super((byte) 12);
        this.nameIndex = i;
        this.signatureIndex = i2;
    }
}
