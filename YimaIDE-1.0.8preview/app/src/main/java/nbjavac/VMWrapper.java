package nbjavac;

import com.sun.nio.zipfs.ZipFileSystemProvider;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.tools.javac.ConfigProvider;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class VMWrapper {
    private static final String[] symbolFileLocation = {"lib", "ct.sym"};
    private static Reference<Path> cachedCtSym = new SoftReference(null);
    private static final Map<String, ?> CT_SYM_ZIP_ENV = Map.of("accessMode", "readOnly", "zipinfo-time", "false");

    private VMWrapper() {
    }

    public static Path findCtSym() {
        Path path = cachedCtSym.get();
        if (path != null) {
            return path;
        }
        try {
            ClassLoader classLoader = VMWrapper.class.getClassLoader();
            if (classLoader == null) {
                classLoader = ClassLoader.getSystemClassLoader();
            }
            Enumeration<URL> resources = classLoader.getResources("META-INF/services/com.sun.tools.javac.platform.PlatformProvider");
            if (resources.hasMoreElements()) {
                Path path2 = new ZipFileSystemProvider().newFileSystem(Paths.get(getJarUrl(resources).toURI()), CT_SYM_ZIP_ENV).getPath("META-INF", "ct.sym");
                cachedCtSym = new SoftReference(path2);
                return path2;
            }
            Path pathResolve = Paths.get(ConfigProvider.getJavaHome(), new String[0]);
            for (String str : symbolFileLocation) {
                pathResolve = pathResolve.resolve(str);
            }
            if (Files.exists(pathResolve, new LinkOption[0])) {
                return new ZipFileSystemProvider().newFileSystem(pathResolve, CT_SYM_ZIP_ENV).getRootDirectories().iterator().next();
            }
            throw new IllegalStateException("Cannot find ct.sym at " + pathResolve);
        } catch (IOException | URISyntaxException e) {
            e7f.a(e);
            return null;
        }
    }

    private static URL getJarUrl(Enumeration<URL> enumeration) throws IOException {
        while (enumeration.hasMoreElements()) {
            URL urlNextElement = enumeration.nextElement();
            if ("jar".equals(urlNextElement.getProtocol())) {
                return ((JarURLConnection) urlNextElement.openConnection()).getJarFileURL();
            }
            if (urlNextElement.getProtocol().equals("bundleresource")) {
                URLConnection uRLConnectionOpenConnection = urlNextElement.openConnection();
                if ("BundleURLConnection".equals(uRLConnectionOpenConnection.getClass().getSimpleName())) {
                    try {
                        URL url = (URL) uRLConnectionOpenConnection.getClass().getMethod("getLocalURL", null).invoke(uRLConnectionOpenConnection, null);
                        if (url != null) {
                            if ("file".equals(url.getProtocol())) {
                                return url;
                            }
                            if ("jar".equals(url.getProtocol())) {
                                return ((JarURLConnection) url.openConnection()).getJarFileURL();
                            }
                            continue;
                        }
                    } catch (ReflectiveOperationException unused) {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        k2d.a("Cannot find jar URL for ct.sym");
        return null;
    }

    public static String[] getRuntimeArguments() {
        return new String[0];
    }

    public static DirectoryStream<Path> newDirectoryStream(Path path) throws IOException {
        final ArrayList arrayList = new ArrayList();
        for (Path path2 : Files.newDirectoryStream(path)) {
            String string = path2.getFileName().toString();
            if (string.endsWith(PsuedoNames.PSEUDONAME_ROOT)) {
                arrayList.add(path.resolve(string.substring(0, string.length() - 1)));
            } else {
                arrayList.add(path2);
            }
        }
        return new DirectoryStream<Path>() { // from class: nbjavac.VMWrapper.1
            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.nio.file.DirectoryStream, java.lang.Iterable
            public Iterator<Path> iterator() {
                return arrayList.iterator();
            }
        };
    }

    public static <T> Stream<T> optional2Stream(Optional<T> optional) {
        return optional.isPresent() ? Stream.of(optional.get()) : Stream.empty();
    }

    public static FileSystem pathFs(Path path) {
        return new 2(path);
    }

    public static <K, V> Map<K, V> toMap(K k, V v, K k2, V v2) {
        return Collections.unmodifiableMap(new HashMap<K, V>(k, v, k2, v2) { // from class: nbjavac.VMWrapper.3
            final /* synthetic */ Object val$k1;
            final /* synthetic */ Object val$k2;
            final /* synthetic */ Object val$v1;
            final /* synthetic */ Object val$v2;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.val$k1 = k;
                this.val$v1 = v;
                this.val$k2 = k2;
                this.val$v2 = v2;
                put(k, v);
                put(k2, v2);
            }
        });
    }
}
