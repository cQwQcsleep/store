package com.sun.org.apache.xerces.internal.dom;

import org.w3c.dom.DOMLocator;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMLocatorImpl implements DOMLocator {
    public int fByteOffset;
    public int fColumnNumber;
    public int fLineNumber;
    public Node fRelatedNode;
    public String fUri;
    public int fUtf16Offset;

    public DOMLocatorImpl() {
        this.fColumnNumber = -1;
        this.fLineNumber = -1;
        this.fRelatedNode = null;
        this.fUri = null;
        this.fByteOffset = -1;
        this.fUtf16Offset = -1;
    }

    @Override // org.w3c.dom.DOMLocator
    public int getByteOffset() {
        return this.fByteOffset;
    }

    @Override // org.w3c.dom.DOMLocator
    public int getColumnNumber() {
        return this.fColumnNumber;
    }

    @Override // org.w3c.dom.DOMLocator
    public int getLineNumber() {
        return this.fLineNumber;
    }

    @Override // org.w3c.dom.DOMLocator
    public Node getRelatedNode() {
        return this.fRelatedNode;
    }

    @Override // org.w3c.dom.DOMLocator
    public String getUri() {
        return this.fUri;
    }

    @Override // org.w3c.dom.DOMLocator
    public int getUtf16Offset() {
        return this.fUtf16Offset;
    }

    public DOMLocatorImpl(int i, int i2, String str) {
        this.fRelatedNode = null;
        this.fByteOffset = -1;
        this.fUtf16Offset = -1;
        this.fLineNumber = i;
        this.fColumnNumber = i2;
        this.fUri = str;
    }

    public DOMLocatorImpl(int i, int i2, int i3, String str) {
        this.fRelatedNode = null;
        this.fByteOffset = -1;
        this.fLineNumber = i;
        this.fColumnNumber = i2;
        this.fUri = str;
        this.fUtf16Offset = i3;
    }

    public DOMLocatorImpl(int i, int i2, int i3, Node node, String str) {
        this.fUtf16Offset = -1;
        this.fLineNumber = i;
        this.fColumnNumber = i2;
        this.fByteOffset = i3;
        this.fRelatedNode = node;
        this.fUri = str;
    }

    public DOMLocatorImpl(int i, int i2, int i3, Node node, String str, int i4) {
        this.fLineNumber = i;
        this.fColumnNumber = i2;
        this.fByteOffset = i3;
        this.fRelatedNode = node;
        this.fUri = str;
        this.fUtf16Offset = i4;
    }
}
