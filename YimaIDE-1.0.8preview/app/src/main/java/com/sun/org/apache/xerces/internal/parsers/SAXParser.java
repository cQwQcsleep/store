package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkProperty;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SAXParser extends AbstractSAXParser {
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String NOTIFY_BUILTIN_REFS = "http://apache.org/xml/features/scanner/notify-builtin-refs";
    protected static final String REPORT_WHITESPACE = "http://java.sun.com/xml/schema/features/report-ignored-element-content-whitespace";
    private static final String[] RECOGNIZED_FEATURES = {NOTIFY_BUILTIN_REFS, REPORT_WHITESPACE};
    private static final String[] RECOGNIZED_PROPERTIES = {"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/grammar-pool"};

    public SAXParser(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool) {
        super(new XIncludeAwareParserConfiguration());
        this.fConfiguration.addRecognizedFeatures(RECOGNIZED_FEATURES);
        this.fConfiguration.setFeature(NOTIFY_BUILTIN_REFS, true);
        this.fConfiguration.addRecognizedProperties(RECOGNIZED_PROPERTIES);
        if (symbolTable != null) {
            this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
        }
        if (xMLGrammarPool != null) {
            this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", xMLGrammarPool);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser, org.xml.sax.XMLReader
    public void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str.equals("http://apache.org/xml/properties/security-manager")) {
            XMLSecurityManager xMLSecurityManagerConvert = XMLSecurityManager.convert(obj, this.securityManager);
            this.securityManager = xMLSecurityManagerConvert;
            super.setProperty("http://apache.org/xml/properties/security-manager", xMLSecurityManagerConvert);
            return;
        }
        if (str.equals(JdkConstants.XML_SECURITY_PROPERTY_MANAGER)) {
            if (obj == null) {
                this.securityPropertyManager = new XMLSecurityPropertyManager();
            } else {
                this.securityPropertyManager = (XMLSecurityPropertyManager) obj;
            }
            super.setProperty(JdkConstants.XML_SECURITY_PROPERTY_MANAGER, this.securityPropertyManager);
            return;
        }
        if (this.securityManager == null) {
            XMLSecurityManager xMLSecurityManager = new XMLSecurityManager(true);
            this.securityManager = xMLSecurityManager;
            super.setProperty("http://apache.org/xml/properties/security-manager", xMLSecurityManager);
        }
        if (this.securityPropertyManager == null) {
            XMLSecurityPropertyManager xMLSecurityPropertyManager = new XMLSecurityPropertyManager();
            this.securityPropertyManager = xMLSecurityPropertyManager;
            super.setProperty(JdkConstants.XML_SECURITY_PROPERTY_MANAGER, xMLSecurityPropertyManager);
        }
        int index = this.securityPropertyManager.getIndex(str);
        if (index > -1) {
            this.securityPropertyManager.setValue(index, XMLSecurityPropertyManager.State.APIPROPERTY, (String) obj);
        } else {
            if (this.securityManager.setLimit(str, JdkProperty.State.APIPROPERTY, obj)) {
                return;
            }
            super.setProperty(str, obj);
        }
    }

    public SAXParser() {
        this(null, null);
    }

    public SAXParser(SymbolTable symbolTable) {
        this(symbolTable, null);
    }

    public SAXParser(XMLParserConfiguration xMLParserConfiguration) {
        super(xMLParserConfiguration);
    }
}
