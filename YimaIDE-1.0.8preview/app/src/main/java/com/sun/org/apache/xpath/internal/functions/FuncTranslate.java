package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncTranslate extends Function3Args {
    static final long serialVersionUID = -1672834340026116482L;

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        String str = this.m_arg0.execute(xPathContext).str();
        String str2 = this.m_arg1.execute(xPathContext).str();
        String str3 = this.m_arg2.execute(xPathContext).str();
        int length = str.length();
        int length2 = str3.length();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            int iIndexOf = str2.indexOf(cCharAt);
            if (iIndexOf < 0) {
                stringBuffer.append(cCharAt);
            } else if (iIndexOf < length2) {
                stringBuffer.append(str3.charAt(iIndexOf));
            }
        }
        return new XString(stringBuffer.toString());
    }
}
