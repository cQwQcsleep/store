package javax.xml.stream.util;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XMLEventConsumer {
    void add(XMLEvent xMLEvent) throws XMLStreamException;
}
