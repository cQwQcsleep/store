package com.sun.org.apache.xerces.internal.xs;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSAttributeDeclaration extends XSObject {
    @Deprecated
    Object getActualVC() throws XSException;

    @Deprecated
    short getActualVCType() throws XSException;

    XSAnnotation getAnnotation();

    XSObjectList getAnnotations();

    short getConstraintType();

    @Deprecated
    String getConstraintValue();

    XSComplexTypeDefinition getEnclosingCTDefinition();

    @Deprecated
    ShortList getItemValueTypes() throws XSException;

    short getScope();

    XSSimpleTypeDefinition getTypeDefinition();

    XSValue getValueConstraintValue();
}
