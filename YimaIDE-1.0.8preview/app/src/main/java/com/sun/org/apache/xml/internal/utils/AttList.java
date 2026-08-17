package com.sun.org.apache.xml.internal.utils;

import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AttList implements Attributes {
    NamedNodeMap m_attrs;
    int m_lastIndex;

    public AttList(NamedNodeMap namedNodeMap) {
        this.m_attrs = namedNodeMap;
        this.m_lastIndex = namedNodeMap.getLength() - 1;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x001f  */
    /* JADX WARN: Code duplicated, block: B:17:0x0029 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:20:0x002a A[SYNTHETIC] */
    @Override // org.xml.sax.Attributes
    public int getIndex(String str, String str2) {
        for (int length = this.m_attrs.getLength() - 1; length >= 0; length--) {
            Node nodeItem = this.m_attrs.item(length);
            String namespaceURI = nodeItem.getNamespaceURI();
            if (namespaceURI == null) {
                if (str != null) {
                    continue;
                } else if (nodeItem.getLocalName().equals(str2)) {
                    return length;
                }
            } else if (!namespaceURI.equals(str)) {
                continue;
            } else if (nodeItem.getLocalName().equals(str2)) {
                return length;
            }
        }
        return -1;
    }

    @Override // org.xml.sax.Attributes
    public int getLength() {
        return this.m_attrs.getLength();
    }

    @Override // org.xml.sax.Attributes
    public String getLocalName(int i) {
        return DOM2Helper.getLocalNameOfNode((Attr) this.m_attrs.item(i));
    }

    @Override // org.xml.sax.Attributes
    public String getQName(int i) {
        return ((Attr) this.m_attrs.item(i)).getName();
    }

    @Override // org.xml.sax.Attributes
    public String getType(int i) {
        return "CDATA";
    }

    @Override // org.xml.sax.Attributes
    public String getURI(int i) {
        String namespaceOfNode = DOM2Helper.getNamespaceOfNode((Attr) this.m_attrs.item(i));
        return namespaceOfNode == null ? "" : namespaceOfNode;
    }

    @Override // org.xml.sax.Attributes
    public String getValue(String str) {
        Attr attr = (Attr) this.m_attrs.getNamedItem(str);
        if (attr != null) {
            return attr.getValue();
        }
        return null;
    }

    @Override // org.xml.sax.Attributes
    public String getType(String str) {
        return "CDATA";
    }

    @Override // org.xml.sax.Attributes
    public String getType(String str, String str2) {
        return "CDATA";
    }

    @Override // org.xml.sax.Attributes
    public String getValue(int i) {
        return ((Attr) this.m_attrs.item(i)).getValue();
    }

    @Override // org.xml.sax.Attributes
    public String getValue(String str, String str2) {
        Node namedItemNS = this.m_attrs.getNamedItemNS(str, str2);
        if (namedItemNS == null) {
            return null;
        }
        return namedItemNS.getNodeValue();
    }

    @Override // org.xml.sax.Attributes
    public int getIndex(String str) {
        for (int length = this.m_attrs.getLength() - 1; length >= 0; length--) {
            if (this.m_attrs.item(length).getNodeName().equals(str)) {
                return length;
            }
        }
        return -1;
    }
}
