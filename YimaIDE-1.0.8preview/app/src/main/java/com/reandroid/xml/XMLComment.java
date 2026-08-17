package com.reandroid.xml;

import com.reandroid.xml.base.Comment;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLComment extends XMLNode implements Comment {
    private String text;

    public XMLComment(String str) {
        this();
        setText(str);
    }

    public String getText(boolean z) {
        String str = this.text;
        return z ? XMLUtil.escapeXmlChars(str) : str;
    }

    @Override // com.reandroid.xml.XMLNode, com.reandroid.xml.base.XmlReader
    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        XMLUtil.expectEvent(xmlPullParser, 9);
        setText(xmlPullParser.getText());
        xmlPullParser.nextToken();
    }

    @Override // com.reandroid.xml.base.XmlSerializable
    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.comment(getText(false));
    }

    @Override // com.reandroid.xml.base.Comment
    public void setText(String str) {
        this.text = str;
    }

    @Override // com.reandroid.xml.XMLNode
    public String toString() {
        return "<!--" + getText() + "-->";
    }

    @Override // com.reandroid.xml.XMLNode
    public void write(Appendable appendable, boolean z, boolean z2) throws IOException {
        appendable.append("<!--");
        appendable.append(getText(z2));
        appendable.append("-->");
    }

    public XMLComment() {
    }

    @Override // com.reandroid.xml.base.Comment
    public String getText() {
        return getText(false);
    }
}
