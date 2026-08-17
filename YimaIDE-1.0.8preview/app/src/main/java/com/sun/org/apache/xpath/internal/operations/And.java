package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class And extends Operation {
    static final long serialVersionUID = 392330077126534022L;

    @Override // com.sun.org.apache.xpath.internal.Expression
    public boolean bool(XPathContext xPathContext) throws TransformerException {
        return this.m_left.bool(xPathContext) && this.m_right.bool(xPathContext);
    }

    @Override // com.sun.org.apache.xpath.internal.operations.Operation, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        if (this.m_left.execute(xPathContext).bool() && this.m_right.execute(xPathContext).bool()) {
            return XBoolean.S_TRUE;
        }
        return XBoolean.S_FALSE;
    }
}
