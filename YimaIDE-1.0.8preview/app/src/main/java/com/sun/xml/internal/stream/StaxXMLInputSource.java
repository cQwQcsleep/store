package com.sun.xml.internal.stream;

import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StaxXMLInputSource {
    XMLEventReader fEventReader;
    XMLInputSource fInputSource;
    boolean fIsCreatedByResolver;
    XMLStreamReader fStreamReader;

    public StaxXMLInputSource(XMLStreamReader xMLStreamReader, boolean z) {
        this.fIsCreatedByResolver = false;
        this.fStreamReader = xMLStreamReader;
    }

    public XMLEventReader getXMLEventReader() {
        return this.fEventReader;
    }

    public XMLInputSource getXMLInputSource() {
        return this.fInputSource;
    }

    public XMLStreamReader getXMLStreamReader() {
        return this.fStreamReader;
    }

    public boolean hasXMLStreamOrXMLEventReader() {
        return (this.fStreamReader == null && this.fEventReader == null) ? false : true;
    }

    public boolean isCreatedByResolver() {
        return this.fIsCreatedByResolver;
    }

    public StaxXMLInputSource(XMLEventReader xMLEventReader, boolean z) {
        this.fIsCreatedByResolver = false;
        this.fEventReader = xMLEventReader;
    }

    public StaxXMLInputSource(XMLInputSource xMLInputSource, boolean z) {
        this.fInputSource = xMLInputSource;
        this.fIsCreatedByResolver = z;
    }
}
