package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class MultiValuedNodeHeapIterator extends DTMAxisIteratorBase {
    private static final int InitSize = 8;
    private int _cachedHeapSize;
    private int _returnedLast;
    private int _heapSize = 0;
    private int _size = 8;
    private HeapNode[] _heap = new HeapNode[8];
    private int _free = 0;
    private int _cachedReturnedLast = -1;

    public abstract class HeapNode implements Cloneable {
        protected boolean _isStartSet = false;
        protected int _markedNode;
        protected int _node;

        public HeapNode() {
        }

        public HeapNode cloneHeapNode() {
            try {
                HeapNode heapNode = (HeapNode) super.clone();
                heapNode._node = this._node;
                heapNode._markedNode = this._node;
                return heapNode;
            } catch (CloneNotSupportedException e) {
                BasisLibrary.runTimeError(BasisLibrary.ITERATOR_CLONE_ERR, e.toString());
                return null;
            }
        }

        public void gotoMark() {
            this._node = this._markedNode;
        }

        public abstract boolean isLessThan(HeapNode heapNode);

        public abstract HeapNode reset();

        public void setMark() {
            this._markedNode = this._node;
        }

        public abstract HeapNode setStartNode(int i);

        public abstract int step();
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0029  */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    private void heapify(int i) {
        while (true) {
            int i2 = (i + 1) << 1;
            int i3 = i2 - 1;
            if (i3 < this._heapSize) {
                HeapNode[] heapNodeArr = this._heap;
                if (!heapNodeArr[i3].isLessThan(heapNodeArr[i])) {
                    i3 = i;
                }
            } else {
                i3 = i;
            }
            if (i2 < this._heapSize) {
                HeapNode[] heapNodeArr2 = this._heap;
                if (!heapNodeArr2[i2].isLessThan(heapNodeArr2[i3])) {
                    i2 = i3;
                }
            } else {
                i2 = i3;
            }
            if (i2 == i) {
                return;
            }
            HeapNode[] heapNodeArr3 = this._heap;
            HeapNode heapNode = heapNodeArr3[i2];
            heapNodeArr3[i2] = heapNodeArr3[i];
            heapNodeArr3[i] = heapNode;
            i = i2;
        }
    }

    public void addHeapNode(HeapNode heapNode) {
        int i = this._free;
        int i2 = this._size;
        if (i == i2) {
            int i3 = i2 * 2;
            this._size = i3;
            HeapNode[] heapNodeArr = new HeapNode[i3];
            System.arraycopy(this._heap, 0, heapNodeArr, 0, i);
            this._heap = heapNodeArr;
        }
        this._heapSize++;
        HeapNode[] heapNodeArr2 = this._heap;
        int i4 = this._free;
        this._free = i4 + 1;
        heapNodeArr2[i4] = heapNode;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator cloneIterator() {
        this._isRestartable = false;
        HeapNode[] heapNodeArr = new HeapNode[this._heap.length];
        try {
            MultiValuedNodeHeapIterator multiValuedNodeHeapIterator = (MultiValuedNodeHeapIterator) super.clone();
            for (int i = 0; i < this._free; i++) {
                heapNodeArr[i] = this._heap[i].cloneHeapNode();
            }
            multiValuedNodeHeapIterator.setRestartable(false);
            multiValuedNodeHeapIterator._heap = heapNodeArr;
            return multiValuedNodeHeapIterator.reset();
        } catch (CloneNotSupportedException e) {
            BasisLibrary.runTimeError(BasisLibrary.ITERATOR_CLONE_ERR, e.toString());
            return null;
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void gotoMark() {
        for (int i = 0; i < this._free; i++) {
            this._heap[i].gotoMark();
        }
        int i2 = this._cachedHeapSize;
        this._heapSize = i2;
        for (int i3 = i2 / 2; i3 >= 0; i3--) {
            heapify(i3);
        }
        this._returnedLast = this._cachedReturnedLast;
    }

    public void init() {
        for (int i = 0; i < this._free; i++) {
            this._heap[i] = null;
        }
        this._heapSize = 0;
        this._free = 0;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public int next() {
        while (true) {
            int i = this._heapSize;
            if (i <= 0) {
                return -1;
            }
            HeapNode[] heapNodeArr = this._heap;
            HeapNode heapNode = heapNodeArr[0];
            int i2 = heapNode._node;
            if (i2 == -1) {
                if (i <= 1) {
                    return -1;
                }
                int i3 = i - 1;
                this._heapSize = i3;
                heapNodeArr[0] = heapNodeArr[i3];
                heapNodeArr[i3] = heapNode;
            } else {
                if (i2 != this._returnedLast) {
                    heapNode.step();
                    heapify(0);
                    this._returnedLast = i2;
                    return returnNode(i2);
                }
                heapNode.step();
            }
            heapify(0);
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator reset() {
        int i;
        int i2 = 0;
        while (true) {
            i = this._free;
            if (i2 >= i) {
                break;
            }
            this._heap[i2].reset();
            this._heap[i2].step();
            i2++;
        }
        this._heapSize = i;
        for (int i3 = i / 2; i3 >= 0; i3--) {
            heapify(i3);
        }
        this._returnedLast = -1;
        return resetPosition();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void setMark() {
        for (int i = 0; i < this._free; i++) {
            this._heap[i].setMark();
        }
        this._cachedReturnedLast = this._returnedLast;
        this._cachedHeapSize = this._heapSize;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public DTMAxisIterator setStartNode(int i) {
        int i2;
        if (!this._isRestartable) {
            return this;
        }
        this._startNode = i;
        int i3 = 0;
        while (true) {
            i2 = this._free;
            if (i3 >= i2) {
                break;
            }
            HeapNode heapNode = this._heap[i3];
            if (!heapNode._isStartSet) {
                heapNode.setStartNode(i);
                this._heap[i3].step();
                this._heap[i3]._isStartSet = true;
            }
            i3++;
        }
        this._heapSize = i2;
        for (int i4 = i2 / 2; i4 >= 0; i4--) {
            heapify(i4);
        }
        this._returnedLast = -1;
        return resetPosition();
    }
}
