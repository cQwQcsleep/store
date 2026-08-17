package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Function2Args extends FunctionOneArg {
    static final long serialVersionUID = 5574294996842710641L;
    Expression m_arg1;

    public class Arg1Owner implements ExpressionOwner {
        public Arg1Owner() {
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public Expression getExpression() {
            return Function2Args.this.m_arg1;
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public void setExpression(Expression expression) {
            expression.exprSetParent(Function2Args.this);
            Function2Args.this.m_arg1 = expression;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void callArgVisitors(XPathVisitor xPathVisitor) {
        super.callArgVisitors(xPathVisitor);
        Expression expression = this.m_arg1;
        if (expression != null) {
            expression.callVisitors(new Arg1Owner(), xPathVisitor);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.Expression
    public boolean canTraverseOutsideSubtree() {
        if (super.canTraverseOutsideSubtree()) {
            return true;
        }
        return this.m_arg1.canTraverseOutsideSubtree();
    }

    @Override // com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void checkNumberArgs(int i) throws WrongNumberArgsException {
        if (i != 2) {
            reportWrongNumberArgs();
        }
    }

    @Override // com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public boolean deepEquals(Expression expression) {
        if (!super.deepEquals(expression)) {
            return false;
        }
        Expression expression2 = this.m_arg1;
        if (expression2 == null) {
            return ((Function2Args) expression).m_arg1 == null;
        }
        Expression expression3 = ((Function2Args) expression).m_arg1;
        return expression3 != null && expression2.deepEquals(expression3);
    }

    @Override // com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
        super.fixupVariables(list, i);
        Expression expression = this.m_arg1;
        if (expression != null) {
            expression.fixupVariables(list, i);
        }
    }

    public Expression getArg1() {
        return this.m_arg1;
    }

    @Override // com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void reportWrongNumberArgs() throws WrongNumberArgsException {
        throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("two", null));
    }

    @Override // com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void setArg(Expression expression, int i) throws WrongNumberArgsException {
        if (i == 0) {
            super.setArg(expression, i);
        } else if (1 != i) {
            reportWrongNumberArgs();
        } else {
            this.m_arg1 = expression;
            expression.exprSetParent(this);
        }
    }
}
