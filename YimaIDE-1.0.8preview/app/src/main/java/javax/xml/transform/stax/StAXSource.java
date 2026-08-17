package javax.xml.transform.stax;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StAXSource implements Source {
    public static final String FEATURE = "http://javax.xml.transform.stax.StAXSource/feature";
    private String systemId;
    private XMLEventReader xmlEventReader;
    private XMLStreamReader xmlStreamReader;

    public StAXSource(XMLEventReader xMLEventReader) throws XMLStreamException {
        this.xmlEventReader = null;
        this.xmlStreamReader = null;
        this.systemId = null;
        if (xMLEventReader == null) {
            w01.a("StAXSource(XMLEventReader) with XMLEventReader == null");
            throw null;
        }
        XMLEvent xMLEventPeek = xMLEventReader.peek();
        int eventType = xMLEventPeek.getEventType();
        if (eventType != 7 && eventType != 1) {
            k2d.a("StAXSource(XMLEventReader) with XMLEventReader not in XMLStreamConstants.START_DOCUMENT or XMLStreamConstants.START_ELEMENT state");
            throw null;
        }
        this.xmlEventReader = xMLEventReader;
        this.systemId = xMLEventPeek.getLocation().getSystemId();
    }

    @Override // javax.xml.transform.Source
    public String getSystemId() {
        return this.systemId;
    }

    public XMLEventReader getXMLEventReader() {
        return this.xmlEventReader;
    }

    public XMLStreamReader getXMLStreamReader() {
        return this.xmlStreamReader;
    }

    @Override // javax.xml.transform.Source
    public boolean isEmpty() {
        return false;
    }

    @Override // javax.xml.transform.Source
    public void setSystemId(String str) {
        throw new UnsupportedOperationException("StAXSource#setSystemId(systemId) cannot set the system identifier for a StAXSource");
    }

    public StAXSource(XMLStreamReader xMLStreamReader) {
        this.xmlEventReader = null;
        this.xmlStreamReader = null;
        this.systemId = null;
        if (xMLStreamReader != null) {
            int eventType = xMLStreamReader.getEventType();
            if (eventType != 7 && eventType != 1) {
                k2d.a("StAXSource(XMLStreamReader) with XMLStreamReadernot in XMLStreamConstants.START_DOCUMENT or XMLStreamConstants.START_ELEMENT state");
                throw null;
            }
            this.xmlStreamReader = xMLStreamReader;
            this.systemId = xMLStreamReader.getLocation().getSystemId();
            return;
        }
        w01.a("StAXSource(XMLStreamReader) with XMLStreamReader == null");
        throw null;
    }
}
