package com.sun.org.apache.xerces.internal.utils;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xerces.internal.utils.ObjectFactory;
import java.util.function.Supplier;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ObjectFactory {
    private static final boolean DEBUG = isDebugEnabled();
    private static final String JAXP_INTERNAL = "com.sun.org.apache";
    private static final String STAX_INTERNAL = "com.sun.xml.internal";

    public static /* synthetic */ String a(Class cls, ClassLoader classLoader) {
        return "created new instance of " + cls + " using ClassLoader: " + classLoader;
    }

    private static void debugPrintln(Supplier<String> supplier) {
        if (DEBUG) {
            System.err.println("XERCES: " + supplier.get());
        }
    }

    public static ClassLoader findClassLoader() throws ConfigurationError {
        if (System.getSecurityManager() != null) {
            return null;
        }
        ClassLoader contextClassLoader = SecuritySupport.getContextClassLoader();
        ClassLoader systemClassLoader = SecuritySupport.getSystemClassLoader();
        for (ClassLoader parentClassLoader = systemClassLoader; contextClassLoader != parentClassLoader; parentClassLoader = SecuritySupport.getParentClassLoader(parentClassLoader)) {
            if (parentClassLoader == null) {
                return contextClassLoader;
            }
        }
        ClassLoader classLoader = ObjectFactory.class.getClassLoader();
        for (ClassLoader parentClassLoader2 = systemClassLoader; classLoader != parentClassLoader2; parentClassLoader2 = SecuritySupport.getParentClassLoader(parentClassLoader2)) {
            if (parentClassLoader2 == null) {
                return classLoader;
            }
        }
        return systemClassLoader;
    }

    public static Class<?> findProviderClass(String str, ClassLoader classLoader, boolean z) throws ConfigurationError, ClassNotFoundException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            if (str.startsWith(JAXP_INTERNAL) || str.startsWith(STAX_INTERNAL)) {
                classLoader = null;
            } else {
                int iLastIndexOf = str.lastIndexOf(Constants.ATTRVAL_THIS);
                securityManager.checkPackageAccess(iLastIndexOf != -1 ? str.substring(0, iLastIndexOf) : str);
            }
        }
        if (classLoader == null) {
            return Class.forName(str, false, ObjectFactory.class.getClassLoader());
        }
        try {
            return classLoader.loadClass(str);
        } catch (ClassNotFoundException e) {
            if (!z) {
                throw e;
            }
            ClassLoader classLoader2 = ObjectFactory.class.getClassLoader();
            if (classLoader2 == null) {
                return Class.forName(str);
            }
            if (classLoader != classLoader2) {
                return classLoader2.loadClass(str);
            }
            throw e;
        }
    }

    private static boolean isDebugEnabled() {
        try {
            String systemProperty = SecuritySupport.getSystemProperty("xerces.debug");
            return (systemProperty == null || "false".equals(systemProperty)) ? false : true;
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static Object newInstance(String str, final ClassLoader classLoader, boolean z) throws ConfigurationError {
        try {
            final Class<?> clsFindProviderClass = findProviderClass(str, classLoader, z);
            Object objNewInstance = clsFindProviderClass.getConstructor(null).newInstance(null);
            debugPrintln(new Supplier() { // from class: foa
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ObjectFactory.a(clsFindProviderClass, classLoader);
                }
            });
            return objNewInstance;
        } catch (ClassNotFoundException e) {
            throw new ConfigurationError("Provider " + str + " not found", e);
        } catch (Exception e2) {
            throw new ConfigurationError("Provider " + str + " could not be instantiated: " + e2, e2);
        }
    }

    public static Object newInstance(String str, boolean z) throws ConfigurationError {
        if (System.getSecurityManager() != null) {
            return newInstance(str, null, z);
        }
        return newInstance(str, findClassLoader(), z);
    }

    public static Class<?> findProviderClass(String str, boolean z) throws ConfigurationError, ClassNotFoundException {
        return findProviderClass(str, findClassLoader(), z);
    }
}
