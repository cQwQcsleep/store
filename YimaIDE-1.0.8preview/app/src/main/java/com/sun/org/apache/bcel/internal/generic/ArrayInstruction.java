package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.ExceptionConst;
import defpackage.c5c;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ArrayInstruction extends Instruction implements ExceptionThrower, TypedInstruction {
    public ArrayInstruction(short s) {
        super(s, (short) 1);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ExceptionThrower
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_ARRAY_EXCEPTION, new Class[0]);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0017  */
    /* JADX WARN: Code duplicated, block: B:13:0x001a  */
    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    /* JADX WARN: Code duplicated, block: B:17:0x0020  */
    /* JADX WARN: Code duplicated, block: B:19:0x0023  */
    /* JADX WARN: Code duplicated, block: B:21:0x0026  */
    /* JADX WARN: Code duplicated, block: B:7:0x0011  */
    /* JADX WARN: Code duplicated, block: B:9:0x0014  */
    @Override // com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        short opcode = super.getOpcode();
        switch (opcode) {
            case 46:
                return Type.INT;
            case 47:
                return Type.LONG;
            case 48:
                return Type.FLOAT;
            case 49:
                return Type.DOUBLE;
            case 50:
                return Type.OBJECT;
            case 51:
                return Type.BYTE;
            case 52:
                return Type.CHAR;
            case 53:
                return Type.SHORT;
            default:
                switch (opcode) {
                    case 79:
                        return Type.INT;
                    case 80:
                        return Type.LONG;
                    case 81:
                        return Type.FLOAT;
                    case 82:
                        return Type.DOUBLE;
                    case 83:
                        return Type.OBJECT;
                    case 84:
                        return Type.BYTE;
                    case 85:
                        return Type.CHAR;
                    case 86:
                        return Type.SHORT;
                    default:
                        c5c.a("Unknown case in switch", opcode);
                        return null;
                }
        }
    }

    public ArrayInstruction() {
    }
}
