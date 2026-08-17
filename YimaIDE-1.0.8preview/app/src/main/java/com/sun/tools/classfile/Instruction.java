package com.sun.tools.classfile;

import com.intellij.psi.PsiKeyword;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Instruction {
    private byte[] bytes;
    private int pc;

    /* JADX INFO: renamed from: com.sun.tools.classfile.Instruction$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$classfile$Instruction$Kind;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$classfile$Opcode;

        static {
            int[] iArr = new int[Kind.values().length];
            $SwitchMap$com$sun$tools$classfile$Instruction$Kind = iArr;
            try {
                iArr[Kind.NO_OPERANDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.ATYPE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.BRANCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.BRANCH_W.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.BYTE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.CPREF.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.CPREF_W.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.CPREF_W_UBYTE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.CPREF_W_UBYTE_ZERO.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.DYNAMIC.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.LOCAL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.LOCAL_BYTE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.SHORT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.WIDE_NO_OPERANDS.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.WIDE_LOCAL.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.WIDE_CPREF_W.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.WIDE_CPREF_W_SHORT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.WIDE_LOCAL_SHORT.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Instruction$Kind[Kind.UNKNOWN.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            int[] iArr2 = new int[Opcode.values().length];
            $SwitchMap$com$sun$tools$classfile$Opcode = iArr2;
            try {
                iArr2[Opcode.TABLESWITCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Opcode[Opcode.LOOKUPSWITCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused21) {
            }
        }
    }

    public enum Kind {
        NO_OPERANDS(1),
        ATYPE(2),
        BRANCH(3),
        BRANCH_W(5),
        BYTE(2),
        CPREF(2),
        CPREF_W(3),
        CPREF_W_UBYTE(4),
        CPREF_W_UBYTE_ZERO(5),
        DYNAMIC(-1),
        LOCAL(2),
        LOCAL_BYTE(3),
        SHORT(3),
        WIDE_NO_OPERANDS(2),
        WIDE_LOCAL(4),
        WIDE_CPREF_W(4),
        WIDE_CPREF_W_SHORT(6),
        WIDE_LOCAL_SHORT(6),
        UNKNOWN(1);

        public final int length;

        Kind(int i) {
            this.length = i;
        }
    }

    public interface KindVisitor<R, P> {
        R visitArrayType(Instruction instruction, TypeKind typeKind, P p);

        R visitBranch(Instruction instruction, int i, P p);

        R visitConstantPoolRef(Instruction instruction, int i, P p);

        R visitConstantPoolRefAndValue(Instruction instruction, int i, int i2, P p);

        R visitLocal(Instruction instruction, int i, P p);

        R visitLocalAndValue(Instruction instruction, int i, int i2, P p);

        R visitLookupSwitch(Instruction instruction, int i, int i2, int[] iArr, int[] iArr2, P p);

        R visitNoOperands(Instruction instruction, P p);

        R visitTableSwitch(Instruction instruction, int i, int i2, int i3, int[] iArr, P p);

        R visitUnknown(Instruction instruction, P p);

        R visitValue(Instruction instruction, int i, P p);
    }

    public enum TypeKind {
        T_BOOLEAN(4, "boolean"),
        T_CHAR(5, PsiKeyword.CHAR),
        T_FLOAT(6, "float"),
        T_DOUBLE(7, "double"),
        T_BYTE(8, "byte"),
        T_SHORT(9, "short"),
        T_INT(10, "int"),
        T_LONG(11, "long");

        public final String name;
        public final int value;

        TypeKind(int i, String str) {
            this.value = i;
            this.name = str;
        }

        public static TypeKind get(int i) {
            switch (i) {
                case 4:
                    return T_BOOLEAN;
                case 5:
                    return T_CHAR;
                case 6:
                    return T_FLOAT;
                case 7:
                    return T_DOUBLE;
                case 8:
                    return T_BYTE;
                case 9:
                    return T_SHORT;
                case 10:
                    return T_INT;
                case 11:
                    return T_LONG;
                default:
                    return null;
            }
        }
    }

    public Instruction(byte[] bArr, int i) {
        this.bytes = bArr;
        this.pc = i;
    }

    private static int align(int i) {
        return (i + 3) & (-4);
    }

    public <R, P> R accept(KindVisitor<R, P> kindVisitor, P p) {
        switch (AnonymousClass1.$SwitchMap$com$sun$tools$classfile$Instruction$Kind[getKind().ordinal()]) {
            case 1:
                return kindVisitor.visitNoOperands(this, p);
            case 2:
                return kindVisitor.visitArrayType(this, TypeKind.get(getUnsignedByte(1)), p);
            case 3:
                return kindVisitor.visitBranch(this, getShort(1), p);
            case 4:
                return kindVisitor.visitBranch(this, getInt(1), p);
            case 5:
                return kindVisitor.visitValue(this, getByte(1), p);
            case 6:
                return kindVisitor.visitConstantPoolRef(this, getUnsignedByte(1), p);
            case 7:
                return kindVisitor.visitConstantPoolRef(this, getUnsignedShort(1), p);
            case 8:
            case 9:
                return kindVisitor.visitConstantPoolRefAndValue(this, getUnsignedShort(1), getUnsignedByte(3), p);
            case 10:
                int i = AnonymousClass1.$SwitchMap$com$sun$tools$classfile$Opcode[getOpcode().ordinal()];
                if (i == 1) {
                    int iAlign = align(this.pc + 1) - this.pc;
                    int i2 = getInt(iAlign);
                    int i3 = getInt(iAlign + 4);
                    int i4 = getInt(iAlign + 8);
                    if (i3 > i4) {
                        g33.a();
                        return null;
                    }
                    int i5 = (i4 - i3) + 1;
                    int[] iArr = new int[i5];
                    for (int i6 = 0; i6 < i5; i6++) {
                        iArr[i6] = getInt(iAlign + 12 + (i6 * 4));
                    }
                    return kindVisitor.visitTableSwitch(this, i2, i3, i4, iArr, p);
                }
                if (i != 2) {
                    g33.a();
                    return null;
                }
                int iAlign2 = align(this.pc + 1) - this.pc;
                int i7 = getInt(iAlign2);
                int i8 = getInt(iAlign2 + 4);
                if (i8 < 0) {
                    g33.a();
                    return null;
                }
                int[] iArr2 = new int[i8];
                int[] iArr3 = new int[i8];
                for (int i9 = 0; i9 < i8; i9++) {
                    int i10 = i9 * 8;
                    iArr2[i9] = getInt(iAlign2 + 8 + i10);
                    iArr3[i9] = getInt(iAlign2 + 12 + i10);
                }
                return kindVisitor.visitLookupSwitch(this, i7, i8, iArr2, iArr3, p);
            case 11:
                return kindVisitor.visitLocal(this, getUnsignedByte(1), p);
            case 12:
                return kindVisitor.visitLocalAndValue(this, getUnsignedByte(1), getByte(2), p);
            case 13:
                return kindVisitor.visitValue(this, getShort(1), p);
            case 14:
                return kindVisitor.visitNoOperands(this, p);
            case 15:
                return kindVisitor.visitLocal(this, getUnsignedShort(2), p);
            case 16:
                return kindVisitor.visitConstantPoolRef(this, getUnsignedShort(2), p);
            case 17:
                return kindVisitor.visitConstantPoolRefAndValue(this, getUnsignedShort(2), getUnsignedByte(4), p);
            case 18:
                return kindVisitor.visitLocalAndValue(this, getUnsignedShort(2), getShort(4), p);
            case 19:
                return kindVisitor.visitUnknown(this, p);
            default:
                g33.a();
                return null;
        }
    }

    public int getByte(int i) {
        return this.bytes[this.pc + i];
    }

    public int getInt(int i) {
        return getUnsignedShort(i + 2) | (getShort(i) << 16);
    }

    public Kind getKind() {
        Opcode opcode = getOpcode();
        return opcode != null ? opcode.kind : Kind.UNKNOWN;
    }

    public String getMnemonic() {
        Opcode opcode = getOpcode();
        if (opcode != null) {
            return opcode.toString().toLowerCase(Locale.US);
        }
        return "bytecode " + getUnsignedByte(0);
    }

    public Opcode getOpcode() {
        int unsignedByte = getUnsignedByte(0);
        return (unsignedByte == 196 || unsignedByte == 254 || unsignedByte == 255) ? Opcode.get(unsignedByte, getUnsignedByte(1)) : Opcode.get(unsignedByte);
    }

    public int getPC() {
        return this.pc;
    }

    public int getShort(int i) {
        return getUnsignedByte(i + 1) | (getByte(i) << 8);
    }

    public int getUnsignedByte(int i) {
        return getByte(i) & 255;
    }

    public int getUnsignedShort(int i) {
        return getShort(i) & 65535;
    }

    public int length() {
        int i;
        int i2;
        Opcode opcode = getOpcode();
        if (opcode == null) {
            return 1;
        }
        int i3 = AnonymousClass1.$SwitchMap$com$sun$tools$classfile$Opcode[opcode.ordinal()];
        if (i3 == 1) {
            int iAlign = align(this.pc + 1) - this.pc;
            int i4 = getInt(iAlign + 4);
            int i5 = getInt(iAlign + 8);
            i = iAlign + 12;
            i2 = ((i5 - i4) + 1) * 4;
        } else {
            if (i3 != 2) {
                return opcode.kind.length;
            }
            int iAlign2 = align(this.pc + 1) - this.pc;
            int i6 = getInt(iAlign2 + 4);
            i = iAlign2 + 8;
            i2 = i6 * 8;
        }
        return i + i2;
    }
}
