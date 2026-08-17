package com.sun.org.apache.bcel.internal.generic;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import defpackage.iva;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Select extends BranchInstruction implements VariableLengthInstruction, StackConsumer, StackProducer {

    @Deprecated
    protected int fixed_length;

    @Deprecated
    protected int[] indices;

    @Deprecated
    protected int[] match;

    @Deprecated
    protected int match_length;

    @Deprecated
    protected int padding;

    @Deprecated
    protected InstructionHandle[] targets;

    public Select(short s, int[] iArr, InstructionHandle[] instructionHandleArr, InstructionHandle instructionHandle) {
        super(s, null);
        this.match = iArr;
        this.targets = instructionHandleArr;
        setTarget(instructionHandle);
        for (InstructionHandle instructionHandle2 : instructionHandleArr) {
            BranchInstruction.notifyTarget(null, instructionHandle2, this);
        }
        int length = iArr.length;
        this.match_length = length;
        if (length == instructionHandleArr.length) {
            this.indices = new int[length];
            return;
        }
        throw new ClassGenException("Match and target array have not the same length: Match length: " + iArr.length + " Target length: " + instructionHandleArr.length);
    }

    public Object clone() throws CloneNotSupportedException {
        Select select = (Select) super.clone();
        select.match = (int[]) this.match.clone();
        select.indices = (int[]) this.indices.clone();
        select.targets = (InstructionHandle[]) this.targets.clone();
        return select;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.BranchInstruction, com.sun.org.apache.bcel.internal.generic.InstructionTargeter
    public boolean containsTarget(InstructionHandle instructionHandle) {
        if (super.getTarget() == instructionHandle) {
            return true;
        }
        for (InstructionHandle instructionHandle2 : this.targets) {
            if (instructionHandle2 == instructionHandle) {
                return true;
            }
        }
        return false;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.BranchInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void dispose() {
        super.dispose();
        for (InstructionHandle instructionHandle : this.targets) {
            instructionHandle.removeTargeter(this);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.BranchInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getOpcode());
        for (int i = 0; i < this.padding; i++) {
            dataOutputStream.writeByte(0);
        }
        super.setIndex(getTargetOffset());
        dataOutputStream.writeInt(super.getIndex());
    }

    public final int getFixedLength() {
        return this.fixed_length;
    }

    public final int getIndices(int i) {
        return this.indices[i];
    }

    public final int getMatch(int i) {
        return this.match[i];
    }

    public final int getMatchLength() {
        return this.match_length;
    }

    public int[] getMatchs() {
        return this.match;
    }

    public final int getPadding() {
        return this.padding;
    }

    public final InstructionHandle getTarget(int i) {
        return this.targets[i];
    }

    public InstructionHandle[] getTargets() {
        return this.targets;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.BranchInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void initFromFile(ByteSequence byteSequence, boolean z) throws IOException {
        this.padding = (4 - (byteSequence.getIndex() % 4)) % 4;
        for (int i = 0; i < this.padding; i++) {
            byteSequence.readByte();
        }
        super.setIndex(byteSequence.readInt());
    }

    public final void setFixedLength(int i) {
        this.fixed_length = i;
    }

    public final int setIndices(int i, int i2) {
        this.indices[i] = i2;
        return i2;
    }

    public final void setMatch(int i, int i2) {
        this.match[i] = i2;
    }

    public final int setMatchLength(int i) {
        this.match_length = i;
        return i;
    }

    public final void setMatches(int[] iArr) {
        this.match = iArr;
    }

    public void setTarget(int i, InstructionHandle instructionHandle) {
        BranchInstruction.notifyTarget(this.targets[i], instructionHandle, this);
        this.targets[i] = instructionHandle;
    }

    public final void setTargets(InstructionHandle[] instructionHandleArr) {
        this.targets = instructionHandleArr;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.BranchInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public String toString(boolean z) {
        StringBuilder sb = new StringBuilder(super.toString(z));
        if (z) {
            for (int i = 0; i < this.match_length; i++) {
                InstructionHandle instructionHandle = this.targets[i];
                String string = instructionHandle != null ? instructionHandle.getInstruction().toString() : PsiKeyword.NULL;
                sb.append("(");
                sb.append(this.match[i]);
                sb.append(", ");
                sb.append(string);
                sb.append(" = {");
                sb.append(this.indices[i]);
                sb.append("})");
            }
        } else {
            sb.append(" ...");
        }
        return sb.toString();
    }

    @Override // com.sun.org.apache.bcel.internal.generic.BranchInstruction
    public int updatePosition(int i, int i2) {
        setPosition(getPosition() + i);
        short length = (short) super.getLength();
        int position = (4 - ((getPosition() + 1) % 4)) % 4;
        this.padding = position;
        super.setLength((short) (this.fixed_length + position));
        return super.getLength() - length;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.BranchInstruction, com.sun.org.apache.bcel.internal.generic.InstructionTargeter
    public void updateTarget(InstructionHandle instructionHandle, InstructionHandle instructionHandle2) {
        boolean z;
        int i = 0;
        if (super.getTarget() == instructionHandle) {
            setTarget(instructionHandle2);
            z = true;
        } else {
            z = false;
        }
        while (true) {
            InstructionHandle[] instructionHandleArr = this.targets;
            if (i >= instructionHandleArr.length) {
                break;
            }
            if (instructionHandleArr[i] == instructionHandle) {
                setTarget(i, instructionHandle2);
                z = true;
            }
            i++;
        }
        if (z) {
            return;
        }
        iva.a("Not targeting ", instructionHandle);
    }

    public int[] getIndices() {
        return this.indices;
    }

    public final void setIndices(int[] iArr) {
        this.indices = iArr;
    }

    public Select() {
    }
}
