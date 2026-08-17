package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Bool extends UnaryOperation {
    static final long serialVersionUID = 44705375321914635L;

    @Override // com.sun.org.apache.xpath.internal.Expression
    public boolean bool(XPathContext xPathContext) throws TransformerException {
        return this.m_right.bool(xPathContext);
    }

    @Override // com.sun.org.apache.xpath.internal.operations.UnaryOperation
    public XObject operate(XObject xObject) throws TransformerException {
        if (1 == xObject.getType()) {
            return xObject;
        }
        return xObject.bool() ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
}
