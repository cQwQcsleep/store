package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Div extends Operation {
    static final long serialVersionUID = 6220756595959798135L;

    @Override // com.sun.org.apache.xpath.internal.Expression
    public double num(XPathContext xPathContext) throws TransformerException {
        return this.m_left.num(xPathContext) / this.m_right.num(xPathContext);
    }

    @Override // com.sun.org.apache.xpath.internal.operations.Operation
    public XObject operate(XObject xObject, XObject xObject2) throws TransformerException {
        return new XNumber(xObject.num() / xObject2.num());
    }
}
