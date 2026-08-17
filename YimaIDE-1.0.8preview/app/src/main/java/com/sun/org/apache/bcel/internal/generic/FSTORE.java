package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FSTORE extends StoreInstruction {
    public FSTORE() {
        super((short) 56, (short) 67);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.StoreInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        super.accept(visitor);
        visitor.visitFSTORE(this);
    }

    public FSTORE(int i) {
        super((short) 56, (short) 67, i);
    }
}
