package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.LocalVariable;
import defpackage.c5c;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LocalVariableGen implements InstructionTargeter, NamedAndTyped, Cloneable {
    private InstructionHandle end;
    private int index;
    private boolean liveToEnd;
    private String name;
    private int origIndex;
    private InstructionHandle start;
    private Type type;

    public LocalVariableGen(int i, String str, Type type, InstructionHandle instructionHandle, InstructionHandle instructionHandle2) {
        if (i < 0 || i > 65535) {
            c5c.a("Invalid index: ", i);
            throw null;
        }
        this.name = str;
        this.type = type;
        this.index = i;
        setStart(instructionHandle);
        setEnd(instructionHandle2);
        this.origIndex = i;
        this.liveToEnd = instructionHandle2 == null;
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
        return this.start == instructionHandle || this.end == instructionHandle;
    }

    public void dispose() {
        setStart(null);
        setEnd(null);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LocalVariableGen)) {
            return false;
        }
        LocalVariableGen localVariableGen = (LocalVariableGen) obj;
        return localVariableGen.index == this.index && localVariableGen.start == this.start && localVariableGen.end == this.end;
    }

    public InstructionHandle getEnd() {
        return this.end;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean getLiveToEnd() {
        return this.liveToEnd;
    }

    public LocalVariable getLocalVariable(ConstantPoolGen constantPoolGen) {
        int i;
        int i2;
        InstructionHandle instructionHandle = this.start;
        if (instructionHandle == null || this.end == null) {
            i = 0;
            i2 = 0;
        } else {
            int position = instructionHandle.getPosition();
            int position2 = this.end.getPosition() - position;
            if (this.end.getNext() == null && this.liveToEnd) {
                position2 += this.end.getInstruction().getLength();
            }
            i = position;
            i2 = position2;
        }
        return new LocalVariable(i, i2, constantPoolGen.addUtf8(this.name), constantPoolGen.addUtf8(this.type.getSignature()), this.index, constantPoolGen.getConstantPool(), this.origIndex);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.NamedAndTyped
    public String getName() {
        return this.name;
    }

    public int getOrigIndex() {
        return this.origIndex;
    }

    public InstructionHandle getStart() {
        return this.start;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.NamedAndTyped
    public Type getType() {
        return this.type;
    }

    public int hashCode() {
        return this.type.hashCode() ^ this.name.hashCode();
    }

    public void setEnd(InstructionHandle instructionHandle) {
        BranchInstruction.notifyTarget(this.end, instructionHandle, this);
        this.end = instructionHandle;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void setLiveToEnd(boolean z) {
        this.liveToEnd = z;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.NamedAndTyped
    public void setName(String str) {
        this.name = str;
    }

    public void setStart(InstructionHandle instructionHandle) {
        BranchInstruction.notifyTarget(this.start, instructionHandle, this);
        this.start = instructionHandle;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.NamedAndTyped
    public void setType(Type type) {
        this.type = type;
    }

    public String toString() {
        return "LocalVariableGen(" + this.name + ", " + this.type + ", " + this.start + ", " + this.end + ")";
    }

    @Override // com.sun.org.apache.bcel.internal.generic.InstructionTargeter
    public void updateTarget(InstructionHandle instructionHandle, InstructionHandle instructionHandle2) {
        boolean z;
        boolean z2 = true;
        if (this.start == instructionHandle) {
            setStart(instructionHandle2);
            z = true;
        } else {
            z = false;
        }
        if (this.end == instructionHandle) {
            setEnd(instructionHandle2);
        } else {
            z2 = z;
        }
        if (z2) {
            return;
        }
        StringBuilder sb = new StringBuilder("Not targeting ");
        sb.append(instructionHandle);
        sb.append(", but {");
        sb.append(this.start);
        InstructionHandle instructionHandle3 = this.end;
        sb.append(", ");
        sb.append(instructionHandle3);
        sb.append("}");
        throw new ClassGenException(sb.toString());
    }

    public LocalVariableGen(int i, String str, Type type, InstructionHandle instructionHandle, InstructionHandle instructionHandle2, int i2) {
        this(i, str, type, instructionHandle, instructionHandle2);
        this.origIndex = i2;
    }
}
