package com.sun.tools.javac.util;

import com.sun.jna.platform.win32.WinError;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Convert {

    public enum Validation {
        NONE(true, true, true),
        PREJDK14(true, true, false),
        STRICT(false, false, false);

        private final boolean allowAnything;
        private final boolean allowLongEncoding;
        private final boolean allowSingleByteNul;

        Validation(boolean z, boolean z2, boolean z3) {
            this.allowSingleByteNul = z;
            this.allowLongEncoding = z2;
            this.allowAnything = z3;
        }

        public boolean allowAnything() {
            return this.allowAnything;
        }

        public boolean allowLongEncoding() {
            return this.allowLongEncoding;
        }

        public boolean allowSingleByteNul() {
            return this.allowSingleByteNul;
        }
    }

    public static int chars2utf(char[] cArr, int i, byte[] bArr, int i2, int i3) {
        int i4 = i3 + i;
        while (i < i4) {
            char c = cArr[i];
            if (1 <= c && c <= 127) {
                bArr[i2] = (byte) c;
                i2++;
            } else if (c <= 2047) {
                int i5 = i2 + 1;
                bArr[i2] = (byte) ((c >> 6) | 192);
                i2 += 2;
                bArr[i5] = (byte) ((c & '?') | 128);
            } else {
                bArr[i2] = (byte) ((c >> '\f') | WinError.ERROR_FORMS_AUTH_REQUIRED);
                int i6 = i2 + 2;
                bArr[i2 + 1] = (byte) (((c >> 6) & 63) | 128);
                i2 += 3;
                bArr[i6] = (byte) ((c & '?') | 128);
            }
            i++;
        }
        return i2;
    }

    public static List<Name> classCandidates(Name name) {
        List listNil = List.nil();
        String string = name.toString();
        int iIndexOf = -1;
        while (true) {
            iIndexOf = string.indexOf(46, iIndexOf + 1);
            if (iIndexOf <= 0) {
                return listNil.reverse();
            }
            int i = iIndexOf + 1;
            String strSubstring = string.substring(0, i);
            String strReplace = string.substring(i).replace('.', '$');
            listNil = listNil.prepend(name.table.names.fromString(strSubstring + strReplace));
        }
    }

    public static List<Name> enclosingCandidates(Name name) {
        List<Name> listNil = List.nil();
        while (true) {
            int iLastIndexOfAscii = name.lastIndexOfAscii('$');
            if (iLastIndexOfAscii <= 0) {
                return listNil;
            }
            name = name.subName(0, iLastIndexOfAscii);
            listNil = listNil.prepend(name);
        }
    }

    public static String escapeUnicode(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (str.charAt(i) > 255) {
                StringBuilder sb = new StringBuilder();
                sb.append(str.substring(0, i));
                while (i < length) {
                    char cCharAt = str.charAt(i);
                    if (cCharAt > 255) {
                        sb.append("\\u");
                        sb.append(Character.forDigit((cCharAt >> '\f') % 16, 16));
                        sb.append(Character.forDigit((cCharAt >> '\b') % 16, 16));
                        sb.append(Character.forDigit((cCharAt >> 4) % 16, 16));
                        sb.append(Character.forDigit(cCharAt % 16, 16));
                    } else {
                        sb.append(cCharAt);
                    }
                    i++;
                }
                str = sb.toString();
            } else {
                i++;
            }
        }
        return str;
    }

    private static boolean isPrintableAscii(char c) {
        return c >= ' ' && c <= '~';
    }

    public static String packagePart(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf < 0 ? "" : str.substring(0, iLastIndexOf);
    }

    public static String quote(char c, boolean z) {
        if (c == '\f') {
            return "\\f";
        }
        if (c == '\r') {
            return "\\r";
        }
        if (c == '\"') {
            return z ? "\"" : "\\\"";
        }
        if (c == '\'') {
            return z ? "\\'" : "'";
        }
        if (c == '\\') {
            return "\\\\";
        }
        switch (c) {
            case '\b':
                return "\\b";
            case '\t':
                return "\\t";
            case '\n':
                return "\\n";
            default:
                return isPrintableAscii(c) ? String.valueOf(c) : String.format("\\u%04x", Integer.valueOf(c));
        }
    }

    public static Name shortName(Name name) {
        int iLastIndexOfAscii = name.lastIndexOfAscii('.') + 1;
        return iLastIndexOfAscii > 0 ? name.subName(iLastIndexOfAscii) : name;
    }

    public static int string2int(String str, int i) throws NumberFormatException {
        int i2;
        if (i == 10) {
            return Integer.parseInt(str, i);
        }
        int i3 = Integer.MAX_VALUE / (i / 2);
        int i4 = 0;
        for (char c : str.toCharArray()) {
            int iDigit = Character.digit(c, i);
            if (i4 < 0 || i4 > i3 || (i2 = i4 * i) > Integer.MAX_VALUE - iDigit) {
                throw new NumberFormatException();
            }
            i4 = i2 + iDigit;
        }
        return i4;
    }

    public static long string2long(String str, int i) throws NumberFormatException {
        if (i == 10) {
            return Long.parseLong(str, i);
        }
        char[] charArray = str.toCharArray();
        long j = ClassFileConstants.JDK_DEFERRED / ((long) (i / 2));
        long j2 = 0;
        for (char c : charArray) {
            int iDigit = Character.digit(c, i);
            if (j2 >= 0 && j2 <= j) {
                long j3 = j2 * ((long) i);
                long j4 = iDigit;
                if (j3 <= ClassFileConstants.JDK_DEFERRED - j4) {
                    j2 = j3 + j4;
                }
            }
            throw new NumberFormatException();
        }
        return j2;
    }

    public static byte[] string2utf(String str) {
        return chars2utf(str.toCharArray());
    }

    /* JADX WARN: Code duplicated, block: B:71:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:86:0x00ce A[SYNTHETIC] */
    public static int utf2chars(byte[] bArr, int i, char[] cArr, int i2, int i3, Validation validation) throws InvalidUtfException {
        int i4;
        int i5 = i2;
        while (true) {
            int i6 = i3 - 1;
            if (i3 <= 0) {
                return i5 - i2;
            }
            int i7 = i + 1;
            int i8 = bArr[i];
            if (i8 < 0) {
                int i9 = 0;
                int i10 = 0;
                if ((i8 & WinError.ERROR_FORMS_AUTH_REQUIRED) == 192) {
                    i3 -= 2;
                    if (i6 > 0) {
                        int i11 = bArr[i7];
                        i7 = i + 2;
                        i10 = i11;
                    } else if (!validation.allowAnything()) {
                        throw new InvalidUtfException(i);
                    }
                    if (!validation.allowAnything() && (i10 & 192) != 128) {
                        throw new InvalidUtfException(i);
                    }
                    i8 = (i10 & 63) | ((i8 & 31) << 6);
                    if (!validation.allowLongEncoding() && ((i8 == true ? 1 : 0) & (-128)) == 0 && i8 != 0) {
                        throw new InvalidUtfException(i);
                    }
                } else if ((i8 & 240) == 224) {
                    i3 -= 3;
                    if (i3 >= 0) {
                        int i12 = bArr[i7];
                        i7 = i + 3;
                        i4 = bArr[i + 2];
                        i9 = i12;
                    } else {
                        if (!validation.allowAnything()) {
                            throw new InvalidUtfException(i);
                        }
                        i4 = 0;
                    }
                    if (!validation.allowAnything() && ((i9 & 192) != 128 || (i4 & 192) != 128)) {
                        throw new InvalidUtfException(i);
                    }
                    i8 = ((i8 & 15) << 12) | ((i9 & 63) << 6) | (i4 & 63);
                    if (!validation.allowLongEncoding() && ((i8 == true ? 1 : 0) & (-2048)) == 0) {
                        throw new InvalidUtfException(i);
                    }
                } else {
                    if (!validation.allowAnything()) {
                        throw new InvalidUtfException(i);
                    }
                    i8 &= 255;
                }
                i = i7;
                if (cArr != null) {
                    cArr[i5] = (char) i8;
                }
                i5++;
            } else if (!validation.allowSingleByteNul() && i8 == 0) {
                throw new InvalidUtfException(i);
            }
            i3 = i6;
            i = i7;
            if (cArr != null) {
                cArr[i5] = (char) i8;
            }
            i5++;
        }
    }

    public static String utf2string(byte[] bArr, int i, int i2, Validation validation) throws InvalidUtfException {
        char[] cArr = new char[i2];
        return new String(cArr, 0, utf2chars(bArr, i, cArr, 0, i2, validation));
    }

    public static int utfNumChars(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (true) {
            int i4 = i2 - 1;
            if (i2 <= 0) {
                return i3;
            }
            int i5 = i + 1;
            if ((bArr[i] & 192) != 128) {
                i3++;
            }
            i = i5;
            i2 = i4;
        }
    }

    public static void utfValidate(byte[] bArr, int i, int i2, Validation validation) throws InvalidUtfException {
        utf2chars(bArr, i, null, 0, i2, validation);
    }

    public static String shortName(String str) {
        return str.substring(str.lastIndexOf(46) + 1);
    }

    public static Name packagePart(Name name) {
        return name.subName(0, Math.max(name.lastIndexOfAscii('.'), 0));
    }

    public static byte[] chars2utf(char[] cArr, int i, int i2) {
        byte[] bArr = new byte[i2 * 3];
        int iChars2utf = chars2utf(cArr, i, bArr, 0, i2);
        byte[] bArr2 = new byte[iChars2utf];
        System.arraycopy(bArr, 0, bArr2, 0, iChars2utf);
        return bArr2;
    }

    public static byte[] chars2utf(char[] cArr) {
        return chars2utf(cArr, 0, cArr.length);
    }

    public static String quote(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(quote(str.charAt(i), false));
        }
        return sb.toString();
    }

    public static char[] utf2chars(byte[] bArr, int i, int i2, Validation validation) throws InvalidUtfException {
        char[] cArr = new char[i2];
        int iUtf2chars = utf2chars(bArr, i, cArr, 0, i2, validation);
        if (iUtf2chars == i2) {
            return cArr;
        }
        char[] cArr2 = new char[iUtf2chars];
        System.arraycopy(cArr, 0, cArr2, 0, iUtf2chars);
        return cArr2;
    }
}
