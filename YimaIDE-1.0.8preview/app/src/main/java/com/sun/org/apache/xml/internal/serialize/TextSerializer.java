package com.sun.org.apache.xml.internal.serialize;

import defpackage.x73;
import java.io.IOException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
public class TextSerializer extends BaseMarkupSerializer {
    public TextSerializer() {
        super(new OutputFormat("text", null, false));
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        try {
            ElementState elementStateContent = content();
            elementStateContent.inCData = false;
            elementStateContent.doCData = false;
            printText(cArr, i, i2, true, true);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public void comment(String str) {
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer, org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) {
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public ElementState content() {
        ElementState elementState = getElementState();
        if (!isDocumentState()) {
            if (elementState.empty) {
                elementState.empty = false;
            }
            elementState.afterElement = false;
        }
        return elementState;
    }

    @Override // org.xml.sax.DocumentHandler
    public void endElement(String str) throws SAXException {
        try {
            endElementIO(str);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    public void endElementIO(String str) throws IOException {
        getElementState();
        ElementState elementStateLeaveElementState = leaveElementState();
        elementStateLeaveElementState.afterElement = true;
        elementStateLeaveElementState.empty = false;
        if (isDocumentState()) {
            this._printer.flush();
        }
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public String getEntityRef(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public void processingInstructionIO(String str, String str2) throws IOException {
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public void serializeElement(Element element) throws IOException {
        String tagName = element.getTagName();
        ElementState elementState = getElementState();
        if (isDocumentState() && !this._started) {
            startDocument(tagName);
        }
        boolean z = elementState.preserveSpace;
        if (!element.hasChildNodes()) {
            if (isDocumentState()) {
                return;
            }
            elementState.afterElement = true;
            elementState.empty = false;
            return;
        }
        enterElementState(null, null, tagName, z);
        for (Node firstChild = element.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            serializeNode(firstChild);
        }
        endElementIO(tagName);
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public void serializeNode(Node node) throws IOException {
        short nodeType = node.getNodeType();
        if (nodeType == 1) {
            serializeElement((Element) node);
            return;
        }
        if (nodeType == 9 || nodeType == 11) {
            for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
                serializeNode(firstChild);
            }
            return;
        }
        if (nodeType == 3) {
            if (node.getNodeValue() != null) {
                characters(node.getNodeValue(), true);
            }
        } else if (nodeType == 4 && node.getNodeValue() != null) {
            characters(node.getNodeValue(), true);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer, com.sun.org.apache.xml.internal.serialize.Serializer
    public void setOutputFormat(OutputFormat outputFormat) {
        if (outputFormat == null) {
            outputFormat = new OutputFormat("text", null, false);
        }
        super.setOutputFormat(outputFormat);
    }

    public void startDocument(String str) throws IOException {
        this._printer.leaveDTD();
        this._started = true;
        serializePreRoot();
    }

    @Override // org.xml.sax.DocumentHandler
    public void startElement(String str, AttributeList attributeList) throws SAXException {
        try {
            ElementState elementState = getElementState();
            if (isDocumentState() && !this._started) {
                startDocument(str);
            }
            enterElementState(null, null, str, elementState.preserveSpace);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        if (str3 != null) {
            str2 = str3;
        }
        endElement(str2);
    }

    public void characters(String str, boolean z) throws IOException {
        ElementState elementStateContent = content();
        elementStateContent.inCData = false;
        elementStateContent.doCData = false;
        printText(str, true, true);
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        if (str3 != null) {
            str2 = str3;
        }
        startElement(str2, null);
    }
}
