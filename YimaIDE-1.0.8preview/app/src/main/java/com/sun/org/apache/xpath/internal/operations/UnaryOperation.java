package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.objects.XObject;
import java.util.List;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class UnaryOperation extends Expression implements ExpressionOwner {
    static final long serialVersionUID = 6536083808424286166L;
    protected Expression m_right;

    @Override // com.sun.org.apache.xpath.internal.XPathVisitable
    public void callVisitors(ExpressionOwner expressionOwner, XPathVisitor xPathVisitor) {
        if (xPathVisitor.visitUnaryOperation(expressionOwner, this)) {
            this.m_right.callVisitors(this, xPathVisitor);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public boolean canTraverseOutsideSubtree() {
        Expression expression = this.m_right;
        return expression != null && expression.canTraverseOutsideSubtree();
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public boolean deepEquals(Expression expression) {
        return isSameClass(expression) && this.m_right.deepEquals(((UnaryOperation) expression).m_right);
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        return operate(this.m_right.execute(xPathContext));
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
        this.m_right.fixupVariables(list, i);
    }

    @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
    public Expression getExpression() {
        return this.m_right;
    }

    public Expression getOperand() {
        return this.m_right;
    }

    public abstract XObject operate(XObject xObject) throws TransformerException;

    @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
    public void setExpression(Expression expression) {
        expression.exprSetParent(this);
        this.m_right = expression;
    }

    public void setRight(Expression expression) {
        this.m_right = expression;
        expression.exprSetParent(this);
    }
}
