package com.sun.org.apache.xerces.internal.util;

import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.ElementValue;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XML11Char {
    public static final int MASK_XML11_CONTENT = 32;
    public static final int MASK_XML11_CONTENT_INTERNAL = 48;
    public static final int MASK_XML11_CONTROL = 16;
    public static final int MASK_XML11_NAME = 8;
    public static final int MASK_XML11_NAME_START = 4;
    public static final int MASK_XML11_NCNAME = 128;
    public static final int MASK_XML11_NCNAME_START = 64;
    public static final int MASK_XML11_SPACE = 2;
    public static final int MASK_XML11_VALID = 1;
    private static final byte[] XML11CHARS;

    static {
        byte[] bArr = new byte[65536];
        XML11CHARS = bArr;
        Arrays.fill(bArr, 1, 9, (byte) 17);
        bArr[9] = 35;
        bArr[10] = 3;
        Arrays.fill(bArr, 11, 13, (byte) 17);
        bArr[13] = 3;
        Arrays.fill(bArr, 14, 32, (byte) 17);
        bArr[32] = 35;
        Arrays.fill(bArr, 33, 38, (byte) 33);
        bArr[38] = 1;
        Arrays.fill(bArr, 39, 45, (byte) 33);
        Arrays.fill(bArr, 45, 47, (byte) -87);
        bArr[47] = 33;
        Arrays.fill(bArr, 48, 58, (byte) -87);
        bArr[58] = 45;
        bArr[59] = 33;
        bArr[60] = 1;
        Arrays.fill(bArr, 61, 65, (byte) 33);
        Arrays.fill(bArr, 65, 91, (byte) -19);
        Arrays.fill(bArr, 91, 93, (byte) 33);
        bArr[93] = 1;
        bArr[94] = 33;
        bArr[95] = -19;
        bArr[96] = 33;
        Arrays.fill(bArr, 97, 123, (byte) -19);
        Arrays.fill(bArr, 123, 127, (byte) 33);
        Arrays.fill(bArr, 127, 133, (byte) 17);
        bArr[133] = 35;
        Arrays.fill(bArr, 134, 160, (byte) 17);
        Arrays.fill(bArr, 160, 183, (byte) 33);
        bArr[183] = -87;
        Arrays.fill(bArr, 184, 192, (byte) 33);
        Arrays.fill(bArr, 192, WinError.ERROR_NESTING_NOT_ALLOWED, (byte) -19);
        bArr[215] = 33;
        Arrays.fill(bArr, WinError.ERROR_EXE_MACHINE_TYPE_MISMATCH, Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED, (byte) -19);
        bArr[247] = 33;
        Arrays.fill(bArr, Const.CHOP_FRAME, 768, (byte) -19);
        Arrays.fill(bArr, 768, 880, (byte) -87);
        Arrays.fill(bArr, 880, 894, (byte) -19);
        bArr[894] = 33;
        Arrays.fill(bArr, 895, 8192, (byte) -19);
        Arrays.fill(bArr, 8192, WinError.ERROR_DS_ATTRIBUTE_TYPE_UNDEFINED, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_DS_ATTRIBUTE_TYPE_UNDEFINED, WinError.ERROR_DS_BUSY, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_DS_BUSY, WinError.ERROR_DS_STRONG_AUTH_REQUIRED, (byte) 33);
        bArr[8232] = 35;
        Arrays.fill(bArr, WinError.ERROR_DS_INAPPROPRIATE_AUTH, WinError.ERROR_DS_PARAM_ERROR, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_DS_PARAM_ERROR, WinError.ERROR_DS_NO_RESULTS_RETURNED, (byte) -87);
        Arrays.fill(bArr, WinError.ERROR_DS_NO_RESULTS_RETURNED, WinError.ERROR_DS_MAX_OBJ_SIZE_EXCEEDED, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_DS_MAX_OBJ_SIZE_EXCEEDED, WinError.ERROR_DS_NTDSCRIPT_PROCESS_ERROR, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_DS_NTDSCRIPT_PROCESS_ERROR, 11264, (byte) 33);
        Arrays.fill(bArr, 11264, 12272, (byte) -19);
        Arrays.fill(bArr, 12272, 12289, (byte) 33);
        Arrays.fill(bArr, 12289, 55296, (byte) -19);
        Arrays.fill(bArr, 57344, 63744, (byte) 33);
        Arrays.fill(bArr, 63744, 64976, (byte) -19);
        Arrays.fill(bArr, 64976, 65008, (byte) 33);
        Arrays.fill(bArr, 65008, 65534, (byte) -19);
    }

    public static boolean isXML11Content(int i) {
        if (i >= 65536 || (XML11CHARS[i] & 32) == 0) {
            return 65536 <= i && i <= 1114111;
        }
        return true;
    }

    public static boolean isXML11InternalEntityContent(int i) {
        if (i >= 65536 || (XML11CHARS[i] & 48) == 0) {
            return 65536 <= i && i <= 1114111;
        }
        return true;
    }

    public static boolean isXML11Invalid(int i) {
        return !isXML11Valid(i);
    }

    public static boolean isXML11NCName(int i) {
        if (i >= 65536 || (XML11CHARS[i] & 128) == 0) {
            return 65536 <= i && i < 983040;
        }
        return true;
    }

    public static boolean isXML11NCNameStart(int i) {
        if (i >= 65536 || (XML11CHARS[i] & ElementValue.ANNOTATION) == 0) {
            return 65536 <= i && i < 983040;
        }
        return true;
    }

    public static boolean isXML11Name(int i) {
        if (i >= 65536 || (XML11CHARS[i] & 8) == 0) {
            return i >= 65536 && i < 983040;
        }
        return true;
    }

    public static boolean isXML11NameHighSurrogate(int i) {
        return 55296 <= i && i <= 56191;
    }

    public static boolean isXML11NameStart(int i) {
        if (i >= 65536 || (XML11CHARS[i] & 4) == 0) {
            return 65536 <= i && i < 983040;
        }
        return true;
    }

    public static boolean isXML11Space(int i) {
        return i < 65536 && (XML11CHARS[i] & 2) != 0;
    }

    public static boolean isXML11Valid(int i) {
        return (i < 65536 && (XML11CHARS[i] & 1) != 0) || (65536 <= i && i <= 1114111);
    }

    public static boolean isXML11ValidLiteral(int i) {
        if (i < 65536) {
            byte b = XML11CHARS[i];
            if ((b & 1) != 0 && (b & 16) == 0) {
                return true;
            }
        }
        return 65536 <= i && i <= 1114111;
    }

    public static boolean isXML11ValidNCName(String str) {
        int i;
        int length = str.length();
        if (length == 0) {
            return false;
        }
        char cCharAt = str.charAt(0);
        if (!isXML11NCNameStart(cCharAt)) {
            if (length > 1 && isXML11NameHighSurrogate(cCharAt)) {
                char cCharAt2 = str.charAt(1);
                if (XMLChar.isLowSurrogate(cCharAt2) && isXML11NCNameStart(XMLChar.supplemental(cCharAt, cCharAt2))) {
                    i = 2;
                }
            }
            return false;
        }
        i = 1;
        while (i < length) {
            char cCharAt3 = str.charAt(i);
            if (!isXML11NCName(cCharAt3)) {
                i++;
                if (i < length && isXML11NameHighSurrogate(cCharAt3)) {
                    char cCharAt4 = str.charAt(i);
                    if (!XMLChar.isLowSurrogate(cCharAt4) || !isXML11NCName(XMLChar.supplemental(cCharAt3, cCharAt4))) {
                    }
                }
                return false;
            }
            i++;
        }
        return true;
    }

    public static boolean isXML11ValidName(String str) {
        int i;
        int length = str.length();
        if (length == 0) {
            return false;
        }
        char cCharAt = str.charAt(0);
        if (!isXML11NameStart(cCharAt)) {
            if (length > 1 && isXML11NameHighSurrogate(cCharAt)) {
                char cCharAt2 = str.charAt(1);
                if (XMLChar.isLowSurrogate(cCharAt2) && isXML11NameStart(XMLChar.supplemental(cCharAt, cCharAt2))) {
                    i = 2;
                }
            }
            return false;
        }
        i = 1;
        while (i < length) {
            char cCharAt3 = str.charAt(i);
            if (!isXML11Name(cCharAt3)) {
                i++;
                if (i < length && isXML11NameHighSurrogate(cCharAt3)) {
                    char cCharAt4 = str.charAt(i);
                    if (!XMLChar.isLowSurrogate(cCharAt4) || !isXML11Name(XMLChar.supplemental(cCharAt3, cCharAt4))) {
                    }
                }
                return false;
            }
            i++;
        }
        return true;
    }

    public static boolean isXML11ValidNmtoken(String str) {
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (!isXML11Name(cCharAt)) {
                i++;
                if (i < length && isXML11NameHighSurrogate(cCharAt)) {
                    char cCharAt2 = str.charAt(i);
                    if (!XMLChar.isLowSurrogate(cCharAt2) || !isXML11Name(XMLChar.supplemental(cCharAt, cCharAt2))) {
                    }
                }
                return false;
            }
            i++;
        }
        return true;
    }
}
