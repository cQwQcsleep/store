package org.jetbrains.org.objectweb.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public final class Type {
    public static final int ARRAY = 9;
    public static final int BOOLEAN = 1;
    public static final int BYTE = 3;
    public static final int CHAR = 2;
    public static final int DOUBLE = 8;
    public static final int FLOAT = 6;
    public static final int INT = 5;
    public static final int LONG = 7;
    public static final int METHOD = 11;
    public static final int OBJECT = 10;
    public static final int SHORT = 4;
    public static final int VOID = 0;
    private final int sort;
    private final int valueBegin;
    private final String valueBuffer;
    private final int valueEnd;
    public static final Type VOID_TYPE = new Type(0, "VZCBSIFJD", 0, 1);
    public static final Type BOOLEAN_TYPE = new Type(1, "VZCBSIFJD", 1, 2);
    public static final Type CHAR_TYPE = new Type(2, "VZCBSIFJD", 2, 3);
    public static final Type BYTE_TYPE = new Type(3, "VZCBSIFJD", 3, 4);
    public static final Type SHORT_TYPE = new Type(4, "VZCBSIFJD", 4, 5);
    public static final Type INT_TYPE = new Type(5, "VZCBSIFJD", 5, 6);
    public static final Type FLOAT_TYPE = new Type(6, "VZCBSIFJD", 6, 7);
    public static final Type LONG_TYPE = new Type(7, "VZCBSIFJD", 7, 8);
    public static final Type DOUBLE_TYPE = new Type(8, "VZCBSIFJD", 8, 9);

    private Type(int i, String str, int i2, int i3) {
        this.sort = i;
        this.valueBuffer = str;
        this.valueBegin = i2;
        this.valueEnd = i3;
    }

    private static void appendDescriptor(Class<?> cls, StringBuilder sb) {
        char c;
        while (cls.isArray()) {
            sb.append('[');
            cls = cls.getComponentType();
        }
        if (!cls.isPrimitive()) {
            sb.append('L');
            sb.append(getInternalName(cls));
            sb.append(';');
            return;
        }
        if (cls == Integer.TYPE) {
            c = 'I';
        } else if (cls == Void.TYPE) {
            c = 'V';
        } else if (cls == Boolean.TYPE) {
            c = 'Z';
        } else if (cls == Byte.TYPE) {
            c = 'B';
        } else if (cls == Character.TYPE) {
            c = 'C';
        } else if (cls == Short.TYPE) {
            c = 'S';
        } else if (cls == Double.TYPE) {
            c = 'D';
        } else if (cls == Float.TYPE) {
            c = 'F';
        } else {
            if (cls != Long.TYPE) {
                x1f.a();
                return;
            }
            c = 'J';
        }
        sb.append(c);
    }

    public static int getArgumentCount(String str) {
        int i = 0;
        int iMax = 1;
        while (str.charAt(iMax) != ')') {
            while (str.charAt(iMax) == '[') {
                iMax++;
            }
            int i2 = iMax + 1;
            iMax = str.charAt(iMax) == 'L' ? Math.max(i2, str.indexOf(59, i2) + 1) : i2;
            i++;
        }
        return i;
    }

    public static Type[] getArgumentTypes(String str) {
        Type[] typeArr = new Type[getArgumentCount(str)];
        int i = 0;
        int i2 = 1;
        while (str.charAt(i2) != ')') {
            int i3 = i2;
            while (str.charAt(i3) == '[') {
                i3++;
            }
            int iMax = i3 + 1;
            if (str.charAt(i3) == 'L') {
                iMax = Math.max(iMax, str.indexOf(59, iMax) + 1);
            }
            typeArr[i] = getTypeInternal(str, i2, iMax);
            i++;
            i2 = iMax;
        }
        return typeArr;
    }

    public static int getArgumentsAndReturnSizes(String str) {
        char cCharAt = str.charAt(1);
        int i = 1;
        int i2 = 1;
        while (cCharAt != ')') {
            if (cCharAt == 'J' || cCharAt == 'D') {
                i++;
                i2 += 2;
            } else {
                while (str.charAt(i) == '[') {
                    i++;
                }
                int iMax = i + 1;
                if (str.charAt(i) == 'L') {
                    iMax = Math.max(iMax, str.indexOf(59, iMax) + 1);
                }
                i2++;
                i = iMax;
            }
            cCharAt = str.charAt(i);
        }
        char cCharAt2 = str.charAt(i + 1);
        if (cCharAt2 == 'V') {
            return i2 << 2;
        }
        return (i2 << 2) | ((cCharAt2 == 'J' || cCharAt2 == 'D') ? 2 : 1);
    }

    public static String getConstructorDescriptor(Constructor<?> constructor) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (Class<?> cls : constructor.getParameterTypes()) {
            appendDescriptor(cls, sb);
        }
        sb.append(")V");
        return sb.toString();
    }

    public static String getInternalName(Class<?> cls) {
        return cls.getName().replace('.', '/');
    }

    public static String getMethodDescriptor(Method method) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (Class<?> cls : method.getParameterTypes()) {
            appendDescriptor(cls, sb);
        }
        sb.append(')');
        appendDescriptor(method.getReturnType(), sb);
        return sb.toString();
    }

    public static Type getMethodType(String str) {
        return new Type(11, str, 0, str.length());
    }

    public static Type getObjectType(String str) {
        return new Type(str.charAt(0) == '[' ? 9 : 12, str, 0, str.length());
    }

    public static Type getReturnType(String str) {
        return getTypeInternal(str, getReturnTypeOffset(str), str.length());
    }

    public static int getReturnTypeOffset(String str) {
        int iMax = 1;
        while (str.charAt(iMax) != ')') {
            while (str.charAt(iMax) == '[') {
                iMax++;
            }
            int i = iMax + 1;
            iMax = str.charAt(iMax) == 'L' ? Math.max(i, str.indexOf(59, i) + 1) : i;
        }
        return iMax + 1;
    }

    public static Type getType(Class<?> cls) {
        if (!cls.isPrimitive()) {
            return getType(getDescriptor(cls));
        }
        if (cls == Integer.TYPE) {
            return INT_TYPE;
        }
        if (cls == Void.TYPE) {
            return VOID_TYPE;
        }
        if (cls == Boolean.TYPE) {
            return BOOLEAN_TYPE;
        }
        if (cls == Byte.TYPE) {
            return BYTE_TYPE;
        }
        if (cls == Character.TYPE) {
            return CHAR_TYPE;
        }
        if (cls == Short.TYPE) {
            return SHORT_TYPE;
        }
        if (cls == Double.TYPE) {
            return DOUBLE_TYPE;
        }
        if (cls == Float.TYPE) {
            return FLOAT_TYPE;
        }
        if (cls == Long.TYPE) {
            return LONG_TYPE;
        }
        x1f.a();
        return null;
    }

    private static Type getTypeInternal(String str, int i, int i2) {
        char cCharAt = str.charAt(i);
        if (cCharAt == '(') {
            return new Type(11, str, i, i2);
        }
        if (cCharAt == 'F') {
            return FLOAT_TYPE;
        }
        if (cCharAt == 'L') {
            return new Type(10, str, i + 1, i2 - 1);
        }
        if (cCharAt == 'S') {
            return SHORT_TYPE;
        }
        if (cCharAt == 'V') {
            return VOID_TYPE;
        }
        if (cCharAt == 'I') {
            return INT_TYPE;
        }
        if (cCharAt == 'J') {
            return LONG_TYPE;
        }
        if (cCharAt == 'Z') {
            return BOOLEAN_TYPE;
        }
        if (cCharAt == '[') {
            return new Type(9, str, i, i2);
        }
        switch (cCharAt) {
            case OPCode.REPEAT /* 66 */:
                return BYTE_TYPE;
            case OPCode.REPEAT_NG /* 67 */:
                return CHAR_TYPE;
            case OPCode.REPEAT_INC /* 68 */:
                return DOUBLE_TYPE;
            default:
                w01.a("Invalid descriptor: ".concat(str));
                return null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Type)) {
            return false;
        }
        Type type = (Type) obj;
        int i = this.sort;
        if (i == 12) {
            i = 10;
        }
        int i2 = type.sort;
        if (i != (i2 != 12 ? i2 : 10)) {
            return false;
        }
        int i3 = this.valueBegin;
        int i4 = this.valueEnd;
        int i5 = type.valueBegin;
        if (i4 - i3 != type.valueEnd - i5) {
            return false;
        }
        while (i3 < i4) {
            if (this.valueBuffer.charAt(i3) != type.valueBuffer.charAt(i5)) {
                return false;
            }
            i3++;
            i5++;
        }
        return true;
    }

    public String getClassName() {
        switch (this.sort) {
            case 0:
                return "void";
            case 1:
                return "boolean";
            case 2:
                return "char";
            case 3:
                return "byte";
            case 4:
                return "short";
            case 5:
                return "int";
            case 6:
                return "float";
            case 7:
                return "long";
            case 8:
                return "double";
            case 9:
                StringBuilder sb = new StringBuilder(getElementType().getClassName());
                for (int dimensions = getDimensions(); dimensions > 0; dimensions--) {
                    sb.append("[]");
                }
                return sb.toString();
            case 10:
            case 12:
                return this.valueBuffer.substring(this.valueBegin, this.valueEnd).replace('/', '.');
            case 11:
            default:
                x1f.a();
                return null;
        }
    }

    public String getDescriptor() {
        int i = this.sort;
        if (i == 10) {
            return this.valueBuffer.substring(this.valueBegin - 1, this.valueEnd + 1);
        }
        String str = this.valueBuffer;
        if (i != 12) {
            return str.substring(this.valueBegin, this.valueEnd);
        }
        return "L" + str.substring(this.valueBegin, this.valueEnd) + ';';
    }

    public int getDimensions() {
        int i = 1;
        while (this.valueBuffer.charAt(this.valueBegin + i) == '[') {
            i++;
        }
        return i;
    }

    public Type getElementType() {
        return getTypeInternal(this.valueBuffer, this.valueBegin + getDimensions(), this.valueEnd);
    }

    public int getOpcode(int i) {
        if (i == 46 || i == 79) {
            switch (this.sort) {
                case 0:
                case 11:
                    a9g.a();
                    return 0;
                case 1:
                case 3:
                    return i + 5;
                case 2:
                    return i + 6;
                case 4:
                    return i + 7;
                case 5:
                    return i;
                case 6:
                    return i + 2;
                case 7:
                    return i + 1;
                case 8:
                    return i + 3;
                case 9:
                case 10:
                case 12:
                    return i + 4;
                default:
                    x1f.a();
                    return 0;
            }
        }
        switch (this.sort) {
            case 0:
                if (i == 172) {
                    return 177;
                }
                a9g.a();
                return 0;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return i;
            case 6:
                return i + 2;
            case 7:
                return i + 1;
            case 8:
                return i + 3;
            case 9:
            case 10:
            case 12:
                if (i == 21 || i == 54 || i == 172) {
                    return i + 4;
                }
                a9g.a();
                return 0;
            case 11:
                a9g.a();
                return 0;
            default:
                x1f.a();
                return 0;
        }
    }

    public int getSize() {
        switch (this.sort) {
            case 0:
                return 0;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 9:
            case 10:
            case 12:
                return 1;
            case 7:
            case 8:
                return 2;
            case 11:
            default:
                x1f.a();
                return 0;
        }
    }

    public int getSort() {
        int i = this.sort;
        if (i == 12) {
            return 10;
        }
        return i;
    }

    public int hashCode() {
        int i = this.sort;
        int iCharAt = (i == 12 ? 10 : i) * 13;
        if (i >= 9) {
            int i2 = this.valueEnd;
            for (int i3 = this.valueBegin; i3 < i2; i3++) {
                iCharAt = (iCharAt + this.valueBuffer.charAt(i3)) * 17;
            }
        }
        return iCharAt;
    }

    public String toString() {
        return getDescriptor();
    }

    public static Type getMethodType(Type type, Type... typeArr) {
        return getType(getMethodDescriptor(type, typeArr));
    }

    public String getInternalName() {
        return this.valueBuffer.substring(this.valueBegin, this.valueEnd);
    }

    public Type getReturnType() {
        return getReturnType(getDescriptor());
    }

    public static Type getReturnType(Method method) {
        return getType(method.getReturnType());
    }

    public static String getMethodDescriptor(Type type, Type... typeArr) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (Type type2 : typeArr) {
            type2.appendDescriptor(sb);
        }
        sb.append(')');
        type.appendDescriptor(sb);
        return sb.toString();
    }

    public int getArgumentCount() {
        return getArgumentCount(getDescriptor());
    }

    public Type[] getArgumentTypes() {
        return getArgumentTypes(getDescriptor());
    }

    public static Type[] getArgumentTypes(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Type[] typeArr = new Type[parameterTypes.length];
        for (int length = parameterTypes.length - 1; length >= 0; length--) {
            typeArr[length] = getType(parameterTypes[length]);
        }
        return typeArr;
    }

    public static String getDescriptor(Class<?> cls) {
        StringBuilder sb = new StringBuilder();
        appendDescriptor(cls, sb);
        return sb.toString();
    }

    public static Type getType(String str) {
        return getTypeInternal(str, 0, str.length());
    }

    public static Type getType(Constructor<?> constructor) {
        return getType(getConstructorDescriptor(constructor));
    }

    public static Type getType(Method method) {
        return getType(getMethodDescriptor(method));
    }

    public int getArgumentsAndReturnSizes() {
        return getArgumentsAndReturnSizes(getDescriptor());
    }

    private void appendDescriptor(StringBuilder sb) {
        int i = this.sort;
        if (i == 10) {
            sb.append((CharSequence) this.valueBuffer, this.valueBegin - 1, this.valueEnd + 1);
        } else {
            if (i == 12) {
                sb.append('L');
                sb.append((CharSequence) this.valueBuffer, this.valueBegin, this.valueEnd);
                sb.append(';');
                return;
            }
            sb.append((CharSequence) this.valueBuffer, this.valueBegin, this.valueEnd);
        }
    }
}
