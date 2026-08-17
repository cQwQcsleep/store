package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConstantInvokeDynamic extends ConstantCP {
    public ConstantInvokeDynamic(ConstantInvokeDynamic constantInvokeDynamic) {
        this(constantInvokeDynamic.getBootstrapMethodAttrIndex(), constantInvokeDynamic.getNameAndTypeIndex());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitConstantInvokeDynamic(this);
    }

    public int getBootstrapMethodAttrIndex() {
        return super.getClassIndex();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ConstantCP, com.sun.org.apache.bcel.internal.classfile.Constant
    public String toString() {
        return super.toString().replace("class_index", "bootstrap_method_attr_index");
    }

    public ConstantInvokeDynamic(DataInput dataInput) throws IOException {
        this(dataInput.readUnsignedShort(), dataInput.readUnsignedShort());
    }

    public ConstantInvokeDynamic(int i, int i2) {
        super((byte) 18, i, i2);
    }
}
