package com.sun.org.apache.xerces.internal.xni;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XMLResourceIdentifier {
    String getBaseSystemId();

    String getExpandedSystemId();

    String getLiteralSystemId();

    String getNamespace();

    String getPublicId();

    void setBaseSystemId(String str);

    void setExpandedSystemId(String str);

    void setLiteralSystemId(String str);

    void setNamespace(String str);

    void setPublicId(String str);
}
