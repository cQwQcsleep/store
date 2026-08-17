package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class POP2 extends StackInstruction implements PopInstruction {
    public POP2() {
        super((short) 88);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitStackConsumer(this);
        visitor.visitPopInstruction(this);
        visitor.visitStackInstruction(this);
        visitor.visitPOP2(this);
    }
}
