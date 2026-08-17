package com.sun.org.apache.xerces.internal.impl;

import com.sun.org.apache.xerces.internal.impl.io.UCSReader;
import com.sun.org.apache.xerces.internal.impl.io.UTF16Reader;
import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;
import com.sun.org.apache.xerces.internal.impl.validation.ValidationManager;
import com.sun.org.apache.xerces.internal.util.AugmentationsImpl;
import com.sun.org.apache.xerces.internal.util.EncodingMap;
import com.sun.org.apache.xerces.internal.util.HTTPInputSource;
import com.sun.org.apache.xerces.internal.util.MessageFormatter;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.URI;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLEntityDescriptionImpl;
import com.sun.org.apache.xerces.internal.util.XMLResourceIdentifierImpl;
import com.sun.org.apache.xerces.internal.utils.XMLLimitAnalyzer;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.xml.internal.stream.Entity;
import com.sun.xml.internal.stream.StaxEntityResolverWrapper;
import com.sun.xml.internal.stream.StaxXMLInputSource;
import com.sun.xml.internal.stream.XMLEntityStorage;
import defpackage.knd;
import defpackage.u01;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.xml.catalog.CatalogException;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.catalog.CatalogManager;
import javax.xml.catalog.CatalogResolver;
import javax.xml.stream.XMLInputFactory;
import javax.xml.transform.Source;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlUtils;
import jdk.xml.internal.SecuritySupport;
import org.xml.sax.InputSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLEntityManager implements XMLComponent, XMLEntityResolver {
    protected static final String BUFFER_SIZE = "http://apache.org/xml/properties/input-buffer-size";
    private static final boolean DEBUG_BUFFER = false;
    private static final boolean DEBUG_ENCODINGS = false;
    private static final boolean DEBUG_ENTITIES = false;
    private static final boolean DEBUG_RESOLVER = false;
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    public static final int DEFAULT_INTERNAL_BUFFER_SIZE = 1024;
    public static final int DEFAULT_XMLDECL_BUFFER_SIZE = 64;
    private static final String DTDEntity;
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    static final String EXTERNAL_ACCESS_DEFAULT = "all";
    private static final Boolean[] FEATURE_DEFAULTS;
    protected static final String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    private static final Object[] PROPERTY_DEFAULTS;
    private static final String[] RECOGNIZED_PROPERTIES;
    protected static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    protected static final String STAX_ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/stax-entity-resolver";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    private static final String XMLEntity;
    private static final String XML_SECURITY_PROPERTY_MANAGER = "jdk.xml.xmlSecurityPropertyManager";
    private static char[] gAfterEscaping1;
    private static char[] gAfterEscaping2;
    private static char[] gHexChs;
    private static boolean[] gNeedEscaping;
    private static String gUserDir;
    private static URI gUserDirURI;
    protected final Object[] defaultEncoding;
    protected int entityExpansionIndex;
    protected String fAccessExternalDTD;
    protected boolean fAllowJavaEncodings;
    protected int fBufferSize;
    CatalogFeatures fCatalogFeatures;
    private String fCatalogFile;
    CatalogResolver fCatalogResolver;
    protected Entity.ScannedEntity fCurrentEntity;
    private String fDefer;
    protected Map<String, Entity> fEntities;
    private final Augmentations fEntityAugs;
    protected int fEntityExpansionCount;
    protected XMLEntityHandler fEntityHandler;
    protected XMLEntityResolver fEntityResolver;
    protected XMLEntityScanner fEntityScanner;
    protected Stack<Entity> fEntityStack;
    protected XMLEntityStorage fEntityStorage;
    protected XMLErrorReporter fErrorReporter;
    protected boolean fExternalGeneralEntities;
    protected boolean fExternalParameterEntities;
    boolean fISCreatedByResolver;
    protected boolean fInExternalSubset;
    protected XMLLimitAnalyzer fLimitAnalyzer;
    protected boolean fLoadExternalDTD;
    private String fPrefer;
    protected PropertyManager fPropertyManager;
    protected Stack<Reader> fReaderStack;
    boolean fReplaceEntityReferences;
    private String fResolve;
    private final XMLResourceIdentifierImpl fResourceIdentifier;
    protected XMLSecurityManager fSecurityManager;
    protected boolean fStandalone;
    protected StaxEntityResolverWrapper fStaxEntityResolver;
    protected boolean fStrictURI;
    boolean fSupportDTD;
    boolean fSupportExternalEntities;
    protected SymbolTable fSymbolTable;
    private boolean fUseCatalog;
    protected boolean fValidation;
    protected ValidationManager fValidationManager;
    protected boolean fWarnDuplicateEntityDef;
    protected XMLEntityScanner fXML10EntityScanner;
    protected XMLEntityScanner fXML11EntityScanner;
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
    protected static final String EXTERNAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    protected static final String WARN_ON_DUPLICATE_ENTITYDEF = "http://apache.org/xml/features/warn-on-duplicate-entitydef";
    protected static final String STANDARD_URI_CONFORMANT = "http://apache.org/xml/features/standard-uri-conformant";
    private static final String[] RECOGNIZED_FEATURES = {VALIDATION, EXTERNAL_GENERAL_ENTITIES, EXTERNAL_PARAMETER_ENTITIES, ALLOW_JAVA_ENCODINGS, WARN_ON_DUPLICATE_ENTITYDEF, STANDARD_URI_CONFORMANT, "http://javax.xml.XMLConstants/feature/useCatalog"};

    static {
        Boolean bool = Boolean.TRUE;
        Boolean bool2 = Boolean.FALSE;
        FEATURE_DEFAULTS = new Boolean[]{null, bool, bool, bool, bool2, bool2, Boolean.valueOf(JdkXmlUtils.USE_CATALOG_DEFAULT)};
        RECOGNIZED_PROPERTIES = new String[]{"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-resolver", VALIDATION_MANAGER, BUFFER_SIZE, "http://apache.org/xml/properties/security-manager", "jdk.xml.xmlSecurityPropertyManager", JdkXmlUtils.CATALOG_DEFER, JdkXmlUtils.CATALOG_FILES, JdkXmlUtils.CATALOG_PREFER, JdkXmlUtils.CATALOG_RESOLVE, JdkConstants.CDATA_CHUNK_SIZE};
        PROPERTY_DEFAULTS = new Object[]{null, null, null, null, 8192, null, null, null, null, null, null, Integer.valueOf(JdkConstants.CDATA_CHUNK_SIZE_DEFAULT)};
        XMLEntity = "[xml]".intern();
        DTDEntity = "[dtd]".intern();
        gNeedEscaping = new boolean[128];
        gAfterEscaping1 = new char[128];
        gAfterEscaping2 = new char[128];
        gHexChs = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        for (int i = 0; i <= 31; i++) {
            gNeedEscaping[i] = true;
            char[] cArr = gAfterEscaping1;
            char[] cArr2 = gHexChs;
            cArr[i] = cArr2[i >> 4];
            gAfterEscaping2[i] = cArr2[i & 15];
        }
        gNeedEscaping[127] = true;
        gAfterEscaping1[127] = '7';
        gAfterEscaping2[127] = 'F';
        char[] cArr3 = {' ', '<', '>', '#', '%', '\"', '{', '}', '|', '\\', '^', '~', '[', ']', '`'};
        for (int i2 = 0; i2 < 15; i2++) {
            char c = cArr3[i2];
            gNeedEscaping[c] = true;
            char[] cArr4 = gAfterEscaping1;
            char[] cArr5 = gHexChs;
            cArr4[c] = cArr5[c >> 4];
            gAfterEscaping2[c] = cArr5[c & 15];
        }
    }

    public XMLEntityManager(PropertyManager propertyManager) {
        this.fAllowJavaEncodings = true;
        this.fLoadExternalDTD = true;
        this.fSupportDTD = true;
        this.fReplaceEntityReferences = true;
        this.fSupportExternalEntities = true;
        this.fAccessExternalDTD = "all";
        this.fBufferSize = 8192;
        this.fSecurityManager = null;
        this.fLimitAnalyzer = null;
        this.fInExternalSubset = false;
        this.fEntityExpansionCount = 0;
        this.fEntities = new HashMap();
        this.fEntityStack = new Stack<>();
        this.fCurrentEntity = null;
        this.fISCreatedByResolver = false;
        this.defaultEncoding = new Object[]{"UTF-8", null};
        this.fResourceIdentifier = new XMLResourceIdentifierImpl();
        this.fEntityAugs = new AugmentationsImpl();
        this.fUseCatalog = true;
        this.fReaderStack = new Stack<>();
        this.fPropertyManager = propertyManager;
        this.fEntityStorage = new XMLEntityStorage(this);
        this.fEntityScanner = new XMLEntityScanner(propertyManager, this);
        reset(propertyManager);
    }

    public static void absolutizeAgainstUserDir(URI uri) throws URI.MalformedURIException {
        uri.absolutize(getUserDir());
    }

    public static OutputStream createOutputStream(String str) throws IOException {
        File parentFile;
        String strExpandSystemId = expandSystemId(str, null, true);
        if (strExpandSystemId != null) {
            str = strExpandSystemId;
        }
        URL url = new URL(str);
        String protocol = url.getProtocol();
        String host = url.getHost();
        if (protocol.equals("file") && (host == null || host.length() == 0 || host.equals("localhost"))) {
            File file = new File(getPathWithoutEscapes(url.getPath()));
            if (!file.exists() && (parentFile = file.getParentFile()) != null && !parentFile.exists()) {
                parentFile.mkdirs();
            }
            return new FileOutputStream(file);
        }
        URLConnection uRLConnectionOpenConnection = url.openConnection();
        uRLConnectionOpenConnection.setDoInput(false);
        uRLConnectionOpenConnection.setDoOutput(true);
        uRLConnectionOpenConnection.setUseCaches(false);
        if (uRLConnectionOpenConnection instanceof HttpURLConnection) {
            ((HttpURLConnection) uRLConnectionOpenConnection).setRequestMethod("PUT");
        }
        return uRLConnectionOpenConnection.getOutputStream();
    }

    /* JADX WARN: Code duplicated, block: B:39:0x00bb A[Catch: Exception -> 0x00c9, TryCatch #2 {Exception -> 0x00c9, blocks: (B:27:0x0074, B:29:0x007a, B:32:0x0081, B:40:0x00bf, B:35:0x0091, B:37:0x0098, B:38:0x00ad, B:39:0x00bb), top: B:51:0x0074, inners: #5 }] */
    /* JADX WARN: Code duplicated, block: B:44:0x00cc  */
    public static String expandSystemId(String str, String str2, boolean z) throws URI.MalformedURIException {
        URI uri;
        URI uri2;
        URI uri3 = null;
        if (str == null) {
            return null;
        }
        if (z) {
            try {
                new URI(str);
                return str;
            } catch (URI.MalformedURIException unused) {
                if (str2 == null || str2.length() == 0) {
                    uri2 = new URI("file", "", getUserDir().toString(), null, null);
                } else {
                    try {
                        uri2 = new URI(str2);
                    } catch (URI.MalformedURIException unused2) {
                        uri2 = new URI("file", "", getUserDir().toString() + str2, null, null);
                    }
                }
                return new URI(uri2, str).toString();
            }
        }
        try {
            try {
                return expandSystemIdStrictOff(str, str2);
            } catch (URISyntaxException unused3) {
                if (str.length() != 0) {
                    String strFixURI = fixURI(str);
                    if (str2 != null) {
                        try {
                            if (str2.length() == 0 || str2.equals(str)) {
                                uri = getUserDir();
                            } else {
                                try {
                                    uri = new URI(fixURI(str2).trim());
                                } catch (URI.MalformedURIException unused4) {
                                    uri = str2.indexOf(58) != -1 ? new URI("file", "", fixURI(str2).trim(), null, null) : new URI(getUserDir(), fixURI(str2));
                                }
                            }
                            uri3 = new URI(uri, strFixURI.trim());
                        } catch (Exception unused5) {
                            if (uri3 != null) {
                                return uri3.toString();
                            }
                            return str;
                        }
                        if (uri3 != null) {
                            return uri3.toString();
                        }
                    } else {
                        uri = getUserDir();
                        uri3 = new URI(uri, strFixURI.trim());
                        if (uri3 != null) {
                            return uri3.toString();
                        }
                    }
                }
                return str;
            }
        } catch (URI.MalformedURIException unused6) {
            return expandSystemIdStrictOff1(str, str2);
        }
    }

    private static String expandSystemIdStrictOff(String str, String str2) throws URI.MalformedURIException {
        URI userDir;
        URI uri = new URI(str, true);
        if (uri.isAbsoluteURI()) {
            if (uri.getScheme().length() > 1) {
                return str;
            }
            throw new URI.MalformedURIException();
        }
        if (str2 == null || str2.length() == 0) {
            userDir = getUserDir();
        } else {
            userDir = new URI(str2, true);
            if (!userDir.isAbsoluteURI()) {
                userDir.absolutize(getUserDir());
            }
        }
        uri.absolutize(userDir);
        return uri.toString();
    }

    private static String expandSystemIdStrictOff1(String str, String str2) throws URI.MalformedURIException, URISyntaxException {
        URI userDir;
        java.net.URI uri = new java.net.URI(str);
        if (uri.isAbsolute()) {
            if (uri.getScheme().length() > 1) {
                return str;
            }
            throw new URISyntaxException(str, "the scheme's length is only one character");
        }
        if (str2 == null || str2.length() == 0) {
            userDir = getUserDir();
        } else {
            userDir = new URI(str2, true);
            if (!userDir.isAbsoluteURI()) {
                userDir.absolutize(getUserDir());
            }
        }
        return new java.net.URI(userDir.toString()).resolve(uri).toString();
    }

    private static String expandSystemIdStrictOn(String str, String str2) throws URI.MalformedURIException {
        URI userDir;
        URI uri = new URI(str, true);
        if (uri.isAbsoluteURI()) {
            return str;
        }
        if (str2 == null || str2.length() == 0) {
            userDir = getUserDir();
        } else {
            userDir = new URI(str2, true);
            if (!userDir.isAbsoluteURI()) {
                userDir.absolutize(getUserDir());
            }
        }
        uri.absolutize(userDir);
        return uri.toString();
    }

    public static String fixURI(String str) {
        String strReplace = str.replace(File.separatorChar, '/');
        if (strReplace.length() >= 2) {
            char cCharAt = strReplace.charAt(1);
            if (cCharAt == ':') {
                char upperCase = Character.toUpperCase(strReplace.charAt(0));
                if (upperCase >= 'A' && upperCase <= 'Z') {
                    strReplace = PsuedoNames.PSEUDONAME_ROOT.concat(strReplace);
                }
            } else if (cCharAt == '/' && strReplace.charAt(0) == '/') {
                strReplace = "file:".concat(strReplace);
            }
        }
        int iIndexOf = strReplace.indexOf(32);
        if (iIndexOf < 0) {
            return strReplace;
        }
        StringBuilder sb = new StringBuilder(strReplace.length());
        for (int i = 0; i < iIndexOf; i++) {
            sb.append(strReplace.charAt(i));
        }
        sb.append("%20");
        for (int i2 = iIndexOf + 1; i2 < strReplace.length(); i2++) {
            if (strReplace.charAt(i2) == ' ') {
                sb.append("%20");
            } else {
                sb.append(strReplace.charAt(i2));
            }
        }
        return sb.toString();
    }

    private static String getPathWithoutEscapes(String str) {
        if (str == null || str.length() == 0 || str.indexOf(37) == -1) {
            return str;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, "%");
        StringBuilder sb = new StringBuilder(str.length());
        int iCountTokens = stringTokenizer.countTokens();
        sb.append(stringTokenizer.nextToken());
        for (int i = 1; i < iCountTokens; i++) {
            String strNextToken = stringTokenizer.nextToken();
            sb.append((char) Integer.valueOf(strNextToken.substring(0, 2), 16).intValue());
            sb.append(strNextToken.substring(2));
        }
        return sb.toString();
    }

    private static synchronized URI getUserDir() throws URI.MalformedURIException {
        char cCharAt;
        char upperCase;
        String systemProperty = "";
        try {
            systemProperty = SecuritySupport.getSystemProperty("user.dir");
        } catch (SecurityException unused) {
        }
        if (systemProperty.length() == 0) {
            return new URI("file", "", "", null, null);
        }
        if (gUserDirURI != null && systemProperty.equals(gUserDir)) {
            return gUserDirURI;
        }
        gUserDir = systemProperty;
        String strReplace = systemProperty.replace(File.separatorChar, '/');
        int length = strReplace.length();
        StringBuilder sb = new StringBuilder(length * 3);
        if (length >= 2 && strReplace.charAt(1) == ':' && (upperCase = Character.toUpperCase(strReplace.charAt(0))) >= 'A' && upperCase <= 'Z') {
            sb.append('/');
        }
        int i = 0;
        while (i < length && (cCharAt = strReplace.charAt(i)) < 128) {
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
            try {
                for (byte b : strReplace.substring(i).getBytes("UTF-8")) {
                    if (b < 0) {
                        int i2 = b + 256;
                        sb.append('%');
                        sb.append(gHexChs[i2 >> 4]);
                        sb.append(gHexChs[i2 & 15]);
                    } else if (gNeedEscaping[b]) {
                        sb.append('%');
                        sb.append(gAfterEscaping1[b]);
                        sb.append(gAfterEscaping2[b]);
                    } else {
                        sb.append((char) b);
                    }
                }
            } catch (UnsupportedEncodingException unused2) {
                return new URI("file", "", strReplace, null, null);
            }
        }
        if (!strReplace.endsWith(PsuedoNames.PSEUDONAME_ROOT)) {
            sb.append('/');
        }
        URI uri = new URI("file", "", sb.toString(), null, null);
        gUserDirURI = uri;
        return uri;
    }

    public void addExternalEntity(String str, String str2, String str3, String str4) throws IOException {
        Entity.ScannedEntity scannedEntity;
        XMLResourceIdentifier xMLResourceIdentifier;
        if (this.fEntities.containsKey(str)) {
            if (this.fWarnDuplicateEntityDef) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[]{str}, (short) 0);
                return;
            }
            return;
        }
        if (str4 == null) {
            int size = this.fEntityStack.size();
            if (size == 0 && (scannedEntity = this.fCurrentEntity) != null && (xMLResourceIdentifier = scannedEntity.entityLocation) != null) {
                str4 = xMLResourceIdentifier.getExpandedSystemId();
            }
            for (int i = size - 1; i >= 0; i--) {
                Entity.ScannedEntity scannedEntity2 = (Entity.ScannedEntity) this.fEntityStack.get(i);
                XMLResourceIdentifier xMLResourceIdentifier2 = scannedEntity2.entityLocation;
                if (xMLResourceIdentifier2 != null && xMLResourceIdentifier2.getExpandedSystemId() != null) {
                    str4 = scannedEntity2.entityLocation.getExpandedSystemId();
                    break;
                }
            }
        }
        String str5 = str4;
        this.fEntities.put(str, new Entity.ExternalEntity(str, new XMLEntityDescriptionImpl(str, str2, str3, str5, expandSystemId(str3, str5, false)), null, this.fInExternalSubset));
    }

    public void addInternalEntity(String str, String str2) {
        if (!this.fEntities.containsKey(str)) {
            this.fEntities.put(str, new Entity.InternalEntity(str, str2, this.fInExternalSubset));
        } else if (this.fWarnDuplicateEntityDef) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[]{str}, (short) 0);
        }
    }

    public void addUnparsedEntity(String str, String str2, String str3, String str4, String str5) {
        if (!this.fEntities.containsKey(str)) {
            this.fEntities.put(str, new Entity.ExternalEntity(str, new XMLEntityDescriptionImpl(str, str2, str3, str4, null), str5, this.fInExternalSubset));
        } else if (this.fWarnDuplicateEntityDef) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[]{str}, (short) 0);
        }
    }

    public void closeReaders() {
        while (!this.fReaderStack.isEmpty()) {
            try {
                this.fReaderStack.pop().close();
            } catch (IOException unused) {
            }
        }
    }

    public Reader createReader(InputStream inputStream, String str, Boolean bool) throws IOException {
        String upperCase = (str != null ? str : "UTF-8").toUpperCase(Locale.ENGLISH);
        MessageFormatter messageFormatter = this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210");
        Locale locale = this.fErrorReporter.getLocale();
        upperCase.getClass();
        switch (upperCase) {
            case "UTF-16":
                if (bool != null) {
                    return new UTF16Reader(inputStream, this.fBufferSize, bool.booleanValue(), messageFormatter, locale);
                }
                break;
            case "ISO-10646-UCS-2":
                if (bool == null) {
                    this.fErrorReporter.reportError((XMLLocator) getEntityScanner(), "http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingByteOrderUnsupported", new Object[]{str}, (short) 2);
                    break;
                } else {
                    return bool.booleanValue() ? new UCSReader(inputStream, (short) 2) : new UCSReader(inputStream, (short) 1);
                }
                break;
            case "ISO-10646-UCS-4":
                if (bool == null) {
                    this.fErrorReporter.reportError((XMLLocator) getEntityScanner(), "http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingByteOrderUnsupported", new Object[]{str}, (short) 2);
                    break;
                } else {
                    return bool.booleanValue() ? new UCSReader(inputStream, (short) 8) : new UCSReader(inputStream, (short) 4);
                }
                break;
            case "UTF-8":
                return new UTF8Reader(inputStream, this.fBufferSize, messageFormatter, locale);
            case "UTF-16BE":
                return new UTF16Reader(inputStream, this.fBufferSize, true, messageFormatter, locale);
            case "UTF-16LE":
                return new UTF16Reader(inputStream, this.fBufferSize, false, messageFormatter, locale);
        }
        boolean zIsValidIANAEncoding = XMLChar.isValidIANAEncoding(str);
        boolean zIsValidJavaEncoding = XMLChar.isValidJavaEncoding(str);
        if (!zIsValidIANAEncoding || (this.fAllowJavaEncodings && !zIsValidJavaEncoding)) {
            this.fErrorReporter.reportError((XMLLocator) getEntityScanner(), "http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingDeclInvalid", new Object[]{str}, (short) 2);
            str = "ISO-8859-1";
        }
        String iANA2JavaMapping = EncodingMap.getIANA2JavaMapping(upperCase);
        if (iANA2JavaMapping != null) {
            str = iANA2JavaMapping;
        } else if (!this.fAllowJavaEncodings) {
            this.fErrorReporter.reportError((XMLLocator) getEntityScanner(), "http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingDeclInvalid", new Object[]{str}, (short) 2);
            str = "ISO8859_1";
        }
        return new BufferedReader(new InputStreamReader(inputStream, str));
    }

    public void endEntity() throws IOException, XNIException {
        Entity.ScannedEntity scannedEntity = this.fEntityStack.size() > 0 ? (Entity.ScannedEntity) this.fEntityStack.pop() : null;
        Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
        if (scannedEntity2 != null) {
            try {
                XMLLimitAnalyzer xMLLimitAnalyzer = this.fLimitAnalyzer;
                if (xMLLimitAnalyzer != null) {
                    xMLLimitAnalyzer.endEntity(XMLSecurityManager.Limit.GENERAL_ENTITY_SIZE_LIMIT, scannedEntity2.name);
                    if (this.fCurrentEntity.name.equals("[xml]")) {
                        this.fSecurityManager.debugPrint(this.fLimitAnalyzer);
                    }
                }
                this.fCurrentEntity.close();
            } catch (IOException e) {
                knd.a(e);
                return;
            }
        }
        if (!this.fReaderStack.isEmpty()) {
            this.fReaderStack.pop();
        }
        XMLEntityHandler xMLEntityHandler = this.fEntityHandler;
        if (xMLEntityHandler != null) {
            if (scannedEntity == null) {
                this.fEntityAugs.removeAllItems();
                this.fEntityAugs.putItem(Constants.LAST_ENTITY, Boolean.TRUE);
                this.fEntityHandler.endEntity(this.fCurrentEntity.name, this.fEntityAugs);
                this.fEntityAugs.removeAllItems();
            } else {
                xMLEntityHandler.endEntity(this.fCurrentEntity.name, null);
            }
        }
        boolean z = this.fCurrentEntity.name == XMLEntity;
        this.fCurrentEntity = scannedEntity;
        this.fEntityScanner.setCurrentEntity(scannedEntity);
        if (!(!z) || !(this.fCurrentEntity == null)) {
            return;
        }
        u01.a();
    }

    public void endExternalSubset() {
        this.fInExternalSubset = false;
    }

    public int getColumnNumber() {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity == null) {
            return -1;
        }
        if (scannedEntity.isExternal()) {
            return this.fCurrentEntity.columnNumber;
        }
        for (int size = this.fEntityStack.size() - 1; size > 0; size--) {
            Entity.ScannedEntity scannedEntity2 = (Entity.ScannedEntity) this.fEntityStack.get(size);
            if (scannedEntity2.isExternal()) {
                return scannedEntity2.columnNumber;
            }
        }
        return -1;
    }

    public Entity.ScannedEntity getCurrentEntity() {
        return this.fCurrentEntity;
    }

    public XMLResourceIdentifier getCurrentResourceIdentifier() {
        return this.fResourceIdentifier;
    }

    public EncodingInfo getEncodingInfo(byte[] bArr, int i) {
        if (i < 2) {
            return EncodingInfo.UTF_8;
        }
        int i2 = bArr[0] & 255;
        int i3 = bArr[1] & 255;
        if (i2 == 254 && i3 == 255) {
            return EncodingInfo.UTF_16_BIG_ENDIAN_WITH_BOM;
        }
        if (i2 == 255 && i3 == 254) {
            return EncodingInfo.UTF_16_LITTLE_ENDIAN_WITH_BOM;
        }
        if (i < 3) {
            return EncodingInfo.UTF_8;
        }
        int i4 = bArr[2] & 255;
        if (i2 == 239 && i3 == 187 && i4 == 191) {
            return EncodingInfo.UTF_8_WITH_BOM;
        }
        if (i < 4) {
            return EncodingInfo.UTF_8;
        }
        int i5 = bArr[3] & 255;
        if (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 60) {
            return EncodingInfo.UCS_4_BIG_ENDIAN;
        }
        if (i2 == 60 && i3 == 0 && i4 == 0 && i5 == 0) {
            return EncodingInfo.UCS_4_LITTLE_ENDIAN;
        }
        if (i2 == 0 && i3 == 0 && i4 == 60 && i5 == 0) {
            return EncodingInfo.UCS_4_UNUSUAL_BYTE_ORDER;
        }
        if (i2 == 0 && i3 == 60 && i4 == 0 && i5 == 0) {
            return EncodingInfo.UCS_4_UNUSUAL_BYTE_ORDER;
        }
        if (i2 == 0 && i3 == 60 && i4 == 0 && i5 == 63) {
            return EncodingInfo.UTF_16_BIG_ENDIAN;
        }
        if (i2 == 60 && i3 == 0 && i4 == 63 && i5 == 0) {
            return EncodingInfo.UTF_16_LITTLE_ENDIAN;
        }
        return (i2 == 76 && i3 == 111 && i4 == 167 && i5 == 148) ? EncodingInfo.EBCDIC : EncodingInfo.UTF_8;
    }

    public XMLEntityScanner getEntityScanner() {
        if (this.fEntityScanner == null) {
            if (this.fXML10EntityScanner == null) {
                this.fXML10EntityScanner = new XMLEntityScanner();
            }
            this.fXML10EntityScanner.reset(this.fSymbolTable, this, this.fErrorReporter);
            this.fEntityScanner = this.fXML10EntityScanner;
        }
        return this.fEntityScanner;
    }

    public XMLEntityStorage getEntityStore() {
        return this.fEntityStorage;
    }

    public String getExpandedSystemId() {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity == null) {
            return null;
        }
        XMLResourceIdentifier xMLResourceIdentifier = scannedEntity.entityLocation;
        if (xMLResourceIdentifier != null && xMLResourceIdentifier.getExpandedSystemId() != null) {
            return this.fCurrentEntity.entityLocation.getExpandedSystemId();
        }
        for (int size = this.fEntityStack.size() - 1; size >= 0; size--) {
            Entity.ScannedEntity scannedEntity2 = (Entity.ScannedEntity) this.fEntityStack.get(size);
            XMLResourceIdentifier xMLResourceIdentifier2 = scannedEntity2.entityLocation;
            if (xMLResourceIdentifier2 != null && xMLResourceIdentifier2.getExpandedSystemId() != null) {
                return scannedEntity2.entityLocation.getExpandedSystemId();
            }
        }
        return null;
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

    public int getLineNumber() {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity == null) {
            return -1;
        }
        if (scannedEntity.isExternal()) {
            return this.fCurrentEntity.lineNumber;
        }
        for (int size = this.fEntityStack.size() - 1; size > 0; size--) {
            Entity.ScannedEntity scannedEntity2 = (Entity.ScannedEntity) this.fEntityStack.get(size);
            if (scannedEntity2.isExternal()) {
                return scannedEntity2.lineNumber;
            }
        }
        return -1;
    }

    public String getLiteralSystemId() {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity == null) {
            return null;
        }
        XMLResourceIdentifier xMLResourceIdentifier = scannedEntity.entityLocation;
        if (xMLResourceIdentifier != null && xMLResourceIdentifier.getLiteralSystemId() != null) {
            return this.fCurrentEntity.entityLocation.getLiteralSystemId();
        }
        for (int size = this.fEntityStack.size() - 1; size >= 0; size--) {
            Entity.ScannedEntity scannedEntity2 = (Entity.ScannedEntity) this.fEntityStack.get(size);
            XMLResourceIdentifier xMLResourceIdentifier2 = scannedEntity2.entityLocation;
            if (xMLResourceIdentifier2 != null && xMLResourceIdentifier2.getLiteralSystemId() != null) {
                return scannedEntity2.entityLocation.getLiteralSystemId();
            }
        }
        return null;
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

    public String getPublicId() {
        XMLResourceIdentifier xMLResourceIdentifier;
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity == null || (xMLResourceIdentifier = scannedEntity.entityLocation) == null) {
            return null;
        }
        return xMLResourceIdentifier.getPublicId();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
    }

    public Entity.ScannedEntity getTopLevelEntity() {
        return (Entity.ScannedEntity) (this.fEntityStack.empty() ? null : this.fEntityStack.get(0));
    }

    public boolean isDeclaredEntity(String str) {
        return this.fEntities.get(str) != null;
    }

    public boolean isEntityDeclInExternalSubset(String str) {
        Entity entity = this.fEntities.get(str);
        if (entity == null) {
            return false;
        }
        return entity.isEntityDeclInExternalSubset();
    }

    public boolean isExternalEntity(String str) {
        Entity entity = this.fEntities.get(str);
        if (entity == null) {
            return false;
        }
        return entity.isExternal();
    }

    public boolean isStandalone() {
        return this.fStandalone;
    }

    public boolean isUnparsedEntity(String str) {
        Entity entity = this.fEntities.get(str);
        if (entity == null) {
            return false;
        }
        return entity.isUnparsed();
    }

    public final void print() {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) throws XMLConfigurationException {
        if (!xMLComponentManager.getFeature(PARSER_SETTINGS, true)) {
            reset();
            XMLEntityScanner xMLEntityScanner = this.fEntityScanner;
            if (xMLEntityScanner != null) {
                xMLEntityScanner.reset(xMLComponentManager);
            }
            XMLEntityStorage xMLEntityStorage = this.fEntityStorage;
            if (xMLEntityStorage != null) {
                xMLEntityStorage.reset(xMLComponentManager);
                return;
            }
            return;
        }
        this.fValidation = xMLComponentManager.getFeature(VALIDATION, false);
        this.fExternalGeneralEntities = xMLComponentManager.getFeature(EXTERNAL_GENERAL_ENTITIES, true);
        this.fExternalParameterEntities = xMLComponentManager.getFeature(EXTERNAL_PARAMETER_ENTITIES, true);
        this.fAllowJavaEncodings = xMLComponentManager.getFeature(ALLOW_JAVA_ENCODINGS, false);
        this.fWarnDuplicateEntityDef = xMLComponentManager.getFeature(WARN_ON_DUPLICATE_ENTITYDEF, false);
        this.fStrictURI = xMLComponentManager.getFeature(STANDARD_URI_CONFORMANT, false);
        this.fLoadExternalDTD = xMLComponentManager.getFeature(LOAD_EXTERNAL_DTD, true);
        this.fSymbolTable = (SymbolTable) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        this.fEntityResolver = (XMLEntityResolver) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver", null);
        this.fStaxEntityResolver = (StaxEntityResolverWrapper) xMLComponentManager.getProperty(STAX_ENTITY_RESOLVER, null);
        this.fValidationManager = (ValidationManager) xMLComponentManager.getProperty(VALIDATION_MANAGER, null);
        XMLSecurityManager xMLSecurityManager = (XMLSecurityManager) xMLComponentManager.getProperty("http://apache.org/xml/properties/security-manager", null);
        this.fSecurityManager = xMLSecurityManager;
        this.entityExpansionIndex = xMLSecurityManager.getIndex(JdkConstants.SP_ENTITY_EXPANSION_LIMIT);
        this.fSupportDTD = true;
        this.fReplaceEntityReferences = true;
        this.fSupportExternalEntities = true;
        XMLSecurityPropertyManager xMLSecurityPropertyManager = (XMLSecurityPropertyManager) xMLComponentManager.getProperty("jdk.xml.xmlSecurityPropertyManager", null);
        if (xMLSecurityPropertyManager == null) {
            xMLSecurityPropertyManager = new XMLSecurityPropertyManager();
        }
        this.fAccessExternalDTD = xMLSecurityPropertyManager.getValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_DTD);
        this.fUseCatalog = xMLComponentManager.getFeature("http://javax.xml.XMLConstants/feature/useCatalog", true);
        this.fCatalogFile = (String) xMLComponentManager.getProperty(JdkXmlUtils.CATALOG_FILES);
        this.fDefer = (String) xMLComponentManager.getProperty(JdkXmlUtils.CATALOG_DEFER);
        this.fPrefer = (String) xMLComponentManager.getProperty(JdkXmlUtils.CATALOG_PREFER);
        this.fResolve = (String) xMLComponentManager.getProperty(JdkXmlUtils.CATALOG_RESOLVE);
        reset();
        this.fEntityScanner.reset(xMLComponentManager);
        this.fEntityStorage.reset(xMLComponentManager);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver
    public XMLInputSource resolveEntity(XMLResourceIdentifier xMLResourceIdentifier) throws IOException, XNIException {
        XMLInputSource xMLInputSource;
        InputSource inputSourceResolveEntity;
        Entity.ScannedEntity scannedEntity;
        XMLResourceIdentifier xMLResourceIdentifier2;
        if (xMLResourceIdentifier == null) {
            return null;
        }
        String publicId = xMLResourceIdentifier.getPublicId();
        String literalSystemId = xMLResourceIdentifier.getLiteralSystemId();
        String baseSystemId = xMLResourceIdentifier.getBaseSystemId();
        String expandedSystemId = xMLResourceIdentifier.getExpandedSystemId();
        boolean z = expandedSystemId == null;
        if (baseSystemId == null && (scannedEntity = this.fCurrentEntity) != null && (xMLResourceIdentifier2 = scannedEntity.entityLocation) != null && (baseSystemId = xMLResourceIdentifier2.getExpandedSystemId()) != null) {
            z = true;
        }
        if (z) {
            expandedSystemId = expandSystemId(literalSystemId, baseSystemId, false);
        }
        if (this.fEntityResolver != null) {
            xMLResourceIdentifier.setBaseSystemId(baseSystemId);
            xMLResourceIdentifier.setExpandedSystemId(expandedSystemId);
            xMLInputSource = this.fEntityResolver.resolveEntity(xMLResourceIdentifier);
        } else {
            xMLInputSource = null;
        }
        if (xMLInputSource == null && this.fUseCatalog) {
            if (this.fCatalogFeatures == null) {
                this.fCatalogFeatures = JdkXmlUtils.getCatalogFeatures(this.fDefer, this.fCatalogFile, this.fPrefer, this.fResolve);
            }
            String str = this.fCatalogFeatures.get(CatalogFeatures.Feature.FILES);
            this.fCatalogFile = str;
            if (str != null) {
                try {
                    if (this.fCatalogResolver == null) {
                        this.fCatalogResolver = CatalogManager.catalogResolver(this.fCatalogFeatures, new java.net.URI[0]);
                    }
                    String namespace = publicId != null ? publicId : xMLResourceIdentifier.getNamespace();
                    inputSourceResolveEntity = (namespace == null && literalSystemId == null) ? null : this.fCatalogResolver.resolveEntity(namespace, literalSystemId);
                } catch (CatalogException unused) {
                }
                if (inputSourceResolveEntity != null && !inputSourceResolveEntity.isEmpty()) {
                    xMLInputSource = new XMLInputSource(inputSourceResolveEntity, true);
                } else if (literalSystemId != null) {
                    if (this.fCatalogResolver == null) {
                        this.fCatalogResolver = CatalogManager.catalogResolver(this.fCatalogFeatures, new java.net.URI[0]);
                    }
                    try {
                        Source sourceResolve = this.fCatalogResolver.resolve(literalSystemId, baseSystemId);
                        if (sourceResolve != null && !sourceResolve.isEmpty()) {
                            xMLInputSource = new XMLInputSource(publicId, sourceResolve.getSystemId(), baseSystemId, true);
                        }
                    } catch (CatalogException e) {
                        knd.a(e);
                        return null;
                    }
                }
            }
        }
        return xMLInputSource == null ? new XMLInputSource(publicId, literalSystemId, baseSystemId, false) : xMLInputSource;
    }

    public StaxXMLInputSource resolveEntityAsPerStax(XMLResourceIdentifier xMLResourceIdentifier) throws IOException {
        XMLResourceIdentifierImpl xMLResourceIdentifierImpl;
        StaxXMLInputSource staxXMLInputSource;
        Entity.ScannedEntity scannedEntity;
        XMLResourceIdentifier xMLResourceIdentifier2;
        XMLInputSource xMLInputSourceResolveEntity = null;
        if (xMLResourceIdentifier == null) {
            return null;
        }
        String publicId = xMLResourceIdentifier.getPublicId();
        String literalSystemId = xMLResourceIdentifier.getLiteralSystemId();
        String baseSystemId = xMLResourceIdentifier.getBaseSystemId();
        String expandedSystemId = xMLResourceIdentifier.getExpandedSystemId();
        boolean z = expandedSystemId == null;
        if (baseSystemId == null && (scannedEntity = this.fCurrentEntity) != null && (xMLResourceIdentifier2 = scannedEntity.entityLocation) != null && (baseSystemId = xMLResourceIdentifier2.getExpandedSystemId()) != null) {
            z = true;
        }
        if (z) {
            expandedSystemId = expandSystemId(literalSystemId, baseSystemId, false);
        }
        if (xMLResourceIdentifier instanceof XMLResourceIdentifierImpl) {
            xMLResourceIdentifierImpl = (XMLResourceIdentifierImpl) xMLResourceIdentifier;
        } else {
            this.fResourceIdentifier.clear();
            xMLResourceIdentifierImpl = this.fResourceIdentifier;
        }
        xMLResourceIdentifierImpl.setValues(publicId, literalSystemId, baseSystemId, expandedSystemId);
        this.fISCreatedByResolver = false;
        StaxEntityResolverWrapper staxEntityResolverWrapper = this.fStaxEntityResolver;
        if (staxEntityResolverWrapper != null) {
            staxXMLInputSource = staxEntityResolverWrapper.resolveEntity(xMLResourceIdentifierImpl);
            if (staxXMLInputSource != null) {
                this.fISCreatedByResolver = true;
            }
        } else {
            staxXMLInputSource = null;
        }
        XMLEntityResolver xMLEntityResolver = this.fEntityResolver;
        if (xMLEntityResolver != null && (xMLInputSourceResolveEntity = xMLEntityResolver.resolveEntity(xMLResourceIdentifierImpl)) != null) {
            this.fISCreatedByResolver = true;
        }
        if (xMLInputSourceResolveEntity != null) {
            staxXMLInputSource = new StaxXMLInputSource(xMLInputSourceResolveEntity, this.fISCreatedByResolver);
        }
        if (staxXMLInputSource == null && this.fUseCatalog) {
            if (this.fCatalogFeatures == null) {
                this.fCatalogFeatures = JdkXmlUtils.getCatalogFeatures(this.fDefer, this.fCatalogFile, this.fPrefer, this.fResolve);
            }
            String str = this.fCatalogFeatures.get(CatalogFeatures.Feature.FILES);
            this.fCatalogFile = str;
            if (str != null) {
                try {
                    if (this.fCatalogResolver == null) {
                        this.fCatalogResolver = CatalogManager.catalogResolver(this.fCatalogFeatures, new java.net.URI[0]);
                    }
                    InputSource inputSourceResolveEntity = this.fCatalogResolver.resolveEntity(publicId, literalSystemId);
                    if (inputSourceResolveEntity != null && !inputSourceResolveEntity.isEmpty()) {
                        staxXMLInputSource = new StaxXMLInputSource(new XMLInputSource(inputSourceResolveEntity, true), true);
                    }
                } catch (CatalogException e) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CatalogException", new Object[]{SecuritySupport.sanitizePath(this.fCatalogFile)}, (short) 2, e);
                }
            }
        }
        if (staxXMLInputSource == null) {
            return new StaxXMLInputSource(new XMLInputSource(publicId, literalSystemId, baseSystemId, true), false);
        }
        staxXMLInputSource.hasXMLStreamOrXMLEventReader();
        return staxXMLInputSource;
    }

    public void setEntityHandler(XMLEntityHandler xMLEntityHandler) {
        this.fEntityHandler = xMLEntityHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setFeature(String str, boolean z) throws XMLConfigurationException {
        if (!str.startsWith(Constants.XERCES_FEATURE_PREFIX)) {
            if (str.equals("http://javax.xml.XMLConstants/feature/useCatalog")) {
                this.fUseCatalog = z;
                return;
            }
            return;
        }
        int length = str.length() - 31;
        if (length == 20 && str.endsWith(Constants.ALLOW_JAVA_ENCODINGS_FEATURE)) {
            this.fAllowJavaEncodings = z;
        }
        if (length == 31 && str.endsWith(Constants.LOAD_EXTERNAL_DTD_FEATURE)) {
            this.fLoadExternalDTD = z;
        }
    }

    public void setLimitAnalyzer(XMLLimitAnalyzer xMLLimitAnalyzer) {
        this.fLimitAnalyzer = xMLLimitAnalyzer;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setProperty(String str, Object obj) {
        Integer num;
        if (str.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            int length = str.length() - 33;
            if (length == 21 && str.endsWith(Constants.SYMBOL_TABLE_PROPERTY)) {
                this.fSymbolTable = (SymbolTable) obj;
                return;
            }
            if (length == 23 && str.endsWith(Constants.ERROR_REPORTER_PROPERTY)) {
                this.fErrorReporter = (XMLErrorReporter) obj;
                return;
            }
            if (length == 24 && str.endsWith(Constants.ENTITY_RESOLVER_PROPERTY)) {
                this.fEntityResolver = (XMLEntityResolver) obj;
                return;
            }
            if (length == 17 && str.endsWith(Constants.BUFFER_SIZE_PROPERTY) && (num = (Integer) obj) != null && num.intValue() > 64) {
                int iIntValue = num.intValue();
                this.fBufferSize = iIntValue;
                this.fEntityScanner.setBufferSize(iIntValue);
            }
            if (length == 16 && str.endsWith(Constants.SECURITY_MANAGER_PROPERTY)) {
                this.fSecurityManager = (XMLSecurityManager) obj;
            }
        }
        if (str.equals("jdk.xml.xmlSecurityPropertyManager")) {
            this.fAccessExternalDTD = ((XMLSecurityPropertyManager) obj).getValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_DTD);
            return;
        }
        if (str.equals(JdkXmlUtils.CATALOG_FILES)) {
            this.fCatalogFile = (String) obj;
            return;
        }
        if (str.equals(JdkXmlUtils.CATALOG_DEFER)) {
            this.fDefer = (String) obj;
        } else if (str.equals(JdkXmlUtils.CATALOG_PREFER)) {
            this.fPrefer = (String) obj;
        } else if (str.equals(JdkXmlUtils.CATALOG_RESOLVE)) {
            this.fResolve = (String) obj;
        }
    }

    public void setScannerVersion(short s) {
        if (s == 1) {
            if (this.fXML10EntityScanner == null) {
                this.fXML10EntityScanner = new XMLEntityScanner();
            }
            this.fXML10EntityScanner.reset(this.fSymbolTable, this, this.fErrorReporter);
            XMLEntityScanner xMLEntityScanner = this.fXML10EntityScanner;
            this.fEntityScanner = xMLEntityScanner;
            xMLEntityScanner.setCurrentEntity(this.fCurrentEntity);
            return;
        }
        if (this.fXML11EntityScanner == null) {
            this.fXML11EntityScanner = new XML11EntityScanner();
        }
        this.fXML11EntityScanner.reset(this.fSymbolTable, this, this.fErrorReporter);
        XMLEntityScanner xMLEntityScanner2 = this.fXML11EntityScanner;
        this.fEntityScanner = xMLEntityScanner2;
        xMLEntityScanner2.setCurrentEntity(this.fCurrentEntity);
    }

    public void setStandalone(boolean z) {
        this.fStandalone = z;
    }

    /* JADX WARN: Code duplicated, block: B:98:0x018b  */
    public String setupCurrentEntity(boolean z, String str, XMLInputSource xMLInputSource, boolean z2, boolean z3) throws IOException, XNIException {
        String str2;
        Reader reader;
        String str3;
        RewindableInputStream rewindableInputStream;
        int i;
        int i2;
        Boolean bool;
        int i3;
        int i4;
        Boolean bool2;
        Reader readerCreateReader;
        boolean followHTTPRedirects;
        String publicId = xMLInputSource.getPublicId();
        String systemId = xMLInputSource.getSystemId();
        String baseSystemId = xMLInputSource.getBaseSystemId();
        String encoding = xMLInputSource.getEncoding();
        boolean z4 = encoding != null;
        Reader characterStream = xMLInputSource.getCharacterStream();
        String strExpandSystemId = expandSystemId(systemId, baseSystemId, this.fStrictURI);
        if (baseSystemId == null) {
            baseSystemId = strExpandSystemId;
        }
        if (characterStream == null) {
            InputStream byteStream = xMLInputSource.getByteStream();
            if (byteStream == null) {
                URLConnection uRLConnectionOpenConnection = new URL(strExpandSystemId).openConnection();
                if (uRLConnectionOpenConnection instanceof HttpURLConnection) {
                    if (xMLInputSource instanceof HTTPInputSource) {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
                        HTTPInputSource hTTPInputSource = (HTTPInputSource) xMLInputSource;
                        Iterator<Map.Entry<String, String>> hTTPRequestProperties = hTTPInputSource.getHTTPRequestProperties();
                        while (hTTPRequestProperties.hasNext()) {
                            Map.Entry<String, String> next = hTTPRequestProperties.next();
                            httpURLConnection.setRequestProperty(next.getKey(), next.getValue());
                        }
                        followHTTPRedirects = hTTPInputSource.getFollowHTTPRedirects();
                        if (!followHTTPRedirects) {
                            httpURLConnection.setInstanceFollowRedirects(followHTTPRedirects);
                        }
                    } else {
                        followHTTPRedirects = true;
                    }
                    InputStream inputStream = uRLConnectionOpenConnection.getInputStream();
                    if (followHTTPRedirects) {
                        String string = uRLConnectionOpenConnection.getURL().toString();
                        if (!string.equals(strExpandSystemId)) {
                            systemId = string;
                            strExpandSystemId = systemId;
                        }
                    }
                    byteStream = inputStream;
                } else {
                    byteStream = uRLConnectionOpenConnection.getInputStream();
                }
            }
            RewindableInputStream rewindableInputStream2 = new RewindableInputStream(byteStream);
            if (encoding == null) {
                byte[] bArr = new byte[4];
                int i5 = 0;
                while (i5 < 4) {
                    bArr[i5] = (byte) rewindableInputStream2.readAndBuffer();
                    i5++;
                }
                if (i5 == 4) {
                    EncodingInfo encodingInfo = getEncodingInfo(bArr, i5);
                    String str4 = encodingInfo.autoDetectedEncoding;
                    String str5 = encodingInfo.readerEncoding;
                    Boolean bool3 = encodingInfo.isBigEndian;
                    rewindableInputStream2.reset();
                    if (encodingInfo.hasBOM) {
                        if ("UTF-8".equals(str5)) {
                            rewindableInputStream2.skip(3L);
                        } else if (EncodingInfo.STR_UTF16.equals(str5)) {
                            rewindableInputStream2.skip(2L);
                        }
                    }
                    readerCreateReader = createReader(rewindableInputStream2, str5, bool3);
                    encoding = str4;
                } else {
                    readerCreateReader = createReader(rewindableInputStream2, encoding, null);
                }
            } else {
                encoding = encoding.toUpperCase(Locale.ENGLISH);
                if ("UTF-8".equals(encoding)) {
                    int[] iArr = new int[3];
                    int i6 = 0;
                    while (i6 < 3) {
                        int andBuffer = rewindableInputStream2.readAndBuffer();
                        iArr[i6] = andBuffer;
                        if (andBuffer == -1) {
                            break;
                        }
                        i6++;
                    }
                    if (i6 != 3 || iArr[0] != 239 || iArr[1] != 187 || iArr[2] != 191) {
                        rewindableInputStream2.reset();
                    }
                } else {
                    if (EncodingInfo.STR_UTF16.equals(encoding)) {
                        int[] iArr2 = new int[4];
                        int i7 = 0;
                        while (i7 < 4) {
                            int andBuffer2 = rewindableInputStream2.readAndBuffer();
                            iArr2[i7] = andBuffer2;
                            if (andBuffer2 == -1) {
                                break;
                            }
                            i7++;
                        }
                        rewindableInputStream2.reset();
                        if (i7 >= 2) {
                            int i8 = iArr2[0];
                            int i9 = iArr2[1];
                            if (i8 == 254 && i9 == 255) {
                                bool = Boolean.TRUE;
                                rewindableInputStream2.skip(2L);
                            } else if (i8 == 255 && i9 == 254) {
                                bool = Boolean.FALSE;
                                rewindableInputStream2.skip(2L);
                            } else if (i7 == 4) {
                                int i10 = iArr2[2];
                                int i11 = iArr2[3];
                                if (i8 == 0 && i9 == 60 && i10 == 0) {
                                    i4 = 63;
                                    bool2 = i11 == 63 ? Boolean.TRUE : null;
                                    if (i8 != 60 && i9 == 0 && i10 == i4 && i11 == 0) {
                                        bool = Boolean.FALSE;
                                    } else {
                                        bool = bool2;
                                    }
                                } else {
                                    i4 = 63;
                                }
                                if (i8 != 60) {
                                    bool = bool2;
                                } else {
                                    bool = bool2;
                                }
                            }
                        }
                    } else if (EncodingInfo.STR_UCS4.equals(encoding)) {
                        int[] iArr3 = new int[4];
                        int i12 = 0;
                        while (i12 < 4) {
                            int andBuffer3 = rewindableInputStream2.readAndBuffer();
                            iArr3[i12] = andBuffer3;
                            if (andBuffer3 == -1) {
                                break;
                            }
                            i12++;
                        }
                        rewindableInputStream2.reset();
                        if (i12 == 4) {
                            int i13 = iArr3[0];
                            if (i13 == 0 && iArr3[1] == 0 && iArr3[2] == 0) {
                                i3 = 60;
                                if (iArr3[3] == 60) {
                                    bool = Boolean.TRUE;
                                }
                            } else {
                                i3 = 60;
                            }
                            if (i13 == i3 && iArr3[1] == 0 && iArr3[2] == 0 && iArr3[3] == 0) {
                                bool = Boolean.FALSE;
                            }
                        }
                    } else if (EncodingInfo.STR_UCS2.equals(encoding)) {
                        int[] iArr4 = new int[4];
                        int i14 = 0;
                        while (i14 < 4) {
                            int andBuffer4 = rewindableInputStream2.readAndBuffer();
                            iArr4[i14] = andBuffer4;
                            if (andBuffer4 == -1) {
                                break;
                            }
                            i14++;
                        }
                        rewindableInputStream2.reset();
                        if (i14 == 4) {
                            int i15 = iArr4[0];
                            if (i15 == 0) {
                                i = 60;
                                if (iArr4[1] == 60 && iArr4[2] == 0) {
                                    i2 = 63;
                                    if (iArr4[3] == 63) {
                                        bool = Boolean.TRUE;
                                    }
                                }
                                if (i15 != i && iArr4[1] == 0 && iArr4[2] == i2 && iArr4[3] == 0) {
                                    bool = Boolean.FALSE;
                                }
                            } else {
                                i = 60;
                            }
                            i2 = 63;
                            if (i15 != i) {
                            }
                        }
                    }
                    readerCreateReader = createReader(rewindableInputStream2, encoding, bool);
                }
                bool = null;
                readerCreateReader = createReader(rewindableInputStream2, encoding, bool);
            }
            rewindableInputStream = rewindableInputStream2;
            str2 = encoding;
            reader = readerCreateReader;
            str3 = strExpandSystemId;
        } else {
            str2 = encoding;
            reader = characterStream;
            str3 = strExpandSystemId;
            rewindableInputStream = null;
        }
        this.fReaderStack.push(reader);
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity != null) {
            this.fEntityStack.push(scannedEntity);
        }
        Entity.ScannedEntity scannedEntity2 = new Entity.ScannedEntity(z, str, new XMLResourceIdentifierImpl(publicId, systemId, baseSystemId, str3), rewindableInputStream, reader, str2, z2, z4, z3);
        this.fCurrentEntity = scannedEntity2;
        scannedEntity2.setEncodingExternallySpecified(z4);
        this.fEntityScanner.setCurrentEntity(this.fCurrentEntity);
        this.fResourceIdentifier.setValues(publicId, systemId, baseSystemId, str3);
        XMLLimitAnalyzer xMLLimitAnalyzer = this.fLimitAnalyzer;
        if (xMLLimitAnalyzer != null) {
            xMLLimitAnalyzer.startEntity(str);
        }
        return str2;
    }

    public void startDTDEntity(XMLInputSource xMLInputSource) throws IOException, XNIException {
        startEntity(false, DTDEntity, xMLInputSource, false, true);
    }

    public void startDocumentEntity(XMLInputSource xMLInputSource) throws IOException, XNIException {
        startEntity(false, XMLEntity, xMLInputSource, false, true);
    }

    public void startEntity(boolean z, String str, boolean z2) throws IOException, XNIException {
        Entity.ExternalEntity externalEntity;
        String literalSystemId;
        String baseSystemId;
        String strExpandSystemId;
        XMLInputSource xMLInputSource;
        String strCheckAccess;
        Entity entity = this.fEntityStorage.getEntity(str);
        if (entity == null) {
            if (this.fEntityHandler != null) {
                this.fResourceIdentifier.clear();
                this.fEntityAugs.removeAllItems();
                Augmentations augmentations = this.fEntityAugs;
                Boolean bool = Boolean.TRUE;
                augmentations.putItem(Constants.ENTITY_SKIPPED, bool);
                this.fEntityHandler.startEntity(str, this.fResourceIdentifier, null, this.fEntityAugs);
                this.fEntityAugs.removeAllItems();
                this.fEntityAugs.putItem(Constants.ENTITY_SKIPPED, bool);
                this.fEntityHandler.endEntity(str, this.fEntityAugs);
                return;
            }
            return;
        }
        boolean zIsExternal = entity.isExternal();
        if (zIsExternal) {
            externalEntity = (Entity.ExternalEntity) entity;
            XMLResourceIdentifier xMLResourceIdentifier = externalEntity.entityLocation;
            literalSystemId = xMLResourceIdentifier != null ? xMLResourceIdentifier.getLiteralSystemId() : null;
            XMLResourceIdentifier xMLResourceIdentifier2 = externalEntity.entityLocation;
            baseSystemId = xMLResourceIdentifier2 != null ? xMLResourceIdentifier2.getBaseSystemId() : null;
            strExpandSystemId = expandSystemId(literalSystemId, baseSystemId, this.fStrictURI);
            boolean zIsUnparsed = entity.isUnparsed();
            boolean zStartsWith = str.startsWith("%");
            if (zIsUnparsed || ((!zStartsWith && !this.fExternalGeneralEntities) || ((zStartsWith && !this.fExternalParameterEntities) || !this.fSupportDTD || !this.fSupportExternalEntities))) {
                if (this.fEntityHandler != null) {
                    this.fResourceIdentifier.clear();
                    XMLResourceIdentifierImpl xMLResourceIdentifierImpl = this.fResourceIdentifier;
                    XMLResourceIdentifier xMLResourceIdentifier3 = externalEntity.entityLocation;
                    xMLResourceIdentifierImpl.setValues(xMLResourceIdentifier3 != null ? xMLResourceIdentifier3.getPublicId() : null, literalSystemId, baseSystemId, strExpandSystemId);
                    this.fEntityAugs.removeAllItems();
                    Augmentations augmentations2 = this.fEntityAugs;
                    Boolean bool2 = Boolean.TRUE;
                    augmentations2.putItem(Constants.ENTITY_SKIPPED, bool2);
                    this.fEntityHandler.startEntity(str, this.fResourceIdentifier, null, this.fEntityAugs);
                    this.fEntityAugs.removeAllItems();
                    this.fEntityAugs.putItem(Constants.ENTITY_SKIPPED, bool2);
                    this.fEntityHandler.endEntity(str, this.fEntityAugs);
                    return;
                }
                return;
            }
        } else {
            externalEntity = null;
            literalSystemId = null;
            baseSystemId = null;
            strExpandSystemId = null;
        }
        int size = this.fEntityStack.size();
        int i = size;
        while (i >= 0) {
            if ((i == size ? this.fCurrentEntity : this.fEntityStack.get(i)).name == str) {
                String str2 = str;
                for (int i2 = i + 1; i2 < size; i2++) {
                    str2 = str2 + " -> " + this.fEntityStack.get(i2).name;
                }
                this.fErrorReporter.reportError((XMLLocator) getEntityScanner(), "http://www.w3.org/TR/1998/REC-xml-19980210", "RecursiveReference", new Object[]{str, (str2 + " -> " + this.fCurrentEntity.name) + " -> " + str}, (short) 2);
                if (this.fEntityHandler != null) {
                    this.fResourceIdentifier.clear();
                    if (zIsExternal) {
                        XMLResourceIdentifierImpl xMLResourceIdentifierImpl2 = this.fResourceIdentifier;
                        XMLResourceIdentifier xMLResourceIdentifier4 = externalEntity.entityLocation;
                        xMLResourceIdentifierImpl2.setValues(xMLResourceIdentifier4 != null ? xMLResourceIdentifier4.getPublicId() : null, literalSystemId, baseSystemId, strExpandSystemId);
                    }
                    this.fEntityAugs.removeAllItems();
                    Augmentations augmentations3 = this.fEntityAugs;
                    Boolean bool3 = Boolean.TRUE;
                    augmentations3.putItem(Constants.ENTITY_SKIPPED, bool3);
                    this.fEntityHandler.startEntity(str, this.fResourceIdentifier, null, this.fEntityAugs);
                    this.fEntityAugs.removeAllItems();
                    this.fEntityAugs.putItem(Constants.ENTITY_SKIPPED, bool3);
                    this.fEntityHandler.endEntity(str, this.fEntityAugs);
                    return;
                }
                return;
            }
            i--;
        }
        if (zIsExternal) {
            XMLInputSource xMLInputSource2 = resolveEntityAsPerStax(externalEntity.entityLocation).getXMLInputSource();
            if (!this.fISCreatedByResolver && (strCheckAccess = SecuritySupport.checkAccess(strExpandSystemId, this.fAccessExternalDTD, "all")) != null) {
                this.fErrorReporter.reportError((XMLLocator) getEntityScanner(), "http://www.w3.org/TR/1998/REC-xml-19980210", "AccessExternalEntity", new Object[]{SecuritySupport.sanitizePath(strExpandSystemId), strCheckAccess}, (short) 2);
            }
            xMLInputSource = xMLInputSource2;
        } else {
            xMLInputSource = new XMLInputSource((String) null, (String) null, (String) null, new StringReader(((Entity.InternalEntity) entity).text), (String) null);
        }
        startEntity(z, str, xMLInputSource, z2, zIsExternal);
    }

    public void startExternalSubset() {
        this.fInExternalSubset = true;
    }

    public void test() {
        this.fEntityStorage.addExternalEntity("entityUsecase1", null, "/space/home/stax/sun/6thJan2004/zephyr/data/test.txt", "/space/home/stax/sun/6thJan2004/zephyr/data/entity.xml");
        this.fEntityStorage.addInternalEntity("entityUsecase2", "<Test>value</Test>");
        this.fEntityStorage.addInternalEntity("entityUsecase3", "value3");
        this.fEntityStorage.addInternalEntity("text", "Hello World.");
        this.fEntityStorage.addInternalEntity("empty-element", "<foo/>");
        this.fEntityStorage.addInternalEntity("balanced-element", "<foo></foo>");
        this.fEntityStorage.addInternalEntity("balanced-element-with-text", "<foo>Hello, World</foo>");
        this.fEntityStorage.addInternalEntity("balanced-element-with-entity", "<foo>&text;</foo>");
        this.fEntityStorage.addInternalEntity("unbalanced-entity", "<foo>");
        this.fEntityStorage.addInternalEntity("recursive-entity", "<foo>&recursive-entity2;</foo>");
        this.fEntityStorage.addInternalEntity("recursive-entity2", "<bar>&recursive-entity3;</bar>");
        this.fEntityStorage.addInternalEntity("recursive-entity3", "<baz>&recursive-entity;</baz>");
        this.fEntityStorage.addInternalEntity("ch", "&#x00A9;");
        this.fEntityStorage.addInternalEntity("ch1", "&#84;");
        this.fEntityStorage.addInternalEntity("% ch2", com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_PARAMVARIABLE_STRING);
    }

    public static class EncodingInfo {
        public static final EncodingInfo EBCDIC;
        public static final String STR_CP037 = "CP037";
        public static final String STR_UCS2 = "ISO-10646-UCS-2";
        public static final String STR_UCS4 = "ISO-10646-UCS-4";
        public static final String STR_UTF16 = "UTF-16";
        public static final String STR_UTF16BE = "UTF-16BE";
        public static final String STR_UTF16LE = "UTF-16LE";
        public static final String STR_UTF8 = "UTF-8";
        public static final EncodingInfo UCS_4_BIG_ENDIAN;
        public static final EncodingInfo UCS_4_LITTLE_ENDIAN;
        public static final EncodingInfo UCS_4_UNUSUAL_BYTE_ORDER;
        public static final EncodingInfo UTF_16_BIG_ENDIAN;
        public static final EncodingInfo UTF_16_BIG_ENDIAN_WITH_BOM;
        public static final EncodingInfo UTF_16_LITTLE_ENDIAN;
        public static final EncodingInfo UTF_16_LITTLE_ENDIAN_WITH_BOM;
        public static final EncodingInfo UTF_8 = new EncodingInfo("UTF-8", null, false);
        public static final EncodingInfo UTF_8_WITH_BOM = new EncodingInfo("UTF-8", null, true);
        public final String autoDetectedEncoding;
        public final boolean hasBOM;
        public final Boolean isBigEndian;
        public final String readerEncoding;

        static {
            Boolean bool = Boolean.TRUE;
            UTF_16_BIG_ENDIAN = new EncodingInfo(STR_UTF16BE, STR_UTF16, bool, false);
            UTF_16_BIG_ENDIAN_WITH_BOM = new EncodingInfo(STR_UTF16BE, STR_UTF16, bool, true);
            Boolean bool2 = Boolean.FALSE;
            UTF_16_LITTLE_ENDIAN = new EncodingInfo(STR_UTF16LE, STR_UTF16, bool2, false);
            UTF_16_LITTLE_ENDIAN_WITH_BOM = new EncodingInfo(STR_UTF16LE, STR_UTF16, bool2, true);
            UCS_4_BIG_ENDIAN = new EncodingInfo(STR_UCS4, bool, false);
            UCS_4_LITTLE_ENDIAN = new EncodingInfo(STR_UCS4, bool2, false);
            UCS_4_UNUSUAL_BYTE_ORDER = new EncodingInfo(STR_UCS4, null, false);
            EBCDIC = new EncodingInfo(STR_CP037, null, false);
        }

        private EncodingInfo(String str, String str2, Boolean bool, boolean z) {
            this.autoDetectedEncoding = str;
            this.readerEncoding = str2;
            this.isBigEndian = bool;
            this.hasBOM = z;
        }

        private EncodingInfo(String str, Boolean bool, boolean z) {
            this(str, str, bool, z);
        }
    }

    public final class RewindableInputStream extends InputStream {
        private InputStream fInputStream;
        private byte[] fData = new byte[64];
        private int fStartOffset = 0;
        private int fEndOffset = -1;
        private int fOffset = 0;
        private int fLength = 0;
        private int fMark = 0;

        public RewindableInputStream(InputStream inputStream) {
            this.fInputStream = inputStream;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            int i = this.fLength;
            int i2 = this.fOffset;
            int i3 = i - i2;
            if (i3 != 0) {
                return i3;
            }
            if (i2 == this.fEndOffset) {
                return -1;
            }
            if (XMLEntityManager.this.fCurrentEntity.mayReadChunks) {
                return this.fInputStream.available();
            }
            return 0;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            InputStream inputStream = this.fInputStream;
            if (inputStream != null) {
                inputStream.close();
                this.fInputStream = null;
            }
        }

        @Override // java.io.InputStream
        public void mark(int i) {
            this.fMark = this.fOffset;
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = this.fLength;
            int i4 = this.fOffset;
            int i5 = i3 - i4;
            if (i5 != 0) {
                if (i2 >= i5) {
                    i2 = i5;
                } else if (i2 <= 0) {
                    return 0;
                }
                if (bArr != null) {
                    System.arraycopy(this.fData, i4, bArr, i, i2);
                }
                this.fOffset += i2;
                return i2;
            }
            if (i4 == this.fEndOffset) {
                return -1;
            }
            Entity.ScannedEntity scannedEntity = XMLEntityManager.this.fCurrentEntity;
            if (scannedEntity.mayReadChunks || !scannedEntity.xmlDeclChunkRead) {
                if (!scannedEntity.xmlDeclChunkRead) {
                    scannedEntity.xmlDeclChunkRead = true;
                    i2 = 28;
                }
                return this.fInputStream.read(bArr, i, i2);
            }
            int andBuffer = readAndBuffer();
            if (andBuffer == -1) {
                this.fEndOffset = this.fOffset;
                return -1;
            }
            bArr[i] = (byte) andBuffer;
            return 1;
        }

        public int readAndBuffer() throws IOException {
            int i = this.fOffset;
            byte[] bArr = this.fData;
            if (i == bArr.length) {
                byte[] bArr2 = new byte[i << 1];
                System.arraycopy(bArr, 0, bArr2, 0, i);
                this.fData = bArr2;
            }
            int i2 = this.fInputStream.read();
            if (i2 == -1) {
                this.fEndOffset = this.fOffset;
                return -1;
            }
            byte[] bArr3 = this.fData;
            int i3 = this.fLength;
            this.fLength = i3 + 1;
            bArr3[i3] = (byte) i2;
            this.fOffset++;
            return i2 & 255;
        }

        @Override // java.io.InputStream
        public void reset() {
            this.fOffset = this.fMark;
        }

        public void rewind() {
            this.fOffset = this.fStartOffset;
        }

        public void setStartOffset(int i) {
            this.fStartOffset = i;
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            if (j <= 0) {
                return 0L;
            }
            int i = this.fLength;
            int i2 = this.fOffset;
            int i3 = i - i2;
            if (i3 == 0) {
                if (i2 == this.fEndOffset) {
                    return 0L;
                }
                return this.fInputStream.skip(j);
            }
            long j2 = i3;
            if (j <= j2) {
                this.fOffset = (int) (((long) i2) + j);
                return j;
            }
            int i4 = i2 + i3;
            this.fOffset = i4;
            if (i4 == this.fEndOffset) {
                return j2;
            }
            return this.fInputStream.skip(j - j2) + j2;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            int i = this.fOffset;
            if (i < this.fLength) {
                byte[] bArr = this.fData;
                this.fOffset = i + 1;
                return bArr[i] & 255;
            }
            if (i == this.fEndOffset) {
                return -1;
            }
            if (XMLEntityManager.this.fCurrentEntity.mayReadChunks) {
                return this.fInputStream.read();
            }
            return readAndBuffer();
        }
    }

    public XMLEntityManager() {
        this.fAllowJavaEncodings = true;
        this.fLoadExternalDTD = true;
        this.fSupportDTD = true;
        this.fReplaceEntityReferences = true;
        this.fSupportExternalEntities = true;
        this.fAccessExternalDTD = "all";
        this.fBufferSize = 8192;
        this.fSecurityManager = null;
        this.fLimitAnalyzer = null;
        this.fInExternalSubset = false;
        this.fEntityExpansionCount = 0;
        this.fEntities = new HashMap();
        this.fEntityStack = new Stack<>();
        this.fCurrentEntity = null;
        this.fISCreatedByResolver = false;
        this.defaultEncoding = new Object[]{"UTF-8", null};
        this.fResourceIdentifier = new XMLResourceIdentifierImpl();
        this.fEntityAugs = new AugmentationsImpl();
        this.fUseCatalog = true;
        this.fReaderStack = new Stack<>();
        this.fSecurityManager = new XMLSecurityManager(true);
        this.fEntityStorage = new XMLEntityStorage(this);
        setScannerVersion((short) 1);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x006e A[Catch: Exception -> 0x0087, TryCatch #1 {Exception -> 0x0087, blocks: (B:10:0x0016, B:12:0x001c, B:15:0x0023, B:24:0x0081, B:18:0x002f, B:20:0x0036, B:22:0x0047, B:23:0x006e), top: B:33:0x0016, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:28:0x008a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:29:0x008b  */
    public static String expandSystemId(String str, String str2) {
        URI uri;
        URI uri2;
        URI uri3;
        if (str == null || str.length() == 0) {
            return str;
        }
        try {
            new URI(str);
            return str;
        } catch (URI.MalformedURIException unused) {
            String strFixURI = fixURI(str);
            if (str2 != null) {
                try {
                    if (str2.length() != 0 && !str2.equals(str)) {
                        try {
                            uri2 = new URI(fixURI(str2));
                        } catch (URI.MalformedURIException unused2) {
                            if (str2.indexOf(58) != -1) {
                                uri = new URI("file", "", fixURI(str2), null, null);
                            } else {
                                uri = new URI("file", "", getUserDir().toString() + fixURI(str2), null, null);
                            }
                            uri2 = uri;
                        }
                    } else {
                        uri2 = new URI("file", "", getUserDir().toString(), null, null);
                    }
                    uri3 = new URI(uri2, strFixURI);
                } catch (Exception unused3) {
                    uri3 = null;
                    if (uri3 == null) {
                        return str;
                    }
                    return uri3.toString();
                }
            } else {
                uri2 = new URI("file", "", getUserDir().toString(), null, null);
                uri3 = new URI(uri2, strFixURI);
            }
            if (uri3 == null) {
                return str;
            }
            return uri3.toString();
        }
    }

    public static String expandSystemId(String str) {
        return expandSystemId(str, null);
    }

    public void reset(PropertyManager propertyManager) {
        this.fSymbolTable = (SymbolTable) propertyManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fErrorReporter = (XMLErrorReporter) propertyManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        try {
            this.fStaxEntityResolver = (StaxEntityResolverWrapper) propertyManager.getProperty(STAX_ENTITY_RESOLVER);
        } catch (XMLConfigurationException unused) {
            this.fStaxEntityResolver = null;
        }
        this.fSupportDTD = ((Boolean) propertyManager.getProperty(XMLInputFactory.SUPPORT_DTD)).booleanValue();
        this.fReplaceEntityReferences = ((Boolean) propertyManager.getProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES)).booleanValue();
        this.fSupportExternalEntities = ((Boolean) propertyManager.getProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES)).booleanValue();
        this.fLoadExternalDTD = !((Boolean) propertyManager.getProperty("http://java.sun.com/xml/stream/properties/ignore-external-dtd")).booleanValue();
        this.fUseCatalog = ((Boolean) propertyManager.getProperty("http://javax.xml.XMLConstants/feature/useCatalog")).booleanValue();
        this.fCatalogFile = (String) propertyManager.getProperty(JdkXmlUtils.CATALOG_FILES);
        this.fDefer = (String) propertyManager.getProperty(JdkXmlUtils.CATALOG_DEFER);
        this.fPrefer = (String) propertyManager.getProperty(JdkXmlUtils.CATALOG_PREFER);
        this.fResolve = (String) propertyManager.getProperty(JdkXmlUtils.CATALOG_RESOLVE);
        this.fAccessExternalDTD = ((XMLSecurityPropertyManager) propertyManager.getProperty("jdk.xml.xmlSecurityPropertyManager")).getValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_DTD);
        this.fSecurityManager = (XMLSecurityManager) propertyManager.getProperty("http://apache.org/xml/properties/security-manager");
        this.fLimitAnalyzer = new XMLLimitAnalyzer();
        this.fEntityStorage.reset(propertyManager);
        this.fEntityScanner.reset(propertyManager);
        this.fEntities.clear();
        this.fEntityStack.removeAllElements();
        this.fCurrentEntity = null;
        this.fValidation = false;
        this.fExternalGeneralEntities = true;
        this.fExternalParameterEntities = true;
        this.fAllowJavaEncodings = true;
    }

    public void reset() {
        this.fLimitAnalyzer = new XMLLimitAnalyzer();
        this.fStandalone = false;
        this.fEntities.clear();
        this.fEntityStack.removeAllElements();
        this.fEntityExpansionCount = 0;
        this.fCurrentEntity = null;
        XMLEntityScanner xMLEntityScanner = this.fXML10EntityScanner;
        if (xMLEntityScanner != null) {
            xMLEntityScanner.reset(this.fSymbolTable, this, this.fErrorReporter);
        }
        XMLEntityScanner xMLEntityScanner2 = this.fXML11EntityScanner;
        if (xMLEntityScanner2 != null) {
            xMLEntityScanner2.reset(this.fSymbolTable, this, this.fErrorReporter);
        }
        this.fEntityHandler = null;
    }

    public void startEntity(boolean z, String str, XMLInputSource xMLInputSource, boolean z2, boolean z3) throws IOException, XNIException {
        String str2 = setupCurrentEntity(z, str, xMLInputSource, z2, z3);
        this.fEntityExpansionCount++;
        XMLLimitAnalyzer xMLLimitAnalyzer = this.fLimitAnalyzer;
        if (xMLLimitAnalyzer != null) {
            xMLLimitAnalyzer.addValue(this.entityExpansionIndex, str, 1);
        }
        XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
        if (xMLSecurityManager != null && xMLSecurityManager.isOverLimit(this.entityExpansionIndex, this.fLimitAnalyzer)) {
            this.fSecurityManager.debugPrint(this.fLimitAnalyzer);
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityExpansionLimit", new Object[]{this.fSecurityManager.getLimitValueByIndex(this.entityExpansionIndex)}, (short) 2);
            this.fEntityExpansionCount = 0;
        }
        XMLEntityHandler xMLEntityHandler = this.fEntityHandler;
        if (xMLEntityHandler != null) {
            xMLEntityHandler.startEntity(str, this.fResourceIdentifier, str2, null);
        }
    }
}
