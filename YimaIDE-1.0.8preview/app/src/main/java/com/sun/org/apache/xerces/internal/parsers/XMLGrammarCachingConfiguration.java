package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.impl.dtd.DTDGrammar;
import com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDLoader;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaLoader;
import com.sun.org.apache.xerces.internal.impl.xs.XSMessageFormatter;
import com.sun.org.apache.xerces.internal.jaxp.JAXPConstants;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLGrammarPoolImpl;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.grammars.Grammar;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLGrammarCachingConfiguration extends XIncludeAwareParserConfiguration {
    protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected XMLDTDLoader fDTDLoader;
    protected XMLSchemaLoader fSchemaLoader;
    public static final int BIG_PRIME = 2039;
    protected static final SynchronizedSymbolTable fStaticSymbolTable = new SynchronizedSymbolTable(BIG_PRIME);
    protected static final XMLGrammarPoolImpl fStaticGrammarPool = new XMLGrammarPoolImpl();

    public XMLGrammarCachingConfiguration(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool, XMLComponentManager xMLComponentManager) {
        super(symbolTable, xMLGrammarPool, xMLComponentManager);
        XMLSchemaLoader xMLSchemaLoader = new XMLSchemaLoader(this.fSymbolTable);
        this.fSchemaLoader = xMLSchemaLoader;
        xMLSchemaLoader.setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fGrammarPool);
        this.fDTDLoader = new XMLDTDLoader(this.fSymbolTable, this.fGrammarPool);
    }

    public void clearGrammarPool() {
        this.fGrammarPool.clear();
    }

    public void lockGrammarPool() {
        this.fGrammarPool.lockPool();
    }

    public DTDGrammar parseDTD(XMLInputSource xMLInputSource) throws IOException {
        XMLEntityResolver entityResolver = getEntityResolver();
        if (entityResolver != null) {
            this.fDTDLoader.setEntityResolver(entityResolver);
        }
        this.fDTDLoader.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
        DTDGrammar dTDGrammar = (DTDGrammar) this.fDTDLoader.loadGrammar(xMLInputSource);
        if (dTDGrammar != null) {
            this.fGrammarPool.cacheGrammars("http://www.w3.org/TR/REC-xml", new Grammar[]{dTDGrammar});
        }
        return dTDGrammar;
    }

    public Grammar parseGrammar(String str, XMLInputSource xMLInputSource) throws IOException, XNIException {
        if (str.equals("http://www.w3.org/2001/XMLSchema")) {
            return parseXMLSchema(xMLInputSource);
        }
        if (str.equals("http://www.w3.org/TR/REC-xml")) {
            return parseDTD(xMLInputSource);
        }
        return null;
    }

    public SchemaGrammar parseXMLSchema(XMLInputSource xMLInputSource) throws IOException {
        XMLEntityResolver entityResolver = getEntityResolver();
        if (entityResolver != null) {
            this.fSchemaLoader.setEntityResolver(entityResolver);
        }
        if (this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN) == null) {
            this.fErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
        }
        this.fSchemaLoader.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
        this.fSchemaLoader.setProperty("http://apache.org/xml/properties/schema/external-schemaLocation", getProperty("http://apache.org/xml/properties/schema/external-schemaLocation"));
        this.fSchemaLoader.setProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", getProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation"));
        this.fSchemaLoader.setProperty(JAXPConstants.JAXP_SCHEMA_SOURCE, getProperty(JAXPConstants.JAXP_SCHEMA_SOURCE));
        this.fSchemaLoader.setFeature(SCHEMA_FULL_CHECKING, getFeature(SCHEMA_FULL_CHECKING));
        SchemaGrammar schemaGrammar = (SchemaGrammar) this.fSchemaLoader.loadGrammar(xMLInputSource);
        if (schemaGrammar != null) {
            this.fGrammarPool.cacheGrammars("http://www.w3.org/2001/XMLSchema", new Grammar[]{schemaGrammar});
        }
        return schemaGrammar;
    }

    public void unlockGrammarPool() {
        this.fGrammarPool.unlockPool();
    }

    public Grammar parseGrammar(String str, String str2) throws IOException, XNIException {
        return parseGrammar(str, new XMLInputSource(null, str2, null, false));
    }

    public XMLGrammarCachingConfiguration(SymbolTable symbolTable) {
        this(symbolTable, fStaticGrammarPool, null);
    }

    public XMLGrammarCachingConfiguration(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool) {
        this(symbolTable, xMLGrammarPool, null);
    }

    public XMLGrammarCachingConfiguration() {
        this(fStaticSymbolTable, fStaticGrammarPool, null);
    }
}
