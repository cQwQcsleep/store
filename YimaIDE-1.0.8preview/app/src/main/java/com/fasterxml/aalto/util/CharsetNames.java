package com.fasterxml.aalto.util;

import org.eclipse.jdt.internal.compiler.util.Util;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class CharsetNames {
    public static boolean encodingStartsWith(String str, String str2) {
        int i;
        char cCharAt;
        int i2;
        char cCharAt2;
        int length = str.length();
        int length2 = str2.length();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= length && i4 >= length2) {
                return true;
            }
            if (i3 >= length) {
                i = i3;
                cCharAt = 0;
            } else {
                i = i3 + 1;
                cCharAt = str.charAt(i3);
            }
            if (i4 >= length2) {
                i2 = i4;
                cCharAt2 = 0;
            } else {
                i2 = i4 + 1;
                cCharAt2 = str2.charAt(i4);
            }
            if (cCharAt != cCharAt2) {
                while (true) {
                    if (cCharAt > ' ' && cCharAt != '_' && cCharAt != '-') {
                        break;
                    }
                    if (i >= length) {
                        cCharAt = 0;
                    } else {
                        int i5 = i + 1;
                        char cCharAt3 = str.charAt(i);
                        i = i5;
                        cCharAt = cCharAt3;
                    }
                }
                while (true) {
                    if (cCharAt2 > ' ' && cCharAt2 != '_' && cCharAt2 != '-') {
                        break;
                    }
                    if (i2 >= length2) {
                        cCharAt2 = 0;
                    } else {
                        int i6 = i2 + 1;
                        char cCharAt4 = str2.charAt(i2);
                        i2 = i6;
                        cCharAt2 = cCharAt4;
                    }
                }
                if (cCharAt == cCharAt2) {
                    continue;
                } else {
                    if (cCharAt2 == 0) {
                        return true;
                    }
                    if (cCharAt == 0 || Character.toLowerCase(cCharAt) != Character.toLowerCase(cCharAt2)) {
                        return false;
                    }
                }
            }
            i3 = i;
            i4 = i2;
        }
    }

    public static boolean equalEncodings(String str, String str2) {
        int i;
        char cCharAt;
        int i2;
        char cCharAt2;
        int length = str.length();
        int length2 = str2.length();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= length && i4 >= length2) {
                return true;
            }
            if (i3 >= length) {
                i = i3;
                cCharAt = 0;
            } else {
                i = i3 + 1;
                cCharAt = str.charAt(i3);
            }
            if (i4 >= length2) {
                i2 = i4;
                cCharAt2 = 0;
            } else {
                i2 = i4 + 1;
                cCharAt2 = str2.charAt(i4);
            }
            if (cCharAt != cCharAt2) {
                while (true) {
                    if (cCharAt > ' ' && cCharAt != '_' && cCharAt != '-') {
                        break;
                    }
                    if (i >= length) {
                        cCharAt = 0;
                    } else {
                        int i5 = i + 1;
                        char cCharAt3 = str.charAt(i);
                        i = i5;
                        cCharAt = cCharAt3;
                    }
                }
                while (true) {
                    if (cCharAt2 > ' ' && cCharAt2 != '_' && cCharAt2 != '-') {
                        break;
                    }
                    if (i2 >= length2) {
                        cCharAt2 = 0;
                    } else {
                        int i6 = i2 + 1;
                        char cCharAt4 = str2.charAt(i2);
                        i2 = i6;
                        cCharAt2 = cCharAt4;
                    }
                }
                if (cCharAt != cCharAt2 && (cCharAt == 0 || cCharAt2 == 0 || Character.toLowerCase(cCharAt) != Character.toLowerCase(cCharAt2))) {
                    return false;
                }
            }
            i3 = i;
            i4 = i2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:118:0x0148  */
    /* JADX WARN: Code duplicated, block: B:120:0x014e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:121:0x014f  */
    /* JADX WARN: Code duplicated, block: B:122:0x0151  */
    /* JADX WARN: Code duplicated, block: B:124:0x0159 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:125:0x015a  */
    /* JADX WARN: Code duplicated, block: B:127:0x0162 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:128:0x0163  */
    /* JADX WARN: Code duplicated, block: B:130:0x0169 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:131:0x016a  */
    /* JADX WARN: Code duplicated, block: B:133:0x0170 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:134:0x0171  */
    /* JADX WARN: Code duplicated, block: B:136:0x0177 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:137:0x0178  */
    /* JADX WARN: Code duplicated, block: B:139:0x017e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:140:0x017f  */
    /* JADX WARN: Code duplicated, block: B:147:0x0196  */
    /* JADX WARN: Code duplicated, block: B:40:0x0071  */
    /* JADX WARN: Code duplicated, block: B:42:0x0079 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:43:0x007a  */
    /* JADX WARN: Code duplicated, block: B:73:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:76:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:78:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:80:0x00eb A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:81:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:83:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:85:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:88:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:91:0x0101  */
    /* JADX WARN: Code duplicated, block: B:93:0x0105  */
    public static String normalize(String str) {
        char cCharAt;
        char cCharAt2;
        String strSubstring = str;
        if (strSubstring == null || strSubstring.length() < 3) {
            return strSubstring;
        }
        boolean z = false;
        char cCharAt3 = strSubstring.charAt(0);
        if ((cCharAt3 == 'c' || cCharAt3 == 'C') && ((cCharAt = strSubstring.charAt(1)) == 's' || cCharAt == 'S')) {
            strSubstring = strSubstring.substring(2);
            cCharAt3 = strSubstring.charAt(0);
            z = true;
        }
        if (cCharAt3 != 'A') {
            if (cCharAt3 != 'C') {
                if (cCharAt3 == 'E') {
                    if (!strSubstring.startsWith("EBCDIC") || strSubstring.startsWith("ebcdic")) {
                        return "EBCDIC";
                    }
                } else if (cCharAt3 == 'S') {
                    if (equalEncodings(strSubstring, "Shift_JIS")) {
                        return "Shift_JIS";
                    }
                } else if (cCharAt3 == 'U') {
                    if (strSubstring.length() >= 2) {
                        cCharAt2 = strSubstring.charAt(1);
                        if (cCharAt2 == 'C') {
                            if (equalEncodings(strSubstring, "UCS-2")) {
                                return "UTF-16";
                            }
                            if (equalEncodings(strSubstring, "UCS-4")) {
                                return "UTF-32";
                            }
                        } else if (cCharAt2 == 'N') {
                            if (z) {
                                if (equalEncodings(strSubstring, "Unicode")) {
                                    return "UTF-16";
                                }
                                if (equalEncodings(strSubstring, "UnicodeAscii")) {
                                    return "ISO-8859-1";
                                }
                                if (equalEncodings(strSubstring, "UnicodeAscii")) {
                                    return "US-ASCII";
                                }
                            }
                        } else if (cCharAt2 != 'c') {
                            if (cCharAt2 != 'n') {
                                if (cCharAt2 == 'S') {
                                    if (equalEncodings(strSubstring, "US-ASCII")) {
                                        return "US-ASCII";
                                    }
                                } else if (cCharAt2 == 'T') {
                                    if (strSubstring != Util.UTF_8 || equalEncodings(strSubstring, Util.UTF_8)) {
                                        return Util.UTF_8;
                                    }
                                    if (equalEncodings(strSubstring, "UTF-16BE")) {
                                        return "UTF-16BE";
                                    }
                                    if (equalEncodings(strSubstring, "UTF-16LE")) {
                                        return "UTF-16LE";
                                    }
                                    if (equalEncodings(strSubstring, "UTF-16")) {
                                        return "UTF-16";
                                    }
                                    if (equalEncodings(strSubstring, "UTF-32BE")) {
                                        return "UTF-32BE";
                                    }
                                    if (equalEncodings(strSubstring, "UTF-32LE")) {
                                        return "UTF-32LE";
                                    }
                                    if (equalEncodings(strSubstring, "UTF-32")) {
                                        return "UTF-32";
                                    }
                                    if (equalEncodings(strSubstring, "UTF")) {
                                        return "UTF-16";
                                    }
                                } else if (cCharAt2 != 's') {
                                    if (cCharAt2 == 't') {
                                        if (strSubstring != Util.UTF_8) {
                                        }
                                        return Util.UTF_8;
                                    }
                                } else if (equalEncodings(strSubstring, "US-ASCII")) {
                                    return "US-ASCII";
                                }
                            } else if (z) {
                                if (equalEncodings(strSubstring, "Unicode")) {
                                    return "UTF-16";
                                }
                                if (equalEncodings(strSubstring, "UnicodeAscii")) {
                                    return "ISO-8859-1";
                                }
                                if (equalEncodings(strSubstring, "UnicodeAscii")) {
                                    return "US-ASCII";
                                }
                            }
                        } else {
                            if (equalEncodings(strSubstring, "UCS-2")) {
                                return "UTF-16";
                            }
                            if (equalEncodings(strSubstring, "UCS-4")) {
                                return "UTF-32";
                            }
                        }
                    }
                } else {
                    if (cCharAt3 == 'a') {
                        if (strSubstring != "ASCII") {
                        }
                        return "US-ASCII";
                    }
                    if (cCharAt3 != 'c') {
                        if (cCharAt3 == 'e') {
                            if (!strSubstring.startsWith("EBCDIC")) {
                            }
                            return "EBCDIC";
                        }
                        if (cCharAt3 != 's') {
                            if (cCharAt3 != 'u') {
                                if (cCharAt3 == 'I') {
                                    if (strSubstring != "ISO-8859-1" || equalEncodings(strSubstring, "ISO-8859-1") || equalEncodings(strSubstring, "ISO-Latin1")) {
                                        return "ISO-8859-1";
                                    }
                                    if (encodingStartsWith(strSubstring, "ISO-10646")) {
                                        String strSubstring2 = strSubstring.substring(strSubstring.indexOf("10646") + 5);
                                        if (equalEncodings(strSubstring2, "UCS-Basic")) {
                                            return "US-ASCII";
                                        }
                                        if (equalEncodings(strSubstring2, "Unicode-Latin1")) {
                                            return "ISO-8859-1";
                                        }
                                        if (equalEncodings(strSubstring2, "UCS-2")) {
                                            return "UTF-16";
                                        }
                                        if (equalEncodings(strSubstring2, "UCS-4")) {
                                            return "UTF-32";
                                        }
                                        if (equalEncodings(strSubstring2, "UTF-1") || equalEncodings(strSubstring2, "J-1") || equalEncodings(strSubstring2, "US-ASCII")) {
                                            return "US-ASCII";
                                        }
                                    }
                                } else if (cCharAt3 == 'J') {
                                    if (equalEncodings(strSubstring, "JIS_Encoding")) {
                                        return "Shift_JIS";
                                    }
                                } else {
                                    if (cCharAt3 == 'i') {
                                        if (strSubstring != "ISO-8859-1") {
                                        }
                                        return "ISO-8859-1";
                                    }
                                    if (cCharAt3 == 'j') {
                                        if (equalEncodings(strSubstring, "JIS_Encoding")) {
                                            return "Shift_JIS";
                                        }
                                    }
                                }
                            } else if (strSubstring.length() >= 2) {
                                cCharAt2 = strSubstring.charAt(1);
                                if (cCharAt2 == 'C') {
                                    if (equalEncodings(strSubstring, "UCS-2")) {
                                        return "UTF-16";
                                    }
                                    if (equalEncodings(strSubstring, "UCS-4")) {
                                        return "UTF-32";
                                    }
                                } else if (cCharAt2 == 'N') {
                                    if (z) {
                                        if (equalEncodings(strSubstring, "Unicode")) {
                                            return "UTF-16";
                                        }
                                        if (equalEncodings(strSubstring, "UnicodeAscii")) {
                                            return "ISO-8859-1";
                                        }
                                        if (equalEncodings(strSubstring, "UnicodeAscii")) {
                                            return "US-ASCII";
                                        }
                                    }
                                } else if (cCharAt2 != 'c') {
                                    if (cCharAt2 != 'n') {
                                        if (cCharAt2 == 'S') {
                                            if (equalEncodings(strSubstring, "US-ASCII")) {
                                                return "US-ASCII";
                                            }
                                        } else {
                                            if (cCharAt2 == 'T') {
                                                if (strSubstring != Util.UTF_8) {
                                                }
                                                return Util.UTF_8;
                                            }
                                            if (cCharAt2 != 's') {
                                                if (cCharAt2 == 't') {
                                                    if (strSubstring != Util.UTF_8) {
                                                    }
                                                    return Util.UTF_8;
                                                }
                                            } else if (equalEncodings(strSubstring, "US-ASCII")) {
                                                return "US-ASCII";
                                            }
                                        }
                                    } else if (z) {
                                        if (equalEncodings(strSubstring, "Unicode")) {
                                            return "UTF-16";
                                        }
                                        if (equalEncodings(strSubstring, "UnicodeAscii")) {
                                            return "ISO-8859-1";
                                        }
                                        if (equalEncodings(strSubstring, "UnicodeAscii")) {
                                            return "US-ASCII";
                                        }
                                    }
                                } else {
                                    if (equalEncodings(strSubstring, "UCS-2")) {
                                        return "UTF-16";
                                    }
                                    if (equalEncodings(strSubstring, "UCS-4")) {
                                        return "UTF-32";
                                    }
                                }
                            }
                        } else if (equalEncodings(strSubstring, "Shift_JIS")) {
                            return "Shift_JIS";
                        }
                    }
                }
            }
            encodingStartsWith(strSubstring, "cs");
            return strSubstring;
        }
        if (strSubstring != "ASCII" || equalEncodings(strSubstring, "ASCII")) {
            return "US-ASCII";
        }
        return strSubstring;
    }
}
