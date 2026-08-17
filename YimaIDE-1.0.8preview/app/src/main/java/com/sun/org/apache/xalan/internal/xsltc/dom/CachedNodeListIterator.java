package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class CachedNodeListIterator extends DTMAxisIteratorBase {
    private DTMAxisIterator _source;
    private IntegerArray _nodes = new IntegerArray();
    private int _numCachedNodes = 0;
    private int _index = 0;
    private boolean _isEnded = false;

    public CachedNodeListIterator(DTMAxisIterator dTMAxisIterator) {
        this._source = dTMAxisIterator;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator cloneIterator() {
        return new ClonedNodeListIterator(this);
    }

    public int getNode(int i) {
        if (i < this._numCachedNodes) {
            return this._nodes.at(i);
        }
        if (this._isEnded) {
            return -1;
        }
        int next = this._source.next();
        if (next == -1) {
            this._isEnded = true;
            return next;
        }
        this._nodes.add(next);
        this._numCachedNodes++;
        return next;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int getNodeByPosition(int i) {
        return getNode(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int getPosition() {
        int i = this._index;
        if (i == 0) {
            return 1;
        }
        return i;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void gotoMark() {
        this._source.gotoMark();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int next() {
        int i = this._index;
        this._index = i + 1;
        return getNode(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator reset() {
        this._index = 0;
        return this;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void setMark() {
        this._source.setMark();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void setRestartable(boolean z) {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator setStartNode(int i) {
        if (this._isRestartable) {
            this._startNode = i;
            this._source.setStartNode(i);
            resetPosition();
            this._isRestartable = false;
        }
        return this;
    }
}
