package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ReturnaddressType extends Type {
    public static final ReturnaddressType NO_TARGET = new ReturnaddressType();
    private InstructionHandle returnTarget;

    public ReturnaddressType(InstructionHandle instructionHandle) {
        super((byte) 16, "<return address targeting " + instructionHandle + ">");
        this.returnTarget = instructionHandle;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Type
    public boolean equals(Object obj) {
        InstructionHandle instructionHandle;
        if (!(obj instanceof ReturnaddressType)) {
            return false;
        }
        ReturnaddressType returnaddressType = (ReturnaddressType) obj;
        InstructionHandle instructionHandle2 = this.returnTarget;
        if (instructionHandle2 == null || (instructionHandle = returnaddressType.returnTarget) == null) {
            return returnaddressType.returnTarget == instructionHandle2;
        }
        return instructionHandle.equals(instructionHandle2);
    }

    public InstructionHandle getTarget() {
        return this.returnTarget;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Type
    public int hashCode() {
        InstructionHandle instructionHandle = this.returnTarget;
        if (instructionHandle == null) {
            return 0;
        }
        return instructionHandle.hashCode();
    }

    private ReturnaddressType() {
        super((byte) 16, "<return address>");
    }
}
