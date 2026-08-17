package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DSTORE extends StoreInstruction {
    public DSTORE() {
        super((short) 57, (short) 71);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.StoreInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        super.accept(visitor);
        visitor.visitDSTORE(this);
    }

    public DSTORE(int i) {
        super((short) 57, (short) 71, i);
    }
}
