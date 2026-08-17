package com.intellij.util.text;

import androidx.collection.ScatterMapKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt;
import com.intellij.openapi.util.text.StringUtilRt;
import java.nio.CharBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class CharArrayUtil {
    /* JADX WARN: Code duplicated, block: B:32:0x0075  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 8 || i == 53 || i == 58) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 8 || i == 53 || i == 58) ? 2 : 3];
        if (i != 1 && i != 3) {
            switch (i) {
                case 5:
                    objArr[0] = "dst";
                    break;
                case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                case ScatterMapKt.ClonedMetadataCount /* 7 */:
                case 54:
                    objArr[0] = "seq";
                    break;
                case 8:
                case 53:
                case 58:
                    objArr[0] = "com/intellij/util/text/CharArrayUtil";
                    break;
                case 9:
                case 11:
                case 13:
                case 17:
                case 18:
                case 20:
                case 23:
                case 24:
                case 26:
                case 29:
                case 30:
                case 36:
                case 41:
                case 42:
                case 44:
                case 46:
                case 47:
                case 48:
                case 51:
                    objArr[0] = "buffer";
                    break;
                case 10:
                case 12:
                case 14:
                case 15:
                case 16:
                case 19:
                case 21:
                case 22:
                case 25:
                case 27:
                    objArr[0] = "chars";
                    break;
                case 28:
                case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                case 37:
                    objArr[0] = "s";
                    break;
                case 32:
                case 34:
                    objArr[0] = "s1";
                    break;
                case 33:
                case 35:
                    objArr[0] = "s2";
                    break;
                case 38:
                    objArr[0] = "buffer1";
                    break;
                case 39:
                    objArr[0] = "buffer2";
                    break;
                case 40:
                case 43:
                case 45:
                case 49:
                case 50:
                    objArr[0] = "pattern";
                    break;
                case 52:
                    objArr[0] = "charsSequence";
                    break;
                case 55:
                case 56:
                    objArr[0] = "text";
                    break;
                case 57:
                    objArr[0] = "sequence";
                    break;
                default:
                    objArr[0] = "src";
                    break;
            }
        } else {
            objArr[0] = "dst";
        }
        if (i == 8) {
            objArr[1] = "fromSequence";
        } else if (i == 53) {
            objArr[1] = "getIndents";
        } else if (i != 58) {
            objArr[1] = "com/intellij/util/text/CharArrayUtil";
        } else {
            objArr[1] = "createImmutableCharSequence";
        }
        switch (i) {
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "fromSequence";
                break;
            case 8:
            case 53:
            case 58:
                break;
            case 9:
            case 10:
            case 11:
            case 12:
            case 16:
            case 17:
                objArr[2] = "shiftForward";
                break;
            case 13:
            case 14:
                objArr[2] = "shiftForwardCarefully";
                break;
            case 15:
                objArr[2] = "isSuitable";
                break;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                objArr[2] = "shiftBackward";
                break;
            case 24:
            case 25:
                objArr[2] = "shiftForwardUntil";
                break;
            case 26:
            case 27:
                objArr[2] = "shiftBackwardUntil";
                break;
            case 28:
            case 29:
            case 30:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
                objArr[2] = "regionMatches";
                break;
            case 38:
            case 39:
                objArr[2] = "equals";
                break;
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
                objArr[2] = "indexOf";
                break;
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
                objArr[2] = "lastIndexOf";
                break;
            case 52:
                objArr[2] = "getIndents";
                break;
            case 54:
                objArr[2] = "containLineBreaks";
                break;
            case 55:
                objArr[2] = "isEmptyOrSpaces";
                break;
            case 56:
                objArr[2] = "readerFromCharSequence";
                break;
            case 57:
                objArr[2] = "createImmutableCharSequence";
                break;
            default:
                objArr[2] = "getChars";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 8 && i != 53 && i != 58) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    private static void assertRegionIndicesInRange(int i, int i2, int i3, int i4, int i5, int i6) {
        if (i2 < 0 || i2 > i3 || i3 > i || i5 < 0 || i5 > i6 || i6 > i4) {
            throw new IllegalArgumentException("Indices out of bounds: (" + i2 + ", " + i3 + ") of CharSequence length " + i + " vs (" + i5 + ", " + i6 + ") of CharSequence length " + i4);
        }
    }

    public static boolean containLineBreaks(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            return false;
        }
        while (i < i2) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt == '\n' || cCharAt == '\r') {
                return true;
            }
            i++;
        }
        return false;
    }

    public static ImmutableCharSequence createImmutableCharSequence(CharSequence charSequence) {
        if (charSequence == null) {
            $$$reportNull$$$0(57);
        }
        ImmutableText immutableTextValueOf = ImmutableText.valueOf((Object) charSequence);
        if (immutableTextValueOf == null) {
            $$$reportNull$$$0(58);
        }
        return immutableTextValueOf;
    }

    public static char[] fromSequence(CharSequence charSequence) {
        if (charSequence == null) {
            $$$reportNull$$$0(6);
        }
        char[] cArrFromSequenceWithoutCopying = fromSequenceWithoutCopying(charSequence);
        return cArrFromSequenceWithoutCopying != null ? (char[]) cArrFromSequenceWithoutCopying.clone() : fromSequence(charSequence, 0, charSequence.length());
    }

    public static char[] fromSequenceWithoutCopying(CharSequence charSequence) {
        if (charSequence instanceof CharSequenceBackedByArray) {
            return ((CharSequenceBackedByArray) charSequence).getChars();
        }
        if (!(charSequence instanceof CharBuffer)) {
            return null;
        }
        CharBuffer charBuffer = (CharBuffer) charSequence;
        if (charBuffer.hasArray() && !charBuffer.isReadOnly() && charBuffer.arrayOffset() == 0 && charBuffer.position() == 0) {
            return charBuffer.array();
        }
        return null;
    }

    public static void getChars(CharSequence charSequence, char[] cArr, int i, int i2, int i3) {
        if (charSequence == null) {
            $$$reportNull$$$0(4);
        }
        if (cArr == null) {
            $$$reportNull$$$0(5);
        }
        if (charSequence instanceof CharArrayExternalizable) {
            ((CharArrayExternalizable) charSequence).getChars(i, i3 + i, cArr, i2);
            return;
        }
        if (i3 >= 10) {
            if (charSequence instanceof String) {
                ((String) charSequence).getChars(i, i3 + i, cArr, i2);
                return;
            }
            if (charSequence instanceof CharBuffer) {
                CharBuffer charBuffer = (CharBuffer) charSequence;
                int iPosition = charBuffer.position();
                charBuffer.position(i + iPosition);
                charBuffer.get(cArr, i2, i3);
                charBuffer.position(iPosition);
                return;
            }
            if (charSequence instanceof CharSequenceBackedByArray) {
                ((CharSequenceBackedByArray) charSequence.subSequence(i, i3 + i)).getChars(cArr, i2);
                return;
            } else if (charSequence instanceof StringBuffer) {
                ((StringBuffer) charSequence).getChars(i, i3 + i, cArr, i2);
                return;
            } else if (charSequence instanceof StringBuilder) {
                ((StringBuilder) charSequence).getChars(i, i3 + i, cArr, i2);
                return;
            }
        }
        int i4 = i3 + i;
        int i5 = 0;
        while (i < i4 && i5 < cArr.length) {
            cArr[i5 + i2] = charSequence.charAt(i);
            i5++;
            i++;
        }
    }

    public static boolean regionMatches(CharSequence charSequence, int i, int i2, CharSequence charSequence2, int i3, int i4, boolean z) {
        int i5;
        int i6;
        if (charSequence == null) {
            $$$reportNull$$$0(34);
        }
        if (charSequence2 == null) {
            $$$reportNull$$$0(35);
        }
        if (z) {
            return regionMatches(charSequence, i, i2, charSequence2, i3, i4);
        }
        if (i2 - i != i4 - i3) {
            return false;
        }
        assertRegionIndicesInRange(charSequence.length(), i, i2, charSequence2.length(), i3, i4);
        while (i5 < i2) {
            if (!StringUtilRt.charsEqualIgnoreCase(charSequence.charAt(i5), charSequence2.charAt(i6))) {
                i5 = i;
                i6 = i3;
                return false;
            }
            i5 = i;
            i6 = i3;
            i5++;
            i6++;
        }
        i5 = i;
        i6 = i3;
        return true;
    }

    public static int shiftBackward(CharSequence charSequence, int i, int i2, String str) {
        if (charSequence == null) {
            $$$reportNull$$$0(20);
        }
        if (str == null) {
            $$$reportNull$$$0(21);
        }
        if (i2 >= charSequence.length()) {
            return i2;
        }
        while (i2 >= i) {
            char cCharAt = charSequence.charAt(i2);
            int i3 = 0;
            while (i3 < str.length() && cCharAt != str.charAt(i3)) {
                i3++;
            }
            if (i3 == str.length()) {
                break;
            }
            i2--;
        }
        return i2;
    }

    public static int shiftBackwardUntil(CharSequence charSequence, int i, String str) {
        if (charSequence == null) {
            $$$reportNull$$$0(26);
        }
        if (str == null) {
            $$$reportNull$$$0(27);
        }
        if (i >= charSequence.length()) {
            return i;
        }
        while (i >= 0) {
            char cCharAt = charSequence.charAt(i);
            int i2 = 0;
            while (i2 < str.length() && cCharAt != str.charAt(i2)) {
                i2++;
            }
            if (i2 < str.length()) {
                break;
            }
            i--;
        }
        return i;
    }

    public static int shiftForward(CharSequence charSequence, int i, int i2, String str) {
        if (charSequence == null) {
            $$$reportNull$$$0(11);
        }
        if (str == null) {
            $$$reportNull$$$0(12);
        }
        int iMin = Math.min(i2, charSequence.length());
        while (i < iMin) {
            char cCharAt = charSequence.charAt(i);
            int i3 = 0;
            while (i3 < str.length() && cCharAt != str.charAt(i3)) {
                i3++;
            }
            if (i3 >= str.length()) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static boolean containLineBreaks(CharSequence charSequence) {
        if (charSequence == null) {
            $$$reportNull$$$0(54);
        }
        return containLineBreaks(charSequence, 0, charSequence.length());
    }

    public static char[] fromSequence(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            $$$reportNull$$$0(7);
        }
        int i3 = i2 - i;
        char[] cArr = new char[i3];
        getChars(charSequence, cArr, i, 0, i3);
        return cArr;
    }

    public static int shiftBackward(CharSequence charSequence, int i, String str) {
        if (charSequence == null) {
            $$$reportNull$$$0(18);
        }
        if (str == null) {
            $$$reportNull$$$0(19);
        }
        return shiftBackward(charSequence, 0, i, str);
    }

    public static int shiftForward(CharSequence charSequence, int i, String str) {
        if (charSequence == null) {
            $$$reportNull$$$0(9);
        }
        if (str == null) {
            $$$reportNull$$$0(10);
        }
        return shiftForward(charSequence, i, charSequence.length(), str);
    }

    public static boolean regionMatches(CharSequence charSequence, int i, int i2, CharSequence charSequence2) {
        if (charSequence == null) {
            $$$reportNull$$$0(30);
        }
        if (charSequence2 == null) {
            $$$reportNull$$$0(31);
        }
        int length = charSequence2.length();
        if (i + length > i2 || i < 0) {
            return false;
        }
        for (int i3 = 0; i3 < length; i3++) {
            if (charSequence.charAt(i + i3) != charSequence2.charAt(i3)) {
                return false;
            }
        }
        return true;
    }

    public static boolean regionMatches(CharSequence charSequence, int i, int i2, CharSequence charSequence2, int i3, int i4) {
        if (charSequence == null) {
            $$$reportNull$$$0(32);
        }
        if (charSequence2 == null) {
            $$$reportNull$$$0(33);
        }
        if (i2 - i != i4 - i3) {
            return false;
        }
        assertRegionIndicesInRange(charSequence.length(), i, i2, charSequence2.length(), i3, i4);
        while (i < i2) {
            if (charSequence.charAt(i) != charSequence2.charAt(i3)) {
                return false;
            }
            i++;
            i3++;
        }
        return true;
    }

    public static boolean regionMatches(char[] cArr, int i, int i2, CharSequence charSequence) {
        if (charSequence == null) {
            $$$reportNull$$$0(28);
        }
        if (cArr == null) {
            $$$reportNull$$$0(29);
        }
        int length = charSequence.length();
        if (i + length > i2 || i < 0) {
            return false;
        }
        for (int i3 = 0; i3 < length; i3++) {
            if (cArr[i + i3] != charSequence.charAt(i3)) {
                return false;
            }
        }
        return true;
    }

    public static boolean regionMatches(CharSequence charSequence, int i, CharSequence charSequence2) {
        if (charSequence == null) {
            $$$reportNull$$$0(36);
        }
        if (charSequence2 == null) {
            $$$reportNull$$$0(37);
        }
        if (charSequence2.length() + i > charSequence.length() || i < 0) {
            return false;
        }
        for (int i2 = 0; i2 < charSequence2.length(); i2++) {
            if (charSequence.charAt(i + i2) != charSequence2.charAt(i2)) {
                return false;
            }
        }
        return true;
    }

    public static void getChars(CharSequence charSequence, char[] cArr, int i, int i2) {
        if (charSequence == null) {
            $$$reportNull$$$0(2);
        }
        if (cArr == null) {
            $$$reportNull$$$0(3);
        }
        getChars(charSequence, cArr, 0, i, i2);
    }

    public static void getChars(CharSequence charSequence, char[] cArr, int i) {
        if (charSequence == null) {
            $$$reportNull$$$0(0);
        }
        if (cArr == null) {
            $$$reportNull$$$0(1);
        }
        getChars(charSequence, cArr, i, charSequence.length());
    }
}
