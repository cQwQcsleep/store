package com.reandroid.xml;

import com.reandroid.utils.StringsUtil;
import com.reandroid.xml.base.Text;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLText extends XMLNode implements Text {
    private String text;

    public XMLText(String str) {
        this.text = str;
    }

    private static boolean isEmptyOrNewlineBlank(String str) {
        if (str == null) {
            return true;
        }
        if (str.length() != 0 && str.indexOf(10) >= 0) {
            return StringsUtil.isBlank(str);
        }
        return false;
    }

    public static boolean isTextEvent(int i) {
        return i == 4 || i == 6 || i == 7;
    }

    public void appendText(String str) {
        if (str == null) {
            return;
        }
        String str2 = this.text;
        if (str2 == null || str2.length() == 0) {
            this.text = str;
            return;
        }
        this.text += str;
    }

    @Override // com.reandroid.xml.XMLNode, com.reandroid.xml.base.Node, com.reandroid.xml.base.Attribute
    public XMLNodeTree getParentNode() {
        return (XMLNodeTree) super.getParentNode();
    }

    public String getText(boolean z) {
        String str = this.text;
        return z ? XMLUtil.escapeXmlChars(str) : str;
    }

    public boolean isBlank() {
        return StringsUtil.isBlank(getText());
    }

    public boolean isIndent() {
        return isEmptyOrNewlineBlank(getText());
    }

    @Override // com.reandroid.xml.XMLNode, com.reandroid.xml.base.XmlReader
    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        if (isTextEvent(eventType)) {
            while (isTextEvent(eventType)) {
                appendText(xmlPullParser.getText());
                eventType = xmlPullParser.nextToken();
            }
        } else {
            throw new XmlPullParserException("Not TEXT event: " + XMLUtil.toEventName(eventType));
        }
    }

    @Override // com.reandroid.xml.base.XmlSerializable
    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.text(getText());
    }

    @Override // com.reandroid.xml.base.Text
    public void setText(String str) {
        this.text = str;
    }

    @Override // com.reandroid.xml.XMLNode
    public String toString() {
        return getText();
    }

    @Override // com.reandroid.xml.XMLNode
    public void write(Appendable appendable, boolean z, boolean z2) throws IOException {
        String text = getText(z2);
        if (text != null) {
            appendable.append(text);
        }
    }

    public XMLText() {
        this(null);
    }

    @Override // com.reandroid.xml.base.Text
    public String getText() {
        return getText(false);
    }

    public void appendText(char c) {
        if (c == 0) {
            return;
        }
        appendText(String.valueOf(c));
    }
}
