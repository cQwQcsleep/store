package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import defpackage.c5c;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RET extends Instruction implements IndexedInstruction, TypedInstruction {
    private int index;
    private boolean wide;

    public RET(int i) {
        super(Const.RET, (short) 2);
        setIndex(i);
    }

    private void setWide() {
        boolean z = this.index > 255;
        this.wide = z;
        if (z) {
            super.setLength(4);
        } else {
            super.setLength(2);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitRET(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        if (this.wide) {
            dataOutputStream.writeByte(196);
        }
        dataOutputStream.writeByte(super.getOpcode());
        boolean z = this.wide;
        int i = this.index;
        if (z) {
            dataOutputStream.writeShort(i);
        } else {
            dataOutputStream.writeByte(i);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.IndexedInstruction
    public final int getIndex() {
        return this.index;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        return ReturnaddressType.NO_TARGET;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void initFromFile(ByteSequence byteSequence, boolean z) throws IOException {
        this.wide = z;
        if (z) {
            this.index = byteSequence.readUnsignedShort();
            super.setLength(4);
        } else {
            this.index = byteSequence.readUnsignedByte();
            super.setLength(2);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.IndexedInstruction
    public final void setIndex(int i) {
        if (i < 0) {
            c5c.a("Negative index value: ", i);
        } else {
            this.index = i;
            setWide();
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public String toString(boolean z) {
        return super.toString(z) + " " + this.index;
    }

    public RET() {
    }
}
