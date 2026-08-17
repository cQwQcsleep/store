package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IFNE extends IfInstruction {
    public IFNE(InstructionHandle instructionHandle) {
        super(Const.IFNE, instructionHandle);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitStackConsumer(this);
        visitor.visitBranchInstruction(this);
        visitor.visitIfInstruction(this);
        visitor.visitIFNE(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.IfInstruction
    public IfInstruction negate() {
        return new IFEQ(super.getTarget());
    }

    public IFNE() {
    }
}
