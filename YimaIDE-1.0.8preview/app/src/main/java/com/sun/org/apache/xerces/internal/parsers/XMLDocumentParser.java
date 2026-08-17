package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLDocumentParser extends AbstractXMLDocumentParser {
    public XMLDocumentParser(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool) {
        super(new XIncludeAwareParserConfiguration());
        this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
        this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", xMLGrammarPool);
    }

    public XMLDocumentParser(XMLParserConfiguration xMLParserConfiguration) {
        super(xMLParserConfiguration);
    }

    public XMLDocumentParser(SymbolTable symbolTable) {
        super(new XIncludeAwareParserConfiguration());
        this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
    }

    public XMLDocumentParser() {
        super(new XIncludeAwareParserConfiguration());
    }
}
