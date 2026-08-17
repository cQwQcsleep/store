package com.sun.xml.internal.stream.events;

import javax.xml.stream.Location;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LocationImpl implements Location {
    int charOffset;
    int colNo;
    int lineNo;
    String publicId;
    String systemId;

    public LocationImpl(Location location) {
        this.systemId = location.getSystemId();
        this.publicId = location.getPublicId();
        this.lineNo = location.getLineNumber();
        this.colNo = location.getColumnNumber();
        this.charOffset = location.getCharacterOffset();
    }

    @Override // javax.xml.stream.Location
    public int getCharacterOffset() {
        return this.charOffset;
    }

    @Override // javax.xml.stream.Location
    public int getColumnNumber() {
        return this.colNo;
    }

    @Override // javax.xml.stream.Location
    public int getLineNumber() {
        return this.lineNo;
    }

    @Override // javax.xml.stream.Location
    public String getPublicId() {
        return this.publicId;
    }

    @Override // javax.xml.stream.Location
    public String getSystemId() {
        return this.systemId;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Line number = " + getLineNumber());
        stringBuffer.append("\n");
        stringBuffer.append("Column number = " + getColumnNumber());
        stringBuffer.append("\n");
        stringBuffer.append("System Id = " + getSystemId());
        stringBuffer.append("\n");
        stringBuffer.append("Public Id = " + getPublicId());
        stringBuffer.append("\n");
        stringBuffer.append("CharacterOffset = " + getCharacterOffset());
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }
}
