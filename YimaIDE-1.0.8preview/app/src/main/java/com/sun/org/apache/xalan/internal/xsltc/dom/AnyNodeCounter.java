package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.Translet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xpath.internal.XPath;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class AnyNodeCounter extends NodeCounter {

    public static class DefaultAnyNodeCounter extends AnyNodeCounter {
        public DefaultAnyNodeCounter(Translet translet, DOM dom, DTMAxisIterator dTMAxisIterator) {
            super(translet, dom, dTMAxisIterator);
        }

        @Override // com.sun.org.apache.xalan.internal.xsltc.dom.AnyNodeCounter, com.sun.org.apache.xalan.internal.xsltc.dom.NodeCounter
        public String getCounter() {
            int i;
            double d = this._value;
            if (d == -2.147483648E9d) {
                int i2 = this._node;
                int expandedTypeID = this._document.getExpandedTypeID(i2);
                int document = this._document.getDocument();
                int i3 = 0;
                while (i2 >= 0) {
                    if (expandedTypeID == this._document.getExpandedTypeID(i2)) {
                        i3++;
                    }
                    if (i2 == document) {
                        break;
                    }
                    i2--;
                }
                i = i3;
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
    }

    public AnyNodeCounter(Translet translet, DOM dom, DTMAxisIterator dTMAxisIterator) {
        super(translet, dom, dTMAxisIterator);
    }

    public static NodeCounter getDefaultNodeCounter(Translet translet, DOM dom, DTMAxisIterator dTMAxisIterator) {
        return new DefaultAnyNodeCounter(translet, dom, dTMAxisIterator);
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
        int document = this._document.getDocument();
        int i = 0;
        for (int i2 = this._node; i2 >= document && !matchesFrom(i2); i2--) {
            if (matchesCount(i2)) {
                i++;
            }
        }
        return formatNumbers(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.NodeCounter
    public NodeCounter setStartNode(int i) {
        this._node = i;
        this._nodeType = this._document.getExpandedTypeID(i);
        return this;
    }

    public AnyNodeCounter(Translet translet, DOM dom, DTMAxisIterator dTMAxisIterator, boolean z) {
        super(translet, dom, dTMAxisIterator, z);
    }
}
