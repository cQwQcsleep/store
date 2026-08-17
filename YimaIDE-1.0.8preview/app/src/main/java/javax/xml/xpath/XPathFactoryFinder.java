package javax.xml.xpath;

import com.sun.org.apache.xpath.internal.jaxp.XPathFactoryImpl;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Properties;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.function.Supplier;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class XPathFactoryFinder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String DEFAULT_PACKAGE = "com.sun.org.apache.xpath.internal";
    private static final Class<XPathFactory> SERVICE_CLASS;
    private static final Properties cacheProps;
    private static boolean debug = false;
    private static volatile boolean firstTime;
    private final ClassLoader classLoader;

    static {
        try {
            debug = SecuritySupport.getSystemProperty("jaxp.debug") != null;
        } catch (Exception unused) {
            debug = $assertionsDisabled;
        }
        cacheProps = new Properties();
        firstTime = true;
        SERVICE_CLASS = XPathFactory.class;
    }

    public XPathFactoryFinder(ClassLoader classLoader) {
        this.classLoader = classLoader;
        if (debug) {
            debugDisplayClassLoader();
        }
    }

    private XPathFactory _newFactory(String str) throws XPathFactoryConfigurationException {
        XPathFactory xPathFactoryCreateInstance;
        final String str2 = SERVICE_CLASS.getName() + ":" + str;
        try {
            debugPrintln(new Supplier() { // from class: javax.xml.xpath.a
                @Override // java.util.function.Supplier
                public final Object get() {
                    return XPathFactoryFinder.k(str2);
                }
            });
            final String systemProperty = SecuritySupport.getSystemProperty(str2);
            if (systemProperty != null) {
                debugPrintln(new Supplier() { // from class: javax.xml.xpath.i
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return XPathFactoryFinder.d(systemProperty);
                    }
                });
                XPathFactory xPathFactoryCreateInstance2 = createInstance(systemProperty);
                if (xPathFactoryCreateInstance2 != null) {
                    return xPathFactoryCreateInstance2;
                }
            } else {
                debugPrintln(new Supplier() { // from class: javax.xml.xpath.j
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return XPathFactoryFinder.g();
                    }
                });
            }
        } catch (Throwable th) {
            if (debug) {
                debugPrintln(new Supplier() { // from class: javax.xml.xpath.k
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return XPathFactoryFinder.p(str2);
                    }
                });
                th.printStackTrace();
            }
        }
        String systemProperty2 = SecuritySupport.getSystemProperty("java.home");
        StringBuilder sb = new StringBuilder();
        sb.append(systemProperty2);
        String str3 = File.separator;
        sb.append(str3);
        sb.append("conf");
        sb.append(str3);
        sb.append("jaxp.properties");
        String string = sb.toString();
        try {
            if (firstTime) {
                Properties properties = cacheProps;
                synchronized (properties) {
                    try {
                        if (firstTime) {
                            final File file = new File(string);
                            firstTime = $assertionsDisabled;
                            if (SecuritySupport.doesFileExist(file)) {
                                debugPrintln(new Supplier() { // from class: javax.xml.xpath.l
                                    @Override // java.util.function.Supplier
                                    public final Object get() {
                                        return XPathFactoryFinder.m(file);
                                    }
                                });
                                properties.load(SecuritySupport.getFileInputStream(file));
                            }
                        }
                    } catch (Throwable th2) {
                        throw th2;
                    }
                }
            }
            final String property = cacheProps.getProperty(str2);
            debugPrintln(new Supplier() { // from class: javax.xml.xpath.m
                @Override // java.util.function.Supplier
                public final Object get() {
                    return XPathFactoryFinder.q(property);
                }
            });
            if (property != null && (xPathFactoryCreateInstance = createInstance(property)) != null) {
                return xPathFactoryCreateInstance;
            }
        } catch (Exception e) {
            if (debug) {
                e.printStackTrace();
            }
        }
        XPathFactory xPathFactoryFindServiceProvider = findServiceProvider(str);
        if (xPathFactoryFindServiceProvider != null) {
            return xPathFactoryFindServiceProvider;
        }
        if (str.equals("http://java.sun.com/jaxp/xpath/dom")) {
            debugPrintln(new Supplier() { // from class: javax.xml.xpath.n
                @Override // java.util.function.Supplier
                public final Object get() {
                    return XPathFactoryFinder.n();
                }
            });
            return new XPathFactoryImpl();
        }
        debugPrintln(new Supplier() { // from class: javax.xml.xpath.o
            @Override // java.util.function.Supplier
            public final Object get() {
                return XPathFactoryFinder.h();
            }
        });
        return null;
    }

    public static /* synthetic */ String a(String str) {
        return "createInstance(" + str + ")";
    }

    public static /* synthetic */ String b(XPathFactoryFinder xPathFactoryFinder) {
        return "using class loader (" + xPathFactoryFinder.classLoader + ") for search";
    }

    public static /* synthetic */ String c(String str, Class cls) {
        return "loaded " + str + " from " + which(cls);
    }

    private Class<?> createClass(String str) {
        boolean z = (System.getSecurityManager() == null || str == null || !str.startsWith(DEFAULT_PACKAGE)) ? false : true;
        try {
            ClassLoader classLoader = this.classLoader;
            return (classLoader == null || z) ? Class.forName(str) : Class.forName(str, $assertionsDisabled, classLoader);
        } catch (Throwable th) {
            if (!debug) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static /* synthetic */ String d(String str) {
        return "The value is '" + str + "'";
    }

    private void debugDisplayClassLoader() {
        try {
            if (this.classLoader == SecuritySupport.getContextClassLoader()) {
                debugPrintln(new Supplier() { // from class: javax.xml.xpath.p
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return XPathFactoryFinder.i(this.b);
                    }
                });
                return;
            }
        } catch (Throwable unused) {
        }
        if (this.classLoader == ClassLoader.getSystemClassLoader()) {
            debugPrintln(new Supplier() { // from class: javax.xml.xpath.q
                @Override // java.util.function.Supplier
                public final Object get() {
                    return XPathFactoryFinder.e(this.b);
                }
            });
        } else {
            debugPrintln(new Supplier() { // from class: javax.xml.xpath.b
                @Override // java.util.function.Supplier
                public final Object get() {
                    return XPathFactoryFinder.b(this.b);
                }
            });
        }
    }

    private static void debugPrintln(Supplier<String> supplier) {
        if (debug) {
            System.err.println("JAXP: " + supplier.get());
        }
    }

    public static /* synthetic */ String e(XPathFactoryFinder xPathFactoryFinder) {
        return "using system class loader (" + xPathFactoryFinder.classLoader + ") for search";
    }

    public static /* synthetic */ String f(XPathFactory xPathFactory, String str) {
        return "factory '" + xPathFactory.getClass().getName() + "' was found for " + str;
    }

    private XPathFactory findServiceProvider(final String str) throws XPathFactoryConfigurationException {
        final AccessControlContext context = AccessController.getContext();
        try {
            return (XPathFactory) AccessController.doPrivileged(new PrivilegedAction<XPathFactory>() { // from class: javax.xml.xpath.XPathFactoryFinder.2
                @Override // java.security.PrivilegedAction
                public XPathFactory run() {
                    for (XPathFactory xPathFactory : ServiceLoader.load(XPathFactoryFinder.SERVICE_CLASS)) {
                        if (XPathFactoryFinder.this.isObjectModelSupportedBy(xPathFactory, str, context)) {
                            return xPathFactory;
                        }
                    }
                    return null;
                }
            });
        } catch (ServiceConfigurationError e) {
            throw new XPathFactoryConfigurationException(e);
        }
    }

    public static /* synthetic */ String g() {
        return "The property is undefined.";
    }

    public static /* synthetic */ String h() {
        return "all things were tried, but none was found. bailing out.";
    }

    public static /* synthetic */ String i(XPathFactoryFinder xPathFactoryFinder) {
        return "using thread context class loader (" + xPathFactoryFinder.classLoader + ") for search";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isObjectModelSupportedBy(final XPathFactory xPathFactory, final String str, AccessControlContext accessControlContext) {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() { // from class: javax.xml.xpath.XPathFactoryFinder.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public Boolean run() {
                return Boolean.valueOf(xPathFactory.isObjectModelSupported(str));
            }
        }, accessControlContext)).booleanValue();
    }

    public static /* synthetic */ String j(String str) {
        return "unable to find a factory for " + str;
    }

    public static /* synthetic */ String k(String str) {
        return "Looking up system property '" + str + "'";
    }

    public static /* synthetic */ String m(File file) {
        return "Read properties file " + file;
    }

    public static /* synthetic */ String n() {
        return "attempting to use the platform default W3C DOM XPath lib";
    }

    public static /* synthetic */ String o(String str) {
        return "failed to getClass(" + str + ")";
    }

    public static /* synthetic */ String p(String str) {
        return "failed to look up system property '" + str + "'";
    }

    public static /* synthetic */ String q(String str) {
        return "found " + str + " in $java.home/conf/jaxp.properties";
    }

    private static String which(Class<?> cls) {
        return SecuritySupport.getClassSource(cls);
    }

    public XPathFactory createInstance(final String str) throws XPathFactoryConfigurationException {
        debugPrintln(new Supplier() { // from class: javax.xml.xpath.c
            @Override // java.util.function.Supplier
            public final Object get() {
                return XPathFactoryFinder.a(str);
            }
        });
        final Class<?> clsCreateClass = createClass(str);
        if (clsCreateClass == null) {
            debugPrintln(new Supplier() { // from class: javax.xml.xpath.d
                @Override // java.util.function.Supplier
                public final Object get() {
                    return XPathFactoryFinder.o(str);
                }
            });
            return null;
        }
        debugPrintln(new Supplier() { // from class: javax.xml.xpath.e
            @Override // java.util.function.Supplier
            public final Object get() {
                return XPathFactoryFinder.c(str, clsCreateClass);
            }
        });
        try {
            return (XPathFactory) clsCreateClass.getConstructor(null).newInstance(null);
        } catch (ClassCastException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            debugPrintln(new Supplier() { // from class: javax.xml.xpath.f
                @Override // java.util.function.Supplier
                public final Object get() {
                    return "could not instantiate ".concat(clsCreateClass.getName());
                }
            });
            if (debug) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public XPathFactory newFactory(final String str) throws XPathFactoryConfigurationException {
        str.getClass();
        final XPathFactory xPathFactory_newFactory = _newFactory(str);
        if (xPathFactory_newFactory != null) {
            debugPrintln(new Supplier() { // from class: javax.xml.xpath.g
                @Override // java.util.function.Supplier
                public final Object get() {
                    return XPathFactoryFinder.f(xPathFactory_newFactory, str);
                }
            });
            return xPathFactory_newFactory;
        }
        debugPrintln(new Supplier() { // from class: javax.xml.xpath.h
            @Override // java.util.function.Supplier
            public final Object get() {
                return XPathFactoryFinder.j(str);
            }
        });
        return xPathFactory_newFactory;
    }
}
