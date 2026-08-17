package com.sun.org.apache.xml.internal.utils;

import com.sun.org.apache.xml.internal.res.XMLMessages;
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
public class UnImplNode implements Node, Element, NodeList, Document {
    protected String actualEncoding;
    protected String fDocumentURI;
    private String xmlEncoding;
    private boolean xmlStandalone;
    private String xmlVersion;

    @Override // org.w3c.dom.Document
    public Node adoptNode(Node node) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node appendChild(Node node) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    public void appendData(String str) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
    }

    @Override // org.w3c.dom.Node
    public Node cloneNode(boolean z) {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public short compareDocumentPosition(Node node) throws DOMException {
        return (short) 0;
    }

    @Override // org.w3c.dom.Document
    public Attr createAttribute(String str) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Document
    public Attr createAttributeNS(String str, String str2) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Document
    public CDATASection createCDATASection(String str) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Document
    public Comment createComment(String str) {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Document
    public DocumentFragment createDocumentFragment() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Document
    public Element createElement(String str) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Document
    public Element createElementNS(String str, String str2) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Document
    public EntityReference createEntityReference(String str) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Document
    public ProcessingInstruction createProcessingInstruction(String str, String str2) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Document
    public Text createTextNode(String str) {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    public void deleteData(int i, int i2) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
    }

    public void error(String str) {
        System.out.println("DOM ERROR! class: ".concat(getClass().getName()));
        throw new RuntimeException(XMLMessages.createXMLMessage(str, null));
    }

    public String getActualEncoding() {
        return this.actualEncoding;
    }

    @Override // org.w3c.dom.Element
    public String getAttribute(String str) {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Element
    public String getAttributeNS(String str, String str2) {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Element
    public Attr getAttributeNode(String str) {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Element
    public Attr getAttributeNodeNS(String str, String str2) {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public NamedNodeMap getAttributes() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getBaseURI() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public NodeList getChildNodes() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Document
    public DocumentType getDoctype() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Document
    public Element getDocumentElement() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
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
    public Element getElementById(String str) {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Element
    public NodeList getElementsByTagName(String str) {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Element
    public NodeList getElementsByTagNameNS(String str, String str2) {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public Object getFeature(String str, String str2) {
        if (isSupported(str, str2)) {
            return this;
        }
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getFirstChild() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Document
    public DOMImplementation getImplementation() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Document
    public String getInputEncoding() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getLastChild() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.NodeList
    public int getLength() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return 0;
    }

    @Override // org.w3c.dom.Node
    public String getLocalName() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getNamespaceURI() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getNextSibling() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getNodeName() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public short getNodeType() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return (short) 0;
    }

    @Override // org.w3c.dom.Node
    public String getNodeValue() throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public Document getOwnerDocument() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    public Element getOwnerElement() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getParentNode() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getPrefix() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getPreviousSibling() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Element
    public TypeInfo getSchemaTypeInfo() {
        return null;
    }

    public boolean getSpecified() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return false;
    }

    public boolean getStandalone() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return false;
    }

    @Override // org.w3c.dom.Document
    public boolean getStrictErrorChecking() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return false;
    }

    @Override // org.w3c.dom.Element
    public String getTagName() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getTextContent() throws DOMException {
        return getNodeValue();
    }

    @Override // org.w3c.dom.Node
    public Object getUserData(String str) {
        return getOwnerDocument().getUserData(str);
    }

    public String getVersion() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

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
        error("ER_FUNCTION_NOT_SUPPORTED");
        return false;
    }

    @Override // org.w3c.dom.Element
    public boolean hasAttributeNS(String str, String str2) {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return false;
    }

    @Override // org.w3c.dom.Node
    public boolean hasAttributes() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return false;
    }

    @Override // org.w3c.dom.Node
    public boolean hasChildNodes() {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return false;
    }

    @Override // org.w3c.dom.Document
    public Node importNode(Node node, boolean z) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node insertBefore(Node node, Node node2) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    public void insertData(int i, String str) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
    }

    @Override // org.w3c.dom.Node
    public boolean isDefaultNamespace(String str) {
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

    public boolean isId() {
        return false;
    }

    @Override // org.w3c.dom.Node
    public boolean isSameNode(Node node) {
        return this == node;
    }

    @Override // org.w3c.dom.Node
    public boolean isSupported(String str, String str2) {
        return false;
    }

    public boolean isWhitespaceInElementContent() {
        return false;
    }

    @Override // org.w3c.dom.NodeList
    public Node item(int i) {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
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
    public void normalize() {
        error("ER_FUNCTION_NOT_SUPPORTED");
    }

    @Override // org.w3c.dom.Document
    public void normalizeDocument() {
    }

    @Override // org.w3c.dom.Element
    public void removeAttribute(String str) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
    }

    @Override // org.w3c.dom.Element
    public void removeAttributeNS(String str, String str2) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
    }

    @Override // org.w3c.dom.Element
    public Attr removeAttributeNode(Attr attr) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node removeChild(Node node) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Document
    public Node renameNode(Node node, String str, String str2) throws DOMException {
        return node;
    }

    @Override // org.w3c.dom.Node
    public Node replaceChild(Node node, Node node2) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    public void replaceData(int i, int i2, String str) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
    }

    public Text replaceWholeText(String str) throws DOMException {
        return null;
    }

    public void setActualEncoding(String str) {
        this.actualEncoding = str;
    }

    @Override // org.w3c.dom.Element
    public void setAttribute(String str, String str2) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
    }

    @Override // org.w3c.dom.Element
    public void setAttributeNS(String str, String str2, String str3) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
    }

    @Override // org.w3c.dom.Element
    public Attr setAttributeNode(Attr attr) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    @Override // org.w3c.dom.Element
    public Attr setAttributeNodeNS(Attr attr) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    public void setData(String str) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
    }

    @Override // org.w3c.dom.Document
    public void setDocumentURI(String str) {
        this.fDocumentURI = str;
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

    public void setInputEncoding(String str) {
        error("ER_FUNCTION_NOT_SUPPORTED");
    }

    @Override // org.w3c.dom.Node
    public void setNodeValue(String str) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
    }

    @Override // org.w3c.dom.Node
    public void setPrefix(String str) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
    }

    public void setStandalone(boolean z) {
        error("ER_FUNCTION_NOT_SUPPORTED");
    }

    @Override // org.w3c.dom.Document
    public void setStrictErrorChecking(boolean z) {
        error("ER_FUNCTION_NOT_SUPPORTED");
    }

    @Override // org.w3c.dom.Node
    public void setTextContent(String str) throws DOMException {
        setNodeValue(str);
    }

    @Override // org.w3c.dom.Node
    public Object setUserData(String str, Object obj, UserDataHandler userDataHandler) {
        return getOwnerDocument().setUserData(str, obj, userDataHandler);
    }

    public void setValue(String str) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
    }

    public void setVersion(String str) {
        error("ER_FUNCTION_NOT_SUPPORTED");
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

    public Text splitText(int i) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    public String substringData(int i, int i2) throws DOMException {
        error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }

    public void error(String str, Object[] objArr) {
        System.out.println("DOM ERROR! class: ".concat(getClass().getName()));
        throw new RuntimeException(XMLMessages.createXMLMessage(str, objArr));
    }
}
