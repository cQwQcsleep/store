package javax.xml.transform;

import java.io.File;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.function.Supplier;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class FactoryFinder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String DEFAULT_PACKAGE = "com.sun.org.apache.xalan.internal.";
    private static boolean debug = false;
    private static final Properties cacheProps = new Properties();
    static volatile boolean firstTime = true;

    static {
        boolean z = true;
        try {
            String systemProperty = SecuritySupport.getSystemProperty("jaxp.debug");
            if (systemProperty == null || "false".equals(systemProperty)) {
                z = false;
            }
            debug = z;
        } catch (SecurityException unused) {
            debug = $assertionsDisabled;
        }
    }

    public static /* synthetic */ String a(File file) {
        return "Read properties file " + file;
    }

    public static /* synthetic */ String b(String str) {
        return "find factoryId =" + str;
    }

    public static /* synthetic */ String c(String str) {
        return "found system property, value=" + str;
    }

    public static /* synthetic */ String d(String str) {
        return "loaded from fallback value: " + str;
    }

    private static void dPrint(Supplier<String> supplier) {
        if (debug) {
            System.err.println("JAXP: " + supplier.get());
        }
    }

    public static /* synthetic */ String e(String str) {
        return "found in ${java.home}/conf/jaxp.properties, value=" + str;
    }

    public static /* synthetic */ String f(Class cls, ClassLoader classLoader) {
        return "created new instance of " + cls + " using ClassLoader: " + classLoader;
    }

    public static <T> T find(Class<T> cls, final String str) throws TransformerFactoryConfigurationError {
        final String name = cls.getName();
        dPrint(new Supplier() { // from class: javax.xml.transform.a
            @Override // java.util.function.Supplier
            public final Object get() {
                return FactoryFinder.b(name);
            }
        });
        try {
            final String systemProperty = SecuritySupport.getSystemProperty(name);
            if (systemProperty != null) {
                dPrint(new Supplier() { // from class: javax.xml.transform.b
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return FactoryFinder.c(systemProperty);
                    }
                });
                return (T) newInstance(cls, systemProperty, null, true);
            }
        } catch (SecurityException e) {
            if (debug) {
                e.printStackTrace();
            }
        }
        try {
            if (firstTime) {
                Properties properties = cacheProps;
                synchronized (properties) {
                    try {
                        if (firstTime) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(SecuritySupport.getSystemProperty("java.home"));
                            String str2 = File.separator;
                            sb.append(str2);
                            sb.append("conf");
                            sb.append(str2);
                            sb.append("jaxp.properties");
                            final File file = new File(sb.toString());
                            firstTime = $assertionsDisabled;
                            if (SecuritySupport.doesFileExist(file)) {
                                dPrint(new Supplier() { // from class: javax.xml.transform.c
                                    @Override // java.util.function.Supplier
                                    public final Object get() {
                                        return FactoryFinder.a(file);
                                    }
                                });
                                properties.load(SecuritySupport.getFileInputStream(file));
                            }
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            final String property = cacheProps.getProperty(name);
            if (property != null) {
                dPrint(new Supplier() { // from class: javax.xml.transform.d
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return FactoryFinder.e(property);
                    }
                });
                return (T) newInstance(cls, property, null, true);
            }
        } catch (Exception e2) {
            if (debug) {
                e2.printStackTrace();
            }
        }
        T t = (T) findServiceProvider(cls);
        if (t != null) {
            return t;
        }
        if (str != null) {
            dPrint(new Supplier() { // from class: javax.xml.transform.e
                @Override // java.util.function.Supplier
                public final Object get() {
                    return FactoryFinder.d(str);
                }
            });
            return (T) newInstance(cls, str, null, true);
        }
        throw new TransformerFactoryConfigurationError(null, "Provider for " + name + " cannot be found");
    }

    private static <T> T findServiceProvider(final Class<T> cls) throws TransformerFactoryConfigurationError {
        try {
            return (T) AccessController.doPrivileged(new PrivilegedAction<T>() { // from class: javax.xml.transform.FactoryFinder.1
                @Override // java.security.PrivilegedAction
                public T run() {
                    Iterator it2 = ServiceLoader.load(cls).iterator();
                    if (it2.hasNext()) {
                        return (T) it2.next();
                    }
                    return null;
                }
            });
        } catch (ServiceConfigurationError e) {
            RuntimeException runtimeException = new RuntimeException("Provider for " + cls + " cannot be created", e);
            throw new TransformerFactoryConfigurationError(runtimeException, runtimeException.getMessage());
        }
    }

    private static Class<?> getProviderClass(String str, ClassLoader classLoader, boolean z, boolean z2) throws ClassNotFoundException {
        try {
            if (classLoader != null) {
                return Class.forName(str, $assertionsDisabled, classLoader);
            }
            if (z2) {
                return Class.forName(str, $assertionsDisabled, FactoryFinder.class.getClassLoader());
            }
            ClassLoader contextClassLoader = SecuritySupport.getContextClassLoader();
            if (contextClassLoader != null) {
                return Class.forName(str, $assertionsDisabled, contextClassLoader);
            }
            throw new ClassNotFoundException();
        } catch (ClassNotFoundException e) {
            if (z) {
                return Class.forName(str, $assertionsDisabled, FactoryFinder.class.getClassLoader());
            }
            throw e;
        }
    }

    public static <T> T newInstance(Class<T> cls, String str, final ClassLoader classLoader, boolean z) throws TransformerFactoryConfigurationError {
        boolean z2;
        if (System.getSecurityManager() == null || str == null || !str.startsWith(DEFAULT_PACKAGE)) {
            z2 = $assertionsDisabled;
        } else {
            z2 = true;
            classLoader = null;
        }
        try {
            final Class<?> providerClass = getProviderClass(str, classLoader, z, z2);
            if (cls.isAssignableFrom(providerClass)) {
                Object objNewInstance = providerClass.getConstructor(null).newInstance(null);
                dPrint(new Supplier() { // from class: javax.xml.transform.f
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return FactoryFinder.f(providerClass, classLoader);
                    }
                });
                return cls.cast(objNewInstance);
            }
            throw new ClassCastException(str + " cannot be cast to " + cls.getName());
        } catch (ClassNotFoundException e) {
            throw new TransformerFactoryConfigurationError(e, "Provider " + str + " not found");
        } catch (Exception e2) {
            throw new TransformerFactoryConfigurationError(e2, "Provider " + str + " could not be instantiated: " + e2);
        }
    }
}
