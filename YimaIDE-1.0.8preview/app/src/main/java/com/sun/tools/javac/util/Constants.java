package com.sun.tools.javac.util;

import com.sun.org.apache.xpath.internal.XPath;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Constants {

    /* JADX INFO: renamed from: com.sun.tools.javac.util.Constants$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;

        static {
            int[] iArr = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr;
            try {
                iArr[TypeTag.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CHAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BYTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.SHORT.ordinal()] = 4;
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

    public static Object decode(Object obj, Type type) {
        if (!(obj instanceof Integer)) {
            return obj;
        }
        int iIntValue = ((Integer) obj).intValue();
        int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()];
        if (i == 1) {
            return Boolean.valueOf(iIntValue != 0);
        }
        if (i == 2) {
            return Character.valueOf((char) iIntValue);
        }
        if (i != 3) {
            return i != 4 ? obj : Short.valueOf((short) iIntValue);
        }
        return Byte.valueOf((byte) iIntValue);
    }

    public static String format(Object obj) {
        if (obj instanceof Byte) {
            return formatByte(((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return formatShort(((Short) obj).shortValue());
        }
        if (obj instanceof Long) {
            return formatLong(((Long) obj).longValue());
        }
        if (obj instanceof Float) {
            return formatFloat(((Float) obj).floatValue());
        }
        if (obj instanceof Double) {
            return formatDouble(((Double) obj).doubleValue());
        }
        if (obj instanceof Character) {
            return formatChar(((Character) obj).charValue());
        }
        if (obj instanceof String) {
            return formatString((String) obj);
        }
        if ((obj instanceof Integer) || (obj instanceof Boolean)) {
            return obj.toString();
        }
        StringBuilder sb = new StringBuilder("Argument is not a primitive type or a string; it ");
        sb.append(obj == null ? "is a null value." : "has class ".concat(obj.getClass().getName()));
        sb.append(com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_THIS);
        throw new IllegalArgumentException(sb.toString());
    }

    private static String formatByte(byte b) {
        return String.format("(byte)0x%02x", Byte.valueOf(b));
    }

    private static String formatChar(char c) {
        return "'" + Convert.quote(c, true) + '\'';
    }

    private static String formatDouble(double d) {
        if (Double.isNaN(d)) {
            return "0.0/0.0";
        }
        if (Double.isInfinite(d)) {
            return d < XPath.MATCH_SCORE_QNAME ? "-1.0/0.0" : "1.0/0.0";
        }
        return d + "";
    }

    private static String formatFloat(float f) {
        if (Float.isNaN(f)) {
            return "0.0f/0.0f";
        }
        if (Float.isInfinite(f)) {
            return f < 0.0f ? "-1.0f/0.0f" : "1.0f/0.0f";
        }
        return f + "f";
    }

    private static String formatLong(long j) {
        return j + "L";
    }

    private static String formatShort(short s) {
        return String.format("(short)%d", Short.valueOf(s));
    }

    private static String formatString(String str) {
        return "\"" + Convert.quote(str) + '\"';
    }

    public static String format(Object obj, Type type) {
        Object objDecode = decode(obj, type);
        int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()];
        if (i == 2) {
            return formatChar(((Character) objDecode).charValue());
        }
        if (i == 3) {
            return formatByte(((Byte) objDecode).byteValue());
        }
        if (i == 5) {
            return formatLong(((Long) objDecode).longValue());
        }
        if (i == 6) {
            return formatFloat(((Float) objDecode).floatValue());
        }
        if (i != 7) {
            if (objDecode instanceof String) {
                return formatString((String) objDecode);
            }
            return objDecode + "";
        }
        return formatDouble(((Double) objDecode).doubleValue());
    }
}
