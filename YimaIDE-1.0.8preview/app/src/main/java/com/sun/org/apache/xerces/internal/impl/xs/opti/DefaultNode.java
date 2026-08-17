package com.sun.org.apache.xerces.internal.impl.xs.opti;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DefaultNode implements Node {
    @Override // org.w3c.dom.Node
    public Node appendChild(Node node) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Node
    public Node cloneNode(boolean z) {
        return null;
    }

    @Override // org.w3c.dom.Node
    public short compareDocumentPosition(Node node) {
        throw new DOMException((short) 9, "Method not supported");
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
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getFirstChild() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getLastChild() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getLocalName() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getNamespaceURI() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getNextSibling() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getNodeName() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public short getNodeType() {
        return (short) -1;
    }

    @Override // org.w3c.dom.Node
    public String getNodeValue() throws DOMException {
        return null;
    }

    @Override // org.w3c.dom.Node
    public Document getOwnerDocument() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getParentNode() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getPrefix() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getPreviousSibling() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getTextContent() throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Node
    public Object getUserData(String str) {
        return null;
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
    public Node insertBefore(Node node, Node node2) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Node
    public boolean isDefaultNamespace(String str) {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Node
    public boolean isEqualNode(Node node) {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Node
    public boolean isSameNode(Node node) {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Node
    public boolean isSupported(String str, String str2) {
        return false;
    }

    @Override // org.w3c.dom.Node
    public String lookupNamespaceURI(String str) {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Node
    public String lookupPrefix(String str) {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Node
    public void normalize() {
    }

    @Override // org.w3c.dom.Node
    public Node removeChild(Node node) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Node
    public Node replaceChild(Node node, Node node2) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Node
    public void setNodeValue(String str) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Node
    public void setPrefix(String str) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Node
    public void setTextContent(String str) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Node
    public Object setUserData(String str, Object obj, UserDataHandler userDataHandler) {
        throw new DOMException((short) 9, "Method not supported");
    }
}
