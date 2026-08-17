package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory;
import com.sun.org.apache.xerces.internal.impl.dv.XSFacets;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.util.SymbolHash;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class BaseDVFactory extends SchemaDVFactory {
    static final String URI_SCHEMAFORSCHEMA = "http://www.w3.org/2001/XMLSchema";
    static SymbolHash fBaseTypes;

    static {
        SymbolHash symbolHash = new SymbolHash(53);
        fBaseTypes = symbolHash;
        createBuiltInTypes(symbolHash);
    }

    public static void createBuiltInTypes(SymbolHash symbolHash) {
        XSFacets xSFacets = new XSFacets();
        XSSimpleTypeDecl xSSimpleTypeDecl = XSSimpleTypeDecl.fAnySimpleType;
        symbolHash.put(SchemaSymbols.ATTVAL_ANYSIMPLETYPE, xSSimpleTypeDecl);
        symbolHash.put("string", new XSSimpleTypeDecl(xSSimpleTypeDecl, "string", (short) 1, (short) 0, false, false, false, true, (short) 2));
        symbolHash.put("boolean", new XSSimpleTypeDecl(xSSimpleTypeDecl, "boolean", (short) 2, (short) 0, false, true, false, true, (short) 3));
        XSSimpleTypeDecl xSSimpleTypeDecl2 = new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_DECIMAL, (short) 3, (short) 2, false, false, true, true, (short) 4);
        symbolHash.put(SchemaSymbols.ATTVAL_DECIMAL, xSSimpleTypeDecl2);
        symbolHash.put(SchemaSymbols.ATTVAL_ANYURI, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_ANYURI, (short) 17, (short) 0, false, false, false, true, (short) 18));
        symbolHash.put(SchemaSymbols.ATTVAL_BASE64BINARY, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_BASE64BINARY, (short) 16, (short) 0, false, false, false, true, (short) 17));
        symbolHash.put(SchemaSymbols.ATTVAL_DATETIME, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_DATETIME, (short) 7, (short) 1, false, false, false, true, (short) 8));
        symbolHash.put(SchemaSymbols.ATTVAL_TIME, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_TIME, (short) 8, (short) 1, false, false, false, true, (short) 9));
        symbolHash.put(SchemaSymbols.ATTVAL_DATE, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_DATE, (short) 9, (short) 1, false, false, false, true, (short) 10));
        symbolHash.put(SchemaSymbols.ATTVAL_YEARMONTH, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_YEARMONTH, (short) 10, (short) 1, false, false, false, true, (short) 11));
        symbolHash.put(SchemaSymbols.ATTVAL_YEAR, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_YEAR, (short) 11, (short) 1, false, false, false, true, (short) 12));
        symbolHash.put(SchemaSymbols.ATTVAL_MONTHDAY, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_MONTHDAY, (short) 12, (short) 1, false, false, false, true, (short) 13));
        symbolHash.put(SchemaSymbols.ATTVAL_DAY, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_DAY, (short) 13, (short) 1, false, false, false, true, (short) 14));
        symbolHash.put(SchemaSymbols.ATTVAL_MONTH, new XSSimpleTypeDecl(xSSimpleTypeDecl, SchemaSymbols.ATTVAL_MONTH, (short) 14, (short) 1, false, false, false, true, (short) 15));
        XSSimpleTypeDecl xSSimpleTypeDecl3 = new XSSimpleTypeDecl(xSSimpleTypeDecl2, SchemaSymbols.ATTVAL_INTEGER, (short) 24, (short) 2, false, false, true, true, (short) 30);
        symbolHash.put(SchemaSymbols.ATTVAL_INTEGER, xSSimpleTypeDecl3);
        xSFacets.maxInclusive = "0";
        XSSimpleTypeDecl xSSimpleTypeDecl4 = new XSSimpleTypeDecl(xSSimpleTypeDecl3, SchemaSymbols.ATTVAL_NONPOSITIVEINTEGER, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 31);
        xSSimpleTypeDecl4.applyFacets1(xSFacets, (short) 32, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_NONPOSITIVEINTEGER, xSSimpleTypeDecl4);
        xSFacets.maxInclusive = "-1";
        XSSimpleTypeDecl xSSimpleTypeDecl5 = new XSSimpleTypeDecl(xSSimpleTypeDecl4, SchemaSymbols.ATTVAL_NEGATIVEINTEGER, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 32);
        xSSimpleTypeDecl5.applyFacets1(xSFacets, (short) 32, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_NEGATIVEINTEGER, xSSimpleTypeDecl5);
        xSFacets.maxInclusive = "9223372036854775807";
        xSFacets.minInclusive = "-9223372036854775808";
        XSSimpleTypeDecl xSSimpleTypeDecl6 = new XSSimpleTypeDecl(xSSimpleTypeDecl3, "long", "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 33);
        xSSimpleTypeDecl6.applyFacets1(xSFacets, (short) 288, (short) 0);
        symbolHash.put("long", xSSimpleTypeDecl6);
        xSFacets.maxInclusive = "2147483647";
        xSFacets.minInclusive = "-2147483648";
        XSSimpleTypeDecl xSSimpleTypeDecl7 = new XSSimpleTypeDecl(xSSimpleTypeDecl6, "int", "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 34);
        xSSimpleTypeDecl7.applyFacets1(xSFacets, (short) 288, (short) 0);
        symbolHash.put("int", xSSimpleTypeDecl7);
        xSFacets.maxInclusive = "32767";
        xSFacets.minInclusive = "-32768";
        XSSimpleTypeDecl xSSimpleTypeDecl8 = new XSSimpleTypeDecl(xSSimpleTypeDecl7, "short", "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 35);
        xSSimpleTypeDecl8.applyFacets1(xSFacets, (short) 288, (short) 0);
        symbolHash.put("short", xSSimpleTypeDecl8);
        xSFacets.maxInclusive = "127";
        xSFacets.minInclusive = "-128";
        XSSimpleTypeDecl xSSimpleTypeDecl9 = new XSSimpleTypeDecl(xSSimpleTypeDecl8, "byte", "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 36);
        xSSimpleTypeDecl9.applyFacets1(xSFacets, (short) 288, (short) 0);
        symbolHash.put("byte", xSSimpleTypeDecl9);
        xSFacets.minInclusive = "0";
        XSSimpleTypeDecl xSSimpleTypeDecl10 = new XSSimpleTypeDecl(xSSimpleTypeDecl3, SchemaSymbols.ATTVAL_NONNEGATIVEINTEGER, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 37);
        xSSimpleTypeDecl10.applyFacets1(xSFacets, (short) 256, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_NONNEGATIVEINTEGER, xSSimpleTypeDecl10);
        xSFacets.maxInclusive = "18446744073709551615";
        XSSimpleTypeDecl xSSimpleTypeDecl11 = new XSSimpleTypeDecl(xSSimpleTypeDecl10, SchemaSymbols.ATTVAL_UNSIGNEDLONG, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 38);
        xSSimpleTypeDecl11.applyFacets1(xSFacets, (short) 32, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_UNSIGNEDLONG, xSSimpleTypeDecl11);
        xSFacets.maxInclusive = "4294967295";
        XSSimpleTypeDecl xSSimpleTypeDecl12 = new XSSimpleTypeDecl(xSSimpleTypeDecl11, SchemaSymbols.ATTVAL_UNSIGNEDINT, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 39);
        xSSimpleTypeDecl12.applyFacets1(xSFacets, (short) 32, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_UNSIGNEDINT, xSSimpleTypeDecl12);
        xSFacets.maxInclusive = "65535";
        XSSimpleTypeDecl xSSimpleTypeDecl13 = new XSSimpleTypeDecl(xSSimpleTypeDecl12, SchemaSymbols.ATTVAL_UNSIGNEDSHORT, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 40);
        xSSimpleTypeDecl13.applyFacets1(xSFacets, (short) 32, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_UNSIGNEDSHORT, xSSimpleTypeDecl13);
        xSFacets.maxInclusive = "255";
        XSSimpleTypeDecl xSSimpleTypeDecl14 = new XSSimpleTypeDecl(xSSimpleTypeDecl13, SchemaSymbols.ATTVAL_UNSIGNEDBYTE, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 41);
        xSSimpleTypeDecl14.applyFacets1(xSFacets, (short) 32, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_UNSIGNEDBYTE, xSSimpleTypeDecl14);
        xSFacets.minInclusive = "1";
        XSSimpleTypeDecl xSSimpleTypeDecl15 = new XSSimpleTypeDecl(xSSimpleTypeDecl10, SchemaSymbols.ATTVAL_POSITIVEINTEGER, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 42);
        xSSimpleTypeDecl15.applyFacets1(xSFacets, (short) 256, (short) 0);
        symbolHash.put(SchemaSymbols.ATTVAL_POSITIVEINTEGER, xSSimpleTypeDecl15);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory
    public XSSimpleType createTypeList(String str, String str2, short s, XSSimpleType xSSimpleType, XSObjectList xSObjectList) {
        return new XSSimpleTypeDecl(str, str2, s, (XSSimpleTypeDecl) xSSimpleType, false, xSObjectList);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory
    public XSSimpleType createTypeRestriction(String str, String str2, short s, XSSimpleType xSSimpleType, XSObjectList xSObjectList) {
        return new XSSimpleTypeDecl((XSSimpleTypeDecl) xSSimpleType, str, str2, s, false, xSObjectList);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory
    public XSSimpleType createTypeUnion(String str, String str2, short s, XSSimpleType[] xSSimpleTypeArr, XSObjectList xSObjectList) {
        int length = xSSimpleTypeArr.length;
        XSSimpleTypeDecl[] xSSimpleTypeDeclArr = new XSSimpleTypeDecl[length];
        System.arraycopy(xSSimpleTypeArr, 0, xSSimpleTypeDeclArr, 0, length);
        return new XSSimpleTypeDecl(str, str2, s, xSSimpleTypeDeclArr, xSObjectList);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory
    public XSSimpleType getBuiltInType(String str) {
        return (XSSimpleType) fBaseTypes.get(str);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory
    public SymbolHash getBuiltInTypes() {
        return fBaseTypes.makeClone();
    }
}
