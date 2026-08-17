package com.sun.org.apache.xml.internal.utils;

import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LocaleUtility {
    public static final String EMPTY_STRING = "";
    public static final char IETF_SEPARATOR = '-';

    public static Locale langToLocale(String str) {
        String strSubstring;
        String strSubstring2;
        if (str != null) {
            String upperCase = "";
            if (!str.equals("")) {
                int iIndexOf = str.indexOf(45);
                if (iIndexOf < 0) {
                    strSubstring2 = "";
                    strSubstring = strSubstring2;
                } else {
                    String strSubstring3 = str.substring(0, iIndexOf);
                    int i = iIndexOf + 1;
                    int iIndexOf2 = str.indexOf(45, i);
                    if (iIndexOf2 < 0) {
                        strSubstring = str.substring(i);
                        strSubstring2 = "";
                    } else {
                        strSubstring = str.substring(i, iIndexOf2);
                        strSubstring2 = str.substring(iIndexOf2 + 1);
                    }
                    str = strSubstring3;
                }
                String lowerCase = str.length() == 2 ? str.toLowerCase() : "";
                String upperCase2 = strSubstring.length() == 2 ? strSubstring.toUpperCase() : "";
                if (strSubstring2.length() > 0 && (lowerCase.length() == 2 || upperCase2.length() == 2)) {
                    upperCase = strSubstring2.toUpperCase();
                }
                return new Locale(lowerCase, upperCase2, upperCase);
            }
        }
        return Locale.getDefault();
    }
}
