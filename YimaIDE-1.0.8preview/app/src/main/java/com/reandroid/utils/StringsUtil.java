package com.reandroid.utils;

import com.intellij.psi.PsiKeyword;
import com.reandroid.utils.collection.ArrayIterator;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringsUtil {
    public static final String EMPTY = ObjectsUtil.of("");
    private static final int MAX_STRING_APPEND = 5;

    public static String append(String str, char c, int i, boolean z) {
        StringBuilder sb = new StringBuilder(str.length() + i);
        if (!z) {
            sb.append(str);
        }
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(c);
        }
        if (z) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static String appendPostfix(String str, char c, int i) {
        return append(str, c, i, false);
    }

    public static int compare(String[] strArr, String[] strArr2) {
        if (strArr == strArr2) {
            return 0;
        }
        if (strArr == null) {
            return 1;
        }
        if (strArr2 == null) {
            return -1;
        }
        int length = strArr.length;
        int length2 = strArr2.length;
        if (length == 0 && length2 == 0) {
            return 0;
        }
        if (length == 0) {
            return 1;
        }
        if (length2 == 0) {
            return -1;
        }
        int i = length > length2 ? length2 : length;
        for (int i2 = 0; i2 < i; i2++) {
            int iCompareStrings = compareStrings(strArr[i2], strArr2[i2]);
            if (iCompareStrings != 0 && (length == length2 || i2 < i - 1)) {
                return iCompareStrings;
            }
        }
        return Integer.compare(length, length2);
    }

    public static int compareStrings(String str, String str2) {
        return CompareUtil.compare(str, str2);
    }

    public static int compareToString(Object obj, Object obj2) {
        return compareStrings(obj == null ? null : obj.toString(), obj2 != null ? obj2.toString() : null);
    }

    private static boolean contains(char[] cArr, char c) {
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsUpperAZ(String str) {
        if (isEmpty(str)) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (isUpperAZ(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static int countChar(String str, char[] cArr, boolean z) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int length = str.length();
        int i = 0;
        boolean z2 = false;
        for (int i2 = 0; i2 < length; i2++) {
            if (!contains(cArr, str.charAt(i2))) {
                z2 = false;
            } else if (!z2 || !z) {
                i++;
                z2 = true;
            }
        }
        return i;
    }

    public static int diffStart(String str, String str2) {
        if (!isEmpty(str) && !isEmpty(str2) && !str.equals(str2)) {
            int iMin = NumbersUtil.min(str.length(), str2.length());
            for (int i = 0; i < iMin; i++) {
                if (str.charAt(i) != str2.charAt(i)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static String emptyToNull(String str) {
        if (isEmpty(str)) {
            return null;
        }
        return str;
    }

    public static boolean endsWith(String str, char c) {
        int length;
        return (str == null || (length = str.length()) == 0 || str.charAt(length - 1) != c) ? false : true;
    }

    public static String formatNumber(long j, long j2) {
        return trailZeros(j, Long.toString(j2).length());
    }

    public static byte[] getASCII(String str) {
        int length = str.length();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) str.charAt(i);
        }
        return bArr;
    }

    public static int indexOfFrom(String str, int i, char c) {
        if (str != null && i >= 0) {
            int length = str.length();
            while (i < length) {
                if (str.charAt(i) == c) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    public static boolean isAz(String str) {
        if (isEmpty(str)) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!isAz(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAzOrDigits(String str) {
        if (isEmpty(str)) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!isAzOrDigits(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAzOrDigitsOr(String str, char[] cArr) {
        if (isEmpty(str)) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!isAzOrDigitsOr(str.charAt(i), cArr)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!isWhiteSpace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isDigits(String str) {
        if (isEmpty(str)) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isLowerAZ(String str) {
        if (isEmpty(str)) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!isLowerAZ(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUpperAZ(String str) {
        if (isEmpty(str)) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!isUpperAZ(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isWhiteSpace(char c) {
        if (c != 0 && c != '\f' && c != '\r' && c != ' ') {
            switch (c) {
                case '\b':
                case '\t':
                case '\n':
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public static String join(Iterator<?> it, Object obj) {
        StringBuilder sb = new StringBuilder();
        loop0: while (true) {
            boolean z = false;
            while (true) {
                if (!it.hasNext()) {
                    break loop0;
                }
                if (z) {
                    sb.append(obj);
                }
                sb.append(it.next());
                if (obj != null) {
                    z = true;
                }
            }
        }
        return sb.length() != 0 ? sb.toString() : EMPTY;
    }

    public static String remove(String str, char[] cArr) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return null;
        }
        int iCountChar = countChar(str, cArr, false);
        if (iCountChar == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(length - iCountChar);
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (!contains(cArr, cCharAt)) {
                sb.append(cCharAt);
            }
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    public static String[] removeEmpty(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        int length = strArr.length;
        if (length != 0) {
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (isEmpty(strArr[i2])) {
                    strArr[i2] = null;
                } else {
                    i++;
                }
            }
            if (i != length) {
                String[] strArr2 = new String[i];
                int i3 = 0;
                for (String str : strArr) {
                    if (str != null) {
                        strArr2[i3] = str;
                        i3++;
                    }
                }
                return strArr2;
            }
        }
        return strArr;
    }

    public static String replaceAll(String str, String str2, String str3) {
        if (str != null && str2 != null && str3 != null && !str2.equals(str3)) {
            int length = str.length();
            int length2 = str2.length();
            if (length != 0 && length2 != 0 && length2 <= length) {
                if (length != length2) {
                    int i = 0;
                    StringBuilder sb = null;
                    while (true) {
                        int iIndexOf = str.indexOf(str2, i);
                        if (iIndexOf < 0) {
                            break;
                        }
                        if (sb == null) {
                            sb = new StringBuilder((str3.length() * 5) + length);
                        }
                        sb.append((CharSequence) str, i, iIndexOf);
                        sb.append(str3);
                        i = iIndexOf + length2;
                    }
                    if (i != 0) {
                        sb.append(str.substring(i));
                        return sb.toString();
                    }
                } else if (str.equals(str2)) {
                    return str3;
                }
            }
        }
        return str;
    }

    public static int skipWhitespace(int i, String str) {
        int length = str.length();
        while (i < length) {
            if (!isWhiteSpace(str.charAt(i))) {
                return i;
            }
            i++;
        }
        return length;
    }

    public static String[] split(String str, char[] cArr, boolean z) {
        if (str == null || str.length() == 0) {
            return new String[0];
        }
        int iCountChar = countChar(str, cArr, z);
        if (iCountChar == 0) {
            return new String[]{str};
        }
        int i = iCountChar + 1;
        String[] strArr = new String[i];
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i2 = 0;
        boolean z2 = false;
        for (int i3 = 0; i3 < length; i3++) {
            char cCharAt = str.charAt(i3);
            if (!contains(cArr, cCharAt)) {
                sb.append(cCharAt);
                z2 = false;
            } else if (!z2 || !z) {
                strArr[i2] = sb.toString();
                sb = new StringBuilder();
                i2++;
                z2 = true;
            }
        }
        if (i2 < i) {
            strArr[i2] = sb.toString();
        }
        return strArr;
    }

    public static boolean startsWith(String str, char c) {
        return !isEmpty(str) && str.charAt(0) == c;
    }

    public static String toLowercase(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            char lowercase = toLowercase(c);
            if (c != lowercase) {
                charArray[i] = lowercase;
                z = true;
            }
        }
        return !z ? str : new String(charArray);
    }

    public static String toString(String str, Iterator<?> it, int i, int i2) {
        if (it == null) {
            return PsiKeyword.NULL;
        }
        if (i < 0 && i2 >= 0) {
            i = i2;
        }
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        while (it.hasNext() && (i < 0 || i3 < i)) {
            if (i3 != 0) {
                sb.append(str);
            }
            sb.append(it.next());
            i3++;
        }
        if (i < 0) {
            i2 = i3;
        }
        StringBuilder sb2 = new StringBuilder(sb.length() + 20);
        if (i2 >= 0) {
            sb2.append("size=");
            sb2.append(i2);
            sb2.append(' ');
        }
        sb2.append('[');
        sb2.append(sb.toString());
        if (i3 < i2) {
            sb2.append(" ... ");
        }
        sb2.append(']');
        return sb2.toString();
    }

    public static void toStringSort(List<?> list) {
        if (list == null || list.size() < 2) {
            return;
        }
        list.sort(CompareUtil.getToStringComparator());
    }

    public static String toUpperCase(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            char upperCase = toUpperCase(c);
            if (c != upperCase) {
                charArray[i] = upperCase;
                z = true;
            }
        }
        return !z ? str : new String(charArray);
    }

    public static String trailZeros(long j, int i) {
        boolean z;
        if (j < 0) {
            j = -j;
            z = true;
        } else {
            z = false;
        }
        String string = Long.toString(j);
        String strAppend = append(string, '0', i - string.length(), true);
        if (!z) {
            return strAppend;
        }
        return "-" + strAppend;
    }

    public static String trimASCII(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length != 0) {
            int i = 0;
            for (int i2 = 0; i2 < length && isWhiteSpace(str.charAt(i2)); i2++) {
                i++;
            }
            if (i == length) {
                return EMPTY;
            }
            int i3 = length;
            for (int i4 = length - 1; i4 > i && isWhiteSpace(str.charAt(i4)); i4--) {
                i3--;
            }
            if (i != 0 || i3 != length) {
                return str.substring(0, i3);
            }
        }
        return str;
    }

    public static String trimEnd(String str, char c) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        int i = length - 1;
        while (i >= 0 && str.charAt(i) == c) {
            i--;
        }
        int i2 = i + 1;
        return i2 == length ? str : str.substring(0, i2);
    }

    public static String trimStart(String str, char c) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        int i = 0;
        while (i < length && str.charAt(i) == c) {
            i++;
        }
        return i == 0 ? str : str.substring(i);
    }

    public static int skipWhitespace(String str) {
        return skipWhitespace(0, str);
    }

    public static boolean isWhiteSpace(String str) {
        return str == null || skipWhitespace(str) == str.length();
    }

    public static boolean isAz(char c) {
        if (c < 'A' || c > 'Z') {
            return c >= 'a' && c <= 'z';
        }
        return true;
    }

    public static boolean isAzOrDigits(char c) {
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        if (c < 'a' || c > 'z') {
            return c >= '0' && c <= '9';
        }
        return true;
    }

    public static boolean isAzOrDigitsOr(char c, char[] cArr) {
        return isAzOrDigits(c) || contains(cArr, c);
    }

    public static boolean isLowerAZ(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean isUpperAZ(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static char toLowercase(char c) {
        return (c > 'Z' || c < 'A') ? c : (char) (c + ' ');
    }

    public static char toUpperCase(char c) {
        return (c > 'z' || c < 'a') ? c : (char) (c - ' ');
    }

    public static int countChar(String str, char c) {
        if (str == null) {
            return 0;
        }
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.charAt(i2) == c) {
                i++;
            }
        }
        return i;
    }

    public static String join(Object[] objArr, Object obj) {
        return join((Iterator<?>) ArrayIterator.of(objArr), obj);
    }

    public static String join(Iterable<?> iterable, Object obj) {
        return join(iterable.iterator(), obj);
    }

    public static int countChar(String str, char c, boolean z) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int length = str.length();
        int i = 0;
        boolean z2 = false;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.charAt(i2) != c) {
                z2 = false;
            } else if (!z2 || !z) {
                i++;
                z2 = true;
            }
        }
        return i;
    }

    public static String replaceAll(String str, char c, String str2) {
        int length;
        if (str != null && str2 != null && (length = str.length()) != 0) {
            int i = 0;
            if (length == 1) {
                if (c == str.charAt(0)) {
                    return str2;
                }
            } else {
                if (str2.length() == 1) {
                    return str.replace(c, str2.charAt(0));
                }
                StringBuilder sb = null;
                while (true) {
                    int iIndexOf = str.indexOf(c, i);
                    if (iIndexOf < 0) {
                        break;
                    }
                    if (sb == null) {
                        sb = new StringBuilder((str2.length() * 5) + length);
                    }
                    sb.append((CharSequence) str, i, iIndexOf);
                    sb.append(str2);
                    i = iIndexOf + 1;
                }
                if (i != 0) {
                    sb.append(str.substring(i));
                    return sb.toString();
                }
            }
        }
        return str;
    }

    public static String[] split(String str, char[] cArr) {
        return split(str, cArr, true);
    }

    public static String[] split(String str, char c) {
        return split(str, c, true);
    }

    public static String[] split(String str, char c, boolean z) {
        if (str != null && str.length() != 0) {
            int iCountChar = countChar(str, c, z);
            if (iCountChar == 0) {
                return new String[]{str};
            }
            int i = iCountChar + 1;
            String[] strArr = new String[i];
            StringBuilder sb = new StringBuilder();
            int length = str.length();
            int i2 = 0;
            boolean z2 = false;
            for (int i3 = 0; i3 < length; i3++) {
                char cCharAt = str.charAt(i3);
                if (cCharAt != c) {
                    sb.append(cCharAt);
                    z2 = false;
                } else if (!z2 || !z) {
                    strArr[i2] = sb.toString();
                    sb = new StringBuilder();
                    i2++;
                    z2 = true;
                }
            }
            if (i2 < i) {
                strArr[i2] = sb.toString();
            }
            return strArr;
        }
        return new String[0];
    }

    public static String replaceAll(String str, String str2, char c) {
        if (str != null && str2 != null) {
            int length = str.length();
            int length2 = str2.length();
            if (length != 0 && length2 != 0 && length2 <= length) {
                if (length != length2) {
                    int i = 0;
                    StringBuilder sb = null;
                    while (true) {
                        int iIndexOf = str.indexOf(str2, i);
                        if (iIndexOf < 0) {
                            break;
                        }
                        if (sb == null) {
                            sb = new StringBuilder(length + 10);
                        }
                        sb.append((CharSequence) str, i, iIndexOf);
                        sb.append(c);
                        i = iIndexOf + length2;
                    }
                    if (i != 0) {
                        sb.append(str.substring(i));
                        return sb.toString();
                    }
                } else if (str.equals(str2)) {
                    return String.valueOf(c);
                }
            }
        }
        return str;
    }

    public static String toString(Collection<?> collection, int i) {
        return toString(collection.iterator(), i, collection.size());
    }

    public static String toString(Iterator<?> it, int i, int i2) {
        return toString(", ", it, i, i2);
    }

    public static String toString(Collection<?> collection) {
        return toString(collection, 5);
    }

    public static String toString(Object[] objArr) {
        return toString(objArr, 5);
    }

    public static String toString(Object[] objArr, int i) {
        if (objArr == null) {
            return PsiKeyword.NULL;
        }
        StringBuilder sb = new StringBuilder("length=");
        sb.append(objArr.length);
        sb.append(" [");
        if (i < 0 || i > objArr.length) {
            i = objArr.length;
        }
        int i2 = 0;
        while (i2 < i) {
            if (i2 != 0) {
                sb.append(", ");
            }
            sb.append(objArr[i2]);
            i2++;
        }
        if (i2 < objArr.length) {
            sb.append(" ... ");
        }
        sb.append(']');
        return sb.toString();
    }
}
