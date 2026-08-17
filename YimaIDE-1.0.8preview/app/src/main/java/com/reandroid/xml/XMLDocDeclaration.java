package com.reandroid.xml;

import com.reandroid.xml.base.XmlReader;
import com.reandroid.xml.base.XmlSerializable;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLDocDeclaration implements XmlReader, XmlSerializable {
    private String encoding;
    private Boolean standalone;
    private String version;

    public void clear() {
        version(null);
        encoding(null);
        standalone(null);
    }

    public String encoding() {
        return this.encoding;
    }

    public Object get(String str) {
        if ("version".equals(str) || XMLUtil.PROPERTY_XMLDECL_VERSION.equals(str)) {
            return getVersion();
        }
        if ("encoding".equals(str)) {
            return encoding();
        }
        if (Constants.ATTRNAME_OUTPUT_STANDALONE.equals(str) || XMLUtil.PROPERTY_XMLDECL_STANDALONE.equals(str)) {
            return standalone();
        }
        return null;
    }

    public String getVersion() {
        String strVersion = version();
        return (strVersion == null && isValid()) ? "1.0" : strVersion;
    }

    public boolean isValid() {
        return (version() == null && encoding() == null && standalone() == null) ? false : true;
    }

    @Override // com.reandroid.xml.base.XmlReader
    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        clear();
        if (xmlPullParser.getEventType() == 0) {
            xmlPullParser.nextToken();
        }
        version((String) XMLUtil.getPropertySafe(xmlPullParser, XMLUtil.PROPERTY_XMLDECL_VERSION));
        encoding(xmlPullParser.getInputEncoding());
        standalone((Boolean) XMLUtil.getPropertySafe(xmlPullParser, XMLUtil.PROPERTY_XMLDECL_STANDALONE));
    }

    @Override // com.reandroid.xml.base.XmlSerializable
    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        if (isValid()) {
            xmlSerializer.startDocument(encoding(), standalone());
        }
    }

    public void set(String str, Object obj) {
        if ("version".equals(str) || XMLUtil.PROPERTY_XMLDECL_VERSION.equals(str)) {
            version((String) obj);
            return;
        }
        if ("encoding".equals(str)) {
            encoding((String) obj);
        } else if (Constants.ATTRNAME_OUTPUT_STANDALONE.equals(str) || XMLUtil.PROPERTY_XMLDECL_STANDALONE.equals(str)) {
            standalone((Boolean) obj);
        }
    }

    public Boolean standalone() {
        return this.standalone;
    }

    public String toString() {
        if (!isValid()) {
            return null;
        }
        StringBuilder sb = new StringBuilder("<?xml version='");
        sb.append(getVersion());
        sb.append("'");
        String strEncoding = encoding();
        if (strEncoding != null) {
            sb.append(" encoding='");
            sb.append(strEncoding);
            sb.append("'");
        }
        Boolean boolStandalone = standalone();
        if (boolStandalone != null) {
            sb.append(" standalone='");
            sb.append(boolStandalone);
            sb.append("'");
        }
        sb.append(" ?>");
        return sb.toString();
    }

    public String version() {
        return this.version;
    }

    public void encoding(String str) {
        this.encoding = str;
    }

    public void standalone(Boolean bool) {
        this.standalone = bool;
    }

    public void version(String str) {
        this.version = str;
    }
}
