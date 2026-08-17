package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.xml.internal.utils.XML11Char;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.util.StringTokenizer;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Util {
    private static char filesep = SecuritySupport.getSystemProperty("file.separator", PsuedoNames.PSEUDONAME_ROOT).charAt(0);

    public static void TRACE1() {
        System.out.println("TRACE1");
    }

    public static void TRACE2() {
        System.out.println("TRACE2");
    }

    public static void TRACE3() {
        System.out.println("TRACE3");
    }

    public static String baseName(String str) {
        int iLastIndexOf = str.lastIndexOf(92);
        if (iLastIndexOf < 0) {
            iLastIndexOf = str.lastIndexOf(47);
        }
        if (iLastIndexOf >= 0) {
            return str.substring(iLastIndexOf + 1);
        }
        int iLastIndexOf2 = str.lastIndexOf(58);
        return iLastIndexOf2 > 0 ? str.substring(iLastIndexOf2 + 1) : str;
    }

    public static String escape(String str) {
        return replace(str, ".-/:", new String[]{"$dot$", "$dash$", "$slash$", "$colon$"});
    }

    public static com.sun.org.apache.bcel.internal.generic.Type getJCRefType(String str) {
        return com.sun.org.apache.bcel.internal.generic.Type.getType(str);
    }

    public static String getLocalName(String str) {
        int iLastIndexOf = str.lastIndexOf(":");
        return iLastIndexOf > 0 ? str.substring(iLastIndexOf + 1) : str;
    }

    public static String getPrefix(String str) {
        int iLastIndexOf = str.lastIndexOf(":");
        return iLastIndexOf > 0 ? str.substring(0, iLastIndexOf) : "";
    }

    public static String internalName(String str) {
        return str.replace('.', filesep);
    }

    public static boolean isLiteral(String str) {
        int length = str.length();
        for (int i = 0; i < length - 1; i++) {
            if (str.charAt(i) == '{' && str.charAt(i + 1) != '{') {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidQNames(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        while (stringTokenizer.hasMoreTokens()) {
            if (!XML11Char.isXML11ValidQName(stringTokenizer.nextToken())) {
                return false;
            }
        }
        return true;
    }

    public static String noExtName(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf < 0) {
            iLastIndexOf = str.length();
        }
        return str.substring(0, iLastIndexOf);
    }

    public static String pathName(String str) {
        int iLastIndexOf = str.lastIndexOf(47);
        if (iLastIndexOf < 0) {
            iLastIndexOf = str.lastIndexOf(92);
        }
        return str.substring(0, iLastIndexOf + 1);
    }

    public static void println(String str) {
        System.out.println(str);
    }

    public static String replace(String str, String str2, String[] strArr) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            int iIndexOf = str2.indexOf(cCharAt);
            if (iIndexOf >= 0) {
                stringBuffer.append(strArr[iIndexOf]);
            } else {
                stringBuffer.append(cCharAt);
            }
        }
        return stringBuffer.toString();
    }

    public static String toJavaName(String str) {
        if (str.length() <= 0) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        char cCharAt = str.charAt(0);
        if (!Character.isJavaIdentifierStart(cCharAt)) {
            cCharAt = '_';
        }
        stringBuffer.append(cCharAt);
        int length = str.length();
        for (int i = 1; i < length; i++) {
            char cCharAt2 = str.charAt(i);
            if (!Character.isJavaIdentifierPart(cCharAt2)) {
                cCharAt2 = '_';
            }
            stringBuffer.append(cCharAt2);
        }
        return stringBuffer.toString();
    }

    public static void println(char c) {
        System.out.println(c);
    }

    public static String replace(String str, char c, String str2) {
        return str.indexOf(c) < 0 ? str : replace(str, String.valueOf(c), new String[]{str2});
    }
}
