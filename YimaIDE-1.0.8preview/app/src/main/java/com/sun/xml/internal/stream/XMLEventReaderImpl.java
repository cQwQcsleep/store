package com.sun.xml.internal.stream;

import com.sun.xml.internal.stream.events.XMLEventAllocatorImpl;
import java.util.NoSuchElementException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.XMLEventAllocator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLEventReaderImpl implements XMLEventReader {
    private XMLEvent fLastEvent;
    private XMLEvent fPeekedEvent;
    protected XMLEventAllocator fXMLEventAllocator;
    protected XMLStreamReader fXMLReader;

    public XMLEventReaderImpl(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        this.fXMLReader = xMLStreamReader;
        XMLEventAllocator xMLEventAllocator = (XMLEventAllocator) xMLStreamReader.getProperty(XMLInputFactory.ALLOCATOR);
        this.fXMLEventAllocator = xMLEventAllocator;
        if (xMLEventAllocator == null) {
            this.fXMLEventAllocator = new XMLEventAllocatorImpl();
        }
        this.fPeekedEvent = this.fXMLEventAllocator.allocate(this.fXMLReader);
    }

    public void close() throws XMLStreamException {
        this.fXMLReader.close();
    }

    public String getElementText() throws XMLStreamException {
        String data;
        String data2;
        if (this.fLastEvent.getEventType() != 1) {
            throw new XMLStreamException("parser must be on START_ELEMENT to read next text", this.fLastEvent.getLocation());
        }
        EntityReference entityReference = this.fPeekedEvent;
        if (entityReference == null) {
            String elementText = this.fXMLReader.getElementText();
            this.fLastEvent = this.fXMLEventAllocator.allocate(this.fXMLReader);
            return elementText;
        }
        this.fPeekedEvent = null;
        int eventType = entityReference.getEventType();
        if (eventType == 4 || eventType == 6 || eventType == 12) {
            data = entityReference.asCharacters().getData();
        } else if (eventType == 9) {
            data = entityReference.getDeclaration().getReplacementText();
        } else {
            if (eventType != 5 && eventType != 3) {
                if (eventType == 1) {
                    throw new XMLStreamException("elementGetText() function expects text only elment but START_ELEMENT was encountered.", entityReference.getLocation());
                }
                if (eventType == 2) {
                    return "";
                }
            }
            data = null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (data != null && data.length() > 0) {
            stringBuffer.append(data);
        }
        EntityReference entityReferenceNextEvent = nextEvent();
        while (true) {
            int eventType2 = entityReferenceNextEvent.getEventType();
            if (eventType2 == 2) {
                return stringBuffer.toString();
            }
            if (eventType2 == 4 || eventType2 == 6 || eventType2 == 12) {
                data2 = entityReferenceNextEvent.asCharacters().getData();
            } else if (eventType2 == 9) {
                data2 = entityReferenceNextEvent.getDeclaration().getReplacementText();
            } else {
                if (eventType2 != 5 && eventType2 != 3) {
                    if (eventType2 == 8) {
                        jnd.a("unexpected end of document when reading element text content");
                        return null;
                    }
                    if (eventType2 == 1) {
                        throw new XMLStreamException("elementGetText() function expects text only elment but START_ELEMENT was encountered.", entityReferenceNextEvent.getLocation());
                    }
                    throw new XMLStreamException("Unexpected event type " + eventType2, entityReferenceNextEvent.getLocation());
                }
                data2 = null;
            }
            if (data2 != null && data2.length() > 0) {
                stringBuffer.append(data2);
            }
            entityReferenceNextEvent = nextEvent();
        }
    }

    public Object getProperty(String str) throws IllegalArgumentException {
        return this.fXMLReader.getProperty(str);
    }

    public boolean hasNext() {
        if (this.fPeekedEvent != null) {
            return true;
        }
        try {
            return this.fXMLReader.hasNext();
        } catch (XMLStreamException unused) {
            return false;
        }
    }

    public Object next() {
        try {
            return nextEvent();
        } catch (XMLStreamException e) {
            this.fLastEvent = null;
            NoSuchElementException noSuchElementException = new NoSuchElementException(e.getMessage());
            noSuchElementException.initCause(e.getCause());
            throw noSuchElementException;
        }
    }

    public XMLEvent nextEvent() throws XMLStreamException {
        XMLEvent xMLEvent = this.fPeekedEvent;
        if (xMLEvent != null) {
            this.fLastEvent = xMLEvent;
            this.fPeekedEvent = null;
            return xMLEvent;
        }
        if (!this.fXMLReader.hasNext()) {
            this.fLastEvent = null;
            z0e.a();
            return null;
        }
        this.fXMLReader.next();
        XMLEvent xMLEventAllocate = this.fXMLEventAllocator.allocate(this.fXMLReader);
        this.fLastEvent = xMLEventAllocate;
        return xMLEventAllocate;
    }

    public XMLEvent nextTag() throws XMLStreamException {
        XMLEvent xMLEventNextEvent = this.fPeekedEvent;
        if (xMLEventNextEvent == null) {
            this.fXMLReader.nextTag();
            XMLEvent xMLEventAllocate = this.fXMLEventAllocator.allocate(this.fXMLReader);
            this.fLastEvent = xMLEventAllocate;
            return xMLEventAllocate;
        }
        this.fPeekedEvent = null;
        int eventType = xMLEventNextEvent.getEventType();
        if ((xMLEventNextEvent.isCharacters() && xMLEventNextEvent.asCharacters().isWhiteSpace()) || eventType == 3 || eventType == 5 || eventType == 7) {
            xMLEventNextEvent = nextEvent();
            eventType = xMLEventNextEvent.getEventType();
        }
        while (true) {
            if ((!xMLEventNextEvent.isCharacters() || !xMLEventNextEvent.asCharacters().isWhiteSpace()) && eventType != 3 && eventType != 5) {
                break;
            }
            xMLEventNextEvent = nextEvent();
            eventType = xMLEventNextEvent.getEventType();
        }
        if (eventType == 1 || eventType == 2) {
            return xMLEventNextEvent;
        }
        throw new XMLStreamException("expected start or end tag", xMLEventNextEvent.getLocation());
    }

    public XMLEvent peek() throws XMLStreamException {
        XMLEvent xMLEvent = this.fPeekedEvent;
        if (xMLEvent != null) {
            return xMLEvent;
        }
        if (!hasNext()) {
            return null;
        }
        this.fXMLReader.next();
        XMLEvent xMLEventAllocate = this.fXMLEventAllocator.allocate(this.fXMLReader);
        this.fPeekedEvent = xMLEventAllocate;
        return xMLEventAllocate;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
