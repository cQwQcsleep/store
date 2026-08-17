package org.codehaus.stax2.util;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class StreamWriterDelegate implements XMLStreamWriter {
    protected XMLStreamWriter mDelegate;

    public StreamWriterDelegate(XMLStreamWriter xMLStreamWriter) {
        this.mDelegate = xMLStreamWriter;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void close() throws XMLStreamException {
        this.mDelegate.close();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void flush() throws XMLStreamException {
        this.mDelegate.flush();
    }

    @Override // javax.xml.stream.XMLStreamWriter
    public NamespaceContext getNamespaceContext() {
        return this.mDelegate.getNamespaceContext();
    }

    public XMLStreamWriter getParent() {
        return this.mDelegate;
    }

    @Override // javax.xml.stream.XMLStreamWriter
    public String getPrefix(String str) throws XMLStreamException {
        return this.mDelegate.getPrefix(str);
    }

    @Override // javax.xml.stream.XMLStreamWriter
    public Object getProperty(String str) throws IllegalArgumentException {
        return this.mDelegate.getProperty(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void setDefaultNamespace(String str) throws XMLStreamException {
        this.mDelegate.setDefaultNamespace(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void setNamespaceContext(NamespaceContext namespaceContext) throws XMLStreamException {
        this.mDelegate.setNamespaceContext(namespaceContext);
    }

    public void setParent(XMLStreamWriter xMLStreamWriter) {
        this.mDelegate = xMLStreamWriter;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void setPrefix(String str, String str2) throws XMLStreamException {
        this.mDelegate.setPrefix(str, str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeAttribute(String str, String str2) throws XMLStreamException {
        this.mDelegate.writeAttribute(str, str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeCData(String str) throws XMLStreamException {
        this.mDelegate.writeCData(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeCharacters(String str) throws XMLStreamException {
        this.mDelegate.writeCharacters(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeComment(String str) throws XMLStreamException {
        this.mDelegate.writeComment(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeDTD(String str) throws XMLStreamException {
        this.mDelegate.writeDTD(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeDefaultNamespace(String str) throws XMLStreamException {
        this.mDelegate.writeDefaultNamespace(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeEmptyElement(String str) throws XMLStreamException {
        this.mDelegate.writeEmptyElement(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeEndDocument() throws XMLStreamException {
        this.mDelegate.writeEndDocument();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeEndElement() throws XMLStreamException {
        this.mDelegate.writeEndElement();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeEntityRef(String str) throws XMLStreamException {
        this.mDelegate.writeEntityRef(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeNamespace(String str, String str2) throws XMLStreamException {
        this.mDelegate.writeNamespace(str, str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeProcessingInstruction(String str) throws XMLStreamException {
        this.mDelegate.writeProcessingInstruction(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeStartDocument() throws XMLStreamException {
        this.mDelegate.writeStartDocument();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeStartElement(String str) throws XMLStreamException {
        this.mDelegate.writeStartElement(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeAttribute(String str, String str2, String str3) throws XMLStreamException {
        this.mDelegate.writeAttribute(str, str2, str3);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeCharacters(char[] cArr, int i, int i2) throws XMLStreamException {
        this.mDelegate.writeCharacters(cArr, i, i2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeEmptyElement(String str, String str2) throws XMLStreamException {
        this.mDelegate.writeEmptyElement(str, str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeProcessingInstruction(String str, String str2) throws XMLStreamException {
        this.mDelegate.writeProcessingInstruction(str, str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeStartDocument(String str) throws XMLStreamException {
        this.mDelegate.writeStartDocument(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeStartElement(String str, String str2) throws XMLStreamException {
        this.mDelegate.writeStartElement(str, str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeAttribute(String str, String str2, String str3, String str4) throws XMLStreamException {
        this.mDelegate.writeAttribute(str, str2, str3, str4);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeEmptyElement(String str, String str2, String str3) throws XMLStreamException {
        this.mDelegate.writeEmptyElement(str, str2, str3);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeStartDocument(String str, String str2) throws XMLStreamException {
        this.mDelegate.writeStartDocument(str, str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.XMLStreamWriter
    public void writeStartElement(String str, String str2, String str3) throws XMLStreamException {
        this.mDelegate.writeStartElement(str, str2, str3);
    }
}
