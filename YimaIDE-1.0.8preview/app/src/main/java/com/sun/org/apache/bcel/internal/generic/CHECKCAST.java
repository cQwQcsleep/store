package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.ExceptionConst;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CHECKCAST extends CPInstruction implements LoadClass, ExceptionThrower, StackProducer, StackConsumer {
    public CHECKCAST(int i) {
        super(Const.CHECKCAST, i);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitLoadClass(this);
        visitor.visitExceptionThrower(this);
        visitor.visitStackProducer(this);
        visitor.visitStackConsumer(this);
        visitor.visitTypedInstruction(this);
        visitor.visitCPInstruction(this);
        visitor.visitCHECKCAST(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ExceptionThrower
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.CLASS_CAST_EXCEPTION);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.LoadClass
    public ObjectType getLoadClassType(ConstantPoolGen constantPoolGen) {
        Type type = getType(constantPoolGen);
        if (type instanceof ArrayType) {
            type = ((ArrayType) type).getBasicType();
        }
        if (type instanceof ObjectType) {
            return (ObjectType) type;
        }
        return null;
    }

    public CHECKCAST() {
    }
}
