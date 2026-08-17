package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncCount extends FunctionOneArg {
    static final long serialVersionUID = -7116225100474153751L;

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        DTMIterator dTMIteratorAsIterator = this.m_arg0.asIterator(xPathContext, xPathContext.getCurrentNode());
        int length = dTMIteratorAsIterator.getLength();
        dTMIteratorAsIterator.detach();
        return new XNumber(length);
    }
}
