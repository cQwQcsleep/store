package com.sun.org.apache.xerces.internal.dom;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import defpackage.zi0;
import org.w3c.dom.CharacterData;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TextImpl extends CharacterDataImpl implements CharacterData, Text {
    static final long serialVersionUID = -5294980852957403469L;

    public TextImpl() {
    }

    private boolean canModifyNext(Node node) {
        boolean z = false;
        for (Node nextSibling = node.getNextSibling(); nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
            short nodeType = nextSibling.getNodeType();
            if (nodeType == 5) {
                Node firstChild = nextSibling.getFirstChild();
                if (firstChild == null) {
                    return false;
                }
                while (firstChild != null) {
                    short nodeType2 = firstChild.getNodeType();
                    if (nodeType2 != 3 && nodeType2 != 4) {
                        if (nodeType2 != 5) {
                            return !z;
                        }
                        if (!canModifyNext(firstChild)) {
                            return false;
                        }
                    }
                    firstChild = firstChild.getNextSibling();
                    z = true;
                }
            } else if (nodeType != 3 && nodeType != 4) {
                return true;
            }
        }
        return true;
    }

    private boolean canModifyPrev(Node node) {
        boolean z = false;
        for (Node previousSibling = node.getPreviousSibling(); previousSibling != null; previousSibling = previousSibling.getPreviousSibling()) {
            short nodeType = previousSibling.getNodeType();
            if (nodeType == 5) {
                Node lastChild = previousSibling.getLastChild();
                if (lastChild == null) {
                    return false;
                }
                while (lastChild != null) {
                    short nodeType2 = lastChild.getNodeType();
                    if (nodeType2 != 3 && nodeType2 != 4) {
                        if (nodeType2 != 5) {
                            return !z;
                        }
                        if (!canModifyPrev(lastChild)) {
                            return false;
                        }
                    }
                    lastChild = lastChild.getPreviousSibling();
                    z = true;
                }
            } else if (nodeType != 3 && nodeType != 4) {
                return true;
            }
        }
        return true;
    }

    private boolean getWholeTextBackward(Node node, StringBuilder sb, Node node2) {
        boolean z = node2 != null && node2.getNodeType() == 5;
        while (node != null) {
            short nodeType = node.getNodeType();
            if (nodeType == 5) {
                if (getWholeTextBackward(node.getLastChild(), sb, node)) {
                    return true;
                }
            } else {
                if (nodeType != 3 && nodeType != 4) {
                    return true;
                }
                ((TextImpl) node).insertTextContent(sb);
            }
            node = node.getPreviousSibling();
        }
        if (!z) {
            return false;
        }
        getWholeTextBackward(node2.getPreviousSibling(), sb, node2.getParentNode());
        return true;
    }

    private boolean getWholeTextForward(Node node, StringBuilder sb, Node node2) {
        boolean z = node2 != null && node2.getNodeType() == 5;
        while (node != null) {
            short nodeType = node.getNodeType();
            if (nodeType == 5) {
                if (getWholeTextForward(node.getFirstChild(), sb, node)) {
                    return true;
                }
            } else {
                if (nodeType != 3 && nodeType != 4) {
                    return true;
                }
                ((NodeImpl) node).getTextContent(sb);
            }
            node = node.getNextSibling();
        }
        if (!z) {
            return false;
        }
        getWholeTextForward(node2.getNextSibling(), sb, node2.getParentNode());
        return true;
    }

    private boolean hasTextOnlyChildren(Node node) {
        if (node == null) {
            return false;
        }
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            short nodeType = firstChild.getNodeType();
            if (nodeType == 5) {
                return hasTextOnlyChildren(firstChild);
            }
            if (nodeType != 3 && nodeType != 4 && nodeType != 5) {
                return false;
            }
        }
        return true;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        return PsuedoNames.PSEUDONAME_TEXT;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return (short) 3;
    }

    @Override // org.w3c.dom.Text
    public String getWholeText() {
        if (needsSyncData()) {
            synchronizeData();
        }
        StringBuilder sb = new StringBuilder();
        String str = this.data;
        if (str != null && str.length() != 0) {
            sb.append(this.data);
        }
        getWholeTextBackward(getPreviousSibling(), sb, getParentNode());
        String string = sb.toString();
        sb.setLength(0);
        getWholeTextForward(getNextSibling(), sb, getParentNode());
        return string.concat(sb.toString());
    }

    public void insertTextContent(StringBuilder sb) throws DOMException {
        String nodeValue = getNodeValue();
        if (nodeValue != null) {
            sb.insert(0, nodeValue);
        }
    }

    @Override // org.w3c.dom.Text
    public boolean isElementContentWhitespace() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return internalIsIgnorableWhitespace();
    }

    public boolean isIgnorableWhitespace() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return internalIsIgnorableWhitespace();
    }

    public String removeData() {
        String str = this.data;
        this.data = "";
        return str;
    }

    public void replaceData(String str) {
        this.data = str;
    }

    @Override // org.w3c.dom.Text
    public Text replaceWholeText(String str) throws DOMException {
        Text textCreateTextNode;
        if (needsSyncData()) {
            synchronizeData();
        }
        Node parentNode = getParentNode();
        if (str == null || str.length() == 0) {
            if (parentNode != null) {
                parentNode.removeChild(this);
            }
            return null;
        }
        if (ownerDocument().errorChecking) {
            if (!canModifyPrev(this)) {
                zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                return null;
            }
            if (!canModifyNext(this)) {
                zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                return null;
            }
        }
        if (isReadOnly()) {
            textCreateTextNode = ownerDocument().createTextNode(str);
            if (parentNode == null) {
                return textCreateTextNode;
            }
            parentNode.insertBefore(textCreateTextNode, this);
            parentNode.removeChild(this);
        } else {
            setData(str);
            textCreateTextNode = this;
        }
        for (Node previousSibling = textCreateTextNode.getPreviousSibling(); previousSibling != null && (previousSibling.getNodeType() == 3 || previousSibling.getNodeType() == 4 || (previousSibling.getNodeType() == 5 && hasTextOnlyChildren(previousSibling))); previousSibling = textCreateTextNode.getPreviousSibling()) {
            parentNode.removeChild(previousSibling);
        }
        for (Node nextSibling = textCreateTextNode.getNextSibling(); nextSibling != null && (nextSibling.getNodeType() == 3 || nextSibling.getNodeType() == 4 || (nextSibling.getNodeType() == 5 && hasTextOnlyChildren(nextSibling))); nextSibling = textCreateTextNode.getNextSibling()) {
            parentNode.removeChild(nextSibling);
        }
        return textCreateTextNode;
    }

    public void setIgnorableWhitespace(boolean z) {
        if (needsSyncData()) {
            synchronizeData();
        }
        isIgnorableWhitespace(z);
    }

    public void setValues(CoreDocumentImpl coreDocumentImpl, String str) {
        this.flags = (short) 0;
        this.nextSibling = null;
        this.previousSibling = null;
        setOwnerDocument(coreDocumentImpl);
        this.data = str;
    }

    @Override // org.w3c.dom.Text
    public Text splitText(int i) throws DOMException {
        if (isReadOnly()) {
            zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            return null;
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        if (i < 0 || i > this.data.length()) {
            zi0.a(1, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INDEX_SIZE_ERR", null));
            return null;
        }
        Text textCreateTextNode = getOwnerDocument().createTextNode(this.data.substring(i));
        setNodeValue(this.data.substring(0, i));
        Node parentNode = getParentNode();
        if (parentNode != null) {
            parentNode.insertBefore(textCreateTextNode, this.nextSibling);
        }
        return textCreateTextNode;
    }

    public TextImpl(CoreDocumentImpl coreDocumentImpl, String str) {
        super(coreDocumentImpl, str);
    }
}
