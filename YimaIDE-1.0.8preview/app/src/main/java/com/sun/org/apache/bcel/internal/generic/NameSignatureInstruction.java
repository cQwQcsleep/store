package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.ConstantCP;
import com.sun.org.apache.bcel.internal.classfile.ConstantNameAndType;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.classfile.ConstantUtf8;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class NameSignatureInstruction extends CPInstruction {
    public NameSignatureInstruction() {
    }

    public String getName(ConstantPoolGen constantPoolGen) {
        return ((ConstantUtf8) constantPoolGen.getConstantPool().getConstant(getNameAndType(constantPoolGen).getNameIndex())).getBytes();
    }

    public ConstantNameAndType getNameAndType(ConstantPoolGen constantPoolGen) {
        ConstantPool constantPool = constantPoolGen.getConstantPool();
        return (ConstantNameAndType) constantPool.getConstant(((ConstantCP) constantPool.getConstant(super.getIndex())).getNameAndTypeIndex());
    }

    public String getSignature(ConstantPoolGen constantPoolGen) {
        return ((ConstantUtf8) constantPoolGen.getConstantPool().getConstant(getNameAndType(constantPoolGen).getSignatureIndex())).getBytes();
    }

    public NameSignatureInstruction(short s, int i) {
        super(s, i);
    }
}
