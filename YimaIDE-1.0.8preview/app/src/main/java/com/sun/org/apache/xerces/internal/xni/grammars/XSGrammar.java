package com.sun.org.apache.xerces.internal.xni.grammars;

import com.sun.org.apache.xerces.internal.xs.XSModel;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSGrammar extends Grammar {
    XSModel toXSModel();

    XSModel toXSModel(XSGrammar[] xSGrammarArr);
}
