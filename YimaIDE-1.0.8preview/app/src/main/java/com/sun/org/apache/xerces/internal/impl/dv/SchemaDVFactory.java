package com.sun.org.apache.xerces.internal.impl.dv;

import com.sun.org.apache.xerces.internal.util.SymbolHash;
import com.sun.org.apache.xerces.internal.utils.ObjectFactory;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SchemaDVFactory {
    private static final String DEFAULT_FACTORY_CLASS = "com.sun.org.apache.xerces.internal.impl.dv.xs.SchemaDVFactoryImpl";

    public static final synchronized SchemaDVFactory getInstance(String str) throws DVFactoryException {
        try {
        } catch (ClassCastException unused) {
            throw new DVFactoryException("Schema factory class " + str + " does not extend from SchemaDVFactory.");
        }
        return (SchemaDVFactory) ObjectFactory.newInstance(str, true);
    }

    public abstract XSSimpleType createTypeList(String str, String str2, short s, XSSimpleType xSSimpleType, XSObjectList xSObjectList);

    public abstract XSSimpleType createTypeRestriction(String str, String str2, short s, XSSimpleType xSSimpleType, XSObjectList xSObjectList);

    public abstract XSSimpleType createTypeUnion(String str, String str2, short s, XSSimpleType[] xSSimpleTypeArr, XSObjectList xSObjectList);

    public abstract XSSimpleType getBuiltInType(String str);

    public abstract SymbolHash getBuiltInTypes();

    public static final synchronized SchemaDVFactory getInstance() throws DVFactoryException {
        return getInstance(DEFAULT_FACTORY_CLASS);
    }
}
