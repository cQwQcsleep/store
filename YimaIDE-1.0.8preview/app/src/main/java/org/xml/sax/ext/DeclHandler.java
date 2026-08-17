package org.xml.sax.ext;

import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DeclHandler {
    void attributeDecl(String str, String str2, String str3, String str4, String str5) throws SAXException;

    void elementDecl(String str, String str2) throws SAXException;

    void externalEntityDecl(String str, String str2, String str3) throws SAXException;

    void internalEntityDecl(String str, String str2) throws SAXException;
}
