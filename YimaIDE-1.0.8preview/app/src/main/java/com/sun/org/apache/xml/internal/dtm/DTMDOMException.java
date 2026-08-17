package com.sun.org.apache.xml.internal.dtm;

import org.w3c.dom.DOMException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTMDOMException extends DOMException {
    static final long serialVersionUID = 1895654266613192414L;

    public DTMDOMException(short s) {
        super(s, "");
    }

    public DTMDOMException(short s, String str) {
        super(s, str);
    }
}
