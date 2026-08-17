package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Equals extends Operation {
    static final long serialVersionUID = -2658315633903426134L;

    @Override // com.sun.org.apache.xpath.internal.Expression
    public boolean bool(XPathContext xPathContext) throws TransformerException {
        XObject xObjectExecute = this.m_left.execute(xPathContext, true);
        XObject xObjectExecute2 = this.m_right.execute(xPathContext, true);
        boolean zEquals = xObjectExecute.equals(xObjectExecute2);
        xObjectExecute.detach();
        xObjectExecute2.detach();
        return zEquals;
    }

    @Override // com.sun.org.apache.xpath.internal.operations.Operation
    public XObject operate(XObject xObject, XObject xObject2) throws TransformerException {
        return xObject.equals(xObject2) ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
}
