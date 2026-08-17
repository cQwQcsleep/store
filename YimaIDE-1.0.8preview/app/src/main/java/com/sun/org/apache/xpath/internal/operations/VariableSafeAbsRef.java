package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XNodeSet;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class VariableSafeAbsRef extends Variable {
    static final long serialVersionUID = -9174661990819967452L;

    @Override // com.sun.org.apache.xpath.internal.operations.Variable, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext, boolean z) throws TransformerException {
        XNodeSet xNodeSet = (XNodeSet) super.execute(xPathContext, z);
        DTMManager dTMManager = xPathContext.getDTMManager();
        int contextNode = xPathContext.getContextNode();
        return dTMManager.getDTM(xNodeSet.getRoot()).getDocument() != dTMManager.getDTM(contextNode).getDocument() ? (XNodeSet) ((Expression) xNodeSet.getContainedIter()).asIterator(xPathContext, contextNode) : xNodeSet;
    }
}
