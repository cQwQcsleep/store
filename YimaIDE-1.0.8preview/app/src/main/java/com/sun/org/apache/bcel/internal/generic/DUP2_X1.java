package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DUP2_X1 extends StackInstruction {
    public DUP2_X1() {
        super((short) 93);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitStackInstruction(this);
        visitor.visitDUP2_X1(this);
    }
}
