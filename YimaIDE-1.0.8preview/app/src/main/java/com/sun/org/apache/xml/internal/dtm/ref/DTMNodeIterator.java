package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.dtm.DTMDOMException;
import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTMNodeIterator implements NodeIterator {
    private DTMIterator dtm_iter;
    private boolean valid = true;

    public DTMNodeIterator(DTMIterator dTMIterator) {
        try {
            this.dtm_iter = (DTMIterator) dTMIterator.clone();
        } catch (CloneNotSupportedException e) {
            throw new WrappedRuntimeException(e);
        }
    }

    @Override // org.w3c.dom.traversal.NodeIterator
    public void detach() {
        this.valid = false;
    }

    public DTMIterator getDTMIterator() {
        return this.dtm_iter;
    }

    @Override // org.w3c.dom.traversal.NodeIterator
    public boolean getExpandEntityReferences() {
        return false;
    }

    @Override // org.w3c.dom.traversal.NodeIterator
    public NodeFilter getFilter() {
        throw new DTMDOMException((short) 9);
    }

    @Override // org.w3c.dom.traversal.NodeIterator
    public Node getRoot() {
        int root = this.dtm_iter.getRoot();
        return this.dtm_iter.getDTM(root).getNode(root);
    }

    @Override // org.w3c.dom.traversal.NodeIterator
    public int getWhatToShow() {
        return this.dtm_iter.getWhatToShow();
    }

    @Override // org.w3c.dom.traversal.NodeIterator
    public Node nextNode() throws DOMException {
        if (!this.valid) {
            throw new DTMDOMException((short) 11);
        }
        int iNextNode = this.dtm_iter.nextNode();
        if (iNextNode == -1) {
            return null;
        }
        return this.dtm_iter.getDTM(iNextNode).getNode(iNextNode);
    }

    @Override // org.w3c.dom.traversal.NodeIterator
    public Node previousNode() {
        if (!this.valid) {
            throw new DTMDOMException((short) 11);
        }
        int iPreviousNode = this.dtm_iter.previousNode();
        if (iPreviousNode == -1) {
            return null;
        }
        return this.dtm_iter.getDTM(iPreviousNode).getNode(iPreviousNode);
    }
}
