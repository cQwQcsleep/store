package com.sun.org.apache.xerces.internal.xni.parser;

import com.sun.org.apache.xerces.internal.xni.XNIException;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XMLDocumentScanner extends XMLDocumentSource {
    int next() throws IOException, XNIException;

    boolean scanDocument(boolean z) throws IOException, XNIException;

    void setInputSource(XMLInputSource xMLInputSource) throws IOException;
}
