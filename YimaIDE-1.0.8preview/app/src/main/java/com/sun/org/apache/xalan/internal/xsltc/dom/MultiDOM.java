package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.StripFilter;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import com.sun.org.apache.xml.internal.dtm.Axis;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIterNodeList;
import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase;
import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import com.sun.org.apache.xml.internal.utils.SuballocatedIntVector;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class MultiDOM implements DOM {
    private static final int INITIAL_SIZE = 4;
    private static final int NO_TYPE = -2;
    private DOM[] _adapters;
    private DTMManager _dtmManager;
    private DOMAdapter _main;
    private Map<String, Integer> _documents = new HashMap();
    private int _size = 4;
    private int _free = 1;

    public final class AxisIterator extends DTMAxisIteratorBase {
        private final int _axis;
        private int _dtmId = -1;
        private DTMAxisIterator _source;
        private final int _type;

        public AxisIterator(int i, int i2) {
            this._axis = i;
            this._type = i2;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator cloneIterator() {
            AxisIterator axisIterator = MultiDOM.this.new AxisIterator(this._axis, this._type);
            DTMAxisIterator dTMAxisIterator = this._source;
            if (dTMAxisIterator != null) {
                axisIterator._source = dTMAxisIterator.cloneIterator();
            }
            axisIterator._dtmId = this._dtmId;
            return axisIterator;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int getLast() {
            DTMAxisIterator dTMAxisIterator = this._source;
            if (dTMAxisIterator != null) {
                return dTMAxisIterator.getLast();
            }
            return -1;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int getPosition() {
            DTMAxisIterator dTMAxisIterator = this._source;
            if (dTMAxisIterator != null) {
                return dTMAxisIterator.getPosition();
            }
            return -1;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void gotoMark() {
            DTMAxisIterator dTMAxisIterator = this._source;
            if (dTMAxisIterator != null) {
                dTMAxisIterator.gotoMark();
            }
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public boolean isReverse() {
            return Axis.isReverse(this._axis);
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            DTMAxisIterator dTMAxisIterator = this._source;
            if (dTMAxisIterator == null) {
                return -1;
            }
            return dTMAxisIterator.next();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator reset() {
            DTMAxisIterator dTMAxisIterator = this._source;
            if (dTMAxisIterator != null) {
                dTMAxisIterator.reset();
            }
            return this;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void setMark() {
            DTMAxisIterator dTMAxisIterator = this._source;
            if (dTMAxisIterator != null) {
                dTMAxisIterator.setMark();
            }
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void setRestartable(boolean z) {
            DTMAxisIterator dTMAxisIterator = this._source;
            if (dTMAxisIterator != null) {
                dTMAxisIterator.setRestartable(z);
            }
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == -1) {
                return this;
            }
            int i2 = i >>> 16;
            if (this._source == null || this._dtmId != i2) {
                if (this._type == -2) {
                    this._source = MultiDOM.this._adapters[i2].getAxisIterator(this._axis);
                } else {
                    int i3 = this._axis;
                    MultiDOM multiDOM = MultiDOM.this;
                    if (i3 == 3) {
                        this._source = multiDOM._adapters[i2].getTypedChildren(this._type);
                    } else {
                        this._source = multiDOM._adapters[i2].getTypedAxisIterator(this._axis, this._type);
                    }
                }
            }
            this._dtmId = i2;
            this._source.setStartNode(i);
            return this;
        }
    }

    public final class NodeValueIterator extends DTMAxisIteratorBase {
        private final boolean _isReverse;
        private boolean _op;
        private int _returnType;
        private DTMAxisIterator _source;
        private String _value;

        public NodeValueIterator(DTMAxisIterator dTMAxisIterator, int i, String str, boolean z) {
            this._source = dTMAxisIterator;
            this._returnType = i;
            this._value = str;
            this._op = z;
            this._isReverse = dTMAxisIterator.isReverse();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator cloneIterator() {
            try {
                NodeValueIterator nodeValueIterator = (NodeValueIterator) super.clone();
                nodeValueIterator._source = this._source.cloneIterator();
                nodeValueIterator.setRestartable(false);
                return nodeValueIterator.reset();
            } catch (CloneNotSupportedException e) {
                BasisLibrary.runTimeError(BasisLibrary.ITERATOR_CLONE_ERR, e.toString());
                return null;
            }
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void gotoMark() {
            this._source.gotoMark();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public boolean isReverse() {
            return this._isReverse;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int next;
            do {
                next = this._source.next();
                if (next == -1) {
                    return -1;
                }
            } while (this._value.equals(MultiDOM.this.getStringValueX(next)) != this._op);
            return this._returnType == 0 ? returnNode(next) : returnNode(MultiDOM.this.getParent(next));
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator reset() {
            this._source.reset();
            return resetPosition();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void setMark() {
            this._source.setMark();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void setRestartable(boolean z) {
            this._isRestartable = z;
            this._source.setRestartable(z);
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (!this._isRestartable) {
                return this;
            }
            DTMAxisIterator dTMAxisIterator = this._source;
            this._startNode = i;
            dTMAxisIterator.setStartNode(i);
            return resetPosition();
        }
    }

    public MultiDOM(DOM dom) {
        DOM[] domArr = new DOM[4];
        this._adapters = domArr;
        DOMAdapter dOMAdapter = (DOMAdapter) dom;
        domArr[0] = dOMAdapter;
        this._main = dOMAdapter;
        Object dOMImpl = dOMAdapter.getDOMImpl();
        if (dOMImpl instanceof DTMDefaultBase) {
            this._dtmManager = ((DTMDefaultBase) dOMImpl).getManager();
        }
        addDOMAdapter(dOMAdapter, false);
    }

    private int addDOMAdapter(DOMAdapter dOMAdapter, boolean z) {
        int document;
        SuballocatedIntVector dTMIDs;
        int size;
        DOM nestedDOM;
        int i;
        Object dOMImpl = dOMAdapter.getDOMImpl();
        if (dOMImpl instanceof DTMDefaultBase) {
            dTMIDs = ((DTMDefaultBase) dOMImpl).getDTMIDs();
            size = dTMIDs.size();
            document = dTMIDs.elementAt(size - 1) >>> 16;
        } else if (dOMImpl instanceof SimpleResultTreeImpl) {
            document = ((SimpleResultTreeImpl) dOMImpl).getDocument() >>> 16;
            dTMIDs = null;
            size = 1;
        } else {
            document = 1;
            dTMIDs = null;
            size = 1;
        }
        int i2 = this._size;
        if (document >= i2) {
            do {
                i = this._size * 2;
                this._size = i;
            } while (i <= document);
            DOMAdapter[] dOMAdapterArr = new DOMAdapter[i];
            System.arraycopy(this._adapters, 0, dOMAdapterArr, 0, i2);
            this._adapters = dOMAdapterArr;
        }
        this._free = document + 1;
        if (size == 1) {
            this._adapters[document] = dOMAdapter;
        } else if (dTMIDs != null) {
            document = 0;
            for (int i3 = size - 1; i3 >= 0; i3--) {
                document = dTMIDs.elementAt(i3) >>> 16;
                this._adapters[document] = dOMAdapter;
            }
        }
        if (z) {
            this._documents.put(dOMAdapter.getDocumentURI(0), Integer.valueOf(document));
        }
        if ((dOMImpl instanceof AdaptiveResultTreeImpl) && (nestedDOM = ((AdaptiveResultTreeImpl) dOMImpl).getNestedDOM()) != null) {
            addDOMAdapter(new DOMAdapter(nestedDOM, dOMAdapter.getNamesArray(), dOMAdapter.getUrisArray(), dOMAdapter.getTypesArray(), dOMAdapter.getNamespaceArray()));
        }
        return document;
    }

    private boolean isMatchingAdapterEntry(DOM dom, DOMAdapter dOMAdapter) {
        DOM dOMImpl = dOMAdapter.getDOMImpl();
        if (dom != dOMAdapter) {
            return (dOMImpl instanceof AdaptiveResultTreeImpl) && (dom instanceof DOMAdapter) && ((AdaptiveResultTreeImpl) dOMImpl).getNestedDOM() == ((DOMAdapter) dom).getDOMImpl();
        }
        return true;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void characters(int i, SerializationHandler serializationHandler) throws TransletException {
        if (i != -1) {
            this._adapters[i >>> 16].characters(i, serializationHandler);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void copy(DTMAxisIterator dTMAxisIterator, SerializationHandler serializationHandler) throws TransletException {
        while (true) {
            int next = dTMAxisIterator.next();
            if (next == -1) {
                return;
            } else {
                this._adapters[next >>> 16].copy(next, serializationHandler);
            }
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getAttributeNode(int i, int i2) {
        if (i2 == -1) {
            return -1;
        }
        return this._adapters[i2 >>> 16].getAttributeNode(i, i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public DTMAxisIterator getAxisIterator(int i) {
        return new AxisIterator(i, -2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getChildren(int i) {
        return this._adapters[getDTMId(i)].getChildren(i);
    }

    public DOM getDOMAdapter(String str) {
        Integer num = this._documents.get(str);
        if (num == null) {
            return null;
        }
        return this._adapters[num.intValue()];
    }

    public DOM getDTM(int i) {
        return this._adapters[getDTMId(i)];
    }

    public int getDTMId(int i) {
        if (i == -1) {
            return 0;
        }
        int i2 = i >>> 16;
        while (i2 >= 2) {
            DOM[] domArr = this._adapters;
            if (domArr[i2] != domArr[i2 - 1]) {
                break;
            }
            i2--;
        }
        return i2;
    }

    public DTMManager getDTMManager() {
        return this._dtmManager;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public int getDocument() {
        return this._main.getDocument();
    }

    public int getDocumentMask(String str) {
        Integer num = this._documents.get(str);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getDocumentURI(int i) {
        if (i == -1) {
            i = 0;
        }
        return this._adapters[i >>> 16].getDocumentURI(0);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public Map<String, Integer> getElementsWithIDs() {
        return this._main.getElementsWithIDs();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public int getExpandedTypeID(int i) {
        if (i != -1) {
            return this._adapters[i >>> 16].getExpandedTypeID(i);
        }
        return -1;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getIterator() {
        return this._main.getIterator();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getLanguage(int i) {
        return this._adapters[getDTMId(i)].getLanguage(i);
    }

    public DOM getMain() {
        return this._main;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getNSType(int i) {
        return this._adapters[getDTMId(i)].getNSType(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getNamespaceAxisIterator(int i, int i2) {
        return this._main.getNamespaceAxisIterator(i, i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getNamespaceName(int i) {
        return i == -1 ? "" : this._adapters[i >>> 16].getNamespaceName(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getNamespaceType(int i) {
        return this._adapters[getDTMId(i)].getNamespaceType(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getNodeHandle(int i) {
        return this._main.getNodeHandle(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getNodeIdent(int i) {
        return this._adapters[i >>> 16].getNodeIdent(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeName(int i) {
        return i == -1 ? "" : this._adapters[i >>> 16].getNodeName(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeNameX(int i) {
        return i == -1 ? "" : this._adapters[i >>> 16].getNodeNameX(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getNodeValueIterator(DTMAxisIterator dTMAxisIterator, int i, String str, boolean z) {
        return new NodeValueIterator(dTMAxisIterator, i, str, z);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getNthDescendant(int i, int i2, boolean z) {
        return this._adapters[getDTMId(i)].getNthDescendant(i, i2, z);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public SerializationHandler getOutputDomBuilder() {
        return this._main.getOutputDomBuilder();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public int getParent(int i) {
        if (i == -1) {
            return -1;
        }
        return this._adapters[i >>> 16].getParent(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DOM getResultTreeFrag(int i, int i2) {
        return this._main.getResultTreeFrag(i, i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getSize() {
        int size = 0;
        for (int i = 0; i < this._size; i++) {
            size += this._adapters[i].getSize();
        }
        return size;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getStringValue() {
        return this._main.getStringValue();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getStringValueX(int i) {
        return i == -1 ? "" : this._adapters[i >>> 16].getStringValueX(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public DTMAxisIterator getTypedAxisIterator(int i, int i2) {
        return new AxisIterator(i, i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getTypedChildren(int i) {
        return new AxisIterator(3, i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public String getUnparsedEntityURI(String str) {
        return this._main.getUnparsedEntityURI(str);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public boolean isAttribute(int i) {
        if (i == -1) {
            return false;
        }
        return this._adapters[i >>> 16].isAttribute(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public boolean isElement(int i) {
        if (i == -1) {
            return false;
        }
        return this._adapters[i >>> 16].isElement(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public boolean lessThan(int i, int i2) {
        if (i == -1) {
            return true;
        }
        if (i2 == -1) {
            return false;
        }
        int dTMId = getDTMId(i);
        int dTMId2 = getDTMId(i2);
        if (dTMId == dTMId2) {
            return this._adapters[dTMId].lessThan(i, i2);
        }
        return dTMId < dTMId2;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String lookupNamespace(int i, String str) throws TransletException {
        return this._main.lookupNamespace(i, str);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public Node makeNode(int i) {
        if (i == -1) {
            return null;
        }
        return this._adapters[getDTMId(i)].makeNode(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public NodeList makeNodeList(DTMAxisIterator dTMAxisIterator) {
        int next = dTMAxisIterator.next();
        if (next == -1) {
            return new DTMAxisIterNodeList(null, null);
        }
        dTMAxisIterator.reset();
        return this._adapters[getDTMId(next)].makeNodeList(dTMAxisIterator);
    }

    public int nextMask() {
        return this._free;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator orderNodes(DTMAxisIterator dTMAxisIterator, int i) {
        return this._adapters[getDTMId(i)].orderNodes(dTMAxisIterator, i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void release() {
        this._main.release();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void removeDOMAdapter(DOMAdapter dOMAdapter) {
        int i = 0;
        this._documents.remove(dOMAdapter.getDocumentURI(0));
        DOM dOMImpl = dOMAdapter.getDOMImpl();
        if (dOMImpl instanceof DTMDefaultBase) {
            SuballocatedIntVector dTMIDs = ((DTMDefaultBase) dOMImpl).getDTMIDs();
            int size = dTMIDs.size();
            while (i < size) {
                this._adapters[dTMIDs.elementAt(i) >>> 16] = null;
                i++;
            }
            return;
        }
        int document = dOMImpl.getDocument() >>> 16;
        if (document > 0) {
            DOM[] domArr = this._adapters;
            if (document < domArr.length && isMatchingAdapterEntry(domArr[document], dOMAdapter)) {
                this._adapters[document] = null;
                return;
            }
        }
        while (true) {
            DOM[] domArr2 = this._adapters;
            if (i >= domArr2.length) {
                return;
            }
            if (isMatchingAdapterEntry(domArr2[document], dOMAdapter)) {
                this._adapters[i] = null;
                return;
            }
            i++;
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void setFilter(StripFilter stripFilter) {
        for (int i = 0; i < this._free; i++) {
            DOM dom = this._adapters[i];
            if (dom != null) {
                dom.setFilter(stripFilter);
            }
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void setupMapping(String[] strArr, String[] strArr2, int[] iArr, String[] strArr3) {
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String shallowCopy(int i, SerializationHandler serializationHandler) throws TransletException {
        return i == -1 ? "" : this._adapters[i >>> 16].shallowCopy(i, serializationHandler);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DOM getResultTreeFrag(int i, int i2, boolean z) {
        return this._main.getResultTreeFrag(i, i2, z);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void copy(int i, SerializationHandler serializationHandler) throws TransletException {
        if (i != -1) {
            this._adapters[i >>> 16].copy(i, serializationHandler);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public Node makeNode(DTMAxisIterator dTMAxisIterator) {
        return this._main.makeNode(dTMAxisIterator);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public NodeList makeNodeList(int i) {
        if (i == -1) {
            return null;
        }
        return this._adapters[getDTMId(i)].makeNodeList(i);
    }

    public int addDOMAdapter(DOMAdapter dOMAdapter) {
        return addDOMAdapter(dOMAdapter, true);
    }
}
