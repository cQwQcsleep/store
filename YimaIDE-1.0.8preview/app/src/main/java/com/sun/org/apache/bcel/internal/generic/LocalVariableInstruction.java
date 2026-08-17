package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.util.ByteSequence;
import defpackage.c5c;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class LocalVariableInstruction extends Instruction implements TypedInstruction, IndexedInstruction {
    private short cTag;
    private short canonTag;

    @Deprecated
    protected int n;

    public LocalVariableInstruction(short s, short s2, int i) {
        super(s, (short) 2);
        this.n = -1;
        this.cTag = s2;
        this.canonTag = s;
        setIndex(i);
    }

    private boolean wide() {
        return this.n > 255;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        if (wide()) {
            dataOutputStream.writeByte(196);
        }
        dataOutputStream.writeByte(super.getOpcode());
        if (super.getLength() > 1) {
            boolean zWide = wide();
            int i = this.n;
            if (zWide) {
                dataOutputStream.writeShort(i);
            } else {
                dataOutputStream.writeByte(i);
            }
        }
    }

    public short getCanonicalTag() {
        return this.canonTag;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.IndexedInstruction
    public final int getIndex() {
        return this.n;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0024  */
    /* JADX WARN: Code duplicated, block: B:13:0x0027  */
    /* JADX WARN: Code duplicated, block: B:15:0x002a  */
    /* JADX WARN: Code duplicated, block: B:7:0x001e  */
    /* JADX WARN: Code duplicated, block: B:9:0x0021  */
    public Type getType(ConstantPoolGen constantPoolGen) {
        short s = this.canonTag;
        switch (s) {
            case 21:
                return Type.INT;
            case 22:
                return Type.LONG;
            case 23:
                return Type.FLOAT;
            case 24:
                return Type.DOUBLE;
            case 25:
                return Type.OBJECT;
            default:
                switch (s) {
                    case 54:
                        return Type.INT;
                    case 55:
                        return Type.LONG;
                    case 56:
                        return Type.FLOAT;
                    case 57:
                        return Type.DOUBLE;
                    case 58:
                        return Type.OBJECT;
                    default:
                        throw new ClassGenException("Unknown case in switch" + ((int) this.canonTag));
                }
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void initFromFile(ByteSequence byteSequence, boolean z) throws IOException {
        if (z) {
            this.n = byteSequence.readUnsignedShort();
            super.setLength(4);
            return;
        }
        short opcode = super.getOpcode();
        if ((opcode >= 21 && opcode <= 25) || (opcode >= 54 && opcode <= 58)) {
            this.n = byteSequence.readUnsignedByte();
            super.setLength(2);
        } else {
            if (opcode <= 45) {
                this.n = (opcode - 26) % 4;
            } else {
                this.n = (opcode - 59) % 4;
            }
            super.setLength(1);
        }
    }

    public void setIndex(int i) {
        if (i < 0 || i > 65535) {
            c5c.a("Illegal value: ", i);
            return;
        }
        this.n = i;
        if (i <= 3) {
            super.setOpcode((short) (this.cTag + i));
            super.setLength(1);
            return;
        }
        super.setOpcode(this.canonTag);
        if (wide()) {
            super.setLength(4);
        } else {
            super.setLength(2);
        }
    }

    public final void setIndexOnly(int i) {
        this.n = i;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public String toString(boolean z) {
        short opcode = super.getOpcode();
        if ((opcode >= 26 && opcode <= 45) || (opcode >= 59 && opcode <= 78)) {
            return super.toString(z);
        }
        return super.toString(z) + " " + this.n;
    }

    public LocalVariableInstruction(short s, short s2) {
        this.n = -1;
        this.canonTag = s;
        this.cTag = s2;
    }

    public LocalVariableInstruction() {
        this.n = -1;
        this.cTag = (short) -1;
        this.canonTag = (short) -1;
    }
}
