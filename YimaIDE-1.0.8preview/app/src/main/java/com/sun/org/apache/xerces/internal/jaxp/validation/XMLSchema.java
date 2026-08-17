package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class XMLSchema extends AbstractXMLSchema {
    private final boolean fFullyComposed;
    private final XMLGrammarPool fGrammarPool;

    public XMLSchema(XMLGrammarPool xMLGrammarPool, boolean z) {
        this.fGrammarPool = xMLGrammarPool;
        this.fFullyComposed = z;
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.XSGrammarPoolContainer
    public XMLGrammarPool getGrammarPool() {
        return this.fGrammarPool;
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.XSGrammarPoolContainer
    public boolean isFullyComposed() {
        return this.fFullyComposed;
    }

    public XMLSchema(XMLGrammarPool xMLGrammarPool) {
        this(xMLGrammarPool, true);
    }
}
