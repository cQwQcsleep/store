package org.eclipse.jdt.core.compiler;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import org.eclipse.jdt.internal.compiler.lookup.TypeIds;
import org.eclipse.jdt.internal.compiler.parser.ScannerHelper;
import org.jcodings.transcode.TranscodeFunctions;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class CharOperation {
    public static final char[] NO_CHAR = new char[0];
    public static final char[][] NO_CHAR_CHAR = new char[0][];
    public static final String[] NO_STRINGS = new String[0];
    public static final char[] ALL_PREFIX = {'*'};
    public static final char[] COMMA_SEPARATOR = {','};
    private static final int[] EMPTY_REGIONS = new int[0];

    public static final char[] append(char[] cArr, char[] cArr2) {
        if (cArr2 != null && cArr2.length != 0) {
            int length = cArr.length;
            int length2 = cArr2.length;
            int i = length + length2;
            if (i > length) {
                char[] cArr3 = new char[i];
                System.arraycopy(cArr, 0, cArr3, 0, length);
                cArr = cArr3;
            }
            System.arraycopy(cArr2, 0, cArr, length, length2);
        }
        return cArr;
    }

    public static final char[][] arrayConcat(char[][] cArr, char[] cArr2) {
        if (cArr2 == null) {
            return cArr;
        }
        if (cArr == null) {
            return new char[][]{cArr2};
        }
        int length = cArr.length;
        char[][] cArr3 = new char[length + 1][];
        System.arraycopy(cArr, 0, cArr3, 0, length);
        cArr3[length] = cArr2;
        return cArr3;
    }

    public static final boolean camelCaseMatch(char[] cArr, int i, int i2, char[] cArr2, int i3, int i4, boolean z) {
        if (cArr2 == null) {
            return false;
        }
        if (cArr == null) {
            return true;
        }
        if (i2 < 0) {
            i2 = cArr.length;
        }
        if (i4 < 0) {
            i4 = cArr2.length;
        }
        if (i2 <= i) {
            return i4 <= i3;
        }
        if (i4 <= i3 || cArr2[i3] != cArr[i]) {
            return false;
        }
        while (true) {
            i++;
            i3++;
            if (i == i2) {
                if (!z || i3 == i4) {
                    return true;
                }
                while (i3 != i4) {
                    char c = cArr2[i3];
                    if (c < 128) {
                        if ((ScannerHelper.OBVIOUS_IDENT_CHAR_NATURES[c] & 32) != 0) {
                            return false;
                        }
                    } else if (!Character.isJavaIdentifierPart(c) || Character.isUpperCase(c)) {
                        return false;
                    }
                    i3++;
                }
                return true;
            }
            if (i3 == i4) {
                return false;
            }
            char c2 = cArr[i];
            if (c2 != cArr2[i3]) {
                if (c2 < 128) {
                    if ((ScannerHelper.OBVIOUS_IDENT_CHAR_NATURES[c2] & 36) == 0) {
                        return false;
                    }
                } else if (Character.isJavaIdentifierPart(c2) && !Character.isUpperCase(c2) && !Character.isDigit(c2)) {
                    return false;
                }
                while (i3 != i4) {
                    char c3 = cArr2[i3];
                    if (c3 < 128) {
                        int i5 = ScannerHelper.OBVIOUS_IDENT_CHAR_NATURES[c3];
                        if ((i5 & TranscodeFunctions.EMACS_MULE_LEADING_CODE_JISX0208_1978) != 0) {
                            continue;
                        } else if ((i5 & 4) != 0) {
                            if (c2 == c3) {
                            }
                        } else if (c2 != c3) {
                            return false;
                        }
                        i3++;
                    } else {
                        if (!Character.isJavaIdentifierPart(c3) || Character.isUpperCase(c3)) {
                            if (Character.isDigit(c3)) {
                                if (c2 == c3) {
                                }
                            } else if (c2 != c3) {
                                return false;
                            }
                        }
                        i3++;
                    }
                }
                return false;
            }
        }
    }

    public static String[] charArrayToStringArray(char[][] cArr) {
        if (cArr == null) {
            return null;
        }
        int length = cArr.length;
        if (length == 0) {
            return NO_STRINGS;
        }
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = new String(cArr[i]);
        }
        return strArr;
    }

    public static String charToString(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        return new String(cArr);
    }

    private static final boolean checkSubstringMatch(char[] cArr, char[] cArr2) {
        for (int i = 0; i < (cArr2.length - cArr.length) + 1; i++) {
            for (int i2 = 0; i2 < cArr.length; i2++) {
                int i3 = i + i2;
                if (Character.toLowerCase(cArr2[i3]) != Character.toLowerCase(cArr[i2])) {
                    char c = cArr2[i3];
                    if (c != '(' && c != ':') {
                        break;
                    }
                    return false;
                }
                if (i2 == cArr.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final int compareTo(char[] cArr, char[] cArr2, int i, int i2) {
        int length = cArr.length;
        int length2 = cArr2.length;
        int iMin = Math.min(Math.min(length, length2), i2);
        while (i < iMin) {
            char c = cArr[i];
            char c2 = cArr2[i];
            if (c != c2) {
                return c - c2;
            }
            i++;
        }
        return length - length2;
    }

    public static final int compareWith(char[] cArr, char[] cArr2) {
        int length = cArr.length;
        int length2 = cArr2.length;
        int iMin = Math.min(length, length2);
        int i = 0;
        while (true) {
            int i2 = iMin - 1;
            if (iMin == 0) {
                return length2 == i ? 0 : -1;
            }
            char c = cArr[i];
            int i3 = i + 1;
            char c2 = cArr2[i];
            if (c != c2) {
                return c - c2;
            }
            iMin = i2;
            i = i3;
        }
    }

    public static final char[] concat(char[] cArr, char c, char[] cArr2, char c2, char[] cArr3) {
        if (cArr == null) {
            return concat(cArr2, cArr3, c2);
        }
        if (cArr2 == null) {
            return concat(cArr, cArr3, c);
        }
        if (cArr3 == null) {
            return concat(cArr, cArr2, c);
        }
        int length = cArr.length;
        int length2 = cArr2.length;
        int length3 = cArr3.length;
        int i = length + length2;
        char[] cArr4 = new char[i + length3 + 2];
        System.arraycopy(cArr, 0, cArr4, 0, length);
        cArr4[length] = c;
        System.arraycopy(cArr2, 0, cArr4, length + 1, length2);
        cArr4[i + 1] = c2;
        System.arraycopy(cArr3, 0, cArr4, i + 2, length3);
        return cArr4;
    }

    public static final char[] concatAll(char[] cArr, char[] cArr2, char c) {
        if (cArr == null) {
            return cArr2;
        }
        if (cArr2 == null) {
            return cArr;
        }
        int length = cArr.length;
        if (length == 0) {
            return cArr2;
        }
        int length2 = cArr2.length;
        char[] cArr3 = new char[length + length2 + 1];
        System.arraycopy(cArr, 0, cArr3, 0, length);
        cArr3[length] = c;
        if (length2 > 0) {
            System.arraycopy(cArr2, 0, cArr3, length + 1, length2);
        }
        return cArr3;
    }

    public static final char[] concatNonEmpty(char[] cArr, char c, char[] cArr2, char c2, char[] cArr3) {
        if (cArr == null || cArr.length == 0) {
            return concatNonEmpty(cArr2, cArr3, c2);
        }
        if (cArr2 == null || cArr2.length == 0) {
            return concatNonEmpty(cArr, cArr3, c);
        }
        return (cArr3 == null || cArr3.length == 0) ? concatNonEmpty(cArr, cArr2, c) : concat(cArr, c, cArr2, c2, cArr3);
    }

    public static final char[] concatWith(char[][] cArr, char[] cArr2, char c) {
        int length = cArr2 == null ? 0 : cArr2.length;
        if (length == 0) {
            return concatWith(cArr, c);
        }
        int length2 = cArr == null ? 0 : cArr.length;
        if (length2 == 0) {
            return cArr2;
        }
        int length3 = length;
        int i = length2;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            char[] cArr3 = cArr[i];
            if (cArr3.length > 0) {
                length3 += cArr3.length + 1;
            }
        }
        char[] cArr4 = new char[length3];
        int i2 = 0;
        for (int i3 = 0; i3 < length2; i3++) {
            char[] cArr5 = cArr[i3];
            int length4 = cArr5.length;
            if (length4 > 0) {
                System.arraycopy(cArr5, 0, cArr4, i2, length4);
                int i4 = i2 + length4;
                cArr4[i4] = c;
                i2 = i4 + 1;
            }
        }
        System.arraycopy(cArr2, 0, cArr4, i2, length);
        return cArr4;
    }

    public static final char[] concatWithAll(char[][] cArr, char c) {
        int length = cArr == null ? 0 : cArr.length;
        if (length == 0) {
            return NO_CHAR;
        }
        int length2 = length - 1;
        int i = length;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            length2 += cArr[i].length;
        }
        char[] cArr2 = new char[length2];
        while (true) {
            length--;
            if (length < 0) {
                return cArr2;
            }
            char[] cArr3 = cArr[length];
            int length3 = cArr3.length;
            if (length3 > 0) {
                length2 -= length3;
                System.arraycopy(cArr3, 0, cArr2, length2, length3);
            }
            length2--;
            if (length2 >= 0) {
                cArr2[length2] = c;
            }
        }
    }

    public static final boolean contains(char c, char[][] cArr) {
        int length = cArr.length;
        while (true) {
            length--;
            if (length < 0) {
                return false;
            }
            char[] cArr2 = cArr[length];
            int length2 = cArr2.length;
            do {
                length2--;
                if (length2 < 0) {
                }
            } while (cArr2[length2] != c);
            return true;
        }
    }

    public static boolean containsEqual(char[][] cArr, char[] cArr2) {
        for (char[] cArr3 : cArr) {
            if (equals(cArr3, cArr2)) {
                return true;
            }
        }
        return false;
    }

    public static final char[][] deepCopy(char[][] cArr) {
        int length = cArr.length;
        char[][] cArr2 = new char[length][];
        for (int i = 0; i < length; i++) {
            char[] cArr3 = cArr[i];
            int length2 = cArr3.length;
            char[] cArr4 = new char[length2];
            System.arraycopy(cArr3, 0, cArr4, 0, length2);
            cArr2[i] = cArr4;
        }
        return cArr2;
    }

    public static final boolean endsWith(char[] cArr, char[] cArr2) {
        int length = cArr2.length;
        int length2 = cArr.length - length;
        if (length2 < 0) {
            return false;
        }
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (cArr2[length] == cArr[length + length2]);
        return false;
    }

    public static final boolean equals(char[] cArr, char[] cArr2, int i, int i2, boolean z) {
        if (cArr == cArr2) {
            return true;
        }
        if (cArr == null || cArr2 == null || cArr.length != i2 - i) {
            return false;
        }
        if (z) {
            int length = cArr.length;
            do {
                length--;
                if (length < 0) {
                }
            } while (cArr[length] == cArr2[length + i]);
            return false;
        }
        int length2 = cArr.length;
        do {
            length2--;
            if (length2 < 0) {
            }
        } while (ScannerHelper.toLowerCase(cArr[length2]) == ScannerHelper.toLowerCase(cArr2[length2 + i]));
        return false;
        return true;
    }

    public static final boolean fragmentEquals(char[] cArr, char[] cArr2, int i, boolean z) {
        int length = cArr.length;
        if (cArr2.length < length + i) {
            return false;
        }
        if (z) {
            do {
                length--;
                if (length < 0) {
                    return true;
                }
            } while (cArr[length] == cArr2[length + i]);
            return false;
        }
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (ScannerHelper.toLowerCase(cArr[length]) == ScannerHelper.toLowerCase(cArr2[length + i]));
        return false;
    }

    public static final int[] getSubWordMatchingRegions(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        return str == null ? EMPTY_REGIONS : new SubwordMatcher(str2).getMatchingRegions(str);
    }

    public static final int hashCode(char[] cArr) {
        return Arrays.hashCode(cArr) & TypeIds.NoId;
    }

    public static final int indexOf(char[] cArr, char[] cArr2, boolean z, int i, int i2) {
        int length = cArr.length;
        if (length > i2 || i < 0) {
            return -1;
        }
        if (length == 0) {
            return 0;
        }
        if (length == i2) {
            if (z) {
                while (i < i2) {
                    if (cArr2[i] != cArr[i]) {
                        return -1;
                    }
                    i++;
                }
                return 0;
            }
            while (i < i2) {
                if (ScannerHelper.toLowerCase(cArr2[i]) != ScannerHelper.toLowerCase(cArr[i])) {
                    return -1;
                }
                i++;
            }
            return 0;
        }
        if (z) {
            int i3 = (i2 - length) + 1;
            while (i < i3) {
                if (cArr2[i] == cArr[0]) {
                    for (int i4 = 1; i4 < length; i4++) {
                        if (cArr2[i + i4] == cArr[i4]) {
                        }
                    }
                    return i;
                }
                i++;
            }
        } else {
            int i5 = (i2 - length) + 1;
            while (i < i5) {
                if (ScannerHelper.toLowerCase(cArr2[i]) == ScannerHelper.toLowerCase(cArr[0])) {
                    for (int i6 = 1; i6 < length; i6++) {
                        if (ScannerHelper.toLowerCase(cArr2[i + i6]) == ScannerHelper.toLowerCase(cArr[i6])) {
                        }
                    }
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    public static boolean isWhitespace(char c) {
        return c < 128 && (ScannerHelper.OBVIOUS_IDENT_CHAR_NATURES[c] & 256) != 0;
    }

    public static final int lastIndexOf(char c, char[] cArr) {
        int length = cArr.length;
        do {
            length--;
            if (length < 0) {
                return -1;
            }
        } while (c != cArr[length]);
        return length;
    }

    public static final char[] lastSegment(char[] cArr, char c) {
        int iLastIndexOf = lastIndexOf(c, cArr);
        return iLastIndexOf < 0 ? cArr : subarray(cArr, iLastIndexOf + 1, cArr.length);
    }

    public static final boolean match(char[] cArr, int i, int i2, char[] cArr2, int i3, int i4, boolean z) {
        int i5;
        if (cArr2 == null) {
            return false;
        }
        if (cArr == null) {
            return true;
        }
        if (i2 < 0) {
            i2 = cArr.length;
        }
        if (i4 < 0) {
            i4 = cArr2.length;
        }
        while (i != i2) {
            char c = cArr[i];
            if (c == '*') {
                if (c == '*') {
                    i++;
                    i5 = i;
                } else {
                    i5 = 0;
                }
                int i6 = i3;
                while (i3 < i4) {
                    if (i != i2) {
                        char c2 = cArr[i];
                        if (c2 == '*') {
                            i5 = i + 1;
                            if (i5 == i2) {
                                return true;
                            }
                            i6 = i3;
                            i = i5;
                        } else {
                            if ((z ? cArr2[i3] : ScannerHelper.toLowerCase(cArr2[i3])) == c2 || c2 == '?') {
                                i3++;
                                i++;
                            }
                        }
                    }
                    i6++;
                    i = i5;
                    i3 = i6;
                }
                return i5 == i2 || (i3 == i4 && i == i2) || (i == i2 - 1 && cArr[i] == '*');
            }
            if (i3 == i4) {
                return false;
            }
            if (c != (z ? cArr2[i3] : ScannerHelper.toLowerCase(cArr2[i3])) && c != '?') {
                return false;
            }
            i3++;
            i++;
        }
        return i3 == i4;
    }

    public static final int occurencesOf(char c, char[] cArr) {
        int i = 0;
        for (char c2 : cArr) {
            if (c == c2) {
                i++;
            }
        }
        return i;
    }

    public static final int parseInt(char[] cArr, int i, int i2) throws NumberFormatException {
        if (i2 != 1) {
            return Integer.parseInt(new String(cArr, i, i2));
        }
        int i3 = cArr[i] - '0';
        if (i3 < 0 || i3 > 9) {
            throw new NumberFormatException("invalid digit");
        }
        return i3;
    }

    /* JADX WARN: Code duplicated, block: B:95:0x00d7 A[PHI: r2 r15
      0x00d7: PHI (r2v14 int) = (r2v12 int), (r2v18 int) binds: [B:94:0x00d5, B:121:0x011c] A[DONT_GENERATE, DONT_INLINE]
      0x00d7: PHI (r15v6 int) = (r15v4 int), (r15v11 int) binds: [B:94:0x00d5, B:121:0x011c] A[DONT_GENERATE, DONT_INLINE]] */
    public static final boolean pathMatch(char[] cArr, char[] cArr2, boolean z, char c) {
        int i;
        int i2;
        if (cArr2 == null) {
            return false;
        }
        if (cArr == null) {
            return true;
        }
        int i3 = cArr[0] == c ? 1 : 0;
        int length = cArr.length;
        int iIndexOf = indexOf(c, cArr, i3 + 1);
        if (iIndexOf < 0) {
            iIndexOf = length;
        }
        boolean z2 = cArr[length + (-1)] == c;
        int length2 = cArr2.length;
        int i4 = cArr2[0] != c ? 0 : 1;
        if (i4 != i3) {
            return false;
        }
        int iIndexOf2 = indexOf(c, cArr2, i4 + 1);
        if (iIndexOf2 < 0) {
            iIndexOf2 = length2;
        }
        while (i3 < length && ((iIndexOf != length || !z2) && (iIndexOf != i3 + 2 || cArr[i3] != '*' || cArr[i3 + 1] != '*'))) {
            if (i4 >= length2 || !match(cArr, i3, iIndexOf, cArr2, i4, iIndexOf2, z)) {
                return false;
            }
            i3 = iIndexOf + 1;
            iIndexOf = indexOf(c, cArr, i3);
            if (iIndexOf < 0) {
                iIndexOf = length;
            }
            i4 = iIndexOf2 + 1;
            iIndexOf2 = indexOf(c, cArr2, i4);
            if (iIndexOf2 < 0) {
                iIndexOf2 = length2;
            }
        }
        if ((i3 >= length && z2) || (iIndexOf == i3 + 2 && cArr[i3] == '*' && cArr[i3 + 1] == '*')) {
            i3 = iIndexOf + 1;
            int iIndexOf3 = indexOf(c, cArr, i3);
            if (iIndexOf3 < 0) {
                iIndexOf3 = length;
            }
            i = iIndexOf3;
            i2 = i3;
        } else {
            if (i3 >= length) {
                return i4 >= length2;
            }
            i = iIndexOf;
            i2 = 0;
        }
        int i5 = i2;
        int i6 = i4;
        int iIndexOf4 = i;
        while (i4 < length2) {
            if (i3 >= length) {
                if (z2) {
                    return true;
                }
                int iIndexOf5 = indexOf(c, cArr, i5);
                iIndexOf4 = iIndexOf5 < 0 ? length : iIndexOf5;
                int iIndexOf6 = indexOf(c, cArr2, i6 + 1);
                i6 = iIndexOf6 < 0 ? length2 : iIndexOf6 + 1;
                iIndexOf2 = indexOf(c, cArr2, i6);
                if (iIndexOf2 < 0) {
                    iIndexOf2 = length2;
                }
                i3 = i5;
                i4 = i6;
            } else if (iIndexOf4 == i3 + 2 && cArr[i3] == '*' && cArr[i3 + 1] == '*') {
                i5 = iIndexOf4 + 1;
                int iIndexOf7 = indexOf(c, cArr, i5);
                iIndexOf4 = iIndexOf7 < 0 ? length : iIndexOf7;
                if (i5 >= length) {
                    return true;
                }
                i6 = i4;
                i3 = i5;
            } else if (match(cArr, i3, iIndexOf4, cArr2, i4, iIndexOf2, z)) {
                i3 = iIndexOf4 + 1;
                iIndexOf4 = indexOf(c, cArr, i3);
                if (iIndexOf4 < 0) {
                    iIndexOf4 = length;
                }
                i4 = iIndexOf2 + 1;
                iIndexOf2 = indexOf(c, cArr2, i4);
                if (iIndexOf2 < 0) {
                    iIndexOf2 = length2;
                }
            } else {
                int iIndexOf8 = indexOf(c, cArr, i5);
                iIndexOf4 = iIndexOf8 < 0 ? length : iIndexOf8;
                int iIndexOf9 = indexOf(c, cArr2, i6 + 1);
                i6 = iIndexOf9 < 0 ? length2 : iIndexOf9 + 1;
                iIndexOf2 = indexOf(c, cArr2, i6);
                if (iIndexOf2 < 0) {
                    iIndexOf2 = length2;
                }
                i3 = i5;
                i4 = i6;
            }
        }
        return i5 >= iIndexOf4 || (i4 >= length2 && i3 >= length) || ((i3 == length + (-2) && cArr[i3] == '*' && cArr[i3 + 1] == '*') || (i3 == length && z2));
    }

    public static final boolean prefixEquals(char[] cArr, char[] cArr2, boolean z, int i) {
        int length = cArr.length;
        if (cArr2.length - i < length) {
            return false;
        }
        if (z) {
            do {
                length--;
                if (length < 0) {
                    return true;
                }
            } while (cArr[length] == cArr2[i + length]);
            return false;
        }
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (ScannerHelper.toLowerCase(cArr[length]) == ScannerHelper.toLowerCase(cArr2[i + length]));
        return false;
    }

    public static final char[] prepend(char c, char[] cArr) {
        if (cArr == null) {
            return new char[]{c};
        }
        int length = cArr.length;
        char[] cArr2 = new char[length + 1];
        System.arraycopy(cArr, 0, cArr2, 1, length);
        cArr2[0] = c;
        return cArr2;
    }

    public static final char[] remove(char[] cArr, char c) {
        char[] cArr2 = null;
        if (cArr == null) {
            return null;
        }
        int length = cArr.length;
        if (length != 0) {
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                char c2 = cArr[i2];
                if (c2 == c) {
                    if (cArr2 == null) {
                        cArr2 = new char[length];
                        System.arraycopy(cArr, 0, cArr2, 0, i2);
                        i = i2;
                    }
                } else if (cArr2 != null) {
                    cArr2[i] = c2;
                    i++;
                }
            }
            if (cArr2 != null) {
                char[] cArr3 = new char[i];
                System.arraycopy(cArr2, 0, cArr3, 0, i);
                return cArr3;
            }
        }
        return cArr;
    }

    public static final char[] replace(char[] cArr, char[] cArr2, char[] cArr3) {
        int i;
        int length = cArr.length;
        int length2 = cArr2.length;
        int length3 = cArr3.length;
        int[] iArr = new int[5];
        if (equals(cArr2, cArr3)) {
            i = 0;
        } else {
            int i2 = 0;
            i = 0;
            while (i2 < length) {
                int iIndexOf = indexOf(cArr2, cArr, true, i2);
                if (iIndexOf == -1) {
                    i2++;
                } else {
                    if (i == iArr.length) {
                        int[] iArr2 = new int[i * 2];
                        System.arraycopy(iArr, 0, iArr2, 0, i);
                        iArr = iArr2;
                    }
                    iArr[i] = iIndexOf;
                    i++;
                    i2 = iIndexOf + length2;
                }
            }
        }
        if (i == 0) {
            return cArr;
        }
        char[] cArr4 = new char[((length3 - length2) * i) + length];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            int i6 = iArr[i5] - i3;
            System.arraycopy(cArr, i3, cArr4, i4, i6);
            int i7 = i4 + i6;
            System.arraycopy(cArr3, 0, cArr4, i7, length3);
            i3 = i3 + i6 + length2;
            i4 = i7 + length3;
        }
        System.arraycopy(cArr, i3, cArr4, i4, length - i3);
        return cArr4;
    }

    public static final char[] replaceOnCopy(char[] cArr, char c, char c2) {
        int length = cArr.length;
        char[] cArr2 = null;
        for (int i = 0; i < length; i++) {
            char c3 = cArr[i];
            if (c3 == c) {
                if (cArr2 == null) {
                    cArr2 = new char[length];
                    System.arraycopy(cArr, 0, cArr2, 0, i);
                }
                cArr2[i] = c2;
            } else if (cArr2 != null) {
                cArr2[i] = c3;
            }
        }
        return cArr2 == null ? cArr : cArr2;
    }

    public static final char[][] splitAndTrimOn(char c, char[] cArr) {
        int length = cArr == null ? 0 : cArr.length;
        if (length == 0) {
            return NO_CHAR_CHAR;
        }
        int i = 1;
        for (int i2 = 0; i2 < length; i2++) {
            if (cArr[i2] == c) {
                i++;
            }
        }
        char[][] cArr2 = new char[i][];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            if (cArr[i5] == c) {
                int i6 = i5 - 1;
                while (i3 < i5 && cArr[i3] == ' ') {
                    i3++;
                }
                while (i6 > i3 && cArr[i6] == ' ') {
                    i6--;
                }
                int i7 = (i6 - i3) + 1;
                char[] cArr3 = new char[i7];
                cArr2[i4] = cArr3;
                i4++;
                System.arraycopy(cArr, i3, cArr3, 0, i7);
                i3 = i5 + 1;
            }
        }
        int i8 = length - 1;
        while (i3 < length && cArr[i3] == ' ') {
            i3++;
        }
        while (i8 > i3 && cArr[i8] == ' ') {
            i8--;
        }
        int i9 = (i8 - i3) + 1;
        char[] cArr4 = new char[i9];
        cArr2[i4] = cArr4;
        System.arraycopy(cArr, i3, cArr4, 0, i9);
        return cArr2;
    }

    public static final char[][] splitOn(char c, char[] cArr, int i, int i2) {
        if ((cArr == null ? 0 : cArr.length) == 0 || i > i2) {
            return NO_CHAR_CHAR;
        }
        int i3 = 1;
        for (int i4 = i; i4 < i2; i4++) {
            if (cArr[i4] == c) {
                i3++;
            }
        }
        char[][] cArr2 = new char[i3][];
        int i5 = i;
        int i6 = 0;
        while (i < i2) {
            if (cArr[i] == c) {
                int i7 = i - i5;
                char[] cArr3 = new char[i7];
                cArr2[i6] = cArr3;
                i6++;
                System.arraycopy(cArr, i5, cArr3, 0, i7);
                i5 = i + 1;
            }
            i++;
        }
        int i8 = i2 - i5;
        char[] cArr4 = new char[i8];
        cArr2[i6] = cArr4;
        System.arraycopy(cArr, i5, cArr4, 0, i8);
        return cArr2;
    }

    public static final char[][] splitOnWithEnclosures(char c, char c2, char c3, char[] cArr, int i, int i2) {
        if ((cArr == null ? 0 : cArr.length) == 0 || i > i2) {
            return NO_CHAR_CHAR;
        }
        int i3 = 0;
        int i4 = 1;
        for (int i5 = i; i5 < i2; i5++) {
            char c4 = cArr[i5];
            if (c4 == c2) {
                i3++;
            } else if (c4 == c) {
                i4++;
            }
        }
        if (i3 == 0) {
            return splitOn(c, cArr, i, i2);
        }
        if (c2 == c || c3 == c) {
            return NO_CHAR_CHAR;
        }
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i4, 2);
        int i6 = i;
        int i7 = i6;
        int i8 = 0;
        int i9 = 0;
        while (i < i2) {
            char c5 = cArr[i];
            if (c5 == c2) {
                i8++;
            } else if (c5 == c3) {
                if (i8 > 0) {
                    i8--;
                }
            } else if (c5 == c && i8 == 0) {
                int[] iArr2 = iArr[i9];
                iArr2[0] = i7;
                i9++;
                iArr2[1] = i;
                i7 = i + 1;
                i6 = i;
            }
            i++;
        }
        if (i6 < i2 - 1) {
            int[] iArr3 = iArr[i9];
            iArr3[0] = i7;
            i9++;
            iArr3[1] = i2;
        }
        int i10 = i9;
        char[][] cArr2 = new char[i10][];
        for (int i11 = 0; i11 < i10; i11++) {
            int[] iArr4 = iArr[i11];
            int i12 = iArr4[0];
            int i13 = iArr4[1] - i12;
            char[] cArr3 = new char[i13];
            cArr2[i11] = cArr3;
            System.arraycopy(cArr, i12, cArr3, 0, i13);
        }
        return cArr2;
    }

    public static final boolean subWordMatch(char[] cArr, char[] cArr2) {
        if (cArr2 == null) {
            return false;
        }
        return cArr == null || getSubWordMatchingRegions(new String(cArr), new String(cArr2)) != null;
    }

    public static final char[][] subarray(char[][] cArr, int i, int i2) {
        if (i2 == -1) {
            i2 = cArr.length;
        }
        if (i > i2 || i < 0 || i2 > cArr.length) {
            return null;
        }
        int i3 = i2 - i;
        char[][] cArr2 = new char[i3][];
        System.arraycopy(cArr, i, cArr2, 0, i3);
        return cArr2;
    }

    public static final boolean substringMatch(String str, String str2) {
        if (str == null || str.length() == 0) {
            return true;
        }
        if (str2 == null) {
            return false;
        }
        return checkSubstringMatch(str.toCharArray(), str2.toCharArray());
    }

    public static char[][] toCharArrays(List<String> list) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        char[][] cArr = new char[size][];
        for (int i = 0; i < size; i++) {
            cArr[i] = list.get(i).toCharArray();
        }
        return cArr;
    }

    public static final char[] toLowerCase(char[] cArr) {
        char[] cArr2 = null;
        if (cArr == null) {
            return null;
        }
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            char c = cArr[i];
            char lowerCase = ScannerHelper.toLowerCase(c);
            if (c != lowerCase || cArr2 != null) {
                if (cArr2 == null) {
                    cArr2 = new char[length];
                    System.arraycopy(cArr, 0, cArr2, 0, i);
                }
                cArr2[i] = lowerCase;
            }
        }
        return cArr2 == null ? cArr : cArr2;
    }

    public static final String toString(char[][] cArr) {
        return new String(concatWith(cArr, '.'));
    }

    public static final String[] toStrings(char[][] cArr) {
        int length;
        if (cArr != null && (length = cArr.length) != 0) {
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = new String(cArr[i]);
            }
            return strArr;
        }
        return NO_STRINGS;
    }

    public static final char[] toUpperCase(char[] cArr) {
        char[] cArr2 = null;
        if (cArr == null) {
            return null;
        }
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            char c = cArr[i];
            char upperCase = ScannerHelper.toUpperCase(c);
            if (c != upperCase || cArr2 != null) {
                if (cArr2 == null) {
                    cArr2 = new char[length];
                    System.arraycopy(cArr, 0, cArr2, 0, i);
                }
                cArr2[i] = upperCase;
            }
        }
        return cArr2 == null ? cArr : cArr2;
    }

    public static final char[] trim(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        int length = cArr.length;
        int i = length - 1;
        int i2 = 0;
        while (i2 < length && cArr[i2] == ' ') {
            i2++;
        }
        int i3 = i;
        while (i3 > i2 && cArr[i3] == ' ') {
            i3--;
        }
        return (i2 == 0 && i3 == i) ? cArr : subarray(cArr, i2, i3 + 1);
    }

    public static final int lastIndexOf(char c, char[] cArr, int i) {
        int length = cArr.length;
        do {
            length--;
            if (length < i) {
                return -1;
            }
        } while (c != cArr[length]);
        return length;
    }

    public static final int lastIndexOf(char c, char[] cArr, int i, int i2) {
        do {
            i2--;
            if (i2 < i) {
                return -1;
            }
        } while (c != cArr[i2]);
        return i2;
    }

    public static final int occurencesOf(char c, char[] cArr, int i) {
        int i2 = 0;
        while (i < cArr.length) {
            if (c == cArr[i]) {
                i2++;
            }
            i++;
        }
        return i2;
    }

    public static final boolean contains(char c, char[] cArr) {
        int length = cArr.length;
        do {
            length--;
            if (length < 0) {
                return false;
            }
        } while (cArr[length] != c);
        return true;
    }

    public static final char[][] arrayConcat(char[][] cArr, char[][] cArr2) {
        if (cArr == null) {
            return cArr2;
        }
        if (cArr2 == null) {
            return cArr;
        }
        int length = cArr.length;
        int length2 = cArr2.length;
        char[][] cArr3 = new char[length + length2][];
        System.arraycopy(cArr, 0, cArr3, 0, length);
        System.arraycopy(cArr2, 0, cArr3, length, length2);
        return cArr3;
    }

    public static final char[] append(char[] cArr, char c) {
        if (cArr == null) {
            return new char[]{c};
        }
        int length = cArr.length;
        char[] cArr2 = new char[length + 1];
        System.arraycopy(cArr, 0, cArr2, 0, length);
        cArr2[length] = c;
        return cArr2;
    }

    public static final boolean contains(char[] cArr, char[] cArr2) {
        int length = cArr2.length;
        while (true) {
            length--;
            if (length < 0) {
                return false;
            }
            int length2 = cArr.length;
            do {
                length2--;
                if (length2 < 0) {
                }
            } while (cArr2[length] != cArr[length2]);
            return true;
        }
    }

    public static final char[] subarray(char[] cArr, int i, int i2) {
        if (i2 == -1) {
            i2 = cArr.length;
        }
        if (i > i2 || i < 0 || i2 > cArr.length) {
            return null;
        }
        int i3 = i2 - i;
        char[] cArr2 = new char[i3];
        System.arraycopy(cArr, i, cArr2, 0, i3);
        return cArr2;
    }

    public static final int compareTo(char[] cArr, char[] cArr2) {
        int length = cArr.length;
        int length2 = cArr2.length;
        int iMin = Math.min(length, length2);
        for (int i = 0; i < iMin; i++) {
            char c = cArr[i];
            char c2 = cArr2[i];
            if (c != c2) {
                return c - c2;
            }
        }
        return length - length2;
    }

    public static final char[] append(char[] cArr, int i, char[] cArr2, int i2, int i3) {
        int i4 = i3 - i2;
        int i5 = i4 + i;
        if (i5 > cArr.length) {
            char[] cArr3 = new char[i5 * 2];
            System.arraycopy(cArr, 0, cArr3, 0, i);
            cArr = cArr3;
        }
        System.arraycopy(cArr2, i2, cArr, i, i4);
        return cArr;
    }

    public static final boolean substringMatch(char[] cArr, char[] cArr2) {
        if (cArr == null || cArr.length == 0) {
            return true;
        }
        if (cArr2 == null) {
            return false;
        }
        return checkSubstringMatch(cArr, cArr2);
    }

    public static final char[] concatNonEmpty(char[] cArr, char[] cArr2, char c) {
        if (cArr == null || cArr.length == 0) {
            return cArr2;
        }
        return (cArr2 == null || cArr2.length == 0) ? cArr : concat(cArr, cArr2, c);
    }

    public static final boolean prefixEquals(char[] cArr, char[] cArr2, boolean z) {
        return prefixEquals(cArr, cArr2, z, 0);
    }

    public static final boolean prefixEquals(char[] cArr, char[] cArr2) {
        int length = cArr.length;
        if (cArr2.length < length) {
            return false;
        }
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (cArr[length] == cArr2[length]);
        return false;
    }

    public static final char[] concat(char[] cArr, char[] cArr2, char[] cArr3) {
        if (cArr == null) {
            return concat(cArr2, cArr3);
        }
        if (cArr2 == null) {
            return concat(cArr, cArr3);
        }
        if (cArr3 == null) {
            return concat(cArr, cArr2);
        }
        int length = cArr.length;
        int length2 = cArr2.length;
        int length3 = cArr3.length;
        int i = length + length2;
        char[] cArr4 = new char[i + length3];
        System.arraycopy(cArr, 0, cArr4, 0, length);
        System.arraycopy(cArr2, 0, cArr4, length, length2);
        System.arraycopy(cArr3, 0, cArr4, i, length3);
        return cArr4;
    }

    public static final boolean equals(char[][] cArr, char[][] cArr2, boolean z) {
        if (z) {
            return equals(cArr, cArr2);
        }
        if (cArr == cArr2) {
            return true;
        }
        if (cArr == null || cArr2 == null || cArr.length != cArr2.length) {
            return false;
        }
        int length = cArr.length;
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (equals(cArr[length], cArr2[length], false));
        return false;
    }

    public static final boolean equals(char[] cArr, char[] cArr2) {
        return Arrays.equals(cArr, cArr2);
    }

    public static final boolean equals(char[] cArr, char[] cArr2, int i, int i2) {
        return equals(cArr, cArr2, i, i2, true);
    }

    public static final boolean equals(char[][] cArr, char[][] cArr2) {
        if (cArr == cArr2) {
            return true;
        }
        if (cArr == null || cArr2 == null || cArr.length != cArr2.length) {
            return false;
        }
        int length = cArr.length;
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (equals(cArr[length], cArr2[length]));
        return false;
    }

    public static final char[][] splitOn(char c, char[] cArr) {
        int length = cArr == null ? 0 : cArr.length;
        if (length == 0) {
            return NO_CHAR_CHAR;
        }
        int i = 1;
        for (int i2 = 0; i2 < length; i2++) {
            if (cArr[i2] == c) {
                i++;
            }
        }
        char[][] cArr2 = new char[i][];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            if (cArr[i5] == c) {
                int i6 = i5 - i4;
                char[] cArr3 = new char[i6];
                cArr2[i3] = cArr3;
                i3++;
                System.arraycopy(cArr, i4, cArr3, 0, i6);
                i4 = i5 + 1;
            }
        }
        int i7 = length - i4;
        char[] cArr4 = new char[i7];
        cArr2[i3] = cArr4;
        System.arraycopy(cArr, i4, cArr4, 0, i7);
        return cArr2;
    }

    public static final char[] concat(char[] cArr, char[] cArr2, char c) {
        if (cArr == null) {
            return cArr2;
        }
        if (cArr2 != null) {
            int length = cArr.length;
            if (length == 0) {
                return cArr2;
            }
            int length2 = cArr2.length;
            if (length2 != 0) {
                char[] cArr3 = new char[length + length2 + 1];
                System.arraycopy(cArr, 0, cArr3, 0, length);
                cArr3[length] = c;
                System.arraycopy(cArr2, 0, cArr3, length + 1, length2);
                return cArr3;
            }
        }
        return cArr;
    }

    public static final char[] concatWith(char[] cArr, char[][] cArr2, char c) {
        int length = cArr == null ? 0 : cArr.length;
        if (length == 0) {
            return concatWith(cArr2, c);
        }
        int length2 = cArr2 == null ? 0 : cArr2.length;
        if (length2 == 0) {
            return cArr;
        }
        int length3 = length;
        int i = length2;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            char[] cArr3 = cArr2[i];
            if (cArr3.length > 0) {
                length3 += cArr3.length + 1;
            }
        }
        char[] cArr4 = new char[length3];
        for (int i2 = length2 - 1; i2 >= 0; i2--) {
            char[] cArr5 = cArr2[i2];
            int length4 = cArr5.length;
            if (length4 > 0) {
                int i3 = length3 - length4;
                System.arraycopy(cArr5, 0, cArr4, i3, length4);
                length3 = i3 - 1;
                cArr4[length3] = c;
            }
        }
        System.arraycopy(cArr, 0, cArr4, 0, length);
        return cArr4;
    }

    public static final boolean equals(char[] cArr, char[] cArr2, boolean z) {
        if (z) {
            return equals(cArr, cArr2);
        }
        if (cArr == cArr2) {
            return true;
        }
        if (cArr == null || cArr2 == null || cArr.length != cArr2.length) {
            return false;
        }
        int length = cArr.length;
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (ScannerHelper.toLowerCase(cArr[length]) == ScannerHelper.toLowerCase(cArr2[length]));
        return false;
    }

    public static final char[] concat(char[] cArr, char[] cArr2) {
        if (cArr == null) {
            return cArr2;
        }
        if (cArr2 == null) {
            return cArr;
        }
        int length = cArr.length;
        int length2 = cArr2.length;
        char[] cArr3 = new char[length + length2];
        System.arraycopy(cArr, 0, cArr3, 0, length);
        System.arraycopy(cArr2, 0, cArr3, length, length2);
        return cArr3;
    }

    public static final char[] concat(char c, char[] cArr, char c2) {
        if (cArr == null) {
            return new char[]{c, c2};
        }
        int length = cArr.length;
        char[] cArr2 = new char[length + 2];
        cArr2[0] = c;
        System.arraycopy(cArr, 0, cArr2, 1, length);
        cArr2[length + 1] = c2;
        return cArr2;
    }

    public static final char[] concatWith(char[][] cArr, char c) {
        int length = cArr == null ? 0 : cArr.length;
        if (length == 0) {
            return NO_CHAR;
        }
        int length2 = length - 1;
        int i = length;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            char[] cArr2 = cArr[i];
            length2 = cArr2.length == 0 ? length2 - 1 : length2 + cArr2.length;
        }
        if (length2 <= 0) {
            return NO_CHAR;
        }
        char[] cArr3 = new char[length2];
        while (true) {
            length--;
            if (length < 0) {
                return cArr3;
            }
            char[] cArr4 = cArr[length];
            int length3 = cArr4.length;
            if (length3 > 0) {
                int i2 = length2 - length3;
                System.arraycopy(cArr4, 0, cArr3, i2, length3);
                length2 = i2 - 1;
                if (length2 >= 0) {
                    cArr3[length2] = c;
                }
            }
        }
    }

    public static final void replace(char[] cArr, char[] cArr2, char c) {
        replace(cArr, cArr2, c, 0, cArr.length);
    }

    public static final void replace(char[] cArr, char[] cArr2, char c, int i, int i2) {
        while (true) {
            i2--;
            if (i2 < i) {
                return;
            }
            int length = cArr2.length;
            while (true) {
                length--;
                if (length < 0) {
                    break;
                } else if (cArr[i2] == cArr2[length]) {
                    cArr[i2] = c;
                }
            }
        }
    }

    public static final void replace(char[] cArr, char c, char c2) {
        if (c != c2) {
            int length = cArr.length;
            for (int i = 0; i < length; i++) {
                if (cArr[i] == c) {
                    cArr[i] = c2;
                }
            }
        }
    }

    public static final boolean match(char[] cArr, char[] cArr2, boolean z) {
        if (cArr2 == null) {
            return false;
        }
        if (cArr == null) {
            return true;
        }
        return match(cArr, 0, cArr.length, cArr2, 0, cArr2.length, z);
    }

    public static final int indexOf(char[] cArr, char[] cArr2, boolean z) {
        return indexOf(cArr, cArr2, z, 0);
    }

    public static final int indexOf(char[] cArr, char[] cArr2, boolean z, int i) {
        return indexOf(cArr, cArr2, z, i, cArr2.length);
    }

    public static final int indexOf(char c, char[] cArr) {
        return indexOf(c, cArr, 0);
    }

    public static final int indexOf(char c, char[] cArr, int i) {
        while (i < cArr.length) {
            if (c == cArr[i]) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static final int indexOf(char c, char[] cArr, int i, int i2) {
        while (i < i2) {
            if (c == cArr[i]) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static final boolean camelCaseMatch(char[] cArr, char[] cArr2, boolean z) {
        if (cArr == null) {
            return true;
        }
        if (cArr2 == null) {
            return false;
        }
        return camelCaseMatch(cArr, 0, cArr.length, cArr2, 0, cArr2.length, z);
    }

    public static final boolean camelCaseMatch(char[] cArr, int i, int i2, char[] cArr2, int i3, int i4) {
        return camelCaseMatch(cArr, i, i2, cArr2, i3, i4, false);
    }

    public static final boolean camelCaseMatch(char[] cArr, char[] cArr2) {
        if (cArr == null) {
            return true;
        }
        if (cArr2 == null) {
            return false;
        }
        return camelCaseMatch(cArr, 0, cArr.length, cArr2, 0, cArr2.length, false);
    }
}
