package com.sun.org.apache.xpath.internal;

import com.sun.org.apache.xml.internal.utils.DefaultErrorHandler;
import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xml.internal.utils.SAXSourceLocator;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import com.sun.org.apache.xpath.internal.compiler.Compiler;
import com.sun.org.apache.xpath.internal.compiler.FunctionTable;
import com.sun.org.apache.xpath.internal.compiler.XPathParser;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import java.io.Serializable;
import java.util.List;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;
import jdk.xml.internal.XMLSecurityManager;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XPath implements Serializable, ExpressionOwner {
    private static final boolean DEBUG_MATCHES = false;
    public static final int MATCH = 1;
    public static final double MATCH_SCORE_NODETEST = -0.5d;
    public static final double MATCH_SCORE_NONE = Double.NEGATIVE_INFINITY;
    public static final double MATCH_SCORE_NSWILD = -0.25d;
    public static final double MATCH_SCORE_OTHER = 0.5d;
    public static final double MATCH_SCORE_QNAME = 0.0d;
    public static final int SELECT = 0;
    static final long serialVersionUID = 3976493477939110553L;
    private transient FunctionTable m_funcTable;
    private Expression m_mainExp;
    String m_patternString;

    public XPath(String str, SourceLocator sourceLocator, PrefixResolver prefixResolver, int i, ErrorListener errorListener, FunctionTable functionTable, XMLSecurityManager xMLSecurityManager) throws TransformerException {
        this.m_funcTable = null;
        if (functionTable == null) {
            initFunctionTable();
        } else {
            this.m_funcTable = functionTable;
        }
        errorListener = errorListener == null ? new DefaultErrorHandler() : errorListener;
        this.m_patternString = str;
        XPathParser xPathParser = new XPathParser(errorListener, sourceLocator, xMLSecurityManager);
        Compiler compiler = new Compiler(errorListener, sourceLocator, this.m_funcTable);
        if (i == 0) {
            xPathParser.initXPath(compiler, str, prefixResolver);
        } else {
            if (1 != i) {
                f63.a(XPATHMessages.createXPATHMessage("ER_CANNOT_DEAL_XPATH_TYPE", new Object[]{Integer.toString(i)}));
                throw null;
            }
            xPathParser.initMatchPattern(compiler, str, prefixResolver);
        }
        Expression expressionCompileExpression = compiler.compileExpression(0);
        setExpression(expressionCompileExpression);
        if (sourceLocator == null || !(sourceLocator instanceof ExpressionNode)) {
            return;
        }
        expressionCompileExpression.exprSetParent((ExpressionNode) sourceLocator);
    }

    private void initFunctionTable() {
        this.m_funcTable = new FunctionTable();
    }

    public void assertion(boolean z, String str) {
        if (z) {
            return;
        }
        f63.a(XPATHMessages.createXPATHMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[]{str}));
    }

    public boolean bool(XPathContext xPathContext, int i, PrefixResolver prefixResolver) throws TransformerException {
        xPathContext.pushNamespaceContext(prefixResolver);
        xPathContext.pushCurrentNodeAndExpression(i, i);
        try {
            try {
                try {
                    boolean zBool = this.m_mainExp.bool(xPathContext);
                    xPathContext.popNamespaceContext();
                    xPathContext.popCurrentNodeAndExpression();
                    return zBool;
                } catch (TransformerException e) {
                    e.setLocator(getLocator());
                    ErrorListener errorListener = xPathContext.getErrorListener();
                    if (errorListener == null) {
                        throw e;
                    }
                    errorListener.error(e);
                    xPathContext.popNamespaceContext();
                    xPathContext.popCurrentNodeAndExpression();
                    return false;
                }
            } catch (Exception e2) {
                e = e2;
                while (e instanceof WrappedRuntimeException) {
                    e = ((WrappedRuntimeException) e).getException();
                }
                String message = e.getMessage();
                if (message == null || message.length() == 0) {
                    message = XPATHMessages.createXPATHMessage("ER_XPATH_ERROR", null);
                }
                TransformerException transformerException = new TransformerException(message, getLocator(), e);
                ErrorListener errorListener2 = xPathContext.getErrorListener();
                if (errorListener2 == null) {
                    throw transformerException;
                }
                errorListener2.fatalError(transformerException);
                xPathContext.popNamespaceContext();
                xPathContext.popCurrentNodeAndExpression();
                return false;
            }
        } catch (Throwable th) {
            xPathContext.popNamespaceContext();
            xPathContext.popCurrentNodeAndExpression();
            throw th;
        }
    }

    public void callVisitors(ExpressionOwner expressionOwner, XPathVisitor xPathVisitor) {
        this.m_mainExp.callVisitors(this, xPathVisitor);
    }

    public void error(XPathContext xPathContext, int i, String str, Object[] objArr) throws TransformerException {
        String strCreateXPATHMessage = XPATHMessages.createXPATHMessage(str, objArr);
        ErrorListener errorListener = xPathContext.getErrorListener();
        if (errorListener != null) {
            errorListener.fatalError(new TransformerException(strCreateXPATHMessage, (SAXSourceLocator) xPathContext.getSAXLocator()));
            return;
        }
        SourceLocator sAXLocator = xPathContext.getSAXLocator();
        System.out.println(strCreateXPATHMessage + "; file " + sAXLocator.getSystemId() + "; line " + sAXLocator.getLineNumber() + "; column " + sAXLocator.getColumnNumber());
    }

    public XObject execute(XPathContext xPathContext, int i, PrefixResolver prefixResolver) throws TransformerException {
        xPathContext.pushNamespaceContext(prefixResolver);
        xPathContext.pushCurrentNodeAndExpression(i, i);
        try {
            try {
                try {
                    XObject xObjectExecute = this.m_mainExp.execute(xPathContext);
                    xPathContext.popNamespaceContext();
                    xPathContext.popCurrentNodeAndExpression();
                    return xObjectExecute;
                } catch (Exception e) {
                    e = e;
                    while (e instanceof WrappedRuntimeException) {
                        e = ((WrappedRuntimeException) e).getException();
                    }
                    String message = e.getMessage();
                    if (message == null || message.length() == 0) {
                        message = XPATHMessages.createXPATHMessage("ER_XPATH_ERROR", null);
                    }
                    TransformerException transformerException = new TransformerException(message, getLocator(), e);
                    ErrorListener errorListener = xPathContext.getErrorListener();
                    if (errorListener == null) {
                        throw transformerException;
                    }
                    errorListener.fatalError(transformerException);
                    xPathContext.popNamespaceContext();
                    xPathContext.popCurrentNodeAndExpression();
                    return null;
                }
            } catch (TransformerException e2) {
                e2.setLocator(getLocator());
                ErrorListener errorListener2 = xPathContext.getErrorListener();
                if (errorListener2 == null) {
                    throw e2;
                }
                errorListener2.error(e2);
                xPathContext.popNamespaceContext();
                xPathContext.popCurrentNodeAndExpression();
                return null;
            }
        } catch (Throwable th) {
            xPathContext.popNamespaceContext();
            xPathContext.popCurrentNodeAndExpression();
            throw th;
        }
    }

    public void fixupVariables(List<QName> list, int i) {
        this.m_mainExp.fixupVariables(list, i);
    }

    @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
    public Expression getExpression() {
        return this.m_mainExp;
    }

    public SourceLocator getLocator() {
        return this.m_mainExp;
    }

    public double getMatchScore(XPathContext xPathContext, int i) throws TransformerException {
        xPathContext.pushCurrentNode(i);
        xPathContext.pushCurrentExpressionNode(i);
        try {
            return this.m_mainExp.execute(xPathContext).num();
        } finally {
            xPathContext.popCurrentNode();
            xPathContext.popCurrentExpressionNode();
        }
    }

    public String getPatternString() {
        return this.m_patternString;
    }

    @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
    public void setExpression(Expression expression) {
        Expression expression2 = this.m_mainExp;
        if (expression2 != null) {
            expression.exprSetParent(expression2.exprGetParent());
        }
        this.m_mainExp = expression;
    }

    public void warn(XPathContext xPathContext, int i, String str, Object[] objArr) throws TransformerException {
        String strCreateXPATHWarning = XPATHMessages.createXPATHWarning(str, objArr);
        ErrorListener errorListener = xPathContext.getErrorListener();
        if (errorListener != null) {
            errorListener.warning(new TransformerException(strCreateXPATHWarning, (SAXSourceLocator) xPathContext.getSAXLocator()));
        }
    }

    public XPath(String str, SourceLocator sourceLocator, PrefixResolver prefixResolver, int i, ErrorListener errorListener) throws TransformerException {
        this(str, sourceLocator, prefixResolver, i, errorListener, null);
    }

    public XPath(String str, SourceLocator sourceLocator, PrefixResolver prefixResolver, int i) throws TransformerException {
        this(str, sourceLocator, prefixResolver, i, null);
    }

    public XPath(String str, SourceLocator sourceLocator, PrefixResolver prefixResolver, int i, ErrorListener errorListener, FunctionTable functionTable) throws TransformerException {
        this(str, sourceLocator, prefixResolver, i, errorListener, functionTable, null);
    }

    public XPath(Expression expression) {
        this.m_funcTable = null;
        setExpression(expression);
        initFunctionTable();
    }

    public XObject execute(XPathContext xPathContext, Node node, PrefixResolver prefixResolver) throws TransformerException {
        return execute(xPathContext, xPathContext.getDTMHandleFromNode(node), prefixResolver);
    }
}
