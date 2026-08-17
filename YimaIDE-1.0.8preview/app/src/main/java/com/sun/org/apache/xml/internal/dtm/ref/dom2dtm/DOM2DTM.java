package com.sun.org.apache.xml.internal.dtm.ref.dom2dtm;

import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xml.internal.dtm.DTMWSFilter;
import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators;
import com.sun.org.apache.xml.internal.dtm.ref.DTMManagerDefault;
import com.sun.org.apache.xml.internal.dtm.ref.ExpandedNameTable;
import com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource;
import com.sun.org.apache.xml.internal.res.XMLMessages;
import com.sun.org.apache.xml.internal.utils.FastStringBuffer;
import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xml.internal.utils.StringBufferPool;
import com.sun.org.apache.xml.internal.utils.SuballocatedIntVector;
import com.sun.org.apache.xml.internal.utils.TreeWalker;
import com.sun.org.apache.xml.internal.utils.XMLCharacterRecognizer;
import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xml.internal.utils.XMLStringFactory;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOM2DTM extends DTMDefaultBaseIterators {
    static final boolean JJK_DEBUG = false;
    static final boolean JJK_NEWCODE = true;
    static final String NAMESPACE_DECL_NS = "http://www.w3.org/XML/1998/namespace";
    private int m_last_kid;
    private int m_last_parent;
    protected List<Node> m_nodes;
    private transient boolean m_nodesAreProcessed;
    private transient Node m_pos;
    boolean m_processedFirstElement;
    private transient Node m_root;
    TreeWalker m_walker;

    public interface CharacterNodeHandler {
        void characters(Node node) throws SAXException;
    }

    public DOM2DTM(DTMManager dTMManager, DOMSource dOMSource, int i, DTMWSFilter dTMWSFilter, XMLStringFactory xMLStringFactory, boolean z) {
        super(dTMManager, dOMSource, i, dTMWSFilter, xMLStringFactory, z);
        this.m_last_parent = 0;
        this.m_last_kid = -1;
        this.m_processedFirstElement = false;
        this.m_nodes = new ArrayList();
        this.m_walker = new TreeWalker(null);
        Node node = dOMSource.getNode();
        this.m_root = node;
        this.m_pos = node;
        this.m_last_kid = -1;
        this.m_last_parent = -1;
        this.m_last_kid = addNode(node, -1, -1, -1);
        if (1 == this.m_root.getNodeType()) {
            NamedNodeMap attributes = this.m_root.getAttributes();
            int length = attributes == null ? 0 : attributes.getLength();
            if (length > 0) {
                int iAddNode = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    iAddNode = addNode(attributes.item(i2), 0, iAddNode, -1);
                    this.m_firstch.setElementAt(-1, iAddNode);
                }
                this.m_nextsib.setElementAt(-1, iAddNode);
            }
        }
        this.m_nodesAreProcessed = false;
    }

    public static void dispatchNodeData(Node node, ContentHandler contentHandler, int i) throws SAXException {
        short nodeType = node.getNodeType();
        if (nodeType != 1) {
            if (nodeType != 2 && nodeType != 3 && nodeType != 4) {
                if (nodeType == 7 || nodeType == 8) {
                    if (i != 0) {
                        return;
                    }
                } else if (nodeType != 9 && nodeType != 11) {
                    return;
                }
            }
            String nodeValue = node.getNodeValue();
            if (contentHandler instanceof CharacterNodeHandler) {
                ((CharacterNodeHandler) contentHandler).characters(node);
                return;
            } else {
                contentHandler.characters(nodeValue.toCharArray(), 0, nodeValue.length());
                return;
            }
        }
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            dispatchNodeData(firstChild, contentHandler, i + 1);
        }
    }

    private int getHandleFromNode(Node node) {
        if (node == null) {
            return -1;
        }
        int size = this.m_nodes.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                boolean zNextNode = nextNode();
                int size2 = this.m_nodes.size();
                if (!zNextNode && i >= size2) {
                    return -1;
                }
                size = size2;
            } else {
                if (this.m_nodes.get(i) == node) {
                    return makeNodeHandle(i);
                }
                i++;
            }
        }
    }

    public static void getNodeData(Node node, FastStringBuffer fastStringBuffer) {
        short nodeType = node.getNodeType();
        if (nodeType != 1) {
            if (nodeType == 2 || nodeType == 3 || nodeType == 4) {
                fastStringBuffer.append(node.getNodeValue());
                return;
            } else if (nodeType != 9 && nodeType != 11) {
                return;
            }
        }
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            getNodeData(firstChild, fastStringBuffer);
        }
    }

    private static boolean isSpace(char c) {
        return XMLCharacterRecognizer.isWhiteSpace(c);
    }

    private Node logicalNextDOMTextNode(Node node) {
        short nodeType;
        Node nextSibling = node.getNextSibling();
        if (nextSibling == null) {
            for (Node parentNode = node.getParentNode(); parentNode != null && 5 == parentNode.getNodeType(); parentNode = parentNode.getParentNode()) {
                nextSibling = parentNode.getNextSibling();
                if (nextSibling != null) {
                    break;
                }
            }
        }
        while (nextSibling != null && 5 == nextSibling.getNodeType()) {
            nextSibling = nextSibling.hasChildNodes() ? nextSibling.getFirstChild() : nextSibling.getNextSibling();
        }
        if (nextSibling == null || 3 == (nodeType = nextSibling.getNodeType()) || 4 == nodeType) {
            return nextSibling;
        }
        return null;
    }

    public int addNode(Node node, int i, int i2, int i3) {
        int size = this.m_nodes.size();
        if (this.m_dtmIdent.size() == (size >>> 16)) {
            try {
                DTMManager dTMManager = this.m_mgr;
                if (dTMManager == null) {
                    throw new ClassCastException();
                }
                DTMManagerDefault dTMManagerDefault = (DTMManagerDefault) dTMManager;
                int firstFreeDTMID = dTMManagerDefault.getFirstFreeDTMID();
                dTMManagerDefault.addDTM(this, firstFreeDTMID, size);
                this.m_dtmIdent.addElement(firstFreeDTMID << 16);
            } catch (ClassCastException unused) {
                error(XMLMessages.createXMLMessage("ER_NO_DTMIDS_AVAIL", null));
            }
        }
        this.m_size++;
        if (-1 == i3) {
            i3 = node.getNodeType();
        }
        if (2 == i3) {
            String nodeName = node.getNodeName();
            if (nodeName.startsWith("xmlns:") || nodeName.equals("xmlns")) {
                i3 = 13;
            }
        }
        this.m_nodes.add(node);
        this.m_firstch.setElementAt(-2, size);
        this.m_nextsib.setElementAt(-2, size);
        this.m_prevsib.setElementAt(i2, size);
        this.m_parent.setElementAt(i, size);
        if (-1 != i && i3 != 2 && i3 != 13 && -2 == this.m_firstch.elementAt(i)) {
            this.m_firstch.setElementAt(size, i);
        }
        String namespaceURI = node.getNamespaceURI();
        String nodeName2 = i3 == 7 ? node.getNodeName() : node.getLocalName();
        if ((i3 == 1 || i3 == 2) && nodeName2 == null) {
            nodeName2 = node.getNodeName();
        }
        ExpandedNameTable expandedNameTable = this.m_expandedNameTable;
        node.getLocalName();
        int expandedTypeID = nodeName2 != null ? expandedNameTable.getExpandedTypeID(namespaceURI, nodeName2, i3) : expandedNameTable.getExpandedTypeID(i3);
        this.m_exptype.setElementAt(expandedTypeID, size);
        indexNode(expandedTypeID, size);
        if (-1 != i2) {
            this.m_nextsib.setElementAt(size, i2);
        }
        if (i3 == 13) {
            declareNamespaceInContext(i, size);
        }
        return size;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public void dispatchCharactersEvents(int i, ContentHandler contentHandler, boolean z) throws SAXException {
        if (z) {
            getStringValue(i).fixWhiteSpace(true, true, false).dispatchCharactersEvents(contentHandler);
            return;
        }
        short nodeType = getNodeType(i);
        Node node = getNode(i);
        dispatchNodeData(node, contentHandler, 0);
        if (3 != nodeType && 4 != nodeType) {
            return;
        }
        while (true) {
            node = logicalNextDOMTextNode(node);
            if (node == null) {
                return;
            } else {
                dispatchNodeData(node, contentHandler, 0);
            }
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public void dispatchToEvents(int i, ContentHandler contentHandler) throws SAXException {
        TreeWalker treeWalker = this.m_walker;
        if (treeWalker.getContentHandler() != null) {
            treeWalker = new TreeWalker(null);
        }
        treeWalker.setContentHandler(contentHandler);
        try {
            treeWalker.traverseFragment(getNode(i));
        } finally {
            treeWalker.setContentHandler(null);
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public int getAttributeNode(int i, String str, String str2) {
        short s_type;
        if (str == null) {
            str = "";
        }
        if (1 == getNodeType(i)) {
            int iMakeNodeIdentity = makeNodeIdentity(i);
            while (true) {
                iMakeNodeIdentity = getNextNodeIdentity(iMakeNodeIdentity);
                if (-1 == iMakeNodeIdentity || !((s_type = _type(iMakeNodeIdentity)) == 2 || s_type == 13)) {
                    break;
                }
                Node nodeLookupNode = lookupNode(iMakeNodeIdentity);
                String namespaceURI = nodeLookupNode.getNamespaceURI();
                if (namespaceURI == null) {
                    namespaceURI = "";
                }
                String localName = nodeLookupNode.getLocalName();
                if (namespaceURI.equals(str) && str2.equals(localName)) {
                    return makeNodeHandle(iMakeNodeIdentity);
                }
            }
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public ContentHandler getContentHandler() {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public DTDHandler getDTDHandler() {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public DeclHandler getDeclHandler() {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentTypeDeclarationPublicIdentifier() {
        DocumentType doctype;
        short nodeType = this.m_root.getNodeType();
        Node node = this.m_root;
        Document ownerDocument = nodeType == 9 ? (Document) node : node.getOwnerDocument();
        if (ownerDocument == null || (doctype = ownerDocument.getDoctype()) == null) {
            return null;
        }
        return doctype.getPublicId();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentTypeDeclarationSystemIdentifier() {
        DocumentType doctype;
        short nodeType = this.m_root.getNodeType();
        Node node = this.m_root;
        Document ownerDocument = nodeType == 9 ? (Document) node : node.getOwnerDocument();
        if (ownerDocument == null || (doctype = ownerDocument.getDoctype()) == null) {
            return null;
        }
        return doctype.getSystemId();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public int getElementById(String str) {
        Element elementById;
        short nodeType = this.m_root.getNodeType();
        Node node = this.m_root;
        Document ownerDocument = nodeType == 9 ? (Document) node : node.getOwnerDocument();
        if (ownerDocument == null || (elementById = ownerDocument.getElementById(str)) == null) {
            return -1;
        }
        int handleFromNode = getHandleFromNode(elementById);
        if (-1 == handleFromNode) {
            int size = this.m_nodes.size() - 1;
            do {
                size = getNextNodeIdentity(size);
                if (-1 != size) {
                }
            } while (getNode(size) != elementById);
            return getHandleFromNode(elementById);
        }
        return handleFromNode;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public EntityResolver getEntityResolver() {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public ErrorHandler getErrorHandler() {
        return null;
    }

    public int getHandleOfNode(Node node) {
        if (node == null) {
            return -1;
        }
        Node node2 = this.m_root;
        if (node2 != node && ((node2.getNodeType() != 9 || this.m_root != node.getOwnerDocument()) && (this.m_root.getNodeType() == 9 || this.m_root.getOwnerDocument() != node.getOwnerDocument()))) {
            return -1;
        }
        Node parentNode = node;
        while (parentNode != null) {
            if (parentNode == this.m_root) {
                return getHandleFromNode(node);
            }
            parentNode = parentNode.getNodeType() != 2 ? parentNode.getParentNode() : ((Attr) parentNode).getOwnerElement();
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public LexicalHandler getLexicalHandler() {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getLocalName(int i) {
        int iMakeNodeIdentity = makeNodeIdentity(i);
        if (-1 == iMakeNodeIdentity) {
            return null;
        }
        Node node = this.m_nodes.get(iMakeNodeIdentity);
        String localName = node.getLocalName();
        if (localName != null) {
            return localName;
        }
        String nodeName = node.getNodeName();
        if ('#' == nodeName.charAt(0)) {
            return "";
        }
        int iIndexOf = nodeName.indexOf(58);
        return iIndexOf < 0 ? nodeName : nodeName.substring(iIndexOf + 1);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNamespaceURI(int i) {
        int iMakeNodeIdentity = makeNodeIdentity(i);
        if (iMakeNodeIdentity == -1) {
            return null;
        }
        return this.m_nodes.get(iMakeNodeIdentity).getNamespaceURI();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase
    public int getNextNodeIdentity(int i) {
        int i2 = i + 1;
        if (i2 < this.m_nodes.size() || nextNode()) {
            return i2;
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public Node getNode(int i) {
        return this.m_nodes.get(makeNodeIdentity(i));
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeName(int i) {
        return getNode(i).getNodeName();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeNameX(int i) {
        short nodeType = getNodeType(i);
        if (nodeType == 1 || nodeType == 2 || nodeType == 5 || nodeType == 7) {
            return getNode(i).getNodeName();
        }
        if (nodeType != 13) {
            return "";
        }
        String nodeName = getNode(i).getNodeName();
        if (nodeName.startsWith("xmlns:")) {
            return QName.getLocalPart(nodeName);
        }
        return nodeName.equals("xmlns") ? "" : nodeName;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeValue(int i) {
        short nodeType = -1 != _exptype(makeNodeIdentity(i)) ? getNodeType(i) : (short) -1;
        if (3 != nodeType && 4 != nodeType) {
            return getNode(i).getNodeValue();
        }
        Node node = getNode(i);
        Node nodeLogicalNextDOMTextNode = logicalNextDOMTextNode(node);
        if (nodeLogicalNextDOMTextNode == null) {
            return node.getNodeValue();
        }
        FastStringBuffer fastStringBuffer = StringBufferPool.get();
        fastStringBuffer.append(node.getNodeValue());
        while (nodeLogicalNextDOMTextNode != null) {
            fastStringBuffer.append(nodeLogicalNextDOMTextNode.getNodeValue());
            nodeLogicalNextDOMTextNode = logicalNextDOMTextNode(nodeLogicalNextDOMTextNode);
        }
        String string = fastStringBuffer.length() > 0 ? fastStringBuffer.toString() : "";
        StringBufferPool.free(fastStringBuffer);
        return string;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase
    public int getNumberOfNodes() {
        return this.m_nodes.size();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getPrefix(int i) {
        String nodeName;
        int iIndexOf;
        short nodeType = getNodeType(i);
        if (nodeType != 1 && nodeType != 2) {
            return (nodeType == 13 && (iIndexOf = (nodeName = getNode(i).getNodeName()).indexOf(58)) >= 0) ? nodeName.substring(iIndexOf + 1) : "";
        }
        String nodeName2 = getNode(i).getNodeName();
        int iIndexOf2 = nodeName2.indexOf(58);
        return iIndexOf2 < 0 ? "" : nodeName2.substring(0, iIndexOf2);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public SourceLocator getSourceLocatorFor(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public XMLString getStringValue(int i) {
        short nodeType = getNodeType(i);
        Node node = getNode(i);
        if (1 == nodeType || 9 == nodeType || 11 == nodeType) {
            FastStringBuffer fastStringBuffer = StringBufferPool.get();
            try {
                getNodeData(node, fastStringBuffer);
                return this.m_xstrf.newstr(fastStringBuffer.length() > 0 ? fastStringBuffer.toString() : "");
            } finally {
                StringBufferPool.free(fastStringBuffer);
            }
        }
        if (3 != nodeType && 4 != nodeType) {
            return this.m_xstrf.newstr(node.getNodeValue());
        }
        FastStringBuffer fastStringBuffer2 = StringBufferPool.get();
        while (node != null) {
            fastStringBuffer2.append(node.getNodeValue());
            node = logicalNextDOMTextNode(node);
        }
        String string = fastStringBuffer2.length() > 0 ? fastStringBuffer2.toString() : "";
        StringBufferPool.free(fastStringBuffer2);
        return this.m_xstrf.newstr(string);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getUnparsedEntityURI(String str) {
        DocumentType doctype;
        NamedNodeMap entities;
        Entity entity;
        short nodeType = this.m_root.getNodeType();
        Node node = this.m_root;
        Document ownerDocument = nodeType == 9 ? (Document) node : node.getOwnerDocument();
        if (ownerDocument == null || (doctype = ownerDocument.getDoctype()) == null || (entities = doctype.getEntities()) == null || (entity = (Entity) entities.getNamedItem(str)) == null || entity.getNotationName() == null) {
            return "";
        }
        String systemId = entity.getSystemId();
        return systemId == null ? entity.getPublicId() : systemId;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public boolean isAttributeSpecified(int i) {
        if (2 == getNodeType(i)) {
            return ((Attr) getNode(i)).getSpecified();
        }
        return false;
    }

    public boolean isWhitespace(int i) {
        short nodeType = getNodeType(i);
        Node node = getNode(i);
        if (3 != nodeType && 4 != nodeType) {
            return false;
        }
        FastStringBuffer fastStringBuffer = StringBufferPool.get();
        while (node != null) {
            fastStringBuffer.append(node.getNodeValue());
            node = logicalNextDOMTextNode(node);
        }
        boolean zIsWhitespace = fastStringBuffer.isWhitespace(0, fastStringBuffer.length());
        StringBufferPool.free(fastStringBuffer);
        return zIsWhitespace;
    }

    public Node lookupNode(int i) {
        return this.m_nodes.get(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean needsTwoThreads() {
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase
    public boolean nextNode() {
        boolean zIsWhiteSpace;
        if (this.m_nodesAreProcessed) {
            return false;
        }
        Node parentNode = this.m_pos;
        Node node = null;
        Node nextSibling = null;
        short nodeType = -1;
        do {
            if (parentNode.hasChildNodes()) {
                nextSibling = parentNode.getFirstChild();
                if (nextSibling != null && 10 == nextSibling.getNodeType()) {
                    nextSibling = nextSibling.getNextSibling();
                }
                if (5 != parentNode.getNodeType()) {
                    int i = this.m_last_kid;
                    this.m_last_parent = i;
                    this.m_last_kid = -1;
                    DTMWSFilter dTMWSFilter = this.m_wsfilter;
                    if (dTMWSFilter != null) {
                        short shouldStripSpace = dTMWSFilter.getShouldStripSpace(makeNodeHandle(i), this);
                        pushShouldStripWhitespace(3 == shouldStripSpace ? getShouldStripWhitespace() : 2 == shouldStripSpace);
                    }
                }
            } else {
                int i2 = this.m_last_kid;
                if (i2 != -1 && this.m_firstch.elementAt(i2) == -2) {
                    this.m_firstch.setElementAt(-1, this.m_last_kid);
                }
                while (this.m_last_parent != -1) {
                    nextSibling = parentNode.getNextSibling();
                    if (nextSibling != null && 10 == nextSibling.getNodeType()) {
                        nextSibling = nextSibling.getNextSibling();
                    }
                    if (nextSibling != null) {
                        break;
                    }
                    parentNode = parentNode.getParentNode();
                    if (parentNode == null || 5 != parentNode.getNodeType()) {
                        popShouldStripWhitespace();
                        int i3 = this.m_last_kid;
                        if (i3 == -1) {
                            this.m_firstch.setElementAt(-1, this.m_last_parent);
                        } else {
                            this.m_nextsib.setElementAt(-1, i3);
                        }
                        SuballocatedIntVector suballocatedIntVector = this.m_parent;
                        int i4 = this.m_last_parent;
                        this.m_last_kid = i4;
                        this.m_last_parent = suballocatedIntVector.elementAt(i4);
                    }
                }
                if (this.m_last_parent == -1) {
                    nextSibling = null;
                }
            }
            if (nextSibling != null) {
                nodeType = nextSibling.getNodeType();
            }
            if (5 == nodeType) {
                parentNode = nextSibling;
            }
        } while (5 == nodeType);
        if (nextSibling == null) {
            this.m_nextsib.setElementAt(-1, 0);
            this.m_nodesAreProcessed = true;
            this.m_pos = null;
            return false;
        }
        short nodeType2 = nextSibling.getNodeType();
        if (3 == nodeType2 || 4 == nodeType2) {
            zIsWhiteSpace = this.m_wsfilter != null && getShouldStripWhitespace();
            short s = nodeType2;
            Node node2 = null;
            Node nodeLogicalNextDOMTextNode = nextSibling;
            while (nodeLogicalNextDOMTextNode != null) {
                if (3 == nodeLogicalNextDOMTextNode.getNodeType()) {
                    s = 3;
                }
                zIsWhiteSpace &= XMLCharacterRecognizer.isWhiteSpace(nodeLogicalNextDOMTextNode.getNodeValue());
                node2 = nodeLogicalNextDOMTextNode;
                nodeLogicalNextDOMTextNode = logicalNextDOMTextNode(nodeLogicalNextDOMTextNode);
            }
            node = node2;
            nodeType2 = s;
        } else {
            zIsWhiteSpace = 7 == nodeType2 ? parentNode.getNodeName().toLowerCase().equals("xml") : false;
        }
        if (!zIsWhiteSpace) {
            int iAddNode = addNode(nextSibling, this.m_last_parent, this.m_last_kid, nodeType2);
            this.m_last_kid = iAddNode;
            if (1 == nodeType2) {
                NamedNodeMap attributes = nextSibling.getAttributes();
                int length = attributes == null ? 0 : attributes.getLength();
                int iAddNode2 = -1;
                if (length > 0) {
                    for (int i5 = 0; i5 < length; i5++) {
                        iAddNode2 = addNode(attributes.item(i5), iAddNode, iAddNode2, -1);
                        this.m_firstch.setElementAt(-1, iAddNode2);
                        if (!this.m_processedFirstElement && "xmlns:xml".equals(attributes.item(i5).getNodeName())) {
                            this.m_processedFirstElement = true;
                        }
                    }
                }
                if (!this.m_processedFirstElement) {
                    iAddNode2 = addNode(new DOM2DTMdefaultNamespaceDeclarationNode((Element) nextSibling, "xml", "http://www.w3.org/XML/1998/namespace", makeNodeHandle((iAddNode2 == -1 ? iAddNode : iAddNode2) + 1)), iAddNode, iAddNode2, -1);
                    this.m_firstch.setElementAt(-1, iAddNode2);
                    this.m_processedFirstElement = true;
                }
                if (iAddNode2 != -1) {
                    this.m_nextsib.setElementAt(-1, iAddNode2);
                }
            }
        }
        if (3 == nodeType2 || 4 == nodeType2) {
            nextSibling = node;
        }
        this.m_pos = nextSibling;
        return true;
    }

    public void setIncrementalSAXSource(IncrementalSAXSource incrementalSAXSource) {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void setProperty(String str, Object obj) {
    }
}
