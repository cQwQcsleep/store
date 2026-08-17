package com.sun.org.apache.xerces.internal.dom;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class NodeListCache implements Serializable {
    private static final long serialVersionUID = -7927529254918631002L;
    ChildNode fChild;
    ParentNode fOwner;
    NodeListCache next;
    int fLength = -1;
    int fChildIndex = -1;

    public NodeListCache(ParentNode parentNode) {
        this.fOwner = parentNode;
    }
}
