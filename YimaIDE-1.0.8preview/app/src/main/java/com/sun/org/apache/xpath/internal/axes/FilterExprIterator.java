package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.objects.XNodeSet;
import java.util.List;
import javax.xml.transform.SourceLocator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FilterExprIterator extends BasicTestIterator {
    static final long serialVersionUID = 2552176105165737614L;
    private boolean m_canDetachNodeset;
    private Expression m_expr;
    private transient XNodeSet m_exprObj;
    private boolean m_mustHardReset;

    public class filterExprOwner implements ExpressionOwner {
        public filterExprOwner() {
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public Expression getExpression() {
            return FilterExprIterator.this.m_expr;
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public void setExpression(Expression expression) {
            expression.exprSetParent(FilterExprIterator.this);
            FilterExprIterator.this.m_expr = expression;
        }
    }

    public FilterExprIterator(Expression expression) {
        super(null);
        this.m_mustHardReset = false;
        this.m_canDetachNodeset = true;
        this.m_expr = expression;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest
    public void callPredicateVisitors(XPathVisitor xPathVisitor) {
        this.m_expr.callVisitors(new filterExprOwner(), xPathVisitor);
        super.callPredicateVisitors(xPathVisitor);
    }

    @Override // com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest, com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public boolean deepEquals(Expression expression) {
        return super.deepEquals(expression) && this.m_expr.deepEquals(((FilterExprIterator) expression).m_expr);
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void detach() {
        super.detach();
        this.m_exprObj.detach();
        this.m_exprObj = null;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest, com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
        super.fixupVariables(list, i);
        this.m_expr.fixupVariables(list, i);
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xpath.internal.axes.PathComponent
    public int getAnalysisBits() {
        SourceLocator sourceLocator = this.m_expr;
        if (sourceLocator == null || !(sourceLocator instanceof PathComponent)) {
            return 67108864;
        }
        return ((PathComponent) sourceLocator).getAnalysisBits();
    }

    public Expression getInnerExpression() {
        return this.m_expr;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.BasicTestIterator
    public int getNextNode() {
        XNodeSet xNodeSet = this.m_exprObj;
        if (xNodeSet != null) {
            this.m_lastFetched = xNodeSet.nextNode();
        } else {
            this.m_lastFetched = -1;
        }
        return this.m_lastFetched;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public boolean isDocOrdered() {
        return this.m_exprObj.isDocOrdered();
    }

    public void setInnerExpression(Expression expression) {
        expression.exprSetParent(this);
        this.m_expr = expression;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void setRoot(int i, Object obj) {
        super.setRoot(i, obj);
        this.m_exprObj = FilterExprIteratorSimple.executeFilterExpr(i, this.m_execContext, getPrefixResolver(), getIsTopLevel(), this.m_stackFrame, this.m_expr);
    }

    public FilterExprIterator() {
        super(null);
        this.m_mustHardReset = false;
        this.m_canDetachNodeset = true;
    }
}
