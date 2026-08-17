package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.Translet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xpath.internal.XPath;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SingleNodeCounter extends NodeCounter {
    private static final int[] EmptyArray = new int[0];
    DTMAxisIterator _countSiblings;

    public static class DefaultSingleNodeCounter extends SingleNodeCounter {
        public DefaultSingleNodeCounter(Translet translet, DOM dom, DTMAxisIterator dTMAxisIterator) {
            super(translet, dom, dTMAxisIterator);
        }

        @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SingleNodeCounter, com.sun.org.apache.xalan.internal.xsltc.dom.NodeCounter
        public String getCounter() {
            int i;
            double d = this._value;
            if (d == -2.147483648E9d) {
                this._countSiblings.setStartNode(this._node);
                i = 1;
                while (this._countSiblings.next() != -1) {
                    i++;
                }
            } else {
                if (d == XPath.MATCH_SCORE_QNAME) {
                    return "0";
                }
                if (Double.isNaN(d)) {
                    return "NaN";
                }
                double d2 = this._value;
                if (d2 < XPath.MATCH_SCORE_QNAME && Double.isInfinite(d2)) {
                    return "-Infinity";
                }
                if (Double.isInfinite(this._value)) {
                    return Constants.ATTRVAL_INFINITY;
                }
                i = (int) this._value;
            }
            return formatNumbers(i);
        }

        @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SingleNodeCounter, com.sun.org.apache.xalan.internal.xsltc.dom.NodeCounter
        public NodeCounter setStartNode(int i) {
            this._node = i;
            this._nodeType = this._document.getExpandedTypeID(i);
            DOM dom = this._document;
            this._countSiblings = dom.getTypedAxisIterator(12, dom.getExpandedTypeID(i));
            return this;
        }
    }

    public SingleNodeCounter(Translet translet, DOM dom, DTMAxisIterator dTMAxisIterator) {
        super(translet, dom, dTMAxisIterator);
        this._countSiblings = null;
    }

    public static NodeCounter getDefaultNodeCounter(Translet translet, DOM dom, DTMAxisIterator dTMAxisIterator) {
        return new DefaultSingleNodeCounter(translet, dom, dTMAxisIterator);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.NodeCounter
    public String getCounter() {
        int parent;
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
        int next = this._node;
        boolean zMatchesCount = matchesCount(next);
        if (!zMatchesCount) {
            while (true) {
                next = this._document.getParent(next);
                if (next <= -1 || matchesCount(next)) {
                    break;
                }
                if (matchesFrom(next)) {
                    next = -1;
                    break;
                }
            }
        }
        if (next != -1) {
            if (!zMatchesCount && this._hasFrom) {
                parent = next;
                do {
                    parent = this._document.getParent(parent);
                    if (parent <= -1) {
                        break;
                    }
                } while (!matchesFrom(parent));
            } else {
                parent = next;
            }
            if (parent != -1) {
                this._countSiblings.setStartNode(next);
                int i = 0;
                do {
                    if (matchesCount(next)) {
                        i++;
                    }
                    next = this._countSiblings.next();
                } while (next != -1);
                return formatNumbers(i);
            }
        }
        return formatNumbers(EmptyArray);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.NodeCounter
    public NodeCounter setStartNode(int i) {
        this._node = i;
        this._nodeType = this._document.getExpandedTypeID(i);
        this._countSiblings = this._document.getAxisIterator(12);
        return this;
    }

    public SingleNodeCounter(Translet translet, DOM dom, DTMAxisIterator dTMAxisIterator, boolean z) {
        super(translet, dom, dTMAxisIterator, z);
        this._countSiblings = null;
    }
}
