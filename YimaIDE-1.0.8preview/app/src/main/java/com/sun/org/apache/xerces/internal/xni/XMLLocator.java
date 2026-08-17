package com.sun.org.apache.xerces.internal.xni;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XMLLocator {
    String getBaseSystemId();

    int getCharacterOffset();

    int getColumnNumber();

    String getEncoding();

    String getExpandedSystemId();

    int getLineNumber();

    String getLiteralSystemId();

    String getPublicId();

    String getXMLVersion();
}
