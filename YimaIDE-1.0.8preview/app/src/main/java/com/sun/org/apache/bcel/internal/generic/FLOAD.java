package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FLOAD extends LoadInstruction {
    public FLOAD() {
        super((short) 23, (short) 34);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.LoadInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        super.accept(visitor);
        visitor.visitFLOAD(this);
    }

    public FLOAD(int i) {
        super((short) 23, (short) 34, i);
    }
}
