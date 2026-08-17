package com.sun.org.apache.bcel.internal.generic;

import defpackage.yz0;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class BranchHandle extends InstructionHandle {
    private BranchInstruction bi;

    private BranchHandle(BranchInstruction branchInstruction) {
        super(branchInstruction);
        this.bi = branchInstruction;
    }

    public static BranchHandle getBranchHandle(BranchInstruction branchInstruction) {
        return new BranchHandle(branchInstruction);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.InstructionHandle
    public int getPosition() {
        return this.bi.getPosition();
    }

    public InstructionHandle getTarget() {
        return this.bi.getTarget();
    }

    @Override // com.sun.org.apache.bcel.internal.generic.InstructionHandle
    public void setInstruction(Instruction instruction) {
        super.setInstruction(instruction);
        if (instruction instanceof BranchInstruction) {
            this.bi = (BranchInstruction) instruction;
        } else {
            yz0.a("Assigning ", instruction, " to branch handle which is not a branch instruction");
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.InstructionHandle
    public void setPosition(int i) {
        this.bi.setPosition(i);
        super.setPosition(i);
    }

    public void setTarget(InstructionHandle instructionHandle) {
        this.bi.setTarget(instructionHandle);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.InstructionHandle
    public int updatePosition(int i, int i2) {
        int iUpdatePosition = this.bi.updatePosition(i, i2);
        super.setPosition(this.bi.getPosition());
        return iUpdatePosition;
    }

    public void updateTarget(InstructionHandle instructionHandle, InstructionHandle instructionHandle2) {
        this.bi.updateTarget(instructionHandle, instructionHandle2);
    }
}
