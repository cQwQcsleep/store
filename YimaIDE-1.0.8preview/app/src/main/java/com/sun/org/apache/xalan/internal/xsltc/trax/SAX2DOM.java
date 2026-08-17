package com.sun.org.apache.xalan.internal.xsltc.trax;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Constants;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import jdk.xml.internal.JdkXmlUtils;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ext.Locator2;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SAX2DOM implements ContentHandler, LexicalHandler, Constants {
    private Document _document;
    private DocumentBuilderFactory _factory;
    private boolean _internal;
    private Node _lastSibling;
    private List<String> _namespaceDecls;
    private Node _nextSibling;
    private Node _nextSiblingCache;
    private Stack<Node> _nodeStk;
    private Node _root;
    private StringBuilder _textBuffer;
    private Locator locator;
    private boolean needToSetDocumentInfo;

    public SAX2DOM(Node node, Node node2, boolean z) throws ParserConfigurationException {
        this._root = null;
        this._document = null;
        this._nextSibling = null;
        this._nodeStk = new Stack<>();
        this._namespaceDecls = null;
        this._lastSibling = null;
        this.locator = null;
        this.needToSetDocumentInfo = true;
        this._textBuffer = new StringBuilder();
        this._nextSiblingCache = null;
        this._internal = true;
        this._root = node;
        if (node instanceof Document) {
            this._document = (Document) node;
        } else if (node != null) {
            this._document = node.getOwnerDocument();
        } else {
            Document documentCreateDocument = createDocument(z);
            this._document = documentCreateDocument;
            this._root = documentCreateDocument;
        }
        this._nextSibling = node2;
    }

    private void appendTextNode() {
        if (this._textBuffer.length() > 0) {
            Node nodePeek = this._nodeStk.peek();
            if (nodePeek != this._root || this._nextSiblingCache == null) {
                this._lastSibling = nodePeek.appendChild(this._document.createTextNode(this._textBuffer.toString()));
            } else {
                this._lastSibling = nodePeek.insertBefore(this._document.createTextNode(this._textBuffer.toString()), this._nextSiblingCache);
            }
            this._textBuffer.setLength(0);
        }
    }

    private Document createDocument(boolean z) throws ParserConfigurationException {
        Document documentNewDocument;
        if (this._factory == null) {
            DocumentBuilderFactory dOMFactory = JdkXmlUtils.getDOMFactory(z);
            this._factory = dOMFactory;
            this._internal = true;
            if (!(dOMFactory instanceof DocumentBuilderFactoryImpl)) {
                this._internal = false;
            }
        }
        if (this._internal) {
            return this._factory.newDocumentBuilder().newDocument();
        }
        synchronized (SAX2DOM.class) {
            documentNewDocument = this._factory.newDocumentBuilder().newDocument();
        }
        return documentNewDocument;
    }

    private void setDocumentInfo() {
        Locator locator = this.locator;
        if (locator == null) {
            return;
        }
        try {
            this._document.setXmlVersion(((Locator2) locator).getXMLVersion());
        } catch (ClassCastException unused) {
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) {
        if (i2 == 0 || this._nodeStk.peek() == this._document) {
            return;
        }
        this._nextSiblingCache = this._nextSibling;
        this._textBuffer.append(cArr, i, i2);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) {
        Node node;
        appendTextNode();
        Node nodePeek = this._nodeStk.peek();
        Comment commentCreateComment = this._document.createComment(new String(cArr, i, i2));
        if (commentCreateComment != null) {
            if (nodePeek != this._root || (node = this._nextSibling) == null) {
                nodePeek.appendChild(commentCreateComment);
            } else {
                nodePeek.insertBefore(commentCreateComment, node);
            }
            this._lastSibling = commentCreateComment;
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endCDATA() {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endDTD() {
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() {
        this._nodeStk.pop();
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) {
        appendTextNode();
        this._nodeStk.pop();
        this._lastSibling = null;
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endEntity(String str) {
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) {
    }

    public Node getDOM() {
        return this._root;
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) {
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) {
        Node node;
        appendTextNode();
        Node nodePeek = this._nodeStk.peek();
        ProcessingInstruction processingInstructionCreateProcessingInstruction = this._document.createProcessingInstruction(str, str2);
        if (processingInstructionCreateProcessingInstruction != null) {
            if (nodePeek != this._root || (node = this._nextSibling) == null) {
                nodePeek.appendChild(processingInstructionCreateProcessingInstruction);
            } else {
                nodePeek.insertBefore(processingInstructionCreateProcessingInstruction, node);
            }
            this._lastSibling = processingInstructionCreateProcessingInstruction;
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startCDATA() {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startDTD(String str, String str2, String str3) throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() {
        this._nodeStk.push(this._root);
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) {
        Node node;
        List<String> list;
        appendTextNode();
        if (this.needToSetDocumentInfo) {
            setDocumentInfo();
            this.needToSetDocumentInfo = false;
        }
        Element elementCreateElementNS = this._document.createElementNS(str, str3);
        List<String> list2 = this._namespaceDecls;
        if (list2 != null) {
            int size = list2.size();
            int i = 0;
            while (true) {
                list = this._namespaceDecls;
                if (i >= size) {
                    break;
                }
                int i2 = i + 1;
                String str4 = list.get(i);
                if (str4 == null || str4.equals("")) {
                    elementCreateElementNS.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", this._namespaceDecls.get(i2));
                } else {
                    elementCreateElementNS.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:".concat(str4), this._namespaceDecls.get(i2));
                }
                i += 2;
            }
            list.clear();
        }
        int length = attributes.getLength();
        for (int i3 = 0; i3 < length; i3++) {
            String qName = attributes.getQName(i3);
            String uri = attributes.getURI(i3);
            String type = attributes.getType(i3) == null ? XMLSymbols.fCDATASymbol : attributes.getType(i3);
            if (attributes.getLocalName(i3).equals("")) {
                elementCreateElementNS.setAttribute(qName, attributes.getValue(i3));
                if (type.equals(SchemaSymbols.ATTVAL_ID)) {
                    elementCreateElementNS.setIdAttribute(qName, true);
                }
            } else {
                elementCreateElementNS.setAttributeNS(uri, qName, attributes.getValue(i3));
                if (type.equals(SchemaSymbols.ATTVAL_ID)) {
                    elementCreateElementNS.setIdAttributeNS(uri, attributes.getLocalName(i3), true);
                }
            }
        }
        Node nodePeek = this._nodeStk.peek();
        if (nodePeek != this._root || (node = this._nextSibling) == null) {
            nodePeek.appendChild(elementCreateElementNS);
        } else {
            nodePeek.insertBefore(elementCreateElementNS, node);
        }
        this._nodeStk.push(elementCreateElementNS);
        this._lastSibling = null;
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String str) {
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) {
        if (this._namespaceDecls == null) {
            this._namespaceDecls = new ArrayList(2);
        }
        this._namespaceDecls.add(str);
        this._namespaceDecls.add(str2);
    }

    public SAX2DOM(boolean z) throws ParserConfigurationException {
        this._root = null;
        this._document = null;
        this._nextSibling = null;
        this._nodeStk = new Stack<>();
        this._namespaceDecls = null;
        this._lastSibling = null;
        this.locator = null;
        this.needToSetDocumentInfo = true;
        this._textBuffer = new StringBuilder();
        this._nextSiblingCache = null;
        this._internal = true;
        Document documentCreateDocument = createDocument(z);
        this._document = documentCreateDocument;
        this._root = documentCreateDocument;
    }

    public SAX2DOM(Node node, boolean z) throws ParserConfigurationException {
        this(node, null, z);
    }
}
