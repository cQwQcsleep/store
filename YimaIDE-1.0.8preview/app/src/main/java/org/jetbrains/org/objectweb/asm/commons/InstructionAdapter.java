package org.jetbrains.org.objectweb.asm.commons;

import org.eclipse.jdt.core.compiler.CategorizedProblem;
import org.eclipse.jdt.internal.compiler.lookup.TypeIds;
import org.eclipse.jdt.internal.compiler.parser.TerminalTokens;
import org.jcodings.transcode.TranscodeFunctions;
import org.jetbrains.org.objectweb.asm.ConstantDynamic;
import org.jetbrains.org.objectweb.asm.Handle;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class InstructionAdapter extends MethodVisitor {
    public static final Type OBJECT_TYPE = Type.getType("Ljava/lang/Object;");

    public InstructionAdapter(MethodVisitor methodVisitor) {
        this(589824, methodVisitor);
        if (getClass() == InstructionAdapter.class) {
            return;
        }
        g33.a();
        throw null;
    }

    public static void cast(MethodVisitor methodVisitor, Type type, Type type2) {
        if (type != type2) {
            Type type3 = Type.DOUBLE_TYPE;
            if (type == type3) {
                if (type2 == Type.FLOAT_TYPE) {
                    methodVisitor.visitInsn(TranscodeFunctions.EMACS_MULE_LEADING_CODE_JISX0208_1978);
                    return;
                } else if (type2 == Type.LONG_TYPE) {
                    methodVisitor.visitInsn(143);
                    return;
                } else {
                    methodVisitor.visitInsn(142);
                    cast(methodVisitor, Type.INT_TYPE, type2);
                    return;
                }
            }
            Type type4 = Type.FLOAT_TYPE;
            if (type == type4) {
                if (type2 == type3) {
                    methodVisitor.visitInsn(141);
                    return;
                } else if (type2 == Type.LONG_TYPE) {
                    methodVisitor.visitInsn(140);
                    return;
                } else {
                    methodVisitor.visitInsn(TerminalTokens.TokenNamegoto);
                    cast(methodVisitor, Type.INT_TYPE, type2);
                    return;
                }
            }
            Type type5 = Type.LONG_TYPE;
            if (type == type5) {
                if (type2 == type3) {
                    methodVisitor.visitInsn(138);
                    return;
                } else if (type2 == type4) {
                    methodVisitor.visitInsn(137);
                    return;
                } else {
                    methodVisitor.visitInsn(136);
                    cast(methodVisitor, Type.INT_TYPE, type2);
                    return;
                }
            }
            if (type2 == Type.BYTE_TYPE) {
                methodVisitor.visitInsn(TypeIds.Object2float);
                return;
            }
            if (type2 == Type.CHAR_TYPE) {
                methodVisitor.visitInsn(146);
                return;
            }
            if (type2 == type3) {
                methodVisitor.visitInsn(135);
                return;
            }
            if (type2 == type4) {
                methodVisitor.visitInsn(TerminalTokens.TokenNameRestrictedIdentifierWhen);
            } else if (type2 == type5) {
                methodVisitor.visitInsn(TerminalTokens.TokenNameBeginCaseElement);
            } else if (type2 == Type.SHORT_TYPE) {
                methodVisitor.visitInsn(TypeIds.Byte2Float);
            }
        }
    }

    public static void newarray(MethodVisitor methodVisitor, Type type) {
        int i;
        switch (type.getSort()) {
            case 1:
                i = 4;
                break;
            case 2:
                i = 5;
                break;
            case 3:
                i = 8;
                break;
            case 4:
                i = 9;
                break;
            case 5:
                i = 10;
                break;
            case 6:
                i = 6;
                break;
            case 7:
                i = 11;
                break;
            case 8:
                i = 7;
                break;
            default:
                methodVisitor.visitTypeInsn(189, type.getInternalName());
                return;
        }
        methodVisitor.visitIntInsn(TypeIds.Null2String, i);
    }

    public void aconst(Object obj) {
        MethodVisitor methodVisitor = ((MethodVisitor) this).mv;
        if (obj == null) {
            methodVisitor.visitInsn(1);
        } else {
            methodVisitor.visitLdcInsn(obj);
        }
    }

    public void add(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type.getOpcode(96));
    }

    public void aload(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type.getOpcode(46));
    }

    public void and(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type.getOpcode(TerminalTokens.TokenNameexports));
    }

    public void anew(Type type) {
        ((MethodVisitor) this).mv.visitTypeInsn(TypeIds.String2String, type.getInternalName());
    }

    public void areturn(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type.getOpcode(172));
    }

    public void arraylength() {
        ((MethodVisitor) this).mv.visitInsn(190);
    }

    public void astore(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type.getOpcode(79));
    }

    public void athrow() {
        ((MethodVisitor) this).mv.visitInsn(191);
    }

    public void cconst(ConstantDynamic constantDynamic) {
        ((MethodVisitor) this).mv.visitLdcInsn(constantDynamic);
    }

    public void checkcast(Type type) {
        ((MethodVisitor) this).mv.visitTypeInsn(192, type.getInternalName());
    }

    public void cmpg(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type == Type.FLOAT_TYPE ? CategorizedProblem.CAT_RESTRICTION : TypeIds.Double2Float);
    }

    public void cmpl(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type == Type.FLOAT_TYPE ? 149 : TypeIds.Long2Float);
    }

    public void dconst(double d) {
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        if (jDoubleToLongBits == 0 || jDoubleToLongBits == 4607182418800017408L) {
            ((MethodVisitor) this).mv.visitInsn(((int) d) + 14);
        } else {
            ((MethodVisitor) this).mv.visitLdcInsn(Double.valueOf(d));
        }
    }

    public void div(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type.getOpcode(TerminalTokens.TokenNamecatch));
    }

    public void dup() {
        ((MethodVisitor) this).mv.visitInsn(89);
    }

    public void dup2() {
        ((MethodVisitor) this).mv.visitInsn(92);
    }

    public void dup2X1() {
        ((MethodVisitor) this).mv.visitInsn(93);
    }

    public void dup2X2() {
        ((MethodVisitor) this).mv.visitInsn(94);
    }

    public void dupX1() {
        ((MethodVisitor) this).mv.visitInsn(90);
    }

    public void dupX2() {
        ((MethodVisitor) this).mv.visitInsn(91);
    }

    public void fconst(float f) {
        int iFloatToIntBits = Float.floatToIntBits(f);
        if (iFloatToIntBits == 0 || iFloatToIntBits == 1065353216 || iFloatToIntBits == 1073741824) {
            ((MethodVisitor) this).mv.visitInsn(((int) f) + 11);
        } else {
            ((MethodVisitor) this).mv.visitLdcInsn(Float.valueOf(f));
        }
    }

    public void getfield(String str, String str2, String str3) {
        ((MethodVisitor) this).mv.visitFieldInsn(180, str, str2, str3);
    }

    public void getstatic(String str, String str2, String str3) {
        ((MethodVisitor) this).mv.visitFieldInsn(TypeIds.Char2String, str, str2, str3);
    }

    public void goTo(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(TypeIds.Long2Int, label);
    }

    public void hconst(Handle handle) {
        ((MethodVisitor) this).mv.visitLdcInsn(handle);
    }

    public void iconst(int i) {
        if (i >= -1 && i <= 5) {
            ((MethodVisitor) this).mv.visitInsn(i + 3);
            return;
        }
        if (i >= -128 && i <= 127) {
            ((MethodVisitor) this).mv.visitIntInsn(16, i);
        } else if (i < -32768 || i > 32767) {
            ((MethodVisitor) this).mv.visitLdcInsn(Integer.valueOf(i));
        } else {
            ((MethodVisitor) this).mv.visitIntInsn(17, i);
        }
    }

    public void ifacmpeq(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(TypeIds.Boolean2Int, label);
    }

    public void ifacmpne(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(166, label);
    }

    public void ifeq(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(TypeIds.Float2Float, label);
    }

    public void ifge(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(156, label);
    }

    public void ifgt(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(157, label);
    }

    public void ificmpeq(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(159, label);
    }

    public void ificmpge(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(TypeIds.Char2Int, label);
    }

    public void ificmpgt(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(TypeIds.Byte2Int, label);
    }

    public void ificmple(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(TypeIds.Short2Int, label);
    }

    public void ificmplt(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(TypeIds.Object2int, label);
    }

    public void ificmpne(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(160, label);
    }

    public void ifle(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(158, label);
    }

    public void iflt(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(155, label);
    }

    public void ifne(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(TypeIds.Int2Float, label);
    }

    public void ifnonnull(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(199, label);
    }

    public void ifnull(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(198, label);
    }

    public void iinc(int i, int i2) {
        ((MethodVisitor) this).mv.visitIincInsn(i, i2);
    }

    public void instanceOf(Type type) {
        ((MethodVisitor) this).mv.visitTypeInsn(193, type.getInternalName());
    }

    public void invokedynamic(String str, String str2, Handle handle, Object[] objArr) {
        ((MethodVisitor) this).mv.visitInvokeDynamicInsn(str, str2, handle, objArr);
    }

    public void invokeinterface(String str, String str2, String str3) {
        ((MethodVisitor) this).mv.visitMethodInsn(TypeIds.Float2String, str, str2, str3, true);
    }

    public void invokespecial(String str, String str2, String str3, boolean z) {
        if (((MethodVisitor) this).api >= 327680) {
            ((MethodVisitor) this).mv.visitMethodInsn(TypeIds.Long2String, str, str2, str3, z);
        } else if (z) {
            c41.a("INVOKESPECIAL on interfaces require ASM 5");
        } else {
            invokespecial(str, str2, str3);
        }
    }

    public void invokestatic(String str, String str2, String str3, boolean z) {
        if (((MethodVisitor) this).api >= 327680) {
            ((MethodVisitor) this).mv.visitMethodInsn(TypeIds.Double2String, str, str2, str3, z);
        } else if (z) {
            c41.a("INVOKESTATIC on interfaces require ASM 5");
        } else {
            invokestatic(str, str2, str3);
        }
    }

    public void invokevirtual(String str, String str2, String str3, boolean z) {
        if (((MethodVisitor) this).api >= 327680) {
            ((MethodVisitor) this).mv.visitMethodInsn(182, str, str2, str3, z);
        } else if (z) {
            c41.a("INVOKEVIRTUAL on interfaces require ASM 5");
        } else {
            invokevirtual(str, str2, str3);
        }
    }

    public void jsr(Label label) {
        ((MethodVisitor) this).mv.visitJumpInsn(TypeIds.Double2Int, label);
    }

    public void lcmp() {
        ((MethodVisitor) this).mv.visitInsn(TypeIds.Short2Float);
    }

    public void lconst(long j) {
        if (j == 0 || j == 1) {
            ((MethodVisitor) this).mv.visitInsn(((int) j) + 9);
        } else {
            ((MethodVisitor) this).mv.visitLdcInsn(Long.valueOf(j));
        }
    }

    public void load(int i, Type type) {
        ((MethodVisitor) this).mv.visitVarInsn(type.getOpcode(21), i);
    }

    public void lookupswitch(Label label, int[] iArr, Label[] labelArr) {
        ((MethodVisitor) this).mv.visitLookupSwitchInsn(label, iArr, labelArr);
    }

    public void mark(Label label) {
        ((MethodVisitor) this).mv.visitLabel(label);
    }

    public void monitorenter() {
        ((MethodVisitor) this).mv.visitInsn(194);
    }

    public void monitorexit() {
        ((MethodVisitor) this).mv.visitInsn(195);
    }

    public void mul(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type.getOpcode(TerminalTokens.TokenNameUNSIGNED_RIGHT_SHIFT_EQUAL));
    }

    public void multianewarray(String str, int i) {
        ((MethodVisitor) this).mv.visitMultiANewArrayInsn(str, i);
    }

    public void neg(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type.getOpcode(116));
    }

    public void nop() {
        ((MethodVisitor) this).mv.visitInsn(0);
    }

    public void or(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type.getOpcode(128));
    }

    public void pop() {
        ((MethodVisitor) this).mv.visitInsn(87);
    }

    public void pop2() {
        ((MethodVisitor) this).mv.visitInsn(88);
    }

    public void putfield(String str, String str2, String str3) {
        ((MethodVisitor) this).mv.visitFieldInsn(TypeIds.Boolean2String, str, str2, str3);
    }

    public void putstatic(String str, String str2, String str3) {
        ((MethodVisitor) this).mv.visitFieldInsn(TypeIds.Byte2String, str, str2, str3);
    }

    public void rem(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type.getOpcode(TerminalTokens.TokenNameimport));
    }

    public void ret(int i) {
        ((MethodVisitor) this).mv.visitVarInsn(TypeIds.Float2Int, i);
    }

    public void shl(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type.getOpcode(120));
    }

    public void shr(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type.getOpcode(122));
    }

    public void store(int i, Type type) {
        ((MethodVisitor) this).mv.visitVarInsn(type.getOpcode(54), i);
    }

    public void sub(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type.getOpcode(100));
    }

    public void swap() {
        ((MethodVisitor) this).mv.visitInsn(95);
    }

    public void tableswitch(int i, int i2, Label label, Label... labelArr) {
        ((MethodVisitor) this).mv.visitTableSwitchInsn(i, i2, label, labelArr);
    }

    public void tconst(Type type) {
        ((MethodVisitor) this).mv.visitLdcInsn(type);
    }

    public void ushr(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type.getOpcode(TerminalTokens.TokenNameelse));
    }

    public void visitFieldInsn(int i, String str, String str2, String str3) {
        switch (i) {
            case TypeIds.Char2String /* 178 */:
                getstatic(str, str2, str3);
                break;
            case TypeIds.Byte2String /* 179 */:
                putstatic(str, str2, str3);
                break;
            case 180:
                getfield(str, str2, str3);
                break;
            case TypeIds.Boolean2String /* 181 */:
                putfield(str, str2, str3);
                break;
            default:
                j2d.a();
                break;
        }
    }

    public void visitIincInsn(int i, int i2) {
        iinc(i, i2);
    }

    public void visitInsn(int i) {
        if (i == 190) {
            arraylength();
            return;
        }
        if (i == 191) {
            athrow();
            return;
        }
        if (i == 194) {
            monitorenter();
            return;
        }
        if (i == 195) {
            monitorexit();
            return;
        }
        switch (i) {
            case 0:
                nop();
                break;
            case 1:
                aconst(null);
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                iconst(i - 3);
                break;
            case 9:
            case 10:
                lconst(i - 9);
                break;
            case 11:
            case 12:
            case 13:
                fconst(i - 11);
                break;
            case 14:
            case 15:
                dconst(i - 14);
                break;
            default:
                switch (i) {
                    case 46:
                        aload(Type.INT_TYPE);
                        break;
                    case 47:
                        aload(Type.LONG_TYPE);
                        break;
                    case 48:
                        aload(Type.FLOAT_TYPE);
                        break;
                    case 49:
                        aload(Type.DOUBLE_TYPE);
                        break;
                    case 50:
                        aload(OBJECT_TYPE);
                        break;
                    case 51:
                        aload(Type.BYTE_TYPE);
                        break;
                    case 52:
                        aload(Type.CHAR_TYPE);
                        break;
                    case 53:
                        aload(Type.SHORT_TYPE);
                        break;
                    default:
                        switch (i) {
                            case TerminalTokens.TokenNamethrow /* 79 */:
                                astore(Type.INT_TYPE);
                                break;
                            case 80:
                                astore(Type.LONG_TYPE);
                                break;
                            case 81:
                                astore(Type.FLOAT_TYPE);
                                break;
                            case 82:
                                astore(Type.DOUBLE_TYPE);
                                break;
                            case TerminalTokens.TokenNamebreak /* 83 */:
                                astore(OBJECT_TYPE);
                                break;
                            case TerminalTokens.TokenNamecontinue /* 84 */:
                                astore(Type.BYTE_TYPE);
                                break;
                            case 85:
                                astore(Type.CHAR_TYPE);
                                break;
                            case 86:
                                astore(Type.SHORT_TYPE);
                                break;
                            case TerminalTokens.TokenNameif /* 87 */:
                                pop();
                                break;
                            case TerminalTokens.TokenNamereturn /* 88 */:
                                pop2();
                                break;
                            case TerminalTokens.TokenNametry /* 89 */:
                                dup();
                                break;
                            case 90:
                                dupX1();
                                break;
                            case 91:
                                dupX2();
                                break;
                            case 92:
                                dup2();
                                break;
                            case 93:
                                dup2X1();
                                break;
                            case 94:
                                dup2X2();
                                break;
                            case TerminalTokens.TokenNameMINUS_EQUAL /* 95 */:
                                swap();
                                break;
                            case TerminalTokens.TokenNameMULTIPLY_EQUAL /* 96 */:
                                add(Type.INT_TYPE);
                                break;
                            case TerminalTokens.TokenNameDIVIDE_EQUAL /* 97 */:
                                add(Type.LONG_TYPE);
                                break;
                            case TerminalTokens.TokenNameAND_EQUAL /* 98 */:
                                add(Type.FLOAT_TYPE);
                                break;
                            case 99:
                                add(Type.DOUBLE_TYPE);
                                break;
                            case 100:
                                sub(Type.INT_TYPE);
                                break;
                            case TerminalTokens.TokenNameREMAINDER_EQUAL /* 101 */:
                                sub(Type.LONG_TYPE);
                                break;
                            case TerminalTokens.TokenNameLEFT_SHIFT_EQUAL /* 102 */:
                                sub(Type.FLOAT_TYPE);
                                break;
                            case TerminalTokens.TokenNameRIGHT_SHIFT_EQUAL /* 103 */:
                                sub(Type.DOUBLE_TYPE);
                                break;
                            case TerminalTokens.TokenNameUNSIGNED_RIGHT_SHIFT_EQUAL /* 104 */:
                                mul(Type.INT_TYPE);
                                break;
                            case TerminalTokens.TokenNameARROW /* 105 */:
                                mul(Type.LONG_TYPE);
                                break;
                            case TerminalTokens.TokenNameboolean /* 106 */:
                                mul(Type.FLOAT_TYPE);
                                break;
                            case TerminalTokens.TokenNamebyte /* 107 */:
                                mul(Type.DOUBLE_TYPE);
                                break;
                            case TerminalTokens.TokenNamecatch /* 108 */:
                                div(Type.INT_TYPE);
                                break;
                            case TerminalTokens.TokenNamechar /* 109 */:
                                div(Type.LONG_TYPE);
                                break;
                            case 110:
                                div(Type.FLOAT_TYPE);
                                break;
                            case TerminalTokens.TokenNamefloat /* 111 */:
                                div(Type.DOUBLE_TYPE);
                                break;
                            case TerminalTokens.TokenNameimport /* 112 */:
                                rem(Type.INT_TYPE);
                                break;
                            case 113:
                                rem(Type.LONG_TYPE);
                                break;
                            case 114:
                                rem(Type.FLOAT_TYPE);
                                break;
                            case 115:
                                rem(Type.DOUBLE_TYPE);
                                break;
                            case 116:
                                neg(Type.INT_TYPE);
                                break;
                            case TerminalTokens.TokenNamefinally /* 117 */:
                                neg(Type.LONG_TYPE);
                                break;
                            case TerminalTokens.TokenNamethrows /* 118 */:
                                neg(Type.FLOAT_TYPE);
                                break;
                            case 119:
                                neg(Type.DOUBLE_TYPE);
                                break;
                            case 120:
                                shl(Type.INT_TYPE);
                                break;
                            case 121:
                                shl(Type.LONG_TYPE);
                                break;
                            case 122:
                                shr(Type.INT_TYPE);
                                break;
                            case TerminalTokens.TokenNameELLIPSIS /* 123 */:
                                shr(Type.LONG_TYPE);
                                break;
                            case TerminalTokens.TokenNameelse /* 124 */:
                                ushr(Type.INT_TYPE);
                                break;
                            case TerminalTokens.TokenNamerequires /* 125 */:
                                ushr(Type.LONG_TYPE);
                                break;
                            case TerminalTokens.TokenNameexports /* 126 */:
                                and(Type.INT_TYPE);
                                break;
                            case TerminalTokens.TokenNameopens /* 127 */:
                                and(Type.LONG_TYPE);
                                break;
                            case 128:
                                or(Type.INT_TYPE);
                                break;
                            case 129:
                                or(Type.LONG_TYPE);
                                break;
                            case 130:
                                xor(Type.INT_TYPE);
                                break;
                            case 131:
                                xor(Type.LONG_TYPE);
                                break;
                            default:
                                switch (i) {
                                    case TerminalTokens.TokenNameBeginCaseElement /* 133 */:
                                        cast(Type.INT_TYPE, Type.LONG_TYPE);
                                        break;
                                    case TerminalTokens.TokenNameRestrictedIdentifierWhen /* 134 */:
                                        cast(Type.INT_TYPE, Type.FLOAT_TYPE);
                                        break;
                                    case 135:
                                        cast(Type.INT_TYPE, Type.DOUBLE_TYPE);
                                        break;
                                    case 136:
                                        cast(Type.LONG_TYPE, Type.INT_TYPE);
                                        break;
                                    case 137:
                                        cast(Type.LONG_TYPE, Type.FLOAT_TYPE);
                                        break;
                                    case 138:
                                        cast(Type.LONG_TYPE, Type.DOUBLE_TYPE);
                                        break;
                                    case TerminalTokens.TokenNamegoto /* 139 */:
                                        cast(Type.FLOAT_TYPE, Type.INT_TYPE);
                                        break;
                                    case 140:
                                        cast(Type.FLOAT_TYPE, Type.LONG_TYPE);
                                        break;
                                    case 141:
                                        cast(Type.FLOAT_TYPE, Type.DOUBLE_TYPE);
                                        break;
                                    case 142:
                                        cast(Type.DOUBLE_TYPE, Type.INT_TYPE);
                                        break;
                                    case 143:
                                        cast(Type.DOUBLE_TYPE, Type.LONG_TYPE);
                                        break;
                                    case TranscodeFunctions.EMACS_MULE_LEADING_CODE_JISX0208_1978 /* 144 */:
                                        cast(Type.DOUBLE_TYPE, Type.FLOAT_TYPE);
                                        break;
                                    case TypeIds.Object2float /* 145 */:
                                        cast(Type.INT_TYPE, Type.BYTE_TYPE);
                                        break;
                                    case 146:
                                        cast(Type.INT_TYPE, Type.CHAR_TYPE);
                                        break;
                                    case TypeIds.Byte2Float /* 147 */:
                                        cast(Type.INT_TYPE, Type.SHORT_TYPE);
                                        break;
                                    case TypeIds.Short2Float /* 148 */:
                                        lcmp();
                                        break;
                                    case 149:
                                        cmpl(Type.FLOAT_TYPE);
                                        break;
                                    case CategorizedProblem.CAT_RESTRICTION /* 150 */:
                                        cmpg(Type.FLOAT_TYPE);
                                        break;
                                    case TypeIds.Long2Float /* 151 */:
                                        cmpl(Type.DOUBLE_TYPE);
                                        break;
                                    case TypeIds.Double2Float /* 152 */:
                                        cmpg(Type.DOUBLE_TYPE);
                                        break;
                                    default:
                                        switch (i) {
                                            case 172:
                                                areturn(Type.INT_TYPE);
                                                break;
                                            case 173:
                                                areturn(Type.LONG_TYPE);
                                                break;
                                            case 174:
                                                areturn(Type.FLOAT_TYPE);
                                                break;
                                            case 175:
                                                areturn(Type.DOUBLE_TYPE);
                                                break;
                                            case 176:
                                                areturn(OBJECT_TYPE);
                                                break;
                                            case TypeIds.Object2String /* 177 */:
                                                areturn(Type.VOID_TYPE);
                                                break;
                                            default:
                                                j2d.a();
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    public void visitIntInsn(int i, int i2) {
        if (i == 16) {
            iconst(i2);
            return;
        }
        if (i == 17) {
            iconst(i2);
            return;
        }
        if (i != 188) {
            j2d.a();
            return;
        }
        switch (i2) {
            case 4:
                newarray(Type.BOOLEAN_TYPE);
                break;
            case 5:
                newarray(Type.CHAR_TYPE);
                break;
            case 6:
                newarray(Type.FLOAT_TYPE);
                break;
            case 7:
                newarray(Type.DOUBLE_TYPE);
                break;
            case 8:
                newarray(Type.BYTE_TYPE);
                break;
            case 9:
                newarray(Type.SHORT_TYPE);
                break;
            case 10:
                newarray(Type.INT_TYPE);
                break;
            case 11:
                newarray(Type.LONG_TYPE);
                break;
            default:
                j2d.a();
                break;
        }
    }

    public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        invokedynamic(str, str2, handle, objArr);
    }

    public void visitJumpInsn(int i, Label label) {
        if (i == 198) {
            ifnull(label);
            return;
        }
        if (i == 199) {
            ifnonnull(label);
            return;
        }
        switch (i) {
            case TypeIds.Float2Float /* 153 */:
                ifeq(label);
                break;
            case TypeIds.Int2Float /* 154 */:
                ifne(label);
                break;
            case 155:
                iflt(label);
                break;
            case 156:
                ifge(label);
                break;
            case 157:
                ifgt(label);
                break;
            case 158:
                ifle(label);
                break;
            case 159:
                ificmpeq(label);
                break;
            case 160:
                ificmpne(label);
                break;
            case TypeIds.Object2int /* 161 */:
                ificmplt(label);
                break;
            case TypeIds.Char2Int /* 162 */:
                ificmpge(label);
                break;
            case TypeIds.Byte2Int /* 163 */:
                ificmpgt(label);
                break;
            case TypeIds.Short2Int /* 164 */:
                ificmple(label);
                break;
            case TypeIds.Boolean2Int /* 165 */:
                ifacmpeq(label);
                break;
            case 166:
                ifacmpne(label);
                break;
            case TypeIds.Long2Int /* 167 */:
                goTo(label);
                break;
            case TypeIds.Double2Int /* 168 */:
                jsr(label);
                break;
            default:
                j2d.a();
                break;
        }
    }

    public void visitLabel(Label label) {
        mark(label);
    }

    public void visitLdcInsn(Object obj) {
        if (((MethodVisitor) this).api < 327680 && ((obj instanceof Handle) || ((obj instanceof Type) && ((Type) obj).getSort() == 11))) {
            c41.a("This feature requires ASM5");
            return;
        }
        if (((MethodVisitor) this).api < 458752 && (obj instanceof ConstantDynamic)) {
            c41.a("This feature requires ASM7");
            return;
        }
        if (obj instanceof Integer) {
            iconst(((Integer) obj).intValue());
            return;
        }
        if (obj instanceof Byte) {
            iconst(((Byte) obj).intValue());
            return;
        }
        if (obj instanceof Character) {
            iconst(((Character) obj).charValue());
            return;
        }
        if (obj instanceof Short) {
            iconst(((Short) obj).intValue());
            return;
        }
        if (obj instanceof Boolean) {
            iconst(((Boolean) obj).booleanValue() ? 1 : 0);
            return;
        }
        if (obj instanceof Float) {
            fconst(((Float) obj).floatValue());
            return;
        }
        if (obj instanceof Long) {
            lconst(((Long) obj).longValue());
            return;
        }
        if (obj instanceof Double) {
            dconst(((Double) obj).doubleValue());
            return;
        }
        if (obj instanceof String) {
            aconst(obj);
            return;
        }
        if (obj instanceof Type) {
            tconst((Type) obj);
            return;
        }
        if (obj instanceof Handle) {
            hconst((Handle) obj);
        } else if (obj instanceof ConstantDynamic) {
            cconst((ConstantDynamic) obj);
        } else {
            j2d.a();
        }
    }

    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        lookupswitch(label, iArr, labelArr);
    }

    public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        if (((MethodVisitor) this).api < 327680 && (i & 256) == 0) {
            super.visitMethodInsn(i, str, str2, str3, z);
        }
        switch (i & (-257)) {
            case 182:
                invokevirtual(str, str2, str3, z);
                break;
            case TypeIds.Long2String /* 183 */:
                invokespecial(str, str2, str3, z);
                break;
            case TypeIds.Double2String /* 184 */:
                invokestatic(str, str2, str3, z);
                break;
            case TypeIds.Float2String /* 185 */:
                invokeinterface(str, str2, str3);
                break;
            default:
                j2d.a();
                break;
        }
    }

    public void visitMultiANewArrayInsn(String str, int i) {
        multianewarray(str, i);
    }

    public void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        tableswitch(i, i2, label, labelArr);
    }

    public void visitTypeInsn(int i, String str) {
        Type objectType = Type.getObjectType(str);
        if (i == 187) {
            anew(objectType);
            return;
        }
        if (i == 189) {
            newarray(objectType);
            return;
        }
        if (i == 192) {
            checkcast(objectType);
        } else if (i == 193) {
            instanceOf(objectType);
        } else {
            j2d.a();
        }
    }

    public void visitVarInsn(int i, int i2) {
        if (i == 169) {
            ret(i2);
            return;
        }
        switch (i) {
            case 21:
                load(i2, Type.INT_TYPE);
                break;
            case 22:
                load(i2, Type.LONG_TYPE);
                break;
            case 23:
                load(i2, Type.FLOAT_TYPE);
                break;
            case 24:
                load(i2, Type.DOUBLE_TYPE);
                break;
            case 25:
                load(i2, OBJECT_TYPE);
                break;
            default:
                switch (i) {
                    case 54:
                        store(i2, Type.INT_TYPE);
                        break;
                    case 55:
                        store(i2, Type.LONG_TYPE);
                        break;
                    case 56:
                        store(i2, Type.FLOAT_TYPE);
                        break;
                    case 57:
                        store(i2, Type.DOUBLE_TYPE);
                        break;
                    case 58:
                        store(i2, OBJECT_TYPE);
                        break;
                    default:
                        j2d.a();
                        break;
                }
                break;
        }
    }

    public void xor(Type type) {
        ((MethodVisitor) this).mv.visitInsn(type.getOpcode(130));
    }

    public InstructionAdapter(int i, MethodVisitor methodVisitor) {
        super(i, methodVisitor);
    }

    @Deprecated
    public void invokespecial(String str, String str2, String str3) {
        if (((MethodVisitor) this).api >= 327680) {
            invokespecial(str, str2, str3, false);
        } else {
            ((MethodVisitor) this).mv.visitMethodInsn(TypeIds.Long2String, str, str2, str3, false);
        }
    }

    @Deprecated
    public void invokestatic(String str, String str2, String str3) {
        if (((MethodVisitor) this).api >= 327680) {
            invokestatic(str, str2, str3, false);
        } else {
            ((MethodVisitor) this).mv.visitMethodInsn(TypeIds.Double2String, str, str2, str3, false);
        }
    }

    @Deprecated
    public void invokevirtual(String str, String str2, String str3) {
        if (((MethodVisitor) this).api >= 327680) {
            invokevirtual(str, str2, str3, false);
        } else {
            ((MethodVisitor) this).mv.visitMethodInsn(182, str, str2, str3);
        }
    }

    public void newarray(Type type) {
        newarray(((MethodVisitor) this).mv, type);
    }

    public void cast(Type type, Type type2) {
        cast(((MethodVisitor) this).mv, type, type2);
    }
}
