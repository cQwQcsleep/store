package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.LineNumber;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LineNumberGen implements InstructionTargeter, Cloneable {
    static final LineNumberGen[] EMPTY_ARRAY = new LineNumberGen[0];
    private InstructionHandle ih;
    private int srcLine;

    public LineNumberGen(InstructionHandle instructionHandle, int i) {
        setInstruction(instructionHandle);
        setSourceLine(i);
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
        return this.ih == instructionHandle;
    }

    public InstructionHandle getInstruction() {
        return this.ih;
    }

    public LineNumber getLineNumber() {
        return new LineNumber(this.ih.getPosition(), this.srcLine);
    }

    public int getSourceLine() {
        return this.srcLine;
    }

    public void setInstruction(InstructionHandle instructionHandle) {
        Objects.requireNonNull(instructionHandle, "instructionHandle");
        BranchInstruction.notifyTarget(this.ih, instructionHandle, this);
        this.ih = instructionHandle;
    }

    public void setSourceLine(int i) {
        this.srcLine = i;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.InstructionTargeter
    public void updateTarget(InstructionHandle instructionHandle, InstructionHandle instructionHandle2) {
        if (instructionHandle == this.ih) {
            setInstruction(instructionHandle2);
            return;
        }
        StringBuilder sb = new StringBuilder("Not targeting ");
        sb.append(instructionHandle);
        InstructionHandle instructionHandle3 = this.ih;
        sb.append(", but ");
        sb.append(instructionHandle3);
        sb.append("}");
        throw new ClassGenException(sb.toString());
    }
}
