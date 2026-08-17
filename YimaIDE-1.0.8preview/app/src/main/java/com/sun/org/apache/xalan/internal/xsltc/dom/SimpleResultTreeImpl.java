package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.StripFilter;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.DTMAxisTraverser;
import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase;
import com.sun.org.apache.xml.internal.dtm.ref.DTMManagerDefault;
import com.sun.org.apache.xml.internal.serializer.EmptySerializer;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xml.internal.utils.XMLStringDefault;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.util.Map;
import javax.xml.transform.SourceLocator;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SimpleResultTreeImpl extends EmptySerializer implements DOM, DTM {
    private static final String EMPTY_STR = "";
    public static final int NUMBER_OF_NODES = 2;
    public static final int RTF_ROOT = 0;
    public static final int RTF_TEXT = 1;
    private int _documentID;
    protected XSLTCDTMManager _dtmManager;
    private String _text;
    private static final DTMAxisIterator EMPTY_ITERATOR = new DTMAxisIteratorBase() { // from class: com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl.1
        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator cloneIterator() {
            return this;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int getLast() {
            return 0;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int getPosition() {
            return 0;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void gotoMark() {
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            return -1;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator reset() {
            return this;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void setMark() {
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void setRestartable(boolean z) {
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            return this;
        }
    };
    private static int _documentURIIndex = 0;
    protected int _size = 0;
    private BitArray _dontEscape = null;
    private boolean _escaping = true;
    protected String[] _textArray = new String[4];

    public SimpleResultTreeImpl(XSLTCDTMManager xSLTCDTMManager, int i) {
        this._dtmManager = xSLTCDTMManager;
        this._documentID = i;
    }

    public void appendChild(int i, boolean z, boolean z2) {
    }

    public void appendTextChild(String str) {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        int i3 = this._size;
        String[] strArr = this._textArray;
        if (i3 >= strArr.length) {
            String[] strArr2 = new String[strArr.length * 2];
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
            this._textArray = strArr2;
        }
        if (!this._escaping) {
            if (this._dontEscape == null) {
                this._dontEscape = new BitArray(8);
            }
            if (this._size >= this._dontEscape.size()) {
                BitArray bitArray = this._dontEscape;
                bitArray.resize(bitArray.size() * 2);
            }
            this._dontEscape.setBit(this._size);
        }
        String[] strArr3 = this._textArray;
        int i4 = this._size;
        this._size = i4 + 1;
        strArr3[i4] = new String(cArr, i, i2);
    }

    public void copy(DTMAxisIterator dTMAxisIterator, SerializationHandler serializationHandler) throws TransletException {
        while (true) {
            int next = dTMAxisIterator.next();
            if (next == -1) {
                return;
            } else {
                copy(next, serializationHandler);
            }
        }
    }

    public void dispatchCharactersEvents(int i, ContentHandler contentHandler, boolean z) throws SAXException {
    }

    public void dispatchToEvents(int i, ContentHandler contentHandler) throws SAXException {
    }

    public void documentRegistration() {
    }

    public void documentRelease() {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        if (this._size == 1) {
            this._text = this._textArray[0];
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this._size; i++) {
            stringBuffer.append(this._textArray[i]);
        }
        this._text = stringBuffer.toString();
    }

    public int getAttributeNode(int i, int i2) {
        return -1;
    }

    public DTMAxisIterator getAxisIterator(int i) {
        if (i != 0) {
            if (i == 1) {
                return new SimpleIterator(0).includeSelf();
            }
            if (i == 3 || i == 4) {
                return new SimpleIterator(1);
            }
            if (i == 5) {
                return new SimpleIterator(1).includeSelf();
            }
            if (i != 10) {
                return i != 13 ? EMPTY_ITERATOR : new SingletonIterator();
            }
        }
        return new SimpleIterator(0);
    }

    public DTMAxisTraverser getAxisTraverser(int i) {
        return null;
    }

    public DTMAxisIterator getChildren(int i) {
        return new SimpleIterator().setStartNode(i);
    }

    public ContentHandler getContentHandler() {
        return null;
    }

    public DTDHandler getDTDHandler() {
        return null;
    }

    public DTMManagerDefault getDTMManager() {
        return this._dtmManager;
    }

    public DeclHandler getDeclHandler() {
        return null;
    }

    public int getDocument() {
        return this._documentID;
    }

    public boolean getDocumentAllDeclarationsProcessed() {
        return false;
    }

    public String getDocumentBaseURI() {
        return "";
    }

    public String getDocumentEncoding(int i) {
        return null;
    }

    public int getDocumentRoot(int i) {
        return getDocument();
    }

    public String getDocumentStandalone(int i) {
        return null;
    }

    public String getDocumentSystemIdentifier(int i) {
        return null;
    }

    public String getDocumentTypeDeclarationPublicIdentifier() {
        return null;
    }

    public String getDocumentTypeDeclarationSystemIdentifier() {
        return null;
    }

    public String getDocumentURI(int i) {
        StringBuilder sb = new StringBuilder("simple_rtf");
        int i2 = _documentURIIndex;
        _documentURIIndex = i2 + 1;
        sb.append(i2);
        return sb.toString();
    }

    public String getDocumentVersion(int i) {
        return null;
    }

    public int getElementById(String str) {
        return -1;
    }

    public Map<String, Integer> getElementsWithIDs() {
        return null;
    }

    public EntityResolver getEntityResolver() {
        return null;
    }

    public ErrorHandler getErrorHandler() {
        return null;
    }

    public int getExpandedTypeID(int i) {
        int nodeIdent = getNodeIdent(i);
        if (nodeIdent == 1) {
            return 3;
        }
        return nodeIdent == 0 ? 0 : -1;
    }

    public int getFirstAttribute(int i) {
        return -1;
    }

    public int getFirstChild(int i) {
        if (getNodeIdent(i) == 0) {
            return getNodeHandle(1);
        }
        return -1;
    }

    public int getFirstNamespaceNode(int i, boolean z) {
        return -1;
    }

    public DTMAxisIterator getIterator() {
        return new SingletonIterator(getDocument());
    }

    public String getLanguage(int i) {
        return null;
    }

    public int getLastChild(int i) {
        return getFirstChild(i);
    }

    public short getLevel(int i) {
        int nodeIdent = getNodeIdent(i);
        if (nodeIdent == 1) {
            return (short) 2;
        }
        return nodeIdent == 0 ? (short) 1 : (short) -1;
    }

    public LexicalHandler getLexicalHandler() {
        return null;
    }

    public String getLocalName(int i) {
        return "";
    }

    public String getLocalNameFromExpandedNameID(int i) {
        return "";
    }

    public int getNSType(int i) {
        return 0;
    }

    public DTMAxisIterator getNamespaceAxisIterator(int i, int i2) {
        return null;
    }

    public String getNamespaceFromExpandedNameID(int i) {
        return "";
    }

    public String getNamespaceName(int i) {
        return "";
    }

    public int getNamespaceType(int i) {
        return 0;
    }

    public String getNamespaceURI(int i) {
        return "";
    }

    public int getNextAttribute(int i) {
        return -1;
    }

    public int getNextNamespaceNode(int i, int i2, boolean z) {
        return -1;
    }

    public int getNextSibling(int i) {
        return -1;
    }

    public Node getNode(int i) {
        return makeNode(i);
    }

    public int getNodeHandle(int i) {
        if (i != -1) {
            return i + this._documentID;
        }
        return -1;
    }

    public int getNodeIdent(int i) {
        if (i != -1) {
            return i - this._documentID;
        }
        return -1;
    }

    public String getNodeName(int i) {
        return getNodeIdent(i) == 1 ? PsuedoNames.PSEUDONAME_TEXT : "";
    }

    public String getNodeNameX(int i) {
        return "";
    }

    public short getNodeType(int i) {
        int nodeIdent = getNodeIdent(i);
        if (nodeIdent == 1) {
            return (short) 3;
        }
        return nodeIdent == 0 ? (short) 0 : (short) -1;
    }

    public String getNodeValue(int i) {
        if (getNodeIdent(i) == 1) {
            return this._text;
        }
        return null;
    }

    public DTMAxisIterator getNodeValueIterator(DTMAxisIterator dTMAxisIterator, int i, String str, boolean z) {
        return null;
    }

    public DTMAxisIterator getNthDescendant(int i, int i2, boolean z) {
        return null;
    }

    public SerializationHandler getOutputDomBuilder() {
        return this;
    }

    public int getOwnerDocument(int i) {
        return getDocument();
    }

    public int getParent(int i) {
        if (getNodeIdent(i) == 1) {
            return getNodeHandle(0);
        }
        return -1;
    }

    public String getPrefix(int i) {
        return null;
    }

    public int getPreviousSibling(int i) {
        return -1;
    }

    public DOM getResultTreeFrag(int i, int i2) {
        return null;
    }

    public int getSize() {
        return 2;
    }

    public SourceLocator getSourceLocatorFor(int i) {
        return null;
    }

    public XMLString getStringValue(int i) {
        return new XMLStringDefault(getStringValueX(i));
    }

    public char[] getStringValueChunk(int i, int i2, int[] iArr) {
        return null;
    }

    public int getStringValueChunkCount(int i) {
        return 0;
    }

    public String getStringValueX(int i) {
        int nodeIdent = getNodeIdent(i);
        return (nodeIdent == 0 || nodeIdent == 1) ? this._text : "";
    }

    public DTMAxisIterator getTypedAxisIterator(int i, int i2) {
        if (i != 0) {
            if (i == 1) {
                return new SimpleIterator(0, i2).includeSelf();
            }
            if (i == 3 || i == 4) {
                return new SimpleIterator(1, i2);
            }
            if (i == 5) {
                return new SimpleIterator(1, i2).includeSelf();
            }
            if (i != 10) {
                return i != 13 ? EMPTY_ITERATOR : new SingletonIterator(i2);
            }
        }
        return new SimpleIterator(0, i2);
    }

    public DTMAxisIterator getTypedChildren(int i) {
        return new SimpleIterator(1, i);
    }

    public String getUnparsedEntityURI(String str) {
        return null;
    }

    public boolean hasChildNodes(int i) {
        return getNodeIdent(i) == 0;
    }

    public boolean isAttribute(int i) {
        return false;
    }

    public boolean isAttributeSpecified(int i) {
        return false;
    }

    public boolean isCharacterElementContentWhitespace(int i) {
        return false;
    }

    public boolean isDocumentAllDeclarationsProcessed(int i) {
        return false;
    }

    public boolean isElement(int i) {
        return false;
    }

    public boolean isNodeAfter(int i, int i2) {
        return lessThan(i, i2);
    }

    public boolean isSupported(String str, String str2) {
        return false;
    }

    public boolean lessThan(int i, int i2) {
        if (i == -1) {
            return false;
        }
        return i2 == -1 || i < i2;
    }

    public String lookupNamespace(int i, String str) throws TransletException {
        return null;
    }

    public Node makeNode(int i) {
        return null;
    }

    public NodeList makeNodeList(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void migrateTo(DTMManager dTMManager) {
    }

    public boolean needsTwoThreads() {
        return false;
    }

    public DTMAxisIterator orderNodes(DTMAxisIterator dTMAxisIterator, int i) {
        return dTMAxisIterator;
    }

    public void release() {
        if (this._documentID != 0) {
            this._dtmManager.release(this, true);
            this._documentID = 0;
        }
    }

    public void setDocumentBaseURI(String str) {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public boolean setEscaping(boolean z) throws SAXException {
        boolean z2 = this._escaping;
        this._escaping = z;
        return z2;
    }

    public void setFeature(String str, boolean z) {
    }

    public void setFilter(StripFilter stripFilter) {
    }

    public void setProperty(String str, Object obj) {
    }

    public void setupMapping(String[] strArr, String[] strArr2, int[] iArr, String[] strArr3) {
    }

    public String shallowCopy(int i, SerializationHandler serializationHandler) throws TransletException {
        characters(i, serializationHandler);
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
    }

    public boolean supportsPreStripping() {
        return false;
    }

    public int getAttributeNode(int i, String str, String str2) {
        return -1;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DOM getResultTreeFrag(int i, int i2, boolean z) {
        return null;
    }

    public Node makeNode(DTMAxisIterator dTMAxisIterator) {
        return null;
    }

    public NodeList makeNodeList(DTMAxisIterator dTMAxisIterator) {
        return null;
    }

    public final class SingletonIterator extends DTMAxisIteratorBase {
        static final int NO_TYPE = -1;
        int _currentNode;
        int _type;

        public SingletonIterator() {
            this._type = -1;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void gotoMark() {
            this._currentNode = this._markedNode;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            if (this._currentNode == -1) {
                return -1;
            }
            this._currentNode = -1;
            if (this._type != -1) {
                return -1;
            }
            return SimpleResultTreeImpl.this.getNodeHandle(-1);
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void setMark() {
            this._markedNode = this._currentNode;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            int nodeIdent = SimpleResultTreeImpl.this.getNodeIdent(i);
            this._startNode = nodeIdent;
            this._currentNode = nodeIdent;
            return this;
        }

        public SingletonIterator(int i) {
            this._type = i;
        }
    }

    public String getStringValue() {
        return this._text;
    }

    public final class SimpleIterator extends DTMAxisIteratorBase {
        static final int DIRECTION_DOWN = 1;
        static final int DIRECTION_UP = 0;
        static final int NO_TYPE = -1;
        int _currentNode;
        int _direction;
        int _type;

        public SimpleIterator() {
            this._direction = 1;
            this._type = -1;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void gotoMark() {
            this._currentNode = this._markedNode;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            if (this._direction == 1) {
                while (true) {
                    int i = this._currentNode;
                    if (i >= 2) {
                        return -1;
                    }
                    int i2 = this._type;
                    if (i2 == -1) {
                        SimpleResultTreeImpl simpleResultTreeImpl = SimpleResultTreeImpl.this;
                        this._currentNode = i + 1;
                        return returnNode(simpleResultTreeImpl.getNodeHandle(i));
                    }
                    if ((i == 0 && i2 == 0) || (i == 1 && i2 == 3)) {
                        SimpleResultTreeImpl simpleResultTreeImpl2 = SimpleResultTreeImpl.this;
                        this._currentNode = i + 1;
                        return returnNode(simpleResultTreeImpl2.getNodeHandle(i));
                    }
                    this._currentNode = i + 1;
                }
            } else {
                while (true) {
                    int i3 = this._currentNode;
                    if (i3 < 0) {
                        return -1;
                    }
                    int i4 = this._type;
                    if (i4 == -1) {
                        SimpleResultTreeImpl simpleResultTreeImpl3 = SimpleResultTreeImpl.this;
                        this._currentNode = i3 - 1;
                        return returnNode(simpleResultTreeImpl3.getNodeHandle(i3));
                    }
                    if ((i3 == 0 && i4 == 0) || (i3 == 1 && i4 == 3)) {
                        SimpleResultTreeImpl simpleResultTreeImpl4 = SimpleResultTreeImpl.this;
                        this._currentNode = i3 - 1;
                        return returnNode(simpleResultTreeImpl4.getNodeHandle(i3));
                    }
                    this._currentNode = i3 - 1;
                }
            }
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void setMark() {
            this._markedNode = this._currentNode;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            int nodeIdent = SimpleResultTreeImpl.this.getNodeIdent(i);
            this._startNode = nodeIdent;
            if (!this._includeSelf && nodeIdent != -1) {
                int i2 = this._direction;
                if (i2 == 1) {
                    nodeIdent++;
                } else if (i2 == 0) {
                    nodeIdent--;
                }
            }
            this._currentNode = nodeIdent;
            return this;
        }

        public SimpleIterator(int i) {
            this._type = -1;
            this._direction = i;
        }

        public SimpleIterator(int i, int i2) {
            this._direction = i;
            this._type = i2;
        }
    }

    public void copy(int i, SerializationHandler serializationHandler) throws TransletException {
        characters(i, serializationHandler);
    }

    public int getExpandedTypeID(String str, String str2, int i) {
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.EmptySerializer, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void characters(String str) throws SAXException {
        int i = this._size;
        String[] strArr = this._textArray;
        if (i >= strArr.length) {
            String[] strArr2 = new String[strArr.length * 2];
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
            this._textArray = strArr2;
        }
        if (!this._escaping) {
            if (this._dontEscape == null) {
                this._dontEscape = new BitArray(8);
            }
            if (this._size >= this._dontEscape.size()) {
                BitArray bitArray = this._dontEscape;
                bitArray.resize(bitArray.size() * 2);
            }
            this._dontEscape.setBit(this._size);
        }
        String[] strArr3 = this._textArray;
        int i2 = this._size;
        this._size = i2 + 1;
        strArr3[i2] = str;
    }

    public void characters(int i, SerializationHandler serializationHandler) throws TransletException {
        int nodeIdent = getNodeIdent(i);
        if (nodeIdent == 0 || nodeIdent == 1) {
            boolean bit = false;
            boolean escaping = false;
            for (int i2 = 0; i2 < this._size; i2++) {
                try {
                    BitArray bitArray = this._dontEscape;
                    if (bitArray != null && (bit = bitArray.getBit(i2))) {
                        escaping = serializationHandler.setEscaping(false);
                    }
                    serializationHandler.characters(this._textArray[i2]);
                    if (bit) {
                        serializationHandler.setEscaping(escaping);
                    }
                } catch (SAXException e) {
                    throw new TransletException(e);
                }
            }
        }
    }
}
