package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SingletonIterator extends DTMAxisIteratorBase {
    private final boolean _isConstant;
    private int _node;

    public SingletonIterator(int i, boolean z) {
        this._startNode = i;
        this._node = i;
        this._isConstant = z;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void gotoMark() {
        this._node = this._markedNode;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int next() {
        int i = this._node;
        this._node = -1;
        return returnNode(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator reset() {
        if (this._isConstant) {
            this._node = this._startNode;
            return resetPosition();
        }
        boolean z = this._isRestartable;
        this._isRestartable = true;
        setStartNode(this._startNode);
        this._isRestartable = z;
        return this;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void setMark() {
        this._markedNode = this._node;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator setStartNode(int i) {
        if (this._isConstant) {
            this._node = this._startNode;
            return resetPosition();
        }
        if (!this._isRestartable) {
            return this;
        }
        if (this._node <= 0) {
            this._startNode = i;
            this._node = i;
        }
        return resetPosition();
    }

    public SingletonIterator(int i) {
        this(i, false);
    }

    public SingletonIterator() {
        this(Integer.MIN_VALUE, false);
    }
}
