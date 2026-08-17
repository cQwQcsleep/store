package com.sun.org.apache.xml.internal.utils;

import com.sun.org.apache.xml.internal.res.XMLMessages;
import java.io.Serializable;
import java.util.Stack;
import java.util.StringTokenizer;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class QName implements Serializable {
    public static final String S_XMLNAMESPACEURI = "http://www.w3.org/XML/1998/namespace";
    static final long serialVersionUID = 467434581652829920L;
    protected String _localName;
    protected String _namespaceURI;
    protected String _prefix;
    private int m_hashCode;

    public QName(String str, Stack<NameSpace> stack, boolean z) {
        String str2;
        String strSubstring;
        int iIndexOf = str.indexOf(58);
        if (iIndexOf > 0) {
            strSubstring = str.substring(0, iIndexOf);
            if (strSubstring.equals("xml")) {
                str2 = "http://www.w3.org/XML/1998/namespace";
            } else {
                if (strSubstring.equals("xmlns")) {
                    return;
                }
                int size = stack.size() - 1;
                String str3 = null;
                while (size >= 0) {
                    for (NameSpace nameSpace = stack.get(size); nameSpace != null; nameSpace = nameSpace.m_next) {
                        String str4 = nameSpace.m_prefix;
                        if (str4 != null && strSubstring.equals(str4)) {
                            str3 = nameSpace.m_uri;
                            size = -1;
                            break;
                        }
                    }
                    size--;
                }
                str2 = str3;
            }
            if (str2 == null) {
                f63.a(XMLMessages.createXMLMessage("ER_PREFIX_MUST_RESOLVE", new Object[]{strSubstring}));
                throw null;
            }
        } else {
            str2 = null;
            strSubstring = null;
        }
        str = iIndexOf >= 0 ? str.substring(iIndexOf + 1) : str;
        this._localName = str;
        if (z && !XML11Char.isXML11ValidNCName(str)) {
            w01.a(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_INVALID", null));
            throw null;
        }
        this._namespaceURI = str2;
        this._prefix = strSubstring;
        this.m_hashCode = toString().hashCode();
    }

    public static String getLocalPart(String str) {
        int iIndexOf = str.indexOf(58);
        return iIndexOf < 0 ? str : str.substring(iIndexOf + 1);
    }

    public static String getPrefixFromXMLNSDecl(String str) {
        int iIndexOf = str.indexOf(58);
        return iIndexOf >= 0 ? str.substring(iIndexOf + 1) : "";
    }

    public static String getPrefixPart(String str) {
        int iIndexOf = str.indexOf(58);
        return iIndexOf >= 0 ? str.substring(0, iIndexOf) : "";
    }

    public static QName getQNameFromString(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "{}", false);
        String strNextToken = stringTokenizer.nextToken();
        String strNextToken2 = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : null;
        return strNextToken2 == null ? new QName((String) null, strNextToken) : new QName(strNextToken, strNextToken2);
    }

    public static boolean isXMLNSDecl(String str) {
        if (str.startsWith("xmlns")) {
            return str.equals("xmlns") || str.startsWith("xmlns:");
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof QName) {
            QName qName = (QName) obj;
            String namespaceURI = getNamespaceURI();
            String namespaceURI2 = qName.getNamespaceURI();
            if (getLocalName().equals(qName.getLocalName()) && (namespaceURI == null || namespaceURI2 == null ? !(namespaceURI != null || namespaceURI2 != null) : namespaceURI.equals(namespaceURI2))) {
                return true;
            }
        }
        return false;
    }

    public String getLocalName() {
        return this._localName;
    }

    public String getNamespace() {
        return getNamespaceURI();
    }

    public String getNamespaceURI() {
        return this._namespaceURI;
    }

    public String getPrefix() {
        return this._prefix;
    }

    public int hashCode() {
        return this.m_hashCode;
    }

    public String toNamespacedString() {
        if (this._namespaceURI == null) {
            return this._localName;
        }
        return "{" + this._namespaceURI + "}" + this._localName;
    }

    public String toString() {
        if (this._prefix != null) {
            return this._prefix + ":" + this._localName;
        }
        if (this._namespaceURI == null) {
            return this._localName;
        }
        return "{" + this._namespaceURI + "}" + this._localName;
    }

    public String getLocalPart() {
        return getLocalName();
    }

    public boolean equals(String str, String str2) {
        String namespaceURI = getNamespaceURI();
        if (!getLocalName().equals(str2)) {
            return false;
        }
        if (namespaceURI == null || str == null) {
            return namespaceURI == null && str == null;
        }
        return namespaceURI.equals(str);
    }

    public QName(String str, String str2) {
        this(str, str2, false);
    }

    public QName(String str, String str2, boolean z) {
        if (str2 != null) {
            if (z && !XML11Char.isXML11ValidNCName(str2)) {
                w01.a(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_INVALID", null));
                throw null;
            }
            this._namespaceURI = str;
            this._localName = str2;
            this.m_hashCode = toString().hashCode();
            return;
        }
        w01.a(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_NULL", null));
        throw null;
    }

    public QName(String str, String str2, String str3) {
        this(str, str2, str3, false);
    }

    public QName(String str, String str2, String str3, boolean z) {
        if (str3 != null) {
            if (z) {
                if (XML11Char.isXML11ValidNCName(str3)) {
                    if (str2 != null && !XML11Char.isXML11ValidNCName(str2)) {
                        w01.a(XMLMessages.createXMLMessage("ER_ARG_PREFIX_INVALID", null));
                        throw null;
                    }
                } else {
                    w01.a(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_INVALID", null));
                    throw null;
                }
            }
            this._namespaceURI = str;
            this._prefix = str2;
            this._localName = str3;
            this.m_hashCode = toString().hashCode();
            return;
        }
        w01.a(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_NULL", null));
        throw null;
    }

    public QName(String str) {
        this(str, false);
    }

    public QName(String str, boolean z) {
        if (str != null) {
            if (z && !XML11Char.isXML11ValidNCName(str)) {
                w01.a(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_INVALID", null));
                throw null;
            }
            this._namespaceURI = null;
            this._localName = str;
            this.m_hashCode = toString().hashCode();
            return;
        }
        w01.a(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_NULL", null));
        throw null;
    }

    public QName(String str, Stack<NameSpace> stack) {
        this(str, stack, false);
    }

    public QName() {
    }

    public QName(String str, Element element, PrefixResolver prefixResolver) {
        this(str, element, prefixResolver, false);
    }

    public QName(String str, Element element, PrefixResolver prefixResolver, boolean z) {
        this._namespaceURI = null;
        int iIndexOf = str.indexOf(58);
        if (iIndexOf > 0 && element != null) {
            String strSubstring = str.substring(0, iIndexOf);
            this._prefix = strSubstring;
            if (strSubstring.equals("xml")) {
                this._namespaceURI = "http://www.w3.org/XML/1998/namespace";
            } else if (strSubstring.equals("xmlns")) {
                return;
            } else {
                this._namespaceURI = prefixResolver.getNamespaceForPrefix(strSubstring, element);
            }
            if (this._namespaceURI == null) {
                f63.a(XMLMessages.createXMLMessage("ER_PREFIX_MUST_RESOLVE", new Object[]{strSubstring}));
                throw null;
            }
        }
        str = iIndexOf >= 0 ? str.substring(iIndexOf + 1) : str;
        this._localName = str;
        if (z && !XML11Char.isXML11ValidNCName(str)) {
            w01.a(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_INVALID", null));
            throw null;
        }
        this.m_hashCode = toString().hashCode();
    }

    public QName(String str, PrefixResolver prefixResolver) {
        this(str, prefixResolver, false);
    }

    public QName(String str, PrefixResolver prefixResolver, boolean z) {
        String strSubstring;
        String str2;
        this._namespaceURI = null;
        int iIndexOf = str.indexOf(58);
        if (iIndexOf > 0) {
            strSubstring = str.substring(0, iIndexOf);
            if (strSubstring.equals("xml")) {
                this._namespaceURI = "http://www.w3.org/XML/1998/namespace";
            } else {
                this._namespaceURI = prefixResolver.getNamespaceForPrefix(strSubstring);
            }
            if (this._namespaceURI != null) {
                this._localName = str.substring(iIndexOf + 1);
            } else {
                f63.a(XMLMessages.createXMLMessage("ER_PREFIX_MUST_RESOLVE", new Object[]{strSubstring}));
                throw null;
            }
        } else if (iIndexOf != 0) {
            this._localName = str;
            strSubstring = null;
        } else {
            f63.a(XMLMessages.createXMLMessage("ER_NAME_CANT_START_WITH_COLON", null));
            throw null;
        }
        if (z && ((str2 = this._localName) == null || !XML11Char.isXML11ValidNCName(str2))) {
            w01.a(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_INVALID", null));
            throw null;
        }
        this.m_hashCode = toString().hashCode();
        this._prefix = strSubstring;
    }
}
