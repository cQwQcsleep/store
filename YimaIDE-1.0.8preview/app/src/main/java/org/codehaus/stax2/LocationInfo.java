package org.codehaus.stax2;

import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface LocationInfo {
    XMLStreamLocation2 getCurrentLocation();

    XMLStreamLocation2 getEndLocation() throws XMLStreamException;

    long getEndingByteOffset() throws XMLStreamException;

    long getEndingCharOffset() throws XMLStreamException;

    Location getLocation();

    XMLStreamLocation2 getStartLocation();

    long getStartingByteOffset();

    long getStartingCharOffset();
}
