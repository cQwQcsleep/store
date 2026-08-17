package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.utils.IntVector;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTMAxisIterNodeList extends DTMNodeListBase {
    private IntVector m_cachedNodes;
    private DTM m_dtm;
    private DTMAxisIterator m_iter;
    private int m_last;

    public DTMAxisIterNodeList(DTM dtm, DTMAxisIterator dTMAxisIterator) {
        this.m_last = -1;
        if (dTMAxisIterator == null) {
            this.m_last = 0;
        } else {
            this.m_cachedNodes = new IntVector();
            this.m_dtm = dtm;
        }
        this.m_iter = dTMAxisIterator;
    }

    public DTMAxisIterator getDTMAxisIterator() {
        return this.m_iter;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMNodeListBase, org.w3c.dom.NodeList
    public int getLength() {
        IntVector intVector;
        if (this.m_last == -1) {
            while (true) {
                int next = this.m_iter.next();
                intVector = this.m_cachedNodes;
                if (next == -1) {
                    break;
                }
                intVector.addElement(next);
            }
            this.m_last = intVector.size();
        }
        return this.m_last;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMNodeListBase, org.w3c.dom.NodeList
    public Node item(int i) {
        if (this.m_iter == null) {
            return null;
        }
        int size = this.m_cachedNodes.size();
        if (size > i) {
            return this.m_dtm.getNode(this.m_cachedNodes.elementAt(i));
        }
        if (this.m_last != -1) {
            return null;
        }
        int next = 0;
        while (size <= i) {
            next = this.m_iter.next();
            if (next == -1) {
                break;
            }
            this.m_cachedNodes.addElement(next);
            size++;
        }
        if (next != -1) {
            return this.m_dtm.getNode(next);
        }
        this.m_last = size;
        return null;
    }

    private DTMAxisIterNodeList() {
        this.m_last = -1;
    }
}
