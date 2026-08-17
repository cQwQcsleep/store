package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Or extends Operation {
    static final long serialVersionUID = -644107191353853079L;

    @Override // com.sun.org.apache.xpath.internal.Expression
    public boolean bool(XPathContext xPathContext) throws TransformerException {
        return this.m_left.bool(xPathContext) || this.m_right.bool(xPathContext);
    }

    @Override // com.sun.org.apache.xpath.internal.operations.Operation, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        if (!this.m_left.execute(xPathContext).bool() && !this.m_right.execute(xPathContext).bool()) {
            return XBoolean.S_FALSE;
        }
        return XBoolean.S_TRUE;
    }
}
