package com.sun.org.apache.xerces.internal.impl.xs;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xerces.internal.dom.DOMErrorImpl;
import com.sun.org.apache.xerces.internal.dom.DOMMessageFormatter;
import com.sun.org.apache.xerces.internal.dom.DOMStringListImpl;
import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.dv.xs.SchemaDVFactoryImpl;
import com.sun.org.apache.xerces.internal.impl.xs.models.CMBuilder;
import com.sun.org.apache.xerces.internal.impl.xs.models.CMNodeFactory;
import com.sun.org.apache.xerces.internal.impl.xs.traversers.XSDHandler;
import com.sun.org.apache.xerces.internal.util.DOMEntityResolverWrapper;
import com.sun.org.apache.xerces.internal.util.DOMErrorHandlerWrapper;
import com.sun.org.apache.xerces.internal.util.DefaultErrorHandler;
import com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings;
import com.sun.org.apache.xerces.internal.util.Status;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.URI;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.grammars.Grammar;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarLoader;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.grammars.XSGrammar;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xs.LSInputList;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.org.apache.xerces.internal.xs.XSLoader;
import com.sun.org.apache.xerces.internal.xs.XSModel;
import defpackage.zi0;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.WeakHashMap;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlUtils;
import jdk.xml.internal.SecuritySupport;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMStringList;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.InputSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLSchemaLoader implements XMLGrammarLoader, XMLComponent, XSElementDeclHelper, XSLoader, DOMConfiguration {
    public static final String ACCESS_EXTERNAL_DTD = "http://javax.xml.XMLConstants/property/accessExternalDTD";
    public static final String ACCESS_EXTERNAL_SCHEMA = "http://javax.xml.XMLConstants/property/accessExternalSchema";
    public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    protected static final String LOCALE = "http://apache.org/xml/properties/locale";
    protected static final String OVERRIDE_PARSER = "jdk.xml.overrideDefaultParser";
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    protected static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    public static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    public static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private static final String XML_SECURITY_PROPERTY_MANAGER = "jdk.xml.xmlSecurityPropertyManager";
    private CMBuilder fCMBuilder;
    private XSDeclarationPool fDeclPool;
    private XMLEntityManager fEntityManager;
    private DOMErrorHandlerWrapper fErrorHandler;
    private XMLErrorReporter fErrorReporter;
    private String fExternalNoNSSchema;
    private String fExternalSchemas;
    private XSGrammarBucket fGrammarBucket;
    private XMLGrammarPool fGrammarPool;
    private boolean fIsCheckedFully;
    private WeakHashMap<Object, SchemaGrammar> fJAXPCache;
    private boolean fJAXPProcessed;
    private Object fJAXPSource;
    private final ParserConfigurationSettings fLoaderConfig;
    private Locale fLocale;
    private final CMNodeFactory fNodeFactory;
    private DOMStringList fRecognizedParameters;
    private DOMEntityResolverWrapper fResourceResolver;
    private XSDHandler fSchemaHandler;
    private boolean fSettingsChanged;
    private SubstitutionGroupHandler fSubGroupHandler;
    private XMLEntityResolver fUserEntityResolver;
    private XSDDescription fXSDDescription;
    private String faccessExternalSchema;
    protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String AUGMENT_PSVI = "http://apache.org/xml/features/validation/schema/augment-psvi";
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    protected static final String STANDARD_URI_CONFORMANT_FEATURE = "http://apache.org/xml/features/standard-uri-conformant";
    protected static final String DISALLOW_DOCTYPE = "http://apache.org/xml/features/disallow-doctype-decl";
    protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
    protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
    protected static final String NAMESPACE_GROWTH = "http://apache.org/xml/features/namespace-growth";
    protected static final String TOLERATE_DUPLICATES = "http://apache.org/xml/features/internal/tolerate-duplicates";
    private static final String[] RECOGNIZED_FEATURES = {SCHEMA_FULL_CHECKING, AUGMENT_PSVI, CONTINUE_AFTER_FATAL_ERROR, ALLOW_JAVA_ENCODINGS, STANDARD_URI_CONFORMANT_FEATURE, DISALLOW_DOCTYPE, "http://apache.org/xml/features/generate-synthetic-annotations", VALIDATE_ANNOTATIONS, HONOUR_ALL_SCHEMALOCATIONS, NAMESPACE_GROWTH, TOLERATE_DUPLICATES, "jdk.xml.overrideDefaultParser", "http://javax.xml.XMLConstants/feature/useCatalog"};
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    public static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String SCHEMA_LOCATION = "http://apache.org/xml/properties/schema/external-schemaLocation";
    protected static final String SCHEMA_NONS_LOCATION = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
    protected static final String SCHEMA_DV_FACTORY = "http://apache.org/xml/properties/internal/validation/schema/dv-factory";
    private static final String[] RECOGNIZED_PROPERTIES = {ENTITY_MANAGER, "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", ERROR_HANDLER, "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/grammar-pool", SCHEMA_LOCATION, SCHEMA_NONS_LOCATION, "http://java.sun.com/xml/jaxp/properties/schemaSource", "http://apache.org/xml/properties/security-manager", "http://apache.org/xml/properties/locale", SCHEMA_DV_FACTORY, "jdk.xml.xmlSecurityPropertyManager", JdkXmlUtils.CATALOG_DEFER, JdkXmlUtils.CATALOG_FILES, JdkXmlUtils.CATALOG_PREFER, JdkXmlUtils.CATALOG_RESOLVE, JdkConstants.CDATA_CHUNK_SIZE};

    public static class LocationArray {
        int length;
        String[] locations = new String[2];

        public void addLocation(String str) {
            int i = this.length;
            if (i >= this.locations.length) {
                resize(i, Math.max(1, i * 2));
            }
            String[] strArr = this.locations;
            int i2 = this.length;
            this.length = i2 + 1;
            strArr[i2] = str;
        }

        public String getFirstLocation() {
            if (this.length > 0) {
                return this.locations[0];
            }
            return null;
        }

        public int getLength() {
            return this.length;
        }

        public String[] getLocationArray() {
            int i = this.length;
            String[] strArr = this.locations;
            if (i < strArr.length) {
                resize(strArr.length, i);
            }
            return this.locations;
        }

        public void resize(int i, int i2) {
            String[] strArr = new String[i2];
            System.arraycopy(this.locations, 0, strArr, 0, Math.min(i, i2));
            this.locations = strArr;
            this.length = Math.min(i, i2);
        }
    }

    public XMLSchemaLoader(SymbolTable symbolTable, XMLErrorReporter xMLErrorReporter, XMLEntityManager xMLEntityManager, XSGrammarBucket xSGrammarBucket, SubstitutionGroupHandler substitutionGroupHandler, CMBuilder cMBuilder) {
        ParserConfigurationSettings parserConfigurationSettings = new ParserConfigurationSettings();
        this.fLoaderConfig = parserConfigurationSettings;
        this.fErrorReporter = new XMLErrorReporter();
        this.fEntityManager = null;
        this.fUserEntityResolver = null;
        this.fGrammarPool = null;
        this.fExternalSchemas = null;
        this.fExternalNoNSSchema = null;
        this.fJAXPSource = null;
        this.fIsCheckedFully = false;
        this.fJAXPProcessed = false;
        this.fSettingsChanged = true;
        this.fDeclPool = null;
        CMNodeFactory cMNodeFactory = new CMNodeFactory();
        this.fNodeFactory = cMNodeFactory;
        this.fXSDDescription = new XSDDescription();
        this.faccessExternalSchema = "all";
        this.fLocale = Locale.getDefault();
        this.fRecognizedParameters = null;
        this.fErrorHandler = null;
        this.fResourceResolver = null;
        parserConfigurationSettings.addRecognizedFeatures(RECOGNIZED_FEATURES);
        parserConfigurationSettings.addRecognizedProperties(RECOGNIZED_PROPERTIES);
        if (symbolTable != null) {
            parserConfigurationSettings.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
        }
        if (xMLErrorReporter == null) {
            xMLErrorReporter = new XMLErrorReporter();
            xMLErrorReporter.setLocale(this.fLocale);
            xMLErrorReporter.setProperty(ERROR_HANDLER, new DefaultErrorHandler());
        }
        this.fErrorReporter = xMLErrorReporter;
        if (xMLErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN) == null) {
            this.fErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
        }
        parserConfigurationSettings.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
        this.fEntityManager = xMLEntityManager;
        if (xMLEntityManager != null) {
            parserConfigurationSettings.setProperty(ENTITY_MANAGER, xMLEntityManager);
        }
        parserConfigurationSettings.setFeature(AUGMENT_PSVI, true);
        this.fGrammarBucket = xSGrammarBucket == null ? new XSGrammarBucket() : xSGrammarBucket;
        this.fSubGroupHandler = substitutionGroupHandler == null ? new SubstitutionGroupHandler(this) : substitutionGroupHandler;
        this.fCMBuilder = cMBuilder == null ? new CMBuilder(cMNodeFactory) : cMBuilder;
        this.fSchemaHandler = new XSDHandler(this.fGrammarBucket);
        this.fJAXPCache = new WeakHashMap<>();
        this.fSettingsChanged = true;
    }

    private void initGrammarBucket() {
        XMLGrammarPool xMLGrammarPool = this.fGrammarPool;
        if (xMLGrammarPool != null) {
            Grammar[] grammarArrRetrieveInitialGrammarSet = xMLGrammarPool.retrieveInitialGrammarSet("http://www.w3.org/2001/XMLSchema");
            int length = grammarArrRetrieveInitialGrammarSet != null ? grammarArrRetrieveInitialGrammarSet.length : 0;
            for (int i = 0; i < length; i++) {
                if (!this.fGrammarBucket.putGrammar((SchemaGrammar) grammarArrRetrieveInitialGrammarSet[i], true)) {
                    this.fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "GrammarConflict", null, (short) 0);
                }
            }
        }
    }

    public static void processExternalHints(String str, String str2, Map<String, LocationArray> map, XMLErrorReporter xMLErrorReporter) {
        if (str != null) {
            try {
                SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_SCHEMALOCATION).fType.validate(str, (ValidationContext) null, (ValidatedInfo) null);
                if (!tokenizeSchemaLocationStr(str, map, null)) {
                    xMLErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "SchemaLocation", new Object[]{str}, (short) 0);
                }
            } catch (InvalidDatatypeValueException e) {
                xMLErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, e.getKey(), e.getArgs(), (short) 0);
            }
        }
        if (str2 != null) {
            try {
                SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION).fType.validate(str2, (ValidationContext) null, (ValidatedInfo) null);
                String str3 = XMLSymbols.EMPTY_STRING;
                LocationArray locationArray = map.get(str3);
                if (locationArray == null) {
                    locationArray = new LocationArray();
                    map.put(str3, locationArray);
                }
                locationArray.addLocation(str2);
            } catch (InvalidDatatypeValueException e2) {
                xMLErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, e2.getKey(), e2.getArgs(), (short) 0);
            }
        }
    }

    private void processJAXPSchemaSource(Map<String, LocationArray> map) throws IOException {
        SchemaGrammar schemaGrammar;
        SchemaGrammar schemaGrammar2;
        this.fJAXPProcessed = true;
        Object obj = this.fJAXPSource;
        if (obj == null) {
            return;
        }
        Class<?> componentType = obj.getClass().getComponentType();
        if (componentType == null) {
            Object obj2 = this.fJAXPSource;
            if (((obj2 instanceof InputStream) || (obj2 instanceof InputSource)) && (schemaGrammar2 = this.fJAXPCache.get(obj2)) != null) {
                this.fGrammarBucket.putGrammar(schemaGrammar2);
                return;
            }
            this.fXSDDescription.reset();
            XMLInputSource xMLInputSourceXsdToXMLInputSource = xsdToXMLInputSource(this.fJAXPSource);
            String systemId = xMLInputSourceXsdToXMLInputSource.getSystemId();
            XSDDescription xSDDescription = this.fXSDDescription;
            xSDDescription.fContextType = (short) 3;
            if (systemId != null) {
                xSDDescription.setBaseSystemId(xMLInputSourceXsdToXMLInputSource.getBaseSystemId());
                this.fXSDDescription.setLiteralSystemId(systemId);
                this.fXSDDescription.setExpandedSystemId(systemId);
                this.fXSDDescription.fLocationHints = new String[]{systemId};
            }
            SchemaGrammar schemaGrammarLoadSchema = loadSchema(this.fXSDDescription, xMLInputSourceXsdToXMLInputSource, map);
            if (schemaGrammarLoadSchema != null) {
                Object obj3 = this.fJAXPSource;
                if ((obj3 instanceof InputStream) || (obj3 instanceof InputSource)) {
                    this.fJAXPCache.put(obj3, schemaGrammarLoadSchema);
                    if (this.fIsCheckedFully) {
                        XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fErrorReporter);
                    }
                }
                this.fGrammarBucket.putGrammar(schemaGrammarLoadSchema);
                return;
            }
            return;
        }
        if (componentType != Object.class && componentType != String.class && !File.class.isAssignableFrom(componentType) && !InputStream.class.isAssignableFrom(componentType) && !InputSource.class.isAssignableFrom(componentType) && !componentType.isInterface()) {
            throw new XMLConfigurationException(Status.NOT_SUPPORTED, this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN).formatMessage(this.fErrorReporter.getLocale(), "jaxp12-schema-source-type.2", new Object[]{componentType.getName()}));
        }
        Object[] objArr = (Object[]) this.fJAXPSource;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < objArr.length; i++) {
            Object obj4 = objArr[i];
            if (((obj4 instanceof InputStream) || (obj4 instanceof InputSource)) && (schemaGrammar = this.fJAXPCache.get(obj4)) != null) {
                this.fGrammarBucket.putGrammar(schemaGrammar);
            } else {
                this.fXSDDescription.reset();
                XMLInputSource xMLInputSourceXsdToXMLInputSource2 = xsdToXMLInputSource(objArr[i]);
                String systemId2 = xMLInputSourceXsdToXMLInputSource2.getSystemId();
                XSDDescription xSDDescription2 = this.fXSDDescription;
                xSDDescription2.fContextType = (short) 3;
                if (systemId2 != null) {
                    xSDDescription2.setBaseSystemId(xMLInputSourceXsdToXMLInputSource2.getBaseSystemId());
                    this.fXSDDescription.setLiteralSystemId(systemId2);
                    this.fXSDDescription.setExpandedSystemId(systemId2);
                    this.fXSDDescription.fLocationHints = new String[]{systemId2};
                }
                SchemaGrammar schema = this.fSchemaHandler.parseSchema(xMLInputSourceXsdToXMLInputSource2, this.fXSDDescription, map);
                if (this.fIsCheckedFully) {
                    XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fErrorReporter);
                }
                if (schema != null) {
                    String targetNamespace = schema.getTargetNamespace();
                    if (arrayList.contains(targetNamespace)) {
                        w01.a(this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN).formatMessage(this.fErrorReporter.getLocale(), "jaxp12-schema-source-ns", null));
                        return;
                    }
                    arrayList.add(targetNamespace);
                    Object obj5 = objArr[i];
                    if ((obj5 instanceof InputStream) || (obj5 instanceof InputSource)) {
                        this.fJAXPCache.put(obj5, schema);
                    }
                    this.fGrammarBucket.putGrammar(schema);
                } else {
                    continue;
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0022  */
    public static XMLInputSource resolveDocument(XSDDescription xSDDescription, Map<String, LocationArray> map, XMLEntityResolver xMLEntityResolver) throws IOException {
        String firstLocation;
        String[] locationHints;
        if (xSDDescription.getContextType() == 2 || xSDDescription.fromInstance()) {
            String targetNamespace = xSDDescription.getTargetNamespace();
            if (targetNamespace == null) {
                targetNamespace = XMLSymbols.EMPTY_STRING;
            }
            LocationArray locationArray = map.get(targetNamespace);
            if (locationArray != null) {
                firstLocation = locationArray.getFirstLocation();
            } else {
                firstLocation = null;
            }
        } else {
            firstLocation = null;
        }
        if (firstLocation == null && (locationHints = xSDDescription.getLocationHints()) != null && locationHints.length > 0) {
            firstLocation = locationHints[0];
        }
        String strExpandSystemId = XMLEntityManager.expandSystemId(firstLocation, xSDDescription.getBaseSystemId(), false);
        xSDDescription.setLiteralSystemId(firstLocation);
        xSDDescription.setExpandedSystemId(strExpandSystemId);
        return xMLEntityResolver.resolveEntity(xSDDescription);
    }

    private static XMLInputSource saxToXMLInputSource(InputSource inputSource) {
        String publicId = inputSource.getPublicId();
        String systemId = inputSource.getSystemId();
        Reader characterStream = inputSource.getCharacterStream();
        if (characterStream != null) {
            return new XMLInputSource(publicId, systemId, (String) null, characterStream, (String) null);
        }
        InputStream byteStream = inputSource.getByteStream();
        return byteStream != null ? new XMLInputSource(publicId, systemId, (String) null, byteStream, inputSource.getEncoding()) : new XMLInputSource(publicId, systemId, null, false);
    }

    public static boolean tokenizeSchemaLocationStr(String str, Map<String, LocationArray> map, String str2) {
        if (str == null) {
            return true;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, " \n\t\r");
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            if (!stringTokenizer.hasMoreTokens()) {
                return false;
            }
            String strNextToken2 = stringTokenizer.nextToken();
            LocationArray locationArray = map.get(strNextToken);
            if (locationArray == null) {
                locationArray = new LocationArray();
                map.put(strNextToken, locationArray);
            }
            if (str2 != null) {
                try {
                    strNextToken2 = XMLEntityManager.expandSystemId(strNextToken2, str2, false);
                } catch (URI.MalformedURIException unused) {
                }
            }
            locationArray.addLocation(strNextToken2);
        }
        return true;
    }

    private XMLInputSource xsdToXMLInputSource(Object obj) {
        BufferedInputStream bufferedInputStream;
        XMLInputSource xMLInputSourceResolveEntity;
        if (obj instanceof String) {
            String str = (String) obj;
            this.fXSDDescription.reset();
            this.fXSDDescription.setValues(null, str, null, null);
            try {
                xMLInputSourceResolveEntity = this.fEntityManager.resolveEntity(this.fXSDDescription);
            } catch (IOException unused) {
                this.fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "schema_reference.4", new Object[]{str}, (short) 1);
                xMLInputSourceResolveEntity = null;
            }
            return xMLInputSourceResolveEntity == null ? new XMLInputSource(null, str, null, false) : xMLInputSourceResolveEntity;
        }
        if (obj instanceof InputSource) {
            return saxToXMLInputSource((InputSource) obj);
        }
        if (obj instanceof InputStream) {
            return new XMLInputSource((String) null, (String) null, (String) null, (InputStream) obj, (String) null);
        }
        if (!(obj instanceof File)) {
            throw new XMLConfigurationException(Status.NOT_SUPPORTED, this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN).formatMessage(this.fErrorReporter.getLocale(), "jaxp12-schema-source-type.1", new Object[]{obj != null ? obj.getClass().getName() : PsiKeyword.NULL}));
        }
        File file = (File) obj;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException unused2) {
            this.fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "schema_reference.4", new Object[]{file.toString()}, (short) 1);
            bufferedInputStream = null;
        }
        return new XMLInputSource((String) null, file.toURI().toString(), (String) null, bufferedInputStream, (String) null);
    }

    @Override // org.w3c.dom.DOMConfiguration
    public boolean canSetParameter(String str, Object obj) {
        if (obj instanceof Boolean) {
            return str.equals("validate") || str.equals(SCHEMA_FULL_CHECKING) || str.equals(VALIDATE_ANNOTATIONS) || str.equals(CONTINUE_AFTER_FATAL_ERROR) || str.equals(ALLOW_JAVA_ENCODINGS) || str.equals(STANDARD_URI_CONFORMANT_FEATURE) || str.equals("http://apache.org/xml/features/generate-synthetic-annotations") || str.equals(HONOUR_ALL_SCHEMALOCATIONS) || str.equals(NAMESPACE_GROWTH) || str.equals(TOLERATE_DUPLICATES) || str.equals("jdk.xml.overrideDefaultParser");
        }
        return str.equals("error-handler") || str.equals(Constants.DOM_RESOURCE_RESOLVER) || str.equals("http://apache.org/xml/properties/internal/symbol-table") || str.equals("http://apache.org/xml/properties/internal/error-reporter") || str.equals(ERROR_HANDLER) || str.equals("http://apache.org/xml/properties/internal/entity-resolver") || str.equals("http://apache.org/xml/properties/internal/grammar-pool") || str.equals(SCHEMA_LOCATION) || str.equals(SCHEMA_NONS_LOCATION) || str.equals("http://java.sun.com/xml/jaxp/properties/schemaSource") || str.equals(SCHEMA_DV_FACTORY);
    }

    public XMLInputSource dom2xmlInputSource(LSInput lSInput) {
        if (lSInput.getCharacterStream() != null) {
            return new XMLInputSource(lSInput.getPublicId(), lSInput.getSystemId(), lSInput.getBaseURI(), lSInput.getCharacterStream(), XMLEntityManager.EncodingInfo.STR_UTF16);
        }
        if (lSInput.getByteStream() != null) {
            return new XMLInputSource(lSInput.getPublicId(), lSInput.getSystemId(), lSInput.getBaseURI(), lSInput.getByteStream(), lSInput.getEncoding());
        }
        return (lSInput.getStringData() == null || lSInput.getStringData().length() == 0) ? new XMLInputSource(lSInput.getPublicId(), lSInput.getSystemId(), lSInput.getBaseURI(), false) : new XMLInputSource(lSInput.getPublicId(), lSInput.getSystemId(), lSInput.getBaseURI(), new StringReader(lSInput.getStringData()), XMLEntityManager.EncodingInfo.STR_UTF16);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSLoader
    public DOMConfiguration getConfig() {
        return this;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarLoader
    public XMLEntityResolver getEntityResolver() {
        return this.fUserEntityResolver;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarLoader
    public XMLErrorHandler getErrorHandler() {
        return this.fErrorReporter.getErrorHandler();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarLoader
    public boolean getFeature(String str) throws XMLConfigurationException {
        return this.fLoaderConfig.getFeature(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public Boolean getFeatureDefault(String str) {
        if (str.equals(AUGMENT_PSVI)) {
            return Boolean.TRUE;
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.XSElementDeclHelper
    public XSElementDecl getGlobalElementDecl(QName qName) {
        SchemaGrammar grammar = this.fGrammarBucket.getGrammar(qName.uri);
        if (grammar != null) {
            return grammar.getGlobalElementDecl(qName.localpart);
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarLoader
    public Locale getLocale() {
        return this.fLocale;
    }

    @Override // org.w3c.dom.DOMConfiguration
    public Object getParameter(String str) throws DOMException {
        if (str.equals("error-handler")) {
            DOMErrorHandlerWrapper dOMErrorHandlerWrapper = this.fErrorHandler;
            if (dOMErrorHandlerWrapper != null) {
                return dOMErrorHandlerWrapper.getErrorHandler();
            }
            return null;
        }
        if (str.equals(Constants.DOM_RESOURCE_RESOLVER)) {
            DOMEntityResolverWrapper dOMEntityResolverWrapper = this.fResourceResolver;
            if (dOMEntityResolverWrapper != null) {
                return dOMEntityResolverWrapper.getEntityResolver();
            }
            return null;
        }
        try {
            try {
                return getFeature(str) ? Boolean.TRUE : Boolean.FALSE;
            } catch (Exception unused) {
                zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{str}));
                return null;
            }
        } catch (Exception unused2) {
            return getProperty(str);
        }
    }

    @Override // org.w3c.dom.DOMConfiguration
    public DOMStringList getParameterNames() {
        if (this.fRecognizedParameters == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("validate");
            arrayList.add("error-handler");
            arrayList.add(Constants.DOM_RESOURCE_RESOLVER);
            arrayList.add("http://apache.org/xml/properties/internal/symbol-table");
            arrayList.add("http://apache.org/xml/properties/internal/error-reporter");
            arrayList.add(ERROR_HANDLER);
            arrayList.add("http://apache.org/xml/properties/internal/entity-resolver");
            arrayList.add("http://apache.org/xml/properties/internal/grammar-pool");
            arrayList.add(SCHEMA_LOCATION);
            arrayList.add(SCHEMA_NONS_LOCATION);
            arrayList.add("http://java.sun.com/xml/jaxp/properties/schemaSource");
            arrayList.add(SCHEMA_FULL_CHECKING);
            arrayList.add(CONTINUE_AFTER_FATAL_ERROR);
            arrayList.add(ALLOW_JAVA_ENCODINGS);
            arrayList.add(STANDARD_URI_CONFORMANT_FEATURE);
            arrayList.add(VALIDATE_ANNOTATIONS);
            arrayList.add("http://apache.org/xml/features/generate-synthetic-annotations");
            arrayList.add(HONOUR_ALL_SCHEMALOCATIONS);
            arrayList.add(NAMESPACE_GROWTH);
            arrayList.add(TOLERATE_DUPLICATES);
            arrayList.add("jdk.xml.overrideDefaultParser");
            this.fRecognizedParameters = new DOMStringListImpl(arrayList);
        }
        return this.fRecognizedParameters;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarLoader
    public Object getProperty(String str) throws XMLConfigurationException {
        return this.fLoaderConfig.getProperty(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public Object getPropertyDefault(String str) {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarLoader, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarLoader, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSLoader
    public XSModel load(LSInput lSInput) {
        try {
            return ((XSGrammar) loadGrammar(dom2xmlInputSource(lSInput))).toXSModel();
        } catch (Exception e) {
            reportDOMFatalError(e);
            return null;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarLoader
    public Grammar loadGrammar(XMLInputSource xMLInputSource) throws IOException, XNIException {
        XMLGrammarPool xMLGrammarPool;
        reset(this.fLoaderConfig);
        this.fSettingsChanged = false;
        XSDDescription xSDDescription = new XSDDescription();
        xSDDescription.fContextType = (short) 3;
        xSDDescription.setBaseSystemId(xMLInputSource.getBaseSystemId());
        xSDDescription.setLiteralSystemId(xMLInputSource.getSystemId());
        HashMap map = new HashMap();
        processExternalHints(this.fExternalSchemas, this.fExternalNoNSSchema, map, this.fErrorReporter);
        SchemaGrammar schemaGrammarLoadSchema = loadSchema(xSDDescription, xMLInputSource, map);
        if (schemaGrammarLoadSchema != null && (xMLGrammarPool = this.fGrammarPool) != null) {
            xMLGrammarPool.cacheGrammars("http://www.w3.org/2001/XMLSchema", this.fGrammarBucket.getGrammars());
            if (this.fIsCheckedFully && this.fJAXPCache.get(schemaGrammarLoadSchema) != schemaGrammarLoadSchema) {
                XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fErrorReporter);
            }
        }
        return schemaGrammarLoadSchema;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSLoader
    public XSModel loadInputList(LSInputList lSInputList) {
        int length = lSInputList.getLength();
        SchemaGrammar[] schemaGrammarArr = new SchemaGrammar[length];
        for (int i = 0; i < length; i++) {
            try {
                schemaGrammarArr[i] = (SchemaGrammar) loadGrammar(dom2xmlInputSource(lSInputList.item(i)));
            } catch (Exception e) {
                reportDOMFatalError(e);
                return null;
            }
        }
        return new XSModelImpl(schemaGrammarArr);
    }

    public SchemaGrammar loadSchema(XSDDescription xSDDescription, XMLInputSource xMLInputSource, Map<String, LocationArray> map) throws IOException, XNIException {
        String strCheckAccess;
        if (!this.fJAXPProcessed) {
            processJAXPSchemaSource(map);
        }
        if (!xSDDescription.isExternal() || xMLInputSource.isCreatedByResolver() || (strCheckAccess = SecuritySupport.checkAccess(xSDDescription.getExpandedSystemId(), this.faccessExternalSchema, "all")) == null) {
            return this.fSchemaHandler.parseSchema(xMLInputSource, xSDDescription, map);
        }
        throw new XNIException(this.fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "schema_reference.access", new Object[]{SecuritySupport.sanitizePath(xSDDescription.getExpandedSystemId()), strCheckAccess}, (short) 1));
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSLoader
    public XSModel loadURI(String str) {
        try {
            return ((XSGrammar) loadGrammar(new XMLInputSource(null, str, null, false))).toXSModel();
        } catch (Exception e) {
            reportDOMFatalError(e);
            return null;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSLoader
    public XSModel loadURIList(StringList stringList) {
        int length = stringList.getLength();
        SchemaGrammar[] schemaGrammarArr = new SchemaGrammar[length];
        for (int i = 0; i < length; i++) {
            try {
                schemaGrammarArr[i] = (SchemaGrammar) loadGrammar(new XMLInputSource(null, stringList.item(i), null, false));
            } catch (Exception e) {
                reportDOMFatalError(e);
                return null;
            }
        }
        return new XSModelImpl(schemaGrammarArr);
    }

    public void reportDOMFatalError(Exception exc) {
        if (this.fErrorHandler != null) {
            DOMErrorImpl dOMErrorImpl = new DOMErrorImpl();
            dOMErrorImpl.fException = exc;
            dOMErrorImpl.fMessage = exc.getMessage();
            dOMErrorImpl.fSeverity = (short) 3;
            this.fErrorHandler.getErrorHandler().handleError(dOMErrorImpl);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) throws XMLConfigurationException {
        XMLSecurityPropertyManager xMLSecurityPropertyManager = (XMLSecurityPropertyManager) xMLComponentManager.getProperty("jdk.xml.xmlSecurityPropertyManager");
        if (xMLSecurityPropertyManager == null) {
            xMLSecurityPropertyManager = new XMLSecurityPropertyManager();
            setProperty("jdk.xml.xmlSecurityPropertyManager", xMLSecurityPropertyManager);
        }
        if (((XMLSecurityManager) xMLComponentManager.getProperty("http://apache.org/xml/properties/security-manager")) == null) {
            setProperty("http://apache.org/xml/properties/security-manager", new XMLSecurityManager(true));
        }
        this.faccessExternalSchema = xMLSecurityPropertyManager.getValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_SCHEMA);
        this.fGrammarBucket.reset();
        this.fSubGroupHandler.reset();
        if (!(xMLComponentManager != this.fLoaderConfig ? xMLComponentManager.getFeature(PARSER_SETTINGS, true) : true) || !this.fSettingsChanged) {
            this.fJAXPProcessed = false;
            initGrammarBucket();
            XSDeclarationPool xSDeclarationPool = this.fDeclPool;
            if (xSDeclarationPool != null) {
                xSDeclarationPool.reset();
                return;
            }
            return;
        }
        this.fNodeFactory.reset(xMLComponentManager);
        this.fEntityManager = (XMLEntityManager) xMLComponentManager.getProperty(ENTITY_MANAGER);
        this.fErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        SchemaDVFactory dVFactory = this.fSchemaHandler.getDVFactory();
        if (dVFactory == null) {
            dVFactory = SchemaDVFactory.getInstance();
            this.fSchemaHandler.setDVFactory(dVFactory);
        }
        try {
            this.fExternalSchemas = (String) xMLComponentManager.getProperty(SCHEMA_LOCATION);
            this.fExternalNoNSSchema = (String) xMLComponentManager.getProperty(SCHEMA_NONS_LOCATION);
        } catch (XMLConfigurationException unused) {
            this.fExternalSchemas = null;
            this.fExternalNoNSSchema = null;
        }
        this.fJAXPSource = xMLComponentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", null);
        this.fJAXPProcessed = false;
        this.fGrammarPool = (XMLGrammarPool) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool", null);
        initGrammarBucket();
        xMLComponentManager.getFeature(AUGMENT_PSVI, false);
        this.fCMBuilder.setDeclPool(null);
        this.fSchemaHandler.setDeclPool(null);
        if (dVFactory instanceof SchemaDVFactoryImpl) {
            ((SchemaDVFactoryImpl) dVFactory).setDeclPool(null);
        }
        try {
            boolean feature = xMLComponentManager.getFeature(CONTINUE_AFTER_FATAL_ERROR, false);
            if (!feature) {
                this.fErrorReporter.setFeature(CONTINUE_AFTER_FATAL_ERROR, feature);
            }
        } catch (XMLConfigurationException unused2) {
        }
        this.fIsCheckedFully = xMLComponentManager.getFeature(SCHEMA_FULL_CHECKING, false);
        this.fSchemaHandler.setGenerateSyntheticAnnotations(xMLComponentManager.getFeature("http://apache.org/xml/features/generate-synthetic-annotations", false));
        this.fSchemaHandler.reset(xMLComponentManager);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarLoader
    public void setEntityResolver(XMLEntityResolver xMLEntityResolver) {
        this.fUserEntityResolver = xMLEntityResolver;
        this.fLoaderConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", xMLEntityResolver);
        this.fEntityManager.setProperty("http://apache.org/xml/properties/internal/entity-resolver", xMLEntityResolver);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarLoader
    public void setErrorHandler(XMLErrorHandler xMLErrorHandler) {
        this.fErrorReporter.setProperty(ERROR_HANDLER, xMLErrorHandler);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarLoader, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setFeature(String str, boolean z) throws XMLConfigurationException {
        this.fSettingsChanged = true;
        if (str.equals(CONTINUE_AFTER_FATAL_ERROR)) {
            this.fErrorReporter.setFeature(CONTINUE_AFTER_FATAL_ERROR, z);
        } else if (str.equals("http://apache.org/xml/features/generate-synthetic-annotations")) {
            this.fSchemaHandler.setGenerateSyntheticAnnotations(z);
        }
        this.fLoaderConfig.setFeature(str, z);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarLoader
    public void setLocale(Locale locale) {
        this.fLocale = locale;
        this.fErrorReporter.setLocale(locale);
    }

    @Override // org.w3c.dom.DOMConfiguration
    public void setParameter(String str, Object obj) throws DOMException {
        if (obj instanceof Boolean) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            if (str.equals("validate") && zBooleanValue) {
                return;
            }
            try {
                setFeature(str, zBooleanValue);
                return;
            } catch (Exception unused) {
                zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{str}));
                return;
            }
        }
        try {
            if (str.equals("error-handler")) {
                if (!(obj instanceof DOMErrorHandler)) {
                    zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{str}));
                    return;
                }
                DOMErrorHandlerWrapper dOMErrorHandlerWrapper = new DOMErrorHandlerWrapper((DOMErrorHandler) obj);
                this.fErrorHandler = dOMErrorHandlerWrapper;
                setErrorHandler(dOMErrorHandlerWrapper);
                return;
            }
            if (!str.equals(Constants.DOM_RESOURCE_RESOLVER)) {
                try {
                    setProperty(str, obj);
                } catch (Exception unused2) {
                    zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{str}));
                }
            } else {
                if (!(obj instanceof LSResourceResolver)) {
                    zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{str}));
                    return;
                }
                DOMEntityResolverWrapper dOMEntityResolverWrapper = new DOMEntityResolverWrapper((LSResourceResolver) obj);
                this.fResourceResolver = dOMEntityResolverWrapper;
                setEntityResolver(dOMEntityResolverWrapper);
            }
        } catch (XMLConfigurationException unused3) {
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarLoader, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setProperty(String str, Object obj) throws XMLConfigurationException {
        this.fSettingsChanged = true;
        this.fLoaderConfig.setProperty(str, obj);
        if (str.equals("http://java.sun.com/xml/jaxp/properties/schemaSource")) {
            this.fJAXPSource = obj;
            this.fJAXPProcessed = false;
            return;
        }
        if (str.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
            this.fGrammarPool = (XMLGrammarPool) obj;
            return;
        }
        if (str.equals(SCHEMA_LOCATION)) {
            this.fExternalSchemas = (String) obj;
            return;
        }
        if (str.equals(SCHEMA_NONS_LOCATION)) {
            this.fExternalNoNSSchema = (String) obj;
            return;
        }
        if (str.equals("http://apache.org/xml/properties/locale")) {
            setLocale((Locale) obj);
            return;
        }
        if (str.equals("http://apache.org/xml/properties/internal/entity-resolver")) {
            this.fEntityManager.setProperty("http://apache.org/xml/properties/internal/entity-resolver", obj);
            return;
        }
        if (!str.equals("http://apache.org/xml/properties/internal/error-reporter")) {
            if (str.equals("jdk.xml.xmlSecurityPropertyManager")) {
                this.faccessExternalSchema = ((XMLSecurityPropertyManager) obj).getValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_SCHEMA);
            }
        } else {
            XMLErrorReporter xMLErrorReporter = (XMLErrorReporter) obj;
            this.fErrorReporter = xMLErrorReporter;
            if (xMLErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN) == null) {
                this.fErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
            }
        }
    }

    public void loadGrammar(XMLInputSource[] xMLInputSourceArr) throws IOException, XNIException {
        for (XMLInputSource xMLInputSource : xMLInputSourceArr) {
            loadGrammar(xMLInputSource);
        }
    }

    public XMLSchemaLoader(SymbolTable symbolTable) {
        this(symbolTable, null, new XMLEntityManager(), null, null, null);
    }

    public XMLSchemaLoader(XMLErrorReporter xMLErrorReporter, XSGrammarBucket xSGrammarBucket, SubstitutionGroupHandler substitutionGroupHandler, CMBuilder cMBuilder) {
        this(null, xMLErrorReporter, null, xSGrammarBucket, substitutionGroupHandler, cMBuilder);
    }

    public XMLSchemaLoader() {
        this(new SymbolTable(), null, new XMLEntityManager(), null, null, null);
    }
}
