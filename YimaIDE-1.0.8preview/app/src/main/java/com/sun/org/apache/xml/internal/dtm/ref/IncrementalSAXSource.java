package com.sun.org.apache.xml.internal.dtm.ref;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface IncrementalSAXSource {
    Object deliverMoreNodes(boolean z);

    void setContentHandler(ContentHandler contentHandler);

    void setDTDHandler(DTDHandler dTDHandler);

    void setLexicalHandler(LexicalHandler lexicalHandler);

    void startParse(InputSource inputSource) throws SAXException;
}
