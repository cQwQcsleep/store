package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import defpackage.c5c;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Instruction implements Cloneable {
    static final Instruction[] EMPTY_ARRAY = new Instruction[0];
    private static InstructionComparator cmp = InstructionComparator.DEFAULT;

    @Deprecated
    protected short length;

    @Deprecated
    protected short opcode;

    public Instruction() {
        this.length = (short) 1;
        this.opcode = (short) -1;
    }

    @Deprecated
    public static InstructionComparator getComparator() {
        return cmp;
    }

    public static boolean isValidByte(int i) {
        return i >= -128 && i <= 127;
    }

    public static boolean isValidShort(int i) {
        return i >= -32768 && i <= 32767;
    }

    public static Instruction readInstruction(ByteSequence byteSequence) throws IOException {
        boolean z;
        Instruction iinc;
        Instruction iload;
        short unsignedByte = (short) byteSequence.readUnsignedByte();
        if (unsignedByte == 196) {
            unsignedByte = (short) byteSequence.readUnsignedByte();
            z = true;
        } else {
            z = false;
        }
        Instruction instruction = InstructionConst.getInstruction(unsignedByte);
        if (instruction != null) {
            return instruction;
        }
        if (unsignedByte == 132) {
            iinc = new IINC();
        } else if (unsignedByte == 192) {
            iinc = new CHECKCAST();
        } else if (unsignedByte == 193) {
            iinc = new INSTANCEOF();
        } else if (unsignedByte == 254) {
            iinc = new IMPDEP1();
        } else if (unsignedByte != 255) {
            switch (unsignedByte) {
                case 16:
                    iinc = new BIPUSH();
                    break;
                case 17:
                    iinc = new SIPUSH();
                    break;
                case 18:
                    iinc = new LDC();
                    break;
                case 19:
                    iinc = new LDC_W();
                    break;
                case 20:
                    iinc = new LDC2_W();
                    break;
                case 21:
                    iinc = new ILOAD();
                    break;
                case 22:
                    iinc = new LLOAD();
                    break;
                case 23:
                    iinc = new FLOAD();
                    break;
                case 24:
                    iinc = new DLOAD();
                    break;
                case 25:
                    iinc = new ALOAD();
                    break;
                case 26:
                    iinc = new ILOAD(0);
                    break;
                case 27:
                    iload = new ILOAD(1);
                    iinc = iload;
                    break;
                case 28:
                    iinc = new ILOAD(2);
                    break;
                case 29:
                    iinc = new ILOAD(3);
                    break;
                case 30:
                    iinc = new LLOAD(0);
                    break;
                case 31:
                    iload = new LLOAD(1);
                    iinc = iload;
                    break;
                case 32:
                    iinc = new LLOAD(2);
                    break;
                case 33:
                    iinc = new LLOAD(3);
                    break;
                case 34:
                    iinc = new FLOAD(0);
                    break;
                case 35:
                    iload = new FLOAD(1);
                    iinc = iload;
                    break;
                case 36:
                    iinc = new FLOAD(2);
                    break;
                case 37:
                    iinc = new FLOAD(3);
                    break;
                case 38:
                    iinc = new DLOAD(0);
                    break;
                case 39:
                    iload = new DLOAD(1);
                    iinc = iload;
                    break;
                case 40:
                    iinc = new DLOAD(2);
                    break;
                case 41:
                    iinc = new DLOAD(3);
                    break;
                case 42:
                    iinc = new ALOAD(0);
                    break;
                case 43:
                    iload = new ALOAD(1);
                    iinc = iload;
                    break;
                case 44:
                    iinc = new ALOAD(2);
                    break;
                case 45:
                    iinc = new ALOAD(3);
                    break;
                default:
                    switch (unsignedByte) {
                        case 54:
                            iinc = new ISTORE();
                            break;
                        case 55:
                            iinc = new LSTORE();
                            break;
                        case 56:
                            iinc = new FSTORE();
                            break;
                        case 57:
                            iinc = new DSTORE();
                            break;
                        case 58:
                            iinc = new ASTORE();
                            break;
                        case 59:
                            iinc = new ISTORE(0);
                            break;
                        case 60:
                            iload = new ISTORE(1);
                            iinc = iload;
                            break;
                        case 61:
                            iinc = new ISTORE(2);
                            break;
                        case 62:
                            iinc = new ISTORE(3);
                            break;
                        case 63:
                            iinc = new LSTORE(0);
                            break;
                        case 64:
                            iload = new LSTORE(1);
                            iinc = iload;
                            break;
                        case 65:
                            iinc = new LSTORE(2);
                            break;
                        case 66:
                            iinc = new LSTORE(3);
                            break;
                        case 67:
                            iinc = new FSTORE(0);
                            break;
                        case 68:
                            iload = new FSTORE(1);
                            iinc = iload;
                            break;
                        case 69:
                            iinc = new FSTORE(2);
                            break;
                        case 70:
                            iinc = new FSTORE(3);
                            break;
                        case 71:
                            iinc = new DSTORE(0);
                            break;
                        case 72:
                            iload = new DSTORE(1);
                            iinc = iload;
                            break;
                        case 73:
                            iinc = new DSTORE(2);
                            break;
                        case 74:
                            iinc = new DSTORE(3);
                            break;
                        case 75:
                            iinc = new ASTORE(0);
                            break;
                        case 76:
                            iload = new ASTORE(1);
                            iinc = iload;
                            break;
                        case 77:
                            iinc = new ASTORE(2);
                            break;
                        case 78:
                            iinc = new ASTORE(3);
                            break;
                        default:
                            switch (unsignedByte) {
                                case 153:
                                    iinc = new IFEQ();
                                    break;
                                case 154:
                                    iinc = new IFNE();
                                    break;
                                case 155:
                                    iinc = new IFLT();
                                    break;
                                case 156:
                                    iinc = new IFGE();
                                    break;
                                case 157:
                                    iinc = new IFGT();
                                    break;
                                case 158:
                                    iinc = new IFLE();
                                    break;
                                case 159:
                                    iinc = new IF_ICMPEQ();
                                    break;
                                case 160:
                                    iinc = new IF_ICMPNE();
                                    break;
                                case 161:
                                    iinc = new IF_ICMPLT();
                                    break;
                                case 162:
                                    iinc = new IF_ICMPGE();
                                    break;
                                case 163:
                                    iinc = new IF_ICMPGT();
                                    break;
                                case 164:
                                    iinc = new IF_ICMPLE();
                                    break;
                                case 165:
                                    iinc = new IF_ACMPEQ();
                                    break;
                                case 166:
                                    iinc = new IF_ACMPNE();
                                    break;
                                case 167:
                                    iinc = new GOTO();
                                    break;
                                case 168:
                                    iinc = new JSR();
                                    break;
                                case 169:
                                    iinc = new RET();
                                    break;
                                case 170:
                                    iinc = new TABLESWITCH();
                                    break;
                                case 171:
                                    iinc = new LOOKUPSWITCH();
                                    break;
                                default:
                                    switch (unsignedByte) {
                                        case 178:
                                            iinc = new GETSTATIC();
                                            break;
                                        case 179:
                                            iinc = new PUTSTATIC();
                                            break;
                                        case 180:
                                            iinc = new GETFIELD();
                                            break;
                                        case 181:
                                            iinc = new PUTFIELD();
                                            break;
                                        case 182:
                                            iinc = new INVOKEVIRTUAL();
                                            break;
                                        case 183:
                                            iinc = new INVOKESPECIAL();
                                            break;
                                        case 184:
                                            iinc = new INVOKESTATIC();
                                            break;
                                        case 185:
                                            iinc = new INVOKEINTERFACE();
                                            break;
                                        case 186:
                                            iinc = new INVOKEDYNAMIC();
                                            break;
                                        case 187:
                                            iinc = new NEW();
                                            break;
                                        case 188:
                                            iinc = new NEWARRAY();
                                            break;
                                        case 189:
                                            iinc = new ANEWARRAY();
                                            break;
                                        default:
                                            switch (unsignedByte) {
                                                case 197:
                                                    iinc = new MULTIANEWARRAY();
                                                    break;
                                                case 198:
                                                    iinc = new IFNULL();
                                                    break;
                                                case 199:
                                                    iinc = new IFNONNULL();
                                                    break;
                                                case 200:
                                                    iinc = new GOTO_W();
                                                    break;
                                                case 201:
                                                    iinc = new JSR_W();
                                                    break;
                                                case 202:
                                                    iinc = new BREAKPOINT();
                                                    break;
                                                default:
                                                    c5c.a("Illegal opcode detected: ", unsignedByte);
                                                    return null;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } else {
            iinc = new IMPDEP2();
        }
        if (z && !(iinc instanceof LocalVariableInstruction) && !(iinc instanceof RET)) {
            c5c.a("Illegal opcode after wide: ", unsignedByte);
            return null;
        }
        iinc.setOpcode(unsignedByte);
        iinc.initFromFile(byteSequence, z);
        return iinc;
    }

    @Deprecated
    public static void setComparator(InstructionComparator instructionComparator) {
        cmp = instructionComparator;
    }

    public abstract void accept(Visitor visitor);

    public int consumeStack(ConstantPoolGen constantPoolGen) {
        return Const.getConsumeStack(this.opcode);
    }

    public Instruction copy() {
        if (InstructionConst.getInstruction(getOpcode()) != null) {
            return this;
        }
        try {
            return (Instruction) clone();
        } catch (CloneNotSupportedException e) {
            System.err.println(e);
            return null;
        }
    }

    public void dispose() {
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(this.opcode);
    }

    public boolean equals(Object obj) {
        return (obj instanceof Instruction) && cmp.equals(this, (Instruction) obj);
    }

    public int getLength() {
        return this.length;
    }

    public String getName() {
        return Const.getOpcodeName(this.opcode);
    }

    public short getOpcode() {
        return this.opcode;
    }

    public int hashCode() {
        return this.opcode;
    }

    public void initFromFile(ByteSequence byteSequence, boolean z) throws IOException {
    }

    public int produceStack(ConstantPoolGen constantPoolGen) {
        return Const.getProduceStack(this.opcode);
    }

    public final void setLength(int i) {
        this.length = (short) i;
    }

    public void setOpcode(short s) {
        this.opcode = s;
    }

    public String toString(boolean z) {
        if (!z) {
            return getName();
        }
        return getName() + "[" + ((int) this.opcode) + "](" + ((int) this.length) + ")";
    }

    public Instruction(short s, short s2) {
        this.length = s2;
        this.opcode = s;
    }

    public String toString() {
        return toString(true);
    }

    public String toString(ConstantPool constantPool) {
        return toString(false);
    }
}
