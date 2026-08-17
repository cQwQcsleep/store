package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.utils.StringVector;
import com.sun.org.apache.xpath.internal.NodeSetDTM;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XNodeSet;
import com.sun.org.apache.xpath.internal.objects.XObject;
import java.util.StringTokenizer;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncId extends FunctionOneArg {
    static final long serialVersionUID = 8930573966143567310L;

    private StringVector getNodesByID(XPathContext xPathContext, int i, String str, StringVector stringVector, NodeSetDTM nodeSetDTM, boolean z) {
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str);
            boolean zHasMoreTokens = stringTokenizer.hasMoreTokens();
            DTM dtm = xPathContext.getDTM(i);
            while (zHasMoreTokens) {
                String strNextToken = stringTokenizer.nextToken();
                boolean zHasMoreTokens2 = stringTokenizer.hasMoreTokens();
                if (stringVector == null || !stringVector.contains(strNextToken)) {
                    int elementById = dtm.getElementById(strNextToken);
                    if (-1 != elementById) {
                        nodeSetDTM.addNodeInDocOrder(elementById, xPathContext);
                    }
                    if (strNextToken != null && (zHasMoreTokens2 || z)) {
                        if (stringVector == null) {
                            stringVector = new StringVector();
                        }
                        stringVector.addElement(strNextToken);
                    }
                }
                zHasMoreTokens = zHasMoreTokens2;
            }
        }
        return stringVector;
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        int document = xPathContext.getDTM(xPathContext.getCurrentNode()).getDocument();
        if (-1 == document) {
            error(xPathContext, "ER_CONTEXT_HAS_NO_OWNERDOC", null);
        }
        XObject xObjectExecute = this.m_arg0.execute(xPathContext);
        int type = xObjectExecute.getType();
        XNodeSet xNodeSet = new XNodeSet(xPathContext.getDTMManager());
        NodeSetDTM nodeSetDTMMutableNodeset = xNodeSet.mutableNodeset();
        if (4 == type) {
            DTMIterator dTMIteratorIter = xObjectExecute.iter();
            int iNextNode = dTMIteratorIter.nextNode();
            StringVector nodesByID = null;
            while (-1 != iNextNode) {
                String string = dTMIteratorIter.getDTM(iNextNode).getStringValue(iNextNode).toString();
                int iNextNode2 = dTMIteratorIter.nextNode();
                XPathContext xPathContext2 = xPathContext;
                FuncId funcId = this;
                nodesByID = funcId.getNodesByID(xPathContext2, document, string, nodesByID, nodeSetDTMMutableNodeset, -1 != iNextNode2);
                this = funcId;
                xPathContext = xPathContext2;
                iNextNode = iNextNode2;
            }
        } else if (-1 != type) {
            getNodesByID(xPathContext, document, xObjectExecute.str(), null, nodeSetDTMMutableNodeset, false);
            return xNodeSet;
        }
        return xNodeSet;
    }
}
