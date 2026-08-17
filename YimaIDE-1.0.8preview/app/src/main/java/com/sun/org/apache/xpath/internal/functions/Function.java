package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.compiler.Compiler;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Function extends Expression {
    static final long serialVersionUID = 6927661240854599768L;

    public void callArgVisitors(XPathVisitor xPathVisitor) {
    }

    @Override // com.sun.org.apache.xpath.internal.XPathVisitable
    public void callVisitors(ExpressionOwner expressionOwner, XPathVisitor xPathVisitor) {
        if (xPathVisitor.visitFunction(expressionOwner, this)) {
            callArgVisitors(xPathVisitor);
        }
    }

    public void checkNumberArgs(int i) throws WrongNumberArgsException {
        if (i != 0) {
            reportWrongNumberArgs();
        }
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public boolean deepEquals(Expression expression) {
        return isSameClass(expression);
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        System.out.println("Error! Function.execute should not be called!");
        return null;
    }

    public void postCompileStep(Compiler compiler) {
    }

    public void reportWrongNumberArgs() throws WrongNumberArgsException {
        throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("zero", null));
    }

    public void setArg(Expression expression, int i) throws WrongNumberArgsException {
        reportWrongNumberArgs();
    }
}
