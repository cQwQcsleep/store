package com.sun.org.apache.xerces.internal.xs;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSFacet extends XSObject {
    Object getActualFacetValue();

    XSAnnotation getAnnotation();

    XSObjectList getAnnotations();

    short getFacetKind();

    boolean getFixed();

    int getIntFacetValue();

    String getLexicalFacetValue();
}
