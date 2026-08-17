package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ClonedNodeListIterator extends DTMAxisIteratorBase {
    private int _index = 0;
    private CachedNodeListIterator _source;

    public ClonedNodeListIterator(CachedNodeListIterator cachedNodeListIterator) {
        this._source = cachedNodeListIterator;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator cloneIterator() {
        return this._source.cloneIterator();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int getNodeByPosition(int i) {
        return this._source.getNode(i);
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
        CachedNodeListIterator cachedNodeListIterator = this._source;
        int i = this._index;
        this._index = i + 1;
        return cachedNodeListIterator.getNode(i);
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
        return this;
    }
}
