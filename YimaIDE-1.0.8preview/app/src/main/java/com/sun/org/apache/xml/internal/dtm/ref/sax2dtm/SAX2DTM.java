package com.sun.org.apache.xml.internal.dtm.ref.sax2dtm;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xml.internal.dtm.DTMWSFilter;
import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators;
import com.sun.org.apache.xml.internal.dtm.ref.DTMManagerDefault;
import com.sun.org.apache.xml.internal.dtm.ref.DTMStringPool;
import com.sun.org.apache.xml.internal.dtm.ref.DTMTreeWalker;
import com.sun.org.apache.xml.internal.dtm.ref.ExpandedNameTable;
import com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource;
import com.sun.org.apache.xml.internal.dtm.ref.NodeLocator;
import com.sun.org.apache.xml.internal.res.XMLMessages;
import com.sun.org.apache.xml.internal.utils.FastStringBuffer;
import com.sun.org.apache.xml.internal.utils.IntStack;
import com.sun.org.apache.xml.internal.utils.IntVector;
import com.sun.org.apache.xml.internal.utils.StringVector;
import com.sun.org.apache.xml.internal.utils.SuballocatedIntVector;
import com.sun.org.apache.xml.internal.utils.SystemIDResolver;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xml.internal.utils.XMLStringFactory;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import defpackage.x73;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.xml.transform.Source;
import javax.xml.transform.SourceLocator;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SAX2DTM extends DTMDefaultBaseIterators implements EntityResolver, DTDHandler, ContentHandler, ErrorHandler, DeclHandler, LexicalHandler {
    private static final boolean DEBUG = false;
    private static final int ENTITY_FIELDS_PER = 4;
    private static final int ENTITY_FIELD_NAME = 3;
    private static final int ENTITY_FIELD_NOTATIONNAME = 2;
    private static final int ENTITY_FIELD_PUBLICID = 0;
    private static final int ENTITY_FIELD_SYSTEMID = 1;
    private static final String[] m_fixednames = {null, null, null, PsuedoNames.PSEUDONAME_TEXT, "#cdata_section", null, null, null, PsuedoNames.PSEUDONAME_COMMENT, "#document", null, "#document-fragment", null};
    protected FastStringBuffer m_chars;
    protected transient int m_coalescedTextType;
    protected transient IntStack m_contextIndexes;
    protected SuballocatedIntVector m_data;
    protected SuballocatedIntVector m_dataOrQName;
    protected boolean m_endDocumentOccured;
    private List<String> m_entities;
    protected Map<String, Integer> m_idAttributes;
    private IncrementalSAXSource m_incrementalSAXSource;
    protected transient boolean m_insideDTD;
    protected transient Locator m_locator;
    protected transient IntStack m_parents;
    boolean m_pastFirstElement;
    protected transient Vector<String> m_prefixMappings;
    protected transient int m_previous;
    protected IntVector m_sourceColumn;
    protected IntVector m_sourceLine;
    protected StringVector m_sourceSystemId;
    private transient String m_systemId;
    protected int m_textPendingStart;
    protected transient int m_textType;
    protected boolean m_useSourceLocationProperty;
    protected DTMStringPool m_valuesOrPrefixes;
    protected DTMTreeWalker m_walker;

    public SAX2DTM(DTMManager dTMManager, Source source, int i, DTMWSFilter dTMWSFilter, XMLStringFactory xMLStringFactory, boolean z, int i2, boolean z2, boolean z3) {
        super(dTMManager, source, i, dTMWSFilter, xMLStringFactory, z, i2, z2, z3);
        this.m_incrementalSAXSource = null;
        this.m_previous = 0;
        this.m_prefixMappings = new Vector<>();
        this.m_textType = 3;
        this.m_coalescedTextType = 3;
        this.m_locator = null;
        this.m_systemId = null;
        this.m_insideDTD = false;
        this.m_walker = new DTMTreeWalker();
        this.m_endDocumentOccured = false;
        this.m_idAttributes = new HashMap();
        this.m_entities = null;
        this.m_textPendingStart = -1;
        this.m_useSourceLocationProperty = false;
        this.m_pastFirstElement = false;
        if (i2 <= 64) {
            this.m_data = new SuballocatedIntVector(i2, 4);
            this.m_dataOrQName = new SuballocatedIntVector(i2, 4);
            this.m_valuesOrPrefixes = new DTMStringPool(16);
            this.m_chars = new FastStringBuffer(7, 10);
            this.m_contextIndexes = new IntStack(4);
            this.m_parents = new IntStack(4);
        } else {
            this.m_data = new SuballocatedIntVector(i2, 32);
            this.m_dataOrQName = new SuballocatedIntVector(i2, 32);
            this.m_valuesOrPrefixes = new DTMStringPool();
            this.m_chars = new FastStringBuffer(10, 13);
            this.m_contextIndexes = new IntStack();
            this.m_parents = new IntStack();
        }
        this.m_data.addElement(0);
        boolean source_location = dTMManager.getSource_location();
        this.m_useSourceLocationProperty = source_location;
        this.m_sourceSystemId = source_location ? new StringVector() : null;
        this.m_sourceLine = this.m_useSourceLocationProperty ? new IntVector() : null;
        this.m_sourceColumn = this.m_useSourceLocationProperty ? new IntVector() : null;
    }

    private final boolean isTextType(int i) {
        return 3 == i || 4 == i;
    }

    public int _dataOrQName(int i) {
        if (i < this.m_size) {
            return this.m_dataOrQName.elementAt(i);
        }
        while (nextNode()) {
            if (i < this.m_size) {
                return this.m_dataOrQName.elementAt(i);
            }
        }
        return -1;
    }

    public void addNewDTMID(int i) {
        try {
            DTMManager dTMManager = this.m_mgr;
            if (dTMManager == null) {
                throw new ClassCastException();
            }
            DTMManagerDefault dTMManagerDefault = (DTMManagerDefault) dTMManager;
            int firstFreeDTMID = dTMManagerDefault.getFirstFreeDTMID();
            dTMManagerDefault.addDTM(this, firstFreeDTMID, i);
            this.m_dtmIdent.addElement(firstFreeDTMID << 16);
        } catch (ClassCastException unused) {
            error(XMLMessages.createXMLMessage("ER_NO_DTMIDS_AVAIL", null));
        }
    }

    public int addNode(int i, int i2, int i3, int i4, int i5, boolean z) {
        int i6 = this.m_size;
        this.m_size = i6 + 1;
        if (this.m_dtmIdent.size() == (i6 >>> 16)) {
            addNewDTMID(i6);
        }
        this.m_firstch.addElement(z ? -2 : -1);
        this.m_nextsib.addElement(-2);
        this.m_parent.addElement(i3);
        this.m_exptype.addElement(i2);
        this.m_dataOrQName.addElement(i5);
        SuballocatedIntVector suballocatedIntVector = this.m_prevsib;
        if (suballocatedIntVector != null) {
            suballocatedIntVector.addElement(i4);
        }
        if (-1 != i4) {
            this.m_nextsib.setElementAt(i6, i4);
        }
        if (this.m_locator != null && this.m_useSourceLocationProperty) {
            setSourceLocation();
        }
        if (i != 2) {
            if (i == 13) {
                declareNamespaceInContext(i3, i6);
            } else if (-1 == i4 && -1 != i3) {
                this.m_firstch.setElementAt(i6, i3);
                return i6;
            }
        }
        return i6;
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void attributeDecl(String str, String str2, String str3, String str4, String str5) throws SAXException {
    }

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        if (this.m_textPendingStart == -1) {
            this.m_textPendingStart = this.m_chars.size();
            this.m_coalescedTextType = this.m_textType;
        } else if (this.m_textType == 3) {
            this.m_coalescedTextType = 3;
        }
        this.m_chars.append(cArr, i, i2);
    }

    public void charactersFlush() {
        SAX2DTM sax2dtm;
        if (this.m_textPendingStart >= 0) {
            int size = this.m_chars.size() - this.m_textPendingStart;
            if (!(getShouldStripWhitespace() ? this.m_chars.isWhitespace(this.m_textPendingStart, size) : false)) {
                if (size > 0) {
                    sax2dtm = this;
                    sax2dtm.m_previous = sax2dtm.addNode(this.m_coalescedTextType, this.m_expandedNameTable.getExpandedTypeID(3), this.m_parents.peek(), this.m_previous, this.m_data.size(), false);
                    sax2dtm.m_data.addElement(sax2dtm.m_textPendingStart);
                    sax2dtm.m_data.addElement(size);
                }
                sax2dtm.m_textPendingStart = -1;
                sax2dtm.m_coalescedTextType = 3;
                sax2dtm.m_textType = 3;
            }
            this.m_chars.setLength(this.m_textPendingStart);
            sax2dtm = this;
            sax2dtm.m_textPendingStart = -1;
            sax2dtm.m_coalescedTextType = 3;
            sax2dtm.m_textType = 3;
        }
    }

    public void clearCoRoutine(boolean z) {
        IncrementalSAXSource incrementalSAXSource = this.m_incrementalSAXSource;
        if (incrementalSAXSource != null) {
            if (z) {
                incrementalSAXSource.deliverMoreNodes(false);
            }
            this.m_incrementalSAXSource = null;
        }
    }

    public void comment(char[] cArr, int i, int i2) throws SAXException {
        if (this.m_insideDTD) {
            return;
        }
        charactersFlush();
        this.m_previous = addNode(8, this.m_expandedNameTable.getExpandedTypeID(8), this.m_parents.peek(), this.m_previous, this.m_valuesOrPrefixes.stringToIndex(new String(cArr, i, i2)), false);
    }

    public boolean declAlreadyDeclared(String str) {
        Vector<String> vector = this.m_prefixMappings;
        int size = vector.size();
        for (int iPeek = this.m_contextIndexes.peek(); iPeek < size; iPeek += 2) {
            String str2 = vector.get(iPeek);
            if (str2 != null && str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public void dispatchCharactersEvents(int i, ContentHandler contentHandler, boolean z) throws SAXException {
        int iMakeNodeIdentity = makeNodeIdentity(i);
        if (iMakeNodeIdentity == -1) {
            return;
        }
        short s_type = _type(iMakeNodeIdentity);
        if (isTextType(s_type)) {
            int iElementAt = this.m_dataOrQName.elementAt(iMakeNodeIdentity);
            int iElementAt2 = this.m_data.elementAt(iElementAt);
            int iElementAt3 = this.m_data.elementAt(iElementAt + 1);
            FastStringBuffer fastStringBuffer = this.m_chars;
            if (z) {
                fastStringBuffer.sendNormalizedSAXcharacters(contentHandler, iElementAt2, iElementAt3);
                return;
            } else {
                fastStringBuffer.sendSAXcharacters(contentHandler, iElementAt2, iElementAt3);
                return;
            }
        }
        int i_firstch = _firstch(iMakeNodeIdentity);
        int iElementAt4 = 0;
        if (-1 == i_firstch) {
            if (s_type != 1) {
                int i_dataOrQName = _dataOrQName(iMakeNodeIdentity);
                if (i_dataOrQName < 0) {
                    i_dataOrQName = this.m_data.elementAt((-i_dataOrQName) + 1);
                }
                String strIndexToString = this.m_valuesOrPrefixes.indexToString(i_dataOrQName);
                if (z) {
                    FastStringBuffer.sendNormalizedSAXcharacters(strIndexToString.toCharArray(), 0, strIndexToString.length(), contentHandler);
                    return;
                } else {
                    contentHandler.characters(strIndexToString.toCharArray(), 0, strIndexToString.length());
                    return;
                }
            }
            return;
        }
        int iElementAt5 = -1;
        do {
            if (isTextType(_type(i_firstch))) {
                int i_dataOrQName2 = _dataOrQName(i_firstch);
                if (-1 == iElementAt5) {
                    iElementAt5 = this.m_data.elementAt(i_dataOrQName2);
                }
                iElementAt4 += this.m_data.elementAt(i_dataOrQName2 + 1);
            }
            i_firstch = getNextNodeIdentity(i_firstch);
            if (-1 == i_firstch) {
                break;
            }
        } while (_parent(i_firstch) >= iMakeNodeIdentity);
        if (iElementAt4 > 0) {
            FastStringBuffer fastStringBuffer2 = this.m_chars;
            if (z) {
                fastStringBuffer2.sendNormalizedSAXcharacters(contentHandler, iElementAt5, iElementAt4);
            } else {
                fastStringBuffer2.sendSAXcharacters(contentHandler, iElementAt5, iElementAt4);
            }
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public void dispatchToEvents(int i, ContentHandler contentHandler) throws SAXException {
        DTMTreeWalker dTMTreeWalker = this.m_walker;
        if (dTMTreeWalker.getcontentHandler() != null) {
            dTMTreeWalker = new DTMTreeWalker();
        }
        dTMTreeWalker.setcontentHandler(contentHandler);
        dTMTreeWalker.setDTM(this);
        try {
            dTMTreeWalker.traverse(i);
        } finally {
            dTMTreeWalker.setcontentHandler(null);
        }
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void elementDecl(String str, String str2) throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
        this.m_textType = 3;
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endDTD() throws SAXException {
        this.m_insideDTD = false;
    }

    public void endDocument() throws SAXException {
        charactersFlush();
        this.m_nextsib.setElementAt(-1, 0);
        if (this.m_firstch.elementAt(0) == -2) {
            this.m_firstch.setElementAt(-1, 0);
        }
        int i = this.m_previous;
        if (-1 != i) {
            this.m_nextsib.setElementAt(-1, i);
        }
        this.m_parents = null;
        this.m_prefixMappings = null;
        this.m_contextIndexes = null;
        this.m_endDocumentOccured = true;
        this.m_locator = null;
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
        charactersFlush();
        this.m_contextIndexes.quickPop(1);
        int iPeek = this.m_contextIndexes.peek();
        if (iPeek != this.m_prefixMappings.size()) {
            this.m_prefixMappings.setSize(iPeek);
        }
        int i = this.m_previous;
        int iPop = this.m_parents.pop();
        this.m_previous = iPop;
        if (-1 == i) {
            this.m_firstch.setElementAt(-1, iPop);
        } else {
            this.m_nextsib.setElementAt(-1, i);
        }
        popShouldStripWhitespace();
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endEntity(String str) throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) throws SAXException {
        if (str == null) {
            str = "";
        }
        int iPeek = this.m_contextIndexes.peek() - 1;
        do {
            iPeek = this.m_prefixMappings.indexOf(str, iPeek + 1);
            if (iPeek < 0) {
                break;
            }
        } while ((iPeek & 1) == 1);
        if (iPeek > -1) {
            this.m_prefixMappings.setElementAt("%@$#^@#", iPeek);
            this.m_prefixMappings.setElementAt("%@$#^@#", iPeek + 1);
        }
    }

    @Override // org.xml.sax.ErrorHandler
    public void error(SAXParseException sAXParseException) throws SAXException {
        throw sAXParseException;
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void externalEntityDecl(String str, String str2, String str3) throws SAXException {
    }

    @Override // org.xml.sax.ErrorHandler
    public void fatalError(SAXParseException sAXParseException) throws SAXException {
        throw sAXParseException;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public int getAttributeNode(int i, String str, String str2) {
        int firstAttribute = getFirstAttribute(i);
        while (-1 != firstAttribute) {
            String namespaceURI = getNamespaceURI(firstAttribute);
            String localName = getLocalName(firstAttribute);
            if ((str == namespaceURI || (str != null && str.equals(namespaceURI))) && str2.equals(localName)) {
                return firstAttribute;
            }
            firstAttribute = getNextAttribute(firstAttribute);
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public ContentHandler getContentHandler() {
        return this.m_incrementalSAXSource.getClass().getName().equals("com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource_Filter") ? (ContentHandler) this.m_incrementalSAXSource : this;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public DTDHandler getDTDHandler() {
        return this;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public DeclHandler getDeclHandler() {
        return this;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentTypeDeclarationPublicIdentifier() {
        error(XMLMessages.createXMLMessage("ER_METHOD_NOT_SUPPORTED", null));
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentTypeDeclarationSystemIdentifier() {
        error(XMLMessages.createXMLMessage("ER_METHOD_NOT_SUPPORTED", null));
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public int getElementById(String str) {
        Integer num;
        boolean zNextNode = true;
        do {
            num = this.m_idAttributes.get(str);
            if (num != null) {
                return makeNodeHandle(num.intValue());
            }
            if (!zNextNode || this.m_endDocumentOccured) {
                return -1;
            }
            zNextNode = nextNode();
        } while (num == null);
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public EntityResolver getEntityResolver() {
        return this;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public ErrorHandler getErrorHandler() {
        return this;
    }

    public String getFixedNames(int i) {
        return m_fixednames[i];
    }

    public int getIdForNamespace(String str) {
        return this.m_valuesOrPrefixes.stringToIndex(str);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public LexicalHandler getLexicalHandler() {
        return this.m_incrementalSAXSource.getClass().getName().equals("com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource_Filter") ? (LexicalHandler) this.m_incrementalSAXSource : this;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getLocalName(int i) {
        return this.m_expandedNameTable.getLocalName(_exptype(makeNodeIdentity(i)));
    }

    public String getNamespaceURI(String str) {
        int iPeek = this.m_contextIndexes.peek() - 1;
        if (str == null) {
            str = "";
        }
        do {
            iPeek = this.m_prefixMappings.indexOf(str, iPeek + 1);
            if (iPeek < 0) {
                break;
            }
        } while ((iPeek & 1) == 1);
        return iPeek > -1 ? this.m_prefixMappings.get(iPeek + 1) : "";
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase
    public int getNextNodeIdentity(int i) {
        int i2 = i + 1;
        while (i2 >= this.m_size) {
            if (this.m_incrementalSAXSource == null) {
                return -1;
            }
            nextNode();
        }
        return i2;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeName(int i) {
        int expandedTypeID = getExpandedTypeID(i);
        if (this.m_expandedNameTable.getNamespaceID(expandedTypeID) != 0) {
            int iElementAt = this.m_dataOrQName.elementAt(makeNodeIdentity(i));
            if (iElementAt < 0) {
                iElementAt = this.m_data.elementAt(-iElementAt);
            }
            return this.m_valuesOrPrefixes.indexToString(iElementAt);
        }
        short nodeType = getNodeType(i);
        ExpandedNameTable expandedNameTable = this.m_expandedNameTable;
        if (nodeType != 13) {
            return expandedNameTable.getLocalNameID(expandedTypeID) == 0 ? m_fixednames[nodeType] : this.m_expandedNameTable.getLocalName(expandedTypeID);
        }
        if (expandedNameTable.getLocalName(expandedTypeID) == null) {
            return "xmlns";
        }
        return "xmlns:" + this.m_expandedNameTable.getLocalName(expandedTypeID);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeNameX(int i) {
        int expandedTypeID = getExpandedTypeID(i);
        if (this.m_expandedNameTable.getNamespaceID(expandedTypeID) == 0) {
            String localName = this.m_expandedNameTable.getLocalName(expandedTypeID);
            return localName == null ? "" : localName;
        }
        int iElementAt = this.m_dataOrQName.elementAt(makeNodeIdentity(i));
        if (iElementAt < 0) {
            iElementAt = this.m_data.elementAt(-iElementAt);
        }
        return this.m_valuesOrPrefixes.indexToString(iElementAt);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeValue(int i) {
        int iMakeNodeIdentity = makeNodeIdentity(i);
        short s_type = _type(iMakeNodeIdentity);
        if (isTextType(s_type)) {
            int i_dataOrQName = _dataOrQName(iMakeNodeIdentity);
            return this.m_chars.getString(this.m_data.elementAt(i_dataOrQName), this.m_data.elementAt(i_dataOrQName + 1));
        }
        if (1 == s_type || 11 == s_type || 9 == s_type) {
            return null;
        }
        int i_dataOrQName2 = _dataOrQName(iMakeNodeIdentity);
        if (i_dataOrQName2 < 0) {
            i_dataOrQName2 = this.m_data.elementAt((-i_dataOrQName2) + 1);
        }
        return this.m_valuesOrPrefixes.indexToString(i_dataOrQName2);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase
    public int getNumberOfNodes() {
        return this.m_size;
    }

    public String getPrefix(String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            if (str != null) {
                int iIndexOf = str.indexOf(58);
                if (iIndexOf > 0) {
                    return str.startsWith("xmlns:") ? str.substring(iIndexOf + 1) : str.substring(0, iIndexOf);
                }
                if (str.equals("xmlns")) {
                    return "";
                }
            }
            return null;
        }
        int iIndexOf2 = -1;
        do {
            iIndexOf2 = this.m_prefixMappings.indexOf(str2, iIndexOf2 + 1);
        } while ((iIndexOf2 & 1) == 0);
        if (iIndexOf2 >= 0) {
            return this.m_prefixMappings.get(iIndexOf2 - 1);
        }
        if (str != null) {
            int iIndexOf3 = str.indexOf(58);
            if (str.equals("xmlns")) {
                return "";
            }
            if (str.startsWith("xmlns:")) {
                return str.substring(iIndexOf3 + 1);
            }
            if (iIndexOf3 > 0) {
                return str.substring(0, iIndexOf3);
            }
        }
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public SourceLocator getSourceLocatorFor(int i) {
        if (this.m_useSourceLocationProperty) {
            int iMakeNodeIdentity = makeNodeIdentity(i);
            return new NodeLocator(null, this.m_sourceSystemId.elementAt(iMakeNodeIdentity), this.m_sourceLine.elementAt(iMakeNodeIdentity), this.m_sourceColumn.elementAt(iMakeNodeIdentity));
        }
        Locator locator = this.m_locator;
        if (locator != null) {
            return new NodeLocator(null, locator.getSystemId(), -1, -1);
        }
        String str = this.m_systemId;
        if (str != null) {
            return new NodeLocator(null, str, -1, -1);
        }
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public XMLString getStringValue(int i) {
        int iMakeNodeIdentity = makeNodeIdentity(i);
        short s_type = iMakeNodeIdentity == -1 ? (short) -1 : _type(iMakeNodeIdentity);
        if (isTextType(s_type)) {
            int i_dataOrQName = _dataOrQName(iMakeNodeIdentity);
            return this.m_xstrf.newstr(this.m_chars, this.m_data.elementAt(i_dataOrQName), this.m_data.elementAt(i_dataOrQName + 1));
        }
        int i_firstch = _firstch(iMakeNodeIdentity);
        if (-1 != i_firstch) {
            int iElementAt = 0;
            int iElementAt2 = -1;
            do {
                if (isTextType(_type(i_firstch))) {
                    int i_dataOrQName2 = _dataOrQName(i_firstch);
                    if (-1 == iElementAt2) {
                        iElementAt2 = this.m_data.elementAt(i_dataOrQName2);
                    }
                    iElementAt += this.m_data.elementAt(i_dataOrQName2 + 1);
                }
                i_firstch = getNextNodeIdentity(i_firstch);
                if (-1 == i_firstch) {
                    break;
                }
            } while (_parent(i_firstch) >= iMakeNodeIdentity);
            if (iElementAt > 0) {
                return this.m_xstrf.newstr(this.m_chars, iElementAt2, iElementAt);
            }
        } else if (s_type != 1) {
            int i_dataOrQName3 = _dataOrQName(iMakeNodeIdentity);
            if (i_dataOrQName3 < 0) {
                i_dataOrQName3 = this.m_data.elementAt((-i_dataOrQName3) + 1);
            }
            return this.m_xstrf.newstr(this.m_valuesOrPrefixes.indexToString(i_dataOrQName3));
        }
        return this.m_xstrf.emptystr();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getUnparsedEntityURI(String str) {
        List<String> list = this.m_entities;
        if (list == null) {
            return "";
        }
        int size = list.size();
        for (int i = 0; i < size; i += 4) {
            String str2 = this.m_entities.get(i + 3);
            if (str2 != null && str2.equals(str)) {
                if (this.m_entities.get(i + 2) == null) {
                    return "";
                }
                String str3 = this.m_entities.get(i + 1);
                return str3 == null ? this.m_entities.get(i) : str3;
            }
        }
        return "";
    }

    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
        characters(cArr, i, i2);
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void internalEntityDecl(String str, String str2) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public boolean isAttributeSpecified(int i) {
        return true;
    }

    public boolean isWhitespace(int i) {
        int iMakeNodeIdentity = makeNodeIdentity(i);
        if (!isTextType(iMakeNodeIdentity != -1 ? _type(iMakeNodeIdentity) : (short) -1)) {
            return false;
        }
        int i_dataOrQName = _dataOrQName(iMakeNodeIdentity);
        return this.m_chars.isWhitespace(this.m_data.elementAt(i_dataOrQName), this.m_data.elementAt(i_dataOrQName + 1));
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public void migrateTo(DTMManager dTMManager) {
        super.migrateTo(dTMManager);
        int size = this.m_dtmIdent.size();
        int firstFreeDTMID = this.m_mgrDefault.getFirstFreeDTMID();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            this.m_dtmIdent.setElementAt(firstFreeDTMID << 16, i2);
            this.m_mgrDefault.addDTM(this, firstFreeDTMID, i);
            firstFreeDTMID++;
            i += 65536;
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean needsTwoThreads() {
        return this.m_incrementalSAXSource != null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase
    public boolean nextNode() {
        IncrementalSAXSource incrementalSAXSource = this.m_incrementalSAXSource;
        if (incrementalSAXSource == null) {
            return false;
        }
        if (this.m_endDocumentOccured) {
            clearCoRoutine();
            return false;
        }
        Object objDeliverMoreNodes = incrementalSAXSource.deliverMoreNodes(true);
        if (objDeliverMoreNodes instanceof Boolean) {
            if (objDeliverMoreNodes != Boolean.TRUE) {
                clearCoRoutine();
            }
            return true;
        }
        if (objDeliverMoreNodes instanceof RuntimeException) {
            throw ((RuntimeException) objDeliverMoreNodes);
        }
        if (objDeliverMoreNodes instanceof Exception) {
            throw new WrappedRuntimeException((Exception) objDeliverMoreNodes);
        }
        clearCoRoutine();
        return false;
    }

    @Override // org.xml.sax.DTDHandler
    public void notationDecl(String str, String str2, String str3) throws SAXException {
    }

    public void processingInstruction(String str, String str2) throws SAXException {
        charactersFlush();
        this.m_previous = addNode(7, this.m_expandedNameTable.getExpandedTypeID(null, str, 7), this.m_parents.peek(), this.m_previous, this.m_valuesOrPrefixes.stringToIndex(str2), false);
    }

    @Override // org.xml.sax.EntityResolver
    public InputSource resolveEntity(String str, String str2) throws SAXException {
        return null;
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        this.m_locator = locator;
        this.m_systemId = locator.getSystemId();
    }

    public void setIDAttribute(String str, int i) {
        this.m_idAttributes.put(str, Integer.valueOf(i));
    }

    public void setIncrementalSAXSource(IncrementalSAXSource incrementalSAXSource) {
        this.m_incrementalSAXSource = incrementalSAXSource;
        incrementalSAXSource.setContentHandler(this);
        incrementalSAXSource.setLexicalHandler(this);
        incrementalSAXSource.setDTDHandler(this);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void setProperty(String str, Object obj) {
    }

    public void setSourceLocation() {
        this.m_sourceSystemId.addElement(this.m_locator.getSystemId());
        this.m_sourceLine.addElement(this.m_locator.getLineNumber());
        this.m_sourceColumn.addElement(this.m_locator.getColumnNumber());
        if (this.m_sourceSystemId.size() == this.m_size) {
            return;
        }
        String str = "CODING ERROR in Source Location: " + this.m_size + " != " + this.m_sourceSystemId.size();
        System.err.println(str);
        f63.a(str);
    }

    public void setUseSourceLocation(boolean z) {
        this.m_useSourceLocationProperty = z;
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startCDATA() throws SAXException {
        this.m_textType = 4;
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startDTD(String str, String str2, String str3) throws SAXException {
        this.m_insideDTD = true;
    }

    public void startDocument() throws SAXException {
        this.m_parents.push(addNode(9, this.m_expandedNameTable.getExpandedTypeID(9), -1, -1, 0, true));
        this.m_previous = -1;
        this.m_contextIndexes.push(this.m_prefixMappings.size());
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        charactersFlush();
        int expandedTypeID = this.m_expandedNameTable.getExpandedTypeID(str, ((str2 == null || str2.isEmpty()) && (str == null || str.isEmpty())) ? str3 : str2, 1);
        int iAddNode = addNode(1, expandedTypeID, this.m_parents.peek(), this.m_previous, getPrefix(str3, str) != null ? this.m_valuesOrPrefixes.stringToIndex(str3) : 0, true);
        if (this.m_indexing) {
            indexNode(expandedTypeID, iAddNode);
        }
        this.m_parents.push(iAddNode);
        int size = this.m_prefixMappings.size();
        int iAddNode2 = -1;
        if (!this.m_pastFirstElement) {
            iAddNode2 = addNode(13, this.m_expandedNameTable.getExpandedTypeID(null, "xml", 13), iAddNode, -1, this.m_valuesOrPrefixes.stringToIndex("http://www.w3.org/XML/1998/namespace"), false);
            this.m_pastFirstElement = true;
        }
        for (int iPeek = this.m_contextIndexes.peek(); iPeek < size; iPeek += 2) {
            String str4 = this.m_prefixMappings.get(iPeek);
            if (str4 != null) {
                iAddNode2 = addNode(13, this.m_expandedNameTable.getExpandedTypeID(null, str4, 13), iAddNode, iAddNode2, this.m_valuesOrPrefixes.stringToIndex(this.m_prefixMappings.get(iPeek + 1)), false);
            }
        }
        int length = attributes.getLength();
        int i = 0;
        while (true) {
            int i2 = 2;
            if (i >= length) {
                break;
            }
            String uri = attributes.getURI(i);
            String qName = attributes.getQName(i);
            String value = attributes.getValue(i);
            String prefix = getPrefix(qName, uri);
            String localName = attributes.getLocalName(i);
            if (qName != null && (qName.equals("xmlns") || qName.startsWith("xmlns:"))) {
                if (!declAlreadyDeclared(prefix)) {
                    i2 = 13;
                }
                i++;
            } else if (attributes.getType(i).equalsIgnoreCase(SchemaSymbols.ATTVAL_ID)) {
                setIDAttribute(value, iAddNode);
            }
            if (value == null) {
                value = "";
            }
            int iStringToIndex = this.m_valuesOrPrefixes.stringToIndex(value);
            if (prefix != null) {
                int iStringToIndex2 = this.m_valuesOrPrefixes.stringToIndex(qName);
                int size2 = this.m_data.size();
                this.m_data.addElement(iStringToIndex2);
                this.m_data.addElement(iStringToIndex);
                iStringToIndex = -size2;
            }
            iAddNode2 = addNode(i2, this.m_expandedNameTable.getExpandedTypeID(uri, localName, i2), iAddNode, iAddNode2, iStringToIndex, false);
            i++;
        }
        if (-1 != iAddNode2) {
            this.m_nextsib.setElementAt(-1, iAddNode2);
        }
        DTMWSFilter dTMWSFilter = this.m_wsfilter;
        if (dTMWSFilter != null) {
            short shouldStripSpace = dTMWSFilter.getShouldStripSpace(makeNodeHandle(iAddNode), this);
            pushShouldStripWhitespace(3 == shouldStripSpace ? getShouldStripWhitespace() : 2 == shouldStripSpace);
        }
        this.m_previous = -1;
        this.m_contextIndexes.push(this.m_prefixMappings.size());
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String str) throws SAXException {
    }

    public void startPrefixMapping(String str, String str2) throws SAXException {
        if (str == null) {
            str = "";
        }
        this.m_prefixMappings.add(str);
        this.m_prefixMappings.add(str2);
    }

    @Override // org.xml.sax.DTDHandler
    public void unparsedEntityDecl(String str, String str2, String str3, String str4) throws SAXException {
        if (this.m_entities == null) {
            this.m_entities = new ArrayList();
        }
        try {
            String absoluteURI = SystemIDResolver.getAbsoluteURI(str3, getDocumentBaseURI());
            this.m_entities.add(str2);
            this.m_entities.add(absoluteURI);
            this.m_entities.add(str4);
            this.m_entities.add(str);
        } catch (Exception e) {
            x73.a(e);
        }
    }

    @Override // org.xml.sax.ErrorHandler
    public void warning(SAXParseException sAXParseException) throws SAXException {
        System.err.println(sAXParseException.getMessage());
    }

    public void clearCoRoutine() {
        clearCoRoutine(true);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNamespaceURI(int i) {
        return this.m_expandedNameTable.getNamespace(_exptype(makeNodeIdentity(i)));
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getPrefix(int i) {
        int i_dataOrQName;
        int iMakeNodeIdentity = makeNodeIdentity(i);
        short s_type = _type(iMakeNodeIdentity);
        if (1 == s_type) {
            int i_dataOrQName2 = _dataOrQName(iMakeNodeIdentity);
            if (i_dataOrQName2 == 0) {
                return "";
            }
            return getPrefix(this.m_valuesOrPrefixes.indexToString(i_dataOrQName2), null);
        }
        if (2 != s_type || (i_dataOrQName = _dataOrQName(iMakeNodeIdentity)) >= 0) {
            return "";
        }
        return getPrefix(this.m_valuesOrPrefixes.indexToString(this.m_data.elementAt(-i_dataOrQName)), null);
    }

    public SAX2DTM(DTMManager dTMManager, Source source, int i, DTMWSFilter dTMWSFilter, XMLStringFactory xMLStringFactory, boolean z) {
        this(dTMManager, source, i, dTMWSFilter, xMLStringFactory, z, 512, true, false);
    }
}
