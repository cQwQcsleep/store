package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.dom.DOMErrorImpl;
import com.sun.org.apache.xerces.internal.dom.DOMMessageFormatter;
import com.sun.org.apache.xerces.internal.dom.DOMStringListImpl;
import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaLoader;
import com.sun.org.apache.xerces.internal.jaxp.JAXPConstants;
import com.sun.org.apache.xerces.internal.util.DOMEntityResolverWrapper;
import com.sun.org.apache.xerces.internal.util.DOMErrorHandlerWrapper;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDTDHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDContentModelSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import com.sun.org.apache.xml.internal.serializer.utils.MsgKey;
import defpackage.zi0;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Stack;
import java.util.StringTokenizer;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMStringList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.ls.LSException;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSParserFilter;
import org.w3c.dom.ls.LSResourceResolver;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMParserImpl extends AbstractDOMParser implements LSParser, DOMConfiguration {
    protected static final boolean DEBUG = false;
    protected static final String DISALLOW_DOCTYPE_DECL_FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
    protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String NAMESPACE_GROWTH = "http://apache.org/xml/features/namespace-growth";
    protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    protected static final String PSVI_AUGMENT = "http://apache.org/xml/features/validation/schema/augment-psvi";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String TOLERATE_DUPLICATES = "http://apache.org/xml/features/internal/tolerate-duplicates";
    protected static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    protected static final String XMLSCHEMA = "http://apache.org/xml/features/validation/schema";
    protected static final String XMLSCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    private AbortHandler abortHandler;
    private boolean abortNow;
    private Thread currentThread;
    protected boolean fBusy;
    protected boolean fNamespaceDeclarations;
    private DOMStringList fRecognizedParameters;
    private String fSchemaLocation;
    protected String fSchemaType;

    public class AbortHandler implements XMLDocumentHandler, XMLDTDHandler, XMLDTDContentModelHandler {
        private XMLDocumentSource documentSource;
        private XMLDTDContentModelSource dtdContentSource;
        private XMLDTDSource dtdSource;

        private AbortHandler() {
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
        public void any(Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void attributeDecl(String str, String str2, String str3, String[] strArr, String str4, XMLString xMLString, XMLString xMLString2, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void characters(XMLString xMLString, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void comment(XMLString xMLString, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void doctypeDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
        public void element(String str, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void elementDecl(String str, String str2, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
        public void empty(Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void emptyElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void endAttlist(Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void endCDATA(Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void endConditional(Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
        public void endContentModel(Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void endDTD(Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void endDocument(Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void endElement(QName qName, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void endExternalSubset(Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void endGeneralEntity(String str, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
        public void endGroup(Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void endParameterEntity(String str, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void externalEntityDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
        public XMLDTDContentModelSource getDTDContentModelSource() {
            return this.dtdContentSource;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public XMLDTDSource getDTDSource() {
            return this.dtdSource;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public XMLDocumentSource getDocumentSource() {
            return this.documentSource;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void ignorableWhitespace(XMLString xMLString, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void ignoredCharacters(XMLString xMLString, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void internalEntityDecl(String str, XMLString xMLString, XMLString xMLString2, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void notationDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
        public void occurrence(short s, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
        public void pcdata(Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void processingInstruction(String str, XMLString xMLString, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
        public void separator(short s, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
        public void setDTDContentModelSource(XMLDTDContentModelSource xMLDTDContentModelSource) {
            this.dtdContentSource = xMLDTDContentModelSource;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void setDTDSource(XMLDTDSource xMLDTDSource) {
            this.dtdSource = xMLDTDSource;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void setDocumentSource(XMLDocumentSource xMLDocumentSource) {
            this.documentSource = xMLDocumentSource;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void startAttlist(String str, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void startCDATA(Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void startConditional(short s, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
        public void startContentModel(String str, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void startDTD(XMLLocator xMLLocator, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void startDocument(XMLLocator xMLLocator, String str, NamespaceContext namespaceContext, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void startElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void startExternalSubset(XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void startGeneralEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
        public void startGroup(Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void startParameterEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void textDecl(String str, String str2, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
        public void unparsedEntityDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
        public void xmlDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.Abort.INSTANCE;
        }
    }

    public DOMParserImpl(XMLParserConfiguration xMLParserConfiguration) {
        super(xMLParserConfiguration);
        this.fNamespaceDeclarations = true;
        this.fSchemaType = null;
        this.fBusy = false;
        this.abortNow = false;
        this.fSchemaLocation = null;
        this.abortHandler = null;
        this.fConfiguration.addRecognizedFeatures(new String[]{"canonical-form", "cdata-sections", Constants.DOM_CHARSET_OVERRIDES_XML_ENCODING, "infoset", "namespace-declarations", "split-cdata-sections", Constants.DOM_SUPPORTED_MEDIATYPES_ONLY, Constants.DOM_CERTIFIED, "well-formed", "ignore-unknown-character-denormalizations"});
        this.fConfiguration.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
        this.fConfiguration.setFeature("namespace-declarations", true);
        this.fConfiguration.setFeature("well-formed", true);
        this.fConfiguration.setFeature("http://apache.org/xml/features/include-comments", true);
        this.fConfiguration.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", true);
        this.fConfiguration.setFeature("http://xml.org/sax/features/namespaces", true);
        this.fConfiguration.setFeature(DYNAMIC_VALIDATION, false);
        this.fConfiguration.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", false);
        this.fConfiguration.setFeature("http://apache.org/xml/features/create-cdata-nodes", false);
        this.fConfiguration.setFeature("canonical-form", false);
        this.fConfiguration.setFeature(Constants.DOM_CHARSET_OVERRIDES_XML_ENCODING, true);
        this.fConfiguration.setFeature("split-cdata-sections", true);
        this.fConfiguration.setFeature(Constants.DOM_SUPPORTED_MEDIATYPES_ONLY, false);
        this.fConfiguration.setFeature("ignore-unknown-character-denormalizations", true);
        this.fConfiguration.setFeature(Constants.DOM_CERTIFIED, true);
        try {
            this.fConfiguration.setFeature(NORMALIZE_DATA, false);
        } catch (XMLConfigurationException unused) {
        }
    }

    private static DOMException newFeatureNotFoundError(String str) {
        return new DOMException((short) 8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_FOUND", new Object[]{str}));
    }

    private static DOMException newTypeMismatchError(String str) {
        return new DOMException((short) 17, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, MsgKey.ER_TYPE_MISMATCH_ERR, new Object[]{str}));
    }

    private void restoreHandlers() {
        this.fConfiguration.setDocumentHandler(this);
        this.fConfiguration.setDTDHandler(this);
        this.fConfiguration.setDTDContentModelHandler(this);
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractDOMParser, org.w3c.dom.ls.LSParser
    public void abort() {
        if (this.fBusy) {
            this.fBusy = false;
            if (this.currentThread != null) {
                this.abortNow = true;
                if (this.abortHandler == null) {
                    this.abortHandler = new AbortHandler();
                }
                this.fConfiguration.setDocumentHandler(this.abortHandler);
                this.fConfiguration.setDTDHandler(this.abortHandler);
                this.fConfiguration.setDTDContentModelHandler(this.abortHandler);
                if (this.currentThread == Thread.currentThread()) {
                    throw AbstractDOMParser.Abort.INSTANCE;
                }
                this.currentThread.interrupt();
            }
        }
    }

    @Override // org.w3c.dom.DOMConfiguration
    public boolean canSetParameter(String str, Object obj) {
        String lowerCase = TOLERATE_DUPLICATES;
        if (obj == null) {
            return true;
        }
        if (!(obj instanceof Boolean)) {
            if (str.equalsIgnoreCase("error-handler")) {
                return obj instanceof DOMErrorHandler;
            }
            if (str.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER)) {
                return obj instanceof LSResourceResolver;
            }
            if (str.equalsIgnoreCase("schema-type")) {
                return (obj instanceof String) && (obj.equals(Constants.NS_XMLSCHEMA) || obj.equals(Constants.NS_DTD));
            }
            if (str.equalsIgnoreCase("schema-location")) {
                return obj instanceof String;
            }
            return str.equalsIgnoreCase("http://apache.org/xml/properties/dom/document-class-name");
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        if (str.equalsIgnoreCase(Constants.DOM_SUPPORTED_MEDIATYPES_ONLY) || str.equalsIgnoreCase("normalize-characters") || str.equalsIgnoreCase("check-character-normalization") || str.equalsIgnoreCase("canonical-form")) {
            return !zBooleanValue;
        }
        if (str.equalsIgnoreCase("well-formed") || str.equalsIgnoreCase("ignore-unknown-character-denormalizations")) {
            return zBooleanValue;
        }
        if (str.equalsIgnoreCase("cdata-sections") || str.equalsIgnoreCase(Constants.DOM_CHARSET_OVERRIDES_XML_ENCODING) || str.equalsIgnoreCase("comments") || str.equalsIgnoreCase("datatype-normalization") || str.equalsIgnoreCase(Constants.DOM_DISALLOW_DOCTYPE) || str.equalsIgnoreCase("entities") || str.equalsIgnoreCase("infoset") || str.equalsIgnoreCase("namespaces") || str.equalsIgnoreCase("namespace-declarations") || str.equalsIgnoreCase("validate") || str.equalsIgnoreCase("validate-if-schema") || str.equalsIgnoreCase("element-content-whitespace") || str.equalsIgnoreCase("xml-declaration")) {
            return true;
        }
        try {
            if (str.equalsIgnoreCase(NAMESPACE_GROWTH)) {
                lowerCase = NAMESPACE_GROWTH;
            } else if (!str.equalsIgnoreCase(TOLERATE_DUPLICATES)) {
                lowerCase = str.toLowerCase(Locale.ENGLISH);
            }
            this.fConfiguration.getFeature(lowerCase);
            return true;
        } catch (XMLConfigurationException unused) {
            return false;
        }
    }

    public XMLInputSource dom2xmlInputSource(LSInput lSInput) {
        if (lSInput.getCharacterStream() != null) {
            return new XMLInputSource(lSInput.getPublicId(), lSInput.getSystemId(), lSInput.getBaseURI(), lSInput.getCharacterStream(), XMLEntityManager.EncodingInfo.STR_UTF16);
        }
        if (lSInput.getByteStream() != null) {
            return new XMLInputSource(lSInput.getPublicId(), lSInput.getSystemId(), lSInput.getBaseURI(), lSInput.getByteStream(), lSInput.getEncoding());
        }
        if (lSInput.getStringData() != null && lSInput.getStringData().length() > 0) {
            return new XMLInputSource(lSInput.getPublicId(), lSInput.getSystemId(), lSInput.getBaseURI(), new StringReader(lSInput.getStringData()), XMLEntityManager.EncodingInfo.STR_UTF16);
        }
        if ((lSInput.getSystemId() != null && lSInput.getSystemId().length() > 0) || (lSInput.getPublicId() != null && lSInput.getPublicId().length() > 0)) {
            return new XMLInputSource(lSInput.getPublicId(), lSInput.getSystemId(), lSInput.getBaseURI(), false);
        }
        if (this.fErrorHandler != null) {
            DOMErrorImpl dOMErrorImpl = new DOMErrorImpl();
            dOMErrorImpl.fType = "no-input-specified";
            dOMErrorImpl.fMessage = "no-input-specified";
            dOMErrorImpl.fSeverity = (short) 3;
            this.fErrorHandler.getErrorHandler().handleError(dOMErrorImpl);
        }
        throw new LSException((short) 81, "no-input-specified");
    }

    @Override // org.w3c.dom.ls.LSParser
    public boolean getAsync() {
        return false;
    }

    @Override // org.w3c.dom.ls.LSParser
    public boolean getBusy() {
        return this.fBusy;
    }

    @Override // org.w3c.dom.ls.LSParser
    public DOMConfiguration getDomConfig() {
        return this;
    }

    @Override // org.w3c.dom.ls.LSParser
    public LSParserFilter getFilter() {
        return this.fDOMFilter;
    }

    @Override // org.w3c.dom.DOMConfiguration
    public Object getParameter(String str) throws DOMException {
        if (str.equalsIgnoreCase("comments")) {
            return this.fConfiguration.getFeature("http://apache.org/xml/features/include-comments") ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("datatype-normalization")) {
            return this.fConfiguration.getFeature(NORMALIZE_DATA) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("entities")) {
            return this.fConfiguration.getFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes") ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("namespaces")) {
            return this.fConfiguration.getFeature("http://xml.org/sax/features/namespaces") ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("validate")) {
            return this.fConfiguration.getFeature(VALIDATION_FEATURE) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("validate-if-schema")) {
            return this.fConfiguration.getFeature(DYNAMIC_VALIDATION) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("element-content-whitespace")) {
            return this.fConfiguration.getFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace") ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase(Constants.DOM_DISALLOW_DOCTYPE)) {
            return this.fConfiguration.getFeature(DISALLOW_DOCTYPE_DECL_FEATURE) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("infoset")) {
            return (!this.fConfiguration.getFeature("http://xml.org/sax/features/namespaces") || !this.fConfiguration.getFeature("namespace-declarations") || !this.fConfiguration.getFeature("http://apache.org/xml/features/include-comments") || !this.fConfiguration.getFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace") || this.fConfiguration.getFeature(DYNAMIC_VALIDATION) || this.fConfiguration.getFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes") || this.fConfiguration.getFeature(NORMALIZE_DATA) || this.fConfiguration.getFeature("http://apache.org/xml/features/create-cdata-nodes")) ? Boolean.FALSE : Boolean.TRUE;
        }
        if (str.equalsIgnoreCase("cdata-sections")) {
            return this.fConfiguration.getFeature("http://apache.org/xml/features/create-cdata-nodes") ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("check-character-normalization") || str.equalsIgnoreCase("normalize-characters")) {
            return Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("namespace-declarations") || str.equalsIgnoreCase("well-formed") || str.equalsIgnoreCase("ignore-unknown-character-denormalizations") || str.equalsIgnoreCase("canonical-form") || str.equalsIgnoreCase(Constants.DOM_SUPPORTED_MEDIATYPES_ONLY) || str.equalsIgnoreCase("split-cdata-sections") || str.equalsIgnoreCase(Constants.DOM_CHARSET_OVERRIDES_XML_ENCODING)) {
            return this.fConfiguration.getFeature(str.toLowerCase(Locale.ENGLISH)) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("error-handler")) {
            DOMErrorHandlerWrapper dOMErrorHandlerWrapper = this.fErrorHandler;
            if (dOMErrorHandlerWrapper != null) {
                return dOMErrorHandlerWrapper.getErrorHandler();
            }
            return null;
        }
        if (str.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER)) {
            try {
                XMLEntityResolver xMLEntityResolver = (XMLEntityResolver) this.fConfiguration.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
                if (xMLEntityResolver != null && (xMLEntityResolver instanceof DOMEntityResolverWrapper)) {
                    return ((DOMEntityResolverWrapper) xMLEntityResolver).getEntityResolver();
                }
            } catch (XMLConfigurationException unused) {
            }
            return null;
        }
        if (str.equalsIgnoreCase("schema-type")) {
            return this.fConfiguration.getProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE);
        }
        if (str.equalsIgnoreCase("schema-location")) {
            return this.fSchemaLocation;
        }
        if (str.equalsIgnoreCase("http://apache.org/xml/properties/internal/symbol-table")) {
            return this.fConfiguration.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        }
        if (str.equalsIgnoreCase("http://apache.org/xml/properties/dom/document-class-name")) {
            return this.fConfiguration.getProperty("http://apache.org/xml/properties/dom/document-class-name");
        }
        String lowerCase = NAMESPACE_GROWTH;
        if (!str.equals(NAMESPACE_GROWTH)) {
            lowerCase = TOLERATE_DUPLICATES;
            if (!str.equals(TOLERATE_DUPLICATES)) {
                lowerCase = str.toLowerCase(Locale.ENGLISH);
            }
        }
        try {
            try {
                return this.fConfiguration.getFeature(lowerCase) ? Boolean.TRUE : Boolean.FALSE;
            } catch (XMLConfigurationException unused2) {
                return this.fConfiguration.getProperty(lowerCase);
            }
        } catch (XMLConfigurationException unused3) {
            throw newFeatureNotFoundError(str);
        }
    }

    @Override // org.w3c.dom.DOMConfiguration
    public DOMStringList getParameterNames() {
        if (this.fRecognizedParameters == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("namespaces");
            arrayList.add("cdata-sections");
            arrayList.add("canonical-form");
            arrayList.add("namespace-declarations");
            arrayList.add("split-cdata-sections");
            arrayList.add("entities");
            arrayList.add("validate-if-schema");
            arrayList.add("validate");
            arrayList.add("datatype-normalization");
            arrayList.add(Constants.DOM_CHARSET_OVERRIDES_XML_ENCODING);
            arrayList.add("check-character-normalization");
            arrayList.add(Constants.DOM_SUPPORTED_MEDIATYPES_ONLY);
            arrayList.add("ignore-unknown-character-denormalizations");
            arrayList.add("normalize-characters");
            arrayList.add("well-formed");
            arrayList.add("infoset");
            arrayList.add(Constants.DOM_DISALLOW_DOCTYPE);
            arrayList.add("element-content-whitespace");
            arrayList.add("comments");
            arrayList.add("error-handler");
            arrayList.add(Constants.DOM_RESOURCE_RESOLVER);
            arrayList.add("schema-location");
            arrayList.add("schema-type");
            this.fRecognizedParameters = new DOMStringListImpl(arrayList);
        }
        return this.fRecognizedParameters;
    }

    @Override // org.w3c.dom.ls.LSParser
    public Document parse(LSInput lSInput) throws LSException {
        XMLInputSource xMLInputSourceDom2xmlInputSource = dom2xmlInputSource(lSInput);
        if (this.fBusy) {
            zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            return null;
        }
        try {
            this.currentThread = Thread.currentThread();
            this.fBusy = true;
            parse(xMLInputSourceDom2xmlInputSource);
            this.fBusy = false;
            if (this.abortNow && this.currentThread.isInterrupted()) {
                this.abortNow = false;
                Thread.interrupted();
            }
        } catch (Exception e) {
            this.fBusy = false;
            if (this.abortNow && this.currentThread.isInterrupted()) {
                Thread.interrupted();
            }
            if (this.abortNow) {
                this.abortNow = false;
                restoreHandlers();
                return null;
            }
            if (e != AbstractDOMParser.Abort.INSTANCE) {
                if (!(e instanceof XMLParseException) && this.fErrorHandler != null) {
                    DOMErrorImpl dOMErrorImpl = new DOMErrorImpl();
                    dOMErrorImpl.fException = e;
                    dOMErrorImpl.fMessage = e.getMessage();
                    dOMErrorImpl.fSeverity = (short) 3;
                    this.fErrorHandler.getErrorHandler().handleError(dOMErrorImpl);
                }
                throw ((LSException) DOMUtil.createLSException((short) 81, e).fillInStackTrace());
            }
        }
        Document document = getDocument();
        dropDocumentReferences();
        return document;
    }

    @Override // org.w3c.dom.ls.LSParser
    public Document parseURI(String str) throws LSException {
        if (this.fBusy) {
            zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            return null;
        }
        XMLInputSource xMLInputSource = new XMLInputSource(null, str, null, false);
        try {
            this.currentThread = Thread.currentThread();
            this.fBusy = true;
            parse(xMLInputSource);
            this.fBusy = false;
            if (this.abortNow && this.currentThread.isInterrupted()) {
                this.abortNow = false;
                Thread.interrupted();
            }
        } catch (Exception e) {
            this.fBusy = false;
            if (this.abortNow && this.currentThread.isInterrupted()) {
                Thread.interrupted();
            }
            if (this.abortNow) {
                this.abortNow = false;
                restoreHandlers();
                return null;
            }
            if (e != AbstractDOMParser.Abort.INSTANCE) {
                if (!(e instanceof XMLParseException) && this.fErrorHandler != null) {
                    DOMErrorImpl dOMErrorImpl = new DOMErrorImpl();
                    dOMErrorImpl.fException = e;
                    dOMErrorImpl.fMessage = e.getMessage();
                    dOMErrorImpl.fSeverity = (short) 3;
                    this.fErrorHandler.getErrorHandler().handleError(dOMErrorImpl);
                }
                throw ((LSException) DOMUtil.createLSException((short) 81, e).fillInStackTrace());
            }
        }
        Document document = getDocument();
        dropDocumentReferences();
        return document;
    }

    @Override // org.w3c.dom.ls.LSParser
    public Node parseWithContext(LSInput lSInput, Node node, short s) throws DOMException, LSException {
        throw new DOMException((short) 9, "Not supported");
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractDOMParser, com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.parsers.XMLParser
    public void reset() {
        super.reset();
        this.fNamespaceDeclarations = this.fConfiguration.getFeature("namespace-declarations");
        Stack<Boolean> stack = this.fSkippedElemStack;
        if (stack != null) {
            stack.removeAllElements();
        }
        this.fRejectedElementDepth = 0;
        this.fFilterReject = false;
        this.fSchemaType = null;
    }

    @Override // org.w3c.dom.ls.LSParser
    public void setFilter(LSParserFilter lSParserFilter) {
        this.fDOMFilter = lSParserFilter;
        if (this.fSkippedElemStack == null) {
            this.fSkippedElemStack = new Stack<>();
        }
    }

    @Override // org.w3c.dom.DOMConfiguration
    public void setParameter(String str, Object obj) throws DOMException {
        boolean z = obj instanceof Boolean;
        String lowerCase = TOLERATE_DUPLICATES;
        String str2 = DOMMessageFormatter.DOM_DOMAIN;
        if (!z) {
            try {
                if (str.equalsIgnoreCase("error-handler")) {
                    if (!(obj instanceof DOMErrorHandler) && obj != null) {
                        zi0.a(17, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, MsgKey.ER_TYPE_MISMATCH_ERR, new Object[]{str}));
                        return;
                    }
                    DOMErrorHandlerWrapper dOMErrorHandlerWrapper = new DOMErrorHandlerWrapper((DOMErrorHandler) obj);
                    this.fErrorHandler = dOMErrorHandlerWrapper;
                    this.fConfiguration.setProperty(XMLSchemaLoader.ERROR_HANDLER, dOMErrorHandlerWrapper);
                    return;
                }
                if (str.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER)) {
                    if ((obj instanceof LSResourceResolver) || obj == null) {
                        this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/entity-resolver", new DOMEntityResolverWrapper((LSResourceResolver) obj));
                        return;
                    } else {
                        zi0.a(17, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, MsgKey.ER_TYPE_MISMATCH_ERR, new Object[]{str}));
                        return;
                    }
                }
                if (str.equalsIgnoreCase("schema-location")) {
                    if (!(obj instanceof String) && obj != null) {
                        zi0.a(17, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, MsgKey.ER_TYPE_MISMATCH_ERR, new Object[]{str}));
                        return;
                    }
                    if (obj == null) {
                        this.fSchemaLocation = null;
                        this.fConfiguration.setProperty(JAXPConstants.JAXP_SCHEMA_SOURCE, null);
                        return;
                    }
                    this.fSchemaLocation = (String) obj;
                    StringTokenizer stringTokenizer = new StringTokenizer(this.fSchemaLocation, " \n\t\r");
                    if (!stringTokenizer.hasMoreTokens()) {
                        this.fConfiguration.setProperty(JAXPConstants.JAXP_SCHEMA_SOURCE, obj);
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(stringTokenizer.nextToken());
                    while (stringTokenizer.hasMoreTokens()) {
                        arrayList.add(stringTokenizer.nextToken());
                    }
                    this.fConfiguration.setProperty(JAXPConstants.JAXP_SCHEMA_SOURCE, arrayList.toArray());
                    return;
                }
                if (!str.equalsIgnoreCase("schema-type")) {
                    if (str.equalsIgnoreCase("http://apache.org/xml/properties/dom/document-class-name")) {
                        this.fConfiguration.setProperty("http://apache.org/xml/properties/dom/document-class-name", obj);
                        return;
                    }
                    String lowerCase2 = str.toLowerCase(Locale.ENGLISH);
                    try {
                        try {
                            this.fConfiguration.setProperty(lowerCase2, obj);
                            return;
                        } catch (XMLConfigurationException unused) {
                            throw newFeatureNotFoundError(str);
                        }
                    } catch (XMLConfigurationException unused2) {
                        if (str.equals(NAMESPACE_GROWTH)) {
                            lowerCase = NAMESPACE_GROWTH;
                        } else if (!str.equals(TOLERATE_DUPLICATES)) {
                            lowerCase = lowerCase2;
                        }
                        this.fConfiguration.getFeature(lowerCase);
                        throw newTypeMismatchError(str);
                    }
                }
                if (!(obj instanceof String) && obj != null) {
                    zi0.a(17, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, MsgKey.ER_TYPE_MISMATCH_ERR, new Object[]{str}));
                    return;
                }
                if (obj == null) {
                    this.fConfiguration.setFeature(XMLSCHEMA, false);
                    this.fConfiguration.setFeature(XMLSCHEMA_FULL_CHECKING, false);
                    this.fConfiguration.setProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE, null);
                    this.fSchemaType = null;
                    return;
                }
                String str3 = Constants.NS_XMLSCHEMA;
                if (obj.equals(str3)) {
                    this.fConfiguration.setFeature(XMLSCHEMA, true);
                    this.fConfiguration.setFeature(XMLSCHEMA_FULL_CHECKING, true);
                    this.fConfiguration.setProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE, str3);
                    this.fSchemaType = str3;
                    return;
                }
                String str4 = Constants.NS_DTD;
                if (obj.equals(str4)) {
                    this.fConfiguration.setFeature(XMLSCHEMA, false);
                    this.fConfiguration.setFeature(XMLSCHEMA_FULL_CHECKING, false);
                    this.fConfiguration.setProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE, str4);
                    this.fSchemaType = str4;
                    return;
                }
                return;
            } catch (XMLConfigurationException unused3) {
                return;
            }
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        try {
            if (str.equalsIgnoreCase("comments")) {
                this.fConfiguration.setFeature("http://apache.org/xml/features/include-comments", zBooleanValue);
                return;
            }
            if (str.equalsIgnoreCase("datatype-normalization")) {
                this.fConfiguration.setFeature(NORMALIZE_DATA, zBooleanValue);
                return;
            }
            if (str.equalsIgnoreCase("entities")) {
                this.fConfiguration.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", zBooleanValue);
                return;
            }
            if (str.equalsIgnoreCase(Constants.DOM_DISALLOW_DOCTYPE)) {
                this.fConfiguration.setFeature(DISALLOW_DOCTYPE_DECL_FEATURE, zBooleanValue);
                return;
            }
            if (str.equalsIgnoreCase(Constants.DOM_SUPPORTED_MEDIATYPES_ONLY) || str.equalsIgnoreCase("normalize-characters") || str.equalsIgnoreCase("check-character-normalization") || str.equalsIgnoreCase("canonical-form")) {
                if (zBooleanValue) {
                    throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{str}));
                }
                return;
            }
            if (str.equalsIgnoreCase("namespaces")) {
                this.fConfiguration.setFeature("http://xml.org/sax/features/namespaces", zBooleanValue);
                return;
            }
            try {
                if (str.equalsIgnoreCase("infoset")) {
                    if (zBooleanValue) {
                        this.fConfiguration.setFeature("http://xml.org/sax/features/namespaces", true);
                        this.fConfiguration.setFeature("namespace-declarations", true);
                        this.fConfiguration.setFeature("http://apache.org/xml/features/include-comments", true);
                        this.fConfiguration.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", true);
                        this.fConfiguration.setFeature(DYNAMIC_VALIDATION, false);
                        this.fConfiguration.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", false);
                        this.fConfiguration.setFeature(NORMALIZE_DATA, false);
                        this.fConfiguration.setFeature("http://apache.org/xml/features/create-cdata-nodes", false);
                        return;
                    }
                    return;
                }
                if (str.equalsIgnoreCase("cdata-sections")) {
                    this.fConfiguration.setFeature("http://apache.org/xml/features/create-cdata-nodes", zBooleanValue);
                    return;
                }
                if (str.equalsIgnoreCase("namespace-declarations")) {
                    this.fConfiguration.setFeature("namespace-declarations", zBooleanValue);
                    return;
                }
                if (!str.equalsIgnoreCase("well-formed") && !str.equalsIgnoreCase("ignore-unknown-character-denormalizations")) {
                    if (str.equalsIgnoreCase("validate")) {
                        this.fConfiguration.setFeature(VALIDATION_FEATURE, zBooleanValue);
                        if (this.fSchemaType != Constants.NS_DTD) {
                            this.fConfiguration.setFeature(XMLSCHEMA, zBooleanValue);
                            this.fConfiguration.setFeature(XMLSCHEMA_FULL_CHECKING, zBooleanValue);
                        }
                        if (zBooleanValue) {
                            this.fConfiguration.setFeature(DYNAMIC_VALIDATION, false);
                            return;
                        }
                        return;
                    }
                    if (str.equalsIgnoreCase("validate-if-schema")) {
                        this.fConfiguration.setFeature(DYNAMIC_VALIDATION, zBooleanValue);
                        if (zBooleanValue) {
                            this.fConfiguration.setFeature(VALIDATION_FEATURE, false);
                            return;
                        }
                        return;
                    }
                    if (str.equalsIgnoreCase("element-content-whitespace")) {
                        this.fConfiguration.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", zBooleanValue);
                        return;
                    }
                    if (str.equalsIgnoreCase(Constants.DOM_PSVI)) {
                        this.fConfiguration.setFeature(PSVI_AUGMENT, true);
                        this.fConfiguration.setProperty("http://apache.org/xml/properties/dom/document-class-name", "com.sun.org.apache.xerces.internal.dom.PSVIDocumentImpl");
                        return;
                    }
                    if (str.equals(NAMESPACE_GROWTH)) {
                        lowerCase = NAMESPACE_GROWTH;
                    } else if (!str.equals(TOLERATE_DUPLICATES)) {
                        lowerCase = str.toLowerCase(Locale.ENGLISH);
                    }
                    this.fConfiguration.setFeature(lowerCase, zBooleanValue);
                    return;
                }
                if (!zBooleanValue) {
                    throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{str}));
                }
            } catch (XMLConfigurationException unused4) {
                str2 = DOMMessageFormatter.DOM_DOMAIN;
                zi0.a(8, DOMMessageFormatter.formatMessage(str2, "FEATURE_NOT_FOUND", new Object[]{str}));
            }
        } catch (XMLConfigurationException unused5) {
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.AbstractDOMParser, com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) {
        if (!this.fNamespaceDeclarations && this.fNamespaceAware) {
            for (int length = xMLAttributes.getLength() - 1; length >= 0; length--) {
                String str = XMLSymbols.PREFIX_XMLNS;
                if (str == xMLAttributes.getPrefix(length) || str == xMLAttributes.getQName(length)) {
                    xMLAttributes.removeAttributeAt(length);
                }
            }
        }
        super.startElement(qName, xMLAttributes, augmentations);
    }

    public DOMParserImpl(XMLParserConfiguration xMLParserConfiguration, String str) {
        this(xMLParserConfiguration);
        if (str != null) {
            String str2 = Constants.NS_DTD;
            if (str.equals(str2)) {
                this.fConfiguration.setProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE, str2);
                this.fSchemaType = str2;
            } else {
                Object obj = Constants.NS_XMLSCHEMA;
                if (str.equals(obj)) {
                    this.fConfiguration.setProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE, obj);
                }
            }
        }
    }

    public DOMParserImpl(SymbolTable symbolTable) {
        this(new XIncludeAwareParserConfiguration());
        this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
    }

    public DOMParserImpl(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool) {
        this(new XIncludeAwareParserConfiguration());
        this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
        this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", xMLGrammarPool);
    }
}
