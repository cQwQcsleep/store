package com.sun.org.apache.xml.internal.utils;

import com.sun.org.apache.xml.internal.dtm.ref.dom2dtm.DOM2DTM;
import org.w3c.dom.Comment;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.LocatorImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TreeWalker {
    private ContentHandler m_contentHandler;
    private LocatorImpl m_locator;
    boolean nextIsRaw;

    public TreeWalker(ContentHandler contentHandler, String str) {
        this.m_contentHandler = null;
        LocatorImpl locatorImpl = new LocatorImpl();
        this.m_locator = locatorImpl;
        this.nextIsRaw = false;
        this.m_contentHandler = contentHandler;
        if (contentHandler != null) {
            contentHandler.setDocumentLocator(locatorImpl);
        }
        if (str != null) {
            this.m_locator.setSystemId(str);
        }
    }

    private final void dispatachChars(Node node) throws SAXException {
        ContentHandler contentHandler = this.m_contentHandler;
        if (contentHandler instanceof DOM2DTM.CharacterNodeHandler) {
            ((DOM2DTM.CharacterNodeHandler) contentHandler).characters(node);
        } else {
            String data = ((Text) node).getData();
            this.m_contentHandler.characters(data.toCharArray(), 0, data.length());
        }
    }

    public void endNode(Node node) throws SAXException {
        short nodeType = node.getNodeType();
        if (nodeType != 1) {
            if (nodeType != 5) {
                return;
            }
            EntityReference entityReference = (EntityReference) node;
            ContentHandler contentHandler = this.m_contentHandler;
            if (contentHandler instanceof LexicalHandler) {
                ((LexicalHandler) contentHandler).endEntity(entityReference.getNodeName());
                return;
            }
            return;
        }
        String namespaceOfNode = DOM2Helper.getNamespaceOfNode(node);
        if (namespaceOfNode == null) {
            namespaceOfNode = "";
        }
        this.m_contentHandler.endElement(namespaceOfNode, DOM2Helper.getLocalNameOfNode(node), node.getNodeName());
        NamedNodeMap attributes = ((Element) node).getAttributes();
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            String nodeName = attributes.item(i).getNodeName();
            if (nodeName.equals("xmlns") || nodeName.startsWith("xmlns:")) {
                int iIndexOf = nodeName.indexOf(":");
                this.m_contentHandler.endPrefixMapping(iIndexOf < 0 ? "" : nodeName.substring(iIndexOf + 1));
            }
        }
    }

    public ContentHandler getContentHandler() {
        return this.m_contentHandler;
    }

    public void setContentHandler(ContentHandler contentHandler) {
        this.m_contentHandler = contentHandler;
    }

    public void startNode(Node node) throws SAXException {
        ContentHandler contentHandler = this.m_contentHandler;
        if (contentHandler instanceof NodeConsumer) {
            ((NodeConsumer) contentHandler).setOriginatingNode(node);
        }
        if (node instanceof Locator) {
            Locator locator = (Locator) node;
            this.m_locator.setColumnNumber(locator.getColumnNumber());
            this.m_locator.setLineNumber(locator.getLineNumber());
            this.m_locator.setPublicId(locator.getPublicId());
            this.m_locator.setSystemId(locator.getSystemId());
        } else {
            this.m_locator.setColumnNumber(0);
            this.m_locator.setLineNumber(0);
        }
        short nodeType = node.getNodeType();
        if (nodeType == 1) {
            NamedNodeMap attributes = ((Element) node).getAttributes();
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                Node nodeItem = attributes.item(i);
                String nodeName = nodeItem.getNodeName();
                if (nodeName.equals("xmlns") || nodeName.startsWith("xmlns:")) {
                    int iIndexOf = nodeName.indexOf(":");
                    this.m_contentHandler.startPrefixMapping(iIndexOf < 0 ? "" : nodeName.substring(iIndexOf + 1), nodeItem.getNodeValue());
                }
            }
            String namespaceOfNode = DOM2Helper.getNamespaceOfNode(node);
            this.m_contentHandler.startElement(namespaceOfNode != null ? namespaceOfNode : "", DOM2Helper.getLocalNameOfNode(node), node.getNodeName(), new AttList(attributes));
            return;
        }
        if (nodeType == 3) {
            if (!this.nextIsRaw) {
                dispatachChars(node);
                return;
            }
            this.nextIsRaw = false;
            this.m_contentHandler.processingInstruction("javax.xml.transform.disable-output-escaping", "");
            dispatachChars(node);
            this.m_contentHandler.processingInstruction("javax.xml.transform.enable-output-escaping", "");
            return;
        }
        if (nodeType == 4) {
            ContentHandler contentHandler2 = this.m_contentHandler;
            boolean z = contentHandler2 instanceof LexicalHandler;
            LexicalHandler lexicalHandler = z ? (LexicalHandler) contentHandler2 : null;
            if (z) {
                lexicalHandler.startCDATA();
            }
            dispatachChars(node);
            if (z) {
                lexicalHandler.endCDATA();
                return;
            }
            return;
        }
        if (nodeType == 5) {
            EntityReference entityReference = (EntityReference) node;
            ContentHandler contentHandler3 = this.m_contentHandler;
            if (contentHandler3 instanceof LexicalHandler) {
                ((LexicalHandler) contentHandler3).startEntity(entityReference.getNodeName());
                return;
            }
            return;
        }
        if (nodeType == 7) {
            ProcessingInstruction processingInstruction = (ProcessingInstruction) node;
            if (processingInstruction.getNodeName().equals("xslt-next-is-raw")) {
                this.nextIsRaw = true;
                return;
            } else {
                this.m_contentHandler.processingInstruction(processingInstruction.getNodeName(), processingInstruction.getData());
                return;
            }
        }
        if (nodeType != 8) {
            return;
        }
        String data = ((Comment) node).getData();
        ContentHandler contentHandler4 = this.m_contentHandler;
        if (contentHandler4 instanceof LexicalHandler) {
            ((LexicalHandler) contentHandler4).comment(data.toCharArray(), 0, data.length());
        }
    }

    public void traverse(Node node, Node node2) throws SAXException {
        this.m_contentHandler.startDocument();
        while (node != null) {
            startNode(node);
            Node firstChild = node.getFirstChild();
            while (true) {
                if (firstChild == null) {
                    endNode(node);
                    if (node2 == null || !node2.equals(node)) {
                        firstChild = node.getNextSibling();
                        if (firstChild == null && ((node = node.getParentNode()) == null || (node2 != null && node2.equals(node)))) {
                            node = null;
                        }
                    }
                }
                node = firstChild;
            }
        }
        this.m_contentHandler.endDocument();
    }

    public void traverseFragment(Node node) throws SAXException {
        Node parentNode = node;
        while (parentNode != null) {
            startNode(parentNode);
            Node firstChild = parentNode.getFirstChild();
            while (true) {
                if (firstChild == null) {
                    endNode(parentNode);
                    if (!node.equals(parentNode)) {
                        firstChild = parentNode.getNextSibling();
                        if (firstChild == null && ((parentNode = parentNode.getParentNode()) == null || node.equals(parentNode))) {
                            if (parentNode != null) {
                                endNode(parentNode);
                            }
                            parentNode = null;
                        }
                    }
                }
                parentNode = firstChild;
            }
        }
    }

    public TreeWalker(ContentHandler contentHandler) {
        this(contentHandler, null);
    }

    public void traverse(Node node) throws SAXException {
        this.m_contentHandler.startDocument();
        traverseFragment(node);
        this.m_contentHandler.endDocument();
    }
}
