package com.sun.org.apache.xerces.internal.dom;

import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl;
import com.sun.org.apache.xerces.internal.util.URI;
import com.sun.org.apache.xerces.internal.util.XML11Char;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import defpackage.zi0;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.function.Consumer;
import jdk.xml.internal.SecuritySupport;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Notation;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.ls.DOMImplementationLS;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CoreDocumentImpl extends ParentNode implements Document {
    private static final int[] kidOK;
    private static final ObjectStreamField[] serialPersistentFields;
    static final long serialVersionUID = 0;
    protected String actualEncoding;
    protected boolean allowGrammarAccess;
    protected boolean ancestorChecking;
    protected int changes;
    protected ElementImpl docElement;
    protected DocumentTypeImpl docType;
    private int documentNumber;
    transient DOMNormalizer domNormalizer;
    protected String encoding;
    protected boolean errorChecking;
    transient DOMConfigurationImpl fConfiguration;
    protected String fDocumentURI;
    transient NodeListCache fFreeNLCache;
    transient Object fXPathEvaluator;
    protected Map<String, Node> identifiers;
    private int nodeCounter;
    private Map<Node, Integer> nodeTable;
    private Map<Node, Map<String, ParentNode.UserDataRecord>> nodeUserData;
    protected boolean standalone;
    protected String version;
    private boolean xml11Version;
    protected boolean xmlVersionChanged;

    static {
        ObjectStreamField objectStreamField = new ObjectStreamField("docType", DocumentTypeImpl.class);
        ObjectStreamField objectStreamField2 = new ObjectStreamField("docElement", ElementImpl.class);
        ObjectStreamField objectStreamField3 = new ObjectStreamField("fFreeNLCache", NodeListCache.class);
        ObjectStreamField objectStreamField4 = new ObjectStreamField("encoding", String.class);
        ObjectStreamField objectStreamField5 = new ObjectStreamField("actualEncoding", String.class);
        ObjectStreamField objectStreamField6 = new ObjectStreamField("version", String.class);
        Class cls = Boolean.TYPE;
        ObjectStreamField objectStreamField7 = new ObjectStreamField(Constants.ATTRNAME_OUTPUT_STANDALONE, cls);
        ObjectStreamField objectStreamField8 = new ObjectStreamField("fDocumentURI", String.class);
        ObjectStreamField objectStreamField9 = new ObjectStreamField("userData", Hashtable.class);
        ObjectStreamField objectStreamField10 = new ObjectStreamField("identifiers", Hashtable.class);
        Class cls2 = Integer.TYPE;
        serialPersistentFields = new ObjectStreamField[]{objectStreamField, objectStreamField2, objectStreamField3, objectStreamField4, objectStreamField5, objectStreamField6, objectStreamField7, objectStreamField8, objectStreamField9, objectStreamField10, new ObjectStreamField("changes", cls2), new ObjectStreamField("allowGrammarAccess", cls), new ObjectStreamField("errorChecking", cls), new ObjectStreamField("ancestorChecking", cls), new ObjectStreamField("xmlVersionChanged", cls), new ObjectStreamField("documentNumber", cls2), new ObjectStreamField("nodeCounter", cls2), new ObjectStreamField("nodeTable", Hashtable.class), new ObjectStreamField("xml11Version", cls)};
        kidOK = new int[]{0, 442, 40, 0, 0, 442, 442, 0, 0, WinError.ERROR_CLASS_ALREADY_EXISTS, 0, 442, 0};
    }

    public CoreDocumentImpl(boolean z) {
        super(null);
        this.changes = 0;
        this.errorChecking = true;
        this.ancestorChecking = true;
        this.xmlVersionChanged = false;
        this.domNormalizer = null;
        this.fConfiguration = null;
        this.fXPathEvaluator = null;
        this.documentNumber = 0;
        this.nodeCounter = 0;
        this.xml11Version = false;
        this.ownerDocument = this;
        this.allowGrammarAccess = z;
        String systemProperty = SecuritySupport.getSystemProperty("http://java.sun.com/xml/dom/properties/ancestor-check");
        if (systemProperty == null || !systemProperty.equalsIgnoreCase("false")) {
            return;
        }
        this.ancestorChecking = false;
    }

    public static /* synthetic */ void b(Map map, short s, Node node, Node node2, String str) {
        ParentNode.UserDataRecord userDataRecord = (ParentNode.UserDataRecord) map.get(str);
        UserDataHandler userDataHandler = userDataRecord.fHandler;
        if (userDataHandler != null) {
            userDataHandler.handle(s, str, userDataRecord.fData, node, node2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:49:0x0160  */
    /* JADX WARN: Code duplicated, block: B:80:0x01dc  */
    /* JADX WARN: Code duplicated, block: B:82:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:84:0x01e8 A[LOOP:0: B:83:0x01e6->B:84:0x01e8, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:87:0x01fb  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v14, types: [com.sun.org.apache.xerces.internal.dom.EntityImpl, com.sun.org.apache.xerces.internal.dom.NodeImpl] */
    /* JADX WARN: Type inference failed for: r2v16, types: [org.w3c.dom.ProcessingInstruction] */
    /* JADX WARN: Type inference failed for: r2v17, types: [org.w3c.dom.Comment] */
    /* JADX WARN: Type inference failed for: r2v20, types: [com.sun.org.apache.xerces.internal.dom.DocumentTypeImpl] */
    /* JADX WARN: Type inference failed for: r2v21, types: [org.w3c.dom.DocumentFragment] */
    /* JADX WARN: Type inference failed for: r2v22, types: [org.w3c.dom.Node] */
    /* JADX WARN: Type inference failed for: r2v25, types: [com.sun.org.apache.xerces.internal.dom.NotationImpl] */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, org.w3c.dom.Element] */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v7, types: [org.w3c.dom.Node] */
    /* JADX WARN: Type inference failed for: r2v8, types: [org.w3c.dom.Text] */
    /* JADX WARN: Type inference failed for: r2v9, types: [org.w3c.dom.CDATASection] */
    private Node importNode(Node node, boolean z, boolean z2, Map<Node, String> map) throws DOMException {
        ?? CreateElement;
        String str;
        Node firstChild;
        Map<String, ParentNode.UserDataRecord> userDataRecord = node instanceof NodeImpl ? ((NodeImpl) node).getUserDataRecord() : null;
        int i = 0;
        switch (node.getNodeType()) {
            case 1:
                boolean zHasFeature = node.getOwnerDocument().getImplementation().hasFeature("XML", "2.0");
                CreateElement = (!zHasFeature || node.getLocalName() == null) ? createElement(node.getNodeName()) : createElementNS(node.getNamespaceURI(), node.getNodeName());
                NamedNodeMap attributes = node.getAttributes();
                if (attributes != null) {
                    int length = attributes.getLength();
                    while (i < length) {
                        Attr attr = (Attr) attributes.item(i);
                        if (attr.getSpecified() || z2) {
                            Attr attr2 = (Attr) importNode(attr, true, z2, map);
                            if (!zHasFeature || attr.getLocalName() == null) {
                                CreateElement.setAttributeNode(attr2);
                            } else {
                                CreateElement.setAttributeNodeNS(attr2);
                            }
                        }
                        i++;
                    }
                }
                if (map != null && (str = map.get(node)) != null) {
                    if (this.identifiers == null) {
                        this.identifiers = new HashMap();
                    }
                    this.identifiers.put(str, (Node) CreateElement);
                }
                if (userDataRecord != null) {
                    callUserDataHandlers(node, CreateElement, (short) 2, userDataRecord);
                }
                if (z) {
                    for (firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
                        CreateElement.appendChild(importNode(firstChild, true, z2, map));
                    }
                }
                if (CreateElement.getNodeType() == 6) {
                    ((NodeImpl) CreateElement).setReadOnly(true, true);
                }
                return CreateElement;
            case 2:
                Attr attrCreateAttribute = (!node.getOwnerDocument().getImplementation().hasFeature("XML", "2.0") || node.getLocalName() == null) ? createAttribute(node.getNodeName()) : createAttributeNS(node.getNamespaceURI(), node.getNodeName());
                CreateElement = attrCreateAttribute;
                if (node instanceof AttrImpl) {
                    AttrImpl attrImpl = (AttrImpl) node;
                    if (attrImpl.hasStringValue()) {
                        ((AttrImpl) CreateElement).setValue(attrImpl.getValue());
                        CreateElement = CreateElement;
                        z = false;
                    } else {
                        z = true;
                    }
                } else if (node.getFirstChild() == null) {
                    CreateElement.setNodeValue(node.getNodeValue());
                    CreateElement = CreateElement;
                    z = false;
                } else {
                    z = true;
                }
                if (userDataRecord != null) {
                    callUserDataHandlers(node, CreateElement, (short) 2, userDataRecord);
                }
                if (z) {
                    while (firstChild != null) {
                        CreateElement.appendChild(importNode(firstChild, true, z2, map));
                    }
                }
                if (CreateElement.getNodeType() == 6) {
                    ((NodeImpl) CreateElement).setReadOnly(true, true);
                }
                return CreateElement;
            case 3:
                CreateElement = createTextNode(node.getNodeValue());
                if (userDataRecord != null) {
                    callUserDataHandlers(node, CreateElement, (short) 2, userDataRecord);
                }
                if (z) {
                    while (firstChild != null) {
                        CreateElement.appendChild(importNode(firstChild, true, z2, map));
                    }
                }
                if (CreateElement.getNodeType() == 6) {
                    ((NodeImpl) CreateElement).setReadOnly(true, true);
                }
                return CreateElement;
            case 4:
                CreateElement = createCDATASection(node.getNodeValue());
                if (userDataRecord != null) {
                    callUserDataHandlers(node, CreateElement, (short) 2, userDataRecord);
                }
                if (z) {
                    while (firstChild != null) {
                        CreateElement.appendChild(importNode(firstChild, true, z2, map));
                    }
                }
                if (CreateElement.getNodeType() == 6) {
                    ((NodeImpl) CreateElement).setReadOnly(true, true);
                }
                return CreateElement;
            case 5:
                CreateElement = createEntityReference(node.getNodeName());
                z = false;
                if (userDataRecord != null) {
                    callUserDataHandlers(node, CreateElement, (short) 2, userDataRecord);
                }
                if (z) {
                    while (firstChild != null) {
                        CreateElement.appendChild(importNode(firstChild, true, z2, map));
                    }
                }
                if (CreateElement.getNodeType() == 6) {
                    ((NodeImpl) CreateElement).setReadOnly(true, true);
                }
                return CreateElement;
            case 6:
                Entity entity = (Entity) node;
                CreateElement = (EntityImpl) createEntity(node.getNodeName());
                CreateElement.setPublicId(entity.getPublicId());
                CreateElement.setSystemId(entity.getSystemId());
                CreateElement.setNotationName(entity.getNotationName());
                CreateElement.isReadOnly(false);
                if (userDataRecord != null) {
                    callUserDataHandlers(node, CreateElement, (short) 2, userDataRecord);
                }
                if (z) {
                    while (firstChild != null) {
                        CreateElement.appendChild(importNode(firstChild, true, z2, map));
                    }
                }
                if (CreateElement.getNodeType() == 6) {
                    ((NodeImpl) CreateElement).setReadOnly(true, true);
                }
                return CreateElement;
            case 7:
                CreateElement = createProcessingInstruction(node.getNodeName(), node.getNodeValue());
                if (userDataRecord != null) {
                    callUserDataHandlers(node, CreateElement, (short) 2, userDataRecord);
                }
                if (z) {
                    while (firstChild != null) {
                        CreateElement.appendChild(importNode(firstChild, true, z2, map));
                    }
                }
                if (CreateElement.getNodeType() == 6) {
                    ((NodeImpl) CreateElement).setReadOnly(true, true);
                }
                return CreateElement;
            case 8:
                CreateElement = createComment(node.getNodeValue());
                if (userDataRecord != null) {
                    callUserDataHandlers(node, CreateElement, (short) 2, userDataRecord);
                }
                if (z) {
                    while (firstChild != null) {
                        CreateElement.appendChild(importNode(firstChild, true, z2, map));
                    }
                }
                if (CreateElement.getNodeType() == 6) {
                    ((NodeImpl) CreateElement).setReadOnly(true, true);
                }
                return CreateElement;
            case 9:
            default:
                zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
                return null;
            case 10:
                if (!z2) {
                    zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
                    return null;
                }
                DocumentType documentType = (DocumentType) node;
                CreateElement = (DocumentTypeImpl) createDocumentType(documentType.getNodeName(), documentType.getPublicId(), documentType.getSystemId());
                NamedNodeMap entities = documentType.getEntities();
                NamedNodeMap entities2 = CreateElement.getEntities();
                if (entities != null) {
                    for (int i2 = 0; i2 < entities.getLength(); i2++) {
                        entities2.setNamedItem(importNode(entities.item(i2), true, true, map));
                    }
                }
                NamedNodeMap notations = documentType.getNotations();
                NamedNodeMap notations2 = CreateElement.getNotations();
                if (notations != null) {
                    while (i < notations.getLength()) {
                        notations2.setNamedItem(importNode(notations.item(i), true, true, map));
                        i++;
                    }
                }
                if (userDataRecord != null) {
                    callUserDataHandlers(node, CreateElement, (short) 2, userDataRecord);
                }
                if (z) {
                    while (firstChild != null) {
                        CreateElement.appendChild(importNode(firstChild, true, z2, map));
                    }
                }
                if (CreateElement.getNodeType() == 6) {
                    ((NodeImpl) CreateElement).setReadOnly(true, true);
                }
                return CreateElement;
            case 11:
                CreateElement = createDocumentFragment();
                if (userDataRecord != null) {
                    callUserDataHandlers(node, CreateElement, (short) 2, userDataRecord);
                }
                if (z) {
                    while (firstChild != null) {
                        CreateElement.appendChild(importNode(firstChild, true, z2, map));
                    }
                }
                if (CreateElement.getNodeType() == 6) {
                    ((NodeImpl) CreateElement).setReadOnly(true, true);
                }
                return CreateElement;
            case 12:
                Notation notation = (Notation) node;
                CreateElement = (NotationImpl) createNotation(node.getNodeName());
                CreateElement.setPublicId(notation.getPublicId());
                CreateElement.setSystemId(notation.getSystemId());
                if (userDataRecord != null) {
                    callUserDataHandlers(node, CreateElement, (short) 2, userDataRecord);
                }
                if (z) {
                    while (firstChild != null) {
                        CreateElement.appendChild(importNode(firstChild, true, z2, map));
                    }
                }
                if (CreateElement.getNodeType() == 6) {
                    ((NodeImpl) CreateElement).setReadOnly(true, true);
                }
                return CreateElement;
        }
    }

    public static final boolean isValidQName(String str, String str2, boolean z) {
        if (str2 == null) {
            return false;
        }
        if (z) {
            return (str == null || XML11Char.isXML11ValidNCName(str)) && XML11Char.isXML11ValidNCName(str2);
        }
        return (str == null || XMLChar.isValidNCName(str)) && XMLChar.isValidNCName(str2);
    }

    public static final boolean isXMLName(String str, boolean z) {
        if (str == null) {
            return false;
        }
        return !z ? XMLChar.isValidName(str) : XML11Char.isXML11ValidName(str);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        ObjectInputStream.GetField fields = objectInputStream.readFields();
        this.docType = (DocumentTypeImpl) fields.get("docType", (Object) null);
        this.docElement = (ElementImpl) fields.get("docElement", (Object) null);
        this.fFreeNLCache = (NodeListCache) fields.get("fFreeNLCache", (Object) null);
        this.encoding = (String) fields.get("encoding", (Object) null);
        this.actualEncoding = (String) fields.get("actualEncoding", (Object) null);
        this.version = (String) fields.get("version", (Object) null);
        this.standalone = fields.get(Constants.ATTRNAME_OUTPUT_STANDALONE, false);
        this.fDocumentURI = (String) fields.get("fDocumentURI", (Object) null);
        Hashtable hashtable = (Hashtable) fields.get("userData", (Object) null);
        Hashtable hashtable2 = (Hashtable) fields.get("identifiers", (Object) null);
        this.changes = fields.get("changes", 0);
        this.allowGrammarAccess = fields.get("allowGrammarAccess", false);
        this.errorChecking = fields.get("errorChecking", true);
        this.ancestorChecking = fields.get("ancestorChecking", true);
        this.xmlVersionChanged = fields.get("xmlVersionChanged", false);
        this.documentNumber = fields.get("documentNumber", 0);
        this.nodeCounter = fields.get("nodeCounter", 0);
        Hashtable hashtable3 = (Hashtable) fields.get("nodeTable", (Object) null);
        this.xml11Version = fields.get("xml11Version", false);
        if (hashtable != null) {
            this.nodeUserData = new HashMap();
            for (Map.Entry entry : hashtable.entrySet()) {
                this.nodeUserData.put((Node) entry.getKey(), new HashMap((Map) entry.getValue()));
            }
        }
        if (hashtable2 != null) {
            this.identifiers = new HashMap(hashtable2);
        }
        if (hashtable3 != null) {
            this.nodeTable = new HashMap(hashtable3);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        Hashtable hashtable;
        if (this.nodeUserData != null) {
            hashtable = new Hashtable();
            for (Map.Entry<Node, Map<String, ParentNode.UserDataRecord>> entry : this.nodeUserData.entrySet()) {
                hashtable.put(entry.getKey(), new Hashtable(entry.getValue()));
            }
        } else {
            hashtable = null;
        }
        Hashtable hashtable2 = this.identifiers == null ? null : new Hashtable(this.identifiers);
        Hashtable hashtable3 = this.nodeTable != null ? new Hashtable(this.nodeTable) : null;
        ObjectOutputStream.PutField putFieldPutFields = objectOutputStream.putFields();
        putFieldPutFields.put("docType", this.docType);
        putFieldPutFields.put("docElement", this.docElement);
        putFieldPutFields.put("fFreeNLCache", this.fFreeNLCache);
        putFieldPutFields.put("encoding", this.encoding);
        putFieldPutFields.put("actualEncoding", this.actualEncoding);
        putFieldPutFields.put("version", this.version);
        putFieldPutFields.put(Constants.ATTRNAME_OUTPUT_STANDALONE, this.standalone);
        putFieldPutFields.put("fDocumentURI", this.fDocumentURI);
        putFieldPutFields.put("userData", hashtable);
        putFieldPutFields.put("identifiers", hashtable2);
        putFieldPutFields.put("changes", this.changes);
        putFieldPutFields.put("allowGrammarAccess", this.allowGrammarAccess);
        putFieldPutFields.put("errorChecking", this.errorChecking);
        putFieldPutFields.put("ancestorChecking", this.ancestorChecking);
        putFieldPutFields.put("xmlVersionChanged", this.xmlVersionChanged);
        putFieldPutFields.put("documentNumber", this.documentNumber);
        putFieldPutFields.put("nodeCounter", this.nodeCounter);
        putFieldPutFields.put("nodeTable", hashtable3);
        putFieldPutFields.put("xml11Version", this.xml11Version);
        objectOutputStream.writeFields();
    }

    public void abort() {
    }

    public void addEventListener(NodeImpl nodeImpl, String str, EventListener eventListener, boolean z) {
    }

    @Override // org.w3c.dom.Document
    public Node adoptNode(Node node) {
        Map<String, ParentNode.UserDataRecord> userDataRecord;
        Node namedItem;
        try {
            NodeImpl nodeImpl = (NodeImpl) node;
            if (node == null) {
                return null;
            }
            if (node.getOwnerDocument() != null) {
                DOMImplementation implementation = getImplementation();
                DOMImplementation implementation2 = node.getOwnerDocument().getImplementation();
                if (implementation != implementation2) {
                    if ((implementation instanceof DOMImplementationImpl) && (implementation2 instanceof DeferredDOMImplementationImpl)) {
                        undeferChildren(nodeImpl);
                    } else if (!(implementation instanceof DeferredDOMImplementationImpl) || !(implementation2 instanceof DOMImplementationImpl)) {
                        return null;
                    }
                } else if (implementation2 instanceof DeferredDOMImplementationImpl) {
                    undeferChildren(nodeImpl);
                }
            }
            short nodeType = nodeImpl.getNodeType();
            if (nodeType == 1) {
                userDataRecord = nodeImpl.getUserDataRecord();
                Node parentNode = nodeImpl.getParentNode();
                if (parentNode != null) {
                    parentNode.removeChild(node);
                }
                nodeImpl.setOwnerDocument(this);
                if (userDataRecord != null) {
                    setUserDataTable(nodeImpl, userDataRecord);
                }
                ((ElementImpl) nodeImpl).reconcileDefaultAttributes();
            } else if (nodeType == 2) {
                AttrImpl attrImpl = (AttrImpl) nodeImpl;
                if (attrImpl.getOwnerElement() != null) {
                    attrImpl.getOwnerElement().removeAttributeNode(attrImpl);
                }
                attrImpl.isSpecified(true);
                Map<String, ParentNode.UserDataRecord> userDataRecord2 = nodeImpl.getUserDataRecord();
                attrImpl.setOwnerDocument(this);
                if (userDataRecord2 != null) {
                    setUserDataTable(nodeImpl, userDataRecord2);
                }
                userDataRecord = userDataRecord2;
            } else {
                if (nodeType != 5) {
                    if (nodeType != 6) {
                        if (nodeType == 9 || nodeType == 10) {
                            zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
                            return null;
                        }
                        if (nodeType != 12) {
                            userDataRecord = nodeImpl.getUserDataRecord();
                            Node parentNode2 = nodeImpl.getParentNode();
                            if (parentNode2 != null) {
                                parentNode2.removeChild(node);
                            }
                            nodeImpl.setOwnerDocument(this);
                            if (userDataRecord != null) {
                                setUserDataTable(nodeImpl, userDataRecord);
                            }
                        }
                    }
                    zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                    return null;
                }
                userDataRecord = nodeImpl.getUserDataRecord();
                Node parentNode3 = nodeImpl.getParentNode();
                if (parentNode3 != null) {
                    parentNode3.removeChild(node);
                }
                while (true) {
                    Node firstChild = nodeImpl.getFirstChild();
                    if (firstChild == null) {
                        break;
                    }
                    nodeImpl.removeChild(firstChild);
                }
                nodeImpl.setOwnerDocument(this);
                if (userDataRecord != null) {
                    setUserDataTable(nodeImpl, userDataRecord);
                }
                DocumentTypeImpl documentTypeImpl = this.docType;
                if (documentTypeImpl != null && (namedItem = documentTypeImpl.getEntities().getNamedItem(nodeImpl.getNodeName())) != null) {
                    for (Node firstChild2 = namedItem.getFirstChild(); firstChild2 != null; firstChild2 = firstChild2.getNextSibling()) {
                        nodeImpl.appendChild(firstChild2.cloneNode(true));
                    }
                }
            }
            if (userDataRecord != null) {
                callUserDataHandlers(node, null, (short) 5, userDataRecord);
            }
            return nodeImpl;
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public void callUserDataHandlers(Node node, Node node2, short s) {
        Map<String, ParentNode.UserDataRecord> userDataRecord;
        if (this.nodeUserData == null || !(node instanceof NodeImpl) || (userDataRecord = ((NodeImpl) node).getUserDataRecord()) == null || userDataRecord.isEmpty()) {
            return;
        }
        callUserDataHandlers(node, node2, s, userDataRecord);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl
    public void changed() {
        this.changes++;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl
    public int changes() {
        return this.changes;
    }

    public final void checkDOMNSErr(String str, String str2) {
        if (this.errorChecking) {
            if (str2 == null) {
                zi0.a(14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
                return;
            }
            if (str.equals("xml") && !str2.equals(NamespaceContext.XML_URI)) {
                zi0.a(14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
            } else {
                if ((!str.equals("xmlns") || str2.equals(NamespaceContext.XMLNS_URI)) && (str.equals("xmlns") || !str2.equals(NamespaceContext.XMLNS_URI))) {
                    return;
                }
                zi0.a(14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
            }
        }
    }

    public final void checkNamespaceWF(String str, int i, int i2) {
        if (this.errorChecking) {
            if (i == 0 || i == str.length() - 1 || i2 != i) {
                zi0.a(14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
            }
        }
    }

    public final void checkQName(String str, String str2) {
        if (this.errorChecking) {
            if (this.xml11Version) {
                if ((str == null || XML11Char.isXML11ValidNCName(str)) && XML11Char.isXML11ValidNCName(str2)) {
                    return;
                }
            } else if ((str == null || XMLChar.isValidNCName(str)) && XMLChar.isValidNCName(str2)) {
                return;
            }
            zi0.a(5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
        }
    }

    public final void clearIdentifiers() {
        Map<String, Node> map = this.identifiers;
        if (map != null) {
            map.clear();
        }
    }

    public Object clone() throws CloneNotSupportedException {
        CoreDocumentImpl coreDocumentImpl = (CoreDocumentImpl) super.clone();
        coreDocumentImpl.docType = null;
        coreDocumentImpl.docElement = null;
        return coreDocumentImpl;
    }

    public void cloneNode(CoreDocumentImpl coreDocumentImpl, boolean z) {
        Map<Node, String> map;
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        if (z) {
            if (this.identifiers != null) {
                map = new HashMap<>(this.identifiers.size());
                for (String str : this.identifiers.keySet()) {
                    map.put(this.identifiers.get(str), str);
                }
            } else {
                map = null;
            }
            for (ChildNode childNode = this.firstChild; childNode != null; childNode = childNode.nextSibling) {
                coreDocumentImpl.appendChild(coreDocumentImpl.importNode(childNode, true, true, map));
            }
        }
        coreDocumentImpl.allowGrammarAccess = this.allowGrammarAccess;
        coreDocumentImpl.errorChecking = this.errorChecking;
    }

    public void copyEventListeners(NodeImpl nodeImpl, NodeImpl nodeImpl2) {
    }

    @Override // org.w3c.dom.Document
    public Attr createAttribute(String str) throws DOMException {
        if (!this.errorChecking || isXMLName(str, this.xml11Version)) {
            return new AttrImpl(this, str);
        }
        zi0.a(5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
        return null;
    }

    @Override // org.w3c.dom.Document
    public Attr createAttributeNS(String str, String str2) throws DOMException {
        return new AttrNSImpl(this, str, str2);
    }

    @Override // org.w3c.dom.Document
    public CDATASection createCDATASection(String str) throws DOMException {
        return new CDATASectionImpl(this, str);
    }

    @Override // org.w3c.dom.Document
    public Comment createComment(String str) {
        return new CommentImpl(this, str);
    }

    @Override // org.w3c.dom.Document
    public DocumentFragment createDocumentFragment() {
        return new DocumentFragmentImpl(this);
    }

    public DocumentType createDocumentType(String str, String str2, String str3) throws DOMException {
        return new DocumentTypeImpl(this, str, str2, str3);
    }

    @Override // org.w3c.dom.Document
    public Element createElement(String str) throws DOMException {
        if (!this.errorChecking || isXMLName(str, this.xml11Version)) {
            return new ElementImpl(this, str);
        }
        zi0.a(5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
        return null;
    }

    public ElementDefinitionImpl createElementDefinition(String str) throws DOMException {
        if (!this.errorChecking || isXMLName(str, this.xml11Version)) {
            return new ElementDefinitionImpl(this, str);
        }
        zi0.a(5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
        return null;
    }

    @Override // org.w3c.dom.Document
    public Element createElementNS(String str, String str2) throws DOMException {
        return new ElementNSImpl(this, str, str2);
    }

    public Entity createEntity(String str) throws DOMException {
        if (!this.errorChecking || isXMLName(str, this.xml11Version)) {
            return new EntityImpl(this, str);
        }
        zi0.a(5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
        return null;
    }

    @Override // org.w3c.dom.Document
    public EntityReference createEntityReference(String str) throws DOMException {
        if (!this.errorChecking || isXMLName(str, this.xml11Version)) {
            return new EntityReferenceImpl(this, str);
        }
        zi0.a(5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
        return null;
    }

    public Notation createNotation(String str) throws DOMException {
        if (!this.errorChecking || isXMLName(str, this.xml11Version)) {
            return new NotationImpl(this, str);
        }
        zi0.a(5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
        return null;
    }

    @Override // org.w3c.dom.Document
    public ProcessingInstruction createProcessingInstruction(String str, String str2) throws DOMException {
        if (!this.errorChecking || isXMLName(str, this.xml11Version)) {
            return new ProcessingInstructionImpl(this, str, str2);
        }
        zi0.a(5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
        return null;
    }

    @Override // org.w3c.dom.Document
    public Text createTextNode(String str) {
        return new TextImpl(this, str);
    }

    public void deletedText(NodeImpl nodeImpl, int i, int i2) {
    }

    public boolean dispatchEvent(NodeImpl nodeImpl, Event event) {
        return false;
    }

    public void freeNodeListCache(NodeListCache nodeListCache) {
        nodeListCache.next = this.fFreeNLCache;
        this.fFreeNLCache = nodeListCache;
    }

    public boolean getAsync() {
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getBaseURI() {
        String str = this.fDocumentURI;
        if (str == null || str.length() == 0) {
            return this.fDocumentURI;
        }
        try {
            return new URI(this.fDocumentURI).toString();
        } catch (URI.MalformedURIException unused) {
            return null;
        }
    }

    @Override // org.w3c.dom.Document
    public DocumentType getDoctype() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return this.docType;
    }

    @Override // org.w3c.dom.Document
    public Element getDocumentElement() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return this.docElement;
    }

    @Override // org.w3c.dom.Document
    public String getDocumentURI() {
        return this.fDocumentURI;
    }

    @Override // org.w3c.dom.Document
    public DOMConfiguration getDomConfig() {
        if (this.fConfiguration == null) {
            this.fConfiguration = new DOMConfigurationImpl();
        }
        return this.fConfiguration;
    }

    @Override // org.w3c.dom.Document
    public Element getElementById(String str) {
        return getIdentifier(str);
    }

    @Override // org.w3c.dom.Document
    public NodeList getElementsByTagName(String str) {
        return new DeepNodeListImpl(this, str);
    }

    @Override // org.w3c.dom.Document
    public NodeList getElementsByTagNameNS(String str, String str2) {
        return new DeepNodeListImpl(this, str, str2);
    }

    @Deprecated
    public String getEncoding() {
        return getXmlEncoding();
    }

    public boolean getErrorChecking() {
        return this.errorChecking;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public Object getFeature(String str, String str2) {
        return super.getFeature(str, str2);
    }

    public Element getIdentifier(String str) {
        Element element;
        if (needsSyncData()) {
            synchronizeData();
        }
        Map<String, Node> map = this.identifiers;
        if (map != null && (element = (Element) map.get(str)) != null) {
            for (Node parentNode = element.getParentNode(); parentNode != null; parentNode = parentNode.getParentNode()) {
                if (parentNode == this) {
                    return element;
                }
            }
        }
        return null;
    }

    @Override // org.w3c.dom.Document
    public DOMImplementation getImplementation() {
        return CoreDOMImplementationImpl.getDOMImplementation();
    }

    @Override // org.w3c.dom.Document
    public String getInputEncoding() {
        return this.actualEncoding;
    }

    public boolean getMutationEvents() {
        return false;
    }

    public NodeListCache getNodeListCache(ParentNode parentNode) {
        NodeListCache nodeListCache = this.fFreeNLCache;
        if (nodeListCache == null) {
            return new NodeListCache(parentNode);
        }
        this.fFreeNLCache = nodeListCache.next;
        nodeListCache.fChild = null;
        nodeListCache.fChildIndex = -1;
        nodeListCache.fLength = -1;
        ParentNode parentNode2 = nodeListCache.fOwner;
        if (parentNode2 != null) {
            parentNode2.fNodeListCache = null;
        }
        nodeListCache.fOwner = parentNode;
        return nodeListCache;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        return "#document";
    }

    public int getNodeNumber(Node node) {
        Map<Node, Integer> map = this.nodeTable;
        if (map == null) {
            HashMap map2 = new HashMap();
            this.nodeTable = map2;
            int i = this.nodeCounter - 1;
            this.nodeCounter = i;
            map2.put(node, Integer.valueOf(i));
            return i;
        }
        Integer num = map.get(node);
        if (num != null) {
            return num.intValue();
        }
        int i2 = this.nodeCounter - 1;
        this.nodeCounter = i2;
        this.nodeTable.put(node, Integer.valueOf(i2));
        return i2;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return (short) 9;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public final Document getOwnerDocument() {
        return null;
    }

    @Deprecated
    public boolean getStandalone() {
        return getXmlStandalone();
    }

    @Override // org.w3c.dom.Document
    public boolean getStrictErrorChecking() {
        return this.errorChecking;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getTextContent() throws DOMException {
        return null;
    }

    public Object getUserData(Node node, String str) {
        Map<String, ParentNode.UserDataRecord> map;
        ParentNode.UserDataRecord userDataRecord;
        Map<Node, Map<String, ParentNode.UserDataRecord>> map2 = this.nodeUserData;
        if (map2 == null || (map = map2.get(node)) == null || (userDataRecord = map.get(str)) == null) {
            return null;
        }
        return userDataRecord.fData;
    }

    public Map<String, ParentNode.UserDataRecord> getUserDataRecord(Node node) {
        Map<String, ParentNode.UserDataRecord> map;
        Map<Node, Map<String, ParentNode.UserDataRecord>> map2 = this.nodeUserData;
        if (map2 == null || (map = map2.get(node)) == null) {
            return null;
        }
        return map;
    }

    @Deprecated
    public String getVersion() {
        return getXmlVersion();
    }

    @Override // org.w3c.dom.Document
    public String getXmlEncoding() {
        return this.encoding;
    }

    @Override // org.w3c.dom.Document
    public boolean getXmlStandalone() {
        return this.standalone;
    }

    @Override // org.w3c.dom.Document
    public String getXmlVersion() {
        String str = this.version;
        return str == null ? "1.0" : str;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public Node insertBefore(Node node, Node node2) throws DOMException {
        short nodeType = node.getNodeType();
        if (this.errorChecking && ((nodeType == 1 && this.docElement != null) || (nodeType == 10 && this.docType != null))) {
            zi0.a(3, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
            return null;
        }
        if (node.getOwnerDocument() == null && (node instanceof DocumentTypeImpl)) {
            ((DocumentTypeImpl) node).ownerDocument = this;
        }
        super.insertBefore(node, node2);
        if (nodeType == 1) {
            this.docElement = (ElementImpl) node;
            return node;
        }
        if (nodeType == 10) {
            this.docType = (DocumentTypeImpl) node;
        }
        return node;
    }

    public void insertedNode(NodeImpl nodeImpl, NodeImpl nodeImpl2, boolean z) {
    }

    public void insertedText(NodeImpl nodeImpl, int i, int i2) {
    }

    public void insertingNode(NodeImpl nodeImpl, boolean z) {
    }

    public boolean isKidOK(Node node, Node node2) {
        if (this.allowGrammarAccess && node.getNodeType() == 10) {
            return node2.getNodeType() == 1;
        }
        return (kidOK[node.getNodeType()] & (1 << node2.getNodeType())) != 0;
    }

    public boolean isNormalizeDocRequired() {
        return true;
    }

    public boolean isXML11Version() {
        return this.xml11Version;
    }

    public boolean isXMLVersionChanged() {
        return this.xmlVersionChanged;
    }

    public boolean load(String str) {
        return false;
    }

    public boolean loadXML(String str) {
        return false;
    }

    public void modifiedAttrValue(AttrImpl attrImpl, String str) {
    }

    public void modifiedCharacterData(NodeImpl nodeImpl, String str, String str2, boolean z) {
    }

    public void modifyingCharacterData(NodeImpl nodeImpl, boolean z) {
    }

    @Override // org.w3c.dom.Document
    public void normalizeDocument() {
        if (!isNormalized() || isNormalizeDocRequired()) {
            if (needsSyncChildren()) {
                synchronizeChildren();
            }
            if (this.domNormalizer == null) {
                this.domNormalizer = new DOMNormalizer();
            }
            DOMConfigurationImpl dOMConfigurationImpl = this.fConfiguration;
            if (dOMConfigurationImpl == null) {
                this.fConfiguration = new DOMConfigurationImpl();
            } else {
                dOMConfigurationImpl.reset();
            }
            this.domNormalizer.normalizeDocument(this, this.fConfiguration);
            isNormalized(true);
            this.xmlVersionChanged = false;
        }
    }

    public void putIdentifier(String str, Element element) {
        if (element == null) {
            removeIdentifier(str);
            return;
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.identifiers == null) {
            this.identifiers = new HashMap();
        }
        this.identifiers.put(str, element);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public Node removeChild(Node node) throws DOMException {
        super.removeChild(node);
        short nodeType = node.getNodeType();
        if (nodeType == 1) {
            this.docElement = null;
            return node;
        }
        if (nodeType == 10) {
            this.docType = null;
        }
        return node;
    }

    public void removeEventListener(NodeImpl nodeImpl, String str, EventListener eventListener, boolean z) {
    }

    public void removeIdentifier(String str) {
        if (needsSyncData()) {
            synchronizeData();
        }
        Map<String, Node> map = this.identifiers;
        if (map == null) {
            return;
        }
        map.remove(str);
    }

    public Map<String, ParentNode.UserDataRecord> removeUserDataTable(Node node) {
        Map<Node, Map<String, ParentNode.UserDataRecord>> map = this.nodeUserData;
        if (map == null) {
            return null;
        }
        return map.get(node);
    }

    public void removedAttrNode(AttrImpl attrImpl, NodeImpl nodeImpl, String str) {
    }

    public void removedNode(NodeImpl nodeImpl, boolean z) {
    }

    public void removingNode(NodeImpl nodeImpl, NodeImpl nodeImpl2, boolean z) {
    }

    @Override // org.w3c.dom.Document
    public Node renameNode(Node node, String str, String str2) throws DOMException {
        if (this.errorChecking && node.getOwnerDocument() != this && node != this) {
            zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
            return null;
        }
        short nodeType = node.getNodeType();
        if (nodeType != 1) {
            if (nodeType != 2) {
                zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
                return null;
            }
            AttrImpl attrImpl = (AttrImpl) node;
            Element ownerElement = attrImpl.getOwnerElement();
            if (ownerElement != null) {
                ownerElement.removeAttributeNode(attrImpl);
            }
            if (node instanceof AttrNSImpl) {
                ((AttrNSImpl) attrImpl).rename(str, str2);
                if (ownerElement != null) {
                    ownerElement.setAttributeNodeNS(attrImpl);
                }
                callUserDataHandlers(attrImpl, null, (short) 4);
            } else if (str == null) {
                attrImpl.rename(str2);
                if (ownerElement != null) {
                    ownerElement.setAttributeNode(attrImpl);
                }
                callUserDataHandlers(attrImpl, null, (short) 4);
            } else {
                AttrNSImpl attrNSImpl = new AttrNSImpl(this, str, str2);
                copyEventListeners(attrImpl, attrNSImpl);
                Map<String, ParentNode.UserDataRecord> mapRemoveUserDataTable = removeUserDataTable(attrImpl);
                for (Node firstChild = attrImpl.getFirstChild(); firstChild != null; firstChild = attrImpl.getFirstChild()) {
                    attrImpl.removeChild(firstChild);
                    attrNSImpl.appendChild(firstChild);
                }
                setUserDataTable(attrNSImpl, mapRemoveUserDataTable);
                callUserDataHandlers(attrImpl, attrNSImpl, (short) 4);
                if (ownerElement != null) {
                    ownerElement.setAttributeNode(attrNSImpl);
                }
                attrImpl = attrNSImpl;
            }
            renamedAttrNode((Attr) node, attrImpl);
            return attrImpl;
        }
        ElementImpl elementImpl = (ElementImpl) node;
        if (elementImpl instanceof ElementNSImpl) {
            ((ElementNSImpl) elementImpl).rename(str, str2);
            callUserDataHandlers(elementImpl, null, (short) 4);
        } else if (str == null) {
            if (this.errorChecking) {
                if (str2.indexOf(58) != -1) {
                    zi0.a(14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
                    return null;
                }
                if (!isXMLName(str2, this.xml11Version)) {
                    zi0.a(5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
                    return null;
                }
            }
            elementImpl.rename(str2);
            callUserDataHandlers(elementImpl, null, (short) 4);
        } else {
            ElementNSImpl elementNSImpl = new ElementNSImpl(this, str, str2);
            copyEventListeners(elementImpl, elementNSImpl);
            Map<String, ParentNode.UserDataRecord> mapRemoveUserDataTable2 = removeUserDataTable(elementImpl);
            Node parentNode = elementImpl.getParentNode();
            Node nextSibling = elementImpl.getNextSibling();
            if (parentNode != null) {
                parentNode.removeChild(elementImpl);
            }
            for (Node firstChild2 = elementImpl.getFirstChild(); firstChild2 != null; firstChild2 = elementImpl.getFirstChild()) {
                elementImpl.removeChild(firstChild2);
                elementNSImpl.appendChild(firstChild2);
            }
            elementNSImpl.moveSpecifiedAttributes(elementImpl);
            setUserDataTable(elementNSImpl, mapRemoveUserDataTable2);
            callUserDataHandlers(elementImpl, elementNSImpl, (short) 4);
            if (parentNode != null) {
                parentNode.insertBefore(elementNSImpl, nextSibling);
            }
            elementImpl = elementNSImpl;
        }
        renamedElement((Element) node, elementImpl);
        return elementImpl;
    }

    public void renamedAttrNode(Attr attr, Attr attr2) {
    }

    public void renamedElement(Element element, Element element2) {
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public Node replaceChild(Node node, Node node2) throws DOMException {
        if (node.getOwnerDocument() == null && (node instanceof DocumentTypeImpl)) {
            ((DocumentTypeImpl) node).ownerDocument = this;
        }
        if (this.errorChecking && ((this.docType != null && node2.getNodeType() != 10 && node.getNodeType() == 10) || (this.docElement != null && node2.getNodeType() != 1 && node.getNodeType() == 1))) {
            zi0.a(3, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
            return null;
        }
        super.replaceChild(node, node2);
        short nodeType = node2.getNodeType();
        if (nodeType == 1) {
            this.docElement = (ElementImpl) node;
            return node2;
        }
        if (nodeType == 10) {
            this.docType = (DocumentTypeImpl) node;
        }
        return node2;
    }

    public void replacedCharacterData(NodeImpl nodeImpl, String str, String str2) {
    }

    public void replacedNode(NodeImpl nodeImpl) {
    }

    public void replacedText(NodeImpl nodeImpl) {
    }

    public void replacingData(NodeImpl nodeImpl) {
    }

    public void replacingNode(NodeImpl nodeImpl) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [org.w3c.dom.ls.LSSerializer] */
    /* JADX WARN: Type inference failed for: r1v1, types: [org.w3c.dom.Node] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v6 */
    public String saveXML(Node node) throws DOMException {
        if (this.errorChecking && node != null && this != node.getOwnerDocument()) {
            zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
            return null;
        }
        ?? CreateLSSerializer = ((DOMImplementationLS) DOMImplementationImpl.getDOMImplementation()).createLSSerializer();
        ?? r1 = this;
        if (node != null) {
            r1 = node;
        }
        return CreateLSSerializer.writeToString(r1);
    }

    public void setAsync(boolean z) {
        if (z) {
            zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
        }
    }

    public void setAttrNode(AttrImpl attrImpl, AttrImpl attrImpl2) {
    }

    @Override // org.w3c.dom.Document
    public void setDocumentURI(String str) {
        this.fDocumentURI = str;
    }

    @Deprecated
    public void setEncoding(String str) {
        setXmlEncoding(str);
    }

    public void setErrorChecking(boolean z) {
        this.errorChecking = z;
    }

    public void setInputEncoding(String str) {
        this.actualEncoding = str;
    }

    public void setMutationEvents(boolean z) {
    }

    @Deprecated
    public void setStandalone(boolean z) {
        setXmlStandalone(z);
    }

    @Override // org.w3c.dom.Document
    public void setStrictErrorChecking(boolean z) {
        this.errorChecking = z;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public void setTextContent(String str) throws DOMException {
    }

    public Object setUserData(Node node, String str, Object obj, UserDataHandler userDataHandler) {
        Map<String, ParentNode.UserDataRecord> map;
        Map<String, ParentNode.UserDataRecord> map2;
        ParentNode.UserDataRecord userDataRecordRemove;
        Map<Node, Map<String, ParentNode.UserDataRecord>> map3 = this.nodeUserData;
        if (obj == null) {
            if (map3 == null || (map2 = map3.get(node)) == null || (userDataRecordRemove = map2.remove(str)) == null) {
                return null;
            }
            return userDataRecordRemove.fData;
        }
        if (map3 == null) {
            this.nodeUserData = new HashMap();
            map = new HashMap<>();
            this.nodeUserData.put(node, map);
        } else {
            map = map3.get(node);
            if (map == null) {
                map = new HashMap<>();
                this.nodeUserData.put(node, map);
            }
        }
        ParentNode.UserDataRecord userDataRecordPut = map.put(str, new ParentNode.UserDataRecord(obj, userDataHandler));
        if (userDataRecordPut != null) {
            return userDataRecordPut.fData;
        }
        return null;
    }

    public void setUserDataTable(Node node, Map<String, ParentNode.UserDataRecord> map) {
        if (this.nodeUserData == null) {
            this.nodeUserData = new HashMap();
        }
        if (map != null) {
            this.nodeUserData.put(node, map);
        }
    }

    @Deprecated
    public void setVersion(String str) {
        setXmlVersion(str);
    }

    public void setXmlEncoding(String str) {
        this.encoding = str;
    }

    @Override // org.w3c.dom.Document
    public void setXmlStandalone(boolean z) throws DOMException {
        this.standalone = z;
    }

    @Override // org.w3c.dom.Document
    public void setXmlVersion(String str) {
        if (str == null) {
            return;
        }
        if (!str.equals("1.0") && !str.equals(SerializerConstants.XMLVERSION11)) {
            zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
            return;
        }
        if (!getXmlVersion().equals(str)) {
            this.xmlVersionChanged = true;
            isNormalized(false);
            this.version = str;
        }
        if (getXmlVersion().equals(SerializerConstants.XMLVERSION11)) {
            this.xml11Version = true;
        } else {
            this.xml11Version = false;
        }
    }

    public void undeferChildren(Node node) {
        Node parentNode = node;
        while (parentNode != null) {
            NodeImpl nodeImpl = (NodeImpl) parentNode;
            if (nodeImpl.needsSyncData()) {
                nodeImpl.synchronizeData();
            }
            NamedNodeMap attributes = parentNode.getAttributes();
            if (attributes != null) {
                int length = attributes.getLength();
                for (int i = 0; i < length; i++) {
                    undeferChildren(attributes.item(i));
                }
            }
            Node firstChild = parentNode.getFirstChild();
            while (true) {
                if (firstChild != null || node.equals(parentNode)) {
                    parentNode = firstChild;
                } else {
                    firstChild = parentNode.getNextSibling();
                    if (firstChild == null && ((parentNode = parentNode.getParentNode()) == null || node.equals(parentNode))) {
                        parentNode = null;
                    }
                }
            }
        }
    }

    public Attr createAttributeNS(String str, String str2, String str3) throws DOMException {
        return new AttrNSImpl(this, str, str2, str3);
    }

    public Element createElementNS(String str, String str2, String str3) throws DOMException {
        return new ElementNSImpl(this, str, str2, str3);
    }

    public Object getUserData(NodeImpl nodeImpl) {
        return getUserData(nodeImpl, "XERCES1DOMUSERDATA");
    }

    public void callUserDataHandlers(final Node node, final Node node2, final short s, final Map<String, ParentNode.UserDataRecord> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        map.keySet().stream().forEach(new Consumer() { // from class: jy2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CoreDocumentImpl.b(map, s, node, node2, (String) obj);
            }
        });
    }

    public CoreDocumentImpl() {
        this(false);
    }

    public CoreDocumentImpl(DocumentType documentType) {
        this(documentType, false);
    }

    public CoreDocumentImpl(DocumentType documentType, boolean z) {
        this(z);
        if (documentType != null) {
            try {
                ((DocumentTypeImpl) documentType).ownerDocument = this;
                appendChild(documentType);
            } catch (ClassCastException unused) {
                zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                throw null;
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl
    public int getNodeNumber() {
        if (this.documentNumber == 0) {
            this.documentNumber = ((CoreDOMImplementationImpl) CoreDOMImplementationImpl.getDOMImplementation()).assignDocumentNumber();
        }
        return this.documentNumber;
    }

    public void setUserData(NodeImpl nodeImpl, Object obj) {
        setUserData(nodeImpl, "XERCES1DOMUSERDATA", obj, null);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.ChildNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public Node cloneNode(boolean z) {
        CoreDocumentImpl coreDocumentImpl = new CoreDocumentImpl();
        callUserDataHandlers(this, coreDocumentImpl, (short) 1);
        cloneNode(coreDocumentImpl, z);
        return coreDocumentImpl;
    }

    @Override // org.w3c.dom.Document
    public Node importNode(Node node, boolean z) throws DOMException {
        return importNode(node, z, false, null);
    }
}
