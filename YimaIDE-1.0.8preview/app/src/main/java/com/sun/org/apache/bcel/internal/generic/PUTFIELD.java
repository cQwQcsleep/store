package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.ExceptionConst;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PUTFIELD extends FieldInstruction implements PopInstruction, ExceptionThrower {
    public PUTFIELD(int i) {
        super(Const.PUTFIELD, i);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitExceptionThrower(this);
        visitor.visitStackConsumer(this);
        visitor.visitPopInstruction(this);
        visitor.visitTypedInstruction(this);
        visitor.visitLoadClass(this);
        visitor.visitCPInstruction(this);
        visitor.visitFieldOrMethod(this);
        visitor.visitFieldInstruction(this);
        visitor.visitPUTFIELD(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction, com.sun.org.apache.bcel.internal.generic.StackConsumer
    public int consumeStack(ConstantPoolGen constantPoolGen) {
        return getFieldSize(constantPoolGen) + 1;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ExceptionThrower
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.NULL_POINTER_EXCEPTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }

    public PUTFIELD() {
    }
}
