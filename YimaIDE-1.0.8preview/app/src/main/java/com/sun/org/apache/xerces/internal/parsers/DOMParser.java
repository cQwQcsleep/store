package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaLoader;
import com.sun.org.apache.xerces.internal.util.EntityResolver2Wrapper;
import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;
import com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper;
import com.sun.org.apache.xerces.internal.util.SAXMessageFormatter;
import com.sun.org.apache.xerces.internal.util.Status;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import defpackage.x73;
import java.io.CharConversionException;
import java.io.IOException;
import jdk.xml.internal.JdkProperty;
import org.w3c.dom.Node;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.EntityResolver2;
import org.xml.sax.helpers.LocatorImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMParser extends AbstractDOMParser {
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String USE_ENTITY_RESOLVER2 = "http://xml.org/sax/features/use-entity-resolver2";
    protected static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private static final String XML_SECURITY_PROPERTY_MANAGER = "jdk.xml.xmlSecurityPropertyManager";
    protected boolean fUseEntityResolver2;
    protected static final String REPORT_WHITESPACE = "http://java.sun.com/xml/schema/features/report-ignored-element-content-whitespace";
    private static final String[] RECOGNIZED_FEATURES = {REPORT_WHITESPACE};
    private static final String[] RECOGNIZED_PROPERTIES = {"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/grammar-pool"};

    public DOMParser(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool) {
        super(new XIncludeAwareParserConfiguration());
        this.fUseEntityResolver2 = true;
        this.fConfiguration.addRecognizedProperties(RECOGNIZED_PROPERTIES);
        if (symbolTable != null) {
            this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
        }
        if (xMLGrammarPool != null) {
            this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", xMLGrammarPool);
        }
        this.fConfiguration.addRecognizedFeatures(RECOGNIZED_FEATURES);
    }

    public EntityResolver getEntityResolver() {
        try {
            XMLEntityResolver xMLEntityResolver = (XMLEntityResolver) this.fConfiguration.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
            if (xMLEntityResolver == null) {
                return null;
            }
            if (xMLEntityResolver instanceof EntityResolverWrapper) {
                return ((EntityResolverWrapper) xMLEntityResolver).getEntityResolver();
            }
            if (xMLEntityResolver instanceof EntityResolver2Wrapper) {
                return ((EntityResolver2Wrapper) xMLEntityResolver).getEntityResolver();
            }
            return null;
        } catch (XMLConfigurationException unused) {
            return null;
        }
    }

    public ErrorHandler getErrorHandler() {
        try {
            XMLErrorHandler xMLErrorHandler = (XMLErrorHandler) this.fConfiguration.getProperty(XMLSchemaLoader.ERROR_HANDLER);
            if (xMLErrorHandler == null || !(xMLErrorHandler instanceof ErrorHandlerWrapper)) {
                return null;
            }
            return ((ErrorHandlerWrapper) xMLErrorHandler).getErrorHandler();
        } catch (XMLConfigurationException unused) {
            return null;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.XMLParser, org.xml.sax.XMLReader
    public boolean getFeature(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            return str.equals(USE_ENTITY_RESOLVER2) ? this.fUseEntityResolver2 : this.fConfiguration.getFeature(str);
        } catch (XMLConfigurationException e) {
            String identifier = e.getIdentifier();
            if (e.getType() == Status.NOT_RECOGNIZED) {
                throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-not-recognized", new Object[]{identifier}));
            }
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-not-supported", new Object[]{identifier}));
        }
    }

    public Object getProperty(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        boolean feature;
        if (!str.equals("http://apache.org/xml/properties/dom/current-element-node")) {
            try {
                XMLSecurityPropertyManager xMLSecurityPropertyManager = (XMLSecurityPropertyManager) this.fConfiguration.getProperty("jdk.xml.xmlSecurityPropertyManager");
                int index = xMLSecurityPropertyManager.getIndex(str);
                return index > -1 ? xMLSecurityPropertyManager.getValueByIndex(index) : this.fConfiguration.getProperty(str);
            } catch (XMLConfigurationException e) {
                String identifier = e.getIdentifier();
                if (e.getType() == Status.NOT_RECOGNIZED) {
                    throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-recognized", new Object[]{identifier}));
                }
                throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-supported", new Object[]{identifier}));
            }
        }
        try {
            feature = getFeature("http://apache.org/xml/features/dom/defer-node-expansion");
        } catch (XMLConfigurationException unused) {
            feature = false;
        }
        if (feature) {
            throw new SAXNotSupportedException("Current element node cannot be queried when node expansion is deferred.");
        }
        Node node = this.fCurrentNode;
        if (node == null || node.getNodeType() != 1) {
            return null;
        }
        return this.fCurrentNode;
    }

    public XMLParserConfiguration getXMLParserConfiguration() {
        return this.fConfiguration;
    }

    public void parse(InputSource inputSource) throws SAXException, IOException {
        try {
            XMLInputSource xMLInputSource = new XMLInputSource(inputSource.getPublicId(), inputSource.getSystemId(), null, false);
            xMLInputSource.setByteStream(inputSource.getByteStream());
            xMLInputSource.setCharacterStream(inputSource.getCharacterStream());
            xMLInputSource.setEncoding(inputSource.getEncoding());
            parse(xMLInputSource);
        } catch (XMLParseException e) {
            Exception exception = e.getException();
            if (exception != null && !(exception instanceof CharConversionException)) {
                if (exception instanceof SAXException) {
                    throw ((SAXException) exception);
                }
                if (exception instanceof IOException) {
                    throw ((IOException) exception);
                }
                x73.a(exception);
                return;
            }
            LocatorImpl locatorImpl = new LocatorImpl();
            locatorImpl.setPublicId(e.getPublicId());
            locatorImpl.setSystemId(e.getExpandedSystemId());
            locatorImpl.setLineNumber(e.getLineNumber());
            locatorImpl.setColumnNumber(e.getColumnNumber());
            if (exception != null) {
                throw new SAXParseException(e.getMessage(), locatorImpl, exception);
            }
        } catch (XNIException e2) {
            Exception exception2 = e2.getException();
            if (exception2 == null) {
                throw new SAXException(e2.getMessage());
            }
            if (exception2 instanceof SAXException) {
                throw ((SAXException) exception2);
            }
            if (exception2 instanceof IOException) {
                throw ((IOException) exception2);
            }
            x73.a(exception2);
        }
    }

    public void setEntityResolver(EntityResolver entityResolver) {
        try {
            XMLEntityResolver xMLEntityResolver = (XMLEntityResolver) this.fConfiguration.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
            if (this.fUseEntityResolver2 && (entityResolver instanceof EntityResolver2)) {
                if (xMLEntityResolver instanceof EntityResolver2Wrapper) {
                    ((EntityResolver2Wrapper) xMLEntityResolver).setEntityResolver((EntityResolver2) entityResolver);
                    return;
                } else {
                    this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/entity-resolver", new EntityResolver2Wrapper((EntityResolver2) entityResolver));
                    return;
                }
            }
            if (xMLEntityResolver instanceof EntityResolverWrapper) {
                ((EntityResolverWrapper) xMLEntityResolver).setEntityResolver(entityResolver);
            } else {
                this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/entity-resolver", new EntityResolverWrapper(entityResolver));
            }
        } catch (XMLConfigurationException unused) {
        }
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        try {
            XMLErrorHandler xMLErrorHandler = (XMLErrorHandler) this.fConfiguration.getProperty(XMLSchemaLoader.ERROR_HANDLER);
            if (xMLErrorHandler instanceof ErrorHandlerWrapper) {
                ((ErrorHandlerWrapper) xMLErrorHandler).setErrorHandler(errorHandler);
            } else {
                this.fConfiguration.setProperty(XMLSchemaLoader.ERROR_HANDLER, new ErrorHandlerWrapper(errorHandler));
            }
        } catch (XMLConfigurationException unused) {
        }
    }

    public void setFeature(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            if (!str.equals(USE_ENTITY_RESOLVER2)) {
                this.fConfiguration.setFeature(str, z);
            } else if (z != this.fUseEntityResolver2) {
                this.fUseEntityResolver2 = z;
                setEntityResolver(getEntityResolver());
            }
        } catch (XMLConfigurationException e) {
            String identifier = e.getIdentifier();
            if (e.getType() != Status.NOT_RECOGNIZED) {
                throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-not-supported", new Object[]{identifier}));
            }
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-not-recognized", new Object[]{identifier}));
        }
    }

    public void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str.equals("http://apache.org/xml/properties/security-manager")) {
            XMLSecurityManager xMLSecurityManagerConvert = XMLSecurityManager.convert(obj, this.securityManager);
            this.securityManager = xMLSecurityManagerConvert;
            setProperty0("http://apache.org/xml/properties/security-manager", xMLSecurityManagerConvert);
            return;
        }
        if (str.equals("jdk.xml.xmlSecurityPropertyManager")) {
            if (obj == null) {
                this.securityPropertyManager = new XMLSecurityPropertyManager();
            } else {
                this.securityPropertyManager = (XMLSecurityPropertyManager) obj;
            }
            setProperty0("jdk.xml.xmlSecurityPropertyManager", this.securityPropertyManager);
            return;
        }
        if (this.securityManager == null) {
            XMLSecurityManager xMLSecurityManager = new XMLSecurityManager(true);
            this.securityManager = xMLSecurityManager;
            setProperty0("http://apache.org/xml/properties/security-manager", xMLSecurityManager);
        }
        if (this.securityPropertyManager == null) {
            XMLSecurityPropertyManager xMLSecurityPropertyManager = new XMLSecurityPropertyManager();
            this.securityPropertyManager = xMLSecurityPropertyManager;
            setProperty0("jdk.xml.xmlSecurityPropertyManager", xMLSecurityPropertyManager);
        }
        int index = this.securityPropertyManager.getIndex(str);
        if (index > -1) {
            this.securityPropertyManager.setValue(index, XMLSecurityPropertyManager.State.APIPROPERTY, (String) obj);
        } else {
            if (this.securityManager.setLimit(str, JdkProperty.State.APIPROPERTY, obj)) {
                return;
            }
            setProperty0(str, obj);
        }
    }

    public void setProperty0(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            this.fConfiguration.setProperty(str, obj);
        } catch (XMLConfigurationException e) {
            String identifier = e.getIdentifier();
            if (e.getType() != Status.NOT_RECOGNIZED) {
                throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-supported", new Object[]{identifier}));
            }
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-recognized", new Object[]{identifier}));
        }
    }

    public DOMParser() {
        this(null, null);
    }

    public DOMParser(SymbolTable symbolTable) {
        this(symbolTable, null);
    }

    public DOMParser(XMLParserConfiguration xMLParserConfiguration) {
        super(xMLParserConfiguration);
        this.fUseEntityResolver2 = true;
    }

    public void parse(String str) throws SAXException, IOException {
        try {
            parse(new XMLInputSource(null, str, null, false));
        } catch (XMLParseException e) {
            Exception exception = e.getException();
            if (exception != null && !(exception instanceof CharConversionException)) {
                if (!(exception instanceof SAXException)) {
                    if (!(exception instanceof IOException)) {
                        x73.a(exception);
                        return;
                    }
                    throw ((IOException) exception);
                }
                throw ((SAXException) exception);
            }
            LocatorImpl locatorImpl = new LocatorImpl();
            locatorImpl.setPublicId(e.getPublicId());
            locatorImpl.setSystemId(e.getExpandedSystemId());
            locatorImpl.setLineNumber(e.getLineNumber());
            locatorImpl.setColumnNumber(e.getColumnNumber());
            if (exception == null) {
                throw new SAXParseException(e.getMessage(), locatorImpl);
            }
            throw new SAXParseException(e.getMessage(), locatorImpl, exception);
        } catch (XNIException e2) {
            e2.printStackTrace();
            Exception exception2 = e2.getException();
            if (exception2 != null) {
                if (!(exception2 instanceof SAXException)) {
                    if (!(exception2 instanceof IOException)) {
                        x73.a(exception2);
                        return;
                    }
                    throw ((IOException) exception2);
                }
                throw ((SAXException) exception2);
            }
            throw new SAXException(e2.getMessage());
        }
    }
}
