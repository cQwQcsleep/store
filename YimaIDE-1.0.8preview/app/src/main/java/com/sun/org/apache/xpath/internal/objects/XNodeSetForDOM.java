package com.sun.org.apache.xpath.internal.objects;

import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xpath.internal.NodeSetDTM;
import com.sun.org.apache.xpath.internal.XPathContext;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XNodeSetForDOM extends XNodeSet {
    static final long serialVersionUID = -8396190713754624640L;
    Object m_origObj;

    public XNodeSetForDOM(Node node, DTMManager dTMManager) {
        this.m_dtmMgr = dTMManager;
        this.m_origObj = node;
        int dTMHandleFromNode = dTMManager.getDTMHandleFromNode(node);
        setObject(new NodeSetDTM(dTMManager));
        ((NodeSetDTM) this.m_obj).addNode(dTMHandleFromNode);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XNodeSet, com.sun.org.apache.xpath.internal.objects.XObject
    public NodeList nodelist() throws TransformerException {
        Object obj = this.m_origObj;
        return obj instanceof NodeList ? (NodeList) obj : super.nodelist();
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XNodeSet, com.sun.org.apache.xpath.internal.objects.XObject
    public NodeIterator nodeset() throws TransformerException {
        Object obj = this.m_origObj;
        return obj instanceof NodeIterator ? (NodeIterator) obj : super.nodeset();
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XNodeSet, com.sun.org.apache.xpath.internal.objects.XObject
    public Object object() {
        return this.m_origObj;
    }

    public XNodeSetForDOM(XNodeSet xNodeSet) {
        super(xNodeSet);
        if (xNodeSet instanceof XNodeSetForDOM) {
            this.m_origObj = ((XNodeSetForDOM) xNodeSet).m_origObj;
        }
    }

    public XNodeSetForDOM(NodeList nodeList, XPathContext xPathContext) {
        this.m_dtmMgr = xPathContext.getDTMManager();
        this.m_origObj = nodeList;
        NodeSetDTM nodeSetDTM = new NodeSetDTM(nodeList, xPathContext);
        this.m_last = nodeSetDTM.getLength();
        setObject(nodeSetDTM);
    }

    public XNodeSetForDOM(NodeIterator nodeIterator, XPathContext xPathContext) {
        this.m_dtmMgr = xPathContext.getDTMManager();
        this.m_origObj = nodeIterator;
        NodeSetDTM nodeSetDTM = new NodeSetDTM(nodeIterator, xPathContext);
        this.m_last = nodeSetDTM.getLength();
        setObject(nodeSetDTM);
    }
}
