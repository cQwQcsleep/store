package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TABLESWITCH extends Select {
    public TABLESWITCH(int[] iArr, InstructionHandle[] instructionHandleArr, InstructionHandle instructionHandle) {
        super(Const.TABLESWITCH, iArr, instructionHandleArr, instructionHandle);
        short matchLength = (short) ((getMatchLength() * 4) + 13);
        super.setLength(matchLength);
        setFixedLength(matchLength);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitVariableLengthInstruction(this);
        visitor.visitStackConsumer(this);
        visitor.visitBranchInstruction(this);
        visitor.visitSelect(this);
        visitor.visitTABLESWITCH(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Select, com.sun.org.apache.bcel.internal.generic.BranchInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        int matchLength = getMatchLength();
        dataOutputStream.writeInt(matchLength > 0 ? super.getMatch(0) : 0);
        dataOutputStream.writeInt(matchLength > 0 ? super.getMatch(matchLength - 1) : 0);
        for (int i = 0; i < matchLength; i++) {
            dataOutputStream.writeInt(setIndices(i, getTargetOffset(super.getTarget(i))));
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Select, com.sun.org.apache.bcel.internal.generic.BranchInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void initFromFile(ByteSequence byteSequence, boolean z) throws IOException {
        super.initFromFile(byteSequence, z);
        int i = byteSequence.readInt();
        int i2 = (byteSequence.readInt() - i) + 1;
        setMatchLength(i2);
        short s = (short) ((i2 * 4) + 13);
        setFixedLength(s);
        super.setLength((short) (s + super.getPadding()));
        super.setMatches(new int[i2]);
        super.setIndices(new int[i2]);
        super.setTargets(new InstructionHandle[i2]);
        for (int i3 = 0; i3 < i2; i3++) {
            super.setMatch(i3, i + i3);
            super.setIndices(i3, byteSequence.readInt());
        }
    }

    public TABLESWITCH() {
    }
}
