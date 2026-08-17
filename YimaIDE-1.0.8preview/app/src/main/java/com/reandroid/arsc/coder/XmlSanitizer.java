package com.reandroid.arsc.coder;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class XmlSanitizer {
    public static String escapeDecodedValue(String str) {
        if (!shouldEscapeDecoded(str) && !shouldEscapeSpecial(str)) {
            return str;
        }
        return "\\" + str;
    }

    public static String escapeQuote(String str) {
        return escapeSpecialCharacter(quoteWhitespace(str));
    }

    public static String escapeSpecialCharacter(String str) {
        if (!shouldEscapeSpecial(str)) {
            return str;
        }
        return "\\" + str;
    }

    private static boolean isAlreadyEscaped(String str, int i) {
        return str.length() > i && str.charAt(i) == '\\';
    }

    private static boolean isBoolean(String str, int i) {
        String strSubstring = str.substring(i);
        return "true".equals(strSubstring) || "false".equals(strSubstring);
    }

    private static boolean isNumber(char c) {
        return c <= '9' && c >= '0';
    }

    private static boolean isQuotedWhiteSpace(String str, int i) {
        int length;
        if (str == null || (length = str.length()) <= i) {
            return false;
        }
        int i2 = (length - i) - 1;
        if (str.charAt(i) == '\"' && str.charAt(i2) == '\"') {
            return isWhiteSpace(str, i + 1);
        }
        return false;
    }

    private static boolean isSpecialCharacter(char c) {
        return c == '#' || c == '?' || c == '@';
    }

    private static boolean isWhiteSpace(String str, int i) {
        if (str == null || str.length() - 1 <= i) {
            return false;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length - i;
        while (i < length) {
            if (!isWhiteSpace(charArray[i])) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static boolean looksDecoded(String str, int i) {
        int length = str.length();
        return length > i && length <= 14 && (looksNumber(str, i) || isBoolean(str, i)) && ValueCoder.encode(str.substring(i)) != null;
    }

    private static boolean looksNumber(String str, int i) {
        char cCharAt = str.charAt(i);
        if (isNumber(cCharAt)) {
            return true;
        }
        int i2 = i + 1;
        if (i2 == str.length() || cCharAt != '-') {
            return false;
        }
        return isNumber(str.charAt(i2));
    }

    public static String quoteWhitespace(String str) {
        if (!shouldQuote(str)) {
            return str;
        }
        return "\"" + str + "\"";
    }

    private static boolean shouldEscapeDecoded(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return looksDecoded(str, 0);
    }

    private static boolean shouldEscapeSpecial(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return isAlreadyEscaped(str, 0) || startsWithSpecialCharacter(str, 0);
    }

    private static boolean shouldQuote(String str) {
        if (str == null) {
            return false;
        }
        return isWhiteSpace(str, 0) || isQuotedWhiteSpace(str, 0);
    }

    private static boolean shouldUnEscape(String str) {
        if (str == null || str.length() < 2 || str.charAt(0) != '\\') {
            return false;
        }
        return isAlreadyEscaped(str, 1) || looksDecoded(str, 1) || startsWithSpecialCharacter(str, 1);
    }

    private static boolean shouldUnQuote(String str) {
        if (str == null || str.length() < 3 || str.charAt(0) != '\"' || str.charAt(str.length() - 1) != '\"') {
            return false;
        }
        return isWhiteSpace(str, 1) || isQuotedWhiteSpace(str, 1);
    }

    private static boolean startsWithSpecialCharacter(String str, int i) {
        if (str.length() < i + 2) {
            return false;
        }
        return isSpecialCharacter(str.charAt(i));
    }

    public static String unEscapeSpecialCharacter(String str) {
        return shouldUnEscape(str) ? str.substring(1) : str;
    }

    public static String unEscapeUnQuote(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        return str.charAt(0) == '\"' ? unQuoteWhitespace(str) : unEscapeSpecialCharacter(str);
    }

    public static String unQuoteWhitespace(String str) {
        return !shouldUnQuote(str) ? str : str.substring(1, str.length() - 1);
    }

    private static boolean isWhiteSpace(char c) {
        return c == '\t' || c == '\n' || c == '\r' || c == ' ';
    }
}
