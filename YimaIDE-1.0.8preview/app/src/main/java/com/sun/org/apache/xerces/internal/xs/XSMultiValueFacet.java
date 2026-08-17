package com.sun.org.apache.xerces.internal.xs;

import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSMultiValueFacet extends XSObject {
    XSObjectList getAnnotations();

    ObjectList getEnumerationValues();

    short getFacetKind();

    StringList getLexicalFacetValues();
}
