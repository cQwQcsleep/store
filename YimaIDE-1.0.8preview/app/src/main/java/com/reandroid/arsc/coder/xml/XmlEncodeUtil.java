package com.reandroid.arsc.coder.xml;

import java.io.File;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class XmlEncodeUtil {
    public static String getQualifiersFromValuesXml(File file) {
        String name = file.getParentFile().getName();
        int iIndexOf = name.indexOf(45);
        return iIndexOf > 0 ? name.substring(iIndexOf) : XmlPullParser.NO_NAMESPACE;
    }

    public static String getTypeFromValuesXml(File file) {
        String name = file.getName();
        String strSubstring = name.substring(0, name.length() - 4);
        return (strSubstring.equals("plurals") || !strSubstring.endsWith("s")) ? strSubstring : strSubstring.substring(0, strSubstring.length() - 1);
    }
}
