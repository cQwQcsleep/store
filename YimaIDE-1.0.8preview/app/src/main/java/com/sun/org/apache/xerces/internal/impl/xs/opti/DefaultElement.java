package com.sun.org.apache.xerces.internal.impl.xs.opti;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DefaultElement extends NodeImpl implements Element {
    public DefaultElement() {
    }

    @Override // org.w3c.dom.Element
    public String getAttribute(String str) {
        return null;
    }

    @Override // org.w3c.dom.Element
    public String getAttributeNS(String str, String str2) {
        return null;
    }

    @Override // org.w3c.dom.Element
    public Attr getAttributeNode(String str) {
        return null;
    }

    @Override // org.w3c.dom.Element
    public Attr getAttributeNodeNS(String str, String str2) {
        return null;
    }

    @Override // org.w3c.dom.Element
    public NodeList getElementsByTagName(String str) {
        return null;
    }

    @Override // org.w3c.dom.Element
    public NodeList getElementsByTagNameNS(String str, String str2) {
        return null;
    }

    @Override // org.w3c.dom.Element
    public TypeInfo getSchemaTypeInfo() {
        return null;
    }

    @Override // org.w3c.dom.Element
    public String getTagName() {
        return null;
    }

    @Override // org.w3c.dom.Element
    public boolean hasAttribute(String str) {
        return false;
    }

    @Override // org.w3c.dom.Element
    public boolean hasAttributeNS(String str, String str2) {
        return false;
    }

    @Override // org.w3c.dom.Element
    public void removeAttribute(String str) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Element
    public void removeAttributeNS(String str, String str2) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Element
    public Attr removeAttributeNode(Attr attr) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Element
    public void setAttribute(String str, String str2) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Element
    public void setAttributeNS(String str, String str2, String str3) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Element
    public Attr setAttributeNode(Attr attr) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Element
    public Attr setAttributeNodeNS(Attr attr) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Element
    public void setIdAttribute(String str, boolean z) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Element
    public void setIdAttributeNS(String str, String str2, boolean z) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Element
    public void setIdAttributeNode(Attr attr, boolean z) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public DefaultElement(String str, String str2, String str3, String str4, short s) {
        super(str, str2, str3, str4, s);
    }
}
