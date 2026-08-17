package org.jetbrains.kotlin.util;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import dalvik.system.PathClassLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.util.ServiceLoaderLite;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001dB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b2\u000e\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\b0\n2\u0006\u0010\u000b\u001a\u00020\fJ8\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\r\"\u0004\b\u0000\u0010\b2\u000e\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\b0\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00072\u0006\u0010\u000b\u001a\u00020\u0010J)\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007H\u0086\bJ#\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0086\bJ1\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00072\u0006\u0010\u000b\u001a\u00020\u0010H\u0086\bJ&\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007J\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0013\u001a\u00020\u000fH\u0002J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000fH\u0002J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000fH\u0002J$\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0013\u001a\u00020\u000f2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u0019H\u0002J\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u0005H\u0002J\u0014\u0010\u001c\u001a\u00020\u00052\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/util/ServiceLoaderLite;", "", Const.CONSTRUCTOR_NAME, "()V", "SERVICE_DIRECTORY_LOCATION", "", "loadImplementations", "", "Service", "service", Constants.CLASS_SIG, "classLoader", "Ljava/net/URLClassLoader;", "", "files", "Ljava/io/File;", "Ljava/lang/ClassLoader;", "findImplementations", "", "file", "findImplementationsInDirectory", "classId", "findImplementationsInJar", "parseLines", "lines", "Lkotlin/sequences/Sequence;", "parseLine", "line", "getClassIdentifier", "ServiceLoadingException", "kotlin-compiler"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ServiceLoaderLite {
    public static final ServiceLoaderLite INSTANCE = new ServiceLoaderLite();
    private static final String SERVICE_DIRECTORY_LOCATION = "META-INF/services/";

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/util/ServiceLoaderLite$ServiceLoadingException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "file", "Ljava/io/File;", "cause", "", Const.CONSTRUCTOR_NAME, "(Ljava/io/File;Ljava/lang/Throwable;)V", "getFile", "()Ljava/io/File;", "kotlin-compiler"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class ServiceLoadingException extends RuntimeException {
        private final File file;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ServiceLoadingException(File file, Throwable th) {
            super("Error loading services from " + file, th);
            file.getClass();
            th.getClass();
            this.file = file;
        }

        public final File getFile() {
            return this.file;
        }
    }

    private ServiceLoaderLite() {
    }

    public static CharSequence a(URL url) {
        String path = url.getPath();
        path.getClass();
        return path;
    }

    private final Set<String> findImplementations(Class<?> service, File file) {
        String classIdentifier = getClassIdentifier(service);
        if (file.isDirectory()) {
            return findImplementationsInDirectory(classIdentifier, file);
        }
        if (file.isFile()) {
            String lowerCase = FilesKt.getExtension(file).toLowerCase(Locale.ROOT);
            lowerCase.getClass();
            if (Intrinsics.areEqual(lowerCase, "jar")) {
                return findImplementationsInJar(classIdentifier, file);
            }
        }
        return SetsKt.emptySet();
    }

    private final Set<String> findImplementationsInDirectory(String classId, File file) {
        File file2 = new File(file, SERVICE_DIRECTORY_LOCATION + classId);
        if (!file2.isFile()) {
            file2 = null;
        }
        if (file2 == null) {
            return SetsKt.emptySet();
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2), Charsets.UTF_8), 8192);
            try {
                Set<String> lines = INSTANCE.parseLines(file, TextStreamsKt.lineSequence(bufferedReader));
                CloseableKt.closeFinally(bufferedReader, (Throwable) null);
                return lines;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(bufferedReader, th);
                    throw th2;
                }
            }
        } catch (IOError e) {
            throw new ServiceLoadingException(file, e);
        }
    }

    private final Set<String> findImplementationsInJar(String classId, File file) {
        ZipFile zipFile = new ZipFile(file);
        try {
            ZipEntry entry = zipFile.getEntry(SERVICE_DIRECTORY_LOCATION + classId);
            if (entry == null) {
                Set<String> setEmptySet = SetsKt.emptySet();
                CloseableKt.closeFinally(zipFile, (Throwable) null);
                return setEmptySet;
            }
            InputStream inputStream = zipFile.getInputStream(entry);
            try {
                inputStream.getClass();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8), 8192);
                try {
                    Set<String> lines = INSTANCE.parseLines(file, TextStreamsKt.lineSequence(bufferedReader));
                    CloseableKt.closeFinally(bufferedReader, (Throwable) null);
                    CloseableKt.closeFinally(inputStream, (Throwable) null);
                    CloseableKt.closeFinally(zipFile, (Throwable) null);
                    return lines;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(bufferedReader, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(inputStream, th3);
                    throw th4;
                }
            }
        } catch (Throwable th5) {
            try {
                throw th5;
            } catch (Throwable th6) {
                CloseableKt.closeFinally(zipFile, th5);
                throw th6;
            }
        }
    }

    private final String getClassIdentifier(Class<?> service) {
        return service.getName();
    }

    private final String parseLine(File file, String line) {
        boolean zIsJavaIdentifierStart;
        String string = StringsKt.trim(StringsKt.substringBefore$default(line, '#', (String) null, 2, (Object) null)).toString();
        if (string.length() <= 0) {
            string = null;
        }
        if (string == null) {
            return null;
        }
        int i = 0;
        int i2 = 0;
        while (i < string.length()) {
            char cCharAt = string.charAt(i);
            int i3 = i2 + 1;
            if (i2 == 0) {
                zIsJavaIdentifierStart = Character.isJavaIdentifierStart(cCharAt);
            } else {
                zIsJavaIdentifierStart = Character.isJavaIdentifierPart(cCharAt) || cCharAt == '.';
            }
            if (!zIsJavaIdentifierStart) {
                throw new ServiceLoadingException(file, new RuntimeException("Invalid Java identifier: " + line));
            }
            i++;
            i2 = i3;
        }
        return string;
    }

    private final Set<String> parseLines(File file, Sequence<String> lines) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = lines.iterator();
        while (it.hasNext()) {
            String line = INSTANCE.parseLine(file, (String) it.next());
            if (line != null) {
                linkedHashSet.add(line);
            }
        }
        return linkedHashSet;
    }

    public final <Service> List<Service> loadImplementations(Class<? extends Service> service, URLClassLoader classLoader) {
        service.getClass();
        classLoader.getClass();
        URL[] uRLs = classLoader.getURLs();
        uRLs.getClass();
        ArrayList arrayList = new ArrayList(uRLs.length);
        for (URL url : uRLs) {
            try {
                arrayList.add(Paths.get(url.toURI()).toFile());
            } catch (UnsupportedOperationException unused) {
                z01.a("Only local URLs are supported, got ", url.getProtocol());
                return null;
            } catch (FileSystemNotFoundException unused2) {
                z01.a("Only local URLs are supported, got ", url.getProtocol());
                return null;
            }
        }
        URL[] uRLs2 = classLoader.getURLs();
        uRLs2.getClass();
        String str = File.pathSeparator;
        str.getClass();
        return loadImplementations(service, arrayList, new PathClassLoader(ArraysKt.joinToString$default(uRLs2, str, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: c8d
            public final Object invoke(Object obj) {
                return ServiceLoaderLite.a((URL) obj);
            }
        }, 30, (Object) null), ServiceLoaderLite.class.getClassLoader()));
    }

    public final Set<String> findImplementations(Class<?> service, List<? extends File> files) {
        service.getClass();
        files.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = files.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(linkedHashSet, INSTANCE.findImplementations(service, (File) it.next()));
        }
        return linkedHashSet;
    }

    public final /* synthetic */ <Service> Set<String> findImplementations(List<? extends File> files) {
        files.getClass();
        Intrinsics.reifiedOperationMarker(4, "Service");
        return findImplementations(Object.class, files);
    }

    public final <Service> List<Service> loadImplementations(Class<? extends Service> service, List<? extends File> files, ClassLoader classLoader) {
        service.getClass();
        files.getClass();
        classLoader.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = findImplementations(service, files).iterator();
        while (it.hasNext()) {
            arrayList.add(service.cast(Class.forName(it.next(), false, classLoader).newInstance()));
        }
        return arrayList;
    }

    public final /* synthetic */ <Service> List<Service> loadImplementations(URLClassLoader classLoader) {
        classLoader.getClass();
        Intrinsics.reifiedOperationMarker(4, "Service");
        return loadImplementations(Object.class, classLoader);
    }

    public final /* synthetic */ <Service> List<Service> loadImplementations(List<? extends File> files, ClassLoader classLoader) {
        files.getClass();
        classLoader.getClass();
        Intrinsics.reifiedOperationMarker(4, "Service");
        return loadImplementations(Object.class, files, classLoader);
    }
}
