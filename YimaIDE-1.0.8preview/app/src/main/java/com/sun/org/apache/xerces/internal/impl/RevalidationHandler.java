package com.sun.org.apache.xerces.internal.impl;

import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentFilter;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface RevalidationHandler extends XMLDocumentFilter {
    boolean characterData(String str, Augmentations augmentations);
}
