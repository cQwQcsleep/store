package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncQname extends FunctionDef1Arg {
    static final long serialVersionUID = -1532307875532617380L;

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        String nodeNameX;
        int arg0AsNode = getArg0AsNode(xPathContext);
        if (-1 != arg0AsNode && (nodeNameX = xPathContext.getDTM(arg0AsNode).getNodeNameX(arg0AsNode)) != null) {
            return new XString(nodeNameX);
        }
        return XString.EMPTYSTRING;
    }
}
