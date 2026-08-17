package com.reandroid.apk.xmlencoder;

import com.reandroid.apk.xmlencoder.EncodeUtil;
import java.io.File;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class EncodeUtil {
    public static String getEntryNameFromResFile(File file) {
        String name = file.getName();
        if (name.endsWith(".9.png")) {
            return name.substring(0, name.length() - 6);
        }
        int iLastIndexOf = name.lastIndexOf(46);
        return iLastIndexOf > 0 ? name.substring(0, iLastIndexOf) : name;
    }

    public static String getEntryPathFromResFile(File file) {
        File parentFile = file.getParentFile();
        return parentFile.getParentFile().getName() + "/" + parentFile.getName() + "/" + file.getName();
    }

    public static String getQualifiersFromResFile(File file) {
        String name = file.getParentFile().getName();
        int iIndexOf = name.indexOf(45);
        return iIndexOf > 0 ? name.substring(iIndexOf) : "";
    }

    public static String getTypeNameFromResFile(File file) {
        String name = file.getParentFile().getName();
        int iIndexOf = name.indexOf(45);
        if (iIndexOf > 0) {
            name = name.substring(0, iIndexOf);
        }
        return (name.equals("plurals") || !name.endsWith("s")) ? name : name.substring(0, name.length() - 1);
    }

    private static String getValuesXmlCompare(File file) {
        String lowerCase = file.getName().toLowerCase();
        if (lowerCase.equals("public.xml")) {
            return "0";
        }
        if (lowerCase.equals("ids.xml")) {
            return "1";
        }
        return lowerCase.contains("attr") ? "2" : "3 ".concat(lowerCase);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String sanitizeType(String str) {
        if (str.length() < 2) {
            return str;
        }
        char cCharAt = str.charAt(0);
        if (cCharAt == '^' || cCharAt == '+' || cCharAt == '*') {
            str = str.substring(1);
        }
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : charArray) {
            if (c > 'z' || c < 'a') {
                break;
            }
            sb.append(c);
        }
        String string = sb.toString();
        return ("plurals".equals(string) || !string.endsWith("s")) ? string : string.substring(0, string.length() - 1);
    }

    public static void sortValuesXml(List<File> list) {
        list.sort(new Comparator() { // from class: hb4
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return EncodeUtil.getValuesXmlCompare((File) obj).compareTo(EncodeUtil.getValuesXmlCompare((File) obj2));
            }
        });
    }
}
