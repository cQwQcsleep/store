package com.sun.org.apache.xml.internal.serializer;

import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
interface ExtendedLexicalHandler extends LexicalHandler {
    void comment(String str) throws SAXException;
}
