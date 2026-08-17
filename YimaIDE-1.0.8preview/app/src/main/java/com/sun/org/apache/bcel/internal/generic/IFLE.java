package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IFLE extends IfInstruction {
    public IFLE(InstructionHandle instructionHandle) {
        super(Const.IFLE, instructionHandle);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitStackConsumer(this);
        visitor.visitBranchInstruction(this);
        visitor.visitIfInstruction(this);
        visitor.visitIFLE(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.IfInstruction
    public IfInstruction negate() {
        return new IFGT(super.getTarget());
    }

    public IFLE() {
    }
}
