package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncDoclocation extends FunctionDef1Arg {
    static final long serialVersionUID = 7469213946343568769L;

    /* JADX WARN: Code duplicated, block: B:9:0x001e  */
    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        String documentBaseURI;
        int arg0AsNode = getArg0AsNode(xPathContext);
        if (-1 != arg0AsNode) {
            DTM dtm = xPathContext.getDTM(arg0AsNode);
            if (11 == dtm.getNodeType(arg0AsNode)) {
                arg0AsNode = dtm.getFirstChild(arg0AsNode);
            }
            if (-1 != arg0AsNode) {
                documentBaseURI = dtm.getDocumentBaseURI();
            } else {
                documentBaseURI = null;
            }
        } else {
            documentBaseURI = null;
        }
        if (documentBaseURI == null) {
            documentBaseURI = "";
        }
        return new XString(documentBaseURI);
    }
}
