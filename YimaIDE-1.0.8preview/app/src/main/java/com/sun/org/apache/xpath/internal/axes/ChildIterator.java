package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.compiler.Compiler;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ChildIterator extends LocPathIterator {
    static final long serialVersionUID = -6935428015142993583L;

    public ChildIterator(Compiler compiler, int i, int i2) throws TransformerException {
        super(compiler, i, i2, false);
        initNodeTest(-1);
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xpath.internal.Expression
    public int asNode(XPathContext xPathContext) throws TransformerException {
        int currentNode = xPathContext.getCurrentNode();
        return xPathContext.getDTM(currentNode).getFirstChild(currentNode);
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getAxis() {
        return 3;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int nextNode() {
        if (this.m_foundLast) {
            return -1;
        }
        int i = this.m_lastFetched;
        DTM dtm = this.m_cdtm;
        int firstChild = -1 == i ? dtm.getFirstChild(this.m_context) : dtm.getNextSibling(i);
        this.m_lastFetched = firstChild;
        if (-1 != firstChild) {
            this.m_pos++;
            return firstChild;
        }
        this.m_foundLast = true;
        return -1;
    }
}
