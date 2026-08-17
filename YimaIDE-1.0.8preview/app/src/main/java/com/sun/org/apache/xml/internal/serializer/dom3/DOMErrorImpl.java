package com.sun.org.apache.xml.internal.serializer.dom3;

import org.w3c.dom.DOMError;
import org.w3c.dom.DOMLocator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class DOMErrorImpl implements DOMError {
    private Exception fException;
    private DOMLocatorImpl fLocation;
    private String fMessage;
    private Object fRelatedData;
    private short fSeverity;
    private String fType;

    public DOMErrorImpl(short s, String str, String str2, Exception exc, Object obj, DOMLocatorImpl dOMLocatorImpl) {
        this.fSeverity = (short) 1;
        this.fMessage = null;
        this.fException = null;
        new DOMLocatorImpl();
        this.fSeverity = s;
        this.fMessage = str;
        this.fType = str2;
        this.fException = exc;
        this.fRelatedData = obj;
        this.fLocation = dOMLocatorImpl;
    }

    @Override // org.w3c.dom.DOMError
    public DOMLocator getLocation() {
        return this.fLocation;
    }

    @Override // org.w3c.dom.DOMError
    public String getMessage() {
        return this.fMessage;
    }

    @Override // org.w3c.dom.DOMError
    public Object getRelatedData() {
        return this.fRelatedData;
    }

    @Override // org.w3c.dom.DOMError
    public Object getRelatedException() {
        return this.fException;
    }

    @Override // org.w3c.dom.DOMError
    public short getSeverity() {
        return this.fSeverity;
    }

    @Override // org.w3c.dom.DOMError
    public String getType() {
        return this.fType;
    }

    public void reset() {
        this.fSeverity = (short) 1;
        this.fException = null;
        this.fMessage = null;
        this.fType = null;
        this.fRelatedData = null;
        this.fLocation = null;
    }

    public DOMErrorImpl(short s, String str, String str2) {
        this.fSeverity = (short) 1;
        this.fMessage = null;
        this.fException = null;
        this.fLocation = new DOMLocatorImpl();
        this.fSeverity = s;
        this.fMessage = str;
        this.fType = str2;
    }

    public DOMErrorImpl(short s, String str, String str2, Exception exc) {
        this.fSeverity = (short) 1;
        this.fMessage = null;
        this.fException = null;
        this.fLocation = new DOMLocatorImpl();
        this.fSeverity = s;
        this.fMessage = str;
        this.fType = str2;
        this.fException = exc;
    }

    public DOMErrorImpl() {
        this.fSeverity = (short) 1;
        this.fMessage = null;
        this.fException = null;
        this.fLocation = new DOMLocatorImpl();
    }
}
