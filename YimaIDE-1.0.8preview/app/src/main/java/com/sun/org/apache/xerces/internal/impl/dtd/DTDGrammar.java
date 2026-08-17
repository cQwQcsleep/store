package com.sun.org.apache.xerces.internal.impl.dtd;

import com.sun.org.apache.xerces.internal.impl.dtd.models.CMAny;
import com.sun.org.apache.xerces.internal.impl.dtd.models.CMBinOp;
import com.sun.org.apache.xerces.internal.impl.dtd.models.CMLeaf;
import com.sun.org.apache.xerces.internal.impl.dtd.models.CMNode;
import com.sun.org.apache.xerces.internal.impl.dtd.models.CMUniOp;
import com.sun.org.apache.xerces.internal.impl.dtd.models.ContentModelValidator;
import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;
import com.sun.org.apache.xerces.internal.impl.dtd.models.MixedContentModel;
import com.sun.org.apache.xerces.internal.impl.dtd.models.SimpleContentModel;
import com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator;
import com.sun.org.apache.xerces.internal.impl.validation.EntityState;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDTDHandler;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.grammars.Grammar;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarDescription;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDContentModelSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDSource;
import defpackage.uv;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTDGrammar implements XMLDTDHandler, XMLDTDContentModelHandler, EntityState, Grammar {
    private static final int CHUNK_MASK = 255;
    private static final int CHUNK_SHIFT = 8;
    private static final int CHUNK_SIZE = 256;
    private static final boolean DEBUG = false;
    private static final int INITIAL_CHUNK_COUNT = 4;
    private static final short LIST_FLAG = 128;
    private static final short LIST_MASK = -129;
    public static final int TOP_LEVEL_SCOPE = -1;
    protected int fCurrentAttributeIndex;
    protected int fCurrentElementIndex;
    protected XMLDTDDescription fGrammarDescription;
    private boolean fMixed;
    private SymbolTable fSymbolTable;
    protected XMLDTDSource fDTDSource = null;
    protected XMLDTDContentModelSource fDTDContentModelSource = null;
    protected boolean fReadingExternalDTD = false;
    private int fElementDeclCount = 0;
    private QName[][] fElementDeclName = new QName[4][];
    private short[][] fElementDeclType = new short[4][];
    private int[][] fElementDeclContentSpecIndex = new int[4][];
    private ContentModelValidator[][] fElementDeclContentModelValidator = new ContentModelValidator[4][];
    private int[][] fElementDeclFirstAttributeDeclIndex = new int[4][];
    private int[][] fElementDeclLastAttributeDeclIndex = new int[4][];
    private int fAttributeDeclCount = 0;
    private QName[][] fAttributeDeclName = new QName[4][];
    private boolean fIsImmutable = false;
    private short[][] fAttributeDeclType = new short[4][];
    private String[][][] fAttributeDeclEnumeration = new String[4][][];
    private short[][] fAttributeDeclDefaultType = new short[4][];
    private DatatypeValidator[][] fAttributeDeclDatatypeValidator = new DatatypeValidator[4][];
    private String[][] fAttributeDeclDefaultValue = new String[4][];
    private String[][] fAttributeDeclNonNormalizedDefaultValue = new String[4][];
    private int[][] fAttributeDeclNextAttributeDeclIndex = new int[4][];
    private int fContentSpecCount = 0;
    private short[][] fContentSpecType = new short[4][];
    private Object[][] fContentSpecValue = new Object[4][];
    private Object[][] fContentSpecOtherValue = new Object[4][];
    private int fEntityCount = 0;
    private String[][] fEntityName = new String[4][];
    private String[][] fEntityValue = new String[4][];
    private String[][] fEntityPublicId = new String[4][];
    private String[][] fEntitySystemId = new String[4][];
    private String[][] fEntityBaseSystemId = new String[4][];
    private String[][] fEntityNotation = new String[4][];
    private byte[][] fEntityIsPE = new byte[4][];
    private byte[][] fEntityInExternal = new byte[4][];
    private int fNotationCount = 0;
    private String[][] fNotationName = new String[4][];
    private String[][] fNotationPublicId = new String[4][];
    private String[][] fNotationSystemId = new String[4][];
    private String[][] fNotationBaseSystemId = new String[4][];
    private final Map<String, Integer> fElementIndexMap = new HashMap();
    private final Map<String, Integer> fEntityIndexMap = new HashMap();
    private final Map<String, Integer> fNotationIndexMap = new HashMap();
    private final QName fQName = new QName();
    private final QName fQName2 = new QName();
    protected final XMLAttributeDecl fAttributeDecl = new XMLAttributeDecl();
    private int fLeafCount = 0;
    private int fEpsilonIndex = -1;
    private XMLElementDecl fElementDecl = new XMLElementDecl();
    private XMLEntityDecl fEntityDecl = new XMLEntityDecl();
    private XMLSimpleType fSimpleType = new XMLSimpleType();
    private XMLContentSpec fContentSpec = new XMLContentSpec();
    Map<String, XMLElementDecl> fElementDeclTab = new HashMap();
    private short[] fOpStack = null;
    private int[] fNodeIndexStack = null;
    private int[] fPrevNodeIndexStack = null;
    private int fDepth = 0;
    private boolean[] fPEntityStack = new boolean[4];
    private int fPEDepth = 0;
    private int[][] fElementDeclIsExternal = new int[4][];
    private int[][] fAttributeDeclIsExternal = new int[4][];
    int valueIndex = -1;
    int prevNodeIndex = -1;
    int nodeIndex = -1;

    public static class ChildrenList {
        public int length = 0;
        public QName[] qname = new QName[2];
        public int[] type = new int[2];
    }

    public DTDGrammar(SymbolTable symbolTable, XMLDTDDescription xMLDTDDescription) {
        this.fGrammarDescription = null;
        this.fSymbolTable = symbolTable;
        this.fGrammarDescription = xMLDTDDescription;
    }

    private void appendContentSpec(XMLContentSpec xMLContentSpec, StringBuffer stringBuffer, boolean z, int i) {
        int i2 = xMLContentSpec.type & 15;
        switch (i2) {
            case 0:
                Object obj = xMLContentSpec.value;
                if (obj == null && xMLContentSpec.otherValue == null) {
                    stringBuffer.append("#PCDATA");
                } else if (obj == null && xMLContentSpec.otherValue != null) {
                    stringBuffer.append("##any:uri=");
                    stringBuffer.append(xMLContentSpec.otherValue);
                } else if (obj != null) {
                    stringBuffer.append(obj);
                } else {
                    stringBuffer.append(SchemaSymbols.ATTVAL_TWOPOUNDANY);
                }
                break;
            case 1:
                if (i == 3 || i == 2 || i == 1) {
                    getContentSpec(((int[]) xMLContentSpec.value)[0], xMLContentSpec);
                    stringBuffer.append('(');
                    appendContentSpec(xMLContentSpec, stringBuffer, true, i2);
                    stringBuffer.append(')');
                } else {
                    getContentSpec(((int[]) xMLContentSpec.value)[0], xMLContentSpec);
                    appendContentSpec(xMLContentSpec, stringBuffer, true, i2);
                }
                stringBuffer.append('?');
                break;
            case 2:
                if (i == 3 || i == 2 || i == 1) {
                    getContentSpec(((int[]) xMLContentSpec.value)[0], xMLContentSpec);
                    stringBuffer.append('(');
                    appendContentSpec(xMLContentSpec, stringBuffer, true, i2);
                    stringBuffer.append(')');
                } else {
                    getContentSpec(((int[]) xMLContentSpec.value)[0], xMLContentSpec);
                    appendContentSpec(xMLContentSpec, stringBuffer, true, i2);
                }
                stringBuffer.append('*');
                break;
            case 3:
                if (i == 3 || i == 2 || i == 1) {
                    stringBuffer.append('(');
                    getContentSpec(((int[]) xMLContentSpec.value)[0], xMLContentSpec);
                    appendContentSpec(xMLContentSpec, stringBuffer, true, i2);
                    stringBuffer.append(')');
                } else {
                    getContentSpec(((int[]) xMLContentSpec.value)[0], xMLContentSpec);
                    appendContentSpec(xMLContentSpec, stringBuffer, true, i2);
                }
                stringBuffer.append('+');
                break;
            case 4:
            case 5:
                if (z) {
                    stringBuffer.append('(');
                }
                short s = xMLContentSpec.type;
                int i3 = ((int[]) xMLContentSpec.otherValue)[0];
                getContentSpec(((int[]) xMLContentSpec.value)[0], xMLContentSpec);
                appendContentSpec(xMLContentSpec, stringBuffer, xMLContentSpec.type != s, i2);
                if (s == 4) {
                    stringBuffer.append('|');
                } else {
                    stringBuffer.append(',');
                }
                getContentSpec(i3, xMLContentSpec);
                appendContentSpec(xMLContentSpec, stringBuffer, true, i2);
                if (z) {
                    stringBuffer.append(')');
                }
                break;
            case 6:
                stringBuffer.append(SchemaSymbols.ATTVAL_TWOPOUNDANY);
                if (xMLContentSpec.otherValue != null) {
                    stringBuffer.append(":uri=");
                    stringBuffer.append(xMLContentSpec.otherValue);
                }
                break;
            case 7:
                stringBuffer.append("##other:uri=");
                stringBuffer.append(xMLContentSpec.otherValue);
                break;
            case 8:
                stringBuffer.append(SchemaSymbols.ATTVAL_TWOPOUNDLOCAL);
                break;
            default:
                stringBuffer.append("???");
                break;
        }
    }

    private final CMNode buildSyntaxTree(int i, XMLContentSpec xMLContentSpec) {
        getContentSpec(i, xMLContentSpec);
        short s = xMLContentSpec.type;
        if ((s & 15) == 6) {
            String str = (String) xMLContentSpec.otherValue;
            int i2 = this.fLeafCount;
            this.fLeafCount = i2 + 1;
            return new CMAny(s, str, i2);
        }
        if ((s & 15) == 7) {
            String str2 = (String) xMLContentSpec.otherValue;
            int i3 = this.fLeafCount;
            this.fLeafCount = i3 + 1;
            return new CMAny(s, str2, i3);
        }
        if ((s & 15) == 8) {
            int i4 = this.fLeafCount;
            this.fLeafCount = i4 + 1;
            return new CMAny(s, null, i4);
        }
        if (s == 0) {
            QName qName = this.fQName;
            Object obj = xMLContentSpec.value;
            qName.setValues(null, (String) obj, (String) obj, (String) xMLContentSpec.otherValue);
            QName qName2 = this.fQName;
            int i5 = this.fLeafCount;
            this.fLeafCount = i5 + 1;
            return new CMLeaf(qName2, i5);
        }
        int i6 = ((int[]) xMLContentSpec.value)[0];
        int i7 = ((int[]) xMLContentSpec.otherValue)[0];
        if (s == 4 || s == 5) {
            return new CMBinOp(s, buildSyntaxTree(i6, xMLContentSpec), buildSyntaxTree(i7, xMLContentSpec));
        }
        if (s == 2) {
            return new CMUniOp(s, buildSyntaxTree(i6, xMLContentSpec));
        }
        if (s == 2 || s == 1 || s == 3) {
            return new CMUniOp(s, buildSyntaxTree(i6, xMLContentSpec));
        }
        f63.a("ImplementationMessages.VAL_CST");
        return null;
    }

    private void contentSpecTree(int i, XMLContentSpec xMLContentSpec, ChildrenList childrenList) {
        getContentSpec(i, xMLContentSpec);
        short s = xMLContentSpec.type;
        if (s != 0 && (s & 15) != 6 && (s & 15) != 8 && (s & 15) != 7) {
            Object obj = xMLContentSpec.value;
            int i2 = obj != null ? ((int[]) obj)[0] : -1;
            Object obj2 = xMLContentSpec.otherValue;
            if (obj2 != null) {
                int i3 = ((int[]) obj2)[0];
                if (s == 4 || s == 5) {
                    contentSpecTree(i2, xMLContentSpec, childrenList);
                    contentSpecTree(i3, xMLContentSpec, childrenList);
                    return;
                } else if (s == 1 || s == 2 || s == 3) {
                    contentSpecTree(i2, xMLContentSpec, childrenList);
                    return;
                } else {
                    uv.a("Invalid content spec type seen in contentSpecTree() method of AbstractDTDGrammar class : ", xMLContentSpec.type);
                    return;
                }
            }
            return;
        }
        int i4 = childrenList.length;
        QName[] qNameArr = childrenList.qname;
        if (i4 == qNameArr.length) {
            QName[] qNameArr2 = new QName[i4 * 2];
            System.arraycopy(qNameArr, 0, qNameArr2, 0, i4);
            childrenList.qname = qNameArr2;
            int i5 = childrenList.length;
            int[] iArr = new int[i5 * 2];
            System.arraycopy(childrenList.type, 0, iArr, 0, i5);
            childrenList.type = iArr;
        }
        QName[] qNameArr3 = childrenList.qname;
        int i6 = childrenList.length;
        Object obj3 = xMLContentSpec.value;
        qNameArr3[i6] = new QName(null, (String) obj3, (String) obj3, (String) xMLContentSpec.otherValue);
        int[] iArr2 = childrenList.type;
        int i7 = childrenList.length;
        iArr2[i7] = xMLContentSpec.type;
        childrenList.length = i7 + 1;
    }

    private synchronized ContentModelValidator createChildModel(int i) {
        try {
            XMLContentSpec xMLContentSpec = new XMLContentSpec();
            getContentSpec(i, xMLContentSpec);
            short s = xMLContentSpec.type;
            if ((s & 15) != 6 && (s & 15) != 7 && (s & 15) != 8) {
                if (s == 0) {
                    Object obj = xMLContentSpec.value;
                    if (obj == null && xMLContentSpec.otherValue == null) {
                        throw new RuntimeException("ImplementationMessages.VAL_NPCD");
                    }
                    this.fQName.setValues(null, (String) obj, (String) obj, (String) xMLContentSpec.otherValue);
                    return new SimpleContentModel(xMLContentSpec.type, this.fQName, null);
                }
                if (s == 4 || s == 5) {
                    XMLContentSpec xMLContentSpec2 = new XMLContentSpec();
                    XMLContentSpec xMLContentSpec3 = new XMLContentSpec();
                    getContentSpec(((int[]) xMLContentSpec.value)[0], xMLContentSpec2);
                    getContentSpec(((int[]) xMLContentSpec.otherValue)[0], xMLContentSpec3);
                    if (xMLContentSpec2.type == 0 && xMLContentSpec3.type == 0) {
                        QName qName = this.fQName;
                        Object obj2 = xMLContentSpec2.value;
                        qName.setValues(null, (String) obj2, (String) obj2, (String) xMLContentSpec2.otherValue);
                        QName qName2 = this.fQName2;
                        Object obj3 = xMLContentSpec3.value;
                        qName2.setValues(null, (String) obj3, (String) obj3, (String) xMLContentSpec3.otherValue);
                        return new SimpleContentModel(xMLContentSpec.type, this.fQName, this.fQName2);
                    }
                } else {
                    if (s != 1 && s != 2 && s != 3) {
                        throw new RuntimeException("ImplementationMessages.VAL_CST");
                    }
                    XMLContentSpec xMLContentSpec4 = new XMLContentSpec();
                    getContentSpec(((int[]) xMLContentSpec.value)[0], xMLContentSpec4);
                    if (xMLContentSpec4.type == 0) {
                        QName qName3 = this.fQName;
                        Object obj4 = xMLContentSpec4.value;
                        qName3.setValues(null, (String) obj4, (String) obj4, (String) xMLContentSpec4.otherValue);
                        return new SimpleContentModel(xMLContentSpec.type, this.fQName, null);
                    }
                }
            }
            this.fLeafCount = 0;
            return new DFAContentModel(buildSyntaxTree(i, xMLContentSpec), this.fLeafCount, false);
        } catch (Throwable th) {
            throw th;
        }
    }

    private void ensureAttributeDeclCapacity(int i) {
        QName[][] qNameArr = this.fAttributeDeclName;
        if (i >= qNameArr.length) {
            int[][] iArr = this.fAttributeDeclIsExternal;
            this.fAttributeDeclIsExternal = resize(iArr, iArr.length * 2);
            QName[][] qNameArr2 = this.fAttributeDeclName;
            this.fAttributeDeclName = resize(qNameArr2, qNameArr2.length * 2);
            short[][] sArr = this.fAttributeDeclType;
            this.fAttributeDeclType = resize(sArr, sArr.length * 2);
            String[][][] strArr = this.fAttributeDeclEnumeration;
            this.fAttributeDeclEnumeration = resize(strArr, strArr.length * 2);
            short[][] sArr2 = this.fAttributeDeclDefaultType;
            this.fAttributeDeclDefaultType = resize(sArr2, sArr2.length * 2);
            DatatypeValidator[][] datatypeValidatorArr = this.fAttributeDeclDatatypeValidator;
            this.fAttributeDeclDatatypeValidator = resize(datatypeValidatorArr, datatypeValidatorArr.length * 2);
            String[][] strArr2 = this.fAttributeDeclDefaultValue;
            this.fAttributeDeclDefaultValue = resize(strArr2, strArr2.length * 2);
            String[][] strArr3 = this.fAttributeDeclNonNormalizedDefaultValue;
            this.fAttributeDeclNonNormalizedDefaultValue = resize(strArr3, strArr3.length * 2);
            int[][] iArr2 = this.fAttributeDeclNextAttributeDeclIndex;
            this.fAttributeDeclNextAttributeDeclIndex = resize(iArr2, iArr2.length * 2);
        } else if (qNameArr[i] != null) {
            return;
        }
        this.fAttributeDeclIsExternal[i] = new int[256];
        this.fAttributeDeclName[i] = new QName[256];
        this.fAttributeDeclType[i] = new short[256];
        this.fAttributeDeclEnumeration[i] = new String[256][];
        this.fAttributeDeclDefaultType[i] = new short[256];
        this.fAttributeDeclDatatypeValidator[i] = new DatatypeValidator[256];
        this.fAttributeDeclDefaultValue[i] = new String[256];
        this.fAttributeDeclNonNormalizedDefaultValue[i] = new String[256];
        this.fAttributeDeclNextAttributeDeclIndex[i] = new int[256];
    }

    private void ensureContentSpecCapacity(int i) {
        short[][] sArr = this.fContentSpecType;
        if (i >= sArr.length) {
            this.fContentSpecType = resize(sArr, sArr.length * 2);
            Object[][] objArr = this.fContentSpecValue;
            this.fContentSpecValue = resize(objArr, objArr.length * 2);
            Object[][] objArr2 = this.fContentSpecOtherValue;
            this.fContentSpecOtherValue = resize(objArr2, objArr2.length * 2);
        } else if (sArr[i] != null) {
            return;
        }
        this.fContentSpecType[i] = new short[256];
        this.fContentSpecValue[i] = new Object[256];
        this.fContentSpecOtherValue[i] = new Object[256];
    }

    private void ensureElementDeclCapacity(int i) {
        QName[][] qNameArr = this.fElementDeclName;
        if (i >= qNameArr.length) {
            int[][] iArr = this.fElementDeclIsExternal;
            this.fElementDeclIsExternal = resize(iArr, iArr.length * 2);
            QName[][] qNameArr2 = this.fElementDeclName;
            this.fElementDeclName = resize(qNameArr2, qNameArr2.length * 2);
            short[][] sArr = this.fElementDeclType;
            this.fElementDeclType = resize(sArr, sArr.length * 2);
            ContentModelValidator[][] contentModelValidatorArr = this.fElementDeclContentModelValidator;
            this.fElementDeclContentModelValidator = resize(contentModelValidatorArr, contentModelValidatorArr.length * 2);
            int[][] iArr2 = this.fElementDeclContentSpecIndex;
            this.fElementDeclContentSpecIndex = resize(iArr2, iArr2.length * 2);
            int[][] iArr3 = this.fElementDeclFirstAttributeDeclIndex;
            this.fElementDeclFirstAttributeDeclIndex = resize(iArr3, iArr3.length * 2);
            int[][] iArr4 = this.fElementDeclLastAttributeDeclIndex;
            this.fElementDeclLastAttributeDeclIndex = resize(iArr4, iArr4.length * 2);
        } else if (qNameArr[i] != null) {
            return;
        }
        this.fElementDeclIsExternal[i] = new int[256];
        this.fElementDeclName[i] = new QName[256];
        this.fElementDeclType[i] = new short[256];
        this.fElementDeclContentModelValidator[i] = new ContentModelValidator[256];
        this.fElementDeclContentSpecIndex[i] = new int[256];
        this.fElementDeclFirstAttributeDeclIndex[i] = new int[256];
        this.fElementDeclLastAttributeDeclIndex[i] = new int[256];
    }

    private void ensureEntityDeclCapacity(int i) {
        String[][] strArr = this.fEntityName;
        if (i >= strArr.length) {
            this.fEntityName = resize(strArr, strArr.length * 2);
            String[][] strArr2 = this.fEntityValue;
            this.fEntityValue = resize(strArr2, strArr2.length * 2);
            String[][] strArr3 = this.fEntityPublicId;
            this.fEntityPublicId = resize(strArr3, strArr3.length * 2);
            String[][] strArr4 = this.fEntitySystemId;
            this.fEntitySystemId = resize(strArr4, strArr4.length * 2);
            String[][] strArr5 = this.fEntityBaseSystemId;
            this.fEntityBaseSystemId = resize(strArr5, strArr5.length * 2);
            String[][] strArr6 = this.fEntityNotation;
            this.fEntityNotation = resize(strArr6, strArr6.length * 2);
            byte[][] bArr = this.fEntityIsPE;
            this.fEntityIsPE = resize(bArr, bArr.length * 2);
            byte[][] bArr2 = this.fEntityInExternal;
            this.fEntityInExternal = resize(bArr2, bArr2.length * 2);
        } else if (strArr[i] != null) {
            return;
        }
        this.fEntityName[i] = new String[256];
        this.fEntityValue[i] = new String[256];
        this.fEntityPublicId[i] = new String[256];
        this.fEntitySystemId[i] = new String[256];
        this.fEntityBaseSystemId[i] = new String[256];
        this.fEntityNotation[i] = new String[256];
        this.fEntityIsPE[i] = new byte[256];
        this.fEntityInExternal[i] = new byte[256];
    }

    private void ensureNotationDeclCapacity(int i) {
        String[][] strArr = this.fNotationName;
        if (i >= strArr.length) {
            this.fNotationName = resize(strArr, strArr.length * 2);
            String[][] strArr2 = this.fNotationPublicId;
            this.fNotationPublicId = resize(strArr2, strArr2.length * 2);
            String[][] strArr3 = this.fNotationSystemId;
            this.fNotationSystemId = resize(strArr3, strArr3.length * 2);
            String[][] strArr4 = this.fNotationBaseSystemId;
            this.fNotationBaseSystemId = resize(strArr4, strArr4.length * 2);
        } else if (strArr[i] != null) {
            return;
        }
        this.fNotationName[i] = new String[256];
        this.fNotationPublicId[i] = new String[256];
        this.fNotationSystemId[i] = new String[256];
        this.fNotationBaseSystemId[i] = new String[256];
    }

    private void printAttribute(int i) {
        XMLAttributeDecl xMLAttributeDecl = new XMLAttributeDecl();
        if (getAttributeDecl(i, xMLAttributeDecl)) {
            System.out.print(" { ");
            System.out.print(xMLAttributeDecl.name.localpart);
            System.out.print(" }");
        }
    }

    private static byte[][] resize(byte[][] bArr, int i) {
        byte[][] bArr2 = new byte[i][];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public int addContentSpecNode(short s, int i, int i2) {
        int iCreateContentSpec = createContentSpec();
        this.fContentSpec.setValues(s, new int[]{i}, new int[]{i2});
        setContentSpec(iCreateContentSpec, this.fContentSpec);
        return iCreateContentSpec;
    }

    public void addContentSpecToElement(XMLElementDecl xMLElementDecl) {
        int i = this.fDepth;
        if ((i == 0 || (i == 1 && xMLElementDecl.type == 2)) && this.fNodeIndexStack != null) {
            if (xMLElementDecl.type == 2) {
                int iAddUniqueLeafNode = addUniqueLeafNode(null);
                int[] iArr = this.fNodeIndexStack;
                int i2 = iArr[0];
                if (i2 == -1) {
                    iArr[0] = iAddUniqueLeafNode;
                } else {
                    iArr[0] = addContentSpecNode((short) 4, iAddUniqueLeafNode, i2);
                }
            }
            setContentSpecIndex(this.fCurrentElementIndex, this.fNodeIndexStack[this.fDepth]);
        }
    }

    public int addUniqueLeafNode(String str) {
        int iCreateContentSpec = createContentSpec();
        this.fContentSpec.setValues((short) 0, str, null);
        setContentSpec(iCreateContentSpec, this.fContentSpec);
        return iCreateContentSpec;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void any(Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void attributeDecl(String str, String str2, String str3, String[] strArr, String str4, XMLString xMLString, XMLString xMLString2, Augmentations augmentations) throws XNIException {
        if (!this.fElementDeclTab.containsKey(str)) {
            this.fCurrentElementIndex = createElementDecl();
            XMLElementDecl xMLElementDecl = new XMLElementDecl();
            xMLElementDecl.name.setValues(null, str, str, null);
            xMLElementDecl.scope = -1;
            this.fElementDeclTab.put(str, xMLElementDecl);
            setElementDecl(this.fCurrentElementIndex, xMLElementDecl);
        }
        int elementDeclIndex = getElementDeclIndex(str);
        if (getAttributeDeclIndex(elementDeclIndex, str2) != -1) {
            return;
        }
        this.fCurrentAttributeIndex = createAttributeDecl();
        this.fSimpleType.clear();
        if (str4 != null) {
            if (str4.equals("#FIXED")) {
                this.fSimpleType.defaultType = (short) 1;
            } else if (str4.equals("#IMPLIED")) {
                this.fSimpleType.defaultType = (short) 0;
            } else if (str4.equals("#REQUIRED")) {
                this.fSimpleType.defaultType = (short) 2;
            }
        }
        this.fSimpleType.defaultValue = xMLString != null ? xMLString.toString() : null;
        this.fSimpleType.nonNormalizedDefaultValue = xMLString2 != null ? xMLString2.toString() : null;
        this.fSimpleType.enumeration = strArr;
        if (str3.equals("CDATA")) {
            this.fSimpleType.type = (short) 0;
        } else if (str3.equals(SchemaSymbols.ATTVAL_ID)) {
            this.fSimpleType.type = (short) 3;
        } else if (str3.startsWith(SchemaSymbols.ATTVAL_IDREF)) {
            this.fSimpleType.type = (short) 4;
            if (str3.indexOf("S") > 0) {
                this.fSimpleType.list = true;
            }
        } else if (str3.equals(SchemaSymbols.ATTVAL_ENTITIES)) {
            XMLSimpleType xMLSimpleType = this.fSimpleType;
            xMLSimpleType.type = (short) 1;
            xMLSimpleType.list = true;
        } else if (str3.equals(SchemaSymbols.ATTVAL_ENTITY)) {
            this.fSimpleType.type = (short) 1;
        } else if (str3.equals(SchemaSymbols.ATTVAL_NMTOKENS)) {
            XMLSimpleType xMLSimpleType2 = this.fSimpleType;
            xMLSimpleType2.type = (short) 5;
            xMLSimpleType2.list = true;
        } else if (str3.equals(SchemaSymbols.ATTVAL_NMTOKEN)) {
            this.fSimpleType.type = (short) 5;
        } else if (str3.startsWith(SchemaSymbols.ATTVAL_NOTATION)) {
            this.fSimpleType.type = (short) 6;
        } else if (str3.startsWith("ENUMERATION")) {
            this.fSimpleType.type = (short) 2;
        } else {
            System.err.println("!!! unknown attribute type ".concat(str3));
        }
        this.fQName.setValues(null, str2, str2, null);
        this.fAttributeDecl.setValues(this.fQName, this.fSimpleType, false);
        setAttributeDecl(elementDeclIndex, this.fCurrentAttributeIndex, this.fAttributeDecl);
        int i = this.fCurrentAttributeIndex;
        int i2 = i >> 8;
        int i3 = i & 255;
        ensureAttributeDeclCapacity(i2);
        this.fAttributeDeclIsExternal[i2][i3] = (this.fReadingExternalDTD || this.fPEDepth > 0) ? 1 : 0;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void comment(XMLString xMLString, Augmentations augmentations) throws XNIException {
    }

    public int createAttributeDecl() {
        int i = this.fAttributeDeclCount;
        int i2 = i >> 8;
        int i3 = i & 255;
        ensureAttributeDeclCapacity(i2);
        this.fAttributeDeclName[i2][i3] = new QName();
        this.fAttributeDeclType[i2][i3] = -1;
        this.fAttributeDeclDatatypeValidator[i2][i3] = null;
        this.fAttributeDeclEnumeration[i2][i3] = null;
        this.fAttributeDeclDefaultType[i2][i3] = 0;
        this.fAttributeDeclDefaultValue[i2][i3] = null;
        this.fAttributeDeclNonNormalizedDefaultValue[i2][i3] = null;
        this.fAttributeDeclNextAttributeDeclIndex[i2][i3] = -1;
        int i4 = this.fAttributeDeclCount;
        this.fAttributeDeclCount = i4 + 1;
        return i4;
    }

    public int createContentSpec() {
        int i = this.fContentSpecCount;
        int i2 = i >> 8;
        int i3 = i & 255;
        ensureContentSpecCapacity(i2);
        this.fContentSpecType[i2][i3] = -1;
        this.fContentSpecValue[i2][i3] = null;
        this.fContentSpecOtherValue[i2][i3] = null;
        int i4 = this.fContentSpecCount;
        this.fContentSpecCount = i4 + 1;
        return i4;
    }

    public int createElementDecl() {
        int i = this.fElementDeclCount;
        int i2 = i >> 8;
        int i3 = i & 255;
        ensureElementDeclCapacity(i2);
        this.fElementDeclName[i2][i3] = new QName();
        this.fElementDeclType[i2][i3] = -1;
        this.fElementDeclContentModelValidator[i2][i3] = null;
        this.fElementDeclFirstAttributeDeclIndex[i2][i3] = -1;
        this.fElementDeclLastAttributeDeclIndex[i2][i3] = -1;
        int i4 = this.fElementDeclCount;
        this.fElementDeclCount = i4 + 1;
        return i4;
    }

    public int createEntityDecl() {
        int i = this.fEntityCount;
        int i2 = i >> 8;
        int i3 = i & 255;
        ensureEntityDeclCapacity(i2);
        this.fEntityIsPE[i2][i3] = 0;
        this.fEntityInExternal[i2][i3] = 0;
        int i4 = this.fEntityCount;
        this.fEntityCount = i4 + 1;
        return i4;
    }

    public int createNotationDecl() {
        ensureNotationDeclCapacity(this.fNotationCount >> 8);
        int i = this.fNotationCount;
        this.fNotationCount = i + 1;
        return i;
    }

    public void element(String str, Augmentations augmentations) throws XNIException {
        boolean z = this.fMixed;
        int[] iArr = this.fNodeIndexStack;
        if (!z) {
            iArr[this.fDepth] = addContentSpecNode((short) 0, str);
            return;
        }
        int i = this.fDepth;
        int i2 = iArr[i];
        if (i2 == -1) {
            iArr[i] = addUniqueLeafNode(str);
        } else {
            iArr[i] = addContentSpecNode((short) 4, i2, addUniqueLeafNode(str));
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void elementDecl(String str, String str2, Augmentations augmentations) throws XNIException {
        XMLElementDecl xMLElementDecl = this.fElementDeclTab.get(str);
        if (xMLElementDecl == null) {
            this.fCurrentElementIndex = createElementDecl();
        } else if (xMLElementDecl.type != -1) {
            return;
        } else {
            this.fCurrentElementIndex = getElementDeclIndex(str);
        }
        XMLElementDecl xMLElementDecl2 = new XMLElementDecl();
        this.fQName.setValues(null, str, str, null);
        xMLElementDecl2.name.setValues(this.fQName);
        xMLElementDecl2.contentModelValidator = null;
        xMLElementDecl2.scope = -1;
        if (str2.equals("EMPTY")) {
            xMLElementDecl2.type = (short) 1;
        } else if (str2.equals("ANY")) {
            xMLElementDecl2.type = (short) 0;
        } else if (str2.startsWith("(")) {
            if (str2.indexOf("#PCDATA") > 0) {
                xMLElementDecl2.type = (short) 2;
            } else {
                xMLElementDecl2.type = (short) 3;
            }
        }
        this.fElementDeclTab.put(str, xMLElementDecl2);
        this.fElementDecl = xMLElementDecl2;
        addContentSpecToElement(xMLElementDecl2);
        setElementDecl(this.fCurrentElementIndex, this.fElementDecl);
        int i = this.fCurrentElementIndex;
        int i2 = i >> 8;
        int i3 = i & 255;
        ensureElementDeclCapacity(i2);
        this.fElementDeclIsExternal[i2][i3] = (this.fReadingExternalDTD || this.fPEDepth > 0) ? 1 : 0;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void empty(Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endAttlist(Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endConditional(Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void endContentModel(Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endDTD(Augmentations augmentations) throws XNIException {
        this.fIsImmutable = true;
        if (this.fGrammarDescription.getRootName() == null) {
            int i = this.fElementDeclCount;
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(this.fElementDeclName[i2 >> 8][i2 & 255].rawname);
            }
            this.fGrammarDescription.setPossibleRoots(arrayList);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endExternalSubset(Augmentations augmentations) throws XNIException {
        this.fReadingExternalDTD = false;
    }

    public void endGroup(Augmentations augmentations) throws XNIException {
        if (this.fMixed) {
            return;
        }
        int[] iArr = this.fPrevNodeIndexStack;
        int i = this.fDepth;
        int i2 = iArr[i];
        if (i2 != -1) {
            int[] iArr2 = this.fNodeIndexStack;
            iArr2[i] = addContentSpecNode(this.fOpStack[i], i2, iArr2[i]);
        }
        int[] iArr3 = this.fNodeIndexStack;
        int i3 = this.fDepth;
        int i4 = i3 - 1;
        this.fDepth = i4;
        iArr3[i4] = iArr3[i3];
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endParameterEntity(String str, Augmentations augmentations) throws XNIException {
        int i = this.fPEDepth;
        if (i > 0) {
            int i2 = i - 1;
            this.fPEDepth = i2;
            this.fReadingExternalDTD = this.fPEntityStack[i2];
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void externalEntityDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
        if (getEntityDeclIndex(str) == -1) {
            int iCreateEntityDecl = createEntityDecl();
            boolean zStartsWith = str.startsWith("%");
            boolean z = this.fReadingExternalDTD || this.fPEDepth > 0;
            XMLEntityDecl xMLEntityDecl = new XMLEntityDecl();
            xMLEntityDecl.setValues(str, xMLResourceIdentifier.getPublicId(), xMLResourceIdentifier.getLiteralSystemId(), xMLResourceIdentifier.getBaseSystemId(), null, null, zStartsWith, z);
            setEntityDecl(iCreateEntityDecl, xMLEntityDecl);
        }
    }

    public boolean getAttributeDecl(int i, XMLAttributeDecl xMLAttributeDecl) {
        boolean z = false;
        if (i < 0 || i >= this.fAttributeDeclCount) {
            return false;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        xMLAttributeDecl.name.setValues(this.fAttributeDeclName[i2][i3]);
        short s = this.fAttributeDeclType[i2][i3];
        short s2 = -1;
        if (s != -1) {
            s2 = (short) (s & LIST_MASK);
            if ((s & 128) != 0) {
                z = true;
            }
        }
        xMLAttributeDecl.simpleType.setValues(s2, this.fAttributeDeclName[i2][i3].localpart, this.fAttributeDeclEnumeration[i2][i3], z, this.fAttributeDeclDefaultType[i2][i3], this.fAttributeDeclDefaultValue[i2][i3], this.fAttributeDeclNonNormalizedDefaultValue[i2][i3], this.fAttributeDeclDatatypeValidator[i2][i3]);
        return true;
    }

    public int getAttributeDeclIndex(int i, String str) {
        if (i == -1) {
            return -1;
        }
        int firstAttributeDeclIndex = getFirstAttributeDeclIndex(i);
        while (firstAttributeDeclIndex != -1) {
            getAttributeDecl(firstAttributeDeclIndex, this.fAttributeDecl);
            String str2 = this.fAttributeDecl.name.rawname;
            if (str2 == str || str.equals(str2)) {
                return firstAttributeDeclIndex;
            }
            firstAttributeDeclIndex = getNextAttributeDeclIndex(firstAttributeDeclIndex);
        }
        return -1;
    }

    public boolean getAttributeDeclIsExternal(int i) {
        if (i < 0) {
            return false;
        }
        return this.fAttributeDeclIsExternal[i >> 8][i & 255] != 0;
    }

    public boolean getContentSpec(int i, XMLContentSpec xMLContentSpec) {
        if (i < 0 || i >= this.fContentSpecCount) {
            return false;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        xMLContentSpec.type = this.fContentSpecType[i2][i3];
        xMLContentSpec.value = this.fContentSpecValue[i2][i3];
        xMLContentSpec.otherValue = this.fContentSpecOtherValue[i2][i3];
        return true;
    }

    public String getContentSpecAsString(int i) {
        if (i >= 0 && i < this.fElementDeclCount) {
            int i2 = this.fElementDeclContentSpecIndex[i >> 8][i & 255];
            XMLContentSpec xMLContentSpec = new XMLContentSpec();
            if (getContentSpec(i2, xMLContentSpec)) {
                StringBuffer stringBuffer = new StringBuffer();
                int i3 = xMLContentSpec.type & 15;
                switch (i3) {
                    case 0:
                        stringBuffer.append('(');
                        Object obj = xMLContentSpec.value;
                        if (obj == null && xMLContentSpec.otherValue == null) {
                            stringBuffer.append("#PCDATA");
                        } else {
                            stringBuffer.append(obj);
                        }
                        stringBuffer.append(')');
                        break;
                    case 1:
                        getContentSpec(((int[]) xMLContentSpec.value)[0], xMLContentSpec);
                        short s = xMLContentSpec.type;
                        if (s == 0) {
                            stringBuffer.append('(');
                            stringBuffer.append(xMLContentSpec.value);
                            stringBuffer.append(')');
                        } else if (s == 3 || s == 2 || s == 1) {
                            stringBuffer.append('(');
                            appendContentSpec(xMLContentSpec, stringBuffer, true, i3);
                            stringBuffer.append(')');
                        } else {
                            appendContentSpec(xMLContentSpec, stringBuffer, true, i3);
                        }
                        stringBuffer.append('?');
                        break;
                    case 2:
                        getContentSpec(((int[]) xMLContentSpec.value)[0], xMLContentSpec);
                        short s2 = xMLContentSpec.type;
                        if (s2 == 0) {
                            stringBuffer.append('(');
                            Object obj2 = xMLContentSpec.value;
                            if (obj2 == null && xMLContentSpec.otherValue == null) {
                                stringBuffer.append("#PCDATA");
                            } else if (xMLContentSpec.otherValue != null) {
                                stringBuffer.append("##any:uri=");
                                stringBuffer.append(xMLContentSpec.otherValue);
                            } else if (obj2 == null) {
                                stringBuffer.append(SchemaSymbols.ATTVAL_TWOPOUNDANY);
                            } else {
                                appendContentSpec(xMLContentSpec, stringBuffer, true, i3);
                            }
                            stringBuffer.append(')');
                        } else if (s2 == 3 || s2 == 2 || s2 == 1) {
                            stringBuffer.append('(');
                            appendContentSpec(xMLContentSpec, stringBuffer, true, i3);
                            stringBuffer.append(')');
                        } else {
                            appendContentSpec(xMLContentSpec, stringBuffer, true, i3);
                        }
                        stringBuffer.append('*');
                        break;
                    case 3:
                        getContentSpec(((int[]) xMLContentSpec.value)[0], xMLContentSpec);
                        short s3 = xMLContentSpec.type;
                        if (s3 == 0) {
                            stringBuffer.append('(');
                            Object obj3 = xMLContentSpec.value;
                            if (obj3 == null && xMLContentSpec.otherValue == null) {
                                stringBuffer.append("#PCDATA");
                            } else if (xMLContentSpec.otherValue != null) {
                                stringBuffer.append("##any:uri=");
                                stringBuffer.append(xMLContentSpec.otherValue);
                            } else if (obj3 == null) {
                                stringBuffer.append(SchemaSymbols.ATTVAL_TWOPOUNDANY);
                            } else {
                                stringBuffer.append(obj3);
                            }
                            stringBuffer.append(')');
                        } else if (s3 == 3 || s3 == 2 || s3 == 1) {
                            stringBuffer.append('(');
                            appendContentSpec(xMLContentSpec, stringBuffer, true, i3);
                            stringBuffer.append(')');
                        } else {
                            appendContentSpec(xMLContentSpec, stringBuffer, true, i3);
                        }
                        stringBuffer.append('+');
                        break;
                    case 4:
                    case 5:
                        appendContentSpec(xMLContentSpec, stringBuffer, true, i3);
                        break;
                    case 6:
                        stringBuffer.append(SchemaSymbols.ATTVAL_TWOPOUNDANY);
                        if (xMLContentSpec.otherValue != null) {
                            stringBuffer.append(":uri=");
                            stringBuffer.append(xMLContentSpec.otherValue);
                        }
                        break;
                    case 7:
                        stringBuffer.append("##other:uri=");
                        stringBuffer.append(xMLContentSpec.otherValue);
                        break;
                    case 8:
                        stringBuffer.append(SchemaSymbols.ATTVAL_TWOPOUNDLOCAL);
                        break;
                    default:
                        stringBuffer.append("???");
                        break;
                }
                return stringBuffer.toString();
            }
        }
        return null;
    }

    public int getContentSpecIndex(int i) {
        if (i < 0 || i >= this.fElementDeclCount) {
            return -1;
        }
        return this.fElementDeclContentSpecIndex[i >> 8][i & 255];
    }

    public short getContentSpecType(int i) {
        if (i < 0 || i >= this.fElementDeclCount) {
            return (short) -1;
        }
        short s = this.fElementDeclType[i >> 8][i & 255];
        if (s == -1) {
            return (short) -1;
        }
        return (short) (s & LIST_MASK);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public XMLDTDContentModelSource getDTDContentModelSource() {
        return this.fDTDContentModelSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public XMLDTDSource getDTDSource() {
        return this.fDTDSource;
    }

    public ContentModelValidator getElementContentModelValidator(int i) {
        ContentModelValidator contentModelValidatorCreateChildModel;
        int i2 = i >> 8;
        int i3 = i & 255;
        ContentModelValidator contentModelValidator = this.fElementDeclContentModelValidator[i2][i3];
        if (contentModelValidator != null) {
            return contentModelValidator;
        }
        short s = this.fElementDeclType[i2][i3];
        if (s == 4) {
            return null;
        }
        int i4 = this.fElementDeclContentSpecIndex[i2][i3];
        XMLContentSpec xMLContentSpec = new XMLContentSpec();
        getContentSpec(i4, xMLContentSpec);
        if (s == 2) {
            ChildrenList childrenList = new ChildrenList();
            contentSpecTree(i4, xMLContentSpec, childrenList);
            contentModelValidatorCreateChildModel = new MixedContentModel(childrenList.qname, childrenList.type, 0, childrenList.length, false);
        } else {
            if (s != 3) {
                f63.a("Unknown content type for a element decl in getElementContentModelValidator() in AbstractDTDGrammar class");
                return null;
            }
            contentModelValidatorCreateChildModel = createChildModel(i4);
        }
        this.fElementDeclContentModelValidator[i2][i3] = contentModelValidatorCreateChildModel;
        return contentModelValidatorCreateChildModel;
    }

    public boolean getElementDecl(int i, XMLElementDecl xMLElementDecl) {
        if (i < 0 || i >= this.fElementDeclCount) {
            return false;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        xMLElementDecl.name.setValues(this.fElementDeclName[i2][i3]);
        short s = this.fElementDeclType[i2][i3];
        if (s == -1) {
            xMLElementDecl.type = (short) -1;
            xMLElementDecl.simpleType.list = false;
        } else {
            xMLElementDecl.type = (short) (s & LIST_MASK);
            xMLElementDecl.simpleType.list = (s & 128) != 0;
        }
        short s2 = xMLElementDecl.type;
        if (s2 == 3 || s2 == 2) {
            xMLElementDecl.contentModelValidator = getElementContentModelValidator(i);
        }
        XMLSimpleType xMLSimpleType = xMLElementDecl.simpleType;
        xMLSimpleType.datatypeValidator = null;
        xMLSimpleType.defaultType = (short) -1;
        xMLSimpleType.defaultValue = null;
        return true;
    }

    public int getElementDeclIndex(String str) {
        Integer num = this.fElementIndexMap.get(str);
        if (num == null) {
            num = -1;
        }
        return num.intValue();
    }

    public boolean getElementDeclIsExternal(int i) {
        if (i < 0) {
            return false;
        }
        return this.fElementDeclIsExternal[i >> 8][i & 255] != 0;
    }

    public QName getElementDeclName(int i) {
        if (i < 0 || i >= this.fElementDeclCount) {
            return null;
        }
        return this.fElementDeclName[i >> 8][i & 255];
    }

    public boolean getEntityDecl(int i, XMLEntityDecl xMLEntityDecl) {
        if (i < 0 || i >= this.fEntityCount) {
            return false;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        xMLEntityDecl.setValues(this.fEntityName[i2][i3], this.fEntityPublicId[i2][i3], this.fEntitySystemId[i2][i3], this.fEntityBaseSystemId[i2][i3], this.fEntityNotation[i2][i3], this.fEntityValue[i2][i3], this.fEntityIsPE[i2][i3] != 0, this.fEntityInExternal[i2][i3] != 0);
        return true;
    }

    public int getEntityDeclIndex(String str) {
        if (str == null || this.fEntityIndexMap.get(str) == null) {
            return -1;
        }
        return this.fEntityIndexMap.get(str).intValue();
    }

    public int getFirstAttributeDeclIndex(int i) {
        return this.fElementDeclFirstAttributeDeclIndex[i >> 8][i & 255];
    }

    public int getFirstElementDeclIndex() {
        return this.fElementDeclCount >= 0 ? 0 : -1;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.Grammar
    public XMLGrammarDescription getGrammarDescription() {
        return this.fGrammarDescription;
    }

    public int getNextAttributeDeclIndex(int i) {
        return this.fAttributeDeclNextAttributeDeclIndex[i >> 8][i & 255];
    }

    public int getNextElementDeclIndex(int i) {
        if (i < this.fElementDeclCount - 1) {
            return i + 1;
        }
        return -1;
    }

    public boolean getNotationDecl(int i, XMLNotationDecl xMLNotationDecl) {
        if (i < 0 || i >= this.fNotationCount) {
            return false;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        xMLNotationDecl.setValues(this.fNotationName[i2][i3], this.fNotationPublicId[i2][i3], this.fNotationSystemId[i2][i3], this.fNotationBaseSystemId[i2][i3]);
        return true;
    }

    public int getNotationDeclIndex(String str) {
        if (str == null || this.fNotationIndexMap.get(str) == null) {
            return -1;
        }
        return this.fNotationIndexMap.get(str).intValue();
    }

    public SymbolTable getSymbolTable() {
        return this.fSymbolTable;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void ignoredCharacters(XMLString xMLString, Augmentations augmentations) throws XNIException {
    }

    public void initializeContentModelStack() {
        short[] sArr = this.fOpStack;
        if (sArr == null) {
            this.fOpStack = new short[8];
            this.fNodeIndexStack = new int[8];
            this.fPrevNodeIndexStack = new int[8];
        } else {
            int i = this.fDepth;
            if (i == sArr.length) {
                short[] sArr2 = new short[i * 2];
                System.arraycopy(sArr, 0, sArr2, 0, i);
                this.fOpStack = sArr2;
                int i2 = this.fDepth;
                int[] iArr = new int[i2 * 2];
                System.arraycopy(this.fNodeIndexStack, 0, iArr, 0, i2);
                this.fNodeIndexStack = iArr;
                int i3 = this.fDepth;
                int[] iArr2 = new int[i3 * 2];
                System.arraycopy(this.fPrevNodeIndexStack, 0, iArr2, 0, i3);
                this.fPrevNodeIndexStack = iArr2;
            }
        }
        short[] sArr3 = this.fOpStack;
        int i4 = this.fDepth;
        sArr3[i4] = -1;
        this.fNodeIndexStack[i4] = -1;
        this.fPrevNodeIndexStack[i4] = -1;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void internalEntityDecl(String str, XMLString xMLString, XMLString xMLString2, Augmentations augmentations) throws XNIException {
        if (getEntityDeclIndex(str) == -1) {
            int iCreateEntityDecl = createEntityDecl();
            boolean zStartsWith = str.startsWith("%");
            boolean z = this.fReadingExternalDTD || this.fPEDepth > 0;
            XMLEntityDecl xMLEntityDecl = new XMLEntityDecl();
            xMLEntityDecl.setValues(str, null, null, null, null, xMLString.toString(), zStartsWith, z);
            setEntityDecl(iCreateEntityDecl, xMLEntityDecl);
        }
    }

    public boolean isCDATAAttribute(QName qName, QName qName2) {
        return !getAttributeDecl(getElementDeclIndex(qName), this.fAttributeDecl) || this.fAttributeDecl.simpleType.type == 0;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.validation.EntityState
    public boolean isEntityDeclared(String str) {
        return getEntityDeclIndex(str) != -1;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.validation.EntityState
    public boolean isEntityUnparsed(String str) {
        int entityDeclIndex = getEntityDeclIndex(str);
        if (entityDeclIndex > -1) {
            if (this.fEntityNotation[entityDeclIndex >> 8][entityDeclIndex & 255] != null) {
                return true;
            }
        }
        return false;
    }

    public boolean isImmutable() {
        return this.fIsImmutable;
    }

    public boolean isNamespaceAware() {
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void notationDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
        XMLNotationDecl xMLNotationDecl = new XMLNotationDecl();
        xMLNotationDecl.setValues(str, xMLResourceIdentifier.getPublicId(), xMLResourceIdentifier.getLiteralSystemId(), xMLResourceIdentifier.getBaseSystemId());
        if (getNotationDeclIndex(str) == -1) {
            setNotationDecl(createNotationDecl(), xMLNotationDecl);
        }
    }

    public void occurrence(short s, Augmentations augmentations) throws XNIException {
        if (this.fMixed) {
            return;
        }
        if (s == 2) {
            int[] iArr = this.fNodeIndexStack;
            int i = this.fDepth;
            iArr[i] = addContentSpecNode((short) 1, iArr[i], -1);
        } else if (s == 3) {
            int[] iArr2 = this.fNodeIndexStack;
            int i2 = this.fDepth;
            iArr2[i2] = addContentSpecNode((short) 2, iArr2[i2], -1);
        } else if (s == 4) {
            int[] iArr3 = this.fNodeIndexStack;
            int i3 = this.fDepth;
            iArr3[i3] = addContentSpecNode((short) 3, iArr3[i3], -1);
        }
    }

    public void pcdata(Augmentations augmentations) throws XNIException {
        this.fMixed = true;
    }

    public void printAttributes(int i) {
        int firstAttributeDeclIndex = getFirstAttributeDeclIndex(i);
        System.out.print(i);
        System.out.print(" [");
        while (firstAttributeDeclIndex != -1) {
            System.out.print(' ');
            System.out.print(firstAttributeDeclIndex);
            printAttribute(firstAttributeDeclIndex);
            firstAttributeDeclIndex = getNextAttributeDeclIndex(firstAttributeDeclIndex);
            if (firstAttributeDeclIndex != -1) {
                System.out.print(",");
            }
        }
        System.out.println(" ]");
    }

    public void printElements() {
        XMLElementDecl xMLElementDecl = new XMLElementDecl();
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!getElementDecl(i, xMLElementDecl)) {
                return;
            }
            System.out.println("element decl: " + xMLElementDecl.name + ", " + xMLElementDecl.name.rawname);
            i = i2;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void processingInstruction(String str, XMLString xMLString, Augmentations augmentations) throws XNIException {
    }

    public void putElementNameMapping(QName qName, int i, int i2) {
    }

    public void separator(short s, Augmentations augmentations) throws XNIException {
        if (this.fMixed) {
            return;
        }
        short[] sArr = this.fOpStack;
        int i = this.fDepth;
        short s2 = sArr[i];
        if (s2 != 5 && s == 0) {
            int i2 = this.fPrevNodeIndexStack[i];
            if (i2 != -1) {
                int[] iArr = this.fNodeIndexStack;
                iArr[i] = addContentSpecNode(s2, i2, iArr[i]);
            }
            int[] iArr2 = this.fPrevNodeIndexStack;
            int i3 = this.fDepth;
            iArr2[i3] = this.fNodeIndexStack[i3];
            this.fOpStack[i3] = 4;
            return;
        }
        if (s2 == 4 || s != 1) {
            return;
        }
        int i4 = this.fPrevNodeIndexStack[i];
        if (i4 != -1) {
            int[] iArr3 = this.fNodeIndexStack;
            iArr3[i] = addContentSpecNode(s2, i4, iArr3[i]);
        }
        int[] iArr4 = this.fPrevNodeIndexStack;
        int i5 = this.fDepth;
        iArr4[i5] = this.fNodeIndexStack[i5];
        this.fOpStack[i5] = 5;
    }

    public void setAttributeDecl(int i, int i2, XMLAttributeDecl xMLAttributeDecl) {
        int i3 = i2 >> 8;
        int i4 = i2 & 255;
        this.fAttributeDeclName[i3][i4].setValues(xMLAttributeDecl.name);
        short[] sArr = this.fAttributeDeclType[i3];
        XMLSimpleType xMLSimpleType = xMLAttributeDecl.simpleType;
        short s = xMLSimpleType.type;
        sArr[i4] = s;
        if (xMLSimpleType.list) {
            sArr[i4] = (short) (s | 128);
        }
        this.fAttributeDeclEnumeration[i3][i4] = xMLSimpleType.enumeration;
        this.fAttributeDeclDefaultType[i3][i4] = xMLSimpleType.defaultType;
        this.fAttributeDeclDatatypeValidator[i3][i4] = xMLSimpleType.datatypeValidator;
        this.fAttributeDeclDefaultValue[i3][i4] = xMLSimpleType.defaultValue;
        this.fAttributeDeclNonNormalizedDefaultValue[i3][i4] = xMLSimpleType.nonNormalizedDefaultValue;
        int i5 = i >> 8;
        int i6 = i & 255;
        int i7 = this.fElementDeclFirstAttributeDeclIndex[i5][i6];
        while (i7 != -1 && i7 != i2) {
            i7 = this.fAttributeDeclNextAttributeDeclIndex[i7 >> 8][i7 & 255];
        }
        if (i7 == -1) {
            int[] iArr = this.fElementDeclFirstAttributeDeclIndex[i5];
            if (iArr[i6] == -1) {
                iArr[i6] = i2;
            } else {
                int i8 = this.fElementDeclLastAttributeDeclIndex[i5][i6];
                this.fAttributeDeclNextAttributeDeclIndex[i8 >> 8][i8 & 255] = i2;
            }
            this.fElementDeclLastAttributeDeclIndex[i5][i6] = i2;
        }
    }

    public void setContentSpec(int i, XMLContentSpec xMLContentSpec) {
        int i2 = i >> 8;
        int i3 = i & 255;
        this.fContentSpecType[i2][i3] = xMLContentSpec.type;
        this.fContentSpecValue[i2][i3] = xMLContentSpec.value;
        this.fContentSpecOtherValue[i2][i3] = xMLContentSpec.otherValue;
    }

    public void setContentSpecIndex(int i, int i2) {
        if (i < 0 || i >= this.fElementDeclCount) {
            return;
        }
        this.fElementDeclContentSpecIndex[i >> 8][i & 255] = i2;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void setDTDContentModelSource(XMLDTDContentModelSource xMLDTDContentModelSource) {
        this.fDTDContentModelSource = xMLDTDContentModelSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void setDTDSource(XMLDTDSource xMLDTDSource) {
        this.fDTDSource = xMLDTDSource;
    }

    public void setElementDecl(int i, XMLElementDecl xMLElementDecl) {
        if (i < 0 || i >= this.fElementDeclCount) {
            return;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        this.fElementDeclName[i2][i3].setValues(xMLElementDecl.name);
        short[][] sArr = this.fElementDeclType;
        sArr[i2][i3] = xMLElementDecl.type;
        this.fElementDeclContentModelValidator[i2][i3] = xMLElementDecl.contentModelValidator;
        if (xMLElementDecl.simpleType.list) {
            short[] sArr2 = sArr[i2];
            sArr2[i3] = (short) (sArr2[i3] | 128);
        }
        this.fElementIndexMap.put(xMLElementDecl.name.rawname, Integer.valueOf(i));
    }

    public void setEntityDecl(int i, XMLEntityDecl xMLEntityDecl) {
        int i2 = i >> 8;
        int i3 = i & 255;
        String[] strArr = this.fEntityName[i2];
        String str = xMLEntityDecl.name;
        strArr[i3] = str;
        this.fEntityValue[i2][i3] = xMLEntityDecl.value;
        this.fEntityPublicId[i2][i3] = xMLEntityDecl.publicId;
        this.fEntitySystemId[i2][i3] = xMLEntityDecl.systemId;
        this.fEntityBaseSystemId[i2][i3] = xMLEntityDecl.baseSystemId;
        this.fEntityNotation[i2][i3] = xMLEntityDecl.notation;
        this.fEntityIsPE[i2][i3] = xMLEntityDecl.isPE;
        this.fEntityInExternal[i2][i3] = xMLEntityDecl.inExternal;
        this.fEntityIndexMap.put(str, Integer.valueOf(i));
    }

    public void setFirstAttributeDeclIndex(int i, int i2) {
        if (i < 0 || i >= this.fElementDeclCount) {
            return;
        }
        this.fElementDeclFirstAttributeDeclIndex[i >> 8][i & 255] = i2;
    }

    public void setNotationDecl(int i, XMLNotationDecl xMLNotationDecl) {
        int i2 = i >> 8;
        int i3 = i & 255;
        String[] strArr = this.fNotationName[i2];
        String str = xMLNotationDecl.name;
        strArr[i3] = str;
        this.fNotationPublicId[i2][i3] = xMLNotationDecl.publicId;
        this.fNotationSystemId[i2][i3] = xMLNotationDecl.systemId;
        this.fNotationBaseSystemId[i2][i3] = xMLNotationDecl.baseSystemId;
        this.fNotationIndexMap.put(str, Integer.valueOf(i));
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startAttlist(String str, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startConditional(short s, Augmentations augmentations) throws XNIException {
    }

    public void startContentModel(String str, Augmentations augmentations) throws XNIException {
        XMLElementDecl xMLElementDecl = this.fElementDeclTab.get(str);
        if (xMLElementDecl != null) {
            this.fElementDecl = xMLElementDecl;
        }
        this.fDepth = 0;
        initializeContentModelStack();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startDTD(XMLLocator xMLLocator, Augmentations augmentations) throws XNIException {
        this.fOpStack = null;
        this.fNodeIndexStack = null;
        this.fPrevNodeIndexStack = null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startExternalSubset(XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
        this.fReadingExternalDTD = true;
    }

    public void startGroup(Augmentations augmentations) throws XNIException {
        this.fDepth++;
        initializeContentModelStack();
        this.fMixed = false;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startParameterEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
        int i = this.fPEDepth;
        boolean[] zArr = this.fPEntityStack;
        if (i == zArr.length) {
            boolean[] zArr2 = new boolean[zArr.length * 2];
            System.arraycopy(zArr, 0, zArr2, 0, zArr.length);
            this.fPEntityStack = zArr2;
        }
        boolean[] zArr3 = this.fPEntityStack;
        int i2 = this.fPEDepth;
        zArr3[i2] = this.fReadingExternalDTD;
        this.fPEDepth = i2 + 1;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void textDecl(String str, String str2, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void unparsedEntityDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
        XMLEntityDecl xMLEntityDecl = new XMLEntityDecl();
        xMLEntityDecl.setValues(str, xMLResourceIdentifier.getPublicId(), xMLResourceIdentifier.getLiteralSystemId(), xMLResourceIdentifier.getBaseSystemId(), str2, null, str.startsWith("%"), this.fReadingExternalDTD || this.fPEDepth > 0);
        if (getEntityDeclIndex(str) == -1) {
            setEntityDecl(createEntityDecl(), xMLEntityDecl);
        }
    }

    private static short[][] resize(short[][] sArr, int i) {
        short[][] sArr2 = new short[i][];
        System.arraycopy(sArr, 0, sArr2, 0, sArr.length);
        return sArr2;
    }

    private static int[][] resize(int[][] iArr, int i) {
        int[][] iArr2 = new int[i][];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }

    private static DatatypeValidator[][] resize(DatatypeValidator[][] datatypeValidatorArr, int i) {
        DatatypeValidator[][] datatypeValidatorArr2 = new DatatypeValidator[i][];
        System.arraycopy(datatypeValidatorArr, 0, datatypeValidatorArr2, 0, datatypeValidatorArr.length);
        return datatypeValidatorArr2;
    }

    private static ContentModelValidator[][] resize(ContentModelValidator[][] contentModelValidatorArr, int i) {
        ContentModelValidator[][] contentModelValidatorArr2 = new ContentModelValidator[i][];
        System.arraycopy(contentModelValidatorArr, 0, contentModelValidatorArr2, 0, contentModelValidatorArr.length);
        return contentModelValidatorArr2;
    }

    private static Object[][] resize(Object[][] objArr, int i) {
        Object[][] objArr2 = new Object[i][];
        System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        return objArr2;
    }

    private static QName[][] resize(QName[][] qNameArr, int i) {
        QName[][] qNameArr2 = new QName[i][];
        System.arraycopy(qNameArr, 0, qNameArr2, 0, qNameArr.length);
        return qNameArr2;
    }

    private static String[][] resize(String[][] strArr, int i) {
        String[][] strArr2 = new String[i][];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        return strArr2;
    }

    public int getElementDeclIndex(QName qName) {
        return getElementDeclIndex(qName.rawname);
    }

    private static String[][][] resize(String[][][] strArr, int i) {
        String[][][] strArr2 = new String[i][][];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        return strArr2;
    }

    public int addContentSpecNode(short s, String str) {
        int iCreateContentSpec = createContentSpec();
        this.fContentSpec.setValues(s, str, null);
        setContentSpec(iCreateContentSpec, this.fContentSpec);
        return iCreateContentSpec;
    }
}
