package com.sun.tools.javac.comp;

import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.jvm.ByteCodes;
import com.sun.tools.javac.util.Context;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class ConstFold {
    private Symtab syms;
    protected static final Context.Key<ConstFold> constFoldKey = new Context.Key<>();
    static final Integer minusOne = -1;
    static final Integer zero = 0;
    static final Integer one = 1;

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.ConstFold$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;

        static {
            int[] iArr = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr;
            try {
                iArr[TypeTag.BYTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CHAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.SHORT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.INT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.LONG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.FLOAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.DOUBLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private ConstFold(Context context) {
        context.put(constFoldKey, this);
        this.syms = Symtab.instance(context);
    }

    private static Integer b2i(boolean z) {
        return z ? one : zero;
    }

    private static double doubleValue(Object obj) {
        return ((Number) obj).doubleValue();
    }

    private static float floatValue(Object obj) {
        return ((Number) obj).floatValue();
    }

    public static ConstFold instance(Context context) {
        ConstFold constFold = (ConstFold) context.get(constFoldKey);
        return constFold == null ? new ConstFold(context) : constFold;
    }

    private static int intValue(Object obj) {
        return ((Number) obj).intValue();
    }

    private static long longValue(Object obj) {
        return ((Number) obj).longValue();
    }

    public Type coerce(Type type, Type type2) {
        if (type.tsym.type == type2.tsym.type) {
            return type;
        }
        if (type.isNumeric()) {
            Object objConstValue = type.constValue();
            switch (AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[type2.getTag().ordinal()]) {
                case 1:
                    return this.syms.byteType.constType(Integer.valueOf((byte) intValue(objConstValue)));
                case 2:
                    return this.syms.charType.constType(Integer.valueOf((char) intValue(objConstValue)));
                case 3:
                    return this.syms.shortType.constType(Integer.valueOf((short) intValue(objConstValue)));
                case 4:
                    return this.syms.intType.constType(Integer.valueOf(intValue(objConstValue)));
                case 5:
                    return this.syms.longType.constType(Long.valueOf(longValue(objConstValue)));
                case 6:
                    return this.syms.floatType.constType(Float.valueOf(floatValue(objConstValue)));
                case 7:
                    return this.syms.doubleType.constType(Double.valueOf(doubleValue(objConstValue)));
            }
        }
        return type2;
    }

    public Type fold1(int i, Type type) {
        try {
            Object objConstValue = type.constValue();
            if (i == 0) {
                return type;
            }
            if (i == 257) {
                return this.syms.booleanType.constType(b2i(intValue(objConstValue) == 0));
            }
            if (i == 130) {
                return this.syms.intType.constType(Integer.valueOf(~intValue(objConstValue)));
            }
            if (i == 131) {
                return this.syms.longType.constType(Long.valueOf(~longValue(objConstValue)));
            }
            switch (i) {
                case 116:
                    return this.syms.intType.constType(Integer.valueOf(-intValue(objConstValue)));
                case 117:
                    return this.syms.longType.constType(Long.valueOf(-longValue(objConstValue)));
                case 118:
                    return this.syms.floatType.constType(Float.valueOf(-floatValue(objConstValue)));
                case 119:
                    return this.syms.doubleType.constType(Double.valueOf(-doubleValue(objConstValue)));
                default:
                    switch (i) {
                        case 153:
                            return this.syms.booleanType.constType(b2i(intValue(objConstValue) == 0));
                        case 154:
                            return this.syms.booleanType.constType(b2i(intValue(objConstValue) != 0));
                        case 155:
                            return this.syms.booleanType.constType(b2i(intValue(objConstValue) < 0));
                        case 156:
                            return this.syms.booleanType.constType(b2i(intValue(objConstValue) >= 0));
                        case 157:
                            return this.syms.booleanType.constType(b2i(intValue(objConstValue) > 0));
                        case 158:
                            return this.syms.booleanType.constType(b2i(intValue(objConstValue) <= 0));
                        default:
                            return null;
                    }
            }
        } catch (ArithmeticException unused) {
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:132:0x029d A[Catch: ArithmeticException -> 0x0515, TryCatch #0 {ArithmeticException -> 0x0515, blocks: (B:5:0x0007, B:8:0x0012, B:10:0x0018, B:16:0x002e, B:17:0x0031, B:18:0x0034, B:19:0x0037, B:20:0x003a, B:22:0x003e, B:24:0x0050, B:26:0x0061, B:29:0x0070, B:31:0x0079, B:34:0x0088, B:36:0x0091, B:39:0x00a0, B:41:0x00a9, B:44:0x00b8, B:46:0x00c1, B:49:0x00d0, B:51:0x00d9, B:54:0x00e8, B:56:0x00f1, B:58:0x00fd, B:60:0x0108, B:62:0x0114, B:64:0x011f, B:66:0x012b, B:70:0x013c, B:72:0x0145, B:74:0x014e, B:76:0x015a, B:78:0x0165, B:80:0x0171, B:82:0x017c, B:84:0x0188, B:88:0x0199, B:90:0x01a2, B:92:0x01ab, B:94:0x01b7, B:96:0x01c2, B:99:0x01d0, B:101:0x01d9, B:103:0x01e2, B:105:0x01f8, B:108:0x0202, B:110:0x0207, B:109:0x0205, B:112:0x0219, B:114:0x022f, B:117:0x0239, B:119:0x023e, B:118:0x023c, B:121:0x0250, B:123:0x0266, B:126:0x0270, B:128:0x0275, B:127:0x0273, B:130:0x0287, B:132:0x029d, B:134:0x02b3, B:136:0x02c9, B:138:0x02df, B:140:0x02f5, B:142:0x030b, B:144:0x0321, B:146:0x0337, B:148:0x034d, B:150:0x0363, B:152:0x0379, B:154:0x038f, B:156:0x03a5, B:158:0x03bb, B:160:0x03d1, B:162:0x03e7, B:164:0x03fd, B:166:0x0413, B:168:0x0429, B:170:0x043f, B:172:0x0455, B:174:0x046b, B:176:0x0481, B:178:0x0497, B:180:0x04ad, B:182:0x04c3, B:185:0x04d3, B:187:0x04dc, B:190:0x04ec, B:192:0x04f5), top: B:195:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:134:0x02b3 A[Catch: ArithmeticException -> 0x0515, TryCatch #0 {ArithmeticException -> 0x0515, blocks: (B:5:0x0007, B:8:0x0012, B:10:0x0018, B:16:0x002e, B:17:0x0031, B:18:0x0034, B:19:0x0037, B:20:0x003a, B:22:0x003e, B:24:0x0050, B:26:0x0061, B:29:0x0070, B:31:0x0079, B:34:0x0088, B:36:0x0091, B:39:0x00a0, B:41:0x00a9, B:44:0x00b8, B:46:0x00c1, B:49:0x00d0, B:51:0x00d9, B:54:0x00e8, B:56:0x00f1, B:58:0x00fd, B:60:0x0108, B:62:0x0114, B:64:0x011f, B:66:0x012b, B:70:0x013c, B:72:0x0145, B:74:0x014e, B:76:0x015a, B:78:0x0165, B:80:0x0171, B:82:0x017c, B:84:0x0188, B:88:0x0199, B:90:0x01a2, B:92:0x01ab, B:94:0x01b7, B:96:0x01c2, B:99:0x01d0, B:101:0x01d9, B:103:0x01e2, B:105:0x01f8, B:108:0x0202, B:110:0x0207, B:109:0x0205, B:112:0x0219, B:114:0x022f, B:117:0x0239, B:119:0x023e, B:118:0x023c, B:121:0x0250, B:123:0x0266, B:126:0x0270, B:128:0x0275, B:127:0x0273, B:130:0x0287, B:132:0x029d, B:134:0x02b3, B:136:0x02c9, B:138:0x02df, B:140:0x02f5, B:142:0x030b, B:144:0x0321, B:146:0x0337, B:148:0x034d, B:150:0x0363, B:152:0x0379, B:154:0x038f, B:156:0x03a5, B:158:0x03bb, B:160:0x03d1, B:162:0x03e7, B:164:0x03fd, B:166:0x0413, B:168:0x0429, B:170:0x043f, B:172:0x0455, B:174:0x046b, B:176:0x0481, B:178:0x0497, B:180:0x04ad, B:182:0x04c3, B:185:0x04d3, B:187:0x04dc, B:190:0x04ec, B:192:0x04f5), top: B:195:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:136:0x02c9 A[Catch: ArithmeticException -> 0x0515, TryCatch #0 {ArithmeticException -> 0x0515, blocks: (B:5:0x0007, B:8:0x0012, B:10:0x0018, B:16:0x002e, B:17:0x0031, B:18:0x0034, B:19:0x0037, B:20:0x003a, B:22:0x003e, B:24:0x0050, B:26:0x0061, B:29:0x0070, B:31:0x0079, B:34:0x0088, B:36:0x0091, B:39:0x00a0, B:41:0x00a9, B:44:0x00b8, B:46:0x00c1, B:49:0x00d0, B:51:0x00d9, B:54:0x00e8, B:56:0x00f1, B:58:0x00fd, B:60:0x0108, B:62:0x0114, B:64:0x011f, B:66:0x012b, B:70:0x013c, B:72:0x0145, B:74:0x014e, B:76:0x015a, B:78:0x0165, B:80:0x0171, B:82:0x017c, B:84:0x0188, B:88:0x0199, B:90:0x01a2, B:92:0x01ab, B:94:0x01b7, B:96:0x01c2, B:99:0x01d0, B:101:0x01d9, B:103:0x01e2, B:105:0x01f8, B:108:0x0202, B:110:0x0207, B:109:0x0205, B:112:0x0219, B:114:0x022f, B:117:0x0239, B:119:0x023e, B:118:0x023c, B:121:0x0250, B:123:0x0266, B:126:0x0270, B:128:0x0275, B:127:0x0273, B:130:0x0287, B:132:0x029d, B:134:0x02b3, B:136:0x02c9, B:138:0x02df, B:140:0x02f5, B:142:0x030b, B:144:0x0321, B:146:0x0337, B:148:0x034d, B:150:0x0363, B:152:0x0379, B:154:0x038f, B:156:0x03a5, B:158:0x03bb, B:160:0x03d1, B:162:0x03e7, B:164:0x03fd, B:166:0x0413, B:168:0x0429, B:170:0x043f, B:172:0x0455, B:174:0x046b, B:176:0x0481, B:178:0x0497, B:180:0x04ad, B:182:0x04c3, B:185:0x04d3, B:187:0x04dc, B:190:0x04ec, B:192:0x04f5), top: B:195:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:138:0x02df A[Catch: ArithmeticException -> 0x0515, TryCatch #0 {ArithmeticException -> 0x0515, blocks: (B:5:0x0007, B:8:0x0012, B:10:0x0018, B:16:0x002e, B:17:0x0031, B:18:0x0034, B:19:0x0037, B:20:0x003a, B:22:0x003e, B:24:0x0050, B:26:0x0061, B:29:0x0070, B:31:0x0079, B:34:0x0088, B:36:0x0091, B:39:0x00a0, B:41:0x00a9, B:44:0x00b8, B:46:0x00c1, B:49:0x00d0, B:51:0x00d9, B:54:0x00e8, B:56:0x00f1, B:58:0x00fd, B:60:0x0108, B:62:0x0114, B:64:0x011f, B:66:0x012b, B:70:0x013c, B:72:0x0145, B:74:0x014e, B:76:0x015a, B:78:0x0165, B:80:0x0171, B:82:0x017c, B:84:0x0188, B:88:0x0199, B:90:0x01a2, B:92:0x01ab, B:94:0x01b7, B:96:0x01c2, B:99:0x01d0, B:101:0x01d9, B:103:0x01e2, B:105:0x01f8, B:108:0x0202, B:110:0x0207, B:109:0x0205, B:112:0x0219, B:114:0x022f, B:117:0x0239, B:119:0x023e, B:118:0x023c, B:121:0x0250, B:123:0x0266, B:126:0x0270, B:128:0x0275, B:127:0x0273, B:130:0x0287, B:132:0x029d, B:134:0x02b3, B:136:0x02c9, B:138:0x02df, B:140:0x02f5, B:142:0x030b, B:144:0x0321, B:146:0x0337, B:148:0x034d, B:150:0x0363, B:152:0x0379, B:154:0x038f, B:156:0x03a5, B:158:0x03bb, B:160:0x03d1, B:162:0x03e7, B:164:0x03fd, B:166:0x0413, B:168:0x0429, B:170:0x043f, B:172:0x0455, B:174:0x046b, B:176:0x0481, B:178:0x0497, B:180:0x04ad, B:182:0x04c3, B:185:0x04d3, B:187:0x04dc, B:190:0x04ec, B:192:0x04f5), top: B:195:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:140:0x02f5 A[Catch: ArithmeticException -> 0x0515, TryCatch #0 {ArithmeticException -> 0x0515, blocks: (B:5:0x0007, B:8:0x0012, B:10:0x0018, B:16:0x002e, B:17:0x0031, B:18:0x0034, B:19:0x0037, B:20:0x003a, B:22:0x003e, B:24:0x0050, B:26:0x0061, B:29:0x0070, B:31:0x0079, B:34:0x0088, B:36:0x0091, B:39:0x00a0, B:41:0x00a9, B:44:0x00b8, B:46:0x00c1, B:49:0x00d0, B:51:0x00d9, B:54:0x00e8, B:56:0x00f1, B:58:0x00fd, B:60:0x0108, B:62:0x0114, B:64:0x011f, B:66:0x012b, B:70:0x013c, B:72:0x0145, B:74:0x014e, B:76:0x015a, B:78:0x0165, B:80:0x0171, B:82:0x017c, B:84:0x0188, B:88:0x0199, B:90:0x01a2, B:92:0x01ab, B:94:0x01b7, B:96:0x01c2, B:99:0x01d0, B:101:0x01d9, B:103:0x01e2, B:105:0x01f8, B:108:0x0202, B:110:0x0207, B:109:0x0205, B:112:0x0219, B:114:0x022f, B:117:0x0239, B:119:0x023e, B:118:0x023c, B:121:0x0250, B:123:0x0266, B:126:0x0270, B:128:0x0275, B:127:0x0273, B:130:0x0287, B:132:0x029d, B:134:0x02b3, B:136:0x02c9, B:138:0x02df, B:140:0x02f5, B:142:0x030b, B:144:0x0321, B:146:0x0337, B:148:0x034d, B:150:0x0363, B:152:0x0379, B:154:0x038f, B:156:0x03a5, B:158:0x03bb, B:160:0x03d1, B:162:0x03e7, B:164:0x03fd, B:166:0x0413, B:168:0x0429, B:170:0x043f, B:172:0x0455, B:174:0x046b, B:176:0x0481, B:178:0x0497, B:180:0x04ad, B:182:0x04c3, B:185:0x04d3, B:187:0x04dc, B:190:0x04ec, B:192:0x04f5), top: B:195:0x0003 }] */
    public Type fold2(int i, Type type, Type type2) {
        try {
            if (i > 511) {
                Type typeFold2 = fold2(i >> 9, type, type2);
                return typeFold2.constValue() == null ? typeFold2 : fold1(i & 511, typeFold2);
            }
            Object objConstValue = type.constValue();
            Object objConstValue2 = type2.constValue();
            if (i == 256) {
                return this.syms.stringType.constType(type.stringValue() + type2.stringValue());
            }
            if (i == 258) {
                return this.syms.booleanType.constType(b2i((intValue(objConstValue) & intValue(objConstValue2)) != 0));
            }
            if (i == 259) {
                return this.syms.booleanType.constType(b2i((intValue(objConstValue) | intValue(objConstValue2)) != 0));
            }
            switch (i) {
                case 96:
                    return this.syms.intType.constType(Integer.valueOf(intValue(objConstValue) + intValue(objConstValue2)));
                case 97:
                    return this.syms.longType.constType(Long.valueOf(longValue(objConstValue) + longValue(objConstValue2)));
                case 98:
                    return this.syms.floatType.constType(Float.valueOf(floatValue(objConstValue) + floatValue(objConstValue2)));
                case 99:
                    return this.syms.doubleType.constType(Double.valueOf(doubleValue(objConstValue) + doubleValue(objConstValue2)));
                case 100:
                    return this.syms.intType.constType(Integer.valueOf(intValue(objConstValue) - intValue(objConstValue2)));
                case 101:
                    return this.syms.longType.constType(Long.valueOf(longValue(objConstValue) - longValue(objConstValue2)));
                case 102:
                    return this.syms.floatType.constType(Float.valueOf(floatValue(objConstValue) - floatValue(objConstValue2)));
                case 103:
                    return this.syms.doubleType.constType(Double.valueOf(doubleValue(objConstValue) - doubleValue(objConstValue2)));
                case 104:
                    return this.syms.intType.constType(Integer.valueOf(intValue(objConstValue) * intValue(objConstValue2)));
                case 105:
                    return this.syms.longType.constType(Long.valueOf(longValue(objConstValue) * longValue(objConstValue2)));
                case 106:
                    return this.syms.floatType.constType(Float.valueOf(floatValue(objConstValue) * floatValue(objConstValue2)));
                case 107:
                    return this.syms.doubleType.constType(Double.valueOf(doubleValue(objConstValue) * doubleValue(objConstValue2)));
                case 108:
                    return this.syms.intType.constType(Integer.valueOf(intValue(objConstValue) / intValue(objConstValue2)));
                case 109:
                    return this.syms.longType.constType(Long.valueOf(longValue(objConstValue) / longValue(objConstValue2)));
                case 110:
                    return this.syms.floatType.constType(Float.valueOf(floatValue(objConstValue) / floatValue(objConstValue2)));
                case 111:
                    return this.syms.doubleType.constType(Double.valueOf(doubleValue(objConstValue) / doubleValue(objConstValue2)));
                case 112:
                    return this.syms.intType.constType(Integer.valueOf(intValue(objConstValue) % intValue(objConstValue2)));
                case 113:
                    return this.syms.longType.constType(Long.valueOf(longValue(objConstValue) % longValue(objConstValue2)));
                case 114:
                    return this.syms.floatType.constType(Float.valueOf(floatValue(objConstValue) % floatValue(objConstValue2)));
                case 115:
                    return this.syms.doubleType.constType(Double.valueOf(doubleValue(objConstValue) % doubleValue(objConstValue2)));
                default:
                    switch (i) {
                        case 120:
                            return this.syms.intType.constType(Integer.valueOf(intValue(objConstValue) << intValue(objConstValue2)));
                        case 121:
                            return this.syms.longType.constType(Long.valueOf(longValue(objConstValue) << intValue(objConstValue2)));
                        case 122:
                            return this.syms.intType.constType(Integer.valueOf(intValue(objConstValue) >> intValue(objConstValue2)));
                        case 123:
                            return this.syms.longType.constType(Long.valueOf(longValue(objConstValue) >> intValue(objConstValue2)));
                        case 124:
                            return this.syms.intType.constType(Integer.valueOf(intValue(objConstValue) >>> intValue(objConstValue2)));
                        case 125:
                            return this.syms.longType.constType(Long.valueOf(longValue(objConstValue) >>> intValue(objConstValue2)));
                        case 126:
                            boolean zHasTag = type.hasTag(TypeTag.BOOLEAN);
                            Symtab symtab = this.syms;
                            return (zHasTag ? symtab.booleanType : symtab.intType).constType(Integer.valueOf(intValue(objConstValue) & intValue(objConstValue2)));
                        case 127:
                            return this.syms.longType.constType(Long.valueOf(longValue(objConstValue) & longValue(objConstValue2)));
                        case 128:
                            boolean zHasTag2 = type.hasTag(TypeTag.BOOLEAN);
                            Symtab symtab2 = this.syms;
                            return (zHasTag2 ? symtab2.booleanType : symtab2.intType).constType(Integer.valueOf(intValue(objConstValue) | intValue(objConstValue2)));
                        case 129:
                            return this.syms.longType.constType(Long.valueOf(longValue(objConstValue) | longValue(objConstValue2)));
                        case 130:
                            boolean zHasTag3 = type.hasTag(TypeTag.BOOLEAN);
                            Symtab symtab3 = this.syms;
                            return (zHasTag3 ? symtab3.booleanType : symtab3.intType).constType(Integer.valueOf(intValue(objConstValue) ^ intValue(objConstValue2)));
                        case 131:
                            return this.syms.longType.constType(Long.valueOf(longValue(objConstValue) ^ longValue(objConstValue2)));
                        default:
                            switch (i) {
                                case 148:
                                    if (longValue(objConstValue) < longValue(objConstValue2)) {
                                        return this.syms.intType.constType(minusOne);
                                    }
                                    long jLongValue = longValue(objConstValue);
                                    long jLongValue2 = longValue(objConstValue2);
                                    Symtab symtab4 = this.syms;
                                    return jLongValue > jLongValue2 ? symtab4.intType.constType(one) : symtab4.intType.constType(zero);
                                case 149:
                                case 150:
                                    if (floatValue(objConstValue) < floatValue(objConstValue2)) {
                                        return this.syms.intType.constType(minusOne);
                                    }
                                    if (floatValue(objConstValue) > floatValue(objConstValue2)) {
                                        return this.syms.intType.constType(one);
                                    }
                                    if (floatValue(objConstValue) == floatValue(objConstValue2)) {
                                        return this.syms.intType.constType(zero);
                                    }
                                    Symtab symtab5 = this.syms;
                                    return i == 150 ? symtab5.intType.constType(one) : symtab5.intType.constType(minusOne);
                                case 151:
                                case 152:
                                    if (doubleValue(objConstValue) < doubleValue(objConstValue2)) {
                                        return this.syms.intType.constType(minusOne);
                                    }
                                    if (doubleValue(objConstValue) > doubleValue(objConstValue2)) {
                                        return this.syms.intType.constType(one);
                                    }
                                    if (doubleValue(objConstValue) == doubleValue(objConstValue2)) {
                                        return this.syms.intType.constType(zero);
                                    }
                                    Symtab symtab6 = this.syms;
                                    return i == 152 ? symtab6.intType.constType(one) : symtab6.intType.constType(minusOne);
                                default:
                                    switch (i) {
                                        case 159:
                                            return this.syms.booleanType.constType(b2i(intValue(objConstValue) == intValue(objConstValue2)));
                                        case 160:
                                            return this.syms.booleanType.constType(b2i(intValue(objConstValue) != intValue(objConstValue2)));
                                        case 161:
                                            return this.syms.booleanType.constType(b2i(intValue(objConstValue) < intValue(objConstValue2)));
                                        case 162:
                                            return this.syms.booleanType.constType(b2i(intValue(objConstValue) >= intValue(objConstValue2)));
                                        case 163:
                                            return this.syms.booleanType.constType(b2i(intValue(objConstValue) > intValue(objConstValue2)));
                                        case 164:
                                            return this.syms.booleanType.constType(b2i(intValue(objConstValue) <= intValue(objConstValue2)));
                                        case 165:
                                            return this.syms.booleanType.constType(b2i(objConstValue.equals(objConstValue2)));
                                        case 166:
                                            return this.syms.booleanType.constType(b2i(!objConstValue.equals(objConstValue2)));
                                        default:
                                            switch (i) {
                                                case ByteCodes.ishll /* 270 */:
                                                    return this.syms.intType.constType(Integer.valueOf(intValue(objConstValue) << intValue(objConstValue2)));
                                                case ByteCodes.lshll /* 271 */:
                                                    return this.syms.longType.constType(Long.valueOf(longValue(objConstValue) << intValue(objConstValue2)));
                                                case ByteCodes.ishrl /* 272 */:
                                                    return this.syms.intType.constType(Integer.valueOf(intValue(objConstValue) >> intValue(objConstValue2)));
                                                case ByteCodes.lshrl /* 273 */:
                                                    return this.syms.longType.constType(Long.valueOf(longValue(objConstValue) >> intValue(objConstValue2)));
                                                case 274:
                                                    return this.syms.intType.constType(Integer.valueOf(intValue(objConstValue) >>> intValue(objConstValue2)));
                                                default:
                                                    return null;
                                            }
                                    }
                            }
                    }
            }
        } catch (ArithmeticException unused) {
            return null;
        }
    }
}
