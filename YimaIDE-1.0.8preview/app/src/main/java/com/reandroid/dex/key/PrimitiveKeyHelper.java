package com.reandroid.dex.key;

import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.utils.HexUtil;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xml.internal.serializer.CharInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class PrimitiveKeyHelper {
    private static boolean isFloatOrDouble(String str) {
        return str.indexOf(46) >= 0 || str.contains("NaN") || str.contains(Constants.ATTRVAL_INFINITY);
    }

    private static boolean isNumbersPrefix(char c) {
        return (c >= '0' && c <= '9') || c == '-' || c == '+' || c == 'N' || c == 'I';
    }

    public static PrimitiveKey parse(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char cCharAt = str.charAt(0);
        if (isNumbersPrefix(cCharAt)) {
            return parseNumbers(str);
        }
        if (cCharAt == '\'') {
            return parseChar(str);
        }
        if (cCharAt == 't' || cCharAt == 'f') {
            return parseBoolean(str);
        }
        return null;
    }

    private static PrimitiveKey parseBoolean(String str) {
        if (str.equals("true")) {
            return PrimitiveKey.of(true);
        }
        if (str.equals("false")) {
            return PrimitiveKey.of(false);
        }
        return null;
    }

    private static PrimitiveKey parseChar(String str) {
        char c;
        if (str.length() < 3 || str.charAt(0) != '\'') {
            return null;
        }
        int length = str.length() - 1;
        if (str.charAt(length) != '\'') {
            return null;
        }
        String strSubstring = str.substring(1, length);
        char cCharAt = strSubstring.charAt(0);
        if (strSubstring.length() == 1) {
            return PrimitiveKey.of(cCharAt);
        }
        if (cCharAt != '\\') {
            return null;
        }
        char cCharAt2 = strSubstring.charAt(1);
        if (cCharAt2 == 'u') {
            if (strSubstring.length() == 6) {
                try {
                    return PrimitiveKey.of((char) HexUtil.parseHex(strSubstring.substring(2)));
                } catch (NumberFormatException unused) {
                }
            }
        } else if (strSubstring.length() == 2) {
            if (cCharAt2 == 'b') {
                c = '\b';
            } else if (cCharAt2 == 'f') {
                c = '\f';
            } else if (cCharAt2 == 'n') {
                c = '\n';
            } else if (cCharAt2 == 'r') {
                c = CharInfo.S_CARRIAGERETURN;
            } else if (cCharAt2 == 't') {
                c = '\t';
            }
            return PrimitiveKey.of(c);
        }
        return null;
    }

    private static PrimitiveKey parseFloatOrDouble(String str) {
        int length = str.length() - 1;
        return str.charAt(length) == 'f' ? PrimitiveKey.of(Float.parseFloat(str.substring(0, length))) : PrimitiveKey.of(Double.parseDouble(str));
    }

    private static PrimitiveKey parseNumbers(String str) {
        int i;
        char cCharAt = str.charAt(0);
        int length = str.length();
        if (length <= 1 || !(cCharAt == '-' || cCharAt == '+')) {
            i = 0;
        } else {
            cCharAt = str.charAt(1);
            i = 1;
        }
        int i2 = i + 1;
        char cCharAt2 = length > i2 ? str.charAt(i2) : (char) 0;
        if (cCharAt == '0' && cCharAt2 == 'x') {
            try {
                return readHex(str);
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        if (str.charAt(0) == '+') {
            str = str.substring(1);
        }
        try {
            return isFloatOrDouble(str) ? parseFloatOrDouble(str) : parsePlainNumber(str);
        } catch (NumberFormatException unused2) {
            return null;
        }
    }

    private static PrimitiveKey parsePlainNumber(String str) {
        char cCharAt = str.charAt(str.length() - 1);
        return (cCharAt == 'L' || cCharAt == 'l') ? PrimitiveKey.of(Long.parseLong(str.substring(0, str.length() - 1))) : PrimitiveKey.of(Integer.parseInt(str));
    }

    private static PrimitiveKey readBoolean(SmaliReader smaliReader) {
        int iPosition = smaliReader.position();
        char ascii = smaliReader.readASCII();
        if (ascii == 't') {
            if (smaliReader.read() == 114 && smaliReader.read() == 117 && smaliReader.read() == 101) {
                return PrimitiveKey.of(true);
            }
        } else if (ascii == 'f' && smaliReader.read() == 97 && smaliReader.read() == 108 && smaliReader.read() == 115 && smaliReader.read() == 101) {
            return PrimitiveKey.of(false);
        }
        smaliReader.position(iPosition);
        return null;
    }

    private static PrimitiveKey readChar(SmaliReader smaliReader) {
        int iPosition = smaliReader.position();
        if (smaliReader.read() != 39) {
            smaliReader.position(iPosition);
            return null;
        }
        char ascii = smaliReader.readASCII();
        if (ascii == '\\') {
            char ascii2 = smaliReader.readASCII();
            if (ascii2 == 'u') {
                try {
                    ascii = (char) HexUtil.parseHex(smaliReader.readString(4));
                } catch (NumberFormatException unused) {
                    smaliReader.position(iPosition);
                    return null;
                }
            } else if (ascii2 == 'b') {
                ascii = '\b';
            } else if (ascii2 == 'f') {
                ascii = '\f';
            } else if (ascii2 == 'n') {
                ascii = '\n';
            } else if (ascii2 == 'r') {
                ascii = CharInfo.S_CARRIAGERETURN;
            } else {
                if (ascii2 != 't') {
                    smaliReader.position(iPosition);
                    return null;
                }
                ascii = '\t';
            }
        }
        if (smaliReader.read() == 39) {
            return PrimitiveKey.of(ascii);
        }
        smaliReader.position(iPosition);
        return null;
    }

    private static PrimitiveKey readHex(String str) {
        char cCharAt = str.charAt(str.length() - 1);
        if (cCharAt == 'L') {
            return PrimitiveKey.of(HexUtil.parseHexLong(str));
        }
        if (cCharAt == 'S' || cCharAt == 's') {
            return PrimitiveKey.of(HexUtil.parseHexShort(str));
        }
        return cCharAt == 't' ? PrimitiveKey.of(HexUtil.parseHexByte(str)) : PrimitiveKey.of(HexUtil.parseHexInteger(str));
    }

    private static PrimitiveKey readNumbers(SmaliReader smaliReader) {
        int iPosition = smaliReader.position();
        char ascii = smaliReader.readASCII();
        if (ascii == '-' || ascii == '+') {
            ascii = smaliReader.readASCII();
        }
        char ascii2 = !smaliReader.finished() ? smaliReader.readASCII() : (char) 0;
        smaliReader.position(iPosition);
        String stringForNumber = smaliReader.readStringForNumber();
        if (ascii == '0' && ascii2 == 'x') {
            try {
                return readHex(stringForNumber);
            } catch (NumberFormatException unused) {
                smaliReader.position(iPosition);
                return null;
            }
        }
        if (stringForNumber.charAt(0) == '+') {
            stringForNumber = stringForNumber.substring(1);
        }
        try {
            return isFloatOrDouble(stringForNumber) ? parseFloatOrDouble(stringForNumber) : parsePlainNumber(stringForNumber);
        } catch (NumberFormatException unused2) {
            smaliReader.position(iPosition);
            return null;
        }
    }

    public static PrimitiveKey readSafe(SmaliReader smaliReader) {
        if (smaliReader.finished()) {
            return null;
        }
        int iPosition = smaliReader.position();
        char ascii = smaliReader.getASCII(iPosition);
        if (isNumbersPrefix(ascii)) {
            return readNumbers(smaliReader);
        }
        if (ascii == '\'') {
            return readChar(smaliReader);
        }
        if (ascii == 't' || ascii == 'f') {
            return readBoolean(smaliReader);
        }
        smaliReader.position(iPosition);
        return null;
    }
}
