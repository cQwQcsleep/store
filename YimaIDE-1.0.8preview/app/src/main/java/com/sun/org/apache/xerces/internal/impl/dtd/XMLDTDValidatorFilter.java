package com.sun.org.apache.xerces.internal.impl.dtd;

import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentFilter;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XMLDTDValidatorFilter extends XMLDocumentFilter {
    boolean hasGrammar();

    boolean validate();
}
