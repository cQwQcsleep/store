package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.Instruction;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class OutlineableChunkStart extends MarkerInstruction {
    public static final Instruction OUTLINEABLECHUNKSTART = new OutlineableChunkStart();

    private OutlineableChunkStart() {
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public String getName() {
        return OutlineableChunkStart.class.getName();
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public String toString() {
        return getName();
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public String toString(boolean z) {
        return getName();
    }
}
