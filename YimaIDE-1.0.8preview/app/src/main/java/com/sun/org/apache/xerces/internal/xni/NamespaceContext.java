package com.sun.org.apache.xerces.internal.xni;

import java.util.Enumeration;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface NamespaceContext {
    public static final String XML_URI = "http://www.w3.org/XML/1998/namespace".intern();
    public static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/".intern();

    boolean declarePrefix(String str, String str2);

    Enumeration<String> getAllPrefixes();

    String getDeclaredPrefixAt(int i);

    int getDeclaredPrefixCount();

    String getPrefix(String str);

    String getURI(String str);

    void popContext();

    void pushContext();

    void reset();
}
