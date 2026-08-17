package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.util.SymbolHash;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ExtendedSchemaDVFactoryImpl extends BaseSchemaDVFactory {
    static SymbolHash fBuiltInTypes = new SymbolHash();

    static {
        createBuiltInTypes();
    }

    public static void createBuiltInTypes() {
        SymbolHash symbolHash = fBuiltInTypes;
        XSSimpleTypeDecl xSSimpleTypeDecl = XSSimpleTypeDecl.fAnyAtomicType;
        BaseSchemaDVFactory.createBuiltInTypes(symbolHash, xSSimpleTypeDecl);
        fBuiltInTypes.put("anyAtomicType", xSSimpleTypeDecl);
        XSSimpleTypeDecl xSSimpleTypeDecl2 = (XSSimpleTypeDecl) fBuiltInTypes.get(SchemaSymbols.ATTVAL_DURATION);
        fBuiltInTypes.put("yearMonthDuration", new XSSimpleTypeDecl(xSSimpleTypeDecl2, "yearMonthDuration", (short) 27, (short) 1, false, false, false, true, (short) 46));
        fBuiltInTypes.put("dayTimeDuration", new XSSimpleTypeDecl(xSSimpleTypeDecl2, "dayTimeDuration", (short) 28, (short) 1, false, false, false, true, (short) 47));
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory
    public XSSimpleType getBuiltInType(String str) {
        return (XSSimpleType) fBuiltInTypes.get(str);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory
    public SymbolHash getBuiltInTypes() {
        return fBuiltInTypes.makeClone();
    }
}
