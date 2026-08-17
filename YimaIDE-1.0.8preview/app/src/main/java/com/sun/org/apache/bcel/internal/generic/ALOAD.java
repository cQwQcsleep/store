package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ALOAD extends LoadInstruction {
    public ALOAD() {
        super((short) 25, (short) 42);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.LoadInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        super.accept(visitor);
        visitor.visitALOAD(this);
    }

    public ALOAD(int i) {
        super((short) 25, (short) 42, i);
    }
}
