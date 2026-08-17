package com.sun.org.apache.xerces.internal.impl.xpath;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XPathException extends Exception {
    static final long serialVersionUID = -948482312169512085L;
    private final String fKey;

    public XPathException() {
        this.fKey = "c-general-xpath";
    }

    public String getKey() {
        return this.fKey;
    }

    public XPathException(String str) {
        this.fKey = str;
    }
}
