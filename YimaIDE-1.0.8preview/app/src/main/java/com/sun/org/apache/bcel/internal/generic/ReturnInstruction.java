package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.ExceptionConst;
import defpackage.c5c;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ReturnInstruction extends Instruction implements ExceptionThrower, TypedInstruction, StackConsumer {
    public ReturnInstruction(short s) {
        super(s, (short) 1);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ExceptionThrower
    public Class<?>[] getExceptions() {
        return new Class[]{ExceptionConst.ILLEGAL_MONITOR_STATE};
    }

    public Type getType() {
        short opcode = super.getOpcode();
        switch (opcode) {
            case 172:
                return Type.INT;
            case 173:
                return Type.LONG;
            case 174:
                return Type.FLOAT;
            case 175:
                return Type.DOUBLE;
            case 176:
                return Type.OBJECT;
            case 177:
                return Type.VOID;
            default:
                c5c.a("Unknown type ", opcode);
                return null;
        }
    }

    public ReturnInstruction() {
    }

    @Override // com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        return getType();
    }
}
