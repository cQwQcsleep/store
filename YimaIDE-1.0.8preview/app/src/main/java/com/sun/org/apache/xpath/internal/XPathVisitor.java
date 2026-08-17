package com.sun.org.apache.xpath.internal;

import com.sun.org.apache.xpath.internal.axes.LocPathIterator;
import com.sun.org.apache.xpath.internal.axes.UnionPathIterator;
import com.sun.org.apache.xpath.internal.functions.Function;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XString;
import com.sun.org.apache.xpath.internal.operations.Operation;
import com.sun.org.apache.xpath.internal.operations.UnaryOperation;
import com.sun.org.apache.xpath.internal.operations.Variable;
import com.sun.org.apache.xpath.internal.patterns.NodeTest;
import com.sun.org.apache.xpath.internal.patterns.StepPattern;
import com.sun.org.apache.xpath.internal.patterns.UnionPattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XPathVisitor {
    public boolean visitBinaryOperation(ExpressionOwner expressionOwner, Operation operation) {
        return true;
    }

    public boolean visitFunction(ExpressionOwner expressionOwner, Function function) {
        return true;
    }

    public boolean visitLocationPath(ExpressionOwner expressionOwner, LocPathIterator locPathIterator) {
        return true;
    }

    public boolean visitMatchPattern(ExpressionOwner expressionOwner, StepPattern stepPattern) {
        return true;
    }

    public boolean visitNumberLiteral(ExpressionOwner expressionOwner, XNumber xNumber) {
        return true;
    }

    public boolean visitPredicate(ExpressionOwner expressionOwner, Expression expression) {
        return true;
    }

    public boolean visitStep(ExpressionOwner expressionOwner, NodeTest nodeTest) {
        return true;
    }

    public boolean visitStringLiteral(ExpressionOwner expressionOwner, XString xString) {
        return true;
    }

    public boolean visitUnaryOperation(ExpressionOwner expressionOwner, UnaryOperation unaryOperation) {
        return true;
    }

    public boolean visitUnionPath(ExpressionOwner expressionOwner, UnionPathIterator unionPathIterator) {
        return true;
    }

    public boolean visitUnionPattern(ExpressionOwner expressionOwner, UnionPattern unionPattern) {
        return true;
    }

    public boolean visitVariableRef(ExpressionOwner expressionOwner, Variable variable) {
        return true;
    }
}
