package javax.xml.stream;

import defpackage.zm4;
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
    private static final String DEFAULT_PACKAGE = "com.sun.xml.internal.";
    private static boolean debug = false;
    private static final Properties cacheProps = new Properties();
    private static volatile boolean firstTime = true;

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
        return "found system property, value=" + str;
    }

    public static /* synthetic */ String c(String str) {
        return "loaded from fallback value: " + str;
    }

    public static /* synthetic */ String d(Class cls, ClassLoader classLoader) {
        return "created new instance of " + cls + " using ClassLoader: " + classLoader;
    }

    private static void dPrint(Supplier<String> supplier) {
        if (debug) {
            System.err.println("JAXP: " + supplier.get());
        }
    }

    public static /* synthetic */ String e(String str) {
        return "find factoryId =" + str;
    }

    public static /* synthetic */ String f(File file) {
        return "Read properties file " + file;
    }

    public static <T> T find(Class<T> cls, final String str, ClassLoader classLoader, final String str2) throws FactoryConfigurationError {
        T t;
        final String string;
        dPrint(new Supplier() { // from class: javax.xml.stream.b
            @Override // java.util.function.Supplier
            public final Object get() {
                return FactoryFinder.e(str);
            }
        });
        try {
            final String systemProperty = cls.getName().equals(str) ? SecuritySupport.getSystemProperty(str) : System.getProperty(str);
            if (systemProperty != null) {
                dPrint(new Supplier() { // from class: javax.xml.stream.c
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return FactoryFinder.b(systemProperty);
                    }
                });
                return (T) newInstance(cls, systemProperty, classLoader, true);
            }
            try {
                if (firstTime) {
                    Properties properties = cacheProps;
                    synchronized (properties) {
                        try {
                            if (firstTime) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(SecuritySupport.getSystemProperty("java.home"));
                                String str3 = File.separator;
                                sb.append(str3);
                                sb.append("conf");
                                sb.append(str3);
                                sb.append("stax.properties");
                                string = sb.toString();
                                final File file = new File(string);
                                firstTime = $assertionsDisabled;
                                if (SecuritySupport.doesFileExist(file)) {
                                    dPrint(new Supplier() { // from class: javax.xml.stream.d
                                        @Override // java.util.function.Supplier
                                        public final Object get() {
                                            return FactoryFinder.a(file);
                                        }
                                    });
                                    properties.load(SecuritySupport.getFileInputStream(file));
                                } else {
                                    string = SecuritySupport.getSystemProperty("java.home") + str3 + "conf" + str3 + "jaxp.properties";
                                    final File file2 = new File(string);
                                    if (SecuritySupport.doesFileExist(file2)) {
                                        dPrint(new Supplier() { // from class: javax.xml.stream.e
                                            @Override // java.util.function.Supplier
                                            public final Object get() {
                                                return FactoryFinder.f(file2);
                                            }
                                        });
                                        properties.load(SecuritySupport.getFileInputStream(file2));
                                    }
                                }
                            } else {
                                string = null;
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                } else {
                    string = null;
                }
                final String property = cacheProps.getProperty(str);
                if (property != null) {
                    dPrint(new Supplier() { // from class: javax.xml.stream.f
                        @Override // java.util.function.Supplier
                        public final Object get() {
                            return FactoryFinder.g(string, property);
                        }
                    });
                    return (T) newInstance(cls, property, classLoader, true);
                }
            } catch (Exception e) {
                if (debug) {
                    e.printStackTrace();
                }
            }
            if (cls.getName().equals(str) && (t = (T) findServiceProvider(cls, classLoader)) != null) {
                return t;
            }
            if (str2 != null) {
                dPrint(new Supplier() { // from class: javax.xml.stream.g
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return FactoryFinder.c(str2);
                    }
                });
                return (T) newInstance(cls, str2, classLoader, true);
            }
            zm4.a("Provider for ", str, " cannot be found", null);
            return null;
        } catch (SecurityException e2) {
            zm4.a("Failed to read factoryId '", str, "'", e2);
            return null;
        }
    }

    private static <T> T findServiceProvider(final Class<T> cls, final ClassLoader classLoader) {
        try {
            return (T) AccessController.doPrivileged(new PrivilegedAction<T>() { // from class: javax.xml.stream.FactoryFinder.1
                @Override // java.security.PrivilegedAction
                public T run() {
                    ClassLoader classLoader2 = classLoader;
                    Class cls2 = cls;
                    Iterator it2 = (classLoader2 == null ? ServiceLoader.load(cls2) : ServiceLoader.load(cls2, classLoader2)).iterator();
                    if (it2.hasNext()) {
                        return (T) it2.next();
                    }
                    return null;
                }
            });
        } catch (ServiceConfigurationError e) {
            RuntimeException runtimeException = new RuntimeException("Provider for " + cls + " cannot be created", e);
            throw new FactoryConfigurationError(runtimeException, runtimeException.getMessage());
        }
    }

    public static /* synthetic */ String g(String str, String str2) {
        return "found in " + str + " value=" + str2;
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

    public static <T> T newInstance(Class<T> cls, String str, final ClassLoader classLoader, boolean z, boolean z2) throws FactoryConfigurationError {
        if (System.getSecurityManager() != null && str != null && str.startsWith(DEFAULT_PACKAGE)) {
            z2 = true;
            classLoader = null;
        }
        try {
            final Class<?> providerClass = getProviderClass(str, classLoader, z, z2);
            if (cls.isAssignableFrom(providerClass)) {
                Object objNewInstance = providerClass.getConstructor(null).newInstance(null);
                dPrint(new Supplier() { // from class: javax.xml.stream.a
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return FactoryFinder.d(providerClass, classLoader);
                    }
                });
                return cls.cast(objNewInstance);
            }
            throw new ClassCastException(str + " cannot be cast to " + cls.getName());
        } catch (ClassNotFoundException e) {
            zm4.a("Provider ", str, " not found", e);
            return null;
        } catch (Exception e2) {
            throw new FactoryConfigurationError("Provider " + str + " could not be instantiated: " + e2, e2);
        }
    }

    public static <T> T newInstance(Class<T> cls, String str, ClassLoader classLoader, boolean z) throws FactoryConfigurationError {
        return (T) newInstance(cls, str, classLoader, z, $assertionsDisabled);
    }

    public static <T> T find(Class<T> cls, String str) throws FactoryConfigurationError {
        return (T) find(cls, cls.getName(), null, str);
    }
}
