package com.sun.tools.javac.file;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.text.Normalizer;
import java.util.Iterator;
import java.util.Objects;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class PathFileObject implements JavaFileObject {
    private static final FileSystem defaultFileSystem = FileSystems.getDefault();
    private static final boolean isMacOS = System.getProperty("os.name", "").contains("OS X");
    protected final BaseFileManager fileManager;
    private boolean hasParents;
    protected final Path path;

    public static class CannotCreateUriError extends Error {
        private static final long serialVersionUID = 9101708840997613546L;

        public CannotCreateUriError(String str, Throwable th) {
            super(str, th);
        }
    }

    public static class DirectoryFileObject extends PathFileObject {
        private final RelativePath relativePath;
        private final Path userPackageRootDir;

        private DirectoryFileObject(BaseFileManager baseFileManager, Path path, Path path2, RelativePath relativePath) {
            super(baseFileManager, path);
            Objects.requireNonNull(path2);
            this.userPackageRootDir = path2;
            this.relativePath = relativePath;
        }

        @Override // javax.tools.FileObject
        public String getName() {
            return this.relativePath.resolveAgainst(this.userPackageRootDir).toString();
        }

        @Override // com.sun.tools.javac.file.PathFileObject
        public PathFileObject getSibling(String str) {
            return new DirectoryFileObject(this.fileManager, this.path.resolveSibling(str), this.userPackageRootDir, new RelativePath.RelativeFile(this.relativePath.dirname(), str));
        }

        @Override // com.sun.tools.javac.file.PathFileObject
        public String inferBinaryName(Iterable<? extends Path> iterable) {
            return PathFileObject.toBinaryName(this.relativePath);
        }

        @Override // com.sun.tools.javac.file.PathFileObject
        public String toString() {
            return "DirectoryFileObject[" + this.userPackageRootDir + ":" + this.relativePath.path + "]";
        }
    }

    public static class JarFileObject extends PathFileObject {
        private final Path userJarPath;

        private JarFileObject(BaseFileManager baseFileManager, Path path, Path path2) {
            super(baseFileManager, path);
            this.userJarPath = path2;
        }

        @Override // javax.tools.FileObject
        public String getName() {
            return this.userJarPath + "(" + this.path + ")";
        }

        @Override // com.sun.tools.javac.file.PathFileObject
        public PathFileObject getSibling(String str) {
            return new JarFileObject(this.fileManager, this.path.resolveSibling(str), this.userJarPath);
        }

        @Override // com.sun.tools.javac.file.PathFileObject
        public String inferBinaryName(Iterable<? extends Path> iterable) {
            return PathFileObject.toBinaryName(this.path.getFileSystem().getRootDirectories().iterator().next().relativize(this.path));
        }

        @Override // com.sun.tools.javac.file.PathFileObject
        public String toString() {
            return "JarFileObject[" + this.userJarPath + ":" + this.path + "]";
        }
    }

    public static class SimpleFileObject extends PathFileObject {
        private final Path userPath;

        private SimpleFileObject(BaseFileManager baseFileManager, Path path, Path path2) {
            super(baseFileManager, path);
            this.userPath = path2;
        }

        @Override // com.sun.tools.javac.file.PathFileObject, javax.tools.JavaFileObject
        public JavaFileObject.Kind getKind() {
            return BaseFileManager.getKind(this.userPath);
        }

        @Override // javax.tools.FileObject
        public String getName() {
            return this.userPath.toString();
        }

        @Override // com.sun.tools.javac.file.PathFileObject
        public String getShortName() {
            return this.userPath.getFileName().toString();
        }

        @Override // com.sun.tools.javac.file.PathFileObject
        public PathFileObject getSibling(String str) {
            return new SimpleFileObject(this.fileManager, this.path.resolveSibling(str), this.userPath.resolveSibling(str));
        }

        @Override // com.sun.tools.javac.file.PathFileObject
        public String inferBinaryName(Iterable<? extends Path> iterable) {
            Path absolutePath = this.path.toAbsolutePath();
            Iterator<? extends Path> it = iterable.iterator();
            while (it.hasNext()) {
                Path absolutePath2 = it.next().toAbsolutePath();
                if (absolutePath.startsWith(absolutePath2)) {
                    try {
                        Path pathRelativize = absolutePath2.relativize(absolutePath);
                        if (pathRelativize != null) {
                            return PathFileObject.toBinaryName(pathRelativize);
                        }
                        continue;
                    } catch (IllegalArgumentException unused) {
                        continue;
                    }
                }
            }
            return null;
        }

        @Override // com.sun.tools.javac.file.PathFileObject, javax.tools.JavaFileObject
        public boolean isNameCompatible(String str, JavaFileObject.Kind kind) {
            return isPathNameCompatible(this.userPath, str, kind);
        }

        @Override // com.sun.tools.javac.file.PathFileObject, javax.tools.FileObject
        public URI toUri() {
            return this.userPath.toUri().normalize();
        }
    }

    public PathFileObject(BaseFileManager baseFileManager, Path path) {
        Objects.requireNonNull(baseFileManager);
        this.fileManager = baseFileManager;
        if (Files.isDirectory(path, new LinkOption[0])) {
            w01.a("directories not supported");
            throw null;
        }
        this.path = path;
    }

    private void ensureParentDirectoriesExist() throws IOException {
        if (this.hasParents) {
            return;
        }
        Path parent = this.path.getParent();
        if (parent != null && !Files.isDirectory(parent, new LinkOption[0])) {
            try {
                Files.createDirectories(parent, new FileAttribute[0]);
            } catch (IOException e) {
                dk3.a("could not create parent directories", e);
                return;
            }
        }
        this.hasParents = true;
    }

    public static PathFileObject forDirectoryPath(BaseFileManager baseFileManager, Path path, Path path2, RelativePath relativePath) {
        return new DirectoryFileObject(baseFileManager, path, path2, relativePath);
    }

    public static PathFileObject forJRTPath(BaseFileManager baseFileManager, Path path) {
        return new JRTFileObject(baseFileManager, path);
    }

    public static PathFileObject forJarPath(BaseFileManager baseFileManager, Path path, Path path2) {
        return new JarFileObject(baseFileManager, path, path2);
    }

    public static PathFileObject forSimplePath(BaseFileManager baseFileManager, Path path, Path path2) {
        return new SimpleFileObject(baseFileManager, path, path2);
    }

    public static String getSimpleName(FileObject fileObject) {
        String schemeSpecificPart = fileObject.toUri().getSchemeSpecificPart();
        return schemeSpecificPart.substring(schemeSpecificPart.lastIndexOf(PsuedoNames.PSEUDONAME_ROOT) + 1);
    }

    private static String removeExtension(String str) {
        int iLastIndexOf = str.lastIndexOf(Constants.ATTRVAL_THIS);
        return iLastIndexOf == -1 ? str : str.substring(0, iLastIndexOf);
    }

    public static String toBinaryName(Path path) {
        return toBinaryName(path.toString(), path.getFileSystem().getSeparator());
    }

    @Override // javax.tools.FileObject
    public boolean delete() {
        try {
            Files.delete(this.path);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof PathFileObject) && this.path.equals(((PathFileObject) obj).path);
    }

    @Override // javax.tools.JavaFileObject
    public Modifier getAccessLevel() {
        return null;
    }

    @Override // javax.tools.FileObject
    public CharSequence getCharContent(boolean z) throws IOException {
        CharBuffer cachedContent = this.fileManager.getCachedContent(this);
        if (cachedContent != null) {
            return cachedContent;
        }
        InputStream inputStreamOpenInputStream = openInputStream();
        try {
            ByteBuffer byteBufferMakeByteBuffer = this.fileManager.makeByteBuffer(inputStreamOpenInputStream);
            JavaFileObject javaFileObjectUseSource = this.fileManager.log.useSource(this);
            try {
                CharBuffer charBufferDecode = this.fileManager.decode(byteBufferMakeByteBuffer, z);
                this.fileManager.log.useSource(javaFileObjectUseSource);
                this.fileManager.recycleByteBuffer(byteBufferMakeByteBuffer);
                if (!z) {
                    this.fileManager.cache(this, charBufferDecode);
                }
                if (inputStreamOpenInputStream != null) {
                    inputStreamOpenInputStream.close();
                }
                return charBufferDecode;
            } catch (Throwable th) {
                this.fileManager.log.useSource(javaFileObjectUseSource);
                throw th;
            }
        } catch (Throwable th2) {
            if (inputStreamOpenInputStream != null) {
                try {
                    inputStreamOpenInputStream.close();
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                }
            }
            throw th2;
        }
    }

    @Override // javax.tools.JavaFileObject
    public JavaFileObject.Kind getKind() {
        return BaseFileManager.getKind(this.path);
    }

    @Override // javax.tools.FileObject
    public long getLastModified() {
        try {
            return Files.getLastModifiedTime(this.path, new LinkOption[0]).toMillis();
        } catch (IOException unused) {
            return 0L;
        }
    }

    @Override // javax.tools.JavaFileObject
    public NestingKind getNestingKind() {
        return null;
    }

    public Path getPath() {
        return this.path;
    }

    public String getShortName() {
        return this.path.getFileName().toString();
    }

    public abstract PathFileObject getSibling(String str);

    public int hashCode() {
        return this.path.hashCode();
    }

    public abstract String inferBinaryName(Iterable<? extends Path> iterable);

    public boolean isJarFile() {
        return this instanceof JarFileObject;
    }

    @Override // javax.tools.JavaFileObject
    public boolean isNameCompatible(String str, JavaFileObject.Kind kind) {
        return isPathNameCompatible(this.path, str, kind);
    }

    public boolean isPathNameCompatible(Path path, String str, JavaFileObject.Kind kind) {
        Objects.requireNonNull(str);
        Objects.requireNonNull(kind);
        if (kind == JavaFileObject.Kind.OTHER && BaseFileManager.getKind(path) != kind) {
            return false;
        }
        String str2 = str + kind.extension;
        String string = path.getFileName().toString();
        if (string.equals(str2)) {
            return true;
        }
        if (path.getFileSystem() == defaultFileSystem) {
            if (isMacOS && Normalizer.isNormalized(string, Normalizer.Form.NFD)) {
                Normalizer.Form form = Normalizer.Form.NFC;
                if (Normalizer.isNormalized(str2, form) && Normalizer.normalize(string, form).equals(str2)) {
                    return true;
                }
            }
            if (string.equalsIgnoreCase(str2)) {
                try {
                    return path.toRealPath(LinkOption.NOFOLLOW_LINKS).getFileName().toString().equals(str2);
                } catch (IOException unused) {
                }
            }
        }
        return false;
    }

    public boolean isSameFile(PathFileObject pathFileObject) {
        return this.path.equals(pathFileObject.path);
    }

    @Override // javax.tools.FileObject
    public InputStream openInputStream() throws IOException {
        this.fileManager.updateLastUsedTime();
        return Files.newInputStream(this.path, new OpenOption[0]);
    }

    @Override // javax.tools.FileObject
    public OutputStream openOutputStream() throws IOException {
        this.fileManager.updateLastUsedTime();
        this.fileManager.flushCache(this);
        ensureParentDirectoriesExist();
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(this.path, new OpenOption[0]);
        this.fileManager.newOutputToPath(this.path);
        return outputStreamNewOutputStream;
    }

    @Override // javax.tools.FileObject
    public Reader openReader(boolean z) throws IOException {
        BaseFileManager baseFileManager = this.fileManager;
        return new InputStreamReader(openInputStream(), baseFileManager.getDecoder(baseFileManager.getEncodingName(), z));
    }

    @Override // javax.tools.FileObject
    public Writer openWriter() throws IOException {
        this.fileManager.updateLastUsedTime();
        this.fileManager.flushCache(this);
        ensureParentDirectoriesExist();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(this.path, new OpenOption[0]), this.fileManager.getEncodingName());
        this.fileManager.newOutputToPath(this.path);
        return outputStreamWriter;
    }

    public String toString() {
        return getClass().getSimpleName() + "[" + this.path + "]";
    }

    @Override // javax.tools.FileObject
    public URI toUri() {
        return this.path.toUri();
    }

    public static class JRTFileObject extends PathFileObject {
        @Override // javax.tools.FileObject
        public String getName() {
            return this.path.toString();
        }

        @Override // com.sun.tools.javac.file.PathFileObject
        public PathFileObject getSibling(String str) {
            return new JRTFileObject(this.fileManager, this.path.resolveSibling(str));
        }

        @Override // com.sun.tools.javac.file.PathFileObject
        public String inferBinaryName(Iterable<? extends Path> iterable) {
            Path path = this.path;
            return PathFileObject.toBinaryName(path.subpath(2, path.getNameCount()));
        }

        @Override // com.sun.tools.javac.file.PathFileObject
        public String toString() {
            return "JRTFileObject[" + this.path + "]";
        }

        private JRTFileObject(BaseFileManager baseFileManager, Path path) {
            super(baseFileManager, path);
        }
    }

    public static String toBinaryName(RelativePath relativePath) {
        return toBinaryName(relativePath.path, PsuedoNames.PSEUDONAME_ROOT);
    }

    private static String toBinaryName(String str, String str2) {
        return removeExtension(str).replace(str2, Constants.ATTRVAL_THIS);
    }
}
