package com.sun.tools.javac.file;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.nio.file.FileSystem;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class RelativePath implements Comparable<RelativePath> {
    protected final String path;

    public RelativePath(String str) {
        this.path = str;
    }

    public abstract String basename();

    @Override // java.lang.Comparable
    public int compareTo(RelativePath relativePath) {
        return this.path.compareTo(relativePath.path);
    }

    public abstract RelativeDirectory dirname();

    public boolean equals(Object obj) {
        return (obj instanceof RelativePath) && this.path.equals(((RelativePath) obj).path);
    }

    public String getPath() {
        return this.path;
    }

    public int hashCode() {
        return this.path.hashCode();
    }

    public Path resolveAgainst(FileSystem fileSystem) throws InvalidPathException {
        return fileSystem.getRootDirectories().iterator().next().resolve(this.path.replace(PsuedoNames.PSEUDONAME_ROOT, fileSystem.getSeparator()));
    }

    public String toString() {
        return "RelPath[" + this.path + "]";
    }

    public static class RelativeFile extends RelativePath {
        public RelativeFile(RelativeDirectory relativeDirectory, String str) {
            this(relativeDirectory.path + str);
        }

        public static RelativeFile forClass(CharSequence charSequence, JavaFileObject.Kind kind) {
            return new RelativeFile(charSequence.toString().replace('.', '/') + kind.extension);
        }

        @Override // com.sun.tools.javac.file.RelativePath
        public String basename() {
            return this.path.substring(this.path.lastIndexOf(47) + 1);
        }

        @Override // com.sun.tools.javac.file.RelativePath, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(RelativePath relativePath) {
            return super.compareTo(relativePath);
        }

        @Override // com.sun.tools.javac.file.RelativePath
        public RelativeDirectory dirname() {
            return new RelativeDirectory(this.path.substring(0, this.path.lastIndexOf(47) + 1));
        }

        public ZipEntry getZipEntry(ZipFile zipFile) {
            return zipFile.getEntry(this.path);
        }

        @Override // com.sun.tools.javac.file.RelativePath
        public String toString() {
            return "RelativeFile[" + this.path + "]";
        }

        public RelativeFile(String str) {
            super(str);
            if (str.endsWith(PsuedoNames.PSEUDONAME_ROOT)) {
                w01.a(str);
                throw null;
            }
        }

        public RelativeFile(RelativeDirectory relativeDirectory, RelativePath relativePath) {
            this(relativeDirectory, relativePath.path);
        }
    }

    public static class RelativeDirectory extends RelativePath {
        /* JADX WARN: Illegal instructions before constructor call */
        public RelativeDirectory(String str) {
            if (str.length() != 0 && !str.endsWith(PsuedoNames.PSEUDONAME_ROOT)) {
                str = str.concat(PsuedoNames.PSEUDONAME_ROOT);
            }
            super(str);
        }

        public static RelativeDirectory forPackage(CharSequence charSequence) {
            return new RelativeDirectory(charSequence.toString().replace('.', '/'));
        }

        @Override // com.sun.tools.javac.file.RelativePath
        public String basename() {
            int length = this.path.length();
            String str = this.path;
            if (length == 0) {
                return str;
            }
            return this.path.substring(str.lastIndexOf(47, length - 2) + 1, length - 1);
        }

        @Override // com.sun.tools.javac.file.RelativePath, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(RelativePath relativePath) {
            return super.compareTo(relativePath);
        }

        public boolean contains(RelativePath relativePath) {
            return relativePath.path.length() > this.path.length() && relativePath.path.startsWith(this.path);
        }

        @Override // com.sun.tools.javac.file.RelativePath
        public RelativeDirectory dirname() {
            int length = this.path.length();
            if (length == 0) {
                return this;
            }
            return new RelativeDirectory(this.path.substring(0, this.path.lastIndexOf(47, length - 2) + 1));
        }

        @Override // com.sun.tools.javac.file.RelativePath
        public String toString() {
            return "RelativeDirectory[" + this.path + "]";
        }

        public RelativeDirectory(RelativeDirectory relativeDirectory, String str) {
            this(relativeDirectory.path + str);
        }
    }

    public Path resolveAgainst(Path path) throws InvalidPathException {
        return path.resolve(this.path.replace(PsuedoNames.PSEUDONAME_ROOT, path.getFileSystem().getSeparator()));
    }
}
