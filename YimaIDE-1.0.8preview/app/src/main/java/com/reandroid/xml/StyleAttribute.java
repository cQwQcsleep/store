package com.reandroid.xml;

import java.io.IOException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StyleAttribute extends XMLAttribute {
    @Override // com.reandroid.xml.XMLAttribute, com.reandroid.xml.XMLNode, com.reandroid.xml.base.Node, com.reandroid.xml.base.Attribute
    public StyleElement getParentNode() {
        return (StyleElement) super.getParentNode();
    }

    @Override // com.reandroid.xml.XMLAttribute, com.reandroid.xml.base.XmlSerializable
    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.attribute(null, getName(), getValueAsString());
    }

    @Override // com.reandroid.xml.XMLAttribute
    public void setFrom(XMLAttribute xMLAttribute) {
        set(xMLAttribute.getName(true), xMLAttribute.getValueAsString(false));
    }

    @Override // com.reandroid.xml.XMLAttribute
    public StyleAttribute set(String str, String str2) {
        super.set(str, str2);
        return this;
    }
}
