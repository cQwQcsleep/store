package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.ExceptionConst;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ARRAYLENGTH extends Instruction implements ExceptionThrower, StackProducer, StackConsumer {
    public ARRAYLENGTH() {
        super(Const.ARRAYLENGTH, (short) 1);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitExceptionThrower(this);
        visitor.visitStackProducer(this);
        visitor.visitARRAYLENGTH(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ExceptionThrower
    public Class<?>[] getExceptions() {
        return new Class[]{ExceptionConst.NULL_POINTER_EXCEPTION};
    }
}
