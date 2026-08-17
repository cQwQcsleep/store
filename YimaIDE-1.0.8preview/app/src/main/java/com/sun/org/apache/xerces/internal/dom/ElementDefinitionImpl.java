package com.sun.org.apache.xerces.internal.dom;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ElementDefinitionImpl extends ParentNode {
    static final long serialVersionUID = -8373890672670022714L;
    protected NamedNodeMapImpl attributes;
    protected String name;

    public ElementDefinitionImpl(CoreDocumentImpl coreDocumentImpl, String str) {
        super(coreDocumentImpl);
        this.name = str;
        this.attributes = new NamedNodeMapImpl(coreDocumentImpl);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.ChildNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public Node cloneNode(boolean z) {
        ElementDefinitionImpl elementDefinitionImpl = (ElementDefinitionImpl) super.cloneNode(z);
        elementDefinitionImpl.attributes = this.attributes.cloneMap(elementDefinitionImpl);
        return elementDefinitionImpl;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public NamedNodeMap getAttributes() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return this.attributes;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.name;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return (short) 21;
    }
}
