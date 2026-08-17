package com.sun.org.apache.xerces.internal.xni.grammars;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XMLGrammarPool {
    void cacheGrammars(String str, Grammar[] grammarArr);

    void clear();

    void lockPool();

    Grammar retrieveGrammar(XMLGrammarDescription xMLGrammarDescription);

    Grammar[] retrieveInitialGrammarSet(String str);

    void unlockPool();
}
