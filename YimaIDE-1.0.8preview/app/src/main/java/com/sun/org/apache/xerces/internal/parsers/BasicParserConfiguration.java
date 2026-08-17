package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.util.FeatureState;
import com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings;
import com.sun.org.apache.xerces.internal.util.PropertyState;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDTDHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class BasicParserConfiguration extends ParserConfigurationSettings implements XMLParserConfiguration {
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
    protected static final String EXTERNAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String XML_STRING = "http://xml.org/sax/properties/xml-string";
    protected List<XMLComponent> fComponents;
    protected XMLDTDContentModelHandler fDTDContentModelHandler;
    protected XMLDTDHandler fDTDHandler;
    protected XMLDocumentHandler fDocumentHandler;
    protected XMLDocumentSource fLastComponent;
    protected Locale fLocale;
    protected SymbolTable fSymbolTable;

    public BasicParserConfiguration(SymbolTable symbolTable, XMLComponentManager xMLComponentManager) {
        super(xMLComponentManager);
        this.fComponents = new ArrayList();
        this.fFeatures = new HashMap();
        this.fProperties = new HashMap();
        addRecognizedFeatures(new String[]{"http://apache.org/xml/features/internal/parser-settings", VALIDATION, "http://xml.org/sax/features/namespaces", EXTERNAL_GENERAL_ENTITIES, EXTERNAL_PARAMETER_ENTITIES});
        Map<String, Boolean> map = this.fFeatures;
        Boolean bool = Boolean.TRUE;
        map.put("http://apache.org/xml/features/internal/parser-settings", bool);
        this.fFeatures.put(VALIDATION, Boolean.FALSE);
        this.fFeatures.put("http://xml.org/sax/features/namespaces", bool);
        this.fFeatures.put(EXTERNAL_GENERAL_ENTITIES, bool);
        this.fFeatures.put(EXTERNAL_PARAMETER_ENTITIES, bool);
        addRecognizedProperties(new String[]{XML_STRING, "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver"});
        symbolTable = symbolTable == null ? new SymbolTable() : symbolTable;
        this.fSymbolTable = symbolTable;
        this.fProperties.put("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
    }

    public void addComponent(XMLComponent xMLComponent) {
        if (this.fComponents.contains(xMLComponent)) {
            return;
        }
        this.fComponents.add(xMLComponent);
        String[] recognizedFeatures = xMLComponent.getRecognizedFeatures();
        addRecognizedFeatures(recognizedFeatures);
        String[] recognizedProperties = xMLComponent.getRecognizedProperties();
        addRecognizedProperties(recognizedProperties);
        if (recognizedFeatures != null) {
            for (String str : recognizedFeatures) {
                Boolean featureDefault = xMLComponent.getFeatureDefault(str);
                if (featureDefault != null) {
                    super.setFeature(str, featureDefault.booleanValue());
                }
            }
        }
        if (recognizedProperties != null) {
            for (String str2 : recognizedProperties) {
                Object propertyDefault = xMLComponent.getPropertyDefault(str2);
                if (propertyDefault != null) {
                    super.setProperty(str2, propertyDefault);
                }
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings
    public FeatureState checkFeature(String str) throws XMLConfigurationException {
        return (str.startsWith(Constants.XERCES_FEATURE_PREFIX) && str.length() + (-31) == 24 && str.endsWith(Constants.PARSER_SETTINGS)) ? FeatureState.NOT_SUPPORTED : super.checkFeature(str);
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings
    public PropertyState checkProperty(String str) throws XMLConfigurationException {
        return (str.startsWith(Constants.SAX_PROPERTY_PREFIX) && str.length() + (-30) == 10 && str.endsWith(Constants.XML_STRING_PROPERTY)) ? PropertyState.NOT_SUPPORTED : super.checkProperty(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public XMLDTDContentModelHandler getDTDContentModelHandler() {
        return this.fDTDContentModelHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public XMLDTDHandler getDTDHandler() {
        return this.fDTDHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public XMLEntityResolver getEntityResolver() {
        return (XMLEntityResolver) this.fProperties.get("http://apache.org/xml/properties/internal/entity-resolver");
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public XMLErrorHandler getErrorHandler() {
        return (XMLErrorHandler) this.fProperties.get("http://apache.org/xml/properties/internal/error-handler");
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public Locale getLocale() {
        return this.fLocale;
    }

    public abstract void parse(XMLInputSource xMLInputSource) throws IOException, XNIException;

    public void reset() throws XNIException {
        Iterator<XMLComponent> it = this.fComponents.iterator();
        while (it.hasNext()) {
            it.next().reset(this);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setDTDContentModelHandler(XMLDTDContentModelHandler xMLDTDContentModelHandler) {
        this.fDTDContentModelHandler = xMLDTDContentModelHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setDTDHandler(XMLDTDHandler xMLDTDHandler) {
        this.fDTDHandler = xMLDTDHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setDocumentHandler(XMLDocumentHandler xMLDocumentHandler) {
        this.fDocumentHandler = xMLDocumentHandler;
        XMLDocumentSource xMLDocumentSource = this.fLastComponent;
        if (xMLDocumentSource != null) {
            xMLDocumentSource.setDocumentHandler(xMLDocumentHandler);
            XMLDocumentHandler xMLDocumentHandler2 = this.fDocumentHandler;
            if (xMLDocumentHandler2 != null) {
                xMLDocumentHandler2.setDocumentSource(this.fLastComponent);
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setEntityResolver(XMLEntityResolver xMLEntityResolver) {
        this.fProperties.put("http://apache.org/xml/properties/internal/entity-resolver", xMLEntityResolver);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setErrorHandler(XMLErrorHandler xMLErrorHandler) {
        this.fProperties.put("http://apache.org/xml/properties/internal/error-handler", xMLErrorHandler);
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setFeature(String str, boolean z) throws XMLConfigurationException {
        Iterator<XMLComponent> it = this.fComponents.iterator();
        while (it.hasNext()) {
            it.next().setFeature(str, z);
        }
        super.setFeature(str, z);
    }

    public void setLocale(Locale locale) throws XNIException {
        this.fLocale = locale;
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setProperty(String str, Object obj) throws XMLConfigurationException {
        Iterator<XMLComponent> it = this.fComponents.iterator();
        while (it.hasNext()) {
            it.next().setProperty(str, obj);
        }
        super.setProperty(str, obj);
    }

    public BasicParserConfiguration(SymbolTable symbolTable) {
        this(symbolTable, null);
    }

    public BasicParserConfiguration() {
        this(null, null);
    }
}
