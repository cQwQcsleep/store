package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xpath.internal.XPath;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncRound extends FunctionOneArg {
    static final long serialVersionUID = -7970583902573826611L;

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        double dNum = this.m_arg0.execute(xPathContext).num();
        if (dNum < -0.5d || dNum >= XPath.MATCH_SCORE_QNAME) {
            return dNum == XPath.MATCH_SCORE_QNAME ? new XNumber(dNum) : new XNumber(Math.floor(dNum + 0.5d));
        }
        return new XNumber(-0.0d);
    }
}
