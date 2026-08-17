package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DUP2 extends StackInstruction implements PushInstruction {
    public DUP2() {
        super((short) 92);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitStackProducer(this);
        visitor.visitPushInstruction(this);
        visitor.visitStackInstruction(this);
        visitor.visitDUP2(this);
    }
}
