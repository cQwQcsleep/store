package com.sun.tools.javac.main;

import com.sun.tools.javac.util.Context;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.Set;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.OptionChecker;
import javax.tools.StandardJavaFileManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DelegatingJavaFileManager implements JavaFileManager {
    private final JavaFileManager baseFM;
    private final JavaFileManager releaseFM;

    private DelegatingJavaFileManager(JavaFileManager javaFileManager, JavaFileManager javaFileManager2) {
        this.releaseFM = javaFileManager;
        this.baseFM = javaFileManager2;
    }

    private JavaFileManager delegate(JavaFileManager.Location location) {
        return this.releaseFM.hasLocation(location) ? this.releaseFM : this.baseFM;
    }

    public static void installReleaseFileManager(Context context, JavaFileManager javaFileManager, JavaFileManager javaFileManager2) {
        context.put((Class<Object>) JavaFileManager.class, (Object) null);
        context.put((Class<OptionChecker>) JavaFileManager.class, javaFileManager2 instanceof StandardJavaFileManager ? new DelegatingSJFM(javaFileManager, (StandardJavaFileManager) javaFileManager2) : new DelegatingJavaFileManager(javaFileManager, javaFileManager2));
    }

    @Override // javax.tools.JavaFileManager, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.releaseFM.close();
        this.baseFM.close();
    }

    @Override // javax.tools.JavaFileManager
    public boolean contains(JavaFileManager.Location location, FileObject fileObject) throws IOException {
        return delegate(location).contains(location, fileObject);
    }

    @Override // javax.tools.JavaFileManager, java.io.Flushable
    public void flush() throws IOException {
        this.releaseFM.flush();
        this.baseFM.flush();
    }

    public JavaFileManager getBaseFileManager() {
        return this.baseFM;
    }

    @Override // javax.tools.JavaFileManager
    public ClassLoader getClassLoader(JavaFileManager.Location location) {
        return delegate(location).getClassLoader(location);
    }

    @Override // javax.tools.JavaFileManager
    public FileObject getFileForInput(JavaFileManager.Location location, String str, String str2) throws IOException {
        return delegate(location).getFileForInput(location, str, str2);
    }

    @Override // javax.tools.JavaFileManager
    public FileObject getFileForOutput(JavaFileManager.Location location, String str, String str2, FileObject fileObject) throws IOException {
        return delegate(location).getFileForOutput(location, str, str2, fileObject);
    }

    @Override // javax.tools.JavaFileManager
    public FileObject getFileForOutputForOriginatingFiles(JavaFileManager.Location location, String str, String str2, FileObject... fileObjectArr) throws IOException {
        return delegate(location).getFileForOutputForOriginatingFiles(location, str, str2, fileObjectArr);
    }

    @Override // javax.tools.JavaFileManager
    public JavaFileObject getJavaFileForInput(JavaFileManager.Location location, String str, JavaFileObject.Kind kind) throws IOException {
        return delegate(location).getJavaFileForInput(location, str, kind);
    }

    @Override // javax.tools.JavaFileManager
    public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String str, JavaFileObject.Kind kind, FileObject fileObject) throws IOException {
        return delegate(location).getJavaFileForOutput(location, str, kind, fileObject);
    }

    @Override // javax.tools.JavaFileManager
    public JavaFileObject getJavaFileForOutputForOriginatingFiles(JavaFileManager.Location location, String str, JavaFileObject.Kind kind, FileObject... fileObjectArr) throws IOException {
        return delegate(location).getJavaFileForOutputForOriginatingFiles(location, str, kind, fileObjectArr);
    }

    @Override // javax.tools.JavaFileManager
    public JavaFileManager.Location getLocationForModule(JavaFileManager.Location location, String str) throws IOException {
        return delegate(location).getLocationForModule(location, str);
    }

    @Override // javax.tools.JavaFileManager
    public <S> ServiceLoader<S> getServiceLoader(JavaFileManager.Location location, Class<S> cls) throws IOException {
        return delegate(location).getServiceLoader(location, cls);
    }

    @Override // javax.tools.JavaFileManager
    public boolean handleOption(String str, Iterator<String> it) {
        return this.baseFM.handleOption(str, it);
    }

    @Override // javax.tools.JavaFileManager
    public boolean hasLocation(JavaFileManager.Location location) {
        return this.releaseFM.hasLocation(location) || this.baseFM.hasLocation(location);
    }

    @Override // javax.tools.JavaFileManager
    public String inferBinaryName(JavaFileManager.Location location, JavaFileObject javaFileObject) {
        return delegate(location).inferBinaryName(location, javaFileObject);
    }

    @Override // javax.tools.JavaFileManager
    public String inferModuleName(JavaFileManager.Location location) throws IOException {
        return delegate(location).inferModuleName(location);
    }

    @Override // javax.tools.JavaFileManager
    public boolean isSameFile(FileObject fileObject, FileObject fileObject2) {
        return this.baseFM.isSameFile(fileObject, fileObject2);
    }

    @Override // javax.tools.OptionChecker
    public int isSupportedOption(String str) {
        return this.baseFM.isSupportedOption(str);
    }

    @Override // javax.tools.JavaFileManager
    public Iterable<JavaFileObject> list(JavaFileManager.Location location, String str, Set<JavaFileObject.Kind> set, boolean z) throws IOException {
        return delegate(location).list(location, str, set, z);
    }

    @Override // javax.tools.JavaFileManager
    public Iterable<Set<JavaFileManager.Location>> listLocationsForModules(JavaFileManager.Location location) throws IOException {
        return delegate(location).listLocationsForModules(location);
    }

    public static final class DelegatingSJFM extends DelegatingJavaFileManager implements StandardJavaFileManager {
        private final StandardJavaFileManager baseSJFM;

        private DelegatingSJFM(JavaFileManager javaFileManager, StandardJavaFileManager standardJavaFileManager) {
            super(javaFileManager, standardJavaFileManager);
            this.baseSJFM = standardJavaFileManager;
        }

        @Override // javax.tools.StandardJavaFileManager
        public Path asPath(FileObject fileObject) {
            return this.baseSJFM.asPath(fileObject);
        }

        @Override // javax.tools.StandardJavaFileManager
        public Iterable<? extends JavaFileObject> getJavaFileObjects(File... fileArr) {
            return this.baseSJFM.getJavaFileObjects(fileArr);
        }

        @Override // javax.tools.StandardJavaFileManager
        public Iterable<? extends JavaFileObject> getJavaFileObjectsFromFiles(Iterable<? extends File> iterable) {
            return this.baseSJFM.getJavaFileObjectsFromFiles(iterable);
        }

        @Override // javax.tools.StandardJavaFileManager
        public Iterable<? extends JavaFileObject> getJavaFileObjectsFromPaths(Collection<? extends Path> collection) {
            return this.baseSJFM.getJavaFileObjectsFromPaths(collection);
        }

        @Override // javax.tools.StandardJavaFileManager
        public Iterable<? extends JavaFileObject> getJavaFileObjectsFromStrings(Iterable<String> iterable) {
            return this.baseSJFM.getJavaFileObjectsFromStrings(iterable);
        }

        @Override // javax.tools.StandardJavaFileManager
        public Iterable<? extends File> getLocation(JavaFileManager.Location location) {
            return this.baseSJFM.getLocation(location);
        }

        @Override // javax.tools.StandardJavaFileManager
        public Iterable<? extends Path> getLocationAsPaths(JavaFileManager.Location location) {
            return this.baseSJFM.getLocationAsPaths(location);
        }

        @Override // com.sun.tools.javac.main.DelegatingJavaFileManager, javax.tools.JavaFileManager
        public boolean isSameFile(FileObject fileObject, FileObject fileObject2) {
            return this.baseSJFM.isSameFile(fileObject, fileObject2);
        }

        @Override // javax.tools.StandardJavaFileManager
        public void setLocation(JavaFileManager.Location location, Iterable<? extends File> iterable) throws IOException {
            this.baseSJFM.setLocation(location, iterable);
        }

        @Override // javax.tools.StandardJavaFileManager
        public void setLocationForModule(JavaFileManager.Location location, String str, Collection<? extends Path> collection) throws IOException {
            this.baseSJFM.setLocationForModule(location, str, collection);
        }

        @Override // javax.tools.StandardJavaFileManager
        public void setLocationFromPaths(JavaFileManager.Location location, Collection<? extends Path> collection) throws IOException {
            this.baseSJFM.setLocationFromPaths(location, collection);
        }

        @Override // javax.tools.StandardJavaFileManager
        public void setPathFactory(StandardJavaFileManager.PathFactory pathFactory) {
            this.baseSJFM.setPathFactory(pathFactory);
        }

        @Override // javax.tools.StandardJavaFileManager
        public Iterable<? extends JavaFileObject> getJavaFileObjects(Path... pathArr) {
            return this.baseSJFM.getJavaFileObjects(pathArr);
        }

        @Override // javax.tools.StandardJavaFileManager
        @Deprecated
        public Iterable<? extends JavaFileObject> getJavaFileObjectsFromPaths(Iterable<? extends Path> iterable) {
            return this.baseSJFM.getJavaFileObjectsFromPaths(iterable);
        }

        @Override // javax.tools.StandardJavaFileManager
        public Iterable<? extends JavaFileObject> getJavaFileObjects(String... strArr) {
            return this.baseSJFM.getJavaFileObjects(strArr);
        }
    }

    @Override // javax.tools.JavaFileManager
    public JavaFileManager.Location getLocationForModule(JavaFileManager.Location location, JavaFileObject javaFileObject) throws IOException {
        return delegate(location).getLocationForModule(location, javaFileObject);
    }
}
