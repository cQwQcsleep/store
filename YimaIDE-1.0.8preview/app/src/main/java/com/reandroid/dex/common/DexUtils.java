package com.reandroid.dex.common;

import com.reandroid.dex.common.DexUtils;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.NumbersUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.sun.org.apache.xml.internal.serializer.CharInfo;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexUtils {
    public static final String[] PLATFORM_PACKAGES = {"Ljava/", "Landroid/", "Ldalvik/", "Lorg/json/", "Lorg/xmlpull/"};

    /* JADX WARN: Code duplicated, block: B:21:0x0044  */
    /* JADX WARN: Code duplicated, block: B:26:0x0054  */
    public static void appendCommentString(int i, Appendable appendable, String str) throws IOException {
        if (appendable == null) {
            return;
        }
        int iMin = NumbersUtil.min(i, str.length());
        appendable.append('\'');
        for (int i2 = 0; i2 < iMin; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt >= ' ' && cCharAt < 127) {
                appendable.append(cCharAt);
            } else if (cCharAt > 127) {
                if (Character.isDefined(cCharAt) || Character.isWhitespace(cCharAt)) {
                    encodeToHexChar(appendable, cCharAt);
                } else {
                    appendable.append(cCharAt);
                }
            } else if (cCharAt == '\t') {
                appendable.append("\\t");
            } else if (cCharAt == '\n') {
                appendable.append("\\n");
            } else if (cCharAt == '\r') {
                appendable.append("\\r");
            } else if (Character.isDefined(cCharAt)) {
                encodeToHexChar(appendable, cCharAt);
            } else {
                encodeToHexChar(appendable, cCharAt);
            }
        }
        appendable.append('\'');
    }

    public static void appendQuotedString(Appendable appendable, String str) throws IOException {
        appendable.append('\"');
        encodeString(appendable, str);
        appendable.append('\"');
    }

    public static void appendSingleQuotedChar(Appendable appendable, char c) throws IOException {
        if (c >= ' ' && c < 127) {
            appendable.append('\'');
            if (c == '\'' || c == '\"' || c == '\\') {
                appendable.append('\\');
            }
            appendable.append(c);
            appendable.append('\'');
            return;
        }
        if (c <= 127) {
            switch (c) {
                case '\b':
                    appendable.append("'\\b'");
                    break;
                case '\t':
                    appendable.append("'\\t'");
                    break;
                case '\n':
                    appendable.append("'\\n'");
                    break;
                case '\f':
                    appendable.append("'\\f'");
                    break;
                case '\r':
                    appendable.append("'\\r'");
                    break;
            }
            return;
        }
        appendable.append('\'');
        encodeToHexChar(appendable, c);
        appendable.append('\'');
    }

    public static int compareDex(Object obj, Object obj2) {
        if (obj == obj2) {
            return 0;
        }
        if (obj == null) {
            return 1;
        }
        if (obj2 == null) {
            return -1;
        }
        return compareDexPath(obj.toString(), obj2.toString());
    }

    public static int compareDexPath(String str, String str2) {
        if (str == null) {
            return 1;
        }
        if (str2 == null) {
            return -1;
        }
        if (str.equals(str2)) {
            return 0;
        }
        return Integer.compare(getDexNumber(str), getDexNumber(str2));
    }

    public static int countArrayPrefix(String str) {
        int i = 0;
        if (str == null || str.length() < 2) {
            return 0;
        }
        while (str.charAt(i) == '[') {
            i++;
        }
        return i;
    }

    public static String createChildClass(String str, String str2) {
        int length = str.length();
        if (length < 2) {
            return str;
        }
        int i = length - 1;
        char cCharAt = str.charAt(i);
        if (cCharAt == ';' || cCharAt == '<') {
            str = str.substring(0, i);
        }
        return str + "$" + str2 + ";";
    }

    @Deprecated
    public static String decodeString(String str) {
        Character chNextHex;
        if (str.indexOf(92) < 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i = 0;
        boolean z = false;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (z) {
                if (cCharAt != 'u' || (chNextHex = nextHex(str, i + 1)) == null) {
                    sb.append(getEscaped(cCharAt));
                } else {
                    sb.append(chNextHex);
                    i += 4;
                }
                z = false;
            } else if (cCharAt == '\\') {
                z = true;
            } else {
                sb.append(cCharAt);
            }
            i++;
        }
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0048  */
    public static boolean encodeString(Appendable appendable, String str) throws IOException {
        int length = str.length();
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt >= ' ' && cCharAt < 127) {
                if (cCharAt == '\'' || cCharAt == '\"' || cCharAt == '\\') {
                    appendable.append('\\');
                }
                appendable.append(cCharAt);
            } else if (cCharAt > 127) {
                encodeToHexChar(appendable, cCharAt);
                if (z && cCharAt != 8230 && cCharAt > 255) {
                    z = true;
                }
            } else if (cCharAt == '\t') {
                appendable.append("\\t");
            } else if (cCharAt == '\n') {
                appendable.append("\\n");
            } else if (cCharAt != '\r') {
                encodeToHexChar(appendable, cCharAt);
                if (z) {
                }
            } else {
                appendable.append("\\r");
            }
        }
        return z;
    }

    public static void encodeToHexChar(Appendable appendable, char c) throws IOException {
        appendable.append("\\u");
        appendable.append(Character.forDigit(c >> '\f', 16));
        appendable.append(Character.forDigit((c >> '\b') & 15, 16));
        appendable.append(Character.forDigit((c >> 4) & 15, 16));
        appendable.append(Character.forDigit(c & 15, 16));
    }

    private static int getDexNumber(String str) {
        int iLastIndexOf = str.lastIndexOf(47);
        if (iLastIndexOf < 0) {
            iLastIndexOf = str.lastIndexOf(File.separatorChar);
        }
        if (iLastIndexOf >= 0) {
            str = str.substring(iLastIndexOf + 1);
        }
        if (str.equals("classes")) {
            return 0;
        }
        if (!str.startsWith("classes")) {
            return 65535;
        }
        String strSubstring = str.substring(7);
        int iIndexOf = strSubstring.indexOf(46);
        if (iIndexOf == 0) {
            return 0;
        }
        if (iIndexOf < 0) {
            return 65535;
        }
        try {
            return Integer.parseInt(strSubstring.substring(0, iIndexOf));
        } catch (NumberFormatException unused) {
            return 65535;
        }
    }

    public static <T> Comparator<T> getDexPathComparator() {
        return new Comparator() { // from class: qr3
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return DexUtils.compareDex(obj, obj2);
            }
        };
    }

    private static char getEscaped(char c) {
        if (c == 'b') {
            return '\b';
        }
        if (c == 'f') {
            return '\f';
        }
        if (c == 'n') {
            return '\n';
        }
        if (c == 'r') {
            return CharInfo.S_CARRIAGERETURN;
        }
        if (c != 't') {
            return c;
        }
        return '\t';
    }

    public static String getPackageName(String str) {
        if (str.length() < 3) {
            return StringsUtil.EMPTY;
        }
        int i = 0;
        while (str.charAt(i) == '[') {
            i++;
        }
        int iLastIndexOf = str.lastIndexOf(47);
        if (iLastIndexOf > i) {
            return str.substring(i, iLastIndexOf + 1);
        }
        return str.substring(i, str.charAt(i) == 'L' ? i + 1 : i);
    }

    public static String getParentClassName(String str) {
        String strTrimArrayPrefix = trimArrayPrefix(str);
        if (strTrimArrayPrefix.length() >= 2) {
            int iLastIndexOf = strTrimArrayPrefix.lastIndexOf(47);
            if (iLastIndexOf < 0) {
                iLastIndexOf = 0;
            }
            int i = iLastIndexOf + 1;
            int iLastIndexOf2 = strTrimArrayPrefix.lastIndexOf(36);
            if (iLastIndexOf2 > i) {
                String strSubstring = strTrimArrayPrefix.substring(0, iLastIndexOf2);
                while (strSubstring.length() > 0 && strSubstring.charAt(strSubstring.length() - 1) == '$') {
                    strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
                }
                return strSubstring.concat(";");
            }
        }
        return strTrimArrayPrefix;
    }

    public static String getSimpleInnerName(String str) {
        String[] strArrRemoveEmpty;
        int length;
        String strTrimArrayPrefix = trimArrayPrefix(str);
        if (strTrimArrayPrefix.length() < 2) {
            return strTrimArrayPrefix;
        }
        int iLastIndexOf = strTrimArrayPrefix.lastIndexOf(47);
        if (iLastIndexOf < 0) {
            iLastIndexOf = 0;
        }
        String strSubstring = strTrimArrayPrefix.substring(iLastIndexOf + 1);
        int length2 = strSubstring.length() - 1;
        if (strSubstring.charAt(length2) == ';' || strSubstring.charAt(length2) == '<') {
            strSubstring = strSubstring.substring(0, length2);
        }
        return (strSubstring.indexOf(36) >= 0 && (length = (strArrRemoveEmpty = StringsUtil.removeEmpty(StringsUtil.split(strSubstring, '$', true))).length) >= 2) ? strArrRemoveEmpty[length - 1] : strSubstring;
    }

    public static String getSimpleName(String str) {
        String strTrimArrayPrefix = trimArrayPrefix(str);
        if (strTrimArrayPrefix.length() < 2) {
            return strTrimArrayPrefix;
        }
        int iLastIndexOf = strTrimArrayPrefix.lastIndexOf(47);
        if (iLastIndexOf < 0) {
            iLastIndexOf = 0;
        }
        String strSubstring = strTrimArrayPrefix.substring(iLastIndexOf + 1);
        int length = strSubstring.length() - 1;
        return (strSubstring.charAt(length) == ';' || strSubstring.charAt(length) == '<') ? strSubstring.substring(0, length) : strSubstring;
    }

    public static boolean isJavaFramework(String str) {
        return str.startsWith("Ljava/");
    }

    public static boolean isPlatform(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return false;
        }
        int i = 0;
        while (i < length && str.charAt(i) == '[') {
            i++;
        }
        if (i >= length) {
            return false;
        }
        if (i != 0) {
            str = str.substring(i);
        }
        for (String str2 : PLATFORM_PACKAGES) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPrimitive(char c) {
        if (c == 'F' || c == 'S' || c == 'V' || c == 'Z' || c == 'I' || c == 'J') {
            return true;
        }
        switch (c) {
            case 'B':
            case 'C':
            case 'D':
                return true;
            default:
                return false;
        }
    }

    private static boolean isSignatureSymbol(char c) {
        if (c == '*' || c == '+' || c == '-' || c == '>' || c == '?') {
            return true;
        }
        switch (c) {
            case ':':
            case ';':
            case '<':
                return true;
            default:
                return false;
        }
    }

    public static boolean isTypeArray(String str) {
        int length;
        if (str == null || (length = str.length()) < 2) {
            return false;
        }
        int i = 0;
        while (str.charAt(i) == '[') {
            i++;
        }
        if (i == 0) {
            return false;
        }
        int i2 = length - 1;
        if (i == i2) {
            return isPrimitive(str.charAt(i));
        }
        return str.charAt(i) == 'L' && str.charAt(i2) == ';';
    }

    public static boolean isTypeObject(String str) {
        if (str == null || str.length() < 2) {
            return false;
        }
        char cCharAt = str.charAt(0);
        return cCharAt == 'L' || cCharAt == '[';
    }

    public static boolean isTypeOrSignature(String str) {
        int length;
        if (str == null || (length = str.length()) < 3) {
            return false;
        }
        char cCharAt = str.charAt(length - 1);
        return str.charAt(0) == 'L' && (cCharAt == '<' || cCharAt == ';');
    }

    public static boolean isTypeSignature(String str) {
        int length;
        return str != null && (length = str.length()) >= 3 && str.charAt(0) == 'L' && str.charAt(length - 1) == '<';
    }

    public static List<File> listDexFiles(File file) {
        File[] fileArrListFiles;
        ArrayCollection arrayCollection = new ArrayCollection();
        if (!file.isDirectory() || (fileArrListFiles = file.listFiles()) == null) {
            return arrayCollection;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isFile() && file2.getName().endsWith(".dex")) {
                arrayCollection.add(file2);
            }
        }
        arrayCollection.sort(getDexPathComparator());
        return arrayCollection;
    }

    public static boolean looksSignatureType(String str) {
        int length = str.length();
        return length >= 3 && str.charAt(0) == 'L' && str.charAt(length - 1) == '<';
    }

    public static String makeArrayType(String str, int i) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length != 0) {
            int i2 = 0;
            while (str.charAt(i2) == '[') {
                i2++;
            }
            if (i2 != i) {
                if (i2 > i) {
                    return str.substring(i2 - i);
                }
                int i3 = i - i2;
                StringBuilder sb = new StringBuilder(length + i3);
                while (i3 > 0) {
                    sb.append('[');
                    i3--;
                }
                sb.append(str);
                return sb.toString();
            }
        }
        return str;
    }

    private static Character nextHex(String str, int i) {
        int i2 = i + 4;
        if (i2 > str.length()) {
            return null;
        }
        int i3 = 0;
        while (i < i2) {
            int iDecodeHexChar = HexUtil.decodeHexChar(str.charAt(i));
            if (iDecodeHexChar == -1) {
                return null;
            }
            i3 = (i3 << 4) | iDecodeHexChar;
            i++;
        }
        return Character.valueOf((char) i3);
    }

    public static String quoteChar(char c) {
        StringBuilder sb = new StringBuilder();
        try {
            appendSingleQuotedChar(sb, c);
        } catch (IOException unused) {
        }
        return sb.toString();
    }

    public static String quoteString(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 2);
        try {
            appendQuotedString(sb, str);
        } catch (IOException unused) {
        }
        return sb.toString();
    }

    public static String[] splitParameters(String str) {
        if (StringsUtil.isEmpty(str)) {
            return null;
        }
        int length = str.length();
        String[] strArr = new String[length];
        int i = 0;
        boolean z = false;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char cCharAt = str.charAt(i3);
            boolean z2 = true;
            if (cCharAt == '[') {
                z = true;
                z2 = false;
            } else if (cCharAt != ';') {
                if ((z || i3 - i2 == 0) && isPrimitive(cCharAt)) {
                    z = false;
                } else {
                    z = false;
                    z2 = false;
                }
            }
            if (z2) {
                int i4 = i3 + 1;
                strArr[i] = str.substring(i2, i4);
                i++;
                i2 = i4;
            }
        }
        if (i == 0) {
            return null;
        }
        if (i == length) {
            return strArr;
        }
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, i);
        return strArr2;
    }

    public static String[] splitSignatures(String str) {
        ArrayCollection arrayCollection = new ArrayCollection(5);
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            sb.append(cCharAt);
            if (isSignatureSymbol(cCharAt)) {
                arrayCollection.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        if (sb.length() != 0) {
            arrayCollection.add(sb.toString());
        }
        return (String[]) arrayCollection.toArray(new String[arrayCollection.size()]);
    }

    public static String toBinaryName(String str) {
        return "L" + str.replace('.', '/') + ';';
    }

    public static String toBinaryPackageName(String str) {
        if (str.indexOf(47) > 0) {
            return str;
        }
        String str2 = "L" + str.replace('.', '/');
        return str2.charAt(str.length() + (-1)) != '/' ? str2.concat(PsuedoNames.PSEUDONAME_ROOT) : str2;
    }

    public static String toDeclaringType(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return str;
        }
        int i = 0;
        while (str.charAt(i) == '[') {
            i++;
        }
        int i2 = length - 1;
        if (i == i2 || str.charAt(i2) == ';' || str.charAt(i2) != '<') {
            return i != 0 ? str.substring(i) : str;
        }
        StringBuilder sb = new StringBuilder(i2 - i);
        sb.append((CharSequence) str, i, i2);
        sb.append(';');
        return sb.toString();
    }

    public static String toSignatureType(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return str;
        }
        int i = 0;
        while (str.charAt(i) == '[') {
            i++;
        }
        int i2 = length - 1;
        if (i == i2 || str.charAt(i2) == '<' || str.charAt(i2) != ';') {
            return i != 0 ? str.substring(i) : str;
        }
        StringBuilder sb = new StringBuilder(i2 - i);
        sb.append((CharSequence) str, i, i2);
        sb.append('<');
        return sb.toString();
    }

    public static String toSourceFileName(String str) {
        String simpleName = getSimpleName(str);
        int iIndexOf = simpleName.indexOf(36);
        if (iIndexOf > 0) {
            simpleName = simpleName.substring(0, iIndexOf);
        }
        return simpleName.concat(".java");
    }

    public static String toSourceName(String str) {
        String strSubstring = str.substring(str.indexOf(76) + 1);
        int iIndexOf = strSubstring.indexOf(59);
        if (iIndexOf < 0) {
            iIndexOf = strSubstring.indexOf(60);
        }
        if (iIndexOf > 0) {
            strSubstring = strSubstring.substring(0, iIndexOf);
        }
        return strSubstring.replace('/', '.');
    }

    public static String trimArrayPrefix(String str) {
        int i = 0;
        while (i < str.length() && str.charAt(i) == '[') {
            i++;
        }
        return i == 0 ? str : str.substring(i);
    }

    public static <T, E> Comparator<T> getDexPathComparator(final Function<T, E> function) {
        return new Comparator() { // from class: pr3
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return DexUtils.compareDex(function, obj, obj2);
            }
        };
    }

    public static <T, E> int compareDex(Function<T, E> function, T t, T t2) {
        return compareDex(function.apply(t), function.apply(t2));
    }

    public static boolean isPrimitive(String str) {
        if (str == null || str.length() != 1) {
            return false;
        }
        return isPrimitive(str.charAt(0));
    }

    public static void encodeToHexChar(StringBuilder sb, char c) {
        try {
            encodeToHexChar((Appendable) sb, c);
        } catch (IOException unused) {
        }
    }

    public static String encodeToHexChar(char c) {
        StringBuilder sb = new StringBuilder(6);
        encodeToHexChar(sb, c);
        return sb.toString();
    }

    public static boolean isPlatform(TypeKey typeKey) {
        if (typeKey == null) {
            return false;
        }
        return isPlatform(typeKey.getTypeName());
    }

    public static String encodeString(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        try {
            encodeString(sb, str);
        } catch (IOException unused) {
        }
        return sb.toString();
    }
}
