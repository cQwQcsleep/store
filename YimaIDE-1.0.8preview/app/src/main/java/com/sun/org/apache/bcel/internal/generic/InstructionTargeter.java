package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface InstructionTargeter {
    boolean containsTarget(InstructionHandle instructionHandle);

    void updateTarget(InstructionHandle instructionHandle, InstructionHandle instructionHandle2) throws ClassGenException;
}
