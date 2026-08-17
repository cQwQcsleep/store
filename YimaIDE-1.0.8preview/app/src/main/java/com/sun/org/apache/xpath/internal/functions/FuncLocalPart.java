package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncLocalPart extends FunctionDef1Arg {
    static final long serialVersionUID = 7591798770325814746L;

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        int arg0AsNode = getArg0AsNode(xPathContext);
        if (-1 == arg0AsNode) {
            return XString.EMPTYSTRING;
        }
        String localName = arg0AsNode != -1 ? xPathContext.getDTM(arg0AsNode).getLocalName(arg0AsNode) : "";
        return (localName.startsWith("#") || localName.equals("xmlns")) ? XString.EMPTYSTRING : new XString(localName);
    }
}
