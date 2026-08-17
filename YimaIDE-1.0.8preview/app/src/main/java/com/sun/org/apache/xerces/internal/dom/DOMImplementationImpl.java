package com.sun.org.apache.xerces.internal.dom;

import defpackage.zi0;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMImplementationImpl extends CoreDOMImplementationImpl implements DOMImplementation {
    static DOMImplementationImpl singleton = new DOMImplementationImpl();

    public static DOMImplementation getDOMImplementation() {
        return singleton;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDOMImplementationImpl, org.w3c.dom.DOMImplementation
    public Document createDocument(String str, String str2, DocumentType documentType) throws DOMException {
        if (str == null && str2 == null && documentType == null) {
            return new DocumentImpl();
        }
        if (documentType != null && documentType.getOwnerDocument() != null) {
            zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
            return null;
        }
        DocumentImpl documentImpl = new DocumentImpl(documentType);
        documentImpl.appendChild(documentImpl.createElementNS(str, str2));
        return documentImpl;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDOMImplementationImpl, org.w3c.dom.DOMImplementation
    public boolean hasFeature(String str, String str2) {
        if (str == null || str.length() == 0) {
            return false;
        }
        boolean zHasFeature = super.hasFeature(str, str2);
        if (zHasFeature) {
            return zHasFeature;
        }
        boolean z = str2 == null || str2.length() == 0;
        if (str.startsWith("+")) {
            str = str.substring(1);
        }
        return (str.equalsIgnoreCase("Events") && (z || str2.equals("2.0"))) || (str.equalsIgnoreCase("MutationEvents") && (z || str2.equals("2.0"))) || ((str.equalsIgnoreCase("Traversal") && (z || str2.equals("2.0"))) || ((str.equalsIgnoreCase("Range") && (z || str2.equals("2.0"))) || (str.equalsIgnoreCase("MutationEvents") && (z || str2.equals("2.0")))));
    }
}
