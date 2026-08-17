package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import javax.xml.XMLConstants;
import javax.xml.catalog.CatalogFeatures;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlFeatures;
import jdk.xml.internal.SecuritySupport;
import jdk.xml.internal.XMLSecurityManager;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XSLTC {
    public static final int BYTEARRAY_AND_FILE_OUTPUT = 4;
    public static final int BYTEARRAY_AND_JAR_OUTPUT = 5;
    public static final int BYTEARRAY_OUTPUT = 2;
    public static final int CLASSLOADER_OUTPUT = 3;
    public static final int JAR_OUTPUT = 1;
    private Map<String, Integer> _attributes;
    private List<JavaClass> _bcelClasses;
    CatalogFeatures _catalogFeatures;
    int _cdataChunkSize;
    private List<ByteArrayOutputStream> _classes;
    private Map<String, Integer> _elements;
    private ClassLoader _extensionClassLoader;
    private final Map<String, Class<?>> _externalExtensionFunctions;
    private List<String> _namesIndex;
    private List<String> _namespaceIndex;
    private Map<String, Integer> _namespacePrefixes;
    private Map<String, Integer> _namespaces;
    private int _nextGType;
    private int _nextNSType;
    private int[] _numberFieldIndexes;
    private boolean _overrideDefaultParser;
    private Parser _parser;
    private Stylesheet _stylesheet;
    private final JdkXmlFeatures _xmlFeatures;
    private XMLSecurityManager _xmlSecurityManager;
    private List<StringBuilder> m_characterData;
    private XMLReader _reader = null;
    private SourceLoader _loader = null;
    private int _modeSerial = 1;
    private int _stylesheetSerial = 1;
    private int _stepPatternSerial = 1;
    private int _helperClassSerial = 0;
    private int _attributeSetSerial = 0;
    private boolean _debug = false;
    private String _jarFileName = null;
    private String _className = null;
    private String _packageName = "die.verwandlung";
    private File _destDir = null;
    private int _outputType = 2;
    private boolean _callsNodeset = false;
    private boolean _multiDocument = false;
    private boolean _hasIdCall = false;
    private boolean _templateInlining = false;
    private boolean _isSecureProcessing = false;
    private String _accessExternalStylesheet = "all";
    private String _accessExternalDTD = "all";

    public XSLTC(JdkXmlFeatures jdkXmlFeatures, boolean z) {
        boolean feature = jdkXmlFeatures.getFeature(JdkXmlFeatures.XmlFeature.JDK_OVERRIDE_PARSER);
        this._overrideDefaultParser = feature;
        this._parser = new Parser(this, feature, z);
        this._xmlFeatures = jdkXmlFeatures;
        this._extensionClassLoader = null;
        this._externalExtensionFunctions = new HashMap();
    }

    private String classFileName(String str) {
        return str.replace('.', File.separatorChar) + JavaClass.EXTENSION;
    }

    private String entryName(File file) throws IOException {
        return file.getName().replace(File.separatorChar, '/');
    }

    private File getOutputFile(String str) {
        return this._destDir != null ? new File(this._destDir, classFileName(str)) : new File(classFileName(str));
    }

    private void reset() {
        this._nextGType = 14;
        this._elements = new HashMap();
        this._attributes = new HashMap();
        HashMap map = new HashMap();
        this._namespaces = map;
        map.put("", Integer.valueOf(this._nextNSType));
        this._namesIndex = new ArrayList(128);
        this._namespaceIndex = new ArrayList(32);
        this._namespacePrefixes = new HashMap();
        this._stylesheet = null;
        this._parser.init();
        this._modeSerial = 1;
        this._stylesheetSerial = 1;
        this._stepPatternSerial = 1;
        this._helperClassSerial = 0;
        this._attributeSetSerial = 0;
        this._multiDocument = false;
        this._hasIdCall = false;
        this._numberFieldIndexes = new int[]{-1, -1, -1};
        this._externalExtensionFunctions.clear();
    }

    private void setExternalExtensionFunctions(String str, Class<?> cls) {
        if (!this._isSecureProcessing || cls == null || this._externalExtensionFunctions.containsKey(str)) {
            return;
        }
        this._externalExtensionFunctions.put(str, cls);
    }

    public int addCharacterData(String str) {
        StringBuilder sb;
        List<StringBuilder> list = this.m_characterData;
        if (list == null) {
            this.m_characterData = new ArrayList();
            sb = new StringBuilder();
            this.m_characterData.add(sb);
        } else {
            sb = list.get(list.size() - 1);
        }
        if (str.length() + sb.length() > 21845) {
            sb = new StringBuilder();
            this.m_characterData.add(sb);
        }
        int length = sb.length();
        sb.append(str);
        return length;
    }

    public boolean callsNodeset() {
        return this._callsNodeset;
    }

    public boolean compile(InputSource inputSource, String str) {
        Stylesheet stylesheet;
        String strBaseName;
        try {
            try {
                reset();
                String systemId = inputSource != null ? inputSource.getSystemId() : null;
                if (this._className == null) {
                    if (str != null) {
                        setClassName(str);
                    } else if (systemId != null && !systemId.isEmpty() && (strBaseName = Util.baseName(systemId)) != null && !strBaseName.isEmpty()) {
                        setClassName(strBaseName);
                    }
                    String str2 = this._className;
                    if (str2 == null || str2.length() == 0) {
                        setClassName("GregorSamsa");
                    }
                }
                XMLReader xMLReader = this._reader;
                Parser parser = this._parser;
                SyntaxTreeNode syntaxTreeNode = xMLReader == null ? parser.parse(inputSource) : parser.parse(xMLReader, inputSource);
                if (!this._parser.errorsFound() && syntaxTreeNode != null) {
                    Stylesheet stylesheetMakeStylesheet = this._parser.makeStylesheet(syntaxTreeNode);
                    this._stylesheet = stylesheetMakeStylesheet;
                    stylesheetMakeStylesheet.setSourceLoader(this._loader);
                    this._stylesheet.setSystemId(systemId);
                    this._stylesheet.setParentStylesheet(null);
                    this._stylesheet.setTemplateInlining(this._templateInlining);
                    this._parser.setCurrentStylesheet(this._stylesheet);
                    this._parser.createAST(this._stylesheet);
                }
                if (!this._parser.errorsFound() && (stylesheet = this._stylesheet) != null) {
                    stylesheet.setCallsNodeset(this._callsNodeset);
                    this._stylesheet.setMultiDocument(this._multiDocument);
                    this._stylesheet.setHasIdCall(this._hasIdCall);
                    synchronized (XSLTC.class) {
                        this._stylesheet.translate();
                    }
                }
            } catch (Error e) {
                if (this._debug) {
                    e.printStackTrace();
                }
                this._parser.reportError(2, new ErrorMsg(ErrorMsg.JAXP_COMPILE_ERR, (Throwable) e));
            } catch (Exception e2) {
                if (this._debug) {
                    e2.printStackTrace();
                }
                boolean zEquals = ErrorMsg.XPATH_LIMIT.equals(e2.getMessage());
                Parser parser2 = this._parser;
                if (zEquals) {
                    boolean z = !parser2.errorsFound();
                    this._reader = null;
                    return z;
                }
                parser2.reportError(2, new ErrorMsg(ErrorMsg.JAXP_COMPILE_ERR, (Throwable) e2));
            }
            this._reader = null;
            return !this._parser.errorsFound();
        } catch (Throwable th) {
            this._reader = null;
            throw th;
        }
    }

    public boolean debug() {
        return this._debug;
    }

    public void dumpClass(JavaClass javaClass) {
        String parent;
        if (this._outputType == 4 && (parent = getOutputFile(javaClass.getClassName()).getParent()) != null) {
            File file = new File(parent);
            if (!SecuritySupport.doesFileExist(file)) {
                file.mkdirs();
            }
        }
        try {
            int i = this._outputType;
            if (i == 1) {
                this._bcelClasses.add(javaClass);
                return;
            }
            if (i == 2 || i == 3 || i == 4 || i == 5) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2048);
                javaClass.dump(byteArrayOutputStream);
                this._classes.add(byteArrayOutputStream);
                int i2 = this._outputType;
                if (i2 == 4) {
                    javaClass.dump(getOutputFile(javaClass.getClassName()));
                } else if (i2 == 5) {
                    this._bcelClasses.add(javaClass);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[][] getBytecodes() {
        int size = this._classes.size();
        byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, size, 1);
        for (int i = 0; i < size; i++) {
            bArr[i] = this._classes.get(i).toByteArray();
        }
        return bArr;
    }

    public String getCharacterData(int i) {
        return this.m_characterData.get(i).toString();
    }

    public int getCharacterDataCount() {
        List<StringBuilder> list = this.m_characterData;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public String getClassName() {
        return this._className;
    }

    public List<ErrorMsg> getErrors() {
        return this._parser.getErrors();
    }

    public Map<String, Class<?>> getExternalExtensionFunctions() {
        return Collections.unmodifiableMap(this._externalExtensionFunctions);
    }

    public boolean getFeature(JdkXmlFeatures.XmlFeature xmlFeature) {
        return this._xmlFeatures.getFeature(xmlFeature);
    }

    public String getHelperClassName() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClassName());
        sb.append('$');
        int i = this._helperClassSerial;
        this._helperClassSerial = i + 1;
        sb.append(i);
        return sb.toString();
    }

    public String getJarFileName() {
        return this._jarFileName;
    }

    public List<String> getNamesIndex() {
        return this._namesIndex;
    }

    public List<String> getNamespaceIndex() {
        return this._namespaceIndex;
    }

    public int[] getNumberFieldIndexes() {
        return this._numberFieldIndexes;
    }

    public Properties getOutputProperties() {
        return this._parser.getOutputProperties();
    }

    public Parser getParser() {
        return this._parser;
    }

    public Object getProperty(String str) {
        if (str.equals(XMLConstants.ACCESS_EXTERNAL_STYLESHEET)) {
            return this._accessExternalStylesheet;
        }
        if (str.equals("http://javax.xml.XMLConstants/property/accessExternalDTD")) {
            return this._accessExternalDTD;
        }
        if (str.equals("http://apache.org/xml/properties/security-manager")) {
            return this._xmlSecurityManager;
        }
        if (str.equals(JdkConstants.JDK_EXT_CLASSLOADER)) {
            return this._extensionClassLoader;
        }
        if (JdkXmlFeatures.CATALOG_FEATURES.equals(str)) {
            return this._catalogFeatures;
        }
        if (JdkConstants.CDATA_CHUNK_SIZE.equals(str)) {
            return Integer.valueOf(this._cdataChunkSize);
        }
        return null;
    }

    public Stylesheet getStylesheet() {
        return this._stylesheet;
    }

    public boolean getTemplateInlining() {
        return this._templateInlining;
    }

    public List<ErrorMsg> getWarnings() {
        return this._parser.getWarnings();
    }

    public XMLReader getXMLReader() {
        return this._reader;
    }

    public boolean hasIdCall() {
        return this._hasIdCall;
    }

    public void init() {
        reset();
        this._reader = null;
        this._classes = new ArrayList();
        this._bcelClasses = new ArrayList();
    }

    public boolean isMultiDocument() {
        return this._multiDocument;
    }

    public boolean isSecureProcessing() {
        return this._isSecureProcessing;
    }

    public Class<?> loadExternalFunction(String str) throws ClassNotFoundException {
        Class<?> cls;
        if (this._externalExtensionFunctions.containsKey(str)) {
            cls = this._externalExtensionFunctions.get(str);
        } else {
            ClassLoader classLoader = this._extensionClassLoader;
            if (classLoader != null) {
                Class<?> cls2 = Class.forName(str, true, classLoader);
                setExternalExtensionFunctions(str, cls2);
                cls = cls2;
            } else {
                cls = null;
            }
        }
        if (cls != null) {
            return cls;
        }
        throw new ClassNotFoundException(str);
    }

    public int nextAttributeSetSerial() {
        int i = this._attributeSetSerial;
        this._attributeSetSerial = i + 1;
        return i;
    }

    public int nextHelperClassSerial() {
        int i = this._helperClassSerial;
        this._helperClassSerial = i + 1;
        return i;
    }

    public int nextModeSerial() {
        int i = this._modeSerial;
        this._modeSerial = i + 1;
        return i;
    }

    public int nextStepPatternSerial() {
        int i = this._stepPatternSerial;
        this._stepPatternSerial = i + 1;
        return i;
    }

    public int nextStylesheetSerial() {
        int i = this._stylesheetSerial;
        this._stylesheetSerial = i + 1;
        return i;
    }

    public void outputToJar() throws IOException {
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.2");
        Map<String, Attributes> entries = manifest.getEntries();
        String string = new Date().toString();
        Attributes.Name name = new Attributes.Name("Date");
        JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(new File(this._destDir, this._jarFileName)), manifest);
        for (JavaClass javaClass : this._bcelClasses) {
            String strReplace = javaClass.getClassName().replace('.', '/');
            Attributes attributes = new Attributes();
            attributes.put(name, string);
            entries.put(strReplace + JavaClass.EXTENSION, attributes);
            jarOutputStream.putNextEntry(new JarEntry(strReplace + JavaClass.EXTENSION));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2048);
            javaClass.dump(byteArrayOutputStream);
            byteArrayOutputStream.writeTo(jarOutputStream);
        }
        jarOutputStream.close();
    }

    public void printErrors() {
        this._parser.printErrors();
    }

    public void printWarnings() {
        this._parser.printWarnings();
    }

    public int registerAttribute(QName qName) {
        Integer numValueOf = this._attributes.get(qName.toString());
        if (numValueOf == null) {
            int i = this._nextGType;
            this._nextGType = i + 1;
            numValueOf = Integer.valueOf(i);
            this._attributes.put(qName.toString(), numValueOf);
            String namespace = qName.getNamespace();
            String str = "@" + qName.getLocalPart();
            if (namespace == null || namespace.equals("")) {
                this._namesIndex.add(str);
            } else {
                this._namesIndex.add(namespace + ":" + str);
            }
            if (qName.getLocalPart().equals("*")) {
                registerNamespace(qName.getNamespace());
            }
        }
        return numValueOf.intValue();
    }

    public int registerElement(QName qName) {
        Integer num = this._elements.get(qName.toString());
        if (num == null) {
            Map<String, Integer> map = this._elements;
            String string = qName.toString();
            int i = this._nextGType;
            this._nextGType = i + 1;
            Integer numValueOf = Integer.valueOf(i);
            map.put(string, numValueOf);
            this._namesIndex.add(qName.toString());
            num = numValueOf;
        }
        if (qName.getLocalPart().equals("*")) {
            registerNamespace(qName.getNamespace());
        }
        return num.intValue();
    }

    public int registerNamespace(String str) {
        Integer numValueOf = this._namespaces.get(str);
        if (numValueOf == null) {
            int i = this._nextNSType;
            this._nextNSType = i + 1;
            numValueOf = Integer.valueOf(i);
            this._namespaces.put(str, numValueOf);
            this._namespaceIndex.add(str);
        }
        return numValueOf.intValue();
    }

    public int registerNamespacePrefix(QName qName) {
        Integer numValueOf = this._namespacePrefixes.get(qName.toString());
        if (numValueOf == null) {
            int i = this._nextGType;
            this._nextGType = i + 1;
            numValueOf = Integer.valueOf(i);
            this._namespacePrefixes.put(qName.toString(), numValueOf);
            String namespace = qName.getNamespace();
            if (namespace == null || namespace.equals("")) {
                this._namesIndex.add("?" + qName.getLocalPart());
            } else {
                this._namesIndex.add("?");
            }
        }
        return numValueOf.intValue();
    }

    public void setCallsNodeset(boolean z) {
        if (z) {
            setMultiDocument(z);
        }
        this._callsNodeset = z;
    }

    public void setClassName(String str) {
        String javaName = Util.toJavaName(Util.noExtName(Util.baseName(str)));
        if (this._packageName == null) {
            this._className = javaName;
            return;
        }
        this._className = this._packageName + '.' + javaName;
    }

    public void setDebug(boolean z) {
        this._debug = z;
    }

    public boolean setDestDirectory(String str) {
        File file = new File(str);
        if (SecuritySupport.doesFileExist(file) || file.mkdirs()) {
            this._destDir = file;
            return true;
        }
        this._destDir = null;
        return false;
    }

    public void setHasIdCall(boolean z) {
        this._hasIdCall = z;
    }

    public void setJarFileName(String str) {
        if (str.endsWith(".jar")) {
            this._jarFileName = str;
        } else {
            this._jarFileName = str.concat(".jar");
        }
        this._outputType = 1;
    }

    public void setMultiDocument(boolean z) {
        this._multiDocument = z;
    }

    public void setOutputType(int i) {
        this._outputType = i;
    }

    public void setPIParameters(String str, String str2, String str3) {
        this._parser.setPIParameters(str, str2, str3);
    }

    public void setPackageName(String str) {
        Objects.requireNonNull(str);
        this._packageName = str;
        String str2 = this._className;
        if (str2 != null) {
            setClassName(str2);
        }
    }

    public void setProperty(String str, Object obj) {
        if (str.equals(XMLConstants.ACCESS_EXTERNAL_STYLESHEET)) {
            this._accessExternalStylesheet = (String) obj;
            return;
        }
        if (str.equals("http://javax.xml.XMLConstants/property/accessExternalDTD")) {
            this._accessExternalDTD = (String) obj;
            return;
        }
        if (str.equals("http://apache.org/xml/properties/security-manager")) {
            this._xmlSecurityManager = (XMLSecurityManager) obj;
            return;
        }
        if (str.equals(JdkConstants.JDK_EXT_CLASSLOADER)) {
            this._extensionClassLoader = (ClassLoader) obj;
            this._externalExtensionFunctions.clear();
        } else if (JdkXmlFeatures.CATALOG_FEATURES.equals(str)) {
            this._catalogFeatures = (CatalogFeatures) obj;
        } else if (JdkConstants.CDATA_CHUNK_SIZE.equals(str)) {
            this._cdataChunkSize = Integer.parseInt((String) obj);
        }
    }

    public void setSecureProcessing(boolean z) {
        this._isSecureProcessing = z;
    }

    public void setSourceLoader(SourceLoader sourceLoader) {
        this._loader = sourceLoader;
    }

    public void setStylesheet(Stylesheet stylesheet) {
        if (this._stylesheet == null) {
            this._stylesheet = stylesheet;
        }
    }

    public void setTemplateInlining(boolean z) {
        this._templateInlining = z;
    }

    public void setXMLReader(XMLReader xMLReader) {
        this._reader = xMLReader;
    }

    public boolean compile(URL url, String str) {
        try {
            InputSource inputSource = new InputSource(url.openStream());
            inputSource.setSystemId(url.toString());
            return compile(inputSource, str);
        } catch (IOException e) {
            this._parser.reportError(2, new ErrorMsg(ErrorMsg.JAXP_COMPILE_ERR, (Throwable) e));
            return false;
        }
    }

    public boolean compile(InputStream inputStream, String str) {
        InputSource inputSource = new InputSource(inputStream);
        inputSource.setSystemId(str);
        return compile(inputSource, str);
    }

    public boolean compile(URL url) {
        try {
            InputSource inputSource = new InputSource(url.openStream());
            inputSource.setSystemId(url.toString());
            return compile(inputSource, this._className);
        } catch (IOException e) {
            this._parser.reportError(2, new ErrorMsg(ErrorMsg.JAXP_COMPILE_ERR, (Throwable) e));
            return false;
        }
    }

    public boolean compile(List<URL> list) {
        int size = list.size();
        if (size == 0) {
            return true;
        }
        if (size == 1) {
            return compile(list.get(0));
        }
        for (URL url : list) {
            this._className = null;
            if (!compile(url)) {
                return false;
            }
        }
        return true;
    }

    public byte[][] compile(String str, InputSource inputSource, int i) {
        this._outputType = i;
        if (compile(inputSource, str)) {
            return getBytecodes();
        }
        return null;
    }

    public byte[][] compile(String str, InputSource inputSource) {
        return compile(str, inputSource, 2);
    }
}
