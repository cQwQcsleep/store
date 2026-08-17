package org.xml.sax;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DTDHandler {
    void notationDecl(String str, String str2, String str3) throws SAXException;

    void unparsedEntityDecl(String str, String str2, String str3, String str4) throws SAXException;
}
