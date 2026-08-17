package com.sun.tools.javac.parser;

import java.util.HashSet;
import java.util.Set;
import nbjavac.StringWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class TextBlockSupport {

    public enum WhitespaceChecks {
        INCONSISTENT,
        TRAILING
    }

    public static Set<WhitespaceChecks> checkWhitespace(String str) {
        int iIndexOfNonWhitespace;
        HashSet hashSet = new HashSet();
        if (str.length() != 0) {
            char cCharAt = str.charAt(str.length() - 1);
            boolean z = cCharAt == '\n' || cCharAt == '\r';
            String[] strArrSplit = str.split("\\R");
            int length = strArrSplit.length;
            String str2 = length == 0 ? "" : strArrSplit[length - 1];
            if (!z) {
                iIndexOfNonWhitespace = indexOfNonWhitespace(str2);
                for (String str3 : strArrSplit) {
                    if (!StringWrapper.isBlank(str3) && (iIndexOfNonWhitespace = Integer.min(iIndexOfNonWhitespace, indexOfNonWhitespace(str3))) == 0) {
                        break;
                    }
                }
            } else {
                iIndexOfNonWhitespace = 0;
            }
            String strSubstring = str2.substring(0, iIndexOfNonWhitespace);
            for (String str4 : strArrSplit) {
                if (!StringWrapper.isBlank(str4) && !str4.startsWith(strSubstring)) {
                    hashSet.add(WhitespaceChecks.INCONSISTENT);
                }
                if (iIndexOfNonWhitespace < str4.length() && Character.isWhitespace(str4.charAt(str4.length() - 1))) {
                    hashSet.add(WhitespaceChecks.TRAILING);
                }
            }
        }
        return hashSet;
    }

    private static int indexOfNonWhitespace(String str) {
        return str.length() - str.replaceAll("^\\p{javaWhitespace}+", "").length();
    }
}
