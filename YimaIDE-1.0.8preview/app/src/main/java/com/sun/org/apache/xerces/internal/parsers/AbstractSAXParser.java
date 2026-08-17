package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaLoader;
import com.sun.org.apache.xerces.internal.util.EntityResolver2Wrapper;
import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;
import com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper;
import com.sun.org.apache.xerces.internal.util.SAXMessageFormatter;
import com.sun.org.apache.xerces.internal.util.Status;
import com.sun.org.apache.xerces.internal.util.SymbolHash;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import com.sun.org.apache.xerces.internal.xs.AttributePSVI;
import com.sun.org.apache.xerces.internal.xs.ElementPSVI;
import com.sun.org.apache.xerces.internal.xs.PSVIProvider;
import defpackage.knd;
import defpackage.x73;
import java.io.CharConversionException;
import java.io.IOException;
import java.util.Locale;
import jdk.xml.internal.JdkConstants;
import org.xml.sax.AttributeList;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.Attributes2;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.EntityResolver2;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ext.Locator2;
import org.xml.sax.helpers.LocatorImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class AbstractSAXParser extends AbstractXMLDocumentParser implements PSVIProvider, Parser, XMLReader {
    protected static final String ALLOW_UE_AND_NOTATION_EVENTS = "http://xml.org/sax/features/allow-dtd-events-after-endDTD";
    private static final int BUFFER_SIZE = 20;
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private final AttributesProxy fAttributesProxy;
    private Augmentations fAugmentations;
    private char[] fCharBuffer;
    protected ContentHandler fContentHandler;
    protected DTDHandler fDTDHandler;
    protected DeclHandler fDeclHandler;
    protected SymbolHash fDeclaredAttrs;
    protected DocumentHandler fDocumentHandler;
    protected LexicalHandler fLexicalHandler;
    protected boolean fLexicalHandlerParameterEntities;
    protected NamespaceContext fNamespaceContext;
    protected boolean fNamespacePrefixes;
    protected boolean fNamespaces;
    protected boolean fParseInProgress;
    protected QName fQName;
    protected boolean fResolveDTDURIs;
    protected boolean fStandalone;
    protected boolean fUseEntityResolver2;
    protected String fVersion;
    protected boolean fXMLNSURIs;
    protected static final String STRING_INTERNING = "http://xml.org/sax/features/string-interning";
    private static final String[] RECOGNIZED_FEATURES = {"http://xml.org/sax/features/namespaces", "http://xml.org/sax/features/namespace-prefixes", STRING_INTERNING};
    protected static final String LEXICAL_HANDLER = "http://xml.org/sax/properties/lexical-handler";
    protected static final String DECLARATION_HANDLER = "http://xml.org/sax/properties/declaration-handler";
    protected static final String DOM_NODE = "http://xml.org/sax/properties/dom-node";
    private static final String[] RECOGNIZED_PROPERTIES = {LEXICAL_HANDLER, DECLARATION_HANDLER, DOM_NODE};

    public class LocatorProxy implements Locator2 {
        protected XMLLocator fLocator;

        public LocatorProxy(XMLLocator xMLLocator) {
            this.fLocator = xMLLocator;
        }

        @Override // org.xml.sax.Locator
        public int getColumnNumber() {
            return this.fLocator.getColumnNumber();
        }

        @Override // org.xml.sax.ext.Locator2
        public String getEncoding() {
            return this.fLocator.getEncoding();
        }

        @Override // org.xml.sax.Locator
        public int getLineNumber() {
            return this.fLocator.getLineNumber();
        }

        @Override // org.xml.sax.Locator
        public String getPublicId() {
            return this.fLocator.getPublicId();
        }

        @Override // org.xml.sax.Locator
        public String getSystemId() {
            return this.fLocator.getExpandedSystemId();
        }

        @Override // org.xml.sax.ext.Locator2
        public String getXMLVersion() {
            return this.fLocator.getXMLVersion();
        }
    }

    public AbstractSAXParser(XMLParserConfiguration xMLParserConfiguration) {
        super(xMLParserConfiguration);
        this.fAttributesProxy = new AttributesProxy();
        this.fNamespacePrefixes = false;
        this.fLexicalHandlerParameterEntities = true;
        this.fResolveDTDURIs = true;
        this.fUseEntityResolver2 = true;
        this.fXMLNSURIs = false;
        this.fQName = new QName();
        this.fParseInProgress = false;
        this.fDeclaredAttrs = null;
        this.fAugmentations = null;
        this.fCharBuffer = new char[20];
        xMLParserConfiguration.addRecognizedFeatures(RECOGNIZED_FEATURES);
        xMLParserConfiguration.addRecognizedProperties(RECOGNIZED_PROPERTIES);
        try {
            xMLParserConfiguration.setFeature(ALLOW_UE_AND_NOTATION_EVENTS, false);
        } catch (XMLConfigurationException unused) {
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void attributeDecl(String str, String str2, String str3, String[] strArr, String str4, XMLString xMLString, XMLString xMLString2, Augmentations augmentations) throws XNIException {
        try {
            if (this.fDeclHandler != null) {
                StringBuffer stringBuffer = new StringBuffer(str);
                stringBuffer.append("<");
                stringBuffer.append(str2);
                String string = stringBuffer.toString();
                if (this.fDeclaredAttrs.get(string) != null) {
                    return;
                }
                this.fDeclaredAttrs.put(string, Boolean.TRUE);
                if (str3.equals(SchemaSymbols.ATTVAL_NOTATION) || str3.equals("ENUMERATION")) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    if (str3.equals(SchemaSymbols.ATTVAL_NOTATION)) {
                        stringBuffer2.append(str3);
                        stringBuffer2.append(" (");
                    } else {
                        stringBuffer2.append("(");
                    }
                    for (int i = 0; i < strArr.length; i++) {
                        stringBuffer2.append(strArr[i]);
                        if (i < strArr.length - 1) {
                            stringBuffer2.append('|');
                        }
                    }
                    stringBuffer2.append(')');
                    str3 = stringBuffer2.toString();
                }
                this.fDeclHandler.attributeDecl(str, str2, str3, str4, xMLString == null ? null : xMLString.toString());
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void characters(XMLString xMLString, Augmentations augmentations) throws XNIException {
        int i = xMLString.length;
        if (i == 0) {
            return;
        }
        try {
            DocumentHandler documentHandler = this.fDocumentHandler;
            if (documentHandler != null) {
                documentHandler.characters(xMLString.ch, xMLString.offset, i);
            }
            ContentHandler contentHandler = this.fContentHandler;
            if (contentHandler != null) {
                contentHandler.characters(xMLString.ch, xMLString.offset, xMLString.length);
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void comment(XMLString xMLString, Augmentations augmentations) throws XNIException {
        try {
            LexicalHandler lexicalHandler = this.fLexicalHandler;
            if (lexicalHandler != null) {
                lexicalHandler.comment(xMLString.ch, 0, xMLString.length);
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void doctypeDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
        this.fInDTD = true;
        try {
            LexicalHandler lexicalHandler = this.fLexicalHandler;
            if (lexicalHandler != null) {
                lexicalHandler.startDTD(str, str2, str3);
            }
            if (this.fDeclHandler != null) {
                this.fDeclaredAttrs = new SymbolHash();
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void elementDecl(String str, String str2, Augmentations augmentations) throws XNIException {
        try {
            DeclHandler declHandler = this.fDeclHandler;
            if (declHandler != null) {
                declHandler.elementDecl(str, str2);
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endCDATA(Augmentations augmentations) throws XNIException {
        try {
            LexicalHandler lexicalHandler = this.fLexicalHandler;
            if (lexicalHandler != null) {
                lexicalHandler.endCDATA();
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endDTD(Augmentations augmentations) throws XNIException {
        this.fInDTD = false;
        try {
            LexicalHandler lexicalHandler = this.fLexicalHandler;
            if (lexicalHandler != null) {
                lexicalHandler.endDTD();
            }
            SymbolHash symbolHash = this.fDeclaredAttrs;
            if (symbolHash != null) {
                symbolHash.clear();
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endDocument(Augmentations augmentations) throws XNIException {
        try {
            DocumentHandler documentHandler = this.fDocumentHandler;
            if (documentHandler != null) {
                documentHandler.endDocument();
            }
            ContentHandler contentHandler = this.fContentHandler;
            if (contentHandler != null) {
                contentHandler.endDocument();
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endElement(QName qName, Augmentations augmentations) throws XNIException {
        try {
            DocumentHandler documentHandler = this.fDocumentHandler;
            if (documentHandler != null) {
                documentHandler.endElement(qName.rawname);
            }
            ContentHandler contentHandler = this.fContentHandler;
            if (contentHandler != null) {
                this.fAugmentations = augmentations;
                String str = qName.uri;
                if (str == null) {
                    str = "";
                }
                contentHandler.endElement(str, this.fNamespaces ? qName.localpart : "", qName.rawname);
                if (this.fNamespaces) {
                    endNamespaceMapping();
                }
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endExternalSubset(Augmentations augmentations) throws XNIException {
        endParameterEntity("[dtd]", augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endGeneralEntity(String str, Augmentations augmentations) throws XNIException {
        if (augmentations != null) {
            try {
                if (Boolean.TRUE.equals(augmentations.getItem(Constants.ENTITY_SKIPPED))) {
                    return;
                }
            } catch (SAXException e) {
                knd.a(e);
                return;
            }
        }
        LexicalHandler lexicalHandler = this.fLexicalHandler;
        if (lexicalHandler != null) {
            lexicalHandler.endEntity(str);
        }
    }

    public final void endNamespaceMapping() throws SAXException {
        int declaredPrefixCount = this.fNamespaceContext.getDeclaredPrefixCount();
        if (declaredPrefixCount > 0) {
            for (int i = 0; i < declaredPrefixCount; i++) {
                this.fContentHandler.endPrefixMapping(this.fNamespaceContext.getDeclaredPrefixAt(i));
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endParameterEntity(String str, Augmentations augmentations) throws XNIException {
        if (augmentations != null) {
            try {
                if (Boolean.TRUE.equals(augmentations.getItem(Constants.ENTITY_SKIPPED))) {
                    return;
                }
            } catch (SAXException e) {
                knd.a(e);
                return;
            }
        }
        LexicalHandler lexicalHandler = this.fLexicalHandler;
        if (lexicalHandler == null || !this.fLexicalHandlerParameterEntities) {
            return;
        }
        lexicalHandler.endEntity(str);
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void externalEntityDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
        try {
            if (this.fDeclHandler != null) {
                this.fDeclHandler.externalEntityDecl(str, xMLResourceIdentifier.getPublicId(), this.fResolveDTDURIs ? xMLResourceIdentifier.getExpandedSystemId() : xMLResourceIdentifier.getLiteralSystemId());
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xs.PSVIProvider
    public AttributePSVI getAttributePSVI(int i) {
        return (AttributePSVI) this.fAttributesProxy.fAttributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_PSVI);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.PSVIProvider
    public AttributePSVI getAttributePSVIByName(String str, String str2) {
        return (AttributePSVI) this.fAttributesProxy.fAttributes.getAugmentations(str, str2).getItem(Constants.ATTRIBUTE_PSVI);
    }

    @Override // org.xml.sax.XMLReader
    public ContentHandler getContentHandler() {
        return this.fContentHandler;
    }

    @Override // org.xml.sax.XMLReader
    public DTDHandler getDTDHandler() {
        return this.fDTDHandler;
    }

    public DeclHandler getDeclHandler() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fDeclHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.PSVIProvider
    public ElementPSVI getElementPSVI() {
        Augmentations augmentations = this.fAugmentations;
        if (augmentations != null) {
            return (ElementPSVI) augmentations.getItem(Constants.ELEMENT_PSVI);
        }
        return null;
    }

    @Override // org.xml.sax.XMLReader
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

    @Override // org.xml.sax.XMLReader
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
            if (str.startsWith(Constants.SAX_FEATURE_PREFIX)) {
                int length = str.length() - 28;
                if (length == 18 && str.endsWith(Constants.NAMESPACE_PREFIXES_FEATURE)) {
                    return this.fConfiguration.getFeature(str);
                }
                if (length == 16 && str.endsWith(Constants.STRING_INTERNING_FEATURE)) {
                    return true;
                }
                if (length == 13 && str.endsWith(Constants.IS_STANDALONE_FEATURE)) {
                    return this.fStandalone;
                }
                if (length == 7 && str.endsWith(Constants.XML_11_FEATURE)) {
                    return this.fConfiguration instanceof XML11Configurable;
                }
                if (length == 34 && str.endsWith(Constants.LEXICAL_HANDLER_PARAMETER_ENTITIES_FEATURE)) {
                    return this.fLexicalHandlerParameterEntities;
                }
                if (length == 16 && str.endsWith(Constants.RESOLVE_DTD_URIS_FEATURE)) {
                    return this.fResolveDTDURIs;
                }
                if (length == 10 && str.endsWith(Constants.XMLNS_URIS_FEATURE)) {
                    return this.fXMLNSURIs;
                }
                if (length == 30 && str.endsWith(Constants.UNICODE_NORMALIZATION_CHECKING_FEATURE)) {
                    return false;
                }
                if (length == 20 && str.endsWith(Constants.USE_ENTITY_RESOLVER2_FEATURE)) {
                    return this.fUseEntityResolver2;
                }
                if ((length == 15 && str.endsWith(Constants.USE_ATTRIBUTES2_FEATURE)) || (length == 12 && str.endsWith(Constants.USE_LOCATOR2_FEATURE))) {
                    return true;
                }
            }
            return this.fConfiguration.getFeature(str);
        } catch (XMLConfigurationException e) {
            String identifier = e.getIdentifier();
            if (e.getType() == Status.NOT_RECOGNIZED) {
                throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-not-recognized", new Object[]{identifier}));
            }
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-not-supported", new Object[]{identifier}));
        }
    }

    public LexicalHandler getLexicalHandler() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fLexicalHandler;
    }

    public Object getProperty(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            if (str.startsWith(Constants.SAX_PROPERTY_PREFIX)) {
                int length = str.length() - 30;
                if (length == 20 && str.endsWith(Constants.DOCUMENT_XML_VERSION_PROPERTY)) {
                    return this.fVersion;
                }
                if (length == 15 && str.endsWith(Constants.LEXICAL_HANDLER_PROPERTY)) {
                    return getLexicalHandler();
                }
                if (length == 19 && str.endsWith(Constants.DECLARATION_HANDLER_PROPERTY)) {
                    return getDeclHandler();
                }
                if (length == 8 && str.endsWith(Constants.DOM_NODE_PROPERTY)) {
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "dom-node-read-not-supported", null));
                }
            }
            return this.fConfiguration.getProperty(str);
        } catch (XMLConfigurationException e) {
            String identifier = e.getIdentifier();
            if (e.getType() == Status.NOT_RECOGNIZED) {
                throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-recognized", new Object[]{identifier}));
            }
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-supported", new Object[]{identifier}));
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void ignorableWhitespace(XMLString xMLString, Augmentations augmentations) throws XNIException {
        try {
            DocumentHandler documentHandler = this.fDocumentHandler;
            if (documentHandler != null) {
                documentHandler.ignorableWhitespace(xMLString.ch, xMLString.offset, xMLString.length);
            }
            ContentHandler contentHandler = this.fContentHandler;
            if (contentHandler != null) {
                contentHandler.ignorableWhitespace(xMLString.ch, xMLString.offset, xMLString.length);
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void internalEntityDecl(String str, XMLString xMLString, XMLString xMLString2, Augmentations augmentations) throws XNIException {
        try {
            DeclHandler declHandler = this.fDeclHandler;
            if (declHandler != null) {
                declHandler.internalEntityDecl(str, xMLString.toString());
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void notationDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
        try {
            if (this.fDTDHandler != null) {
                this.fDTDHandler.notationDecl(str, xMLResourceIdentifier.getPublicId(), this.fResolveDTDURIs ? xMLResourceIdentifier.getExpandedSystemId() : xMLResourceIdentifier.getLiteralSystemId());
            }
        } catch (SAXException e) {
            knd.a(e);
        }
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
            LocatorImpl locatorImpl = new LocatorImpl() { // from class: com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser.2
                public String getEncoding() {
                    return null;
                }

                public String getXMLVersion() {
                    return AbstractSAXParser.this.fVersion;
                }
            };
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

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void processingInstruction(String str, XMLString xMLString, Augmentations augmentations) throws XNIException {
        try {
            DocumentHandler documentHandler = this.fDocumentHandler;
            if (documentHandler != null) {
                documentHandler.processingInstruction(str, xMLString.toString());
            }
            ContentHandler contentHandler = this.fContentHandler;
            if (contentHandler != null) {
                contentHandler.processingInstruction(str, xMLString.toString());
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.parsers.XMLParser
    public void reset() throws XNIException {
        super.reset();
        this.fInDTD = false;
        this.fVersion = "1.0";
        this.fStandalone = false;
        this.fNamespaces = this.fConfiguration.getFeature("http://xml.org/sax/features/namespaces");
        this.fNamespacePrefixes = this.fConfiguration.getFeature("http://xml.org/sax/features/namespace-prefixes");
        this.fAugmentations = null;
        this.fDeclaredAttrs = null;
    }

    @Override // org.xml.sax.XMLReader
    public void setContentHandler(ContentHandler contentHandler) {
        this.fContentHandler = contentHandler;
    }

    @Override // org.xml.sax.Parser, org.xml.sax.XMLReader
    public void setDTDHandler(DTDHandler dTDHandler) {
        this.fDTDHandler = dTDHandler;
    }

    public void setDeclHandler(DeclHandler declHandler) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.fParseInProgress) {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-parsing-supported", new Object[]{DECLARATION_HANDLER}));
        }
        this.fDeclHandler = declHandler;
    }

    @Override // org.xml.sax.Parser
    public void setDocumentHandler(DocumentHandler documentHandler) {
        this.fDocumentHandler = documentHandler;
    }

    @Override // org.xml.sax.Parser, org.xml.sax.XMLReader
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

    @Override // org.xml.sax.Parser, org.xml.sax.XMLReader
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
            if (str.startsWith(Constants.SAX_FEATURE_PREFIX)) {
                int length = str.length() - 28;
                if (length == 10 && str.endsWith("namespaces")) {
                    this.fConfiguration.setFeature(str, z);
                    this.fNamespaces = z;
                    return;
                }
                if (length == 18 && str.endsWith(Constants.NAMESPACE_PREFIXES_FEATURE)) {
                    this.fConfiguration.setFeature(str, z);
                    this.fNamespacePrefixes = z;
                    return;
                }
                if (length == 16 && str.endsWith(Constants.STRING_INTERNING_FEATURE)) {
                    if (!z) {
                        throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "false-not-supported", new Object[]{str}));
                    }
                    return;
                }
                if (length == 34 && str.endsWith(Constants.LEXICAL_HANDLER_PARAMETER_ENTITIES_FEATURE)) {
                    this.fLexicalHandlerParameterEntities = z;
                    return;
                }
                if (length == 16 && str.endsWith(Constants.RESOLVE_DTD_URIS_FEATURE)) {
                    this.fResolveDTDURIs = z;
                    return;
                }
                if (length == 30 && str.endsWith(Constants.UNICODE_NORMALIZATION_CHECKING_FEATURE)) {
                    if (z) {
                        throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "true-not-supported", new Object[]{str}));
                    }
                    return;
                }
                if (length == 10 && str.endsWith(Constants.XMLNS_URIS_FEATURE)) {
                    this.fXMLNSURIs = z;
                    return;
                }
                if (length == 20 && str.endsWith(Constants.USE_ENTITY_RESOLVER2_FEATURE)) {
                    if (z != this.fUseEntityResolver2) {
                        this.fUseEntityResolver2 = z;
                        setEntityResolver(getEntityResolver());
                        return;
                    }
                    return;
                }
                if ((length == 13 && str.endsWith(Constants.IS_STANDALONE_FEATURE)) || ((length == 15 && str.endsWith(Constants.USE_ATTRIBUTES2_FEATURE)) || ((length == 12 && str.endsWith(Constants.USE_LOCATOR2_FEATURE)) || (length == 7 && str.endsWith(Constants.XML_11_FEATURE))))) {
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-read-only", new Object[]{str}));
                }
            } else if (str.equals("http://javax.xml.XMLConstants/feature/secure-processing") && z && this.fConfiguration.getProperty("http://apache.org/xml/properties/security-manager") == null) {
                this.fConfiguration.setProperty("http://apache.org/xml/properties/security-manager", new XMLSecurityManager());
            }
            this.fConfiguration.setFeature(str, z);
        } catch (XMLConfigurationException e) {
            String identifier = e.getIdentifier();
            if (e.getType() != Status.NOT_RECOGNIZED) {
                throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-not-supported", new Object[]{identifier}));
            }
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-not-recognized", new Object[]{identifier}));
        }
    }

    public void setLexicalHandler(LexicalHandler lexicalHandler) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.fParseInProgress) {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-parsing-supported", new Object[]{LEXICAL_HANDLER}));
        }
        this.fLexicalHandler = lexicalHandler;
    }

    @Override // org.xml.sax.Parser
    public void setLocale(Locale locale) throws SAXException {
        this.fConfiguration.setLocale(locale);
    }

    /* JADX WARN: Code duplicated, block: B:38:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:40:0x00b9  */
    public void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
        String identifier;
        try {
            if (str.startsWith(Constants.SAX_PROPERTY_PREFIX)) {
                int length = str.length() - 30;
                if (length == 15 && str.endsWith(Constants.LEXICAL_HANDLER_PROPERTY)) {
                    try {
                        setLexicalHandler((LexicalHandler) obj);
                        return;
                    } catch (ClassCastException unused) {
                        throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "incompatible-class", new Object[]{str, "org.xml.sax.ext.LexicalHandler"}));
                    }
                } else if (length == 19 && str.endsWith(Constants.DECLARATION_HANDLER_PROPERTY)) {
                    try {
                        setDeclHandler((DeclHandler) obj);
                        return;
                    } catch (ClassCastException unused2) {
                        throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "incompatible-class", new Object[]{str, "org.xml.sax.ext.DeclHandler"}));
                    }
                } else if ((length == 8 && str.endsWith(Constants.DOM_NODE_PROPERTY)) || (length == 20 && str.endsWith(Constants.DOCUMENT_XML_VERSION_PROPERTY))) {
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-read-only", new Object[]{str}));
                }
                identifier = e.getIdentifier();
                if (e.getType() == Status.NOT_RECOGNIZED) {
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-supported", new Object[]{identifier}));
                }
                throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-recognized", new Object[]{identifier}));
            }
            this.fConfiguration.setProperty(str, obj);
        } catch (XMLConfigurationException e) {
            identifier = e.getIdentifier();
            if (e.getType() == Status.NOT_RECOGNIZED) {
                throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-supported", new Object[]{identifier}));
            }
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-recognized", new Object[]{identifier}));
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startCDATA(Augmentations augmentations) throws XNIException {
        try {
            LexicalHandler lexicalHandler = this.fLexicalHandler;
            if (lexicalHandler != null) {
                lexicalHandler.startCDATA();
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startDocument(XMLLocator xMLLocator, String str, NamespaceContext namespaceContext, Augmentations augmentations) throws XNIException {
        this.fNamespaceContext = namespaceContext;
        try {
            DocumentHandler documentHandler = this.fDocumentHandler;
            if (documentHandler != null) {
                if (xMLLocator != null) {
                    documentHandler.setDocumentLocator(new LocatorProxy(xMLLocator));
                }
                this.fDocumentHandler.startDocument();
            }
            ContentHandler contentHandler = this.fContentHandler;
            if (contentHandler != null) {
                if (xMLLocator != null) {
                    contentHandler.setDocumentLocator(new LocatorProxy(xMLLocator));
                }
                this.fContentHandler.startDocument();
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
        try {
            if (this.fDocumentHandler != null) {
                this.fAttributesProxy.setAttributes(xMLAttributes);
                this.fDocumentHandler.startElement(qName.rawname, this.fAttributesProxy);
            }
            if (this.fContentHandler != null) {
                if (this.fNamespaces) {
                    startNamespaceMapping();
                    int length = xMLAttributes.getLength();
                    if (!this.fNamespacePrefixes) {
                        for (int i = length - 1; i >= 0; i--) {
                            xMLAttributes.getName(i, this.fQName);
                            QName qName2 = this.fQName;
                            String str = qName2.prefix;
                            String str2 = XMLSymbols.PREFIX_XMLNS;
                            if (str == str2 || qName2.rawname == str2) {
                                xMLAttributes.removeAttributeAt(i);
                            }
                        }
                    } else if (!this.fXMLNSURIs) {
                        for (int i2 = length - 1; i2 >= 0; i2--) {
                            xMLAttributes.getName(i2, this.fQName);
                            QName qName3 = this.fQName;
                            String str3 = qName3.prefix;
                            String str4 = XMLSymbols.PREFIX_XMLNS;
                            if (str3 == str4 || qName3.rawname == str4) {
                                qName3.prefix = "";
                                qName3.uri = "";
                                qName3.localpart = "";
                                xMLAttributes.setName(i2, qName3);
                            }
                        }
                    }
                }
                this.fAugmentations = augmentations;
                String str5 = qName.uri;
                if (str5 == null) {
                    str5 = "";
                }
                String str6 = this.fNamespaces ? qName.localpart : "";
                this.fAttributesProxy.setAttributes(xMLAttributes);
                this.fContentHandler.startElement(str5, str6, qName.rawname, this.fAttributesProxy);
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startExternalSubset(XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
        startParameterEntity("[dtd]", null, null, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startGeneralEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
        if (augmentations != null) {
            try {
                if (Boolean.TRUE.equals(augmentations.getItem(Constants.ENTITY_SKIPPED))) {
                    ContentHandler contentHandler = this.fContentHandler;
                    if (contentHandler != null) {
                        contentHandler.skippedEntity(str);
                        return;
                    }
                    return;
                }
            } catch (SAXException e) {
                knd.a(e);
                return;
            }
        }
        LexicalHandler lexicalHandler = this.fLexicalHandler;
        if (lexicalHandler != null) {
            lexicalHandler.startEntity(str);
        }
    }

    public final void startNamespaceMapping() throws SAXException {
        int declaredPrefixCount = this.fNamespaceContext.getDeclaredPrefixCount();
        if (declaredPrefixCount > 0) {
            for (int i = 0; i < declaredPrefixCount; i++) {
                String declaredPrefixAt = this.fNamespaceContext.getDeclaredPrefixAt(i);
                String uri = this.fNamespaceContext.getURI(declaredPrefixAt);
                ContentHandler contentHandler = this.fContentHandler;
                if (uri == null) {
                    uri = "";
                }
                contentHandler.startPrefixMapping(declaredPrefixAt, uri);
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startParameterEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
        if (augmentations != null) {
            try {
                if (Boolean.TRUE.equals(augmentations.getItem(Constants.ENTITY_SKIPPED))) {
                    ContentHandler contentHandler = this.fContentHandler;
                    if (contentHandler != null) {
                        contentHandler.skippedEntity(str);
                        return;
                    }
                    return;
                }
            } catch (SAXException e) {
                knd.a(e);
                return;
            }
        }
        LexicalHandler lexicalHandler = this.fLexicalHandler;
        if (lexicalHandler == null || !this.fLexicalHandlerParameterEntities) {
            return;
        }
        lexicalHandler.startEntity(str);
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void unparsedEntityDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
        try {
            if (this.fDTDHandler != null) {
                this.fDTDHandler.unparsedEntityDecl(str, xMLResourceIdentifier.getPublicId(), this.fResolveDTDURIs ? xMLResourceIdentifier.getExpandedSystemId() : xMLResourceIdentifier.getLiteralSystemId(), str2);
            }
        } catch (SAXException e) {
            knd.a(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void xmlDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
        this.fVersion = str;
        this.fStandalone = JdkConstants.JDK_YES.equals(str3);
        ContentHandler contentHandler = this.fContentHandler;
        if (contentHandler != null) {
            try {
                contentHandler.declaration(str, str2, str3);
            } catch (SAXException e) {
                knd.a(e);
            }
        }
    }

    public static final class AttributesProxy implements AttributeList, Attributes2 {
        protected XMLAttributes fAttributes;

        @Override // org.xml.sax.Attributes
        public int getIndex(String str, String str2) {
            boolean zEquals = str.equals("");
            XMLAttributes xMLAttributes = this.fAttributes;
            return zEquals ? xMLAttributes.getIndex(null, str2) : xMLAttributes.getIndex(str, str2);
        }

        @Override // org.xml.sax.AttributeList, org.xml.sax.Attributes
        public int getLength() {
            return this.fAttributes.getLength();
        }

        @Override // org.xml.sax.Attributes
        public String getLocalName(int i) {
            return this.fAttributes.getLocalName(i);
        }

        @Override // org.xml.sax.AttributeList
        public String getName(int i) {
            return this.fAttributes.getQName(i);
        }

        @Override // org.xml.sax.Attributes
        public String getQName(int i) {
            return this.fAttributes.getQName(i);
        }

        @Override // org.xml.sax.Attributes
        public String getType(String str, String str2) {
            boolean zEquals = str.equals("");
            XMLAttributes xMLAttributes = this.fAttributes;
            return zEquals ? xMLAttributes.getType(null, str2) : xMLAttributes.getType(str, str2);
        }

        @Override // org.xml.sax.Attributes
        public String getURI(int i) {
            String uri = this.fAttributes.getURI(i);
            return uri != null ? uri : "";
        }

        @Override // org.xml.sax.Attributes
        public String getValue(String str, String str2) {
            boolean zEquals = str.equals("");
            XMLAttributes xMLAttributes = this.fAttributes;
            return zEquals ? xMLAttributes.getValue(null, str2) : xMLAttributes.getValue(str, str2);
        }

        @Override // org.xml.sax.ext.Attributes2
        public boolean isDeclared(int i) {
            if (i >= 0 && i < this.fAttributes.getLength()) {
                return Boolean.TRUE.equals(this.fAttributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_DECLARED));
            }
            y0e.a(i);
            return false;
        }

        @Override // org.xml.sax.ext.Attributes2
        public boolean isSpecified(int i) {
            if (i >= 0 && i < this.fAttributes.getLength()) {
                return this.fAttributes.isSpecified(i);
            }
            y0e.a(i);
            return false;
        }

        public void setAttributes(XMLAttributes xMLAttributes) {
            this.fAttributes = xMLAttributes;
        }

        @Override // org.xml.sax.Attributes
        public int getIndex(String str) {
            return this.fAttributes.getIndex(str);
        }

        @Override // org.xml.sax.AttributeList, org.xml.sax.Attributes
        public String getType(String str) {
            return this.fAttributes.getType(str);
        }

        @Override // org.xml.sax.AttributeList, org.xml.sax.Attributes
        public String getValue(String str) {
            return this.fAttributes.getValue(str);
        }

        @Override // org.xml.sax.AttributeList, org.xml.sax.Attributes
        public String getType(int i) {
            return this.fAttributes.getType(i);
        }

        @Override // org.xml.sax.AttributeList, org.xml.sax.Attributes
        public String getValue(int i) {
            return this.fAttributes.getValue(i);
        }

        @Override // org.xml.sax.ext.Attributes2
        public boolean isSpecified(String str) {
            int index = getIndex(str);
            if (index != -1) {
                return this.fAttributes.isSpecified(index);
            }
            w01.a(str);
            return false;
        }

        @Override // org.xml.sax.ext.Attributes2
        public boolean isSpecified(String str, String str2) {
            int index = getIndex(str, str2);
            if (index != -1) {
                return this.fAttributes.isSpecified(index);
            }
            w01.a(str2);
            return false;
        }

        @Override // org.xml.sax.ext.Attributes2
        public boolean isDeclared(String str) {
            int index = getIndex(str);
            if (index != -1) {
                return Boolean.TRUE.equals(this.fAttributes.getAugmentations(index).getItem(Constants.ATTRIBUTE_DECLARED));
            }
            w01.a(str);
            return false;
        }

        @Override // org.xml.sax.ext.Attributes2
        public boolean isDeclared(String str, String str2) {
            int index = getIndex(str, str2);
            if (index != -1) {
                return Boolean.TRUE.equals(this.fAttributes.getAugmentations(index).getItem(Constants.ATTRIBUTE_DECLARED));
            }
            w01.a(str2);
            return false;
        }
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
            LocatorImpl locatorImpl = new LocatorImpl() { // from class: com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser.1
                public String getEncoding() {
                    return null;
                }

                public String getXMLVersion() {
                    return AbstractSAXParser.this.fVersion;
                }
            };
            locatorImpl.setPublicId(e.getPublicId());
            locatorImpl.setSystemId(e.getExpandedSystemId());
            locatorImpl.setLineNumber(e.getLineNumber());
            locatorImpl.setColumnNumber(e.getColumnNumber());
            if (exception == null) {
                throw new SAXParseException(e.getMessage(), locatorImpl);
            }
            throw new SAXParseException(e.getMessage(), locatorImpl, exception);
        } catch (XNIException e2) {
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
