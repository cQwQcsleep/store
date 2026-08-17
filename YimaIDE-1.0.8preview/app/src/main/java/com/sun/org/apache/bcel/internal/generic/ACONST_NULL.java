package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ACONST_NULL extends Instruction implements PushInstruction, TypedInstruction {
    public ACONST_NULL() {
        super((short) 1, (short) 1);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitStackProducer(this);
        visitor.visitPushInstruction(this);
        visitor.visitTypedInstruction(this);
        visitor.visitACONST_NULL(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        return Type.NULL;
    }
}
