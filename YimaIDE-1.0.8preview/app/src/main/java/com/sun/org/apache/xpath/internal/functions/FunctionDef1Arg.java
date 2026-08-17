package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.XPath;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XString;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FunctionDef1Arg extends FunctionOneArg {
    static final long serialVersionUID = 2325189412814149264L;

    public boolean Arg0IsNodesetExpr() {
        Expression expression = this.m_arg0;
        if (expression == null) {
            return true;
        }
        return expression.isNodesetExpr();
    }

    @Override // com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.Expression
    public boolean canTraverseOutsideSubtree() {
        if (this.m_arg0 == null) {
            return false;
        }
        return super.canTraverseOutsideSubtree();
    }

    @Override // com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void checkNumberArgs(int i) throws WrongNumberArgsException {
        if (i > 1) {
            reportWrongNumberArgs();
        }
    }

    public int getArg0AsNode(XPathContext xPathContext) throws TransformerException {
        Expression expression = this.m_arg0;
        return expression == null ? xPathContext.getCurrentNode() : expression.asNode(xPathContext);
    }

    public double getArg0AsNumber(XPathContext xPathContext) throws TransformerException {
        Expression expression = this.m_arg0;
        if (expression != null) {
            return expression.execute(xPathContext).num();
        }
        int currentNode = xPathContext.getCurrentNode();
        return -1 == currentNode ? XPath.MATCH_SCORE_QNAME : xPathContext.getDTM(currentNode).getStringValue(currentNode).toDouble();
    }

    public XMLString getArg0AsString(XPathContext xPathContext) throws TransformerException {
        Expression expression = this.m_arg0;
        if (expression != null) {
            return expression.execute(xPathContext).xstr();
        }
        int currentNode = xPathContext.getCurrentNode();
        return -1 == currentNode ? XString.EMPTYSTRING : xPathContext.getDTM(currentNode).getStringValue(currentNode);
    }

    @Override // com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void reportWrongNumberArgs() throws WrongNumberArgsException {
        throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("ER_ZERO_OR_ONE", null));
    }
}
