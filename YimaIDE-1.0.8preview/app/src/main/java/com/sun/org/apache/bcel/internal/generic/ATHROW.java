package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.ExceptionConst;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ATHROW extends Instruction implements UnconditionalBranch, ExceptionThrower {
    public ATHROW() {
        super(Const.ATHROW, (short) 1);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitUnconditionalBranch(this);
        visitor.visitExceptionThrower(this);
        visitor.visitATHROW(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ExceptionThrower
    public Class<?>[] getExceptions() {
        return new Class[]{ExceptionConst.THROWABLE};
    }
}
