package com.sun.org.apache.xerces.internal.impl.dtd;

import com.sun.org.apache.xerces.internal.impl.XML11DTDScannerImpl;
import com.sun.org.apache.xerces.internal.impl.XMLDTDScannerImpl;
import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XML11Char;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XML11DTDProcessor extends XMLDTDLoader {
    public XML11DTDProcessor() {
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDLoader
    public XMLDTDScannerImpl createDTDScanner(SymbolTable symbolTable, XMLErrorReporter xMLErrorReporter, XMLEntityManager xMLEntityManager) {
        return new XML11DTDScannerImpl(symbolTable, xMLErrorReporter, xMLEntityManager);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDLoader
    public short getScannerVersion() {
        return (short) 2;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDProcessor
    public boolean isValidName(String str) {
        return XML11Char.isXML11ValidName(str);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDProcessor
    public boolean isValidNmtoken(String str) {
        return XML11Char.isXML11ValidNmtoken(str);
    }

    public XML11DTDProcessor(SymbolTable symbolTable) {
        super(symbolTable);
    }

    public XML11DTDProcessor(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool) {
        super(symbolTable, xMLGrammarPool);
    }

    public XML11DTDProcessor(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool, XMLErrorReporter xMLErrorReporter, XMLEntityResolver xMLEntityResolver) {
        super(symbolTable, xMLGrammarPool, xMLErrorReporter, xMLEntityResolver);
    }
}
