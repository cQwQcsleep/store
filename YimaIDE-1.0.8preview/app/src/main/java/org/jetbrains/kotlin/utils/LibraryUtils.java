package org.jetbrains.kotlin.utils;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.vfs.VirtualFile;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\r\u001a\u0004\u0018\u00010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102\u0006\u0010\u0011\u001a\u00020\bH\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J \u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0014\u0010 \u001a\u00020\b*\u00020!2\u0006\u0010\"\u001a\u00020\bH\u0002R\u0013\u0010\u0004\u001a\u00070\u0005¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/utils/LibraryUtils;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "LOG", "Lcom/intellij/openapi/diagnostic/Logger;", "Lorg/jetbrains/annotations/NotNull;", "TITLE_KOTLIN_JAVASCRIPT_STDLIB", "", "META_INF", "getMETA_INF", "()Ljava/lang/String;", "MANIFEST_PATH", "getJarFile", "Lcom/intellij/openapi/vfs/VirtualFile;", "classesRoots", "", "jarName", "isKotlinJavascriptStdLibrary", "", "library", "Ljava/io/File;", "getManifestFromJar", "Ljava/util/jar/Manifest;", "getManifestFromDirectory", "getManifestFromJarOrDirectory", "getManifestMainAttributesFromJarOrDirectory", "Ljava/util/jar/Attributes;", "checkAttributeValue", "expected", "attributeName", "Ljava/util/jar/Attributes$Name;", "getPropertyOrFail", "Ljava/util/Properties;", "propName", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LibraryUtils {
    public static final LibraryUtils INSTANCE;
    private static final Logger LOG;
    private static final String MANIFEST_PATH;
    private static final String META_INF;
    private static String TITLE_KOTLIN_JAVASCRIPT_STDLIB;

    static {
        String propertyOrFail;
        LibraryUtils libraryUtils = new LibraryUtils();
        INSTANCE = libraryUtils;
        Logger logger = Logger.getInstance(LibraryUtils.class);
        logger.getClass();
        LOG = logger;
        META_INF = "META-INF/";
        MANIFEST_PATH = "META-INF/MANIFEST.MF";
        InputStream resourceAsStream = LibraryUtils.class.getResourceAsStream("/kotlinManifest.properties");
        if (resourceAsStream != null) {
            try {
                Properties properties = new Properties();
                properties.load(resourceAsStream);
                propertyOrFail = libraryUtils.getPropertyOrFail(properties, "manifest.impl.title.kotlin.javascript.stdlib");
            } catch (IOException e) {
                LOG.error(e);
                propertyOrFail = "";
            }
            TITLE_KOTLIN_JAVASCRIPT_STDLIB = propertyOrFail;
        }
        logger.error("Resource 'kotlinManifest.properties' not found.");
        propertyOrFail = "";
        TITLE_KOTLIN_JAVASCRIPT_STDLIB = propertyOrFail;
    }

    private LibraryUtils() {
    }

    private final boolean checkAttributeValue(File library, String expected, Attributes.Name attributeName) {
        Attributes manifestMainAttributesFromJarOrDirectory = getManifestMainAttributesFromJarOrDirectory(library);
        String value = manifestMainAttributesFromJarOrDirectory != null ? manifestMainAttributesFromJarOrDirectory.getValue(attributeName) : null;
        return value != null && Intrinsics.areEqual(value, expected);
    }

    @JvmStatic
    public static final VirtualFile getJarFile(List<? extends VirtualFile> classesRoots, String jarName) {
        Object next;
        classesRoots.getClass();
        jarName.getClass();
        Iterator<T> it = classesRoots.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (Intrinsics.areEqual(((VirtualFile) next).getName(), jarName)) {
                return (VirtualFile) next;
            }
        }
        next = null;
        return (VirtualFile) next;
    }

    private final Manifest getManifestFromDirectory(File library) {
        if (library.canRead() && library.isDirectory()) {
            File file = new File(library, MANIFEST_PATH);
            if (!file.exists()) {
                return null;
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    return new Manifest(fileInputStream);
                } finally {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                LOG.warn("IOException " + e);
            }
        }
        return null;
    }

    private final Manifest getManifestFromJar(File library) {
        if (!library.canRead()) {
            return null;
        }
        try {
            JarFile jarFile = new JarFile(library);
            try {
                Manifest manifest = jarFile.getManifest();
                CloseableKt.closeFinally(jarFile, (Throwable) null);
                return manifest;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(jarFile, th);
                    throw th2;
                }
            }
        } catch (IOException unused) {
            return null;
        }
    }

    private final Manifest getManifestFromJarOrDirectory(File library) {
        return library.isDirectory() ? getManifestFromDirectory(library) : getManifestFromJar(library);
    }

    private final Attributes getManifestMainAttributesFromJarOrDirectory(File library) {
        Manifest manifestFromJarOrDirectory = getManifestFromJarOrDirectory(library);
        if (manifestFromJarOrDirectory != null) {
            return manifestFromJarOrDirectory.getMainAttributes();
        }
        return null;
    }

    private final String getPropertyOrFail(Properties properties, String str) {
        String property = properties.getProperty(str);
        if (property == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            properties.list(new PrintStream(byteArrayOutputStream));
            LOG.error(str + " not found.\n " + byteArrayOutputStream);
        }
        property.getClass();
        return property;
    }

    @JvmStatic
    public static final boolean isKotlinJavascriptStdLibrary(File library) {
        library.getClass();
        LibraryUtils libraryUtils = INSTANCE;
        String str = TITLE_KOTLIN_JAVASCRIPT_STDLIB;
        Attributes.Name name = Attributes.Name.IMPLEMENTATION_TITLE;
        name.getClass();
        return libraryUtils.checkAttributeValue(library, str, name);
    }

    public final String getMETA_INF() {
        return META_INF;
    }
}
