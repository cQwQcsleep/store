package com.reandroid.xml.source;

import com.reandroid.xml.XMLFactory;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLStringParserSource implements XMLParserSource {
    private final String path;
    private final String xmlContent;

    public XMLStringParserSource(String str, String str2) {
        this.path = str;
        this.xmlContent = str2;
    }

    public XmlPullParser getParser() throws XmlPullParserException {
        return XMLFactory.newPullParser(this.xmlContent);
    }

    public String getPath() {
        return this.path;
    }

    public String getXmlContent() {
        return this.xmlContent;
    }

    public String toString() {
        return getPath();
    }
}
