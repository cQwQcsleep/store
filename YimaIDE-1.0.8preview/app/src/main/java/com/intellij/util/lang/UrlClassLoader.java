package com.intellij.util.lang;

import androidx.collection.ScatterMapKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class UrlClassLoader extends ClassLoader implements ClassPath.ClassDataConsumer {
    protected final ClassPath.ClassDataConsumer classDataConsumer;
    private final ClassLoadingLocks classLoadingLocks;
    protected final ClassPath classPath;
    private final boolean isBootstrapResourcesAllowed;
    private final boolean isSystemClassLoader;
    private static final boolean isClassPathIndexEnabledGlobalValue = Boolean.parseBoolean(System.getProperty("idea.classpath.index.enabled", "false"));
    private static final boolean mimicJarUrlConnection = Boolean.parseBoolean(System.getProperty("idea.mimic.jar.url.connection", "false"));
    private static final boolean isParallelCapable = ClassLoader.registerAsParallelCapable();
    private static final ClassLoader appClassLoader = UrlClassLoader.class.getClassLoader();
    private static final ThreadLocal<Boolean> skipFindingResource = new ThreadLocal<>();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2 || i == 3 || i == 8 || i == 34 || i == 14 || i == 15 || i == 27 || i == 28) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 2 || i == 3 || i == 8 || i == 34 || i == 14 || i == 15 || i == 27 || i == 28) ? 2 : 3];
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 8:
            case 14:
            case 15:
            case 27:
            case 28:
            case 34:
                objArr[0] = "com/intellij/util/lang/UrlClassLoader";
                break;
            case 4:
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "parent";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "classLoader";
                break;
            case 9:
            case 10:
                objArr[0] = "builder";
                break;
            case 11:
                objArr[0] = "classPath";
                break;
            case 12:
            case 33:
                objArr[0] = "url";
                break;
            case 13:
                objArr[0] = "files";
                break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 26:
                objArr[0] = "name";
                break;
            case 23:
                objArr[0] = "dir";
                break;
            case 24:
                objArr[0] = "fileNameFilter";
                break;
            case 25:
                objArr[0] = "consumer";
                break;
            case 29:
                objArr[0] = "path";
                break;
            case 30:
                objArr[0] = "text";
                break;
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                objArr[0] = "suffix";
                break;
            case 32:
                objArr[0] = "s";
                break;
            default:
                objArr[0] = "jar";
                break;
        }
        if (i == 1) {
            objArr[1] = "getClassPath";
        } else if (i == 2) {
            objArr[1] = "getBaseUrls";
        } else if (i == 3) {
            objArr[1] = "getLoadingStats";
        } else if (i == 8) {
            objArr[1] = "createDefaultBuilderForJdk";
        } else if (i == 34) {
            objArr[1] = "urlToFilePath";
        } else if (i == 14) {
            objArr[1] = "getUrls";
        } else if (i == 15) {
            objArr[1] = "getFiles";
        } else if (i == 27) {
            objArr[1] = "findResources";
        } else if (i != 28) {
            objArr[1] = "com/intellij/util/lang/UrlClassLoader";
        } else {
            objArr[1] = "getClassLoadingLock";
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 8:
            case 14:
            case 15:
            case 27:
            case 28:
            case 34:
                break;
            case 4:
            case 9:
            case 10:
            case 11:
                objArr[2] = "<init>";
                break;
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "registerInClassLoaderValueMap";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "createDefaultBuilderForJdk";
                break;
            case 12:
                objArr[2] = "addURL";
                break;
            case 13:
                objArr[2] = "addFiles";
                break;
            case 16:
                objArr[2] = "findClass";
                break;
            case 17:
                objArr[2] = "isByteBufferSupported";
                break;
            case 18:
            case 19:
                objArr[2] = "consumeClassData";
                break;
            case 20:
                objArr[2] = "findResource";
                break;
            case 21:
                objArr[2] = "getResourceAsBytes";
                break;
            case 22:
                objArr[2] = "getResourceAsStream";
                break;
            case 23:
            case 24:
            case 25:
                objArr[2] = "processResources";
                break;
            case 26:
                objArr[2] = "findResources";
                break;
            case 29:
                objArr[2] = "toCanonicalPath";
                break;
            case 30:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                objArr[2] = "endsWith";
                break;
            case 32:
                objArr[2] = "lastIndexOf";
                break;
            case 33:
                objArr[2] = "urlToFilePath";
                break;
            default:
                objArr[2] = "appendToClassPathForInstrumentation";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2 && i != 3 && i != 8 && i != 34 && i != 14 && i != 15 && i != 27 && i != 28) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    private void definePackageIfNeeded(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf == -1) {
            return;
        }
        String strSubstring = str.substring(0, iLastIndexOf);
        if (isPackageDefined(strSubstring)) {
            return;
        }
        try {
            definePackage(strSubstring, null, null, null, null, null, null, null);
        } catch (IllegalArgumentException unused) {
        }
    }

    private Resource doFindResource(String str) {
        String canonicalPath = toCanonicalPath(str);
        Resource resourceFindResource = this.classPath.findResource(canonicalPath);
        if (resourceFindResource == null && canonicalPath.startsWith("/") && this.classPath.findResource(canonicalPath.substring(1)) != null) {
            logError("Calling `ClassLoader#getResource` with leading slash doesn't work; strip", new IllegalArgumentException(str));
        }
        return resourceFindResource;
    }

    private static boolean endsWith(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null) {
            $$$reportNull$$$0(30);
        }
        if (charSequence2 == null) {
            $$$reportNull$$$0(31);
        }
        int length = charSequence.length();
        int length2 = charSequence2.length();
        if (length < length2) {
            return false;
        }
        for (int i = length - 1; i >= length - length2; i--) {
            if (charSequence.charAt(i) != charSequence2.charAt((i + length2) - length)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNotExcludedLangClasses(String str) {
        return (str.endsWith("/CompoundRuntimeException") || str.endsWith("/JavaVersion")) ? false : true;
    }

    private static int lastIndexOf(CharSequence charSequence, char c, int i, int i2) {
        if (charSequence == null) {
            $$$reportNull$$$0(32);
        }
        int iMax = Math.max(i, 0);
        for (int iMin = Math.min(i2, charSequence.length()) - 1; iMin >= iMax; iMin--) {
            if (charSequence.charAt(iMin) == c) {
                return iMin;
            }
        }
        return -1;
    }

    private void logError(String str, Throwable th) {
        try {
            Class<?> clsLoadClass = loadClass("com.intellij.openapi.diagnostic.Logger");
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            (void) lookup.findVirtual(clsLoadClass, "error", MethodType.methodType(Void.TYPE, String.class, Throwable.class)).bindTo((Object) lookup.findStatic(clsLoadClass, "getInstance", MethodType.methodType(clsLoadClass, (Class<?>) Class.class)).invoke(getClass())).invokeExact(str, th);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
            System.err.println(getClass().getName() + ": " + str);
            th.printStackTrace(System.err);
        }
    }

    private static void processDots(StringBuilder sb, int i, int i2) {
        if (i != 2) {
            if (i != 1) {
                for (int i3 = 0; i3 < i; i3++) {
                    sb.append('.');
                }
                sb.append('/');
                return;
            }
            return;
        }
        if (endsWith(sb, "/../") || "../".contentEquals(sb)) {
            i2 = -1;
        } else {
            int iLastIndexOf = lastIndexOf(sb, '/', i2, sb.length() - 1);
            if (iLastIndexOf >= 0) {
                i2 = iLastIndexOf + 1;
            } else if (i2 <= 0) {
                i2 = sb.length() > 0 ? 0 : iLastIndexOf;
            }
        }
        if (i2 >= 0) {
            sb.delete(i2, sb.length());
        } else {
            sb.append("../");
        }
    }

    private static int processRoot(String str, StringBuilder sb) {
        if (!str.isEmpty() && str.charAt(0) == '/') {
            sb.append('/');
            return 1;
        }
        if (str.length() <= 2 || str.charAt(1) != ':' || str.charAt(2) != '/') {
            return 0;
        }
        sb.append((CharSequence) str, 0, 3);
        return 3;
    }

    public static String toCanonicalPath(String str) {
        if (str == null) {
            $$$reportNull$$$0(29);
        }
        if (str.isEmpty()) {
            return str;
        }
        if (str.charAt(0) == '.') {
            if (str.length() == 1) {
                return "";
            }
            if (str.charAt(1) == '/') {
                str = str.substring(2);
            }
        }
        int iIndexOf = -1;
        do {
            iIndexOf = str.indexOf(47, iIndexOf + 1);
            char cCharAt = iIndexOf == str.length() - 1 ? (char) 0 : str.charAt(iIndexOf + 1);
            if (cCharAt == '.' || cCharAt == '/') {
                break;
            }
        } while (iIndexOf != -1);
        if (iIndexOf == -1) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        int iProcessRoot = processRoot(str, sb);
        int i = 0;
        boolean z = true;
        for (int i2 = iProcessRoot; i2 < str.length(); i2++) {
            char cCharAt2 = str.charAt(i2);
            if (cCharAt2 == '/') {
                if (!z) {
                    processDots(sb, i, iProcessRoot);
                    i = 0;
                }
                z = true;
            } else {
                if (cCharAt2 != '.') {
                    while (i > 0) {
                        sb.append('.');
                        i--;
                    }
                    sb.append(cCharAt2);
                } else if (z || i > 0) {
                    i++;
                } else {
                    sb.append('.');
                }
                z = false;
            }
        }
        if (i > 0) {
            processDots(sb, i, iProcessRoot);
        }
        return sb.toString();
    }

    public static String urlToFilePath(String str) {
        int i;
        if (str == null) {
            $$$reportNull$$$0(33);
        }
        int i2 = str.startsWith("file:") ? 5 : 0;
        int iIndexOf = str.indexOf("!/");
        if (str.charAt(i2) == '/' && str.length() > (i = i2 + 2) && str.charAt(i) == ':') {
            i2++;
        }
        if (iIndexOf < 0) {
            iIndexOf = str.length();
        }
        String string = UrlUtilRt.unescapePercentSequences(str, i2, iIndexOf).toString();
        if (string == null) {
            $$$reportNull$$$0(34);
        }
        return string;
    }

    @Override // com.intellij.util.lang.ClassPath.ClassDataConsumer
    public Class<?> consumeClassData(String str, byte[] bArr) throws IOException {
        if (str == null) {
            $$$reportNull$$$0(18);
        }
        definePackageIfNeeded(str);
        return super.defineClass(str, bArr, 0, bArr.length, null);
    }

    @Override // java.lang.ClassLoader
    public Class<?> findClass(String str) throws ClassNotFoundException {
        String str2;
        if (str == null) {
            $$$reportNull$$$0(16);
        }
        String strReplace = str.replace('.', '/');
        String str3 = strReplace + ".class";
        long packageNameHash = ClasspathCache.getPackageNameHash(strReplace, strReplace.lastIndexOf(47));
        if (this.isSystemClassLoader && packageNameHash == -9217824570049207139L && isNotExcludedLangClasses(strReplace)) {
            return appClassLoader.loadClass(str);
        }
        try {
            str2 = str;
            try {
                Class<?> clsFindClass = this.classPath.findClass(str2, str3, packageNameHash, this.classDataConsumer);
                if (clsFindClass != null) {
                    return clsFindClass;
                }
                throw new ClassNotFoundException(str2);
            } catch (IOException e) {
                e = e;
                throw new ClassNotFoundException(str2, e);
            }
        } catch (IOException e2) {
            e = e2;
            str2 = str;
        }
    }

    @Override // java.lang.ClassLoader
    public URL findResource(String str) {
        Resource resourceDoFindResource;
        if (str == null) {
            $$$reportNull$$$0(20);
        }
        if (skipFindingResource.get() == null && (resourceDoFindResource = doFindResource(str)) != null) {
            return resourceDoFindResource.getURL();
        }
        return null;
    }

    @Override // java.lang.ClassLoader
    public Enumeration<URL> findResources(String str) {
        if (str == null) {
            $$$reportNull$$$0(26);
        }
        Enumeration<URL> resources = this.classPath.getResources(str);
        if (resources == null) {
            $$$reportNull$$$0(27);
        }
        return resources;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    public final Object getClassLoadingLock(String str) {
        Object orCreateLock;
        ClassLoadingLocks classLoadingLocks = this.classLoadingLocks;
        ?? r1 = this;
        if (classLoadingLocks != null) {
            orCreateLock = classLoadingLocks.getOrCreateLock(str);
        }
        if (r1 == 0) {
            r1 = orCreateLock;
            $$$reportNull$$$0(28);
        }
        r1 = orCreateLock;
        return r1;
    }

    @Override // java.lang.ClassLoader
    public InputStream getResourceAsStream(String str) {
        if (str == null) {
            $$$reportNull$$$0(22);
        }
        Resource resourceDoFindResource = doFindResource(str);
        if (resourceDoFindResource != null) {
            try {
                return resourceDoFindResource.getInputStream();
            } catch (IOException e) {
                logError("Cannot load resource " + str, e);
                return null;
            }
        }
        if (this.isBootstrapResourcesAllowed) {
            ThreadLocal<Boolean> threadLocal = skipFindingResource;
            threadLocal.set(Boolean.TRUE);
            try {
                URL resource = super.getResource(str);
                if (resource != null) {
                    try {
                        InputStream inputStreamOpenStream = resource.openStream();
                        threadLocal.set(null);
                        return inputStreamOpenStream;
                    } catch (IOException unused) {
                    }
                }
                skipFindingResource.set(null);
            } catch (Throwable th) {
                skipFindingResource.set(null);
                throw th;
            }
        }
        return null;
    }

    public final List<URL> getUrls() {
        ArrayList arrayList = new ArrayList();
        Iterator<Path> it = this.classPath.getFiles().iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(it.next().toUri().toURL());
            } catch (MalformedURLException unused) {
            }
        }
        return arrayList;
    }

    public boolean isPackageDefined(String str) {
        return getPackage(str) != null;
    }
}
