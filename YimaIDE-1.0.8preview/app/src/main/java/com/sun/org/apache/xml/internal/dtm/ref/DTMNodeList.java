package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTMNodeList extends DTMNodeListBase {
    private DTMIterator m_iter;

    public DTMNodeList(DTMIterator dTMIterator) {
        if (dTMIterator != null) {
            int currentPos = dTMIterator.getCurrentPos();
            try {
                this.m_iter = dTMIterator.cloneWithReset();
            } catch (CloneNotSupportedException unused) {
                this.m_iter = dTMIterator;
            }
            this.m_iter.setShouldCacheNodes(true);
            this.m_iter.runTo(-1);
            this.m_iter.setCurrentPos(currentPos);
        }
    }

    public DTMIterator getDTMIterator() {
        return this.m_iter;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMNodeListBase, org.w3c.dom.NodeList
    public int getLength() {
        DTMIterator dTMIterator = this.m_iter;
        if (dTMIterator != null) {
            return dTMIterator.getLength();
        }
        return 0;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMNodeListBase, org.w3c.dom.NodeList
    public Node item(int i) {
        int iItem;
        DTMIterator dTMIterator = this.m_iter;
        if (dTMIterator == null || (iItem = dTMIterator.item(i)) == -1) {
            return null;
        }
        return this.m_iter.getDTM(iItem).getNode(iItem);
    }

    private DTMNodeList() {
    }
}
