package javax.xml.validation;

import com.sun.org.apache.xerces.internal.jaxp.validation.XMLSchemaFactory;
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
class SchemaFactoryFinder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String DEFAULT_PACKAGE = "com.sun.org.apache.xerces.internal";
    private static final Class<SchemaFactory> SERVICE_CLASS;
    private static boolean debug = false;
    private final ClassLoader classLoader;
    private static final Properties cacheProps = new Properties();
    private static volatile boolean firstTime = true;

    static {
        boolean z = true;
        try {
            if (SecuritySupport.getSystemProperty("jaxp.debug") == null) {
                z = false;
            }
            debug = z;
        } catch (Exception unused) {
            debug = $assertionsDisabled;
        }
        SERVICE_CLASS = SchemaFactory.class;
    }

    public SchemaFactoryFinder(ClassLoader classLoader) {
        this.classLoader = classLoader;
        if (debug) {
            debugDisplayClassLoader();
        }
    }

    private SchemaFactory _newFactory(String str) {
        SchemaFactory schemaFactoryCreateInstance;
        final String str2 = SERVICE_CLASS.getName() + ":" + str;
        try {
            debugPrintln(new Supplier() { // from class: javax.xml.validation.o
                @Override // java.util.function.Supplier
                public final Object get() {
                    return SchemaFactoryFinder.i(str2);
                }
            });
            final String systemProperty = SecuritySupport.getSystemProperty(str2);
            if (systemProperty != null) {
                debugPrintln(new Supplier() { // from class: javax.xml.validation.p
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return SchemaFactoryFinder.k(systemProperty);
                    }
                });
                SchemaFactory schemaFactoryCreateInstance2 = createInstance(systemProperty);
                if (schemaFactoryCreateInstance2 != null) {
                    return schemaFactoryCreateInstance2;
                }
            } else {
                debugPrintln(new Supplier() { // from class: javax.xml.validation.q
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return SchemaFactoryFinder.o();
                    }
                });
            }
        } catch (Throwable th) {
            if (debug) {
                debugPrintln(new Supplier() { // from class: javax.xml.validation.b
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return SchemaFactoryFinder.c(str2);
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
                                debugPrintln(new Supplier() { // from class: javax.xml.validation.c
                                    @Override // java.util.function.Supplier
                                    public final Object get() {
                                        return SchemaFactoryFinder.p(file);
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
            debugPrintln(new Supplier() { // from class: javax.xml.validation.d
                @Override // java.util.function.Supplier
                public final Object get() {
                    return SchemaFactoryFinder.b(property);
                }
            });
            if (property != null && (schemaFactoryCreateInstance = createInstance(property)) != null) {
                return schemaFactoryCreateInstance;
            }
        } catch (Exception e) {
            if (debug) {
                e.printStackTrace();
            }
        }
        SchemaFactory schemaFactoryFindServiceProvider = findServiceProvider(str);
        if (schemaFactoryFindServiceProvider != null) {
            return schemaFactoryFindServiceProvider;
        }
        if (str.equals("http://www.w3.org/2001/XMLSchema")) {
            debugPrintln(new Supplier() { // from class: javax.xml.validation.e
                @Override // java.util.function.Supplier
                public final Object get() {
                    return SchemaFactoryFinder.m();
                }
            });
            return new XMLSchemaFactory();
        }
        debugPrintln(new Supplier() { // from class: javax.xml.validation.f
            @Override // java.util.function.Supplier
            public final Object get() {
                return SchemaFactoryFinder.d();
            }
        });
        return null;
    }

    public static /* synthetic */ String a(SchemaFactoryFinder schemaFactoryFinder) {
        return "using class loader (" + schemaFactoryFinder.classLoader + ") for search";
    }

    public static /* synthetic */ String b(String str) {
        return "found " + str + " in $java.home/conf/jaxp.properties";
    }

    public static /* synthetic */ String c(String str) {
        return "failed to look up system property '" + str + "'";
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

    public static /* synthetic */ String d() {
        return "all things were tried, but none was found. bailing out.";
    }

    private void debugDisplayClassLoader() {
        try {
            if (this.classLoader == SecuritySupport.getContextClassLoader()) {
                debugPrintln(new Supplier() { // from class: javax.xml.validation.a
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return SchemaFactoryFinder.g(this.b);
                    }
                });
                return;
            }
        } catch (Throwable unused) {
        }
        if (this.classLoader == ClassLoader.getSystemClassLoader()) {
            debugPrintln(new Supplier() { // from class: javax.xml.validation.i
                @Override // java.util.function.Supplier
                public final Object get() {
                    return SchemaFactoryFinder.q(this.b);
                }
            });
        } else {
            debugPrintln(new Supplier() { // from class: javax.xml.validation.j
                @Override // java.util.function.Supplier
                public final Object get() {
                    return SchemaFactoryFinder.a(this.b);
                }
            });
        }
    }

    private static void debugPrintln(Supplier<String> supplier) {
        if (debug) {
            System.err.println("JAXP: " + supplier.get());
        }
    }

    public static /* synthetic */ String f(String str) {
        return "unable to find a factory for " + str;
    }

    private SchemaFactory findServiceProvider(final String str) {
        final AccessControlContext context = AccessController.getContext();
        try {
            return (SchemaFactory) AccessController.doPrivileged(new PrivilegedAction<SchemaFactory>() { // from class: javax.xml.validation.SchemaFactoryFinder.2
                @Override // java.security.PrivilegedAction
                public SchemaFactory run() {
                    for (SchemaFactory schemaFactory : ServiceLoader.load(SchemaFactoryFinder.SERVICE_CLASS)) {
                        if (SchemaFactoryFinder.this.isSchemaLanguageSupportedBy(schemaFactory, str, context)) {
                            return schemaFactory;
                        }
                    }
                    return null;
                }
            });
        } catch (ServiceConfigurationError e) {
            throw new SchemaFactoryConfigurationError("Provider for " + SERVICE_CLASS + " cannot be created", e);
        }
    }

    public static /* synthetic */ String g(SchemaFactoryFinder schemaFactoryFinder) {
        return "using thread context class loader (" + schemaFactoryFinder.classLoader + ") for search";
    }

    public static /* synthetic */ String h(SchemaFactory schemaFactory, String str) {
        return "factory '" + schemaFactory.getClass().getName() + "' was found for " + str;
    }

    public static /* synthetic */ String i(String str) {
        return "Looking up system property '" + str + "'";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSchemaLanguageSupportedBy(final SchemaFactory schemaFactory, final String str, AccessControlContext accessControlContext) {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() { // from class: javax.xml.validation.SchemaFactoryFinder.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public Boolean run() {
                return Boolean.valueOf(schemaFactory.isSchemaLanguageSupported(str));
            }
        }, accessControlContext)).booleanValue();
    }

    public static /* synthetic */ String j(String str, Class cls) {
        return "loaded " + str + " from " + which(cls);
    }

    public static /* synthetic */ String k(String str) {
        return "The value is '" + str + "'";
    }

    public static /* synthetic */ String l(String str) {
        return "failed to getClass(" + str + ")";
    }

    public static /* synthetic */ String m() {
        return "attempting to use the platform default XML Schema validator";
    }

    public static /* synthetic */ String n(String str) {
        return "createInstance(" + str + ")";
    }

    public static /* synthetic */ String o() {
        return "The property is undefined.";
    }

    public static /* synthetic */ String p(File file) {
        return "Read properties file " + file;
    }

    public static /* synthetic */ String q(SchemaFactoryFinder schemaFactoryFinder) {
        return "using system class loader (" + schemaFactoryFinder.classLoader + ") for search";
    }

    private static String which(Class<?> cls) {
        return SecuritySupport.getClassSource(cls);
    }

    public SchemaFactory createInstance(final String str) {
        debugPrintln(new Supplier() { // from class: javax.xml.validation.k
            @Override // java.util.function.Supplier
            public final Object get() {
                return SchemaFactoryFinder.n(str);
            }
        });
        final Class<?> clsCreateClass = createClass(str);
        if (clsCreateClass == null) {
            debugPrintln(new Supplier() { // from class: javax.xml.validation.l
                @Override // java.util.function.Supplier
                public final Object get() {
                    return SchemaFactoryFinder.l(str);
                }
            });
            return null;
        }
        debugPrintln(new Supplier() { // from class: javax.xml.validation.m
            @Override // java.util.function.Supplier
            public final Object get() {
                return SchemaFactoryFinder.j(str, clsCreateClass);
            }
        });
        try {
            if (SchemaFactory.class.isAssignableFrom(clsCreateClass)) {
                return (SchemaFactory) clsCreateClass.getConstructor(null).newInstance(null);
            }
            throw new ClassCastException(clsCreateClass.getName() + " cannot be cast to " + SchemaFactory.class);
        } catch (ClassCastException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            debugPrintln(new Supplier() { // from class: javax.xml.validation.n
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

    public SchemaFactory newFactory(final String str) {
        str.getClass();
        final SchemaFactory schemaFactory_newFactory = _newFactory(str);
        if (schemaFactory_newFactory != null) {
            debugPrintln(new Supplier() { // from class: javax.xml.validation.g
                @Override // java.util.function.Supplier
                public final Object get() {
                    return SchemaFactoryFinder.h(schemaFactory_newFactory, str);
                }
            });
            return schemaFactory_newFactory;
        }
        debugPrintln(new Supplier() { // from class: javax.xml.validation.h
            @Override // java.util.function.Supplier
            public final Object get() {
                return SchemaFactoryFinder.f(str);
            }
        });
        return schemaFactory_newFactory;
    }
}
