package com.reandroid.apk;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XmlHelper {
    public static final String FEATURE_INDENT = "http://xmlpull.org/v1/doc/features.html#indent-output";
    public static final String RESOURCES_TAG = "resources";

    public static void closeSilent(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException unused) {
            }
        }
    }

    private static boolean findElement(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return false;
            }
            if (next == 2 && str.equals(xmlPullParser.getName())) {
                return true;
            }
        }
    }

    public static Map<String, String> mapAttributes(XmlPullParser xmlPullParser) {
        HashMap map = new HashMap();
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            int iIndexOf = attributeName.indexOf(58);
            if (iIndexOf > 0 && iIndexOf < attributeName.length() && !attributeName.startsWith("xmlns:")) {
                attributeName = attributeName.substring(iIndexOf + 1);
            }
            map.put(attributeName, xmlPullParser.getAttributeValue(i));
        }
        return map;
    }

    public static Map<String, String> readAttributes(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        if (findElement(xmlPullParser, str)) {
            return mapAttributes(xmlPullParser);
        }
        return null;
    }

    public static void setFeatureSafe(XmlSerializer xmlSerializer, String str, boolean z) {
        try {
            xmlSerializer.setFeature(str, z);
        } catch (Throwable unused) {
        }
    }

    public static void setIndent(XmlSerializer xmlSerializer, boolean z) {
        setFeatureSafe(xmlSerializer, FEATURE_INDENT, z);
    }

    public static String toXMLTagName(String str) {
        return (str.length() <= 0 || str.charAt(0) != '^') ? str : str.substring(1);
    }
}
