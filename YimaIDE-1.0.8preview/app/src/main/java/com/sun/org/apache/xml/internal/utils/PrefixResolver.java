package com.sun.org.apache.xml.internal.utils;

import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface PrefixResolver {
    String getBaseIdentifier();

    String getNamespaceForPrefix(String str);

    String getNamespaceForPrefix(String str, Node node);

    boolean handlesNullPrefixes();
}
