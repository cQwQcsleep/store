package com.sun.org.apache.xerces.internal.xs;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSValue {
    Object getActualValue();

    short getActualValueType();

    ShortList getListValueTypes();

    XSSimpleTypeDefinition getMemberTypeDefinition();

    XSObjectList getMemberTypeDefinitions();

    String getNormalizedValue();

    XSSimpleTypeDefinition getTypeDefinition();
}
