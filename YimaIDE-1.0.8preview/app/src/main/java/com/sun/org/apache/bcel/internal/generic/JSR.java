package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JSR extends JsrInstruction implements VariableLengthInstruction {
    public JSR(InstructionHandle instructionHandle) {
        super(Const.JSR, instructionHandle);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitStackProducer(this);
        visitor.visitVariableLengthInstruction(this);
        visitor.visitBranchInstruction(this);
        visitor.visitJsrInstruction(this);
        visitor.visitJSR(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.BranchInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.setIndex(getTargetOffset());
        if (super.getOpcode() == 168) {
            super.dump(dataOutputStream);
            return;
        }
        super.setIndex(getTargetOffset());
        dataOutputStream.writeByte(super.getOpcode());
        dataOutputStream.writeInt(super.getIndex());
    }

    @Override // com.sun.org.apache.bcel.internal.generic.BranchInstruction
    public int updatePosition(int i, int i2) {
        int targetOffset = getTargetOffset();
        setPosition(getPosition() + i);
        if (Math.abs(targetOffset) < 32767 - i2) {
            return 0;
        }
        super.setOpcode(Const.JSR_W);
        short length = (short) super.getLength();
        super.setLength(5);
        return super.getLength() - length;
    }

    public JSR() {
    }
}
