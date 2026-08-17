package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.NodeIterator;
import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class NodeIteratorBase implements NodeIterator {
    protected int _markedNode;
    protected int _last = -1;
    protected int _position = 0;
    protected int _startNode = -1;
    protected boolean _includeSelf = false;
    protected boolean _isRestartable = true;

    @Override // com.sun.org.apache.xalan.internal.xsltc.NodeIterator
    public NodeIterator cloneIterator() {
        try {
            NodeIteratorBase nodeIteratorBase = (NodeIteratorBase) super.clone();
            nodeIteratorBase._isRestartable = false;
            return nodeIteratorBase.reset();
        } catch (CloneNotSupportedException e) {
            BasisLibrary.runTimeError(BasisLibrary.ITERATOR_CLONE_ERR, e.toString());
            return null;
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.NodeIterator
    public int getLast() {
        if (this._last == -1) {
            int i = this._position;
            setMark();
            reset();
            do {
                this._last++;
            } while (next() != -1);
            gotoMark();
            this._position = i;
        }
        return this._last;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.NodeIterator
    public int getPosition() {
        int i = this._position;
        if (i == 0) {
            return 1;
        }
        return i;
    }

    public NodeIterator includeSelf() {
        this._includeSelf = true;
        return this;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.NodeIterator
    public boolean isReverse() {
        return false;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.NodeIterator
    public NodeIterator reset() {
        boolean z = this._isRestartable;
        this._isRestartable = true;
        boolean z2 = this._includeSelf;
        int i = this._startNode;
        if (z2) {
            i++;
        }
        setStartNode(i);
        this._isRestartable = z;
        return this;
    }

    public final NodeIterator resetPosition() {
        this._position = 0;
        return this;
    }

    public final int returnNode(int i) {
        this._position++;
        return i;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.NodeIterator
    public void setRestartable(boolean z) {
        this._isRestartable = z;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.NodeIterator
    public abstract NodeIterator setStartNode(int i);
}
