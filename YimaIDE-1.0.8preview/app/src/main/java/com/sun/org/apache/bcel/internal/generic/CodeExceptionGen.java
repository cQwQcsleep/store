package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.CodeException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class CodeExceptionGen implements InstructionTargeter, Cloneable {
    static final CodeExceptionGen[] EMPTY_ARRAY = new CodeExceptionGen[0];
    private ObjectType catchType;
    private InstructionHandle endPc;
    private InstructionHandle handlerPc;
    private InstructionHandle startPc;

    public CodeExceptionGen(InstructionHandle instructionHandle, InstructionHandle instructionHandle2, InstructionHandle instructionHandle3, ObjectType objectType) {
        setStartPC(instructionHandle);
        setEndPC(instructionHandle2);
        setHandlerPC(instructionHandle3);
        this.catchType = objectType;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new Error("Clone Not Supported");
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.InstructionTargeter
    public boolean containsTarget(InstructionHandle instructionHandle) {
        return this.startPc == instructionHandle || this.endPc == instructionHandle || this.handlerPc == instructionHandle;
    }

    public ObjectType getCatchType() {
        return this.catchType;
    }

    public CodeException getCodeException(ConstantPoolGen constantPoolGen) {
        int position = this.startPc.getPosition();
        int position2 = this.endPc.getPosition() + this.endPc.getInstruction().getLength();
        int position3 = this.handlerPc.getPosition();
        ObjectType objectType = this.catchType;
        return new CodeException(position, position2, position3, objectType == null ? 0 : constantPoolGen.addClass(objectType));
    }

    public InstructionHandle getEndPC() {
        return this.endPc;
    }

    public InstructionHandle getHandlerPC() {
        return this.handlerPc;
    }

    public InstructionHandle getStartPC() {
        return this.startPc;
    }

    public void setCatchType(ObjectType objectType) {
        this.catchType = objectType;
    }

    public void setEndPC(InstructionHandle instructionHandle) {
        BranchInstruction.notifyTarget(this.endPc, instructionHandle, this);
        this.endPc = instructionHandle;
    }

    public void setHandlerPC(InstructionHandle instructionHandle) {
        BranchInstruction.notifyTarget(this.handlerPc, instructionHandle, this);
        this.handlerPc = instructionHandle;
    }

    public void setStartPC(InstructionHandle instructionHandle) {
        BranchInstruction.notifyTarget(this.startPc, instructionHandle, this);
        this.startPc = instructionHandle;
    }

    public String toString() {
        return "CodeExceptionGen(" + this.startPc + ", " + this.endPc + ", " + this.handlerPc + ")";
    }

    @Override // com.sun.org.apache.bcel.internal.generic.InstructionTargeter
    public void updateTarget(InstructionHandle instructionHandle, InstructionHandle instructionHandle2) {
        boolean z;
        boolean z2 = true;
        if (this.startPc == instructionHandle) {
            setStartPC(instructionHandle2);
            z = true;
        } else {
            z = false;
        }
        if (this.endPc == instructionHandle) {
            setEndPC(instructionHandle2);
            z = true;
        }
        if (this.handlerPc == instructionHandle) {
            setHandlerPC(instructionHandle2);
        } else {
            z2 = z;
        }
        if (z2) {
            return;
        }
        StringBuilder sb = new StringBuilder("Not targeting ");
        sb.append(instructionHandle);
        sb.append(", but {");
        sb.append(this.startPc);
        sb.append(", ");
        sb.append(this.endPc);
        InstructionHandle instructionHandle3 = this.handlerPc;
        sb.append(", ");
        sb.append(instructionHandle3);
        sb.append("}");
        throw new ClassGenException(sb.toString());
    }
}
