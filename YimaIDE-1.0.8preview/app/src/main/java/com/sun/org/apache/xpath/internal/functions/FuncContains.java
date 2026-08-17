package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncContains extends Function2Args {
    static final long serialVersionUID = 5084753781887919723L;

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        String str = this.m_arg0.execute(xPathContext).str();
        String str2 = this.m_arg1.execute(xPathContext).str();
        if ((str.length() != 0 || str2.length() != 0) && str.indexOf(str2) <= -1) {
            return XBoolean.S_FALSE;
        }
        return XBoolean.S_TRUE;
    }
}
