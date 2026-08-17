package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConstantMethodType extends Constant {
    private int descriptorIndex;

    public ConstantMethodType(ConstantMethodType constantMethodType) {
        this(constantMethodType.getDescriptorIndex());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitConstantMethodType(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getTag());
        dataOutputStream.writeShort(this.descriptorIndex);
    }

    public int getDescriptorIndex() {
        return this.descriptorIndex;
    }

    public void setDescriptorIndex(int i) {
        this.descriptorIndex = i;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public String toString() {
        return super.toString() + "(descriptorIndex = " + this.descriptorIndex + ")";
    }

    public ConstantMethodType(DataInput dataInput) throws IOException {
        this(dataInput.readUnsignedShort());
    }

    public ConstantMethodType(int i) {
        super((byte) 16);
        this.descriptorIndex = i;
    }
}
