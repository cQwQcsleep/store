package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XSAttributeDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSGrammarBucket;
import com.sun.org.apache.xerces.internal.impl.xs.util.XInt;
import com.sun.org.apache.xerces.internal.impl.xs.util.XIntPool;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.xni.QName;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSAttributeChecker {
    public static final int ATTIDX_ABSTRACT = 0;
    public static final int ATTIDX_AFORMDEFAULT = 1;
    public static final int ATTIDX_BASE;
    public static final int ATTIDX_BLOCK;
    public static final int ATTIDX_BLOCKDEFAULT;
    private static int ATTIDX_COUNT = 0;
    public static final int ATTIDX_DEFAULT;
    public static final int ATTIDX_EFORMDEFAULT;
    public static final int ATTIDX_ENUMNSDECLS;
    public static final int ATTIDX_FINAL;
    public static final int ATTIDX_FINALDEFAULT;
    public static final int ATTIDX_FIXED;
    public static final int ATTIDX_FORM;
    public static final int ATTIDX_FROMDEFAULT;
    public static final int ATTIDX_ID;
    public static final int ATTIDX_ISRETURNED;
    public static final int ATTIDX_ITEMTYPE;
    public static final int ATTIDX_MAXOCCURS;
    public static final int ATTIDX_MEMBERTYPES;
    public static final int ATTIDX_MINOCCURS;
    public static final int ATTIDX_MIXED;
    public static final int ATTIDX_NAME;
    public static final int ATTIDX_NAMESPACE;
    public static final int ATTIDX_NAMESPACE_LIST;
    public static final int ATTIDX_NILLABLE;
    public static final int ATTIDX_NONSCHEMA;
    public static final int ATTIDX_PROCESSCONTENTS;
    public static final int ATTIDX_PUBLIC;
    public static final int ATTIDX_REF;
    public static final int ATTIDX_REFER;
    public static final int ATTIDX_SCHEMALOCATION;
    public static final int ATTIDX_SOURCE;
    public static final int ATTIDX_SUBSGROUP;
    public static final int ATTIDX_SYSTEM;
    public static final int ATTIDX_TARGETNAMESPACE;
    public static final int ATTIDX_TYPE;
    public static final int ATTIDX_USE;
    public static final int ATTIDX_VALUE;
    public static final int ATTIDX_VERSION;
    public static final int ATTIDX_XML_LANG;
    public static final int ATTIDX_XPATH;
    private static final String ATTRIBUTE_N = "attribute_n";
    private static final String ATTRIBUTE_R = "attribute_r";
    protected static final int DT_ANYURI = 0;
    protected static final int DT_BLOCK = -1;
    protected static final int DT_BLOCK1 = -2;
    protected static final int DT_BOOLEAN = -15;
    protected static final int DT_COUNT = 9;
    protected static final int DT_FINAL = -3;
    protected static final int DT_FINAL1 = -4;
    protected static final int DT_FINAL2 = -5;
    protected static final int DT_FORM = -6;
    protected static final int DT_ID = 1;
    protected static final int DT_LANGUAGE = 8;
    protected static final int DT_MAXOCCURS = -7;
    protected static final int DT_MAXOCCURS1 = -8;
    protected static final int DT_MEMBERTYPES = -9;
    protected static final int DT_MINOCCURS1 = -10;
    protected static final int DT_NAMESPACE = -11;
    protected static final int DT_NCNAME = 5;
    protected static final int DT_NONNEGINT = -16;
    protected static final int DT_POSINT = -17;
    protected static final int DT_PROCESSCONTENTS = -12;
    protected static final int DT_QNAME = 2;
    protected static final int DT_STRING = 3;
    protected static final int DT_TOKEN = 4;
    protected static final int DT_USE = -13;
    protected static final int DT_WHITESPACE = -14;
    protected static final int DT_XPATH = 6;
    protected static final int DT_XPATH1 = 7;
    private static final String ELEMENT_N = "element_n";
    private static final String ELEMENT_R = "element_r";
    static final int INC_POOL_SIZE = 10;
    static final int INIT_POOL_SIZE = 10;
    private static final XInt INT_ANY_ANY;
    private static final XInt INT_ANY_LAX;
    private static final XInt INT_ANY_LIST;
    private static final XInt INT_ANY_NOT;
    private static final XInt INT_ANY_SKIP;
    private static final XInt INT_ANY_STRICT;
    private static final XInt INT_EMPTY_SET;
    private static final XInt INT_QUALIFIED;
    private static final XInt INT_UNBOUNDED;
    private static final XInt INT_UNQUALIFIED;
    private static final XInt INT_USE_OPTIONAL;
    private static final XInt INT_USE_PROHIBITED;
    private static final XInt INT_USE_REQUIRED;
    private static final XInt INT_WS_COLLAPSE;
    private static final XInt INT_WS_PRESERVE;
    private static final XInt INT_WS_REPLACE;
    private static final Map<String, Container> fEleAttrsMapG;
    private static final Map<String, Container> fEleAttrsMapL;
    private static final XSSimpleType[] fExtraDVs;
    private static boolean[] fSeenTemp;
    private static Object[] fTempArray;
    private static final XIntPool fXIntPool;
    Object[][] fArrayPool;
    int fPoolPos;
    protected XSDHandler fSchemaHandler;
    protected boolean[] fSeen;
    protected SymbolTable fSymbolTable = null;
    protected Map<String, List<String>> fNonSchemaAttrs = new HashMap();
    protected List<String> fNamespaceList = new ArrayList();

    static {
        int i = 1 + 1;
        int i2 = i + 1;
        ATTIDX_BASE = i;
        int i3 = i + 2;
        ATTIDX_BLOCK = i2;
        int i4 = i + 3;
        ATTIDX_BLOCKDEFAULT = i3;
        int i5 = i + 4;
        ATTIDX_DEFAULT = i4;
        int i6 = i + 5;
        ATTIDX_EFORMDEFAULT = i5;
        int i7 = i + 6;
        ATTIDX_FINAL = i6;
        int i8 = i + 7;
        ATTIDX_FINALDEFAULT = i7;
        int i9 = i + 8;
        ATTIDX_FIXED = i8;
        int i10 = i + 9;
        ATTIDX_FORM = i9;
        int i11 = i + 10;
        ATTIDX_ID = i10;
        int i12 = i + 11;
        ATTIDX_ITEMTYPE = i11;
        int i13 = i + 12;
        ATTIDX_MAXOCCURS = i12;
        int i14 = i + 13;
        ATTIDX_MEMBERTYPES = i13;
        int i15 = i + 14;
        ATTIDX_MINOCCURS = i14;
        int i16 = i + 15;
        ATTIDX_MIXED = i15;
        int i17 = i + 16;
        ATTIDX_NAME = i16;
        ATTIDX_NAMESPACE = i17;
        int i18 = i + 18;
        ATTIDX_NAMESPACE_LIST = i + 17;
        ATTIDX_NILLABLE = i18;
        int i19 = i + 20;
        ATTIDX_NONSCHEMA = i + 19;
        int i20 = i + 21;
        ATTIDX_PROCESSCONTENTS = i19;
        int i21 = i + 22;
        ATTIDX_PUBLIC = i20;
        int i22 = i + 23;
        ATTIDX_REF = i21;
        int i23 = i + 24;
        ATTIDX_REFER = i22;
        int i24 = i + 25;
        ATTIDX_SCHEMALOCATION = i23;
        int i25 = i + 26;
        ATTIDX_SOURCE = i24;
        int i26 = i + 27;
        ATTIDX_SUBSGROUP = i25;
        int i27 = i + 28;
        ATTIDX_SYSTEM = i26;
        int i28 = i + 29;
        ATTIDX_TARGETNAMESPACE = i27;
        int i29 = i + 30;
        ATTIDX_TYPE = i28;
        int i30 = i + 31;
        ATTIDX_USE = i29;
        ATTIDX_VALUE = i30;
        int i31 = i + 33;
        ATTIDX_ENUMNSDECLS = i + 32;
        int i32 = i + 34;
        ATTIDX_VERSION = i31;
        int i33 = i + 35;
        ATTIDX_XML_LANG = i32;
        ATTIDX_XPATH = i33;
        ATTIDX_FROMDEFAULT = i + 36;
        ATTIDX_COUNT = i + 38;
        ATTIDX_ISRETURNED = i + 37;
        XIntPool xIntPool = new XIntPool();
        fXIntPool = xIntPool;
        INT_QUALIFIED = xIntPool.getXInt(1);
        XInt xInt = xIntPool.getXInt(0);
        INT_UNQUALIFIED = xInt;
        XInt xInt2 = xIntPool.getXInt(0);
        INT_EMPTY_SET = xInt2;
        XInt xInt3 = xIntPool.getXInt(1);
        INT_ANY_STRICT = xInt3;
        INT_ANY_LAX = xIntPool.getXInt(3);
        INT_ANY_SKIP = xIntPool.getXInt(2);
        XInt xInt4 = xIntPool.getXInt(1);
        INT_ANY_ANY = xInt4;
        INT_ANY_LIST = xIntPool.getXInt(3);
        INT_ANY_NOT = xIntPool.getXInt(2);
        XInt xInt5 = xIntPool.getXInt(0);
        INT_USE_OPTIONAL = xInt5;
        INT_USE_REQUIRED = xIntPool.getXInt(1);
        INT_USE_PROHIBITED = xIntPool.getXInt(2);
        INT_WS_PRESERVE = xIntPool.getXInt(0);
        INT_WS_REPLACE = xIntPool.getXInt(1);
        INT_WS_COLLAPSE = xIntPool.getXInt(2);
        INT_UNBOUNDED = xIntPool.getXInt(-1);
        HashMap map = new HashMap(29);
        fEleAttrsMapG = map;
        HashMap map2 = new HashMap(79);
        fEleAttrsMapL = map2;
        XSSimpleType[] xSSimpleTypeArr = new XSSimpleType[9];
        fExtraDVs = xSSimpleTypeArr;
        SchemaGrammar.BuiltinSchemaGrammar builtinSchemaGrammar = SchemaGrammar.SG_SchemaNS;
        xSSimpleTypeArr[0] = (XSSimpleType) builtinSchemaGrammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_ANYURI);
        xSSimpleTypeArr[1] = (XSSimpleType) builtinSchemaGrammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_ID);
        xSSimpleTypeArr[2] = (XSSimpleType) builtinSchemaGrammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_QNAME);
        xSSimpleTypeArr[3] = (XSSimpleType) builtinSchemaGrammar.getGlobalTypeDecl("string");
        xSSimpleTypeArr[4] = (XSSimpleType) builtinSchemaGrammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_TOKEN);
        xSSimpleTypeArr[5] = (XSSimpleType) builtinSchemaGrammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_NCNAME);
        XSSimpleType xSSimpleType = xSSimpleTypeArr[3];
        xSSimpleTypeArr[6] = xSSimpleType;
        xSSimpleTypeArr[6] = xSSimpleType;
        xSSimpleTypeArr[8] = (XSSimpleType) builtinSchemaGrammar.getGlobalTypeDecl("language");
        String str = SchemaSymbols.ATT_ABSTRACT;
        Boolean bool = Boolean.FALSE;
        String str2 = SchemaSymbols.ATT_ATTRIBUTEFORMDEFAULT;
        String str3 = SchemaSymbols.ATT_BASE;
        String str4 = SchemaSymbols.ATT_BLOCK;
        String str5 = SchemaSymbols.ATT_BLOCKDEFAULT;
        String str6 = SchemaSymbols.ATT_DEFAULT;
        String str7 = SchemaSymbols.ATT_ELEMENTFORMDEFAULT;
        String str8 = SchemaSymbols.ATT_FINAL;
        String str9 = SchemaSymbols.ATT_FINALDEFAULT;
        String str10 = SchemaSymbols.ATT_FIXED;
        String str11 = SchemaSymbols.ATT_FORM;
        String str12 = SchemaSymbols.ATT_ID;
        String str13 = SchemaSymbols.ATT_ITEMTYPE;
        String str14 = SchemaSymbols.ATT_MAXOCCURS;
        String str15 = SchemaSymbols.ATT_MEMBERTYPES;
        String str16 = SchemaSymbols.ATT_MINOCCURS;
        String str17 = SchemaSymbols.ATT_MIXED;
        String str18 = SchemaSymbols.ATT_NAME;
        String str19 = SchemaSymbols.ATT_NAMESPACE;
        String str20 = SchemaSymbols.ATT_NILLABLE;
        String str21 = SchemaSymbols.ATT_PROCESSCONTENTS;
        String str22 = SchemaSymbols.ATT_PUBLIC;
        String str23 = SchemaSymbols.ATT_REF;
        String str24 = SchemaSymbols.ATT_REFER;
        String str25 = SchemaSymbols.ATT_SCHEMALOCATION;
        String str26 = SchemaSymbols.ATT_SOURCE;
        String str27 = SchemaSymbols.ATT_SUBSTITUTIONGROUP;
        String str28 = SchemaSymbols.ATT_SYSTEM;
        String str29 = SchemaSymbols.ATT_TARGETNAMESPACE;
        String str30 = SchemaSymbols.ATT_TYPE;
        String str31 = SchemaSymbols.ATT_USE;
        String str32 = SchemaSymbols.ATT_VALUE;
        String str33 = SchemaSymbols.ATT_VERSION;
        String str34 = SchemaSymbols.ATT_XML_LANG;
        String str35 = SchemaSymbols.ATT_XPATH;
        OneAttr[] oneAttrArr = {new OneAttr(str, DT_BOOLEAN, 0, bool), new OneAttr(str2, -6, 1, xInt), new OneAttr(str3, 2, i, null), new OneAttr(str3, 2, i, null), new OneAttr(str4, -1, i2, null), new OneAttr(str4, -2, i2, null), new OneAttr(str5, -1, i3, xInt2), new OneAttr(str6, 3, i4, null), new OneAttr(str7, -6, i5, xInt), new OneAttr(str8, -3, i6, null), new OneAttr(str8, -4, i6, null), new OneAttr(str9, -5, i7, xInt2), new OneAttr(str10, 3, i8, null), new OneAttr(str10, DT_BOOLEAN, i8, bool), new OneAttr(str11, -6, i9, null), new OneAttr(str12, 1, i10, null), new OneAttr(str13, 2, i11, null), new OneAttr(str14, -7, i12, xIntPool.getXInt(1)), new OneAttr(str14, -8, i12, xIntPool.getXInt(1)), new OneAttr(str15, DT_MEMBERTYPES, i13, null), new OneAttr(str16, -16, i14, xIntPool.getXInt(1)), new OneAttr(str16, -10, i14, xIntPool.getXInt(1)), new OneAttr(str17, DT_BOOLEAN, i15, bool), new OneAttr(str17, DT_BOOLEAN, i15, null), new OneAttr(str18, 5, i16, null), new OneAttr(str19, -11, i17, xInt4), new OneAttr(str19, 0, i17, null), new OneAttr(str20, DT_BOOLEAN, i18, bool), new OneAttr(str21, -12, i19, xInt3), new OneAttr(str22, 4, i20, null), new OneAttr(str23, 2, i21, null), new OneAttr(str24, 2, i22, null), new OneAttr(str25, 0, i23, null), new OneAttr(str25, 0, i23, null), new OneAttr(str26, 0, i24, null), new OneAttr(str27, 2, i25, null), new OneAttr(str28, 0, i26, null), new OneAttr(str29, 0, i27, null), new OneAttr(str30, 2, i28, null), new OneAttr(str31, DT_USE, i29, xInt5), new OneAttr(str32, -16, i30, null), new OneAttr(str32, DT_POSINT, i30, null), new OneAttr(str32, 3, i30, null), new OneAttr(str32, -14, i30, null), new OneAttr(str33, 4, i31, null), new OneAttr(str34, 8, i32, null), new OneAttr(str35, 6, i33, null), new OneAttr(str35, 7, i33, null)};
        Container container = Container.getContainer(5);
        container.put(str6, oneAttrArr[7]);
        container.put(str10, oneAttrArr[12]);
        container.put(str12, oneAttrArr[15]);
        container.put(str18, oneAttrArr[24]);
        container.put(str30, oneAttrArr[38]);
        map.put(SchemaSymbols.ELT_ATTRIBUTE, container);
        Container container2 = Container.getContainer(7);
        container2.put(str6, oneAttrArr[7]);
        container2.put(str10, oneAttrArr[12]);
        container2.put(str11, oneAttrArr[14]);
        container2.put(str12, oneAttrArr[15]);
        container2.put(str18, oneAttrArr[24]);
        container2.put(str30, oneAttrArr[38]);
        container2.put(str31, oneAttrArr[39]);
        map2.put(ATTRIBUTE_N, container2);
        Container container3 = Container.getContainer(5);
        container3.put(str6, oneAttrArr[7]);
        container3.put(str10, oneAttrArr[12]);
        container3.put(str12, oneAttrArr[15]);
        container3.put(str23, oneAttrArr[30]);
        container3.put(str31, oneAttrArr[39]);
        map2.put(ATTRIBUTE_R, container3);
        Container container4 = Container.getContainer(10);
        container4.put(str, oneAttrArr[0]);
        container4.put(str4, oneAttrArr[4]);
        container4.put(str6, oneAttrArr[7]);
        container4.put(str8, oneAttrArr[9]);
        container4.put(str10, oneAttrArr[12]);
        container4.put(str12, oneAttrArr[15]);
        container4.put(str18, oneAttrArr[24]);
        container4.put(str20, oneAttrArr[27]);
        container4.put(str27, oneAttrArr[35]);
        container4.put(str30, oneAttrArr[38]);
        map.put(SchemaSymbols.ELT_ELEMENT, container4);
        Container container5 = Container.getContainer(10);
        container5.put(str4, oneAttrArr[4]);
        container5.put(str6, oneAttrArr[7]);
        container5.put(str10, oneAttrArr[12]);
        container5.put(str11, oneAttrArr[14]);
        container5.put(str12, oneAttrArr[15]);
        container5.put(str14, oneAttrArr[17]);
        container5.put(str16, oneAttrArr[20]);
        container5.put(str18, oneAttrArr[24]);
        container5.put(str20, oneAttrArr[27]);
        container5.put(str30, oneAttrArr[38]);
        map2.put(ELEMENT_N, container5);
        Container container6 = Container.getContainer(4);
        container6.put(str12, oneAttrArr[15]);
        container6.put(str14, oneAttrArr[17]);
        container6.put(str16, oneAttrArr[20]);
        container6.put(str23, oneAttrArr[30]);
        map2.put(ELEMENT_R, container6);
        Container container7 = Container.getContainer(6);
        container7.put(str, oneAttrArr[0]);
        container7.put(str4, oneAttrArr[5]);
        container7.put(str8, oneAttrArr[9]);
        container7.put(str12, oneAttrArr[15]);
        container7.put(str17, oneAttrArr[22]);
        container7.put(str18, oneAttrArr[24]);
        String str36 = SchemaSymbols.ELT_COMPLEXTYPE;
        map.put(str36, container7);
        Container container8 = Container.getContainer(4);
        container8.put(str12, oneAttrArr[15]);
        container8.put(str18, oneAttrArr[24]);
        container8.put(str22, oneAttrArr[29]);
        container8.put(str28, oneAttrArr[36]);
        map.put(SchemaSymbols.ELT_NOTATION, container8);
        Container container9 = Container.getContainer(2);
        container9.put(str12, oneAttrArr[15]);
        container9.put(str17, oneAttrArr[22]);
        map2.put(str36, container9);
        Container container10 = Container.getContainer(1);
        container10.put(str12, oneAttrArr[15]);
        map2.put(SchemaSymbols.ELT_SIMPLECONTENT, container10);
        Container container11 = Container.getContainer(2);
        container11.put(str3, oneAttrArr[3]);
        container11.put(str12, oneAttrArr[15]);
        map2.put(SchemaSymbols.ELT_RESTRICTION, container11);
        Container container12 = Container.getContainer(2);
        container12.put(str3, oneAttrArr[2]);
        container12.put(str12, oneAttrArr[15]);
        map2.put(SchemaSymbols.ELT_EXTENSION, container12);
        Container container13 = Container.getContainer(2);
        container13.put(str12, oneAttrArr[15]);
        container13.put(str23, oneAttrArr[30]);
        String str37 = SchemaSymbols.ELT_ATTRIBUTEGROUP;
        map2.put(str37, container13);
        Container container14 = Container.getContainer(3);
        container14.put(str12, oneAttrArr[15]);
        container14.put(str19, oneAttrArr[25]);
        container14.put(str21, oneAttrArr[28]);
        map2.put(SchemaSymbols.ELT_ANYATTRIBUTE, container14);
        Container container15 = Container.getContainer(2);
        container15.put(str12, oneAttrArr[15]);
        container15.put(str17, oneAttrArr[23]);
        map2.put(SchemaSymbols.ELT_COMPLEXCONTENT, container15);
        Container container16 = Container.getContainer(2);
        container16.put(str12, oneAttrArr[15]);
        container16.put(str18, oneAttrArr[24]);
        map.put(str37, container16);
        Container container17 = Container.getContainer(2);
        container17.put(str12, oneAttrArr[15]);
        container17.put(str18, oneAttrArr[24]);
        String str38 = SchemaSymbols.ELT_GROUP;
        map.put(str38, container17);
        Container container18 = Container.getContainer(4);
        container18.put(str12, oneAttrArr[15]);
        container18.put(str14, oneAttrArr[17]);
        container18.put(str16, oneAttrArr[20]);
        container18.put(str23, oneAttrArr[30]);
        map2.put(str38, container18);
        Container container19 = Container.getContainer(3);
        container19.put(str12, oneAttrArr[15]);
        container19.put(str14, oneAttrArr[18]);
        container19.put(str16, oneAttrArr[21]);
        map2.put(SchemaSymbols.ELT_ALL, container19);
        Container container20 = Container.getContainer(3);
        container20.put(str12, oneAttrArr[15]);
        container20.put(str14, oneAttrArr[17]);
        container20.put(str16, oneAttrArr[20]);
        map2.put(SchemaSymbols.ELT_CHOICE, container20);
        map2.put(SchemaSymbols.ELT_SEQUENCE, container20);
        Container container21 = Container.getContainer(5);
        container21.put(str12, oneAttrArr[15]);
        container21.put(str14, oneAttrArr[17]);
        container21.put(str16, oneAttrArr[20]);
        container21.put(str19, oneAttrArr[25]);
        container21.put(str21, oneAttrArr[28]);
        map2.put(SchemaSymbols.ELT_ANY, container21);
        Container container22 = Container.getContainer(2);
        container22.put(str12, oneAttrArr[15]);
        container22.put(str18, oneAttrArr[24]);
        map2.put(SchemaSymbols.ELT_UNIQUE, container22);
        map2.put(SchemaSymbols.ELT_KEY, container22);
        Container container23 = Container.getContainer(3);
        container23.put(str12, oneAttrArr[15]);
        container23.put(str18, oneAttrArr[24]);
        container23.put(str24, oneAttrArr[31]);
        map2.put(SchemaSymbols.ELT_KEYREF, container23);
        Container container24 = Container.getContainer(2);
        container24.put(str12, oneAttrArr[15]);
        container24.put(str35, oneAttrArr[46]);
        map2.put(SchemaSymbols.ELT_SELECTOR, container24);
        Container container25 = Container.getContainer(2);
        container25.put(str12, oneAttrArr[15]);
        container25.put(str35, oneAttrArr[47]);
        map2.put(SchemaSymbols.ELT_FIELD, container25);
        Container container26 = Container.getContainer(1);
        container26.put(str12, oneAttrArr[15]);
        String str39 = SchemaSymbols.ELT_ANNOTATION;
        map.put(str39, container26);
        map2.put(str39, container26);
        Container container27 = Container.getContainer(1);
        container27.put(str26, oneAttrArr[34]);
        String str40 = SchemaSymbols.ELT_APPINFO;
        map.put(str40, container27);
        map2.put(str40, container27);
        Container container28 = Container.getContainer(2);
        container28.put(str26, oneAttrArr[34]);
        container28.put(str34, oneAttrArr[45]);
        String str41 = SchemaSymbols.ELT_DOCUMENTATION;
        map.put(str41, container28);
        map2.put(str41, container28);
        Container container29 = Container.getContainer(3);
        container29.put(str8, oneAttrArr[10]);
        container29.put(str12, oneAttrArr[15]);
        container29.put(str18, oneAttrArr[24]);
        String str42 = SchemaSymbols.ELT_SIMPLETYPE;
        map.put(str42, container29);
        Container container30 = Container.getContainer(2);
        container30.put(str8, oneAttrArr[10]);
        container30.put(str12, oneAttrArr[15]);
        map2.put(str42, container30);
        Container container31 = Container.getContainer(2);
        container31.put(str12, oneAttrArr[15]);
        container31.put(str13, oneAttrArr[16]);
        map2.put(SchemaSymbols.ELT_LIST, container31);
        Container container32 = Container.getContainer(2);
        container32.put(str12, oneAttrArr[15]);
        container32.put(str15, oneAttrArr[19]);
        map2.put(SchemaSymbols.ELT_UNION, container32);
        Container container33 = Container.getContainer(8);
        container33.put(str2, oneAttrArr[1]);
        container33.put(str5, oneAttrArr[6]);
        container33.put(str7, oneAttrArr[8]);
        container33.put(str9, oneAttrArr[11]);
        container33.put(str12, oneAttrArr[15]);
        container33.put(str29, oneAttrArr[37]);
        container33.put(str33, oneAttrArr[44]);
        container33.put(str34, oneAttrArr[45]);
        map.put(SchemaSymbols.ELT_SCHEMA, container33);
        Container container34 = Container.getContainer(2);
        container34.put(str12, oneAttrArr[15]);
        container34.put(str25, oneAttrArr[32]);
        map.put(SchemaSymbols.ELT_INCLUDE, container34);
        map.put(SchemaSymbols.ELT_REDEFINE, container34);
        Container container35 = Container.getContainer(3);
        container35.put(str12, oneAttrArr[15]);
        container35.put(str19, oneAttrArr[26]);
        container35.put(str25, oneAttrArr[33]);
        map.put(SchemaSymbols.ELT_IMPORT, container35);
        Container container36 = Container.getContainer(3);
        container36.put(str12, oneAttrArr[15]);
        container36.put(str32, oneAttrArr[40]);
        container36.put(str10, oneAttrArr[13]);
        map2.put(SchemaSymbols.ELT_LENGTH, container36);
        map2.put(SchemaSymbols.ELT_MINLENGTH, container36);
        map2.put(SchemaSymbols.ELT_MAXLENGTH, container36);
        map2.put(SchemaSymbols.ELT_FRACTIONDIGITS, container36);
        Container container37 = Container.getContainer(3);
        container37.put(str12, oneAttrArr[15]);
        container37.put(str32, oneAttrArr[41]);
        container37.put(str10, oneAttrArr[13]);
        map2.put(SchemaSymbols.ELT_TOTALDIGITS, container37);
        Container container38 = Container.getContainer(2);
        container38.put(str12, oneAttrArr[15]);
        container38.put(str32, oneAttrArr[42]);
        map2.put(SchemaSymbols.ELT_PATTERN, container38);
        Container container39 = Container.getContainer(2);
        container39.put(str12, oneAttrArr[15]);
        container39.put(str32, oneAttrArr[42]);
        map2.put(SchemaSymbols.ELT_ENUMERATION, container39);
        Container container40 = Container.getContainer(3);
        container40.put(str12, oneAttrArr[15]);
        container40.put(str32, oneAttrArr[43]);
        container40.put(str10, oneAttrArr[13]);
        map2.put(SchemaSymbols.ELT_WHITESPACE, container40);
        Container container41 = Container.getContainer(3);
        container41.put(str12, oneAttrArr[15]);
        container41.put(str32, oneAttrArr[42]);
        container41.put(str10, oneAttrArr[13]);
        map2.put(SchemaSymbols.ELT_MAXINCLUSIVE, container41);
        map2.put(SchemaSymbols.ELT_MAXEXCLUSIVE, container41);
        map2.put(SchemaSymbols.ELT_MININCLUSIVE, container41);
        map2.put(SchemaSymbols.ELT_MINEXCLUSIVE, container41);
        int i34 = ATTIDX_COUNT;
        fSeenTemp = new boolean[i34];
        fTempArray = new Object[i34];
    }

    public XSAttributeChecker(XSDHandler xSDHandler) {
        this.fSchemaHandler = null;
        int i = ATTIDX_COUNT;
        this.fSeen = new boolean[i];
        this.fArrayPool = (Object[][]) Array.newInstance((Class<?>) Object.class, 10, i);
        this.fPoolPos = 0;
        this.fSchemaHandler = xSDHandler;
    }

    public static String normalize(String str, short s) {
        int i;
        int i2;
        char cCharAt;
        int length = str == null ? 0 : str.length();
        if (length == 0 || s == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        if (s == 1) {
            for (int i3 = 0; i3 < length; i3++) {
                char cCharAt2 = str.charAt(i3);
                if (cCharAt2 == '\t' || cCharAt2 == '\n' || cCharAt2 == '\r') {
                    sb.append(' ');
                } else {
                    sb.append(cCharAt2);
                }
            }
        } else {
            int i4 = 0;
            boolean z = true;
            while (i4 < length) {
                char cCharAt3 = str.charAt(i4);
                if (cCharAt3 == '\t' || cCharAt3 == '\n' || cCharAt3 == '\r' || cCharAt3 == ' ') {
                    while (true) {
                        i = length - 1;
                        if (i4 >= i || !((cCharAt = str.charAt((i2 = i4 + 1))) == '\t' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == ' ')) {
                            break;
                        }
                        i4 = i2;
                    }
                    if (i4 < i && !z) {
                        sb.append(' ');
                    }
                } else {
                    sb.append(cCharAt3);
                    z = false;
                }
                i4++;
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:282:0x028c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:285:0x0254 A[SYNTHETIC] */
    private Object validate(Object[] objArr, String str, String str2, int i, XSDocumentInfo xSDocumentInfo) throws InvalidDatatypeValueException {
        String strAddSymbol;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        if (str2 == null) {
            return null;
        }
        String strTrim = XMLChar.trim(str2);
        ValidatedInfo validatedInfo = null;
        int i10 = 0;
        switch (i) {
            case DT_POSINT /* -17 */:
                try {
                    if (strTrim.length() > 0 && strTrim.charAt(0) == '+') {
                        strTrim = strTrim.substring(1);
                    }
                    XInt xInt = fXIntPool.getXInt(Integer.parseInt(strTrim));
                    if (xInt.intValue() > 0) {
                        return xInt;
                    }
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{strTrim, SchemaSymbols.ATTVAL_POSITIVEINTEGER});
                } catch (NumberFormatException unused) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{strTrim, SchemaSymbols.ATTVAL_POSITIVEINTEGER});
                }
            case -16:
                try {
                    if (strTrim.length() > 0 && strTrim.charAt(0) == '+') {
                        strTrim = strTrim.substring(1);
                    }
                    XInt xInt2 = fXIntPool.getXInt(Integer.parseInt(strTrim));
                    if (xInt2.intValue() >= 0) {
                        return xInt2;
                    }
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{strTrim, SchemaSymbols.ATTVAL_NONNEGATIVEINTEGER});
                } catch (NumberFormatException unused2) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{strTrim, SchemaSymbols.ATTVAL_NONNEGATIVEINTEGER});
                }
            case DT_BOOLEAN /* -15 */:
                if (strTrim.equals("false") || strTrim.equals("0")) {
                    return Boolean.FALSE;
                }
                if (strTrim.equals("true") || strTrim.equals("1")) {
                    return Boolean.TRUE;
                }
                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{strTrim, "boolean"});
            case -14:
                if (strTrim.equals(SchemaSymbols.ATTVAL_PRESERVE)) {
                    return INT_WS_PRESERVE;
                }
                if (strTrim.equals(SchemaSymbols.ATTVAL_REPLACE)) {
                    return INT_WS_REPLACE;
                }
                if (strTrim.equals(SchemaSymbols.ATTVAL_COLLAPSE)) {
                    return INT_WS_COLLAPSE;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[]{strTrim, "(preserve | replace | collapse)"});
            case DT_USE /* -13 */:
                if (strTrim.equals(SchemaSymbols.ATTVAL_OPTIONAL)) {
                    return INT_USE_OPTIONAL;
                }
                if (strTrim.equals(SchemaSymbols.ATTVAL_REQUIRED)) {
                    return INT_USE_REQUIRED;
                }
                if (strTrim.equals(SchemaSymbols.ATTVAL_PROHIBITED)) {
                    return INT_USE_PROHIBITED;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[]{strTrim, "(optional | prohibited | required)"});
            case -12:
                if (strTrim.equals(SchemaSymbols.ATTVAL_STRICT)) {
                    return INT_ANY_STRICT;
                }
                if (strTrim.equals(SchemaSymbols.ATTVAL_LAX)) {
                    return INT_ANY_LAX;
                }
                if (strTrim.equals(SchemaSymbols.ATTVAL_SKIP)) {
                    return INT_ANY_SKIP;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[]{strTrim, "(lax | skip | strict)"});
            case -11:
                if (strTrim.equals(SchemaSymbols.ATTVAL_TWOPOUNDANY)) {
                    return INT_ANY_ANY;
                }
                if (strTrim.equals(SchemaSymbols.ATTVAL_TWOPOUNDOTHER)) {
                    XInt xInt3 = INT_ANY_NOT;
                    objArr[ATTIDX_NAMESPACE_LIST] = new String[]{xSDocumentInfo.fTargetNamespace, null};
                    return xInt3;
                }
                XInt xInt4 = INT_ANY_LIST;
                this.fNamespaceList.clear();
                StringTokenizer stringTokenizer = new StringTokenizer(strTrim, " \n\t\r");
                while (stringTokenizer.hasMoreTokens()) {
                    try {
                        String strNextToken = stringTokenizer.nextToken();
                        if (strNextToken.equals(SchemaSymbols.ATTVAL_TWOPOUNDLOCAL)) {
                            strAddSymbol = null;
                        } else {
                            if (strNextToken.equals(SchemaSymbols.ATTVAL_TWOPOUNDTARGETNS)) {
                                strAddSymbol = xSDocumentInfo.fTargetNamespace;
                            } else {
                                fExtraDVs[0].validate(strNextToken, (ValidationContext) xSDocumentInfo.fValidationContext, (ValidatedInfo) null);
                                strAddSymbol = this.fSymbolTable.addSymbol(strNextToken);
                            }
                            if (!this.fNamespaceList.contains(strAddSymbol)) {
                                this.fNamespaceList.add(strAddSymbol);
                            }
                        }
                        if (!this.fNamespaceList.contains(strAddSymbol)) {
                            this.fNamespaceList.add(strAddSymbol);
                        }
                    } catch (InvalidDatatypeValueException unused3) {
                        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[]{strTrim, "((##any | ##other) | List of (anyURI | (##targetNamespace | ##local)) )"});
                    }
                }
                objArr[ATTIDX_NAMESPACE_LIST] = (String[]) this.fNamespaceList.toArray(new String[this.fNamespaceList.size()]);
                return xInt4;
            case -10:
                if (strTrim.equals("0")) {
                    return fXIntPool.getXInt(0);
                }
                if (strTrim.equals("1")) {
                    return fXIntPool.getXInt(1);
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[]{strTrim, "(0 | 1)"});
            case DT_MEMBERTYPES /* -9 */:
                ArrayList arrayList = new ArrayList();
                try {
                    StringTokenizer stringTokenizer2 = new StringTokenizer(strTrim, " \n\t\r");
                    while (stringTokenizer2.hasMoreTokens()) {
                        QName qName = (QName) fExtraDVs[2].validate(stringTokenizer2.nextToken(), (ValidationContext) xSDocumentInfo.fValidationContext, validatedInfo);
                        if (qName.prefix == XMLSymbols.EMPTY_STRING && qName.uri == null && xSDocumentInfo.fIsChameleonSchema) {
                            qName.uri = xSDocumentInfo.fTargetNamespace;
                        }
                        arrayList.add(qName);
                        validatedInfo = null;
                        break;
                    }
                    return arrayList;
                } catch (InvalidDatatypeValueException unused4) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.2", new Object[]{strTrim, "(List of QName)"});
                }
            case -8:
                if (strTrim.equals("1")) {
                    return fXIntPool.getXInt(1);
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[]{strTrim, "(1)"});
            case -7:
                if (strTrim.equals(SchemaSymbols.ATTVAL_UNBOUNDED)) {
                    return INT_UNBOUNDED;
                }
                try {
                    return validate(objArr, str, strTrim, -16, xSDocumentInfo);
                } catch (NumberFormatException unused5) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[]{strTrim, "(nonNegativeInteger | unbounded)"});
                }
            case -6:
                if (strTrim.equals(SchemaSymbols.ATTVAL_QUALIFIED)) {
                    return INT_QUALIFIED;
                }
                if (strTrim.equals(SchemaSymbols.ATTVAL_UNQUALIFIED)) {
                    return INT_UNQUALIFIED;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[]{strTrim, "(qualified | unqualified)"});
            case -5:
                if (strTrim.equals(SchemaSymbols.ATTVAL_POUNDALL)) {
                    i2 = 31;
                } else {
                    StringTokenizer stringTokenizer3 = new StringTokenizer(strTrim, " \n\t\r");
                    i2 = 0;
                    while (stringTokenizer3.hasMoreTokens()) {
                        String strNextToken2 = stringTokenizer3.nextToken();
                        if (strNextToken2.equals(SchemaSymbols.ATTVAL_EXTENSION)) {
                            i3 = i2 | 1;
                        } else if (strNextToken2.equals(SchemaSymbols.ATTVAL_RESTRICTION)) {
                            i3 = i2 | 2;
                        } else if (strNextToken2.equals(SchemaSymbols.ATTVAL_LIST)) {
                            i3 = i2 | 16;
                        } else {
                            if (!strNextToken2.equals(SchemaSymbols.ATTVAL_UNION)) {
                                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[]{strTrim, "(#all | List of (extension | restriction | list | union))"});
                            }
                            i3 = i2 | 8;
                        }
                        i2 = i3;
                    }
                }
                return fXIntPool.getXInt(i2);
            case -4:
                if (strTrim.equals(SchemaSymbols.ATTVAL_POUNDALL)) {
                    i4 = 31;
                } else {
                    StringTokenizer stringTokenizer4 = new StringTokenizer(strTrim, " \n\t\r");
                    i4 = 0;
                    while (stringTokenizer4.hasMoreTokens()) {
                        String strNextToken3 = stringTokenizer4.nextToken();
                        if (strNextToken3.equals(SchemaSymbols.ATTVAL_LIST)) {
                            i5 = i4 | 16;
                        } else if (strNextToken3.equals(SchemaSymbols.ATTVAL_UNION)) {
                            i5 = i4 | 8;
                        } else {
                            if (!strNextToken3.equals(SchemaSymbols.ATTVAL_RESTRICTION)) {
                                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[]{strTrim, "(#all | List of (list | union | restriction))"});
                            }
                            i5 = i4 | 2;
                        }
                        i4 = i5;
                    }
                }
                return fXIntPool.getXInt(i4);
            case -3:
            case -2:
                if (strTrim.equals(SchemaSymbols.ATTVAL_POUNDALL)) {
                    i6 = 31;
                } else {
                    StringTokenizer stringTokenizer5 = new StringTokenizer(strTrim, " \n\t\r");
                    i6 = 0;
                    while (stringTokenizer5.hasMoreTokens()) {
                        String strNextToken4 = stringTokenizer5.nextToken();
                        if (strNextToken4.equals(SchemaSymbols.ATTVAL_EXTENSION)) {
                            i7 = i6 | 1;
                        } else {
                            if (!strNextToken4.equals(SchemaSymbols.ATTVAL_RESTRICTION)) {
                                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[]{strTrim, "(#all | List of (extension | restriction))"});
                            }
                            i7 = i6 | 2;
                        }
                        i6 = i7;
                    }
                }
                return fXIntPool.getXInt(i6);
            case -1:
                if (strTrim.equals(SchemaSymbols.ATTVAL_POUNDALL)) {
                    i8 = 7;
                } else {
                    StringTokenizer stringTokenizer6 = new StringTokenizer(strTrim, " \n\t\r");
                    while (stringTokenizer6.hasMoreTokens()) {
                        String strNextToken5 = stringTokenizer6.nextToken();
                        if (strNextToken5.equals(SchemaSymbols.ATTVAL_EXTENSION)) {
                            i9 = i10 | 1;
                        } else if (strNextToken5.equals(SchemaSymbols.ATTVAL_RESTRICTION)) {
                            i9 = i10 | 2;
                        } else {
                            if (!strNextToken5.equals(SchemaSymbols.ATTVAL_SUBSTITUTION)) {
                                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[]{strTrim, "(#all | List of (extension | restriction | substitution))"});
                            }
                            i9 = i10 | 4;
                        }
                        i10 = i9;
                    }
                    i8 = i10;
                }
                return fXIntPool.getXInt(i8);
            default:
                return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:132:0x0188 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:22:0x005f A[PHI: r0
      0x005f: PHI (r0v4 java.util.Map<java.lang.String, com.sun.org.apache.xerces.internal.impl.xs.traversers.Container>) = 
      (r0v3 java.util.Map<java.lang.String, com.sun.org.apache.xerces.internal.impl.xs.traversers.Container>)
      (r0v38 java.util.Map<java.lang.String, com.sun.org.apache.xerces.internal.impl.xs.traversers.Container>)
     binds: [B:9:0x002f, B:17:0x004f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:55:0x0104  */
    /* JADX WARN: Code duplicated, block: B:57:0x010a  */
    /* JADX WARN: Code duplicated, block: B:58:0x0113  */
    /* JADX WARN: Code duplicated, block: B:61:0x011e  */
    /* JADX WARN: Code duplicated, block: B:63:0x0121  */
    /* JADX WARN: Code duplicated, block: B:79:0x0153 A[Catch: InvalidDatatypeValueException -> 0x014c, TryCatch #0 {InvalidDatatypeValueException -> 0x014c, blocks: (B:59:0x011a, B:67:0x0127, B:69:0x0136, B:71:0x013f, B:73:0x0143, B:75:0x0147, B:78:0x014e, B:79:0x0153, B:80:0x0156), top: B:124:0x011a }] */
    /* JADX WARN: Code duplicated, block: B:80:0x0156 A[Catch: InvalidDatatypeValueException -> 0x014c, TRY_LEAVE, TryCatch #0 {InvalidDatatypeValueException -> 0x014c, blocks: (B:59:0x011a, B:67:0x0127, B:69:0x0136, B:71:0x013f, B:73:0x0143, B:75:0x0147, B:78:0x014e, B:79:0x0153, B:80:0x0156), top: B:124:0x011a }] */
    /* JADX WARN: Code duplicated, block: B:87:0x017b A[ADDED_TO_REGION] */
    public Object[] checkAttributes(Element element, boolean z, XSDocumentInfo xSDocumentInfo, boolean z2) {
        String str;
        int limit;
        OneAttr oneAttr;
        int i;
        int i2;
        if (element == null) {
            return null;
        }
        Attr[] attrs = DOMUtil.getAttrs(element);
        resolveNamespace(element, attrs, xSDocumentInfo.fNamespaceSupport);
        String namespaceURI = DOMUtil.getNamespaceURI(element);
        String localName = DOMUtil.getLocalName(element);
        if (!SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(namespaceURI)) {
            reportSchemaError("s4s-elt-schema-ns", new Object[]{localName}, element);
        }
        Map<String, Container> map = fEleAttrsMapG;
        if (z) {
            str = localName;
        } else {
            map = fEleAttrsMapL;
            if (localName.equals(SchemaSymbols.ELT_ELEMENT)) {
                str = DOMUtil.getAttr(element, SchemaSymbols.ATT_REF) != null ? ELEMENT_R : ELEMENT_N;
            } else if (localName.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                str = DOMUtil.getAttr(element, SchemaSymbols.ATT_REF) != null ? ATTRIBUTE_R : ATTRIBUTE_N;
            } else {
                str = localName;
            }
        }
        Container container = map.get(str);
        if (container == null) {
            reportSchemaError("s4s-elt-invalid", new Object[]{localName}, element);
            return null;
        }
        Object[] availableArray = getAvailableArray();
        System.arraycopy(fSeenTemp, 0, this.fSeen, 0, ATTIDX_COUNT);
        for (Attr attr : attrs) {
            String name = attr.getName();
            String namespaceURI2 = DOMUtil.getNamespaceURI(attr);
            String value = DOMUtil.getValue(attr);
            if (name.startsWith("xml")) {
                if (!"xmlns".equals(DOMUtil.getPrefix(attr)) && !"xmlns".equals(name)) {
                    if (SchemaSymbols.ATT_XML_LANG.equals(name) && (SchemaSymbols.ELT_SCHEMA.equals(localName) || SchemaSymbols.ELT_DOCUMENTATION.equals(localName))) {
                        namespaceURI2 = null;
                    }
                    if (namespaceURI2 != null) {
                        oneAttr = container.get(name);
                        if (oneAttr == null) {
                            reportSchemaError("s4s-att-not-allowed", new Object[]{localName, name}, element);
                        } else {
                            boolean[] zArr = this.fSeen;
                            i = oneAttr.valueIndex;
                            zArr[i] = true;
                            i2 = oneAttr.dvIndex;
                            if (i2 < 0) {
                                availableArray[i] = validate(availableArray, name, value, i2, xSDocumentInfo);
                            } else if (i2 != 3) {
                                availableArray[i] = value;
                            } else {
                                availableArray[i] = value;
                            }
                            if (!localName.equals(SchemaSymbols.ELT_ENUMERATION)) {
                            }
                        }
                    } else {
                        oneAttr = container.get(name);
                        if (oneAttr == null) {
                            reportSchemaError("s4s-att-not-allowed", new Object[]{localName, name}, element);
                        } else {
                            boolean[] zArr2 = this.fSeen;
                            i = oneAttr.valueIndex;
                            zArr2[i] = true;
                            i2 = oneAttr.dvIndex;
                            if (i2 < 0) {
                                availableArray[i] = validate(availableArray, name, value, i2, xSDocumentInfo);
                            } else if (i2 != 3) {
                                availableArray[i] = value;
                            } else {
                                availableArray[i] = value;
                            }
                            if (!localName.equals(SchemaSymbols.ELT_ENUMERATION)) {
                            }
                        }
                    }
                }
            } else if (namespaceURI2 != null || namespaceURI2.length() == 0) {
                oneAttr = container.get(name);
                if (oneAttr == null) {
                    reportSchemaError("s4s-att-not-allowed", new Object[]{localName, name}, element);
                } else {
                    boolean[] zArr3 = this.fSeen;
                    i = oneAttr.valueIndex;
                    zArr3[i] = true;
                    try {
                        i2 = oneAttr.dvIndex;
                        if (i2 < 0) {
                            availableArray[i] = validate(availableArray, name, value, i2, xSDocumentInfo);
                        } else if (i2 != 3 || i2 == 6 || i2 == 7) {
                            availableArray[i] = value;
                        } else {
                            Object objValidate = fExtraDVs[i2].validate(value, (ValidationContext) xSDocumentInfo.fValidationContext, (ValidatedInfo) null);
                            if (oneAttr.dvIndex == 2) {
                                QName qName = (QName) objValidate;
                                if (qName.prefix == XMLSymbols.EMPTY_STRING && qName.uri == null && xSDocumentInfo.fIsChameleonSchema) {
                                    qName.uri = xSDocumentInfo.fTargetNamespace;
                                }
                            }
                            availableArray[oneAttr.valueIndex] = objValidate;
                        }
                    } catch (InvalidDatatypeValueException e) {
                        reportSchemaError("s4s-att-invalid-value", new Object[]{localName, name, e.getMessage()}, element);
                        Object obj = oneAttr.dfltValue;
                        if (obj != null) {
                            availableArray[oneAttr.valueIndex] = obj;
                        }
                    }
                    if (!localName.equals(SchemaSymbols.ELT_ENUMERATION) && z2) {
                        availableArray[ATTIDX_ENUMNSDECLS] = new SchemaNamespaceSupport(xSDocumentInfo.fNamespaceSupport);
                    }
                }
            } else if (namespaceURI2.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA)) {
                reportSchemaError("s4s-att-not-allowed", new Object[]{localName, name}, element);
            } else {
                int i3 = ATTIDX_NONSCHEMA;
                Object obj2 = availableArray[i3];
                List arrayList = obj2 == null ? new ArrayList(4) : (List) obj2;
                arrayList.add(name);
                arrayList.add(value);
                availableArray[i3] = arrayList;
            }
        }
        long j = 0;
        for (OneAttr oneAttr2 : container.values) {
            Object obj3 = oneAttr2.dfltValue;
            if (obj3 != null) {
                boolean[] zArr4 = this.fSeen;
                int i4 = oneAttr2.valueIndex;
                if (!zArr4[i4]) {
                    availableArray[i4] = obj3;
                    j |= (long) (1 << i4);
                }
            }
        }
        availableArray[ATTIDX_FROMDEFAULT] = Long.valueOf(j);
        int i5 = ATTIDX_MAXOCCURS;
        if (availableArray[i5] != null) {
            int i6 = ATTIDX_MINOCCURS;
            int iIntValue = ((XInt) availableArray[i6]).intValue();
            int iIntValue2 = ((XInt) availableArray[i5]).intValue();
            if (iIntValue2 != -1) {
                if (this.fSchemaHandler.fSecurityManager != null) {
                    String localName2 = element.getLocalName();
                    if (((!localName2.equals("element") && !localName2.equals("any")) || element.getNextSibling() != null || element.getPreviousSibling() != null || !element.getParentNode().getLocalName().equals("sequence")) && iIntValue2 > (limit = this.fSchemaHandler.fSecurityManager.getLimit(XMLSecurityManager.Limit.MAX_OCCUR_NODE_LIMIT)) && !this.fSchemaHandler.fSecurityManager.isNoLimit(limit)) {
                        reportSchemaFatalError("MaxOccurLimit", new Object[]{Integer.valueOf(limit)}, element);
                        availableArray[i5] = fXIntPool.getXInt(limit);
                        iIntValue2 = limit;
                    }
                }
                if (iIntValue > iIntValue2) {
                    reportSchemaError("p-props-correct.2.1", new Object[]{localName, availableArray[i6], availableArray[i5]}, element);
                    availableArray[i6] = availableArray[i5];
                }
            }
        }
        return availableArray;
    }

    public void checkNonSchemaAttributes(XSGrammarBucket xSGrammarBucket) {
        XSAttributeDecl globalAttributeDecl;
        XSSimpleType xSSimpleType;
        for (Map.Entry<String, List<String>> entry : this.fNonSchemaAttrs.entrySet()) {
            String key = entry.getKey();
            String strSubstring = key.substring(0, key.indexOf(44));
            String strSubstring2 = key.substring(key.indexOf(44) + 1);
            SchemaGrammar grammar = xSGrammarBucket.getGrammar(strSubstring);
            if (grammar != null && (globalAttributeDecl = grammar.getGlobalAttributeDecl(strSubstring2)) != null && (xSSimpleType = (XSSimpleType) globalAttributeDecl.getTypeDefinition()) != null) {
                List<String> value = entry.getValue();
                String str = value.get(0);
                int size = value.size();
                for (int i = 1; i < size; i += 2) {
                    String str2 = value.get(i);
                    try {
                        xSSimpleType.validate(value.get(i + 1), (ValidationContext) null, (ValidatedInfo) null);
                    } catch (InvalidDatatypeValueException e) {
                        reportSchemaError("s4s-att-invalid-value", new Object[]{str2, str, e.getMessage()}, null);
                    }
                }
            }
        }
    }

    public Object[] getAvailableArray() {
        int length = this.fArrayPool.length;
        int i = this.fPoolPos;
        if (length == i) {
            this.fArrayPool = new Object[i + 10][];
            while (true) {
                Object[][] objArr = this.fArrayPool;
                if (i >= objArr.length) {
                    break;
                }
                objArr[i] = new Object[ATTIDX_COUNT];
                i++;
            }
        }
        Object[][] objArr2 = this.fArrayPool;
        int i2 = this.fPoolPos;
        Object[] objArr3 = objArr2[i2];
        this.fPoolPos = i2 + 1;
        objArr2[i2] = null;
        System.arraycopy(fTempArray, 0, objArr3, 0, ATTIDX_COUNT - 1);
        objArr3[ATTIDX_ISRETURNED] = Boolean.FALSE;
        return objArr3;
    }

    public void reportSchemaError(String str, Object[] objArr, Element element) {
        this.fSchemaHandler.reportSchemaError(str, objArr, element);
    }

    public void reportSchemaFatalError(String str, Object[] objArr, Element element) {
        this.fSchemaHandler.reportSchemaFatalError(str, objArr, element);
    }

    public void reset(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
        this.fNonSchemaAttrs.clear();
    }

    public void resolveNamespace(Element element, Attr[] attrArr, SchemaNamespaceSupport schemaNamespaceSupport) {
        schemaNamespaceSupport.pushContext();
        for (Attr attr : attrArr) {
            String name = DOMUtil.getName(attr);
            String strAddSymbol = name.equals(XMLSymbols.PREFIX_XMLNS) ? XMLSymbols.EMPTY_STRING : name.startsWith("xmlns:") ? this.fSymbolTable.addSymbol(DOMUtil.getLocalName(attr)) : null;
            if (strAddSymbol != null) {
                String strAddSymbol2 = this.fSymbolTable.addSymbol(DOMUtil.getValue(attr));
                schemaNamespaceSupport.declarePrefix(strAddSymbol, strAddSymbol2.length() != 0 ? strAddSymbol2 : null);
            }
        }
    }

    public void returnAttrArray(Object[] objArr, XSDocumentInfo xSDocumentInfo) {
        if (xSDocumentInfo != null) {
            xSDocumentInfo.fNamespaceSupport.popContext();
        }
        if (this.fPoolPos == 0 || objArr == null || objArr.length != ATTIDX_COUNT) {
            return;
        }
        int i = ATTIDX_ISRETURNED;
        if (((Boolean) objArr[i]).booleanValue()) {
            return;
        }
        objArr[i] = Boolean.TRUE;
        Object obj = objArr[ATTIDX_NONSCHEMA];
        if (obj != null) {
            ((List) obj).clear();
        }
        Object[][] objArr2 = this.fArrayPool;
        int i2 = this.fPoolPos - 1;
        this.fPoolPos = i2;
        objArr2[i2] = objArr;
    }

    public Object[] checkAttributes(Element element, boolean z, XSDocumentInfo xSDocumentInfo) {
        return checkAttributes(element, z, xSDocumentInfo, false);
    }
}
