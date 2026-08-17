package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xpath.internal.XPath;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncSum extends FunctionOneArg {
    static final long serialVersionUID = -2719049259574677519L;

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        DTMIterator dTMIteratorAsIterator = this.m_arg0.asIterator(xPathContext, xPathContext.getCurrentNode());
        double d = XPath.MATCH_SCORE_QNAME;
        while (true) {
            int iNextNode = dTMIteratorAsIterator.nextNode();
            if (-1 == iNextNode) {
                dTMIteratorAsIterator.detach();
                return new XNumber(d);
            }
            XMLString stringValue = dTMIteratorAsIterator.getDTM(iNextNode).getStringValue(iNextNode);
            if (stringValue != null) {
                d += stringValue.toDouble();
            }
        }
    }
}
