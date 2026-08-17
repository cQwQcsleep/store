package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StepIterator extends DTMAxisIteratorBase {
    protected DTMAxisIterator _iterator;
    private int _pos = -1;
    protected DTMAxisIterator _source;

    public StepIterator(DTMAxisIterator dTMAxisIterator, DTMAxisIterator dTMAxisIterator2) {
        this._source = dTMAxisIterator;
        this._iterator = dTMAxisIterator2;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator cloneIterator() {
        this._isRestartable = false;
        try {
            StepIterator stepIterator = (StepIterator) super.clone();
            stepIterator._source = this._source.cloneIterator();
            DTMAxisIterator dTMAxisIteratorCloneIterator = this._iterator.cloneIterator();
            stepIterator._iterator = dTMAxisIteratorCloneIterator;
            dTMAxisIteratorCloneIterator.setRestartable(true);
            stepIterator._isRestartable = false;
            return stepIterator.reset();
        } catch (CloneNotSupportedException e) {
            BasisLibrary.runTimeError(BasisLibrary.ITERATOR_CLONE_ERR, e.toString());
            return null;
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void gotoMark() {
        this._source.gotoMark();
        this._iterator.gotoMark();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int next() {
        while (true) {
            int next = this._iterator.next();
            if (next != -1) {
                return returnNode(next);
            }
            int next2 = this._source.next();
            if (next2 == -1) {
                return -1;
            }
            this._iterator.setStartNode(next2);
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator reset() {
        this._source.reset();
        this._iterator.setStartNode(this._includeSelf ? this._startNode : this._source.next());
        return resetPosition();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void setMark() {
        this._source.setMark();
        this._iterator.setMark();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void setRestartable(boolean z) {
        this._isRestartable = z;
        this._source.setRestartable(z);
        this._iterator.setRestartable(true);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator setStartNode(int i) {
        if (!this._isRestartable) {
            return this;
        }
        DTMAxisIterator dTMAxisIterator = this._source;
        this._startNode = i;
        dTMAxisIterator.setStartNode(i);
        this._iterator.setStartNode(this._includeSelf ? this._startNode : this._source.next());
        return resetPosition();
    }
}
