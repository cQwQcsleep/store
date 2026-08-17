package com.reandroid.xml;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLProcessingInstruction extends XMLNode {
    private String mText;

    public XMLProcessingInstruction(String str) {
        this.mText = str;
    }

    public String getText() {
        return this.mText;
    }

    @Override // com.reandroid.xml.XMLNode, com.reandroid.xml.base.XmlReader
    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        XMLUtil.expectEvent(xmlPullParser, 8);
        setText(xmlPullParser.getText());
        xmlPullParser.nextToken();
    }

    @Override // com.reandroid.xml.base.XmlSerializable
    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        String text = getText();
        if (text != null) {
            xmlSerializer.processingInstruction(text);
        }
    }

    public void setText(String str) {
        this.mText = str;
    }

    @Override // com.reandroid.xml.XMLNode
    public String toString() {
        return "<?" + getText() + "?>";
    }

    @Override // com.reandroid.xml.XMLNode
    public void write(Appendable appendable, boolean z, boolean z2) throws IOException {
        String text = getText();
        if (text != null) {
            appendable.append("<?");
            appendable.append(text);
            appendable.append("?>");
        }
    }

    public XMLProcessingInstruction() {
        this(null);
    }
}
