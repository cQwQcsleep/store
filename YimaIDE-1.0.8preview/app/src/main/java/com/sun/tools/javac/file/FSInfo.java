package com.sun.tools.javac.file;

import com.sun.nio.zipfs.ZipFileSystemProvider;
import com.sun.tools.javac.util.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FSInfo {
    private static final Map<String, String> READ_ONLY_JARFS_ENV = Map.of("accessMode", "readOnly", "zipinfo-time", "false");
    private FileSystemProvider jarFSProvider;

    public FSInfo(Context context) {
        context.put((Class<FSInfo>) FSInfo.class, this);
    }

    public static FSInfo instance(Context context) {
        FSInfo fSInfo = (FSInfo) context.get(FSInfo.class);
        return fSInfo == null ? new FSInfo() : fSInfo;
    }

    public static URL tryResolveFile(URL url, String str) throws MalformedURLException {
        URL url2 = new URL(url, str);
        if (str.indexOf(58) < 0 || "file".equalsIgnoreCase(url2.getProtocol())) {
            return url2;
        }
        return null;
    }

    public boolean exists(Path path) {
        return Files.exists(path, new LinkOption[0]);
    }

    public Path getCanonicalFile(Path path) {
        try {
            return path.toRealPath(new LinkOption[0]);
        } catch (IOException unused) {
            return path.toAbsolutePath().normalize();
        }
    }

    public List<Path> getJarClassPath(Path path) throws IOException {
        JarFile jarFile = new JarFile(path.toFile());
        try {
            Manifest manifest = jarFile.getManifest();
            if (manifest == null) {
                List<Path> list = Collections.EMPTY_LIST;
                jarFile.close();
                return list;
            }
            Attributes mainAttributes = manifest.getMainAttributes();
            if (mainAttributes == null) {
                List<Path> list2 = Collections.EMPTY_LIST;
                jarFile.close();
                return list2;
            }
            String value = mainAttributes.getValue(Attributes.Name.CLASS_PATH);
            if (value == null) {
                List<Path> list3 = Collections.EMPTY_LIST;
                jarFile.close();
                return list3;
            }
            ArrayList arrayList = new ArrayList();
            URL url = path.toUri().toURL();
            StringTokenizer stringTokenizer = new StringTokenizer(value);
            while (stringTokenizer.hasMoreTokens()) {
                try {
                    URL urlTryResolveFile = tryResolveFile(url, stringTokenizer.nextToken());
                    if (urlTryResolveFile != null) {
                        arrayList.add(Path.of(urlTryResolveFile.toURI()));
                    }
                } catch (URISyntaxException e) {
                    throw new IOException(e);
                }
            }
            jarFile.close();
            return arrayList;
        } catch (Throwable th) {
            try {
                jarFile.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public synchronized FileSystemProvider getJarFSProvider() {
        FileSystemProvider fileSystemProvider = this.jarFSProvider;
        if (fileSystemProvider != null) {
            return fileSystemProvider;
        }
        ZipFileSystemProvider zipFileSystemProvider = new ZipFileSystemProvider();
        this.jarFSProvider = zipFileSystemProvider;
        return zipFileSystemProvider;
    }

    public boolean isDirectory(Path path) {
        return Files.isDirectory(path, new LinkOption[0]);
    }

    public boolean isFile(Path path) {
        return Files.isRegularFile(path, new LinkOption[0]);
    }

    public Map<String, ?> readOnlyJarFSEnv(String str) {
        if (str == null) {
            return READ_ONLY_JARFS_ENV;
        }
        HashMap map = new HashMap(READ_ONLY_JARFS_ENV);
        map.put("releaseVersion", str);
        return Collections.unmodifiableMap(map);
    }

    public FSInfo() {
    }
}
