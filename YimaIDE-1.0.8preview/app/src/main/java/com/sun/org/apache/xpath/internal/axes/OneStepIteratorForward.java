package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.dtm.DTMAxisTraverser;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.compiler.Compiler;
import com.sun.org.apache.xpath.internal.compiler.OpMap;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class OneStepIteratorForward extends ChildTestIterator {
    static final long serialVersionUID = -1576936606178190566L;
    protected int m_axis;

    public OneStepIteratorForward(Compiler compiler, int i, int i2) throws TransformerException {
        super(compiler, i, i2);
        this.m_axis = -1;
        this.m_axis = WalkerFactory.getAxisFromStep(compiler, OpMap.getFirstChildPos(i));
    }

    @Override // com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest, com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public boolean deepEquals(Expression expression) {
        return super.deepEquals(expression) && this.m_axis == ((OneStepIteratorForward) expression).m_axis;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.ChildTestIterator, com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getAxis() {
        return this.m_axis;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.ChildTestIterator, com.sun.org.apache.xpath.internal.axes.BasicTestIterator
    public int getNextNode() {
        int i = this.m_lastFetched;
        DTMAxisTraverser dTMAxisTraverser = this.m_traverser;
        int iFirst = -1 == i ? dTMAxisTraverser.first(this.m_context) : dTMAxisTraverser.next(this.m_context, i);
        this.m_lastFetched = iFirst;
        return iFirst;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.ChildTestIterator, com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void setRoot(int i, Object obj) {
        super.setRoot(i, obj);
        this.m_traverser = this.m_cdtm.getAxisTraverser(this.m_axis);
    }

    public OneStepIteratorForward(int i) {
        super(null);
        this.m_axis = i;
        initNodeTest(-1);
    }
}
