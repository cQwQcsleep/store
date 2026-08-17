package com.sun.org.apache.xerces.internal.dom;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Comment;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CommentImpl extends CharacterDataImpl implements CharacterData, Comment {
    static final long serialVersionUID = -2685736833408134044L;

    public CommentImpl(CoreDocumentImpl coreDocumentImpl, String str) {
        super(coreDocumentImpl, str);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        return PsuedoNames.PSEUDONAME_COMMENT;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return (short) 8;
    }
}
