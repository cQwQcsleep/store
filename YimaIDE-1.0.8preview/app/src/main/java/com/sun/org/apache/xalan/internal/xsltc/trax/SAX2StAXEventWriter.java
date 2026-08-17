package com.sun.org.apache.xalan.internal.xsltc.trax;

import defpackage.x73;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.XMLEvent;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SAX2StAXEventWriter extends SAX2StAXBaseWriter {
    private XMLEventFactory eventFactory;
    private List<Collection<Namespace>> namespaceStack;
    private boolean needToCallStartDocument;
    private XMLEventWriter writer;

    public SAX2StAXEventWriter(XMLEventWriter xMLEventWriter, XMLEventFactory xMLEventFactory) {
        this.namespaceStack = new ArrayList();
        this.needToCallStartDocument = false;
        this.writer = xMLEventWriter;
        if (xMLEventFactory != null) {
            this.eventFactory = xMLEventFactory;
        } else {
            this.eventFactory = XMLEventFactory.newInstance();
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXBaseWriter, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        super.characters(cArr, i, i2);
        try {
            if (this.isCDATA) {
                return;
            }
            this.eventFactory.setLocation(getCurrentLocation());
            this.writer.add((XMLEvent) this.eventFactory.createCharacters(new String(cArr, i, i2)));
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
        this.eventFactory.setLocation(getCurrentLocation());
        try {
            this.writer.add((XMLEvent) this.eventFactory.createComment(new String(cArr, i, i2)));
        } catch (XMLStreamException e) {
            x73.a(e);
        }
    }

    public Namespace createNamespace(String str, String str2) {
        return (str == null || str.length() == 0) ? this.eventFactory.createNamespace(str2) : this.eventFactory.createNamespace(str, str2);
    }

    public void createStartEvents(Attributes attributes, Collection<Attribute>[] collectionArr) {
        HashMap map;
        List<String> list = this.namespaces;
        List arrayList = null;
        if (list != null) {
            int size = list.size();
            map = null;
            for (int i = 0; i < size; i += 2) {
                String str = this.namespaces.get(i);
                Namespace namespaceCreateNamespace = createNamespace(str, this.namespaces.get(i + 1));
                if (map == null) {
                    map = new HashMap();
                }
                map.put(str, namespaceCreateNamespace);
            }
        } else {
            map = null;
        }
        String[] strArr = {null, null};
        int length = attributes.getLength();
        for (int i2 = 0; i2 < length; i2++) {
            SAX2StAXBaseWriter.parseQName(attributes.getQName(i2), strArr);
            String str2 = strArr[0];
            String str3 = strArr[1];
            String qName = attributes.getQName(i2);
            String value = attributes.getValue(i2);
            String uri = attributes.getURI(i2);
            if ("xmlns".equals(qName) || "xmlns".equals(str2)) {
                if (map == null) {
                    map = new HashMap();
                }
                if (!map.containsKey(str3)) {
                    map.put(str3, createNamespace(str3, value));
                }
            } else {
                int length2 = str2.length();
                XMLEventFactory xMLEventFactory = this.eventFactory;
                Attribute attributeCreateAttribute = length2 > 0 ? xMLEventFactory.createAttribute(str2, uri, str3, value) : xMLEventFactory.createAttribute(str3, value);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(attributeCreateAttribute);
            }
        }
        collectionArr[0] = map == null ? Collections.EMPTY_LIST : map.values();
        if (arrayList == null) {
            arrayList = Collections.EMPTY_LIST;
        }
        collectionArr[1] = arrayList;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXBaseWriter, org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
        this.eventFactory.setLocation(getCurrentLocation());
        try {
            this.writer.add((XMLEvent) this.eventFactory.createCData(this.CDATABuffer.toString()));
            super.endCDATA();
        } catch (XMLStreamException e) {
            x73.a(e);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXBaseWriter, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        this.eventFactory.setLocation(getCurrentLocation());
        try {
            this.writer.add(this.eventFactory.createEndDocument());
            super.endDocument();
            this.namespaceStack.clear();
        } catch (XMLStreamException e) {
            x73.a(e);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXBaseWriter, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        super.endElement(str, str2, str3);
        this.eventFactory.setLocation(getCurrentLocation());
        String[] strArr = {null, null};
        SAX2StAXBaseWriter.parseQName(str3, strArr);
        List<Collection<Namespace>> list = this.namespaceStack;
        try {
            this.writer.add(this.eventFactory.createEndElement(strArr[0], str, strArr[1], list.remove(list.size() - 1).iterator()));
        } catch (XMLStreamException e) {
            x73.a(e);
        }
    }

    public XMLEventFactory getEventFactory() {
        return this.eventFactory;
    }

    public XMLEventWriter getEventWriter() {
        return this.writer;
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
        super.ignorableWhitespace(cArr, i, i2);
        characters(cArr, i, i2);
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        if (this.needToCallStartDocument) {
            writeStartDocument();
        }
        super.processingInstruction(str, str2);
        try {
            this.writer.add(this.eventFactory.createProcessingInstruction(str, str2));
        } catch (XMLStreamException e) {
            x73.a(e);
        }
    }

    public void setEventFactory(XMLEventFactory xMLEventFactory) {
        this.eventFactory = xMLEventFactory;
    }

    public void setEventWriter(XMLEventWriter xMLEventWriter) {
        this.writer = xMLEventWriter;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXBaseWriter, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        super.startDocument();
        this.namespaceStack.clear();
        this.eventFactory.setLocation(getCurrentLocation());
        this.needToCallStartDocument = true;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXBaseWriter, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws Throwable {
        String str4;
        if (this.needToCallStartDocument) {
            writeStartDocument();
        }
        this.eventFactory.setLocation(getCurrentLocation());
        Collection<Namespace>[] collectionArr = {null, null};
        createStartEvents(attributes, collectionArr);
        this.namespaceStack.add(collectionArr[0]);
        try {
            String[] strArr = {null, null};
            SAX2StAXBaseWriter.parseQName(str3, strArr);
            str4 = str;
            try {
                try {
                    this.writer.add((XMLEvent) this.eventFactory.createStartElement(strArr[0], str4, strArr[1], collectionArr[1].iterator(), collectionArr[0].iterator()));
                    super.startElement(str4, str2, str3, attributes);
                } catch (XMLStreamException e) {
                    e = e;
                    throw new SAXException(e);
                }
            } catch (Throwable th) {
                th = th;
                Throwable th2 = th;
                super.startElement(str4, str2, str3, attributes);
                throw th2;
            }
        } catch (XMLStreamException e2) {
            e = e2;
            str4 = str;
        } catch (Throwable th3) {
            th = th3;
            str4 = str;
            Throwable th4 = th;
            super.startElement(str4, str2, str3, attributes);
            throw th4;
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXBaseWriter
    public void writeStartDocument() throws SAXException {
        super.writeStartDocument();
        try {
            this.writer.add((XMLEvent) this.eventFactory.createStartDocument(this.encoding, this.xmlVersion));
            this.needToCallStartDocument = false;
        } catch (XMLStreamException e) {
            x73.a(e);
        }
    }

    public SAX2StAXEventWriter(XMLEventWriter xMLEventWriter) {
        this.namespaceStack = new ArrayList();
        this.needToCallStartDocument = false;
        this.writer = xMLEventWriter;
        this.eventFactory = XMLEventFactory.newInstance();
    }

    public SAX2StAXEventWriter() {
        this.namespaceStack = new ArrayList();
        this.needToCallStartDocument = false;
        this.eventFactory = XMLEventFactory.newInstance();
    }
}
