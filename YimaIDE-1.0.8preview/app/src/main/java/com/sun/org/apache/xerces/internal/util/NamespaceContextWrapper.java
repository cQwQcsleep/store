package com.sun.org.apache.xerces.internal.util;

import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NamespaceContextWrapper implements NamespaceContext {
    private com.sun.org.apache.xerces.internal.xni.NamespaceContext fNamespaceContext;

    public NamespaceContextWrapper(NamespaceSupport namespaceSupport) {
        this.fNamespaceContext = namespaceSupport;
    }

    public com.sun.org.apache.xerces.internal.xni.NamespaceContext getNamespaceContext() {
        return this.fNamespaceContext;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public String getNamespaceURI(String str) {
        if (str != null) {
            return this.fNamespaceContext.getURI(str.intern());
        }
        w01.a("Prefix can't be null");
        return null;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public String getPrefix(String str) {
        if (str != null) {
            return this.fNamespaceContext.getPrefix(str.intern());
        }
        w01.a("URI can't be null.");
        return null;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public Iterator<String> getPrefixes(String str) {
        if (str != null) {
            return ((NamespaceSupport) this.fNamespaceContext).getPrefixes(str.intern()).iterator();
        }
        w01.a("URI can't be null.");
        return null;
    }
}
