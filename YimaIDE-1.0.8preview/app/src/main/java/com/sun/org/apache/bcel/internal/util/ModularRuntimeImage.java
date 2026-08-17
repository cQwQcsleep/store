package com.sun.org.apache.bcel.internal.util;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ModularRuntimeImage implements Closeable {
    static final String MODULES_PATH;
    static final String PACKAGES_PATH;
    private final URLClassLoader classLoader;
    private final FileSystem fileSystem;

    static {
        StringBuilder sb = new StringBuilder();
        String str = File.separator;
        sb.append(str);
        sb.append("modules");
        MODULES_PATH = sb.toString();
        PACKAGES_PATH = str + "packages";
    }

    public ModularRuntimeImage(String str) throws IOException {
        Map map = Collections.EMPTY_MAP;
        URLClassLoader uRLClassLoaderNewInstance = URLClassLoader.newInstance(new URL[]{Paths.get(str, new String[0]).resolve("lib").resolve("jrt-fs.jar").toUri().toURL()});
        this.classLoader = uRLClassLoaderNewInstance;
        this.fileSystem = FileSystems.newFileSystem(URI.create("jrt:/"), map, uRLClassLoaderNewInstance);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        URLClassLoader uRLClassLoader = this.classLoader;
        if (uRLClassLoader != null) {
            uRLClassLoader.close();
        }
        FileSystem fileSystem = this.fileSystem;
        if (fileSystem != null) {
            fileSystem.close();
        }
    }

    public FileSystem getFileSystem() {
        return this.fileSystem;
    }

    public List<Path> list(Path path) throws IOException {
        final ArrayList arrayList = new ArrayList();
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path);
        try {
            directoryStreamNewDirectoryStream.forEach(new Consumer() { // from class: j4a
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    arrayList.add((Path) obj);
                }
            });
            directoryStreamNewDirectoryStream.close();
            return arrayList;
        } catch (Throwable th) {
            if (directoryStreamNewDirectoryStream != null) {
                try {
                    directoryStreamNewDirectoryStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public List<Path> modules() throws IOException {
        return list(MODULES_PATH);
    }

    public List<Path> packages() throws IOException {
        return list(PACKAGES_PATH);
    }

    public List<Path> list(String str) throws IOException {
        return list(this.fileSystem.getPath(str, new String[0]));
    }

    public ModularRuntimeImage() {
        this(null, FileSystems.getFileSystem(URI.create("jrt:/")));
    }

    private ModularRuntimeImage(URLClassLoader uRLClassLoader, FileSystem fileSystem) {
        this.classLoader = uRLClassLoader;
        this.fileSystem = fileSystem;
    }
}
