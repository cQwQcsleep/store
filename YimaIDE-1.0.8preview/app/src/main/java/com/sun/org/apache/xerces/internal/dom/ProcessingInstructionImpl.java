package com.sun.org.apache.xerces.internal.dom;

import org.w3c.dom.ProcessingInstruction;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProcessingInstructionImpl extends CharacterDataImpl implements ProcessingInstruction {
    static final long serialVersionUID = 7554435174099981510L;
    protected String target;

    public ProcessingInstructionImpl(CoreDocumentImpl coreDocumentImpl, String str, String str2) {
        super(coreDocumentImpl, str2);
        this.target = str;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getBaseURI() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.ownerNode.getBaseURI();
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CharacterDataImpl, org.w3c.dom.ProcessingInstruction
    public String getData() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.data;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.target;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return (short) 7;
    }

    @Override // org.w3c.dom.ProcessingInstruction
    public String getTarget() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.target;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CharacterDataImpl, org.w3c.dom.ProcessingInstruction
    public void setData(String str) {
        setNodeValue(str);
    }
}
