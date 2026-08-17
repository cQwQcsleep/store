package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConstantMethodref extends ConstantCP {
    public ConstantMethodref(ConstantMethodref constantMethodref) {
        super((byte) 10, constantMethodref.getClassIndex(), constantMethodref.getNameAndTypeIndex());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitConstantMethodref(this);
    }

    public ConstantMethodref(DataInput dataInput) throws IOException {
        super((byte) 10, dataInput);
    }

    public ConstantMethodref(int i, int i2) {
        super((byte) 10, i, i2);
    }
}
