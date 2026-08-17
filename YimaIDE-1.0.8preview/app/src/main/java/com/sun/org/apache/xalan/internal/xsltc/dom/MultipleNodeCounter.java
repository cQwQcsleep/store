package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.Translet;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xpath.internal.XPath;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class MultipleNodeCounter extends NodeCounter {
    private DTMAxisIterator _precSiblings;

    public static class DefaultMultipleNodeCounter extends MultipleNodeCounter {
        public DefaultMultipleNodeCounter(Translet translet, DOM dom, DTMAxisIterator dTMAxisIterator) {
            super(translet, dom, dTMAxisIterator);
        }
    }

    public MultipleNodeCounter(Translet translet, DOM dom, DTMAxisIterator dTMAxisIterator) {
        super(translet, dom, dTMAxisIterator);
        this._precSiblings = null;
    }

    public static NodeCounter getDefaultNodeCounter(Translet translet, DOM dom, DTMAxisIterator dTMAxisIterator) {
        return new DefaultMultipleNodeCounter(translet, dom, dTMAxisIterator);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.NodeCounter
    public String getCounter() {
        double d = this._value;
        if (d != -2.147483648E9d) {
            if (d == XPath.MATCH_SCORE_QNAME) {
                return "0";
            }
            if (Double.isNaN(d)) {
                return "NaN";
            }
            double d2 = this._value;
            if (d2 >= XPath.MATCH_SCORE_QNAME || !Double.isInfinite(d2)) {
                return Double.isInfinite(this._value) ? Constants.ATTRVAL_INFINITY : formatNumbers((int) this._value);
            }
            return "-Infinity";
        }
        IntegerArray integerArray = new IntegerArray();
        int parent = this._node;
        integerArray.add(parent);
        while (true) {
            parent = this._document.getParent(parent);
            if (parent <= -1 || matchesFrom(parent)) {
                break;
            }
            integerArray.add(parent);
        }
        int iCardinality = integerArray.cardinality();
        int[] iArr = new int[iCardinality];
        int i = 0;
        for (int i2 = 0; i2 < iCardinality; i2++) {
            iArr[i2] = Integer.MIN_VALUE;
        }
        int i3 = iCardinality - 1;
        while (i3 >= 0) {
            int i4 = iArr[i];
            int iAt = integerArray.at(i3);
            if (matchesCount(iAt)) {
                this._precSiblings.setStartNode(iAt);
                while (true) {
                    int next = this._precSiblings.next();
                    if (next == -1) {
                        break;
                    }
                    if (matchesCount(next)) {
                        int i5 = iArr[i];
                        iArr[i] = i5 == Integer.MIN_VALUE ? 1 : i5 + 1;
                    }
                }
                int i6 = iArr[i];
                iArr[i] = i6 == Integer.MIN_VALUE ? 1 : i6 + 1;
            }
            i3--;
            i++;
        }
        return formatNumbers(iArr);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.NodeCounter
    public NodeCounter setStartNode(int i) {
        this._node = i;
        this._nodeType = this._document.getExpandedTypeID(i);
        this._precSiblings = this._document.getAxisIterator(12);
        return this;
    }

    public MultipleNodeCounter(Translet translet, DOM dom, DTMAxisIterator dTMAxisIterator, boolean z) {
        super(translet, dom, dTMAxisIterator, z);
        this._precSiblings = null;
    }
}
