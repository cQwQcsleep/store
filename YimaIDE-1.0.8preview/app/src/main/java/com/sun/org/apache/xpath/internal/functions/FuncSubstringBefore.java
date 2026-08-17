package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncSubstringBefore extends Function2Args {
    static final long serialVersionUID = 4110547161672431775L;

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        String str = this.m_arg0.execute(xPathContext).str();
        int iIndexOf = str.indexOf(this.m_arg1.execute(xPathContext).str());
        return -1 == iIndexOf ? XString.EMPTYSTRING : new XString(str.substring(0, iIndexOf));
    }
}
