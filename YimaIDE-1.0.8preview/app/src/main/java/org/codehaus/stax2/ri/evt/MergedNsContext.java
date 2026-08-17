package org.codehaus.stax2.ri.evt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.events.Namespace;
import org.jdom2.JDOMConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class MergedNsContext implements NamespaceContext {
    final List<Namespace> _namespaces;
    final NamespaceContext _parentCtxt;

    public MergedNsContext(NamespaceContext namespaceContext, List<Namespace> list) {
        this._parentCtxt = namespaceContext;
        this._namespaces = list == null ? Collections.EMPTY_LIST : list;
    }

    public static MergedNsContext construct(NamespaceContext namespaceContext, List<Namespace> list) {
        return new MergedNsContext(namespaceContext, list);
    }

    public <T> ArrayList<T> addToList(ArrayList<T> arrayList, T t) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        arrayList.add(t);
        return arrayList;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public String getNamespaceURI(String str) {
        String namespaceURI;
        if (str == null) {
            w01.a("Illegal to pass null prefix");
            return null;
        }
        int size = this._namespaces.size();
        for (int i = 0; i < size; i++) {
            Namespace namespace = this._namespaces.get(i);
            if (str.equals(namespace.getPrefix())) {
                return namespace.getNamespaceURI();
            }
        }
        NamespaceContext namespaceContext = this._parentCtxt;
        if (namespaceContext != null && (namespaceURI = namespaceContext.getNamespaceURI(str)) != null) {
            return namespaceURI;
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
            w01.a("Illegal to pass null/empty prefix as argument.");
            return null;
        }
        int size = this._namespaces.size();
        for (int i = 0; i < size; i++) {
            Namespace namespace = this._namespaces.get(i);
            if (str.equals(namespace.getNamespaceURI())) {
                return namespace.getPrefix();
            }
        }
        NamespaceContext namespaceContext = this._parentCtxt;
        if (namespaceContext != null) {
            String prefix = namespaceContext.getPrefix(str);
            if (prefix != null && getNamespaceURI(prefix).equals(str)) {
                return prefix;
            }
            Iterator prefixes = this._parentCtxt.getPrefixes(str);
            while (prefixes.hasNext()) {
                String str2 = (String) prefixes.next();
                if (!str2.equals(prefix) && getNamespaceURI(str2).equals(str)) {
                    return str2;
                }
            }
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
        int size = this._namespaces.size();
        ArrayList arrayListAddToList = null;
        for (int i = 0; i < size; i++) {
            Namespace namespace = this._namespaces.get(i);
            if (str.equals(namespace.getNamespaceURI())) {
                arrayListAddToList = addToList(arrayListAddToList, namespace.getPrefix());
            }
        }
        NamespaceContext namespaceContext = this._parentCtxt;
        if (namespaceContext != null) {
            Iterator prefixes = namespaceContext.getPrefixes(str);
            while (prefixes.hasNext()) {
                String str2 = (String) prefixes.next();
                if (getNamespaceURI(str2).equals(str)) {
                    arrayListAddToList = addToList(arrayListAddToList, str2);
                }
            }
        }
        if (str.equals("http://www.w3.org/XML/1998/namespace")) {
            arrayListAddToList = addToList(arrayListAddToList, JDOMConstants.NS_PREFIX_XML);
        }
        if (str.equals(JDOMConstants.NS_URI_XMLNS)) {
            addToList(arrayListAddToList, JDOMConstants.NS_PREFIX_XMLNS);
        }
        return null;
    }
}
