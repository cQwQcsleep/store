package com.sun.org.apache.xalan.internal.xsltc.trax;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xalan.internal.utils.FeaturePropertyBase;
import com.sun.org.apache.xalan.internal.utils.ObjectFactory;
import com.sun.org.apache.xalan.internal.utils.XMLSecurityPropertyManager;
import com.sun.org.apache.xalan.internal.xsltc.compiler.SourceLoader;
import com.sun.org.apache.xalan.internal.xsltc.compiler.XSLTC;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.dom.XSLTCDTMManager;
import com.sun.org.apache.xml.internal.utils.StopParseException;
import com.sun.org.apache.xml.internal.utils.StylesheetPIHandler;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import defpackage.bke;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.xml.XMLConstants;
import javax.xml.catalog.CatalogException;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.catalog.CatalogManager;
import javax.xml.catalog.CatalogResolver;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TemplatesHandler;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkProperty;
import jdk.xml.internal.JdkXmlFeatures;
import jdk.xml.internal.JdkXmlUtils;
import jdk.xml.internal.SecuritySupport;
import jdk.xml.internal.TransformErrorListener;
import jdk.xml.internal.XMLSecurityManager;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TransformerFactoryImpl extends SAXTransformerFactory implements SourceLoader {
    public static final String AUTO_TRANSLET = "auto-translet";
    public static final String DEBUG = "debug";
    private static final String DEFAULT_TRANSLATE_PACKAGE = "die.verwandlung";
    protected static final String DEFAULT_TRANSLET_NAME = "GregorSamsa";
    public static final String DESTINATION_DIRECTORY = "destination-directory";
    public static final String ENABLE_INLINING = "enable-inlining";
    public static final String GENERATE_TRANSLET = "generate-translet";
    public static final String INDENT_NUMBER = "indent-number";
    public static final String JAR_NAME = "jar-name";
    public static final String PACKAGE_NAME = "package-name";
    public static final String TRANSLET_NAME = "translet-name";
    public static final String USE_CLASSPATH = "use-classpath";
    private String _accessExternalDTD;
    private String _accessExternalStylesheet;
    private boolean _autoTranslet;
    String _catalogDefer;
    CatalogFeatures _catalogFeatures;
    String _catalogFiles;
    String _catalogPrefer;
    String _catalogResolve;
    CatalogResolver _catalogUriResolver;
    int _cdataChunkSize;
    private boolean _debug;
    private final ErrorListener _defaultListener;
    private String _destinationDirectory;
    private boolean _enableInlining;
    private ErrorListener _errorListener;
    private JdkProperty<ClassLoader> _extensionClassLoader;
    private boolean _generateTranslet;
    private boolean _hasUserErrListener;
    private int _indentNumber;
    private boolean _isNotSecureProcessing;
    private boolean _isSecureMode;
    private String _jarFileName;
    private boolean _overrideDefaultParser;
    private String _packageName;
    private Map<Source, PIParamWrapper> _piParams;
    private String _transletName;
    private URIResolver _uriResolver;
    private boolean _useClasspath;
    private final JdkXmlFeatures _xmlFeatures;
    private XMLSecurityManager _xmlSecurityManager;
    private XMLSecurityPropertyManager _xmlSecurityPropertyMgr;
    private Map<String, Class<?>> _xsltcExtensionFunctions;
    CatalogFeatures.Builder cfBuilder;

    public static class PIParamWrapper {
        public String _charset;
        public String _media;
        public String _title;

        public PIParamWrapper(String str, String str2, String str3) {
            this._media = str;
            this._title = str2;
            this._charset = str3;
        }
    }

    public TransformerFactoryImpl() {
        TransformErrorListener transformErrorListener = new TransformErrorListener();
        this._defaultListener = transformErrorListener;
        this._errorListener = transformErrorListener;
        this._uriResolver = null;
        this._transletName = DEFAULT_TRANSLET_NAME;
        this._destinationDirectory = null;
        this._packageName = DEFAULT_TRANSLATE_PACKAGE;
        this._jarFileName = null;
        this._piParams = null;
        this._debug = false;
        this._enableInlining = false;
        this._generateTranslet = false;
        this._autoTranslet = false;
        this._useClasspath = false;
        this._indentNumber = -1;
        this._isNotSecureProcessing = true;
        this._isSecureMode = false;
        this._accessExternalStylesheet = "all";
        this._accessExternalDTD = "all";
        this._extensionClassLoader = null;
        this.cfBuilder = CatalogFeatures.builder();
        this._catalogFiles = null;
        this._catalogDefer = null;
        this._catalogPrefer = null;
        this._catalogResolve = null;
        this._cdataChunkSize = JdkConstants.CDATA_CHUNK_SIZE_DEFAULT;
        if (System.getSecurityManager() != null) {
            this._isSecureMode = true;
            this._isNotSecureProcessing = false;
        }
        JdkXmlFeatures jdkXmlFeatures = new JdkXmlFeatures(!this._isNotSecureProcessing);
        this._xmlFeatures = jdkXmlFeatures;
        this._overrideDefaultParser = jdkXmlFeatures.getFeature(JdkXmlFeatures.XmlFeature.JDK_OVERRIDE_PARSER);
        XMLSecurityPropertyManager xMLSecurityPropertyManager = new XMLSecurityPropertyManager();
        this._xmlSecurityPropertyMgr = xMLSecurityPropertyManager;
        this._accessExternalDTD = xMLSecurityPropertyManager.getValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_DTD);
        this._accessExternalStylesheet = this._xmlSecurityPropertyMgr.getValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_STYLESHEET);
        this._xmlSecurityManager = new XMLSecurityManager(true);
        this._xsltcExtensionFunctions = null;
        this._extensionClassLoader = new JdkProperty<>(JdkProperty.ImplPropMap.EXTCLSLOADER, ClassLoader.class, null, JdkProperty.State.DEFAULT);
    }

    private CatalogFeatures buildCatalogFeatures() {
        if (this._catalogFeatures == null) {
            this._catalogFeatures = this.cfBuilder.build();
        }
        this._catalogFiles = this._catalogFeatures.get(CatalogFeatures.Feature.FILES);
        this._catalogDefer = this._catalogFeatures.get(CatalogFeatures.Feature.DEFER);
        this._catalogPrefer = this._catalogFeatures.get(CatalogFeatures.Feature.PREFER);
        this._catalogResolve = this._catalogFeatures.get(CatalogFeatures.Feature.RESOLVE);
        return this._catalogFeatures;
    }

    private byte[][] getBytecodesFromClasses(Source source, String str) throws IOException {
        String str2;
        if (str == null) {
            return null;
        }
        String stylesheetFileName = getStylesheetFileName(source);
        File file = stylesheetFileName != null ? new File(stylesheetFileName) : null;
        int iLastIndexOf = str.lastIndexOf(46);
        String strSubstring = iLastIndexOf > 0 ? str.substring(iLastIndexOf + 1) : str;
        String strReplace = str.replace('.', '/');
        if (this._destinationDirectory != null) {
            str2 = this._destinationDirectory + PsuedoNames.PSEUDONAME_ROOT + strReplace + JavaClass.EXTENSION;
        } else if (file == null || file.getParent() == null) {
            str2 = strReplace + JavaClass.EXTENSION;
        } else {
            str2 = file.getParent() + PsuedoNames.PSEUDONAME_ROOT + strReplace + JavaClass.EXTENSION;
        }
        File file2 = new File(str2);
        if (!file2.exists()) {
            return null;
        }
        if (file != null && file.exists() && file2.lastModified() < file.lastModified()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int length = (int) file2.length();
        if (length > 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file2);
                byte[] bArr = new byte[length];
                readFromInputStream(bArr, fileInputStream, length);
                fileInputStream.close();
                arrayList.add(bArr);
                String parent = file2.getParent();
                if (parent == null) {
                    parent = SecuritySupport.getSystemProperty("user.dir");
                }
                File file3 = new File(parent);
                final String strConcat = strSubstring.concat("$");
                File[] fileArrListFiles = file3.listFiles(new FilenameFilter() { // from class: com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl.1
                    @Override // java.io.FilenameFilter
                    public boolean accept(File file4, String str3) {
                        return str3.endsWith(JavaClass.EXTENSION) && str3.startsWith(strConcat);
                    }
                });
                for (File file4 : fileArrListFiles) {
                    int length2 = (int) file4.length();
                    if (length2 > 0) {
                        try {
                            FileInputStream fileInputStream2 = new FileInputStream(file4);
                            byte[] bArr2 = new byte[length2];
                            readFromInputStream(bArr2, fileInputStream2, length2);
                            fileInputStream2.close();
                            arrayList.add(bArr2);
                        } catch (FileNotFoundException | IOException unused) {
                        }
                    }
                }
                int size = arrayList.size();
                if (size > 0) {
                    byte[][] bArr3 = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, size, 1);
                    for (int i = 0; i < size; i++) {
                        bArr3[i] = (byte[]) arrayList.get(i);
                    }
                    return bArr3;
                }
            } catch (FileNotFoundException | IOException unused2) {
            }
        }
        return null;
    }

    private byte[][] getBytecodesFromJar(Source source, String str) {
        String str2;
        String stylesheetFileName = getStylesheetFileName(source);
        File file = stylesheetFileName != null ? new File(stylesheetFileName) : null;
        if (this._destinationDirectory != null) {
            str2 = this._destinationDirectory + PsuedoNames.PSEUDONAME_ROOT + this._jarFileName;
        } else if (file == null || file.getParent() == null) {
            str2 = this._jarFileName;
        } else {
            str2 = file.getParent() + PsuedoNames.PSEUDONAME_ROOT + this._jarFileName;
        }
        File file2 = new File(str2);
        if (!file2.exists()) {
            return null;
        }
        if (file != null && file.exists() && file2.lastModified() < file.lastModified()) {
            return null;
        }
        try {
            ZipFile zipFile = new ZipFile(file2);
            String strReplace = str.replace('.', '/');
            String str3 = strReplace + "$";
            String str4 = strReplace + JavaClass.EXTENSION;
            ArrayList arrayList = new ArrayList();
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            while (enumerationEntries.hasMoreElements()) {
                ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                String name = zipEntryNextElement.getName();
                if (zipEntryNextElement.getSize() > 0 && (name.equals(str4) || (name.endsWith(JavaClass.EXTENSION) && name.startsWith(str3)))) {
                    try {
                        InputStream inputStream = zipFile.getInputStream(zipEntryNextElement);
                        int size = (int) zipEntryNextElement.getSize();
                        byte[] bArr = new byte[size];
                        readFromInputStream(bArr, inputStream, size);
                        inputStream.close();
                        arrayList.add(bArr);
                    } catch (IOException unused) {
                        return null;
                    }
                }
            }
            int size2 = arrayList.size();
            if (size2 > 0) {
                byte[][] bArr2 = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, size2, 1);
                for (int i = 0; i < size2; i++) {
                    bArr2[i] = (byte[]) arrayList.get(i);
                }
                return bArr2;
            }
        } catch (IOException unused2) {
        }
        return null;
    }

    private String getStylesheetFileName(Source source) {
        String systemId = source.getSystemId();
        if (systemId != null) {
            if (new File(systemId).exists()) {
                return systemId;
            }
            try {
                URL url = new URL(systemId);
                if ("file".equals(url.getProtocol())) {
                    return url.getFile();
                }
            } catch (MalformedURLException unused) {
            }
        }
        return null;
    }

    private String getTransletBaseName(Source source) {
        String strBaseName;
        if (!this._transletName.equals(DEFAULT_TRANSLET_NAME)) {
            return this._transletName;
        }
        String systemId = source.getSystemId();
        String javaName = (systemId == null || (strBaseName = Util.baseName(systemId)) == null) ? null : Util.toJavaName(Util.noExtName(strBaseName));
        return javaName != null ? javaName : DEFAULT_TRANSLET_NAME;
    }

    private void passErrorsToListener(List<ErrorMsg> list) {
        try {
            if (this._errorListener != null && list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    this._errorListener.error(new TransformerException(list.get(i).toString()));
                }
            }
        } catch (TransformerException unused) {
        }
    }

    private void passWarningsToListener(List<ErrorMsg> list) throws TransformerException {
        if (this._errorListener == null || list == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ErrorMsg errorMsg = list.get(i);
            boolean zIsWarningError = errorMsg.isWarningError();
            ErrorListener errorListener = this._errorListener;
            if (zIsWarningError) {
                errorListener.error(new TransformerConfigurationException(errorMsg.toString()));
            } else {
                errorListener.warning(new TransformerConfigurationException(errorMsg.toString()));
            }
        }
    }

    private void readFromInputStream(byte[] bArr, InputStream inputStream, int i) throws IOException {
        int i2 = 0;
        while (i > 0) {
            int i3 = inputStream.read(bArr, i2, i);
            if (i3 <= 0) {
                return;
            }
            i2 += i3;
            i -= i3;
        }
    }

    private void resetTransientAttributes() {
        this._transletName = DEFAULT_TRANSLET_NAME;
        this._destinationDirectory = null;
        this._packageName = DEFAULT_TRANSLATE_PACKAGE;
        this._jarFileName = null;
    }

    public final XSLTCDTMManager createNewDTMManagerInstance() {
        return XSLTCDTMManager.createNewDTMManagerInstance();
    }

    @Override // javax.xml.transform.TransformerFactory
    public Source getAssociatedStylesheet(Source source, String str, String str2, String str3) throws TransformerConfigurationException {
        StylesheetPIHandler stylesheetPIHandler = new StylesheetPIHandler(null, str, str2, str3);
        try {
            if (source instanceof DOMSource) {
                DOMSource dOMSource = (DOMSource) source;
                String systemId = dOMSource.getSystemId();
                DOM2SAX dom2sax = new DOM2SAX(dOMSource.getNode());
                stylesheetPIHandler.setBaseId(systemId);
                dom2sax.setContentHandler(stylesheetPIHandler);
                dom2sax.parse();
            } else {
                XMLReader xMLReader = source instanceof SAXSource ? ((SAXSource) source).getXMLReader() : null;
                InputSource inputSourceSourceToInputSource = SAXSource.sourceToInputSource(source);
                String systemId2 = inputSourceSourceToInputSource.getSystemId();
                if (xMLReader == null) {
                    xMLReader = JdkXmlUtils.getXMLReader(this._overrideDefaultParser, !this._isNotSecureProcessing);
                }
                stylesheetPIHandler.setBaseId(systemId2);
                xMLReader.setContentHandler(stylesheetPIHandler);
                xMLReader.parse(inputSourceSourceToInputSource);
            }
            URIResolver uRIResolver = this._uriResolver;
            if (uRIResolver != null) {
                stylesheetPIHandler.setURIResolver(uRIResolver);
            }
        } catch (StopParseException unused) {
        } catch (IOException | SAXException e) {
            throw new TransformerConfigurationException("getAssociatedStylesheets failed", e);
        }
        return stylesheetPIHandler.getAssociatedStylesheet();
    }

    @Override // javax.xml.transform.TransformerFactory
    public Object getAttribute(String str) throws IllegalArgumentException {
        if (str.equals(TRANSLET_NAME)) {
            return this._transletName;
        }
        if (str.equals(GENERATE_TRANSLET)) {
            return Boolean.valueOf(this._generateTranslet);
        }
        if (str.equals(AUTO_TRANSLET)) {
            return Boolean.valueOf(this._autoTranslet);
        }
        if (str.equals(ENABLE_INLINING)) {
            return this._enableInlining ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equals("http://apache.org/xml/properties/security-manager")) {
            return this._xmlSecurityManager;
        }
        if (JdkProperty.ImplPropMap.EXTCLSLOADER.is(str)) {
            JdkProperty<ClassLoader> jdkProperty = this._extensionClassLoader;
            if (jdkProperty == null) {
                return null;
            }
            return jdkProperty.getValue();
        }
        if (JdkXmlUtils.CATALOG_FILES.equals(str)) {
            return this._catalogFiles;
        }
        if (JdkXmlUtils.CATALOG_DEFER.equals(str)) {
            return this._catalogDefer;
        }
        if (JdkXmlUtils.CATALOG_PREFER.equals(str)) {
            return this._catalogPrefer;
        }
        if (JdkXmlUtils.CATALOG_RESOLVE.equals(str)) {
            return this._catalogResolve;
        }
        if (JdkXmlFeatures.CATALOG_FEATURES.equals(str)) {
            return buildCatalogFeatures();
        }
        if (JdkProperty.ImplPropMap.CDATACHUNKSIZE.is(str)) {
            return Integer.valueOf(this._cdataChunkSize);
        }
        XMLSecurityManager xMLSecurityManager = this._xmlSecurityManager;
        String limitAsString = xMLSecurityManager != null ? xMLSecurityManager.getLimitAsString(str) : null;
        if (limitAsString != null) {
            return limitAsString;
        }
        XMLSecurityPropertyManager xMLSecurityPropertyManager = this._xmlSecurityPropertyMgr;
        String value = xMLSecurityPropertyManager != null ? xMLSecurityPropertyManager.getValue(str) : null;
        if (value != null) {
            return value;
        }
        bke.a(new ErrorMsg(ErrorMsg.JAXP_INVALID_ATTR_ERR, str));
        return null;
    }

    @Override // javax.xml.transform.TransformerFactory
    public ErrorListener getErrorListener() {
        return this._errorListener;
    }

    public Map<String, Class<?>> getExternalExtensionsMap() {
        return this._xsltcExtensionFunctions;
    }

    @Override // javax.xml.transform.TransformerFactory
    public boolean getFeature(String str) {
        String[] strArr = {"http://javax.xml.transform.dom.DOMSource/feature", DOMResult.FEATURE, SAXSource.FEATURE, "http://javax.xml.transform.sax.SAXResult/feature", StAXSource.FEATURE, StAXResult.FEATURE, StreamSource.FEATURE, StreamResult.FEATURE, SAXTransformerFactory.FEATURE, SAXTransformerFactory.FEATURE_XMLFILTER, JdkConstants.ORACLE_FEATURE_SERVICE_MECHANISM};
        if (str == null) {
            throw new NullPointerException(new ErrorMsg(ErrorMsg.JAXP_GET_FEATURE_NULL_NAME).toString());
        }
        for (int i = 0; i < 11; i++) {
            if (str.equals(strArr[i])) {
                return true;
            }
        }
        if (str.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            return !this._isNotSecureProcessing;
        }
        int index = this._xmlFeatures.getIndex(str);
        if (index > -1) {
            return this._xmlFeatures.getFeature(index);
        }
        return false;
    }

    public JdkXmlFeatures getJdkXmlFeatures() {
        return this._xmlFeatures;
    }

    public String getPackageName() {
        return this._packageName;
    }

    @Override // javax.xml.transform.TransformerFactory
    public URIResolver getURIResolver() {
        return this._uriResolver;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SourceLoader
    public InputSource loadSource(String str, String str2, XSLTC xsltc) {
        try {
            URIResolver uRIResolver = this._uriResolver;
            Source sourceResolve = uRIResolver != null ? uRIResolver.resolve(str, str2) : null;
            if (sourceResolve == null && this._catalogFiles != null && this._xmlFeatures.getFeature(JdkXmlFeatures.XmlFeature.USE_CATALOG)) {
                if (this._catalogUriResolver == null) {
                    this._catalogUriResolver = CatalogManager.catalogResolver(this._catalogFeatures, new URI[0]);
                }
                sourceResolve = this._catalogUriResolver.resolve(str, str2);
            }
            if (sourceResolve != null) {
                return Util.getInputSource(xsltc, sourceResolve);
            }
        } catch (TransformerException e) {
            xsltc.getParser().reportError(2, new ErrorMsg(ErrorMsg.INVALID_URI_ERR, str + "\n" + e.getMessage(), this));
        } catch (CatalogException e2) {
            xsltc.getParser().reportError(2, new ErrorMsg(ErrorMsg.CATALOG_EXCEPTION, str + "\n" + e2.getMessage(), this));
        }
        return null;
    }

    @Override // javax.xml.transform.TransformerFactory
    public Templates newTemplates(Source source) throws TransformerConfigurationException {
        String parent;
        int i;
        PIParamWrapper pIParamWrapper;
        if (this._useClasspath) {
            String transletBaseName = getTransletBaseName(source);
            if (this._packageName != null) {
                transletBaseName = this._packageName + Constants.ATTRVAL_THIS + transletBaseName;
            }
            String str = transletBaseName;
            try {
                Class<?> clsFindProviderClass = ObjectFactory.findProviderClass(str, true);
                resetTransientAttributes();
                TemplatesImpl templatesImpl = new TemplatesImpl((Class<?>[]) new Class[]{clsFindProviderClass}, str, (Properties) null, this._indentNumber, this);
                URIResolver uRIResolver = this._uriResolver;
                if (uRIResolver != null) {
                    templatesImpl.setURIResolver(uRIResolver);
                }
                return templatesImpl;
            } catch (ClassNotFoundException unused) {
                throw new TransformerConfigurationException(new ErrorMsg(ErrorMsg.CLASS_NOT_FOUND_ERR, str).toString());
            } catch (Exception e) {
                throw new TransformerConfigurationException(new ErrorMsg(new ErrorMsg(ErrorMsg.RUNTIME_ERROR_KEY) + e.getMessage()).toString());
            }
        }
        if (this._autoTranslet) {
            String transletBaseName2 = getTransletBaseName(source);
            if (this._packageName != null) {
                transletBaseName2 = this._packageName + Constants.ATTRVAL_THIS + transletBaseName2;
            }
            String str2 = transletBaseName2;
            byte[][] bytecodesFromJar = this._jarFileName != null ? getBytecodesFromJar(source, str2) : getBytecodesFromClasses(source, str2);
            if (bytecodesFromJar != null) {
                if (this._debug) {
                    if (this._jarFileName != null) {
                        System.err.println(new ErrorMsg(ErrorMsg.TRANSFORM_WITH_JAR_STR, str2, this._jarFileName));
                    } else {
                        System.err.println(new ErrorMsg(ErrorMsg.TRANSFORM_WITH_TRANSLET_STR, str2));
                    }
                }
                resetTransientAttributes();
                TemplatesImpl templatesImpl2 = new TemplatesImpl(bytecodesFromJar, str2, (Properties) null, this._indentNumber, this);
                URIResolver uRIResolver2 = this._uriResolver;
                if (uRIResolver2 != null) {
                    templatesImpl2.setURIResolver(uRIResolver2);
                }
                return templatesImpl2;
            }
        }
        XSLTC xsltc = new XSLTC(this._xmlFeatures, this._hasUserErrListener);
        if (this._debug) {
            xsltc.setDebug(true);
        }
        if (this._enableInlining) {
            xsltc.setTemplateInlining(true);
        } else {
            xsltc.setTemplateInlining(false);
        }
        if (!this._isNotSecureProcessing) {
            xsltc.setSecureProcessing(true);
        }
        xsltc.setProperty(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, this._accessExternalStylesheet);
        xsltc.setProperty("http://javax.xml.XMLConstants/property/accessExternalDTD", this._accessExternalDTD);
        xsltc.setProperty("http://apache.org/xml/properties/security-manager", this._xmlSecurityManager);
        JdkProperty<ClassLoader> jdkProperty = this._extensionClassLoader;
        xsltc.setProperty(JdkConstants.JDK_EXT_CLASSLOADER, jdkProperty == null ? null : jdkProperty.getValue());
        buildCatalogFeatures();
        xsltc.setProperty(JdkXmlFeatures.CATALOG_FEATURES, this._catalogFeatures);
        xsltc.init();
        if (!this._isNotSecureProcessing) {
            this._xsltcExtensionFunctions = xsltc.getExternalExtensionFunctions();
        }
        if (this._uriResolver != null || (this._catalogFiles != null && this._xmlFeatures.getFeature(JdkXmlFeatures.XmlFeature.USE_CATALOG))) {
            xsltc.setSourceLoader(this);
        }
        Map<Source, PIParamWrapper> map = this._piParams;
        if (map != null && map.get(source) != null && (pIParamWrapper = this._piParams.get(source)) != null) {
            xsltc.setPIParameters(pIParamWrapper._media, pIParamWrapper._title, pIParamWrapper._charset);
        }
        if (this._generateTranslet || this._autoTranslet) {
            xsltc.setClassName(getTransletBaseName(source));
            String str3 = this._destinationDirectory;
            if (str3 != null) {
                xsltc.setDestDirectory(str3);
            } else {
                String stylesheetFileName = getStylesheetFileName(source);
                if (stylesheetFileName != null && (parent = new File(stylesheetFileName).getParent()) != null) {
                    xsltc.setDestDirectory(parent);
                }
            }
            String str4 = this._packageName;
            if (str4 != null) {
                xsltc.setPackageName(str4);
            }
            String str5 = this._jarFileName;
            if (str5 != null) {
                xsltc.setJarFileName(str5);
                i = 5;
            } else {
                i = 4;
            }
        } else {
            i = 2;
        }
        byte[][] bArrCompile = xsltc.compile(null, Util.getInputSource(xsltc, source), i);
        String className = xsltc.getClassName();
        if ((this._generateTranslet || this._autoTranslet) && bArrCompile != null && this._jarFileName != null) {
            try {
                xsltc.outputToJar();
            } catch (IOException unused2) {
            }
        }
        resetTransientAttributes();
        if (this._errorListener != this) {
            try {
                passWarningsToListener(xsltc.getWarnings());
            } catch (TransformerException e2) {
                throw new TransformerConfigurationException(e2);
            }
        } else {
            xsltc.printWarnings();
        }
        if (bArrCompile != null) {
            TemplatesImpl templatesImpl3 = new TemplatesImpl(bArrCompile, className, xsltc.getOutputProperties(), this._indentNumber, this);
            URIResolver uRIResolver3 = this._uriResolver;
            if (uRIResolver3 != null) {
                templatesImpl3.setURIResolver(uRIResolver3);
            }
            return templatesImpl3;
        }
        List<ErrorMsg> errors = xsltc.getErrors();
        ErrorMsg errorMsg = errors != null ? errors.get(errors.size() - 1) : new ErrorMsg(ErrorMsg.JAXP_COMPILE_ERR);
        Throwable cause = errorMsg.getCause();
        TransformerConfigurationException transformerConfigurationException = cause != null ? new TransformerConfigurationException(cause.getMessage(), cause) : new TransformerConfigurationException(errorMsg.toString());
        if (this._errorListener == null) {
            xsltc.printErrors();
            throw transformerConfigurationException;
        }
        passErrorsToListener(xsltc.getErrors());
        try {
            this._errorListener.fatalError(transformerConfigurationException);
            throw transformerConfigurationException;
        } catch (TransformerException unused3) {
            throw transformerConfigurationException;
        }
    }

    @Override // javax.xml.transform.sax.SAXTransformerFactory
    public TemplatesHandler newTemplatesHandler() throws TransformerConfigurationException {
        buildCatalogFeatures();
        TemplatesHandlerImpl templatesHandlerImpl = new TemplatesHandlerImpl(this._indentNumber, this, this._hasUserErrListener);
        URIResolver uRIResolver = this._uriResolver;
        if (uRIResolver != null) {
            templatesHandlerImpl.setURIResolver(uRIResolver);
        }
        return templatesHandlerImpl;
    }

    @Override // javax.xml.transform.TransformerFactory
    public Transformer newTransformer() throws TransformerConfigurationException {
        buildCatalogFeatures();
        TransformerImpl transformerImpl = new TransformerImpl(new Properties(), this._indentNumber, this);
        URIResolver uRIResolver = this._uriResolver;
        if (uRIResolver != null) {
            transformerImpl.setURIResolver(uRIResolver);
        }
        if (!this._isNotSecureProcessing) {
            transformerImpl.setSecureProcessing(true);
        }
        return transformerImpl;
    }

    @Override // javax.xml.transform.sax.SAXTransformerFactory
    public TransformerHandler newTransformerHandler() throws TransformerConfigurationException {
        Transformer transformerNewTransformer = newTransformer();
        URIResolver uRIResolver = this._uriResolver;
        if (uRIResolver != null) {
            transformerNewTransformer.setURIResolver(uRIResolver);
        }
        return new TransformerHandlerImpl((TransformerImpl) transformerNewTransformer);
    }

    @Override // javax.xml.transform.sax.SAXTransformerFactory
    public XMLFilter newXMLFilter(Templates templates) throws TransformerConfigurationException {
        try {
            return new TrAXFilter(templates);
        } catch (TransformerConfigurationException e) {
            ErrorListener errorListener = this._errorListener;
            if (errorListener == null) {
                throw e;
            }
            try {
                errorListener.fatalError(e);
                return null;
            } catch (TransformerException e2) {
                throw new TransformerConfigurationException(e2);
            }
        }
    }

    public boolean overrideDefaultParser() {
        return this._overrideDefaultParser;
    }

    @Override // javax.xml.transform.TransformerFactory
    public void setAttribute(String str, Object obj) throws IllegalArgumentException {
        if (str.equals(TRANSLET_NAME) && (obj instanceof String)) {
            this._transletName = (String) obj;
            return;
        }
        if (str.equals(DESTINATION_DIRECTORY) && (obj instanceof String)) {
            this._destinationDirectory = (String) obj;
            return;
        }
        if (str.equals(PACKAGE_NAME) && (obj instanceof String)) {
            this._packageName = (String) obj;
            return;
        }
        if (str.equals(JAR_NAME) && (obj instanceof String)) {
            this._jarFileName = (String) obj;
            return;
        }
        if (str.equals(GENERATE_TRANSLET)) {
            if (obj instanceof Boolean) {
                this._generateTranslet = ((Boolean) obj).booleanValue();
                return;
            } else if (obj instanceof String) {
                this._generateTranslet = ((String) obj).equalsIgnoreCase("true");
                return;
            }
        } else if (str.equals(AUTO_TRANSLET)) {
            if (obj instanceof Boolean) {
                this._autoTranslet = ((Boolean) obj).booleanValue();
                return;
            } else if (obj instanceof String) {
                this._autoTranslet = ((String) obj).equalsIgnoreCase("true");
                return;
            }
        } else if (str.equals(USE_CLASSPATH)) {
            if (obj instanceof Boolean) {
                this._useClasspath = ((Boolean) obj).booleanValue();
                return;
            } else if (obj instanceof String) {
                this._useClasspath = ((String) obj).equalsIgnoreCase("true");
                return;
            }
        } else if (str.equals(DEBUG)) {
            if (obj instanceof Boolean) {
                this._debug = ((Boolean) obj).booleanValue();
                return;
            } else if (obj instanceof String) {
                this._debug = ((String) obj).equalsIgnoreCase("true");
                return;
            }
        } else if (str.equals(ENABLE_INLINING)) {
            if (obj instanceof Boolean) {
                this._enableInlining = ((Boolean) obj).booleanValue();
                return;
            } else if (obj instanceof String) {
                this._enableInlining = ((String) obj).equalsIgnoreCase("true");
                return;
            }
        } else if (str.equals(INDENT_NUMBER)) {
            if (obj instanceof String) {
                try {
                    this._indentNumber = Integer.parseInt((String) obj);
                    return;
                } catch (NumberFormatException unused) {
                }
            } else if (obj instanceof Integer) {
                this._indentNumber = ((Integer) obj).intValue();
                return;
            }
        } else {
            if (JdkProperty.ImplPropMap.EXTCLSLOADER.is(str)) {
                if (obj instanceof ClassLoader) {
                    this._extensionClassLoader.setValue(str, (ClassLoader) obj, JdkProperty.State.APIPROPERTY);
                    return;
                } else {
                    bke.a(new ErrorMsg(ErrorMsg.JAXP_INVALID_ATTR_VALUE_ERR, "Extension Functions ClassLoader"));
                    return;
                }
            }
            if (JdkXmlUtils.CATALOG_FILES.equals(str)) {
                this._catalogFiles = (String) obj;
                this.cfBuilder = CatalogFeatures.builder().with(CatalogFeatures.Feature.FILES, this._catalogFiles);
                return;
            }
            if (JdkXmlUtils.CATALOG_DEFER.equals(str)) {
                this._catalogDefer = (String) obj;
                this.cfBuilder = CatalogFeatures.builder().with(CatalogFeatures.Feature.DEFER, this._catalogDefer);
                return;
            } else if (JdkXmlUtils.CATALOG_PREFER.equals(str)) {
                this._catalogPrefer = (String) obj;
                this.cfBuilder = CatalogFeatures.builder().with(CatalogFeatures.Feature.PREFER, this._catalogPrefer);
                return;
            } else if (JdkXmlUtils.CATALOG_RESOLVE.equals(str)) {
                this._catalogResolve = (String) obj;
                this.cfBuilder = CatalogFeatures.builder().with(CatalogFeatures.Feature.RESOLVE, this._catalogResolve);
                return;
            } else if (JdkProperty.ImplPropMap.CDATACHUNKSIZE.is(str)) {
                this._cdataChunkSize = JdkXmlUtils.getValue(obj, this._cdataChunkSize);
                return;
            }
        }
        XMLSecurityManager xMLSecurityManager = this._xmlSecurityManager;
        if (xMLSecurityManager == null || !xMLSecurityManager.setLimit(str, JdkProperty.State.APIPROPERTY, obj)) {
            XMLSecurityPropertyManager xMLSecurityPropertyManager = this._xmlSecurityPropertyMgr;
            if (xMLSecurityPropertyManager == null || !xMLSecurityPropertyManager.setValue(str, FeaturePropertyBase.State.APIPROPERTY, obj)) {
                bke.a(new ErrorMsg(ErrorMsg.JAXP_INVALID_ATTR_ERR, str));
            } else {
                this._accessExternalDTD = this._xmlSecurityPropertyMgr.getValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_DTD);
                this._accessExternalStylesheet = this._xmlSecurityPropertyMgr.getValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_STYLESHEET);
            }
        }
    }

    @Override // javax.xml.transform.TransformerFactory
    public void setErrorListener(ErrorListener errorListener) throws IllegalArgumentException {
        if (errorListener == null) {
            bke.a(new ErrorMsg(ErrorMsg.ERROR_LISTENER_NULL_ERR, "TransformerFactory"));
        } else {
            this._hasUserErrListener = true;
            this._errorListener = errorListener;
        }
    }

    @Override // javax.xml.transform.TransformerFactory
    public void setFeature(String str, boolean z) throws TransformerConfigurationException {
        JdkXmlFeatures jdkXmlFeatures;
        if (str == null) {
            throw new NullPointerException(new ErrorMsg(ErrorMsg.JAXP_SET_FEATURE_NULL_NAME).toString());
        }
        if (!str.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            if (str.equals(JdkConstants.ORACLE_FEATURE_SERVICE_MECHANISM) && this._isSecureMode) {
                return;
            }
            JdkXmlFeatures jdkXmlFeatures2 = this._xmlFeatures;
            if (jdkXmlFeatures2 == null || !jdkXmlFeatures2.setFeature(str, JdkProperty.State.APIPROPERTY, Boolean.valueOf(z))) {
                throw new TransformerConfigurationException(new ErrorMsg(ErrorMsg.JAXP_UNSUPPORTED_FEATURE, str).toString());
            }
            if (JdkProperty.ImplPropMap.OVERRIDEPARSER.is(str)) {
                this._overrideDefaultParser = this._xmlFeatures.getFeature(JdkXmlFeatures.XmlFeature.JDK_OVERRIDE_PARSER);
                return;
            }
            return;
        }
        if (this._isSecureMode && !z) {
            throw new TransformerConfigurationException(new ErrorMsg(ErrorMsg.JAXP_SECUREPROCESSING_FEATURE).toString());
        }
        this._isNotSecureProcessing = !z;
        this._xmlSecurityManager.setSecureProcessing(z);
        if (z) {
            XMLSecurityPropertyManager xMLSecurityPropertyManager = this._xmlSecurityPropertyMgr;
            XMLSecurityPropertyManager.Property property = XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_DTD;
            FeaturePropertyBase.State state = FeaturePropertyBase.State.FSP;
            xMLSecurityPropertyManager.setValue(property, state, "");
            XMLSecurityPropertyManager xMLSecurityPropertyManager2 = this._xmlSecurityPropertyMgr;
            XMLSecurityPropertyManager.Property property2 = XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_STYLESHEET;
            xMLSecurityPropertyManager2.setValue(property2, state, "");
            this._accessExternalDTD = this._xmlSecurityPropertyMgr.getValue(property);
            this._accessExternalStylesheet = this._xmlSecurityPropertyMgr.getValue(property2);
        }
        if (!z || (jdkXmlFeatures = this._xmlFeatures) == null) {
            return;
        }
        jdkXmlFeatures.setFeature(JdkXmlFeatures.XmlFeature.ENABLE_EXTENSION_FUNCTION, JdkProperty.State.FSP, false);
    }

    @Override // javax.xml.transform.TransformerFactory
    public void setURIResolver(URIResolver uRIResolver) {
        this._uriResolver = uRIResolver;
    }

    @Override // javax.xml.transform.sax.SAXTransformerFactory
    public TransformerHandler newTransformerHandler(Source source) throws TransformerConfigurationException {
        Transformer transformerNewTransformer = newTransformer(source);
        URIResolver uRIResolver = this._uriResolver;
        if (uRIResolver != null) {
            transformerNewTransformer.setURIResolver(uRIResolver);
        }
        return new TransformerHandlerImpl((TransformerImpl) transformerNewTransformer);
    }

    @Override // javax.xml.transform.sax.SAXTransformerFactory
    public TransformerHandler newTransformerHandler(Templates templates) throws TransformerConfigurationException {
        return new TransformerHandlerImpl((TransformerImpl) templates.newTransformer());
    }

    @Override // javax.xml.transform.sax.SAXTransformerFactory
    public XMLFilter newXMLFilter(Source source) throws TransformerConfigurationException {
        Templates templatesNewTemplates = newTemplates(source);
        if (templatesNewTemplates == null) {
            return null;
        }
        return newXMLFilter(templatesNewTemplates);
    }

    @Override // javax.xml.transform.TransformerFactory
    public Transformer newTransformer(Source source) throws TransformerConfigurationException {
        Transformer transformerNewTransformer = newTemplates(source).newTransformer();
        URIResolver uRIResolver = this._uriResolver;
        if (uRIResolver != null) {
            transformerNewTransformer.setURIResolver(uRIResolver);
        }
        return transformerNewTransformer;
    }
}
