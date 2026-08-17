package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.ExceptionConst;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class GETFIELD extends FieldInstruction implements ExceptionThrower, StackConsumer, StackProducer {
    public GETFIELD(int i) {
        super(Const.GETFIELD, i);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitExceptionThrower(this);
        visitor.visitStackConsumer(this);
        visitor.visitStackProducer(this);
        visitor.visitTypedInstruction(this);
        visitor.visitLoadClass(this);
        visitor.visitCPInstruction(this);
        visitor.visitFieldOrMethod(this);
        visitor.visitFieldInstruction(this);
        visitor.visitGETFIELD(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ExceptionThrower
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.NULL_POINTER_EXCEPTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction, com.sun.org.apache.bcel.internal.generic.StackProducer
    public int produceStack(ConstantPoolGen constantPoolGen) {
        return getFieldSize(constantPoolGen);
    }

    public GETFIELD() {
    }
}
