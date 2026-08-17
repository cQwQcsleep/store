package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.compiler.Compiler;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SelfIteratorNoPredicate extends LocPathIterator {
    static final long serialVersionUID = -4226887905279814201L;

    public SelfIteratorNoPredicate(Compiler compiler, int i, int i2) throws TransformerException {
        super(compiler, i, i2, false);
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xpath.internal.Expression
    public int asNode(XPathContext xPathContext) throws TransformerException {
        return xPathContext.getCurrentNode();
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest, com.sun.org.apache.xpath.internal.axes.SubContextList
    public int getLastPos(XPathContext xPathContext) {
        return 1;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int nextNode() {
        if (this.m_foundLast) {
            return -1;
        }
        int i = -1 == this.m_lastFetched ? this.m_context : -1;
        this.m_lastFetched = i;
        if (-1 != i) {
            this.m_pos++;
            return i;
        }
        this.m_foundLast = true;
        return -1;
    }

    public SelfIteratorNoPredicate() throws TransformerException {
        super(null);
    }
}
