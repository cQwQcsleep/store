package org.codehaus.stax2.validation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public abstract class XMLValidationSchemaFactory {
    public static final String INTERNAL_ID_SCHEMA_DTD = "dtd";
    public static final String INTERNAL_ID_SCHEMA_RELAXNG = "relaxng";
    public static final String INTERNAL_ID_SCHEMA_TREX = "trex";
    public static final String INTERNAL_ID_SCHEMA_W3C = "w3c";
    static final String JAXP_PROP_FILENAME = "jaxp.properties";
    public static final String P_ENABLE_CACHING = "org.codehaus2.stax2.validation.enableCaching";
    public static final String P_IS_NAMESPACE_AWARE = "org.codehaus2.stax2.validation.isNamespaceAware";
    public static final String SERVICE_DEFINITION_PATH = "META-INF/services/org.codehaus.stax2.validation.XMLValidationSchemaFactory.";
    public static final String SYSTEM_PROPERTY_FOR_IMPL = "org.codehaus.stax2.validation.XMLValidationSchemaFactory.";
    static final HashMap<String, String> sSchemaIds;
    protected final String mSchemaType;

    static {
        HashMap<String, String> map = new HashMap<>();
        sSchemaIds = map;
        map.put("http://www.w3.org/XML/1998/namespace", INTERNAL_ID_SCHEMA_DTD);
        map.put(XMLValidationSchema.SCHEMA_ID_RELAXNG, INTERNAL_ID_SCHEMA_RELAXNG);
        map.put(XMLValidationSchema.SCHEMA_ID_W3C_SCHEMA, INTERNAL_ID_SCHEMA_W3C);
        map.put(XMLValidationSchema.SCHEMA_ID_TREX, INTERNAL_ID_SCHEMA_TREX);
    }

    public XMLValidationSchemaFactory(String str) {
        this.mSchemaType = str;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.FactoryConfigurationError */
    private static XMLValidationSchemaFactory createNewInstance(ClassLoader classLoader, String str) throws FactoryConfigurationError {
        try {
            return (XMLValidationSchemaFactory) (classLoader == null ? Class.forName(str) : classLoader.loadClass(str)).newInstance();
        } catch (ClassNotFoundException e) {
            zm4.a("XMLValidationSchemaFactory implementation '", str, "' not found (missing jar in classpath?)", e);
            return null;
        } catch (Exception e2) {
            throw new FactoryConfigurationError("XMLValidationSchemaFactory implementation '" + str + "' could not be instantiated: " + e2, e2);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.FactoryConfigurationError */
    public static XMLValidationSchemaFactory newInstance(String str, ClassLoader classLoader) throws FactoryConfigurationError {
        String strTrim;
        String str2 = sSchemaIds.get(str);
        if (str2 == null) {
            throw new FactoryConfigurationError("Unrecognized schema type (id '" + str + "')");
        }
        String strConcat = SYSTEM_PROPERTY_FOR_IMPL.concat(str2);
        try {
            String property = System.getProperty(strConcat);
            if (property != null && property.length() > 0) {
                return createNewInstance(classLoader, property);
            }
            e = null;
        } catch (SecurityException e) {
            e = e;
        }
        try {
            File file = new File(new File(new File(System.getProperty("java.home")), "lib"), JAXP_PROP_FILENAME);
            if (file.exists()) {
                Properties properties = new Properties();
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        properties.load(fileInputStream);
                        try {
                            fileInputStream.close();
                        } catch (IOException unused) {
                        }
                        String property2 = properties.getProperty(strConcat);
                        if (property2 != null && property2.length() > 0) {
                            return createNewInstance(classLoader, property2);
                        }
                    } catch (Throwable th) {
                        try {
                            fileInputStream.close();
                        } catch (IOException unused2) {
                        }
                        throw th;
                    }
                } catch (IOException unused3) {
                }
            }
        } catch (SecurityException e2) {
            e = e2;
        }
        String strConcat2 = SERVICE_DEFINITION_PATH.concat(str2);
        try {
            Enumeration<URL> systemResources = classLoader == null ? ClassLoader.getSystemResources(strConcat2) : classLoader.getResources(strConcat2);
            if (systemResources != null) {
                while (systemResources.hasMoreElements()) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(systemResources.nextElement().openStream(), "ISO-8859-1"));
                    while (true) {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                strTrim = null;
                                break;
                            }
                            strTrim = line.trim();
                            if (strTrim.length() > 0 && strTrim.charAt(0) != '#') {
                                break;
                            }
                        } catch (Throwable th2) {
                            bufferedReader.close();
                            throw th2;
                        }
                    }
                    bufferedReader.close();
                    if (strTrim != null && strTrim.length() > 0) {
                        return createNewInstance(classLoader, strTrim);
                    }
                }
            }
        } catch (IOException unused4) {
        } catch (SecurityException e3) {
            e = e3;
        }
        String str3 = "No XMLValidationSchemaFactory implementation class specified or accessible (via system property '" + strConcat + "', or service definition under '" + strConcat2 + "')";
        if (e == null) {
            throw new FactoryConfigurationError(str3);
        }
        throw new FactoryConfigurationError(str3 + " (possibly caused by: " + e + ")", e);
    }

    public abstract XMLValidationSchema createSchema(File file) throws XMLStreamException;

    public XMLValidationSchema createSchema(InputStream inputStream) throws XMLStreamException {
        return createSchema(inputStream, null);
    }

    public abstract XMLValidationSchema createSchema(InputStream inputStream, String str, String str2, String str3) throws XMLStreamException;

    public abstract XMLValidationSchema createSchema(Reader reader, String str, String str2) throws XMLStreamException;

    public abstract XMLValidationSchema createSchema(URL url) throws XMLStreamException;

    public abstract Object getProperty(String str);

    public final String getSchemaType() {
        return this.mSchemaType;
    }

    public abstract boolean isPropertySupported(String str);

    public abstract boolean setProperty(String str, Object obj);

    public XMLValidationSchema createSchema(InputStream inputStream, String str) throws XMLStreamException {
        return createSchema(inputStream, str, null, null);
    }

    public XMLValidationSchema createSchema(Reader reader) throws XMLStreamException {
        return createSchema(reader, null, null);
    }

    public static XMLValidationSchemaFactory newInstance(String str) throws FactoryConfigurationError {
        return newInstance(str, Thread.currentThread().getContextClassLoader());
    }
}
