package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class WeakReferenceXMLSchema extends AbstractXMLSchema {
    private WeakReference<XMLGrammarPool> fGrammarPool = new WeakReference<>(null);

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.XSGrammarPoolContainer
    public synchronized XMLGrammarPool getGrammarPool() {
        XMLGrammarPool softReferenceGrammarPool;
        softReferenceGrammarPool = this.fGrammarPool.get();
        if (softReferenceGrammarPool == null) {
            softReferenceGrammarPool = new SoftReferenceGrammarPool();
            this.fGrammarPool = new WeakReference<>(softReferenceGrammarPool);
        }
        return softReferenceGrammarPool;
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.XSGrammarPoolContainer
    public boolean isFullyComposed() {
        return false;
    }
}
