package com.sun.org.apache.xerces.internal.jaxp.validation;

import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class WrappedSAXException extends RuntimeException {
    private static final long serialVersionUID = -3201986204982729962L;
    public final SAXException exception;

    public WrappedSAXException(SAXException sAXException) {
        this.exception = sAXException;
    }
}
