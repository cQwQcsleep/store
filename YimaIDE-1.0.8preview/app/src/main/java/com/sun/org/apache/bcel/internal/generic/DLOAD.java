package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DLOAD extends LoadInstruction {
    public DLOAD() {
        super((short) 24, (short) 38);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.LoadInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        super.accept(visitor);
        visitor.visitDLOAD(this);
    }

    public DLOAD(int i) {
        super((short) 24, (short) 38, i);
    }
}
