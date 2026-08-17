package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DTMAxisIteratorBase implements DTMAxisIterator {
    protected int _markedNode;
    protected int _last = -1;
    protected int _position = 0;
    protected int _startNode = -1;
    protected boolean _includeSelf = false;
    protected boolean _isRestartable = true;

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator cloneIterator() {
        try {
            DTMAxisIteratorBase dTMAxisIteratorBase = (DTMAxisIteratorBase) super.clone();
            dTMAxisIteratorBase._isRestartable = false;
            return dTMAxisIteratorBase;
        } catch (CloneNotSupportedException e) {
            throw new WrappedRuntimeException(e);
        }
    }

    public int getAxis() {
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
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

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int getNodeByPosition(int i) {
        int next;
        if (i > 0) {
            if (isReverse()) {
                i = (getLast() - i) + 1;
            }
            do {
                next = next();
                if (next != -1) {
                }
            } while (i != getPosition());
            return next;
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int getPosition() {
        int i = this._position;
        if (i == 0) {
            return 1;
        }
        return i;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int getStartNode() {
        return this._startNode;
    }

    public DTMAxisIterator includeSelf() {
        this._includeSelf = true;
        return this;
    }

    public boolean isDocOrdered() {
        return true;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public boolean isReverse() {
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator reset() {
        boolean z = this._isRestartable;
        this._isRestartable = true;
        setStartNode(this._startNode);
        this._isRestartable = z;
        return this;
    }

    public final DTMAxisIterator resetPosition() {
        this._position = 0;
        return this;
    }

    public final int returnNode(int i) {
        this._position++;
        return i;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void setRestartable(boolean z) {
        this._isRestartable = z;
    }
}
