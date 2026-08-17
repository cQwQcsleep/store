package com.sun.org.apache.xerces.internal.xinclude;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.impl.io.MalformedByteSequenceException;
import com.sun.org.apache.xerces.internal.parsers.XIncludeParserConfiguration;
import com.sun.org.apache.xerces.internal.parsers.XPointerParserConfiguration;
import com.sun.org.apache.xerces.internal.util.AugmentationsImpl;
import com.sun.org.apache.xerces.internal.util.HTTPInputSource;
import com.sun.org.apache.xerces.internal.util.IntStack;
import com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.URI;
import com.sun.org.apache.xerces.internal.util.XMLAttributesImpl;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLLocatorWrapper;
import com.sun.org.apache.xerces.internal.util.XMLResourceIdentifierImpl;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLDTDHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDFilter;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentFilter;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import com.sun.org.apache.xerces.internal.xpointer.XPointerHandler;
import com.sun.org.apache.xerces.internal.xpointer.XPointerProcessor;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.xml.catalog.CatalogException;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.catalog.CatalogManager;
import javax.xml.catalog.CatalogResolver;
import javax.xml.transform.Source;
import jdk.xml.internal.JdkXmlUtils;
import org.xml.sax.InputSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XIncludeHandler implements XMLComponent, XMLDocumentFilter, XMLDTDFilter {
    protected static final String ALLOW_UE_AND_NOTATION_EVENTS = "http://xml.org/sax/features/allow-dtd-events-after-endDTD";
    protected static final String BUFFER_SIZE = "http://apache.org/xml/properties/input-buffer-size";
    public static final String CURRENT_BASE_URI = "currentBaseURI";
    protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final Boolean[] FEATURE_DEFAULTS;
    public static final String HTTP_ACCEPT = "Accept";
    public static final String HTTP_ACCEPT_LANGUAGE = "Accept-Language";
    private static final int INITIAL_SIZE = 8;
    protected static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private static final QName NEW_NS_ATTR_QNAME;
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    private static final Object[] PROPERTY_DEFAULTS;
    private static final String[] RECOGNIZED_FEATURES;
    private static final String[] RECOGNIZED_PROPERTIES;
    protected static final String SCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    protected static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private static final int STATE_EXPECT_FALLBACK = 3;
    private static final int STATE_IGNORE = 2;
    private static final int STATE_NORMAL_PROCESSING = 1;
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    private static final String XINCLUDE_BASE;
    protected static final String XINCLUDE_FIXUP_BASE_URIS = "http://apache.org/xml/features/xinclude/fixup-base-uris";
    protected static final String XINCLUDE_FIXUP_LANGUAGE = "http://apache.org/xml/features/xinclude/fixup-language";
    private static final String XINCLUDE_LANG;
    private static final QName XML_BASE_QNAME;
    private static final QName XML_LANG_QNAME;
    protected static final String XML_SECURITY_PROPERTY_MANAGER = "jdk.xml.xmlSecurityPropertyManager";
    public static final String XPOINTER = "xpointer";
    private static final char[] gAfterEscaping1;
    private static final char[] gAfterEscaping2;
    private static final char[] gHexChs;
    private static final boolean[] gNeedEscaping;
    protected final Stack<String> fBaseURI;
    protected final IntStack fBaseURIScope;
    CatalogFeatures fCatalogFeatures;
    private String fCatalogFile;
    CatalogResolver fCatalogResolver;
    protected XMLParserConfiguration fChildConfig;
    protected final XMLResourceIdentifier fCurrentBaseURI;
    protected String fCurrentLanguage;
    protected XMLDTDHandler fDTDHandler;
    protected XMLDTDSource fDTDSource;
    private String fDefer;
    protected XMLLocator fDocLocation;
    protected XMLDocumentHandler fDocumentHandler;
    protected XMLDocumentSource fDocumentSource;
    protected XMLEntityResolver fEntityResolver;
    protected XMLErrorReporter fErrorReporter;
    protected final Stack<String> fExpandedSystemID;
    boolean fHasIncludeReportedContent;
    protected String fHrefFromParent;
    private boolean fInDTD;
    private boolean fIsXML11;
    protected final IntStack fLanguageScope;
    protected final Stack<String> fLanguageStack;
    protected final Stack<String> fLiteralSystemID;
    protected XIncludeNamespaceSupport fNamespaceContext;
    private final List<Notation> fNotations;
    protected String fParentRelativeURI;
    protected XIncludeHandler fParentXIncludeHandler;
    private String fPrefer;
    private String fResolve;
    private int fResultDepth;
    private boolean[] fSawFallback;
    private boolean[] fSawInclude;
    protected XMLSecurityManager fSecurityManager;
    protected XMLSecurityPropertyManager fSecurityPropertyMgr;
    private boolean fSeenRootElement;
    private boolean fSendUEAndNotationEvents;
    protected ParserConfigurationSettings fSettings;
    private int[] fState;
    protected SymbolTable fSymbolTable;
    private final List<UnparsedEntity> fUnparsedEntities;
    protected XIncludeTextReader fXInclude10TextReader;
    protected XIncludeTextReader fXInclude11TextReader;
    protected XMLParserConfiguration fXIncludeChildConfig;
    protected XMLParserConfiguration fXPointerChildConfig;
    public static final String XINCLUDE_NS_URI = "http://www.w3.org/2001/XInclude".intern();
    public static final String XINCLUDE_INCLUDE = Constants.ELEMNAME_INCLUDE_STRING.intern();
    public static final String XINCLUDE_FALLBACK = Constants.ELEMNAME_FALLBACK_STRING.intern();
    public static final String XINCLUDE_PARSE_XML = "xml".intern();
    public static final String XINCLUDE_PARSE_TEXT = "text".intern();
    public static final String XINCLUDE_ATTR_HREF = Constants.ATTRNAME_HREF.intern();
    public static final String XINCLUDE_ATTR_PARSE = "parse".intern();
    public static final String XINCLUDE_ATTR_ENCODING = "encoding".intern();
    public static final String XINCLUDE_ATTR_ACCEPT = "accept".intern();
    public static final String XINCLUDE_ATTR_ACCEPT_LANGUAGE = "accept-language".intern();
    public static final String XINCLUDE_INCLUDED = "[included]".intern();
    protected int fBufferSize = 8192;
    protected XPointerProcessor fXPtrProcessor = null;
    protected XMLLocatorWrapper fXIncludeLocator = new XMLLocatorWrapper();
    protected XIncludeMessageFormatter fXIncludeMessageFormatter = new XIncludeMessageFormatter();
    private boolean fFixupBaseURIs = true;
    private boolean fFixupLanguage = true;
    private boolean fNeedCopyFeatures = true;
    private boolean fUseCatalog = true;
    private int fDepth = 0;

    public static class Notation {
        public Augmentations augmentations;
        public String baseURI;
        public String expandedSystemId;
        public String name;
        public String publicId;
        public String systemId;

        public boolean equals(Object obj) {
            if (obj != this) {
                return (obj instanceof Notation) && Objects.equals(this.name, ((Notation) obj).name);
            }
            return true;
        }

        public int hashCode() {
            return Objects.hashCode(this.name);
        }

        public boolean isDuplicate(Object obj) {
            if (obj != null && (obj instanceof Notation)) {
                Notation notation = (Notation) obj;
                if (Objects.equals(this.name, notation.name) && Objects.equals(this.publicId, notation.publicId) && Objects.equals(this.expandedSystemId, notation.expandedSystemId)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class UnparsedEntity {
        public Augmentations augmentations;
        public String baseURI;
        public String expandedSystemId;
        public String name;
        public String notation;
        public String publicId;
        public String systemId;

        public boolean equals(Object obj) {
            if (obj != this) {
                return (obj instanceof UnparsedEntity) && Objects.equals(this.name, ((UnparsedEntity) obj).name);
            }
            return true;
        }

        public int hashCode() {
            return Objects.hashCode(this.name);
        }

        public boolean isDuplicate(Object obj) {
            if (obj != null && (obj instanceof UnparsedEntity)) {
                UnparsedEntity unparsedEntity = (UnparsedEntity) obj;
                if (Objects.equals(this.name, unparsedEntity.name) && Objects.equals(this.publicId, unparsedEntity.publicId) && Objects.equals(this.expandedSystemId, unparsedEntity.expandedSystemId) && Objects.equals(this.notation, unparsedEntity.notation)) {
                    return true;
                }
            }
            return false;
        }
    }

    static {
        String strIntern = "base".intern();
        XINCLUDE_BASE = strIntern;
        String str = XMLSymbols.PREFIX_XML;
        String strIntern2 = (str + ":" + strIntern).intern();
        String str2 = NamespaceContext.XML_URI;
        XML_BASE_QNAME = new QName(str, strIntern, strIntern2, str2);
        String strIntern3 = "lang".intern();
        XINCLUDE_LANG = strIntern3;
        XML_LANG_QNAME = new QName(str, strIntern3, (str + ":" + strIntern3).intern(), str2);
        String str3 = XMLSymbols.PREFIX_XMLNS;
        NEW_NS_ATTR_QNAME = new QName(str3, "", str3 + ":", NamespaceContext.XMLNS_URI);
        RECOGNIZED_FEATURES = new String[]{ALLOW_UE_AND_NOTATION_EVENTS, XINCLUDE_FIXUP_BASE_URIS, XINCLUDE_FIXUP_LANGUAGE};
        Boolean bool = Boolean.TRUE;
        FEATURE_DEFAULTS = new Boolean[]{bool, bool, bool};
        RECOGNIZED_PROPERTIES = new String[]{"http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/security-manager", BUFFER_SIZE};
        PROPERTY_DEFAULTS = new Object[]{null, null, null, 8192};
        gNeedEscaping = new boolean[128];
        gAfterEscaping1 = new char[128];
        gAfterEscaping2 = new char[128];
        gHexChs = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr = {' ', '<', '>', '\"', '{', '}', '|', '\\', '^', '`'};
        for (int i = 0; i < 10; i++) {
            char c = cArr[i];
            gNeedEscaping[c] = true;
            char[] cArr2 = gAfterEscaping1;
            char[] cArr3 = gHexChs;
            cArr2[c] = cArr3[c >> 4];
            gAfterEscaping2[c] = cArr3[c & 15];
        }
    }

    public XIncludeHandler() {
        boolean[] zArr = new boolean[8];
        this.fSawInclude = zArr;
        boolean[] zArr2 = new boolean[8];
        this.fSawFallback = zArr2;
        int[] iArr = new int[8];
        this.fState = iArr;
        zArr2[0] = false;
        zArr[0] = false;
        iArr[0] = 1;
        this.fNotations = new ArrayList();
        this.fUnparsedEntities = new ArrayList();
        this.fBaseURIScope = new IntStack();
        this.fBaseURI = new Stack<>();
        this.fLiteralSystemID = new Stack<>();
        this.fExpandedSystemID = new Stack<>();
        this.fCurrentBaseURI = new XMLResourceIdentifierImpl();
        this.fLanguageScope = new IntStack();
        this.fLanguageStack = new Stack<>();
        this.fCurrentLanguage = null;
    }

    private void checkMultipleRootElements() {
        if (getRootElementProcessed()) {
            reportFatalError("MultipleRootElements");
        }
        setRootElementProcessed(true);
    }

    private void checkWhitespace(XMLString xMLString) {
        int i = xMLString.offset;
        int i2 = xMLString.length + i;
        while (i < i2) {
            if (!XMLChar.isSpace(xMLString.ch[i])) {
                reportFatalError("ContentIllegalAtTopLevel");
                return;
            }
            i++;
        }
    }

    private void copyFeatures1(Enumeration<Object> enumeration, String str, XMLComponentManager xMLComponentManager, ParserConfigurationSettings parserConfigurationSettings) {
        while (enumeration.hasMoreElements()) {
            String str2 = str + ((String) enumeration.nextElement());
            parserConfigurationSettings.addRecognizedFeatures(new String[]{str2});
            try {
                parserConfigurationSettings.setFeature(str2, xMLComponentManager.getFeature(str2));
            } catch (XMLConfigurationException unused) {
            }
        }
    }

    private XMLInputSource createInputSource(String str, String str2, String str3, String str4, String str5) {
        HTTPInputSource hTTPInputSource = new HTTPInputSource(str, str2, str3);
        if (str4 != null && str4.length() > 0) {
            hTTPInputSource.setHTTPRequestProperty(HTTP_ACCEPT, str4);
        }
        if (str5 != null && str5.length() > 0) {
            hTTPInputSource.setHTTPRequestProperty(HTTP_ACCEPT_LANGUAGE, str5);
        }
        return hTTPInputSource;
    }

    /* JADX WARN: Code duplicated, block: B:59:0x00e1  */
    private String escapeHref(String str) {
        int iSupplemental;
        char cCharAt;
        int length = str.length();
        StringBuilder sb = new StringBuilder(length * 3);
        int i = 0;
        while (i < length && (cCharAt = str.charAt(i)) <= '~') {
            if (cCharAt < ' ') {
                return str;
            }
            if (gNeedEscaping[cCharAt]) {
                sb.append('%');
                sb.append(gAfterEscaping1[cCharAt]);
                sb.append(gAfterEscaping2[cCharAt]);
            } else {
                sb.append(cCharAt);
            }
            i++;
        }
        if (i < length) {
            int i2 = i;
            while (i2 < length) {
                char cCharAt2 = str.charAt(i2);
                if ((cCharAt2 < ' ' || cCharAt2 > '~') && ((cCharAt2 < 160 || cCharAt2 > 55295) && ((cCharAt2 < 63744 || cCharAt2 > 64975) && (cCharAt2 < 65008 || cCharAt2 > 65519)))) {
                    if (XMLChar.isHighSurrogate(cCharAt2) && (i2 = i2 + 1) < length) {
                        char cCharAt3 = str.charAt(i2);
                        if (!XMLChar.isLowSurrogate(cCharAt3) || (iSupplemental = XMLChar.supplemental(cCharAt2, cCharAt3)) >= 983040 || (iSupplemental & 65535) > 65533) {
                        }
                    }
                }
                i2++;
            }
            try {
                byte[] bytes = str.substring(i).getBytes("UTF-8");
                int length2 = bytes.length;
                for (byte b : bytes) {
                    if (b < 0) {
                        int i3 = b + 256;
                        sb.append('%');
                        char[] cArr = gHexChs;
                        sb.append(cArr[i3 >> 4]);
                        sb.append(cArr[i3 & 15]);
                    } else if (gNeedEscaping[b]) {
                        sb.append('%');
                        sb.append(gAfterEscaping1[b]);
                        sb.append(gAfterEscaping2[b]);
                    } else {
                        sb.append((char) b);
                    }
                }
                length = length2;
                if (sb.length() != length) {
                    return sb.toString();
                }
            } catch (UnsupportedEncodingException unused) {
            }
        } else if (sb.length() != length) {
            return sb.toString();
        }
        return str;
    }

    private String getIncludeParentBaseURI() {
        int includeParentDepth = getIncludeParentDepth();
        return (isRootDocument() || includeParentDepth != 0) ? getBaseURI(includeParentDepth) : this.fParentXIncludeHandler.getIncludeParentBaseURI();
    }

    private int getIncludeParentDepth() {
        for (int i = this.fDepth - 1; i >= 0; i--) {
            if (!getSawInclude(i) && !getSawFallback(i)) {
                return i;
            }
        }
        return 0;
    }

    private String getIncludeParentLanguage() {
        int includeParentDepth = getIncludeParentDepth();
        return (isRootDocument() || includeParentDepth != 0) ? getLanguage(includeParentDepth) : this.fParentXIncludeHandler.getIncludeParentLanguage();
    }

    private int getResultDepth() {
        return this.fResultDepth;
    }

    private boolean getRootElementProcessed() {
        return isRootDocument() ? this.fSeenRootElement : this.fParentXIncludeHandler.getRootElementProcessed();
    }

    private boolean isValidInHTTPHeader(String str) {
        for (int length = str.length() - 1; length >= 0; length--) {
            char cCharAt = str.charAt(length);
            if (cCharAt < ' ' || cCharAt > '~') {
                return false;
            }
        }
        return true;
    }

    private void reportError(String str, Object[] objArr, short s, Exception exc) {
        XMLErrorReporter xMLErrorReporter = this.fErrorReporter;
        if (xMLErrorReporter != null) {
            xMLErrorReporter.reportError(XIncludeMessageFormatter.XINCLUDE_DOMAIN, str, objArr, s, exc);
        }
    }

    private int scopeOfBaseURI(int i) {
        for (int size = this.fBaseURIScope.size() - 1; size >= 0; size--) {
            if (this.fBaseURIScope.elementAt(size) <= i) {
                return size;
            }
        }
        return -1;
    }

    private int scopeOfLanguage(int i) {
        for (int size = this.fLanguageScope.size() - 1; size >= 0; size--) {
            if (this.fLanguageScope.elementAt(size) <= i) {
                return size;
            }
        }
        return -1;
    }

    private void setErrorReporter(XMLErrorReporter xMLErrorReporter) {
        this.fErrorReporter = xMLErrorReporter;
        if (xMLErrorReporter != null) {
            xMLErrorReporter.putMessageFormatter(XIncludeMessageFormatter.XINCLUDE_DOMAIN, this.fXIncludeMessageFormatter);
            XMLLocator xMLLocator = this.fDocLocation;
            if (xMLLocator != null) {
                this.fErrorReporter.setDocumentLocator(xMLLocator);
            }
        }
    }

    private void setRootElementProcessed(boolean z) {
        if (isRootDocument()) {
            this.fSeenRootElement = z;
        } else {
            this.fParentXIncludeHandler.setRootElementProcessed(z);
        }
    }

    private void setupCurrentBaseURI(XMLLocator xMLLocator) {
        this.fCurrentBaseURI.setBaseSystemId(xMLLocator.getBaseSystemId());
        String literalSystemId = xMLLocator.getLiteralSystemId();
        XMLResourceIdentifier xMLResourceIdentifier = this.fCurrentBaseURI;
        if (literalSystemId != null) {
            xMLResourceIdentifier.setLiteralSystemId(xMLLocator.getLiteralSystemId());
        } else {
            xMLResourceIdentifier.setLiteralSystemId(this.fHrefFromParent);
        }
        String expandedSystemId = xMLLocator.getExpandedSystemId();
        if (expandedSystemId == null) {
            try {
                expandedSystemId = XMLEntityManager.expandSystemId(this.fCurrentBaseURI.getLiteralSystemId(), this.fCurrentBaseURI.getBaseSystemId(), false);
                if (expandedSystemId == null) {
                    expandedSystemId = this.fCurrentBaseURI.getLiteralSystemId();
                }
            } catch (URI.MalformedURIException unused) {
                reportFatalError("ExpandedSystemId");
            }
        }
        this.fCurrentBaseURI.setExpandedSystemId(expandedSystemId);
    }

    public void addNotation(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) {
        Notation notation = new Notation();
        notation.name = str;
        notation.systemId = xMLResourceIdentifier.getLiteralSystemId();
        notation.publicId = xMLResourceIdentifier.getPublicId();
        notation.baseURI = xMLResourceIdentifier.getBaseSystemId();
        notation.expandedSystemId = xMLResourceIdentifier.getExpandedSystemId();
        notation.augmentations = augmentations;
        this.fNotations.add(notation);
    }

    public void addUnparsedEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) {
        UnparsedEntity unparsedEntity = new UnparsedEntity();
        unparsedEntity.name = str;
        unparsedEntity.systemId = xMLResourceIdentifier.getLiteralSystemId();
        unparsedEntity.publicId = xMLResourceIdentifier.getPublicId();
        unparsedEntity.baseURI = xMLResourceIdentifier.getBaseSystemId();
        unparsedEntity.expandedSystemId = xMLResourceIdentifier.getExpandedSystemId();
        unparsedEntity.notation = str2;
        unparsedEntity.augmentations = augmentations;
        this.fUnparsedEntities.add(unparsedEntity);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void attributeDecl(String str, String str2, String str3, String[] strArr, String str4, XMLString xMLString, XMLString xMLString2, Augmentations augmentations) throws XNIException {
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.attributeDecl(str, str2, str3, strArr, str4, xMLString, xMLString2, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void characters(XMLString xMLString, Augmentations augmentations) throws XNIException {
        if (getState() == 1) {
            if (this.fResultDepth == 0) {
                checkWhitespace(xMLString);
            } else if (this.fDocumentHandler != null) {
                this.fDepth++;
                this.fDocumentHandler.characters(xMLString, modifyAugmentations(augmentations));
                this.fDepth--;
            }
        }
    }

    public void checkAndSendNotation(Notation notation) {
        XMLDTDHandler xMLDTDHandler;
        if (!isRootDocument()) {
            this.fParentXIncludeHandler.checkAndSendNotation(notation);
            return;
        }
        int iIndexOf = this.fNotations.indexOf(notation);
        if (iIndexOf != -1) {
            if (notation.isDuplicate(this.fNotations.get(iIndexOf))) {
                return;
            }
            reportFatalError("NonDuplicateNotation", new Object[]{notation.name});
        } else {
            XMLResourceIdentifierImpl xMLResourceIdentifierImpl = new XMLResourceIdentifierImpl(notation.publicId, notation.systemId, notation.baseURI, notation.expandedSystemId);
            addNotation(notation.name, xMLResourceIdentifierImpl, notation.augmentations);
            if (!this.fSendUEAndNotationEvents || (xMLDTDHandler = this.fDTDHandler) == null) {
                return;
            }
            xMLDTDHandler.notationDecl(notation.name, xMLResourceIdentifierImpl, notation.augmentations);
        }
    }

    public void checkAndSendUnparsedEntity(UnparsedEntity unparsedEntity) {
        XMLDTDHandler xMLDTDHandler;
        if (!isRootDocument()) {
            this.fParentXIncludeHandler.checkAndSendUnparsedEntity(unparsedEntity);
            return;
        }
        int iIndexOf = this.fUnparsedEntities.indexOf(unparsedEntity);
        if (iIndexOf != -1) {
            if (unparsedEntity.isDuplicate(this.fUnparsedEntities.get(iIndexOf))) {
                return;
            }
            reportFatalError("NonDuplicateUnparsedEntity", new Object[]{unparsedEntity.name});
        } else {
            XMLResourceIdentifierImpl xMLResourceIdentifierImpl = new XMLResourceIdentifierImpl(unparsedEntity.publicId, unparsedEntity.systemId, unparsedEntity.baseURI, unparsedEntity.expandedSystemId);
            addUnparsedEntity(unparsedEntity.name, xMLResourceIdentifierImpl, unparsedEntity.notation, unparsedEntity.augmentations);
            if (!this.fSendUEAndNotationEvents || (xMLDTDHandler = this.fDTDHandler) == null) {
                return;
            }
            xMLDTDHandler.unparsedEntityDecl(unparsedEntity.name, xMLResourceIdentifierImpl, unparsedEntity.notation, unparsedEntity.augmentations);
        }
    }

    public void checkNotation(String str) {
        Notation notation = new Notation();
        notation.name = str;
        int iIndexOf = this.fNotations.indexOf(notation);
        if (iIndexOf != -1) {
            checkAndSendNotation(this.fNotations.get(iIndexOf));
        }
    }

    public void checkUnparsedEntity(String str) {
        UnparsedEntity unparsedEntity = new UnparsedEntity();
        unparsedEntity.name = str;
        int iIndexOf = this.fUnparsedEntities.indexOf(unparsedEntity);
        if (iIndexOf != -1) {
            UnparsedEntity unparsedEntity2 = this.fUnparsedEntities.get(iIndexOf);
            checkNotation(unparsedEntity2.notation);
            checkAndSendUnparsedEntity(unparsedEntity2);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void comment(XMLString xMLString, Augmentations augmentations) throws XNIException {
        if (this.fInDTD) {
            XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
            if (xMLDTDHandler != null) {
                xMLDTDHandler.comment(xMLString, augmentations);
                return;
            }
            return;
        }
        if (this.fDocumentHandler == null || getState() != 1) {
            return;
        }
        this.fDepth++;
        this.fDocumentHandler.comment(xMLString, modifyAugmentations(augmentations));
        this.fDepth--;
    }

    public void copyFeatures(XMLComponentManager xMLComponentManager, ParserConfigurationSettings parserConfigurationSettings) {
        copyFeatures1(com.sun.org.apache.xerces.internal.impl.Constants.getXercesFeatures(), com.sun.org.apache.xerces.internal.impl.Constants.XERCES_FEATURE_PREFIX, xMLComponentManager, parserConfigurationSettings);
        copyFeatures1(com.sun.org.apache.xerces.internal.impl.Constants.getSAXFeatures(), com.sun.org.apache.xerces.internal.impl.Constants.SAX_FEATURE_PREFIX, xMLComponentManager, parserConfigurationSettings);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void doctypeDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler;
        if (!isRootDocument() || (xMLDocumentHandler = this.fDocumentHandler) == null) {
            return;
        }
        xMLDocumentHandler.doctypeDecl(str, str2, str3, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void elementDecl(String str, String str2, Augmentations augmentations) throws XNIException {
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.elementDecl(str, str2, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void emptyElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
        int i = this.fDepth;
        this.fDepth = i + 1;
        int state = getState(i);
        if (state == 3 && getState(this.fDepth - 2) == 3) {
            setState(2);
        } else {
            setState(state);
        }
        processXMLBaseAttributes(xMLAttributes);
        if (this.fFixupLanguage) {
            processXMLLangAttributes(xMLAttributes);
        }
        if (isIncludeElement(qName)) {
            if (handleIncludeElement(xMLAttributes)) {
                setState(2);
            } else {
                reportFatalError("NoFallback", new Object[]{xMLAttributes.getValue(null, Constants.ATTRNAME_HREF)});
            }
        } else if (isFallbackElement(qName)) {
            handleFallbackElement();
        } else if (hasXIncludeNamespace(qName)) {
            if (getSawInclude(this.fDepth - 1)) {
                reportFatalError("IncludeChild", new Object[]{qName.rawname});
            }
            if (getSawFallback(this.fDepth - 1)) {
                reportFatalError("FallbackChild", new Object[]{qName.rawname});
            }
            if (getState() == 1) {
                if (this.fResultDepth == 0) {
                    checkMultipleRootElements();
                }
                if (this.fDocumentHandler != null) {
                    this.fDocumentHandler.emptyElement(qName, processAttributes(xMLAttributes), modifyAugmentations(augmentations));
                }
            }
        } else if (getState() == 1) {
            if (this.fResultDepth == 0) {
                checkMultipleRootElements();
            }
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.emptyElement(qName, processAttributes(xMLAttributes), modifyAugmentations(augmentations));
            }
        }
        setSawFallback(this.fDepth + 1, false);
        setSawInclude(this.fDepth, false);
        if (this.fBaseURIScope.size() > 0 && this.fDepth == this.fBaseURIScope.peek()) {
            restoreBaseURI();
        }
        this.fDepth--;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endAttlist(Augmentations augmentations) throws XNIException {
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.endAttlist(augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endCDATA(Augmentations augmentations) throws XNIException {
        if (this.fDocumentHandler == null || getState() != 1 || this.fResultDepth == 0) {
            return;
        }
        this.fDocumentHandler.endCDATA(augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endConditional(Augmentations augmentations) throws XNIException {
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.endConditional(augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endDTD(Augmentations augmentations) throws XNIException {
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.endDTD(augmentations);
        }
        this.fInDTD = false;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endDocument(Augmentations augmentations) throws XNIException {
        if (isRootDocument()) {
            if (!this.fSeenRootElement) {
                reportFatalError("RootElementRequired");
            }
            XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
            if (xMLDocumentHandler != null) {
                xMLDocumentHandler.endDocument(augmentations);
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endElement(QName qName, Augmentations augmentations) throws XNIException {
        if (isIncludeElement(qName) && getState() == 3 && !getSawFallback(this.fDepth + 1)) {
            reportFatalError("NoFallback", new Object[]{"unknown"});
        }
        if (isFallbackElement(qName)) {
            if (getState() == 1) {
                setState(2);
            }
        } else if (getState() == 1) {
            this.fResultDepth--;
            XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
            if (xMLDocumentHandler != null) {
                xMLDocumentHandler.endElement(qName, augmentations);
            }
        }
        setSawFallback(this.fDepth + 1, false);
        setSawInclude(this.fDepth, false);
        if (this.fBaseURIScope.size() > 0 && this.fDepth == this.fBaseURIScope.peek()) {
            restoreBaseURI();
        }
        if (this.fLanguageScope.size() > 0 && this.fDepth == this.fLanguageScope.peek()) {
            this.fCurrentLanguage = restoreLanguage();
        }
        this.fDepth--;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endExternalSubset(Augmentations augmentations) throws XNIException {
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.endExternalSubset(augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endGeneralEntity(String str, Augmentations augmentations) throws XNIException {
        if (this.fDocumentHandler == null || getState() != 1 || this.fResultDepth == 0) {
            return;
        }
        this.fDocumentHandler.endGeneralEntity(str, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endParameterEntity(String str, Augmentations augmentations) throws XNIException {
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.endParameterEntity(str, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void externalEntityDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.externalEntityDecl(str, xMLResourceIdentifier, augmentations);
        }
    }

    public String getBaseURI(int i) {
        return this.fExpandedSystemID.get(scopeOfBaseURI(i));
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDTDSource
    public XMLDTDHandler getDTDHandler() {
        return this.fDTDHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public XMLDTDSource getDTDSource() {
        return this.fDTDSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource
    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public XMLDocumentSource getDocumentSource() {
        return this.fDocumentSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public Boolean getFeatureDefault(String str) {
        int i = 0;
        while (true) {
            String[] strArr = RECOGNIZED_FEATURES;
            if (i >= strArr.length) {
                return null;
            }
            if (strArr[i].equals(str)) {
                return FEATURE_DEFAULTS[i];
            }
            i++;
        }
    }

    public String getLanguage(int i) {
        return this.fLanguageStack.get(scopeOfLanguage(i));
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public Object getPropertyDefault(String str) {
        int i = 0;
        while (true) {
            String[] strArr = RECOGNIZED_PROPERTIES;
            if (i >= strArr.length) {
                return null;
            }
            if (strArr[i].equals(str)) {
                return PROPERTY_DEFAULTS[i];
            }
            i++;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
    }

    public String getRelativeBaseURI() throws URI.MalformedURIException {
        int includeParentDepth = getIncludeParentDepth();
        String relativeURI = getRelativeURI(includeParentDepth);
        if (isRootDocument()) {
            return relativeURI;
        }
        if (relativeURI.length() == 0) {
            relativeURI = this.fCurrentBaseURI.getLiteralSystemId();
        }
        if (includeParentDepth == 0) {
            if (this.fParentRelativeURI == null) {
                this.fParentRelativeURI = this.fParentXIncludeHandler.getRelativeBaseURI();
            }
            if (this.fParentRelativeURI.length() != 0) {
                URI uri = new URI(this.fParentRelativeURI, true);
                URI uri2 = new URI(uri, relativeURI);
                if (Objects.equals(uri.getScheme(), uri2.getScheme())) {
                    if (!Objects.equals(uri.getAuthority(), uri2.getAuthority())) {
                        return uri2.getSchemeSpecificPart();
                    }
                    String path = uri2.getPath();
                    String queryString = uri2.getQueryString();
                    String fragment = uri2.getFragment();
                    if (queryString == null && fragment == null) {
                        return path;
                    }
                    StringBuilder sb = new StringBuilder();
                    if (path != null) {
                        sb.append(path);
                    }
                    if (queryString != null) {
                        sb.append('?');
                        sb.append(queryString);
                    }
                    if (fragment != null) {
                        sb.append('#');
                        sb.append(fragment);
                    }
                    return sb.toString();
                }
            }
        }
        return relativeURI;
    }

    public String getRelativeURI(int i) throws URI.MalformedURIException {
        int iScopeOfBaseURI = scopeOfBaseURI(i);
        int i2 = iScopeOfBaseURI + 1;
        if (i2 == this.fBaseURIScope.size()) {
            return "";
        }
        URI uri = new URI("file", this.fLiteralSystemID.get(i2));
        int i3 = iScopeOfBaseURI + 2;
        while (i3 < this.fBaseURIScope.size()) {
            URI uri2 = new URI(uri, this.fLiteralSystemID.get(i3));
            i3++;
            uri = uri2;
        }
        return uri.getPath();
    }

    public boolean getSawFallback(int i) {
        boolean[] zArr = this.fSawFallback;
        if (i >= zArr.length) {
            return false;
        }
        return zArr[i];
    }

    public boolean getSawInclude(int i) {
        boolean[] zArr = this.fSawInclude;
        if (i >= zArr.length) {
            return false;
        }
        return zArr[i];
    }

    public int getState() {
        return this.fState[this.fDepth];
    }

    public void handleFallbackElement() {
        if (!getSawInclude(this.fDepth - 1)) {
            if (getState() == 2) {
                return;
            } else {
                reportFatalError("FallbackParent");
            }
        }
        setSawInclude(this.fDepth, false);
        this.fNamespaceContext.setContextInvalid();
        if (getSawFallback(this.fDepth)) {
            reportFatalError("MultipleFallbacks");
        } else {
            setSawFallback(this.fDepth, true);
        }
        if (getState() == 3) {
            setState(1);
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x01d1 A[PHI: r2
      0x01d1: PHI (r2v15 com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource) = 
      (r2v14 com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource)
      (r2v47 com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource)
     binds: [B:96:0x01bf, B:100:0x01c6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:105:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:112:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:113:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:116:0x0208  */
    /* JADX WARN: Code duplicated, block: B:119:0x0215  */
    /* JADX WARN: Code duplicated, block: B:122:0x021e  */
    /* JADX WARN: Code duplicated, block: B:125:0x0266  */
    /* JADX WARN: Code duplicated, block: B:127:0x0293  */
    /* JADX WARN: Code duplicated, block: B:129:0x02b1  */
    /* JADX WARN: Code duplicated, block: B:131:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:137:0x02e3  */
    /* JADX WARN: Code duplicated, block: B:140:0x02eb  */
    /* JADX WARN: Code duplicated, block: B:144:0x030b A[Catch: all -> 0x0311, IOException -> 0x0314, XNIException -> 0x0316, TryCatch #16 {XNIException -> 0x0316, IOException -> 0x0314, blocks: (B:142:0x02f4, B:144:0x030b, B:152:0x031a, B:154:0x0322, B:156:0x0326, B:158:0x032c), top: B:256:0x02f4, outer: #10 }] */
    /* JADX WARN: Code duplicated, block: B:156:0x0326 A[Catch: all -> 0x0311, IOException -> 0x0314, XNIException -> 0x0316, TryCatch #16 {XNIException -> 0x0316, IOException -> 0x0314, blocks: (B:142:0x02f4, B:144:0x030b, B:152:0x031a, B:154:0x0322, B:156:0x0326, B:158:0x032c), top: B:256:0x02f4, outer: #10 }] */
    /* JADX WARN: Code duplicated, block: B:157:0x032b  */
    /* JADX WARN: Code duplicated, block: B:180:0x039a  */
    /* JADX WARN: Code duplicated, block: B:182:0x03a2  */
    /* JADX WARN: Code duplicated, block: B:185:0x03b1 A[Catch: all -> 0x03bf, IOException -> 0x03c2, CharConversionException -> 0x03c4, MalformedByteSequenceException -> 0x03c8, TryCatch #15 {MalformedByteSequenceException -> 0x03c8, CharConversionException -> 0x03c4, IOException -> 0x03c2, blocks: (B:183:0x03ab, B:185:0x03b1, B:187:0x03b5, B:197:0x03d0, B:204:0x03e8, B:196:0x03cd, B:199:0x03d4, B:201:0x03d8, B:203:0x03e5, B:202:0x03e2), top: B:265:0x03ab, outer: #16 }] */
    /* JADX WARN: Code duplicated, block: B:187:0x03b5 A[Catch: all -> 0x03bf, IOException -> 0x03c2, CharConversionException -> 0x03c4, MalformedByteSequenceException -> 0x03c8, TryCatch #15 {MalformedByteSequenceException -> 0x03c8, CharConversionException -> 0x03c4, IOException -> 0x03c2, blocks: (B:183:0x03ab, B:185:0x03b1, B:187:0x03b5, B:197:0x03d0, B:204:0x03e8, B:196:0x03cd, B:199:0x03d4, B:201:0x03d8, B:203:0x03e5, B:202:0x03e2), top: B:265:0x03ab, outer: #16 }] */
    /* JADX WARN: Code duplicated, block: B:196:0x03cd A[Catch: all -> 0x03bf, IOException -> 0x03c2, CharConversionException -> 0x03c4, MalformedByteSequenceException -> 0x03c8, TryCatch #15 {MalformedByteSequenceException -> 0x03c8, CharConversionException -> 0x03c4, IOException -> 0x03c2, blocks: (B:183:0x03ab, B:185:0x03b1, B:187:0x03b5, B:197:0x03d0, B:204:0x03e8, B:196:0x03cd, B:199:0x03d4, B:201:0x03d8, B:203:0x03e5, B:202:0x03e2), top: B:265:0x03ab, outer: #16 }] */
    /* JADX WARN: Code duplicated, block: B:199:0x03d4 A[Catch: all -> 0x03bf, IOException -> 0x03c2, CharConversionException -> 0x03c4, MalformedByteSequenceException -> 0x03c8, TryCatch #15 {MalformedByteSequenceException -> 0x03c8, CharConversionException -> 0x03c4, IOException -> 0x03c2, blocks: (B:183:0x03ab, B:185:0x03b1, B:187:0x03b5, B:197:0x03d0, B:204:0x03e8, B:196:0x03cd, B:199:0x03d4, B:201:0x03d8, B:203:0x03e5, B:202:0x03e2), top: B:265:0x03ab, outer: #16 }] */
    /* JADX WARN: Code duplicated, block: B:201:0x03d8 A[Catch: all -> 0x03bf, IOException -> 0x03c2, CharConversionException -> 0x03c4, MalformedByteSequenceException -> 0x03c8, TryCatch #15 {MalformedByteSequenceException -> 0x03c8, CharConversionException -> 0x03c4, IOException -> 0x03c2, blocks: (B:183:0x03ab, B:185:0x03b1, B:187:0x03b5, B:197:0x03d0, B:204:0x03e8, B:196:0x03cd, B:199:0x03d4, B:201:0x03d8, B:203:0x03e5, B:202:0x03e2), top: B:265:0x03ab, outer: #16 }] */
    /* JADX WARN: Code duplicated, block: B:202:0x03e2 A[Catch: all -> 0x03bf, IOException -> 0x03c2, CharConversionException -> 0x03c4, MalformedByteSequenceException -> 0x03c8, TryCatch #15 {MalformedByteSequenceException -> 0x03c8, CharConversionException -> 0x03c4, IOException -> 0x03c2, blocks: (B:183:0x03ab, B:185:0x03b1, B:187:0x03b5, B:197:0x03d0, B:204:0x03e8, B:196:0x03cd, B:199:0x03d4, B:201:0x03d8, B:203:0x03e5, B:202:0x03e2), top: B:265:0x03ab, outer: #16 }] */
    /* JADX WARN: Code duplicated, block: B:242:0x0489  */
    /* JADX WARN: Code duplicated, block: B:248:0x012d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:252:0x00ea A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:56:0x010f A[Catch: IOException | CatalogException -> 0x011e, TryCatch #8 {IOException | CatalogException -> 0x011e, blocks: (B:50:0x00ea, B:52:0x0107, B:54:0x010b, B:56:0x010f, B:59:0x0121, B:61:0x012d, B:63:0x0131, B:64:0x013b, B:68:0x014b, B:68:0x014b, B:70:0x0151, B:70:0x0151, B:71:0x0162, B:71:0x0162, B:73:0x0166, B:73:0x0166, B:74:0x0170, B:74:0x0170, B:76:0x0178, B:76:0x0178, B:78:0x017e, B:78:0x017e, B:80:0x0186, B:80:0x0186, B:86:0x0191, B:86:0x0191, B:88:0x0197, B:88:0x0197, B:90:0x019d, B:90:0x019d), top: B:252:0x00ea }] */
    /* JADX WARN: Code duplicated, block: B:63:0x0131 A[Catch: IOException | CatalogException -> 0x011e, CatalogException -> 0x0148, TryCatch #5 {CatalogException -> 0x0148, blocks: (B:61:0x012d, B:63:0x0131, B:64:0x013b), top: B:248:0x012d }] */
    /* JADX WARN: Code duplicated, block: B:71:0x0162 A[Catch: IOException | CatalogException -> 0x011e, IOException | CatalogException -> 0x011e, TryCatch #8 {IOException | CatalogException -> 0x011e, blocks: (B:50:0x00ea, B:52:0x0107, B:54:0x010b, B:56:0x010f, B:59:0x0121, B:61:0x012d, B:63:0x0131, B:64:0x013b, B:68:0x014b, B:68:0x014b, B:70:0x0151, B:70:0x0151, B:71:0x0162, B:71:0x0162, B:73:0x0166, B:73:0x0166, B:74:0x0170, B:74:0x0170, B:76:0x0178, B:76:0x0178, B:78:0x017e, B:78:0x017e, B:80:0x0186, B:80:0x0186, B:86:0x0191, B:86:0x0191, B:88:0x0197, B:88:0x0197, B:90:0x019d, B:90:0x019d), top: B:252:0x00ea }] */
    /* JADX WARN: Code duplicated, block: B:73:0x0166 A[Catch: IOException | CatalogException -> 0x011e, IOException | CatalogException -> 0x011e, TryCatch #8 {IOException | CatalogException -> 0x011e, blocks: (B:50:0x00ea, B:52:0x0107, B:54:0x010b, B:56:0x010f, B:59:0x0121, B:61:0x012d, B:63:0x0131, B:64:0x013b, B:68:0x014b, B:68:0x014b, B:70:0x0151, B:70:0x0151, B:71:0x0162, B:71:0x0162, B:73:0x0166, B:73:0x0166, B:74:0x0170, B:74:0x0170, B:76:0x0178, B:76:0x0178, B:78:0x017e, B:78:0x017e, B:80:0x0186, B:80:0x0186, B:86:0x0191, B:86:0x0191, B:88:0x0197, B:88:0x0197, B:90:0x019d, B:90:0x019d), top: B:252:0x00ea }] */
    /* JADX WARN: Code duplicated, block: B:76:0x0178 A[Catch: IOException | CatalogException -> 0x011e, IOException | CatalogException -> 0x011e, TryCatch #8 {IOException | CatalogException -> 0x011e, blocks: (B:50:0x00ea, B:52:0x0107, B:54:0x010b, B:56:0x010f, B:59:0x0121, B:61:0x012d, B:63:0x0131, B:64:0x013b, B:68:0x014b, B:68:0x014b, B:70:0x0151, B:70:0x0151, B:71:0x0162, B:71:0x0162, B:73:0x0166, B:73:0x0166, B:74:0x0170, B:74:0x0170, B:76:0x0178, B:76:0x0178, B:78:0x017e, B:78:0x017e, B:80:0x0186, B:80:0x0186, B:86:0x0191, B:86:0x0191, B:88:0x0197, B:88:0x0197, B:90:0x019d, B:90:0x019d), top: B:252:0x00ea }] */
    /* JADX WARN: Code duplicated, block: B:85:0x018f  */
    /* JADX WARN: Code duplicated, block: B:95:0x01be  */
    /* JADX WARN: Code duplicated, block: B:97:0x01c1  */
    public boolean handleIncludeElement(XMLAttributes xMLAttributes) throws XNIException {
        String str;
        XMLInputSource xMLInputSourceResolveEntity;
        XMLInputSource xMLInputSourceCreateInputSource;
        String str2;
        Source sourceResolve;
        InputSource inputSourceResolveEntity;
        String str3;
        XIncludeTextReader xIncludeTextReader;
        XIncludeTextReader xIncludeTextReader2;
        XIncludeTextReader xIncludeTextReader3;
        XMLErrorReporter xMLErrorReporter;
        XMLErrorReporter xMLErrorReporter2;
        Locale locale;
        SymbolTable symbolTable;
        XMLErrorReporter xMLErrorReporter3;
        XMLEntityResolver xMLEntityResolver;
        XMLParserConfiguration xMLParserConfiguration;
        XMLErrorReporter xMLErrorReporter4;
        if (getSawInclude(this.fDepth - 1)) {
            reportFatalError("IncludeChild", new Object[]{XINCLUDE_INCLUDE});
        }
        if (getState() == 2) {
            return true;
        }
        setSawInclude(this.fDepth, true);
        this.fNamespaceContext.setContextInvalid();
        String value = xMLAttributes.getValue(XINCLUDE_ATTR_HREF);
        String value2 = xMLAttributes.getValue(XINCLUDE_ATTR_PARSE);
        String value3 = xMLAttributes.getValue(XPOINTER);
        String value4 = xMLAttributes.getValue(XINCLUDE_ATTR_ACCEPT);
        String value5 = xMLAttributes.getValue(XINCLUDE_ATTR_ACCEPT_LANGUAGE);
        if (value2 == null) {
            value2 = XINCLUDE_PARSE_XML;
        }
        String str4 = value2;
        if (value == null) {
            value = XMLSymbols.EMPTY_STRING;
        }
        XIncludeTextReader xIncludeTextReader4 = null;
        if (value.length() == 0 && XINCLUDE_PARSE_XML.equals(str4)) {
            if (value3 != null) {
                XMLErrorReporter xMLErrorReporter5 = this.fErrorReporter;
                reportResourceError("XMLResourceError", new Object[]{value, this.fXIncludeMessageFormatter.formatMessage(xMLErrorReporter5 != null ? xMLErrorReporter5.getLocale() : null, "XPointerStreamability", null)});
                return false;
            }
            reportFatalError("XpointerMissing");
        }
        try {
            if (new URI(value, true).getFragment() != null) {
                reportFatalError("HrefFragmentIdentifierIllegal", new Object[]{value});
            }
        } catch (URI.MalformedURIException unused) {
            String strEscapeHref = escapeHref(value);
            if (value != strEscapeHref) {
                try {
                    if (new URI(strEscapeHref, true).getFragment() != null) {
                        reportFatalError("HrefFragmentIdentifierIllegal", new Object[]{strEscapeHref});
                    }
                } catch (URI.MalformedURIException unused2) {
                    reportFatalError("HrefSyntacticallyInvalid", new Object[]{strEscapeHref});
                }
                str = strEscapeHref;
            } else {
                reportFatalError("HrefSyntacticallyInvalid", new Object[]{value});
            }
            if (value4 != null) {
                reportFatalError("AcceptMalformed", null);
                value4 = null;
            }
            if (value5 != null) {
                reportFatalError("AcceptLanguageMalformed", null);
                value5 = null;
            }
            if (this.fEntityResolver != null) {
                try {
                    xMLInputSourceResolveEntity = this.fEntityResolver.resolveEntity(new XMLResourceIdentifierImpl(null, str, this.fCurrentBaseURI.getExpandedSystemId(), XMLEntityManager.expandSystemId(str, this.fCurrentBaseURI.getExpandedSystemId(), false)));
                    if (xMLInputSourceResolveEntity == null) {
                        if (this.fCatalogFeatures == null) {
                            this.fCatalogFeatures = JdkXmlUtils.getCatalogFeatures(this.fDefer, this.fCatalogFile, this.fPrefer, this.fResolve);
                        }
                        str2 = this.fCatalogFeatures.get(CatalogFeatures.Feature.FILES);
                        this.fCatalogFile = str2;
                        if (str2 != null) {
                            try {
                                if (this.fCatalogResolver == null) {
                                    this.fCatalogResolver = CatalogManager.catalogResolver(this.fCatalogFeatures, new java.net.URI[0]);
                                }
                                sourceResolve = this.fCatalogResolver.resolve(str, this.fCurrentBaseURI.getExpandedSystemId());
                            } catch (CatalogException unused3) {
                                sourceResolve = null;
                            }
                            if (sourceResolve != null) {
                                if (this.fCatalogResolver == null) {
                                    this.fCatalogResolver = CatalogManager.catalogResolver(this.fCatalogFeatures, new java.net.URI[0]);
                                }
                                inputSourceResolveEntity = this.fCatalogResolver.resolveEntity(str, str);
                                if (inputSourceResolveEntity != null) {
                                    xMLInputSourceResolveEntity = new XMLInputSource(inputSourceResolveEntity, true);
                                }
                            } else {
                                if (this.fCatalogResolver == null) {
                                    this.fCatalogResolver = CatalogManager.catalogResolver(this.fCatalogFeatures, new java.net.URI[0]);
                                }
                                inputSourceResolveEntity = this.fCatalogResolver.resolveEntity(str, str);
                                if (inputSourceResolveEntity != null) {
                                    xMLInputSourceResolveEntity = new XMLInputSource(inputSourceResolveEntity, true);
                                }
                            }
                        }
                    }
                    xMLInputSourceCreateInputSource = xMLInputSourceResolveEntity == null ? xMLInputSourceResolveEntity : xMLInputSourceResolveEntity;
                } catch (IOException | CatalogException e) {
                    reportResourceError("XMLResourceError", new Object[]{str, e.getMessage()}, e);
                    return false;
                }
            } else {
                xMLInputSourceCreateInputSource = null;
            }
            if (xMLInputSourceCreateInputSource != null) {
                str3 = str;
            } else {
                if (value4 == null) {
                }
                str3 = str;
                xMLInputSourceCreateInputSource = createInputSource(null, str3, this.fCurrentBaseURI.getExpandedSystemId(), value4, value5);
            }
            if (str4.equals(XINCLUDE_PARSE_XML)) {
                if (str4.equals(XINCLUDE_PARSE_TEXT)) {
                    reportFatalError("InvalidParseValue", new Object[]{str4});
                    return true;
                }
                xMLInputSourceCreateInputSource.setEncoding(xMLAttributes.getValue(XINCLUDE_ATTR_ENCODING));
                try {
                    try {
                        this.fHasIncludeReportedContent = false;
                        if (this.fIsXML11) {
                            xIncludeTextReader = this.fXInclude11TextReader;
                            if (xIncludeTextReader == null) {
                                this.fXInclude11TextReader = new XInclude11TextReader(xMLInputSourceCreateInputSource, this, this.fBufferSize);
                            } else {
                                xIncludeTextReader.setInputSource(xMLInputSourceCreateInputSource);
                            }
                            xIncludeTextReader2 = this.fXInclude11TextReader;
                        } else {
                            xIncludeTextReader3 = this.fXInclude10TextReader;
                            if (xIncludeTextReader3 == null) {
                                this.fXInclude10TextReader = new XIncludeTextReader(xMLInputSourceCreateInputSource, this, this.fBufferSize);
                            } else {
                                xIncludeTextReader3.setInputSource(xMLInputSourceCreateInputSource);
                            }
                            xIncludeTextReader2 = this.fXInclude10TextReader;
                        }
                        xIncludeTextReader4 = xIncludeTextReader2;
                        xIncludeTextReader4.setErrorReporter(this.fErrorReporter);
                        xIncludeTextReader4.parse();
                        try {
                            xIncludeTextReader4.close();
                            return true;
                        } catch (IOException e2) {
                            reportResourceError("TextResourceError", new Object[]{str3, e2.getMessage()}, e2);
                            return false;
                        }
                    } catch (Throwable th) {
                        if (xIncludeTextReader4 != null) {
                            try {
                                xIncludeTextReader4.close();
                            } catch (IOException e3) {
                                reportResourceError("TextResourceError", new Object[]{str3, e3.getMessage()}, e3);
                                return false;
                            }
                        }
                        throw th;
                    }
                } catch (MalformedByteSequenceException e4) {
                    this.fErrorReporter.reportError(e4.getDomain(), e4.getKey(), e4.getArguments(), (short) 2, (Exception) e4);
                    if (xIncludeTextReader4 == null) {
                        return true;
                    }
                    try {
                        xIncludeTextReader4.close();
                        return true;
                    } catch (IOException e5) {
                        reportResourceError("TextResourceError", new Object[]{str3, e5.getMessage()}, e5);
                        return false;
                    }
                } catch (CharConversionException e6) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", (Object[]) null, (short) 2, (Exception) e6);
                    if (xIncludeTextReader4 == null) {
                        return true;
                    }
                    try {
                        xIncludeTextReader4.close();
                        return true;
                    } catch (IOException e7) {
                        reportResourceError("TextResourceError", new Object[]{str3, e7.getMessage()}, e7);
                        return false;
                    }
                } catch (IOException e8) {
                    if (this.fHasIncludeReportedContent) {
                        throw new XNIException(e8);
                    }
                    reportResourceError("TextResourceError", new Object[]{str3, e8.getMessage()}, e8);
                    if (xIncludeTextReader4 != null) {
                        try {
                            xIncludeTextReader4.close();
                        } catch (IOException e9) {
                            reportResourceError("TextResourceError", new Object[]{str3, e9.getMessage()}, e9);
                        }
                    }
                    return false;
                }
            }
            if (value3 == null) {
                if (value3 == null) {
                    this.fChildConfig = new XIncludeParserConfiguration();
                } else {
                    this.fChildConfig = new XPointerParserConfiguration();
                }
                symbolTable = this.fSymbolTable;
                if (symbolTable != null) {
                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
                }
                xMLErrorReporter3 = this.fErrorReporter;
                if (xMLErrorReporter3 != null) {
                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", xMLErrorReporter3);
                }
                xMLEntityResolver = this.fEntityResolver;
                if (xMLEntityResolver != null) {
                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", xMLEntityResolver);
                }
                this.fChildConfig.setProperty("http://apache.org/xml/properties/security-manager", this.fSecurityManager);
                this.fChildConfig.setProperty("jdk.xml.xmlSecurityPropertyManager", this.fSecurityPropertyMgr);
                this.fChildConfig.setProperty(BUFFER_SIZE, Integer.valueOf(this.fBufferSize));
                this.fNeedCopyFeatures = true;
                this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                this.fChildConfig.setFeature(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs);
                this.fChildConfig.setFeature(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage);
                xMLParserConfiguration = this.fChildConfig;
                if (value3 != null) {
                    XPointerHandler xPointerHandler = (XPointerHandler) xMLParserConfiguration.getProperty("http://apache.org/xml/properties/internal/xpointer-handler");
                    this.fXPtrProcessor = xPointerHandler;
                    xPointerHandler.setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                    ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_BASE_URIS, Boolean.valueOf(this.fFixupBaseURIs));
                    ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_LANGUAGE, Boolean.valueOf(this.fFixupLanguage));
                    xMLErrorReporter4 = this.fErrorReporter;
                    if (xMLErrorReporter4 != null) {
                        ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/error-reporter", xMLErrorReporter4);
                    }
                    xPointerHandler.setParent(this);
                    xPointerHandler.setHref(str3);
                    xPointerHandler.setXIncludeLocator(this.fXIncludeLocator);
                    xPointerHandler.setDocumentHandler(getDocumentHandler());
                    this.fXPointerChildConfig = this.fChildConfig;
                } else {
                    XIncludeHandler xIncludeHandler = (XIncludeHandler) xMLParserConfiguration.getProperty("http://apache.org/xml/properties/internal/xinclude-handler");
                    xIncludeHandler.setParent(this);
                    xIncludeHandler.setHref(str3);
                    xIncludeHandler.setDocumentHandler(getDocumentHandler());
                    this.fXIncludeChildConfig = this.fChildConfig;
                }
            } else {
                if (value3 == null) {
                    this.fChildConfig = new XIncludeParserConfiguration();
                } else {
                    this.fChildConfig = new XPointerParserConfiguration();
                }
                symbolTable = this.fSymbolTable;
                if (symbolTable != null) {
                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
                }
                xMLErrorReporter3 = this.fErrorReporter;
                if (xMLErrorReporter3 != null) {
                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", xMLErrorReporter3);
                }
                xMLEntityResolver = this.fEntityResolver;
                if (xMLEntityResolver != null) {
                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", xMLEntityResolver);
                }
                this.fChildConfig.setProperty("http://apache.org/xml/properties/security-manager", this.fSecurityManager);
                this.fChildConfig.setProperty("jdk.xml.xmlSecurityPropertyManager", this.fSecurityPropertyMgr);
                this.fChildConfig.setProperty(BUFFER_SIZE, Integer.valueOf(this.fBufferSize));
                this.fNeedCopyFeatures = true;
                this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                this.fChildConfig.setFeature(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs);
                this.fChildConfig.setFeature(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage);
                xMLParserConfiguration = this.fChildConfig;
                if (value3 != null) {
                    XPointerHandler xPointerHandler2 = (XPointerHandler) xMLParserConfiguration.getProperty("http://apache.org/xml/properties/internal/xpointer-handler");
                    this.fXPtrProcessor = xPointerHandler2;
                    xPointerHandler2.setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                    ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_BASE_URIS, Boolean.valueOf(this.fFixupBaseURIs));
                    ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_LANGUAGE, Boolean.valueOf(this.fFixupLanguage));
                    xMLErrorReporter4 = this.fErrorReporter;
                    if (xMLErrorReporter4 != null) {
                        ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/error-reporter", xMLErrorReporter4);
                    }
                    xPointerHandler2.setParent(this);
                    xPointerHandler2.setHref(str3);
                    xPointerHandler2.setXIncludeLocator(this.fXIncludeLocator);
                    xPointerHandler2.setDocumentHandler(getDocumentHandler());
                    this.fXPointerChildConfig = this.fChildConfig;
                } else {
                    XIncludeHandler xIncludeHandler2 = (XIncludeHandler) xMLParserConfiguration.getProperty("http://apache.org/xml/properties/internal/xinclude-handler");
                    xIncludeHandler2.setParent(this);
                    xIncludeHandler2.setHref(str3);
                    xIncludeHandler2.setDocumentHandler(getDocumentHandler());
                    this.fXIncludeChildConfig = this.fChildConfig;
                }
            }
            if (value3 != null) {
                this.fChildConfig = this.fXPointerChildConfig;
                try {
                    this.fXPtrProcessor.parseXPointer(value3);
                } catch (XNIException e10) {
                    reportResourceError("XMLResourceError", new Object[]{str3, e10.getMessage()});
                    return false;
                }
            } else {
                this.fChildConfig = this.fXIncludeChildConfig;
            }
            if (this.fNeedCopyFeatures) {
                copyFeatures(this.fSettings, this.fChildConfig);
            }
            this.fNeedCopyFeatures = false;
            try {
                try {
                    this.fHasIncludeReportedContent = false;
                    this.fNamespaceContext.pushScope();
                    this.fChildConfig.parse(xMLInputSourceCreateInputSource);
                    this.fXIncludeLocator.setLocator(this.fDocLocation);
                    xMLErrorReporter = this.fErrorReporter;
                    if (xMLErrorReporter != null) {
                        xMLErrorReporter.setDocumentLocator(this.fDocLocation);
                    }
                    if (value3 != null) {
                        xMLErrorReporter2 = this.fErrorReporter;
                        if (xMLErrorReporter2 != null) {
                            locale = xMLErrorReporter2.getLocale();
                        } else {
                            locale = null;
                        }
                        reportResourceError("XMLResourceError", new Object[]{str3, this.fXIncludeMessageFormatter.formatMessage(locale, "XPointerResolutionUnsuccessful", null)});
                        this.fNamespaceContext.popScope();
                        return false;
                    }
                } catch (Throwable th2) {
                    this.fNamespaceContext.popScope();
                    throw th2;
                }
            } catch (XNIException e11) {
                this.fXIncludeLocator.setLocator(this.fDocLocation);
                XMLErrorReporter xMLErrorReporter6 = this.fErrorReporter;
                if (xMLErrorReporter6 != null) {
                    xMLErrorReporter6.setDocumentLocator(this.fDocLocation);
                }
                reportFatalError("XMLParseError", new Object[]{str3, e11.getMessage()});
            } catch (IOException e12) {
                this.fXIncludeLocator.setLocator(this.fDocLocation);
                XMLErrorReporter xMLErrorReporter7 = this.fErrorReporter;
                if (xMLErrorReporter7 != null) {
                    xMLErrorReporter7.setDocumentLocator(this.fDocLocation);
                }
                if (this.fHasIncludeReportedContent) {
                    throw new XNIException(e12);
                }
                reportResourceError("XMLResourceError", new Object[]{str3, e12.getMessage()}, e12);
                this.fNamespaceContext.popScope();
                return false;
            }
            this.fNamespaceContext.popScope();
            return true;
        }
        str = value;
        if (value4 != null && !isValidInHTTPHeader(value4)) {
            reportFatalError("AcceptMalformed", null);
            value4 = null;
        }
        if (value5 != null && !isValidInHTTPHeader(value5)) {
            reportFatalError("AcceptLanguageMalformed", null);
            value5 = null;
        }
        if (this.fEntityResolver != null) {
            xMLInputSourceResolveEntity = this.fEntityResolver.resolveEntity(new XMLResourceIdentifierImpl(null, str, this.fCurrentBaseURI.getExpandedSystemId(), XMLEntityManager.expandSystemId(str, this.fCurrentBaseURI.getExpandedSystemId(), false)));
            if (xMLInputSourceResolveEntity == null && this.fUseCatalog) {
                if (this.fCatalogFeatures == null) {
                    this.fCatalogFeatures = JdkXmlUtils.getCatalogFeatures(this.fDefer, this.fCatalogFile, this.fPrefer, this.fResolve);
                }
                str2 = this.fCatalogFeatures.get(CatalogFeatures.Feature.FILES);
                this.fCatalogFile = str2;
                if (str2 != null) {
                    if (this.fCatalogResolver == null) {
                        this.fCatalogResolver = CatalogManager.catalogResolver(this.fCatalogFeatures, new java.net.URI[0]);
                    }
                    sourceResolve = this.fCatalogResolver.resolve(str, this.fCurrentBaseURI.getExpandedSystemId());
                    if (sourceResolve != null || sourceResolve.isEmpty()) {
                        if (this.fCatalogResolver == null) {
                            this.fCatalogResolver = CatalogManager.catalogResolver(this.fCatalogFeatures, new java.net.URI[0]);
                        }
                        inputSourceResolveEntity = this.fCatalogResolver.resolveEntity(str, str);
                        if (inputSourceResolveEntity != null && !inputSourceResolveEntity.isEmpty()) {
                            xMLInputSourceResolveEntity = new XMLInputSource(inputSourceResolveEntity, true);
                        }
                    } else {
                        xMLInputSourceResolveEntity = new XMLInputSource(null, sourceResolve.getSystemId(), this.fCurrentBaseURI.getExpandedSystemId(), true);
                    }
                }
            }
            if (xMLInputSourceResolveEntity == null && !(xMLInputSourceResolveEntity instanceof HTTPInputSource) && (value4 != null || value5 != null)) {
                if (xMLInputSourceResolveEntity.getCharacterStream() == null && xMLInputSourceResolveEntity.getByteStream() == null) {
                    XMLInputSource xMLInputSource = xMLInputSourceResolveEntity;
                    xMLInputSourceCreateInputSource = createInputSource(xMLInputSource.getPublicId(), xMLInputSource.getSystemId(), xMLInputSource.getBaseSystemId(), value4, value5);
                }
            }
        } else {
            xMLInputSourceCreateInputSource = null;
        }
        if (xMLInputSourceCreateInputSource != null) {
            str3 = str;
        } else if (value4 == null || value5 != null) {
            str3 = str;
            xMLInputSourceCreateInputSource = createInputSource(null, str3, this.fCurrentBaseURI.getExpandedSystemId(), value4, value5);
        } else {
            xMLInputSourceCreateInputSource = new XMLInputSource(null, str, this.fCurrentBaseURI.getExpandedSystemId(), false);
            str3 = str;
        }
        if (str4.equals(XINCLUDE_PARSE_XML)) {
            if (str4.equals(XINCLUDE_PARSE_TEXT)) {
                reportFatalError("InvalidParseValue", new Object[]{str4});
                return true;
            }
            xMLInputSourceCreateInputSource.setEncoding(xMLAttributes.getValue(XINCLUDE_ATTR_ENCODING));
            this.fHasIncludeReportedContent = false;
            if (this.fIsXML11) {
                xIncludeTextReader3 = this.fXInclude10TextReader;
                if (xIncludeTextReader3 == null) {
                    this.fXInclude10TextReader = new XIncludeTextReader(xMLInputSourceCreateInputSource, this, this.fBufferSize);
                } else {
                    xIncludeTextReader3.setInputSource(xMLInputSourceCreateInputSource);
                }
                xIncludeTextReader2 = this.fXInclude10TextReader;
            } else {
                xIncludeTextReader = this.fXInclude11TextReader;
                if (xIncludeTextReader == null) {
                    this.fXInclude11TextReader = new XInclude11TextReader(xMLInputSourceCreateInputSource, this, this.fBufferSize);
                } else {
                    xIncludeTextReader.setInputSource(xMLInputSourceCreateInputSource);
                }
                xIncludeTextReader2 = this.fXInclude11TextReader;
            }
            xIncludeTextReader4 = xIncludeTextReader2;
            xIncludeTextReader4.setErrorReporter(this.fErrorReporter);
            xIncludeTextReader4.parse();
            xIncludeTextReader4.close();
            return true;
        }
        if ((value3 == null && this.fXPointerChildConfig == null) || (value3 == null && this.fXIncludeChildConfig == null)) {
            if (value3 == null) {
                this.fChildConfig = new XIncludeParserConfiguration();
            } else {
                this.fChildConfig = new XPointerParserConfiguration();
            }
            symbolTable = this.fSymbolTable;
            if (symbolTable != null) {
                this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
            }
            xMLErrorReporter3 = this.fErrorReporter;
            if (xMLErrorReporter3 != null) {
                this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", xMLErrorReporter3);
            }
            xMLEntityResolver = this.fEntityResolver;
            if (xMLEntityResolver != null) {
                this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", xMLEntityResolver);
            }
            this.fChildConfig.setProperty("http://apache.org/xml/properties/security-manager", this.fSecurityManager);
            this.fChildConfig.setProperty("jdk.xml.xmlSecurityPropertyManager", this.fSecurityPropertyMgr);
            this.fChildConfig.setProperty(BUFFER_SIZE, Integer.valueOf(this.fBufferSize));
            this.fNeedCopyFeatures = true;
            this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
            this.fChildConfig.setFeature(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs);
            this.fChildConfig.setFeature(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage);
            xMLParserConfiguration = this.fChildConfig;
            if (value3 != null) {
                XPointerHandler xPointerHandler3 = (XPointerHandler) xMLParserConfiguration.getProperty("http://apache.org/xml/properties/internal/xpointer-handler");
                this.fXPtrProcessor = xPointerHandler3;
                xPointerHandler3.setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_BASE_URIS, Boolean.valueOf(this.fFixupBaseURIs));
                ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_LANGUAGE, Boolean.valueOf(this.fFixupLanguage));
                xMLErrorReporter4 = this.fErrorReporter;
                if (xMLErrorReporter4 != null) {
                    ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/error-reporter", xMLErrorReporter4);
                }
                xPointerHandler3.setParent(this);
                xPointerHandler3.setHref(str3);
                xPointerHandler3.setXIncludeLocator(this.fXIncludeLocator);
                xPointerHandler3.setDocumentHandler(getDocumentHandler());
                this.fXPointerChildConfig = this.fChildConfig;
            } else {
                XIncludeHandler xIncludeHandler3 = (XIncludeHandler) xMLParserConfiguration.getProperty("http://apache.org/xml/properties/internal/xinclude-handler");
                xIncludeHandler3.setParent(this);
                xIncludeHandler3.setHref(str3);
                xIncludeHandler3.setDocumentHandler(getDocumentHandler());
                this.fXIncludeChildConfig = this.fChildConfig;
            }
        }
        if (value3 != null) {
            this.fChildConfig = this.fXPointerChildConfig;
            this.fXPtrProcessor.parseXPointer(value3);
        } else {
            this.fChildConfig = this.fXIncludeChildConfig;
        }
        if (this.fNeedCopyFeatures) {
            copyFeatures(this.fSettings, this.fChildConfig);
        }
        this.fNeedCopyFeatures = false;
        this.fHasIncludeReportedContent = false;
        this.fNamespaceContext.pushScope();
        this.fChildConfig.parse(xMLInputSourceCreateInputSource);
        this.fXIncludeLocator.setLocator(this.fDocLocation);
        xMLErrorReporter = this.fErrorReporter;
        if (xMLErrorReporter != null) {
            xMLErrorReporter.setDocumentLocator(this.fDocLocation);
        }
        if (value3 != null && !this.fXPtrProcessor.isXPointerResolved()) {
            xMLErrorReporter2 = this.fErrorReporter;
            if (xMLErrorReporter2 != null) {
                locale = xMLErrorReporter2.getLocale();
            } else {
                locale = null;
            }
            reportResourceError("XMLResourceError", new Object[]{str3, this.fXIncludeMessageFormatter.formatMessage(locale, "XPointerResolutionUnsuccessful", null)});
            this.fNamespaceContext.popScope();
            return false;
        }
        this.fNamespaceContext.popScope();
        return true;
    }

    public boolean hasXIncludeNamespace(QName qName) {
        String str = qName.uri;
        String str2 = XINCLUDE_NS_URI;
        return str == str2 || this.fNamespaceContext.getURI(qName.prefix) == str2;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void ignorableWhitespace(XMLString xMLString, Augmentations augmentations) throws XNIException {
        if (this.fDocumentHandler == null || getState() != 1 || this.fResultDepth == 0) {
            return;
        }
        this.fDocumentHandler.ignorableWhitespace(xMLString, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void ignoredCharacters(XMLString xMLString, Augmentations augmentations) throws XNIException {
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.ignoredCharacters(xMLString, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void internalEntityDecl(String str, XMLString xMLString, XMLString xMLString2, Augmentations augmentations) throws XNIException {
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.internalEntityDecl(str, xMLString, xMLString2, augmentations);
        }
    }

    public boolean isFallbackElement(QName qName) {
        return qName.localpart.equals(XINCLUDE_FALLBACK) && hasXIncludeNamespace(qName);
    }

    public boolean isIncludeElement(QName qName) {
        return qName.localpart.equals(XINCLUDE_INCLUDE) && hasXIncludeNamespace(qName);
    }

    public boolean isRootDocument() {
        return this.fParentXIncludeHandler == null;
    }

    public boolean isTopLevelIncludedItem() {
        return isTopLevelIncludedItemViaInclude() || isTopLevelIncludedItemViaFallback();
    }

    public boolean isTopLevelIncludedItemViaFallback() {
        return getSawFallback(this.fDepth - 1);
    }

    public boolean isTopLevelIncludedItemViaInclude() {
        return this.fDepth == 1 && !isRootDocument();
    }

    public Augmentations modifyAugmentations(Augmentations augmentations, boolean z) {
        if (!z && !isTopLevelIncludedItem()) {
            return augmentations;
        }
        if (augmentations == null) {
            augmentations = new AugmentationsImpl();
        }
        augmentations.putItem(XINCLUDE_INCLUDED, Boolean.TRUE);
        return augmentations;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void notationDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
        addNotation(str, xMLResourceIdentifier, augmentations);
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.notationDecl(str, xMLResourceIdentifier, augmentations);
        }
    }

    public XMLAttributes processAttributes(XMLAttributes xMLAttributes) {
        String expandedSystemId;
        if (isTopLevelIncludedItem()) {
            if (this.fFixupBaseURIs && !sameBaseURIAsIncludeParent()) {
                if (xMLAttributes == null) {
                    xMLAttributes = new XMLAttributesImpl();
                }
                try {
                    expandedSystemId = getRelativeBaseURI();
                } catch (URI.MalformedURIException unused) {
                    expandedSystemId = this.fCurrentBaseURI.getExpandedSystemId();
                }
                xMLAttributes.setSpecified(xMLAttributes.addAttribute(XML_BASE_QNAME, XMLSymbols.fCDATASymbol, expandedSystemId), true);
            }
            if (this.fFixupLanguage && !sameLanguageAsIncludeParent()) {
                if (xMLAttributes == null) {
                    xMLAttributes = new XMLAttributesImpl();
                }
                xMLAttributes.setSpecified(xMLAttributes.addAttribute(XML_LANG_QNAME, XMLSymbols.fCDATASymbol, this.fCurrentLanguage), true);
            }
            Enumeration<String> allPrefixes = this.fNamespaceContext.getAllPrefixes();
            while (allPrefixes.hasMoreElements()) {
                String strNextElement = allPrefixes.nextElement();
                String uRIFromIncludeParent = this.fNamespaceContext.getURIFromIncludeParent(strNextElement);
                String uri = this.fNamespaceContext.getURI(strNextElement);
                if (uRIFromIncludeParent != uri && xMLAttributes != null) {
                    String str = XMLSymbols.EMPTY_STRING;
                    if (strNextElement == str) {
                        String str2 = NamespaceContext.XMLNS_URI;
                        String str3 = XMLSymbols.PREFIX_XMLNS;
                        if (xMLAttributes.getValue(str2, str3) == null) {
                            QName qName = (QName) NEW_NS_ATTR_QNAME.clone();
                            qName.prefix = null;
                            qName.localpart = str3;
                            qName.rawname = str3;
                            String str4 = XMLSymbols.fCDATASymbol;
                            if (uri != null) {
                                str = uri;
                            }
                            xMLAttributes.setSpecified(xMLAttributes.addAttribute(qName, str4, str), true);
                            this.fNamespaceContext.declarePrefix(strNextElement, uri);
                        }
                    } else if (xMLAttributes.getValue(NamespaceContext.XMLNS_URI, strNextElement) == null) {
                        QName qName2 = (QName) NEW_NS_ATTR_QNAME.clone();
                        qName2.localpart = strNextElement;
                        String str5 = qName2.rawname + strNextElement;
                        qName2.rawname = str5;
                        SymbolTable symbolTable = this.fSymbolTable;
                        qName2.rawname = symbolTable != null ? symbolTable.addSymbol(str5) : str5.intern();
                        String str6 = XMLSymbols.fCDATASymbol;
                        if (uri != null) {
                            str = uri;
                        }
                        xMLAttributes.setSpecified(xMLAttributes.addAttribute(qName2, str6, str), true);
                        this.fNamespaceContext.declarePrefix(strNextElement, uri);
                    }
                }
            }
        }
        if (xMLAttributes != null) {
            int length = xMLAttributes.getLength();
            for (int i = 0; i < length; i++) {
                String type = xMLAttributes.getType(i);
                String value = xMLAttributes.getValue(i);
                if (type == XMLSymbols.fENTITYSymbol) {
                    checkUnparsedEntity(value);
                }
                if (type == XMLSymbols.fENTITIESSymbol) {
                    StringTokenizer stringTokenizer = new StringTokenizer(value);
                    while (stringTokenizer.hasMoreTokens()) {
                        checkUnparsedEntity(stringTokenizer.nextToken());
                    }
                } else if (type == XMLSymbols.fNOTATIONSymbol) {
                    checkNotation(value);
                }
            }
        }
        return xMLAttributes;
    }

    public void processXMLBaseAttributes(XMLAttributes xMLAttributes) {
        String value = xMLAttributes.getValue(NamespaceContext.XML_URI, "base");
        if (value != null) {
            try {
                String strExpandSystemId = XMLEntityManager.expandSystemId(value, this.fCurrentBaseURI.getExpandedSystemId(), false);
                this.fCurrentBaseURI.setLiteralSystemId(value);
                XMLResourceIdentifier xMLResourceIdentifier = this.fCurrentBaseURI;
                xMLResourceIdentifier.setBaseSystemId(xMLResourceIdentifier.getExpandedSystemId());
                this.fCurrentBaseURI.setExpandedSystemId(strExpandSystemId);
                saveBaseURI();
            } catch (URI.MalformedURIException unused) {
            }
        }
    }

    public void processXMLLangAttributes(XMLAttributes xMLAttributes) {
        String value = xMLAttributes.getValue(NamespaceContext.XML_URI, "lang");
        if (value != null) {
            this.fCurrentLanguage = value;
            saveLanguage(value);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void processingInstruction(String str, XMLString xMLString, Augmentations augmentations) throws XNIException {
        if (this.fInDTD) {
            XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
            if (xMLDTDHandler != null) {
                xMLDTDHandler.processingInstruction(str, xMLString, augmentations);
                return;
            }
            return;
        }
        if (this.fDocumentHandler == null || getState() != 1) {
            return;
        }
        this.fDepth++;
        this.fDocumentHandler.processingInstruction(str, xMLString, modifyAugmentations(augmentations));
        this.fDepth--;
    }

    public void reportFatalError(String str) {
        reportFatalError(str, null);
    }

    public void reportResourceError(String str) {
        reportResourceError(str, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v15, types: [int] */
    /* JADX WARN: Type inference failed for: r13v16 */
    /* JADX WARN: Type inference failed for: r13v17, types: [int] */
    /* JADX WARN: Type inference failed for: r13v26 */
    /* JADX WARN: Type inference failed for: r13v27 */
    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) throws XNIException {
        boolean z;
        this.fNamespaceContext = null;
        boolean z2 = false;
        this.fDepth = 0;
        this.fResultDepth = isRootDocument() ? 0 : this.fParentXIncludeHandler.getResultDepth();
        this.fNotations.clear();
        this.fUnparsedEntities.clear();
        this.fParentRelativeURI = null;
        this.fIsXML11 = false;
        this.fInDTD = false;
        this.fSeenRootElement = false;
        this.fBaseURIScope.clear();
        this.fBaseURI.clear();
        this.fLiteralSystemID.clear();
        this.fExpandedSystemID.clear();
        this.fLanguageScope.clear();
        this.fLanguageStack.clear();
        int i = 0;
        while (true) {
            int[] iArr = this.fState;
            z = z2;
            if (i >= iArr.length) {
                break;
            }
            iArr[i] = 1;
            i++;
            z2 = z;
        }
        ?? r13 = z;
        while (true) {
            boolean[] zArr = this.fSawFallback;
            if (r13 >= zArr.length) {
                break;
            }
            zArr[r13] = z;
            r13++;
        }
        ?? r14 = z;
        while (true) {
            boolean[] zArr2 = this.fSawInclude;
            if (r14 < zArr2.length) {
                zArr2[r14] = z;
                r14++;
            } else {
                try {
                    break;
                } catch (XMLConfigurationException unused) {
                }
            }
        }
        if (!xMLComponentManager.getFeature(PARSER_SETTINGS)) {
            return;
        }
        this.fNeedCopyFeatures = true;
        try {
            boolean feature = xMLComponentManager.getFeature(ALLOW_UE_AND_NOTATION_EVENTS);
            this.fSendUEAndNotationEvents = feature;
            XMLParserConfiguration xMLParserConfiguration = this.fChildConfig;
            if (xMLParserConfiguration != null) {
                xMLParserConfiguration.setFeature(ALLOW_UE_AND_NOTATION_EVENTS, feature);
            }
        } catch (XMLConfigurationException unused2) {
        }
        try {
            boolean feature2 = xMLComponentManager.getFeature(XINCLUDE_FIXUP_BASE_URIS);
            this.fFixupBaseURIs = feature2;
            XMLParserConfiguration xMLParserConfiguration2 = this.fChildConfig;
            if (xMLParserConfiguration2 != null) {
                xMLParserConfiguration2.setFeature(XINCLUDE_FIXUP_BASE_URIS, feature2);
            }
        } catch (XMLConfigurationException unused3) {
            this.fFixupBaseURIs = true;
        }
        try {
            boolean feature3 = xMLComponentManager.getFeature(XINCLUDE_FIXUP_LANGUAGE);
            this.fFixupLanguage = feature3;
            XMLParserConfiguration xMLParserConfiguration3 = this.fChildConfig;
            if (xMLParserConfiguration3 != null) {
                xMLParserConfiguration3.setFeature(XINCLUDE_FIXUP_LANGUAGE, feature3);
            }
        } catch (XMLConfigurationException unused4) {
            this.fFixupLanguage = true;
        }
        try {
            SymbolTable symbolTable = (SymbolTable) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
            if (symbolTable != null) {
                this.fSymbolTable = symbolTable;
                XMLParserConfiguration xMLParserConfiguration4 = this.fChildConfig;
                if (xMLParserConfiguration4 != null) {
                    xMLParserConfiguration4.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
                }
            }
        } catch (XMLConfigurationException unused5) {
            this.fSymbolTable = null;
        }
        try {
            XMLErrorReporter xMLErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
            if (xMLErrorReporter != null) {
                setErrorReporter(xMLErrorReporter);
                XMLParserConfiguration xMLParserConfiguration5 = this.fChildConfig;
                if (xMLParserConfiguration5 != null) {
                    xMLParserConfiguration5.setProperty("http://apache.org/xml/properties/internal/error-reporter", xMLErrorReporter);
                }
            }
        } catch (XMLConfigurationException unused6) {
            this.fErrorReporter = null;
        }
        try {
            XMLEntityResolver xMLEntityResolver = (XMLEntityResolver) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
            if (xMLEntityResolver != null) {
                this.fEntityResolver = xMLEntityResolver;
                XMLParserConfiguration xMLParserConfiguration6 = this.fChildConfig;
                if (xMLParserConfiguration6 != null) {
                    xMLParserConfiguration6.setProperty("http://apache.org/xml/properties/internal/entity-resolver", xMLEntityResolver);
                }
            }
        } catch (XMLConfigurationException unused7) {
            this.fEntityResolver = null;
        }
        try {
            XMLSecurityManager xMLSecurityManager = (XMLSecurityManager) xMLComponentManager.getProperty("http://apache.org/xml/properties/security-manager");
            if (xMLSecurityManager != null) {
                this.fSecurityManager = xMLSecurityManager;
                XMLParserConfiguration xMLParserConfiguration7 = this.fChildConfig;
                if (xMLParserConfiguration7 != null) {
                    xMLParserConfiguration7.setProperty("http://apache.org/xml/properties/security-manager", xMLSecurityManager);
                }
            }
        } catch (XMLConfigurationException unused8) {
            this.fSecurityManager = null;
        }
        this.fSecurityPropertyMgr = (XMLSecurityPropertyManager) xMLComponentManager.getProperty("jdk.xml.xmlSecurityPropertyManager");
        this.fUseCatalog = xMLComponentManager.getFeature("http://javax.xml.XMLConstants/feature/useCatalog");
        this.fCatalogFile = (String) xMLComponentManager.getProperty(CatalogFeatures.Feature.FILES.getPropertyName());
        this.fDefer = (String) xMLComponentManager.getProperty(CatalogFeatures.Feature.DEFER.getPropertyName());
        this.fPrefer = (String) xMLComponentManager.getProperty(CatalogFeatures.Feature.PREFER.getPropertyName());
        this.fResolve = (String) xMLComponentManager.getProperty(CatalogFeatures.Feature.RESOLVE.getPropertyName());
        try {
            Integer num = (Integer) xMLComponentManager.getProperty(BUFFER_SIZE);
            if (num == null || num.intValue() <= 0) {
                this.fBufferSize = ((Integer) getPropertyDefault(BUFFER_SIZE)).intValue();
            } else {
                this.fBufferSize = num.intValue();
                XMLParserConfiguration xMLParserConfiguration8 = this.fChildConfig;
                if (xMLParserConfiguration8 != null) {
                    xMLParserConfiguration8.setProperty(BUFFER_SIZE, num);
                }
            }
        } catch (XMLConfigurationException unused9) {
            this.fBufferSize = ((Integer) getPropertyDefault(BUFFER_SIZE)).intValue();
        }
        XIncludeTextReader xIncludeTextReader = this.fXInclude10TextReader;
        if (xIncludeTextReader != null) {
            xIncludeTextReader.setBufferSize(this.fBufferSize);
        }
        XIncludeTextReader xIncludeTextReader2 = this.fXInclude11TextReader;
        if (xIncludeTextReader2 != null) {
            xIncludeTextReader2.setBufferSize(this.fBufferSize);
        }
        ParserConfigurationSettings parserConfigurationSettings = new ParserConfigurationSettings();
        this.fSettings = parserConfigurationSettings;
        copyFeatures(xMLComponentManager, parserConfigurationSettings);
        try {
            if (xMLComponentManager.getFeature(SCHEMA_VALIDATION)) {
                this.fSettings.setFeature(SCHEMA_VALIDATION, z);
                if (com.sun.org.apache.xerces.internal.impl.Constants.NS_XMLSCHEMA.equals(xMLComponentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage"))) {
                    this.fSettings.setFeature(VALIDATION, false);
                } else if (xMLComponentManager.getFeature(VALIDATION)) {
                    this.fSettings.setFeature(DYNAMIC_VALIDATION, true);
                }
            }
        } catch (XMLConfigurationException unused10) {
        }
    }

    public void restoreBaseURI() {
        this.fBaseURI.pop();
        this.fLiteralSystemID.pop();
        this.fExpandedSystemID.pop();
        this.fBaseURIScope.pop();
        this.fCurrentBaseURI.setBaseSystemId(this.fBaseURI.peek());
        this.fCurrentBaseURI.setLiteralSystemId(this.fLiteralSystemID.peek());
        this.fCurrentBaseURI.setExpandedSystemId(this.fExpandedSystemID.peek());
    }

    public String restoreLanguage() {
        this.fLanguageStack.pop();
        this.fLanguageScope.pop();
        return this.fLanguageStack.peek();
    }

    public boolean sameBaseURIAsIncludeParent() {
        String includeParentBaseURI = getIncludeParentBaseURI();
        return includeParentBaseURI != null && includeParentBaseURI.equals(this.fCurrentBaseURI.getExpandedSystemId());
    }

    public boolean sameLanguageAsIncludeParent() {
        String includeParentLanguage = getIncludeParentLanguage();
        return includeParentLanguage != null && includeParentLanguage.equalsIgnoreCase(this.fCurrentLanguage);
    }

    public void saveBaseURI() {
        this.fBaseURIScope.push(this.fDepth);
        this.fBaseURI.push(this.fCurrentBaseURI.getBaseSystemId());
        this.fLiteralSystemID.push(this.fCurrentBaseURI.getLiteralSystemId());
        this.fExpandedSystemID.push(this.fCurrentBaseURI.getExpandedSystemId());
    }

    public void saveLanguage(String str) {
        this.fLanguageScope.push(this.fDepth);
        this.fLanguageStack.push(str);
    }

    public boolean searchForRecursiveIncludes(String str) {
        if (str.equals(this.fCurrentBaseURI.getExpandedSystemId())) {
            return true;
        }
        XIncludeHandler xIncludeHandler = this.fParentXIncludeHandler;
        if (xIncludeHandler == null) {
            return false;
        }
        return xIncludeHandler.searchForRecursiveIncludes(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDTDSource
    public void setDTDHandler(XMLDTDHandler xMLDTDHandler) {
        this.fDTDHandler = xMLDTDHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void setDTDSource(XMLDTDSource xMLDTDSource) {
        this.fDTDSource = xMLDTDSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource
    public void setDocumentHandler(XMLDocumentHandler xMLDocumentHandler) {
        if (this.fDocumentHandler != xMLDocumentHandler) {
            this.fDocumentHandler = xMLDocumentHandler;
            XMLParserConfiguration xMLParserConfiguration = this.fXIncludeChildConfig;
            if (xMLParserConfiguration != null) {
                xMLParserConfiguration.setDocumentHandler(xMLDocumentHandler);
            }
            XMLParserConfiguration xMLParserConfiguration2 = this.fXPointerChildConfig;
            if (xMLParserConfiguration2 != null) {
                xMLParserConfiguration2.setDocumentHandler(xMLDocumentHandler);
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void setDocumentSource(XMLDocumentSource xMLDocumentSource) {
        this.fDocumentSource = xMLDocumentSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setFeature(String str, boolean z) throws XMLConfigurationException {
        if (str.equals(ALLOW_UE_AND_NOTATION_EVENTS)) {
            this.fSendUEAndNotationEvents = z;
        }
        ParserConfigurationSettings parserConfigurationSettings = this.fSettings;
        if (parserConfigurationSettings != null) {
            this.fNeedCopyFeatures = true;
            parserConfigurationSettings.setFeature(str, z);
        }
    }

    public void setHref(String str) {
        this.fHrefFromParent = str;
    }

    public void setParent(XIncludeHandler xIncludeHandler) {
        this.fParentXIncludeHandler = xIncludeHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setProperty(String str, Object obj) throws XMLConfigurationException {
        if (str.equals("http://apache.org/xml/properties/internal/symbol-table")) {
            this.fSymbolTable = (SymbolTable) obj;
            XMLParserConfiguration xMLParserConfiguration = this.fChildConfig;
            if (xMLParserConfiguration != null) {
                xMLParserConfiguration.setProperty(str, obj);
                return;
            }
            return;
        }
        if (str.equals("http://apache.org/xml/properties/internal/error-reporter")) {
            setErrorReporter((XMLErrorReporter) obj);
            XMLParserConfiguration xMLParserConfiguration2 = this.fChildConfig;
            if (xMLParserConfiguration2 != null) {
                xMLParserConfiguration2.setProperty(str, obj);
                return;
            }
            return;
        }
        if (str.equals("http://apache.org/xml/properties/internal/entity-resolver")) {
            this.fEntityResolver = (XMLEntityResolver) obj;
            XMLParserConfiguration xMLParserConfiguration3 = this.fChildConfig;
            if (xMLParserConfiguration3 != null) {
                xMLParserConfiguration3.setProperty(str, obj);
                return;
            }
            return;
        }
        if (str.equals("http://apache.org/xml/properties/security-manager")) {
            this.fSecurityManager = (XMLSecurityManager) obj;
            XMLParserConfiguration xMLParserConfiguration4 = this.fChildConfig;
            if (xMLParserConfiguration4 != null) {
                xMLParserConfiguration4.setProperty(str, obj);
                return;
            }
            return;
        }
        if (str.equals("jdk.xml.xmlSecurityPropertyManager")) {
            this.fSecurityPropertyMgr = (XMLSecurityPropertyManager) obj;
            XMLParserConfiguration xMLParserConfiguration5 = this.fChildConfig;
            if (xMLParserConfiguration5 != null) {
                xMLParserConfiguration5.setProperty("jdk.xml.xmlSecurityPropertyManager", obj);
                return;
            }
            return;
        }
        if (str.equals(BUFFER_SIZE)) {
            Integer num = (Integer) obj;
            XMLParserConfiguration xMLParserConfiguration6 = this.fChildConfig;
            if (xMLParserConfiguration6 != null) {
                xMLParserConfiguration6.setProperty(str, obj);
            }
            if (num == null || num.intValue() <= 0) {
                return;
            }
            int iIntValue = num.intValue();
            this.fBufferSize = iIntValue;
            XIncludeTextReader xIncludeTextReader = this.fXInclude10TextReader;
            if (xIncludeTextReader != null) {
                xIncludeTextReader.setBufferSize(iIntValue);
            }
            XIncludeTextReader xIncludeTextReader2 = this.fXInclude11TextReader;
            if (xIncludeTextReader2 != null) {
                xIncludeTextReader2.setBufferSize(this.fBufferSize);
            }
        }
    }

    public void setSawFallback(int i, boolean z) {
        boolean[] zArr = this.fSawFallback;
        if (i >= zArr.length) {
            boolean[] zArr2 = new boolean[i * 2];
            System.arraycopy(zArr, 0, zArr2, 0, zArr.length);
            this.fSawFallback = zArr2;
        }
        this.fSawFallback[i] = z;
    }

    public void setSawInclude(int i, boolean z) {
        boolean[] zArr = this.fSawInclude;
        if (i >= zArr.length) {
            boolean[] zArr2 = new boolean[i * 2];
            System.arraycopy(zArr, 0, zArr2, 0, zArr.length);
            this.fSawInclude = zArr2;
        }
        this.fSawInclude[i] = z;
    }

    public void setState(int i) {
        int i2 = this.fDepth;
        int[] iArr = this.fState;
        if (i2 >= iArr.length) {
            int[] iArr2 = new int[i2 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.fState = iArr2;
        }
        this.fState[this.fDepth] = i;
    }

    public void setXIncludeLocator(XMLLocatorWrapper xMLLocatorWrapper) {
        this.fXIncludeLocator = xMLLocatorWrapper;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startAttlist(String str, Augmentations augmentations) throws XNIException {
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.startAttlist(str, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startCDATA(Augmentations augmentations) throws XNIException {
        if (this.fDocumentHandler == null || getState() != 1 || this.fResultDepth == 0) {
            return;
        }
        this.fDocumentHandler.startCDATA(augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startConditional(short s, Augmentations augmentations) throws XNIException {
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.startConditional(s, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startDTD(XMLLocator xMLLocator, Augmentations augmentations) throws XNIException {
        this.fInDTD = true;
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.startDTD(xMLLocator, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startDocument(XMLLocator xMLLocator, String str, NamespaceContext namespaceContext, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler;
        this.fErrorReporter.setDocumentLocator(xMLLocator);
        if (!(namespaceContext instanceof XIncludeNamespaceSupport)) {
            reportFatalError("IncompatibleNamespaceContext");
        }
        this.fNamespaceContext = (XIncludeNamespaceSupport) namespaceContext;
        this.fDocLocation = xMLLocator;
        this.fXIncludeLocator.setLocator(xMLLocator);
        setupCurrentBaseURI(xMLLocator);
        saveBaseURI();
        if (augmentations == null) {
            augmentations = new AugmentationsImpl();
        }
        augmentations.putItem(CURRENT_BASE_URI, this.fCurrentBaseURI);
        if (!isRootDocument()) {
            XIncludeHandler xIncludeHandler = this.fParentXIncludeHandler;
            xIncludeHandler.fHasIncludeReportedContent = true;
            if (xIncludeHandler.searchForRecursiveIncludes(this.fCurrentBaseURI.getExpandedSystemId())) {
                reportFatalError("RecursiveInclude", new Object[]{this.fCurrentBaseURI.getExpandedSystemId()});
            }
        }
        String str2 = XMLSymbols.EMPTY_STRING;
        this.fCurrentLanguage = str2;
        saveLanguage(str2);
        if (!isRootDocument() || (xMLDocumentHandler = this.fDocumentHandler) == null) {
            return;
        }
        xMLDocumentHandler.startDocument(this.fXIncludeLocator, str, namespaceContext, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
        int i = this.fDepth;
        this.fDepth = i + 1;
        int state = getState(i);
        if (state == 3 && getState(this.fDepth - 2) == 3) {
            setState(2);
        } else {
            setState(state);
        }
        processXMLBaseAttributes(xMLAttributes);
        if (this.fFixupLanguage) {
            processXMLLangAttributes(xMLAttributes);
        }
        if (isIncludeElement(qName)) {
            if (handleIncludeElement(xMLAttributes)) {
                setState(2);
                return;
            } else {
                setState(3);
                return;
            }
        }
        if (isFallbackElement(qName)) {
            handleFallbackElement();
            return;
        }
        if (!hasXIncludeNamespace(qName)) {
            if (getState() == 1) {
                int i2 = this.fResultDepth;
                this.fResultDepth = i2 + 1;
                if (i2 == 0) {
                    checkMultipleRootElements();
                }
                if (this.fDocumentHandler != null) {
                    Augmentations augmentationsModifyAugmentations = modifyAugmentations(augmentations);
                    this.fDocumentHandler.startElement(qName, processAttributes(xMLAttributes), augmentationsModifyAugmentations);
                    return;
                }
                return;
            }
            return;
        }
        if (getSawInclude(this.fDepth - 1)) {
            reportFatalError("IncludeChild", new Object[]{qName.rawname});
        }
        if (getSawFallback(this.fDepth - 1)) {
            reportFatalError("FallbackChild", new Object[]{qName.rawname});
        }
        if (getState() == 1) {
            int i3 = this.fResultDepth;
            this.fResultDepth = i3 + 1;
            if (i3 == 0) {
                checkMultipleRootElements();
            }
            if (this.fDocumentHandler != null) {
                Augmentations augmentationsModifyAugmentations2 = modifyAugmentations(augmentations);
                this.fDocumentHandler.startElement(qName, processAttributes(xMLAttributes), augmentationsModifyAugmentations2);
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startExternalSubset(XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.startExternalSubset(xMLResourceIdentifier, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startGeneralEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
        if (getState() == 1) {
            if (this.fResultDepth == 0) {
                if (augmentations == null || !Boolean.TRUE.equals(augmentations.getItem(com.sun.org.apache.xerces.internal.impl.Constants.ENTITY_SKIPPED))) {
                    return;
                }
                reportFatalError("UnexpandedEntityReferenceIllegal");
                return;
            }
            XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
            if (xMLDocumentHandler != null) {
                xMLDocumentHandler.startGeneralEntity(str, xMLResourceIdentifier, str2, augmentations);
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startParameterEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.startParameterEntity(str, xMLResourceIdentifier, str2, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void textDecl(String str, String str2, Augmentations augmentations) throws XNIException {
        if (this.fDocumentHandler == null || getState() != 1) {
            return;
        }
        this.fDocumentHandler.textDecl(str, str2, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void unparsedEntityDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
        addUnparsedEntity(str, xMLResourceIdentifier, str2, augmentations);
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.unparsedEntityDecl(str, xMLResourceIdentifier, str2, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void xmlDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler;
        this.fIsXML11 = SerializerConstants.XMLVERSION11.equals(str);
        if (!isRootDocument() || (xMLDocumentHandler = this.fDocumentHandler) == null) {
            return;
        }
        xMLDocumentHandler.xmlDecl(str, str2, str3, augmentations);
    }

    public void reportFatalError(String str, Object[] objArr) {
        reportFatalError(str, objArr, null);
    }

    public void reportResourceError(String str, Object[] objArr) {
        reportResourceError(str, objArr, null);
    }

    public void reportFatalError(String str, Object[] objArr, Exception exc) {
        reportError(str, objArr, (short) 2, exc);
    }

    public void reportResourceError(String str, Object[] objArr, Exception exc) {
        reportError(str, objArr, (short) 0, exc);
    }

    public int getState(int i) {
        return this.fState[i];
    }

    public void copyFeatures(XMLComponentManager xMLComponentManager, XMLParserConfiguration xMLParserConfiguration) {
        copyFeatures1(com.sun.org.apache.xerces.internal.impl.Constants.getXercesFeatures(), com.sun.org.apache.xerces.internal.impl.Constants.XERCES_FEATURE_PREFIX, xMLComponentManager, xMLParserConfiguration);
        copyFeatures1(com.sun.org.apache.xerces.internal.impl.Constants.getSAXFeatures(), com.sun.org.apache.xerces.internal.impl.Constants.SAX_FEATURE_PREFIX, xMLComponentManager, xMLParserConfiguration);
    }

    public Augmentations modifyAugmentations(Augmentations augmentations) {
        return modifyAugmentations(augmentations, false);
    }

    private void copyFeatures1(Enumeration<Object> enumeration, String str, XMLComponentManager xMLComponentManager, XMLParserConfiguration xMLParserConfiguration) {
        while (enumeration.hasMoreElements()) {
            String str2 = str + ((String) enumeration.nextElement());
            try {
                xMLParserConfiguration.setFeature(str2, xMLComponentManager.getFeature(str2));
            } catch (XMLConfigurationException unused) {
            }
        }
    }
}
