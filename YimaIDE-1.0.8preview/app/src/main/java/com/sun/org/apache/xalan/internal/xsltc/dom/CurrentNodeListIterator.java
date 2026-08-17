package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class CurrentNodeListIterator extends DTMAxisIteratorBase {
    private int _currentIndex;
    private final int _currentNode;
    private boolean _docOrder;
    private final CurrentNodeListFilter _filter;
    private IntegerArray _nodes;
    private DTMAxisIterator _source;
    private AbstractTranslet _translet;

    public CurrentNodeListIterator(DTMAxisIterator dTMAxisIterator, boolean z, CurrentNodeListFilter currentNodeListFilter, int i, AbstractTranslet abstractTranslet) {
        this._nodes = new IntegerArray();
        this._source = dTMAxisIterator;
        this._filter = currentNodeListFilter;
        this._translet = abstractTranslet;
        this._docOrder = z;
        this._currentNode = i;
    }

    private int computePositionOfLast() {
        int iCardinality = this._nodes.cardinality();
        int i = this._currentNode;
        AbstractTranslet abstractTranslet = this._translet;
        int i2 = this._position;
        int i3 = this._currentIndex;
        while (i3 < iCardinality) {
            int i4 = i3 + 1;
            CurrentNodeListIterator currentNodeListIterator = this;
            if (this._filter.test(this._nodes.at(i3), this._docOrder ? i3 + 1 : iCardinality - i3, iCardinality, i, abstractTranslet, currentNodeListIterator)) {
                i2++;
            }
            this = currentNodeListIterator;
            i3 = i4;
        }
        return i2;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator cloneIterator() {
        try {
            CurrentNodeListIterator currentNodeListIterator = (CurrentNodeListIterator) super.clone();
            currentNodeListIterator._nodes = (IntegerArray) this._nodes.clone();
            currentNodeListIterator._source = this._source.cloneIterator();
            currentNodeListIterator._isRestartable = false;
            return currentNodeListIterator.reset();
        } catch (CloneNotSupportedException e) {
            BasisLibrary.runTimeError(BasisLibrary.ITERATOR_CLONE_ERR, e.toString());
            return null;
        }
    }

    public DTMAxisIterator forceNaturalOrder() {
        this._docOrder = true;
        return this;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int getLast() {
        if (this._last == -1) {
            this._last = computePositionOfLast();
        }
        return this._last;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void gotoMark() {
        this._currentIndex = this._markedNode;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public boolean isReverse() {
        return !this._docOrder;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int next() {
        int iCardinality = this._nodes.cardinality();
        int i = this._currentNode;
        AbstractTranslet abstractTranslet = this._translet;
        int i2 = this._currentIndex;
        while (i2 < iCardinality) {
            int i3 = this._docOrder ? i2 + 1 : iCardinality - i2;
            int i4 = i2 + 1;
            int iAt = this._nodes.at(i2);
            CurrentNodeListIterator currentNodeListIterator = this;
            if (this._filter.test(iAt, i3, iCardinality, i, abstractTranslet, currentNodeListIterator)) {
                currentNodeListIterator._currentIndex = i4;
                return currentNodeListIterator.returnNode(iAt);
            }
            this = currentNodeListIterator;
            i2 = i4;
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator reset() {
        this._currentIndex = 0;
        return resetPosition();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void setMark() {
        this._markedNode = this._currentIndex;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void setRestartable(boolean z) {
        this._isRestartable = z;
        this._source.setRestartable(z);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator setStartNode(int i) {
        if (this._isRestartable) {
            DTMAxisIterator dTMAxisIterator = this._source;
            this._startNode = i;
            dTMAxisIterator.setStartNode(i);
            this._nodes.clear();
            while (true) {
                int next = this._source.next();
                if (next == -1) {
                    break;
                }
                this._nodes.add(next);
            }
            this._currentIndex = 0;
            resetPosition();
        }
        return this;
    }

    public CurrentNodeListIterator(DTMAxisIterator dTMAxisIterator, CurrentNodeListFilter currentNodeListFilter, int i, AbstractTranslet abstractTranslet) {
        this(dTMAxisIterator, !dTMAxisIterator.isReverse(), currentNodeListFilter, i, abstractTranslet);
    }
}
