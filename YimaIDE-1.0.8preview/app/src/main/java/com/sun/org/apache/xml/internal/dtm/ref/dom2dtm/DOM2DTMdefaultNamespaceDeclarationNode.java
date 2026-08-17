package com.sun.org.apache.xml.internal.dtm.ref.dom2dtm;

import com.sun.org.apache.xml.internal.dtm.DTMException;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOM2DTMdefaultNamespaceDeclarationNode implements Attr, TypeInfo {
    final String NOT_SUPPORTED_ERR = "Unsupported operation on pseudonode";
    int handle;
    String nodename;
    String prefix;
    Element pseudoparent;
    String uri;

    public DOM2DTMdefaultNamespaceDeclarationNode(Element element, String str, String str2, int i) {
        this.pseudoparent = element;
        this.prefix = str;
        this.uri = str2;
        this.handle = i;
        this.nodename = "xmlns:" + str;
    }

    @Override // org.w3c.dom.Node
    public Node appendChild(Node node) {
        throw new DTMException("Unsupported operation on pseudonode");
    }

    @Override // org.w3c.dom.Node
    public Node cloneNode(boolean z) {
        throw new DTMException("Unsupported operation on pseudonode");
    }

    @Override // org.w3c.dom.Node
    public short compareDocumentPosition(Node node) throws DOMException {
        return (short) 0;
    }

    @Override // org.w3c.dom.Node
    public NamedNodeMap getAttributes() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getBaseURI() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public NodeList getChildNodes() {
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
        return null;
    }

    public int getHandleOfNode() {
        return this.handle;
    }

    @Override // org.w3c.dom.Node
    public Node getLastChild() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getLocalName() {
        return this.prefix;
    }

    @Override // org.w3c.dom.Attr
    public String getName() {
        return this.nodename;
    }

    @Override // org.w3c.dom.Node
    public String getNamespaceURI() {
        return "http://www.w3.org/2000/xmlns/";
    }

    @Override // org.w3c.dom.Node
    public Node getNextSibling() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getNodeName() {
        return this.nodename;
    }

    @Override // org.w3c.dom.Node
    public short getNodeType() {
        return (short) 2;
    }

    @Override // org.w3c.dom.Node
    public String getNodeValue() {
        return this.uri;
    }

    @Override // org.w3c.dom.Node
    public Document getOwnerDocument() {
        return this.pseudoparent.getOwnerDocument();
    }

    @Override // org.w3c.dom.Attr
    public Element getOwnerElement() {
        return this.pseudoparent;
    }

    @Override // org.w3c.dom.Node
    public Node getParentNode() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getPrefix() {
        return this.prefix;
    }

    @Override // org.w3c.dom.Node
    public Node getPreviousSibling() {
        return null;
    }

    @Override // org.w3c.dom.Attr
    public TypeInfo getSchemaTypeInfo() {
        return this;
    }

    @Override // org.w3c.dom.Attr
    public boolean getSpecified() {
        return false;
    }

    @Override // org.w3c.dom.Node
    public String getTextContent() throws DOMException {
        return getNodeValue();
    }

    @Override // org.w3c.dom.TypeInfo
    public String getTypeName() {
        return null;
    }

    @Override // org.w3c.dom.TypeInfo
    public String getTypeNamespace() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public Object getUserData(String str) {
        return getOwnerDocument().getUserData(str);
    }

    @Override // org.w3c.dom.Attr
    public String getValue() {
        return this.uri;
    }

    @Override // org.w3c.dom.Node
    public boolean hasAttributes() {
        return false;
    }

    @Override // org.w3c.dom.Node
    public boolean hasChildNodes() {
        return false;
    }

    @Override // org.w3c.dom.Node
    public Node insertBefore(Node node, Node node2) {
        throw new DTMException("Unsupported operation on pseudonode");
    }

    @Override // org.w3c.dom.Node
    public boolean isDefaultNamespace(String str) {
        return false;
    }

    @Override // org.w3c.dom.TypeInfo
    public boolean isDerivedFrom(String str, String str2, int i) {
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
    public boolean isSupported(String str, String str2) {
        return false;
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
    }

    @Override // org.w3c.dom.Node
    public Node removeChild(Node node) {
        throw new DTMException("Unsupported operation on pseudonode");
    }

    @Override // org.w3c.dom.Node
    public Node replaceChild(Node node, Node node2) {
        throw new DTMException("Unsupported operation on pseudonode");
    }

    @Override // org.w3c.dom.Node
    public void setNodeValue(String str) {
        throw new DTMException("Unsupported operation on pseudonode");
    }

    @Override // org.w3c.dom.Node
    public void setPrefix(String str) {
        throw new DTMException("Unsupported operation on pseudonode");
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
    public void setValue(String str) {
        throw new DTMException("Unsupported operation on pseudonode");
    }
}
