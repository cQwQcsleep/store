package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class FieldInstruction extends FieldOrMethod {
    public FieldInstruction() {
    }

    public String getFieldName(ConstantPoolGen constantPoolGen) {
        return getName(constantPoolGen);
    }

    public int getFieldSize(ConstantPoolGen constantPoolGen) {
        return Type.size(Type.getTypeSize(getSignature(constantPoolGen)));
    }

    public Type getFieldType(ConstantPoolGen constantPoolGen) {
        return Type.getType(getSignature(constantPoolGen));
    }

    @Override // com.sun.org.apache.bcel.internal.generic.CPInstruction, com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        return getFieldType(constantPoolGen);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.CPInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public String toString(ConstantPool constantPool) {
        return Const.getOpcodeName(super.getOpcode()) + " " + constantPool.constantToString(super.getIndex(), (byte) 9);
    }

    public FieldInstruction(short s, int i) {
        super(s, i);
    }
}
