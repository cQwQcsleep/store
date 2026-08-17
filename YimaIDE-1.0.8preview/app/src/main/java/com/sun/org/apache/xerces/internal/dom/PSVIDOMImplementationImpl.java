package com.sun.org.apache.xerces.internal.dom;

import com.sun.org.apache.xerces.internal.impl.Constants;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PSVIDOMImplementationImpl extends DOMImplementationImpl {
    static final PSVIDOMImplementationImpl singleton = new PSVIDOMImplementationImpl();

    public static DOMImplementation getDOMImplementation() {
        return singleton;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDOMImplementationImpl
    public CoreDocumentImpl createDocument(DocumentType documentType) {
        return new PSVIDocumentImpl(documentType);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.DOMImplementationImpl, com.sun.org.apache.xerces.internal.dom.CoreDOMImplementationImpl, org.w3c.dom.DOMImplementation
    public boolean hasFeature(String str, String str2) {
        return super.hasFeature(str, str2) || str.equalsIgnoreCase(Constants.DOM_PSVI);
    }
}
