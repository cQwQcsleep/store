package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncLang extends FunctionOneArg {
    static final long serialVersionUID = -7868705139354872185L;

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        int attributeNode;
        int length;
        String str = this.m_arg0.execute(xPathContext).str();
        int currentNode = xPathContext.getCurrentNode();
        DTM dtm = xPathContext.getDTM(currentNode);
        while (-1 != currentNode) {
            if (1 == dtm.getNodeType(currentNode) && -1 != (attributeNode = dtm.getAttributeNode(currentNode, "http://www.w3.org/XML/1998/namespace", "lang"))) {
                String nodeValue = dtm.getNodeValue(attributeNode);
                if (!nodeValue.toLowerCase().startsWith(str.toLowerCase()) || (nodeValue.length() != (length = str.length()) && nodeValue.charAt(length) != '-')) {
                    break;
                    break;
                }
                return XBoolean.S_TRUE;
            }
            currentNode = dtm.getParent(currentNode);
        }
        return XBoolean.S_FALSE;
    }
}
