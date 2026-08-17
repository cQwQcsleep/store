package com.android.tools.r8.internal;

import com.android.tools.r8.DataResource;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1732iG {
    public static String a(Function function, String str) {
        str.getClass();
        switch (str) {
            case "double":
                return "D";
            case "int":
                return "I";
            case "byte":
                return "B";
            case "char":
                return "C";
            case "long":
                return "J";
            case "void":
                return "V";
            case "boolean":
                return "Z";
            case "float":
                return "F";
            case "short":
                return "S";
            default:
                StringBuilder sb = new StringBuilder(str.length());
                int length = str.length() - 1;
                if (length < 0) {
                    throw ((RuntimeException) function.apply("Invalid empty type"));
                }
                while (str.charAt(length) == ']') {
                    if (str.charAt(length - 1) != '[') {
                        throw ((RuntimeException) function.apply("Invalid type: '" + str + "'"));
                    }
                    sb.append('[');
                    length -= 2;
                }
                sb.append('L');
                for (int i = 0; i <= length; i++) {
                    char cCharAt = str.charAt(i);
                    if (cCharAt == '.') {
                        cCharAt = DataResource.SEPARATOR;
                    }
                    sb.append(cCharAt);
                }
                sb.append(';');
                return sb.toString();
        }
    }

    public static String b(String str) {
        return a(str.replace('.', DataResource.SEPARATOR));
    }

    public static String c(String str) {
        return a(new Function() { // from class: g8h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new IllegalStateException((String) obj);
            }
        }, str);
    }

    public static String d(String str) {
        if (str.length() != 1) {
            if (str.charAt(0) != '[') {
                if (str.charAt(0) == 'L') {
                    return str.substring(1, str.length() - 1).replace(DataResource.SEPARATOR, '.');
                }
                k2d.a("Unexpected descriptor: ".concat(str));
                return null;
            }
            return d(str.substring(1)) + "[]";
        }
        char cCharAt = str.charAt(0);
        if (cCharAt == 'F') {
            return "float";
        }
        if (cCharAt == 'S') {
            return "short";
        }
        if (cCharAt == 'V') {
            return "void";
        }
        if (cCharAt == 'Z') {
            return "boolean";
        }
        if (cCharAt == 'I') {
            return "int";
        }
        if (cCharAt == 'J') {
            return "long";
        }
        switch (cCharAt) {
            case 'B':
                return "byte";
            case 'C':
                return "char";
            case 'D':
                return "double";
            default:
                k2d.a("Unexpected descriptor: ".concat(str));
                return null;
        }
    }

    public static AbstractC2587sH a(String str, final C3030xW c3030xW) {
        return AbstractC2587sH.a(a(new Function() { // from class: h8h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return c3030xW.a((String) obj);
            }
        }, str));
    }

    public static String a(String str) {
        return "L" + str + ";";
    }
}
