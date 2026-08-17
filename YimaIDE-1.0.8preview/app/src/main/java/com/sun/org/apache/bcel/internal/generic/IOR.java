package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IOR extends ArithmeticInstruction {
    public IOR() {
        super((short) 128);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitTypedInstruction(this);
        visitor.visitStackProducer(this);
        visitor.visitStackConsumer(this);
        visitor.visitArithmeticInstruction(this);
        visitor.visitIOR(this);
    }
}
