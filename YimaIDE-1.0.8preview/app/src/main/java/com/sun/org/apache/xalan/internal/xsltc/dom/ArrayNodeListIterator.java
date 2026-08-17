package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ArrayNodeListIterator implements DTMAxisIterator {
    private static final int[] EMPTY = new int[0];
    private int[] _nodes;
    private int _pos = 0;
    private int _mark = 0;

    public ArrayNodeListIterator(int[] iArr) {
        this._nodes = iArr;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator cloneIterator() {
        return new ArrayNodeListIterator(this._nodes);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int getLast() {
        return this._nodes.length;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int getNodeByPosition(int i) {
        return this._nodes[i - 1];
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int getPosition() {
        return this._pos;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int getStartNode() {
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void gotoMark() {
        this._pos = this._mark;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public boolean isReverse() {
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int next() {
        int i = this._pos;
        int[] iArr = this._nodes;
        if (i >= iArr.length) {
            return -1;
        }
        this._pos = i + 1;
        return iArr[i];
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator reset() {
        this._pos = 0;
        return this;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void setMark() {
        this._mark = this._pos;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void setRestartable(boolean z) {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator setStartNode(int i) {
        if (i == -1) {
            this._nodes = EMPTY;
        }
        return this;
    }
}
