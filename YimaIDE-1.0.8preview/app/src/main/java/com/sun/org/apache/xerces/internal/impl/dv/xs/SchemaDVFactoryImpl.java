package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.util.SymbolHash;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SchemaDVFactoryImpl extends BaseSchemaDVFactory {
    static final SymbolHash fBuiltInTypes = new SymbolHash();

    static {
        createBuiltInTypes();
    }

    public static void createBuiltInTypes() {
        BaseSchemaDVFactory.createBuiltInTypes(fBuiltInTypes, XSSimpleTypeDecl.fAnySimpleType);
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
