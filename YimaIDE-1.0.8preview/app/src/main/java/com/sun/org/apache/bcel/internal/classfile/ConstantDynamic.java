package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConstantDynamic extends ConstantCP {
    public ConstantDynamic(ConstantDynamic constantDynamic) {
        this(constantDynamic.getBootstrapMethodAttrIndex(), constantDynamic.getNameAndTypeIndex());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitConstantDynamic(this);
    }

    public int getBootstrapMethodAttrIndex() {
        return super.getClassIndex();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ConstantCP, com.sun.org.apache.bcel.internal.classfile.Constant
    public String toString() {
        return super.toString().replace("class_index", "bootstrap_method_attr_index");
    }

    public ConstantDynamic(DataInput dataInput) throws IOException {
        this(dataInput.readShort(), dataInput.readShort());
    }

    public ConstantDynamic(int i, int i2) {
        super((byte) 17, i, i2);
    }
}
