package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.utils.ObjectFactory;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.grammars.Grammar;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarLoader;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLGrammarPreparser {
    private static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String GRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private static final Map<String, String> KNOWN_LOADERS;
    private static final String[] RECOGNIZED_PROPERTIES;
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected XMLEntityResolver fEntityResolver;
    protected XMLErrorReporter fErrorReporter;
    protected XMLGrammarPool fGrammarPool;
    private Map<String, XMLGrammarLoader> fLoaders;
    protected Locale fLocale;
    protected SymbolTable fSymbolTable;

    static {
        HashMap map = new HashMap();
        map.put("http://www.w3.org/2001/XMLSchema", "com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaLoader");
        map.put("http://www.w3.org/TR/REC-xml", "com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDLoader");
        KNOWN_LOADERS = Collections.unmodifiableMap(map);
        RECOGNIZED_PROPERTIES = new String[]{"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/grammar-pool"};
    }

    public XMLGrammarPreparser(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
        this.fLoaders = new HashMap();
        this.fErrorReporter = new XMLErrorReporter();
        setLocale(Locale.getDefault());
        this.fEntityResolver = new XMLEntityManager();
    }

    public XMLEntityResolver getEntityResolver() {
        return this.fEntityResolver;
    }

    public XMLErrorHandler getErrorHandler() {
        return this.fErrorReporter.getErrorHandler();
    }

    public boolean getFeature(String str, String str2) {
        return this.fLoaders.get(str).getFeature(str2);
    }

    public XMLGrammarPool getGrammarPool() {
        return this.fGrammarPool;
    }

    public XMLGrammarLoader getLoader(String str) {
        return this.fLoaders.get(str);
    }

    public Locale getLocale() {
        return this.fLocale;
    }

    public Object getProperty(String str, String str2) {
        return this.fLoaders.get(str).getProperty(str2);
    }

    public Grammar preparseGrammar(String str, XMLInputSource xMLInputSource) throws IOException, XNIException {
        if (!this.fLoaders.containsKey(str)) {
            return null;
        }
        XMLGrammarLoader xMLGrammarLoader = this.fLoaders.get(str);
        xMLGrammarLoader.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
        xMLGrammarLoader.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fEntityResolver);
        xMLGrammarLoader.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
        XMLGrammarPool xMLGrammarPool = this.fGrammarPool;
        if (xMLGrammarPool != null) {
            try {
                xMLGrammarLoader.setProperty("http://apache.org/xml/properties/internal/grammar-pool", xMLGrammarPool);
            } catch (Exception unused) {
            }
        }
        return xMLGrammarLoader.loadGrammar(xMLInputSource);
    }

    public boolean registerPreparser(String str, XMLGrammarLoader xMLGrammarLoader) {
        if (xMLGrammarLoader != null) {
            this.fLoaders.put(str, xMLGrammarLoader);
            return true;
        }
        Map<String, String> map = KNOWN_LOADERS;
        if (map.containsKey(str)) {
            try {
                this.fLoaders.put(str, (XMLGrammarLoader) ObjectFactory.newInstance(map.get(str), true));
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public void setEntityResolver(XMLEntityResolver xMLEntityResolver) {
        this.fEntityResolver = xMLEntityResolver;
    }

    public void setErrorHandler(XMLErrorHandler xMLErrorHandler) {
        this.fErrorReporter.setProperty("http://apache.org/xml/properties/internal/error-handler", xMLErrorHandler);
    }

    public void setFeature(String str, boolean z) {
        Iterator<Map.Entry<String, XMLGrammarLoader>> it = this.fLoaders.entrySet().iterator();
        while (it.hasNext()) {
            try {
                it.next().getValue().setFeature(str, z);
            } catch (Exception unused) {
            }
        }
        if (str.equals(CONTINUE_AFTER_FATAL_ERROR)) {
            this.fErrorReporter.setFeature(CONTINUE_AFTER_FATAL_ERROR, z);
        }
    }

    public void setGrammarPool(XMLGrammarPool xMLGrammarPool) {
        this.fGrammarPool = xMLGrammarPool;
    }

    public void setLocale(Locale locale) {
        this.fLocale = locale;
        this.fErrorReporter.setLocale(locale);
    }

    public void setProperty(String str, Object obj) {
        Iterator<Map.Entry<String, XMLGrammarLoader>> it = this.fLoaders.entrySet().iterator();
        while (it.hasNext()) {
            try {
                it.next().getValue().setProperty(str, obj);
            } catch (Exception unused) {
            }
        }
    }

    public XMLGrammarPreparser() {
        this(new SymbolTable());
    }
}
