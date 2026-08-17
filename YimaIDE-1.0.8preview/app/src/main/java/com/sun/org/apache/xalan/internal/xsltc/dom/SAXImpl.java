package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.DOMEnhancedForDTM;
import com.sun.org.apache.xalan.internal.xsltc.StripFilter;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import com.sun.org.apache.xml.internal.dtm.Axis;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xml.internal.dtm.DTMWSFilter;
import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIterNodeList;
import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase;
import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeProxy;
import com.sun.org.apache.xml.internal.dtm.ref.EmptyIterator;
import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import com.sun.org.apache.xml.internal.serializer.ToXMLSAXHandler;
import com.sun.org.apache.xml.internal.utils.SystemIDResolver;
import com.sun.org.apache.xml.internal.utils.XMLStringFactory;
import java.util.HashMap;
import java.util.Map;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class SAXImpl extends SAX2DTM2 implements DOMEnhancedForDTM, DOMBuilder {
    private static final String EMPTYSTRING = "";
    private static final String PRESERVE_STRING = "preserve";
    private static final String XMLSPACE_STRING = "xml:space";
    private static final String XML_PREFIX = "xml";
    private static final String XML_URI = "http://www.w3.org/XML/1998/namespace";
    private boolean _disableEscaping;
    private Document _document;
    private BitArray _dontEscape;
    private XSLTCDTMManager _dtmManager;
    private boolean _escaping;
    private boolean _hasDOMSource;
    private int _idx;
    private int _namesSize;
    private Map<Node, Integer> _node2Ids;
    private NodeList[] _nodeLists;
    private Node[] _nodes;
    private Map<Integer, Integer> _nsIndex;
    private boolean _preserve;
    private int _size;
    private int _textNodeToProcess;
    private int _uriCount;
    private int[] _xmlSpaceStack;
    private static final DTMAxisIterator EMPTYITERATOR = EmptyIterator.getInstance();
    private static int _documentURIIndex = 0;

    public final class NamespaceAttributeIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
        private final int _nsType;

        public NamespaceAttributeIterator(int i) {
            super();
            this._nsType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            int i2 = this._nsType;
            if (i == -1) {
                return -1;
            }
            int nextAttribute = SAXImpl.this.getNextAttribute(i);
            while (nextAttribute != -1 && SAXImpl.this.getNSType(nextAttribute) != i2) {
                nextAttribute = SAXImpl.this.getNextAttribute(nextAttribute);
            }
            this._currentNode = nextAttribute;
            return returnNode(i);
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = SAXImpl.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            int i2 = this._nsType;
            this._startNode = i;
            int firstAttribute = SAXImpl.this.getFirstAttribute(i);
            while (firstAttribute != -1 && SAXImpl.this.getNSType(firstAttribute) != i2) {
                firstAttribute = SAXImpl.this.getNextAttribute(firstAttribute);
            }
            this._currentNode = firstAttribute;
            return resetPosition();
        }
    }

    public final class NamespaceChildrenIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
        private final int _nsType;

        public NamespaceChildrenIterator(int i) {
            super();
            this._nsType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            if (i != -1) {
                SAXImpl sAXImpl = SAXImpl.this;
                int i_firstch = -2 == i ? sAXImpl._firstch(sAXImpl.makeNodeIdentity(this._startNode)) : sAXImpl._nextsib(i);
                while (i_firstch != -1) {
                    int iMakeNodeHandle = SAXImpl.this.makeNodeHandle(i_firstch);
                    if (SAXImpl.this.getNSType(iMakeNodeHandle) == this._nsType) {
                        this._currentNode = i_firstch;
                        return returnNode(iMakeNodeHandle);
                    }
                    i_firstch = SAXImpl.this._nextsib(i_firstch);
                }
            }
            return -1;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = SAXImpl.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            this._currentNode = i != -1 ? -2 : -1;
            return resetPosition();
        }
    }

    public final class NamespaceWildcardIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
        protected DTMAxisIterator m_baseIterator;
        protected int m_nsType;

        public NamespaceWildcardIterator(int i, int i2) {
            super();
            this.m_nsType = i2;
            if (i == 2) {
                this.m_baseIterator = SAXImpl.this.getAxisIterator(i);
            } else if (i != 9) {
                this.m_baseIterator = SAXImpl.this.getTypedAxisIterator(i, 1);
            } else {
                this.m_baseIterator = SAXImpl.this.getAxisIterator(i);
            }
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator cloneIterator() {
            try {
                DTMAxisIterator dTMAxisIteratorCloneIterator = this.m_baseIterator.cloneIterator();
                NamespaceWildcardIterator namespaceWildcardIterator = (NamespaceWildcardIterator) super.clone();
                namespaceWildcardIterator.m_baseIterator = dTMAxisIteratorCloneIterator;
                namespaceWildcardIterator.m_nsType = this.m_nsType;
                namespaceWildcardIterator._isRestartable = false;
                return namespaceWildcardIterator;
            } catch (CloneNotSupportedException e) {
                BasisLibrary.runTimeError(BasisLibrary.ITERATOR_CLONE_ERR, e.toString());
                return null;
            }
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.InternalAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void gotoMark() {
            this.m_baseIterator.gotoMark();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public boolean isReverse() {
            return this.m_baseIterator.isReverse();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int next;
            do {
                next = this.m_baseIterator.next();
                if (next == -1) {
                    return -1;
                }
            } while (SAXImpl.this.getNSType(next) != this.m_nsType);
            return returnNode(next);
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.InternalAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void setMark() {
            this.m_baseIterator.setMark();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (this._isRestartable) {
                this._startNode = i;
                this.m_baseIterator.setStartNode(i);
                resetPosition();
            }
            return this;
        }
    }

    public final class NodeValueIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
        private final boolean _isReverse;
        private boolean _op;
        private int _returnType;
        private DTMAxisIterator _source;
        private String _value;

        public NodeValueIterator(DTMAxisIterator dTMAxisIterator, int i, String str, boolean z) {
            super();
            this._source = dTMAxisIterator;
            this._returnType = i;
            this._value = str;
            this._op = z;
            this._isReverse = dTMAxisIterator.isReverse();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator cloneIterator() {
            try {
                NodeValueIterator nodeValueIterator = (NodeValueIterator) super.clone();
                nodeValueIterator._isRestartable = false;
                nodeValueIterator._source = this._source.cloneIterator();
                nodeValueIterator._value = this._value;
                nodeValueIterator._op = this._op;
                return nodeValueIterator.reset();
            } catch (CloneNotSupportedException e) {
                BasisLibrary.runTimeError(BasisLibrary.ITERATOR_CLONE_ERR, e.toString());
                return null;
            }
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.InternalAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void gotoMark() {
            this._source.gotoMark();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public boolean isReverse() {
            return this._isReverse;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int next;
            do {
                next = this._source.next();
                if (next == -1) {
                    return -1;
                }
            } while (this._value.equals(SAXImpl.this.getStringValueX(next)) != this._op);
            return this._returnType == 0 ? returnNode(next) : returnNode(SAXImpl.this.getParent(next));
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator reset() {
            this._source.reset();
            return resetPosition();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.InternalAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void setMark() {
            this._source.setMark();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void setRestartable(boolean z) {
            this._isRestartable = z;
            this._source.setRestartable(z);
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (!this._isRestartable) {
                return this;
            }
            DTMAxisIterator dTMAxisIterator = this._source;
            this._startNode = i;
            dTMAxisIterator.setStartNode(i);
            return resetPosition();
        }
    }

    public class TypedNamespaceIterator extends DTMDefaultBaseIterators.NamespaceIterator {
        private String _nsPrefix;

        public TypedNamespaceIterator(int i) {
            super();
            if (((DTMDefaultBase) SAXImpl.this).m_expandedNameTable != null) {
                this._nsPrefix = ((DTMDefaultBase) SAXImpl.this).m_expandedNameTable.getLocalName(i);
            }
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.NamespaceIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int next;
            String str = this._nsPrefix;
            if (str != null && str.length() != 0) {
                do {
                    next = super.next();
                    if (next != -1) {
                    }
                } while (this._nsPrefix.compareTo(SAXImpl.this.getLocalName(next)) != 0);
                return returnNode(next);
            }
            return -1;
        }
    }

    public SAXImpl(XSLTCDTMManager xSLTCDTMManager, Source source, int i, DTMWSFilter dTMWSFilter, XMLStringFactory xMLStringFactory, boolean z, int i2, boolean z2, boolean z3) {
        super(xSLTCDTMManager, source, i, dTMWSFilter, xMLStringFactory, z, i2, false, z2, z3);
        this._uriCount = 0;
        this._idx = 1;
        this._preserve = false;
        this._escaping = true;
        this._disableEscaping = false;
        this._textNodeToProcess = -1;
        this._namesSize = -1;
        this._nsIndex = new HashMap();
        this._dontEscape = null;
        this._node2Ids = null;
        this._hasDOMSource = false;
        this._dtmManager = xSLTCDTMManager;
        this._size = i2;
        int[] iArr = new int[i2 <= 64 ? 4 : 64];
        this._xmlSpaceStack = iArr;
        iArr[0] = 0;
        if (source instanceof DOMSource) {
            this._hasDOMSource = true;
            Node node = ((DOMSource) source).getNode();
            if (node instanceof Document) {
                this._document = (Document) node;
            } else {
                this._document = node.getOwnerDocument();
            }
            this._node2Ids = new HashMap();
        }
    }

    private final void copy(int i, SerializationHandler serializationHandler, boolean z) throws TransletException {
        boolean escaping;
        int iMakeNodeIdentity = makeNodeIdentity(i);
        int i_exptype2 = _exptype2(iMakeNodeIdentity);
        int i_exptype2Type = _exptype2Type(i_exptype2);
        try {
            if (i_exptype2Type != 0) {
                if (i_exptype2Type == 13) {
                    serializationHandler.namespaceAfterStartElement(getNodeNameX(i), getNodeValue(i));
                    return;
                }
                if (i_exptype2Type == 2) {
                    copyAttribute(iMakeNodeIdentity, i_exptype2, serializationHandler);
                    return;
                }
                if (i_exptype2Type == 3) {
                    BitArray bitArray = this._dontEscape;
                    boolean z2 = false;
                    if (bitArray != null) {
                        boolean bit = bitArray.getBit(getNodeIdent(i));
                        z2 = bit;
                        escaping = bit ? serializationHandler.setEscaping(false) : false;
                    } else {
                        escaping = false;
                    }
                    copyTextNode(iMakeNodeIdentity, serializationHandler);
                    if (z2) {
                        serializationHandler.setEscaping(escaping);
                        return;
                    }
                    return;
                }
                if (i_exptype2Type == 7) {
                    copyPI(i, serializationHandler);
                    return;
                }
                if (i_exptype2Type == 8) {
                    serializationHandler.comment(getStringValueX(i));
                    return;
                }
                if (i_exptype2Type != 9) {
                    if (i_exptype2Type != 1) {
                        String namespaceName = getNamespaceName(i);
                        if (namespaceName.length() != 0) {
                            serializationHandler.namespaceAfterStartElement(getPrefix(i), namespaceName);
                        }
                        serializationHandler.addAttribute(getNodeName(i), getNodeValue(i));
                        return;
                    }
                    String strCopyElement = copyElement(iMakeNodeIdentity, i_exptype2, serializationHandler);
                    copyNS(iMakeNodeIdentity, serializationHandler, !z);
                    copyAttributes(iMakeNodeIdentity, serializationHandler);
                    int i_firstch2 = _firstch2(iMakeNodeIdentity);
                    while (i_firstch2 != -1) {
                        copy(makeNodeHandle(i_firstch2), serializationHandler, true);
                        i_firstch2 = _nextsib2(i_firstch2);
                    }
                    serializationHandler.endElement(strCopyElement);
                    return;
                }
            }
            int i_firstch3 = _firstch2(iMakeNodeIdentity);
            while (i_firstch3 != -1) {
                copy(makeNodeHandle(i_firstch3), serializationHandler, true);
                i_firstch3 = _nextsib2(i_firstch3);
            }
        } catch (Exception e) {
            throw new TransletException(e);
        }
    }

    private void copyPI(int i, SerializationHandler serializationHandler) throws TransletException {
        try {
            serializationHandler.processingInstruction(getNodeName(i), getStringValueX(i));
        } catch (Exception e) {
            throw new TransletException(e);
        }
    }

    private void definePrefixAndUri(String str, String str2) throws SAXException {
        Integer numValueOf = Integer.valueOf(getIdForNamespace(str2));
        if (this._nsIndex.get(numValueOf) == null) {
            Map<Integer, Integer> map = this._nsIndex;
            int i = this._uriCount;
            this._uriCount = i + 1;
            map.put(numValueOf, Integer.valueOf(i));
        }
    }

    private short[] getMapping2(String[] strArr, String[] strArr2, int[] iArr) {
        int i;
        int length = strArr.length;
        int size = this.m_expandedNameTable.getSize();
        int[] iArr2 = length > 0 ? new int[length] : null;
        int i2 = size;
        for (int i3 = 0; i3 < length; i3++) {
            int expandedTypeID = this.m_expandedNameTable.getExpandedTypeID(strArr2[i3], strArr[i3], iArr[i3], false);
            iArr2[i3] = expandedTypeID;
            if (this._namesSize < 0 && expandedTypeID >= i2) {
                i2 = expandedTypeID + 1;
            }
        }
        short[] sArr = new short[i2];
        int i4 = 0;
        while (true) {
            if (i4 >= 14) {
                break;
            }
            sArr[i4] = (short) i4;
            i4++;
        }
        for (i = 14; i < size; i++) {
            sArr[i] = this.m_expandedNameTable.getType(i);
        }
        for (int i5 = 0; i5 < length; i5++) {
            int i6 = iArr2[i5];
            if (i6 >= 0 && i6 < i2) {
                sArr[i6] = (short) (i5 + 14);
            }
        }
        return sArr;
    }

    private void handleTextEscaping() {
        int i;
        if (this._disableEscaping && (i = this._textNodeToProcess) != -1 && _type(i) == 3) {
            if (this._dontEscape == null) {
                this._dontEscape = new BitArray(this._size);
            }
            if (this._textNodeToProcess >= this._dontEscape.size()) {
                BitArray bitArray = this._dontEscape;
                bitArray.resize(bitArray.size() * 2);
            }
            this._dontEscape.setBit(this._textNodeToProcess);
            this._disableEscaping = false;
        }
        this._textNodeToProcess = -1;
    }

    private void xmlSpaceDefine(String str, int i) {
        boolean zEquals = str.equals("preserve");
        if (zEquals != this._preserve) {
            int[] iArr = this._xmlSpaceStack;
            int i2 = this._idx;
            this._idx = i2 + 1;
            iArr[i2] = i;
            this._preserve = zEquals;
        }
    }

    private void xmlSpaceRevert(int i) {
        int[] iArr = this._xmlSpaceStack;
        int i2 = this._idx;
        if (i == iArr[i2 - 1]) {
            this._idx = i2 - 1;
            this._preserve = !this._preserve;
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        super.characters(cArr, i, i2);
        this._disableEscaping = !this._escaping;
        this._textNodeToProcess = getNumberOfNodes();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2, com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
        super.comment(cArr, i, i2);
        handleTextEscaping();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2, com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        super.endDocument();
        handleTextEscaping();
        this._namesSize = this.m_expandedNameTable.getSize();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2, com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        super.endElement(str, str2, str3);
        handleTextEscaping();
        if (this.m_wsfilter != null) {
            xmlSpaceRevert(this.m_previous);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getAttributeNode(int i, int i2) {
        int firstAttribute = getFirstAttribute(i2);
        while (firstAttribute != -1) {
            if (getExpandedTypeID(firstAttribute) == i) {
                return firstAttribute;
            }
            firstAttribute = getNextAttribute(firstAttribute);
        }
        return -1;
    }

    public String getAttributeValue(int i, int i2) {
        int attributeNode = getAttributeNode(i, i2);
        return attributeNode != -1 ? getStringValueX(attributeNode) : "";
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators, com.sun.org.apache.xml.internal.dtm.DTM
    public DTMAxisIterator getAxisIterator(int i) {
        if (i == 19) {
            return new DTMDefaultBaseIterators.RootIterator();
        }
        switch (i) {
            case 0:
                return new SAX2DTM2.AncestorIterator();
            case 1:
                return new SAX2DTM2.AncestorIterator().includeSelf();
            case 2:
                return new SAX2DTM2.AttributeIterator();
            case 3:
                return new SAX2DTM2.ChildrenIterator();
            case 4:
                return new SAX2DTM2.DescendantIterator();
            case 5:
                return new SAX2DTM2.DescendantIterator().includeSelf();
            case 6:
                return new SAX2DTM2.FollowingIterator();
            case 7:
                return new SAX2DTM2.FollowingSiblingIterator();
            default:
                switch (i) {
                    case 9:
                        return new DTMDefaultBaseIterators.NamespaceIterator();
                    case 10:
                        return new SAX2DTM2.ParentIterator();
                    case 11:
                        return new SAX2DTM2.PrecedingIterator();
                    case 12:
                        return new SAX2DTM2.PrecedingSiblingIterator();
                    case 13:
                        return new DTMDefaultBaseIterators.SingletonIterator(this);
                    default:
                        BasisLibrary.runTimeError(BasisLibrary.AXIS_SUPPORT_ERR, Axis.getNames(i));
                        return null;
                }
        }
    }

    public DOMBuilder getBuilder() {
        return this;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getChildren(int i) {
        return new SAX2DTM2.ChildrenIterator().setStartNode(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOMEnhancedForDTM
    public String getDocumentURI() {
        String documentBaseURI = getDocumentBaseURI();
        if (documentBaseURI != null) {
            return documentBaseURI;
        }
        StringBuilder sb = new StringBuilder("rtf");
        int i = _documentURIIndex;
        _documentURIIndex = i + 1;
        sb.append(i);
        return sb.toString();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public int getElementById(String str) {
        Integer num;
        Element elementById = this._document.getElementById(str);
        if (elementById == null || (num = this._node2Ids.get(elementById)) == null) {
            return -1;
        }
        return num.intValue();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public Map<String, Integer> getElementsWithIDs() {
        return this.m_idAttributes;
    }

    public int getGeneralizedType(String str, boolean z) {
        int i;
        int iLastIndexOf = str.lastIndexOf(":");
        String strSubstring = iLastIndexOf > -1 ? str.substring(0, iLastIndexOf) : null;
        int i2 = iLastIndexOf + 1;
        if (str.charAt(i2) == '@') {
            i2 = iLastIndexOf + 2;
            i = 2;
        } else {
            i = 1;
        }
        if (i2 != 0) {
            str = str.substring(i2);
        }
        return this.m_expandedNameTable.getExpandedTypeID(strSubstring, str, i, z);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getIterator() {
        return new DTMDefaultBaseIterators.SingletonIterator(getDocument(), true);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getLanguage(int i) {
        int attributeNode;
        while (-1 != i) {
            if (1 == getNodeType(i) && -1 != (attributeNode = getAttributeNode(i, "http://www.w3.org/XML/1998/namespace", "lang"))) {
                return getNodeValue(attributeNode);
            }
            i = getParent(i);
        }
        return null;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOMEnhancedForDTM
    public short[] getMapping(String[] strArr, String[] strArr2, int[] iArr) {
        int i;
        if (this._namesSize < 0) {
            return getMapping2(strArr, strArr2, iArr);
        }
        int length = strArr.length;
        int size = this.m_expandedNameTable.getSize();
        short[] sArr = new short[size];
        int i2 = 0;
        while (true) {
            if (i2 >= 14) {
                break;
            }
            sArr[i2] = (short) i2;
            i2++;
        }
        for (i = 14; i < size; i++) {
            sArr[i] = this.m_expandedNameTable.getType(i);
        }
        for (int i3 = 0; i3 < length; i3++) {
            int expandedTypeID = this.m_expandedNameTable.getExpandedTypeID(strArr2[i3], strArr[i3], iArr[i3], true);
            if (expandedTypeID >= 0 && expandedTypeID < size) {
                sArr[expandedTypeID] = (short) (i3 + 14);
            }
        }
        return sArr;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getNSType(int i) {
        String namespaceURI = getNamespaceURI(i);
        if (namespaceURI == null) {
            return 0;
        }
        return this._nsIndex.get(Integer.valueOf(getIdForNamespace(namespaceURI))).intValue();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getNamespaceAxisIterator(int i, int i2) {
        if (i2 == -1) {
            return EMPTYITERATOR;
        }
        if (i != 2) {
            return i != 3 ? new NamespaceWildcardIterator(i, i2) : new NamespaceChildrenIterator(i2);
        }
        return new NamespaceAttributeIterator(i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOMEnhancedForDTM
    public short[] getNamespaceMapping(String[] strArr) {
        int length = strArr.length;
        int i = this._uriCount;
        short[] sArr = new short[i];
        for (int i2 = 0; i2 < i; i2++) {
            sArr[i2] = -1;
        }
        for (int i3 = 0; i3 < length; i3++) {
            Integer num = this._nsIndex.get(Integer.valueOf(getIdForNamespace(strArr[i3])));
            if (num != null) {
                sArr[num.intValue()] = (short) i3;
            }
        }
        return sArr;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getNamespaceName(int i) {
        String namespaceURI;
        return (i == -1 || (namespaceURI = getNamespaceURI(i)) == null) ? "" : namespaceURI;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getNamespaceType(int i) {
        return super.getNamespaceType(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2, com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeName(int i) {
        short nodeType = getNodeType(i);
        if (nodeType == 0 || nodeType == 3) {
            return "";
        }
        if (nodeType != 13) {
            return (nodeType == 8 || nodeType == 9) ? "" : super.getNodeName(i);
        }
        return getLocalName(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getNodeValueIterator(DTMAxisIterator dTMAxisIterator, int i, String str, boolean z) {
        return new NodeValueIterator(dTMAxisIterator, i, str, z);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getNthDescendant(int i, int i2, boolean z) {
        return new DTMDefaultBaseIterators.NthDescendantIterator(i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public SerializationHandler getOutputDomBuilder() {
        return new ToXMLSAXHandler(this, "UTF-8");
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DOM getResultTreeFrag(int i, int i2, boolean z) {
        if (i2 == 0) {
            if (!z) {
                return new SimpleResultTreeImpl(this._dtmManager, 0);
            }
            int firstFreeDTMID = this._dtmManager.getFirstFreeDTMID();
            SimpleResultTreeImpl simpleResultTreeImpl = new SimpleResultTreeImpl(this._dtmManager, firstFreeDTMID << 16);
            this._dtmManager.addDTM(simpleResultTreeImpl, firstFreeDTMID, 0);
            return simpleResultTreeImpl;
        }
        if (i2 != 1) {
            return (DOM) this._dtmManager.getDTM(null, true, this.m_wsfilter, true, false, false, i, this.m_buildIdIndex);
        }
        if (!z) {
            return new AdaptiveResultTreeImpl(this._dtmManager, 0, this.m_wsfilter, i, this.m_buildIdIndex);
        }
        int firstFreeDTMID2 = this._dtmManager.getFirstFreeDTMID();
        AdaptiveResultTreeImpl adaptiveResultTreeImpl = new AdaptiveResultTreeImpl(this._dtmManager, firstFreeDTMID2 << 16, this.m_wsfilter, i, this.m_buildIdIndex);
        this._dtmManager.addDTM(adaptiveResultTreeImpl, firstFreeDTMID2, 0);
        return adaptiveResultTreeImpl;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOMEnhancedForDTM
    public int[] getReverseMapping(String[] strArr, String[] strArr2, int[] iArr) {
        int[] iArr2 = new int[strArr.length + 14];
        for (int i = 0; i < 14; i++) {
            iArr2[i] = i;
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            iArr2[i2 + 14] = this.m_expandedNameTable.getExpandedTypeID(strArr2[i2], strArr[i2], iArr[i2], true);
        }
        return iArr2;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOMEnhancedForDTM
    public short[] getReverseNamespaceMapping(String[] strArr) {
        int length = strArr.length;
        short[] sArr = new short[length];
        for (int i = 0; i < length; i++) {
            Integer num = this._nsIndex.get(Integer.valueOf(getIdForNamespace(strArr[i])));
            sArr[i] = num == null ? (short) -1 : num.shortValue();
        }
        return sArr;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase
    public boolean getShouldStripWhitespace() {
        if (this._preserve) {
            return false;
        }
        return super.getShouldStripWhitespace();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getSize() {
        return getNumberOfNodes();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators, com.sun.org.apache.xml.internal.dtm.DTM
    public DTMAxisIterator getTypedAxisIterator(int i, int i2) {
        if (i == 3) {
            return new SAX2DTM2.TypedChildrenIterator(i2);
        }
        if (i2 == -1) {
            return EMPTYITERATOR;
        }
        if (i == 19) {
            return new SAX2DTM2.TypedRootIterator(i2);
        }
        switch (i) {
            case 0:
                return new SAX2DTM2.TypedAncestorIterator(i2);
            case 1:
                return new SAX2DTM2.TypedAncestorIterator(i2).includeSelf();
            case 2:
                return new SAX2DTM2.TypedAttributeIterator(i2);
            case 3:
                return new SAX2DTM2.TypedChildrenIterator(i2);
            case 4:
                return new SAX2DTM2.TypedDescendantIterator(i2);
            case 5:
                return new SAX2DTM2.TypedDescendantIterator(i2).includeSelf();
            case 6:
                return new SAX2DTM2.TypedFollowingIterator(i2);
            case 7:
                return new SAX2DTM2.TypedFollowingSiblingIterator(i2);
            default:
                switch (i) {
                    case 9:
                        return new TypedNamespaceIterator(i2);
                    case 10:
                        return new SAX2DTM2.ParentIterator().setNodeType(i2);
                    case 11:
                        return new SAX2DTM2.TypedPrecedingIterator(i2);
                    case 12:
                        return new SAX2DTM2.TypedPrecedingSiblingIterator(i2);
                    case 13:
                        return new SAX2DTM2.TypedSingletonIterator(i2);
                    default:
                        BasisLibrary.runTimeError(BasisLibrary.TYPED_AXIS_SUPPORT_ERR, Axis.getNames(i));
                        return null;
                }
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getTypedChildren(int i) {
        return new SAX2DTM2.TypedChildrenIterator(i);
    }

    public DTMAxisIterator getTypedDescendantIterator(int i) {
        return new SAX2DTM2.TypedDescendantIterator(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getUnparsedEntityURI(String str) {
        NamedNodeMap entities;
        Entity entity;
        Document document = this._document;
        if (document == null) {
            return super.getUnparsedEntityURI(str);
        }
        DocumentType doctype = document.getDoctype();
        if (doctype == null || (entities = doctype.getEntities()) == null || (entity = (Entity) entities.getNamedItem(str)) == null || entity.getNotationName() == null) {
            return "";
        }
        String systemId = entity.getSystemId();
        return systemId == null ? entity.getPublicId() : systemId;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOMEnhancedForDTM
    public boolean hasDOMSource() {
        return this._hasDOMSource;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
        super.ignorableWhitespace(cArr, i, i2);
        this._textNodeToProcess = getNumberOfNodes();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public boolean isAttribute(int i) {
        return getNodeType(i) == 2;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public boolean isElement(int i) {
        return getNodeType(i) == 1;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public boolean lessThan(int i, int i2) {
        if (i == -1) {
            return false;
        }
        return i2 == -1 || i < i2;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String lookupNamespace(int i, String str) throws TransletException {
        int next;
        SAX2DTM2.AncestorIterator ancestorIterator = new SAX2DTM2.AncestorIterator();
        if (isElement(i)) {
            ancestorIterator.includeSelf();
        }
        ancestorIterator.setStartNode(i);
        while (true) {
            int next2 = ancestorIterator.next();
            if (next2 == -1) {
                BasisLibrary.runTimeError(BasisLibrary.NAMESPACE_PREFIX_ERR, str);
                return null;
            }
            DTMDefaultBaseIterators.NamespaceIterator namespaceIterator = new DTMDefaultBaseIterators.NamespaceIterator();
            namespaceIterator.setStartNode(next2);
            do {
                next = namespaceIterator.next();
                if (next != -1) {
                }
            } while (!getLocalName(next).equals(str));
            return getNodeValue(next);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public Node makeNode(int i) {
        if (this._nodes == null) {
            this._nodes = new Node[this._namesSize];
        }
        int iMakeNodeIdentity = makeNodeIdentity(i);
        if (iMakeNodeIdentity < 0) {
            return null;
        }
        Node[] nodeArr = this._nodes;
        if (iMakeNodeIdentity >= nodeArr.length) {
            return new DTMNodeProxy(this, i);
        }
        Node node = nodeArr[iMakeNodeIdentity];
        if (node != null) {
            return node;
        }
        DTMNodeProxy dTMNodeProxy = new DTMNodeProxy(this, i);
        nodeArr[iMakeNodeIdentity] = dTMNodeProxy;
        return dTMNodeProxy;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public NodeList makeNodeList(int i) {
        if (this._nodeLists == null) {
            this._nodeLists = new NodeList[this._namesSize];
        }
        int iMakeNodeIdentity = makeNodeIdentity(i);
        if (iMakeNodeIdentity < 0) {
            return null;
        }
        NodeList[] nodeListArr = this._nodeLists;
        if (iMakeNodeIdentity >= nodeListArr.length) {
            return new DTMAxisIterNodeList(this, new DTMDefaultBaseIterators.SingletonIterator(this, i));
        }
        NodeList nodeList = nodeListArr[iMakeNodeIdentity];
        if (nodeList != null) {
            return nodeList;
        }
        DTMAxisIterNodeList dTMAxisIterNodeList = new DTMAxisIterNodeList(this, new DTMDefaultBaseIterators.SingletonIterator(this, i));
        nodeListArr[iMakeNodeIdentity] = dTMAxisIterNodeList;
        return dTMAxisIterNodeList;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public void migrateTo(DTMManager dTMManager) {
        super.migrateTo(dTMManager);
        if (dTMManager instanceof XSLTCDTMManager) {
            this._dtmManager = (XSLTCDTMManager) dTMManager;
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator orderNodes(DTMAxisIterator dTMAxisIterator, int i) {
        return new DupFilterIterator(dTMAxisIterator);
    }

    public void print(int i, int i2) {
        short nodeType = getNodeType(i);
        if (nodeType != 0) {
            if (nodeType == 3 || nodeType == 7 || nodeType == 8) {
                System.out.print(getStringValueX(i));
                return;
            }
            if (nodeType != 9) {
                String nodeName = getNodeName(i);
                System.out.print("<" + nodeName);
                int firstAttribute = getFirstAttribute(i);
                while (firstAttribute != -1) {
                    System.out.print("\n" + getNodeName(firstAttribute) + "=\"" + getStringValueX(firstAttribute) + "\"");
                    firstAttribute = getNextAttribute(firstAttribute);
                }
                System.out.print('>');
                int firstChild = getFirstChild(i);
                while (firstChild != -1) {
                    print(firstChild, i2 + 1);
                    firstChild = getNextSibling(firstChild);
                }
                System.out.println("</" + nodeName + '>');
                return;
            }
        }
        print(getFirstChild(i), i2);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2, com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        super.processingInstruction(str, str2);
        handleTextEscaping();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void release() {
        this._dtmManager.release(this, true);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOMEnhancedForDTM
    public void setDocumentURI(String str) {
        if (str != null) {
            setDocumentBaseURI(SystemIDResolver.getAbsoluteURI(str));
        }
    }

    public boolean setEscaping(boolean z) {
        boolean z2 = this._escaping;
        this._escaping = z;
        return z2;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void setFilter(StripFilter stripFilter) {
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void setupMapping(String[] strArr, String[] strArr2, int[] iArr, String[] strArr3) {
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String shallowCopy(int i, SerializationHandler serializationHandler) throws TransletException {
        int iMakeNodeIdentity = makeNodeIdentity(i);
        int i_exptype2 = _exptype2(iMakeNodeIdentity);
        int i_exptype2Type = _exptype2Type(i_exptype2);
        if (i_exptype2Type == 0) {
            return "";
        }
        try {
            if (i_exptype2Type == 1) {
                String strCopyElement = copyElement(iMakeNodeIdentity, i_exptype2, serializationHandler);
                copyNS(iMakeNodeIdentity, serializationHandler, true);
                return strCopyElement;
            }
            if (i_exptype2Type == 2) {
                copyAttribute(iMakeNodeIdentity, i_exptype2, serializationHandler);
                return null;
            }
            if (i_exptype2Type == 3) {
                copyTextNode(iMakeNodeIdentity, serializationHandler);
                return null;
            }
            if (i_exptype2Type == 7) {
                copyPI(i, serializationHandler);
                return null;
            }
            if (i_exptype2Type == 8) {
                serializationHandler.comment(getStringValueX(i));
                return null;
            }
            if (i_exptype2Type == 9) {
                return "";
            }
            if (i_exptype2Type == 13) {
                serializationHandler.namespaceAfterStartElement(getNodeNameX(i), getNodeValue(i));
                return null;
            }
            String namespaceName = getNamespaceName(i);
            if (namespaceName.length() != 0) {
                serializationHandler.namespaceAfterStartElement(getPrefix(i), namespaceName);
            }
            serializationHandler.addAttribute(getNodeName(i), getNodeValue(i));
            return null;
        } catch (Exception e) {
            throw new TransletException(e);
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2, com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        super.startDocument();
        Map<Integer, Integer> map = this._nsIndex;
        int i = this._uriCount;
        this._uriCount = i + 1;
        map.put(0, Integer.valueOf(i));
        definePrefixAndUri("xml", "http://www.w3.org/XML/1998/namespace");
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2, com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        int index;
        super.startElement(str, str2, str3, attributes);
        handleTextEscaping();
        if (this.m_wsfilter == null || (index = attributes.getIndex("xml:space")) < 0) {
            return;
        }
        xmlSpaceDefine(attributes.getValue(index), this.m_parents.peek());
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) throws SAXException {
        super.startPrefixMapping(str, str2);
        handleTextEscaping();
        definePrefixAndUri(str, str2);
    }

    public String getAttributeValue(String str, int i) {
        return getAttributeValue(getGeneralizedType(str), i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void characters(int i, SerializationHandler serializationHandler) throws TransletException {
        if (i != -1) {
            try {
                dispatchCharactersEvents(i, serializationHandler, false);
            } catch (SAXException e) {
                throw new TransletException(e);
            }
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getDocumentURI(int i) {
        return getDocumentURI();
    }

    public void startElement(String str, String str2, String str3, Attributes attributes, Node node) throws SAXException {
        startElement(str, str2, str3, attributes);
        if (this.m_buildIdIndex) {
            this._node2Ids.put(node, Integer.valueOf(this.m_parents.peek()));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public Node makeNode(DTMAxisIterator dTMAxisIterator) {
        return makeNode(dTMAxisIterator.next());
    }

    public int getGeneralizedType(String str) {
        return getGeneralizedType(str, true);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public NodeList makeNodeList(DTMAxisIterator dTMAxisIterator) {
        return new DTMAxisIterNodeList(this, dTMAxisIterator);
    }

    public SAXImpl(XSLTCDTMManager xSLTCDTMManager, Source source, int i, DTMWSFilter dTMWSFilter, XMLStringFactory xMLStringFactory, boolean z, boolean z2) {
        this(xSLTCDTMManager, source, i, dTMWSFilter, xMLStringFactory, z, 512, z2, false);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DOM getResultTreeFrag(int i, int i2) {
        return getResultTreeFrag(i, i2, true);
    }

    public void copy(SerializationHandler serializationHandler) throws TransletException {
        copy(getDocument(), serializationHandler);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void copy(int i, SerializationHandler serializationHandler) throws TransletException {
        copy(i, serializationHandler, false);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
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
}
