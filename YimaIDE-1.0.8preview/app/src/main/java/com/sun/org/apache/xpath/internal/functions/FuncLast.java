package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.axes.SubContextList;
import com.sun.org.apache.xpath.internal.compiler.Compiler;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;
import java.util.List;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncLast extends Function {
    static final long serialVersionUID = 9205812403085432943L;
    private boolean m_isTopLevel;

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        return new XNumber(getCountOfContextNodeList(xPathContext));
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
    }

    public int getCountOfContextNodeList(XPathContext xPathContext) throws TransformerException {
        SubContextList subContextList = this.m_isTopLevel ? null : xPathContext.getSubContextList();
        if (subContextList != null) {
            return subContextList.getLastPos(xPathContext);
        }
        DTMIterator contextNodeList = xPathContext.getContextNodeList();
        if (contextNodeList != null) {
            return contextNodeList.getLength();
        }
        return 0;
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function
    public void postCompileStep(Compiler compiler) {
        this.m_isTopLevel = compiler.getLocationPathDepth() == -1;
    }
}
