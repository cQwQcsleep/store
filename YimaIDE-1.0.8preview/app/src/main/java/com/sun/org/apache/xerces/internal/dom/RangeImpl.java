package com.sun.org.apache.xerces.internal.dom;

import defpackage.zi0;
import java.util.ArrayList;
import org.w3c.dom.CharacterData;
import org.w3c.dom.DOMException;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;
import org.w3c.dom.ranges.Range;
import org.w3c.dom.ranges.RangeException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RangeImpl implements Range {
    static final int CLONE_CONTENTS = 2;
    static final int DELETE_CONTENTS = 3;
    static final int EXTRACT_CONTENTS = 1;
    DocumentImpl fDocument;
    Node fEndContainer;
    boolean fIsCollapsed;
    Node fStartContainer;
    Node fInsertNode = null;
    Node fDeleteNode = null;
    Node fSplitNode = null;
    boolean fInsertedFromRange = false;
    Node fRemoveChild = null;
    int fStartOffset = 0;
    int fEndOffset = 0;
    boolean fDetach = false;

    public RangeImpl(DocumentImpl documentImpl) {
        this.fDocument = documentImpl;
        this.fStartContainer = documentImpl;
        this.fEndContainer = documentImpl;
    }

    private Node getRootContainer(Node node) {
        if (node == null) {
            return null;
        }
        while (node.getParentNode() != null) {
            node = node.getParentNode();
        }
        return node;
    }

    private Node getSelectedNode(Node node, int i) {
        if (node.getNodeType() != 3 && i >= 0) {
            Node firstChild = node.getFirstChild();
            while (firstChild != null && i > 0) {
                i--;
                firstChild = firstChild.getNextSibling();
            }
            if (firstChild != null) {
                return firstChild;
            }
        }
        return node;
    }

    private boolean hasLegalRootContainer(Node node) {
        if (node == null) {
            return false;
        }
        short nodeType = getRootContainer(node).getNodeType();
        return nodeType == 2 || nodeType == 9 || nodeType == 11;
    }

    private boolean isLegalContainedNode(Node node) {
        short nodeType;
        return (node == null || (nodeType = node.getNodeType()) == 2 || nodeType == 6 || nodeType == 9 || nodeType == 11 || nodeType == 12) ? false : true;
    }

    private boolean isLegalContainer(Node node) {
        if (node == null) {
            return false;
        }
        while (node != null) {
            short nodeType = node.getNodeType();
            if (nodeType == 6 || nodeType == 10 || nodeType == 12) {
                return false;
            }
            node = node.getParentNode();
        }
        return true;
    }

    private DocumentFragment traverseCommonAncestors(Node node, Node node2, int i) {
        DocumentFragment documentFragmentCreateDocumentFragment = i != 3 ? this.fDocument.createDocumentFragment() : null;
        Node nodeTraverseLeftBoundary = traverseLeftBoundary(node, i);
        if (documentFragmentCreateDocumentFragment != null) {
            documentFragmentCreateDocumentFragment.appendChild(nodeTraverseLeftBoundary);
        }
        Node parentNode = node.getParentNode();
        int iIndexOf = indexOf(node2, parentNode) - (indexOf(node, parentNode) + 1);
        Node nextSibling = node.getNextSibling();
        while (iIndexOf > 0) {
            Node nextSibling2 = nextSibling.getNextSibling();
            Node nodeTraverseFullySelected = traverseFullySelected(nextSibling, i);
            if (documentFragmentCreateDocumentFragment != null) {
                documentFragmentCreateDocumentFragment.appendChild(nodeTraverseFullySelected);
            }
            iIndexOf--;
            nextSibling = nextSibling2;
        }
        Node nodeTraverseRightBoundary = traverseRightBoundary(node2, i);
        if (documentFragmentCreateDocumentFragment != null) {
            documentFragmentCreateDocumentFragment.appendChild(nodeTraverseRightBoundary);
        }
        if (i != 2) {
            setStartAfter(node);
            collapse(true);
        }
        return documentFragmentCreateDocumentFragment;
    }

    private DocumentFragment traverseCommonEndContainer(Node node, int i) {
        DocumentFragment documentFragmentCreateDocumentFragment = i != 3 ? this.fDocument.createDocumentFragment() : null;
        Node nodeTraverseLeftBoundary = traverseLeftBoundary(node, i);
        if (documentFragmentCreateDocumentFragment != null) {
            documentFragmentCreateDocumentFragment.appendChild(nodeTraverseLeftBoundary);
        }
        int iIndexOf = this.fEndOffset - (indexOf(node, this.fEndContainer) + 1);
        Node nextSibling = node.getNextSibling();
        while (iIndexOf > 0) {
            Node nextSibling2 = nextSibling.getNextSibling();
            Node nodeTraverseFullySelected = traverseFullySelected(nextSibling, i);
            if (documentFragmentCreateDocumentFragment != null) {
                documentFragmentCreateDocumentFragment.appendChild(nodeTraverseFullySelected);
            }
            iIndexOf--;
            nextSibling = nextSibling2;
        }
        if (i != 2) {
            setStartAfter(node);
            collapse(true);
        }
        return documentFragmentCreateDocumentFragment;
    }

    private DocumentFragment traverseCommonStartContainer(Node node, int i) {
        DocumentFragment documentFragmentCreateDocumentFragment = i != 3 ? this.fDocument.createDocumentFragment() : null;
        Node nodeTraverseRightBoundary = traverseRightBoundary(node, i);
        if (documentFragmentCreateDocumentFragment != null) {
            documentFragmentCreateDocumentFragment.appendChild(nodeTraverseRightBoundary);
        }
        int iIndexOf = indexOf(node, this.fStartContainer) - this.fStartOffset;
        if (iIndexOf > 0) {
            Node previousSibling = node.getPreviousSibling();
            while (iIndexOf > 0) {
                Node previousSibling2 = previousSibling.getPreviousSibling();
                Node nodeTraverseFullySelected = traverseFullySelected(previousSibling, i);
                if (documentFragmentCreateDocumentFragment != null) {
                    documentFragmentCreateDocumentFragment.insertBefore(nodeTraverseFullySelected, documentFragmentCreateDocumentFragment.getFirstChild());
                }
                iIndexOf--;
                previousSibling = previousSibling2;
            }
            if (i != 2) {
                setEndBefore(node);
                collapse(false);
            }
        } else if (i != 2) {
            setEndBefore(node);
            collapse(false);
            return documentFragmentCreateDocumentFragment;
        }
        return documentFragmentCreateDocumentFragment;
    }

    private DocumentFragment traverseContents(int i) throws DOMException {
        Node node;
        Node node2 = this.fStartContainer;
        if (node2 != null && (node = this.fEndContainer) != null) {
            if (this.fDetach) {
                zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            } else {
                if (node2 == node) {
                    return traverseSameContainer(i);
                }
                int i2 = 0;
                Node parentNode = node.getParentNode();
                Node node3 = node;
                int i3 = 0;
                while (true) {
                    Node node4 = this.fStartContainer;
                    if (parentNode == null) {
                        Node parentNode2 = node4.getParentNode();
                        while (true) {
                            Node node5 = node4;
                            node4 = parentNode2;
                            if (node4 == null) {
                                int i4 = i2 - i3;
                                Node parentNode3 = this.fStartContainer;
                                while (i4 > 0) {
                                    parentNode3 = parentNode3.getParentNode();
                                    i4--;
                                }
                                Node parentNode4 = this.fEndContainer;
                                while (i4 < 0) {
                                    parentNode4 = parentNode4.getParentNode();
                                    i4++;
                                }
                                Node parentNode5 = parentNode3.getParentNode();
                                Node parentNode6 = parentNode4.getParentNode();
                                while (true) {
                                    Node node6 = parentNode5;
                                    Node node7 = parentNode3;
                                    parentNode3 = node6;
                                    Node node8 = parentNode6;
                                    Node node9 = parentNode4;
                                    parentNode4 = node8;
                                    if (parentNode3 == parentNode4) {
                                        return traverseCommonAncestors(node7, node9, i);
                                    }
                                    parentNode5 = parentNode3.getParentNode();
                                    parentNode6 = parentNode4.getParentNode();
                                }
                            } else {
                                if (node4 == this.fEndContainer) {
                                    return traverseCommonEndContainer(node5, i);
                                }
                                i2++;
                                parentNode2 = node4.getParentNode();
                            }
                        }
                    } else {
                        if (parentNode == node4) {
                            return traverseCommonStartContainer(node3, i);
                        }
                        i3++;
                        Node node10 = parentNode;
                        parentNode = parentNode.getParentNode();
                        node3 = node10;
                    }
                }
            }
        }
        return null;
    }

    private Node traverseFullySelected(Node node, int i) {
        if (i == 1) {
            if (node.getNodeType() != 10) {
                return node;
            }
            zi0.a(3, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
            return null;
        }
        if (i == 2) {
            return node.cloneNode(true);
        }
        if (i != 3) {
            return null;
        }
        node.getParentNode().removeChild(node);
        return null;
    }

    private Node traverseLeftBoundary(Node node, int i) {
        Node selectedNode = getSelectedNode(getStartContainer(), getStartOffset());
        boolean z = selectedNode != getStartContainer();
        if (selectedNode == node) {
            return traverseNode(selectedNode, z, true, i);
        }
        Node parentNode = selectedNode.getParentNode();
        Node nodeTraverseNode = traverseNode(parentNode, false, true, i);
        while (parentNode != null) {
            while (selectedNode != null) {
                Node nextSibling = selectedNode.getNextSibling();
                Node nodeTraverseNode2 = traverseNode(selectedNode, z, true, i);
                if (i != 3) {
                    nodeTraverseNode.appendChild(nodeTraverseNode2);
                }
                z = true;
                selectedNode = nextSibling;
            }
            if (parentNode == node) {
                return nodeTraverseNode;
            }
            selectedNode = parentNode.getNextSibling();
            parentNode = parentNode.getParentNode();
            Node nodeTraverseNode3 = traverseNode(parentNode, false, true, i);
            if (i != 3) {
                nodeTraverseNode3.appendChild(nodeTraverseNode);
            }
            nodeTraverseNode = nodeTraverseNode3;
        }
        return null;
    }

    private Node traverseNode(Node node, boolean z, boolean z2, int i) {
        if (z) {
            return traverseFullySelected(node, i);
        }
        return node.getNodeType() == 3 ? traverseTextNode(node, z2, i) : traversePartiallySelected(node, i);
    }

    private Node traversePartiallySelected(Node node, int i) {
        if (i == 1 || i == 2) {
            return node.cloneNode(false);
        }
        return null;
    }

    private Node traverseRightBoundary(Node node, int i) {
        Node selectedNode = getSelectedNode(this.fEndContainer, this.fEndOffset - 1);
        boolean z = selectedNode != this.fEndContainer;
        if (selectedNode == node) {
            return traverseNode(selectedNode, z, false, i);
        }
        Node parentNode = selectedNode.getParentNode();
        Node nodeTraverseNode = traverseNode(parentNode, false, false, i);
        while (parentNode != null) {
            while (selectedNode != null) {
                Node previousSibling = selectedNode.getPreviousSibling();
                Node nodeTraverseNode2 = traverseNode(selectedNode, z, false, i);
                if (i != 3) {
                    nodeTraverseNode.insertBefore(nodeTraverseNode2, nodeTraverseNode.getFirstChild());
                }
                z = true;
                selectedNode = previousSibling;
            }
            if (parentNode == node) {
                return nodeTraverseNode;
            }
            selectedNode = parentNode.getPreviousSibling();
            parentNode = parentNode.getParentNode();
            Node nodeTraverseNode3 = traverseNode(parentNode, false, false, i);
            if (i != 3) {
                nodeTraverseNode3.appendChild(nodeTraverseNode);
            }
            nodeTraverseNode = nodeTraverseNode3;
        }
        return null;
    }

    private DocumentFragment traverseSameContainer(int i) {
        DocumentFragment documentFragmentCreateDocumentFragment = i != 3 ? this.fDocument.createDocumentFragment() : null;
        if (this.fStartOffset != this.fEndOffset) {
            short nodeType = this.fStartContainer.getNodeType();
            Node node = this.fStartContainer;
            if (nodeType == 3) {
                String strSubstring = node.getNodeValue().substring(this.fStartOffset, this.fEndOffset);
                if (i != 2) {
                    TextImpl textImpl = (TextImpl) this.fStartContainer;
                    int i2 = this.fStartOffset;
                    textImpl.deleteData(i2, this.fEndOffset - i2);
                    collapse(true);
                }
                if (i == 3) {
                    return null;
                }
                documentFragmentCreateDocumentFragment.appendChild(this.fDocument.createTextNode(strSubstring));
                return documentFragmentCreateDocumentFragment;
            }
            Node selectedNode = getSelectedNode(node, this.fStartOffset);
            int i3 = this.fEndOffset - this.fStartOffset;
            while (i3 > 0) {
                Node nextSibling = selectedNode.getNextSibling();
                Node nodeTraverseFullySelected = traverseFullySelected(selectedNode, i);
                if (documentFragmentCreateDocumentFragment != null) {
                    documentFragmentCreateDocumentFragment.appendChild(nodeTraverseFullySelected);
                }
                i3--;
                selectedNode = nextSibling;
            }
            if (i != 2) {
                collapse(true);
            }
        }
        return documentFragmentCreateDocumentFragment;
    }

    private Node traverseTextNode(Node node, boolean z, int i) {
        String strSubstring;
        String strSubstring2;
        String nodeValue = node.getNodeValue();
        if (z) {
            int startOffset = getStartOffset();
            strSubstring = nodeValue.substring(startOffset);
            strSubstring2 = nodeValue.substring(0, startOffset);
        } else {
            int endOffset = getEndOffset();
            strSubstring = nodeValue.substring(0, endOffset);
            strSubstring2 = nodeValue.substring(endOffset);
        }
        if (i != 2) {
            node.setNodeValue(strSubstring2);
        }
        if (i == 3) {
            return null;
        }
        Node nodeCloneNode = node.cloneNode(false);
        nodeCloneNode.setNodeValue(strSubstring);
        return nodeCloneNode;
    }

    public void checkIndex(Node node, int i) throws DOMException {
        if (i < 0) {
            zi0.a(1, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INDEX_SIZE_ERR", null));
            return;
        }
        short nodeType = node.getNodeType();
        if (nodeType == 3 || nodeType == 4 || nodeType == 8 || nodeType == 7) {
            if (i <= node.getNodeValue().length()) {
                return;
            }
            zi0.a(1, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INDEX_SIZE_ERR", null));
        } else {
            if (i <= node.getChildNodes().getLength()) {
                return;
            }
            zi0.a(1, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INDEX_SIZE_ERR", null));
        }
    }

    @Override // org.w3c.dom.ranges.Range
    public DocumentFragment cloneContents() throws DOMException {
        return traverseContents(2);
    }

    @Override // org.w3c.dom.ranges.Range
    public Range cloneRange() {
        if (this.fDetach) {
            zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            return null;
        }
        Range rangeCreateRange = this.fDocument.createRange();
        rangeCreateRange.setStart(this.fStartContainer, this.fStartOffset);
        rangeCreateRange.setEnd(this.fEndContainer, this.fEndOffset);
        return rangeCreateRange;
    }

    @Override // org.w3c.dom.ranges.Range
    public void collapse(boolean z) {
        if (this.fDetach) {
            zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
        } else if (z) {
            this.fEndContainer = this.fStartContainer;
            this.fEndOffset = this.fStartOffset;
        } else {
            this.fStartContainer = this.fEndContainer;
            this.fStartOffset = this.fEndOffset;
        }
    }

    @Override // org.w3c.dom.ranges.Range
    public short compareBoundaryPoints(short s, Range range) throws DOMException {
        Node endContainer;
        Node parentNode;
        int endOffset;
        int i;
        Node node;
        Node node2;
        DocumentImpl documentImpl = this.fDocument;
        int i2 = 0;
        if (documentImpl.errorChecking) {
            if (this.fDetach) {
                zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
                return (short) 0;
            }
            if ((documentImpl != range.getStartContainer().getOwnerDocument() && this.fDocument != range.getStartContainer() && range.getStartContainer() != null) || (this.fDocument != range.getEndContainer().getOwnerDocument() && this.fDocument != range.getEndContainer() && range.getStartContainer() != null)) {
                zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                return (short) 0;
            }
        }
        if (s == 0) {
            endContainer = range.getStartContainer();
            parentNode = this.fStartContainer;
            endOffset = range.getStartOffset();
            i = this.fStartOffset;
        } else if (s == 1) {
            endContainer = range.getStartContainer();
            parentNode = this.fEndContainer;
            endOffset = range.getStartOffset();
            i = this.fEndOffset;
        } else if (s == 3) {
            endContainer = range.getEndContainer();
            parentNode = this.fStartContainer;
            endOffset = range.getEndOffset();
            i = this.fStartOffset;
        } else {
            endContainer = range.getEndContainer();
            parentNode = this.fEndContainer;
            endOffset = range.getEndOffset();
            i = this.fEndOffset;
        }
        if (endContainer == parentNode) {
            if (endOffset < i) {
                return (short) 1;
            }
            return endOffset == i ? (short) 0 : (short) -1;
        }
        Node node3 = parentNode;
        for (Node parentNode2 = parentNode.getParentNode(); parentNode2 != null; parentNode2 = parentNode2.getParentNode()) {
            if (parentNode2 == endContainer) {
                return endOffset <= indexOf(node3, endContainer) ? (short) 1 : (short) -1;
            }
            node3 = parentNode2;
        }
        Node node4 = endContainer;
        for (Node parentNode3 = endContainer.getParentNode(); parentNode3 != null; parentNode3 = parentNode3.getParentNode()) {
            if (parentNode3 == parentNode) {
                return indexOf(node4, parentNode) < i ? (short) 1 : (short) -1;
            }
            node4 = parentNode3;
        }
        for (Node parentNode4 = endContainer; parentNode4 != null; parentNode4 = parentNode4.getParentNode()) {
            i2++;
        }
        for (Node parentNode5 = parentNode; parentNode5 != null; parentNode5 = parentNode5.getParentNode()) {
            i2--;
        }
        while (i2 > 0) {
            endContainer = endContainer.getParentNode();
            i2--;
        }
        while (i2 < 0) {
            parentNode = parentNode.getParentNode();
            i2++;
        }
        Node parentNode6 = endContainer.getParentNode();
        Node parentNode7 = parentNode.getParentNode();
        while (true) {
            node = endContainer;
            endContainer = parentNode6;
            node2 = parentNode;
            parentNode = parentNode7;
            if (endContainer == parentNode) {
                break;
            }
            parentNode6 = endContainer.getParentNode();
            parentNode7 = parentNode.getParentNode();
        }
        for (Node nextSibling = node.getNextSibling(); nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
            if (nextSibling == node2) {
                return (short) 1;
            }
        }
        return (short) -1;
    }

    @Override // org.w3c.dom.ranges.Range
    public void deleteContents() throws DOMException {
        traverseContents(3);
    }

    public void deleteData(CharacterData characterData, int i, int i2) {
        this.fDeleteNode = characterData;
        characterData.deleteData(i, i2);
        this.fDeleteNode = null;
    }

    @Override // org.w3c.dom.ranges.Range
    public void detach() {
        if (this.fDetach) {
            zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
        } else {
            this.fDetach = true;
            this.fDocument.removeRange(this);
        }
    }

    @Override // org.w3c.dom.ranges.Range
    public DocumentFragment extractContents() throws DOMException {
        return traverseContents(1);
    }

    @Override // org.w3c.dom.ranges.Range
    public boolean getCollapsed() {
        if (!this.fDetach) {
            return this.fStartContainer == this.fEndContainer && this.fStartOffset == this.fEndOffset;
        }
        zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
        return false;
    }

    @Override // org.w3c.dom.ranges.Range
    public Node getCommonAncestorContainer() {
        Node node = null;
        if (this.fDetach) {
            zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Node parentNode = this.fStartContainer; parentNode != null; parentNode = parentNode.getParentNode()) {
            arrayList.add(parentNode);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Node parentNode2 = this.fEndContainer; parentNode2 != null; parentNode2 = parentNode2.getParentNode()) {
            arrayList2.add(parentNode2);
        }
        int size = arrayList.size() - 1;
        for (int size2 = arrayList2.size() - 1; size >= 0 && size2 >= 0 && arrayList.get(size) == arrayList2.get(size2); size2--) {
            node = (Node) arrayList.get(size);
            size--;
        }
        return node;
    }

    @Override // org.w3c.dom.ranges.Range
    public Node getEndContainer() {
        if (!this.fDetach) {
            return this.fEndContainer;
        }
        zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
        return null;
    }

    @Override // org.w3c.dom.ranges.Range
    public int getEndOffset() {
        if (!this.fDetach) {
            return this.fEndOffset;
        }
        zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
        return 0;
    }

    @Override // org.w3c.dom.ranges.Range
    public Node getStartContainer() {
        if (!this.fDetach) {
            return this.fStartContainer;
        }
        zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
        return null;
    }

    @Override // org.w3c.dom.ranges.Range
    public int getStartOffset() {
        if (!this.fDetach) {
            return this.fStartOffset;
        }
        zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
        return 0;
    }

    public int indexOf(Node node, Node node2) {
        if (node.getParentNode() != node2) {
            return -1;
        }
        int i = 0;
        for (Node firstChild = node2.getFirstChild(); firstChild != node; firstChild = firstChild.getNextSibling()) {
            i++;
        }
        return i;
    }

    public void insertData(CharacterData characterData, int i, String str) {
        this.fInsertNode = characterData;
        characterData.insertData(i, str);
        this.fInsertNode = null;
    }

    @Override // org.w3c.dom.ranges.Range
    public void insertNode(Node node) throws RangeException, DOMException {
        int i;
        if (node == null) {
            return;
        }
        short nodeType = node.getNodeType();
        DocumentImpl documentImpl = this.fDocument;
        if (documentImpl.errorChecking) {
            if (this.fDetach) {
                zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
                return;
            }
            if (documentImpl != node.getOwnerDocument()) {
                zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                return;
            } else if (nodeType == 2 || nodeType == 6 || nodeType == 12 || nodeType == 9) {
                throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
            }
        }
        this.fInsertedFromRange = true;
        if (this.fStartContainer.getNodeType() == 3) {
            Node parentNode = this.fStartContainer.getParentNode();
            int length = parentNode.getChildNodes().getLength();
            Node nodeCloneNode = this.fStartContainer.cloneNode(false);
            ((TextImpl) nodeCloneNode).setNodeValueInternal(nodeCloneNode.getNodeValue().substring(this.fStartOffset));
            Node node2 = this.fStartContainer;
            ((TextImpl) node2).setNodeValueInternal(node2.getNodeValue().substring(0, this.fStartOffset));
            Node nextSibling = this.fStartContainer.getNextSibling();
            if (nextSibling != null) {
                parentNode.insertBefore(node, nextSibling);
                parentNode.insertBefore(nodeCloneNode, nextSibling);
            } else {
                parentNode.appendChild(node);
                parentNode.appendChild(nodeCloneNode);
            }
            Node node3 = this.fEndContainer;
            if (node3 == this.fStartContainer) {
                this.fEndContainer = nodeCloneNode;
                this.fEndOffset -= this.fStartOffset;
            } else if (node3 == parentNode) {
                this.fEndOffset += parentNode.getChildNodes().getLength() - length;
            }
            signalSplitData(this.fStartContainer, nodeCloneNode, this.fStartOffset);
        } else {
            Node node4 = this.fEndContainer;
            int length2 = node4 == this.fStartContainer ? node4.getChildNodes().getLength() : 0;
            Node firstChild = this.fStartContainer.getFirstChild();
            for (int i2 = 0; i2 < this.fStartOffset && firstChild != null; i2++) {
                firstChild = firstChild.getNextSibling();
            }
            Node node5 = this.fStartContainer;
            if (firstChild != null) {
                node5.insertBefore(node, firstChild);
            } else {
                node5.appendChild(node);
            }
            Node node6 = this.fEndContainer;
            if (node6 == this.fStartContainer && (i = this.fEndOffset) != 0) {
                this.fEndOffset = i + (node6.getChildNodes().getLength() - length2);
            }
        }
        this.fInsertedFromRange = false;
    }

    public void insertedNodeFromDOM(Node node) {
        if (node == null || this.fInsertNode == node || this.fInsertedFromRange) {
            return;
        }
        Node parentNode = node.getParentNode();
        Node node2 = this.fStartContainer;
        if (parentNode == node2) {
            int iIndexOf = indexOf(node, node2);
            int i = this.fStartOffset;
            if (iIndexOf < i) {
                this.fStartOffset = i + 1;
            }
        }
        Node node3 = this.fEndContainer;
        if (parentNode == node3) {
            int iIndexOf2 = indexOf(node, node3);
            int i2 = this.fEndOffset;
            if (iIndexOf2 < i2) {
                this.fEndOffset = i2 + 1;
            }
        }
    }

    public boolean isAncestorOf(Node node, Node node2) {
        while (node2 != null) {
            if (node2 == node) {
                return true;
            }
            node2 = node2.getParentNode();
        }
        return false;
    }

    public Node nextNode(Node node, boolean z) {
        Node firstChild;
        if (node == null) {
            return null;
        }
        if (z && (firstChild = node.getFirstChild()) != null) {
            return firstChild;
        }
        Node nextSibling = node.getNextSibling();
        if (nextSibling != null) {
            return nextSibling;
        }
        for (Node parentNode = node.getParentNode(); parentNode != null && parentNode != this.fDocument; parentNode = parentNode.getParentNode()) {
            Node nextSibling2 = parentNode.getNextSibling();
            if (nextSibling2 != null) {
                return nextSibling2;
            }
        }
        return null;
    }

    public void receiveDeletedText(Node node, int i, int i2) {
        if (node == null || this.fDeleteNode == node) {
            return;
        }
        Node node2 = this.fStartContainer;
        if (node == node2 && node2.getNodeType() == 3) {
            int i3 = this.fStartOffset;
            int i4 = i + i2;
            if (i3 > i4) {
                this.fStartOffset = (i3 - i4) + i;
            } else if (i3 > i) {
                this.fStartOffset = i;
            }
        }
        Node node3 = this.fEndContainer;
        if (node == node3 && node3.getNodeType() == 3) {
            int i5 = this.fEndOffset;
            int i6 = i2 + i;
            if (i5 > i6) {
                this.fEndOffset = i + (i5 - i6);
            } else if (i5 > i) {
                this.fEndOffset = i;
            }
        }
    }

    public void receiveInsertedText(Node node, int i, int i2) {
        int i3;
        int i4;
        if (node == null || this.fInsertNode == node) {
            return;
        }
        Node node2 = this.fStartContainer;
        if (node == node2 && node2.getNodeType() == 3 && i < (i4 = this.fStartOffset)) {
            this.fStartOffset = i4 + i2;
        }
        Node node3 = this.fEndContainer;
        if (node == node3 && node3.getNodeType() == 3 && i < (i3 = this.fEndOffset)) {
            this.fEndOffset = i3 + i2;
        }
    }

    public void receiveReplacedText(Node node) {
        if (node == null) {
            return;
        }
        Node node2 = this.fStartContainer;
        if (node == node2 && node2.getNodeType() == 3) {
            this.fStartOffset = 0;
        }
        Node node3 = this.fEndContainer;
        if (node == node3 && node3.getNodeType() == 3) {
            this.fEndOffset = 0;
        }
    }

    public void receiveSplitData(Node node, Node node2, int i) {
        int i2;
        int i3;
        if (node == null || node2 == null || this.fSplitNode == node) {
            return;
        }
        Node node3 = this.fStartContainer;
        if (node == node3 && node3.getNodeType() == 3 && (i3 = this.fStartOffset) > i) {
            this.fStartOffset = i3 - i;
            this.fStartContainer = node2;
        }
        Node node4 = this.fEndContainer;
        if (node == node4 && node4.getNodeType() == 3 && (i2 = this.fEndOffset) > i) {
            this.fEndOffset = i2 - i;
            this.fEndContainer = node2;
        }
    }

    public Node removeChild(Node node, Node node2) {
        this.fRemoveChild = node2;
        Node nodeRemoveChild = node.removeChild(node2);
        this.fRemoveChild = null;
        return nodeRemoveChild;
    }

    public void removeNode(Node node) {
        if (node == null || this.fRemoveChild == node) {
            return;
        }
        Node parentNode = node.getParentNode();
        Node node2 = this.fStartContainer;
        if (parentNode == node2) {
            int iIndexOf = indexOf(node, node2);
            int i = this.fStartOffset;
            if (iIndexOf < i) {
                this.fStartOffset = i - 1;
            }
        }
        Node node3 = this.fEndContainer;
        if (parentNode == node3) {
            int iIndexOf2 = indexOf(node, node3);
            int i2 = this.fEndOffset;
            if (iIndexOf2 < i2) {
                this.fEndOffset = i2 - 1;
            }
        }
        Node node4 = this.fStartContainer;
        if (parentNode == node4 && parentNode == this.fEndContainer) {
            return;
        }
        if (isAncestorOf(node, node4)) {
            this.fStartContainer = parentNode;
            this.fStartOffset = indexOf(node, parentNode);
        }
        if (isAncestorOf(node, this.fEndContainer)) {
            this.fEndContainer = parentNode;
            this.fEndOffset = indexOf(node, parentNode);
        }
    }

    @Override // org.w3c.dom.ranges.Range
    public void selectNode(Node node) throws RangeException {
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
                return;
            } else {
                if (!isLegalContainer(node.getParentNode()) || !isLegalContainedNode(node)) {
                    throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
                }
                if (this.fDocument != node.getOwnerDocument() && this.fDocument != node) {
                    zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                    return;
                }
            }
        }
        Node parentNode = node.getParentNode();
        if (parentNode != null) {
            this.fStartContainer = parentNode;
            this.fEndContainer = parentNode;
            int i = 0;
            while (node != null) {
                i++;
                node = node.getPreviousSibling();
            }
            this.fStartOffset = i - 1;
            this.fEndOffset = i;
        }
    }

    @Override // org.w3c.dom.ranges.Range
    public void selectNodeContents(Node node) throws RangeException {
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
                return;
            } else {
                if (!isLegalContainer(node)) {
                    throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
                }
                if (this.fDocument != node.getOwnerDocument() && this.fDocument != node) {
                    zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                    return;
                }
            }
        }
        this.fStartContainer = node;
        this.fEndContainer = node;
        Node firstChild = node.getFirstChild();
        int i = 0;
        this.fStartOffset = 0;
        if (firstChild == null) {
            this.fEndOffset = 0;
            return;
        }
        while (firstChild != null) {
            i++;
            firstChild = firstChild.getNextSibling();
        }
        this.fEndOffset = i;
    }

    @Override // org.w3c.dom.ranges.Range
    public void setEnd(Node node, int i) throws RangeException, DOMException {
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
                return;
            } else {
                if (!isLegalContainer(node)) {
                    throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
                }
                if (this.fDocument != node.getOwnerDocument() && this.fDocument != node) {
                    zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                    return;
                }
            }
        }
        checkIndex(node, i);
        this.fEndContainer = node;
        this.fEndOffset = i;
        if (getCommonAncestorContainer() == null || (this.fStartContainer == this.fEndContainer && this.fEndOffset < this.fStartOffset)) {
            collapse(false);
        }
    }

    @Override // org.w3c.dom.ranges.Range
    public void setEndAfter(Node node) throws RangeException {
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
                return;
            } else {
                if (!hasLegalRootContainer(node) || !isLegalContainedNode(node)) {
                    throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
                }
                if (this.fDocument != node.getOwnerDocument() && this.fDocument != node) {
                    zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                    return;
                }
            }
        }
        this.fEndContainer = node.getParentNode();
        int i = 0;
        while (node != null) {
            i++;
            node = node.getPreviousSibling();
        }
        this.fEndOffset = i;
        if (getCommonAncestorContainer() == null || (this.fStartContainer == this.fEndContainer && this.fEndOffset < this.fStartOffset)) {
            collapse(false);
        }
    }

    @Override // org.w3c.dom.ranges.Range
    public void setEndBefore(Node node) throws RangeException {
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
                return;
            } else {
                if (!hasLegalRootContainer(node) || !isLegalContainedNode(node)) {
                    throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
                }
                if (this.fDocument != node.getOwnerDocument() && this.fDocument != node) {
                    zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                    return;
                }
            }
        }
        this.fEndContainer = node.getParentNode();
        int i = 0;
        while (node != null) {
            i++;
            node = node.getPreviousSibling();
        }
        this.fEndOffset = i - 1;
        if (getCommonAncestorContainer() == null || (this.fStartContainer == this.fEndContainer && this.fEndOffset < this.fStartOffset)) {
            collapse(false);
        }
    }

    @Override // org.w3c.dom.ranges.Range
    public void setStart(Node node, int i) throws RangeException, DOMException {
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
                return;
            } else {
                if (!isLegalContainer(node)) {
                    throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
                }
                if (this.fDocument != node.getOwnerDocument() && this.fDocument != node) {
                    zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                    return;
                }
            }
        }
        checkIndex(node, i);
        this.fStartContainer = node;
        this.fStartOffset = i;
        if (getCommonAncestorContainer() == null || (this.fStartContainer == this.fEndContainer && this.fEndOffset < this.fStartOffset)) {
            collapse(true);
        }
    }

    @Override // org.w3c.dom.ranges.Range
    public void setStartAfter(Node node) throws RangeException {
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
                return;
            } else {
                if (!hasLegalRootContainer(node) || !isLegalContainedNode(node)) {
                    throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
                }
                if (this.fDocument != node.getOwnerDocument() && this.fDocument != node) {
                    zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                    return;
                }
            }
        }
        this.fStartContainer = node.getParentNode();
        int i = 0;
        while (node != null) {
            i++;
            node = node.getPreviousSibling();
        }
        this.fStartOffset = i;
        if (getCommonAncestorContainer() == null || (this.fStartContainer == this.fEndContainer && this.fEndOffset < this.fStartOffset)) {
            collapse(true);
        }
    }

    @Override // org.w3c.dom.ranges.Range
    public void setStartBefore(Node node) throws RangeException {
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
                return;
            } else {
                if (!hasLegalRootContainer(node) || !isLegalContainedNode(node)) {
                    throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
                }
                if (this.fDocument != node.getOwnerDocument() && this.fDocument != node) {
                    zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                    return;
                }
            }
        }
        this.fStartContainer = node.getParentNode();
        int i = 0;
        while (node != null) {
            i++;
            node = node.getPreviousSibling();
        }
        this.fStartOffset = i - 1;
        if (getCommonAncestorContainer() == null || (this.fStartContainer == this.fEndContainer && this.fEndOffset < this.fStartOffset)) {
            collapse(true);
        }
    }

    public void signalSplitData(Node node, Node node2, int i) {
        this.fSplitNode = node;
        this.fDocument.splitData(node, node2, i);
        this.fSplitNode = null;
    }

    @Override // org.w3c.dom.ranges.Range
    public void surroundContents(Node node) throws RangeException, DOMException {
        if (node == null) {
            return;
        }
        short nodeType = node.getNodeType();
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
                return;
            } else if (nodeType == 2 || nodeType == 6 || nodeType == 12 || nodeType == 10 || nodeType == 9 || nodeType == 11) {
                throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
            }
        }
        Node parentNode = this.fStartContainer;
        Node parentNode2 = this.fEndContainer;
        if (parentNode.getNodeType() == 3) {
            parentNode = this.fStartContainer.getParentNode();
        }
        if (this.fEndContainer.getNodeType() == 3) {
            parentNode2 = this.fEndContainer.getParentNode();
        }
        if (parentNode != parentNode2) {
            throw new RangeExceptionImpl((short) 1, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "BAD_BOUNDARYPOINTS_ERR", null));
        }
        DocumentFragment documentFragmentExtractContents = extractContents();
        insertNode(node);
        node.appendChild(documentFragmentExtractContents);
        selectNode(node);
    }

    @Override // org.w3c.dom.ranges.Range
    public String toString() {
        Node nodeNextNode;
        if (this.fDetach) {
            zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            return null;
        }
        Node node = this.fStartContainer;
        Node nodeNextNode2 = this.fEndContainer;
        StringBuffer stringBuffer = new StringBuffer();
        if (this.fStartContainer.getNodeType() == 3 || this.fStartContainer.getNodeType() == 4) {
            Node node2 = this.fStartContainer;
            if (node2 == this.fEndContainer) {
                stringBuffer.append(node2.getNodeValue().substring(this.fStartOffset, this.fEndOffset));
                return stringBuffer.toString();
            }
            stringBuffer.append(node2.getNodeValue().substring(this.fStartOffset));
            nodeNextNode = nextNode(node, true);
        } else {
            nodeNextNode = node.getFirstChild();
            if (this.fStartOffset > 0) {
                for (int i = 0; i < this.fStartOffset && nodeNextNode != null; i++) {
                    nodeNextNode = nodeNextNode.getNextSibling();
                }
            }
            if (nodeNextNode == null) {
                nodeNextNode = nextNode(this.fStartContainer, false);
            }
        }
        if (this.fEndContainer.getNodeType() != 3 && this.fEndContainer.getNodeType() != 4) {
            int i2 = this.fEndOffset;
            Node firstChild = this.fEndContainer.getFirstChild();
            while (i2 > 0 && firstChild != null) {
                i2--;
                firstChild = firstChild.getNextSibling();
            }
            nodeNextNode2 = firstChild == null ? nextNode(this.fEndContainer, false) : firstChild;
        }
        while (nodeNextNode != nodeNextNode2 && nodeNextNode != null) {
            if (nodeNextNode.getNodeType() == 3 || nodeNextNode.getNodeType() == 4) {
                stringBuffer.append(nodeNextNode.getNodeValue());
            }
            nodeNextNode = nextNode(nodeNextNode, true);
        }
        if (this.fEndContainer.getNodeType() == 3 || this.fEndContainer.getNodeType() == 4) {
            stringBuffer.append(this.fEndContainer.getNodeValue().substring(0, this.fEndOffset));
        }
        return stringBuffer.toString();
    }
}
