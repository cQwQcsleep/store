package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.dtm.DTMAxisTraverser;
import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xpath.internal.compiler.Compiler;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ChildTestIterator extends BasicTestIterator {
    static final long serialVersionUID = -7936835957960705722L;
    protected transient DTMAxisTraverser m_traverser;

    public ChildTestIterator(DTMAxisTraverser dTMAxisTraverser) {
        super(null);
        this.m_traverser = dTMAxisTraverser;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.BasicTestIterator, com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public DTMIterator cloneWithReset() throws CloneNotSupportedException {
        ChildTestIterator childTestIterator = (ChildTestIterator) super.cloneWithReset();
        childTestIterator.m_traverser = this.m_traverser;
        return childTestIterator;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void detach() {
        if (this.m_allowDetach) {
            this.m_traverser = null;
            super.detach();
        }
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getAxis() {
        return 3;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.BasicTestIterator
    public int getNextNode() {
        int i = this.m_lastFetched;
        DTMAxisTraverser dTMAxisTraverser = this.m_traverser;
        int iFirst = -1 == i ? dTMAxisTraverser.first(this.m_context) : dTMAxisTraverser.next(this.m_context, i);
        this.m_lastFetched = iFirst;
        return iFirst;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void setRoot(int i, Object obj) {
        super.setRoot(i, obj);
        this.m_traverser = this.m_cdtm.getAxisTraverser(3);
    }

    public ChildTestIterator(Compiler compiler, int i, int i2) throws TransformerException {
        super(compiler, i, i2);
    }
}
