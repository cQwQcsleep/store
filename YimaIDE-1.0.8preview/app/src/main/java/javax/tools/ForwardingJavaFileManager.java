package javax.tools;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import javax.tools.JavaFileManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ForwardingJavaFileManager<M extends JavaFileManager> implements JavaFileManager {
    protected final M fileManager;

    public ForwardingJavaFileManager(M m) {
        Objects.requireNonNull(m);
        this.fileManager = m;
    }

    @Override // javax.tools.JavaFileManager, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.fileManager.close();
    }

    @Override // javax.tools.JavaFileManager
    public boolean contains(JavaFileManager.Location location, FileObject fileObject) throws IOException {
        return this.fileManager.contains(location, fileObject);
    }

    @Override // javax.tools.JavaFileManager, java.io.Flushable
    public void flush() throws IOException {
        this.fileManager.flush();
    }

    @Override // javax.tools.JavaFileManager
    public ClassLoader getClassLoader(JavaFileManager.Location location) {
        return this.fileManager.getClassLoader(location);
    }

    @Override // javax.tools.JavaFileManager
    public FileObject getFileForInput(JavaFileManager.Location location, String str, String str2) throws IOException {
        return this.fileManager.getFileForInput(location, str, str2);
    }

    @Override // javax.tools.JavaFileManager
    public FileObject getFileForOutput(JavaFileManager.Location location, String str, String str2, FileObject fileObject) throws IOException {
        return this.fileManager.getFileForOutput(location, str, str2, fileObject);
    }

    @Override // javax.tools.JavaFileManager
    public FileObject getFileForOutputForOriginatingFiles(JavaFileManager.Location location, String str, String str2, FileObject... fileObjectArr) throws IOException {
        try {
            return getClass().getMethod("getFileForOutput", JavaFileManager.Location.class, String.class, String.class, FileObject.class).getDeclaringClass() == ForwardingJavaFileManager.class ? this.fileManager.getFileForOutputForOriginatingFiles(location, str, str2, fileObjectArr) : super.getFileForOutputForOriginatingFiles(location, str, str2, fileObjectArr);
        } catch (NoSuchMethodException e) {
            throw new InternalError("This should never happen.", e);
        }
    }

    @Override // javax.tools.JavaFileManager
    public JavaFileObject getJavaFileForInput(JavaFileManager.Location location, String str, JavaFileObject.Kind kind) throws IOException {
        return this.fileManager.getJavaFileForInput(location, str, kind);
    }

    @Override // javax.tools.JavaFileManager
    public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String str, JavaFileObject.Kind kind, FileObject fileObject) throws IOException {
        return this.fileManager.getJavaFileForOutput(location, str, kind, fileObject);
    }

    @Override // javax.tools.JavaFileManager
    public JavaFileObject getJavaFileForOutputForOriginatingFiles(JavaFileManager.Location location, String str, JavaFileObject.Kind kind, FileObject... fileObjectArr) throws IOException {
        try {
            return getClass().getMethod("getJavaFileForOutput", JavaFileManager.Location.class, String.class, JavaFileObject.Kind.class, FileObject.class).getDeclaringClass() == ForwardingJavaFileManager.class ? this.fileManager.getJavaFileForOutputForOriginatingFiles(location, str, kind, fileObjectArr) : super.getJavaFileForOutputForOriginatingFiles(location, str, kind, fileObjectArr);
        } catch (NoSuchMethodException e) {
            throw new InternalError("This should never happen.", e);
        }
    }

    @Override // javax.tools.JavaFileManager
    public JavaFileManager.Location getLocationForModule(JavaFileManager.Location location, String str) throws IOException {
        return this.fileManager.getLocationForModule(location, str);
    }

    @Override // javax.tools.JavaFileManager
    public <S> ServiceLoader<S> getServiceLoader(JavaFileManager.Location location, Class<S> cls) throws IOException {
        return this.fileManager.getServiceLoader(location, cls);
    }

    @Override // javax.tools.JavaFileManager
    public boolean handleOption(String str, Iterator<String> it) {
        return this.fileManager.handleOption(str, it);
    }

    @Override // javax.tools.JavaFileManager
    public boolean hasLocation(JavaFileManager.Location location) {
        return this.fileManager.hasLocation(location);
    }

    @Override // javax.tools.JavaFileManager
    public String inferBinaryName(JavaFileManager.Location location, JavaFileObject javaFileObject) {
        return this.fileManager.inferBinaryName(location, javaFileObject);
    }

    @Override // javax.tools.JavaFileManager
    public String inferModuleName(JavaFileManager.Location location) throws IOException {
        return this.fileManager.inferModuleName(location);
    }

    @Override // javax.tools.JavaFileManager
    public boolean isSameFile(FileObject fileObject, FileObject fileObject2) {
        return this.fileManager.isSameFile(fileObject, fileObject2);
    }

    @Override // javax.tools.OptionChecker
    public int isSupportedOption(String str) {
        return this.fileManager.isSupportedOption(str);
    }

    @Override // javax.tools.JavaFileManager
    public Iterable<JavaFileObject> list(JavaFileManager.Location location, String str, Set<JavaFileObject.Kind> set, boolean z) throws IOException {
        return this.fileManager.list(location, str, set, z);
    }

    @Override // javax.tools.JavaFileManager
    public Iterable<Set<JavaFileManager.Location>> listLocationsForModules(JavaFileManager.Location location) throws IOException {
        return this.fileManager.listLocationsForModules(location);
    }

    @Override // javax.tools.JavaFileManager
    public JavaFileManager.Location getLocationForModule(JavaFileManager.Location location, JavaFileObject javaFileObject) throws IOException {
        return this.fileManager.getLocationForModule(location, javaFileObject);
    }
}
