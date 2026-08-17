package com.sun.org.apache.xml.internal.utils;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.File;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SystemIDResolver {
    private static String getAbsolutePathFromRelativePath(String str) {
        return new File(str).getAbsolutePath();
    }

    public static String getAbsoluteURI(String str) {
        int iIndexOf;
        if (!isAbsoluteURI(str)) {
            return getAbsoluteURIFromRelative(str);
        }
        if (!str.startsWith("file:")) {
            return str;
        }
        String strSubstring = str.substring(5);
        if (!strSubstring.startsWith(PsuedoNames.PSEUDONAME_ROOT)) {
            return getAbsoluteURIFromRelative(str.substring(5));
        }
        if ((strSubstring.startsWith("///") || !strSubstring.startsWith("//")) && (iIndexOf = str.indexOf(58, 5)) > 0) {
            int i = iIndexOf - 1;
            String strSubstring2 = str.substring(i);
            try {
                if (!isAbsolutePath(strSubstring2)) {
                    str = str.substring(0, i) + getAbsolutePathFromRelativePath(strSubstring2);
                }
            } catch (SecurityException unused) {
                return str;
            }
        }
        return replaceChars(str);
    }

    public static String getAbsoluteURIFromRelative(String str) {
        String absolutePathFromRelativePath;
        String strConcat;
        if (str == null || str.length() == 0) {
            return "";
        }
        if (isAbsolutePath(str)) {
            absolutePathFromRelativePath = str;
        } else {
            try {
                absolutePathFromRelativePath = getAbsolutePathFromRelativePath(str);
            } catch (SecurityException unused) {
                return "file:".concat(str);
            }
        }
        if (absolutePathFromRelativePath != null) {
            strConcat = absolutePathFromRelativePath.startsWith(File.separator) ? "file://".concat(absolutePathFromRelativePath) : "file:///".concat(absolutePathFromRelativePath);
        } else {
            strConcat = "file:".concat(str);
        }
        return replaceChars(strConcat);
    }

    public static boolean isAbsolutePath(String str) {
        if (str == null) {
            return false;
        }
        return new File(str).isAbsolute();
    }

    public static boolean isAbsoluteURI(String str) {
        if (isWindowsAbsolutePath(str)) {
            return false;
        }
        int iIndexOf = str.indexOf(35);
        int iIndexOf2 = str.indexOf(63);
        int iIndexOf3 = str.indexOf(47);
        int iIndexOf4 = str.indexOf(58);
        int length = str.length() - 1;
        if (iIndexOf <= 0) {
            iIndexOf = length;
        }
        if (iIndexOf2 <= 0 || iIndexOf2 >= iIndexOf) {
            iIndexOf2 = iIndexOf;
        }
        if (iIndexOf3 <= 0 || iIndexOf3 >= iIndexOf2) {
            iIndexOf3 = iIndexOf2;
        }
        return iIndexOf4 > 0 && iIndexOf4 < iIndexOf3;
    }

    private static boolean isWindowsAbsolutePath(String str) {
        return isAbsolutePath(str) && str.length() > 2 && str.charAt(1) == ':' && Character.isLetter(str.charAt(0)) && (str.charAt(2) == '\\' || str.charAt(2) == '/');
    }

    private static String replaceChars(String str) {
        StringBuffer stringBuffer = new StringBuffer(str);
        int length = stringBuffer.length();
        int i = 0;
        while (i < length) {
            char cCharAt = stringBuffer.charAt(i);
            if (cCharAt == ' ') {
                stringBuffer.setCharAt(i, '%');
                stringBuffer.insert(i + 1, "20");
                length += 2;
                i += 2;
            } else if (cCharAt == '\\') {
                stringBuffer.setCharAt(i, '/');
            }
            i++;
        }
        return stringBuffer.toString();
    }

    public static String getAbsoluteURI(String str, String str2) throws TransformerException {
        if (str2 != null && str2.length() != 0) {
            try {
                return replaceChars(new URI(new URI(getAbsoluteURI(str2)), str).toString());
            } catch (URI.MalformedURIException e) {
                throw new TransformerException(e);
            }
        }
        return getAbsoluteURI(str);
    }
}
