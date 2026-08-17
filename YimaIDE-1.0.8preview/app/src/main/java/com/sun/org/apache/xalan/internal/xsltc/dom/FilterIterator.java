package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.DTMFilter;
import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class FilterIterator extends DTMAxisIteratorBase {
    private final DTMFilter _filter;
    private final boolean _isReverse;
    private DTMAxisIterator _source;

    public FilterIterator(DTMAxisIterator dTMAxisIterator, DTMFilter dTMFilter) {
        this._source = dTMAxisIterator;
        this._filter = dTMFilter;
        this._isReverse = dTMAxisIterator.isReverse();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator cloneIterator() {
        try {
            FilterIterator filterIterator = (FilterIterator) super.clone();
            filterIterator._source = this._source.cloneIterator();
            filterIterator._isRestartable = false;
            return filterIterator.reset();
        } catch (CloneNotSupportedException e) {
            BasisLibrary.runTimeError(BasisLibrary.ITERATOR_CLONE_ERR, e.toString());
            return null;
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void gotoMark() {
        this._source.gotoMark();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public boolean isReverse() {
        return this._isReverse;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int next() {
        int next;
        do {
            next = this._source.next();
            if (next == -1) {
                return -1;
            }
        } while (this._filter.acceptNode(next, -1) != 1);
        return returnNode(next);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator reset() {
        this._source.reset();
        return resetPosition();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void setMark() {
        this._source.setMark();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void setRestartable(boolean z) {
        this._isRestartable = z;
        this._source.setRestartable(z);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator setStartNode(int i) {
        if (!this._isRestartable) {
            return this;
        }
        DTMAxisIterator dTMAxisIterator = this._source;
        this._startNode = i;
        dTMAxisIterator.setStartNode(i);
        return resetPosition();
    }
}
