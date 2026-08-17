package com.reandroid.xml;

import com.reandroid.apk.XmlHelper;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.xml.kxml2.KXmlParser;
import com.reandroid.xml.kxml2.KXmlSerializer;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import java.io.Closeable;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLUtil {
    public static String[] EVENT_TYPES;
    public static final String FEATURE_INDENT_OUTPUT;
    public static final String FEATURE_RELAXED;
    public static final boolean KEEP_CHARSET_ENCODING;
    public static final String PROPERTY_LOCATION;
    public static final String PROPERTY_XMLDECL_STANDALONE;
    public static final String PROPERTY_XMLDECL_VERSION;

    static {
        KEEP_CHARSET_ENCODING = System.getProperty("com.reandroid.xml.KeepCharsetEncoding") != null;
        PROPERTY_XMLDECL_VERSION = ObjectsUtil.of("http://xmlpull.org/v1/doc/properties.html#xmldecl-version");
        PROPERTY_XMLDECL_STANDALONE = ObjectsUtil.of("http://xmlpull.org/v1/doc/properties.html#xmldecl-standalone");
        FEATURE_INDENT_OUTPUT = ObjectsUtil.of(XmlHelper.FEATURE_INDENT);
        PROPERTY_LOCATION = ObjectsUtil.of("http://xmlpull.org/v1/doc/properties.html#location");
        FEATURE_RELAXED = ObjectsUtil.of("http://xmlpull.org/v1/doc/features.html#relaxed");
        EVENT_TYPES = new String[]{"START_DOCUMENT", "END_DOCUMENT", "START_TAG", "END_TAG", "TEXT", "CDSECT", "ENTITY_REF", "IGNORABLE_WHITESPACE", "PROCESSING_INSTRUCTION", "COMMENT", "DOCDECL"};
    }

    private static void appendCodePoint(StringBuilder sb, int i) {
        sb.append("&#");
        sb.append(i);
        sb.append(";");
    }

    public static void close(XmlSerializer xmlSerializer) {
        if (xmlSerializer instanceof XmlSerializerWrapper) {
            close(((XmlSerializerWrapper) xmlSerializer).getBaseSerializer());
            return;
        }
        if (xmlSerializer != null) {
            try {
                xmlSerializer.flush();
            } catch (IOException unused) {
            }
            if (xmlSerializer instanceof Closeable) {
                try {
                    ((Closeable) xmlSerializer).close();
                } catch (IOException unused2) {
                }
            }
        }
    }

    public static String decodeEntityRef(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (str.equals("lt")) {
            return "<";
        }
        if (str.equals("gt")) {
            return ">";
        }
        if (str.equals("amp")) {
            return "&";
        }
        if (str.equals("quote")) {
            return "\"";
        }
        if (str.equals("apos")) {
            return "'";
        }
        if (str.startsWith("#")) {
            return new StringBuilder().appendCodePoint(str.startsWith("#x") ? Integer.parseInt(str.substring(2), 16) : Integer.parseInt(str.substring(1))).toString();
        }
        return str;
    }

    public static int ensureStartTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        while (eventType != 2 && eventType != 1) {
            eventType = xmlPullParser.next();
        }
        return eventType;
    }

    public static int ensureTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        while (eventType != 2 && eventType != 3 && eventType != 1) {
            eventType = xmlPullParser.next();
        }
        return eventType;
    }

    public static String escapeXmlChars(String str, boolean z) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(length + 16);
        int i = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\n' || cCharAt == '\r' || cCharAt == '\t') {
                if (z) {
                    appendCodePoint(sb, cCharAt);
                } else {
                    sb.append(cCharAt);
                }
            } else if (cCharAt == '&') {
                sb.append(SerializerConstants.ENTITY_AMP);
            } else if (cCharAt == '<') {
                sb.append(SerializerConstants.ENTITY_LT);
            } else if (cCharAt == '>') {
                sb.append(SerializerConstants.ENTITY_GT);
            } else if ((cCharAt >= ' ' && cCharAt <= 55295) || (cCharAt >= 57344 && cCharAt <= 65533)) {
                sb.append(cCharAt);
            } else if (!Character.isHighSurrogate(cCharAt) || i >= length - 1) {
                appendCodePoint(sb, cCharAt);
            } else {
                i++;
                appendCodePoint(sb, Character.toCodePoint(cCharAt, str.charAt(i)));
            }
            i++;
        }
        return sb.toString();
    }

    public static void expectEvent(XmlPullParser xmlPullParser, int i) throws XmlPullParserException {
        int eventType = xmlPullParser.getEventType();
        if (eventType == i) {
            return;
        }
        throw new XmlPullParserException("Expecting event: " + toEventName(i) + ", but found: " + toEventName(eventType));
    }

    public static boolean getFeatureSafe(XmlPullParser xmlPullParser, String str, boolean z) {
        try {
            return xmlPullParser.getFeature(str);
        } catch (Throwable unused) {
            return z;
        }
    }

    public static KXmlSerializer getKXmlSerializer(XmlSerializer xmlSerializer) {
        if (xmlSerializer instanceof KXmlSerializer) {
            return (KXmlSerializer) xmlSerializer;
        }
        if (xmlSerializer instanceof XmlSerializerWrapper) {
            return getKXmlSerializer(((XmlSerializerWrapper) xmlSerializer).getBaseSerializer());
        }
        return null;
    }

    public static Object getLocation(XmlPullParser xmlPullParser) {
        try {
            return xmlPullParser.getProperty(PROPERTY_LOCATION);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Object getPropertySafe(XmlPullParser xmlPullParser, String str) {
        try {
            return xmlPullParser.getProperty(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getSimplePositionDescription(XmlPullParser xmlPullParser) {
        if (xmlPullParser instanceof KXmlParser) {
            return ((KXmlParser) xmlPullParser).getSimplePositionDescription();
        }
        if (xmlPullParser != null) {
            return xmlPullParser.getPositionDescription();
        }
        return null;
    }

    public static boolean hasFeatureRelaxed(XmlPullParser xmlPullParser) {
        return getFeatureSafe(xmlPullParser, FEATURE_RELAXED, false);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static void setFeatureRelaxed(XmlPullParser xmlPullParser, boolean z) {
        setFeatureSafe(xmlPullParser, FEATURE_RELAXED, z);
    }

    public static void setFeatureSafe(XmlPullParser xmlPullParser, String str, boolean z) {
        try {
            xmlPullParser.setFeature(str, z);
        } catch (Throwable unused) {
        }
    }

    public static void setLocation(XmlPullParser xmlPullParser, Object obj) {
        try {
            xmlPullParser.setProperty(PROPERTY_LOCATION, obj);
        } catch (Throwable unused) {
        }
    }

    public static String splitName(String str) {
        if (str == null) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf(58);
        if (iLastIndexOf >= 0) {
            str = str.substring(iLastIndexOf + 1);
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            return null;
        }
        return strTrim;
    }

    public static String splitPrefix(String str) {
        int iIndexOf;
        if (str != null && (iIndexOf = str.indexOf(58)) > 0) {
            return str.substring(0, iIndexOf);
        }
        return null;
    }

    public static String toEventName(int i) {
        String[] strArr = EVENT_TYPES;
        return (i < 0 || i >= strArr.length) ? String.valueOf(i) : strArr[i];
    }

    public static void setFeatureSafe(XmlSerializer xmlSerializer, String str, boolean z) {
        try {
            xmlSerializer.setFeature(str, z);
        } catch (Throwable unused) {
        }
    }

    public static String escapeXmlChars(String str) {
        return escapeXmlChars(str, false);
    }
}
