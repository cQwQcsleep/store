package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.compiler.Compiler;
import com.sun.org.apache.xpath.internal.objects.XNodeSet;
import com.sun.org.apache.xpath.internal.operations.Variable;
import java.util.List;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FilterExprWalker extends AxesWalker {
    static final long serialVersionUID = 5457182471424488375L;
    private boolean m_canDetachNodeset;
    private Expression m_expr;
    private transient XNodeSet m_exprObj;
    private boolean m_mustHardReset;

    public class filterExprOwner implements ExpressionOwner {
        public filterExprOwner() {
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public Expression getExpression() {
            return FilterExprWalker.this.m_expr;
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public void setExpression(Expression expression) {
            expression.exprSetParent(FilterExprWalker.this);
            FilterExprWalker.this.m_expr = expression;
        }
    }

    public FilterExprWalker(WalkingIterator walkingIterator) {
        super(walkingIterator, 20);
        this.m_mustHardReset = false;
        this.m_canDetachNodeset = true;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest
    public short acceptNode(int i) {
        try {
            if (getPredicateCount() <= 0) {
                return (short) 1;
            }
            countProximityPosition(0);
            return !executePredicates(i, this.m_lpi.getXPathContext()) ? (short) 3 : (short) 1;
        } catch (TransformerException e) {
            f63.a(e.getMessage());
            return (short) 0;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest
    public void callPredicateVisitors(XPathVisitor xPathVisitor) {
        this.m_expr.callVisitors(new filterExprOwner(), xPathVisitor);
        super.callPredicateVisitors(xPathVisitor);
    }

    @Override // com.sun.org.apache.xpath.internal.axes.AxesWalker, com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest
    public Object clone() throws CloneNotSupportedException {
        FilterExprWalker filterExprWalker = (FilterExprWalker) super.clone();
        XNodeSet xNodeSet = this.m_exprObj;
        if (xNodeSet != null) {
            filterExprWalker.m_exprObj = (XNodeSet) xNodeSet.clone();
        }
        return filterExprWalker;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.AxesWalker, com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest, com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public boolean deepEquals(Expression expression) {
        return super.deepEquals(expression) && this.m_expr.deepEquals(((FilterExprWalker) expression).m_expr);
    }

    @Override // com.sun.org.apache.xpath.internal.axes.AxesWalker
    public void detach() {
        super.detach();
        if (this.m_canDetachNodeset) {
            this.m_exprObj.detach();
        }
        this.m_exprObj = null;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest, com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
        super.fixupVariables(list, i);
        this.m_expr.fixupVariables(list, i);
    }

    @Override // com.sun.org.apache.xpath.internal.axes.AxesWalker, com.sun.org.apache.xpath.internal.axes.PathComponent
    public int getAnalysisBits() {
        SourceLocator sourceLocator = this.m_expr;
        if (sourceLocator == null || !(sourceLocator instanceof PathComponent)) {
            return 67108864;
        }
        return ((PathComponent) sourceLocator).getAnalysisBits();
    }

    @Override // com.sun.org.apache.xpath.internal.axes.AxesWalker
    public int getAxis() {
        return this.m_exprObj.getAxis();
    }

    public Expression getInnerExpression() {
        return this.m_expr;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.AxesWalker, com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest, com.sun.org.apache.xpath.internal.axes.SubContextList
    public int getLastPos(XPathContext xPathContext) {
        return this.m_exprObj.getLength();
    }

    @Override // com.sun.org.apache.xpath.internal.axes.AxesWalker
    public int getNextNode() {
        XNodeSet xNodeSet = this.m_exprObj;
        if (xNodeSet != null) {
            return xNodeSet.nextNode();
        }
        return -1;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.AxesWalker
    public void init(Compiler compiler, int i, int i2) throws TransformerException {
        super.init(compiler, i, i2);
        switch (i2) {
            case 22:
            case 23:
                break;
            case 24:
            case 25:
                this.m_mustHardReset = true;
                break;
            default:
                Expression expressionCompileExpression = compiler.compileExpression(i + 2);
                this.m_expr = expressionCompileExpression;
                expressionCompileExpression.exprSetParent(this);
                return;
        }
        Expression expressionCompileExpression2 = compiler.compileExpression(i);
        this.m_expr = expressionCompileExpression2;
        expressionCompileExpression2.exprSetParent(this);
        if (this.m_expr instanceof Variable) {
            this.m_canDetachNodeset = false;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.axes.AxesWalker
    public boolean isDocOrdered() {
        return this.m_exprObj.isDocOrdered();
    }

    public void setInnerExpression(Expression expression) {
        expression.exprSetParent(this);
        this.m_expr = expression;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.AxesWalker
    public void setRoot(int i) {
        super.setRoot(i);
        this.m_exprObj = FilterExprIteratorSimple.executeFilterExpr(i, this.m_lpi.getXPathContext(), this.m_lpi.getPrefixResolver(), this.m_lpi.getIsTopLevel(), this.m_lpi.m_stackFrame, this.m_expr);
    }
}
