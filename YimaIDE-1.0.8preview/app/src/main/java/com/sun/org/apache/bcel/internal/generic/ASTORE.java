package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ASTORE extends StoreInstruction {
    public ASTORE() {
        super((short) 58, (short) 75);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.StoreInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        super.accept(visitor);
        visitor.visitASTORE(this);
    }

    public ASTORE(int i) {
        super((short) 58, (short) 75, i);
    }
}
