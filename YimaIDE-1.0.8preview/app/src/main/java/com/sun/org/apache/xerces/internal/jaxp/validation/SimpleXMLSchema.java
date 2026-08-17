package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.xni.grammars.Grammar;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarDescription;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class SimpleXMLSchema extends AbstractXMLSchema implements XMLGrammarPool {
    private static final Grammar[] ZERO_LENGTH_GRAMMAR_ARRAY = new Grammar[0];
    private Grammar fGrammar;
    private XMLGrammarDescription fGrammarDescription;
    private Grammar[] fGrammars;

    public SimpleXMLSchema(Grammar grammar) {
        this.fGrammar = grammar;
        this.fGrammars = new Grammar[]{grammar};
        this.fGrammarDescription = grammar.getGrammarDescription();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
    public void cacheGrammars(String str, Grammar[] grammarArr) {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
    public void clear() {
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.XSGrammarPoolContainer
    public XMLGrammarPool getGrammarPool() {
        return this;
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.XSGrammarPoolContainer
    public boolean isFullyComposed() {
        return true;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
    public void lockPool() {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
    public Grammar retrieveGrammar(XMLGrammarDescription xMLGrammarDescription) {
        if (this.fGrammarDescription.equals(xMLGrammarDescription)) {
            return this.fGrammar;
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
    public Grammar[] retrieveInitialGrammarSet(String str) {
        return "http://www.w3.org/2001/XMLSchema".equals(str) ? (Grammar[]) this.fGrammars.clone() : ZERO_LENGTH_GRAMMAR_ARRAY;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
    public void unlockPool() {
    }
}
