package com.sun.org.apache.xerces.internal.impl;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class XMLStreamFilterImpl implements XMLStreamReader {
    private int fCurrentEvent;
    private boolean fEventAccepted;
    private boolean fStreamAdvancedByHasNext = false;
    private StreamFilter fStreamFilter;
    private XMLStreamReader fStreamReader;

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public XMLStreamFilterImpl(XMLStreamReader xMLStreamReader, StreamFilter streamFilter) throws XMLStreamException {
        this.fEventAccepted = false;
        this.fStreamReader = xMLStreamReader;
        this.fStreamFilter = streamFilter;
        if (streamFilter.accept(xMLStreamReader)) {
            this.fEventAccepted = true;
        } else {
            findNextEvent();
        }
    }

    private int findNextEvent() throws XMLStreamException {
        this.fStreamAdvancedByHasNext = false;
        while (this.fStreamReader.hasNext()) {
            this.fCurrentEvent = this.fStreamReader.next();
            if (this.fStreamFilter.accept(this.fStreamReader)) {
                this.fEventAccepted = true;
                return this.fCurrentEvent;
            }
        }
        int i = this.fCurrentEvent;
        if (i == 8) {
            return i;
        }
        return -1;
    }

    private int findNextTag() throws XMLStreamException {
        this.fStreamAdvancedByHasNext = false;
        while (this.fStreamReader.hasNext()) {
            this.fCurrentEvent = this.fStreamReader.nextTag();
            if (this.fStreamFilter.accept(this.fStreamReader)) {
                this.fEventAccepted = true;
                return this.fCurrentEvent;
            }
        }
        int i = this.fCurrentEvent;
        if (i == 8) {
            return i;
        }
        return -1;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamReader
    public void close() throws XMLStreamException {
        this.fStreamReader.close();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public int getAttributeCount() {
        return this.fStreamReader.getAttributeCount();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getAttributeLocalName(int i) {
        return this.fStreamReader.getAttributeLocalName(i);
    }

    @Override // javax.xml.stream.XMLStreamReader
    public QName getAttributeName(int i) {
        return this.fStreamReader.getAttributeName(i);
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getAttributeNamespace(int i) {
        return this.fStreamReader.getAttributeNamespace(i);
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getAttributePrefix(int i) {
        return this.fStreamReader.getAttributePrefix(i);
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getAttributeType(int i) {
        return this.fStreamReader.getAttributeType(i);
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getAttributeValue(int i) {
        return this.fStreamReader.getAttributeValue(i);
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getCharacterEncodingScheme() {
        return this.fStreamReader.getCharacterEncodingScheme();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getElementText() throws XMLStreamException {
        return this.fStreamReader.getElementText();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getEncoding() {
        return this.fStreamReader.getEncoding();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public int getEventType() {
        return this.fStreamReader.getEventType();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getLocalName() {
        return this.fStreamReader.getLocalName();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public Location getLocation() {
        return this.fStreamReader.getLocation();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public QName getName() {
        return this.fStreamReader.getName();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public NamespaceContext getNamespaceContext() {
        return this.fStreamReader.getNamespaceContext();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public int getNamespaceCount() {
        return this.fStreamReader.getNamespaceCount();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getNamespacePrefix(int i) {
        return this.fStreamReader.getNamespacePrefix(i);
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getNamespaceURI() {
        return this.fStreamReader.getNamespaceURI();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getPIData() {
        return this.fStreamReader.getPIData();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getPITarget() {
        return this.fStreamReader.getPITarget();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getPrefix() {
        return this.fStreamReader.getPrefix();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public Object getProperty(String str) throws IllegalArgumentException {
        return this.fStreamReader.getProperty(str);
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getText() {
        return this.fStreamReader.getText();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public char[] getTextCharacters() {
        return this.fStreamReader.getTextCharacters();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public int getTextLength() {
        return this.fStreamReader.getTextLength();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public int getTextStart() {
        return this.fStreamReader.getTextStart();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getVersion() {
        return this.fStreamReader.getVersion();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean hasName() {
        return this.fStreamReader.hasName();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamReader
    public boolean hasNext() throws XMLStreamException {
        if (!this.fStreamReader.hasNext()) {
            return false;
        }
        if (!this.fEventAccepted) {
            int iFindNextEvent = findNextEvent();
            this.fCurrentEvent = iFindNextEvent;
            if (iFindNextEvent == -1) {
                return false;
            }
            this.fStreamAdvancedByHasNext = true;
        }
        return true;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean hasText() {
        return this.fStreamReader.hasText();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean isAttributeSpecified(int i) {
        return this.fStreamReader.isAttributeSpecified(i);
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean isCharacters() {
        return this.fStreamReader.isCharacters();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean isEndElement() {
        return this.fStreamReader.isEndElement();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean isStandalone() {
        return this.fStreamReader.isStandalone();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean isStartElement() {
        return this.fStreamReader.isStartElement();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean isWhiteSpace() {
        return this.fStreamReader.isWhiteSpace();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamReader
    public int next() throws XMLStreamException {
        if (this.fStreamAdvancedByHasNext && this.fEventAccepted) {
            this.fStreamAdvancedByHasNext = false;
            return this.fCurrentEvent;
        }
        int iFindNextEvent = findNextEvent();
        if (iFindNextEvent != -1) {
            return iFindNextEvent;
        }
        k2d.a("The stream reader has reached the end of the document, or there are no more  items to return");
        return 0;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamReader
    public int nextTag() throws XMLStreamException {
        int i;
        if (this.fStreamAdvancedByHasNext && this.fEventAccepted && ((i = this.fCurrentEvent) == 1 || i == 1)) {
            this.fStreamAdvancedByHasNext = false;
            return i;
        }
        int iFindNextTag = findNextTag();
        if (iFindNextTag != -1) {
            return iFindNextTag;
        }
        k2d.a("The stream reader has reached the end of the document, or there are no more  items to return");
        return 0;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamReader
    public void require(int i, String str, String str2) throws XMLStreamException {
        this.fStreamReader.require(i, str, str2);
    }

    public void setStreamFilter(StreamFilter streamFilter) {
        this.fStreamFilter = streamFilter;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean standaloneSet() {
        return this.fStreamReader.standaloneSet();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getAttributeValue(String str, String str2) {
        return this.fStreamReader.getAttributeValue(str, str2);
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getNamespaceURI(int i) {
        return this.fStreamReader.getNamespaceURI(i);
    }

    @Override // javax.xml.stream.XMLStreamReader
    public int getTextCharacters(int i, char[] cArr, int i2, int i3) throws XMLStreamException {
        return this.fStreamReader.getTextCharacters(i, cArr, i2, i3);
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getNamespaceURI(String str) {
        return this.fStreamReader.getNamespaceURI(str);
    }
}
