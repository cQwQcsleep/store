package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMDOMException;
import com.sun.org.apache.xpath.internal.NodeSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTMNodeProxy implements Node, Document, Text, Element, Attr, ProcessingInstruction, Comment, DocumentFragment {
    private static final String EMPTYSTRING = "";
    static final DOMImplementation implementation = new DTMNodeProxyImplementation();
    protected String actualEncoding;
    public DTM dtm;
    protected String fDocumentURI;
    int node;
    private String xmlEncoding;
    private boolean xmlStandalone;
    private String xmlVersion;

    public static class DTMNodeProxyImplementation implements DOMImplementation {
        @Override // org.w3c.dom.DOMImplementation
        public Document createDocument(String str, String str2, DocumentType documentType) {
            throw new DTMDOMException((short) 9);
        }

        @Override // org.w3c.dom.DOMImplementation
        public DocumentType createDocumentType(String str, String str2, String str3) {
            throw new DTMDOMException((short) 9);
        }

        @Override // org.w3c.dom.DOMImplementation
        public Object getFeature(String str, String str2) {
            return null;
        }

        @Override // org.w3c.dom.DOMImplementation
        public boolean hasFeature(String str, String str2) {
            if ("CORE".equals(str.toUpperCase()) || "XML".equals(str.toUpperCase())) {
                return "1.0".equals(str2) || "2.0".equals(str2);
            }
            return false;
        }
    }

    public DTMNodeProxy(DTM dtm, int i) {
        this.dtm = dtm;
        this.node = i;
    }

    private final void traverseChildren(List<Node> list, Node node, String str, String str2, boolean z, boolean z2) {
        if (node == null) {
            return;
        }
        if (node.getNodeType() == 1 && (z2 || node.getLocalName().equals(str2))) {
            String namespaceURI = node.getNamespaceURI();
            if ((str == null && namespaceURI == null) || z || (str != null && str.equals(namespaceURI))) {
                list.add(node);
            }
        }
        if (node.hasChildNodes()) {
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                traverseChildren(list, childNodes.item(i), str, str2, z, z2);
            }
        }
    }

    @Override // org.w3c.dom.Document
    public Node adoptNode(Node node) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Node
    public final Node appendChild(Node node) throws DOMException {
        throw new DTMDOMException((short) 7);
    }

    @Override // org.w3c.dom.CharacterData
    public final void appendData(String str) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Node
    public final Node cloneNode(boolean z) {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Node
    public short compareDocumentPosition(Node node) throws DOMException {
        return (short) 0;
    }

    @Override // org.w3c.dom.Document
    public final Attr createAttribute(String str) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Document
    public final Attr createAttributeNS(String str, String str2) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Document
    public final CDATASection createCDATASection(String str) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Document
    public final Comment createComment(String str) {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Document
    public final DocumentFragment createDocumentFragment() {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Document
    public final Element createElement(String str) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Document
    public final Element createElementNS(String str, String str2) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Document
    public final EntityReference createEntityReference(String str) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Document
    public final ProcessingInstruction createProcessingInstruction(String str, String str2) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Document
    public final Text createTextNode(String str) {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.CharacterData
    public final void deleteData(int i, int i2) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    public final boolean equals(Node node) {
        try {
            DTMNodeProxy dTMNodeProxy = (DTMNodeProxy) node;
            return dTMNodeProxy.node == this.node && dTMNodeProxy.dtm == this.dtm;
        } catch (ClassCastException unused) {
        }
    }

    public String getActualEncoding() {
        return this.actualEncoding;
    }

    @Override // org.w3c.dom.Element
    public final String getAttribute(String str) {
        Node namedItem = new DTMNamedNodeMap(this.dtm, this.node).getNamedItem(str);
        return namedItem == null ? "" : namedItem.getNodeValue();
    }

    @Override // org.w3c.dom.Element
    public final String getAttributeNS(String str, String str2) {
        int attributeNode = this.dtm.getAttributeNode(this.node, str, str2);
        Node node = attributeNode != -1 ? this.dtm.getNode(attributeNode) : null;
        return node == null ? "" : node.getNodeValue();
    }

    @Override // org.w3c.dom.Element
    public final Attr getAttributeNode(String str) {
        return (Attr) new DTMNamedNodeMap(this.dtm, this.node).getNamedItem(str);
    }

    @Override // org.w3c.dom.Element
    public final Attr getAttributeNodeNS(String str, String str2) {
        int attributeNode = this.dtm.getAttributeNode(this.node, str, str2);
        if (attributeNode != -1) {
            return (Attr) this.dtm.getNode(attributeNode);
        }
        return null;
    }

    @Override // org.w3c.dom.Node
    public final NamedNodeMap getAttributes() {
        return new DTMNamedNodeMap(this.dtm, this.node);
    }

    @Override // org.w3c.dom.Node
    public String getBaseURI() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public final NodeList getChildNodes() {
        return new DTMChildIterNodeList(this.dtm, this.node);
    }

    public final DTM getDTM() {
        return this.dtm;
    }

    public final int getDTMNodeNumber() {
        return this.node;
    }

    @Override // org.w3c.dom.CharacterData
    public final String getData() throws DOMException {
        return this.dtm.getNodeValue(this.node);
    }

    @Override // org.w3c.dom.Document
    public final DocumentType getDoctype() {
        return null;
    }

    @Override // org.w3c.dom.Document
    public final Element getDocumentElement() {
        int document = this.dtm.getDocument();
        int firstChild = this.dtm.getFirstChild(document);
        int i = -1;
        while (firstChild != -1) {
            short nodeType = this.dtm.getNodeType(firstChild);
            if (nodeType != 1) {
                if (nodeType != 10 && nodeType != 7 && nodeType != 8) {
                    firstChild = this.dtm.getLastChild(document);
                    i = -1;
                }
            } else if (i != -1) {
                firstChild = this.dtm.getLastChild(document);
                i = -1;
            } else {
                i = firstChild;
            }
            firstChild = this.dtm.getNextSibling(firstChild);
        }
        if (i != -1) {
            return (Element) this.dtm.getNode(i);
        }
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Document
    public String getDocumentURI() {
        return this.fDocumentURI;
    }

    @Override // org.w3c.dom.Document
    public DOMConfiguration getDomConfig() {
        return null;
    }

    @Override // org.w3c.dom.Document
    public final Element getElementById(String str) {
        DTM dtm = this.dtm;
        return (Element) dtm.getNode(dtm.getElementById(str));
    }

    @Override // org.w3c.dom.Document, org.w3c.dom.Element
    public final NodeList getElementsByTagName(String str) {
        ArrayList arrayList = new ArrayList();
        Node node = this.dtm.getNode(this.node);
        if (node != null) {
            boolean zEquals = "*".equals(str);
            if (1 == node.getNodeType()) {
                NodeList childNodes = node.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    traverseChildren(arrayList, childNodes.item(i), str, zEquals);
                }
            } else if (9 == node.getNodeType()) {
                traverseChildren(arrayList, this.dtm.getNode(this.node), str, zEquals);
            }
        }
        int size = arrayList.size();
        NodeSet nodeSet = new NodeSet(size);
        for (int i2 = 0; i2 < size; i2++) {
            nodeSet.addNode(arrayList.get(i2));
        }
        return nodeSet;
    }

    @Override // org.w3c.dom.Document, org.w3c.dom.Element
    public final NodeList getElementsByTagNameNS(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        Node node = this.dtm.getNode(this.node);
        if (node != null) {
            boolean zEquals = "*".equals(str);
            boolean zEquals2 = "*".equals(str2);
            if (1 == node.getNodeType()) {
                NodeList childNodes = node.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    traverseChildren(arrayList, childNodes.item(i), str, str2, zEquals, zEquals2);
                }
            } else if (9 == node.getNodeType()) {
                traverseChildren(arrayList, this.dtm.getNode(this.node), str, str2, zEquals, zEquals2);
            }
        }
        int size = arrayList.size();
        NodeSet nodeSet = new NodeSet(size);
        for (int i2 = 0; i2 < size; i2++) {
            nodeSet.addNode(arrayList.get(i2));
        }
        return nodeSet;
    }

    @Override // org.w3c.dom.Node
    public Object getFeature(String str, String str2) {
        if (isSupported(str, str2)) {
            return this;
        }
        return null;
    }

    @Override // org.w3c.dom.Node
    public final Node getFirstChild() {
        int firstChild = this.dtm.getFirstChild(this.node);
        if (firstChild == -1) {
            return null;
        }
        return this.dtm.getNode(firstChild);
    }

    @Override // org.w3c.dom.Document
    public final DOMImplementation getImplementation() {
        return implementation;
    }

    @Override // org.w3c.dom.Document
    public String getInputEncoding() {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Node
    public final Node getLastChild() {
        int lastChild = this.dtm.getLastChild(this.node);
        if (lastChild == -1) {
            return null;
        }
        return this.dtm.getNode(lastChild);
    }

    @Override // org.w3c.dom.CharacterData
    public final int getLength() {
        return this.dtm.getNodeValue(this.node).length();
    }

    @Override // org.w3c.dom.Node
    public final String getLocalName() {
        return this.dtm.getLocalName(this.node);
    }

    @Override // org.w3c.dom.Attr
    public final String getName() {
        return this.dtm.getNodeName(this.node);
    }

    @Override // org.w3c.dom.Node
    public final String getNamespaceURI() {
        return this.dtm.getNamespaceURI(this.node);
    }

    @Override // org.w3c.dom.Node
    public final Node getNextSibling() {
        int nextSibling;
        if (this.dtm.getNodeType(this.node) == 2 || (nextSibling = this.dtm.getNextSibling(this.node)) == -1) {
            return null;
        }
        return this.dtm.getNode(nextSibling);
    }

    @Override // org.w3c.dom.Node
    public final String getNodeName() {
        return this.dtm.getNodeName(this.node);
    }

    @Override // org.w3c.dom.Node
    public final short getNodeType() {
        return this.dtm.getNodeType(this.node);
    }

    @Override // org.w3c.dom.Node
    public final String getNodeValue() throws DOMException {
        return this.dtm.getNodeValue(this.node);
    }

    @Override // org.w3c.dom.Node
    public final Document getOwnerDocument() {
        DTM dtm = this.dtm;
        return (Document) dtm.getNode(dtm.getOwnerDocument(this.node));
    }

    @Override // org.w3c.dom.Attr
    public final Element getOwnerElement() {
        int parent;
        if (getNodeType() == 2 && (parent = this.dtm.getParent(this.node)) != -1) {
            return (Element) this.dtm.getNode(parent);
        }
        return null;
    }

    public final Node getOwnerNode() {
        int parent = this.dtm.getParent(this.node);
        if (parent == -1) {
            return null;
        }
        return this.dtm.getNode(parent);
    }

    @Override // org.w3c.dom.Node
    public final Node getParentNode() {
        int parent;
        if (getNodeType() == 2 || (parent = this.dtm.getParent(this.node)) == -1) {
            return null;
        }
        return this.dtm.getNode(parent);
    }

    @Override // org.w3c.dom.Node
    public final String getPrefix() {
        return this.dtm.getPrefix(this.node);
    }

    @Override // org.w3c.dom.Node
    public final Node getPreviousSibling() {
        int previousSibling = this.dtm.getPreviousSibling(this.node);
        if (previousSibling == -1) {
            return null;
        }
        return this.dtm.getNode(previousSibling);
    }

    @Override // org.w3c.dom.Element
    public TypeInfo getSchemaTypeInfo() {
        return null;
    }

    @Override // org.w3c.dom.Attr
    public final boolean getSpecified() {
        return true;
    }

    public boolean getStandalone() {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Document
    public boolean getStrictErrorChecking() {
        throw new DTMDOMException((short) 9);
    }

    public final String getStringValue() throws DOMException {
        return this.dtm.getStringValue(this.node).toString();
    }

    @Override // org.w3c.dom.Element
    public final String getTagName() {
        return this.dtm.getNodeName(this.node);
    }

    @Override // org.w3c.dom.ProcessingInstruction
    public final String getTarget() {
        return this.dtm.getNodeName(this.node);
    }

    @Override // org.w3c.dom.Node
    public String getTextContent() throws DOMException {
        return this.dtm.getStringValue(this.node).toString();
    }

    @Override // org.w3c.dom.Node
    public Object getUserData(String str) {
        return getOwnerDocument().getUserData(str);
    }

    @Override // org.w3c.dom.Attr
    public final String getValue() {
        return this.dtm.getNodeValue(this.node);
    }

    public String getVersion() {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Text
    public String getWholeText() {
        return null;
    }

    @Override // org.w3c.dom.Document
    public String getXmlEncoding() {
        return this.xmlEncoding;
    }

    @Override // org.w3c.dom.Document
    public boolean getXmlStandalone() {
        return this.xmlStandalone;
    }

    @Override // org.w3c.dom.Document
    public String getXmlVersion() {
        return this.xmlVersion;
    }

    @Override // org.w3c.dom.Element
    public boolean hasAttribute(String str) {
        return -1 != this.dtm.getAttributeNode(this.node, null, str);
    }

    @Override // org.w3c.dom.Element
    public boolean hasAttributeNS(String str, String str2) {
        return -1 != this.dtm.getAttributeNode(this.node, str, str2);
    }

    @Override // org.w3c.dom.Node
    public boolean hasAttributes() {
        return -1 != this.dtm.getFirstAttribute(this.node);
    }

    @Override // org.w3c.dom.Node
    public final boolean hasChildNodes() {
        return -1 != this.dtm.getFirstChild(this.node);
    }

    public int hashCode() {
        return ((203 + Objects.hashCode(this.dtm)) * 29) + this.node;
    }

    @Override // org.w3c.dom.Document
    public final Node importNode(Node node, boolean z) throws DOMException {
        throw new DTMDOMException((short) 7);
    }

    @Override // org.w3c.dom.Node
    public final Node insertBefore(Node node, Node node2) throws DOMException {
        throw new DTMDOMException((short) 7);
    }

    @Override // org.w3c.dom.CharacterData
    public final void insertData(int i, String str) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Node
    public boolean isDefaultNamespace(String str) {
        return false;
    }

    @Override // org.w3c.dom.Text
    public boolean isElementContentWhitespace() {
        return false;
    }

    @Override // org.w3c.dom.Node
    public boolean isEqualNode(Node node) {
        if (node == this) {
            return true;
        }
        if (node.getNodeType() != getNodeType()) {
            return false;
        }
        if (getNodeName() == null) {
            if (node.getNodeName() != null) {
                return false;
            }
        } else if (!getNodeName().equals(node.getNodeName())) {
            return false;
        }
        if (getLocalName() == null) {
            if (node.getLocalName() != null) {
                return false;
            }
        } else if (!getLocalName().equals(node.getLocalName())) {
            return false;
        }
        if (getNamespaceURI() == null) {
            if (node.getNamespaceURI() != null) {
                return false;
            }
        } else if (!getNamespaceURI().equals(node.getNamespaceURI())) {
            return false;
        }
        if (getPrefix() == null) {
            if (node.getPrefix() != null) {
                return false;
            }
        } else if (!getPrefix().equals(node.getPrefix())) {
            return false;
        }
        if (getNodeValue() == null) {
            if (node.getNodeValue() != null) {
                return false;
            }
        } else if (!getNodeValue().equals(node.getNodeValue())) {
            return false;
        }
        return true;
    }

    @Override // org.w3c.dom.Attr
    public boolean isId() {
        return false;
    }

    @Override // org.w3c.dom.Node
    public boolean isSameNode(Node node) {
        return this == node;
    }

    @Override // org.w3c.dom.Node
    public final boolean isSupported(String str, String str2) {
        return implementation.hasFeature(str, str2);
    }

    @Override // org.w3c.dom.Node
    public String lookupNamespaceURI(String str) {
        short nodeType = getNodeType();
        if (nodeType != 1) {
            if (nodeType == 2 && getOwnerElement().getNodeType() == 1) {
                return getOwnerElement().lookupNamespaceURI(str);
            }
            return null;
        }
        String namespaceURI = getNamespaceURI();
        String prefix = getPrefix();
        if (namespaceURI != null && ((str == null && prefix == str) || (prefix != null && prefix.equals(str)))) {
            return namespaceURI;
        }
        if (hasAttributes()) {
            NamedNodeMap attributes = getAttributes();
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                Node nodeItem = attributes.item(i);
                String prefix2 = nodeItem.getPrefix();
                String nodeValue = nodeItem.getNodeValue();
                String namespaceURI2 = nodeItem.getNamespaceURI();
                if (namespaceURI2 != null && namespaceURI2.equals("http://www.w3.org/2000/xmlns/") && ((str == null && nodeItem.getNodeName().equals("xmlns")) || (prefix2 != null && prefix2.equals("xmlns") && nodeItem.getLocalName().equals(str)))) {
                    return nodeValue;
                }
            }
        }
        return null;
    }

    @Override // org.w3c.dom.Node
    public String lookupPrefix(String str) {
        if (str != null && getNodeType() == 2 && getOwnerElement().getNodeType() == 1) {
            return getOwnerElement().lookupPrefix(str);
        }
        return null;
    }

    @Override // org.w3c.dom.Node
    public final void normalize() {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Document
    public void normalizeDocument() {
    }

    @Override // org.w3c.dom.Element
    public final void removeAttribute(String str) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Element
    public final void removeAttributeNS(String str, String str2) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Element
    public final Attr removeAttributeNode(Attr attr) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Node
    public final Node removeChild(Node node) throws DOMException {
        throw new DTMDOMException((short) 7);
    }

    @Override // org.w3c.dom.Document
    public Node renameNode(Node node, String str, String str2) throws DOMException {
        return node;
    }

    @Override // org.w3c.dom.Node
    public final Node replaceChild(Node node, Node node2) throws DOMException {
        throw new DTMDOMException((short) 7);
    }

    @Override // org.w3c.dom.CharacterData
    public final void replaceData(int i, int i2, String str) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Text
    public Text replaceWholeText(String str) throws DOMException {
        return null;
    }

    public final boolean sameNodeAs(Node node) {
        if (!(node instanceof DTMNodeProxy)) {
            return false;
        }
        DTMNodeProxy dTMNodeProxy = (DTMNodeProxy) node;
        return this.dtm == dTMNodeProxy.dtm && this.node == dTMNodeProxy.node;
    }

    public void setActualEncoding(String str) {
        this.actualEncoding = str;
    }

    @Override // org.w3c.dom.Element
    public final void setAttribute(String str, String str2) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Element
    public final void setAttributeNS(String str, String str2, String str3) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Element
    public final Attr setAttributeNode(Attr attr) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Element
    public final Attr setAttributeNodeNS(Attr attr) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.CharacterData
    public final void setData(String str) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Document
    public void setDocumentURI(String str) {
        this.fDocumentURI = str;
    }

    public void setEncoding(String str) {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Element
    public void setIdAttribute(String str, boolean z) {
    }

    public void setIdAttribute(boolean z) {
    }

    @Override // org.w3c.dom.Element
    public void setIdAttributeNS(String str, String str2, boolean z) {
    }

    @Override // org.w3c.dom.Element
    public void setIdAttributeNode(Attr attr, boolean z) {
    }

    @Override // org.w3c.dom.Node
    public final void setNodeValue(String str) throws DOMException {
        throw new DTMDOMException((short) 7);
    }

    @Override // org.w3c.dom.Node
    public final void setPrefix(String str) throws DOMException {
        throw new DTMDOMException((short) 7);
    }

    public void setStandalone(boolean z) {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Document
    public void setStrictErrorChecking(boolean z) {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.Node
    public void setTextContent(String str) throws DOMException {
        setNodeValue(str);
    }

    @Override // org.w3c.dom.Node
    public Object setUserData(String str, Object obj, UserDataHandler userDataHandler) {
        return getOwnerDocument().setUserData(str, obj, userDataHandler);
    }

    @Override // org.w3c.dom.Attr
    public final void setValue(String str) {
        throw new DTMDOMException((short) 9);
    }

    public void setVersion(String str) {
        throw new DTMDOMException((short) 9);
    }

    public void setXmlEncoding(String str) {
        this.xmlEncoding = str;
    }

    @Override // org.w3c.dom.Document
    public void setXmlStandalone(boolean z) throws DOMException {
        this.xmlStandalone = z;
    }

    @Override // org.w3c.dom.Document
    public void setXmlVersion(String str) throws DOMException {
        this.xmlVersion = str;
    }

    @Override // org.w3c.dom.Text
    public final Text splitText(int i) throws DOMException {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.CharacterData
    public final String substringData(int i, int i2) throws DOMException {
        return getData().substring(i, i2 + i);
    }

    public final boolean supports(String str, String str2) {
        return implementation.hasFeature(str, str2);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof Node) && equals((Node) obj);
    }

    private final void traverseChildren(List<Node> list, Node node, String str, boolean z) {
        if (node == null) {
            return;
        }
        if (node.getNodeType() == 1 && (z || node.getNodeName().equals(str))) {
            list.add(node);
        }
        if (node.hasChildNodes()) {
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                traverseChildren(list, childNodes.item(i), str, z);
            }
        }
    }
}
