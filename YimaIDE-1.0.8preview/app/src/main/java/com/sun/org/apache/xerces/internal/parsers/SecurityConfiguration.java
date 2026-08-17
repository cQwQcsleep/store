package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SecurityConfiguration extends XIncludeAwareParserConfiguration {
    protected static final String SECURITY_MANAGER_PROPERTY = "http://apache.org/xml/properties/security-manager";

    public SecurityConfiguration(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool, XMLComponentManager xMLComponentManager) {
        super(symbolTable, xMLGrammarPool, xMLComponentManager);
        setProperty("http://apache.org/xml/properties/security-manager", new XMLSecurityManager(true));
    }

    public SecurityConfiguration(SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }

    public SecurityConfiguration(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool) {
        this(symbolTable, xMLGrammarPool, null);
    }

    public SecurityConfiguration() {
        this(null, null, null);
    }
}
