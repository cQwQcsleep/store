package com.sun.tools.javac.util;

import java.util.regex.Pattern;
import javax.lang.model.SourceVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MatchingUtils {
    private static final String allMatchesString = ".*";
    private static final Pattern allMatches = Pattern.compile(allMatchesString);

    public static boolean isValidImportString(String str) {
        boolean zIsIdentifier = true;
        if (str.equals("*")) {
            return true;
        }
        int iIndexOf = str.indexOf(42);
        if (iIndexOf != -1) {
            if (iIndexOf != str.length() - 1) {
                return false;
            }
            int i = iIndexOf - 1;
            if (i >= 0) {
                zIsIdentifier = str.charAt(i) == '.';
                str = str.substring(0, str.length() - 2);
            }
        }
        if (zIsIdentifier) {
            for (String str2 : str.split("\\.", str.length() + 2)) {
                zIsIdentifier &= SourceVersion.isIdentifier(str2);
            }
        }
        return zIsIdentifier;
    }

    public static Pattern validImportStringToPattern(String str) {
        String strValidImportStringToPatternString = validImportStringToPatternString(str);
        return strValidImportStringToPatternString == allMatchesString ? allMatches : Pattern.compile(strValidImportStringToPatternString);
    }

    public static String validImportStringToPatternString(String str) {
        if (str.equals("*")) {
            return allMatchesString;
        }
        String strReplace = str.replace(com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_THIS, "\\.");
        return strReplace.endsWith("*") ? strReplace.substring(0, strReplace.length() - 1).concat(".+") : strReplace;
    }
}
