package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.util.FeatureState;
import com.sun.org.apache.xerces.internal.util.NamespaceSupport;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler;
import com.sun.org.apache.xerces.internal.xinclude.XIncludeNamespaceSupport;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.XMLDTDHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XIncludeAwareParserConfiguration extends XML11Configuration {
    protected static final String ALLOW_UE_AND_NOTATION_EVENTS = "http://xml.org/sax/features/allow-dtd-events-after-endDTD";
    protected static final String NAMESPACE_CONTEXT = "http://apache.org/xml/properties/internal/namespace-context";
    protected static final String XINCLUDE_FEATURE = "http://apache.org/xml/features/xinclude";
    protected static final String XINCLUDE_FIXUP_BASE_URIS = "http://apache.org/xml/features/xinclude/fixup-base-uris";
    protected static final String XINCLUDE_FIXUP_LANGUAGE = "http://apache.org/xml/features/xinclude/fixup-language";
    protected static final String XINCLUDE_HANDLER = "http://apache.org/xml/properties/internal/xinclude-handler";
    protected NamespaceContext fCurrentNSContext;
    protected NamespaceSupport fNonXIncludeNSContext;
    protected boolean fXIncludeEnabled;
    protected XIncludeHandler fXIncludeHandler;
    protected XIncludeNamespaceSupport fXIncludeNSContext;

    public XIncludeAwareParserConfiguration(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool, XMLComponentManager xMLComponentManager) {
        super(symbolTable, xMLGrammarPool, xMLComponentManager);
        this.fXIncludeEnabled = false;
        addRecognizedFeatures(new String[]{ALLOW_UE_AND_NOTATION_EVENTS, XINCLUDE_FIXUP_BASE_URIS, XINCLUDE_FIXUP_LANGUAGE});
        addRecognizedProperties(new String[]{XINCLUDE_HANDLER, NAMESPACE_CONTEXT});
        setFeature(ALLOW_UE_AND_NOTATION_EVENTS, true);
        setFeature(XINCLUDE_FIXUP_BASE_URIS, true);
        setFeature(XINCLUDE_FIXUP_LANGUAGE, true);
        NamespaceSupport namespaceSupport = new NamespaceSupport();
        this.fNonXIncludeNSContext = namespaceSupport;
        this.fCurrentNSContext = namespaceSupport;
        setProperty(NAMESPACE_CONTEXT, namespaceSupport);
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.XML11Configuration
    public void configurePipeline() {
        XMLDocumentSource documentSource;
        super.configurePipeline();
        if (!this.fXIncludeEnabled) {
            NamespaceContext namespaceContext = this.fCurrentNSContext;
            NamespaceSupport namespaceSupport = this.fNonXIncludeNSContext;
            if (namespaceContext != namespaceSupport) {
                this.fCurrentNSContext = namespaceSupport;
                setProperty(NAMESPACE_CONTEXT, namespaceSupport);
                return;
            }
            return;
        }
        if (this.fXIncludeHandler == null) {
            XIncludeHandler xIncludeHandler = new XIncludeHandler();
            this.fXIncludeHandler = xIncludeHandler;
            setProperty(XINCLUDE_HANDLER, xIncludeHandler);
            addCommonComponent(this.fXIncludeHandler);
            this.fXIncludeHandler.reset(this);
        }
        NamespaceContext namespaceContext2 = this.fCurrentNSContext;
        XIncludeNamespaceSupport xIncludeNamespaceSupport = this.fXIncludeNSContext;
        if (namespaceContext2 != xIncludeNamespaceSupport) {
            if (xIncludeNamespaceSupport == null) {
                this.fXIncludeNSContext = new XIncludeNamespaceSupport();
            }
            XIncludeNamespaceSupport xIncludeNamespaceSupport2 = this.fXIncludeNSContext;
            this.fCurrentNSContext = xIncludeNamespaceSupport2;
            setProperty(NAMESPACE_CONTEXT, xIncludeNamespaceSupport2);
        }
        this.fDTDScanner.setDTDHandler(this.fDTDProcessor);
        this.fDTDProcessor.setDTDSource(this.fDTDScanner);
        this.fDTDProcessor.setDTDHandler(this.fXIncludeHandler);
        this.fXIncludeHandler.setDTDSource(this.fDTDProcessor);
        this.fXIncludeHandler.setDTDHandler(this.fDTDHandler);
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.setDTDSource(this.fXIncludeHandler);
        }
        if (this.fFeatures.get("http://apache.org/xml/features/validation/schema") == Boolean.TRUE) {
            documentSource = this.fSchemaValidator.getDocumentSource();
        } else {
            documentSource = this.fLastComponent;
            this.fLastComponent = this.fXIncludeHandler;
        }
        XMLDocumentHandler documentHandler = documentSource.getDocumentHandler();
        documentSource.setDocumentHandler(this.fXIncludeHandler);
        this.fXIncludeHandler.setDocumentSource(documentSource);
        if (documentHandler != null) {
            this.fXIncludeHandler.setDocumentHandler(documentHandler);
            documentHandler.setDocumentSource(this.fXIncludeHandler);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.XML11Configuration
    public void configureXML11Pipeline() {
        XMLDocumentSource documentSource;
        super.configureXML11Pipeline();
        if (!this.fXIncludeEnabled) {
            NamespaceContext namespaceContext = this.fCurrentNSContext;
            NamespaceSupport namespaceSupport = this.fNonXIncludeNSContext;
            if (namespaceContext != namespaceSupport) {
                this.fCurrentNSContext = namespaceSupport;
                setProperty(NAMESPACE_CONTEXT, namespaceSupport);
                return;
            }
            return;
        }
        if (this.fXIncludeHandler == null) {
            XIncludeHandler xIncludeHandler = new XIncludeHandler();
            this.fXIncludeHandler = xIncludeHandler;
            setProperty(XINCLUDE_HANDLER, xIncludeHandler);
            addCommonComponent(this.fXIncludeHandler);
            this.fXIncludeHandler.reset(this);
        }
        NamespaceContext namespaceContext2 = this.fCurrentNSContext;
        XIncludeNamespaceSupport xIncludeNamespaceSupport = this.fXIncludeNSContext;
        if (namespaceContext2 != xIncludeNamespaceSupport) {
            if (xIncludeNamespaceSupport == null) {
                this.fXIncludeNSContext = new XIncludeNamespaceSupport();
            }
            XIncludeNamespaceSupport xIncludeNamespaceSupport2 = this.fXIncludeNSContext;
            this.fCurrentNSContext = xIncludeNamespaceSupport2;
            setProperty(NAMESPACE_CONTEXT, xIncludeNamespaceSupport2);
        }
        this.fXML11DTDScanner.setDTDHandler(this.fXML11DTDProcessor);
        this.fXML11DTDProcessor.setDTDSource(this.fXML11DTDScanner);
        this.fXML11DTDProcessor.setDTDHandler(this.fXIncludeHandler);
        this.fXIncludeHandler.setDTDSource(this.fXML11DTDProcessor);
        this.fXIncludeHandler.setDTDHandler(this.fDTDHandler);
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.setDTDSource(this.fXIncludeHandler);
        }
        if (this.fFeatures.get("http://apache.org/xml/features/validation/schema") == Boolean.TRUE) {
            documentSource = this.fSchemaValidator.getDocumentSource();
        } else {
            documentSource = this.fLastComponent;
            this.fLastComponent = this.fXIncludeHandler;
        }
        XMLDocumentHandler documentHandler = documentSource.getDocumentHandler();
        documentSource.setDocumentHandler(this.fXIncludeHandler);
        this.fXIncludeHandler.setDocumentSource(documentSource);
        if (documentHandler != null) {
            this.fXIncludeHandler.setDocumentHandler(documentHandler);
            documentHandler.setDocumentSource(this.fXIncludeHandler);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.XML11Configuration, com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    public FeatureState getFeatureState(String str) throws XMLConfigurationException {
        if (str.equals("http://apache.org/xml/features/internal/parser-settings")) {
            return FeatureState.is(this.fConfigUpdated);
        }
        return str.equals(XINCLUDE_FEATURE) ? FeatureState.is(this.fXIncludeEnabled) : super.getFeatureState0(str);
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.XML11Configuration, com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setFeature(String str, boolean z) throws XMLConfigurationException {
        if (!str.equals(XINCLUDE_FEATURE)) {
            super.setFeature(str, z);
        } else {
            this.fXIncludeEnabled = z;
            this.fConfigUpdated = true;
        }
    }

    public XIncludeAwareParserConfiguration(SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }

    public XIncludeAwareParserConfiguration(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool) {
        this(symbolTable, xMLGrammarPool, null);
    }

    public XIncludeAwareParserConfiguration() {
        this(null, null, null);
    }
}
