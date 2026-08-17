package com.sun.org.apache.xerces.internal.dom;

import defpackage.zi0;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class CharacterDataImpl extends ChildNode {
    static final long serialVersionUID = 7931170150428474230L;
    private static transient NodeList singletonNodeList = new NodeList() { // from class: com.sun.org.apache.xerces.internal.dom.CharacterDataImpl.1
        @Override // org.w3c.dom.NodeList
        public int getLength() {
            return 0;
        }

        @Override // org.w3c.dom.NodeList
        public Node item(int i) {
            return null;
        }
    };
    protected String data;

    public CharacterDataImpl(CoreDocumentImpl coreDocumentImpl, String str) {
        super(coreDocumentImpl);
        this.data = str;
    }

    public void appendData(String str) {
        if (isReadOnly()) {
            zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            return;
        }
        if (str == null) {
            return;
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        setNodeValue(this.data + str);
    }

    public void deleteData(int i, int i2) throws DOMException {
        internalDeleteData(i, i2, false);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public NodeList getChildNodes() {
        return singletonNodeList;
    }

    public String getData() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.data;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.NodeList
    public int getLength() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.data.length();
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeValue() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.data;
    }

    public void insertData(int i, String str) throws DOMException {
        internalInsertData(i, str, false);
    }

    public void internalDeleteData(int i, int i2, boolean z) throws DOMException {
        String strSubstring;
        CoreDocumentImpl coreDocumentImplOwnerDocument = ownerDocument();
        if (coreDocumentImplOwnerDocument.errorChecking) {
            if (isReadOnly()) {
                zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                return;
            } else if (i2 < 0) {
                zi0.a(1, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INDEX_SIZE_ERR", null));
                return;
            }
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        int iMax = Math.max((this.data.length() - i2) - i, 0);
        try {
            String strSubstring2 = this.data.substring(0, i);
            if (iMax > 0) {
                int i3 = i + i2;
                strSubstring = this.data.substring(i3, iMax + i3);
            } else {
                strSubstring = "";
            }
            setNodeValueInternal(strSubstring2.concat(strSubstring), z);
            coreDocumentImplOwnerDocument.deletedText(this, i, i2);
        } catch (StringIndexOutOfBoundsException unused) {
            zi0.a(1, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INDEX_SIZE_ERR", null));
        }
    }

    public void internalInsertData(int i, String str, boolean z) throws DOMException {
        CoreDocumentImpl coreDocumentImplOwnerDocument = ownerDocument();
        if (coreDocumentImplOwnerDocument.errorChecking && isReadOnly()) {
            zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            return;
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        try {
            setNodeValueInternal(new StringBuffer(this.data).insert(i, str).toString(), z);
            coreDocumentImplOwnerDocument.insertedText(this, i, str.length());
        } catch (StringIndexOutOfBoundsException unused) {
            zi0.a(1, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INDEX_SIZE_ERR", null));
        }
    }

    public void replaceData(int i, int i2, String str) throws DOMException {
        CoreDocumentImpl coreDocumentImplOwnerDocument = ownerDocument();
        if (coreDocumentImplOwnerDocument.errorChecking && isReadOnly()) {
            zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            return;
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        coreDocumentImplOwnerDocument.replacingData(this);
        String str2 = this.data;
        internalDeleteData(i, i2, true);
        internalInsertData(i, str, true);
        coreDocumentImplOwnerDocument.replacedCharacterData(this, str2, this.data);
    }

    public void setData(String str) throws DOMException {
        setNodeValue(str);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public void setNodeValue(String str) {
        setNodeValueInternal(str);
        ownerDocument().replacedText(this);
    }

    public void setNodeValueInternal(String str, boolean z) {
        CoreDocumentImpl coreDocumentImplOwnerDocument = ownerDocument();
        if (coreDocumentImplOwnerDocument.errorChecking && isReadOnly()) {
            zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            return;
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        String str2 = this.data;
        coreDocumentImplOwnerDocument.modifyingCharacterData(this, z);
        this.data = str;
        coreDocumentImplOwnerDocument.modifiedCharacterData(this, str2, str, z);
    }

    public String substringData(int i, int i2) throws DOMException {
        if (needsSyncData()) {
            synchronizeData();
        }
        int length = this.data.length();
        if (i2 < 0 || i < 0 || i > length - 1) {
            zi0.a(1, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INDEX_SIZE_ERR", null));
            return null;
        }
        return this.data.substring(i, Math.min(i2 + i, length));
    }

    public CharacterDataImpl() {
    }

    public void setNodeValueInternal(String str) {
        setNodeValueInternal(str, false);
    }
}
