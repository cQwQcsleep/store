package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl;
import com.sun.org.apache.xerces.internal.impl.XMLNSDocumentScannerImpl;
import com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDValidator;
import com.sun.org.apache.xerces.internal.impl.dtd.XMLNSDTDValidator;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator;
import com.sun.org.apache.xerces.internal.impl.xs.XSMessageFormatter;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentScanner;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IntegratedParserConfiguration extends StandardParserConfiguration {
    protected XMLNSDocumentScannerImpl fNamespaceScanner;
    protected XMLDTDValidator fNonNSDTDValidator;
    protected XMLDocumentScannerImpl fNonNSScanner;

    public IntegratedParserConfiguration(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool, XMLComponentManager xMLComponentManager) {
        super(symbolTable, xMLGrammarPool, xMLComponentManager);
        this.fNonNSScanner = new XMLDocumentScannerImpl();
        this.fNonNSDTDValidator = new XMLDTDValidator();
        addComponent(this.fNonNSScanner);
        addComponent(this.fNonNSDTDValidator);
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.StandardParserConfiguration, com.sun.org.apache.xerces.internal.parsers.DTDConfiguration
    public void configurePipeline() {
        setProperty("http://apache.org/xml/properties/internal/datatype-validator-factory", this.fDatatypeValidatorFactory);
        configureDTDPipeline();
        Boolean bool = this.fFeatures.get("http://xml.org/sax/features/namespaces");
        Boolean bool2 = Boolean.TRUE;
        if (bool == bool2) {
            this.fProperties.put("http://apache.org/xml/properties/internal/namespace-binder", this.fNamespaceBinder);
            XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl = this.fNamespaceScanner;
            this.fScanner = xMLNSDocumentScannerImpl;
            this.fProperties.put("http://apache.org/xml/properties/internal/document-scanner", xMLNSDocumentScannerImpl);
            XMLDTDValidator xMLDTDValidator = this.fDTDValidator;
            if (xMLDTDValidator != null) {
                this.fProperties.put("http://apache.org/xml/properties/internal/validator/dtd", xMLDTDValidator);
                this.fNamespaceScanner.setDTDValidator(this.fDTDValidator);
                this.fNamespaceScanner.setDocumentHandler(this.fDTDValidator);
                this.fDTDValidator.setDocumentSource(this.fNamespaceScanner);
                this.fDTDValidator.setDocumentHandler(this.fDocumentHandler);
                XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
                if (xMLDocumentHandler != null) {
                    xMLDocumentHandler.setDocumentSource(this.fDTDValidator);
                }
                this.fLastComponent = this.fDTDValidator;
            } else {
                this.fNamespaceScanner.setDocumentHandler(this.fDocumentHandler);
                this.fNamespaceScanner.setDTDValidator(null);
                XMLDocumentHandler xMLDocumentHandler2 = this.fDocumentHandler;
                if (xMLDocumentHandler2 != null) {
                    xMLDocumentHandler2.setDocumentSource(this.fNamespaceScanner);
                }
                this.fLastComponent = this.fNamespaceScanner;
            }
        } else {
            XMLDocumentScannerImpl xMLDocumentScannerImpl = this.fNonNSScanner;
            this.fScanner = xMLDocumentScannerImpl;
            this.fProperties.put("http://apache.org/xml/properties/internal/document-scanner", xMLDocumentScannerImpl);
            XMLDTDValidator xMLDTDValidator2 = this.fNonNSDTDValidator;
            if (xMLDTDValidator2 != null) {
                this.fProperties.put("http://apache.org/xml/properties/internal/validator/dtd", xMLDTDValidator2);
                this.fNonNSScanner.setDocumentHandler(this.fNonNSDTDValidator);
                this.fNonNSDTDValidator.setDocumentSource(this.fNonNSScanner);
                this.fNonNSDTDValidator.setDocumentHandler(this.fDocumentHandler);
                XMLDocumentHandler xMLDocumentHandler3 = this.fDocumentHandler;
                if (xMLDocumentHandler3 != null) {
                    xMLDocumentHandler3.setDocumentSource(this.fNonNSDTDValidator);
                }
                this.fLastComponent = this.fNonNSDTDValidator;
            } else {
                this.fScanner.setDocumentHandler(this.fDocumentHandler);
                XMLDocumentHandler xMLDocumentHandler4 = this.fDocumentHandler;
                if (xMLDocumentHandler4 != null) {
                    xMLDocumentHandler4.setDocumentSource(this.fScanner);
                }
                this.fLastComponent = this.fScanner;
            }
        }
        if (this.fFeatures.get("http://apache.org/xml/features/validation/schema") == bool2) {
            if (this.fSchemaValidator == null) {
                XMLSchemaValidator xMLSchemaValidator = new XMLSchemaValidator();
                this.fSchemaValidator = xMLSchemaValidator;
                this.fProperties.put("http://apache.org/xml/properties/internal/validator/schema", xMLSchemaValidator);
                addComponent(this.fSchemaValidator);
                if (this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN) == null) {
                    this.fErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
                }
            }
            this.fLastComponent.setDocumentHandler(this.fSchemaValidator);
            this.fSchemaValidator.setDocumentSource(this.fLastComponent);
            this.fSchemaValidator.setDocumentHandler(this.fDocumentHandler);
            XMLDocumentHandler xMLDocumentHandler5 = this.fDocumentHandler;
            if (xMLDocumentHandler5 != null) {
                xMLDocumentHandler5.setDocumentSource(this.fSchemaValidator);
            }
            this.fLastComponent = this.fSchemaValidator;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.DTDConfiguration
    public XMLDTDValidator createDTDValidator() {
        return new XMLNSDTDValidator();
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.DTDConfiguration
    public XMLDocumentScanner createDocumentScanner() {
        XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl = new XMLNSDocumentScannerImpl();
        this.fNamespaceScanner = xMLNSDocumentScannerImpl;
        return xMLNSDocumentScannerImpl;
    }

    public IntegratedParserConfiguration(SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }

    public IntegratedParserConfiguration(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool) {
        this(symbolTable, xMLGrammarPool, null);
    }

    public IntegratedParserConfiguration() {
        this(null, null, null);
    }
}
