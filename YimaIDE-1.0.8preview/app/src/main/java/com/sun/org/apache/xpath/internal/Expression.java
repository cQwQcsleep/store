package com.sun.org.apache.xpath.internal;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xpath.internal.objects.XNodeSet;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import java.io.Serializable;
import java.util.List;
import javax.xml.transform.TransformerException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Expression implements Serializable, ExpressionNode, XPathVisitable {
    static final long serialVersionUID = 565665869777906902L;
    private ExpressionNode m_parent;

    public DTMIterator asIterator(XPathContext xPathContext, int i) throws TransformerException {
        try {
            xPathContext.pushCurrentNodeAndExpression(i, i);
            return execute(xPathContext).iter();
        } finally {
            xPathContext.popCurrentNodeAndExpression();
        }
    }

    public DTMIterator asIteratorRaw(XPathContext xPathContext, int i) throws TransformerException {
        try {
            xPathContext.pushCurrentNodeAndExpression(i, i);
            return ((XNodeSet) execute(xPathContext)).iterRaw();
        } finally {
            xPathContext.popCurrentNodeAndExpression();
        }
    }

    public int asNode(XPathContext xPathContext) throws TransformerException {
        return execute(xPathContext).iter().nextNode();
    }

    public void assertion(boolean z, String str) {
        if (z) {
            return;
        }
        f63.a(XPATHMessages.createXPATHMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[]{str}));
    }

    public boolean bool(XPathContext xPathContext) throws TransformerException {
        return execute(xPathContext).bool();
    }

    public boolean canTraverseOutsideSubtree() {
        return false;
    }

    public abstract boolean deepEquals(Expression expression);

    public void error(XPathContext xPathContext, String str, Object[] objArr) throws TransformerException {
        String strCreateXPATHMessage = XPATHMessages.createXPATHMessage(str, objArr);
        if (xPathContext != null) {
            xPathContext.getErrorListener().fatalError(new TransformerException(strCreateXPATHMessage, this));
        }
    }

    public abstract XObject execute(XPathContext xPathContext) throws TransformerException;

    public XObject execute(XPathContext xPathContext, int i) throws TransformerException {
        return execute(xPathContext);
    }

    public void executeCharsToContentHandler(XPathContext xPathContext, ContentHandler contentHandler) throws TransformerException, SAXException {
        XObject xObjectExecute = execute(xPathContext);
        xObjectExecute.dispatchCharactersEvents(contentHandler);
        xObjectExecute.detach();
    }

    @Override // com.sun.org.apache.xpath.internal.ExpressionNode
    public void exprAddChild(ExpressionNode expressionNode, int i) {
        assertion(false, "exprAddChild method not implemented!");
    }

    @Override // com.sun.org.apache.xpath.internal.ExpressionNode
    public ExpressionNode exprGetChild(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xpath.internal.ExpressionNode
    public int exprGetNumChildren() {
        return 0;
    }

    @Override // com.sun.org.apache.xpath.internal.ExpressionNode
    public ExpressionNode exprGetParent() {
        return this.m_parent;
    }

    @Override // com.sun.org.apache.xpath.internal.ExpressionNode
    public void exprSetParent(ExpressionNode expressionNode) {
        assertion(expressionNode != this, "Can not parent an expression to itself!");
        this.m_parent = expressionNode;
    }

    public abstract void fixupVariables(List<QName> list, int i);

    @Override // javax.xml.transform.SourceLocator
    public int getColumnNumber() {
        ExpressionNode expressionNode = this.m_parent;
        if (expressionNode == null) {
            return 0;
        }
        return expressionNode.getColumnNumber();
    }

    public ExpressionNode getExpressionOwner() {
        ExpressionNode expressionNodeExprGetParent = exprGetParent();
        while (expressionNodeExprGetParent != null && (expressionNodeExprGetParent instanceof Expression)) {
            expressionNodeExprGetParent = expressionNodeExprGetParent.exprGetParent();
        }
        return expressionNodeExprGetParent;
    }

    @Override // javax.xml.transform.SourceLocator
    public int getLineNumber() {
        ExpressionNode expressionNode = this.m_parent;
        if (expressionNode == null) {
            return 0;
        }
        return expressionNode.getLineNumber();
    }

    @Override // javax.xml.transform.SourceLocator
    public String getPublicId() {
        ExpressionNode expressionNode = this.m_parent;
        if (expressionNode == null) {
            return null;
        }
        return expressionNode.getPublicId();
    }

    @Override // javax.xml.transform.SourceLocator
    public String getSystemId() {
        ExpressionNode expressionNode = this.m_parent;
        if (expressionNode == null) {
            return null;
        }
        return expressionNode.getSystemId();
    }

    public boolean isNodesetExpr() {
        return false;
    }

    public final boolean isSameClass(Expression expression) {
        return expression != null && getClass() == expression.getClass();
    }

    public boolean isStableNumber() {
        return false;
    }

    public double num(XPathContext xPathContext) throws TransformerException {
        return execute(xPathContext).num();
    }

    public void warn(XPathContext xPathContext, String str, Object[] objArr) throws TransformerException {
        String strCreateXPATHWarning = XPATHMessages.createXPATHWarning(str, objArr);
        if (xPathContext != null) {
            xPathContext.getErrorListener().warning(new TransformerException(strCreateXPATHWarning, xPathContext.getSAXLocator()));
        }
    }

    public XMLString xstr(XPathContext xPathContext) throws TransformerException {
        return execute(xPathContext).xstr();
    }

    public XObject execute(XPathContext xPathContext, int i, DTM dtm, int i2) throws TransformerException {
        return execute(xPathContext);
    }

    public XObject execute(XPathContext xPathContext, boolean z) throws TransformerException {
        return execute(xPathContext);
    }
}
