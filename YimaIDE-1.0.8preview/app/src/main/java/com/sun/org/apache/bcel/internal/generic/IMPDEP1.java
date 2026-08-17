package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IMPDEP1 extends Instruction {
    public IMPDEP1() {
        super(Const.IMPDEP1, (short) 1);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitIMPDEP1(this);
    }
}
