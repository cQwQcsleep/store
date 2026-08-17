package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.DTMAxisTraverser;
import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xml.internal.dtm.DTMWSFilter;
import com.sun.org.apache.xml.internal.utils.FastStringBuffer;
import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xml.internal.utils.XMLStringFactory;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.PrintStream;
import javax.xml.transform.SourceLocator;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTMDocumentImpl implements DTM, ContentHandler, LexicalHandler {
    protected static final int DOCHANDLE_MASK = -8388608;
    protected static final byte DOCHANDLE_SHIFT = 22;
    protected static final int NODEHANDLE_MASK = 8388607;
    private static final String[] fixednames = {null, null, null, PsuedoNames.PSEUDONAME_TEXT, "#cdata_section", null, null, null, PsuedoNames.PSEUDONAME_COMMENT, "#document", null, "#document-fragment", null};
    protected String m_documentBaseURI;
    private XMLStringFactory m_xsf;
    int m_docHandle = -1;
    int m_docElement = -1;
    int currentParent = 0;
    int previousSibling = 0;
    protected int m_currentNode = -1;
    private boolean previousSiblingWasParent = false;
    int[] gotslot = new int[4];
    private boolean done = false;
    boolean m_isError = false;
    private final boolean DEBUG = false;
    private IncrementalSAXSource m_incrSAXSource = null;
    ChunkedIntArray nodes = new ChunkedIntArray(4);
    private FastStringBuffer m_char = new FastStringBuffer();
    private int m_char_current_start = 0;
    private DTMStringPool m_localNames = new DTMStringPool();
    private DTMStringPool m_nsNames = new DTMStringPool();
    private DTMStringPool m_prefixNames = new DTMStringPool();
    private ExpandedNameTable m_expandedNames = new ExpandedNameTable();

    public DTMDocumentImpl(DTMManager dTMManager, int i, DTMWSFilter dTMWSFilter, XMLStringFactory xMLStringFactory) {
        initDocument(i);
        this.m_xsf = xMLStringFactory;
    }

    private final int appendNode(int i, int i2, int i3, int i4) {
        int iAppendSlot = this.nodes.appendSlot(i, i2, i3, i4);
        if (this.previousSiblingWasParent) {
            this.nodes.writeEntry(this.previousSibling, 2, iAppendSlot);
        }
        this.previousSiblingWasParent = false;
        return iAppendSlot;
    }

    private void processAccumulatedText() {
        int length = this.m_char.length();
        int i = this.m_char_current_start;
        if (length != i) {
            appendTextChild(i, length - i);
            this.m_char_current_start = length;
        }
    }

    public void appendAttribute(int i, int i2, int i3, boolean z, int i4, int i5) {
        int i6 = this.currentParent;
        int i7 = i2 | (i3 << 16);
        System.out.println("set w3=" + i7 + " " + (i7 >> 16) + PsuedoNames.PSEUDONAME_ROOT + (65535 & i7));
        int iAppendNode = appendNode((i << 16) | 2, i6, 0, i7);
        this.previousSibling = iAppendNode;
        appendNode(3, iAppendNode, i4, i5);
        this.previousSiblingWasParent = true;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void appendChild(int i, boolean z, boolean z2) {
    }

    public void appendComment(int i, int i2) {
        this.previousSibling = appendNode(8, this.currentParent, i, i2);
    }

    public void appendEndDocument() {
        this.done = true;
    }

    public void appendEndElement() {
        if (this.previousSiblingWasParent) {
            this.nodes.writeEntry(this.previousSibling, 2, -1);
        }
        int i = this.currentParent;
        this.previousSibling = i;
        this.nodes.readSlot(i, this.gotslot);
        this.currentParent = this.gotslot[1] & 65535;
        this.previousSiblingWasParent = true;
    }

    public void appendNSDeclaration(int i, int i2, boolean z) {
        this.m_nsNames.stringToIndex("http://www.w3.org/2000/xmlns/");
        this.previousSibling = appendNode((this.m_nsNames.stringToIndex("http://www.w3.org/2000/xmlns/") << 16) | 13, this.currentParent, 0, i2);
        this.previousSiblingWasParent = false;
    }

    public void appendStartDocument() {
        this.m_docElement = -1;
        initDocument(0);
    }

    public void appendStartElement(int i, int i2, int i3) {
        int i4 = this.currentParent;
        int i5 = i2 | (i3 << 16);
        System.out.println("set w3=" + i5 + " " + (i5 >> 16) + PsuedoNames.PSEUDONAME_ROOT + (65535 & i5));
        int iAppendNode = appendNode((i << 16) | 1, i4, 0, i5);
        this.currentParent = iAppendNode;
        this.previousSibling = 0;
        if (this.m_docElement == -1) {
            this.m_docElement = iAppendNode;
        }
    }

    public void appendTextChild(int i, int i2) {
        this.previousSibling = appendNode(3, this.currentParent, i, i2);
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        this.m_char.append(cArr, i, i2);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
        processAccumulatedText();
        this.m_char.append(cArr, i, i2);
        appendComment(this.m_char_current_start, i2);
        this.m_char_current_start += i2;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void dispatchCharactersEvents(int i, ContentHandler contentHandler, boolean z) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void dispatchToEvents(int i, ContentHandler contentHandler) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void documentRegistration() {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void documentRelease() {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endDTD() throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        appendEndDocument();
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        processAccumulatedText();
        appendEndElement();
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endEntity(String str) throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getAttributeNode(int i, String str, String str2) {
        int iStringToIndex = this.m_nsNames.stringToIndex(str);
        int iStringToIndex2 = this.m_localNames.stringToIndex(str2);
        int i2 = i & NODEHANDLE_MASK;
        this.nodes.readSlot(i2, this.gotslot);
        short s = (short) (this.gotslot[0] & 65535);
        if (s == 1) {
            i2++;
        }
        while (s == 2) {
            int[] iArr = this.gotslot;
            if (iStringToIndex == (iArr[0] << 16) && iArr[3] == iStringToIndex2) {
                return this.m_docHandle | i2;
            }
            i2 = iArr[2];
            this.nodes.readSlot(i2, iArr);
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public DTMAxisIterator getAxisIterator(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public DTMAxisTraverser getAxisTraverser(int i) {
        return null;
    }

    public FastStringBuffer getContentBuffer() {
        return this.m_char;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public ContentHandler getContentHandler() {
        IncrementalSAXSource incrementalSAXSource = this.m_incrSAXSource;
        return incrementalSAXSource instanceof IncrementalSAXSource_Filter ? (ContentHandler) incrementalSAXSource : this;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public DTDHandler getDTDHandler() {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public DeclHandler getDeclHandler() {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getDocument() {
        return this.m_docHandle;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean getDocumentAllDeclarationsProcessed() {
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentBaseURI() {
        return this.m_documentBaseURI;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentEncoding(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getDocumentRoot(int i) {
        if ((NODEHANDLE_MASK & i) == 0) {
            return -1;
        }
        return DOCHANDLE_MASK & i;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentStandalone(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentSystemIdentifier(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentTypeDeclarationPublicIdentifier() {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentTypeDeclarationSystemIdentifier() {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentVersion(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getElementById(String str) {
        return 0;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public EntityResolver getEntityResolver() {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public ErrorHandler getErrorHandler() {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getExpandedTypeID(int i) {
        this.nodes.readSlot(i, this.gotslot);
        String strIndexToString = this.m_localNames.indexToString(this.gotslot[3]);
        String strSubstring = strIndexToString.substring(strIndexToString.indexOf(":") + 1);
        return this.m_nsNames.stringToIndex(this.m_nsNames.indexToString(this.gotslot[0] << 16) + ":" + strSubstring);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getFirstAttribute(int i) {
        int i2 = i & NODEHANDLE_MASK;
        if (1 != (this.nodes.readEntry(i2, 0) & 65535)) {
            return -1;
        }
        int i3 = i2 + 1;
        if (2 == (this.nodes.readEntry(i3, 0) & 65535)) {
            return this.m_docHandle | i3;
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getFirstChild(int i) {
        int i2 = i & NODEHANDLE_MASK;
        this.nodes.readSlot(i2, this.gotslot);
        int[] iArr = this.gotslot;
        short s = (short) (iArr[0] & 65535);
        if (s == 1 || s == 9 || s == 5) {
            int i3 = i2 + 1;
            this.nodes.readSlot(i3, iArr);
            while (true) {
                int[] iArr2 = this.gotslot;
                if (2 != (iArr2[0] & 65535)) {
                    if (iArr2[1] != i2) {
                        break;
                    }
                    return this.m_docHandle | i3;
                }
                i3 = iArr2[2];
                if (i3 == -1) {
                    return -1;
                }
                this.nodes.readSlot(i3, iArr2);
            }
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getFirstNamespaceNode(int i, boolean z) {
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getLastChild(int i) {
        int firstChild = getFirstChild(i & NODEHANDLE_MASK);
        int i2 = -1;
        while (firstChild != -1) {
            i2 = firstChild;
            firstChild = getNextSibling(firstChild);
        }
        return this.m_docHandle | i2;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public short getLevel(int i) {
        short s = 0;
        while (i != 0) {
            s = (short) (s + 1);
            i = this.nodes.readEntry(i, 1);
        }
        return s;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public LexicalHandler getLexicalHandler() {
        IncrementalSAXSource incrementalSAXSource = this.m_incrSAXSource;
        return incrementalSAXSource instanceof IncrementalSAXSource_Filter ? (LexicalHandler) incrementalSAXSource : this;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getLocalName(int i) {
        this.nodes.readSlot(i, this.gotslot);
        int[] iArr = this.gotslot;
        short s = (short) (iArr[0] & 65535);
        if (s != 1 && s != 2) {
            return "";
        }
        String strIndexToString = this.m_localNames.indexToString(iArr[3] & 65535);
        return strIndexToString == null ? "" : strIndexToString;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getLocalNameFromExpandedNameID(int i) {
        String strIndexToString = this.m_localNames.indexToString(i);
        return strIndexToString.substring(strIndexToString.indexOf(":") + 1);
    }

    public DTMStringPool getLocalNameTable() {
        return this.m_localNames;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getNamespaceFromExpandedNameID(int i) {
        String strIndexToString = this.m_localNames.indexToString(i);
        return strIndexToString.substring(0, strIndexToString.indexOf(":"));
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getNamespaceURI(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getNextAttribute(int i) {
        int i2;
        int i3 = i & NODEHANDLE_MASK;
        this.nodes.readSlot(i3, this.gotslot);
        int[] iArr = this.gotslot;
        short s = (short) (iArr[0] & 65535);
        if (s == 1) {
            return getFirstAttribute(i3);
        }
        if (s != 2 || (i2 = iArr[2]) == -1) {
            return -1;
        }
        return this.m_docHandle | i2;
    }

    public int getNextDescendant(int i, int i2) {
        int i3 = i & NODEHANDLE_MASK;
        int i4 = i2 & NODEHANDLE_MASK;
        if (i4 == 0) {
            return -1;
        }
        while (!this.m_isError && (!this.done || i4 <= this.nodes.slotsUsed())) {
            if (i4 > i3) {
                int i5 = i4 + 1;
                this.nodes.readSlot(i5, this.gotslot);
                int[] iArr = this.gotslot;
                if (iArr[2] == 0) {
                    if (this.done) {
                        break;
                    }
                } else {
                    if (((short) (iArr[0] & 65535)) != 2) {
                        if (iArr[1] < i3) {
                            break;
                        }
                        return this.m_docHandle | i5;
                    }
                    i4 += 2;
                }
            } else {
                i4++;
            }
        }
        return -1;
    }

    public int getNextFollowing(int i, int i2) {
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getNextNamespaceNode(int i, int i2, boolean z) {
        return -1;
    }

    public int getNextPreceding(int i, int i2) {
        int i3 = i2 & NODEHANDLE_MASK;
        while (i3 > 1) {
            i3--;
            if (2 != (this.nodes.readEntry(i3, 0) & 65535)) {
                return this.nodes.specialFind(i, i3) | this.m_docHandle;
            }
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getNextSibling(int i) {
        int i2 = i & NODEHANDLE_MASK;
        if (i2 == 0) {
            return -1;
        }
        short entry = (short) (this.nodes.readEntry(i2, 0) & 65535);
        if (entry == 1 || entry == 2 || entry == 5) {
            int entry2 = this.nodes.readEntry(i2, 2);
            if (entry2 == -1) {
                return -1;
            }
            if (entry2 != 0) {
                return this.m_docHandle | entry2;
            }
        }
        int entry3 = this.nodes.readEntry(i2, 1);
        int i3 = i2 + 1;
        if (this.nodes.readEntry(i3, 1) == entry3) {
            return this.m_docHandle | i3;
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public Node getNode(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeName(int i) {
        this.nodes.readSlot(i, this.gotslot);
        int[] iArr = this.gotslot;
        String str = fixednames[(short) (iArr[0] & 65535)];
        if (str != null) {
            return str;
        }
        int i2 = iArr[3];
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder("got i=");
        sb.append(i2);
        sb.append(" ");
        int i3 = i2 >> 16;
        sb.append(i3);
        sb.append(PsuedoNames.PSEUDONAME_ROOT);
        int i4 = i2 & 65535;
        sb.append(i4);
        printStream.println(sb.toString());
        String strIndexToString = this.m_localNames.indexToString(i4);
        String strIndexToString2 = this.m_prefixNames.indexToString(i3);
        if (strIndexToString2 == null || strIndexToString2.length() <= 0) {
            return strIndexToString;
        }
        return strIndexToString2 + ":" + strIndexToString;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeNameX(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public short getNodeType(int i) {
        return (short) (this.nodes.readEntry(i, 0) & 65535);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeValue(int i) {
        this.nodes.readSlot(i, this.gotslot);
        int[] iArr = this.gotslot;
        int i2 = iArr[0] & 255;
        if (i2 == 2) {
            this.nodes.readSlot(i + 1, iArr);
        } else if (i2 != 3 && i2 != 4 && i2 != 8) {
            return null;
        }
        FastStringBuffer fastStringBuffer = this.m_char;
        int[] iArr2 = this.gotslot;
        return fastStringBuffer.getString(iArr2[2], iArr2[3]);
    }

    public DTMStringPool getNsNameTable() {
        return this.m_nsNames;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getOwnerDocument(int i) {
        if ((NODEHANDLE_MASK & i) == 0) {
            return -1;
        }
        return DOCHANDLE_MASK & i;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getParent(int i) {
        return this.nodes.readEntry(i, 1) | this.m_docHandle;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getPrefix(int i) {
        this.nodes.readSlot(i, this.gotslot);
        int[] iArr = this.gotslot;
        short s = (short) (iArr[0] & 65535);
        if (s != 1 && s != 2) {
            return "";
        }
        String strIndexToString = this.m_prefixNames.indexToString(iArr[3] >> 16);
        return strIndexToString == null ? "" : strIndexToString;
    }

    public DTMStringPool getPrefixNameTable() {
        return this.m_prefixNames;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getPreviousSibling(int i) {
        int i2 = i & NODEHANDLE_MASK;
        int i3 = -1;
        if (i2 == 0) {
            return -1;
        }
        int firstChild = getFirstChild(this.nodes.readEntry(i2, 1));
        while (true) {
            int i4 = firstChild;
            int i5 = i3;
            i3 = i4;
            if (i3 == i2) {
                return this.m_docHandle | i5;
            }
            firstChild = getNextSibling(i3);
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public SourceLocator getSourceLocatorFor(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public XMLString getStringValue(int i) {
        this.nodes.readSlot(i, this.gotslot);
        int[] iArr = this.gotslot;
        int i2 = iArr[0] & 255;
        return this.m_xsf.newstr((i2 == 3 || i2 == 4 || i2 == 8) ? this.m_char.getString(iArr[2], iArr[3]) : null);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public char[] getStringValueChunk(int i, int i2, int[] iArr) {
        return new char[0];
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getStringValueChunkCount(int i) {
        return 0;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public DTMAxisIterator getTypedAxisIterator(int i, int i2) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getUnparsedEntityURI(String str) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean hasChildNodes(int i) {
        return getFirstChild(i) != -1;
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
    }

    public final void initDocument(int i) {
        this.m_docHandle = i << 22;
        this.nodes.writeSlot(0, 9, -1, -1, 0);
        this.done = false;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean isAttributeSpecified(int i) {
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean isCharacterElementContentWhitespace(int i) {
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean isDocumentAllDeclarationsProcessed(int i) {
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean isNodeAfter(int i, int i2) {
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean isSupported(String str, String str2) {
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void migrateTo(DTMManager dTMManager) {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean needsTwoThreads() {
        return this.m_incrSAXSource != null;
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        processAccumulatedText();
    }

    public void setContentBuffer(FastStringBuffer fastStringBuffer) {
        this.m_char = fastStringBuffer;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void setDocumentBaseURI(String str) {
        this.m_documentBaseURI = str;
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void setFeature(String str, boolean z) {
    }

    public void setIncrementalSAXSource(IncrementalSAXSource incrementalSAXSource) {
        this.m_incrSAXSource = incrementalSAXSource;
        incrementalSAXSource.setContentHandler(this);
        incrementalSAXSource.setLexicalHandler(this);
    }

    public void setLocalNameTable(DTMStringPool dTMStringPool) {
        this.m_localNames = dTMStringPool;
    }

    public void setNsNameTable(DTMStringPool dTMStringPool) {
        this.m_nsNames = dTMStringPool;
    }

    public void setPrefixNameTable(DTMStringPool dTMStringPool) {
        this.m_prefixNames = dTMStringPool;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void setProperty(String str, Object obj) {
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) throws SAXException {
        processAccumulatedText();
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startCDATA() throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startDTD(String str, String str2, String str3) throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        appendStartDocument();
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        String strSubstring;
        String strSubstring2;
        processAccumulatedText();
        int iIndexOf = str3.indexOf(58);
        String strSubstring3 = iIndexOf > 0 ? str3.substring(0, iIndexOf) : null;
        System.out.println("Prefix=" + strSubstring3 + " index=" + this.m_prefixNames.stringToIndex(strSubstring3));
        appendStartElement(this.m_nsNames.stringToIndex(str), this.m_localNames.stringToIndex(str2), this.m_prefixNames.stringToIndex(strSubstring3));
        int length = (attributes == null ? 0 : attributes.getLength()) - 1;
        for (int i = length; i >= 0; i--) {
            String qName = attributes.getQName(i);
            if (qName.startsWith("xmlns:") || "xmlns".equals(qName)) {
                int iIndexOf2 = qName.indexOf(58);
                appendNSDeclaration(this.m_prefixNames.stringToIndex(iIndexOf2 > 0 ? qName.substring(0, iIndexOf2) : null), this.m_nsNames.stringToIndex(attributes.getValue(i)), attributes.getType(i).equalsIgnoreCase(SchemaSymbols.ATTVAL_ID));
            }
        }
        for (int i2 = length; i2 >= 0; i2--) {
            String qName2 = attributes.getQName(i2);
            if (!qName2.startsWith("xmlns:") && !"xmlns".equals(qName2)) {
                int iIndexOf3 = qName2.indexOf(58);
                if (iIndexOf3 > 0) {
                    strSubstring = qName2.substring(0, iIndexOf3);
                    strSubstring2 = qName2.substring(iIndexOf3 + 1);
                } else {
                    strSubstring = "";
                    strSubstring2 = qName2;
                }
                this.m_char.append(attributes.getValue(i2));
                int length2 = this.m_char.length();
                if (!"xmlns".equals(strSubstring) && !"xmlns".equals(qName2)) {
                    int iStringToIndex = this.m_nsNames.stringToIndex(attributes.getURI(i2));
                    int iStringToIndex2 = this.m_localNames.stringToIndex(strSubstring2);
                    int iStringToIndex3 = this.m_prefixNames.stringToIndex(strSubstring);
                    boolean zEqualsIgnoreCase = attributes.getType(i2).equalsIgnoreCase(SchemaSymbols.ATTVAL_ID);
                    int i3 = this.m_char_current_start;
                    appendAttribute(iStringToIndex, iStringToIndex2, iStringToIndex3, zEqualsIgnoreCase, i3, length2 - i3);
                }
                this.m_char_current_start = length2;
            }
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String str) throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean supportsPreStripping() {
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void appendTextChild(String str) {
    }

    public int getDocumentRoot() {
        return this.m_docElement | this.m_docHandle;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getExpandedTypeID(String str, String str2, int i) {
        return this.m_nsNames.stringToIndex(str + ":" + str2);
    }
}
