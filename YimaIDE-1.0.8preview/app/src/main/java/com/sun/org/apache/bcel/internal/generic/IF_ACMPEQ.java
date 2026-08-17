package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IF_ACMPEQ extends IfInstruction {
    public IF_ACMPEQ(InstructionHandle instructionHandle) {
        super(Const.IF_ACMPEQ, instructionHandle);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitStackConsumer(this);
        visitor.visitBranchInstruction(this);
        visitor.visitIfInstruction(this);
        visitor.visitIF_ACMPEQ(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.IfInstruction
    public IfInstruction negate() {
        return new IF_ACMPNE(super.getTarget());
    }

    public IF_ACMPEQ() {
    }
}
