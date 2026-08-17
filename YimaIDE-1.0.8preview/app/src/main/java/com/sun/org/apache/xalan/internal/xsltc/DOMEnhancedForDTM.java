package com.sun.org.apache.xalan.internal.xsltc;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DOMEnhancedForDTM extends DOM {
    String getDocumentURI();

    int getElementById(String str);

    int getExpandedTypeID2(int i);

    short[] getMapping(String[] strArr, String[] strArr2, int[] iArr);

    short[] getNamespaceMapping(String[] strArr);

    int[] getReverseMapping(String[] strArr, String[] strArr2, int[] iArr);

    short[] getReverseNamespaceMapping(String[] strArr);

    boolean hasDOMSource();

    void setDocumentURI(String str);
}
