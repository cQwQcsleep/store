package com.sun.org.apache.xml.internal.dtm.ref;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMAxisTraverser;
import com.sun.org.apache.xml.internal.dtm.DTMException;
import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xml.internal.dtm.DTMWSFilter;
import com.sun.org.apache.xml.internal.res.XMLMessages;
import com.sun.org.apache.xml.internal.utils.BoolStack;
import com.sun.org.apache.xml.internal.utils.SuballocatedIntVector;
import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xml.internal.utils.XMLStringFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Vector;
import javax.xml.transform.Source;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DTMDefaultBase implements DTM {
    public static final int DEFAULT_BLOCKSIZE = 512;
    public static final int DEFAULT_NUMBLOCKS = 32;
    public static final int DEFAULT_NUMBLOCKS_SMALL = 4;
    static final boolean JJK_DEBUG = false;
    protected static final int NOTPROCESSED = -2;
    public static final int ROOTNODE = 0;
    protected String m_documentBaseURI;
    protected SuballocatedIntVector m_dtmIdent;
    protected int[][][] m_elemIndexes;
    protected ExpandedNameTable m_expandedNameTable;
    protected SuballocatedIntVector m_exptype;
    protected SuballocatedIntVector m_firstch;
    protected boolean m_indexing;
    public DTMManager m_mgr;
    protected DTMManagerDefault m_mgrDefault;
    protected SuballocatedIntVector m_namespaceDeclSetElements;
    protected Vector<SuballocatedIntVector> m_namespaceDeclSets;
    protected SuballocatedIntVector m_nextsib;
    protected SuballocatedIntVector m_parent;
    protected SuballocatedIntVector m_prevsib;
    protected boolean m_shouldStripWS;
    protected BoolStack m_shouldStripWhitespaceStack;
    protected int m_size;
    protected DTMAxisTraverser[] m_traversers;
    protected DTMWSFilter m_wsfilter;
    protected XMLStringFactory m_xstrf;

    public DTMDefaultBase(DTMManager dTMManager, Source source, int i, DTMWSFilter dTMWSFilter, XMLStringFactory xMLStringFactory, boolean z, int i2, boolean z2, boolean z3) {
        int i3;
        this.m_size = 0;
        this.m_namespaceDeclSets = null;
        this.m_namespaceDeclSetElements = null;
        this.m_mgrDefault = null;
        this.m_shouldStripWS = false;
        if (i2 <= 64) {
            i3 = 4;
            this.m_dtmIdent = new SuballocatedIntVector(4, 1);
        } else {
            i3 = 32;
            this.m_dtmIdent = new SuballocatedIntVector(32);
        }
        this.m_exptype = new SuballocatedIntVector(i2, i3);
        this.m_firstch = new SuballocatedIntVector(i2, i3);
        this.m_nextsib = new SuballocatedIntVector(i2, i3);
        this.m_parent = new SuballocatedIntVector(i2, i3);
        if (z2) {
            this.m_prevsib = new SuballocatedIntVector(i2, i3);
        }
        this.m_mgr = dTMManager;
        if (dTMManager instanceof DTMManagerDefault) {
            this.m_mgrDefault = (DTMManagerDefault) dTMManager;
        }
        this.m_documentBaseURI = source != null ? source.getSystemId() : null;
        this.m_dtmIdent.setElementAt(i, 0);
        this.m_wsfilter = dTMWSFilter;
        this.m_xstrf = xMLStringFactory;
        this.m_indexing = z;
        if (z) {
            this.m_expandedNameTable = new ExpandedNameTable();
        } else {
            this.m_expandedNameTable = this.m_mgrDefault.getExpandedNameTable(this);
        }
        if (dTMWSFilter != null) {
            this.m_shouldStripWhitespaceStack = new BoolStack();
            pushShouldStripWhitespace(false);
        }
    }

    public int _exptype(int i) {
        if (i == -1) {
            return -1;
        }
        while (i >= this.m_size) {
            if (!nextNode() && i >= this.m_size) {
                return -1;
            }
        }
        return this.m_exptype.elementAt(i);
    }

    public int _firstch(int i) {
        int iElementAt = i >= this.m_size ? -2 : this.m_firstch.elementAt(i);
        while (iElementAt == -2) {
            boolean zNextNode = nextNode();
            if (i >= this.m_size && !zNextNode) {
                return -1;
            }
            int iElementAt2 = this.m_firstch.elementAt(i);
            if (iElementAt2 == -2 && !zNextNode) {
                return -1;
            }
            iElementAt = iElementAt2;
        }
        return iElementAt;
    }

    public int _level(int i) {
        while (i >= this.m_size) {
            if (!nextNode() && i >= this.m_size) {
                return -1;
            }
        }
        int i2 = 0;
        while (true) {
            i = _parent(i);
            if (-1 == i) {
                return i2;
            }
            i2++;
        }
    }

    public int _nextsib(int i) {
        int iElementAt = i >= this.m_size ? -2 : this.m_nextsib.elementAt(i);
        while (iElementAt == -2) {
            boolean zNextNode = nextNode();
            if (i >= this.m_size && !zNextNode) {
                return -1;
            }
            int iElementAt2 = this.m_nextsib.elementAt(i);
            if (iElementAt2 == -2 && !zNextNode) {
                return -1;
            }
            iElementAt = iElementAt2;
        }
        return iElementAt;
    }

    public int _parent(int i) {
        int i2;
        if (i < this.m_size) {
            return this.m_parent.elementAt(i);
        }
        do {
            boolean zNextNode = nextNode();
            i2 = this.m_size;
            if (i >= i2 && !zNextNode) {
                return -1;
            }
        } while (i >= i2);
        return this.m_parent.elementAt(i);
    }

    public int _prevsib(int i) {
        int i2;
        if (i < this.m_size) {
            return this.m_prevsib.elementAt(i);
        }
        do {
            boolean zNextNode = nextNode();
            i2 = this.m_size;
            if (i >= i2 && !zNextNode) {
                return -1;
            }
        } while (i >= i2);
        return this.m_prevsib.elementAt(i);
    }

    public short _type(int i) {
        int i_exptype = _exptype(i);
        if (-1 != i_exptype) {
            return this.m_expandedNameTable.getType(i_exptype);
        }
        return (short) -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void appendChild(int i, boolean z, boolean z2) {
        error(XMLMessages.createXMLMessage("ER_METHOD_NOT_SUPPORTED", null));
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void appendTextChild(String str) {
        error(XMLMessages.createXMLMessage("ER_METHOD_NOT_SUPPORTED", null));
    }

    public void declareNamespaceInContext(int i, int i2) {
        SuballocatedIntVector suballocatedIntVector;
        if (this.m_namespaceDeclSets == null) {
            SuballocatedIntVector suballocatedIntVector2 = new SuballocatedIntVector(32);
            this.m_namespaceDeclSetElements = suballocatedIntVector2;
            suballocatedIntVector2.addElement(i);
            this.m_namespaceDeclSets = new Vector<>();
            suballocatedIntVector = new SuballocatedIntVector(32);
            this.m_namespaceDeclSets.add(suballocatedIntVector);
        } else {
            int size = this.m_namespaceDeclSetElements.size() - 1;
            suballocatedIntVector = (size < 0 || i != this.m_namespaceDeclSetElements.elementAt(size)) ? null : this.m_namespaceDeclSets.get(size);
        }
        if (suballocatedIntVector == null) {
            this.m_namespaceDeclSetElements.addElement(i);
            SuballocatedIntVector suballocatedIntVectorFindNamespaceContext = findNamespaceContext(_parent(i));
            if (suballocatedIntVectorFindNamespaceContext != null) {
                int size2 = suballocatedIntVectorFindNamespaceContext.size();
                SuballocatedIntVector suballocatedIntVector3 = new SuballocatedIntVector(Math.max(Math.min(size2 + 16, 2048), 32));
                for (int i3 = 0; i3 < size2; i3++) {
                    suballocatedIntVector3.addElement(suballocatedIntVectorFindNamespaceContext.elementAt(i3));
                }
                suballocatedIntVector = suballocatedIntVector3;
            } else {
                suballocatedIntVector = new SuballocatedIntVector(32);
            }
            this.m_namespaceDeclSets.add(suballocatedIntVector);
        }
        int i_exptype = _exptype(i2);
        for (int size3 = suballocatedIntVector.size() - 1; size3 >= 0; size3--) {
            if (i_exptype == getExpandedTypeID(suballocatedIntVector.elementAt(size3))) {
                suballocatedIntVector.setElementAt(makeNodeHandle(i2), size3);
                return;
            }
        }
        suballocatedIntVector.addElement(makeNodeHandle(i2));
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public abstract void dispatchCharactersEvents(int i, ContentHandler contentHandler, boolean z) throws SAXException;

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public abstract void dispatchToEvents(int i, ContentHandler contentHandler) throws SAXException;

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void documentRegistration() {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void documentRelease() {
    }

    public void dumpDTM(OutputStream outputStream) {
        if (outputStream == null) {
            try {
                File file = new File("DTMDump" + hashCode() + ".txt");
                System.err.println("Dumping... " + file.getAbsolutePath());
                outputStream = new FileOutputStream(file);
            } catch (IOException e) {
                e.printStackTrace(System.err);
                f63.a(e.getMessage());
                return;
            }
        }
        PrintStream printStream = new PrintStream(outputStream);
        while (nextNode()) {
        }
        int i = this.m_size;
        printStream.println("Total nodes: " + i);
        for (int i2 = 0; i2 < i; i2++) {
            int iMakeNodeHandle = makeNodeHandle(i2);
            printStream.println("=========== index=" + i2 + " handle=" + iMakeNodeHandle + " ===========");
            StringBuilder sb = new StringBuilder();
            sb.append("NodeName: ");
            sb.append(getNodeName(iMakeNodeHandle));
            printStream.println(sb.toString());
            printStream.println("NodeNameX: " + getNodeNameX(iMakeNodeHandle));
            printStream.println("LocalName: " + getLocalName(iMakeNodeHandle));
            printStream.println("NamespaceURI: " + getNamespaceURI(iMakeNodeHandle));
            printStream.println("Prefix: " + getPrefix(iMakeNodeHandle));
            printStream.println("Expanded Type ID: " + Integer.toHexString(_exptype(i2)));
            String str = "DOCUMENT_NODE";
            switch (_type(i2)) {
                case -1:
                    str = "NULL";
                    break;
                case 0:
                default:
                    str = "Unknown!";
                    break;
                case 1:
                    str = "ELEMENT_NODE";
                    break;
                case 2:
                    str = "ATTRIBUTE_NODE";
                    break;
                case 3:
                    str = "TEXT_NODE";
                    break;
                case 4:
                    str = "CDATA_SECTION_NODE";
                    break;
                case 5:
                    str = "ENTITY_REFERENCE_NODE";
                    break;
                case 6:
                    str = "ENTITY_NODE";
                    break;
                case 7:
                    str = "PROCESSING_INSTRUCTION_NODE";
                    break;
                case 8:
                    str = "COMMENT_NODE";
                    break;
                case 9:
                case 10:
                    break;
                case 11:
                    str = "DOCUMENT_FRAGMENT_NODE";
                    break;
                case 12:
                    str = "NOTATION_NODE";
                    break;
                case 13:
                    str = "NAMESPACE_NODE";
                    break;
            }
            printStream.println("Type: " + str);
            int i_firstch = _firstch(i2);
            if (-1 == i_firstch) {
                printStream.println("First child: DTM.NULL");
            } else if (-2 == i_firstch) {
                printStream.println("First child: NOTPROCESSED");
            } else {
                printStream.println("First child: " + i_firstch);
            }
            if (this.m_prevsib != null) {
                int i_prevsib = _prevsib(i2);
                if (-1 == i_prevsib) {
                    printStream.println("Prev sibling: DTM.NULL");
                } else if (-2 == i_prevsib) {
                    printStream.println("Prev sibling: NOTPROCESSED");
                } else {
                    printStream.println("Prev sibling: " + i_prevsib);
                }
            }
            int i_nextsib = _nextsib(i2);
            if (-1 == i_nextsib) {
                printStream.println("Next sibling: DTM.NULL");
            } else if (-2 == i_nextsib) {
                printStream.println("Next sibling: NOTPROCESSED");
            } else {
                printStream.println("Next sibling: " + i_nextsib);
            }
            int i_parent = _parent(i2);
            if (-1 == i_parent) {
                printStream.println("Parent: DTM.NULL");
            } else if (-2 == i_parent) {
                printStream.println("Parent: NOTPROCESSED");
            } else {
                printStream.println("Parent: " + i_parent);
            }
            printStream.println("Level: " + _level(i2));
            printStream.println("Node Value: " + getNodeValue(iMakeNodeHandle));
            printStream.println("String Value: " + getStringValue(iMakeNodeHandle));
        }
    }

    public String dumpNode(int i) {
        String str;
        if (i == -1) {
            return "[null]";
        }
        switch (getNodeType(i)) {
            case -1:
                str = PsiKeyword.NULL;
                break;
            case 0:
            default:
                str = "Unknown!";
                break;
            case 1:
                str = "ELEMENT";
                break;
            case 2:
                str = "ATTR";
                break;
            case 3:
                str = "TEXT";
                break;
            case 4:
                str = "CDATA";
                break;
            case 5:
                str = "ENT_REF";
                break;
            case 6:
                str = SchemaSymbols.ATTVAL_ENTITY;
                break;
            case 7:
                str = "PI";
                break;
            case 8:
                str = "COMMENT";
                break;
            case 9:
                str = "DOC";
                break;
            case 10:
                str = "DOC_TYPE";
                break;
            case 11:
                str = "DOC_FRAG";
                break;
            case 12:
                str = SchemaSymbols.ATTVAL_NOTATION;
                break;
            case 13:
                str = "NAMESPACE";
                break;
        }
        return "[" + i + ": " + str + "(0x" + Integer.toHexString(getExpandedTypeID(i)) + ") " + getNodeNameX(i) + " {" + getNamespaceURI(i) + "}=\"" + getNodeValue(i) + "\"]";
    }

    public void ensureSizeOfIndex(int i, int i2) {
        int[][][] iArr = this.m_elemIndexes;
        if (iArr == null) {
            this.m_elemIndexes = new int[i + 20][][];
        } else if (iArr.length <= i) {
            int[][][] iArr2 = new int[i + 20][][];
            this.m_elemIndexes = iArr2;
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        }
        int[][][] iArr3 = this.m_elemIndexes;
        int[][] iArr4 = iArr3[i];
        if (iArr4 == null) {
            iArr4 = new int[i2 + 100][];
            iArr3[i] = iArr4;
        } else if (iArr4.length <= i2) {
            int[][] iArr5 = new int[i2 + 100][];
            System.arraycopy(iArr4, 0, iArr5, 0, iArr4.length);
            this.m_elemIndexes[i] = iArr5;
            iArr4 = iArr5;
        }
        int[] iArr6 = iArr4[i2];
        if (iArr6 == null) {
            int[] iArr7 = new int[128];
            iArr4[i2] = iArr7;
            iArr7[0] = 1;
            return;
        }
        int length = iArr6.length;
        int i3 = iArr6[0];
        if (length <= i3 + 1) {
            int[] iArr8 = new int[i3 + 1024];
            System.arraycopy(iArr6, 0, iArr8, 0, iArr6.length);
            iArr4[i2] = iArr8;
        }
    }

    public void error(String str) {
        throw new DTMException(str);
    }

    public int findElementFromIndex(int i, int i2, int i3) {
        int[][] iArr;
        int[] iArr2;
        int iFindGTE;
        int[][][] iArr3 = this.m_elemIndexes;
        if (iArr3 == null || i >= iArr3.length || (iArr = iArr3[i]) == null || i2 >= iArr.length || (iArr2 = iArr[i2]) == null || (iFindGTE = findGTE(iArr2, 1, iArr2[0], i3)) <= -1) {
            return -2;
        }
        return iArr2[iFindGTE];
    }

    public int findGTE(int[] iArr, int i, int i2, int i3) {
        int i4 = (i2 - 1) + i;
        int i5 = i4;
        while (i <= i5) {
            int i6 = (i + i5) >>> 1;
            int i7 = iArr[i6];
            if (i7 > i3) {
                i5 = i6 - 1;
            } else {
                if (i7 >= i3) {
                    return i6;
                }
                i = i6 + 1;
            }
        }
        if (i > i4 || iArr[i] <= i3) {
            return -1;
        }
        return i;
    }

    public int findInSortedSuballocatedIntVector(SuballocatedIntVector suballocatedIntVector, int i) {
        int i2 = 0;
        if (suballocatedIntVector != null) {
            int size = suballocatedIntVector.size() - 1;
            int i3 = 0;
            while (i2 <= size) {
                i3 = (i2 + size) / 2;
                int iElementAt = i - suballocatedIntVector.elementAt(i3);
                if (iElementAt == 0) {
                    return i3;
                }
                if (iElementAt < 0) {
                    size = i3 - 1;
                } else {
                    i2 = i3 + 1;
                }
            }
            if (i2 <= i3) {
                i2 = i3;
            }
        }
        return (-1) - i2;
    }

    public SuballocatedIntVector findNamespaceContext(int i) {
        int i_firstch;
        SuballocatedIntVector suballocatedIntVector = this.m_namespaceDeclSetElements;
        if (suballocatedIntVector != null) {
            int iFindInSortedSuballocatedIntVector = findInSortedSuballocatedIntVector(suballocatedIntVector, i);
            if (iFindInSortedSuballocatedIntVector < 0) {
                if (iFindInSortedSuballocatedIntVector != -1) {
                    int i2 = (-2) - iFindInSortedSuballocatedIntVector;
                    int iElementAt = this.m_namespaceDeclSetElements.elementAt(i2);
                    int i_parent = _parent(i);
                    if (i2 == 0 && iElementAt < i_parent) {
                        int documentRoot = getDocumentRoot(makeNodeHandle(i));
                        int iMakeNodeIdentity = makeNodeIdentity(documentRoot);
                        if (getNodeType(documentRoot) == 9 && (i_firstch = _firstch(iMakeNodeIdentity)) != -1) {
                            iMakeNodeIdentity = i_firstch;
                        }
                        if (iElementAt == iMakeNodeIdentity) {
                            return this.m_namespaceDeclSets.get(i2);
                        }
                    }
                    while (i2 >= 0 && i_parent > 0) {
                        if (iElementAt != i_parent) {
                            if (iElementAt >= i_parent) {
                                if (i2 <= 0) {
                                    break;
                                }
                                i2--;
                                iElementAt = this.m_namespaceDeclSetElements.elementAt(i2);
                            } else {
                                do {
                                    i_parent = _parent(i_parent);
                                } while (iElementAt < i_parent);
                            }
                        } else {
                            return this.m_namespaceDeclSets.get(i2);
                        }
                    }
                } else {
                    return null;
                }
            } else {
                return this.m_namespaceDeclSets.get(iFindInSortedSuballocatedIntVector);
            }
        }
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public abstract int getAttributeNode(int i, String str, String str2);

    public SuballocatedIntVector getDTMIDs() {
        if (this.m_mgr == null) {
            return null;
        }
        return this.m_dtmIdent;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getDocument() {
        return this.m_dtmIdent.elementAt(0);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean getDocumentAllDeclarationsProcessed() {
        return true;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentBaseURI() {
        return this.m_documentBaseURI;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentEncoding(int i) {
        return "UTF-8";
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getDocumentRoot(int i) {
        return getManager().getDTM(i).getDocument();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentStandalone(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentSystemIdentifier(int i) {
        return this.m_documentBaseURI;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public abstract String getDocumentTypeDeclarationPublicIdentifier();

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public abstract String getDocumentTypeDeclarationSystemIdentifier();

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getDocumentVersion(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public abstract int getElementById(String str);

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getExpandedTypeID(int i) {
        int iMakeNodeIdentity = makeNodeIdentity(i);
        if (iMakeNodeIdentity == -1) {
            return -1;
        }
        return _exptype(iMakeNodeIdentity);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getFirstAttribute(int i) {
        return makeNodeHandle(getFirstAttributeIdentity(makeNodeIdentity(i)));
    }

    public int getFirstAttributeIdentity(int i) {
        short s_type;
        if (1 == _type(i)) {
            do {
                i = getNextNodeIdentity(i);
                if (-1 == i) {
                    break;
                }
                s_type = _type(i);
                if (s_type == 2) {
                    return i;
                }
            } while (13 == s_type);
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getFirstChild(int i) {
        return makeNodeHandle(_firstch(makeNodeIdentity(i)));
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getFirstNamespaceNode(int i, boolean z) {
        short s_type;
        SuballocatedIntVector suballocatedIntVectorFindNamespaceContext;
        if (z) {
            int iMakeNodeIdentity = makeNodeIdentity(i);
            if (_type(iMakeNodeIdentity) != 1 || (suballocatedIntVectorFindNamespaceContext = findNamespaceContext(iMakeNodeIdentity)) == null || suballocatedIntVectorFindNamespaceContext.size() < 1) {
                return -1;
            }
            return suballocatedIntVectorFindNamespaceContext.elementAt(0);
        }
        int iMakeNodeIdentity2 = makeNodeIdentity(i);
        if (_type(iMakeNodeIdentity2) == 1) {
            do {
                iMakeNodeIdentity2 = getNextNodeIdentity(iMakeNodeIdentity2);
                if (-1 == iMakeNodeIdentity2) {
                    break;
                }
                s_type = _type(iMakeNodeIdentity2);
                if (s_type == 13) {
                    return makeNodeHandle(iMakeNodeIdentity2);
                }
            } while (2 == s_type);
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getLastChild(int i) {
        int i_firstch = _firstch(makeNodeIdentity(i));
        int i2 = -1;
        while (i_firstch != -1) {
            i2 = i_firstch;
            i_firstch = _nextsib(i_firstch);
        }
        return makeNodeHandle(i2);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public short getLevel(int i) {
        return (short) (_level(makeNodeIdentity(i)) + 1);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public abstract String getLocalName(int i);

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getLocalNameFromExpandedNameID(int i) {
        return this.m_expandedNameTable.getLocalName(i);
    }

    public DTMManager getManager() {
        return this.m_mgr;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getNamespaceFromExpandedNameID(int i) {
        return this.m_expandedNameTable.getNamespace(i);
    }

    public int getNamespaceType(int i) {
        return this.m_expandedNameTable.getNamespaceID(_exptype(makeNodeIdentity(i)));
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public abstract String getNamespaceURI(int i);

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getNextAttribute(int i) {
        int iMakeNodeIdentity = makeNodeIdentity(i);
        if (_type(iMakeNodeIdentity) == 2) {
            return makeNodeHandle(getNextAttributeIdentity(iMakeNodeIdentity));
        }
        return -1;
    }

    public int getNextAttributeIdentity(int i) {
        short s_type;
        do {
            i = getNextNodeIdentity(i);
            if (-1 == i) {
                break;
            }
            s_type = _type(i);
            if (s_type == 2) {
                return i;
            }
        } while (s_type == 13);
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getNextNamespaceNode(int i, int i2, boolean z) {
        short s_type;
        int iIndexOf;
        if (z) {
            SuballocatedIntVector suballocatedIntVectorFindNamespaceContext = findNamespaceContext(makeNodeIdentity(i));
            if (suballocatedIntVectorFindNamespaceContext == null || (iIndexOf = suballocatedIntVectorFindNamespaceContext.indexOf(i2) + 1) <= 0 || iIndexOf == suballocatedIntVectorFindNamespaceContext.size()) {
                return -1;
            }
            return suballocatedIntVectorFindNamespaceContext.elementAt(iIndexOf);
        }
        int iMakeNodeIdentity = makeNodeIdentity(i2);
        do {
            iMakeNodeIdentity = getNextNodeIdentity(iMakeNodeIdentity);
            if (-1 == iMakeNodeIdentity) {
                break;
            }
            s_type = _type(iMakeNodeIdentity);
            if (s_type == 13) {
                return makeNodeHandle(iMakeNodeIdentity);
            }
        } while (s_type == 2);
        return -1;
    }

    public abstract int getNextNodeIdentity(int i);

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getNextSibling(int i) {
        if (i == -1) {
            return -1;
        }
        return makeNodeHandle(_nextsib(makeNodeIdentity(i)));
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public Node getNode(int i) {
        return new DTMNodeProxy(this, i);
    }

    public int getNodeHandle(int i) {
        return makeNodeHandle(i);
    }

    public int getNodeIdent(int i) {
        return makeNodeIdentity(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public abstract String getNodeName(int i);

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeNameX(int i) {
        error(XMLMessages.createXMLMessage("ER_METHOD_NOT_SUPPORTED", null));
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public short getNodeType(int i) {
        if (i == -1) {
            return (short) -1;
        }
        return this.m_expandedNameTable.getType(_exptype(makeNodeIdentity(i)));
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public abstract String getNodeValue(int i);

    public abstract int getNumberOfNodes();

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getOwnerDocument(int i) {
        if (9 == getNodeType(i)) {
            return -1;
        }
        return getDocumentRoot(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getParent(int i) {
        int iMakeNodeIdentity = makeNodeIdentity(i);
        if (iMakeNodeIdentity > 0) {
            return makeNodeHandle(_parent(iMakeNodeIdentity));
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public abstract String getPrefix(int i);

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getPreviousSibling(int i) {
        int i2 = -1;
        if (i == -1) {
            return -1;
        }
        if (this.m_prevsib != null) {
            return makeNodeHandle(_prevsib(makeNodeIdentity(i)));
        }
        int iMakeNodeIdentity = makeNodeIdentity(i);
        int i_firstch = _firstch(_parent(iMakeNodeIdentity));
        while (true) {
            int i3 = i_firstch;
            int i4 = i2;
            i2 = i3;
            if (i2 == iMakeNodeIdentity) {
                return makeNodeHandle(i4);
            }
            i_firstch = _nextsib(i2);
        }
    }

    public boolean getShouldStripWhitespace() {
        return this.m_shouldStripWS;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public abstract XMLString getStringValue(int i);

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public char[] getStringValueChunk(int i, int i2, int[] iArr) {
        error(XMLMessages.createXMLMessage("ER_METHOD_NOT_SUPPORTED", null));
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getStringValueChunkCount(int i) {
        error(XMLMessages.createXMLMessage("ER_METHOD_NOT_SUPPORTED", null));
        return 0;
    }

    public int getTypedAttribute(int i, int i2) {
        if (1 == getNodeType(i)) {
            int iMakeNodeIdentity = makeNodeIdentity(i);
            while (true) {
                iMakeNodeIdentity = getNextNodeIdentity(iMakeNodeIdentity);
                if (-1 == iMakeNodeIdentity) {
                    break;
                }
                short s_type = _type(iMakeNodeIdentity);
                if (s_type == 2) {
                    if (_exptype(iMakeNodeIdentity) == i2) {
                        return makeNodeHandle(iMakeNodeIdentity);
                    }
                } else if (13 != s_type) {
                    break;
                }
            }
        }
        return -1;
    }

    public int getTypedFirstChild(int i, int i2) {
        if (i2 < 14) {
            int i_firstch = _firstch(makeNodeIdentity(i));
            while (i_firstch != -1) {
                int i_exptype = _exptype(i_firstch);
                if (i_exptype == i2 || (i_exptype >= 14 && this.m_expandedNameTable.getType(i_exptype) == i2)) {
                    return makeNodeHandle(i_firstch);
                }
                i_firstch = _nextsib(i_firstch);
            }
        } else {
            int i_firstch2 = _firstch(makeNodeIdentity(i));
            while (i_firstch2 != -1) {
                if (_exptype(i_firstch2) == i2) {
                    return makeNodeHandle(i_firstch2);
                }
                i_firstch2 = _nextsib(i_firstch2);
            }
        }
        return -1;
    }

    public int getTypedNextSibling(int i, int i2) {
        int i_exptype;
        if (i == -1) {
            return -1;
        }
        int iMakeNodeIdentity = makeNodeIdentity(i);
        do {
            iMakeNodeIdentity = _nextsib(iMakeNodeIdentity);
            if (iMakeNodeIdentity == -1 || (i_exptype = _exptype(iMakeNodeIdentity)) == i2) {
                break;
            }
        } while (this.m_expandedNameTable.getType(i_exptype) != i2);
        if (iMakeNodeIdentity == -1) {
            return -1;
        }
        return makeNodeHandle(iMakeNodeIdentity);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public abstract String getUnparsedEntityURI(String str);

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean hasChildNodes(int i) {
        return _firstch(makeNodeIdentity(i)) != -1;
    }

    public void indexNode(int i, int i2) {
        ExpandedNameTable expandedNameTable = this.m_expandedNameTable;
        if (1 == expandedNameTable.getType(i)) {
            int namespaceID = expandedNameTable.getNamespaceID(i);
            int localNameID = expandedNameTable.getLocalNameID(i);
            ensureSizeOfIndex(namespaceID, localNameID);
            int[] iArr = this.m_elemIndexes[namespaceID][localNameID];
            iArr[iArr[0]] = i2;
            iArr[0] = iArr[0] + 1;
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public abstract boolean isAttributeSpecified(int i);

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean isCharacterElementContentWhitespace(int i) {
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean isDocumentAllDeclarationsProcessed(int i) {
        return true;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean isNodeAfter(int i, int i2) {
        int iMakeNodeIdentity = makeNodeIdentity(i);
        int iMakeNodeIdentity2 = makeNodeIdentity(i2);
        return (iMakeNodeIdentity == -1 || iMakeNodeIdentity2 == -1 || iMakeNodeIdentity > iMakeNodeIdentity2) ? false : true;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean isSupported(String str, String str2) {
        return false;
    }

    public final int makeNodeHandle(int i) {
        if (-1 == i) {
            return -1;
        }
        return this.m_dtmIdent.elementAt(i >>> 16) + (i & 65535);
    }

    public final int makeNodeIdentity(int i) {
        if (-1 == i) {
            return -1;
        }
        DTMManagerDefault dTMManagerDefault = this.m_mgrDefault;
        if (dTMManagerDefault != null) {
            int i2 = i >>> 16;
            if (dTMManagerDefault.m_dtms[i2] != this) {
                return -1;
            }
            return dTMManagerDefault.m_dtm_offsets[i2] | (i & 65535);
        }
        int iIndexOf = this.m_dtmIdent.indexOf((-65536) & i);
        if (iIndexOf == -1) {
            return -1;
        }
        return (iIndexOf << 16) + (i & 65535);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void migrateTo(DTMManager dTMManager) {
        this.m_mgr = dTMManager;
        if (dTMManager instanceof DTMManagerDefault) {
            this.m_mgrDefault = (DTMManagerDefault) dTMManager;
        }
    }

    public abstract boolean nextNode();

    public void popShouldStripWhitespace() {
        BoolStack boolStack = this.m_shouldStripWhitespaceStack;
        if (boolStack != null) {
            this.m_shouldStripWS = boolStack.popAndTop();
        }
    }

    public void pushShouldStripWhitespace(boolean z) {
        this.m_shouldStripWS = z;
        BoolStack boolStack = this.m_shouldStripWhitespaceStack;
        if (boolStack != null) {
            boolStack.push(z);
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void setDocumentBaseURI(String str) {
        this.m_documentBaseURI = str;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public void setFeature(String str, boolean z) {
    }

    public void setShouldStripWhitespace(boolean z) {
        this.m_shouldStripWS = z;
        BoolStack boolStack = this.m_shouldStripWhitespaceStack;
        if (boolStack != null) {
            boolStack.setTop(z);
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public boolean supportsPreStripping() {
        return true;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public int getExpandedTypeID(String str, String str2, int i) {
        return this.m_expandedNameTable.getExpandedTypeID(str, str2, i);
    }

    public DTMDefaultBase(DTMManager dTMManager, Source source, int i, DTMWSFilter dTMWSFilter, XMLStringFactory xMLStringFactory, boolean z) {
        this(dTMManager, source, i, dTMWSFilter, xMLStringFactory, z, 512, true, false);
    }
}
