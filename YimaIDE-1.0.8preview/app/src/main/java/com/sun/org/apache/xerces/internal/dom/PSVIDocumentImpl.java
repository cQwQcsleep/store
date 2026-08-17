package com.sun.org.apache.xerces.internal.dom;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PSVIDocumentImpl extends DocumentImpl {
    static final long serialVersionUID = -8822220250676434522L;

    public PSVIDocumentImpl() {
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        throw new NotSerializableException(getClass().getName());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        throw new NotSerializableException(getClass().getName());
    }

    @Override // com.sun.org.apache.xerces.internal.dom.DocumentImpl, com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl, com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.ChildNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public Node cloneNode(boolean z) {
        PSVIDocumentImpl pSVIDocumentImpl = new PSVIDocumentImpl();
        callUserDataHandlers(this, pSVIDocumentImpl, (short) 1);
        cloneNode(pSVIDocumentImpl, z);
        pSVIDocumentImpl.mutationEvents = this.mutationEvents;
        return pSVIDocumentImpl;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl, org.w3c.dom.Document
    public Attr createAttributeNS(String str, String str2) throws DOMException {
        return new PSVIAttrNSImpl(this, str, str2);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl, org.w3c.dom.Document
    public Element createElementNS(String str, String str2) throws DOMException {
        return new PSVIElementNSImpl(this, str, str2);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl, org.w3c.dom.Document
    public DOMConfiguration getDomConfig() {
        super.getDomConfig();
        return this.fConfiguration;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.DocumentImpl, com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl, org.w3c.dom.Document
    public DOMImplementation getImplementation() {
        return PSVIDOMImplementationImpl.getDOMImplementation();
    }

    public PSVIDocumentImpl(DocumentType documentType) {
        super(documentType);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public Attr createAttributeNS(String str, String str2, String str3) throws DOMException {
        return new PSVIAttrNSImpl(this, str, str2, str3);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public Element createElementNS(String str, String str2, String str3) throws DOMException {
        return new PSVIElementNSImpl(this, str, str2, str3);
    }
}
