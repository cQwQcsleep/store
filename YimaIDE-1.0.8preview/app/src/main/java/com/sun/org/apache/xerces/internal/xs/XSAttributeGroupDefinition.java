package com.sun.org.apache.xerces.internal.xs;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSAttributeGroupDefinition extends XSObject {
    XSAnnotation getAnnotation();

    XSObjectList getAnnotations();

    XSObjectList getAttributeUses();

    XSWildcard getAttributeWildcard();
}
