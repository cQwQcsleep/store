package com.sun.org.apache.xalan.internal.xsltc;

import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class TransletException extends SAXException {
    static final long serialVersionUID = -878916829521217293L;

    public TransletException(Exception exc) {
        super(exc.toString());
        initCause(exc);
    }

    public TransletException() {
        super("Translet error");
    }

    public TransletException(String str) {
        super(str);
    }
}
