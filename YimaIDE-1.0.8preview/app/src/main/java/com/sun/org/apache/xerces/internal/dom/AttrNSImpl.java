package com.sun.org.apache.xerces.internal.dom;

import com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import defpackage.zi0;
import org.w3c.dom.DOMException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AttrNSImpl extends AttrImpl {
    static final long serialVersionUID = -781906615369795414L;
    static final String xmlURI = "http://www.w3.org/XML/1998/namespace";
    static final String xmlnsURI = "http://www.w3.org/2000/xmlns/";
    protected String localName;
    protected String namespaceURI;

    public AttrNSImpl(CoreDocumentImpl coreDocumentImpl, String str, String str2, String str3) {
        super(coreDocumentImpl, str2);
        this.localName = str3;
        this.namespaceURI = str;
    }

    private void setName(String str, String str2) {
        CoreDocumentImpl coreDocumentImplOwnerDocument = ownerDocument();
        this.namespaceURI = str;
        if (str != null) {
            this.namespaceURI = str.length() == 0 ? null : str;
        }
        int iIndexOf = str2.indexOf(58);
        int iLastIndexOf = str2.lastIndexOf(58);
        coreDocumentImplOwnerDocument.checkNamespaceWF(str2, iIndexOf, iLastIndexOf);
        if (iIndexOf >= 0) {
            String strSubstring = str2.substring(0, iIndexOf);
            String strSubstring2 = str2.substring(iLastIndexOf + 1);
            this.localName = strSubstring2;
            coreDocumentImplOwnerDocument.checkQName(strSubstring, strSubstring2);
            coreDocumentImplOwnerDocument.checkDOMNSErr(strSubstring, str);
            return;
        }
        this.localName = str2;
        if (coreDocumentImplOwnerDocument.errorChecking) {
            coreDocumentImplOwnerDocument.checkQName(null, str2);
            if ((!str2.equals("xmlns") || (str != null && str.equals(NamespaceContext.XMLNS_URI))) && (str == null || !str.equals(NamespaceContext.XMLNS_URI) || str2.equals("xmlns"))) {
                return;
            }
            zi0.a(14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getLocalName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.localName;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getNamespaceURI() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.namespaceURI;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getPrefix() {
        if (needsSyncData()) {
            synchronizeData();
        }
        int iIndexOf = this.name.indexOf(58);
        if (iIndexOf < 0) {
            return null;
        }
        return this.name.substring(0, iIndexOf);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.AttrImpl, org.w3c.dom.TypeInfo
    public String getTypeName() {
        Object obj = this.type;
        if (obj != null) {
            return obj instanceof XSSimpleTypeDecl ? ((XSSimpleTypeDecl) obj).getName() : (String) obj;
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.AttrImpl, org.w3c.dom.TypeInfo
    public String getTypeNamespace() {
        Object obj = this.type;
        if (obj != null) {
            return obj instanceof XSSimpleTypeDecl ? ((XSSimpleTypeDecl) obj).getNamespace() : "http://www.w3.org/TR/REC-xml";
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.AttrImpl, org.w3c.dom.TypeInfo
    public boolean isDerivedFrom(String str, String str2, int i) {
        Object obj = this.type;
        if (obj == null || !(obj instanceof XSSimpleTypeDecl)) {
            return false;
        }
        return ((XSSimpleTypeDecl) obj).isDOMDerivedFrom(str, str2, i);
    }

    public void rename(String str, String str2) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.name = str2;
        setName(str, str2);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public void setPrefix(String str) throws DOMException {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (ownerDocument().errorChecking) {
            if (isReadOnly()) {
                zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                return;
            }
            if (str != null && str.length() != 0) {
                if (!CoreDocumentImpl.isXMLName(str, ownerDocument().isXML11Version())) {
                    zi0.a(5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
                    return;
                }
                if (this.namespaceURI == null || str.indexOf(58) >= 0) {
                    zi0.a(14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
                    return;
                }
                if (str.equals("xmlns")) {
                    if (!this.namespaceURI.equals("http://www.w3.org/2000/xmlns/")) {
                        zi0.a(14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
                        return;
                    }
                } else if (str.equals("xml")) {
                    if (!this.namespaceURI.equals("http://www.w3.org/XML/1998/namespace")) {
                        zi0.a(14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
                        return;
                    }
                } else if (this.name.equals("xmlns")) {
                    zi0.a(14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
                    return;
                }
            }
        }
        if (str == null || str.length() == 0) {
            this.name = this.localName;
            return;
        }
        this.name = str + ":" + this.localName;
    }

    public AttrNSImpl(CoreDocumentImpl coreDocumentImpl, String str, String str2) {
        super(coreDocumentImpl, str2);
        setName(str, str2);
    }

    public AttrNSImpl() {
    }

    public AttrNSImpl(CoreDocumentImpl coreDocumentImpl, String str) {
        super(coreDocumentImpl, str);
    }
}
