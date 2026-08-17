package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class StAXInputSource extends XMLInputSource {
    private final boolean fConsumeRemainingContent;
    private final XMLEventReader fEventReader;
    private final XMLStreamReader fStreamReader;

    public StAXInputSource(XMLEventReader xMLEventReader, boolean z) {
        super(null, getEventReaderSystemId(xMLEventReader), null, false);
        if (xMLEventReader == null) {
            w01.a("XMLEventReader parameter cannot be null.");
            throw null;
        }
        this.fStreamReader = null;
        this.fEventReader = xMLEventReader;
        this.fConsumeRemainingContent = z;
    }

    private static String getEventReaderSystemId(XMLEventReader xMLEventReader) {
        if (xMLEventReader == null) {
            return null;
        }
        try {
            return xMLEventReader.peek().getLocation().getSystemId();
        } catch (XMLStreamException unused) {
            return null;
        }
    }

    public XMLEventReader getXMLEventReader() {
        return this.fEventReader;
    }

    public XMLStreamReader getXMLStreamReader() {
        return this.fStreamReader;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource
    public void setSystemId(String str) {
        throw new UnsupportedOperationException("Cannot set the system ID on a StAXInputSource");
    }

    public boolean shouldConsumeRemainingContent() {
        return this.fConsumeRemainingContent;
    }

    public StAXInputSource(XMLStreamReader xMLStreamReader, boolean z) {
        super(null, xMLStreamReader.getLocation().getSystemId(), null, false);
        this.fStreamReader = xMLStreamReader;
        this.fEventReader = null;
        this.fConsumeRemainingContent = z;
    }

    public StAXInputSource(XMLEventReader xMLEventReader) {
        this(xMLEventReader, false);
    }

    public StAXInputSource(XMLStreamReader xMLStreamReader) {
        this(xMLStreamReader, false);
    }
}
