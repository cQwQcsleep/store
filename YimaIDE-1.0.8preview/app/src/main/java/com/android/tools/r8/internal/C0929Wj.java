package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0005a;
import com.android.tools.r8.DataResource;
import com.android.tools.r8.naming.C3313b;
import defpackage.f44;
import defpackage.iti;
import defpackage.n33;
import defpackage.oof;
import defpackage.x0g;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Wj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0929Wj {
    public static final AbstractC0706Nu a = AbstractC0706Nu.e().a("void", "V").a("boolean", "Z").a("byte", "B").a("short", "S").a("char", "C").a("int", "I").a("long", "J").a("float", "F").a("double", "D").a();
    public static final /* synthetic */ boolean b = true;

    public static boolean A(String str) {
        if (!z(str)) {
            if (!(str.length() != 1 ? false : a(str.charAt(0)))) {
                if (!((str.length() >= 2 && str.charAt(0) == '[') ? A(str.substring(1)) : false)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean B(String str) {
        List<String> listA = Wf0.a(v(str), '$');
        if (listA.size() < 2) {
            return false;
        }
        return Character.isLowerCase(listA.get(listA.size() - 1).charAt(0)) && listA.get(listA.size() - 2).equals("R");
    }

    public static boolean C(String str) {
        if (str.length() < 3 || str.charAt(0) != 'L' || str.charAt(str.length() - 1) != ';' || str.charAt(1) == '/' || str.charAt(str.length() - 2) == '/') {
            return false;
        }
        int iCharCount = 1;
        while (iCharCount < str.length() - 1) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt != 47 && !AbstractC1613gu.c(iCodePointAt) && !AbstractC1613gu.d(iCodePointAt)) {
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return true;
    }

    public static boolean D(String str) {
        boolean zD;
        int i;
        if (str.isEmpty() || str.charAt(0) != '[') {
            zD = false;
        } else {
            int i2 = -1;
            while (true) {
                i = i2 + 1;
                if (i >= str.length() || str.charAt(i) != '[') {
                    break;
                }
                i2 = i;
            }
            zD = D(i2 >= 0 ? str.substring(i) : str);
        }
        if (!zD && !C(str)) {
            if (!(str.length() != 1 ? false : a(str.charAt(0))) && !H(str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean E(String str) {
        int iCharCount;
        if (str.isEmpty()) {
            return false;
        }
        int length = str.length();
        if (str.charAt(0) != '<') {
            iCharCount = 0;
        } else {
            if (str.charAt(length - 1) != '>') {
                return false;
            }
            length--;
            iCharCount = 1;
        }
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (!AbstractC1613gu.c(iCodePointAt) && !AbstractC1613gu.d(iCodePointAt)) {
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return true;
    }

    public static boolean F(String str) {
        if (str.length() == 0) {
            return false;
        }
        int i = 0;
        char c = 0;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            if (cCharAt == ';' || cCharAt == '[' || cCharAt == '/') {
                return false;
            }
            if (cCharAt == '.' && (i == 0 || c == '.')) {
                return false;
            }
            i++;
            c = cCharAt;
        }
        return true;
    }

    public static boolean G(String str) {
        if (str.isEmpty()) {
            return false;
        }
        if (str.charAt(0) == '<' && (str.equals("<init>") || str.equals("<clinit>"))) {
            return true;
        }
        int iCharCount = 0;
        while (iCharCount < str.length()) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (!AbstractC1613gu.c(iCodePointAt) && !AbstractC1613gu.d(iCodePointAt)) {
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return true;
    }

    public static boolean H(String str) {
        return str.length() == 1 && str.charAt(0) == 'V';
    }

    public static String I(String str) {
        if (b || str.indexOf(47) == -1) {
            return a(str, false, false);
        }
        x1f.a();
        return null;
    }

    public static String J(String str) {
        if (b || str.indexOf(47) == -1) {
            return a(str, false, true);
        }
        x1f.a();
        return null;
    }

    public static String K(String str) {
        return a(str, true, false);
    }

    public static String a(String str, C3313b c3313b) {
        char cCharAt = str.charAt(0);
        if (cCharAt == 'L') {
            if (b || str.charAt(str.length() - 1) == ';') {
                String strReplace = str.substring(1, str.length() - 1).replace(DataResource.SEPARATOR, '.');
                return c3313b == null ? strReplace : c3313b.a(strReplace);
            }
            x1f.a();
            return null;
        }
        if (cCharAt != '[') {
            return c(cCharAt);
        }
        return a(str.substring(1), c3313b) + "[]";
    }

    public static String b(char c) {
        if (c == 'F') {
            return "java/lang/Float";
        }
        if (c == 'S') {
            return "java/lang/Short";
        }
        if (c == 'V') {
            return "java/lang/Void";
        }
        if (c == 'Z') {
            return "java/lang/Boolean";
        }
        if (c == 'I') {
            return "java/lang/Integer";
        }
        if (c == 'J') {
            return "java/lang/Long";
        }
        switch (c) {
            case 'B':
                return "java/lang/Byte";
            case 'C':
                return "java/lang/Character";
            case 'D':
                return "java/lang/Double";
            default:
                oof.a("Unknown type ", c);
                return null;
        }
    }

    public static String c(char c) {
        if (c == 'F') {
            return "float";
        }
        if (c == 'S') {
            return "short";
        }
        if (c == 'V') {
            return "void";
        }
        if (c == 'Z') {
            return "boolean";
        }
        if (c == 'I') {
            return "int";
        }
        if (c == 'J') {
            return "long";
        }
        switch (c) {
            case 'B':
                return "byte";
            case 'C':
                return "char";
            case 'D':
                return "double";
            default:
                oof.a("Unknown type ", c);
                return null;
        }
    }

    public static int d(String str) {
        int i;
        int length = str.length();
        int i2 = 0;
        int i3 = 1;
        while (i3 < length) {
            int i4 = i3 + 1;
            char cCharAt = str.charAt(i3);
            if (cCharAt == ')') {
                i3 = i4;
                break;
            }
            if (cCharAt == 'L') {
                while (true) {
                    if (i4 >= length) {
                        i = i4;
                        break;
                    }
                    i = i4 + 1;
                    if (str.charAt(i4) == ';') {
                        break;
                    }
                    i4 = i;
                }
                if (i >= length || str.charAt(i - 1) != ';') {
                    throw new OB(str);
                }
                i2++;
                i3 = i;
            } else {
                if (cCharAt != '[') {
                    i2++;
                }
                i3 = i4;
            }
        }
        if (i3 >= length || str.charAt(i3 - 1) != ')') {
            throw new OB(str);
        }
        return i2;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x007e  */
    public static String[] e(String str) {
        int i;
        String[] strArr = new String[d(str)];
        int i2 = 0;
        int i3 = 1;
        while (true) {
            char cCharAt = str.charAt(i3);
            if (cCharAt == ')') {
                return strArr;
            }
            if (cCharAt == 'F') {
                i = i2 + 1;
                strArr[i2] = Character.toString(cCharAt);
                i2 = i;
            } else if (cCharAt != 'L') {
                if (cCharAt == 'S') {
                    i = i2 + 1;
                    strArr[i2] = Character.toString(cCharAt);
                } else {
                    if (cCharAt == 'V') {
                        throw new OB(str);
                    }
                    if (cCharAt == 'I' || cCharAt == 'J' || cCharAt == 'Z') {
                        i = i2 + 1;
                        strArr[i2] = Character.toString(cCharAt);
                    } else if (cCharAt != '[') {
                        switch (cCharAt) {
                            case 'B':
                            case 'C':
                            case 'D':
                                i = i2 + 1;
                                strArr[i2] = Character.toString(cCharAt);
                                break;
                            default:
                                throw new OB(str);
                        }
                    } else {
                        int i4 = i3;
                        do {
                            i4++;
                        } while (str.charAt(i4) == '[');
                        if (str.charAt(i4) == 'L') {
                            do {
                                i4++;
                            } while (str.charAt(i4) != ';');
                        }
                        i = i2 + 1;
                        strArr[i2] = str.substring(i3, i4 + 1);
                        i3 = i4;
                    }
                }
                i2 = i;
            } else {
                int i5 = i3;
                while (true) {
                    int i6 = i5 + 1;
                    if (str.charAt(i6) != ';') {
                        i5 = i6;
                    } else {
                        strArr[i2] = str.substring(i3, i5 + 2);
                        i2++;
                        i3 = i6;
                    }
                }
            }
            i3++;
        }
    }

    public static String f(String str) {
        if (b || z(str)) {
            return AbstractC0005a.a(1, 1, str);
        }
        x1f.a();
        return null;
    }

    public static String g(String str) {
        return str.replace('.', DataResource.SEPARATOR);
    }

    public static String h(String str) {
        return k(str).replace('$', '.');
    }

    public static String i(String str) {
        if (b || z(str)) {
            return AbstractC0005a.a(1, 1, str);
        }
        s22.a("Invalid class descriptor ", str);
        return null;
    }

    public static String j(String str) {
        if (!b && (str == null || !z(str))) {
            x1f.a();
            return null;
        }
        return i(str) + ".class";
    }

    public static String k(String str) {
        return i(str).replace(DataResource.SEPARATOR, '.');
    }

    public static String l(String str) {
        if (!b && str == null) {
            x1f.a();
            return null;
        }
        return "L" + str + ";";
    }

    public static String m(String str) {
        boolean z = b;
        if (!z && str == null) {
            x1f.a();
            return null;
        }
        if (!z && str.contains("[")) {
            x01.a(str);
            return null;
        }
        return "L" + str.replace('.', '$') + ";";
    }

    public static String n(String str) {
        return str.replace(DataResource.SEPARATOR, '.');
    }

    public static String o(String str) {
        return str.replace('.', DataResource.SEPARATOR);
    }

    public static String p(String str) {
        int iLastIndexOf = str.lastIndexOf(47);
        return iLastIndexOf < 0 ? XmlPullParser.NO_NAMESPACE : str.substring(0, iLastIndexOf).replace(DataResource.SEPARATOR, '.');
    }

    public static String q(String str) {
        return p(i(str));
    }

    public static String r(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf < 0 ? XmlPullParser.NO_NAMESPACE : str.substring(0, iLastIndexOf);
    }

    public static String s(String str) {
        boolean z = b;
        if (!z && !str.startsWith("L")) {
            x1f.a();
            return null;
        }
        if (!z && !str.endsWith(";")) {
            x1f.a();
            return null;
        }
        return AbstractC0005a.a(1, 1, str) + ".class";
    }

    public static String t(String str) {
        if (!b && !F(str)) {
            x1f.a();
            return null;
        }
        return str.replace('.', DataResource.SEPARATOR) + ".class";
    }

    public static String u(String str) {
        if (b || str.indexOf(41) != -1) {
            return str.substring(str.indexOf(41) + 1);
        }
        x1f.a();
        return null;
    }

    public static String v(String str) {
        return AbstractC0005a.a(1, Integer.max(str.lastIndexOf("/"), 0) + 1, str);
    }

    public static String w(String str) {
        String strI = i(str);
        int iLastIndexOf = strI.lastIndexOf(47);
        return iLastIndexOf < 0 ? strI : strI.substring(iLastIndexOf + 1);
    }

    public static C0903Vj x(String str) {
        boolean z = b;
        if (!z && str == null) {
            x1f.a();
            return null;
        }
        if (!z && !str.endsWith(".class")) {
            iti.a("Name ", str, " must have .class suffix");
            return null;
        }
        if (!z && !str.startsWith("/modules")) {
            iti.a("Name ", str, " must have /modules prefix");
            return null;
        }
        if (!z && str.charAt(8) != '/') {
            x1f.a();
            return null;
        }
        int iIndexOf = str.indexOf(47, 9);
        String strSubstring = str.substring(9, iIndexOf);
        String strA = AbstractC0005a.a(6, iIndexOf + 1, str);
        if (strA.indexOf(46) != -1) {
            n33.a("Unexpected class file name: ".concat(str));
            return null;
        }
        return new C0903Vj(strSubstring, "L" + strA + ";");
    }

    public static String y(String str) {
        boolean z = b;
        if (!z && str == null) {
            x1f.a();
            return null;
        }
        if (!z && !str.endsWith(".class")) {
            iti.a("Name ", str, " must have .class suffix");
            return null;
        }
        String strA = AbstractC0005a.a(6, 0, str);
        if (strA.indexOf(46) != -1) {
            f44.a("Unexpected class file name: ", str);
            return null;
        }
        return "L" + strA + ";";
    }

    public static boolean z(String str) {
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        if (length >= 3 && charArray[0] == 'L') {
            int i = 1;
            while (i < length) {
                int i2 = i + 1;
                char c = charArray[i];
                if (c != '.' && c != '[' && c != '/' && c != ';') {
                    while (i2 < length) {
                        i = i2 + 1;
                        char c2 = charArray[i2];
                        if (c2 != '.' && c2 != '[') {
                            if (c2 != '/' && c2 != ';') {
                                i2 = i;
                            } else if (c2 == ';') {
                                if (i == length) {
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    public static String b(String str) {
        return a(str, (C3313b) null);
    }

    public static String c(String str) {
        String strReplace = f(str).replace('$', '.');
        if (!str.startsWith("Lj$/")) {
            return strReplace;
        }
        if (b || strReplace.startsWith("j./")) {
            return "j$/".concat(strReplace.substring(3));
        }
        x1f.a();
        return null;
    }

    public static String b(String str, String str2) {
        if (str2.length() <= str.length()) {
            return null;
        }
        String strConcat = str.concat("$");
        if (str2.startsWith(strConcat)) {
            return str2.substring(strConcat.length());
        }
        return null;
    }

    public static String b(Class<?> cls) {
        return t(cls.getTypeName());
    }

    public static String c(Class<?> cls) {
        return I(cls.getTypeName());
    }

    public static String c(String str, String str2) {
        return "L" + str.substring(1, Integer.max(str.lastIndexOf("/"), 0) + 1) + str2 + ";";
    }

    public static String a(String str, boolean z, boolean z2) {
        String str2 = !z2 ? (String) a.get(str) : null;
        if (str2 != null) {
            return str2;
        }
        if (z) {
            return "L";
        }
        if (str.endsWith("[]")) {
            return "[" + a(str.substring(0, str.length() - 2), z, z2);
        }
        return "L" + str.replace('.', DataResource.SEPARATOR) + ";";
    }

    public static String a(Function function, String str) {
        int i = 0;
        for (int length = str.length() - 2; length > 0; length -= 2) {
            if (str.charAt(length) == '[' && str.charAt(length + 1) == ']') {
                i++;
            }
        }
        if (i > 0) {
            str = str.substring(0, str.length() - (i * 2));
        }
        String str2 = (String) function.apply(str);
        if (i == 0) {
            return str2;
        }
        StringBuilder sb = new StringBuilder(str2);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("[]");
        }
        return sb.toString();
    }

    public static String a(String str) {
        char cCharAt = str.charAt(0);
        if (cCharAt == 'L') {
            return AbstractC0005a.a(1, 1, str);
        }
        if (cCharAt == '[') {
            return str;
        }
        x0g.a("Not array or class type");
        return null;
    }

    public static boolean a(char c) {
        return c == 'Z' || c == 'B' || c == 'S' || c == 'C' || c == 'I' || c == 'F' || c == 'J' || c == 'D';
    }

    public static String a(Class<?> cls) {
        return g(cls.getTypeName());
    }

    public static String a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3, com.android.tools.r8.graph.H2 h2) {
        if (!b && i3 == null) {
            x1f.a();
            return null;
        }
        if (i2 != null && h2 != null) {
            return a(i2.A0(), i3.A0(), h2.toString());
        }
        return String.valueOf('$');
    }

    public static String a(String str, String str2, String str3) {
        if (!b && (str3 == null || str3.isEmpty())) {
            x1f.a();
            return null;
        }
        if (str3.length() + str.length() > str2.length()) {
            return null;
        }
        String strSubstring = str2.substring(str.length(), str2.length() - str3.length());
        if (strSubstring.startsWith(String.valueOf('$'))) {
            return strSubstring;
        }
        return null;
    }

    public static String a(Path path) {
        String string = path.toString();
        char c = File.separatorChar;
        if (c != '/') {
            string = string.replace(c, DataResource.SEPARATOR);
        }
        return y(string);
    }

    public static String a(String str, String str2) {
        if (str2.length() <= str.length()) {
            return null;
        }
        String str3 = AbstractC0005a.a(1, 0, str) + "$";
        if (str2.startsWith(str3)) {
            return AbstractC0005a.a(1, str3.length(), str2);
        }
        return null;
    }
}
