package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory;
import com.sun.org.apache.xerces.internal.impl.dv.XSFacets;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XSDeclarationPool;
import com.sun.org.apache.xerces.internal.util.SymbolHash;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class BaseSchemaDVFactory extends SchemaDVFactory {
    static final String URI_SCHEMAFORSCHEMA = "http://www.w3.org/2001/XMLSchema";
    protected XSDeclarationPool fDeclPool = null;

    public static void createBuiltInTypes(SymbolHash symbolHash, XSSimpleTypeDecl xSSimpleTypeDecl) {
        XSFacets xSFacets = new XSFacets();
        symbolHash.put(SchemaSymbols.ATTVAL_ANYSIMPLETYPE, XSSimpleTypeDecl.fAnySimpleType);
        XSSimpleTypeDecl xSSimpleTypeDecl2 = new XSSimpleTypeDecl(xSSimpleTypeDecl, "string", (short) 1, (short) 0, false, false, false, true, (short) 2);
        symbolHash.put("string", xSSimpleTypeDecl2);
        symbolHash.put("boolean", new XSSimpleTypeDecl(xSSimpleTypeDecl, "boolean", (short) 2, (short) 0, false, true, false, true, (short) 3));
        XSSimpleTypeDecl xSSimpleTypeDecl3 = new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_DECIMAL, (short) 3, (short) 2, false, false, true, true, (short) 4);
        symbolHash.put(SchemaSymbols.ATTVAL_DECIMAL, xSSimpleTypeDecl3);
        symbolHash.put(SchemaSymbols.ATTVAL_ANYURI, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_ANYURI, (short) 17, (short) 0, false, false, false, true, (short) 18));
        symbolHash.put(SchemaSymbols.ATTVAL_BASE64BINARY, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_BASE64BINARY, (short) 16, (short) 0, false, false, false, true, (short) 17));
        symbolHash.put(SchemaSymbols.ATTVAL_DURATION, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_DURATION, (short) 6, (short) 1, false, false, false, true, (short) 7));
        symbolHash.put(SchemaSymbols.ATTVAL_DATETIME, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_DATETIME, (short) 7, (short) 1, false, false, false, true, (short) 8));
        symbolHash.put(SchemaSymbols.ATTVAL_TIME, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_TIME, (short) 8, (short) 1, false, false, false, true, (short) 9));
        symbolHash.put(SchemaSymbols.ATTVAL_DATE, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_DATE, (short) 9, (short) 1, false, false, false, true, (short) 10));
        symbolHash.put(SchemaSymbols.ATTVAL_YEARMONTH, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_YEARMONTH, (short) 10, (short) 1, false, false, false, true, (short) 11));
        symbolHash.put(SchemaSymbols.ATTVAL_YEAR, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_YEAR, (short) 11, (short) 1, false, false, false, true, (short) 12));
        symbolHash.put(SchemaSymbols.ATTVAL_MONTHDAY, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_MONTHDAY, (short) 12, (short) 1, false, false, false, true, (short) 13));
        symbolHash.put(SchemaSymbols.ATTVAL_DAY, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_DAY, (short) 13, (short) 1, false, false, false, true, (short) 14));
        symbolHash.put(SchemaSymbols.ATTVAL_MONTH, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_MONTH, (short) 14, (short) 1, false, false, false, true, (short) 15));
        XSSimpleTypeDecl xSSimpleTypeDecl4 = new XSSimpleTypeDecl(xSSimpleTypeDecl3, SchemaSymbols.ATTVAL_INTEGER, (short) 24, (short) 2, false, false, true, true, (short) 30);
        symbolHash.put(SchemaSymbols.ATTVAL_INTEGER, xSSimpleTypeDecl4);
        xSFacets.maxInclusive = "0";
        XSSimpleTypeDecl xSSimpleTypeDecl5 = new XSSimpleTypeDecl(xSSimpleTypeDecl4, SchemaSymbols.ATTVAL_NONPOSITIVEINTEGER, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 31);
        xSSimpleTypeDecl5.applyFacets1(xSFacets, (short) 32, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_NONPOSITIVEINTEGER, xSSimpleTypeDecl5);
        xSFacets.maxInclusive = "-1";
        XSSimpleTypeDecl xSSimpleTypeDecl6 = new XSSimpleTypeDecl(xSSimpleTypeDecl5, SchemaSymbols.ATTVAL_NEGATIVEINTEGER, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 32);
        xSSimpleTypeDecl6.applyFacets1(xSFacets, (short) 32, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_NEGATIVEINTEGER, xSSimpleTypeDecl6);
        xSFacets.maxInclusive = "9223372036854775807";
        xSFacets.minInclusive = "-9223372036854775808";
        XSSimpleTypeDecl xSSimpleTypeDecl7 = new XSSimpleTypeDecl(xSSimpleTypeDecl4, "long", "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 33);
        xSSimpleTypeDecl7.applyFacets1(xSFacets, (short) 288, (short) 0);
        symbolHash.put("long", xSSimpleTypeDecl7);
        xSFacets.maxInclusive = "2147483647";
        xSFacets.minInclusive = "-2147483648";
        XSSimpleTypeDecl xSSimpleTypeDecl8 = new XSSimpleTypeDecl(xSSimpleTypeDecl7, "int", "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 34);
        xSSimpleTypeDecl8.applyFacets1(xSFacets, (short) 288, (short) 0);
        symbolHash.put("int", xSSimpleTypeDecl8);
        xSFacets.maxInclusive = "32767";
        xSFacets.minInclusive = "-32768";
        XSSimpleTypeDecl xSSimpleTypeDecl9 = new XSSimpleTypeDecl(xSSimpleTypeDecl8, "short", "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 35);
        xSSimpleTypeDecl9.applyFacets1(xSFacets, (short) 288, (short) 0);
        symbolHash.put("short", xSSimpleTypeDecl9);
        xSFacets.maxInclusive = "127";
        xSFacets.minInclusive = "-128";
        XSSimpleTypeDecl xSSimpleTypeDecl10 = new XSSimpleTypeDecl(xSSimpleTypeDecl9, "byte", "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 36);
        xSSimpleTypeDecl10.applyFacets1(xSFacets, (short) 288, (short) 0);
        symbolHash.put("byte", xSSimpleTypeDecl10);
        xSFacets.minInclusive = "0";
        XSSimpleTypeDecl xSSimpleTypeDecl11 = new XSSimpleTypeDecl(xSSimpleTypeDecl4, SchemaSymbols.ATTVAL_NONNEGATIVEINTEGER, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 37);
        xSSimpleTypeDecl11.applyFacets1(xSFacets, (short) 256, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_NONNEGATIVEINTEGER, xSSimpleTypeDecl11);
        xSFacets.maxInclusive = "18446744073709551615";
        XSSimpleTypeDecl xSSimpleTypeDecl12 = new XSSimpleTypeDecl(xSSimpleTypeDecl11, SchemaSymbols.ATTVAL_UNSIGNEDLONG, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 38);
        xSSimpleTypeDecl12.applyFacets1(xSFacets, (short) 32, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_UNSIGNEDLONG, xSSimpleTypeDecl12);
        xSFacets.maxInclusive = "4294967295";
        XSSimpleTypeDecl xSSimpleTypeDecl13 = new XSSimpleTypeDecl(xSSimpleTypeDecl12, SchemaSymbols.ATTVAL_UNSIGNEDINT, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 39);
        xSSimpleTypeDecl13.applyFacets1(xSFacets, (short) 32, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_UNSIGNEDINT, xSSimpleTypeDecl13);
        xSFacets.maxInclusive = "65535";
        XSSimpleTypeDecl xSSimpleTypeDecl14 = new XSSimpleTypeDecl(xSSimpleTypeDecl13, SchemaSymbols.ATTVAL_UNSIGNEDSHORT, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 40);
        xSSimpleTypeDecl14.applyFacets1(xSFacets, (short) 32, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_UNSIGNEDSHORT, xSSimpleTypeDecl14);
        xSFacets.maxInclusive = "255";
        XSSimpleTypeDecl xSSimpleTypeDecl15 = new XSSimpleTypeDecl(xSSimpleTypeDecl14, SchemaSymbols.ATTVAL_UNSIGNEDBYTE, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 41);
        xSSimpleTypeDecl15.applyFacets1(xSFacets, (short) 32, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_UNSIGNEDBYTE, xSSimpleTypeDecl15);
        xSFacets.minInclusive = "1";
        XSSimpleTypeDecl xSSimpleTypeDecl16 = new XSSimpleTypeDecl(xSSimpleTypeDecl11, SchemaSymbols.ATTVAL_POSITIVEINTEGER, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 42);
        xSSimpleTypeDecl16.applyFacets1(xSFacets, (short) 256, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_POSITIVEINTEGER, xSSimpleTypeDecl16);
        symbolHash.put("float", new XSSimpleTypeDecl(xSSimpleTypeDecl, "float", (short) 4, (short) 1, true, true, true, true, (short) 5));
        symbolHash.put("double", new XSSimpleTypeDecl(xSSimpleTypeDecl, "double", (short) 5, (short) 1, true, true, true, true, (short) 6));
        symbolHash.put(SchemaSymbols.ATTVAL_HEXBINARY, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_HEXBINARY, (short) 15, (short) 0, false, false, false, true, (short) 16));
        symbolHash.put(SchemaSymbols.ATTVAL_NOTATION, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_NOTATION, (short) 20, (short) 0, false, false, false, true, (short) 20));
        xSFacets.whiteSpace = (short) 1;
        XSSimpleTypeDecl xSSimpleTypeDecl17 = new XSSimpleTypeDecl(xSSimpleTypeDecl2, SchemaSymbols.ATTVAL_NORMALIZEDSTRING, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 21);
        xSSimpleTypeDecl17.applyFacets1(xSFacets, (short) 16, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_NORMALIZEDSTRING, xSSimpleTypeDecl17);
        xSFacets.whiteSpace = (short) 2;
        XSSimpleTypeDecl xSSimpleTypeDecl18 = new XSSimpleTypeDecl(xSSimpleTypeDecl17, SchemaSymbols.ATTVAL_TOKEN, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 22);
        xSSimpleTypeDecl18.applyFacets1(xSFacets, (short) 16, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_TOKEN, xSSimpleTypeDecl18);
        xSFacets.whiteSpace = (short) 2;
        xSFacets.pattern = "([a-zA-Z]{1,8})(-[a-zA-Z0-9]{1,8})*";
        XSSimpleTypeDecl xSSimpleTypeDecl19 = new XSSimpleTypeDecl(xSSimpleTypeDecl18, "language", "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 23);
        xSSimpleTypeDecl19.applyFacets1(xSFacets, (short) 24, (short) 0);
        symbolHash.put("language", xSSimpleTypeDecl19);
        xSFacets.whiteSpace = (short) 2;
        XSSimpleTypeDecl xSSimpleTypeDecl20 = new XSSimpleTypeDecl(xSSimpleTypeDecl18, SchemaSymbols.ATTVAL_NAME, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 25);
        xSSimpleTypeDecl20.applyFacets1(xSFacets, (short) 16, (short) 0, (short) 2);
        symbolHash.put(SchemaSymbols.ATTVAL_NAME, xSSimpleTypeDecl20);
        xSFacets.whiteSpace = (short) 2;
        XSSimpleTypeDecl xSSimpleTypeDecl21 = new XSSimpleTypeDecl(xSSimpleTypeDecl20, SchemaSymbols.ATTVAL_NCNAME, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 26);
        xSSimpleTypeDecl21.applyFacets1(xSFacets, (short) 16, (short) 0, (short) 3);
        symbolHash.put(SchemaSymbols.ATTVAL_NCNAME, xSSimpleTypeDecl21);
        symbolHash.put(SchemaSymbols.ATTVAL_QNAME, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_QNAME, (short) 18, (short) 0, false, false, false, true, (short) 19));
        symbolHash.put(SchemaSymbols.ATTVAL_ID, new XSSimpleTypeDecl(xSSimpleTypeDecl21, SchemaSymbols.ATTVAL_ID, (short) 21, (short) 0, false, false, false, true, (short) 27));
        XSSimpleTypeDecl xSSimpleTypeDecl22 = new XSSimpleTypeDecl(xSSimpleTypeDecl21, SchemaSymbols.ATTVAL_IDREF, (short) 22, (short) 0, false, false, false, true, (short) 28);
        symbolHash.put(SchemaSymbols.ATTVAL_IDREF, xSSimpleTypeDecl22);
        xSFacets.minLength = 1;
        XSSimpleTypeDecl xSSimpleTypeDecl23 = new XSSimpleTypeDecl(new XSSimpleTypeDecl((String) null, "http://www.w3.org/2001/XMLSchema", (short) 0, xSSimpleTypeDecl22, true, (XSObjectList) null), SchemaSymbols.ATTVAL_IDREFS, "http://www.w3.org/2001/XMLSchema", (short) 0, false, (XSObjectList) null);
        xSSimpleTypeDecl23.applyFacets1(xSFacets, (short) 2, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_IDREFS, xSSimpleTypeDecl23);
        XSSimpleTypeDecl xSSimpleTypeDecl24 = new XSSimpleTypeDecl(xSSimpleTypeDecl21, SchemaSymbols.ATTVAL_ENTITY, (short) 23, (short) 0, false, false, false, true, (short) 29);
        symbolHash.put(SchemaSymbols.ATTVAL_ENTITY, xSSimpleTypeDecl24);
        xSFacets.minLength = 1;
        XSSimpleTypeDecl xSSimpleTypeDecl25 = new XSSimpleTypeDecl(new XSSimpleTypeDecl((String) null, "http://www.w3.org/2001/XMLSchema", (short) 0, xSSimpleTypeDecl24, true, (XSObjectList) null), SchemaSymbols.ATTVAL_ENTITIES, "http://www.w3.org/2001/XMLSchema", (short) 0, false, (XSObjectList) null);
        xSSimpleTypeDecl25.applyFacets1(xSFacets, (short) 2, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_ENTITIES, xSSimpleTypeDecl25);
        xSFacets.whiteSpace = (short) 2;
        XSSimpleTypeDecl xSSimpleTypeDecl26 = new XSSimpleTypeDecl(xSSimpleTypeDecl18, SchemaSymbols.ATTVAL_NMTOKEN, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 24);
        xSSimpleTypeDecl26.applyFacets1(xSFacets, (short) 16, (short) 0, (short) 1);
        symbolHash.put(SchemaSymbols.ATTVAL_NMTOKEN, xSSimpleTypeDecl26);
        xSFacets.minLength = 1;
        XSSimpleTypeDecl xSSimpleTypeDecl27 = new XSSimpleTypeDecl(new XSSimpleTypeDecl((String) null, "http://www.w3.org/2001/XMLSchema", (short) 0, xSSimpleTypeDecl26, true, (XSObjectList) null), SchemaSymbols.ATTVAL_NMTOKENS, "http://www.w3.org/2001/XMLSchema", (short) 0, false, (XSObjectList) null);
        xSSimpleTypeDecl27.applyFacets1(xSFacets, (short) 2, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_NMTOKENS, xSSimpleTypeDecl27);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory
    public XSSimpleType createTypeList(String str, String str2, short s, XSSimpleType xSSimpleType, XSObjectList xSObjectList) {
        XSDeclarationPool xSDeclarationPool = this.fDeclPool;
        return xSDeclarationPool != null ? xSDeclarationPool.getSimpleTypeDecl().setListValues(str, str2, s, (XSSimpleTypeDecl) xSSimpleType, xSObjectList) : new XSSimpleTypeDecl(str, str2, s, (XSSimpleTypeDecl) xSSimpleType, false, xSObjectList);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory
    public XSSimpleType createTypeRestriction(String str, String str2, short s, XSSimpleType xSSimpleType, XSObjectList xSObjectList) {
        XSDeclarationPool xSDeclarationPool = this.fDeclPool;
        return xSDeclarationPool != null ? xSDeclarationPool.getSimpleTypeDecl().setRestrictionValues((XSSimpleTypeDecl) xSSimpleType, str, str2, s, xSObjectList) : new XSSimpleTypeDecl((XSSimpleTypeDecl) xSSimpleType, str, str2, s, false, xSObjectList);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory
    public XSSimpleType createTypeUnion(String str, String str2, short s, XSSimpleType[] xSSimpleTypeArr, XSObjectList xSObjectList) {
        int length = xSSimpleTypeArr.length;
        XSSimpleTypeDecl[] xSSimpleTypeDeclArr = new XSSimpleTypeDecl[length];
        System.arraycopy(xSSimpleTypeArr, 0, xSSimpleTypeDeclArr, 0, length);
        XSDeclarationPool xSDeclarationPool = this.fDeclPool;
        return xSDeclarationPool != null ? xSDeclarationPool.getSimpleTypeDecl().setUnionValues(str, str2, s, xSSimpleTypeDeclArr, xSObjectList) : new XSSimpleTypeDecl(str, str2, s, xSSimpleTypeDeclArr, xSObjectList);
    }

    public XSSimpleTypeDecl newXSSimpleTypeDecl() {
        return new XSSimpleTypeDecl();
    }

    public void setDeclPool(XSDeclarationPool xSDeclarationPool) {
        this.fDeclPool = xSDeclarationPool;
    }
}
