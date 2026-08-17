package com.reandroid.xml;

import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DocumentSerializer implements XmlSerializer {
    private XMLElement currentElement;
    private XMLText currentText;
    private XMLDocument document;
    private final Map<String, String> namespaceMap = new HashMap();
    private final Map<String, Object> propertiesMap = new HashMap();
    private final Map<String, Boolean> featureMap = new HashMap();

    public DocumentSerializer(XMLDocument xMLDocument) {
        this.document = xMLDocument;
    }

    private void appendText(String str) {
        XMLText xMLTextNewText = this.currentText;
        if (xMLTextNewText == null) {
            xMLTextNewText = getCurrentNode().newText();
            this.currentText = xMLTextNewText;
        }
        xMLTextNewText.appendText(str);
    }

    private XMLNodeTree getCurrentNode() {
        XMLElement xMLElement = this.currentElement;
        return xMLElement == null ? this.document : xMLElement;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer attribute(String str, String str2, String str3) throws IOException {
        XMLAttribute xMLAttributeNewAttribute = this.currentElement.newAttribute();
        xMLAttributeNewAttribute.set(str2, str3);
        xMLAttributeNewAttribute.setNamespace(str, getPrefix(str, true));
        return this;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void cdsect(String str) throws IllegalStateException, IOException, IllegalArgumentException {
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void comment(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        getCurrentNode().newComment().setText(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void docdecl(String str) throws IllegalStateException, IOException, IllegalArgumentException {
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void endDocument() throws IllegalStateException, IOException, IllegalArgumentException {
        this.currentElement = null;
        this.currentText = null;
        this.namespaceMap.clear();
        this.propertiesMap.clear();
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer endTag(String str, String str2) throws IOException {
        XMLElement xMLElement = this.currentElement;
        if (xMLElement == null) {
            r8g.a("Invalid state endTag ", str2);
            return null;
        }
        if (!xMLElement.equalsName(str2)) {
            v8g.a("Mismatch end: ", str2, ", expect = ", xMLElement.getName());
            return null;
        }
        this.currentElement = xMLElement.getParentElement();
        this.currentText = null;
        return this;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void entityRef(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        appendText(XMLUtil.decodeEntityRef(str));
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void flush() throws IOException {
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public int getDepth() {
        XMLElement xMLElement = this.currentElement;
        if (xMLElement != null) {
            return xMLElement.getDepth();
        }
        return 0;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public boolean getFeature(String str) {
        Boolean bool = this.featureMap.get(str);
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public String getName() {
        XMLElement xMLElement = this.currentElement;
        if (xMLElement != null) {
            return xMLElement.getName(false);
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public String getNamespace() {
        XMLNamespace namespace;
        XMLElement xMLElement = this.currentElement;
        if (xMLElement == null || (namespace = xMLElement.getNamespace()) == null) {
            return null;
        }
        return namespace.getUri();
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public String getPrefix(String str, boolean z) throws IllegalArgumentException {
        if (str == null || str.length() == 0) {
            return null;
        }
        Map<String, String> map = this.namespaceMap;
        String str2 = map.get(str);
        if (str2 != null || !z) {
            return str2;
        }
        for (int i = 0; i < 1000; i++) {
            String str3 = Constants.ATTRNAME_NS + i;
            if (!map.containsValue(str3)) {
                map.put(str, str3);
                return str3;
            }
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public Object getProperty(String str) {
        return this.propertiesMap.get(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void ignorableWhitespace(String str) throws IllegalStateException, IOException, IllegalArgumentException {
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void processingInstruction(String str) throws IllegalStateException, IOException, IllegalArgumentException {
    }

    public void setDocument(XMLDocument xMLDocument) {
        this.document = xMLDocument;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setFeature(String str, boolean z) throws IllegalStateException, IllegalArgumentException {
        this.featureMap.put(str, Boolean.valueOf(z));
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setOutput(OutputStream outputStream, String str) throws IllegalStateException, IOException, IllegalArgumentException {
        throw new IOException("XMLDocument serializer");
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setPrefix(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException {
        this.namespaceMap.put(str2, str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setProperty(String str, Object obj) throws IllegalStateException, IllegalArgumentException {
        this.propertiesMap.put(str, obj);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void startDocument(String str, Boolean bool) throws IllegalStateException, IOException, IllegalArgumentException {
        this.document.setEncoding(str);
        this.document.setStandalone(bool);
        this.currentElement = null;
        this.currentText = null;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer startTag(String str, String str2) throws IOException {
        this.currentText = null;
        XMLNodeTree xMLNodeTree = this.currentElement;
        if (xMLNodeTree == null) {
            xMLNodeTree = this.document;
        }
        XMLElement xMLElementNewElement = xMLNodeTree.newElement();
        xMLElementNewElement.setName(str2);
        this.currentElement = xMLElementNewElement;
        return this;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer text(char[] cArr, int i, int i2) throws IllegalStateException, IOException, IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        int i3 = i2 + i;
        while (i < i3) {
            sb.append(cArr[i]);
            i++;
        }
        appendText(sb.toString());
        return this;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setOutput(Writer writer) throws IllegalStateException, IOException, IllegalArgumentException {
        throw new IOException("XMLDocument serializer");
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer text(String str) throws IOException {
        appendText(str);
        return this;
    }
}
