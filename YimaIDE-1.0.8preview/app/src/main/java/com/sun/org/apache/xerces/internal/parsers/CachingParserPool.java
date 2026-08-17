package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.util.ShadowedSymbolTable;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLGrammarPoolImpl;
import com.sun.org.apache.xerces.internal.xni.grammars.Grammar;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarDescription;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CachingParserPool {
    public static final boolean DEFAULT_SHADOW_GRAMMAR_POOL = false;
    public static final boolean DEFAULT_SHADOW_SYMBOL_TABLE = false;
    protected boolean fShadowGrammarPool;
    protected boolean fShadowSymbolTable;
    protected XMLGrammarPool fSynchronizedGrammarPool;
    protected SymbolTable fSynchronizedSymbolTable;

    public static final class ShadowedGrammarPool extends XMLGrammarPoolImpl {
        private XMLGrammarPool fGrammarPool;

        public ShadowedGrammarPool(XMLGrammarPool xMLGrammarPool) {
            this.fGrammarPool = xMLGrammarPool;
        }

        @Override // com.sun.org.apache.xerces.internal.util.XMLGrammarPoolImpl, com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public void cacheGrammars(String str, Grammar[] grammarArr) {
            super.cacheGrammars(str, grammarArr);
            this.fGrammarPool.cacheGrammars(str, grammarArr);
        }

        @Override // com.sun.org.apache.xerces.internal.util.XMLGrammarPoolImpl
        public boolean containsGrammar(XMLGrammarDescription xMLGrammarDescription) {
            return super.containsGrammar(xMLGrammarDescription);
        }

        @Override // com.sun.org.apache.xerces.internal.util.XMLGrammarPoolImpl
        public Grammar getGrammar(XMLGrammarDescription xMLGrammarDescription) {
            if (super.containsGrammar(xMLGrammarDescription)) {
                return super.getGrammar(xMLGrammarDescription);
            }
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.util.XMLGrammarPoolImpl, com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public Grammar retrieveGrammar(XMLGrammarDescription xMLGrammarDescription) {
            Grammar grammarRetrieveGrammar = super.retrieveGrammar(xMLGrammarDescription);
            return grammarRetrieveGrammar != null ? grammarRetrieveGrammar : this.fGrammarPool.retrieveGrammar(xMLGrammarDescription);
        }

        @Override // com.sun.org.apache.xerces.internal.util.XMLGrammarPoolImpl, com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public Grammar[] retrieveInitialGrammarSet(String str) {
            Grammar[] grammarArrRetrieveInitialGrammarSet = super.retrieveInitialGrammarSet(str);
            return grammarArrRetrieveInitialGrammarSet != null ? grammarArrRetrieveInitialGrammarSet : this.fGrammarPool.retrieveInitialGrammarSet(str);
        }
    }

    public static final class SynchronizedGrammarPool implements XMLGrammarPool {
        private XMLGrammarPool fGrammarPool;

        public SynchronizedGrammarPool(XMLGrammarPool xMLGrammarPool) {
            this.fGrammarPool = xMLGrammarPool;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public void cacheGrammars(String str, Grammar[] grammarArr) {
            synchronized (this.fGrammarPool) {
                this.fGrammarPool.cacheGrammars(str, grammarArr);
            }
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public void clear() {
            synchronized (this.fGrammarPool) {
                this.fGrammarPool.clear();
            }
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public void lockPool() {
            synchronized (this.fGrammarPool) {
                this.fGrammarPool.lockPool();
            }
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public Grammar retrieveGrammar(XMLGrammarDescription xMLGrammarDescription) {
            Grammar grammarRetrieveGrammar;
            synchronized (this.fGrammarPool) {
                grammarRetrieveGrammar = this.fGrammarPool.retrieveGrammar(xMLGrammarDescription);
            }
            return grammarRetrieveGrammar;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public Grammar[] retrieveInitialGrammarSet(String str) {
            Grammar[] grammarArrRetrieveInitialGrammarSet;
            synchronized (this.fGrammarPool) {
                grammarArrRetrieveInitialGrammarSet = this.fGrammarPool.retrieveInitialGrammarSet(str);
            }
            return grammarArrRetrieveInitialGrammarSet;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public void unlockPool() {
            synchronized (this.fGrammarPool) {
                this.fGrammarPool.unlockPool();
            }
        }
    }

    public CachingParserPool(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool) {
        this.fShadowSymbolTable = false;
        this.fShadowGrammarPool = false;
        this.fSynchronizedSymbolTable = new SynchronizedSymbolTable(symbolTable);
        this.fSynchronizedGrammarPool = new SynchronizedGrammarPool(xMLGrammarPool);
    }

    public DOMParser createDOMParser() {
        boolean z = this.fShadowSymbolTable;
        SymbolTable shadowedSymbolTable = this.fSynchronizedSymbolTable;
        if (z) {
            shadowedSymbolTable = new ShadowedSymbolTable(shadowedSymbolTable);
        }
        boolean z2 = this.fShadowGrammarPool;
        XMLGrammarPool shadowedGrammarPool = this.fSynchronizedGrammarPool;
        if (z2) {
            shadowedGrammarPool = new ShadowedGrammarPool(shadowedGrammarPool);
        }
        return new DOMParser(shadowedSymbolTable, shadowedGrammarPool);
    }

    public SAXParser createSAXParser() {
        boolean z = this.fShadowSymbolTable;
        SymbolTable shadowedSymbolTable = this.fSynchronizedSymbolTable;
        if (z) {
            shadowedSymbolTable = new ShadowedSymbolTable(shadowedSymbolTable);
        }
        boolean z2 = this.fShadowGrammarPool;
        XMLGrammarPool shadowedGrammarPool = this.fSynchronizedGrammarPool;
        if (z2) {
            shadowedGrammarPool = new ShadowedGrammarPool(shadowedGrammarPool);
        }
        return new SAXParser(shadowedSymbolTable, shadowedGrammarPool);
    }

    public SymbolTable getSymbolTable() {
        return this.fSynchronizedSymbolTable;
    }

    public XMLGrammarPool getXMLGrammarPool() {
        return this.fSynchronizedGrammarPool;
    }

    public void setShadowSymbolTable(boolean z) {
        this.fShadowSymbolTable = z;
    }

    public CachingParserPool() {
        this(new SymbolTable(), new XMLGrammarPoolImpl());
    }
}
