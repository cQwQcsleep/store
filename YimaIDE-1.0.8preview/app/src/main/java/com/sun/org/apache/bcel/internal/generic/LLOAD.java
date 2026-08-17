package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LLOAD extends LoadInstruction {
    public LLOAD() {
        super((short) 22, (short) 30);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.LoadInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        super.accept(visitor);
        visitor.visitLLOAD(this);
    }

    public LLOAD(int i) {
        super((short) 22, (short) 30, i);
    }
}
