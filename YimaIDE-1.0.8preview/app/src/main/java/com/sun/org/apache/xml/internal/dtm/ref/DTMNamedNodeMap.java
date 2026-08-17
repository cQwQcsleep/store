package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.dtm.DTM;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTMNamedNodeMap implements NamedNodeMap {
    DTM dtm;
    int element;
    short m_count = -1;

    public DTMNamedNodeMap(DTM dtm, int i) {
        this.dtm = dtm;
        this.element = i;
    }

    @Override // org.w3c.dom.NamedNodeMap
    public int getLength() {
        if (this.m_count == -1) {
            int firstAttribute = this.dtm.getFirstAttribute(this.element);
            short s = 0;
            while (firstAttribute != -1) {
                s = (short) (s + 1);
                firstAttribute = this.dtm.getNextAttribute(firstAttribute);
            }
            this.m_count = s;
        }
        return this.m_count;
    }

    @Override // org.w3c.dom.NamedNodeMap
    public Node getNamedItem(String str) {
        int firstAttribute = this.dtm.getFirstAttribute(this.element);
        while (firstAttribute != -1) {
            boolean zEquals = this.dtm.getNodeName(firstAttribute).equals(str);
            DTM dtm = this.dtm;
            if (zEquals) {
                return dtm.getNode(firstAttribute);
            }
            firstAttribute = dtm.getNextAttribute(firstAttribute);
        }
        return null;
    }

    @Override // org.w3c.dom.NamedNodeMap
    public Node getNamedItemNS(String str, String str2) {
        int firstAttribute = this.dtm.getFirstAttribute(this.element);
        while (firstAttribute != -1) {
            if (str2.equals(this.dtm.getLocalName(firstAttribute))) {
                String namespaceURI = this.dtm.getNamespaceURI(firstAttribute);
                if ((str == null && namespaceURI == null) || (str != null && str.equals(namespaceURI))) {
                    return this.dtm.getNode(firstAttribute);
                }
            }
            firstAttribute = this.dtm.getNextAttribute(firstAttribute);
        }
        return null;
    }

    @Override // org.w3c.dom.NamedNodeMap
    public Node item(int i) {
        int firstAttribute = this.dtm.getFirstAttribute(this.element);
        int i2 = 0;
        while (firstAttribute != -1) {
            DTM dtm = this.dtm;
            if (i2 == i) {
                return dtm.getNode(firstAttribute);
            }
            i2++;
            firstAttribute = dtm.getNextAttribute(firstAttribute);
        }
        return null;
    }

    @Override // org.w3c.dom.NamedNodeMap
    public Node removeNamedItem(String str) {
        throw new DTMException((short) 7);
    }

    @Override // org.w3c.dom.NamedNodeMap
    public Node removeNamedItemNS(String str, String str2) throws DOMException {
        throw new DTMException((short) 7);
    }

    @Override // org.w3c.dom.NamedNodeMap
    public Node setNamedItem(Node node) {
        throw new DTMException((short) 7);
    }

    @Override // org.w3c.dom.NamedNodeMap
    public Node setNamedItemNS(Node node) throws DOMException {
        throw new DTMException((short) 7);
    }

    public class DTMException extends DOMException {
        static final long serialVersionUID = -8290238117162437678L;

        public DTMException(short s) {
            super(s, "");
        }

        public DTMException(short s, String str) {
            super(s, str);
        }
    }
}
