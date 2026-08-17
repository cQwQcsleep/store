package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class IfInstruction extends BranchInstruction implements StackConsumer {
    public IfInstruction() {
    }

    public abstract IfInstruction negate();

    public IfInstruction(short s, InstructionHandle instructionHandle) {
        super(s, instructionHandle);
    }
}
