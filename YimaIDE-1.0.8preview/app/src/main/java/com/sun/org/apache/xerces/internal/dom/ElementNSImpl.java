package com.sun.org.apache.xerces.internal.dom;

import com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSComplexTypeDecl;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import defpackage.zi0;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ElementNSImpl extends ElementImpl {
    static final long serialVersionUID = -9142310625494392642L;
    static final String xmlURI = "http://www.w3.org/XML/1998/namespace";
    protected String localName;
    protected String namespaceURI;
    transient XSTypeDefinition type;

    public ElementNSImpl(CoreDocumentImpl coreDocumentImpl, String str, String str2, String str3) throws DOMException {
        super(coreDocumentImpl, str2);
        this.localName = str3;
        this.namespaceURI = str;
    }

    private void setName(String str, String str2) {
        this.namespaceURI = str;
        if (str != null) {
            this.namespaceURI = str.length() == 0 ? null : str;
        }
        if (str2 == null) {
            zi0.a(14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
            return;
        }
        int iIndexOf = str2.indexOf(58);
        int iLastIndexOf = str2.lastIndexOf(58);
        this.ownerDocument.checkNamespaceWF(str2, iIndexOf, iLastIndexOf);
        if (iIndexOf >= 0) {
            String strSubstring = str2.substring(0, iIndexOf);
            this.localName = str2.substring(iLastIndexOf + 1);
            if (this.ownerDocument.errorChecking) {
                if (str == null || (strSubstring.equals("xml") && !str.equals(NamespaceContext.XML_URI))) {
                    zi0.a(14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
                    return;
                } else {
                    this.ownerDocument.checkQName(strSubstring, this.localName);
                    this.ownerDocument.checkDOMNSErr(strSubstring, str);
                    return;
                }
            }
            return;
        }
        this.localName = str2;
        CoreDocumentImpl coreDocumentImpl = this.ownerDocument;
        if (coreDocumentImpl.errorChecking) {
            coreDocumentImpl.checkQName(null, str2);
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

    @Override // com.sun.org.apache.xerces.internal.dom.ElementImpl, org.w3c.dom.TypeInfo
    public String getTypeName() {
        XSTypeDefinition xSTypeDefinition = this.type;
        if (xSTypeDefinition == null) {
            return null;
        }
        if (xSTypeDefinition instanceof XSSimpleTypeDecl) {
            return ((XSSimpleTypeDecl) xSTypeDefinition).getTypeName();
        }
        if (xSTypeDefinition instanceof XSComplexTypeDecl) {
            return ((XSComplexTypeDecl) xSTypeDefinition).getTypeName();
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ElementImpl, org.w3c.dom.TypeInfo
    public String getTypeNamespace() {
        XSTypeDefinition xSTypeDefinition = this.type;
        if (xSTypeDefinition != null) {
            return xSTypeDefinition.getNamespace();
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ElementImpl
    public Attr getXMLBaseAttribute() {
        return (Attr) this.attributes.getNamedItemNS("http://www.w3.org/XML/1998/namespace", "base");
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ElementImpl, org.w3c.dom.TypeInfo
    public boolean isDerivedFrom(String str, String str2, int i) {
        if (needsSyncData()) {
            synchronizeData();
        }
        XSTypeDefinition xSTypeDefinition = this.type;
        if (xSTypeDefinition == null) {
            return false;
        }
        if (xSTypeDefinition instanceof XSSimpleTypeDecl) {
            return ((XSSimpleTypeDecl) xSTypeDefinition).isDOMDerivedFrom(str, str2, i);
        }
        if (xSTypeDefinition instanceof XSComplexTypeDecl) {
            return ((XSComplexTypeDecl) xSTypeDefinition).isDOMDerivedFrom(str, str2, i);
        }
        return false;
    }

    public void rename(String str, String str2) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.name = str2;
        setName(str, str2);
        reconcileDefaultAttributes();
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public void setPrefix(String str) throws DOMException {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.ownerDocument.errorChecking) {
            if (isReadOnly()) {
                zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                return;
            }
            if (str != null && str.length() != 0) {
                if (!CoreDocumentImpl.isXMLName(str, this.ownerDocument.isXML11Version())) {
                    zi0.a(5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
                    return;
                }
                if (this.namespaceURI == null || str.indexOf(58) >= 0) {
                    zi0.a(14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
                    return;
                } else if (str.equals("xml") && !this.namespaceURI.equals("http://www.w3.org/XML/1998/namespace")) {
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

    public void setType(XSTypeDefinition xSTypeDefinition) {
        this.type = xSTypeDefinition;
    }

    public ElementNSImpl(CoreDocumentImpl coreDocumentImpl, String str, String str2) throws DOMException {
        super(coreDocumentImpl, str2);
        setName(str, str2);
    }

    public ElementNSImpl() {
    }

    public ElementNSImpl(CoreDocumentImpl coreDocumentImpl, String str) {
        super(coreDocumentImpl, str);
    }
}
