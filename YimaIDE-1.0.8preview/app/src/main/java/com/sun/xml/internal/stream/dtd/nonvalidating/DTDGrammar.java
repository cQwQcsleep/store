package com.sun.xml.internal.stream.dtd.nonvalidating;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDContentModelSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTDGrammar {
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
    private final SymbolTable fSymbolTable;
    protected XMLDTDSource fDTDSource = null;
    protected XMLDTDContentModelSource fDTDContentModelSource = null;
    protected boolean fReadingExternalDTD = false;
    private final ArrayList<XMLNotationDecl> notationDecls = new ArrayList<>();
    private int fElementDeclCount = 0;
    private QName[][] fElementDeclName = new QName[4][];
    private short[][] fElementDeclType = new short[4][];
    private int[][] fElementDeclFirstAttributeDeclIndex = new int[4][];
    private int[][] fElementDeclLastAttributeDeclIndex = new int[4][];
    private int fAttributeDeclCount = 0;
    private QName[][] fAttributeDeclName = new QName[4][];
    private short[][] fAttributeDeclType = new short[4][];
    private String[][][] fAttributeDeclEnumeration = new String[4][][];
    private short[][] fAttributeDeclDefaultType = new short[4][];
    private String[][] fAttributeDeclDefaultValue = new String[4][];
    private String[][] fAttributeDeclNonNormalizedDefaultValue = new String[4][];
    private int[][] fAttributeDeclNextAttributeDeclIndex = new int[4][];
    private final Map<String, Integer> fElementIndexMap = new HashMap();
    private final QName fQName = new QName();
    protected XMLAttributeDecl fAttributeDecl = new XMLAttributeDecl();
    private XMLElementDecl fElementDecl = new XMLElementDecl();
    private XMLSimpleType fSimpleType = new XMLSimpleType();
    Map<String, XMLElementDecl> fElementDeclTab = new HashMap();

    public DTDGrammar(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
    }

    private void ensureAttributeDeclCapacity(int i) {
        QName[][] qNameArr = this.fAttributeDeclName;
        if (i >= qNameArr.length) {
            this.fAttributeDeclName = resize(qNameArr, qNameArr.length * 2);
            short[][] sArr = this.fAttributeDeclType;
            this.fAttributeDeclType = resize(sArr, sArr.length * 2);
            String[][][] strArr = this.fAttributeDeclEnumeration;
            this.fAttributeDeclEnumeration = resize(strArr, strArr.length * 2);
            short[][] sArr2 = this.fAttributeDeclDefaultType;
            this.fAttributeDeclDefaultType = resize(sArr2, sArr2.length * 2);
            String[][] strArr2 = this.fAttributeDeclDefaultValue;
            this.fAttributeDeclDefaultValue = resize(strArr2, strArr2.length * 2);
            String[][] strArr3 = this.fAttributeDeclNonNormalizedDefaultValue;
            this.fAttributeDeclNonNormalizedDefaultValue = resize(strArr3, strArr3.length * 2);
            int[][] iArr = this.fAttributeDeclNextAttributeDeclIndex;
            this.fAttributeDeclNextAttributeDeclIndex = resize(iArr, iArr.length * 2);
        } else if (qNameArr[i] != null) {
            return;
        }
        this.fAttributeDeclName[i] = new QName[256];
        this.fAttributeDeclType[i] = new short[256];
        this.fAttributeDeclEnumeration[i] = new String[256][];
        this.fAttributeDeclDefaultType[i] = new short[256];
        this.fAttributeDeclDefaultValue[i] = new String[256];
        this.fAttributeDeclNonNormalizedDefaultValue[i] = new String[256];
        this.fAttributeDeclNextAttributeDeclIndex[i] = new int[256];
    }

    private void ensureElementDeclCapacity(int i) {
        QName[][] qNameArr = this.fElementDeclName;
        if (i >= qNameArr.length) {
            this.fElementDeclName = resize(qNameArr, qNameArr.length * 2);
            short[][] sArr = this.fElementDeclType;
            this.fElementDeclType = resize(sArr, sArr.length * 2);
            int[][] iArr = this.fElementDeclFirstAttributeDeclIndex;
            this.fElementDeclFirstAttributeDeclIndex = resize(iArr, iArr.length * 2);
            int[][] iArr2 = this.fElementDeclLastAttributeDeclIndex;
            this.fElementDeclLastAttributeDeclIndex = resize(iArr2, iArr2.length * 2);
        } else if (qNameArr[i] != null) {
            return;
        }
        this.fElementDeclName[i] = new QName[256];
        this.fElementDeclType[i] = new short[256];
        this.fElementDeclFirstAttributeDeclIndex[i] = new int[256];
        this.fElementDeclLastAttributeDeclIndex[i] = new int[256];
    }

    private boolean normalizeDefaultAttrValue(XMLString xMLString) {
        int i = xMLString.length;
        int i2 = xMLString.offset;
        int i3 = i + i2;
        int i4 = i2;
        boolean z = true;
        while (i2 < i3) {
            char[] cArr = xMLString.ch;
            char c = cArr[i2];
            if (c != ' ') {
                if (i4 != i2) {
                    cArr[i4] = c;
                }
                i4++;
                z = false;
            } else if (!z) {
                cArr[i4] = ' ';
                i4++;
                z = true;
            }
            i2++;
        }
        if (i4 == i3) {
            return false;
        }
        if (z) {
            i4--;
        }
        xMLString.length = i4 - xMLString.offset;
        return true;
    }

    private void printAttribute(int i) {
        XMLAttributeDecl xMLAttributeDecl = new XMLAttributeDecl();
        if (getAttributeDecl(i, xMLAttributeDecl)) {
            System.out.print(" { ");
            System.out.print(xMLAttributeDecl.name.localpart);
            System.out.print(" }");
        }
    }

    private static short[][] resize(short[][] sArr, int i) {
        short[][] sArr2 = new short[i][];
        System.arraycopy(sArr, 0, sArr2, 0, sArr.length);
        return sArr2;
    }

    public void attributeDecl(String str, String str2, String str3, String[] strArr, String str4, XMLString xMLString, XMLString xMLString2, Augmentations augmentations) throws XNIException {
        if (str3 != XMLSymbols.fCDATASymbol && xMLString != null) {
            normalizeDefaultAttrValue(xMLString);
        }
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
        ensureAttributeDeclCapacity(this.fCurrentAttributeIndex >> 8);
    }

    public int createAttributeDecl() {
        int i = this.fAttributeDeclCount;
        int i2 = i >> 8;
        int i3 = i & 255;
        ensureAttributeDeclCapacity(i2);
        this.fAttributeDeclName[i2][i3] = new QName();
        this.fAttributeDeclType[i2][i3] = -1;
        this.fAttributeDeclEnumeration[i2][i3] = null;
        this.fAttributeDeclDefaultType[i2][i3] = 0;
        this.fAttributeDeclDefaultValue[i2][i3] = null;
        this.fAttributeDeclNonNormalizedDefaultValue[i2][i3] = null;
        this.fAttributeDeclNextAttributeDeclIndex[i2][i3] = -1;
        int i4 = this.fAttributeDeclCount;
        this.fAttributeDeclCount = i4 + 1;
        return i4;
    }

    public int createElementDecl() {
        int i = this.fElementDeclCount;
        int i2 = i >> 8;
        int i3 = i & 255;
        ensureElementDeclCapacity(i2);
        this.fElementDeclName[i2][i3] = new QName();
        this.fElementDeclType[i2][i3] = -1;
        this.fElementDeclFirstAttributeDeclIndex[i2][i3] = -1;
        this.fElementDeclLastAttributeDeclIndex[i2][i3] = -1;
        int i4 = this.fElementDeclCount;
        this.fElementDeclCount = i4 + 1;
        return i4;
    }

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
        xMLElementDecl2.name.setValues(new QName(null, str, str, null));
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
        setElementDecl(this.fCurrentElementIndex, xMLElementDecl2);
        ensureElementDeclCapacity(this.fCurrentElementIndex >> 8);
    }

    public void endDTD(Augmentations augmentations) throws XNIException {
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
        xMLAttributeDecl.simpleType.setValues(s2, this.fAttributeDeclName[i2][i3].localpart, this.fAttributeDeclEnumeration[i2][i3], z, this.fAttributeDeclDefaultType[i2][i3], this.fAttributeDeclDefaultValue[i2][i3], this.fAttributeDeclNonNormalizedDefaultValue[i2][i3]);
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
        XMLSimpleType xMLSimpleType = xMLElementDecl.simpleType;
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

    public int getFirstAttributeDeclIndex(int i) {
        return this.fElementDeclFirstAttributeDeclIndex[i >> 8][i & 255];
    }

    public int getFirstElementDeclIndex() {
        return this.fElementDeclCount >= 0 ? 0 : -1;
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

    public List<XMLNotationDecl> getNotationDecls() {
        return this.notationDecls;
    }

    public SymbolTable getSymbolTable() {
        return this.fSymbolTable;
    }

    public boolean isCDATAAttribute(QName qName, QName qName2) {
        return !getAttributeDecl(getElementDeclIndex(qName), this.fAttributeDecl) || this.fAttributeDecl.simpleType.type == 0;
    }

    public void notationDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
        XMLNotationDecl xMLNotationDecl = new XMLNotationDecl();
        xMLNotationDecl.setValues(str, xMLResourceIdentifier.getPublicId(), xMLResourceIdentifier.getLiteralSystemId(), xMLResourceIdentifier.getBaseSystemId());
        this.notationDecls.add(xMLNotationDecl);
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

    public void setElementDecl(int i, XMLElementDecl xMLElementDecl) {
        if (i < 0 || i >= this.fElementDeclCount) {
            return;
        }
        int i2 = i >> 8;
        int i3 = i & 255;
        int i4 = xMLElementDecl.scope;
        this.fElementDeclName[i2][i3].setValues(xMLElementDecl.name);
        short[] sArr = this.fElementDeclType[i2];
        short s = xMLElementDecl.type;
        sArr[i3] = s;
        if (xMLElementDecl.simpleType.list) {
            sArr[i3] = (short) (s | 128);
        }
        this.fElementIndexMap.put(xMLElementDecl.name.rawname, Integer.valueOf(i));
    }

    public void setFirstAttributeDeclIndex(int i, int i2) {
        if (i < 0 || i >= this.fElementDeclCount) {
            return;
        }
        this.fElementDeclFirstAttributeDeclIndex[i >> 8][i & 255] = i2;
    }

    public void startDTD(XMLLocator xMLLocator, Augmentations augmentations) throws XNIException {
    }

    private static int[][] resize(int[][] iArr, int i) {
        int[][] iArr2 = new int[i][];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
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

    private static String[][][] resize(String[][][] strArr, int i) {
        String[][][] strArr2 = new String[i][][];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        return strArr2;
    }

    public int getElementDeclIndex(QName qName) {
        return getElementDeclIndex(qName.rawname);
    }
}
