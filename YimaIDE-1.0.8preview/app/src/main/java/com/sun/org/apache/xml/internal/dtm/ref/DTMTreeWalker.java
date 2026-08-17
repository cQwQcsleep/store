package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.utils.XMLString;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTMTreeWalker {
    private ContentHandler m_contentHandler;
    protected DTM m_dtm;
    boolean nextIsRaw;

    public DTMTreeWalker(ContentHandler contentHandler, DTM dtm) {
        this.nextIsRaw = false;
        this.m_contentHandler = contentHandler;
        this.m_dtm = dtm;
    }

    private final void dispatachChars(int i) throws SAXException {
        this.m_dtm.dispatchCharactersEvents(i, this.m_contentHandler, false);
    }

    public void endNode(int i) throws SAXException {
        short nodeType = this.m_dtm.getNodeType(i);
        if (nodeType != 1) {
            if (nodeType != 5) {
                if (nodeType != 9) {
                    return;
                }
                this.m_contentHandler.endDocument();
                return;
            } else {
                ContentHandler contentHandler = this.m_contentHandler;
                if (contentHandler instanceof LexicalHandler) {
                    ((LexicalHandler) contentHandler).endEntity(this.m_dtm.getNodeName(i));
                    return;
                }
                return;
            }
        }
        String namespaceURI = this.m_dtm.getNamespaceURI(i);
        if (namespaceURI == null) {
            namespaceURI = "";
        }
        this.m_contentHandler.endElement(namespaceURI, this.m_dtm.getLocalName(i), this.m_dtm.getNodeName(i));
        int firstNamespaceNode = this.m_dtm.getFirstNamespaceNode(i, true);
        while (-1 != firstNamespaceNode) {
            this.m_contentHandler.endPrefixMapping(this.m_dtm.getNodeNameX(firstNamespaceNode));
            firstNamespaceNode = this.m_dtm.getNextNamespaceNode(i, firstNamespaceNode, true);
        }
    }

    public ContentHandler getcontentHandler() {
        return this.m_contentHandler;
    }

    public void setDTM(DTM dtm) {
        this.m_dtm = dtm;
    }

    public void setcontentHandler(ContentHandler contentHandler) {
        this.m_contentHandler = contentHandler;
    }

    public void startNode(int i) throws SAXException {
        short nodeType = this.m_dtm.getNodeType(i);
        if (nodeType == 1) {
            DTM dtm = this.m_dtm;
            int firstNamespaceNode = dtm.getFirstNamespaceNode(i, true);
            while (-1 != firstNamespaceNode) {
                this.m_contentHandler.startPrefixMapping(dtm.getNodeNameX(firstNamespaceNode), dtm.getNodeValue(firstNamespaceNode));
                firstNamespaceNode = dtm.getNextNamespaceNode(i, firstNamespaceNode, true);
            }
            String namespaceURI = dtm.getNamespaceURI(i);
            String str = namespaceURI != null ? namespaceURI : "";
            AttributesImpl attributesImpl = new AttributesImpl();
            for (int firstAttribute = dtm.getFirstAttribute(i); firstAttribute != -1; firstAttribute = dtm.getNextAttribute(firstAttribute)) {
                attributesImpl.addAttribute(dtm.getNamespaceURI(firstAttribute), dtm.getLocalName(firstAttribute), dtm.getNodeName(firstAttribute), "CDATA", dtm.getNodeValue(firstAttribute));
            }
            this.m_contentHandler.startElement(str, this.m_dtm.getLocalName(i), this.m_dtm.getNodeName(i), attributesImpl);
            return;
        }
        if (nodeType == 3) {
            if (!this.nextIsRaw) {
                dispatachChars(i);
                return;
            }
            this.nextIsRaw = false;
            this.m_contentHandler.processingInstruction("javax.xml.transform.disable-output-escaping", "");
            dispatachChars(i);
            this.m_contentHandler.processingInstruction("javax.xml.transform.enable-output-escaping", "");
            return;
        }
        if (nodeType == 4) {
            ContentHandler contentHandler = this.m_contentHandler;
            boolean z = contentHandler instanceof LexicalHandler;
            LexicalHandler lexicalHandler = z ? (LexicalHandler) contentHandler : null;
            if (z) {
                lexicalHandler.startCDATA();
            }
            dispatachChars(i);
            if (z) {
                lexicalHandler.endCDATA();
                return;
            }
            return;
        }
        if (nodeType == 5) {
            ContentHandler contentHandler2 = this.m_contentHandler;
            if (contentHandler2 instanceof LexicalHandler) {
                ((LexicalHandler) contentHandler2).startEntity(this.m_dtm.getNodeName(i));
                return;
            }
            return;
        }
        if (nodeType == 7) {
            String nodeName = this.m_dtm.getNodeName(i);
            if (nodeName.equals("xslt-next-is-raw")) {
                this.nextIsRaw = true;
                return;
            } else {
                this.m_contentHandler.processingInstruction(nodeName, this.m_dtm.getNodeValue(i));
                return;
            }
        }
        if (nodeType != 8) {
            if (nodeType != 9) {
                return;
            }
            this.m_contentHandler.startDocument();
        } else {
            XMLString stringValue = this.m_dtm.getStringValue(i);
            ContentHandler contentHandler3 = this.m_contentHandler;
            if (contentHandler3 instanceof LexicalHandler) {
                stringValue.dispatchAsComment((LexicalHandler) contentHandler3);
            }
        }
    }

    public void traverse(int i) throws SAXException {
        int parent = i;
        while (-1 != parent) {
            startNode(parent);
            int firstChild = this.m_dtm.getFirstChild(parent);
            while (true) {
                if (-1 == firstChild) {
                    endNode(parent);
                    if (i != parent) {
                        firstChild = this.m_dtm.getNextSibling(parent);
                        if (-1 == firstChild && (-1 == (parent = this.m_dtm.getParent(parent)) || i == parent)) {
                            if (-1 != parent) {
                                endNode(parent);
                            }
                            parent = -1;
                        }
                    }
                }
                parent = firstChild;
            }
        }
    }

    public DTMTreeWalker() {
        this.m_contentHandler = null;
        this.nextIsRaw = false;
    }

    public void traverse(int i, int i2) throws SAXException {
        while (-1 != i) {
            startNode(i);
            int firstChild = this.m_dtm.getFirstChild(i);
            while (true) {
                if (-1 == firstChild) {
                    endNode(i);
                    if (-1 == i2 || i2 != i) {
                        firstChild = this.m_dtm.getNextSibling(i);
                        if (-1 == firstChild && (-1 == (i = this.m_dtm.getParent(i)) || (-1 != i2 && i2 == i))) {
                            i = -1;
                        }
                    }
                }
                i = firstChild;
            }
        }
    }
}
