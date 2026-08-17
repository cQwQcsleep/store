package com.sun.org.apache.bcel.internal.generic;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import defpackage.c5c;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class BranchInstruction extends Instruction implements InstructionTargeter {

    @Deprecated
    protected int index;

    @Deprecated
    protected int position;

    @Deprecated
    protected InstructionHandle target;

    public BranchInstruction(short s, InstructionHandle instructionHandle) {
        super(s, (short) 3);
        setTarget(instructionHandle);
    }

    public static void notifyTarget(InstructionHandle instructionHandle, InstructionHandle instructionHandle2, InstructionTargeter instructionTargeter) {
        if (instructionHandle != null) {
            instructionHandle.removeTargeter(instructionTargeter);
        }
        if (instructionHandle2 != null) {
            instructionHandle2.addTargeter(instructionTargeter);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.InstructionTargeter
    public boolean containsTarget(InstructionHandle instructionHandle) {
        return this.target == instructionHandle;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void dispose() {
        setTarget(null);
        this.index = -1;
        this.position = -1;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getOpcode());
        int targetOffset = getTargetOffset();
        this.index = targetOffset;
        boolean zIsValidShort = Instruction.isValidShort(targetOffset);
        int i = this.index;
        if (zIsValidShort) {
            dataOutputStream.writeShort(i);
        } else {
            c5c.a("Branch target offset too large for short: ", i);
        }
    }

    public final int getIndex() {
        return this.index;
    }

    public int getPosition() {
        return this.position;
    }

    public InstructionHandle getTarget() {
        return this.target;
    }

    public int getTargetOffset(InstructionHandle instructionHandle) {
        if (instructionHandle == null) {
            throw new ClassGenException("Target of " + super.toString(true) + " is invalid null handle");
        }
        int position = instructionHandle.getPosition();
        if (position >= 0) {
            return position - this.position;
        }
        throw new ClassGenException("Invalid branch target position offset for " + super.toString(true) + ":" + position + ":" + instructionHandle);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void initFromFile(ByteSequence byteSequence, boolean z) throws IOException {
        super.setLength(3);
        this.index = byteSequence.readShort();
    }

    public void setIndex(int i) {
        this.index = i;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void setOpcode(short s) {
        InstructionHandle instructionHandle = this.target;
        if (instructionHandle == null) {
            super.setOpcode(s);
            return;
        }
        setTarget(null);
        super.setOpcode(s);
        setTarget(instructionHandle);
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public void setTarget(InstructionHandle instructionHandle) {
        notifyTarget(this.target, instructionHandle, this);
        this.target = instructionHandle;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public String toString(boolean z) {
        String str;
        String string = super.toString(z);
        InstructionHandle instructionHandle = this.target;
        if (instructionHandle == null) {
            str = PsiKeyword.NULL;
        } else if (!z) {
            this.index = instructionHandle.getPosition();
            str = "" + this.index;
        } else if (instructionHandle.getInstruction() == this) {
            str = "<points to itself>";
        } else if (this.target.getInstruction() == null) {
            str = "<null instruction!!!?>";
        } else {
            str = "" + this.target.getPosition();
        }
        return string + " -> " + str;
    }

    public int updatePosition(int i, int i2) {
        this.position += i;
        return 0;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.InstructionTargeter
    public void updateTarget(InstructionHandle instructionHandle, InstructionHandle instructionHandle2) {
        if (this.target == instructionHandle) {
            setTarget(instructionHandle2);
            return;
        }
        StringBuilder sb = new StringBuilder("Not targeting ");
        sb.append(instructionHandle);
        InstructionHandle instructionHandle3 = this.target;
        sb.append(", but ");
        sb.append(instructionHandle3);
        throw new ClassGenException(sb.toString());
    }

    public BranchInstruction() {
    }

    public int getTargetOffset() {
        return getTargetOffset(this.target);
    }
}
