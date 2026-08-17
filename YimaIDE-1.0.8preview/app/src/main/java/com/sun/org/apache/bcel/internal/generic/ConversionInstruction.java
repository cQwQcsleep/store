package com.sun.org.apache.bcel.internal.generic;

import defpackage.c5c;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ConversionInstruction extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    public ConversionInstruction(short s) {
        super(s, (short) 1);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        short opcode = super.getOpcode();
        switch (opcode) {
            case 133:
            case 140:
            case 143:
                return Type.LONG;
            case 134:
            case 137:
            case 144:
                return Type.FLOAT;
            case 135:
            case 138:
            case 141:
                return Type.DOUBLE;
            case 136:
            case 139:
            case 142:
                return Type.INT;
            case 145:
                return Type.BYTE;
            case 146:
                return Type.CHAR;
            case 147:
                return Type.SHORT;
            default:
                c5c.a("Unknown type ", opcode);
                return null;
        }
    }

    public ConversionInstruction() {
    }
}
