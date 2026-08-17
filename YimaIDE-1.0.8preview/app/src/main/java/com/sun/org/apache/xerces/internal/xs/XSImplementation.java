package com.sun.org.apache.xerces.internal.xs;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSImplementation {
    XSLoader createXSLoader(StringList stringList) throws XSException;

    StringList getRecognizedVersions();
}
