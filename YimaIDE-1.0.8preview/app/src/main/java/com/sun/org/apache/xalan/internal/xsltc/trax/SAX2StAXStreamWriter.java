package com.sun.org.apache.xalan.internal.xsltc.trax;

import defpackage.x73;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SAX2StAXStreamWriter extends SAX2StAXBaseWriter {
    private boolean needToCallStartDocument = false;
    private XMLStreamWriter writer;

    public SAX2StAXStreamWriter(XMLStreamWriter xMLStreamWriter) {
        this.writer = xMLStreamWriter;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXBaseWriter, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        super.characters(cArr, i, i2);
        try {
            if (this.isCDATA) {
                return;
            }
            this.writer.writeCharacters(cArr, i, i2);
        } catch (XMLStreamException e) {
            x73.a(e);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXBaseWriter, org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
        if (this.needToCallStartDocument) {
            writeStartDocument();
        }
        super.comment(cArr, i, i2);
        try {
            this.writer.writeComment(new String(cArr, i, i2));
        } catch (XMLStreamException e) {
            x73.a(e);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXBaseWriter, org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
        try {
            this.writer.writeCData(this.CDATABuffer.toString());
            super.endCDATA();
        } catch (XMLStreamException e) {
            x73.a(e);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXBaseWriter, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        try {
            this.writer.writeEndDocument();
            super.endDocument();
        } catch (XMLStreamException e) {
            x73.a(e);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXBaseWriter, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        try {
            try {
                this.writer.writeEndElement();
                super.endElement(str, str2, str3);
            } catch (XMLStreamException e) {
                throw new SAXException(e);
            }
        } catch (Throwable th) {
            super.endElement(str, str2, str3);
            throw th;
        }
    }

    public XMLStreamWriter getStreamWriter() {
        return this.writer;
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
        super.ignorableWhitespace(cArr, i, i2);
        try {
            this.writer.writeCharacters(cArr, i, i2);
        } catch (XMLStreamException e) {
            x73.a(e);
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        super.processingInstruction(str, str2);
        try {
            this.writer.writeProcessingInstruction(str, str2);
        } catch (XMLStreamException e) {
            x73.a(e);
        }
    }

    public void setStreamWriter(XMLStreamWriter xMLStreamWriter) {
        this.writer = xMLStreamWriter;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXBaseWriter, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        super.startDocument();
        this.needToCallStartDocument = true;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXBaseWriter, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        if (this.needToCallStartDocument) {
            writeStartDocument();
        }
        try {
            try {
                String[] strArr = {null, null};
                SAX2StAXBaseWriter.parseQName(str3, strArr);
                this.writer.writeStartElement(str3);
                int length = attributes.getLength();
                for (int i = 0; i < length; i++) {
                    SAX2StAXBaseWriter.parseQName(attributes.getQName(i), strArr);
                    String str4 = strArr[0];
                    String str5 = strArr[1];
                    String qName = attributes.getQName(i);
                    String value = attributes.getValue(i);
                    String uri = attributes.getURI(i);
                    if ("xmlns".equals(str4) || "xmlns".equals(qName)) {
                        int length2 = str5.length();
                        XMLStreamWriter xMLStreamWriter = this.writer;
                        if (length2 == 0) {
                            xMLStreamWriter.setDefaultNamespace(value);
                        } else {
                            xMLStreamWriter.setPrefix(str5, value);
                        }
                        this.writer.writeNamespace(str5, value);
                    } else {
                        int length3 = str4.length();
                        XMLStreamWriter xMLStreamWriter2 = this.writer;
                        if (length3 > 0) {
                            xMLStreamWriter2.writeAttribute(str4, uri, str5, value);
                        } else {
                            xMLStreamWriter2.writeAttribute(qName, value);
                        }
                    }
                }
                super.startElement(str, str2, str3, attributes);
            } catch (XMLStreamException e) {
                throw new SAXException(e);
            }
        } catch (Throwable th) {
            super.startElement(str, str2, str3, attributes);
            throw th;
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXBaseWriter
    public void writeStartDocument() throws SAXException {
        super.writeStartDocument();
        try {
            this.writer.writeStartDocument(this.xmlVersion);
            this.needToCallStartDocument = false;
        } catch (XMLStreamException e) {
            x73.a(e);
        }
    }

    public SAX2StAXStreamWriter() {
    }
}
