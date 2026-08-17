package com.sun.org.apache.xml.internal.utils;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xml.internal.res.XMLMessages;
import defpackage.x73;
import java.io.Writer;
import java.util.Stack;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMBuilder implements ContentHandler, LexicalHandler {
    protected Node m_currentNode;
    public Document m_doc;
    public DocumentFragment m_docFrag;
    protected Stack<Node> m_elemStack;
    protected boolean m_inCData;
    protected Node m_nextSibling;
    protected Node m_root;

    public DOMBuilder(Document document, Node node) {
        this.m_currentNode = null;
        this.m_root = null;
        this.m_nextSibling = null;
        this.m_docFrag = null;
        Stack<Node> stack = new Stack<>();
        this.m_elemStack = stack;
        this.m_inCData = false;
        this.m_doc = document;
        this.m_root = node;
        this.m_currentNode = node;
        if (node instanceof Element) {
            stack.push(node);
        }
    }

    private boolean isOutsideDocElem() {
        if (this.m_docFrag != null || this.m_elemStack.size() != 0) {
            return false;
        }
        Node node = this.m_currentNode;
        return node == null || node.getNodeType() == 9;
    }

    public void append(Node node) throws SAXException {
        Node node2;
        Node node3 = this.m_currentNode;
        if (node3 != null) {
            if (node3 != this.m_root || (node2 = this.m_nextSibling) == null) {
                node3.appendChild(node);
                return;
            } else {
                node3.insertBefore(node, node2);
                return;
            }
        }
        DocumentFragment documentFragment = this.m_docFrag;
        if (documentFragment != null) {
            Node node4 = this.m_nextSibling;
            if (node4 != null) {
                documentFragment.insertBefore(node, node4);
                return;
            } else {
                documentFragment.appendChild(node);
                return;
            }
        }
        short nodeType = node.getNodeType();
        if (nodeType == 3) {
            String nodeValue = node.getNodeValue();
            if (nodeValue != null && nodeValue.trim().length() > 0) {
                throw new SAXException(XMLMessages.createXMLMessage("ER_CANT_OUTPUT_TEXT_BEFORE_DOC", null));
            }
            return;
        }
        if (nodeType == 1 && this.m_doc.getDocumentElement() != null) {
            throw new SAXException(XMLMessages.createXMLMessage("ER_CANT_HAVE_MORE_THAN_ONE_ROOT", null));
        }
        Node node5 = this.m_nextSibling;
        Document document = this.m_doc;
        if (node5 != null) {
            document.insertBefore(node, node5);
        } else {
            document.appendChild(node);
        }
    }

    public void cdata(char[] cArr, int i, int i2) throws SAXException {
        if (isOutsideDocElem() && XMLCharacterRecognizer.isWhiteSpace(cArr, i, i2)) {
            return;
        }
        ((CDATASection) this.m_currentNode.getLastChild()).appendData(new String(cArr, i, i2));
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        if (isOutsideDocElem() && XMLCharacterRecognizer.isWhiteSpace(cArr, i, i2)) {
            return;
        }
        if (this.m_inCData) {
            cdata(cArr, i, i2);
            return;
        }
        String str = new String(cArr, i, i2);
        Node node = this.m_currentNode;
        Node lastChild = node != null ? node.getLastChild() : null;
        if (lastChild == null || lastChild.getNodeType() != 3) {
            append(this.m_doc.createTextNode(str));
        } else {
            ((Text) lastChild).appendData(str);
        }
    }

    public void charactersRaw(char[] cArr, int i, int i2) throws SAXException {
        if (isOutsideDocElem() && XMLCharacterRecognizer.isWhiteSpace(cArr, i, i2)) {
            return;
        }
        String str = new String(cArr, i, i2);
        append(this.m_doc.createProcessingInstruction("xslt-next-is-raw", "formatter-to-dom"));
        append(this.m_doc.createTextNode(str));
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
        append(this.m_doc.createComment(new String(cArr, i, i2)));
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
        this.m_inCData = false;
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endDTD() throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        this.m_elemStack.pop();
        this.m_currentNode = this.m_elemStack.isEmpty() ? null : this.m_elemStack.peek();
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endEntity(String str) throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) throws SAXException {
    }

    public void entityReference(String str) throws SAXException {
        append(this.m_doc.createEntityReference(str));
    }

    public Node getCurrentNode() {
        return this.m_currentNode;
    }

    public Node getNextSibling() {
        return this.m_nextSibling;
    }

    public Node getRootDocument() {
        DocumentFragment documentFragment = this.m_docFrag;
        return documentFragment != null ? documentFragment : this.m_doc;
    }

    public Node getRootNode() {
        return this.m_root;
    }

    public Writer getWriter() {
        return null;
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
        if (isOutsideDocElem()) {
            return;
        }
        append(this.m_doc.createTextNode(new String(cArr, i, i2)));
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        append(this.m_doc.createProcessingInstruction(str, str2));
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
    }

    public void setIDAttribute(String str, Element element) {
    }

    public void setNextSibling(Node node) {
        this.m_nextSibling = node;
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startCDATA() throws SAXException {
        this.m_inCData = true;
        append(this.m_doc.createCDATASection(""));
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startDTD(String str, String str2, String str3) throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        Element elementCreateElementNS = (str == null || str.length() == 0) ? this.m_doc.createElementNS(null, str3) : this.m_doc.createElementNS(str, str3);
        append(elementCreateElementNS);
        try {
            int length = attributes.getLength();
            if (length != 0) {
                for (int i = 0; i < length; i++) {
                    if (attributes.getType(i).equalsIgnoreCase(SchemaSymbols.ATTVAL_ID)) {
                        setIDAttribute(attributes.getValue(i), elementCreateElementNS);
                    }
                    String uri = attributes.getURI(i);
                    if ("".equals(uri)) {
                        uri = null;
                    }
                    String qName = attributes.getQName(i);
                    if (qName.startsWith("xmlns:") || qName.equals("xmlns")) {
                        uri = "http://www.w3.org/2000/xmlns/";
                    }
                    elementCreateElementNS.setAttributeNS(uri, qName, attributes.getValue(i));
                }
            }
            this.m_elemStack.push(elementCreateElementNS);
            this.m_currentNode = elementCreateElementNS;
        } catch (Exception e) {
            x73.a(e);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String str) throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) throws SAXException {
    }

    public DOMBuilder(Document document, DocumentFragment documentFragment) {
        this.m_currentNode = null;
        this.m_root = null;
        this.m_nextSibling = null;
        this.m_docFrag = null;
        this.m_elemStack = new Stack<>();
        this.m_inCData = false;
        this.m_doc = document;
        this.m_docFrag = documentFragment;
    }

    public DOMBuilder(Document document) {
        this.m_currentNode = null;
        this.m_root = null;
        this.m_nextSibling = null;
        this.m_docFrag = null;
        this.m_elemStack = new Stack<>();
        this.m_inCData = false;
        this.m_doc = document;
    }
}
