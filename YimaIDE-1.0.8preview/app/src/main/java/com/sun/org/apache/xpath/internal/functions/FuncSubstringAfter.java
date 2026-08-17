package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncSubstringAfter extends Function2Args {
    static final long serialVersionUID = -8119731889862512194L;

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        XMLString xMLStringXstr = this.m_arg0.execute(xPathContext).xstr();
        XMLString xMLStringXstr2 = this.m_arg1.execute(xPathContext).xstr();
        int iIndexOf = xMLStringXstr.indexOf(xMLStringXstr2);
        return -1 == iIndexOf ? XString.EMPTYSTRING : (XString) xMLStringXstr.substring(iIndexOf + xMLStringXstr2.length());
    }
}
