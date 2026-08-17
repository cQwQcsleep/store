package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.DOMEnhancedForDTM;
import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class KeyIndex extends DTMAxisIteratorBase {
    private static final IntegerArray EMPTY_NODES = new IntegerArray(0);
    private DOM _dom;
    private DOMEnhancedForDTM _enhancedDOM;
    private Map<String, IntegerArray> _index;
    private int _currentDocumentNode = -1;
    private Map<Integer, Map<String, IntegerArray>> _rootToIndexMap = new HashMap();
    private IntegerArray _nodes = null;
    private int _markedPosition = 0;

    public KeyIndex(int i) {
    }

    public void add(String str, int i, int i2) {
        if (this._currentDocumentNode != i2) {
            this._currentDocumentNode = i2;
            this._index = new HashMap();
            this._rootToIndexMap.put(Integer.valueOf(i2), this._index);
        }
        IntegerArray integerArray = this._index.get(str);
        if (integerArray == null) {
            IntegerArray integerArray2 = new IntegerArray();
            this._index.put(str, integerArray2);
            integerArray2.add(i);
        } else if (i != integerArray.at(integerArray.cardinality() - 1)) {
            integerArray.add(i);
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    @Deprecated
    public DTMAxisIterator cloneIterator() {
        KeyIndex keyIndex = new KeyIndex(0);
        keyIndex._index = this._index;
        keyIndex._rootToIndexMap = this._rootToIndexMap;
        keyIndex._nodes = this._nodes;
        keyIndex._position = this._position;
        return keyIndex;
    }

    public int containsID(int i, Object obj) {
        DOMEnhancedForDTM dOMEnhancedForDTM;
        Map<String, IntegerArray> map = this._rootToIndexMap.get(Integer.valueOf(this._dom.getAxisIterator(19).setStartNode(i).next()));
        StringTokenizer stringTokenizer = new StringTokenizer((String) obj, " \n\t");
        while (stringTokenizer.hasMoreElements()) {
            String str = (String) stringTokenizer.nextElement();
            IntegerArray dOMNodeById = map != null ? map.get(str) : null;
            if (dOMNodeById == null && (dOMEnhancedForDTM = this._enhancedDOM) != null && dOMEnhancedForDTM.hasDOMSource()) {
                dOMNodeById = getDOMNodeById(str);
            }
            if (dOMNodeById != null && dOMNodeById.indexOf(i) >= 0) {
                return 1;
            }
        }
        return 0;
    }

    public int containsKey(int i, Object obj) {
        IntegerArray integerArray;
        Map<String, IntegerArray> map = this._rootToIndexMap.get(Integer.valueOf(this._dom.getAxisIterator(19).setStartNode(i).next()));
        return (map == null || (integerArray = map.get(obj)) == null || integerArray.indexOf(i) < 0) ? 0 : 1;
    }

    public IntegerArray getDOMNodeById(String str) {
        int elementById;
        DOMEnhancedForDTM dOMEnhancedForDTM = this._enhancedDOM;
        IntegerArray integerArray = null;
        if (dOMEnhancedForDTM != null && (elementById = dOMEnhancedForDTM.getElementById(str)) != -1) {
            Integer numValueOf = Integer.valueOf(this._enhancedDOM.getDocument());
            Map<String, IntegerArray> map = this._rootToIndexMap.get(numValueOf);
            if (map == null) {
                map = new HashMap<>();
                this._rootToIndexMap.put(numValueOf, map);
            } else {
                integerArray = map.get(str);
            }
            if (integerArray == null) {
                integerArray = new IntegerArray();
                map.put(str, integerArray);
            }
            integerArray.add(this._enhancedDOM.getNodeHandle(elementById));
        }
        return integerArray;
    }

    public KeyIndexIterator getKeyIndexIterator(Object obj, boolean z) {
        return obj instanceof DTMAxisIterator ? getKeyIndexIterator((DTMAxisIterator) obj, z) : getKeyIndexIterator(BasisLibrary.stringF(obj, this._dom), z);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    @Deprecated
    public int getLast() {
        IntegerArray integerArray = this._nodes;
        if (integerArray == null) {
            return 0;
        }
        return integerArray.cardinality();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    @Deprecated
    public int getPosition() {
        return this._position;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    @Deprecated
    public int getStartNode() {
        return 0;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    @Deprecated
    public void gotoMark() {
        this._position = this._markedPosition;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    @Deprecated
    public boolean isReverse() {
        return false;
    }

    @Deprecated
    public void lookupId(Object obj) {
        DOMEnhancedForDTM dOMEnhancedForDTM;
        this._nodes = null;
        StringTokenizer stringTokenizer = new StringTokenizer((String) obj, " \n\t");
        while (stringTokenizer.hasMoreElements()) {
            String str = (String) stringTokenizer.nextElement();
            IntegerArray dOMNodeById = this._index.get(str);
            if (dOMNodeById == null && (dOMEnhancedForDTM = this._enhancedDOM) != null && dOMEnhancedForDTM.hasDOMSource()) {
                dOMNodeById = getDOMNodeById(str);
            }
            if (dOMNodeById != null) {
                IntegerArray integerArray = this._nodes;
                if (integerArray == null) {
                    this._nodes = (IntegerArray) dOMNodeById.clone();
                } else {
                    integerArray.merge(dOMNodeById);
                }
            }
        }
    }

    @Deprecated
    public void lookupKey(Object obj) {
        IntegerArray integerArray = this._index.get(obj);
        this._nodes = integerArray != null ? (IntegerArray) integerArray.clone() : null;
        this._position = 0;
    }

    @Deprecated
    public void merge(KeyIndex keyIndex) {
        IntegerArray integerArray;
        if (keyIndex == null || (integerArray = keyIndex._nodes) == null) {
            return;
        }
        IntegerArray integerArray2 = this._nodes;
        if (integerArray2 == null) {
            this._nodes = (IntegerArray) integerArray.clone();
        } else {
            integerArray2.merge(integerArray);
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    @Deprecated
    public int next() {
        IntegerArray integerArray = this._nodes;
        if (integerArray == null || this._position >= integerArray.cardinality()) {
            return -1;
        }
        DOM dom = this._dom;
        IntegerArray integerArray2 = this._nodes;
        int i = this._position;
        this._position = i + 1;
        return dom.getNodeHandle(integerArray2.at(i));
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    @Deprecated
    public DTMAxisIterator reset() {
        this._position = 0;
        return this;
    }

    public void setDom(DOM dom, int i) {
        this._dom = dom;
        if (dom instanceof MultiDOM) {
            dom = ((MultiDOM) dom).getDTM(i);
        }
        if (dom instanceof DOMEnhancedForDTM) {
            this._enhancedDOM = (DOMEnhancedForDTM) dom;
        } else if (dom instanceof DOMAdapter) {
            DOM dOMImpl = ((DOMAdapter) dom).getDOMImpl();
            if (dOMImpl instanceof DOMEnhancedForDTM) {
                this._enhancedDOM = (DOMEnhancedForDTM) dOMImpl;
            }
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    @Deprecated
    public void setMark() {
        this._markedPosition = this._position;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public void setRestartable(boolean z) {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    @Deprecated
    public DTMAxisIterator setStartNode(int i) {
        if (i == -1) {
            this._nodes = null;
            return this;
        }
        if (this._nodes != null) {
            this._position = 0;
        }
        return this;
    }

    public class KeyIndexIterator extends MultiValuedNodeHeapIterator {
        private boolean _isKeyIterator;
        private String _keyValue;
        private DTMAxisIterator _keyValueIterator;
        private IntegerArray _nodes;

        public class KeyIndexHeapNode extends MultiValuedNodeHeapIterator.HeapNode {
            private int _markPosition;
            private IntegerArray _nodes;
            private int _position;

            public KeyIndexHeapNode(IntegerArray integerArray) {
                super();
                this._position = 0;
                this._markPosition = -1;
                this._nodes = integerArray;
            }

            @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode
            public MultiValuedNodeHeapIterator.HeapNode cloneHeapNode() {
                KeyIndexHeapNode keyIndexHeapNode = (KeyIndexHeapNode) super.cloneHeapNode();
                keyIndexHeapNode._nodes = this._nodes;
                keyIndexHeapNode._position = this._position;
                keyIndexHeapNode._markPosition = this._markPosition;
                return keyIndexHeapNode;
            }

            @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode
            public void gotoMark() {
                this._position = this._markPosition;
            }

            @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode
            public boolean isLessThan(MultiValuedNodeHeapIterator.HeapNode heapNode) {
                return this._node < heapNode._node;
            }

            @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode
            public MultiValuedNodeHeapIterator.HeapNode reset() {
                this._position = 0;
                return this;
            }

            @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode
            public void setMark() {
                this._markPosition = this._position;
            }

            @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode
            public MultiValuedNodeHeapIterator.HeapNode setStartNode(int i) {
                return this;
            }

            @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode
            public int step() {
                if (this._position < this._nodes.cardinality()) {
                    this._node = this._nodes.at(this._position);
                    this._position++;
                } else {
                    this._node = -1;
                }
                return this._node;
            }
        }

        public KeyIndexIterator(String str, boolean z) {
            this._isKeyIterator = z;
            this._keyValue = str;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int getLast() {
            IntegerArray integerArray = this._nodes;
            return integerArray != null ? integerArray.cardinality() : super.getLast();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int getNodeByPosition(int i) {
            IntegerArray integerArray = this._nodes;
            if (integerArray == null) {
                return super.getNodeByPosition(i);
            }
            if (i > 0) {
                if (i <= integerArray.cardinality()) {
                    this._position = i;
                    return this._nodes.at(i - 1);
                }
                this._position = this._nodes.cardinality();
            }
            return -1;
        }

        @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator
        public void init() {
            super.init();
            boolean z = false;
            this._position = 0;
            int next = KeyIndex.this._dom.getAxisIterator(19).setStartNode(this._startNode).next();
            DTMAxisIterator dTMAxisIterator = this._keyValueIterator;
            if (dTMAxisIterator == null) {
                IntegerArray integerArrayLookupNodes = lookupNodes(next, this._keyValue);
                this._nodes = integerArrayLookupNodes;
                if (integerArrayLookupNodes == null) {
                    this._nodes = KeyIndex.EMPTY_NODES;
                    return;
                }
                return;
            }
            DTMAxisIterator dTMAxisIteratorReset = dTMAxisIterator.reset();
            this._nodes = null;
            for (int next2 = dTMAxisIteratorReset.next(); next2 != -1; next2 = dTMAxisIteratorReset.next()) {
                IntegerArray integerArrayLookupNodes2 = lookupNodes(next, BasisLibrary.stringF(next2, KeyIndex.this._dom));
                if (integerArrayLookupNodes2 != null) {
                    if (z) {
                        IntegerArray integerArray = this._nodes;
                        if (integerArray != null) {
                            addHeapNode(new KeyIndexHeapNode(integerArray));
                            this._nodes = null;
                        }
                        addHeapNode(new KeyIndexHeapNode(integerArrayLookupNodes2));
                    } else {
                        this._nodes = integerArrayLookupNodes2;
                        z = true;
                    }
                }
            }
            if (z) {
                return;
            }
            this._nodes = KeyIndex.EMPTY_NODES;
        }

        public IntegerArray lookupNodes(int i, String str) {
            Map map = (Map) KeyIndex.this._rootToIndexMap.get(Integer.valueOf(i));
            if (this._isKeyIterator) {
                if (map != null) {
                    return (IntegerArray) map.get(str);
                }
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(str, " \n\t");
            IntegerArray integerArray = null;
            while (stringTokenizer.hasMoreElements()) {
                String str2 = (String) stringTokenizer.nextElement();
                IntegerArray dOMNodeById = map != null ? (IntegerArray) map.get(str2) : null;
                if (dOMNodeById == null && KeyIndex.this._enhancedDOM != null && KeyIndex.this._enhancedDOM.hasDOMSource()) {
                    dOMNodeById = KeyIndex.this.getDOMNodeById(str2);
                }
                if (dOMNodeById != null) {
                    if (integerArray == null) {
                        integerArray = (IntegerArray) dOMNodeById.clone();
                    } else {
                        integerArray.merge(dOMNodeById);
                    }
                }
            }
            return integerArray;
        }

        @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            IntegerArray integerArray = this._nodes;
            if (integerArray == null) {
                return super.next();
            }
            if (this._position < integerArray.cardinality()) {
                return returnNode(this._nodes.at(this._position));
            }
            return -1;
        }

        @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator, com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator reset() {
            if (this._nodes == null) {
                init();
            } else {
                super.reset();
            }
            return resetPosition();
        }

        @Override // com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            this._startNode = i;
            DTMAxisIterator dTMAxisIterator = this._keyValueIterator;
            if (dTMAxisIterator != null) {
                this._keyValueIterator = dTMAxisIterator.setStartNode(i);
            }
            init();
            return super.setStartNode(i);
        }

        public KeyIndexIterator(DTMAxisIterator dTMAxisIterator, boolean z) {
            this._keyValueIterator = dTMAxisIterator;
            this._isKeyIterator = z;
        }
    }

    public KeyIndexIterator getKeyIndexIterator(String str, boolean z) {
        return new KeyIndexIterator(str, z);
    }

    public KeyIndexIterator getKeyIndexIterator(DTMAxisIterator dTMAxisIterator, boolean z) {
        return new KeyIndexIterator(dTMAxisIterator, z);
    }
}
