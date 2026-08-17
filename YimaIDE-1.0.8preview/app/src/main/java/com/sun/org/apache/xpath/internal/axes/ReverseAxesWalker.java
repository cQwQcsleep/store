package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xpath.internal.XPathContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ReverseAxesWalker extends AxesWalker {
    static final long serialVersionUID = 2847007647832768941L;
    protected DTMAxisIterator m_iterator;

    public ReverseAxesWalker(LocPathIterator locPathIterator, int i) {
        super(locPathIterator, i);
    }

    @Override // com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest
    public void countProximityPosition(int i) {
        int[] iArr = this.m_proximityPositions;
        if (i < iArr.length) {
            iArr[i] = iArr[i] - 1;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.axes.AxesWalker
    public void detach() {
        this.m_iterator = null;
        super.detach();
    }

    @Override // com.sun.org.apache.xpath.internal.axes.AxesWalker, com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest, com.sun.org.apache.xpath.internal.axes.SubContextList
    public int getLastPos(XPathContext xPathContext) {
        AxesWalker lastUsedWalker = wi().getLastUsedWalker();
        int i = 0;
        try {
            ReverseAxesWalker reverseAxesWalker = (ReverseAxesWalker) clone();
            reverseAxesWalker.setRoot(getRoot());
            reverseAxesWalker.setPredicateCount(getPredicateCount() - 1);
            reverseAxesWalker.setPrevWalker(null);
            reverseAxesWalker.setNextWalker(null);
            wi().setLastUsedWalker(reverseAxesWalker);
            while (-1 != reverseAxesWalker.nextNode()) {
                i++;
            }
        } catch (CloneNotSupportedException unused) {
        } finally {
            wi().setLastUsedWalker(lastUsedWalker);
        }
        return i;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.AxesWalker
    public int getNextNode() {
        if (this.m_foundLast) {
            return -1;
        }
        int next = this.m_iterator.next();
        if (this.m_isFresh) {
            this.m_isFresh = false;
        }
        if (-1 == next) {
            this.m_foundLast = true;
        }
        return next;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest
    public int getProximityPosition(int i) {
        if (i < 0) {
            return -1;
        }
        int i2 = this.m_proximityPositions[i];
        if (i2 > 0) {
            return i2;
        }
        AxesWalker lastUsedWalker = wi().getLastUsedWalker();
        try {
            ReverseAxesWalker reverseAxesWalker = (ReverseAxesWalker) clone();
            reverseAxesWalker.setRoot(getRoot());
            reverseAxesWalker.setPredicateCount(i);
            reverseAxesWalker.setPrevWalker(null);
            reverseAxesWalker.setNextWalker(null);
            wi().setLastUsedWalker(reverseAxesWalker);
            do {
                i2++;
            } while (-1 != reverseAxesWalker.nextNode());
            this.m_proximityPositions[i] = i2;
        } catch (CloneNotSupportedException unused) {
        } finally {
            wi().setLastUsedWalker(lastUsedWalker);
        }
        return i2;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.AxesWalker
    public boolean isDocOrdered() {
        return false;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest
    public boolean isReverseAxes() {
        return true;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.AxesWalker
    public void setRoot(int i) {
        super.setRoot(i);
        DTMAxisIterator axisIterator = getDTM(i).getAxisIterator(this.m_axis);
        this.m_iterator = axisIterator;
        axisIterator.setStartNode(i);
    }
}
