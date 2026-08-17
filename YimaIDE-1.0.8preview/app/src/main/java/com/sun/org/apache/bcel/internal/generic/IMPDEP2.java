package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IMPDEP2 extends Instruction {
    public IMPDEP2() {
        super(Const.IMPDEP2, (short) 1);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitIMPDEP2(this);
    }
}
