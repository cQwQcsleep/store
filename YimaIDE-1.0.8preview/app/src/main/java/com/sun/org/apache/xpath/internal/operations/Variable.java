package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.axes.PathComponent;
import com.sun.org.apache.xpath.internal.objects.XNodeSet;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import java.util.List;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Variable extends Expression implements PathComponent {
    static final java.lang.String PSUEDOVARNAMESPACE = "http://xml.apache.org/xalan/psuedovar";
    static final long serialVersionUID = -4334975375609297049L;
    protected int m_index;
    protected QName m_qname;
    private boolean m_fixUpWasCalled = false;
    protected boolean m_isGlobal = false;

    @Override // com.sun.org.apache.xpath.internal.XPathVisitable
    public void callVisitors(ExpressionOwner expressionOwner, XPathVisitor xPathVisitor) {
        xPathVisitor.visitVariableRef(expressionOwner, this);
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public boolean deepEquals(Expression expression) {
        return isSameClass(expression) && this.m_qname.equals(((Variable) expression).m_qname);
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext, boolean z) throws TransformerException {
        XObject variableOrParam;
        xPathContext.getNamespaceContext();
        if (this.m_fixUpWasCalled) {
            variableOrParam = this.m_isGlobal ? xPathContext.getVarStack().getGlobalVariable(xPathContext, this.m_index, z) : xPathContext.getVarStack().getLocalVariable(xPathContext, this.m_index, z);
        } else {
            variableOrParam = xPathContext.getVarStack().getVariableOrParam(xPathContext, this.m_qname);
        }
        if (variableOrParam != null) {
            return variableOrParam;
        }
        warn(xPathContext, "WG_ILLEGAL_VARIABLE_REFERENCE", new Object[]{this.m_qname.getLocalPart()});
        return new XNodeSet(xPathContext.getDTMManager());
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
        this.m_fixUpWasCalled = true;
        list.size();
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).equals(this.m_qname)) {
                if (size >= i) {
                    this.m_index = size - i;
                    return;
                } else {
                    this.m_isGlobal = true;
                    this.m_index = size;
                    return;
                }
            }
        }
        throw new WrappedRuntimeException(new TransformerException(XPATHMessages.createXPATHMessage("ER_COULD_NOT_FIND_VAR", new Object[]{this.m_qname.toString()}), this));
    }

    @Override // com.sun.org.apache.xpath.internal.axes.PathComponent
    public int getAnalysisBits() {
        return 67108864;
    }

    public boolean getGlobal() {
        return this.m_isGlobal;
    }

    public int getIndex() {
        return this.m_index;
    }

    public QName getQName() {
        return this.m_qname;
    }

    public boolean isPsuedoVarRef() {
        java.lang.String namespaceURI = this.m_qname.getNamespaceURI();
        return namespaceURI != null && namespaceURI.equals(PSUEDOVARNAMESPACE) && this.m_qname.getLocalName().startsWith("#");
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public boolean isStableNumber() {
        return true;
    }

    public void setIndex(int i) {
        this.m_index = i;
    }

    public void setIsGlobal(boolean z) {
        this.m_isGlobal = z;
    }

    public void setQName(QName qName) {
        this.m_qname = qName;
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        return execute(xPathContext, false);
    }
}
