package jdk.xml.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class SecuritySupport {
    public static final String NEWLINE = System.lineSeparator();
    static final Properties cacheProps = new Properties();
    static volatile boolean firstTime = true;

    private SecuritySupport() {
    }

    public static /* synthetic */ InputStream b(ClassLoader classLoader, String str) {
        return classLoader == null ? SecuritySupport.class.getResourceAsStream(str) : classLoader.getResourceAsStream(str);
    }

    public static /* synthetic */ FileInputStream c(File file) {
        return new FileInputStream(file);
    }

    public static String checkAccess(String str, String str2, String str3) throws IOException {
        if (str == null || (str2 != null && str2.equalsIgnoreCase(str3))) {
            return null;
        }
        String strSubstring = "file";
        if (str.contains(":")) {
            URL url = new URL(str);
            String protocol = url.getProtocol();
            if (protocol.equalsIgnoreCase("jar")) {
                String path = url.getPath();
                strSubstring = path.substring(0, path.indexOf(":"));
            } else if (!protocol.equalsIgnoreCase("jrt")) {
                strSubstring = protocol;
            }
        }
        if (isProtocolAllowed(strSubstring, str2)) {
            return null;
        }
        return strSubstring;
    }

    public static /* synthetic */ String d(Class cls) {
        CodeSource codeSource = cls.getProtectionDomain().getCodeSource();
        if (codeSource == null) {
            return "(no code source)";
        }
        URL location = codeSource.getLocation();
        return location != null ? location.toString() : "(no location)";
    }

    public static boolean doesFileExist(final File file) {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction() { // from class: z1d
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return Boolean.valueOf(file.exists());
            }
        })).booleanValue();
    }

    public static /* synthetic */ ClassLoader e(ClassLoader classLoader) {
        ClassLoader parent;
        try {
            parent = classLoader.getParent();
        } catch (SecurityException unused) {
            parent = null;
        }
        if (parent == classLoader) {
            return null;
        }
        return parent;
    }

    public static /* synthetic */ ClassLoader g() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        return contextClassLoader == null ? ClassLoader.getSystemClassLoader() : contextClassLoader;
    }

    public static ClassLoader getClassLoader() throws SecurityException {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: v1d
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return SecuritySupport.h();
            }
        });
    }

    public static String getClassSource(final Class<?> cls) {
        return (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: x1d
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return SecuritySupport.d(cls);
            }
        });
    }

    public static ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: t1d
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return SecuritySupport.g();
            }
        });
    }

    public static String getErrorMessage(Locale locale, String str, String str2, Object[] objArr) {
        String string = (locale != null ? ResourceBundle.getBundle(str, locale) : ResourceBundle.getBundle(str)).getString(str2);
        return objArr != null ? MessageFormat.format(string, objArr) : string;
    }

    public static FileInputStream getFileInputStream(final File file) throws FileNotFoundException {
        try {
            return (FileInputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: a2d
                @Override // java.security.PrivilegedExceptionAction
                public final Object run() {
                    return SecuritySupport.c(file);
                }
            });
        } catch (PrivilegedActionException e) {
            throw ((FileNotFoundException) e.getException());
        }
    }

    public static <T> T getJAXPSystemProperty(Class<T> cls, String str, String str2) {
        String jAXPSystemProperty = getJAXPSystemProperty(str);
        if (jAXPSystemProperty != null) {
            str2 = jAXPSystemProperty;
        }
        if (Integer.class.isAssignableFrom(cls)) {
            return cls.cast(Integer.valueOf(Integer.parseInt(str2)));
        }
        return Boolean.class.isAssignableFrom(cls) ? cls.cast(Boolean.valueOf(Boolean.parseBoolean(str2))) : cls.cast(str2);
    }

    public static long getLastModified(final File file) {
        return ((Long) AccessController.doPrivileged(new PrivilegedAction() { // from class: u1d
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return Long.valueOf(file.lastModified());
            }
        })).longValue();
    }

    public static ClassLoader getParentClassLoader(final ClassLoader classLoader) {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: w1d
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return SecuritySupport.e(classLoader);
            }
        });
    }

    public static InputStream getResourceAsStream(final String str) {
        return (InputStream) AccessController.doPrivileged(new PrivilegedAction() { // from class: s1d
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return SecuritySupport.class.getResourceAsStream("/" + str);
            }
        });
    }

    public static ResourceBundle getResourceBundle(final String str, final Locale locale) {
        return (ResourceBundle) AccessController.doPrivileged(new PrivilegedAction() { // from class: y1d
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return SecuritySupport.m(str, locale);
            }
        });
    }

    public static ClassLoader getSystemClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: d2d
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return SecuritySupport.i();
            }
        });
    }

    public static <T> T getSystemProperty(Class<T> cls, String str, String str2) {
        String systemProperty = getSystemProperty(str);
        if (systemProperty != null) {
            str2 = systemProperty;
        }
        if (Integer.class.isAssignableFrom(cls)) {
            return cls.cast(Integer.valueOf(Integer.parseInt(str2)));
        }
        return Boolean.class.isAssignableFrom(cls) ? cls.cast(Boolean.valueOf(Boolean.parseBoolean(str2))) : cls.cast(str2);
    }

    public static /* synthetic */ ClassLoader h() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        return contextClassLoader == null ? ClassLoader.getSystemClassLoader() : contextClassLoader;
    }

    public static /* synthetic */ ClassLoader i() {
        try {
            return ClassLoader.getSystemClassLoader();
        } catch (SecurityException unused) {
            return null;
        }
    }

    public static boolean isDirectory(final File file) {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction() { // from class: b2d
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return Boolean.valueOf(file.isDirectory());
            }
        })).booleanValue();
    }

    public static boolean isFileExists(final File file) {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction() { // from class: c2d
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return Boolean.valueOf(file.exists());
            }
        })).booleanValue();
    }

    private static boolean isProtocolAllowed(String str, String str2) {
        if (str2 == null) {
            return false;
        }
        for (String str3 : str2.split(",")) {
            if (str3.trim().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ ResourceBundle m(String str, Locale locale) {
        try {
            try {
                return ResourceBundle.getBundle(str, locale);
            } catch (MissingResourceException unused) {
                throw new MissingResourceException("Could not load any resource bundle by " + str, str, "");
            }
        } catch (MissingResourceException unused2) {
            return ResourceBundle.getBundle(str, new Locale("en", "US"));
        }
    }

    /* JADX WARN: Code duplicated, block: B:46:0x0062 A[EXC_TOP_SPLITTER, PHI: r0 r2
      0x0062: PHI (r0v2 java.lang.String) = (r0v7 java.lang.String), (r0v5 java.lang.String) binds: [B:37:0x006c, B:31:0x0060] A[DONT_GENERATE, DONT_INLINE]
      0x0062: PHI (r2v2 java.io.FileInputStream) = (r2v0 java.io.FileInputStream), (r2v5 java.io.FileInputStream) binds: [B:37:0x006c, B:31:0x0060] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0053: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:25:0x0053 */
    public static String readJAXPProperty(String str) throws Throwable {
        FileInputStream fileInputStream;
        InputStream inputStream;
        InputStream inputStream2 = null;
        property = null;
        String property = null;
        try {
            try {
                if (firstTime) {
                    Properties properties = cacheProps;
                    try {
                        synchronized (properties) {
                            try {
                                if (firstTime) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(getSystemProperty("java.home"));
                                    String str2 = File.separator;
                                    sb.append(str2);
                                    sb.append("conf");
                                    sb.append(str2);
                                    sb.append("jaxp.properties");
                                    File file = new File(sb.toString());
                                    if (isFileExists(file)) {
                                        fileInputStream = getFileInputStream(file);
                                        properties.load(fileInputStream);
                                    } else {
                                        fileInputStream = null;
                                    }
                                    firstTime = false;
                                } else {
                                    fileInputStream = null;
                                }
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } else {
                    fileInputStream = null;
                }
                property = cacheProps.getProperty(str);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException unused) {
                    }
                }
            } catch (IOException unused2) {
                if (0 != 0) {
                    fileInputStream.close();
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream2 = inputStream;
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
        } catch (IOException unused4) {
            fileInputStream = null;
        } catch (Throwable th4) {
            th = th4;
        }
        return property;
    }

    public static String sanitizePath(String str) {
        int iLastIndexOf;
        return (str != null && (iLastIndexOf = str.lastIndexOf("/")) > 0) ? str.substring(iLastIndexOf + 1, str.length()) : "";
    }

    public static InputStream getResourceAsStream(final ClassLoader classLoader, final String str) {
        return (InputStream) AccessController.doPrivileged(new PrivilegedAction() { // from class: e2d
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return SecuritySupport.b(classLoader, str);
            }
        });
    }

    public static ResourceBundle getResourceBundle(String str) {
        return getResourceBundle(str, Locale.getDefault());
    }

    public static String getJAXPSystemProperty(String str) {
        String systemProperty = getSystemProperty(str);
        return systemProperty == null ? readJAXPProperty(str) : systemProperty;
    }

    public static String getSystemProperty(String str, String str2) {
        String systemProperty = getSystemProperty(str);
        return systemProperty == null ? str2 : systemProperty;
    }

    public static String getSystemProperty(final String str) {
        return (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: f2d
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return System.getProperty(str);
            }
        });
    }
}
