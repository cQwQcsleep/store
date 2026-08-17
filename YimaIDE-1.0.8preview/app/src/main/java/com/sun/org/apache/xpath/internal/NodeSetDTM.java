package com.sun.org.apache.xpath.internal;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMFilter;
import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xml.internal.utils.NodeVector;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NodeSetDTM extends NodeVector implements DTMIterator, Cloneable {
    static final long serialVersionUID = 7686480133331317070L;
    protected transient boolean m_cacheNodes;
    private transient int m_last;
    DTMManager m_manager;
    protected transient boolean m_mutable;
    protected transient int m_next;
    protected int m_root;

    public NodeSetDTM(NodeList nodeList, XPathContext xPathContext) {
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_root = -1;
        this.m_last = 0;
        this.m_manager = xPathContext.getDTMManager();
        int length = nodeList.getLength();
        for (int i = 0; i < length; i++) {
            addNode(xPathContext.getDTMHandleFromNode(nodeList.item(i)));
        }
    }

    @Override // com.sun.org.apache.xml.internal.utils.NodeVector
    public void addElement(int i) {
        if (this.m_mutable) {
            super.addElement(i);
        } else {
            f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
    }

    public void addNode(int i) {
        if (this.m_mutable) {
            addElement(i);
        } else {
            f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
    }

    public int addNodeInDocOrder(int i, boolean z, XPathContext xPathContext) {
        int iElementAt;
        if (!this.m_mutable) {
            f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
            return 0;
        }
        if (!z) {
            int size = size();
            for (int i2 = 0; i2 < size; i2++) {
                if (i2 == i) {
                    return size;
                }
            }
            addElement(i);
            return size;
        }
        int size2 = size();
        do {
            size2--;
            if (size2 < 0) {
                break;
            }
            iElementAt = elementAt(size2);
            if (iElementAt == i) {
                size2 = -2;
                break;
            }
        } while (xPathContext.getDTM(i).isNodeAfter(i, iElementAt));
        if (size2 == -2) {
            return -1;
        }
        int i3 = size2 + 1;
        insertElementAt(i, i3);
        return i3;
    }

    public void addNodes(DTMIterator dTMIterator) {
        if (!this.m_mutable) {
            f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        } else {
            if (dTMIterator == null) {
                return;
            }
            while (true) {
                int iNextNode = dTMIterator.nextNode();
                if (-1 == iNextNode) {
                    return;
                } else {
                    addElement(iNextNode);
                }
            }
        }
    }

    public void addNodesInDocOrder(DTMIterator dTMIterator, XPathContext xPathContext) {
        if (!this.m_mutable) {
            f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
            return;
        }
        while (true) {
            int iNextNode = dTMIterator.nextNode();
            if (-1 == iNextNode) {
                return;
            } else {
                addNodeInDocOrder(iNextNode, xPathContext);
            }
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void allowDetachToRelease(boolean z) {
    }

    @Override // com.sun.org.apache.xml.internal.utils.NodeVector
    public void appendNodes(NodeVector nodeVector) {
        if (this.m_mutable) {
            super.appendNodes(nodeVector);
        } else {
            f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
    }

    @Override // com.sun.org.apache.xml.internal.utils.NodeVector, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public Object clone() throws CloneNotSupportedException {
        return (NodeSetDTM) super.clone();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public DTMIterator cloneWithReset() throws CloneNotSupportedException {
        NodeSetDTM nodeSetDTM = (NodeSetDTM) clone();
        nodeSetDTM.reset();
        return nodeSetDTM;
    }

    @Override // com.sun.org.apache.xml.internal.utils.NodeVector
    public boolean contains(int i) {
        runTo(-1);
        return super.contains(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void detach() {
    }

    @Override // com.sun.org.apache.xml.internal.utils.NodeVector
    public int elementAt(int i) {
        runTo(i);
        return super.elementAt(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getAxis() {
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getCurrentNode() {
        if (!this.m_cacheNodes) {
            f63.a("This NodeSetDTM can not do indexing or counting functions!");
            return 0;
        }
        int i = this.m_next;
        int i2 = i > 0 ? i - 1 : i;
        int iElementAt = i2 < this.m_firstFree ? elementAt(i2) : -1;
        this.m_next = i;
        return iElementAt;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getCurrentPos() {
        return this.m_next;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public DTM getDTM(int i) {
        return this.m_manager.getDTM(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public DTMManager getDTMManager() {
        return this.m_manager;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public boolean getExpandEntityReferences() {
        return true;
    }

    public DTMFilter getFilter() {
        return null;
    }

    public int getLast() {
        return this.m_last;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getLength() {
        runTo(-1);
        return size();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getRoot() {
        int i = this.m_root;
        if (-1 != i) {
            return i;
        }
        if (size() > 0) {
            return item(0);
        }
        return -1;
    }

    public boolean getShouldCacheNodes() {
        return this.m_cacheNodes;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getWhatToShow() {
        return -17;
    }

    @Override // com.sun.org.apache.xml.internal.utils.NodeVector
    public int indexOf(int i, int i2) {
        runTo(-1);
        return super.indexOf(i, i2);
    }

    @Override // com.sun.org.apache.xml.internal.utils.NodeVector
    public void insertElementAt(int i, int i2) {
        if (this.m_mutable) {
            super.insertElementAt(i, i2);
        } else {
            f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
    }

    public void insertNode(int i, int i2) {
        if (this.m_mutable) {
            insertElementAt(i, i2);
        } else {
            f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public boolean isDocOrdered() {
        return true;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public boolean isFresh() {
        return this.m_next == 0;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public boolean isMutable() {
        return this.m_mutable;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int item(int i) {
        runTo(i);
        return elementAt(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int nextNode() {
        if (this.m_next >= size()) {
            return -1;
        }
        int iElementAt = elementAt(this.m_next);
        this.m_next++;
        return iElementAt;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int previousNode() {
        if (!this.m_cacheNodes) {
            f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_CANNOT_ITERATE", null));
            return 0;
        }
        int i = this.m_next;
        if (i - 1 <= 0) {
            return -1;
        }
        int i2 = i - 1;
        this.m_next = i2;
        return elementAt(i2);
    }

    @Override // com.sun.org.apache.xml.internal.utils.NodeVector
    public void removeAllElements() {
        if (this.m_mutable) {
            super.removeAllElements();
        } else {
            f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
    }

    @Override // com.sun.org.apache.xml.internal.utils.NodeVector
    public boolean removeElement(int i) {
        if (this.m_mutable) {
            return super.removeElement(i);
        }
        f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.utils.NodeVector
    public void removeElementAt(int i) {
        if (this.m_mutable) {
            super.removeElementAt(i);
        } else {
            f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
    }

    public void removeNode(int i) {
        if (this.m_mutable) {
            removeElement(i);
        } else {
            f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void reset() {
        this.m_next = 0;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void runTo(int i) {
        if (!this.m_cacheNodes) {
            f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_CANNOT_INDEX", null));
        } else if (i < 0 || this.m_next >= this.m_firstFree) {
            this.m_next = this.m_firstFree - 1;
        } else {
            this.m_next = i;
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void setCurrentPos(int i) {
        if (this.m_cacheNodes) {
            this.m_next = i;
        } else {
            f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_CANNOT_INDEX", null));
        }
    }

    @Override // com.sun.org.apache.xml.internal.utils.NodeVector
    public void setElementAt(int i, int i2) {
        if (this.m_mutable) {
            super.setElementAt(i, i2);
        } else {
            f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
    }

    public void setEnvironment(Object obj) {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void setItem(int i, int i2) {
        if (this.m_mutable) {
            super.setElementAt(i, i2);
        } else {
            f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
    }

    public void setLast(int i) {
        this.m_last = i;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void setRoot(int i, Object obj) {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void setShouldCacheNodes(boolean z) {
        if (!isFresh()) {
            f63.a(XPATHMessages.createXPATHMessage("ER_CANNOT_CALL_SETSHOULDCACHENODE", null));
        } else {
            this.m_cacheNodes = z;
            this.m_mutable = true;
        }
    }

    @Override // com.sun.org.apache.xml.internal.utils.NodeVector
    public int size() {
        return super.size();
    }

    @Override // com.sun.org.apache.xml.internal.utils.NodeVector
    public int indexOf(int i) {
        runTo(-1);
        return super.indexOf(i);
    }

    public NodeSetDTM(int i, int i2, DTMManager dTMManager) {
        super(i);
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_root = -1;
        this.m_last = 0;
        this.m_manager = dTMManager;
    }

    public NodeSetDTM(NodeSetDTM nodeSetDTM) {
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_root = -1;
        this.m_last = 0;
        this.m_manager = nodeSetDTM.getDTMManager();
        this.m_root = nodeSetDTM.getRoot();
        addNodes(nodeSetDTM);
    }

    public NodeSetDTM(DTMIterator dTMIterator) {
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_root = -1;
        this.m_last = 0;
        this.m_manager = dTMIterator.getDTMManager();
        this.m_root = dTMIterator.getRoot();
        addNodes(dTMIterator);
    }

    public NodeSetDTM(NodeIterator nodeIterator, XPathContext xPathContext) {
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_root = -1;
        this.m_last = 0;
        this.m_manager = xPathContext.getDTMManager();
        while (true) {
            Node nodeNextNode = nodeIterator.nextNode();
            if (nodeNextNode == null) {
                return;
            } else {
                addNodeInDocOrder(xPathContext.getDTMHandleFromNode(nodeNextNode), xPathContext);
            }
        }
    }

    public int addNodeInDocOrder(int i, XPathContext xPathContext) {
        if (this.m_mutable) {
            return addNodeInDocOrder(i, true, xPathContext);
        }
        f63.a(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        return 0;
    }

    public NodeSetDTM(DTMManager dTMManager) {
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_root = -1;
        this.m_last = 0;
        this.m_manager = dTMManager;
    }

    public NodeSetDTM(int i, DTMManager dTMManager) {
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_root = -1;
        this.m_last = 0;
        this.m_manager = dTMManager;
        addNode(i);
    }
}
