package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Function3Args extends Function2Args {
    static final long serialVersionUID = 7915240747161506646L;
    Expression m_arg2;

    public class Arg2Owner implements ExpressionOwner {
        public Arg2Owner() {
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public Expression getExpression() {
            return Function3Args.this.m_arg2;
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public void setExpression(Expression expression) {
            expression.exprSetParent(Function3Args.this);
            Function3Args.this.m_arg2 = expression;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void callArgVisitors(XPathVisitor xPathVisitor) {
        super.callArgVisitors(xPathVisitor);
        Expression expression = this.m_arg2;
        if (expression != null) {
            expression.callVisitors(new Arg2Owner(), xPathVisitor);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.Expression
    public boolean canTraverseOutsideSubtree() {
        if (super.canTraverseOutsideSubtree()) {
            return true;
        }
        return this.m_arg2.canTraverseOutsideSubtree();
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void checkNumberArgs(int i) throws WrongNumberArgsException {
        if (i != 3) {
            reportWrongNumberArgs();
        }
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public boolean deepEquals(Expression expression) {
        if (!super.deepEquals(expression)) {
            return false;
        }
        Expression expression2 = this.m_arg2;
        if (expression2 == null) {
            return ((Function3Args) expression).m_arg2 == null;
        }
        Expression expression3 = ((Function3Args) expression).m_arg2;
        return expression3 != null && expression2.deepEquals(expression3);
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
        super.fixupVariables(list, i);
        Expression expression = this.m_arg2;
        if (expression != null) {
            expression.fixupVariables(list, i);
        }
    }

    public Expression getArg2() {
        return this.m_arg2;
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void reportWrongNumberArgs() throws WrongNumberArgsException {
        throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("three", null));
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void setArg(Expression expression, int i) throws WrongNumberArgsException {
        if (i < 2) {
            super.setArg(expression, i);
        } else if (2 != i) {
            reportWrongNumberArgs();
        } else {
            this.m_arg2 = expression;
            expression.exprSetParent(this);
        }
    }
}
