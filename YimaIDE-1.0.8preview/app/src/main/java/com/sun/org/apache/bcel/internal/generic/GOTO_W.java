package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class GOTO_W extends GotoInstruction {
    public GOTO_W(InstructionHandle instructionHandle) {
        super(Const.GOTO_W, instructionHandle);
        super.setLength(5);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitUnconditionalBranch(this);
        visitor.visitBranchInstruction(this);
        visitor.visitGotoInstruction(this);
        visitor.visitGOTO_W(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.BranchInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.setIndex(getTargetOffset());
        dataOutputStream.writeByte(super.getOpcode());
        dataOutputStream.writeInt(super.getIndex());
    }

    @Override // com.sun.org.apache.bcel.internal.generic.BranchInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void initFromFile(ByteSequence byteSequence, boolean z) throws IOException {
        super.setIndex(byteSequence.readInt());
        super.setLength(5);
    }

    public GOTO_W() {
    }
}
