package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FCMPL extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    public FCMPL() {
        super(Const.FCMPL, (short) 1);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitTypedInstruction(this);
        visitor.visitStackProducer(this);
        visitor.visitStackConsumer(this);
        visitor.visitFCMPL(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        return Type.FLOAT;
    }
}
