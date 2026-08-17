package com.sun.org.apache.xerces.internal.xs;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ItemPSVI {
    public static final short VALIDATION_FULL = 2;
    public static final short VALIDATION_NONE = 0;
    public static final short VALIDATION_PARTIAL = 1;
    public static final short VALIDITY_INVALID = 1;
    public static final short VALIDITY_NOTKNOWN = 0;
    public static final short VALIDITY_VALID = 2;

    ItemPSVI constant();

    @Deprecated
    Object getActualNormalizedValue() throws XSException;

    @Deprecated
    short getActualNormalizedValueType() throws XSException;

    StringList getErrorCodes();

    StringList getErrorMessages();

    boolean getIsSchemaSpecified();

    @Deprecated
    ShortList getItemValueTypes() throws XSException;

    XSSimpleTypeDefinition getMemberTypeDefinition();

    String getSchemaDefault();

    @Deprecated
    String getSchemaNormalizedValue();

    XSValue getSchemaValue();

    XSTypeDefinition getTypeDefinition();

    short getValidationAttempted();

    String getValidationContext();

    short getValidity();

    boolean isConstant();
}
