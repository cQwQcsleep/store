package com.sun.org.apache.xalan.internal.xsltc.trax;

import com.sun.org.apache.xalan.internal.xsltc.dom.SAXImpl;
import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOM2SAX implements XMLReader, Locator {
    private static final String EMPTYSTRING = "";
    private static final String XMLNS_PREFIX = "xmlns";
    private Node _dom;
    private ContentHandler _sax = null;
    private LexicalHandler _lex = null;
    private SAXImpl _saxImpl = null;
    private Map<String, Stack<String>> _nsPrefixes = new HashMap();

    public DOM2SAX(Node node) {
        this._dom = null;
        this._dom = node;
    }

    private void endPrefixMapping(String str) throws SAXException {
        Stack<String> stack = this._nsPrefixes.get(str);
        if (stack != null) {
            this._sax.endPrefixMapping(str);
            stack.pop();
        }
    }

    private void parse(Node node) throws SAXException, IOException {
        Node node2;
        String strSubstring;
        String strGeneratePrefix;
        if (node == null) {
            return;
        }
        short nodeType = node.getNodeType();
        if (nodeType != 1) {
            if (nodeType == 3) {
                String nodeValue = node.getNodeValue();
                this._sax.characters(nodeValue.toCharArray(), 0, nodeValue.length());
                return;
            }
            if (nodeType == 4) {
                String nodeValue2 = node.getNodeValue();
                LexicalHandler lexicalHandler = this._lex;
                if (lexicalHandler == null) {
                    this._sax.characters(nodeValue2.toCharArray(), 0, nodeValue2.length());
                    return;
                }
                lexicalHandler.startCDATA();
                this._sax.characters(nodeValue2.toCharArray(), 0, nodeValue2.length());
                this._lex.endCDATA();
                return;
            }
            if (nodeType == 7) {
                this._sax.processingInstruction(node.getNodeName(), node.getNodeValue());
                return;
            }
            if (nodeType == 8) {
                if (this._lex != null) {
                    String nodeValue3 = node.getNodeValue();
                    this._lex.comment(nodeValue3.toCharArray(), 0, nodeValue3.length());
                    return;
                }
                return;
            }
            if (nodeType != 9) {
                return;
            }
            this._sax.setDocumentLocator(this);
            this._sax.startDocument();
            for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
                parse(firstChild);
            }
            this._sax.endDocument();
            return;
        }
        ArrayList arrayList = new ArrayList();
        AttributesImpl attributesImpl = new AttributesImpl();
        NamedNodeMap attributes = node.getAttributes();
        int length = attributes.getLength();
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Node nodeItem = attributes.item(i);
            String nodeName = nodeItem.getNodeName();
            if (nodeName.startsWith("xmlns")) {
                String nodeValue4 = nodeItem.getNodeValue();
                int iLastIndexOf = nodeName.lastIndexOf(58);
                strSubstring = iLastIndexOf > 0 ? nodeName.substring(iLastIndexOf + 1) : "";
                if (startPrefixMapping(strSubstring, nodeValue4)) {
                    arrayList.add(strSubstring);
                }
            }
            i++;
        }
        for (int i2 = 0; i2 < length; i2++) {
            Node nodeItem2 = attributes.item(i2);
            String nodeName2 = nodeItem2.getNodeName();
            if (!nodeName2.startsWith("xmlns")) {
                String namespaceURI = nodeItem2.getNamespaceURI();
                if (namespaceURI != null) {
                    int iLastIndexOf2 = nodeName2.lastIndexOf(58);
                    if (iLastIndexOf2 > 0) {
                        strGeneratePrefix = nodeName2.substring(0, iLastIndexOf2);
                    } else {
                        strGeneratePrefix = BasisLibrary.generatePrefix();
                        nodeName2 = strGeneratePrefix + ':' + nodeName2;
                    }
                    if (startPrefixMapping(strGeneratePrefix, namespaceURI)) {
                        arrayList.add(strGeneratePrefix);
                    }
                }
                attributesImpl.addAttribute(nodeItem2.getNamespaceURI(), nodeItem2.getLocalName(), nodeName2, "CDATA", nodeItem2.getNodeValue());
            }
        }
        String nodeName3 = node.getNodeName();
        String namespaceURI2 = node.getNamespaceURI();
        String localName = node.getLocalName();
        if (namespaceURI2 != null) {
            int iLastIndexOf3 = nodeName3.lastIndexOf(58);
            strSubstring = iLastIndexOf3 > 0 ? nodeName3.substring(0, iLastIndexOf3) : "";
            if (startPrefixMapping(strSubstring, namespaceURI2)) {
                arrayList.add(strSubstring);
            }
        }
        SAXImpl sAXImpl = this._saxImpl;
        if (sAXImpl != null) {
            node2 = node;
            sAXImpl.startElement(namespaceURI2, localName, nodeName3, attributesImpl, node2);
        } else {
            node2 = node;
            this._sax.startElement(namespaceURI2, localName, nodeName3, attributesImpl);
        }
        for (Node firstChild2 = node2.getFirstChild(); firstChild2 != null; firstChild2 = firstChild2.getNextSibling()) {
            parse(firstChild2);
        }
        this._sax.endElement(namespaceURI2, localName, nodeName3);
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            endPrefixMapping((String) arrayList.get(i3));
        }
    }

    private boolean startPrefixMapping(String str, String str2) throws SAXException {
        Stack<String> stack = this._nsPrefixes.get(str);
        if (stack == null) {
            this._sax.startPrefixMapping(str, str2);
            Map<String, Stack<String>> map = this._nsPrefixes;
            Stack<String> stack2 = new Stack<>();
            map.put(str, stack2);
            stack2.push(str2);
            return true;
        }
        if (stack.isEmpty()) {
            this._sax.startPrefixMapping(str, str2);
            stack.push(str2);
            return true;
        }
        if (stack.peek().equals(str2)) {
            return false;
        }
        this._sax.startPrefixMapping(str, str2);
        stack.push(str2);
        return true;
    }

    @Override // org.xml.sax.Locator
    public int getColumnNumber() {
        return 0;
    }

    @Override // org.xml.sax.XMLReader
    public ContentHandler getContentHandler() {
        return this._sax;
    }

    @Override // org.xml.sax.XMLReader
    public DTDHandler getDTDHandler() {
        return null;
    }

    @Override // org.xml.sax.XMLReader
    public EntityResolver getEntityResolver() {
        return null;
    }

    @Override // org.xml.sax.XMLReader
    public ErrorHandler getErrorHandler() {
        return null;
    }

    @Override // org.xml.sax.XMLReader
    public boolean getFeature(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        return false;
    }

    @Override // org.xml.sax.Locator
    public int getLineNumber() {
        return 0;
    }

    @Override // org.xml.sax.XMLReader
    public Object getProperty(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        return null;
    }

    @Override // org.xml.sax.Locator
    public String getPublicId() {
        return null;
    }

    @Override // org.xml.sax.Locator
    public String getSystemId() {
        return null;
    }

    @Override // org.xml.sax.XMLReader
    public void setContentHandler(ContentHandler contentHandler) throws NullPointerException {
        this._sax = contentHandler;
        if (contentHandler instanceof LexicalHandler) {
            this._lex = (LexicalHandler) contentHandler;
        }
        if (contentHandler instanceof SAXImpl) {
            this._saxImpl = (SAXImpl) contentHandler;
        }
    }

    @Override // org.xml.sax.XMLReader
    public void setDTDHandler(DTDHandler dTDHandler) throws NullPointerException {
    }

    @Override // org.xml.sax.XMLReader
    public void setEntityResolver(EntityResolver entityResolver) throws NullPointerException {
    }

    @Override // org.xml.sax.XMLReader
    public void setErrorHandler(ErrorHandler errorHandler) throws NullPointerException {
    }

    @Override // org.xml.sax.XMLReader
    public void setFeature(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException {
    }

    @Override // org.xml.sax.XMLReader
    public void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
    }

    public void parse() throws SAXException, IOException {
        Node node = this._dom;
        if (node != null) {
            if (node.getNodeType() != 9) {
                this._sax.startDocument();
                parse(this._dom);
                this._sax.endDocument();
                return;
            }
            parse(this._dom);
        }
    }

    @Override // org.xml.sax.XMLReader
    public void parse(InputSource inputSource) throws SAXException, IOException {
        parse(this._dom);
    }

    @Override // org.xml.sax.XMLReader
    public void parse(String str) throws SAXException, IOException {
        throw new IOException("This method is not yet implemented.");
    }
}
