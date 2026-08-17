package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.NodeSetDTM;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XNodeSet;
import com.sun.org.apache.xpath.internal.objects.XObject;
import java.util.List;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class FuncHere extends Function {
    private static final long serialVersionUID = 4328660760070034592L;

    private static Document getOwnerDocument(Node node) {
        return node.getNodeType() == 9 ? (Document) node : node.getOwnerDocument();
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0053  */
    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        Node node = (Node) xPathContext.getOwnerObject();
        if (node == null) {
            return null;
        }
        int dTMHandleFromNode = xPathContext.getDTMHandleFromNode(node);
        int currentNode = xPathContext.getCurrentNode();
        DTM dtm = xPathContext.getDTM(currentNode);
        if (dtm.getDocument() == -1) {
            error(xPathContext, "ER_CONTEXT_HAS_NO_OWNERDOC", null);
        }
        if (getOwnerDocument(dtm.getNode(currentNode)) != getOwnerDocument(node)) {
            throw new TransformerException("Owner documents differ");
        }
        XNodeSet xNodeSet = new XNodeSet(xPathContext.getDTMManager());
        NodeSetDTM nodeSetDTMMutableNodeset = xNodeSet.mutableNodeset();
        short nodeType = dtm.getNodeType(dTMHandleFromNode);
        if (nodeType == 2) {
            nodeSetDTMMutableNodeset.addNode(dTMHandleFromNode);
        } else if (nodeType == 3) {
            nodeSetDTMMutableNodeset.addNode(dtm.getParent(dTMHandleFromNode));
        } else if (nodeType == 7) {
            nodeSetDTMMutableNodeset.addNode(dTMHandleFromNode);
        }
        nodeSetDTMMutableNodeset.detach();
        return xNodeSet;
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
    }
}
