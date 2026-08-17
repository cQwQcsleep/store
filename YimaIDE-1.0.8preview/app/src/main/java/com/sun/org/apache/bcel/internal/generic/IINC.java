package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import defpackage.c5c;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IINC extends LocalVariableInstruction {
    private int c;
    private boolean wide;

    public IINC(int i, int i2) {
        super.setOpcode(Const.IINC);
        super.setLength(3);
        setIndex(i);
        setIncrement(i2);
    }

    private void setWide() {
        boolean z = super.getIndex() > 255;
        this.wide = z;
        int i = this.c;
        if (i > 0) {
            this.wide = z || i > 127;
        } else {
            this.wide = z || i < -128;
        }
        if (this.wide) {
            super.setLength(6);
        } else {
            super.setLength(3);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitLocalVariableInstruction(this);
        visitor.visitIINC(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.LocalVariableInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        if (this.wide) {
            dataOutputStream.writeByte(196);
        }
        dataOutputStream.writeByte(super.getOpcode());
        if (this.wide) {
            dataOutputStream.writeShort(super.getIndex());
            dataOutputStream.writeShort(this.c);
        } else {
            dataOutputStream.writeByte(super.getIndex());
            dataOutputStream.writeByte(this.c);
        }
    }

    public final int getIncrement() {
        return this.c;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.LocalVariableInstruction, com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        return Type.INT;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.LocalVariableInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void initFromFile(ByteSequence byteSequence, boolean z) throws IOException {
        this.wide = z;
        if (z) {
            super.setLength(6);
            super.setIndexOnly(byteSequence.readUnsignedShort());
            this.c = byteSequence.readShort();
        } else {
            super.setLength(3);
            super.setIndexOnly(byteSequence.readUnsignedByte());
            this.c = byteSequence.readByte();
        }
    }

    public final void setIncrement(int i) {
        this.c = i;
        setWide();
    }

    @Override // com.sun.org.apache.bcel.internal.generic.LocalVariableInstruction, com.sun.org.apache.bcel.internal.generic.IndexedInstruction
    public final void setIndex(int i) {
        if (i < 0) {
            c5c.a("Negative index value: ", i);
        } else {
            super.setIndexOnly(i);
            setWide();
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.LocalVariableInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public String toString(boolean z) {
        return super.toString(z) + " " + this.c;
    }

    public IINC() {
    }
}
