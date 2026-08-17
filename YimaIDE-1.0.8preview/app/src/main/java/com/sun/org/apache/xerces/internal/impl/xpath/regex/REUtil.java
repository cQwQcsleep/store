package com.sun.org.apache.xerces.internal.impl.xpath.regex;

import com.sun.jna.platform.win32.Ddeml;
import java.text.CharacterIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class REUtil {
    static final int CACHESIZE = 20;
    static final RegularExpression[] regexCache = new RegularExpression[20];

    private REUtil() {
    }

    public static final int composeFromSurrogates(int i, int i2) {
        return ((((i - 55296) << 10) + 65536) + i2) - 56320;
    }

    public static final String createOptionString(int i) {
        StringBuilder sb = new StringBuilder(9);
        if ((i & 256) != 0) {
            sb.append('F');
        }
        if ((i & 128) != 0) {
            sb.append('H');
        }
        if ((i & 512) != 0) {
            sb.append('X');
        }
        if ((i & 2) != 0) {
            sb.append('i');
        }
        if ((i & 8) != 0) {
            sb.append('m');
        }
        if ((i & 4) != 0) {
            sb.append('s');
        }
        if ((i & 32) != 0) {
            sb.append('u');
        }
        if ((i & 64) != 0) {
            sb.append('w');
        }
        if ((i & 16) != 0) {
            sb.append('x');
        }
        if ((i & 1024) != 0) {
            sb.append(',');
        }
        return sb.toString().intern();
    }

    public static RegularExpression createRegex(String str, String str2) throws ParseException {
        RegularExpression regularExpression;
        int options = parseOptions(str2);
        synchronized (regexCache) {
            int i = 0;
            while (true) {
                regularExpression = null;
                if (i >= 20) {
                    break;
                }
                try {
                    RegularExpression regularExpression2 = regexCache[i];
                    if (regularExpression2 == null) {
                        i = -1;
                        break;
                    }
                    if (regularExpression2.equals(str, options)) {
                        regularExpression = regularExpression2;
                        break;
                    }
                    i++;
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (regularExpression == null) {
                regularExpression = new RegularExpression(str, str2);
                RegularExpression[] regularExpressionArr = regexCache;
                System.arraycopy(regularExpressionArr, 0, regularExpressionArr, 1, 19);
                regularExpressionArr[0] = regularExpression;
            } else if (i != 0) {
                RegularExpression[] regularExpressionArr2 = regexCache;
                System.arraycopy(regularExpressionArr2, 0, regularExpressionArr2, 1, i);
                regularExpressionArr2[0] = regularExpression;
            }
        }
        return regularExpression;
    }

    public static final String decomposeToSurrogates(int i) {
        int i2 = i - 65536;
        return new String(new char[]{(char) ((i2 >> 10) + 55296), (char) ((i2 & 1023) + 56320)});
    }

    public static void dumpString(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.print(Integer.toHexString(str.charAt(i)));
            System.out.print(" ");
        }
        System.out.println();
    }

    public static final int getOptionValue(int i) {
        if (i == 44) {
            return 1024;
        }
        if (i == 70) {
            return 256;
        }
        if (i == 72) {
            return 128;
        }
        if (i == 88) {
            return 512;
        }
        if (i == 105) {
            return 2;
        }
        if (i == 109) {
            return 8;
        }
        if (i == 115) {
            return 4;
        }
        if (i == 117) {
            return 32;
        }
        if (i != 119) {
            return i != 120 ? 0 : 16;
        }
        return 64;
    }

    public static final boolean isHighSurrogate(int i) {
        return (i & Ddeml.XCLASS_MASK) == 55296;
    }

    public static final boolean isLowSurrogate(int i) {
        return (i & Ddeml.XCLASS_MASK) == 56320;
    }

    public static void main(String[] strArr) {
        String str = null;
        try {
            if (strArr.length == 0) {
                System.out.println("Error:Usage: java REUtil -i|-m|-s|-u|-w|-X regularExpression String");
                System.exit(0);
            }
            String str2 = "";
            String str3 = null;
            for (int i = 0; i < strArr.length; i++) {
                if (strArr[i].length() == 0 || strArr[i].charAt(0) != '-') {
                    if (str == null) {
                        str = strArr[i];
                    } else if (str3 == null) {
                        str3 = strArr[i];
                    } else {
                        System.err.println("Unnecessary: " + strArr[i]);
                    }
                } else if (strArr[i].equals("-i")) {
                    str2 = str2 + "i";
                } else if (strArr[i].equals("-m")) {
                    str2 = str2 + "m";
                } else if (strArr[i].equals("-s")) {
                    str2 = str2 + "s";
                } else if (strArr[i].equals("-u")) {
                    str2 = str2 + "u";
                } else if (strArr[i].equals("-w")) {
                    str2 = str2 + "w";
                } else if (strArr[i].equals("-X")) {
                    str2 = str2 + "X";
                } else {
                    System.err.println("Unknown option: " + strArr[i]);
                }
            }
            RegularExpression regularExpression = new RegularExpression(str, str2);
            System.out.println("RegularExpression: " + regularExpression);
            Match match = new Match();
            regularExpression.matches(str3, match);
            for (int i2 = 0; i2 < match.getNumberOfGroups(); i2++) {
                if (i2 == 0) {
                    System.out.print("Matched range for the whole pattern: ");
                } else {
                    System.out.print("[" + i2 + "]: ");
                }
                if (match.getBeginning(i2) < 0) {
                    System.out.println("-1");
                } else {
                    System.out.print(match.getBeginning(i2) + ", " + match.getEnd(i2) + ", ");
                    System.out.println("\"" + match.getCapturedText(i2) + "\"");
                }
            }
        } catch (ParseException e) {
            if (str == null) {
                e.printStackTrace();
                return;
            }
            System.err.println("com.sun.org.apache.xerces.internal.utils.regex.ParseException: " + e.getMessage());
            System.err.println("        ".concat(str));
            int location = e.getLocation();
            if (location >= 0) {
                System.err.print("        ");
                for (int i3 = 0; i3 < location; i3++) {
                    System.err.print("-");
                }
                System.err.println("^");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean matches(String str, String str2) throws ParseException {
        return createRegex(str, null).matches(str2);
    }

    public static final int parseOptions(String str) throws ParseException {
        if (str == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            int optionValue = getOptionValue(str.charAt(i2));
            if (optionValue == 0) {
                throw new ParseException("Unknown Option: ".concat(str.substring(i2)), -1);
            }
            i |= optionValue;
        }
        return i;
    }

    public static String quoteMeta(String str) {
        int length = str.length();
        StringBuilder sb = null;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (".*+?{[()|\\^$".indexOf(cCharAt) >= 0) {
                if (sb == null) {
                    sb = new StringBuilder(((length - i) * 2) + i);
                    if (i > 0) {
                        sb.append(str.substring(0, i));
                    }
                }
                sb.append('\\');
                sb.append(cCharAt);
            } else if (sb != null) {
                sb.append(cCharAt);
            }
        }
        return sb != null ? sb.toString() : str;
    }

    public static String stripExtendedComment(String str) {
        int i;
        char cCharAt;
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i2 + 1;
            char cCharAt2 = str.charAt(i2);
            if (cCharAt2 == '\t' || cCharAt2 == '\n' || cCharAt2 == '\f' || cCharAt2 == '\r' || cCharAt2 == ' ') {
                if (i3 > 0) {
                    sb.append(cCharAt2);
                }
                i2 = i4;
                break;
            }
            if (cCharAt2 == '#') {
                while (true) {
                    i2 = i4;
                    if (i2 >= length) {
                        break;
                    }
                    i4 = i2 + 1;
                    char cCharAt3 = str.charAt(i2);
                    if (cCharAt3 == '\r' || cCharAt3 == '\n') {
                        i2 = i4;
                        break;
                        break;
                    }
                }
            } else {
                if (cCharAt2 != '\\' || i4 >= length) {
                    if (cCharAt2 == '[') {
                        i3++;
                        sb.append(cCharAt2);
                        if (i4 < length) {
                            char cCharAt4 = str.charAt(i4);
                            if (cCharAt4 == '[' || cCharAt4 == ']') {
                                sb.append(cCharAt4);
                            } else if (cCharAt4 == '^' && (i = i2 + 2) < length && ((cCharAt = str.charAt(i)) == '[' || cCharAt == ']')) {
                                sb.append('^');
                                sb.append(cCharAt);
                                i2 += 3;
                            }
                        }
                    } else {
                        if (i3 > 0 && cCharAt2 == ']') {
                            i3--;
                        }
                        sb.append(cCharAt2);
                    }
                    i2 = i4;
                    break;
                    break;
                }
                char cCharAt5 = str.charAt(i4);
                if (cCharAt5 == '#' || cCharAt5 == '\t' || cCharAt5 == '\n' || cCharAt5 == '\f' || cCharAt5 == '\r' || cCharAt5 == ' ') {
                    sb.append(cCharAt5);
                } else {
                    sb.append('\\');
                    sb.append(cCharAt5);
                }
                i2 += 2;
            }
        }
        return sb.toString();
    }

    public static final String substring(CharacterIterator characterIterator, int i, int i2) {
        int i3 = i2 - i;
        char[] cArr = new char[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            cArr[i4] = characterIterator.setIndex(i4 + i);
        }
        return new String(cArr);
    }

    public static boolean matches(String str, String str2, String str3) throws ParseException {
        return createRegex(str, str2).matches(str3);
    }
}
