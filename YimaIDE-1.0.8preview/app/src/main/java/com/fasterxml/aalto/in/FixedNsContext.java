package com.fasterxml.aalto.in;

import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import org.codehaus.stax2.ri.EmptyIterator;
import org.codehaus.stax2.ri.SingletonIterator;
import org.jdom2.JDOMConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class FixedNsContext implements NamespaceContext {
    public static final FixedNsContext EMPTY_CONTEXT = new FixedNsContext(null, new String[0]);
    protected final String[] _declarationData;
    protected final NsDeclaration _lastDeclaration;
    protected ArrayList<String> _tmpDecl = null;

    private FixedNsContext(NsDeclaration nsDeclaration, String[] strArr) {
        this._lastDeclaration = nsDeclaration;
        this._declarationData = strArr;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public final String getNamespaceURI(String str) {
        if (str == null) {
            w01.a("Null prefix not allowed");
            return null;
        }
        if (str.length() > 0) {
            if (str.equals(JDOMConstants.NS_PREFIX_XML)) {
                return "http://www.w3.org/XML/1998/namespace";
            }
            if (str.equals(JDOMConstants.NS_PREFIX_XMLNS)) {
                return JDOMConstants.NS_URI_XMLNS;
            }
        }
        String[] strArr = this._declarationData;
        int length = strArr.length;
        for (int i = 0; i < length; i += 2) {
            if (str.equals(strArr[i])) {
                return strArr[i + 1];
            }
        }
        return null;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public final String getPrefix(String str) {
        if (str == null || str.length() == 0) {
            w01.a("Illegal to pass null/empty prefix as argument.");
            return null;
        }
        if (str.equals("http://www.w3.org/XML/1998/namespace")) {
            return JDOMConstants.NS_PREFIX_XML;
        }
        if (str.equals(JDOMConstants.NS_URI_XMLNS)) {
            return JDOMConstants.NS_PREFIX_XMLNS;
        }
        String[] strArr = this._declarationData;
        int length = strArr.length;
        for (int i = 1; i < length; i += 2) {
            if (str.equals(strArr[i])) {
                int i2 = i - 1;
                String str2 = strArr[i2];
                for (int i3 = i + 1; i3 < length; i3 += 2) {
                    if (strArr[i3] != str2) {
                    }
                }
                return strArr[i2];
            }
        }
        return null;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public final Iterator<String> getPrefixes(String str) {
        ArrayList arrayList = null;
        if (str == null || str.length() == 0) {
            w01.a("Illegal to pass null/empty prefix as argument.");
            return null;
        }
        if (str.equals("http://www.w3.org/XML/1998/namespace")) {
            return SingletonIterator.create(JDOMConstants.NS_PREFIX_XML);
        }
        if (str.equals(JDOMConstants.NS_URI_XMLNS)) {
            return SingletonIterator.create(JDOMConstants.NS_PREFIX_XMLNS);
        }
        String[] strArr = this._declarationData;
        int length = strArr.length;
        String str2 = null;
        for (int i = 1; i < length; i += 2) {
            String str3 = strArr[i];
            if (str3 == str || str3.equals(str)) {
                String str4 = strArr[i - 1];
                int i2 = i + 1;
                while (true) {
                    if (i2 >= length) {
                        if (str2 != null) {
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                                arrayList.add(str2);
                            }
                            arrayList.add(str4);
                            break;
                        }
                        str2 = str4;
                        break;
                    }
                    if (strArr[i2] == str4) {
                        break;
                    }
                    i2 += 2;
                }
            }
        }
        if (arrayList != null) {
            return arrayList.iterator();
        }
        return str2 != null ? SingletonIterator.create(str2) : EmptyIterator.getInstance();
    }

    public String toString() {
        if (this == EMPTY_CONTEXT) {
            return "[EMPTY non-transient NsContext]";
        }
        StringBuilder sb = new StringBuilder("[");
        int length = this._declarationData.length;
        for (int i = 0; i < length; i += 2) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append('\"');
            sb.append(this._declarationData[i]);
            sb.append("\"->\"");
            sb.append(this._declarationData[i + 1]);
            sb.append('\"');
        }
        sb.append(']');
        return sb.toString();
    }
}
