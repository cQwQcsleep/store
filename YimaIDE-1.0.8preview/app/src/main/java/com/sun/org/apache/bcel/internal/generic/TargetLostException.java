package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class TargetLostException extends Exception {
    private static final long serialVersionUID = -6857272667645328384L;
    private final InstructionHandle[] targets;

    public TargetLostException(InstructionHandle[] instructionHandleArr, String str) {
        super(str);
        this.targets = instructionHandleArr;
    }

    public InstructionHandle[] getTargets() {
        return this.targets;
    }
}
