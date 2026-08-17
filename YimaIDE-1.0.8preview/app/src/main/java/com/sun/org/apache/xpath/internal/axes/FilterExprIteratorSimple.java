package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.VariableStack;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.objects.XNodeSet;
import java.util.List;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FilterExprIteratorSimple extends LocPathIterator {
    static final long serialVersionUID = -6978977187025375579L;
    private boolean m_canDetachNodeset;
    private Expression m_expr;
    private transient XNodeSet m_exprObj;
    private boolean m_mustHardReset;

    public class filterExprOwner implements ExpressionOwner {
        public filterExprOwner() {
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public Expression getExpression() {
            return FilterExprIteratorSimple.this.m_expr;
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public void setExpression(Expression expression) {
            expression.exprSetParent(FilterExprIteratorSimple.this);
            FilterExprIteratorSimple.this.m_expr = expression;
        }
    }

    public FilterExprIteratorSimple(Expression expression) {
        super(null);
        this.m_mustHardReset = false;
        this.m_canDetachNodeset = true;
        this.m_expr = expression;
    }

    public static XNodeSet executeFilterExpr(int i, XPathContext xPathContext, PrefixResolver prefixResolver, boolean z, int i2, Expression expression) throws WrappedRuntimeException {
        XNodeSet xNodeSet;
        PrefixResolver namespaceContext = xPathContext.getNamespaceContext();
        try {
            try {
                xPathContext.pushCurrentNode(i);
                xPathContext.setNamespaceContext(prefixResolver);
                if (z) {
                    VariableStack varStack = xPathContext.getVarStack();
                    int stackFrame = varStack.getStackFrame();
                    varStack.setStackFrame(i2);
                    xNodeSet = (XNodeSet) expression.execute(xPathContext);
                    xNodeSet.setShouldCacheNodes(true);
                    varStack.setStackFrame(stackFrame);
                } else {
                    xNodeSet = (XNodeSet) expression.execute(xPathContext);
                }
                xPathContext.popCurrentNode();
                xPathContext.setNamespaceContext(namespaceContext);
                return xNodeSet;
            } catch (TransformerException e) {
                throw new WrappedRuntimeException(e);
            }
        } catch (Throwable th) {
            xPathContext.popCurrentNode();
            xPathContext.setNamespaceContext(namespaceContext);
            throw th;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest
    public void callPredicateVisitors(XPathVisitor xPathVisitor) {
        this.m_expr.callVisitors(new filterExprOwner(), xPathVisitor);
        super.callPredicateVisitors(xPathVisitor);
    }

    @Override // com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest, com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public boolean deepEquals(Expression expression) {
        return super.deepEquals(expression) && this.m_expr.deepEquals(((FilterExprIteratorSimple) expression).m_expr);
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void detach() {
        if (this.m_allowDetach) {
            super.detach();
            this.m_exprObj.detach();
            this.m_exprObj = null;
        }
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

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getAxis() {
        XNodeSet xNodeSet = this.m_exprObj;
        if (xNodeSet != null) {
            return xNodeSet.getAxis();
        }
        return 20;
    }

    public Expression getInnerExpression() {
        return this.m_expr;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public boolean isDocOrdered() {
        return this.m_exprObj.isDocOrdered();
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int nextNode() {
        int iNextNode;
        if (this.m_foundLast) {
            return -1;
        }
        XNodeSet xNodeSet = this.m_exprObj;
        if (xNodeSet != null) {
            iNextNode = xNodeSet.nextNode();
            this.m_lastFetched = iNextNode;
        } else {
            this.m_lastFetched = -1;
            iNextNode = -1;
        }
        if (-1 != iNextNode) {
            this.m_pos++;
            return iNextNode;
        }
        this.m_foundLast = true;
        return -1;
    }

    public void setInnerExpression(Expression expression) {
        expression.exprSetParent(this);
        this.m_expr = expression;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void setRoot(int i, Object obj) {
        super.setRoot(i, obj);
        this.m_exprObj = executeFilterExpr(i, this.m_execContext, getPrefixResolver(), getIsTopLevel(), this.m_stackFrame, this.m_expr);
    }

    public FilterExprIteratorSimple() {
        super(null);
        this.m_mustHardReset = false;
        this.m_canDetachNodeset = true;
    }
}
