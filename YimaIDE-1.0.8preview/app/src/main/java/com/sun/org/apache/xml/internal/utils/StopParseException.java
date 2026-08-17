package com.sun.org.apache.xml.internal.utils;

import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StopParseException extends SAXException {
    static final long serialVersionUID = 210102479218258961L;

    public StopParseException() {
        super("Stylesheet PIs found, stop the parse");
    }
}
