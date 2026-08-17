package com.reandroid.xml;

import com.reandroid.utils.io.FileUtil;
import com.reandroid.xml.kxml2.KXmlSerializer;
import com.sun.xml.internal.stream.writers.WriterUtility;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLFactory {
    private static KXmlSerializer getKXmlSerializer(XmlSerializer xmlSerializer) {
        if (xmlSerializer instanceof KXmlSerializer) {
            return (KXmlSerializer) xmlSerializer;
        }
        while (xmlSerializer instanceof XmlSerializerWrapper) {
            xmlSerializer = ((XmlSerializerWrapper) xmlSerializer).getBaseSerializer();
            if (xmlSerializer instanceof KXmlSerializer) {
                return (KXmlSerializer) xmlSerializer;
            }
        }
        return null;
    }

    public static XmlPullParser newPullParser(File file) throws XmlPullParserException {
        XmlPullParser xmlPullParserNewPullParser = newPullParser();
        try {
            xmlPullParserNewPullParser.setInput(FileUtil.inputStream(file), null);
            XMLUtil.setLocation(xmlPullParserNewPullParser, file);
            return xmlPullParserNewPullParser;
        } catch (IOException e) {
            throw new XmlPullParserException(e.getMessage());
        }
    }

    public static XmlSerializer newSerializer(OutputStream outputStream, String str) throws IOException {
        XmlSerializer xmlSerializerNewSerializer = newSerializer();
        if (str == null) {
            str = WriterUtility.UTF_8;
        }
        xmlSerializerNewSerializer.setOutput(outputStream, str);
        return xmlSerializerNewSerializer;
    }

    public static void setEnableIndentAttributes(XmlSerializer xmlSerializer, boolean z) {
        KXmlSerializer kXmlSerializer = getKXmlSerializer(xmlSerializer);
        if (kXmlSerializer != null) {
            kXmlSerializer.setEnableIndentAttributes(z);
        }
    }

    public static XmlSerializer newSerializer(File file) throws IOException {
        return newSerializer(FileUtil.outputStream(file));
    }

    public static XmlSerializer newSerializer(File file, String str) throws IOException {
        return newSerializer(FileUtil.outputStream(file), str);
    }

    public static XmlSerializer newSerializer(OutputStream outputStream) throws IOException {
        XmlSerializer xmlSerializerNewSerializer = newSerializer();
        xmlSerializerNewSerializer.setOutput(outputStream, WriterUtility.UTF_8);
        return xmlSerializerNewSerializer;
    }

    public static XmlSerializer newSerializer(Writer writer) throws IOException {
        XmlSerializer xmlSerializerNewSerializer = newSerializer();
        xmlSerializerNewSerializer.setOutput(writer);
        return xmlSerializerNewSerializer;
    }

    public static XmlSerializer newSerializer() {
        return new CloseableSerializer();
    }

    public static XmlPullParser newPullParser(String str) throws XmlPullParserException {
        XmlPullParser xmlPullParserNewPullParser = newPullParser();
        xmlPullParserNewPullParser.setInput(new StringReader(str));
        XMLUtil.setLocation(xmlPullParserNewPullParser, "<XML_STRING>");
        return xmlPullParserNewPullParser;
    }

    public static XmlPullParser newPullParser(Reader reader) throws XmlPullParserException {
        XmlPullParser xmlPullParserNewPullParser = newPullParser();
        xmlPullParserNewPullParser.setInput(reader);
        return xmlPullParserNewPullParser;
    }

    public static XmlPullParser newPullParser(InputStream inputStream) throws XmlPullParserException {
        XmlPullParser xmlPullParserNewPullParser = newPullParser();
        xmlPullParserNewPullParser.setInput(inputStream, null);
        return xmlPullParserNewPullParser;
    }

    public static XmlPullParser newPullParser() {
        CloseableParser closeableParser = new CloseableParser();
        try {
            closeableParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        } catch (Throwable unused) {
        }
        return closeableParser;
    }
}
