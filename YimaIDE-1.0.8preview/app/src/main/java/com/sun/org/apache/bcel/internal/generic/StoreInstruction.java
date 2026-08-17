package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class StoreInstruction extends LocalVariableInstruction implements PopInstruction {
    public StoreInstruction(short s, short s2) {
        super(s, s2);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitStackConsumer(this);
        visitor.visitPopInstruction(this);
        visitor.visitTypedInstruction(this);
        visitor.visitLocalVariableInstruction(this);
        visitor.visitStoreInstruction(this);
    }

    public StoreInstruction(short s, short s2, int i) {
        super(s, s2, i);
    }
}
