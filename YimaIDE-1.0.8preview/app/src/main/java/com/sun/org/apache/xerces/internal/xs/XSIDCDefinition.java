package com.sun.org.apache.xerces.internal.xs;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSIDCDefinition extends XSObject {
    public static final short IC_KEY = 1;
    public static final short IC_KEYREF = 2;
    public static final short IC_UNIQUE = 3;

    XSObjectList getAnnotations();

    short getCategory();

    StringList getFieldStrs();

    XSIDCDefinition getRefKey();

    String getSelectorStr();
}
