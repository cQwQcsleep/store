package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FunctionMultiArgs extends Function3Args {
    static final long serialVersionUID = 7117257746138417181L;
    Expression[] m_args;

    public class ArgMultiOwner implements ExpressionOwner {
        int m_argIndex;

        public ArgMultiOwner(int i) {
            this.m_argIndex = i;
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public Expression getExpression() {
            return FunctionMultiArgs.this.m_args[this.m_argIndex];
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public void setExpression(Expression expression) {
            expression.exprSetParent(FunctionMultiArgs.this);
            FunctionMultiArgs.this.m_args[this.m_argIndex] = expression;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function3Args, com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void callArgVisitors(XPathVisitor xPathVisitor) {
        super.callArgVisitors(xPathVisitor);
        Expression[] expressionArr = this.m_args;
        if (expressionArr != null) {
            int length = expressionArr.length;
            for (int i = 0; i < length; i++) {
                this.m_args[i].callVisitors(new ArgMultiOwner(i), xPathVisitor);
            }
        }
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function3Args, com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.Expression
    public boolean canTraverseOutsideSubtree() {
        if (super.canTraverseOutsideSubtree()) {
            return true;
        }
        int length = this.m_args.length;
        for (int i = 0; i < length; i++) {
            if (this.m_args[i].canTraverseOutsideSubtree()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function3Args, com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void checkNumberArgs(int i) throws WrongNumberArgsException {
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function3Args, com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public boolean deepEquals(Expression expression) {
        if (!super.deepEquals(expression)) {
            return false;
        }
        FunctionMultiArgs functionMultiArgs = (FunctionMultiArgs) expression;
        Expression[] expressionArr = this.m_args;
        if (expressionArr == null) {
            return functionMultiArgs.m_args == null;
        }
        int length = expressionArr.length;
        if (functionMultiArgs == null || functionMultiArgs.m_args.length != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!this.m_args[i].deepEquals(functionMultiArgs.m_args[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function3Args, com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
        super.fixupVariables(list, i);
        if (this.m_args == null) {
            return;
        }
        int i2 = 0;
        while (true) {
            Expression[] expressionArr = this.m_args;
            if (i2 >= expressionArr.length) {
                return;
            }
            expressionArr[i2].fixupVariables(list, i);
            i2++;
        }
    }

    public Expression[] getArgs() {
        return this.m_args;
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function3Args, com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void reportWrongNumberArgs() throws WrongNumberArgsException {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[]{"Programmer's assertion:  the method FunctionMultiArgs.reportWrongNumberArgs() should never be called."}));
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function3Args, com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void setArg(Expression expression, int i) throws WrongNumberArgsException {
        if (i < 3) {
            super.setArg(expression, i);
            return;
        }
        Expression[] expressionArr = this.m_args;
        if (expressionArr == null) {
            this.m_args = new Expression[]{expression};
        } else {
            Expression[] expressionArr2 = new Expression[expressionArr.length + 1];
            System.arraycopy(expressionArr, 0, expressionArr2, 0, expressionArr.length);
            expressionArr2[this.m_args.length] = expression;
            this.m_args = expressionArr2;
        }
        expression.exprSetParent(this);
    }
}
