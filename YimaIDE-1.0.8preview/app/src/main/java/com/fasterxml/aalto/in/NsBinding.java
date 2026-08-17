package com.fasterxml.aalto.in;

import defpackage.obi;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
final class NsBinding {
    public final String mPrefix;
    public String mURI;
    public static final NsBinding XML_BINDING = new NsBinding("xml", "http://www.w3.org/XML/1998/namespace", null);
    public static final NsBinding XMLNS_BINDING = new NsBinding("xmlns", "http://www.w3.org/2000/xmlns/", null);

    public NsBinding(String str) {
        if (str == "xml" || str == "xmlns") {
            obi.a("Trying to create non-singleton binding for ns prefix '", str, "'");
            throw null;
        }
        this.mPrefix = str;
        this.mURI = null;
    }

    public static final NsBinding createDefaultNs() {
        return new NsBinding(null);
    }

    public boolean isImmutable() {
        return this == XML_BINDING || this == XMLNS_BINDING;
    }

    private NsBinding(String str, String str2, Object obj) {
        this.mPrefix = str;
        this.mURI = str2;
    }
}
