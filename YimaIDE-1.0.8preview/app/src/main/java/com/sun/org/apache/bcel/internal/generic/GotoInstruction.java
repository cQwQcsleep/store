package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class GotoInstruction extends BranchInstruction implements UnconditionalBranch {
    public GotoInstruction() {
    }

    public GotoInstruction(short s, InstructionHandle instructionHandle) {
        super(s, instructionHandle);
    }
}
