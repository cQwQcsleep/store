package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xalan.internal.res.XSLMessages;
import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest;
import com.sun.org.apache.xpath.internal.axes.SubContextList;
import com.sun.org.apache.xpath.internal.objects.XNodeSet;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.patterns.StepPattern;
import java.util.List;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncCurrent extends Function {
    static final long serialVersionUID = 5715316804877715008L;

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        int contextNode;
        SubContextList currentNodeList = xPathContext.getCurrentNodeList();
        if (currentNodeList == null) {
            contextNode = xPathContext.getContextNode();
        } else if (currentNodeList instanceof PredicatedNodeTest) {
            contextNode = ((PredicatedNodeTest) currentNodeList).getLocPathIterator().getCurrentContextNode();
        } else {
            if (currentNodeList instanceof StepPattern) {
                f63.a(XSLMessages.createMessage("ER_PROCESSOR_ERROR", null));
                return null;
            }
            contextNode = -1;
        }
        return new XNodeSet(contextNode, xPathContext.getDTMManager());
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
    }
}
