package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LOOKUPSWITCH extends Select {
    public LOOKUPSWITCH(int[] iArr, InstructionHandle[] instructionHandleArr, InstructionHandle instructionHandle) {
        super(Const.LOOKUPSWITCH, iArr, instructionHandleArr, instructionHandle);
        short matchLength = (short) ((getMatchLength() * 8) + 9);
        super.setLength(matchLength);
        setFixedLength(matchLength);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitVariableLengthInstruction(this);
        visitor.visitStackConsumer(this);
        visitor.visitBranchInstruction(this);
        visitor.visitSelect(this);
        visitor.visitLOOKUPSWITCH(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Select, com.sun.org.apache.bcel.internal.generic.BranchInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        int matchLength = getMatchLength();
        dataOutputStream.writeInt(matchLength);
        for (int i = 0; i < matchLength; i++) {
            dataOutputStream.writeInt(super.getMatch(i));
            dataOutputStream.writeInt(setIndices(i, getTargetOffset(super.getTarget(i))));
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Select, com.sun.org.apache.bcel.internal.generic.BranchInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void initFromFile(ByteSequence byteSequence, boolean z) throws IOException {
        super.initFromFile(byteSequence, z);
        int i = byteSequence.readInt();
        setMatchLength(i);
        setFixedLength((short) ((i * 8) + 9));
        super.setLength((short) (super.getPadding() + i));
        super.setMatches(new int[i]);
        super.setIndices(new int[i]);
        super.setTargets(new InstructionHandle[i]);
        for (int i2 = 0; i2 < i; i2++) {
            super.setMatch(i2, byteSequence.readInt());
            super.setIndices(i2, byteSequence.readInt());
        }
    }

    public LOOKUPSWITCH() {
    }
}
