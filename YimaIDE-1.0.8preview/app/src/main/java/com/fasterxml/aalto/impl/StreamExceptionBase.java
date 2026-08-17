package com.fasterxml.aalto.impl;

import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class StreamExceptionBase extends XMLStreamException {
    final String mMsg;

    /* JADX WARN: Multi-variable type inference failed */
    public StreamExceptionBase(Throwable th) {
        super(th.getMessage(), th);
        this.mMsg = th.getMessage();
        if (getCause() == null) {
            initCause(th);
        }
    }

    public String getLocationDesc() {
        Location location = getLocation();
        if (location == null) {
            return null;
        }
        return location.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String getMessage() {
        String locationDesc = getLocationDesc();
        if (locationDesc == null) {
            return super/*java.lang.Throwable*/.getMessage();
        }
        StringBuilder sb = new StringBuilder(this.mMsg.length() + locationDesc.length() + 20);
        sb.append(this.mMsg);
        sb.append("\n at ");
        sb.append(locationDesc);
        return sb.toString();
    }

    public String toString() {
        return getClass().getName() + ": " + getMessage();
    }

    public StreamExceptionBase(String str) {
        super(str);
        this.mMsg = str;
    }

    public StreamExceptionBase(String str, Location location) {
        super(str, location);
        this.mMsg = str;
    }
}
