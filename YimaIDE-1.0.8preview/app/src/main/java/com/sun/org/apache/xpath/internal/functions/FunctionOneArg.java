package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FunctionOneArg extends Function implements ExpressionOwner {
    static final long serialVersionUID = -5180174180765609758L;
    Expression m_arg0;

    @Override // com.sun.org.apache.xpath.internal.functions.Function
    public void callArgVisitors(XPathVisitor xPathVisitor) {
        Expression expression = this.m_arg0;
        if (expression != null) {
            expression.callVisitors(this, xPathVisitor);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public boolean canTraverseOutsideSubtree() {
        return this.m_arg0.canTraverseOutsideSubtree();
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function
    public void checkNumberArgs(int i) throws WrongNumberArgsException {
        if (i != 1) {
            reportWrongNumberArgs();
        }
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public boolean deepEquals(Expression expression) {
        if (!super.deepEquals(expression)) {
            return false;
        }
        Expression expression2 = this.m_arg0;
        if (expression2 == null) {
            return ((FunctionOneArg) expression).m_arg0 == null;
        }
        Expression expression3 = ((FunctionOneArg) expression).m_arg0;
        return expression3 != null && expression2.deepEquals(expression3);
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
        Expression expression = this.m_arg0;
        if (expression != null) {
            expression.fixupVariables(list, i);
        }
    }

    public Expression getArg0() {
        return this.m_arg0;
    }

    @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
    public Expression getExpression() {
        return this.m_arg0;
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function
    public void reportWrongNumberArgs() throws WrongNumberArgsException {
        throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("one", null));
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function
    public void setArg(Expression expression, int i) throws WrongNumberArgsException {
        if (i != 0) {
            reportWrongNumberArgs();
        } else {
            this.m_arg0 = expression;
            expression.exprSetParent(this);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
    public void setExpression(Expression expression) {
        expression.exprSetParent(this);
        this.m_arg0 = expression;
    }
}
