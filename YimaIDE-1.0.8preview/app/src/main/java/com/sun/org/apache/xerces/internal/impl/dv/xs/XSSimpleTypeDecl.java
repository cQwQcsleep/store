package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.DatatypeException;
import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeFacetException;
import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.dv.XSFacets;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.util.ObjectListImpl;
import com.sun.org.apache.xerces.internal.impl.xs.util.ShortListImpl;
import com.sun.org.apache.xerces.internal.impl.xs.util.StringListImpl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.org.apache.xerces.internal.xs.XSAnnotation;
import com.sun.org.apache.xerces.internal.xs.XSFacet;
import com.sun.org.apache.xerces.internal.xs.XSMultiValueFacet;
import com.sun.org.apache.xerces.internal.xs.XSNamespaceItem;
import com.sun.org.apache.xerces.internal.xs.XSObject;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import org.w3c.dom.TypeInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSSimpleTypeDecl implements XSSimpleType, TypeInfo {
    public static final short ANYATOMICTYPE_DT = 49;
    static final String ANY_TYPE = "anyType";
    public static final short DAYTIMEDURATION_DT = 47;
    static final int DERIVATION_ANY = 0;
    static final int DERIVATION_EXTENSION = 2;
    static final int DERIVATION_LIST = 8;
    static final int DERIVATION_RESTRICTION = 1;
    static final int DERIVATION_UNION = 4;
    protected static final short DV_ANYATOMICTYPE = 29;
    protected static final short DV_ANYSIMPLETYPE = 0;
    protected static final short DV_ANYURI = 17;
    protected static final short DV_BASE64BINARY = 16;
    protected static final short DV_BOOLEAN = 2;
    protected static final short DV_DATE = 9;
    protected static final short DV_DATETIME = 7;
    protected static final short DV_DAYTIMEDURATION = 28;
    protected static final short DV_DECIMAL = 3;
    protected static final short DV_DOUBLE = 5;
    protected static final short DV_DURATION = 6;
    protected static final short DV_ENTITY = 23;
    protected static final short DV_FLOAT = 4;
    protected static final short DV_GDAY = 13;
    protected static final short DV_GMONTH = 14;
    protected static final short DV_GMONTHDAY = 12;
    protected static final short DV_GYEAR = 11;
    protected static final short DV_GYEARMONTH = 10;
    protected static final short DV_HEXBINARY = 15;
    protected static final short DV_ID = 21;
    protected static final short DV_IDREF = 22;
    protected static final short DV_INTEGER = 24;
    protected static final short DV_LIST = 25;
    protected static final short DV_NOTATION = 20;
    protected static final short DV_PRECISIONDECIMAL = 19;
    protected static final short DV_QNAME = 18;
    protected static final short DV_STRING = 1;
    protected static final short DV_TIME = 8;
    protected static final short DV_UNION = 26;
    protected static final short DV_YEARMONTHDURATION = 27;
    static final short NORMALIZE_FULL = 2;
    static final short NORMALIZE_NONE = 0;
    static final short NORMALIZE_TRIM = 1;
    public static final short PRECISIONDECIMAL_DT = 48;
    static final short SPECIAL_PATTERN_NAME = 2;
    static final short SPECIAL_PATTERN_NCNAME = 3;
    static final short SPECIAL_PATTERN_NMTOKEN = 1;
    static final short SPECIAL_PATTERN_NONE = 0;
    static final String URI_SCHEMAFORSCHEMA = "http://www.w3.org/2001/XMLSchema";
    public static final short YEARMONTHDURATION_DT = 46;
    static final XSSimpleTypeDecl fAnyAtomicType;
    static final XSSimpleTypeDecl fAnySimpleType;
    static final ValidationContext fDummyContext;
    public XSObjectList enumerationAnnotations;
    private ObjectList fActualEnumeration;
    private XSObjectList fAnnotations;
    private boolean fAnonymous;
    private XSSimpleTypeDecl fBase;
    private boolean fBounded;
    private short fBuiltInKind;
    private TypeValidator[] fDVs;
    private ValidatedInfo[] fEnumeration;
    private ObjectList fEnumerationItemTypeList;
    private int fEnumerationSize;
    private ShortList fEnumerationTypeList;
    private XSObjectListImpl fFacets;
    private short fFacetsDefined;
    private short fFinalSet;
    private boolean fFinite;
    private short fFixedFacet;
    private int fFractionDigits;
    private boolean fIsImmutable;
    private XSSimpleTypeDecl fItemType;
    private int fLength;
    private StringList fLexicalEnumeration;
    private StringList fLexicalPattern;
    private Object fMaxExclusive;
    private Object fMaxInclusive;
    private int fMaxLength;
    private XSSimpleTypeDecl[] fMemberTypes;
    private Object fMinExclusive;
    private Object fMinInclusive;
    private int fMinLength;
    private XSObjectListImpl fMultiValueFacets;
    private XSNamespaceItem fNamespaceItem;
    private boolean fNumeric;
    private short fOrdered;
    private List<RegularExpression> fPattern;
    private List<String> fPatternStr;
    private short fPatternType;
    private String fTargetNamespace;
    private int fTotalDigits;
    private String fTypeName;
    private short fValidationDV;
    private short fVariety;
    private short fWhiteSpace;
    public XSAnnotation fractionDigitsAnnotation;
    public XSAnnotation lengthAnnotation;
    public XSAnnotation maxExclusiveAnnotation;
    public XSAnnotation maxInclusiveAnnotation;
    public XSAnnotation maxLengthAnnotation;
    public XSAnnotation minExclusiveAnnotation;
    public XSAnnotation minInclusiveAnnotation;
    public XSAnnotation minLengthAnnotation;
    public XSObjectListImpl patternAnnotations;
    public XSAnnotation totalDigitsAnnotation;
    public XSAnnotation whiteSpaceAnnotation;
    private static final TypeValidator[] gDVs = {new AnySimpleDV(), new StringDV(), new BooleanDV(), new DecimalDV(), new FloatDV(), new DoubleDV(), new DurationDV(), new DateTimeDV(), new TimeDV(), new DateDV(), new YearMonthDV(), new YearDV(), new MonthDayDV(), new DayDV(), new MonthDV(), new HexBinaryDV(), new Base64BinaryDV(), new AnyURIDV(), new QNameDV(), new PrecisionDecimalDV(), new QNameDV(), new IDDV(), new IDREFDV(), new EntityDV(), new IntegerDV(), new ListDV(), new UnionDV(), new YearMonthDurationDV(), new DayTimeDurationDV(), new AnyAtomicDV()};
    static final short[] fDVNormalizeType = {0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 1, 1, 0};
    static final String[] SPECIAL_PATTERN_STRING = {"NONE", SchemaSymbols.ATTVAL_NMTOKEN, SchemaSymbols.ATTVAL_NAME, SchemaSymbols.ATTVAL_NCNAME};
    static final String[] WS_FACET_STRING = {SchemaSymbols.ATTVAL_PRESERVE, SchemaSymbols.ATTVAL_REPLACE, SchemaSymbols.ATTVAL_COLLAPSE};
    static final ValidationContext fEmptyContext = new ValidationContext() { // from class: com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl.1
        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public void addId(String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public void addIdRef(String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public Locale getLocale() {
            return Locale.getDefault();
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public String getSymbol(String str) {
            return str.intern();
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public String getURI(String str) {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public boolean isEntityDeclared(String str) {
            return false;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public boolean isEntityUnparsed(String str) {
            return false;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public boolean isIdDeclared(String str) {
            return false;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public boolean needExtraChecking() {
            return false;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public boolean needFacetChecking() {
            return true;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public boolean needToNormalize() {
            return true;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public boolean useNamespaces() {
            return true;
        }
    };

    public static abstract class AbstractObjectList extends AbstractList<Object> implements ObjectList {
        private AbstractObjectList() {
        }

        @Override // java.util.AbstractList, java.util.List
        public Object get(int i) {
            if (i >= 0 && i < getLength()) {
                return item(i);
            }
            b1e.a("Index: ", i);
            return null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return getLength();
        }
    }

    public static final class ValidationContextImpl implements ValidationContext {
        final ValidationContext fExternal;
        NamespaceContext fNSContext;

        public ValidationContextImpl(ValidationContext validationContext) {
            this.fExternal = validationContext;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public void addId(String str) {
            this.fExternal.addId(str);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public void addIdRef(String str) {
            this.fExternal.addIdRef(str);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public Locale getLocale() {
            return this.fExternal.getLocale();
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public String getSymbol(String str) {
            return this.fExternal.getSymbol(str);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public String getURI(String str) {
            NamespaceContext namespaceContext = this.fNSContext;
            return namespaceContext == null ? this.fExternal.getURI(str) : namespaceContext.getURI(str);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public boolean isEntityDeclared(String str) {
            return this.fExternal.isEntityDeclared(str);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public boolean isEntityUnparsed(String str) {
            return this.fExternal.isEntityUnparsed(str);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public boolean isIdDeclared(String str) {
            return this.fExternal.isIdDeclared(str);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public boolean needExtraChecking() {
            return this.fExternal.needExtraChecking();
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public boolean needFacetChecking() {
            return this.fExternal.needFacetChecking();
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public boolean needToNormalize() {
            return this.fExternal.needToNormalize();
        }

        public void setNSContext(NamespaceContext namespaceContext) {
            this.fNSContext = namespaceContext;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
        public boolean useNamespaces() {
            return true;
        }
    }

    public static final class XSFacetImpl implements XSFacet {
        final XSObjectList annotations;
        Object avalue;
        final boolean fixed;
        final int ivalue;
        final short kind;
        final String svalue;

        public XSFacetImpl(short s, String str, int i, Object obj, boolean z, XSAnnotation xSAnnotation) {
            this.kind = s;
            this.svalue = str;
            this.ivalue = i;
            this.avalue = obj;
            this.fixed = z;
            if (xSAnnotation == null) {
                this.annotations = XSObjectListImpl.EMPTY_LIST;
                return;
            }
            XSObjectListImpl xSObjectListImpl = new XSObjectListImpl();
            this.annotations = xSObjectListImpl;
            xSObjectListImpl.addXSObject(xSAnnotation);
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSFacet
        public Object getActualFacetValue() {
            if (this.avalue == null) {
                if (this.kind == 16) {
                    this.avalue = this.svalue;
                } else {
                    this.avalue = BigInteger.valueOf(this.ivalue);
                }
            }
            return this.avalue;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSFacet
        public XSAnnotation getAnnotation() {
            return (XSAnnotation) this.annotations.item(0);
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSFacet
        public XSObjectList getAnnotations() {
            return this.annotations;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSFacet
        public short getFacetKind() {
            return this.kind;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSFacet
        public boolean getFixed() {
            return this.fixed;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSFacet
        public int getIntFacetValue() {
            return this.ivalue;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSFacet
        public String getLexicalFacetValue() {
            return this.svalue;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSObject
        public String getName() {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSObject
        public String getNamespace() {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSObject
        public XSNamespaceItem getNamespaceItem() {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSObject
        public short getType() {
            return (short) 13;
        }
    }

    public static final class XSMVFacetImpl implements XSMultiValueFacet {
        final XSObjectList annotations;
        final ObjectList avalues;
        final short kind;
        final StringList svalues;

        public XSMVFacetImpl(short s, StringList stringList, ObjectList objectList, XSObjectList xSObjectList) {
            this.kind = s;
            this.svalues = stringList;
            this.avalues = objectList;
            this.annotations = xSObjectList == null ? XSObjectListImpl.EMPTY_LIST : xSObjectList;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSMultiValueFacet
        public XSObjectList getAnnotations() {
            return this.annotations;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSMultiValueFacet
        public ObjectList getEnumerationValues() {
            return this.avalues;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSMultiValueFacet
        public short getFacetKind() {
            return this.kind;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSMultiValueFacet
        public StringList getLexicalFacetValues() {
            return this.svalues;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSObject
        public String getName() {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSObject
        public String getNamespace() {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSObject
        public XSNamespaceItem getNamespaceItem() {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.XSObject
        public short getType() {
            return (short) 14;
        }
    }

    static {
        XSSimpleTypeDecl xSSimpleTypeDecl = new XSSimpleTypeDecl(null, SchemaSymbols.ATTVAL_ANYSIMPLETYPE, (short) 0, (short) 0, false, true, false, true, (short) 1);
        fAnySimpleType = xSSimpleTypeDecl;
        fAnyAtomicType = new XSSimpleTypeDecl(xSSimpleTypeDecl, "anyAtomicType", (short) 29, (short) 0, false, true, false, true, (short) 49);
        fDummyContext = new ValidationContext() { // from class: com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl.4
            @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
            public void addId(String str) {
            }

            @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
            public void addIdRef(String str) {
            }

            @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
            public Locale getLocale() {
                return Locale.getDefault();
            }

            @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
            public String getSymbol(String str) {
                return str.intern();
            }

            @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
            public String getURI(String str) {
                return null;
            }

            @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
            public boolean isEntityDeclared(String str) {
                return false;
            }

            @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
            public boolean isEntityUnparsed(String str) {
                return false;
            }

            @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
            public boolean isIdDeclared(String str) {
                return false;
            }

            @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
            public boolean needExtraChecking() {
                return false;
            }

            @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
            public boolean needFacetChecking() {
                return true;
            }

            @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
            public boolean needToNormalize() {
                return false;
            }

            @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
            public boolean useNamespaces() {
                return true;
            }
        };
    }

    public XSSimpleTypeDecl(XSSimpleTypeDecl xSSimpleTypeDecl, String str, String str2, short s, boolean z, XSObjectList xSObjectList) {
        this.fDVs = gDVs;
        this.fIsImmutable = false;
        this.fVariety = (short) -1;
        this.fValidationDV = (short) -1;
        this.fFacetsDefined = (short) 0;
        this.fFixedFacet = (short) 0;
        this.fWhiteSpace = (short) 0;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fPatternType = (short) 0;
        this.fNamespaceItem = null;
        this.fAnonymous = false;
        this.fBase = xSSimpleTypeDecl;
        this.fTypeName = str;
        this.fTargetNamespace = str2;
        this.fFinalSet = s;
        this.fAnnotations = xSObjectList;
        short s2 = xSSimpleTypeDecl.fVariety;
        this.fVariety = s2;
        this.fValidationDV = xSSimpleTypeDecl.fValidationDV;
        if (s2 == 2) {
            this.fItemType = xSSimpleTypeDecl.fItemType;
        } else if (s2 == 3) {
            this.fMemberTypes = xSSimpleTypeDecl.fMemberTypes;
        }
        this.fLength = xSSimpleTypeDecl.fLength;
        this.fMinLength = xSSimpleTypeDecl.fMinLength;
        this.fMaxLength = xSSimpleTypeDecl.fMaxLength;
        this.fPattern = xSSimpleTypeDecl.fPattern;
        this.fPatternStr = xSSimpleTypeDecl.fPatternStr;
        this.fEnumeration = xSSimpleTypeDecl.fEnumeration;
        this.fEnumerationSize = xSSimpleTypeDecl.fEnumerationSize;
        this.fWhiteSpace = xSSimpleTypeDecl.fWhiteSpace;
        this.fMaxExclusive = xSSimpleTypeDecl.fMaxExclusive;
        this.fMaxInclusive = xSSimpleTypeDecl.fMaxInclusive;
        this.fMinExclusive = xSSimpleTypeDecl.fMinExclusive;
        this.fMinInclusive = xSSimpleTypeDecl.fMinInclusive;
        this.fTotalDigits = xSSimpleTypeDecl.fTotalDigits;
        this.fFractionDigits = xSSimpleTypeDecl.fFractionDigits;
        this.fPatternType = xSSimpleTypeDecl.fPatternType;
        this.fFixedFacet = xSSimpleTypeDecl.fFixedFacet;
        this.fFacetsDefined = xSSimpleTypeDecl.fFacetsDefined;
        this.lengthAnnotation = xSSimpleTypeDecl.lengthAnnotation;
        this.minLengthAnnotation = xSSimpleTypeDecl.minLengthAnnotation;
        this.maxLengthAnnotation = xSSimpleTypeDecl.maxLengthAnnotation;
        this.patternAnnotations = xSSimpleTypeDecl.patternAnnotations;
        this.enumerationAnnotations = xSSimpleTypeDecl.enumerationAnnotations;
        this.whiteSpaceAnnotation = xSSimpleTypeDecl.whiteSpaceAnnotation;
        this.maxExclusiveAnnotation = xSSimpleTypeDecl.maxExclusiveAnnotation;
        this.maxInclusiveAnnotation = xSSimpleTypeDecl.maxInclusiveAnnotation;
        this.minExclusiveAnnotation = xSSimpleTypeDecl.minExclusiveAnnotation;
        this.minInclusiveAnnotation = xSSimpleTypeDecl.minInclusiveAnnotation;
        this.totalDigitsAnnotation = xSSimpleTypeDecl.totalDigitsAnnotation;
        this.fractionDigitsAnnotation = xSSimpleTypeDecl.fractionDigitsAnnotation;
        calcFundamentalFacets();
        this.fIsImmutable = z;
        this.fBuiltInKind = xSSimpleTypeDecl.fBuiltInKind;
    }

    private void appendEnumString(StringBuffer stringBuffer) {
        stringBuffer.append('[');
        for (int i = 0; i < this.fEnumerationSize; i++) {
            if (i != 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(this.fEnumeration[i].actualValue);
        }
        stringBuffer.append(']');
    }

    private void calcFundamentalFacets() {
        setOrdered();
        setNumeric();
        setBounded();
        setCardinality();
    }

    private void checkExtraRules(ValidationContext validationContext, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        Object obj = validatedInfo.actualValue;
        short s = this.fVariety;
        if (s == 1) {
            this.fDVs[this.fValidationDV].checkExtraRules(obj, validationContext);
            return;
        }
        if (s != 2) {
            ((XSSimpleTypeDecl) validatedInfo.memberType).checkExtraRules(validationContext, validatedInfo);
            return;
        }
        ListDV.ListData listData = (ListDV.ListData) obj;
        XSSimpleType xSSimpleType = validatedInfo.memberType;
        int length = listData.getLength();
        try {
            if (this.fItemType.fVariety == 3) {
                XSSimpleTypeDecl[] xSSimpleTypeDeclArr = (XSSimpleTypeDecl[]) validatedInfo.memberTypes;
                for (int i = length - 1; i >= 0; i--) {
                    validatedInfo.actualValue = listData.item(i);
                    validatedInfo.memberType = xSSimpleTypeDeclArr[i];
                    this.fItemType.checkExtraRules(validationContext, validatedInfo);
                }
            } else {
                for (int i2 = length - 1; i2 >= 0; i2--) {
                    validatedInfo.actualValue = listData.item(i2);
                    this.fItemType.checkExtraRules(validationContext, validatedInfo);
                }
            }
            validatedInfo.actualValue = listData;
            validatedInfo.memberType = xSSimpleType;
        } catch (Throwable th) {
            validatedInfo.actualValue = listData;
            validatedInfo.memberType = xSSimpleType;
            throw th;
        }
    }

    private void checkFacets(ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        int iCompare;
        int iCompare2;
        int totalDigits;
        int fractionDigits;
        Object obj = validatedInfo.actualValue;
        String str = validatedInfo.normalizedValue;
        short s = validatedInfo.actualValueType;
        ShortList shortList = validatedInfo.itemValueTypes;
        short s2 = this.fValidationDV;
        if (s2 != 18 && s2 != 20) {
            int dataLength = this.fDVs[s2].getDataLength(obj);
            short s3 = this.fFacetsDefined;
            if ((s3 & 4) != 0 && dataLength > this.fMaxLength) {
                throw new InvalidDatatypeValueException("cvc-maxLength-valid", new Object[]{str, Integer.toString(dataLength), Integer.toString(this.fMaxLength), this.fTypeName});
            }
            if ((s3 & 2) != 0 && dataLength < this.fMinLength) {
                throw new InvalidDatatypeValueException("cvc-minLength-valid", new Object[]{str, Integer.toString(dataLength), Integer.toString(this.fMinLength), this.fTypeName});
            }
            if ((s3 & 1) != 0 && dataLength != this.fLength) {
                throw new InvalidDatatypeValueException("cvc-length-valid", new Object[]{str, Integer.toString(dataLength), Integer.toString(this.fLength), this.fTypeName});
            }
        }
        if ((this.fFacetsDefined & 2048) != 0) {
            int i = this.fEnumerationSize;
            short sConvertToPrimitiveKind = convertToPrimitiveKind(s);
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    StringBuffer stringBuffer = new StringBuffer();
                    appendEnumString(stringBuffer);
                    throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[]{str, stringBuffer.toString()});
                }
                short sConvertToPrimitiveKind2 = convertToPrimitiveKind(this.fEnumeration[i2].actualValueType);
                if ((sConvertToPrimitiveKind == sConvertToPrimitiveKind2 || ((sConvertToPrimitiveKind == 1 && sConvertToPrimitiveKind2 == 2) || (sConvertToPrimitiveKind == 2 && sConvertToPrimitiveKind2 == 1))) && this.fEnumeration[i2].actualValue.equals(obj)) {
                    if (sConvertToPrimitiveKind != 44 && sConvertToPrimitiveKind != 43) {
                        break;
                    }
                    ShortList shortList2 = this.fEnumeration[i2].itemValueTypes;
                    int length = shortList != null ? shortList.getLength() : 0;
                    if (length == (shortList2 != null ? shortList2.getLength() : 0)) {
                        int i3 = 0;
                        while (i3 < length) {
                            short sConvertToPrimitiveKind3 = convertToPrimitiveKind(shortList.item(i3));
                            short sConvertToPrimitiveKind4 = convertToPrimitiveKind(shortList2.item(i3));
                            if (sConvertToPrimitiveKind3 != sConvertToPrimitiveKind4 && ((sConvertToPrimitiveKind3 != 1 || sConvertToPrimitiveKind4 != 2) && (sConvertToPrimitiveKind3 != 2 || sConvertToPrimitiveKind4 != 1))) {
                                break;
                            } else {
                                i3++;
                            }
                        }
                        if (i3 == length) {
                            break;
                        }
                    } else {
                        continue;
                    }
                }
                i2++;
            }
        }
        if ((this.fFacetsDefined & 1024) != 0 && (fractionDigits = this.fDVs[this.fValidationDV].getFractionDigits(obj)) > this.fFractionDigits) {
            throw new InvalidDatatypeValueException("cvc-fractionDigits-valid", new Object[]{str, Integer.toString(fractionDigits), Integer.toString(this.fFractionDigits)});
        }
        if ((this.fFacetsDefined & 512) != 0 && (totalDigits = this.fDVs[this.fValidationDV].getTotalDigits(obj)) > this.fTotalDigits) {
            throw new InvalidDatatypeValueException("cvc-totalDigits-valid", new Object[]{str, Integer.toString(totalDigits), Integer.toString(this.fTotalDigits)});
        }
        if ((this.fFacetsDefined & 32) != 0 && (iCompare2 = this.fDVs[this.fValidationDV].compare(obj, this.fMaxInclusive)) != -1 && iCompare2 != 0) {
            throw new InvalidDatatypeValueException("cvc-maxInclusive-valid", new Object[]{str, this.fMaxInclusive, this.fTypeName});
        }
        if ((this.fFacetsDefined & 64) != 0 && this.fDVs[this.fValidationDV].compare(obj, this.fMaxExclusive) != -1) {
            throw new InvalidDatatypeValueException("cvc-maxExclusive-valid", new Object[]{str, this.fMaxExclusive, this.fTypeName});
        }
        if ((this.fFacetsDefined & 256) != 0 && (iCompare = this.fDVs[this.fValidationDV].compare(obj, this.fMinInclusive)) != 1 && iCompare != 0) {
            throw new InvalidDatatypeValueException("cvc-minInclusive-valid", new Object[]{str, this.fMinInclusive, this.fTypeName});
        }
        if ((this.fFacetsDefined & 128) != 0 && this.fDVs[this.fValidationDV].compare(obj, this.fMinExclusive) != 1) {
            throw new InvalidDatatypeValueException("cvc-minExclusive-valid", new Object[]{str, this.fMinExclusive, this.fTypeName});
        }
    }

    private short convertToPrimitiveKind(short s) {
        if (s > 20) {
            if (s <= 29) {
                return (short) 2;
            }
            if (s <= 42) {
                return (short) 4;
            }
        }
        return s;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0067  */
    /* JADX WARN: Multi-variable type inference failed */
    private Object getActualValue(Object obj, ValidationContext validationContext, ValidatedInfo validatedInfo, boolean z) throws InvalidDatatypeValueException {
        XSSimpleTypeDecl xSSimpleTypeDecl;
        short s;
        XSSimpleTypeDecl xSSimpleTypeDecl2;
        short s2;
        boolean zIsValidNCName;
        String strNormalize = z ? normalize(obj, this.fWhiteSpace) : obj.toString();
        if ((this.fFacetsDefined & 8) != 0) {
            for (int size = this.fPattern.size() - 1; size >= 0; size--) {
                if (!this.fPattern.get(size).matches(strNormalize)) {
                    throw new InvalidDatatypeValueException("cvc-pattern-valid", new Object[]{obj, this.fPatternStr.get(size), this.fTypeName});
                }
            }
        }
        short s3 = this.fVariety;
        int i = 0;
        if (s3 == 1) {
            short s4 = this.fPatternType;
            if (s4 != 0) {
                if (s4 == 1) {
                    zIsValidNCName = XMLChar.isValidNmtoken(strNormalize);
                } else if (s4 == 2) {
                    zIsValidNCName = XMLChar.isValidName(strNormalize);
                } else {
                    if (s4 == 3) {
                        zIsValidNCName = XMLChar.isValidNCName(strNormalize);
                    }
                    if (i != 0) {
                        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{strNormalize, SPECIAL_PATTERN_STRING[this.fPatternType]});
                    }
                }
                i = !zIsValidNCName;
                if (i != 0) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{strNormalize, SPECIAL_PATTERN_STRING[this.fPatternType]});
                }
            }
            validatedInfo.normalizedValue = strNormalize;
            Object actualValue = this.fDVs[this.fValidationDV].getActualValue(strNormalize, validationContext);
            validatedInfo.actualValue = actualValue;
            validatedInfo.actualValueType = this.fBuiltInKind;
            validatedInfo.actualType = this;
            return actualValue;
        }
        if (s3 == 2) {
            StringTokenizer stringTokenizer = new StringTokenizer(strNormalize, " ");
            int iCountTokens = stringTokenizer.countTokens();
            Object[] objArr = new Object[iCountTokens];
            boolean z2 = this.fItemType.getVariety() == 3;
            int i2 = z2 ? iCountTokens : 1;
            short[] sArr = new short[i2];
            if (!z2) {
                sArr[0] = this.fItemType.fBuiltInKind;
            }
            XSSimpleTypeDecl[] xSSimpleTypeDeclArr = new XSSimpleTypeDecl[iCountTokens];
            for (int i3 = 0; i3 < iCountTokens; i3++) {
                objArr[i3] = this.fItemType.getActualValue(stringTokenizer.nextToken(), validationContext, validatedInfo, false);
                if (validationContext.needFacetChecking() && (s2 = (xSSimpleTypeDecl2 = this.fItemType).fFacetsDefined) != 0 && s2 != 16) {
                    xSSimpleTypeDecl2.checkFacets(validatedInfo);
                }
                XSSimpleTypeDecl xSSimpleTypeDecl3 = (XSSimpleTypeDecl) validatedInfo.memberType;
                xSSimpleTypeDeclArr[i3] = xSSimpleTypeDecl3;
                if (z2) {
                    sArr[i3] = xSSimpleTypeDecl3.fBuiltInKind;
                }
            }
            ListDV.ListData listData = new ListDV.ListData(objArr);
            validatedInfo.actualValue = listData;
            validatedInfo.actualValueType = z2 ? (short) 43 : (short) 44;
            validatedInfo.memberType = null;
            validatedInfo.memberTypes = xSSimpleTypeDeclArr;
            validatedInfo.itemValueTypes = new ShortListImpl(sArr, i2);
            validatedInfo.normalizedValue = strNormalize;
            validatedInfo.actualType = this;
            return listData;
        }
        Object string = (this.fMemberTypes.length <= 1 || obj == null) ? obj : obj.toString();
        int i4 = 0;
        while (true) {
            XSSimpleTypeDecl[] xSSimpleTypeDeclArr2 = this.fMemberTypes;
            if (i4 >= xSSimpleTypeDeclArr2.length) {
                StringBuffer stringBuffer = new StringBuffer();
                while (i < this.fMemberTypes.length) {
                    if (i != 0) {
                        stringBuffer.append(" | ");
                    }
                    XSSimpleTypeDecl xSSimpleTypeDecl4 = this.fMemberTypes[i];
                    if (xSSimpleTypeDecl4.fTargetNamespace != null) {
                        stringBuffer.append('{');
                        stringBuffer.append(xSSimpleTypeDecl4.fTargetNamespace);
                        stringBuffer.append('}');
                    }
                    stringBuffer.append(xSSimpleTypeDecl4.fTypeName);
                    if (xSSimpleTypeDecl4.fEnumeration != null) {
                        stringBuffer.append(" : ");
                        xSSimpleTypeDecl4.appendEnumString(stringBuffer);
                    }
                    i++;
                }
                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[]{obj, this.fTypeName, stringBuffer.toString()});
            }
            try {
                Object actualValue2 = xSSimpleTypeDeclArr2[i4].getActualValue(string, validationContext, validatedInfo, true);
                if (validationContext.needFacetChecking() && (s = (xSSimpleTypeDecl = this.fMemberTypes[i4]).fFacetsDefined) != 0 && s != 16) {
                    xSSimpleTypeDecl.checkFacets(validatedInfo);
                }
                validatedInfo.memberType = this.fMemberTypes[i4];
                validatedInfo.actualType = this;
                return actualValue2;
            } catch (InvalidDatatypeValueException unused) {
                i4++;
            }
        }
    }

    public static TypeValidator[] getGDVs() {
        return (TypeValidator[]) gDVs.clone();
    }

    private short getPrimitiveDV(short s) {
        if (s == 21 || s == 22 || s == 23) {
            return (short) 1;
        }
        if (s == 24) {
            return (short) 3;
        }
        return s;
    }

    private boolean isDerivedByAny(String str, String str2, XSTypeDefinition xSTypeDefinition) {
        XSTypeDefinition baseType;
        xSTypeDefinition = null;
        while (xSTypeDefinition != null && xSTypeDefinition != xSTypeDefinition) {
            if ((str2.equals(xSTypeDefinition.getName()) && ((str == null && xSTypeDefinition.getNamespace() == null) || (str != null && str.equals(xSTypeDefinition.getNamespace())))) || isDerivedByRestriction(str, str2, xSTypeDefinition) || isDerivedByList(str, str2, xSTypeDefinition) || isDerivedByUnion(str, str2, xSTypeDefinition)) {
                return true;
            }
            XSSimpleTypeDecl xSSimpleTypeDecl = (XSSimpleTypeDecl) xSTypeDefinition;
            if (xSSimpleTypeDecl.getVariety() == 0 || xSSimpleTypeDecl.getVariety() == 1) {
                baseType = xSTypeDefinition.getBaseType();
                xSTypeDefinition = baseType;
            } else if (xSSimpleTypeDecl.getVariety() == 3) {
                if (xSSimpleTypeDecl.getMemberTypes().getLength() > 0) {
                    return isDerivedByAny(str, str2, (XSTypeDefinition) xSSimpleTypeDecl.getMemberTypes().item(0));
                }
            } else if (xSSimpleTypeDecl.getVariety() == 2) {
                baseType = xSSimpleTypeDecl.getItemType();
                xSTypeDefinition = baseType;
            }
        }
        return false;
    }

    private boolean isDerivedByList(String str, String str2, XSTypeDefinition xSTypeDefinition) {
        XSSimpleTypeDefinition itemType;
        if (xSTypeDefinition == null) {
            return false;
        }
        XSSimpleTypeDefinition xSSimpleTypeDefinition = (XSSimpleTypeDefinition) xSTypeDefinition;
        return xSSimpleTypeDefinition.getVariety() == 2 && (itemType = xSSimpleTypeDefinition.getItemType()) != null && isDerivedByRestriction(str, str2, itemType);
    }

    private boolean isDerivedByRestriction(String str, String str2, XSTypeDefinition xSTypeDefinition) {
        XSTypeDefinition xSTypeDefinition2 = null;
        while (xSTypeDefinition != null && xSTypeDefinition != xSTypeDefinition2) {
            if (str2.equals(xSTypeDefinition.getName())) {
                if (str != null && str.equals(xSTypeDefinition.getNamespace())) {
                    return true;
                }
                if (xSTypeDefinition.getNamespace() == null && str == null) {
                    return true;
                }
            }
            XSTypeDefinition xSTypeDefinition3 = xSTypeDefinition;
            xSTypeDefinition = xSTypeDefinition.getBaseType();
            xSTypeDefinition2 = xSTypeDefinition3;
        }
        return false;
    }

    private boolean isDerivedByUnion(String str, String str2, XSTypeDefinition xSTypeDefinition) {
        if (xSTypeDefinition != null) {
            XSSimpleTypeDefinition xSSimpleTypeDefinition = (XSSimpleTypeDefinition) xSTypeDefinition;
            if (xSSimpleTypeDefinition.getVariety() == 3) {
                XSObjectList memberTypes = xSSimpleTypeDefinition.getMemberTypes();
                for (int i = 0; i < memberTypes.getLength(); i++) {
                    if (memberTypes.item(i) != null && isDerivedByRestriction(str, str2, (XSSimpleTypeDefinition) memberTypes.item(i))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void setBounded() {
        short s = this.fVariety;
        if (s == 1) {
            short s2 = this.fFacetsDefined;
            if (((s2 & 256) == 0 && (s2 & 128) == 0) || ((s2 & 32) == 0 && (s2 & 64) == 0)) {
                this.fBounded = false;
                return;
            } else {
                this.fBounded = true;
                return;
            }
        }
        if (s == 2) {
            short s3 = this.fFacetsDefined;
            if ((s3 & 1) == 0 && ((s3 & 2) == 0 || (s3 & 4) == 0)) {
                this.fBounded = false;
                return;
            } else {
                this.fBounded = true;
                return;
            }
        }
        if (s == 3) {
            XSSimpleTypeDecl[] xSSimpleTypeDeclArr = this.fMemberTypes;
            short primitiveDV = xSSimpleTypeDeclArr.length > 0 ? getPrimitiveDV(xSSimpleTypeDeclArr[0].fValidationDV) : (short) 0;
            for (int i = 0; i < xSSimpleTypeDeclArr.length; i++) {
                if (!xSSimpleTypeDeclArr[i].getBounded() || primitiveDV != getPrimitiveDV(xSSimpleTypeDeclArr[i].fValidationDV)) {
                    this.fBounded = false;
                    return;
                }
            }
            this.fBounded = true;
        }
    }

    private void setCardinality() {
        short s = this.fVariety;
        if (s != 1) {
            if (s == 2) {
                short s2 = this.fFacetsDefined;
                if ((s2 & 1) == 0 && ((s2 & 2) == 0 || (s2 & 4) == 0)) {
                    this.fFinite = false;
                    return;
                } else {
                    this.fFinite = true;
                    return;
                }
            }
            if (s == 3) {
                for (XSSimpleTypeDecl xSSimpleTypeDecl : this.fMemberTypes) {
                    if (!xSSimpleTypeDecl.getFinite()) {
                        this.fFinite = false;
                        return;
                    }
                }
                this.fFinite = true;
                return;
            }
            return;
        }
        if (this.fBase.fFinite) {
            this.fFinite = true;
            return;
        }
        short s3 = this.fFacetsDefined;
        if ((s3 & 1) != 0 || (s3 & 4) != 0 || (s3 & 512) != 0) {
            this.fFinite = true;
            return;
        }
        if (((s3 & 256) == 0 && (s3 & 128) == 0) || ((s3 & 32) == 0 && (s3 & 64) == 0)) {
            this.fFinite = false;
        } else if ((s3 & 1024) != 0 || specialCardinalityCheck()) {
            this.fFinite = true;
        } else {
            this.fFinite = false;
        }
    }

    private void setNumeric() {
        short s = this.fVariety;
        if (s == 1) {
            this.fNumeric = this.fBase.fNumeric;
            return;
        }
        if (s == 2) {
            this.fNumeric = false;
            return;
        }
        if (s == 3) {
            for (XSSimpleTypeDecl xSSimpleTypeDecl : this.fMemberTypes) {
                if (!xSSimpleTypeDecl.getNumeric()) {
                    this.fNumeric = false;
                    return;
                }
            }
            this.fNumeric = true;
        }
    }

    private void setOrdered() {
        XSSimpleTypeDecl[] xSSimpleTypeDeclArr;
        short s = this.fVariety;
        if (s == 1) {
            this.fOrdered = this.fBase.fOrdered;
            return;
        }
        if (s == 2) {
            this.fOrdered = (short) 0;
            return;
        }
        if (s == 3) {
            XSSimpleTypeDecl[] xSSimpleTypeDeclArr2 = this.fMemberTypes;
            if (xSSimpleTypeDeclArr2.length == 0) {
                this.fOrdered = (short) 1;
                return;
            }
            short primitiveDV = getPrimitiveDV(xSSimpleTypeDeclArr2[0].fValidationDV);
            boolean z = primitiveDV != 0;
            boolean z2 = this.fMemberTypes[0].fOrdered == 0;
            int i = 1;
            while (true) {
                xSSimpleTypeDeclArr = this.fMemberTypes;
                if (i >= xSSimpleTypeDeclArr.length || !(z || z2)) {
                    break;
                }
                if (z) {
                    z = primitiveDV == getPrimitiveDV(xSSimpleTypeDeclArr[i].fValidationDV);
                }
                if (z2) {
                    z2 = this.fMemberTypes[i].fOrdered == 0;
                }
                i++;
            }
            if (z) {
                this.fOrdered = xSSimpleTypeDeclArr[0].fOrdered;
            } else if (z2) {
                this.fOrdered = (short) 0;
            } else {
                this.fOrdered = (short) 1;
            }
        }
    }

    private boolean specialCardinalityCheck() {
        short s = this.fBase.fValidationDV;
        return s == 9 || s == 10 || s == 11 || s == 12 || s == 13 || s == 14;
    }

    private String whiteSpaceValue(short s) {
        return WS_FACET_STRING[s];
    }

    /* JADX WARN: Code duplicated, block: B:508:0x0411 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:518:0x033e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public void applyFacets(XSFacets xSFacets, short s, short s2, short s3, ValidationContext validationContext) throws InvalidDatatypeFacetException {
        short s4;
        Object obj;
        Object obj2;
        short s5;
        int i;
        int i2;
        int i3;
        int i4;
        short s6;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int iCompare;
        int iCompare2;
        int i14;
        ValidationContextImpl validationContextImpl;
        List<NamespaceContext> list;
        RegularExpression regularExpression;
        if (this.fIsImmutable) {
            return;
        }
        ValidatedInfo validatedInfo = new ValidatedInfo();
        this.fFacetsDefined = (short) 0;
        this.fFixedFacet = (short) 0;
        short allowedFacets = this.fDVs[this.fValidationDV].getAllowedFacets();
        if ((s & 1) != 0) {
            if ((allowedFacets & 1) == 0) {
                reportError("cos-applicable-facets", new Object[]{"length", this.fTypeName});
            } else {
                this.fLength = xSFacets.length;
                this.lengthAnnotation = xSFacets.lengthAnnotation;
                this.fFacetsDefined = (short) (this.fFacetsDefined | 1);
                if ((s2 & 1) != 0) {
                    this.fFixedFacet = (short) (this.fFixedFacet | 1);
                }
            }
        }
        if ((s & 2) != 0) {
            if ((allowedFacets & 2) == 0) {
                reportError("cos-applicable-facets", new Object[]{"minLength", this.fTypeName});
            } else {
                this.fMinLength = xSFacets.minLength;
                this.minLengthAnnotation = xSFacets.minLengthAnnotation;
                this.fFacetsDefined = (short) (this.fFacetsDefined | 2);
                if ((s2 & 2) != 0) {
                    this.fFixedFacet = (short) (this.fFixedFacet | 2);
                }
            }
        }
        if ((s & 4) != 0) {
            if ((allowedFacets & 4) == 0) {
                reportError("cos-applicable-facets", new Object[]{"maxLength", this.fTypeName});
            } else {
                this.fMaxLength = xSFacets.maxLength;
                this.maxLengthAnnotation = xSFacets.maxLengthAnnotation;
                this.fFacetsDefined = (short) (this.fFacetsDefined | 4);
                if ((s2 & 4) != 0) {
                    this.fFixedFacet = (short) (this.fFixedFacet | 4);
                }
            }
        }
        if ((s & 8) == 0) {
            s4 = 2;
        } else if ((allowedFacets & 8) == 0) {
            s4 = 2;
            reportError("cos-applicable-facets", new Object[]{"pattern", this.fTypeName});
        } else {
            s4 = 2;
            this.patternAnnotations = xSFacets.patternAnnotations;
            try {
                regularExpression = new RegularExpression(xSFacets.pattern, "X", validationContext.getLocale());
            } catch (Exception e) {
                reportError("InvalidRegex", new Object[]{xSFacets.pattern, e.getLocalizedMessage()});
                regularExpression = null;
            }
            if (regularExpression != null) {
                ArrayList arrayList = new ArrayList();
                this.fPattern = arrayList;
                arrayList.add(regularExpression);
                ArrayList arrayList2 = new ArrayList();
                this.fPatternStr = arrayList2;
                arrayList2.add(xSFacets.pattern);
                this.fFacetsDefined = (short) (this.fFacetsDefined | 8);
                if ((s2 & 8) != 0) {
                    this.fFixedFacet = (short) (this.fFixedFacet | 8);
                }
            }
        }
        if ((s & 16) != 0) {
            if ((allowedFacets & 16) == 0) {
                reportError("cos-applicable-facets", new Object[]{"whiteSpace", this.fTypeName});
            } else {
                this.fWhiteSpace = xSFacets.whiteSpace;
                this.whiteSpaceAnnotation = xSFacets.whiteSpaceAnnotation;
                this.fFacetsDefined = (short) (this.fFacetsDefined | 16);
                if ((s2 & 16) != 0) {
                    this.fFixedFacet = (short) (this.fFixedFacet | 16);
                }
            }
        }
        if ((s & 2048) == 0) {
            obj = "whiteSpace";
        } else if ((allowedFacets & 2048) == 0) {
            reportError("cos-applicable-facets", new Object[]{"enumeration", this.fTypeName});
            obj = "whiteSpace";
        } else {
            List<String> list2 = xSFacets.enumeration;
            int size = list2.size();
            this.fEnumeration = new ValidatedInfo[size];
            List<NamespaceContext> list3 = xSFacets.enumNSDecls;
            ValidationContextImpl validationContextImpl2 = new ValidationContextImpl(validationContext);
            obj = "whiteSpace";
            this.enumerationAnnotations = xSFacets.enumAnnotations;
            int i15 = 0;
            this.fEnumerationSize = 0;
            while (i15 < size) {
                if (list3 != null) {
                    validationContextImpl2.setNSContext(list3.get(i15));
                }
                try {
                    list = list3;
                    try {
                        ValidatedInfo actualEnumValue = getActualEnumValue(list2.get(i15), validationContextImpl2, null);
                        ValidatedInfo[] validatedInfoArr = this.fEnumeration;
                        validationContextImpl = validationContextImpl2;
                        try {
                            int i16 = this.fEnumerationSize;
                            this.fEnumerationSize = i16 + 1;
                            validatedInfoArr[i16] = actualEnumValue;
                        } catch (InvalidDatatypeValueException unused) {
                            reportError("enumeration-valid-restriction", new Object[]{list2.get(i15), getBaseType().getName()});
                        }
                    } catch (InvalidDatatypeValueException unused2) {
                        validationContextImpl = validationContextImpl2;
                    }
                } catch (InvalidDatatypeValueException unused3) {
                    validationContextImpl = validationContextImpl2;
                    list = list3;
                }
                i15++;
                list3 = list;
                size = size;
                validationContextImpl2 = validationContextImpl;
            }
            this.fFacetsDefined = (short) (this.fFacetsDefined | 2048);
            if ((s2 & 2048) != 0) {
                this.fFixedFacet = (short) (this.fFixedFacet | 2048);
            }
        }
        if ((s & 32) == 0) {
            obj2 = "maxLength";
        } else if ((allowedFacets & 32) == 0) {
            reportError("cos-applicable-facets", new Object[]{"maxInclusive", this.fTypeName});
            obj2 = "maxLength";
        } else {
            this.maxInclusiveAnnotation = xSFacets.maxInclusiveAnnotation;
            try {
                obj2 = "maxLength";
                try {
                    this.fMaxInclusive = this.fBase.getActualValue(xSFacets.maxInclusive, validationContext, validatedInfo, true);
                    this.fFacetsDefined = (short) (this.fFacetsDefined | 32);
                    if ((s2 & 32) != 0) {
                        this.fFixedFacet = (short) (this.fFixedFacet | 32);
                    }
                } catch (InvalidDatatypeValueException e2) {
                    e = e2;
                    reportError(e.getKey(), e.getArgs());
                    reportError("FacetValueFromBase", new Object[]{this.fTypeName, xSFacets.maxInclusive, "maxInclusive", this.fBase.getName()});
                }
            } catch (InvalidDatatypeValueException e3) {
                e = e3;
                obj2 = "maxLength";
            }
            XSSimpleTypeDecl xSSimpleTypeDecl = this.fBase;
            if ((xSSimpleTypeDecl.fFacetsDefined & 32) != 0 && (xSSimpleTypeDecl.fFixedFacet & 32) != 0 && this.fDVs[this.fValidationDV].compare(this.fMaxInclusive, xSSimpleTypeDecl.fMaxInclusive) != 0) {
                reportError("FixedFacetValue", new Object[]{"maxInclusive", this.fMaxInclusive, this.fBase.fMaxInclusive, this.fTypeName});
            }
            try {
                this.fBase.validate(validationContext, validatedInfo);
            } catch (InvalidDatatypeValueException e4) {
                reportError(e4.getKey(), e4.getArgs());
                reportError("FacetValueFromBase", new Object[]{this.fTypeName, xSFacets.maxInclusive, "maxInclusive", this.fBase.getName()});
            }
        }
        if ((s & 64) != 0) {
            if ((allowedFacets & 64) == 0) {
                reportError("cos-applicable-facets", new Object[]{"maxExclusive", this.fTypeName});
            } else {
                this.maxExclusiveAnnotation = xSFacets.maxExclusiveAnnotation;
                try {
                    this.fMaxExclusive = this.fBase.getActualValue(xSFacets.maxExclusive, validationContext, validatedInfo, true);
                    this.fFacetsDefined = (short) (this.fFacetsDefined | 64);
                    if ((s2 & 64) != 0) {
                        this.fFixedFacet = (short) (this.fFixedFacet | 64);
                    }
                } catch (InvalidDatatypeValueException e5) {
                    reportError(e5.getKey(), e5.getArgs());
                    reportError("FacetValueFromBase", new Object[]{this.fTypeName, xSFacets.maxExclusive, "maxExclusive", this.fBase.getName()});
                }
                XSSimpleTypeDecl xSSimpleTypeDecl2 = this.fBase;
                if ((xSSimpleTypeDecl2.fFacetsDefined & 64) != 0) {
                    int iCompare3 = this.fDVs[this.fValidationDV].compare(this.fMaxExclusive, xSSimpleTypeDecl2.fMaxExclusive);
                    XSSimpleTypeDecl xSSimpleTypeDecl3 = this.fBase;
                    if ((xSSimpleTypeDecl3.fFixedFacet & 64) != 0 && iCompare3 != 0) {
                        reportError("FixedFacetValue", new Object[]{"maxExclusive", xSFacets.maxExclusive, xSSimpleTypeDecl3.fMaxExclusive, this.fTypeName});
                    }
                    if (iCompare3 == 0) {
                        XSSimpleTypeDecl xSSimpleTypeDecl4 = this.fBase;
                        if ((xSSimpleTypeDecl4.fFacetsDefined & 32) != 0 && this.fDVs[this.fValidationDV].compare(this.fMaxExclusive, xSSimpleTypeDecl4.fMaxInclusive) > 0) {
                            reportError("maxExclusive-valid-restriction.2", new Object[]{xSFacets.maxExclusive, this.fBase.fMaxInclusive});
                        }
                    } else {
                        try {
                            this.fBase.validate(validationContext, validatedInfo);
                        } catch (InvalidDatatypeValueException e6) {
                            reportError(e6.getKey(), e6.getArgs());
                            reportError("FacetValueFromBase", new Object[]{this.fTypeName, xSFacets.maxExclusive, "maxExclusive", this.fBase.getName()});
                        }
                    }
                } else {
                    this.fBase.validate(validationContext, validatedInfo);
                }
            }
        }
        if ((s & 128) != 0) {
            if ((allowedFacets & 128) == 0) {
                reportError("cos-applicable-facets", new Object[]{"minExclusive", this.fTypeName});
            } else {
                this.minExclusiveAnnotation = xSFacets.minExclusiveAnnotation;
                try {
                    this.fMinExclusive = this.fBase.getActualValue(xSFacets.minExclusive, validationContext, validatedInfo, true);
                    this.fFacetsDefined = (short) (this.fFacetsDefined | 128);
                    if ((s2 & 128) != 0) {
                        this.fFixedFacet = (short) (this.fFixedFacet | 128);
                    }
                } catch (InvalidDatatypeValueException e7) {
                    reportError(e7.getKey(), e7.getArgs());
                    reportError("FacetValueFromBase", new Object[]{this.fTypeName, xSFacets.minExclusive, "minExclusive", this.fBase.getName()});
                }
                XSSimpleTypeDecl xSSimpleTypeDecl5 = this.fBase;
                if ((xSSimpleTypeDecl5.fFacetsDefined & 128) != 0) {
                    int iCompare4 = this.fDVs[this.fValidationDV].compare(this.fMinExclusive, xSSimpleTypeDecl5.fMinExclusive);
                    XSSimpleTypeDecl xSSimpleTypeDecl6 = this.fBase;
                    if ((xSSimpleTypeDecl6.fFixedFacet & 128) != 0 && iCompare4 != 0) {
                        reportError("FixedFacetValue", new Object[]{"minExclusive", xSFacets.minExclusive, xSSimpleTypeDecl6.fMinExclusive, this.fTypeName});
                    }
                    if (iCompare4 == 0) {
                        XSSimpleTypeDecl xSSimpleTypeDecl7 = this.fBase;
                        if ((xSSimpleTypeDecl7.fFacetsDefined & 256) != 0 && this.fDVs[this.fValidationDV].compare(this.fMinExclusive, xSSimpleTypeDecl7.fMinInclusive) < 0) {
                            reportError("minExclusive-valid-restriction.3", new Object[]{xSFacets.minExclusive, this.fBase.fMinInclusive});
                        }
                    } else {
                        try {
                            this.fBase.validate(validationContext, validatedInfo);
                        } catch (InvalidDatatypeValueException e8) {
                            reportError(e8.getKey(), e8.getArgs());
                            reportError("FacetValueFromBase", new Object[]{this.fTypeName, xSFacets.minExclusive, "minExclusive", this.fBase.getName()});
                        }
                    }
                } else {
                    this.fBase.validate(validationContext, validatedInfo);
                }
            }
        }
        if ((s & 256) != 0) {
            if ((allowedFacets & 256) == 0) {
                reportError("cos-applicable-facets", new Object[]{"minInclusive", this.fTypeName});
            } else {
                this.minInclusiveAnnotation = xSFacets.minInclusiveAnnotation;
                try {
                    this.fMinInclusive = this.fBase.getActualValue(xSFacets.minInclusive, validationContext, validatedInfo, true);
                    this.fFacetsDefined = (short) (this.fFacetsDefined | 256);
                    if ((s2 & 256) != 0) {
                        this.fFixedFacet = (short) (this.fFixedFacet | 256);
                    }
                } catch (InvalidDatatypeValueException e9) {
                    reportError(e9.getKey(), e9.getArgs());
                    reportError("FacetValueFromBase", new Object[]{this.fTypeName, xSFacets.minInclusive, "minInclusive", this.fBase.getName()});
                }
                XSSimpleTypeDecl xSSimpleTypeDecl8 = this.fBase;
                if ((xSSimpleTypeDecl8.fFacetsDefined & 256) != 0 && (xSSimpleTypeDecl8.fFixedFacet & 256) != 0 && this.fDVs[this.fValidationDV].compare(this.fMinInclusive, xSSimpleTypeDecl8.fMinInclusive) != 0) {
                    reportError("FixedFacetValue", new Object[]{"minInclusive", xSFacets.minInclusive, this.fBase.fMinInclusive, this.fTypeName});
                }
                try {
                    this.fBase.validate(validationContext, validatedInfo);
                } catch (InvalidDatatypeValueException e10) {
                    reportError(e10.getKey(), e10.getArgs());
                    reportError("FacetValueFromBase", new Object[]{this.fTypeName, xSFacets.minInclusive, "minInclusive", this.fBase.getName()});
                }
            }
        }
        if ((s & 512) != 0) {
            if ((allowedFacets & 512) == 0) {
                reportError("cos-applicable-facets", new Object[]{"totalDigits", this.fTypeName});
            } else {
                this.totalDigitsAnnotation = xSFacets.totalDigitsAnnotation;
                this.fTotalDigits = xSFacets.totalDigits;
                this.fFacetsDefined = (short) (this.fFacetsDefined | 512);
                if ((s2 & 512) != 0) {
                    this.fFixedFacet = (short) (this.fFixedFacet | 512);
                }
            }
        }
        if ((s & 1024) != 0) {
            if ((allowedFacets & 1024) == 0) {
                reportError("cos-applicable-facets", new Object[]{"fractionDigits", this.fTypeName});
            } else {
                this.fFractionDigits = xSFacets.fractionDigits;
                this.fractionDigitsAnnotation = xSFacets.fractionDigitsAnnotation;
                this.fFacetsDefined = (short) (this.fFacetsDefined | 1024);
                if ((s2 & 1024) != 0) {
                    this.fFixedFacet = (short) (this.fFixedFacet | 1024);
                }
            }
        }
        if (s3 != 0) {
            this.fPatternType = s3;
        }
        short s7 = this.fFacetsDefined;
        if (s7 != 0) {
            if ((s7 & 2) != 0 && (s7 & 4) != 0 && (i14 = this.fMinLength) > this.fMaxLength) {
                reportError("minLength-less-than-equal-to-maxLength", new Object[]{Integer.toString(i14), Integer.toString(this.fMaxLength), this.fTypeName});
            }
            short s8 = this.fFacetsDefined;
            if ((s8 & 64) != 0 && (s8 & 32) != 0) {
                reportError("maxInclusive-maxExclusive", new Object[]{this.fMaxInclusive, this.fMaxExclusive, this.fTypeName});
            }
            short s9 = this.fFacetsDefined;
            if ((s9 & 128) != 0 && (s9 & 256) != 0) {
                reportError("minInclusive-minExclusive", new Object[]{this.fMinInclusive, this.fMinExclusive, this.fTypeName});
            }
            short s10 = this.fFacetsDefined;
            if ((s10 & 32) != 0 && (s10 & 256) != 0 && (iCompare2 = this.fDVs[this.fValidationDV].compare(this.fMinInclusive, this.fMaxInclusive)) != -1 && iCompare2 != 0) {
                reportError("minInclusive-less-than-equal-to-maxInclusive", new Object[]{this.fMinInclusive, this.fMaxInclusive, this.fTypeName});
            }
            short s11 = this.fFacetsDefined;
            if ((s11 & 64) != 0 && (s11 & 128) != 0 && (iCompare = this.fDVs[this.fValidationDV].compare(this.fMinExclusive, this.fMaxExclusive)) != -1 && iCompare != 0) {
                reportError("minExclusive-less-than-equal-to-maxExclusive", new Object[]{this.fMinExclusive, this.fMaxExclusive, this.fTypeName});
            }
            short s12 = this.fFacetsDefined;
            if ((s12 & 32) != 0 && (s12 & 128) != 0 && this.fDVs[this.fValidationDV].compare(this.fMinExclusive, this.fMaxInclusive) != -1) {
                reportError("minExclusive-less-than-maxInclusive", new Object[]{this.fMinExclusive, this.fMaxInclusive, this.fTypeName});
            }
            short s13 = this.fFacetsDefined;
            if ((s13 & 64) != 0 && (s13 & 256) != 0 && this.fDVs[this.fValidationDV].compare(this.fMinInclusive, this.fMaxExclusive) != -1) {
                reportError("minInclusive-less-than-maxExclusive", new Object[]{this.fMinInclusive, this.fMaxExclusive, this.fTypeName});
            }
            short s14 = this.fFacetsDefined;
            if ((s14 & 1024) != 0 && (s14 & 512) != 0 && (i13 = this.fFractionDigits) > this.fTotalDigits) {
                reportError("fractionDigits-totalDigits", new Object[]{Integer.toString(i13), Integer.toString(this.fTotalDigits), this.fTypeName});
            }
            if ((this.fFacetsDefined & 1) != 0) {
                XSSimpleTypeDecl xSSimpleTypeDecl9 = this.fBase;
                if ((xSSimpleTypeDecl9.fFacetsDefined & 2) != 0 && (i12 = this.fLength) < xSSimpleTypeDecl9.fMinLength) {
                    reportError("length-minLength-maxLength.1.1", new Object[]{this.fTypeName, Integer.toString(i12), Integer.toString(this.fBase.fMinLength)});
                }
                XSSimpleTypeDecl xSSimpleTypeDecl10 = this.fBase;
                if ((xSSimpleTypeDecl10.fFacetsDefined & 4) != 0 && (i11 = this.fLength) > xSSimpleTypeDecl10.fMaxLength) {
                    reportError("length-minLength-maxLength.2.1", new Object[]{this.fTypeName, Integer.toString(i11), Integer.toString(this.fBase.fMaxLength)});
                }
                XSSimpleTypeDecl xSSimpleTypeDecl11 = this.fBase;
                if ((xSSimpleTypeDecl11.fFacetsDefined & 1) != 0 && (i10 = this.fLength) != xSSimpleTypeDecl11.fLength) {
                    reportError("length-valid-restriction", new Object[]{Integer.toString(i10), Integer.toString(this.fBase.fLength), this.fTypeName});
                }
            }
            XSSimpleTypeDecl xSSimpleTypeDecl12 = this.fBase;
            short s15 = xSSimpleTypeDecl12.fFacetsDefined;
            if ((s15 & 1) != 0 || (this.fFacetsDefined & 1) != 0) {
                if ((this.fFacetsDefined & 2) != 0) {
                    if ((s15 & 1) != 0 && (i4 = xSSimpleTypeDecl12.fLength) < this.fMinLength) {
                        reportError("length-minLength-maxLength.1.1", new Object[]{this.fTypeName, Integer.toString(i4), Integer.toString(this.fMinLength)});
                    }
                    if ((this.fFacetsDefined & 1) != 0 && (i3 = this.fLength) < this.fMinLength) {
                        reportError("length-minLength-maxLength.1.1", new Object[]{this.fTypeName, Integer.toString(i3), Integer.toString(this.fMinLength)});
                    }
                    if ((this.fBase.fFacetsDefined & 2) == 0) {
                        reportError("length-minLength-maxLength.1.2.a", new Object[]{this.fTypeName});
                    }
                    int i17 = this.fMinLength;
                    if (i17 != this.fBase.fMinLength) {
                        reportError("length-minLength-maxLength.1.2.b", new Object[]{this.fTypeName, Integer.toString(i17), Integer.toString(this.fBase.fMinLength)});
                    }
                }
                if ((this.fFacetsDefined & 4) != 0) {
                    XSSimpleTypeDecl xSSimpleTypeDecl13 = this.fBase;
                    if ((xSSimpleTypeDecl13.fFacetsDefined & 1) != 0 && (i2 = xSSimpleTypeDecl13.fLength) > this.fMaxLength) {
                        reportError("length-minLength-maxLength.2.1", new Object[]{this.fTypeName, Integer.toString(i2), Integer.toString(this.fMaxLength)});
                    }
                    if ((this.fFacetsDefined & 1) != 0 && (i = this.fLength) > this.fMaxLength) {
                        reportError("length-minLength-maxLength.2.1", new Object[]{this.fTypeName, Integer.toString(i), Integer.toString(this.fMaxLength)});
                    }
                    if ((this.fBase.fFacetsDefined & 4) == 0) {
                        reportError("length-minLength-maxLength.2.2.a", new Object[]{this.fTypeName});
                    }
                    int i18 = this.fMaxLength;
                    if (i18 != this.fBase.fMaxLength) {
                        reportError("length-minLength-maxLength.2.2.b", new Object[]{this.fTypeName, Integer.toString(i18), Integer.toString(this.fBase.fBase.fMaxLength)});
                    }
                }
            }
            if ((this.fFacetsDefined & 2) != 0) {
                XSSimpleTypeDecl xSSimpleTypeDecl14 = this.fBase;
                short s16 = xSSimpleTypeDecl14.fFacetsDefined;
                if ((s16 & 4) != 0) {
                    int i19 = this.fMinLength;
                    if (i19 > xSSimpleTypeDecl14.fMaxLength) {
                        reportError("minLength-less-than-equal-to-maxLength", new Object[]{Integer.toString(i19), Integer.toString(this.fBase.fMaxLength), this.fTypeName});
                    }
                } else if ((s16 & 2) != 0) {
                    if ((xSSimpleTypeDecl14.fFixedFacet & 2) != 0 && (i9 = this.fMinLength) != xSSimpleTypeDecl14.fMinLength) {
                        reportError("FixedFacetValue", new Object[]{"minLength", Integer.toString(i9), Integer.toString(this.fBase.fMinLength), this.fTypeName});
                    }
                    int i20 = this.fMinLength;
                    if (i20 < this.fBase.fMinLength) {
                        reportError("minLength-valid-restriction", new Object[]{Integer.toString(i20), Integer.toString(this.fBase.fMinLength), this.fTypeName});
                    }
                }
            }
            if ((this.fFacetsDefined & 4) != 0) {
                XSSimpleTypeDecl xSSimpleTypeDecl15 = this.fBase;
                if ((xSSimpleTypeDecl15.fFacetsDefined & 2) != 0) {
                    int i21 = this.fMaxLength;
                    int i22 = xSSimpleTypeDecl15.fMinLength;
                    if (i21 < i22) {
                        reportError("minLength-less-than-equal-to-maxLength", new Object[]{Integer.toString(i22), Integer.toString(this.fMaxLength)});
                    }
                }
            }
            if ((this.fFacetsDefined & 4) != 0) {
                XSSimpleTypeDecl xSSimpleTypeDecl16 = this.fBase;
                if ((xSSimpleTypeDecl16.fFacetsDefined & 4) != 0) {
                    if ((xSSimpleTypeDecl16.fFixedFacet & 4) != 0 && (i8 = this.fMaxLength) != xSSimpleTypeDecl16.fMaxLength) {
                        reportError("FixedFacetValue", new Object[]{obj2, Integer.toString(i8), Integer.toString(this.fBase.fMaxLength), this.fTypeName});
                    }
                    int i23 = this.fMaxLength;
                    if (i23 > this.fBase.fMaxLength) {
                        reportError("maxLength-valid-restriction", new Object[]{Integer.toString(i23), Integer.toString(this.fBase.fMaxLength), this.fTypeName});
                    }
                }
            }
            if ((this.fFacetsDefined & 512) != 0) {
                XSSimpleTypeDecl xSSimpleTypeDecl17 = this.fBase;
                if ((xSSimpleTypeDecl17.fFacetsDefined & 512) != 0) {
                    if ((xSSimpleTypeDecl17.fFixedFacet & 512) != 0 && (i7 = this.fTotalDigits) != xSSimpleTypeDecl17.fTotalDigits) {
                        reportError("FixedFacetValue", new Object[]{"totalDigits", Integer.toString(i7), Integer.toString(this.fBase.fTotalDigits), this.fTypeName});
                    }
                    int i24 = this.fTotalDigits;
                    if (i24 > this.fBase.fTotalDigits) {
                        reportError("totalDigits-valid-restriction", new Object[]{Integer.toString(i24), Integer.toString(this.fBase.fTotalDigits), this.fTypeName});
                    }
                }
            }
            if ((this.fFacetsDefined & 1024) != 0) {
                XSSimpleTypeDecl xSSimpleTypeDecl18 = this.fBase;
                if ((xSSimpleTypeDecl18.fFacetsDefined & 512) != 0 && (i6 = this.fFractionDigits) > xSSimpleTypeDecl18.fTotalDigits) {
                    reportError("fractionDigits-totalDigits", new Object[]{Integer.toString(i6), Integer.toString(this.fTotalDigits), this.fTypeName});
                }
            }
            if ((this.fFacetsDefined & 1024) != 0) {
                XSSimpleTypeDecl xSSimpleTypeDecl19 = this.fBase;
                if ((xSSimpleTypeDecl19.fFacetsDefined & 1024) != 0) {
                    if (((xSSimpleTypeDecl19.fFixedFacet & 1024) != 0 && this.fFractionDigits != xSSimpleTypeDecl19.fFractionDigits) || (this.fValidationDV == 24 && this.fFractionDigits != 0)) {
                        reportError("FixedFacetValue", new Object[]{"fractionDigits", Integer.toString(this.fFractionDigits), Integer.toString(this.fBase.fFractionDigits), this.fTypeName});
                    }
                    int i25 = this.fFractionDigits;
                    if (i25 > this.fBase.fFractionDigits) {
                        reportError("fractionDigits-valid-restriction", new Object[]{Integer.toString(i25), Integer.toString(this.fBase.fFractionDigits), this.fTypeName});
                    }
                } else if (this.fValidationDV == 24 && (i5 = this.fFractionDigits) != 0) {
                    reportError("FixedFacetValue", new Object[]{"fractionDigits", Integer.toString(i5), "0", this.fTypeName});
                }
            }
            if ((this.fFacetsDefined & 16) != 0) {
                XSSimpleTypeDecl xSSimpleTypeDecl20 = this.fBase;
                if ((xSSimpleTypeDecl20.fFacetsDefined & 16) != 0) {
                    if ((xSSimpleTypeDecl20.fFixedFacet & 16) != 0 && (s6 = this.fWhiteSpace) != xSSimpleTypeDecl20.fWhiteSpace) {
                        reportError("FixedFacetValue", new Object[]{obj, whiteSpaceValue(s6), whiteSpaceValue(this.fBase.fWhiteSpace), this.fTypeName});
                    }
                    if (this.fWhiteSpace == 0 && this.fBase.fWhiteSpace == s4) {
                        reportError("whiteSpace-valid-restriction.1", new Object[]{this.fTypeName, SchemaSymbols.ATTVAL_PRESERVE});
                    }
                    if (this.fWhiteSpace == 1 && this.fBase.fWhiteSpace == 2) {
                        reportError("whiteSpace-valid-restriction.1", new Object[]{this.fTypeName, SchemaSymbols.ATTVAL_REPLACE});
                    }
                    if (this.fWhiteSpace == 0 && this.fBase.fWhiteSpace == 1) {
                        reportError("whiteSpace-valid-restriction.2", new Object[]{this.fTypeName});
                    }
                }
            }
        }
        short s17 = this.fFacetsDefined;
        if ((s17 & 1) == 0) {
            XSSimpleTypeDecl xSSimpleTypeDecl21 = this.fBase;
            if ((xSSimpleTypeDecl21.fFacetsDefined & 1) != 0) {
                this.fFacetsDefined = (short) (s17 | 1);
                this.fLength = xSSimpleTypeDecl21.fLength;
                this.lengthAnnotation = xSSimpleTypeDecl21.lengthAnnotation;
            }
        }
        short s18 = this.fFacetsDefined;
        if ((s18 & 2) == 0) {
            XSSimpleTypeDecl xSSimpleTypeDecl22 = this.fBase;
            if ((xSSimpleTypeDecl22.fFacetsDefined & 2) != 0) {
                this.fFacetsDefined = (short) (s18 | 2);
                this.fMinLength = xSSimpleTypeDecl22.fMinLength;
                this.minLengthAnnotation = xSSimpleTypeDecl22.minLengthAnnotation;
            }
        }
        short s19 = this.fFacetsDefined;
        if ((s19 & 4) == 0) {
            XSSimpleTypeDecl xSSimpleTypeDecl23 = this.fBase;
            if ((xSSimpleTypeDecl23.fFacetsDefined & 4) != 0) {
                this.fFacetsDefined = (short) (s19 | 4);
                this.fMaxLength = xSSimpleTypeDecl23.fMaxLength;
                this.maxLengthAnnotation = xSSimpleTypeDecl23.maxLengthAnnotation;
            }
        }
        XSSimpleTypeDecl xSSimpleTypeDecl24 = this.fBase;
        if ((xSSimpleTypeDecl24.fFacetsDefined & 8) != 0) {
            short s20 = this.fFacetsDefined;
            if ((s20 & 8) == 0) {
                this.fFacetsDefined = (short) (s20 | 8);
                this.fPattern = xSSimpleTypeDecl24.fPattern;
                this.fPatternStr = xSSimpleTypeDecl24.fPatternStr;
                this.patternAnnotations = xSSimpleTypeDecl24.patternAnnotations;
            } else {
                for (int size2 = xSSimpleTypeDecl24.fPattern.size() - 1; size2 >= 0; size2--) {
                    this.fPattern.add(this.fBase.fPattern.get(size2));
                    this.fPatternStr.add(this.fBase.fPatternStr.get(size2));
                }
                XSObjectListImpl xSObjectListImpl = this.fBase.patternAnnotations;
                if (xSObjectListImpl != null) {
                    if (this.patternAnnotations != null) {
                        for (int length = xSObjectListImpl.getLength() - 1; length >= 0; length--) {
                            this.patternAnnotations.addXSObject(this.fBase.patternAnnotations.item(length));
                        }
                    } else {
                        this.patternAnnotations = xSObjectListImpl;
                    }
                }
            }
        }
        short s21 = this.fFacetsDefined;
        if ((s21 & 16) == 0) {
            XSSimpleTypeDecl xSSimpleTypeDecl25 = this.fBase;
            if ((xSSimpleTypeDecl25.fFacetsDefined & 16) != 0) {
                this.fFacetsDefined = (short) (s21 | 16);
                this.fWhiteSpace = xSSimpleTypeDecl25.fWhiteSpace;
                this.whiteSpaceAnnotation = xSSimpleTypeDecl25.whiteSpaceAnnotation;
            }
        }
        short s22 = this.fFacetsDefined;
        if ((s22 & 2048) == 0) {
            XSSimpleTypeDecl xSSimpleTypeDecl26 = this.fBase;
            if ((xSSimpleTypeDecl26.fFacetsDefined & 2048) != 0) {
                this.fFacetsDefined = (short) (s22 | 2048);
                this.fEnumeration = xSSimpleTypeDecl26.fEnumeration;
                this.fEnumerationSize = xSSimpleTypeDecl26.fEnumerationSize;
                this.enumerationAnnotations = xSSimpleTypeDecl26.enumerationAnnotations;
            }
        }
        XSSimpleTypeDecl xSSimpleTypeDecl27 = this.fBase;
        if ((xSSimpleTypeDecl27.fFacetsDefined & 64) != 0) {
            short s23 = this.fFacetsDefined;
            if ((s23 & 64) == 0 && (s23 & 32) == 0) {
                this.fFacetsDefined = (short) (s23 | 64);
                this.fMaxExclusive = xSSimpleTypeDecl27.fMaxExclusive;
                this.maxExclusiveAnnotation = xSSimpleTypeDecl27.maxExclusiveAnnotation;
            }
        }
        if ((xSSimpleTypeDecl27.fFacetsDefined & 32) != 0) {
            short s24 = this.fFacetsDefined;
            if ((s24 & 64) == 0 && (s24 & 32) == 0) {
                this.fFacetsDefined = (short) (s24 | 32);
                this.fMaxInclusive = xSSimpleTypeDecl27.fMaxInclusive;
                this.maxInclusiveAnnotation = xSSimpleTypeDecl27.maxInclusiveAnnotation;
            }
        }
        if ((xSSimpleTypeDecl27.fFacetsDefined & 128) != 0) {
            short s25 = this.fFacetsDefined;
            if ((s25 & 128) == 0 && (s25 & 256) == 0) {
                this.fFacetsDefined = (short) (s25 | 128);
                this.fMinExclusive = xSSimpleTypeDecl27.fMinExclusive;
                this.minExclusiveAnnotation = xSSimpleTypeDecl27.minExclusiveAnnotation;
            }
        }
        if ((xSSimpleTypeDecl27.fFacetsDefined & 256) != 0) {
            short s26 = this.fFacetsDefined;
            if ((s26 & 128) == 0 && (s26 & 256) == 0) {
                this.fFacetsDefined = (short) (s26 | 256);
                this.fMinInclusive = xSSimpleTypeDecl27.fMinInclusive;
                this.minInclusiveAnnotation = xSSimpleTypeDecl27.minInclusiveAnnotation;
            }
        }
        if ((xSSimpleTypeDecl27.fFacetsDefined & 512) != 0) {
            short s27 = this.fFacetsDefined;
            if ((s27 & 512) == 0) {
                this.fFacetsDefined = (short) (s27 | 512);
                this.fTotalDigits = xSSimpleTypeDecl27.fTotalDigits;
                this.totalDigitsAnnotation = xSSimpleTypeDecl27.totalDigitsAnnotation;
            }
        }
        if ((xSSimpleTypeDecl27.fFacetsDefined & 1024) != 0) {
            short s28 = this.fFacetsDefined;
            if ((s28 & 1024) == 0) {
                this.fFacetsDefined = (short) (s28 | 1024);
                this.fFractionDigits = xSSimpleTypeDecl27.fFractionDigits;
                this.fractionDigitsAnnotation = xSSimpleTypeDecl27.fractionDigitsAnnotation;
            }
        }
        if (this.fPatternType == 0 && (s5 = xSSimpleTypeDecl27.fPatternType) != 0) {
            this.fPatternType = s5;
        }
        this.fFixedFacet = (short) (xSSimpleTypeDecl27.fFixedFacet | this.fFixedFacet);
        calcFundamentalFacets();
    }

    public void applyFacets1(XSFacets xSFacets, short s, short s2) {
        try {
            applyFacets(xSFacets, s, s2, (short) 0, fDummyContext);
            this.fIsImmutable = true;
        } catch (InvalidDatatypeFacetException unused) {
            f63.a("internal error");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl] */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.sun.org.apache.xerces.internal.xs.XSObject, com.sun.org.apache.xerces.internal.xs.XSTypeDefinition] */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.sun.org.apache.xerces.internal.xs.XSTypeDefinition] */
    @Override // com.sun.org.apache.xerces.internal.xs.XSTypeDefinition
    public boolean derivedFrom(String str, String str2, short s) {
        if (str2 == null) {
            return false;
        }
        if ("http://www.w3.org/2001/XMLSchema".equals(str) && "anyType".equals(str2)) {
            return true;
        }
        while (true) {
            if ((str2.equals(this.getName()) && ((str == null && this.getNamespace() == null) || (str != null && str.equals(this.getNamespace())))) || this == fAnySimpleType) {
                break;
            }
            this = this.getBaseType();
        }
        return this != fAnySimpleType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.sun.org.apache.xerces.internal.xs.XSTypeDefinition] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    @Override // com.sun.org.apache.xerces.internal.xs.XSTypeDefinition
    public boolean derivedFromType(XSTypeDefinition xSTypeDefinition, short s) {
        if (xSTypeDefinition == null) {
            return false;
        }
        while (xSTypeDefinition instanceof XSSimpleTypeDelegate) {
            xSTypeDefinition = ((XSSimpleTypeDelegate) xSTypeDefinition).type;
        }
        ?? baseType = this;
        if (xSTypeDefinition.getBaseType() == xSTypeDefinition) {
            return true;
        }
        while (baseType != xSTypeDefinition && baseType != fAnySimpleType) {
            baseType = baseType.getBaseType();
        }
        return baseType == xSTypeDefinition;
    }

    public ValidatedInfo getActualEnumValue(String str, ValidationContext validationContext, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        return this.fBase.validateWithInfo(str, validationContext, validatedInfo);
    }

    public ObjectList getActualEnumeration() {
        if (this.fActualEnumeration == null) {
            this.fActualEnumeration = new AbstractObjectList() { // from class: com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl.2
                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List, com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList
                public boolean contains(Object obj) {
                    if (XSSimpleTypeDecl.this.fEnumeration == null) {
                        return false;
                    }
                    for (int i = 0; i < XSSimpleTypeDecl.this.fEnumerationSize; i++) {
                        if (XSSimpleTypeDecl.this.fEnumeration[i].getActualValue().equals(obj)) {
                            return true;
                        }
                    }
                    return false;
                }

                @Override // com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList
                public int getLength() {
                    if (XSSimpleTypeDecl.this.fEnumeration != null) {
                        return XSSimpleTypeDecl.this.fEnumerationSize;
                    }
                    return 0;
                }

                @Override // com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList
                public Object item(int i) {
                    if (i < 0 || i >= getLength()) {
                        return null;
                    }
                    return XSSimpleTypeDecl.this.fEnumeration[i].getActualValue();
                }
            };
        }
        return this.fActualEnumeration;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public XSObjectList getAnnotations() {
        XSObjectList xSObjectList = this.fAnnotations;
        return xSObjectList != null ? xSObjectList : XSObjectListImpl.EMPTY_LIST;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSTypeDefinition
    public boolean getAnonymous() {
        return this.fAnonymous || this.fTypeName == null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSTypeDefinition
    public XSTypeDefinition getBaseType() {
        return this.fBase;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public boolean getBounded() {
        return this.fBounded;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public short getBuiltInKind() {
        return this.fBuiltInKind;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public short getDefinedFacets() {
        int i;
        short s = this.fValidationDV;
        if (s == 0 || s == 29) {
            return (short) 0;
        }
        if (this.fPatternType != 0) {
            i = this.fFacetsDefined | 8;
        } else {
            short s2 = this.fFacetsDefined;
            if (s != 24) {
                return s2;
            }
            i = s2 | 1032;
        }
        return (short) i;
    }

    public ObjectList getEnumerationItemTypeList() {
        if (this.fEnumerationItemTypeList == null) {
            if (this.fEnumeration == null) {
                return null;
            }
            this.fEnumerationItemTypeList = new AbstractObjectList() { // from class: com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl.3
                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List, com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList
                public boolean contains(Object obj) {
                    if (XSSimpleTypeDecl.this.fEnumeration != null && (obj instanceof ShortList)) {
                        for (int i = 0; i < XSSimpleTypeDecl.this.fEnumerationSize; i++) {
                            if (XSSimpleTypeDecl.this.fEnumeration[i].itemValueTypes == obj) {
                                return true;
                            }
                        }
                    }
                    return false;
                }

                @Override // com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList
                public int getLength() {
                    if (XSSimpleTypeDecl.this.fEnumeration != null) {
                        return XSSimpleTypeDecl.this.fEnumerationSize;
                    }
                    return 0;
                }

                @Override // com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList
                public Object item(int i) {
                    if (i < 0 || i >= getLength()) {
                        return null;
                    }
                    return XSSimpleTypeDecl.this.fEnumeration[i].itemValueTypes;
                }
            };
        }
        return this.fEnumerationItemTypeList;
    }

    public ShortList getEnumerationTypeList() {
        if (this.fEnumerationTypeList == null) {
            if (this.fEnumeration == null) {
                return ShortListImpl.EMPTY_LIST;
            }
            short[] sArr = new short[this.fEnumerationSize];
            for (int i = 0; i < this.fEnumerationSize; i++) {
                sArr[i] = this.fEnumeration[i].actualValueType;
            }
            this.fEnumerationTypeList = new ShortListImpl(sArr, this.fEnumerationSize);
        }
        return this.fEnumerationTypeList;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public XSObject getFacet(int i) {
        int i2 = 0;
        if (i == 2048 || i == 8) {
            XSObjectList multiValueFacets = getMultiValueFacets();
            while (i2 < multiValueFacets.getLength()) {
                XSMultiValueFacet xSMultiValueFacet = (XSMultiValueFacet) multiValueFacets.item(i2);
                if (xSMultiValueFacet.getFacetKind() == i) {
                    return xSMultiValueFacet;
                }
                i2++;
            }
            return null;
        }
        XSObjectList facets = getFacets();
        while (i2 < facets.getLength()) {
            XSFacet xSFacet = (XSFacet) facets.item(i2);
            if (xSFacet.getFacetKind() == i) {
                return xSFacet;
            }
            i2++;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:61:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:63:0x0109  */
    /* JADX WARN: Code duplicated, block: B:64:0x010b  */
    /* JADX WARN: Code duplicated, block: B:68:0x011c  */
    /* JADX WARN: Code duplicated, block: B:70:0x012a  */
    /* JADX WARN: Code duplicated, block: B:71:0x012c  */
    /* JADX WARN: Code duplicated, block: B:75:0x013d  */
    /* JADX WARN: Code duplicated, block: B:77:0x014b  */
    /* JADX WARN: Code duplicated, block: B:78:0x014d  */
    /* JADX WARN: Code duplicated, block: B:82:0x015e  */
    /* JADX WARN: Code duplicated, block: B:84:0x016c  */
    /* JADX WARN: Code duplicated, block: B:85:0x016e  */
    /* JADX WARN: Code duplicated, block: B:88:0x017d  */
    /* JADX WARN: Code duplicated, block: B:89:0x0183  */
    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public XSObjectList getFacets() {
        short s;
        int i;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        XSObjectListImpl xSObjectListImpl;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        short s2;
        if (this.fFacets == null && ((s = this.fFacetsDefined) != 0 || this.fValidationDV == 24)) {
            XSFacetImpl[] xSFacetImplArr = new XSFacetImpl[10];
            if ((s & 16) == 0 || (s2 = this.fValidationDV) == 0 || s2 == 29) {
                i = 0;
            } else {
                xSFacetImplArr[0] = new XSFacetImpl((short) 16, WS_FACET_STRING[this.fWhiteSpace], 0, null, (this.fFixedFacet & 16) != 0, this.whiteSpaceAnnotation);
                i = 1;
            }
            int i2 = this.fLength;
            if (i2 != -1) {
                xSFacetImplArr[i] = new XSFacetImpl((short) 1, Integer.toString(i2), this.fLength, null, (this.fFixedFacet & 1) != 0, this.lengthAnnotation);
                i++;
            }
            int i3 = this.fMinLength;
            if (i3 != -1) {
                xSFacetImplArr[i] = new XSFacetImpl((short) 2, Integer.toString(i3), this.fMinLength, null, (this.fFixedFacet & 2) != 0, this.minLengthAnnotation);
                i++;
            }
            int i4 = this.fMaxLength;
            if (i4 != -1) {
                xSFacetImplArr[i] = new XSFacetImpl((short) 4, Integer.toString(i4), this.fMaxLength, null, (this.fFixedFacet & 4) != 0, this.maxLengthAnnotation);
                i++;
            }
            int i5 = this.fTotalDigits;
            if (i5 != -1) {
                xSFacetImplArr[i] = new XSFacetImpl((short) 512, Integer.toString(i5), this.fTotalDigits, null, (this.fFixedFacet & 512) != 0, this.totalDigitsAnnotation);
                i++;
            }
            if (this.fValidationDV == 24) {
                xSFacetImplArr[i] = new XSFacetImpl((short) 1024, "0", 0, null, true, this.fractionDigitsAnnotation);
            } else {
                int i6 = this.fFractionDigits;
                if (i6 != -1) {
                    xSFacetImplArr[i] = new XSFacetImpl((short) 1024, Integer.toString(i6), this.fFractionDigits, null, (this.fFixedFacet & 1024) != 0, this.fractionDigitsAnnotation);
                }
                obj = this.fMaxInclusive;
                if (obj != null) {
                    String string = obj.toString();
                    Object obj5 = this.fMaxInclusive;
                    if ((this.fFixedFacet & 32) != 0) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    xSFacetImplArr[i] = new XSFacetImpl((short) 32, string, 0, obj5, z4, this.maxInclusiveAnnotation);
                    i++;
                }
                obj2 = this.fMaxExclusive;
                if (obj2 != null) {
                    String string2 = obj2.toString();
                    Object obj6 = this.fMaxExclusive;
                    if ((this.fFixedFacet & 64) != 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    xSFacetImplArr[i] = new XSFacetImpl((short) 64, string2, 0, obj6, z3, this.maxExclusiveAnnotation);
                    i++;
                }
                obj3 = this.fMinExclusive;
                if (obj3 != null) {
                    String string3 = obj3.toString();
                    Object obj7 = this.fMinExclusive;
                    if ((this.fFixedFacet & 128) != 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    xSFacetImplArr[i] = new XSFacetImpl((short) 128, string3, 0, obj7, z2, this.minExclusiveAnnotation);
                    i++;
                }
                obj4 = this.fMinInclusive;
                if (obj4 != null) {
                    String string4 = obj4.toString();
                    Object obj8 = this.fMinInclusive;
                    if ((this.fFixedFacet & 256) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    xSFacetImplArr[i] = new XSFacetImpl((short) 256, string4, 0, obj8, z, this.minInclusiveAnnotation);
                    i++;
                }
                if (i > 0) {
                    xSObjectListImpl = new XSObjectListImpl(xSFacetImplArr, i);
                } else {
                    xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
                }
                this.fFacets = xSObjectListImpl;
            }
            i++;
            obj = this.fMaxInclusive;
            if (obj != null) {
                String string5 = obj.toString();
                Object obj9 = this.fMaxInclusive;
                if ((this.fFixedFacet & 32) != 0) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                xSFacetImplArr[i] = new XSFacetImpl((short) 32, string5, 0, obj9, z4, this.maxInclusiveAnnotation);
                i++;
            }
            obj2 = this.fMaxExclusive;
            if (obj2 != null) {
                String string6 = obj2.toString();
                Object obj10 = this.fMaxExclusive;
                if ((this.fFixedFacet & 64) != 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                xSFacetImplArr[i] = new XSFacetImpl((short) 64, string6, 0, obj10, z3, this.maxExclusiveAnnotation);
                i++;
            }
            obj3 = this.fMinExclusive;
            if (obj3 != null) {
                String string7 = obj3.toString();
                Object obj11 = this.fMinExclusive;
                if ((this.fFixedFacet & 128) != 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                xSFacetImplArr[i] = new XSFacetImpl((short) 128, string7, 0, obj11, z2, this.minExclusiveAnnotation);
                i++;
            }
            obj4 = this.fMinInclusive;
            if (obj4 != null) {
                String string8 = obj4.toString();
                Object obj12 = this.fMinInclusive;
                if ((this.fFixedFacet & 256) != 0) {
                    z = true;
                } else {
                    z = false;
                }
                xSFacetImplArr[i] = new XSFacetImpl((short) 256, string8, 0, obj12, z, this.minInclusiveAnnotation);
                i++;
            }
            if (i > 0) {
                xSObjectListImpl = new XSObjectListImpl(xSFacetImplArr, i);
            } else {
                xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
            }
            this.fFacets = xSObjectListImpl;
        }
        XSObjectListImpl xSObjectListImpl2 = this.fFacets;
        return xSObjectListImpl2 != null ? xSObjectListImpl2 : XSObjectListImpl.EMPTY_LIST;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSTypeDefinition
    public short getFinal() {
        return this.fFinalSet;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public boolean getFinite() {
        return this.fFinite;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public short getFixedFacets() {
        short s = this.fValidationDV;
        short s2 = this.fFixedFacet;
        return s == 24 ? (short) (s2 | 1024) : s2;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public XSSimpleTypeDefinition getItemType() {
        if (this.fVariety == 2) {
            return this.fItemType;
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public StringList getLexicalEnumeration() {
        if (this.fLexicalEnumeration == null) {
            if (this.fEnumeration == null) {
                return StringListImpl.EMPTY_LIST;
            }
            int i = this.fEnumerationSize;
            String[] strArr = new String[i];
            for (int i2 = 0; i2 < i; i2++) {
                strArr[i2] = this.fEnumeration[i2].normalizedValue;
            }
            this.fLexicalEnumeration = new StringListImpl(strArr, i);
        }
        return this.fLexicalEnumeration;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public String getLexicalFacetValue(short s) {
        if (s == 1) {
            int i = this.fLength;
            if (i == -1) {
                return null;
            }
            return Integer.toString(i);
        }
        if (s == 2) {
            int i2 = this.fMinLength;
            if (i2 == -1) {
                return null;
            }
            return Integer.toString(i2);
        }
        if (s == 4) {
            int i3 = this.fMaxLength;
            if (i3 == -1) {
                return null;
            }
            return Integer.toString(i3);
        }
        if (s == 16) {
            short s2 = this.fValidationDV;
            if (s2 == 0 || s2 == 29) {
                return null;
            }
            return WS_FACET_STRING[this.fWhiteSpace];
        }
        if (s == 32) {
            Object obj = this.fMaxInclusive;
            if (obj == null) {
                return null;
            }
            return obj.toString();
        }
        if (s == 64) {
            Object obj2 = this.fMaxExclusive;
            if (obj2 == null) {
                return null;
            }
            return obj2.toString();
        }
        if (s == 128) {
            Object obj3 = this.fMinExclusive;
            if (obj3 == null) {
                return null;
            }
            return obj3.toString();
        }
        if (s == 256) {
            Object obj4 = this.fMinInclusive;
            if (obj4 == null) {
                return null;
            }
            return obj4.toString();
        }
        if (s == 512) {
            int i4 = this.fTotalDigits;
            if (i4 == -1) {
                return null;
            }
            return Integer.toString(i4);
        }
        if (s != 1024) {
            return null;
        }
        if (this.fValidationDV == 24) {
            return "0";
        }
        int i5 = this.fFractionDigits;
        if (i5 == -1) {
            return null;
        }
        return Integer.toString(i5);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public StringList getLexicalPattern() {
        String[] strArr;
        if (this.fPatternType == 0 && this.fValidationDV != 24 && this.fPatternStr == null) {
            return StringListImpl.EMPTY_LIST;
        }
        if (this.fLexicalPattern == null) {
            List<String> list = this.fPatternStr;
            int size = list == null ? 0 : list.size();
            short s = this.fPatternType;
            if (s == 1) {
                strArr = new String[size + 1];
                strArr[size] = "\\c+";
            } else if (s == 2) {
                strArr = new String[size + 1];
                strArr[size] = "\\i\\c*";
            } else if (s == 3) {
                strArr = new String[size + 2];
                strArr[size] = "\\i\\c*";
                strArr[size + 1] = "[\\i-[:]][\\c-[:]]*";
            } else if (this.fValidationDV == 24) {
                strArr = new String[size + 1];
                strArr[size] = "[\\-+]?[0-9]+";
            } else {
                strArr = new String[size];
            }
            for (int i = 0; i < size; i++) {
                strArr[i] = this.fPatternStr.get(i);
            }
            this.fLexicalPattern = new StringListImpl(strArr, strArr.length);
        }
        return this.fLexicalPattern;
    }

    public Object getMaxExclusiveValue() {
        return this.fMaxExclusive;
    }

    public Object getMaxInclusiveValue() {
        return this.fMaxInclusive;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public XSObjectList getMemberTypes() {
        if (this.fVariety != 3) {
            return XSObjectListImpl.EMPTY_LIST;
        }
        XSSimpleTypeDecl[] xSSimpleTypeDeclArr = this.fMemberTypes;
        return new XSObjectListImpl(xSSimpleTypeDeclArr, xSSimpleTypeDeclArr.length);
    }

    public Object getMinExclusiveValue() {
        return this.fMinExclusive;
    }

    public Object getMinInclusiveValue() {
        return this.fMinInclusive;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public XSObjectList getMultiValueFacets() {
        if (this.fMultiValueFacets == null) {
            short s = this.fFacetsDefined;
            if ((s & 2048) != 0 || (s & 8) != 0 || this.fPatternType != 0 || this.fValidationDV == 24) {
                XSMVFacetImpl[] xSMVFacetImplArr = new XSMVFacetImpl[2];
                int i = 0;
                if ((s & 8) != 0 || this.fPatternType != 0 || this.fValidationDV == 24) {
                    xSMVFacetImplArr[0] = new XSMVFacetImpl((short) 8, getLexicalPattern(), null, this.patternAnnotations);
                    i = 1;
                }
                if (this.fEnumeration != null) {
                    xSMVFacetImplArr[i] = new XSMVFacetImpl((short) 2048, getLexicalEnumeration(), new ObjectListImpl(this.fEnumeration, this.fEnumerationSize), this.enumerationAnnotations);
                    i++;
                }
                this.fMultiValueFacets = new XSObjectListImpl(xSMVFacetImplArr, i);
            }
        }
        XSObjectListImpl xSObjectListImpl = this.fMultiValueFacets;
        return xSObjectListImpl != null ? xSObjectListImpl : XSObjectListImpl.EMPTY_LIST;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public String getName() {
        if (getAnonymous()) {
            return null;
        }
        return this.fTypeName;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public String getNamespace() {
        return this.fTargetNamespace;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public XSNamespaceItem getNamespaceItem() {
        return this.fNamespaceItem;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public boolean getNumeric() {
        return this.fNumeric;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public short getOrdered() {
        return this.fOrdered;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
    public short getPrimitiveKind() {
        short s;
        if (this.fVariety != 1 || (s = this.fValidationDV) == 0) {
            return (short) 0;
        }
        if (s == 21 || s == 22 || s == 23) {
            return (short) 1;
        }
        if (s == 24) {
            return (short) 3;
        }
        return s;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public XSSimpleTypeDefinition getPrimitiveType() {
        if (this.fVariety != 1 || this.fValidationDV == 0) {
            return null;
        }
        while (true) {
            XSSimpleTypeDecl xSSimpleTypeDecl = this.fBase;
            if (xSSimpleTypeDecl == fAnySimpleType) {
                return this;
            }
            this = xSSimpleTypeDecl;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public short getType() {
        return (short) 3;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSTypeDefinition
    public short getTypeCategory() {
        return (short) 16;
    }

    @Override // org.w3c.dom.TypeInfo
    public String getTypeName() {
        return this.fTypeName;
    }

    @Override // org.w3c.dom.TypeInfo
    public String getTypeNamespace() {
        return getNamespace();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public short getVariety() {
        if (this.fValidationDV == 0) {
            return (short) 0;
        }
        return this.fVariety;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
    public short getWhitespace() throws DatatypeException {
        if (this.fVariety != 3) {
            return this.fWhiteSpace;
        }
        throw new DatatypeException("dt-whitespace", new Object[]{this.fTypeName});
    }

    public boolean isDOMDerivedFrom(String str, String str2, int i) {
        if (str2 == null) {
            return false;
        }
        if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(str) && "anyType".equals(str2) && ((i & 1) != 0 || i == 0)) {
            return true;
        }
        int i2 = i & 1;
        if (i2 != 0 && isDerivedByRestriction(str, str2, this)) {
            return true;
        }
        int i3 = i & 8;
        if (i3 != 0 && isDerivedByList(str, str2, this)) {
            return true;
        }
        int i4 = i & 4;
        if (i4 != 0 && isDerivedByUnion(str, str2, this)) {
            return true;
        }
        int i5 = i & 2;
        if (!(i5 != 0 && i2 == 0 && i3 == 0 && i4 == 0) && i5 == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            return isDerivedByAny(str, str2, this);
        }
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public boolean isDefinedFacet(short s) {
        short s2 = this.fValidationDV;
        if (s2 != 0 && s2 != 29) {
            if ((this.fFacetsDefined & s) != 0) {
                return true;
            }
            if (this.fPatternType != 0) {
                return s == 8;
            }
            if (s2 == 24) {
                return s == 8 || s == 1024;
            }
        }
        return false;
    }

    @Override // org.w3c.dom.TypeInfo
    public boolean isDerivedFrom(String str, String str2, int i) {
        return isDOMDerivedFrom(str, str2, i);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
    public boolean isEqual(Object obj, Object obj2) {
        if (obj == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSTypeDefinition
    public boolean isFinal(short s) {
        return (this.fFinalSet & s) != 0;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public boolean isFixedFacet(short s) {
        if ((this.fFixedFacet & s) != 0) {
            return true;
        }
        return this.fValidationDV == 24 && s == 1024;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
    public boolean isIDType() {
        short s = this.fVariety;
        if (s == 1) {
            return this.fValidationDV == 21;
        }
        if (s == 2) {
            return this.fItemType.isIDType();
        }
        if (s == 3) {
            int i = 0;
            while (true) {
                XSSimpleTypeDecl[] xSSimpleTypeDeclArr = this.fMemberTypes;
                if (i >= xSSimpleTypeDeclArr.length) {
                    break;
                }
                if (xSSimpleTypeDeclArr[i].isIDType()) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    public boolean isIdentical(Object obj, Object obj2) {
        if (obj == null) {
            return false;
        }
        return this.fDVs[this.fValidationDV].isIdentical(obj, obj2);
    }

    public String normalize(Object obj, short s) {
        int i;
        int i2;
        char cCharAt;
        if (obj == null) {
            return null;
        }
        if ((this.fFacetsDefined & 8) == 0) {
            short s2 = fDVNormalizeType[this.fValidationDV];
            if (s2 == 0) {
                return obj.toString();
            }
            if (s2 == 1) {
                return XMLChar.trim(obj.toString());
            }
        }
        if (!(obj instanceof StringBuffer)) {
            return normalize(obj.toString(), s);
        }
        StringBuffer stringBuffer = (StringBuffer) obj;
        int length = stringBuffer.length();
        if (length == 0) {
            return "";
        }
        if (s == 0) {
            return stringBuffer.toString();
        }
        if (s == 1) {
            for (int i3 = 0; i3 < length; i3++) {
                char cCharAt2 = stringBuffer.charAt(i3);
                if (cCharAt2 == '\t' || cCharAt2 == '\n' || cCharAt2 == '\r') {
                    stringBuffer.setCharAt(i3, ' ');
                }
            }
        } else {
            boolean z = true;
            int i4 = 0;
            int i5 = 0;
            while (i4 < length) {
                char cCharAt3 = stringBuffer.charAt(i4);
                if (cCharAt3 == '\t' || cCharAt3 == '\n' || cCharAt3 == '\r' || cCharAt3 == ' ') {
                    while (true) {
                        i = length - 1;
                        if (i4 >= i || !((cCharAt = stringBuffer.charAt((i2 = i4 + 1))) == '\t' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == ' ')) {
                            break;
                        }
                        i4 = i2;
                    }
                    if (i4 < i && !z) {
                        stringBuffer.setCharAt(i5, ' ');
                        i5++;
                    }
                } else {
                    stringBuffer.setCharAt(i5, cCharAt3);
                    i5++;
                    z = false;
                }
                i4++;
            }
            stringBuffer.setLength(i5);
        }
        return stringBuffer.toString();
    }

    public void reportError(String str, Object[] objArr) throws InvalidDatatypeFacetException {
        throw new InvalidDatatypeFacetException(str, objArr);
    }

    public void reset() {
        if (this.fIsImmutable) {
            return;
        }
        this.fItemType = null;
        this.fMemberTypes = null;
        this.fTypeName = null;
        this.fTargetNamespace = null;
        this.fFinalSet = (short) 0;
        this.fBase = null;
        this.fVariety = (short) -1;
        this.fValidationDV = (short) -1;
        this.fFacetsDefined = (short) 0;
        this.fFixedFacet = (short) 0;
        this.fWhiteSpace = (short) 0;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fPattern = null;
        this.fPatternStr = null;
        this.fEnumeration = null;
        this.fLexicalPattern = null;
        this.fLexicalEnumeration = null;
        this.fActualEnumeration = null;
        this.fEnumerationTypeList = null;
        this.fEnumerationItemTypeList = null;
        this.fMaxInclusive = null;
        this.fMaxExclusive = null;
        this.fMinExclusive = null;
        this.fMinInclusive = null;
        this.lengthAnnotation = null;
        this.minLengthAnnotation = null;
        this.maxLengthAnnotation = null;
        this.whiteSpaceAnnotation = null;
        this.totalDigitsAnnotation = null;
        this.fractionDigitsAnnotation = null;
        this.patternAnnotations = null;
        this.enumerationAnnotations = null;
        this.maxInclusiveAnnotation = null;
        this.maxExclusiveAnnotation = null;
        this.minInclusiveAnnotation = null;
        this.minExclusiveAnnotation = null;
        this.fPatternType = (short) 0;
        this.fAnnotations = null;
        this.fFacets = null;
    }

    public void setAnonymous(boolean z) {
        this.fAnonymous = z;
    }

    public void setDVs(TypeValidator[] typeValidatorArr) {
        this.fDVs = typeValidatorArr;
    }

    public XSSimpleTypeDecl setListValues(String str, String str2, short s, XSSimpleTypeDecl xSSimpleTypeDecl, XSObjectList xSObjectList) {
        if (this.fIsImmutable) {
            return null;
        }
        this.fBase = fAnySimpleType;
        this.fAnonymous = false;
        this.fTypeName = str;
        this.fTargetNamespace = str2;
        this.fFinalSet = s;
        this.fAnnotations = xSObjectList;
        this.fVariety = (short) 2;
        this.fItemType = xSSimpleTypeDecl;
        this.fValidationDV = (short) 25;
        this.fFacetsDefined = (short) 16;
        this.fFixedFacet = (short) 16;
        this.fWhiteSpace = (short) 2;
        calcFundamentalFacets();
        this.fBuiltInKind = (short) 44;
        return this;
    }

    public void setNamespaceItem(XSNamespaceItem xSNamespaceItem) {
        this.fNamespaceItem = xSNamespaceItem;
    }

    public XSSimpleTypeDecl setRestrictionValues(XSSimpleTypeDecl xSSimpleTypeDecl, String str, String str2, short s, XSObjectList xSObjectList) {
        if (this.fIsImmutable) {
            return null;
        }
        this.fBase = xSSimpleTypeDecl;
        this.fAnonymous = false;
        this.fTypeName = str;
        this.fTargetNamespace = str2;
        this.fFinalSet = s;
        this.fAnnotations = xSObjectList;
        short s2 = xSSimpleTypeDecl.fVariety;
        this.fVariety = s2;
        this.fValidationDV = xSSimpleTypeDecl.fValidationDV;
        if (s2 == 2) {
            this.fItemType = xSSimpleTypeDecl.fItemType;
        } else if (s2 == 3) {
            this.fMemberTypes = xSSimpleTypeDecl.fMemberTypes;
        }
        this.fLength = xSSimpleTypeDecl.fLength;
        this.fMinLength = xSSimpleTypeDecl.fMinLength;
        this.fMaxLength = xSSimpleTypeDecl.fMaxLength;
        this.fPattern = xSSimpleTypeDecl.fPattern;
        this.fPatternStr = xSSimpleTypeDecl.fPatternStr;
        this.fEnumeration = xSSimpleTypeDecl.fEnumeration;
        this.fEnumerationSize = xSSimpleTypeDecl.fEnumerationSize;
        this.fWhiteSpace = xSSimpleTypeDecl.fWhiteSpace;
        this.fMaxExclusive = xSSimpleTypeDecl.fMaxExclusive;
        this.fMaxInclusive = xSSimpleTypeDecl.fMaxInclusive;
        this.fMinExclusive = xSSimpleTypeDecl.fMinExclusive;
        this.fMinInclusive = xSSimpleTypeDecl.fMinInclusive;
        this.fTotalDigits = xSSimpleTypeDecl.fTotalDigits;
        this.fFractionDigits = xSSimpleTypeDecl.fFractionDigits;
        this.fPatternType = xSSimpleTypeDecl.fPatternType;
        this.fFixedFacet = xSSimpleTypeDecl.fFixedFacet;
        this.fFacetsDefined = xSSimpleTypeDecl.fFacetsDefined;
        calcFundamentalFacets();
        this.fBuiltInKind = xSSimpleTypeDecl.fBuiltInKind;
        return this;
    }

    public XSSimpleTypeDecl setUnionValues(String str, String str2, short s, XSSimpleTypeDecl[] xSSimpleTypeDeclArr, XSObjectList xSObjectList) {
        if (this.fIsImmutable) {
            return null;
        }
        this.fBase = fAnySimpleType;
        this.fAnonymous = false;
        this.fTypeName = str;
        this.fTargetNamespace = str2;
        this.fFinalSet = s;
        this.fAnnotations = xSObjectList;
        this.fVariety = (short) 3;
        this.fMemberTypes = xSSimpleTypeDeclArr;
        this.fValidationDV = (short) 26;
        this.fFacetsDefined = (short) 16;
        this.fWhiteSpace = (short) 2;
        calcFundamentalFacets();
        this.fBuiltInKind = (short) 45;
        return this;
    }

    public String toString() {
        return this.fTargetNamespace + "," + this.fTypeName;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
    public Object validate(String str, ValidationContext validationContext, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        if (validationContext == null) {
            validationContext = fEmptyContext;
        }
        if (validatedInfo == null) {
            validatedInfo = new ValidatedInfo();
        } else {
            validatedInfo.memberType = null;
        }
        Object actualValue = getActualValue(str, validationContext, validatedInfo, validationContext == null || validationContext.needToNormalize());
        validate(validationContext, validatedInfo);
        return actualValue;
    }

    public ValidatedInfo validateWithInfo(String str, ValidationContext validationContext, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        if (validationContext == null) {
            validationContext = fEmptyContext;
        }
        if (validatedInfo == null) {
            validatedInfo = new ValidatedInfo();
        } else {
            validatedInfo.memberType = null;
        }
        getActualValue(str, validationContext, validatedInfo, validationContext == null || validationContext.needToNormalize());
        validate(validationContext, validatedInfo);
        return validatedInfo;
    }

    public void applyFacets1(XSFacets xSFacets, short s, short s2, short s3) {
        try {
            applyFacets(xSFacets, s, s2, s3, fDummyContext);
            this.fIsImmutable = true;
        } catch (InvalidDatatypeFacetException unused) {
            f63.a("internal error");
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
    public Object validate(Object obj, ValidationContext validationContext, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        if (validationContext == null) {
            validationContext = fEmptyContext;
        }
        if (validatedInfo == null) {
            validatedInfo = new ValidatedInfo();
        } else {
            validatedInfo.memberType = null;
        }
        Object actualValue = getActualValue(obj, validationContext, validatedInfo, validationContext == null || validationContext.needToNormalize());
        validate(validationContext, validatedInfo);
        return actualValue;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
    public void validate(ValidationContext validationContext, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        short s;
        if (validationContext == null) {
            validationContext = fEmptyContext;
        }
        if (validationContext.needFacetChecking() && (s = this.fFacetsDefined) != 0 && s != 16) {
            checkFacets(validatedInfo);
        }
        if (validationContext.needExtraChecking()) {
            checkExtraRules(validationContext, validatedInfo);
        }
    }

    public static String normalize(String str, short s) {
        int i;
        int i2;
        char cCharAt;
        int length = str == null ? 0 : str.length();
        if (length == 0 || s == 0) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (s == 1) {
            for (int i3 = 0; i3 < length; i3++) {
                char cCharAt2 = str.charAt(i3);
                if (cCharAt2 != '\t' && cCharAt2 != '\n' && cCharAt2 != '\r') {
                    stringBuffer.append(cCharAt2);
                } else {
                    stringBuffer.append(' ');
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
                        stringBuffer.append(' ');
                    }
                } else {
                    stringBuffer.append(cCharAt3);
                    z = false;
                }
                i4++;
            }
        }
        return stringBuffer.toString();
    }

    public XSSimpleTypeDecl(XSSimpleTypeDecl xSSimpleTypeDecl, String str, short s, short s2, boolean z, boolean z2, boolean z3, boolean z4, short s3) {
        this.fDVs = gDVs;
        this.fFinalSet = (short) 0;
        this.fFixedFacet = (short) 0;
        this.fWhiteSpace = (short) 0;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fAnnotations = null;
        this.fPatternType = (short) 0;
        this.fNamespaceItem = null;
        this.fAnonymous = false;
        this.fIsImmutable = z4;
        this.fBase = xSSimpleTypeDecl;
        this.fTypeName = str;
        this.fTargetNamespace = "http://www.w3.org/2001/XMLSchema";
        this.fVariety = (short) 1;
        this.fValidationDV = s;
        this.fFacetsDefined = (short) 16;
        if (s != 0 && s != 29 && s != 1) {
            this.fWhiteSpace = (short) 2;
            this.fFixedFacet = (short) 16;
        } else {
            this.fWhiteSpace = (short) 0;
        }
        this.fOrdered = s2;
        this.fBounded = z;
        this.fFinite = z2;
        this.fNumeric = z3;
        this.fAnnotations = null;
        this.fBuiltInKind = s3;
    }

    public XSSimpleTypeDecl(XSSimpleTypeDecl xSSimpleTypeDecl, String str, String str2, short s, boolean z, XSObjectList xSObjectList, short s2) {
        this(xSSimpleTypeDecl, str, str2, s, z, xSObjectList);
        this.fBuiltInKind = s2;
    }

    public XSSimpleTypeDecl() {
        this.fDVs = gDVs;
        this.fIsImmutable = false;
        this.fFinalSet = (short) 0;
        this.fVariety = (short) -1;
        this.fValidationDV = (short) -1;
        this.fFacetsDefined = (short) 0;
        this.fFixedFacet = (short) 0;
        this.fWhiteSpace = (short) 0;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fAnnotations = null;
        this.fPatternType = (short) 0;
        this.fNamespaceItem = null;
        this.fAnonymous = false;
    }

    public XSSimpleTypeDecl(String str, String str2, short s, XSSimpleTypeDecl xSSimpleTypeDecl, boolean z, XSObjectList xSObjectList) {
        this.fDVs = gDVs;
        this.fIsImmutable = false;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fPatternType = (short) 0;
        this.fNamespaceItem = null;
        this.fAnonymous = false;
        this.fBase = fAnySimpleType;
        this.fTypeName = str;
        this.fTargetNamespace = str2;
        this.fFinalSet = s;
        this.fAnnotations = xSObjectList;
        this.fVariety = (short) 2;
        this.fItemType = xSSimpleTypeDecl;
        this.fValidationDV = (short) 25;
        this.fFacetsDefined = (short) 16;
        this.fFixedFacet = (short) 16;
        this.fWhiteSpace = (short) 2;
        calcFundamentalFacets();
        this.fIsImmutable = z;
        this.fBuiltInKind = (short) 44;
    }

    public XSSimpleTypeDecl(String str, String str2, short s, XSSimpleTypeDecl[] xSSimpleTypeDeclArr, XSObjectList xSObjectList) {
        this.fDVs = gDVs;
        this.fIsImmutable = false;
        this.fFixedFacet = (short) 0;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fPatternType = (short) 0;
        this.fNamespaceItem = null;
        this.fAnonymous = false;
        this.fBase = fAnySimpleType;
        this.fTypeName = str;
        this.fTargetNamespace = str2;
        this.fFinalSet = s;
        this.fAnnotations = xSObjectList;
        this.fVariety = (short) 3;
        this.fMemberTypes = xSSimpleTypeDeclArr;
        this.fValidationDV = (short) 26;
        this.fFacetsDefined = (short) 16;
        this.fWhiteSpace = (short) 2;
        calcFundamentalFacets();
        this.fIsImmutable = false;
        this.fBuiltInKind = (short) 45;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
    public void applyFacets(XSFacets xSFacets, short s, short s2, ValidationContext validationContext) throws InvalidDatatypeFacetException {
        if (validationContext == null) {
            validationContext = fEmptyContext;
        }
        applyFacets(xSFacets, s, s2, (short) 0, validationContext);
    }
}
