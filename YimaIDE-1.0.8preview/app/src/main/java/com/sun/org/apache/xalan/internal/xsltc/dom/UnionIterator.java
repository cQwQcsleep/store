package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class UnionIterator extends MultiValuedNodeHeapIterator {
    private final DOM _dom;

    public final class LookAheadIterator extends MultiValuedNodeHeapIterator.HeapNode {
        public DTMAxisIterator iterator;

        public LookAheadIterator(DTMAxisIterator dTMAxisIterator) {
            super();
            this.iterator = dTMAxisIterator;
        }

        @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode
        public MultiValuedNodeHeapIterator.HeapNode cloneHeapNode() {
            LookAheadIterator lookAheadIterator = (LookAheadIterator) super.cloneHeapNode();
            lookAheadIterator.iterator = this.iterator.cloneIterator();
            return lookAheadIterator;
        }

        @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode
        public void gotoMark() {
            super.gotoMark();
            this.iterator.gotoMark();
        }

        @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode
        public boolean isLessThan(MultiValuedNodeHeapIterator.HeapNode heapNode) {
            return UnionIterator.this._dom.lessThan(this._node, heapNode._node);
        }

        @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode
        public MultiValuedNodeHeapIterator.HeapNode reset() {
            this.iterator.reset();
            return this;
        }

        @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode
        public void setMark() {
            super.setMark();
            this.iterator.setMark();
        }

        @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode
        public MultiValuedNodeHeapIterator.HeapNode setStartNode(int i) {
            this.iterator.setStartNode(i);
            return this;
        }

        @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode
        public int step() {
            int next = this.iterator.next();
            this._node = next;
            return next;
        }
    }

    public UnionIterator(DOM dom) {
        this._dom = dom;
    }

    public UnionIterator addIterator(DTMAxisIterator dTMAxisIterator) {
        addHeapNode(new LookAheadIterator(dTMAxisIterator));
        return this;
    }
}
