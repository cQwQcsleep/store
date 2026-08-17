package com.sun.nio.zipfs;

import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.Formatter;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ZipFileAttributes implements BasicFileAttributes {
    private final ZipFileSystem.Entry e;

    public ZipFileAttributes(ZipFileSystem.Entry entry) {
        this.e = entry;
    }

    public byte[] comment() {
        byte[] bArr = this.e.comment;
        if (bArr != null) {
            return Arrays.copyOf(bArr, bArr.length);
        }
        return null;
    }

    public long compressedSize() {
        return this.e.csize;
    }

    public long crc() {
        return this.e.crc;
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public FileTime creationTime() {
        long j = this.e.ctime;
        if (j != -1) {
            return FileTime.fromMillis(j);
        }
        return null;
    }

    public byte[] extra() {
        byte[] bArr = this.e.extra;
        if (bArr != null) {
            return Arrays.copyOf(bArr, bArr.length);
        }
        return null;
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public Object fileKey() {
        return null;
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public boolean isDirectory() {
        return this.e.isDir();
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public boolean isOther() {
        return false;
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public boolean isRegularFile() {
        return !this.e.isDir();
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public boolean isSymbolicLink() {
        return false;
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public FileTime lastAccessTime() {
        long j = this.e.atime;
        if (j != -1) {
            return FileTime.fromMillis(j);
        }
        return null;
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public FileTime lastModifiedTime() {
        return FileTime.fromMillis(this.e.mtime);
    }

    public int method() {
        return this.e.method;
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public long size() {
        return this.e.size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(1024);
        Formatter formatter = new Formatter(sb);
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
        formatter.format("    crc             : %x%n", Long.valueOf(crc()));
        formatter.format("    method          : %d%n", Integer.valueOf(method()));
        formatter.close();
        return sb.toString();
    }
}
