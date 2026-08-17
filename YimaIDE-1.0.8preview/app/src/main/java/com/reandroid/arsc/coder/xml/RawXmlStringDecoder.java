package com.reandroid.arsc.coder.xml;

import com.reandroid.arsc.item.StringItem;
import com.reandroid.xml.XMLUtil;
import java.io.IOException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class RawXmlStringDecoder implements XmlStringDecoder {
    @Override // com.reandroid.arsc.coder.xml.XmlStringDecoder
    public String decodeAttributeValue(StringItem stringItem) {
        return XMLUtil.escapeXmlChars(stringItem.getXml());
    }

    @Override // com.reandroid.arsc.coder.xml.XmlStringDecoder
    public void serializeText(StringItem stringItem, XmlSerializer xmlSerializer) throws IOException {
        stringItem.serializeText(xmlSerializer, true);
    }
}
