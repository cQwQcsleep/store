package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class StackInstruction extends Instruction {
    public StackInstruction(short s) {
        super(s, (short) 1);
    }

    public Type getType(ConstantPoolGen constantPoolGen) {
        return Type.UNKNOWN;
    }

    public StackInstruction() {
    }
}
