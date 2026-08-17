package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConstantInterfaceMethodref extends ConstantCP {
    public ConstantInterfaceMethodref(ConstantInterfaceMethodref constantInterfaceMethodref) {
        super((byte) 11, constantInterfaceMethodref.getClassIndex(), constantInterfaceMethodref.getNameAndTypeIndex());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitConstantInterfaceMethodref(this);
    }

    public ConstantInterfaceMethodref(DataInput dataInput) throws IOException {
        super((byte) 11, dataInput);
    }

    public ConstantInterfaceMethodref(int i, int i2) {
        super((byte) 11, i, i2);
    }
}
