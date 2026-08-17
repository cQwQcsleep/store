package javax.xml.stream;

import java.util.Iterator;
import javax.xml.stream.events.XMLEvent;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface XMLEventReader extends Iterator<Object> {
    void close() throws XMLStreamException;

    String getElementText() throws XMLStreamException;

    Object getProperty(String str) throws IllegalArgumentException;

    @Override // java.util.Iterator
    boolean hasNext();

    XMLEvent nextEvent() throws XMLStreamException;

    XMLEvent nextTag() throws XMLStreamException;

    XMLEvent peek() throws XMLStreamException;
}
