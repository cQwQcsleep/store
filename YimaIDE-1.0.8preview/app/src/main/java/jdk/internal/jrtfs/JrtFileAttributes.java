package jdk.internal.jrtfs;

import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Formatter;
import jdk.internal.jimage.ImageReader;
import org.bouncycastle.pqc.crypto.newhope.NewHope;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class JrtFileAttributes implements BasicFileAttributes {
    private final ImageReader.Node node;

    public JrtFileAttributes(ImageReader.Node node) {
        this.node = node;
    }

    public long compressedSize() {
        return this.node.compressedSize();
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public FileTime creationTime() {
        return this.node.getFileAttributes().creationTime();
    }

    public String extension() {
        return this.node.extension();
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public Object fileKey() {
        return this.node.resolveLink(true);
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public boolean isDirectory() {
        return this.node.isDirectory();
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public boolean isOther() {
        return false;
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public boolean isRegularFile() {
        return !isDirectory();
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public boolean isSymbolicLink() {
        return this.node.isLink();
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public FileTime lastAccessTime() {
        return this.node.getFileAttributes().lastAccessTime();
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public FileTime lastModifiedTime() {
        return this.node.getFileAttributes().lastModifiedTime();
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public long size() {
        return this.node.size();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(NewHope.POLY_SIZE);
        Formatter formatter = new Formatter(sb);
        try {
            if (creationTime() != null) {
                formatter.format("    creationTime    : %tc%n", Long.valueOf(creationTime().toMillis()));
            } else {
                formatter.format("    creationTime    : null%n", new Object[0]);
            }
            if (lastAccessTime() != null) {
                formatter.format("    lastAccessTime  : %tc%n", Long.valueOf(lastAccessTime().toMillis()));
            } else {
                formatter.format("    lastAccessTime  : null%n", new Object[0]);
            }
            formatter.format("    lastModifiedTime: %tc%n", Long.valueOf(lastModifiedTime().toMillis()));
            formatter.format("    isRegularFile   : %b%n", Boolean.valueOf(isRegularFile()));
            formatter.format("    isDirectory     : %b%n", Boolean.valueOf(isDirectory()));
            formatter.format("    isSymbolicLink  : %b%n", Boolean.valueOf(isSymbolicLink()));
            formatter.format("    isOther         : %b%n", Boolean.valueOf(isOther()));
            formatter.format("    fileKey         : %s%n", fileKey());
            formatter.format("    size            : %d%n", Long.valueOf(size()));
            formatter.format("    compressedSize  : %d%n", Long.valueOf(compressedSize()));
            formatter.format("    extension       : %s%n", extension());
            formatter.close();
            return sb.toString();
        } catch (Throwable th) {
            try {
                formatter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
