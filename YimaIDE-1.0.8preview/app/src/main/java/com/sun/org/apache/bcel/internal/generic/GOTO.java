package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class GOTO extends GotoInstruction implements VariableLengthInstruction {
    public GOTO(InstructionHandle instructionHandle) {
        super(Const.GOTO, instructionHandle);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitVariableLengthInstruction(this);
        visitor.visitUnconditionalBranch(this);
        visitor.visitBranchInstruction(this);
        visitor.visitGotoInstruction(this);
        visitor.visitGOTO(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.BranchInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.setIndex(getTargetOffset());
        short opcode = getOpcode();
        if (opcode == 167) {
            super.dump(dataOutputStream);
            return;
        }
        super.setIndex(getTargetOffset());
        dataOutputStream.writeByte(opcode);
        dataOutputStream.writeInt(super.getIndex());
    }

    @Override // com.sun.org.apache.bcel.internal.generic.BranchInstruction
    public int updatePosition(int i, int i2) {
        int targetOffset = getTargetOffset();
        setPosition(getPosition() + i);
        if (Math.abs(targetOffset) < 32767 - i2) {
            return 0;
        }
        super.setOpcode(Const.GOTO_W);
        short length = (short) super.getLength();
        super.setLength(5);
        return super.getLength() - length;
    }

    public GOTO() {
    }
}
