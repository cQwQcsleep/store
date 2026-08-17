package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSGrammarPoolContainer {
    Boolean getFeature(String str);

    XMLGrammarPool getGrammarPool();

    Object getProperty(String str);

    boolean isFullyComposed();

    void setFeature(String str, boolean z);

    void setProperty(String str, Object obj);
}
