package com.sun.org.apache.xerces.internal.xs;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSAttributeUse extends XSObject {
    @Deprecated
    Object getActualVC() throws XSException;

    @Deprecated
    short getActualVCType() throws XSException;

    XSObjectList getAnnotations();

    XSAttributeDeclaration getAttrDeclaration();

    short getConstraintType();

    @Deprecated
    String getConstraintValue();

    @Deprecated
    ShortList getItemValueTypes() throws XSException;

    boolean getRequired();

    XSValue getValueConstraintValue();
}
