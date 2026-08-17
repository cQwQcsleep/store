package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncNamespace extends FunctionDef1Arg {
    static final long serialVersionUID = -4695674566722321237L;

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        String namespaceURI;
        int arg0AsNode = getArg0AsNode(xPathContext);
        if (arg0AsNode == -1) {
            return XString.EMPTYSTRING;
        }
        DTM dtm = xPathContext.getDTM(arg0AsNode);
        short nodeType = dtm.getNodeType(arg0AsNode);
        if (nodeType == 1) {
            namespaceURI = dtm.getNamespaceURI(arg0AsNode);
        } else {
            if (nodeType != 2) {
                return XString.EMPTYSTRING;
            }
            String nodeName = dtm.getNodeName(arg0AsNode);
            if (nodeName.startsWith("xmlns:") || nodeName.equals("xmlns")) {
                return XString.EMPTYSTRING;
            }
            namespaceURI = dtm.getNamespaceURI(arg0AsNode);
        }
        return namespaceURI == null ? XString.EMPTYSTRING : new XString(namespaceURI);
    }
}
