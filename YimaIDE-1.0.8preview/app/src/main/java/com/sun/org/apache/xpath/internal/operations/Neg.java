package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Neg extends UnaryOperation {
    static final long serialVersionUID = -6280607702375702291L;

    @Override // com.sun.org.apache.xpath.internal.Expression
    public double num(XPathContext xPathContext) throws TransformerException {
        return -this.m_right.num(xPathContext);
    }

    @Override // com.sun.org.apache.xpath.internal.operations.UnaryOperation
    public XObject operate(XObject xObject) throws TransformerException {
        return new XNumber(-xObject.num());
    }
}
