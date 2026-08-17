package com.sun.xml.internal.stream;

import defpackage.z0e;
import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.EventReaderDelegate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class EventFilterSupport extends EventReaderDelegate {
    EventFilter fEventFilter;

    public EventFilterSupport(XMLEventReader xMLEventReader, EventFilter eventFilter) {
        setParent(xMLEventReader);
        this.fEventFilter = eventFilter;
    }

    @Override // javax.xml.stream.util.EventReaderDelegate, javax.xml.stream.XMLEventReader, java.util.Iterator
    public boolean hasNext() {
        try {
            return peek() != null;
        } catch (XMLStreamException unused) {
        }
    }

    @Override // javax.xml.stream.util.EventReaderDelegate, java.util.Iterator
    public Object next() {
        try {
            return nextEvent();
        } catch (XMLStreamException unused) {
            z0e.a();
            return null;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.util.EventReaderDelegate, javax.xml.stream.XMLEventReader
    public XMLEvent nextEvent() throws XMLStreamException {
        while (super.hasNext()) {
            XMLEvent xMLEventNextEvent = super.nextEvent();
            if (this.fEventFilter.accept(xMLEventNextEvent)) {
                return xMLEventNextEvent;
            }
        }
        z0e.a();
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.util.EventReaderDelegate, javax.xml.stream.XMLEventReader
    public XMLEvent nextTag() throws XMLStreamException {
        while (super.hasNext()) {
            XMLEvent xMLEventNextTag = super.nextTag();
            if (this.fEventFilter.accept(xMLEventNextTag)) {
                return xMLEventNextTag;
            }
        }
        z0e.a();
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // javax.xml.stream.util.EventReaderDelegate, javax.xml.stream.XMLEventReader
    public XMLEvent peek() throws XMLStreamException {
        while (true) {
            XMLEvent xMLEventPeek = super.peek();
            if (xMLEventPeek == null) {
                return null;
            }
            if (this.fEventFilter.accept(xMLEventPeek)) {
                return xMLEventPeek;
            }
            super.next();
        }
    }
}
