package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.StripFilter;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.DTMAxisTraverser;
import com.sun.org.apache.xml.internal.dtm.DTMWSFilter;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import com.sun.org.apache.xml.internal.utils.XMLString;
import java.util.Map;
import javax.xml.transform.SourceLocator;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AdaptiveResultTreeImpl extends SimpleResultTreeImpl {
    private static final String EMPTY_STRING = "".intern();
    private static int _documentURIIndex = 0;
    private final AttributesImpl _attributes;
    private boolean _buildIdIndex;
    private SAXImpl _dom;
    private int _initSize;
    private String _openElementName;
    private DTMWSFilter _wsfilter;

    public AdaptiveResultTreeImpl(XSLTCDTMManager xSLTCDTMManager, int i, DTMWSFilter dTMWSFilter, int i2, boolean z) {
        super(xSLTCDTMManager, i);
        this._attributes = new AttributesImpl();
        this._wsfilter = dTMWSFilter;
        this._initSize = i2;
        this._buildIdIndex = z;
    }

    private void maybeEmitStartElement() throws SAXException {
        String str = this._openElementName;
        if (str != null) {
            int iIndexOf = str.indexOf(58);
            SAXImpl sAXImpl = this._dom;
            if (iIndexOf < 0) {
                String str2 = this._openElementName;
                sAXImpl.startElement(null, str2, str2, this._attributes);
            } else {
                this._dom.startElement(sAXImpl.getNamespaceURI(this._openElementName.substring(0, iIndexOf)), this._openElementName.substring(iIndexOf + 1), this._openElementName, this._attributes);
            }
            this._openElementName = null;
        }
    }

    private void prepareNewDOM() throws SAXException {
        SAXImpl sAXImpl = (SAXImpl) this._dtmManager.getDTM(null, true, this._wsfilter, true, false, false, this._initSize, this._buildIdIndex);
        this._dom = sAXImpl;
        sAXImpl.startDocument();
        for (int i = 0; i < this._size; i++) {
            String str = this._textArray[i];
            this._dom.characters(str.toCharArray(), 0, str.length());
        }
        this._size = 0;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttribute(String str, String str2) {
        String str3;
        int iIndexOf = str.indexOf(58);
        String namespaceURI = EMPTY_STRING;
        if (iIndexOf > 0) {
            String strSubstring = str.substring(0, iIndexOf);
            String strSubstring2 = str.substring(iIndexOf + 1);
            namespaceURI = this._dom.getNamespaceURI(strSubstring);
            str3 = strSubstring2;
        } else {
            str3 = str;
        }
        addAttribute(namespaceURI, str3, str, "CDATA", str2);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addUniqueAttribute(String str, String str2, int i) throws SAXException {
        addAttribute(str, str2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public void appendChild(int i, boolean z, boolean z2) {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            sAXImpl.appendChild(i, z, z2);
        } else {
            super.appendChild(i, z, z2);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public void appendTextChild(String str) {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            sAXImpl.appendTextChild(str);
        } else {
            super.appendTextChild(str);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.serializer.EmptySerializer, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void characters(String str) throws SAXException {
        if (this._dom != null) {
            characters(str.toCharArray(), 0, str.length());
        } else {
            super.characters(str);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedLexicalHandler
    public void comment(String str) throws SAXException {
        if (this._dom == null) {
            prepareNewDOM();
        }
        maybeEmitStartElement();
        char[] charArray = str.toCharArray();
        this._dom.comment(charArray, 0, charArray.length);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public void copy(int i, SerializationHandler serializationHandler) throws TransletException {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            sAXImpl.copy(i, serializationHandler);
        } else {
            super.copy(i, serializationHandler);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public void dispatchCharactersEvents(int i, ContentHandler contentHandler, boolean z) throws SAXException {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            sAXImpl.dispatchCharactersEvents(i, contentHandler, z);
        } else {
            super.dispatchCharactersEvents(i, contentHandler, z);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public void dispatchToEvents(int i, ContentHandler contentHandler) throws SAXException {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            sAXImpl.dispatchToEvents(i, contentHandler);
        } else {
            super.dispatchToEvents(i, contentHandler);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public void documentRegistration() {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            sAXImpl.documentRegistration();
        } else {
            super.documentRegistration();
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public void documentRelease() {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            sAXImpl.documentRelease();
        } else {
            super.documentRelease();
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.serializer.EmptySerializer, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            sAXImpl.endDocument();
        } else {
            super.endDocument();
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void endElement(String str) throws SAXException {
        maybeEmitStartElement();
        this._dom.endElement(null, null, str);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getAttributeNode(int i, int i2) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getAttributeNode(i, i2) : super.getAttributeNode(i, i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public DTMAxisIterator getAxisIterator(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getAxisIterator(i) : super.getAxisIterator(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public DTMAxisTraverser getAxisTraverser(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getAxisTraverser(i) : super.getAxisTraverser(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getChildren(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getChildren(i) : super.getChildren(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public ContentHandler getContentHandler() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getContentHandler() : super.getContentHandler();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public DTDHandler getDTDHandler() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getDTDHandler() : super.getDTDHandler();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public DeclHandler getDeclHandler() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getDeclHandler() : super.getDeclHandler();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public int getDocument() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getDocument() : super.getDocument();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public boolean getDocumentAllDeclarationsProcessed() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getDocumentAllDeclarationsProcessed() : super.getDocumentAllDeclarationsProcessed();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentBaseURI() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getDocumentBaseURI() : super.getDocumentBaseURI();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentEncoding(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getDocumentEncoding(i) : super.getDocumentEncoding(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public int getDocumentRoot(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getDocumentRoot(i) : super.getDocumentRoot(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentStandalone(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getDocumentStandalone(i) : super.getDocumentStandalone(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentSystemIdentifier(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getDocumentSystemIdentifier(i) : super.getDocumentSystemIdentifier(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentTypeDeclarationPublicIdentifier() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getDocumentTypeDeclarationPublicIdentifier() : super.getDocumentTypeDeclarationPublicIdentifier();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentTypeDeclarationSystemIdentifier() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getDocumentTypeDeclarationSystemIdentifier() : super.getDocumentTypeDeclarationSystemIdentifier();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getDocumentURI(int i) {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            return sAXImpl.getDocumentURI(i);
        }
        StringBuilder sb = new StringBuilder("adaptive_rtf");
        int i2 = _documentURIIndex;
        _documentURIIndex = i2 + 1;
        sb.append(i2);
        return sb.toString();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentVersion(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getDocumentVersion(i) : super.getDocumentVersion(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public int getElementById(String str) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getElementById(str) : super.getElementById(str);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public Map<String, Integer> getElementsWithIDs() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getElementsWithIDs() : super.getElementsWithIDs();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public EntityResolver getEntityResolver() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getEntityResolver() : super.getEntityResolver();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public ErrorHandler getErrorHandler() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getErrorHandler() : super.getErrorHandler();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public int getExpandedTypeID(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getExpandedTypeID(i) : super.getExpandedTypeID(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public int getFirstAttribute(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getFirstAttribute(i) : super.getFirstAttribute(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public int getFirstChild(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getFirstChild(i) : super.getFirstChild(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public int getFirstNamespaceNode(int i, boolean z) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getFirstNamespaceNode(i, z) : super.getFirstNamespaceNode(i, z);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getIterator() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getIterator() : super.getIterator();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getLanguage(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getLanguage(i) : super.getLanguage(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public int getLastChild(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getLastChild(i) : super.getLastChild(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public short getLevel(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getLevel(i) : super.getLevel(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public LexicalHandler getLexicalHandler() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getLexicalHandler() : super.getLexicalHandler();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public String getLocalName(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getLocalName(i) : super.getLocalName(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public String getLocalNameFromExpandedNameID(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getLocalNameFromExpandedNameID(i) : super.getLocalNameFromExpandedNameID(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getNSType(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNSType(i) : super.getNSType(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getNamespaceAxisIterator(int i, int i2) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNamespaceAxisIterator(i, i2) : super.getNamespaceAxisIterator(i, i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNamespaceFromExpandedNameID(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNamespaceFromExpandedNameID(i) : super.getNamespaceFromExpandedNameID(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getNamespaceName(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNamespaceName(i) : super.getNamespaceName(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getNamespaceType(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNamespaceType(i) : super.getNamespaceType(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNamespaceURI(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNamespaceURI(i) : super.getNamespaceURI(i);
    }

    public DOM getNestedDOM() {
        return this._dom;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public int getNextAttribute(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNextAttribute(i) : super.getNextAttribute(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public int getNextNamespaceNode(int i, int i2, boolean z) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNextNamespaceNode(i, i2, z) : super.getNextNamespaceNode(i, i2, z);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public int getNextSibling(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNextSibling(i) : super.getNextSibling(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public Node getNode(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNode(i) : super.getNode(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public final int getNodeHandle(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNodeHandle(i) : super.getNodeHandle(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public final int getNodeIdent(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNodeIdent(i) : super.getNodeIdent(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeName(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNodeName(i) : super.getNodeName(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeNameX(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNodeNameX(i) : super.getNodeNameX(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public short getNodeType(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNodeType(i) : super.getNodeType(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeValue(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNodeValue(i) : super.getNodeValue(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getNodeValueIterator(DTMAxisIterator dTMAxisIterator, int i, String str, boolean z) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNodeValueIterator(dTMAxisIterator, i, str, z) : super.getNodeValueIterator(dTMAxisIterator, i, str, z);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getNthDescendant(int i, int i2, boolean z) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getNthDescendant(i, i2, z) : super.getNthDescendant(i, i2, z);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public SerializationHandler getOutputDomBuilder() {
        return this;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public int getOwnerDocument(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getOwnerDocument(i) : super.getOwnerDocument(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public int getParent(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getParent(i) : super.getParent(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public String getPrefix(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getPrefix(i) : super.getPrefix(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public int getPreviousSibling(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getPreviousSibling(i) : super.getPreviousSibling(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public DOM getResultTreeFrag(int i, int i2) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getResultTreeFrag(i, i2) : super.getResultTreeFrag(i, i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getSize() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getSize() : super.getSize();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public SourceLocator getSourceLocatorFor(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getSourceLocatorFor(i) : super.getSourceLocatorFor(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getStringValue() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getStringValue() : super.getStringValue();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public char[] getStringValueChunk(int i, int i2, int[] iArr) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getStringValueChunk(i, i2, iArr) : super.getStringValueChunk(i, i2, iArr);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public int getStringValueChunkCount(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getStringValueChunkCount(i) : super.getStringValueChunkCount(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getStringValueX(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getStringValueX(i) : super.getStringValueX(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public DTMAxisIterator getTypedAxisIterator(int i, int i2) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getTypedAxisIterator(i, i2) : super.getTypedAxisIterator(i, i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getTypedChildren(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getTypedChildren(i) : super.getTypedChildren(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public String getUnparsedEntityURI(String str) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.getUnparsedEntityURI(str) : super.getUnparsedEntityURI(str);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public boolean hasChildNodes(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.hasChildNodes(i) : super.hasChildNodes(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public boolean isAttribute(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.isAttribute(i) : super.isAttribute(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public boolean isAttributeSpecified(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.isAttributeSpecified(i) : super.isAttributeSpecified(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public boolean isCharacterElementContentWhitespace(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.isCharacterElementContentWhitespace(i) : super.isCharacterElementContentWhitespace(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public boolean isDocumentAllDeclarationsProcessed(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.isDocumentAllDeclarationsProcessed(i) : super.isDocumentAllDeclarationsProcessed(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public boolean isElement(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.isElement(i) : super.isElement(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public boolean isNodeAfter(int i, int i2) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.isNodeAfter(i, i2) : super.isNodeAfter(i, i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public boolean isSupported(String str, String str2) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.isSupported(str, str2) : super.isSupported(str, str2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public boolean lessThan(int i, int i2) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.lessThan(i, i2) : super.lessThan(i, i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public String lookupNamespace(int i, String str) throws TransletException {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.lookupNamespace(i, str) : super.lookupNamespace(i, str);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public Node makeNode(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.makeNode(i) : super.makeNode(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public NodeList makeNodeList(int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.makeNodeList(i) : super.makeNodeList(i);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void namespaceAfterStartElement(String str, String str2) throws SAXException {
        if (this._dom == null) {
            prepareNewDOM();
        }
        this._dom.startPrefixMapping(str, str2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public boolean needsTwoThreads() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.needsTwoThreads() : super.needsTwoThreads();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator orderNodes(DTMAxisIterator dTMAxisIterator, int i) {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.orderNodes(dTMAxisIterator, i) : super.orderNodes(dTMAxisIterator, i);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        if (this._dom == null) {
            prepareNewDOM();
        }
        maybeEmitStartElement();
        this._dom.processingInstruction(str, str2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public void release() {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            sAXImpl.release();
            this._dom = null;
        }
        super.release();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public void setDocumentBaseURI(String str) {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            sAXImpl.setDocumentBaseURI(str);
        } else {
            super.setDocumentBaseURI(str);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.serializer.EmptySerializer, com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public boolean setEscaping(boolean z) throws SAXException {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.setEscaping(z) : super.setEscaping(z);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public void setFeature(String str, boolean z) {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            sAXImpl.setFeature(str, z);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public void setFilter(StripFilter stripFilter) {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            sAXImpl.setFilter(stripFilter);
        } else {
            super.setFilter(stripFilter);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public void setProperty(String str, Object obj) {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            sAXImpl.setProperty(str, obj);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public void setupMapping(String[] strArr, String[] strArr2, int[] iArr, String[] strArr3) {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            sAXImpl.setupMapping(strArr, strArr2, iArr, strArr3);
        } else {
            super.setupMapping(strArr, strArr2, iArr, strArr3);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public String shallowCopy(int i, SerializationHandler serializationHandler) throws TransletException {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.shallowCopy(i, serializationHandler) : super.shallowCopy(i, serializationHandler);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.serializer.EmptySerializer, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void startElement(String str) throws SAXException {
        if (this._dom == null) {
            prepareNewDOM();
        }
        maybeEmitStartElement();
        this._openElementName = str;
        this._attributes.clear();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public boolean supportsPreStripping() {
        SAXImpl sAXImpl = this._dom;
        return sAXImpl != null ? sAXImpl.supportsPreStripping() : super.supportsPreStripping();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        endElement(str3);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public void copy(DTMAxisIterator dTMAxisIterator, SerializationHandler serializationHandler) throws TransletException {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            sAXImpl.copy(dTMAxisIterator, serializationHandler);
        } else {
            super.copy(dTMAxisIterator, serializationHandler);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public int getAttributeNode(int i, String str, String str2) {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            return sAXImpl.getAttributeNode(i, str, str2);
        }
        return super.getAttributeNode(i, str, str2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public int getExpandedTypeID(String str, String str2, int i) {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            return sAXImpl.getExpandedTypeID(str, str2, i);
        }
        return super.getExpandedTypeID(str, str2, i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.dtm.DTM
    public XMLString getStringValue(int i) {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            return sAXImpl.getStringValue(i);
        }
        return super.getStringValue(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public Node makeNode(DTMAxisIterator dTMAxisIterator) {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            return sAXImpl.makeNode(dTMAxisIterator);
        }
        return super.makeNode(dTMAxisIterator);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public NodeList makeNodeList(DTMAxisIterator dTMAxisIterator) {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            return sAXImpl.makeNodeList(dTMAxisIterator);
        }
        return super.makeNodeList(dTMAxisIterator);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void startElement(String str, String str2, String str3) throws SAXException {
        startElement(str3);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        startElement(str3);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xalan.internal.xsltc.DOM
    public void characters(int i, SerializationHandler serializationHandler) throws TransletException {
        SAXImpl sAXImpl = this._dom;
        if (sAXImpl != null) {
            sAXImpl.characters(i, serializationHandler);
        } else {
            super.characters(i, serializationHandler);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
        if (this._dom == null) {
            prepareNewDOM();
        }
        maybeEmitStartElement();
        this._dom.comment(cArr, i, i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl, com.sun.org.apache.xml.internal.serializer.EmptySerializer, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        if (this._dom != null) {
            maybeEmitStartElement();
            this._dom.characters(cArr, i, i2);
        } else {
            super.characters(cArr, i, i2);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttribute(String str, String str2, String str3, String str4, String str5) {
        if (this._openElementName != null) {
            this._attributes.addAttribute(str, str2, str3, str4, str5);
        } else {
            BasisLibrary.runTimeError("STRAY_ATTRIBUTE_ERR", str3);
        }
    }
}
