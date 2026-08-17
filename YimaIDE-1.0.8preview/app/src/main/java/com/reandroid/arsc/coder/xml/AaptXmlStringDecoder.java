package com.reandroid.arsc.coder.xml;

import com.reandroid.arsc.item.StringItem;
import com.reandroid.xml.StyleDocument;
import com.reandroid.xml.StyleText;
import com.reandroid.xml.XMLUtil;
import java.io.IOException;
import java.util.Iterator;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AaptXmlStringDecoder implements XmlStringDecoder {
    private String decodePlainToAaptString(String str) {
        return escapeXmlValue(str);
    }

    public static void escapeStyleDocument(StyleDocument styleDocument) {
        Iterator<StyleText> styleTexts = styleDocument.getStyleTexts();
        while (styleTexts.hasNext()) {
            StyleText next = styleTexts.next();
            next.setText(escapeXmlValue(next.getText(false)));
        }
    }

    /* JADX WARN: Code duplicated, block: B:38:0x006f  */
    /* JADX WARN: Code duplicated, block: B:40:0x0074  */
    public static String escapeXmlValue(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder(str.length() + 10);
        char c = charArray[0];
        if (c == '#' || c == '?' || c == '@') {
            sb.append('\\');
        }
        boolean z = false;
        boolean z2 = false;
        int length = 0;
        boolean z3 = true;
        for (char c2 : charArray) {
            if (z2) {
                if (c2 == '>') {
                    length = sb.length() + 1;
                    z = false;
                    z2 = false;
                }
            } else if (c2 == ' ') {
                if (z3) {
                    z = true;
                }
                z3 = true;
            } else if (c2 == '\n') {
                z3 = false;
                z = true;
            } else if (c2 == '\"') {
                sb.append('\\');
                z3 = false;
            } else if (c2 == '\'') {
                z3 = false;
                z = true;
            } else if (c2 != '<') {
                if (c2 == '\\') {
                    sb.append('\\');
                }
                z3 = false;
            } else {
                if (z) {
                    sb.insert(length, '\"').append('\"');
                }
                z3 = false;
                z2 = true;
            }
            sb.append(c2);
        }
        if (z || z3) {
            sb.insert(length, '\"').append('\"');
        }
        return sb.toString();
    }

    public String decodeAttributeValue(StringItem stringItem) {
        return XMLUtil.escapeXmlChars(stringItem.getXml());
    }

    public void serializeText(StringItem stringItem, XmlSerializer xmlSerializer) throws IOException {
        StyleDocument styleDocument = stringItem.getStyleDocument();
        if (styleDocument == null) {
            xmlSerializer.text(decodePlainToAaptString(stringItem.getXml()));
        } else {
            escapeStyleDocument(styleDocument);
            styleDocument.serialize(xmlSerializer);
        }
    }
}
