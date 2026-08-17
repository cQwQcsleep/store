package com.sun.org.apache.xalan.internal.xsltc.trax;

import com.sun.org.apache.xalan.internal.xsltc.compiler.CompilerException;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;
import com.sun.org.apache.xalan.internal.xsltc.compiler.SourceLoader;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Stylesheet;
import com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode;
import com.sun.org.apache.xalan.internal.xsltc.compiler.XSLTC;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.util.ArrayList;
import javax.xml.XMLConstants;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.TemplatesHandler;
import jdk.xml.internal.JdkXmlFeatures;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TemplatesHandlerImpl implements ContentHandler, TemplatesHandler, SourceLoader {
    CatalogFeatures _catalogFeatures;
    private int _indentNumber;
    private Parser _parser;
    private String _systemId;
    private TransformerFactoryImpl _tfactory;
    boolean _useCatalog;
    private URIResolver _uriResolver = null;
    private TemplatesImpl _templates = null;

    public TemplatesHandlerImpl(int i, TransformerFactoryImpl transformerFactoryImpl, boolean z) {
        this._parser = null;
        this._useCatalog = true;
        this._indentNumber = i;
        this._tfactory = transformerFactoryImpl;
        XSLTC xsltc = new XSLTC(transformerFactoryImpl.getJdkXmlFeatures(), z);
        if (transformerFactoryImpl.getFeature("http://javax.xml.XMLConstants/feature/secure-processing")) {
            xsltc.setSecureProcessing(true);
        }
        xsltc.setProperty(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, (String) transformerFactoryImpl.getAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET));
        xsltc.setProperty("http://javax.xml.XMLConstants/property/accessExternalDTD", (String) transformerFactoryImpl.getAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD"));
        xsltc.setProperty("http://apache.org/xml/properties/security-manager", transformerFactoryImpl.getAttribute("http://apache.org/xml/properties/security-manager"));
        if ("true".equals(transformerFactoryImpl.getAttribute(TransformerFactoryImpl.ENABLE_INLINING))) {
            xsltc.setTemplateInlining(true);
        } else {
            xsltc.setTemplateInlining(false);
        }
        this._useCatalog = transformerFactoryImpl.getFeature("http://javax.xml.XMLConstants/feature/useCatalog");
        CatalogFeatures catalogFeatures = (CatalogFeatures) transformerFactoryImpl.getAttribute(JdkXmlFeatures.CATALOG_FEATURES);
        this._catalogFeatures = catalogFeatures;
        xsltc.setProperty(JdkXmlFeatures.CATALOG_FEATURES, catalogFeatures);
        this._parser = xsltc.getParser();
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) {
        this._parser.characters(cArr, i, i2);
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        this._parser.endDocument();
        try {
            XSLTC xsltc = this._parser.getXSLTC();
            String str = this._systemId;
            xsltc.setClassName(str != null ? Util.baseName(str) : (String) this._tfactory.getAttribute(TransformerFactoryImpl.TRANSLET_NAME));
            String className = xsltc.getClassName();
            SyntaxTreeNode documentRoot = this._parser.getDocumentRoot();
            Stylesheet stylesheet = null;
            if (!this._parser.errorsFound() && documentRoot != null) {
                Stylesheet stylesheetMakeStylesheet = this._parser.makeStylesheet(documentRoot);
                stylesheetMakeStylesheet.setSystemId(this._systemId);
                stylesheetMakeStylesheet.setParentStylesheet(null);
                if (xsltc.getTemplateInlining()) {
                    stylesheetMakeStylesheet.setTemplateInlining(true);
                } else {
                    stylesheetMakeStylesheet.setTemplateInlining(false);
                }
                if (this._uriResolver != null || (this._useCatalog && this._catalogFeatures.get(CatalogFeatures.Feature.FILES) != null)) {
                    stylesheetMakeStylesheet.setSourceLoader(this);
                }
                this._parser.setCurrentStylesheet(stylesheetMakeStylesheet);
                xsltc.setStylesheet(stylesheetMakeStylesheet);
                this._parser.createAST(stylesheetMakeStylesheet);
                stylesheet = stylesheetMakeStylesheet;
            }
            if (!this._parser.errorsFound() && stylesheet != null) {
                stylesheet.setMultiDocument(xsltc.isMultiDocument());
                stylesheet.setHasIdCall(xsltc.hasIdCall());
                synchronized (XSLTC.class) {
                    stylesheet.translate();
                }
            }
            if (this._parser.errorsFound()) {
                StringBuilder sb = new StringBuilder();
                ArrayList<ErrorMsg> errors = this._parser.getErrors();
                int size = errors.size();
                for (int i = 0; i < size; i++) {
                    if (sb.length() > 0) {
                        sb.append('\n');
                    }
                    sb.append(errors.get(i).toString());
                }
                throw new SAXException(ErrorMsg.JAXP_COMPILE_ERR, new TransformerException(sb.toString()));
            }
            if (xsltc.getBytecodes() != null) {
                TemplatesImpl templatesImpl = new TemplatesImpl(xsltc.getBytecodes(), className, this._parser.getOutputProperties(), this._indentNumber, this._tfactory);
                this._templates = templatesImpl;
                URIResolver uRIResolver = this._uriResolver;
                if (uRIResolver != null) {
                    templatesImpl.setURIResolver(uRIResolver);
                }
            }
        } catch (CompilerException e) {
            throw new SAXException(ErrorMsg.JAXP_COMPILE_ERR, e);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) {
        this._parser.endElement(str, str2, str3);
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) {
        this._parser.endPrefixMapping(str);
    }

    @Override // javax.xml.transform.sax.TemplatesHandler
    public String getSystemId() {
        return this._systemId;
    }

    @Override // javax.xml.transform.sax.TemplatesHandler
    public Templates getTemplates() {
        return this._templates;
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) {
        this._parser.ignorableWhitespace(cArr, i, i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SourceLoader
    public InputSource loadSource(String str, String str2, XSLTC xsltc) {
        try {
            Source sourceResolve = this._uriResolver.resolve(str, str2);
            if (sourceResolve != null) {
                return Util.getInputSource(xsltc, sourceResolve);
            }
            return null;
        } catch (TransformerException unused) {
            return null;
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) {
        this._parser.processingInstruction(str, str2);
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        setSystemId(locator.getSystemId());
        this._parser.setDocumentLocator(locator);
    }

    @Override // javax.xml.transform.sax.TemplatesHandler
    public void setSystemId(String str) {
        this._systemId = str;
    }

    public void setURIResolver(URIResolver uRIResolver) {
        this._uriResolver = uRIResolver;
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) {
        this._parser.skippedEntity(str);
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() {
        XSLTC xsltc = this._parser.getXSLTC();
        xsltc.init();
        xsltc.setOutputType(2);
        this._parser.startDocument();
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        this._parser.startElement(str, str2, str3, attributes);
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) {
        this._parser.startPrefixMapping(str, str2);
    }
}
