package com.android.tools.r8.internal;

import org.jetbrains.kotlin.backend.wasm.serialization.ImmediateTags;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public final class gL extends eL {
    public int c;

    public gL(SK sk) {
        super(sk);
        this.c = 0;
    }

    public static boolean f(int i) {
        if (dL.a || i >= 1) {
            return i <= 15 || i == 177 || i == 209 || i == 211 || i == 220;
        }
        x1f.a();
        return false;
    }

    public static String g(int i) {
        if (i == 18) {
            return "LDC";
        }
        if (i == 170) {
            return "TABLESWITCH";
        }
        switch (i) {
            case 1:
                return "ACONST_NULL";
            case 2:
                return "ICONST_M1";
            case 3:
                return "ICONST_0";
            case 4:
                return "ICONST_1";
            case 5:
                return "ICONST_2";
            case 6:
                return "ICONST_3";
            case 7:
                return "ICONST_4";
            case 8:
                return "ICONST_5";
            case 9:
                return "LCONST_0";
            case 10:
                return "LCONST_1";
            case 11:
                return "FCONST_0";
            case 12:
                return "FCONST_1";
            case 13:
                return "FCONST_2";
            case 14:
                return "DCONST_0";
            case 15:
                return "DCONST_1";
            default:
                switch (i) {
                    case OPCode.BACKREF1 /* 46 */:
                        return "IALOAD";
                    case OPCode.BACKREF2 /* 47 */:
                        return "LALOAD";
                    case OPCode.BACKREFN /* 48 */:
                        return "FALOAD";
                    case OPCode.BACKREFN_IC /* 49 */:
                        return "DALOAD";
                    case OPCode.BACKREF_MULTI /* 50 */:
                        return "AALOAD";
                    case OPCode.BACKREF_MULTI_IC /* 51 */:
                        return "BALOAD";
                    case OPCode.BACKREF_WITH_LEVEL /* 52 */:
                        return "CALOAD";
                    case OPCode.MEMORY_START /* 53 */:
                        return "SALOAD";
                    default:
                        switch (i) {
                            case OPCode.FAIL_POS /* 79 */:
                                return "IASTORE";
                            case OPCode.PUSH_STOP_BT /* 80 */:
                                return "LASTORE";
                            case OPCode.POP_STOP_BT /* 81 */:
                                return "FASTORE";
                            case OPCode.LOOK_BEHIND /* 82 */:
                                return "DASTORE";
                            case OPCode.PUSH_LOOK_BEHIND_NOT /* 83 */:
                                return "AASTORE";
                            case OPCode.FAIL_LOOK_BEHIND_NOT /* 84 */:
                                return "BASTORE";
                            case OPCode.PUSH_ABSENT_POS /* 85 */:
                                return "CASTORE";
                            case OPCode.ABSENT /* 86 */:
                                return "SASTORE";
                            default:
                                switch (i) {
                                    case OPCode.SET_OPTION_PUSH /* 96 */:
                                        return "IADD";
                                    case OPCode.SET_OPTION /* 97 */:
                                        return "LADD";
                                    case OPCode.EXACT1_IC_SB /* 98 */:
                                        return "FADD";
                                    case OPCode.EXACTN_IC_SB /* 99 */:
                                        return "DADD";
                                    case 100:
                                        return "ISUB";
                                    case 101:
                                        return "LSUB";
                                    case 102:
                                        return "FSUB";
                                    case 103:
                                        return "DSUB";
                                    case 104:
                                        return "IMUL";
                                    case 105:
                                        return "LMUL";
                                    case 106:
                                        return "FMUL";
                                    case 107:
                                        return "DMUL";
                                    case 108:
                                        return "IDIV";
                                    case 109:
                                        return "LDIV";
                                    case 110:
                                        return "FDIV";
                                    case 111:
                                        return "DDIV";
                                    case 112:
                                        return "IREM";
                                    case 113:
                                        return "LREM";
                                    case 114:
                                        return "FREM";
                                    case 115:
                                        return "DREM";
                                    case 116:
                                        return "INEG";
                                    case 117:
                                        return "LNEG";
                                    case 118:
                                        return "FNEG";
                                    case 119:
                                        return "DNEG";
                                    case 120:
                                        return "ISHL";
                                    case 121:
                                        return "LSHL";
                                    case 122:
                                        return "ISHR";
                                    case 123:
                                        return "LSHR";
                                    case 124:
                                        return "IUSHR";
                                    case 125:
                                        return "LUSHR";
                                    case 126:
                                        return "IAND";
                                    case 127:
                                        return "LAND";
                                    case 128:
                                        return "IOR";
                                    case ImmediateTags.BLOCK_TYPE_NULL_VALUE /* 129 */:
                                        return "LOR";
                                    case 130:
                                        return "IXOR";
                                    case 131:
                                        return "LXOR";
                                    default:
                                        switch (i) {
                                            case 133:
                                                return "I2L";
                                            case 134:
                                                return "I2F";
                                            case 135:
                                                return "I2D";
                                            case 136:
                                                return "L2I";
                                            case 137:
                                                return "L2F";
                                            case 138:
                                                return "L2D";
                                            case 139:
                                                return "F2I";
                                            case 140:
                                                return "F2L";
                                            case 141:
                                                return "F2D";
                                            case 142:
                                                return "D2I";
                                            case 143:
                                                return "D2L";
                                            case 144:
                                                return "D2F";
                                            case 145:
                                                return "I2B";
                                            case 146:
                                                return "I2C";
                                            case 147:
                                                return "I2S";
                                            case 148:
                                                return "LCMP";
                                            case 149:
                                                return "FCMPL";
                                            case 150:
                                                return "FCMPG";
                                            case 151:
                                                return "DCMPL";
                                            case 152:
                                                return "DCMPG";
                                            case 153:
                                                return "IFEQ";
                                            case 154:
                                                return "IFNE";
                                            case 155:
                                                return "IFLT";
                                            case 156:
                                                return "IFGE";
                                            case 157:
                                                return "IFGT";
                                            case 158:
                                                return "IFLE";
                                            case 159:
                                                return "IF_ICMPEQ";
                                            case 160:
                                                return "IF_ICMPNE";
                                            case 161:
                                                return "IF_ICMPLT";
                                            case 162:
                                                return "IF_ICMPGE";
                                            case 163:
                                                return "IF_ICMPGT";
                                            case 164:
                                                return "IF_ICMPLE";
                                            case 165:
                                                return "IF_ACMPEQ";
                                            case 166:
                                                return "IF_ACMPNE";
                                            case 167:
                                                return "GOTO";
                                            default:
                                                switch (i) {
                                                    case 176:
                                                        return "ARETURN";
                                                    case 177:
                                                        return "RETURN";
                                                    case 178:
                                                        return "GETSTATIC";
                                                    case 179:
                                                        return "PUTSTATIC";
                                                    case 180:
                                                        return "GETFIELD";
                                                    case 181:
                                                        return "PUTFIELD";
                                                    case 182:
                                                        return "INVOKEVIRTUAL";
                                                    case 183:
                                                        return "INVOKESPECIAL";
                                                    case 184:
                                                        return "INVOKESTATIC";
                                                    case 185:
                                                        return "INVOKEINTERFACE";
                                                    case 186:
                                                        return "INVOKEDYNAMIC";
                                                    case 187:
                                                        return "NEW";
                                                    case 188:
                                                        return "NEWARRAY";
                                                    default:
                                                        switch (i) {
                                                            case 190:
                                                                return "ARRAYLENGTH";
                                                            case 191:
                                                                return "ATHROW";
                                                            case 192:
                                                                return "CHECKCAST";
                                                            case 193:
                                                                return "INSTANCEOF";
                                                            case 194:
                                                                return "MONITORENTER";
                                                            case 195:
                                                                return "MONITOREXIT";
                                                            default:
                                                                switch (i) {
                                                                    case 197:
                                                                        return "MULTIANEWARRAY";
                                                                    case 198:
                                                                        return "IFNULL";
                                                                    case 199:
                                                                        return "IFNONNULL";
                                                                    case 200:
                                                                        return "ICONST";
                                                                    case 201:
                                                                        return "LCONST";
                                                                    case 202:
                                                                        return "FCONST";
                                                                    case 203:
                                                                        return "DCONST";
                                                                    case 204:
                                                                        return "INVOKESTATIC_ITF";
                                                                    case 205:
                                                                        return "INVOKEDIRECT";
                                                                    case 206:
                                                                        return "INVOKEDIRECT_ITF";
                                                                    case 207:
                                                                        return "INVOKESUPER";
                                                                    case 208:
                                                                        return "INVOKESUPER_ITF";
                                                                    case 209:
                                                                        return "DEBUGPOS";
                                                                    case 210:
                                                                        return "PHI";
                                                                    case 211:
                                                                        return "FALLTHROUGH";
                                                                    case 212:
                                                                        return "MOVEEXCEPTION";
                                                                    case 213:
                                                                        return "DEBUGLOCALWRITE";
                                                                    case 214:
                                                                        return "NEWARRAYFILLED";
                                                                    case 215:
                                                                        return "NEWARRAYFILLEDDATA";
                                                                    case 216:
                                                                        return "ITEMBASEDCONSTSTRING";
                                                                    case 217:
                                                                        return "NEWUNBOXEDENUMINSTANCE";
                                                                    case 218:
                                                                        return "INOT";
                                                                    case 219:
                                                                        return "LNOT";
                                                                    case 220:
                                                                        return "DEBUGLOCALREAD";
                                                                    case 221:
                                                                        return "INITCLASS";
                                                                    case 222:
                                                                        return "INVOKEPOLYMORPHIC";
                                                                    case 223:
                                                                        return "RECORDFIELDVALUES";
                                                                    case 224:
                                                                        return "CHECKCAST_SAFE";
                                                                    case 225:
                                                                        return "CHECKCAST_IGNORE_COMPAT";
                                                                    case 226:
                                                                        return "CONSTCLASS_IGNORE_COMPAT";
                                                                    case 227:
                                                                        return "STRINGSWITCH";
                                                                    case 228:
                                                                        return "RESOURCENUMBER";
                                                                    default:
                                                                        exe.a("Unexpected LIR opcode: ", i);
                                                                        return null;
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    public final void a(Object obj, KK kk) {
        int i = this.c;
        int[] iArr = kk.b;
        this.c = (iArr.length * 4) + 4 + (iArr.length * 9) + (iArr.length * 4) + 7 + i;
    }

    /* JADX WARN: Code duplicated, block: B:45:0x0046 A[FALL_THROUGH, RETURN] */
    public final int b(int i, ZK zk) {
        if (i == 18) {
            return 2;
        }
        if (i != 170) {
            switch (i) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    return 1;
                default:
                    switch (i) {
                        default:
                            switch (i) {
                                default:
                                    switch (i) {
                                        case OPCode.SET_OPTION_PUSH /* 96 */:
                                        case OPCode.SET_OPTION /* 97 */:
                                        case OPCode.EXACT1_IC_SB /* 98 */:
                                        case OPCode.EXACTN_IC_SB /* 99 */:
                                        case 100:
                                        case 101:
                                        case 102:
                                        case 103:
                                        case 104:
                                        case 105:
                                        case 106:
                                        case 107:
                                        case 108:
                                        case 109:
                                        case 110:
                                        case 111:
                                        case 112:
                                        case 113:
                                        case 114:
                                        case 115:
                                            break;
                                        case 116:
                                        case 117:
                                        case 118:
                                        case 119:
                                            return 1;
                                        case 120:
                                        case 121:
                                        case 122:
                                        case 123:
                                        case 124:
                                        case 125:
                                        case 126:
                                        case 127:
                                        case 128:
                                        case ImmediateTags.BLOCK_TYPE_NULL_VALUE /* 129 */:
                                        case 130:
                                        case 131:
                                            return 2;
                                        default:
                                            switch (i) {
                                                case 133:
                                                case 134:
                                                case 135:
                                                case 136:
                                                case 137:
                                                case 138:
                                                case 139:
                                                case 140:
                                                case 141:
                                                case 142:
                                                case 143:
                                                case 144:
                                                case 145:
                                                case 146:
                                                case 147:
                                                    return 1;
                                                case 148:
                                                case 149:
                                                case 150:
                                                case 151:
                                                case 152:
                                                case 153:
                                                case 154:
                                                case 155:
                                                case 156:
                                                case 157:
                                                case 158:
                                                case 159:
                                                case 160:
                                                case 161:
                                                case 162:
                                                case 163:
                                                case 164:
                                                case 165:
                                                case 166:
                                                    return 2;
                                                default:
                                                    switch (i) {
                                                        case 176:
                                                        case 177:
                                                            break;
                                                        case 178:
                                                        case 179:
                                                        case 180:
                                                        case 181:
                                                            return 2;
                                                        case 182:
                                                        case 183:
                                                        case 184:
                                                        case 185:
                                                        case 186:
                                                            return 3;
                                                        case 187:
                                                        case 188:
                                                            return 2;
                                                        default:
                                                            switch (i) {
                                                                case 190:
                                                                case 191:
                                                                    return 1;
                                                                case 192:
                                                                case 193:
                                                                    return 2;
                                                                case 194:
                                                                case 195:
                                                                    return 1;
                                                                default:
                                                                    switch (i) {
                                                                        case 197:
                                                                            return 3;
                                                                        case 198:
                                                                        case 199:
                                                                            return 1;
                                                                        case 200:
                                                                        case 201:
                                                                        case 202:
                                                                        case 203:
                                                                            return 2;
                                                                        case 204:
                                                                        case 205:
                                                                        case 206:
                                                                        case 207:
                                                                        case 208:
                                                                            return 3;
                                                                        case 209:
                                                                            return 0;
                                                                        case 210:
                                                                            return 1;
                                                                        case 211:
                                                                            return 0;
                                                                        case 212:
                                                                        case 213:
                                                                            return 1;
                                                                        case 214:
                                                                            return 3;
                                                                        case 215:
                                                                        case 227:
                                                                            break;
                                                                        case 216:
                                                                        case 217:
                                                                            return 2;
                                                                        case 218:
                                                                        case 219:
                                                                            return 1;
                                                                        case 220:
                                                                            return 0;
                                                                        case 221:
                                                                            return 2;
                                                                        case 222:
                                                                            return 3;
                                                                        case 223:
                                                                        case 224:
                                                                        case 225:
                                                                        case 226:
                                                                            return 2;
                                                                        case 228:
                                                                            return 1;
                                                                        default:
                                                                            exe.a("Unexpected LIR opcode: ", i);
                                                                            return 0;
                                                                    }
                                                                    break;
                                                            }
                                                            break;
                                                    }
                                                case 167:
                                                    return 1;
                                            }
                                            break;
                                    }
                                case OPCode.FAIL_POS /* 79 */:
                                case OPCode.PUSH_STOP_BT /* 80 */:
                                case OPCode.POP_STOP_BT /* 81 */:
                                case OPCode.LOOK_BEHIND /* 82 */:
                                case OPCode.PUSH_LOOK_BEHIND_NOT /* 83 */:
                                case OPCode.FAIL_LOOK_BEHIND_NOT /* 84 */:
                                case OPCode.PUSH_ABSENT_POS /* 85 */:
                                case OPCode.ABSENT /* 86 */:
                                    return 2;
                            }
                        case OPCode.BACKREF1 /* 46 */:
                        case OPCode.BACKREF2 /* 47 */:
                        case OPCode.BACKREFN /* 48 */:
                        case OPCode.BACKREFN_IC /* 49 */:
                        case OPCode.BACKREF_MULTI /* 50 */:
                        case OPCode.BACKREF_MULTI_IC /* 51 */:
                        case OPCode.BACKREF_WITH_LEVEL /* 52 */:
                        case OPCode.MEMORY_START /* 53 */:
                            return 2;
                    }
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    return 2;
            }
        }
        super.a(zk);
        return 0;
    }

    public final void a(Object obj, HK hk) {
        this.c = (hk.b.length * 4) + 4 + this.c;
    }

    public final void a(ZK zk) {
        this.c = b(zk.b(), zk) + this.c;
    }

    public final void a(int i, long j, short[] sArr, Object obj) {
        this.c = sArr.length + 8 + this.c;
    }

    public final int b() {
        return 0;
    }
}
