package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.axes.SubContextList;
import com.sun.org.apache.xpath.internal.compiler.Compiler;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;
import java.util.List;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncPosition extends Function {
    static final long serialVersionUID = -9092846348197271582L;
    private boolean m_isTopLevel;

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        return new XNumber(getPositionInContextNodeList(xPathContext));
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
    }

    public int getPositionInContextNodeList(XPathContext xPathContext) {
        int iNextNode;
        SubContextList subContextList = this.m_isTopLevel ? null : xPathContext.getSubContextList();
        if (subContextList != null) {
            return subContextList.getProximityPosition(xPathContext);
        }
        DTMIterator contextNodeList = xPathContext.getContextNodeList();
        if (contextNodeList == null) {
            return -1;
        }
        if (contextNodeList.getCurrentNode() == -1) {
            if (contextNodeList.getCurrentPos() == 0) {
                return 0;
            }
            try {
                contextNodeList = contextNodeList.cloneWithReset();
                int contextNode = xPathContext.getContextNode();
                do {
                    iNextNode = contextNodeList.nextNode();
                    if (-1 == iNextNode) {
                        break;
                    }
                } while (iNextNode != contextNode);
            } catch (CloneNotSupportedException e) {
                throw new WrappedRuntimeException(e);
            }
        }
        return contextNodeList.getCurrentPos();
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function
    public void postCompileStep(Compiler compiler) {
        this.m_isTopLevel = compiler.getLocationPathDepth() == -1;
    }
}
