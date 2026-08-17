package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SWAP extends StackInstruction implements StackConsumer, StackProducer {
    public SWAP() {
        super((short) 95);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitStackConsumer(this);
        visitor.visitStackProducer(this);
        visitor.visitStackInstruction(this);
        visitor.visitSWAP(this);
    }
}
