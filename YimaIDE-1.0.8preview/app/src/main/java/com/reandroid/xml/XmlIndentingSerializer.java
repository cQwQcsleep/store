package com.reandroid.xml;

import java.io.IOException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XmlIndentingSerializer extends XmlSerializerWrapper {
    public XmlIndentingSerializer(XmlSerializer xmlSerializer) {
        super(xmlSerializer);
    }

    private static boolean containsIndenting(XmlSerializer xmlSerializer) {
        boolean z;
        while (true) {
            z = xmlSerializer instanceof XmlIndentingSerializer;
            if (z || !(xmlSerializer instanceof XmlSerializerWrapper)) {
                break;
            }
            xmlSerializer = ((XmlSerializerWrapper) xmlSerializer).getBaseSerializer();
        }
        return z;
    }

    public static XmlSerializer create(XmlSerializer xmlSerializer) {
        return containsIndenting(xmlSerializer) ? xmlSerializer : new XmlIndentingSerializer(xmlSerializer);
    }

    private void setIndentFeature() {
        XMLUtil.setFeatureSafe((XmlSerializer) this, XMLUtil.FEATURE_INDENT_OUTPUT, true);
    }

    @Override // com.reandroid.xml.XmlSerializerWrapper, org.xmlpull.v1.XmlSerializer
    public void startDocument(String str, Boolean bool) throws IllegalStateException, IOException, IllegalArgumentException {
        super.startDocument(str, bool);
        setIndentFeature();
    }

    @Override // com.reandroid.xml.XmlSerializerWrapper, org.xmlpull.v1.XmlSerializer
    public XmlSerializer startTag(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException {
        setIndentFeature();
        return super.startTag(str, str2);
    }
}
