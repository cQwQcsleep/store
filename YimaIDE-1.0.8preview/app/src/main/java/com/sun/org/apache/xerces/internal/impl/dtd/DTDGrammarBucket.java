package com.sun.org.apache.xerces.internal.impl.dtd;

import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarDescription;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTDGrammarBucket {
    protected DTDGrammar fActiveGrammar;
    protected Map<XMLDTDDescription, DTDGrammar> fGrammars = new HashMap();
    protected boolean fIsStandalone;

    public void clear() {
        this.fGrammars.clear();
        this.fActiveGrammar = null;
        this.fIsStandalone = false;
    }

    public DTDGrammar getActiveGrammar() {
        return this.fActiveGrammar;
    }

    public DTDGrammar getGrammar(XMLGrammarDescription xMLGrammarDescription) {
        return this.fGrammars.get((XMLDTDDescription) xMLGrammarDescription);
    }

    public boolean getStandalone() {
        return this.fIsStandalone;
    }

    public void putGrammar(DTDGrammar dTDGrammar) {
        this.fGrammars.put((XMLDTDDescription) dTDGrammar.getGrammarDescription(), dTDGrammar);
    }

    public void setActiveGrammar(DTDGrammar dTDGrammar) {
        this.fActiveGrammar = dTDGrammar;
    }

    public void setStandalone(boolean z) {
        this.fIsStandalone = z;
    }
}
