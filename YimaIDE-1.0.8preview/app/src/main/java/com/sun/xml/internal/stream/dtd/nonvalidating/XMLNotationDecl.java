package com.sun.xml.internal.stream.dtd.nonvalidating;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLNotationDecl {
    public String baseSystemId;
    public String name;
    public String publicId;
    public String systemId;

    public void clear() {
        this.name = null;
        this.publicId = null;
        this.systemId = null;
        this.baseSystemId = null;
    }

    public void setValues(String str, String str2, String str3, String str4) {
        this.name = str;
        this.publicId = str2;
        this.systemId = str3;
        this.baseSystemId = str4;
    }
}
