package com.sun.org.apache.xerces.internal.dom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DeferredDocumentImpl extends DocumentImpl implements DeferredNode {
    protected static final int CHUNK_MASK = 255;
    protected static final int CHUNK_SHIFT = 8;
    protected static final int CHUNK_SIZE = 256;
    private static final boolean DEBUG_IDS = false;
    private static final boolean DEBUG_PRINT_REF_COUNTS = false;
    private static final boolean DEBUG_PRINT_TABLES = false;
    protected static final int INITIAL_CHUNK_COUNT = 32;
    private static final int[] INIT_ARRAY = new int[257];
    static final long serialVersionUID = 5186323580749626857L;
    private final transient StringBuilder fBufferStr;
    protected transient int fIdCount;
    protected transient int[] fIdElement;
    protected transient String[] fIdName;
    protected boolean fNamespacesEnabled;
    protected transient int fNodeCount;
    protected transient int[][] fNodeExtra;
    protected transient int[][] fNodeLastChild;
    protected transient Object[][] fNodeName;
    protected transient int[][] fNodeParent;
    protected transient int[][] fNodePrevSib;
    protected transient int[][] fNodeType;
    protected transient Object[][] fNodeURI;
    protected transient Object[][] fNodeValue;
    private final transient List<String> fStrChunks;

    public static final class IntVector {
        private int[] data;
        private int size;

        private void ensureCapacity(int i) {
            int[] iArr = this.data;
            if (iArr == null) {
                this.data = new int[i + 15];
            } else if (i > iArr.length) {
                int[] iArr2 = new int[i + 15];
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                this.data = iArr2;
            }
        }

        public void addElement(int i) {
            ensureCapacity(this.size + 1);
            int[] iArr = this.data;
            int i2 = this.size;
            this.size = i2 + 1;
            iArr[i2] = i;
        }

        public int elementAt(int i) {
            return this.data[i];
        }

        public void removeAllElements() {
            this.size = 0;
        }

        public int size() {
            return this.size;
        }
    }

    public static final class RefCount {
        int fCount;
    }

    static {
        for (int i = 0; i < 256; i++) {
            INIT_ARRAY[i] = -1;
        }
    }

    public DeferredDocumentImpl(boolean z, boolean z2) {
        super(z2);
        this.fBufferStr = new StringBuilder();
        this.fStrChunks = new ArrayList();
        this.fNodeCount = 0;
        this.fNamespacesEnabled = false;
        needsSyncData(true);
        needsSyncChildren(true);
        this.fNamespacesEnabled = z;
    }

    public static int binarySearch(int[] iArr, int i, int i2, int i3) {
        while (i <= i2) {
            int i4 = (i + i2) >>> 1;
            int i5 = iArr[i4];
            if (i5 == i3) {
                while (i4 > 0 && iArr[i4 - 1] == i3) {
                    i4--;
                }
                return i4;
            }
            if (i5 > i3) {
                i2 = i4 - 1;
            } else {
                i = i4 + 1;
            }
        }
        return -1;
    }

    private final int clearChunkIndex(int[][] iArr, int i, int i2) {
        int[] iArr2 = iArr[i];
        int i3 = iArr2 != null ? iArr2[i2] : -1;
        if (i3 != -1) {
            iArr2[256] = iArr2[256] - 1;
            iArr2[i2] = -1;
            if (iArr2[256] == 0) {
                iArr[i] = null;
            }
        }
        return i3;
    }

    private final String clearChunkValue(Object[][] objArr, int i, int i2) {
        Object[] objArr2 = objArr[i];
        String str = objArr2 != null ? (String) objArr2[i2] : null;
        if (str != null) {
            objArr2[i2] = null;
            RefCount refCount = (RefCount) objArr[i][256];
            int i3 = refCount.fCount - 1;
            refCount.fCount = i3;
            if (i3 == 0) {
                objArr[i] = null;
            }
        }
        return str;
    }

    private final void createChunk(Object[][] objArr, int i) {
        Object[] objArr2 = new Object[257];
        objArr[i] = objArr2;
        objArr2[256] = new RefCount();
    }

    private final int getChunkIndex(int[][] iArr, int i, int i2) {
        int[] iArr2 = iArr[i];
        if (iArr2 != null) {
            return iArr2[i2];
        }
        return -1;
    }

    private final String getChunkValue(Object[][] objArr, int i, int i2) {
        Object[] objArr2 = objArr[i];
        if (objArr2 != null) {
            return (String) objArr2[i2];
        }
        return null;
    }

    private static void print(int[] iArr, int i, int i2, int i3, int i4) {
    }

    private final void putIdentifier0(String str, Element element) {
        if (this.identifiers == null) {
            this.identifiers = new HashMap();
        }
        this.identifiers.put(str, element);
    }

    private final int setChunkIndex(int[][] iArr, int i, int i2, int i3) {
        if (i == -1) {
            return clearChunkIndex(iArr, i2, i3);
        }
        int[] iArr2 = iArr[i2];
        if (iArr2 == null) {
            createChunk(iArr, i2);
            iArr2 = iArr[i2];
        }
        int i4 = iArr2[i3];
        if (i4 == -1) {
            iArr2[256] = iArr2[256] + 1;
        }
        iArr2[i3] = i;
        return i4;
    }

    private final String setChunkValue(Object[][] objArr, Object obj, int i, int i2) {
        if (obj == null) {
            return clearChunkValue(objArr, i, i2);
        }
        Object[] objArr2 = objArr[i];
        if (objArr2 == null) {
            createChunk(objArr, i);
            objArr2 = objArr[i];
        }
        String str = (String) objArr2[i2];
        if (str == null) {
            ((RefCount) objArr2[256]).fCount++;
        }
        objArr2[i2] = obj;
        return str;
    }

    public void appendChild(int i, int i2) {
        int i3 = i >> 8;
        int i4 = i & 255;
        int i5 = i2 >> 8;
        int i6 = i2 & 255;
        setChunkIndex(this.fNodeParent, i, i5, i6);
        setChunkIndex(this.fNodePrevSib, getChunkIndex(this.fNodeLastChild, i3, i4), i5, i6);
        setChunkIndex(this.fNodeLastChild, i2, i3, i4);
    }

    public int cloneNode(int i, boolean z) {
        int i2 = i >> 8;
        int i3 = i & 255;
        int i4 = this.fNodeType[i2][i3];
        int iCreateNode = createNode((short) i4);
        int i5 = iCreateNode >> 8;
        int i6 = iCreateNode & 255;
        Object[][] objArr = this.fNodeName;
        setChunkValue(objArr, objArr[i2][i3], i5, i6);
        Object[][] objArr2 = this.fNodeValue;
        setChunkValue(objArr2, objArr2[i2][i3], i5, i6);
        Object[][] objArr3 = this.fNodeURI;
        setChunkValue(objArr3, objArr3[i2][i3], i5, i6);
        int iCloneNode = this.fNodeExtra[i2][i3];
        if (iCloneNode != -1) {
            if (i4 != 2 && i4 != 3) {
                iCloneNode = cloneNode(iCloneNode, false);
            }
            setChunkIndex(this.fNodeExtra, iCloneNode, i5, i6);
        }
        if (z) {
            int lastChild = getLastChild(i, false);
            int i7 = -1;
            while (lastChild != -1) {
                int iCloneNode2 = cloneNode(lastChild, z);
                insertBefore(iCreateNode, iCloneNode2, i7);
                lastChild = getRealPrevSibling(lastChild, false);
                i7 = iCloneNode2;
            }
        }
        return iCreateNode;
    }

    public int createDeferredAttribute(String str, String str2, String str3, boolean z) {
        int iCreateNode = createNode((short) 2);
        int i = iCreateNode >> 8;
        int i2 = iCreateNode & 255;
        setChunkValue(this.fNodeName, str, i, i2);
        setChunkValue(this.fNodeURI, str2, i, i2);
        setChunkValue(this.fNodeValue, str3, i, i2);
        setChunkIndex(this.fNodeExtra, z ? 32 : 0, i, i2);
        return iCreateNode;
    }

    public int createDeferredCDATASection(String str) {
        int iCreateNode = createNode((short) 4);
        setChunkValue(this.fNodeValue, str, iCreateNode >> 8, iCreateNode & 255);
        return iCreateNode;
    }

    public int createDeferredComment(String str) {
        int iCreateNode = createNode((short) 8);
        setChunkValue(this.fNodeValue, str, iCreateNode >> 8, iCreateNode & 255);
        return iCreateNode;
    }

    public int createDeferredDocument() {
        return createNode((short) 9);
    }

    public int createDeferredDocumentType(String str, String str2, String str3) {
        int iCreateNode = createNode((short) 10);
        int i = iCreateNode >> 8;
        int i2 = iCreateNode & 255;
        setChunkValue(this.fNodeName, str, i, i2);
        setChunkValue(this.fNodeValue, str2, i, i2);
        setChunkValue(this.fNodeURI, str3, i, i2);
        return iCreateNode;
    }

    @Deprecated
    public int createDeferredElement(String str, String str2, Object obj) {
        int iCreateNode = createNode((short) 1);
        int i = iCreateNode >> 8;
        int i2 = iCreateNode & 255;
        setChunkValue(this.fNodeName, str2, i, i2);
        setChunkValue(this.fNodeURI, str, i, i2);
        setChunkValue(this.fNodeValue, obj, i, i2);
        return iCreateNode;
    }

    public int createDeferredElementDefinition(String str) {
        int iCreateNode = createNode((short) 21);
        setChunkValue(this.fNodeName, str, iCreateNode >> 8, iCreateNode & 255);
        return iCreateNode;
    }

    public int createDeferredEntity(String str, String str2, String str3, String str4, String str5) {
        int iCreateNode = createNode((short) 6);
        int i = iCreateNode >> 8;
        int i2 = iCreateNode & 255;
        int iCreateNode2 = createNode((short) 6);
        int i3 = iCreateNode2 >> 8;
        int i4 = iCreateNode2 & 255;
        setChunkValue(this.fNodeName, str, i, i2);
        setChunkValue(this.fNodeValue, str2, i, i2);
        setChunkValue(this.fNodeURI, str3, i, i2);
        setChunkIndex(this.fNodeExtra, iCreateNode2, i, i2);
        setChunkValue(this.fNodeName, str4, i3, i4);
        setChunkValue(this.fNodeValue, null, i3, i4);
        setChunkValue(this.fNodeURI, null, i3, i4);
        int iCreateNode3 = createNode((short) 6);
        setChunkIndex(this.fNodeExtra, iCreateNode3, i3, i4);
        setChunkValue(this.fNodeName, str5, iCreateNode3 >> 8, iCreateNode3 & 255);
        return iCreateNode;
    }

    public int createDeferredEntityReference(String str, String str2) {
        int iCreateNode = createNode((short) 5);
        int i = iCreateNode >> 8;
        int i2 = iCreateNode & 255;
        setChunkValue(this.fNodeName, str, i, i2);
        setChunkValue(this.fNodeValue, str2, i, i2);
        return iCreateNode;
    }

    public int createDeferredNotation(String str, String str2, String str3, String str4) {
        int iCreateNode = createNode((short) 12);
        int i = iCreateNode >> 8;
        int i2 = iCreateNode & 255;
        int iCreateNode2 = createNode((short) 12);
        setChunkValue(this.fNodeName, str, i, i2);
        setChunkValue(this.fNodeValue, str2, i, i2);
        setChunkValue(this.fNodeURI, str3, i, i2);
        setChunkIndex(this.fNodeExtra, iCreateNode2, i, i2);
        setChunkValue(this.fNodeName, str4, iCreateNode2 >> 8, iCreateNode2 & 255);
        return iCreateNode;
    }

    public int createDeferredProcessingInstruction(String str, String str2) {
        int iCreateNode = createNode((short) 7);
        int i = iCreateNode >> 8;
        int i2 = iCreateNode & 255;
        setChunkValue(this.fNodeName, str, i, i2);
        setChunkValue(this.fNodeValue, str2, i, i2);
        return iCreateNode;
    }

    public int createDeferredTextNode(String str, boolean z) {
        int iCreateNode = createNode((short) 3);
        int i = iCreateNode >> 8;
        int i2 = iCreateNode & 255;
        setChunkValue(this.fNodeValue, str, i, i2);
        setChunkIndex(this.fNodeExtra, z ? 1 : 0, i, i2);
        return iCreateNode;
    }

    public int createNode(short s) {
        int i = this.fNodeCount;
        int i2 = i >> 8;
        ensureCapacity(i2);
        setChunkIndex(this.fNodeType, s, i2, i & 255);
        int i3 = this.fNodeCount;
        this.fNodeCount = i3 + 1;
        return i3;
    }

    public void ensureCapacity(int i) {
        int[][] iArr = this.fNodeType;
        if (iArr == null) {
            this.fNodeType = new int[32][];
            this.fNodeName = new Object[32][];
            this.fNodeValue = new Object[32][];
            this.fNodeParent = new int[32][];
            this.fNodeLastChild = new int[32][];
            this.fNodePrevSib = new int[32][];
            this.fNodeURI = new Object[32][];
            this.fNodeExtra = new int[32][];
        } else if (iArr.length <= i) {
            int i2 = i * 2;
            int[][] iArr2 = new int[i2][];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            this.fNodeType = iArr2;
            Object[][] objArr = new Object[i2][];
            System.arraycopy(this.fNodeName, 0, objArr, 0, i);
            this.fNodeName = objArr;
            Object[][] objArr2 = new Object[i2][];
            System.arraycopy(this.fNodeValue, 0, objArr2, 0, i);
            this.fNodeValue = objArr2;
            int[][] iArr3 = new int[i2][];
            System.arraycopy(this.fNodeParent, 0, iArr3, 0, i);
            this.fNodeParent = iArr3;
            int[][] iArr4 = new int[i2][];
            System.arraycopy(this.fNodeLastChild, 0, iArr4, 0, i);
            this.fNodeLastChild = iArr4;
            int[][] iArr5 = new int[i2][];
            System.arraycopy(this.fNodePrevSib, 0, iArr5, 0, i);
            this.fNodePrevSib = iArr5;
            Object[][] objArr3 = new Object[i2][];
            System.arraycopy(this.fNodeURI, 0, objArr3, 0, i);
            this.fNodeURI = objArr3;
            int[][] iArr6 = new int[i2][];
            System.arraycopy(this.fNodeExtra, 0, iArr6, 0, i);
            this.fNodeExtra = iArr6;
        } else if (iArr[i] != null) {
            return;
        }
        createChunk(this.fNodeType, i);
        createChunk(this.fNodeName, i);
        createChunk(this.fNodeValue, i);
        createChunk(this.fNodeParent, i);
        createChunk(this.fNodeLastChild, i);
        createChunk(this.fNodePrevSib, i);
        createChunk(this.fNodeURI, i);
        createChunk(this.fNodeExtra, i);
    }

    public String getAttribute(int i, String str) {
        if (i != -1 && str != null) {
            int chunkIndex = getChunkIndex(this.fNodeExtra, i >> 8, i & 255);
            while (chunkIndex != -1) {
                int i2 = chunkIndex >> 8;
                int i3 = chunkIndex & 255;
                if (getChunkValue(this.fNodeName, i2, i3) == str) {
                    return getChunkValue(this.fNodeValue, i2, i3);
                }
                chunkIndex = getChunkIndex(this.fNodePrevSib, i2, i3);
            }
        }
        return null;
    }

    public String getDeferredEntityBaseURI(int i) {
        if (i != -1) {
            return getNodeName(getNodeExtra(getNodeExtra(i, false), false), false);
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.DocumentImpl, com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl, org.w3c.dom.Document
    public DOMImplementation getImplementation() {
        return DeferredDOMImplementationImpl.getDOMImplementation();
    }

    public int getLastChild(int i, boolean z) {
        if (i == -1) {
            return -1;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        int[][] iArr = this.fNodeLastChild;
        return z ? clearChunkIndex(iArr, i2, i3) : getChunkIndex(iArr, i2, i3);
    }

    public boolean getNamespacesEnabled() {
        return this.fNamespacesEnabled;
    }

    public int getNodeExtra(int i, boolean z) {
        if (i == -1) {
            return -1;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        int[][] iArr = this.fNodeExtra;
        return z ? clearChunkIndex(iArr, i2, i3) : getChunkIndex(iArr, i2, i3);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.DeferredNode
    public int getNodeIndex() {
        return 0;
    }

    public String getNodeName(int i, boolean z) {
        if (i == -1) {
            return null;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        Object[][] objArr = this.fNodeName;
        return z ? clearChunkValue(objArr, i2, i3) : getChunkValue(objArr, i2, i3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.sun.org.apache.xerces.internal.dom.DeferredNode, org.w3c.dom.Element] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl, com.sun.org.apache.xerces.internal.dom.DeferredDocumentImpl, com.sun.org.apache.xerces.internal.dom.DeferredNode] */
    public DeferredNode getNodeObject(int i) {
        if (i == -1) {
            return null;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        int chunkIndex = getChunkIndex(this.fNodeType, i2, i3);
        if (chunkIndex != 3 && chunkIndex != 4) {
            clearChunkIndex(this.fNodeType, i2, i3);
        }
        if (chunkIndex == 12) {
            return new DeferredNotationImpl(this, i);
        }
        if (chunkIndex == 21) {
            return new DeferredElementDefinitionImpl(this, i);
        }
        switch (chunkIndex) {
            case 1:
                ?? deferredElementNSImpl = this.fNamespacesEnabled ? new DeferredElementNSImpl(this, i) : new DeferredElementImpl(this, i);
                int[] iArr = this.fIdElement;
                if (iArr != null) {
                    int iBinarySearch = binarySearch(iArr, 0, this.fIdCount - 1, i);
                    while (iBinarySearch != -1) {
                        String str = this.fIdName[iBinarySearch];
                        if (str != null) {
                            putIdentifier0(str, deferredElementNSImpl);
                            this.fIdName[iBinarySearch] = null;
                        }
                        iBinarySearch++;
                        if (iBinarySearch >= this.fIdCount || this.fIdElement[iBinarySearch] != i) {
                            iBinarySearch = -1;
                        }
                    }
                }
                return deferredElementNSImpl;
            case 2:
                return this.fNamespacesEnabled ? new DeferredAttrNSImpl(this, i) : new DeferredAttrImpl(this, i);
            case 3:
                return new DeferredTextImpl(this, i);
            case 4:
                return new DeferredCDATASectionImpl(this, i);
            case 5:
                return new DeferredEntityReferenceImpl(this, i);
            case 6:
                return new DeferredEntityImpl(this, i);
            case 7:
                return new DeferredProcessingInstructionImpl(this, i);
            case 8:
                return new DeferredCommentImpl(this, i);
            case 9:
                return this;
            case 10:
                DeferredDocumentTypeImpl deferredDocumentTypeImpl = new DeferredDocumentTypeImpl(this, i);
                this.docType = deferredDocumentTypeImpl;
                return deferredDocumentTypeImpl;
            default:
                qf1.a("type: ", chunkIndex);
                return null;
        }
    }

    public short getNodeType(int i, boolean z) {
        if (i == -1) {
            return (short) -1;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        int[][] iArr = this.fNodeType;
        return (short) (z ? clearChunkIndex(iArr, i2, i3) : getChunkIndex(iArr, i2, i3));
    }

    public String getNodeURI(int i, boolean z) {
        if (i == -1) {
            return null;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        Object[][] objArr = this.fNodeURI;
        return z ? clearChunkValue(objArr, i2, i3) : getChunkValue(objArr, i2, i3);
    }

    public String getNodeValue(int i, boolean z) {
        if (i == -1) {
            return null;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        Object[][] objArr = this.fNodeValue;
        return z ? clearChunkValue(objArr, i2, i3) : getChunkValue(objArr, i2, i3);
    }

    public String getNodeValueString(int i, boolean z) {
        int lastChild;
        if (i == -1) {
            return null;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        Object[][] objArr = this.fNodeValue;
        String strClearChunkValue = z ? clearChunkValue(objArr, i2, i3) : getChunkValue(objArr, i2, i3);
        if (strClearChunkValue == null) {
            return null;
        }
        int chunkIndex = getChunkIndex(this.fNodeType, i2, i3);
        if (chunkIndex == 3) {
            int realPrevSibling = getRealPrevSibling(i);
            if (realPrevSibling != -1 && getNodeType(realPrevSibling, false) == 3) {
                this.fStrChunks.add(strClearChunkValue);
                do {
                    int i4 = realPrevSibling >> 8;
                    int i5 = realPrevSibling & 255;
                    this.fStrChunks.add(getChunkValue(this.fNodeValue, i4, i5));
                    realPrevSibling = getChunkIndex(this.fNodePrevSib, i4, i5);
                    if (realPrevSibling == -1) {
                        break;
                    }
                } while (getNodeType(realPrevSibling, false) == 3);
                int size = this.fStrChunks.size();
                while (true) {
                    size--;
                    StringBuilder sb = this.fBufferStr;
                    if (size < 0) {
                        String string = sb.toString();
                        this.fStrChunks.clear();
                        this.fBufferStr.setLength(0);
                        return string;
                    }
                    sb.append(this.fStrChunks.get(size));
                }
            }
        } else if (chunkIndex == 4 && (lastChild = getLastChild(i, false)) != -1) {
            this.fBufferStr.append(strClearChunkValue);
            while (lastChild != -1) {
                int i6 = lastChild >> 8;
                int i7 = lastChild & 255;
                this.fStrChunks.add(getChunkValue(this.fNodeValue, i6, i7));
                lastChild = getChunkIndex(this.fNodePrevSib, i6, i7);
            }
            int size2 = this.fStrChunks.size();
            while (true) {
                size2--;
                StringBuilder sb2 = this.fBufferStr;
                if (size2 < 0) {
                    String string2 = sb2.toString();
                    this.fStrChunks.clear();
                    this.fBufferStr.setLength(0);
                    return string2;
                }
                sb2.append(this.fStrChunks.get(size2));
            }
        }
        return strClearChunkValue;
    }

    public int getParentNode(int i, boolean z) {
        if (i == -1) {
            return -1;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        int[][] iArr = this.fNodeParent;
        return z ? clearChunkIndex(iArr, i2, i3) : getChunkIndex(iArr, i2, i3);
    }

    public int getPrevSibling(int i, boolean z) {
        int chunkIndex;
        if (i == -1) {
            return -1;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        if (getChunkIndex(this.fNodeType, i2, i3) != 3) {
            return getChunkIndex(this.fNodePrevSib, i2, i3);
        }
        while (true) {
            chunkIndex = getChunkIndex(this.fNodePrevSib, i2, i3);
            if (chunkIndex == -1) {
                break;
            }
            i2 = chunkIndex >> 8;
            int i4 = chunkIndex & 255;
            if (getChunkIndex(this.fNodeType, i2, i4) != 3) {
                break;
            }
            i3 = i4;
        }
        return chunkIndex;
    }

    public int getRealPrevSibling(int i, boolean z) {
        if (i == -1) {
            return -1;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        int[][] iArr = this.fNodePrevSib;
        return z ? clearChunkIndex(iArr, i2, i3) : getChunkIndex(iArr, i2, i3);
    }

    public Object getTypeInfo(int i) {
        if (i == -1) {
            return null;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        Object[][] objArr = this.fNodeValue;
        Object[] objArr2 = objArr[i2];
        Object obj = objArr2 != null ? objArr2[i3] : null;
        if (obj != null) {
            objArr2[i3] = null;
            RefCount refCount = (RefCount) objArr[i2][256];
            int i4 = refCount.fCount - 1;
            refCount.fCount = i4;
            if (i4 == 0) {
                objArr[i2] = null;
            }
        }
        return obj;
    }

    public int insertBefore(int i, int i2, int i3) {
        if (i3 == -1) {
            appendChild(i, i2);
            return i2;
        }
        int i4 = i3 >> 8;
        int i5 = i3 & 255;
        int chunkIndex = getChunkIndex(this.fNodePrevSib, i4, i5);
        setChunkIndex(this.fNodePrevSib, i2, i4, i5);
        setChunkIndex(this.fNodePrevSib, chunkIndex, i2 >> 8, i2 & 255);
        return i2;
    }

    public int lookupElementDefinition(String str) {
        if (this.fNodeCount > 1) {
            int chunkIndex = getChunkIndex(this.fNodeLastChild, 0, 0);
            while (true) {
                if (chunkIndex == -1) {
                    chunkIndex = -1;
                    break;
                }
                int i = chunkIndex >> 8;
                int i2 = chunkIndex & 255;
                if (getChunkIndex(this.fNodeType, i, i2) == 10) {
                    break;
                }
                chunkIndex = getChunkIndex(this.fNodePrevSib, i, i2);
            }
            if (chunkIndex == -1) {
                return -1;
            }
            int chunkIndex2 = getChunkIndex(this.fNodeLastChild, chunkIndex >> 8, chunkIndex & 255);
            while (chunkIndex2 != -1) {
                int i3 = chunkIndex2 >> 8;
                int i4 = chunkIndex2 & 255;
                if (getChunkIndex(this.fNodeType, i3, i4) == 21 && getChunkValue(this.fNodeName, i3, i4) == str) {
                    return chunkIndex2;
                }
                chunkIndex2 = getChunkIndex(this.fNodePrevSib, i3, i4);
            }
        }
        return -1;
    }

    public void print() {
    }

    public void putIdentifier(String str, int i) {
        if (this.fIdName == null) {
            this.fIdName = new String[64];
            this.fIdElement = new int[64];
        }
        int i2 = this.fIdCount;
        String[] strArr = this.fIdName;
        if (i2 == strArr.length) {
            int i3 = i2 * 2;
            String[] strArr2 = new String[i3];
            System.arraycopy(strArr, 0, strArr2, 0, i2);
            this.fIdName = strArr2;
            int[] iArr = new int[i3];
            System.arraycopy(this.fIdElement, 0, iArr, 0, this.fIdCount);
            this.fIdElement = iArr;
        }
        String[] strArr3 = this.fIdName;
        int i4 = this.fIdCount;
        strArr3[i4] = str;
        this.fIdElement[i4] = i;
        this.fIdCount = i4 + 1;
    }

    public void setAsLastChild(int i, int i2) {
        setChunkIndex(this.fNodeLastChild, i2, i >> 8, i & 255);
    }

    public int setAttributeNode(int i, int i2) {
        int i3 = i >> 8;
        int i4 = i & 255;
        int i5 = i2 >> 8;
        int i6 = i2 & 255;
        String chunkValue = getChunkValue(this.fNodeName, i5, i6);
        int chunkIndex = getChunkIndex(this.fNodeExtra, i3, i4);
        int i7 = -1;
        int i8 = -1;
        int i9 = -1;
        while (chunkIndex != -1) {
            i8 = chunkIndex >> 8;
            i9 = chunkIndex & 255;
            if (getChunkValue(this.fNodeName, i8, i9).equals(chunkValue)) {
                break;
            }
            i7 = chunkIndex;
            chunkIndex = getChunkIndex(this.fNodePrevSib, i8, i9);
        }
        if (chunkIndex != -1) {
            int chunkIndex2 = getChunkIndex(this.fNodePrevSib, i8, i9);
            if (i7 == -1) {
                setChunkIndex(this.fNodeExtra, chunkIndex2, i3, i4);
            } else {
                setChunkIndex(this.fNodePrevSib, chunkIndex2, i7 >> 8, i7 & 255);
            }
            clearChunkIndex(this.fNodeType, i8, i9);
            clearChunkValue(this.fNodeName, i8, i9);
            clearChunkValue(this.fNodeValue, i8, i9);
            clearChunkIndex(this.fNodeParent, i8, i9);
            clearChunkIndex(this.fNodePrevSib, i8, i9);
            int iClearChunkIndex = clearChunkIndex(this.fNodeLastChild, i8, i9);
            int i10 = iClearChunkIndex >> 8;
            int i11 = iClearChunkIndex & 255;
            clearChunkIndex(this.fNodeType, i10, i11);
            clearChunkValue(this.fNodeValue, i10, i11);
            clearChunkIndex(this.fNodeParent, i10, i11);
            clearChunkIndex(this.fNodeLastChild, i10, i11);
        }
        int chunkIndex3 = getChunkIndex(this.fNodeExtra, i3, i4);
        setChunkIndex(this.fNodeExtra, i2, i3, i4);
        setChunkIndex(this.fNodePrevSib, chunkIndex3, i5, i6);
        return chunkIndex;
    }

    public int setDeferredAttribute(int i, String str, String str2, String str3, boolean z, boolean z2, Object obj) {
        int iCreateDeferredAttribute = createDeferredAttribute(str, str2, str3, z);
        int i2 = iCreateDeferredAttribute >> 8;
        int i3 = iCreateDeferredAttribute & 255;
        setChunkIndex(this.fNodeParent, i, i2, i3);
        int i4 = i >> 8;
        int i5 = i & 255;
        int chunkIndex = getChunkIndex(this.fNodeExtra, i4, i5);
        if (chunkIndex != 0) {
            setChunkIndex(this.fNodePrevSib, chunkIndex, i2, i3);
        }
        setChunkIndex(this.fNodeExtra, iCreateDeferredAttribute, i4, i5);
        int chunkIndex2 = getChunkIndex(this.fNodeExtra, i2, i3);
        if (z2) {
            setChunkIndex(this.fNodeExtra, chunkIndex2 | 512, i2, i3);
            putIdentifier(getChunkValue(this.fNodeValue, i2, i3), i);
        }
        if (obj != null) {
            int iCreateNode = createNode((short) 20);
            setChunkIndex(this.fNodeLastChild, iCreateNode, i2, i3);
            setChunkValue(this.fNodeValue, obj, iCreateNode >> 8, iCreateNode & 255);
        }
        return iCreateDeferredAttribute;
    }

    public void setEntityInfo(int i, String str, String str2) {
        int nodeExtra = getNodeExtra(i, false);
        if (nodeExtra != -1) {
            int i2 = nodeExtra >> 8;
            int i3 = nodeExtra & 255;
            setChunkValue(this.fNodeValue, str, i2, i3);
            setChunkValue(this.fNodeURI, str2, i2, i3);
        }
    }

    public void setIdAttribute(int i) {
        int i2 = i >> 8;
        int i3 = i & 255;
        setChunkIndex(this.fNodeExtra, getChunkIndex(this.fNodeExtra, i2, i3) | 512, i2, i3);
    }

    public void setIdAttributeNode(int i, int i2) {
        int i3 = i2 >> 8;
        int i4 = i2 & 255;
        setChunkIndex(this.fNodeExtra, getChunkIndex(this.fNodeExtra, i3, i4) | 512, i3, i4);
        putIdentifier(getChunkValue(this.fNodeValue, i3, i4), i);
    }

    public void setInputEncoding(int i, String str) {
        int nodeExtra = getNodeExtra(getNodeExtra(i, false), false);
        setChunkValue(this.fNodeValue, str, nodeExtra >> 8, nodeExtra & 255);
    }

    public void setInternalSubset(int i, String str) {
        int iCreateNode = createNode((short) 10);
        setChunkIndex(this.fNodeExtra, iCreateNode, i >> 8, i & 255);
        setChunkValue(this.fNodeValue, str, iCreateNode >> 8, iCreateNode & 255);
    }

    public void setNamespacesEnabled(boolean z) {
        this.fNamespacesEnabled = z;
    }

    public void setTypeInfo(int i, Object obj) {
        setChunkValue(this.fNodeValue, obj, i >> 8, i & 255);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode
    public void synchronizeChildren() {
        if (needsSyncData()) {
            synchronizeData();
            if (!needsSyncChildren()) {
                return;
            }
        }
        boolean z = this.mutationEvents;
        this.mutationEvents = false;
        needsSyncChildren(false);
        getNodeType(0);
        int lastChild = getLastChild(0);
        ChildNode childNode = null;
        ChildNode childNode2 = null;
        while (lastChild != -1) {
            ChildNode childNode3 = (ChildNode) getNodeObject(lastChild);
            if (childNode2 == null) {
                childNode2 = childNode3;
            } else {
                childNode.previousSibling = childNode3;
            }
            childNode3.ownerNode = this;
            childNode3.isOwned(true);
            childNode3.nextSibling = childNode;
            short nodeType = childNode3.getNodeType();
            if (nodeType == 1) {
                this.docElement = (ElementImpl) childNode3;
            } else if (nodeType == 10) {
                this.docType = (DocumentTypeImpl) childNode3;
            }
            lastChild = getPrevSibling(lastChild);
            childNode = childNode3;
        }
        if (childNode != null) {
            this.firstChild = childNode;
            childNode.isFirstChild(true);
            lastChild(childNode2);
        }
        this.mutationEvents = z;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl
    public void synchronizeData() {
        int i = 0;
        needsSyncData(false);
        if (this.fIdElement != null) {
            IntVector intVector = new IntVector();
            while (i < this.fIdCount) {
                int i2 = this.fIdElement[i];
                String str = this.fIdName[i];
                if (str != null) {
                    intVector.removeAllElements();
                    int chunkIndex = i2;
                    do {
                        intVector.addElement(chunkIndex);
                        chunkIndex = getChunkIndex(this.fNodeParent, chunkIndex >> 8, chunkIndex & 255);
                    } while (chunkIndex != -1);
                    Element element = this;
                    for (int size = intVector.size() - 2; size >= 0; size--) {
                        int iElementAt = intVector.elementAt(size);
                        for (Node lastChild = element.getLastChild(); lastChild != null; lastChild = lastChild.getPreviousSibling()) {
                            if ((lastChild instanceof DeferredNode) && ((DeferredNode) lastChild).getNodeIndex() == iElementAt) {
                                element = lastChild;
                                break;
                            }
                        }
                    }
                    Element element2 = element;
                    putIdentifier0(str, element2);
                    this.fIdName[i] = null;
                    while (true) {
                        int i3 = i + 1;
                        if (i3 >= this.fIdCount || this.fIdElement[i3] != i2) {
                            break;
                        }
                        String str2 = this.fIdName[i3];
                        if (str2 != null) {
                            putIdentifier0(str2, element2);
                        }
                        i = i3;
                    }
                }
                i++;
            }
        }
    }

    private final void createChunk(int[][] iArr, int i) {
        int[] iArr2 = new int[257];
        iArr[i] = iArr2;
        System.arraycopy(INIT_ARRAY, 0, iArr2, 0, 256);
    }

    public int getLastChild(int i) {
        return getLastChild(i, true);
    }

    public int getNodeExtra(int i) {
        return getNodeExtra(i, true);
    }

    public int getParentNode(int i) {
        return getParentNode(i, false);
    }

    public int getRealPrevSibling(int i) {
        return getRealPrevSibling(i, true);
    }

    public String getNodeName(int i) {
        return getNodeName(i, true);
    }

    public short getNodeType(int i) {
        return getNodeType(i, true);
    }

    public String getNodeURI(int i) {
        return getNodeURI(i, true);
    }

    public String getNodeValue(int i) {
        return getNodeValue(i, true);
    }

    private final String getNodeValue(int i, int i2) {
        Object obj = this.fNodeValue[i][i2];
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        return obj.toString();
    }

    @Deprecated
    public int createDeferredElement(String str) {
        return createDeferredElement(null, str);
    }

    public int createDeferredElement(String str, String str2) {
        int iCreateNode = createNode((short) 1);
        int i = iCreateNode >> 8;
        int i2 = iCreateNode & 255;
        setChunkValue(this.fNodeName, str2, i, i2);
        setChunkValue(this.fNodeURI, str, i, i2);
        return iCreateNode;
    }

    public DeferredDocumentImpl(boolean z) {
        this(z, false);
    }

    public DeferredDocumentImpl() {
        this(false);
    }

    public int createDeferredAttribute(String str, String str2, boolean z) {
        return createDeferredAttribute(str, null, str2, z);
    }

    public int getPrevSibling(int i) {
        return getPrevSibling(i, true);
    }

    public final void synchronizeChildren(AttrImpl attrImpl, int i) {
        boolean mutationEvents = getMutationEvents();
        setMutationEvents(false);
        attrImpl.needsSyncChildren(false);
        int lastChild = getLastChild(i);
        if (getPrevSibling(lastChild) == -1) {
            attrImpl.value = getNodeValueString(i);
            attrImpl.hasStringValue(true);
        } else {
            ChildNode childNode = null;
            int prevSibling = lastChild;
            ChildNode childNode2 = null;
            while (prevSibling != -1) {
                ChildNode childNode3 = (ChildNode) getNodeObject(prevSibling);
                if (childNode == null) {
                    childNode = childNode3;
                } else {
                    childNode2.previousSibling = childNode3;
                }
                childNode3.ownerNode = attrImpl;
                childNode3.isOwned(true);
                childNode3.nextSibling = childNode2;
                prevSibling = getPrevSibling(prevSibling);
                childNode2 = childNode3;
            }
            if (childNode != null) {
                attrImpl.value = childNode2;
                childNode2.isFirstChild(true);
                attrImpl.lastChild(childNode);
            }
            attrImpl.hasStringValue(false);
        }
        setMutationEvents(mutationEvents);
    }

    public final void synchronizeChildren(ParentNode parentNode, int i) {
        boolean mutationEvents = getMutationEvents();
        setMutationEvents(false);
        parentNode.needsSyncChildren(false);
        int lastChild = getLastChild(i);
        ChildNode childNode = null;
        ChildNode childNode2 = null;
        while (lastChild != -1) {
            ChildNode childNode3 = (ChildNode) getNodeObject(lastChild);
            if (childNode == null) {
                childNode = childNode3;
            } else {
                childNode2.previousSibling = childNode3;
            }
            childNode3.ownerNode = parentNode;
            childNode3.isOwned(true);
            childNode3.nextSibling = childNode2;
            lastChild = getPrevSibling(lastChild);
            childNode2 = childNode3;
        }
        if (childNode != null) {
            parentNode.firstChild = childNode2;
            childNode2.isFirstChild(true);
            parentNode.lastChild(childNode);
        }
        setMutationEvents(mutationEvents);
    }

    public String getNodeValueString(int i) {
        return getNodeValueString(i, true);
    }
}
