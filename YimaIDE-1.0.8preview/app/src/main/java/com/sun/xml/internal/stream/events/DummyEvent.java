package com.sun.xml.internal.stream.events;

import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import java.io.IOException;
import java.io.Writer;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DummyEvent implements XMLEvent {
    private static DummyLocation nowhere = new DummyLocation();
    private int fEventType;
    protected Location fLocation = nowhere;

    public static class DummyLocation implements Location {
        @Override // javax.xml.stream.Location
        public int getCharacterOffset() {
            return -1;
        }

        @Override // javax.xml.stream.Location
        public int getColumnNumber() {
            return -1;
        }

        @Override // javax.xml.stream.Location
        public int getLineNumber() {
            return -1;
        }

        @Override // javax.xml.stream.Location
        public String getPublicId() {
            return null;
        }

        @Override // javax.xml.stream.Location
        public String getSystemId() {
            return null;
        }
    }

    public DummyEvent(int i) {
        this.fEventType = i;
    }

    public Characters asCharacters() {
        return (Characters) this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public EndElement asEndElement() {
        return (EndElement) this;
    }

    public StartElement asStartElement() {
        return (StartElement) this;
    }

    public void charEncode(Writer writer, String str) throws IOException {
        if (str == null || str == "") {
            return;
        }
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == '\"') {
                writer.write(str, i, i2 - i);
                writer.write(SerializerConstants.ENTITY_QUOT);
            } else if (cCharAt == '&') {
                writer.write(str, i, i2 - i);
                writer.write(SerializerConstants.ENTITY_AMP);
            } else if (cCharAt != '<') {
                if (cCharAt == '>') {
                    writer.write(str, i, i2 - i);
                    writer.write(SerializerConstants.ENTITY_GT);
                }
            } else {
                writer.write(str, i, i2 - i);
                writer.write(SerializerConstants.ENTITY_LT);
            }
            i = i2 + 1;
        }
        writer.write(str, i, length - i);
    }

    public int getEventType() {
        return this.fEventType;
    }

    public Location getLocation() {
        return this.fLocation;
    }

    public QName getSchemaType() {
        return null;
    }

    public boolean isAttribute() {
        return this.fEventType == 10;
    }

    public boolean isCharacterData() {
        return this.fEventType == 4;
    }

    public boolean isCharacters() {
        return this.fEventType == 4;
    }

    public boolean isEndDocument() {
        return this.fEventType == 8;
    }

    public boolean isEndElement() {
        return this.fEventType == 2;
    }

    public boolean isEntityReference() {
        return this.fEventType == 9;
    }

    public boolean isNamespace() {
        return this.fEventType == 13;
    }

    public boolean isProcessingInstruction() {
        return this.fEventType == 3;
    }

    public boolean isStartDocument() {
        return this.fEventType == 7;
    }

    public boolean isStartElement() {
        return this.fEventType == 1;
    }

    public void setEventType(int i) {
        this.fEventType = i;
    }

    public void setLocation(Location location) {
        if (location == null) {
            this.fLocation = nowhere;
        } else {
            this.fLocation = location;
        }
    }

    public void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {
        try {
            writeAsEncodedUnicodeEx(writer);
        } catch (IOException e) {
            az3.a(e);
        }
    }

    public abstract void writeAsEncodedUnicodeEx(Writer writer) throws XMLStreamException, IOException;

    public DummyEvent() {
    }
}
