package com.sun.org.apache.xerces.internal.dom;

import org.w3c.dom.CDATASection;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CDATASectionImpl extends TextImpl implements CDATASection {
    static final long serialVersionUID = 2372071297878177780L;

    public CDATASectionImpl(CoreDocumentImpl coreDocumentImpl, String str) {
        super(coreDocumentImpl, str);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.TextImpl, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        return "#cdata-section";
    }

    @Override // com.sun.org.apache.xerces.internal.dom.TextImpl, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return (short) 4;
    }
}
