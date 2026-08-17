package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;
import javax.xml.transform.TransformerException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncNormalizeSpace extends FunctionDef1Arg {
    static final long serialVersionUID = -3377956872032190880L;

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        return (XString) getArg0AsString(xPathContext).fixWhiteSpace(true, true, false);
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public void executeCharsToContentHandler(XPathContext xPathContext, ContentHandler contentHandler) throws TransformerException, SAXException {
        if (!Arg0IsNodesetExpr()) {
            execute(xPathContext).dispatchCharactersEvents(contentHandler);
            return;
        }
        int arg0AsNode = getArg0AsNode(xPathContext);
        if (-1 != arg0AsNode) {
            xPathContext.getDTM(arg0AsNode).dispatchCharactersEvents(arg0AsNode, contentHandler, true);
        }
    }
}
