package com.sun.org.apache.xerces.internal.dom;

import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import org.w3c.dom.DOMException;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.UserDataHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DocumentTypeImpl extends ParentNode implements DocumentType {
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("name", String.class), new ObjectStreamField("entities", NamedNodeMapImpl.class), new ObjectStreamField("notations", NamedNodeMapImpl.class), new ObjectStreamField(Constants.ATTRNAME_ELEMENTS, NamedNodeMapImpl.class), new ObjectStreamField("publicID", String.class), new ObjectStreamField("systemID", String.class), new ObjectStreamField("internalSubset", String.class), new ObjectStreamField("doctypeNumber", Integer.TYPE), new ObjectStreamField("userData", Hashtable.class)};
    static final long serialVersionUID = 7751299192316526485L;
    private int doctypeNumber;
    protected NamedNodeMapImpl elements;
    protected NamedNodeMapImpl entities;
    protected String internalSubset;
    protected String name;
    protected NamedNodeMapImpl notations;
    protected String publicID;
    protected String systemID;
    private Map<String, ParentNode.UserDataRecord> userData;

    public DocumentTypeImpl(CoreDocumentImpl coreDocumentImpl, String str) {
        super(coreDocumentImpl);
        this.doctypeNumber = 0;
        this.userData = null;
        this.name = str;
        this.entities = new NamedNodeMapImpl(this);
        this.notations = new NamedNodeMapImpl(this);
        this.elements = new NamedNodeMapImpl(this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        ObjectInputStream.GetField fields = objectInputStream.readFields();
        this.name = (String) fields.get("name", (Object) null);
        this.entities = (NamedNodeMapImpl) fields.get("entities", (Object) null);
        this.notations = (NamedNodeMapImpl) fields.get("notations", (Object) null);
        this.elements = (NamedNodeMapImpl) fields.get(Constants.ATTRNAME_ELEMENTS, (Object) null);
        this.publicID = (String) fields.get("publicID", (Object) null);
        this.systemID = (String) fields.get("systemID", (Object) null);
        this.internalSubset = (String) fields.get("internalSubset", (Object) null);
        this.doctypeNumber = fields.get("doctypeNumber", 0);
        Hashtable hashtable = (Hashtable) fields.get("userData", (Object) null);
        if (hashtable != null) {
            this.userData = new HashMap(hashtable);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        Hashtable hashtable = this.userData == null ? null : new Hashtable(this.userData);
        ObjectOutputStream.PutField putFieldPutFields = objectOutputStream.putFields();
        putFieldPutFields.put("name", this.name);
        putFieldPutFields.put("entities", this.entities);
        putFieldPutFields.put("notations", this.notations);
        putFieldPutFields.put(Constants.ATTRNAME_ELEMENTS, this.elements);
        putFieldPutFields.put("publicID", this.publicID);
        putFieldPutFields.put("systemID", this.systemID);
        putFieldPutFields.put("internalSubset", this.internalSubset);
        putFieldPutFields.put("doctypeNumber", this.doctypeNumber);
        putFieldPutFields.put("userData", hashtable);
        objectOutputStream.writeFields();
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.ChildNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public Node cloneNode(boolean z) {
        DocumentTypeImpl documentTypeImpl = (DocumentTypeImpl) super.cloneNode(z);
        documentTypeImpl.entities = this.entities.cloneMap(documentTypeImpl);
        documentTypeImpl.notations = this.notations.cloneMap(documentTypeImpl);
        documentTypeImpl.elements = this.elements.cloneMap(documentTypeImpl);
        return documentTypeImpl;
    }

    public NamedNodeMap getElements() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return this.elements;
    }

    @Override // org.w3c.dom.DocumentType
    public NamedNodeMap getEntities() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return this.entities;
    }

    @Override // org.w3c.dom.DocumentType
    public String getInternalSubset() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.internalSubset;
    }

    @Override // org.w3c.dom.DocumentType
    public String getName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.name;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.name;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl
    public int getNodeNumber() {
        if (getOwnerDocument() != null) {
            return super.getNodeNumber();
        }
        if (this.doctypeNumber == 0) {
            this.doctypeNumber = ((CoreDOMImplementationImpl) CoreDOMImplementationImpl.getDOMImplementation()).assignDocTypeNumber();
        }
        return this.doctypeNumber;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return (short) 10;
    }

    @Override // org.w3c.dom.DocumentType
    public NamedNodeMap getNotations() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return this.notations;
    }

    @Override // org.w3c.dom.DocumentType
    public String getPublicId() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.publicID;
    }

    @Override // org.w3c.dom.DocumentType
    public String getSystemId() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.systemID;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getTextContent() throws DOMException {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public Object getUserData(String str) {
        ParentNode.UserDataRecord userDataRecord;
        Map<String, ParentNode.UserDataRecord> map = this.userData;
        if (map == null || (userDataRecord = map.get(str)) == null) {
            return null;
        }
        return userDataRecord.fData;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl
    public Map<String, ParentNode.UserDataRecord> getUserDataRecord() {
        return this.userData;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public boolean isEqualNode(Node node) {
        if (!super.isEqualNode(node)) {
            return false;
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        DocumentTypeImpl documentTypeImpl = (DocumentTypeImpl) node;
        if ((getPublicId() == null && documentTypeImpl.getPublicId() != null) || ((getPublicId() != null && documentTypeImpl.getPublicId() == null) || ((getSystemId() == null && documentTypeImpl.getSystemId() != null) || ((getSystemId() != null && documentTypeImpl.getSystemId() == null) || ((getInternalSubset() == null && documentTypeImpl.getInternalSubset() != null) || (getInternalSubset() != null && documentTypeImpl.getInternalSubset() == null)))))) {
            return false;
        }
        if (getPublicId() != null && !getPublicId().equals(documentTypeImpl.getPublicId())) {
            return false;
        }
        if (getSystemId() != null && !getSystemId().equals(documentTypeImpl.getSystemId())) {
            return false;
        }
        if (getInternalSubset() != null && !getInternalSubset().equals(documentTypeImpl.getInternalSubset())) {
            return false;
        }
        NamedNodeMapImpl namedNodeMapImpl = documentTypeImpl.entities;
        NamedNodeMapImpl namedNodeMapImpl2 = this.entities;
        if ((namedNodeMapImpl2 == null && namedNodeMapImpl != null) || (namedNodeMapImpl2 != null && namedNodeMapImpl == null)) {
            return false;
        }
        if (namedNodeMapImpl2 != null && namedNodeMapImpl != null) {
            if (namedNodeMapImpl2.getLength() != namedNodeMapImpl.getLength()) {
                return false;
            }
            for (int i = 0; this.entities.item(i) != null; i++) {
                Node nodeItem = this.entities.item(i);
                if (!((NodeImpl) nodeItem).isEqualNode(namedNodeMapImpl.getNamedItem(nodeItem.getNodeName()))) {
                    return false;
                }
            }
        }
        NamedNodeMapImpl namedNodeMapImpl3 = documentTypeImpl.notations;
        NamedNodeMapImpl namedNodeMapImpl4 = this.notations;
        if ((namedNodeMapImpl4 == null && namedNodeMapImpl3 != null) || (namedNodeMapImpl4 != null && namedNodeMapImpl3 == null)) {
            return false;
        }
        if (namedNodeMapImpl4 == null || namedNodeMapImpl3 == null) {
            return true;
        }
        if (namedNodeMapImpl4.getLength() != namedNodeMapImpl3.getLength()) {
            return false;
        }
        for (int i2 = 0; this.notations.item(i2) != null; i2++) {
            Node nodeItem2 = this.notations.item(i2);
            if (!((NodeImpl) nodeItem2).isEqualNode(namedNodeMapImpl3.getNamedItem(nodeItem2.getNodeName()))) {
                return false;
            }
        }
        return true;
    }

    public void setInternalSubset(String str) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.internalSubset = str;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl
    public void setOwnerDocument(CoreDocumentImpl coreDocumentImpl) {
        super.setOwnerDocument(coreDocumentImpl);
        this.entities.setOwnerDocument(coreDocumentImpl);
        this.notations.setOwnerDocument(coreDocumentImpl);
        this.elements.setOwnerDocument(coreDocumentImpl);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl
    public void setReadOnly(boolean z, boolean z2) {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        super.setReadOnly(z, z2);
        this.elements.setReadOnly(z, true);
        this.entities.setReadOnly(z, true);
        this.notations.setReadOnly(z, true);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public void setTextContent(String str) throws DOMException {
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public Object setUserData(String str, Object obj, UserDataHandler userDataHandler) {
        ParentNode.UserDataRecord userDataRecordRemove;
        if (this.userData == null) {
            this.userData = new HashMap();
        }
        Map<String, ParentNode.UserDataRecord> map = this.userData;
        if (obj == null) {
            if (map == null || (userDataRecordRemove = map.remove(str)) == null) {
                return null;
            }
            return userDataRecordRemove.fData;
        }
        ParentNode.UserDataRecord userDataRecordPut = map.put(str, new ParentNode.UserDataRecord(obj, userDataHandler));
        if (userDataRecordPut != null) {
            return userDataRecordPut.fData;
        }
        return null;
    }

    public DocumentTypeImpl(CoreDocumentImpl coreDocumentImpl, String str, String str2, String str3) {
        this(coreDocumentImpl, str);
        this.publicID = str2;
        this.systemID = str3;
    }
}
