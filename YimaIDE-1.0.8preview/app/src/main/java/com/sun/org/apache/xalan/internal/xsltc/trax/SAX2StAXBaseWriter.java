package com.sun.org.apache.xalan.internal.xsltc.trax;

import defpackage.x73;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.Location;
import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ext.Locator2;
import org.xml.sax.helpers.DefaultHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SAX2StAXBaseWriter extends DefaultHandler implements LexicalHandler {
    protected StringBuffer CDATABuffer;
    protected Locator docLocator;
    protected boolean isCDATA;
    protected List<String> namespaces;
    protected XMLReporter reporter;
    String xmlVersion = null;
    String encoding = null;

    public static final class SAXLocation implements Location {
        private int columnNumber;
        private int lineNumber;
        private String publicId;
        private String systemId;

        private SAXLocation(Locator locator) {
            this.lineNumber = locator.getLineNumber();
            this.columnNumber = locator.getColumnNumber();
            this.publicId = locator.getPublicId();
            this.systemId = locator.getSystemId();
        }

        @Override // javax.xml.stream.Location
        public int getCharacterOffset() {
            return -1;
        }

        @Override // javax.xml.stream.Location
        public int getColumnNumber() {
            return this.columnNumber;
        }

        @Override // javax.xml.stream.Location
        public int getLineNumber() {
            return this.lineNumber;
        }

        @Override // javax.xml.stream.Location
        public String getPublicId() {
            return this.publicId;
        }

        @Override // javax.xml.stream.Location
        public String getSystemId() {
            return this.systemId;
        }
    }

    public SAX2StAXBaseWriter(XMLReporter xMLReporter) {
        this.reporter = xMLReporter;
    }

    public static final void parseQName(String str, String[] strArr) {
        String strSubstring;
        int iIndexOf = str.indexOf(58);
        if (iIndexOf >= 0) {
            strSubstring = str.substring(0, iIndexOf);
            str = str.substring(iIndexOf + 1);
        } else {
            strSubstring = "";
        }
        strArr[0] = strSubstring;
        strArr[1] = str;
    }

    private void updateVersionAndEncoding() {
        Locator locator = this.docLocator;
        if (locator instanceof Locator2) {
            Locator2 locator2 = (Locator2) locator;
            this.xmlVersion = locator2.getXMLVersion();
            this.encoding = locator2.getEncoding();
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        if (this.isCDATA) {
            this.CDATABuffer.append(cArr, i, i2);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
        this.isCDATA = false;
        this.CDATABuffer.setLength(0);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endDTD() throws SAXException {
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        this.namespaces = null;
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        this.namespaces = null;
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endEntity(String str) throws SAXException {
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) throws SAXException {
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ErrorHandler
    public void error(SAXParseException sAXParseException) throws SAXException {
        reportException("ERROR", sAXParseException);
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ErrorHandler
    public void fatalError(SAXParseException sAXParseException) throws SAXException {
        reportException("FATAL", sAXParseException);
    }

    public Location getCurrentLocation() {
        Locator locator = this.docLocator;
        if (locator != null) {
            return new SAXLocation(locator);
        }
        return null;
    }

    public void reportException(String str, SAXException sAXException) throws SAXException {
        XMLReporter xMLReporter = this.reporter;
        if (xMLReporter != null) {
            try {
                xMLReporter.report(sAXException.getMessage(), str, sAXException, getCurrentLocation());
            } catch (XMLStreamException e) {
                x73.a(e);
            }
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        this.docLocator = locator;
    }

    public void setEncoding(String str) {
        this.encoding = str;
    }

    public void setXMLReporter(XMLReporter xMLReporter) {
        this.reporter = xMLReporter;
    }

    public void setXmlVersion(String str) {
        this.xmlVersion = str;
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startCDATA() throws SAXException {
        this.isCDATA = true;
        StringBuffer stringBuffer = this.CDATABuffer;
        if (stringBuffer == null) {
            this.CDATABuffer = new StringBuffer();
        } else {
            stringBuffer.setLength(0);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startDTD(String str, String str2, String str3) throws SAXException {
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        this.namespaces = new ArrayList(2);
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        this.namespaces = null;
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String str) throws SAXException {
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) throws SAXException {
        if (str == null) {
            str = "";
        } else if (str.equals("xml")) {
            return;
        }
        if (this.namespaces == null) {
            this.namespaces = new ArrayList(2);
        }
        this.namespaces.add(str);
        this.namespaces.add(str2);
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ErrorHandler
    public void warning(SAXParseException sAXParseException) throws SAXException {
        reportException("WARNING", sAXParseException);
    }

    public void writeStartDocument() throws SAXException {
        updateVersionAndEncoding();
    }

    public SAX2StAXBaseWriter() {
    }
}
