package com.reandroid.xml;

import com.reandroid.utils.StringsUtil;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLDocType extends XMLNode {
    private String mName;

    public XMLDocType(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    @Override // com.reandroid.xml.XMLNode, com.reandroid.xml.base.XmlReader
    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        XMLUtil.expectEvent(xmlPullParser, 10);
        String strTrimStart = StringsUtil.trimStart(xmlPullParser.getText(), ' ');
        setName(strTrimStart);
        if (strTrimStart != null && strTrimStart.contains("html")) {
            XMLUtil.setFeatureRelaxed(xmlPullParser, true);
        }
        xmlPullParser.nextToken();
    }

    @Override // com.reandroid.xml.base.XmlSerializable
    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        String name = getName();
        if (name != null) {
            xmlSerializer.docdecl(" ".concat(name));
        }
    }

    public void setName(String str) {
        this.mName = str;
    }

    @Override // com.reandroid.xml.XMLNode
    public String toString() {
        return "<!DOCTYPE " + getName() + ">";
    }

    @Override // com.reandroid.xml.XMLNode
    public void write(Appendable appendable, boolean z, boolean z2) throws IOException {
        String name = getName();
        if (name != null) {
            appendable.append("<!DOCTYPE");
            appendable.append(' ');
            appendable.append(name);
            appendable.append('>');
        }
    }

    public XMLDocType() {
        this(null);
    }
}
