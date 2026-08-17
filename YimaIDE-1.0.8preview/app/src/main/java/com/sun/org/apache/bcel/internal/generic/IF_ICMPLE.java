package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IF_ICMPLE extends IfInstruction {
    public IF_ICMPLE(InstructionHandle instructionHandle) {
        super(Const.IF_ICMPLE, instructionHandle);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitStackConsumer(this);
        visitor.visitBranchInstruction(this);
        visitor.visitIfInstruction(this);
        visitor.visitIF_ICMPLE(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.IfInstruction
    public IfInstruction negate() {
        return new IF_ICMPGT(super.getTarget());
    }

    public IF_ICMPLE() {
    }
}
