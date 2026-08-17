package com.reandroid.xml;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLCDSect extends XMLNode {
    private String text;

    public XMLCDSect(String str) {
        this.text = str;
    }

    public String getText() {
        return this.text;
    }

    @Override // com.reandroid.xml.XMLNode, com.reandroid.xml.base.XmlReader
    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        XMLUtil.expectEvent(xmlPullParser, 5);
        setText(xmlPullParser.getText());
        xmlPullParser.nextToken();
    }

    @Override // com.reandroid.xml.base.XmlSerializable
    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.cdsect(getText());
    }

    public void setText(String str) {
        this.text = str;
    }

    @Override // com.reandroid.xml.XMLNode
    public String toString() {
        return "<![CDATA[" + getText() + "]]>";
    }

    @Override // com.reandroid.xml.XMLNode
    public void write(Appendable appendable, boolean z, boolean z2) throws IOException {
        String text = getText();
        if (text != null) {
            appendable.append("<![CDATA[");
            appendable.append(text);
            appendable.append("]]>");
        }
    }

    public XMLCDSect() {
        this(null);
    }
}
