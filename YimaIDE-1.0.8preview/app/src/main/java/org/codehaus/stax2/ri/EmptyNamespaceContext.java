package org.codehaus.stax2.ri;

import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import org.jdom2.JDOMConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class EmptyNamespaceContext implements NamespaceContext {
    static final EmptyNamespaceContext sInstance = new EmptyNamespaceContext();

    private EmptyNamespaceContext() {
    }

    public static EmptyNamespaceContext getInstance() {
        return sInstance;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public final String getNamespaceURI(String str) {
        if (str == null) {
            w01.a("Illegal to pass null/empty prefix as argument.");
            return null;
        }
        if (str.length() <= 0) {
            return null;
        }
        if (str.equals(JDOMConstants.NS_PREFIX_XML)) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        if (str.equals(JDOMConstants.NS_PREFIX_XMLNS)) {
            return JDOMConstants.NS_URI_XMLNS;
        }
        return null;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public String getPrefix(String str) {
        if (str == null || str.length() == 0) {
            w01.a("Illegal to pass null/empty URI as argument.");
            return null;
        }
        if (str.equals("http://www.w3.org/XML/1998/namespace")) {
            return JDOMConstants.NS_PREFIX_XML;
        }
        if (str.equals(JDOMConstants.NS_URI_XMLNS)) {
            return JDOMConstants.NS_PREFIX_XMLNS;
        }
        return null;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public Iterator<String> getPrefixes(String str) {
        if (str == null || str.length() == 0) {
            w01.a("Illegal to pass null/empty prefix as argument.");
            return null;
        }
        if (str.equals("http://www.w3.org/XML/1998/namespace")) {
            return SingletonIterator.create(JDOMConstants.NS_PREFIX_XML);
        }
        return str.equals(JDOMConstants.NS_URI_XMLNS) ? SingletonIterator.create(JDOMConstants.NS_PREFIX_XMLNS) : EmptyIterator.getInstance();
    }
}
