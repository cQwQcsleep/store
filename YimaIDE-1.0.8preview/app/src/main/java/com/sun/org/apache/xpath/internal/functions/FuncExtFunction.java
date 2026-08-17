package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionNode;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.ExtensionsProvider;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.objects.XNull;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncExtFunction extends Function {
    static final long serialVersionUID = 5196115554693708718L;
    List<Expression> m_argVec = new ArrayList();
    String m_extensionName;
    Object m_methodKey;
    String m_namespace;

    public class ArgExtOwner implements ExpressionOwner {
        Expression m_exp;

        public ArgExtOwner(Expression expression) {
            this.m_exp = expression;
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public Expression getExpression() {
            return this.m_exp;
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public void setExpression(Expression expression) {
            expression.exprSetParent(FuncExtFunction.this);
            this.m_exp = expression;
        }
    }

    public FuncExtFunction(String str, String str2, Object obj) {
        this.m_namespace = str;
        this.m_extensionName = str2;
        this.m_methodKey = obj;
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function
    public void callArgVisitors(XPathVisitor xPathVisitor) {
        for (int i = 0; i < this.m_argVec.size(); i++) {
            Expression expression = this.m_argVec.get(i);
            expression.callVisitors(new ArgExtOwner(expression), xPathVisitor);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function
    public void checkNumberArgs(int i) throws WrongNumberArgsException {
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        if (xPathContext.isSecureProcessing()) {
            throw new TransformerException(XPATHMessages.createXPATHMessage("ER_EXTENSION_FUNCTION_CANNOT_BE_INVOKED", new Object[]{toString()}));
        }
        ArrayList arrayList = new ArrayList();
        int size = this.m_argVec.size();
        for (int i = 0; i < size; i++) {
            XObject xObjectExecute = this.m_argVec.get(i).execute(xPathContext);
            xObjectExecute.allowDetachToRelease(false);
            arrayList.add(xObjectExecute);
        }
        Object objExtFunction = ((ExtensionsProvider) xPathContext.getOwnerObject()).extFunction(this, arrayList);
        return objExtFunction != null ? XObject.create(objExtFunction, xPathContext) : new XNull();
    }

    @Override // com.sun.org.apache.xpath.internal.Expression, com.sun.org.apache.xpath.internal.ExpressionNode
    public void exprSetParent(ExpressionNode expressionNode) {
        super.exprSetParent(expressionNode);
        int size = this.m_argVec.size();
        for (int i = 0; i < size; i++) {
            this.m_argVec.get(i).exprSetParent(expressionNode);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
        List<Expression> list2 = this.m_argVec;
        if (list2 != null) {
            int size = list2.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.m_argVec.get(i2).fixupVariables(list, i);
            }
        }
    }

    public Expression getArg(int i) {
        if (i < 0 || i >= this.m_argVec.size()) {
            return null;
        }
        return this.m_argVec.get(i);
    }

    public int getArgCount() {
        return this.m_argVec.size();
    }

    public String getFunctionName() {
        return this.m_extensionName;
    }

    public Object getMethodKey() {
        return this.m_methodKey;
    }

    public String getNamespace() {
        return this.m_namespace;
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function
    public void reportWrongNumberArgs() throws WrongNumberArgsException {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[]{"Programmer's assertion:  the method FunctionMultiArgs.reportWrongNumberArgs() should never be called."}));
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function
    public void setArg(Expression expression, int i) throws WrongNumberArgsException {
        this.m_argVec.add(expression);
        expression.exprSetParent(this);
    }

    public String toString() {
        String str = this.m_namespace;
        if (str == null || str.length() <= 0) {
            return this.m_extensionName;
        }
        return "{" + this.m_namespace + "}" + this.m_extensionName;
    }
}
