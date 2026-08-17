package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.functions.FuncLast;
import com.sun.org.apache.xpath.internal.functions.FuncPosition;
import com.sun.org.apache.xpath.internal.functions.Function;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.operations.Div;
import com.sun.org.apache.xpath.internal.operations.Minus;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.org.apache.xpath.internal.operations.Mult;
import com.sun.org.apache.xpath.internal.operations.Number;
import com.sun.org.apache.xpath.internal.operations.Plus;
import com.sun.org.apache.xpath.internal.operations.Quo;
import com.sun.org.apache.xpath.internal.operations.Variable;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class HasPositionalPredChecker extends XPathVisitor {
    private boolean m_hasPositionalPred = false;
    private int m_predDepth = 0;

    public static boolean check(LocPathIterator locPathIterator) {
        HasPositionalPredChecker hasPositionalPredChecker = new HasPositionalPredChecker();
        locPathIterator.callVisitors(null, hasPositionalPredChecker);
        return hasPositionalPredChecker.m_hasPositionalPred;
    }

    @Override // com.sun.org.apache.xpath.internal.XPathVisitor
    public boolean visitFunction(ExpressionOwner expressionOwner, Function function) {
        if ((function instanceof FuncPosition) || (function instanceof FuncLast)) {
            this.m_hasPositionalPred = true;
        }
        return true;
    }

    @Override // com.sun.org.apache.xpath.internal.XPathVisitor
    public boolean visitPredicate(ExpressionOwner expressionOwner, Expression expression) {
        int i = this.m_predDepth + 1;
        this.m_predDepth = i;
        if (i == 1) {
            if ((expression instanceof Variable) || (expression instanceof XNumber) || (expression instanceof Div) || (expression instanceof Plus) || (expression instanceof Minus) || (expression instanceof Mod) || (expression instanceof Quo) || (expression instanceof Mult) || (expression instanceof Number) || (expression instanceof Function)) {
                this.m_hasPositionalPred = true;
            } else {
                expression.callVisitors(expressionOwner, this);
            }
        }
        this.m_predDepth--;
        return false;
    }
}
